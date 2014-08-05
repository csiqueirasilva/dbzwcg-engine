/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.rejuvenation;

import com.dbzwcg.events.CardMoveEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.play.MoveCardMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class RejuvenationPhaseRejuvenateEvent extends MatchEvent {
    
    public RejuvenationPhaseRejuvenateEvent(GameMechanic gameMechanic, MatchPlayer target) {
        super(gameMechanic, target);
        super.player = target;
        super.callbacks = null;
        super.nextEvent = AfterRejuvenationPhaseEvent.class;
    }

    private void rejuvenate (Match m, RejuvenationPhase rejuvenationPhase) throws NullPointerException {
        MatchCard card = super.player.getDiscardPile().get(super.player.getDiscardPile().size() - 1);
        MoveCardMechanic mcm = new MoveCardMechanic(card, super.player.getDiscardPile(), super.player.getLifeDeck());
        CardMoveEvent rejuvenationCardEvent = new CardMoveEvent(rejuvenationPhase, super.player, mcm, 0);
        MatchDLO.applyEvent(m, rejuvenationCardEvent);
    }
    
    @Override
    protected void phaseEffect (Match m) {
        RejuvenationPhase rejuvenationPhase = (RejuvenationPhase) super.sourceActor.getGameMechanic();

        try {
            this.rejuvenate(m, rejuvenationPhase);
            rejuvenationPhase.setRejuvenated(true);
        } catch (NullPointerException e) {
            rejuvenationPhase.setRejuvenated(false);
        }
    }
    
    @Override
    public String logMessage() {
        return "REJUVENATION PHASE REJUVENATE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
