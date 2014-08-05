/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.discard;

import com.dbzwcg.events.turn.PlayerFinishTurnEvent;
import com.dbzwcg.events.turnphase.rejuvenation.BeforeRejuvenationPhaseEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.turns.EndTurn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AfterDiscardPhaseEvent extends MatchEvent {
    
    public AfterDiscardPhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getAfterDiscardPhaseCallbacks();
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe;
        
        if(MatchDLO.getLastTurn(m).getCombatPhase() == null) {
            bdpe = new BeforeRejuvenationPhaseEvent(new RejuvenationPhase(), super.player);
        } else {
            bdpe = new PlayerFinishTurnEvent(new EndTurn(), super.player);
        }
        
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "AFTER DISCARD PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}