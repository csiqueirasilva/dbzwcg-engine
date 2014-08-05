/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.combat.internal;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class InternalCombatPhase extends GameMechanic {

    private Integer phaseNumber;
    private MatchPlayer attackingPlayer;
    private MatchPlayer defendingPlayer;

    public Integer getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(Integer phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public MatchPlayer getAttackingPlayer() {
        return attackingPlayer;
    }

    public void setAttackingPlayer(MatchPlayer attackingPlayer) {
        this.attackingPlayer = attackingPlayer;
    }

    public MatchPlayer getDefendingPlayer() {
        return defendingPlayer;
    }

    public void setDefendingPlayer(MatchPlayer defendingPlayer) {
        this.defendingPlayer = defendingPlayer;
    }

    @Override
    public String getName() {
        return "INTERNAL COMBAT PHASE";
    }
}
