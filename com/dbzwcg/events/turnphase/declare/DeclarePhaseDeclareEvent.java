/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.declare;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.turns.Turn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.combat.CombatPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DeclarePhaseDeclareEvent extends MatchEvent {

    public DeclarePhaseDeclareEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
        super.callbacks = null;
        super.nextEvent = AfterDeclarePhaseEvent.class;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        Turn lastTurn = MatchDLO.getLastTurn(m);
        lastTurn.setCombatPhase(new CombatPhase());
    }

    @Override
    public String logMessage() {
        return "DECLARING COMBAT EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
