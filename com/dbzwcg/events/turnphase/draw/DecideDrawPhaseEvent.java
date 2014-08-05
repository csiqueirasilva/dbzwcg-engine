/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.draw;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.draw.DrawPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DecideDrawPhaseEvent extends MatchEvent {

    public DecideDrawPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent event;
        
        if(super.player.getSkipDrawPhase()) {
            event = new SkipDrawPhaseEvent(super.player);
        } else {
            event = new BeforeDrawPhaseEvent(new DrawPhase(), super.player);
        }

        MatchDLO.applyEvent(m, event);
    }

    @Override
    public String logMessage() {
        return "DECIDE DRAW PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
