/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.noncombat;

import com.dbzwcg.events.turnphase.powerup.BeforePowerUpPhaseEvent;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.pur.PowerUpPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class SkipNonCombatPhaseEvent extends MatchEvent {

    public SkipNonCombatPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        BeforePowerUpPhaseEvent bdpe = new BeforePowerUpPhaseEvent(new PowerUpPhase(), super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "SKIP NON COMBAT PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
