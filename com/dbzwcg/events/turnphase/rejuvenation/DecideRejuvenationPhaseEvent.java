/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.rejuvenation;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DecideRejuvenationPhaseEvent extends MatchEvent {
    
    public DecideRejuvenationPhaseEvent(MatchPlayer declaringPlayer) {
        super(declaringPlayer);
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe;
        if(super.player.getSkipRejuvenationPhase()) {
            bdpe = new SkipRejuvenationPhaseEvent(super.player);
        } else {
            bdpe = new BeforeRejuvenationPhaseEvent(new RejuvenationPhase(), super.player);
        }
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "DECIDING REJUVENATION PHASE";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
