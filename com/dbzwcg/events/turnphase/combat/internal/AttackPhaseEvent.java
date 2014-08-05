/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.combat.internal;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.combat.CombatPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AttackPhaseEvent extends MatchEvent {
    
    public AttackPhaseEvent(CombatPhase phase, MatchPlayer attackingPlayer) {
        super(phase, attackingPlayer);
        super.player = attackingPlayer;
        super.callbacks = super.player.getAttackPhaseCallbacks();
    }
    
    @Override
    protected void phaseEffect(Match m) {
        
    }

    @Override
    public String logMessage() {
        return "ATTACK PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
