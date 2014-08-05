/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.powerup;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.pur.PowerUpPhase;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforePowerUpPhaseEvent extends MatchEvent {

    public BeforePowerUpPhaseEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
        super.nextEvent = PowerUpPhaseEvent.class;
        super.callbacks = player.getBeforePowerUpPhaseCallbacks();
    }

    @Override
    public String logMessage() {
        return "BEFORE POWER UP PHASE EVENT";
    }

    @Override
    protected void phaseEffect(Match m) {
        PowerUpPhase phase = (PowerUpPhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setPowerUpPhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}