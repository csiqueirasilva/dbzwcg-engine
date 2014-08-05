/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.combat;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.combat.CombatPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforeCombatPhaseEvent extends MatchEvent {

    public BeforeCombatPhaseEvent(GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getBeforeCombatPhaseCallbacks();
        super.nextEvent = EnteringCombatPhaseEvent.class;
    }

    @Override
    public String logMessage() {
        return "BEFORE COMBAT PHASE EVENT";
    }

    @Override
    protected void phaseEffect(Match m) {
        CombatPhase phase = (CombatPhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setCombatPhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}