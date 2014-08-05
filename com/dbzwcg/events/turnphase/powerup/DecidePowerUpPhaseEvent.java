/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.powerup;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.pur.PowerUpPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DecidePowerUpPhaseEvent extends MatchEvent {
    public DecidePowerUpPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe;
        if(super.player.getSkipPowerUpPhase()) {
            bdpe = new SkipPowerUpPhaseEvent(player);
        } else {
            bdpe = new BeforePowerUpPhaseEvent(new PowerUpPhase(), super.player);
        }
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "DECIDING POWER UP PHASE";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
