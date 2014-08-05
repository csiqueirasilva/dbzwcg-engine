/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.noncombat;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.noncombat.NonCombatPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DecideNonCombatPhaseEvent extends MatchEvent {

    public DecideNonCombatPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe;
        if(super.player.getSkipNonCombatPhase()) {
            bdpe = new SkipNonCombatPhaseEvent(player);
        } else {
            bdpe = new BeforeNonCombatPhaseEvent(new NonCombatPhase(), super.player);
        }
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "DECIDING NON COMBAT PHASE";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
