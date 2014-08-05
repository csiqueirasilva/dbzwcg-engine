/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.powerup;

import com.dbzwcg.events.turnphase.declare.DecideDeclarePhaseEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AfterPowerUpPhaseEvent extends MatchEvent {

    public AfterPowerUpPhaseEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
        super.callbacks = player.getAfterPowerUpPhaseCallbacks();
        super.nextEvent = null;
    }

    @Override
    public String logMessage() {
        return "AFTER POWER UP PHASE";
    }

    @Override
    protected void phaseEffect(Match m) {
        DecideDeclarePhaseEvent bdpe = new DecideDeclarePhaseEvent(super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}