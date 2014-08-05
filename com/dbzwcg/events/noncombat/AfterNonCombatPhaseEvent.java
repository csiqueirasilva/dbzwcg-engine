/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.noncombat;

import com.dbzwcg.events.turnphase.powerup.DecidePowerUpPhaseEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AfterNonCombatPhaseEvent extends MatchEvent {
    
    public AfterNonCombatPhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getAfterCombatPhaseCallbacks();
        super.nextEvent = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        DecidePowerUpPhaseEvent bdpe = new DecidePowerUpPhaseEvent(super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "AFTER NON COMBAT PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}