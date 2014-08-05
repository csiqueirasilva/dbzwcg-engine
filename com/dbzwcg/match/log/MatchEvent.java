/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.log;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.callback.returns.CallbackReturn;
import com.dbzwcg.match.callback.sets.CallbackSet;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.card.effects.interfaces.UseWhenNeededEffect;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author csiqueira
 */
public abstract class MatchEvent {

    protected MatchEventCreator sourceActor;
    protected MatchEventCreator targetActor;
    protected MatchPlayer player;
    protected Class nextEvent;
    protected CallbackSet callbacks;
    protected List<DateTime> interruptions;
    protected Integer interruptionCursor; // probably private

    protected abstract void phaseEffect(Match m);

    public abstract String logMessage();

    public abstract void applyFromDatabase(Match m);

    private Boolean checkCardGroupUseWhenNeeded(List mcg, Match m, MatchPlayer player, Boolean before) {
        Boolean ret = false;
        for (Object o : mcg) {
            MatchCard c = (MatchCard) o;
            if (c.getEffect() instanceof UseWhenNeededEffect) {
                ret = ((UseWhenNeededEffect) c.getEffect()).checkUseWhenNeeded(m, this, player, before);
            }
        }
        return ret;
    }

    private Boolean checkUseWhenNeeded(Match m, Boolean before) { /* Before and After */
        Boolean ret = false;
        Integer playerIndex = m.getPlayers().indexOf(this.player);
        int i = playerIndex;
        do {
            MatchPlayer p = m.getPlayers().get(i);
            ret = ret || this.checkCardGroupUseWhenNeeded(p.getDiscardPile(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getLifeDeck(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getRemovedFromTheGame(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getSetAside(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getMainPersonality().getPersonalities(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getDragonballs(), m, p, before)
                    || this.checkCardGroupUseWhenNeeded(p.getNonCombats(), m, p, before);
            i = (i+1)%m.getPlayers().size();
        } while (i != playerIndex);

        if (ret) {
            DateTime dt = new DateTime();
            this.interruptions.add(dt);
        }

        return ret;
    }

    public void resolveEffect(Match m) {
        if (this.player != null) {
            if (!this.checkUseWhenNeeded(m, true)) {
                CallbackReturn ret = null;

                if (this.callbacks != null) {
                    ret = this.callbacks.resolveCallbacks(m, this);
                }

                if (ret == null || !ret.getSkipEventEffect()) {
                    phaseEffect(m);
                }

                if (!this.checkUseWhenNeeded(m, false)) {
                    if (this.nextEvent != null && (ret == null || !ret.getSkipHookedEvent())) {
                        MatchEvent bdpe;

                        try {
                            bdpe = (MatchEvent) nextEvent.getConstructor(GameMechanic.class, MatchPlayer.class).newInstance(this.sourceActor.getGameMechanic(), this.player);
                            MatchDLO.applyEvent(m, bdpe);
                        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(MatchEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (this.nextEvent != null && (ret == null || !ret.getSkipHookedEvent())) {
                        MatchEvent bdpe;

                        try {
                            bdpe = (MatchEvent) nextEvent.getConstructor(GameMechanic.class, MatchPlayer.class).newInstance(this.sourceActor.getGameMechanic(), this.player);
                            ResumeEvent re = new ResumeEvent(this.player, bdpe);
                            MatchDLO.applyResumeEvent(m, re);
                        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(MatchEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                ResumeEvent re = new ResumeEvent(this.player, this);
                MatchDLO.applyResumeEvent(m, re);
            }
        }
    }

    // CONSTRUCTORS
    protected MatchEvent() {
        this.sourceActor = new MatchEventCreator();
        this.targetActor = new MatchEventCreator();
        this.interruptions = new ArrayList<DateTime>();
    }

    public MatchEvent(MatchCard card) {
        this();
        this.sourceActor.setCard(card);
        this.targetActor.setCard(card);
    }

    public MatchEvent(MatchCard sourceCard, MatchCard targetCard) {
        this();
        this.sourceActor.setCard(sourceCard);
        this.targetActor.setCard(targetCard);
    }

    public MatchEvent(MatchPlayer generator) {
        this();
        this.sourceActor.setPlayer(generator.getPlayer());
        this.targetActor.setPlayer(generator.getPlayer());
    }

    public MatchEvent(MatchPlayer generator, MatchPlayer target) {
        this();
        this.sourceActor.setPlayer(generator.getPlayer());
        this.targetActor.setPlayer(target.getPlayer());
    }

    public MatchEvent(GameMechanic generator, MatchPlayer target) {
        this();
        this.sourceActor.setGameMechanic(generator);
        this.targetActor.setPlayer(target.getPlayer());
    }

    public MatchEvent(GameMechanic generator, MatchCard target) {
        this();
        this.sourceActor.setGameMechanic(generator);
        this.targetActor.setCard(target);
    }

    public MatchEvent(MatchCard source, MatchPlayer target) {
        this();
        this.sourceActor.setCard(source);
        this.targetActor.setPlayer(target.getPlayer());
    }

    public MatchEvent(MatchPlayer source, MatchCard target) {
        this();
        this.targetActor.setCard(target);
        this.sourceActor.setPlayer(source.getPlayer());
    }

    public MatchEvent(MatchCard source, GameMechanic target) {
        this();
        this.sourceActor.setCard(source);
        this.targetActor.setGameMechanic(target);
    }

    /* GETTER AND SETTERS */
    public MatchEventCreator getSourceActor() {
        return sourceActor;
    }

    public void setSourceActor(MatchEventCreator sourceActor) {
        this.sourceActor = sourceActor;
    }

    public MatchEventCreator getTargetActor() {
        return targetActor;
    }

    public void setTargetActor(MatchEventCreator targetActor) {
        this.targetActor = targetActor;
    }
}