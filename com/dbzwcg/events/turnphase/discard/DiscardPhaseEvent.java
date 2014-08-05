/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.discard;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.discard.DiscardPhase;
import com.dbzwcg.match.phase.discard.DiscardPhaseDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DiscardPhaseEvent extends MatchEvent {
    
    public DiscardPhaseEvent(GameMechanic gameMechanic, MatchPlayer target) {
        super(gameMechanic, target);
        super.player = target;
        super.callbacks = target.getDiscardPhaseCallbacks();
        super.nextEvent = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        DiscardPhase discardPhase = (DiscardPhase) super.getSourceActor().getGameMechanic();
        discardPhase.setOwner(super.player);
        discardPhase.setPlayers(m.getPlayers());
        discardPhase.setCursor(super.player);
        DiscardPhaseDLO.playerDiscardPhase(m);
    }

    @Override
    public String logMessage() {
        return "DISCARD PHASE";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
