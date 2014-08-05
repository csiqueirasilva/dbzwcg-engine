/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.discard;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.discard.DiscardPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforeDiscardPhaseEvent extends MatchEvent {
    
    public BeforeDiscardPhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getBeforeDiscardPhaseCallbacks();
        super.nextEvent = DiscardPhaseEvent.class;
    }
    
    @Override
    public String logMessage() {
        return "BEFORE DISCARD PHASE EVENT";
    }

    @Override
    protected void phaseEffect(Match m) {
        DiscardPhase phase = (DiscardPhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setDiscardPhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}