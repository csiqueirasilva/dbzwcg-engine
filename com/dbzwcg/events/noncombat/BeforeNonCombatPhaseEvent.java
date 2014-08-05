/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.noncombat;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.noncombat.NonCombatPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforeNonCombatPhaseEvent extends MatchEvent {
    
    public BeforeNonCombatPhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getBeforeNonCombatPhaseCallbacks();
        super.nextEvent = NonCombatPhaseEvent.class;
    }
    
    @Override
    public String logMessage() {
        return "BEFORE NON COMBAT PHASE EVENT";
    }

    @Override
    protected void phaseEffect(Match m) {
        NonCombatPhase phase = (NonCombatPhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setNonCombatPhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}