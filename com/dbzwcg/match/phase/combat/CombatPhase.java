/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.combat;

import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.match.phase.combat.internal.InternalCombatPhase;
import com.dbzwcg.types.PhaseType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class CombatPhase extends Phase {
    private List<InternalCombatPhase> phases;
    private MatchPlayer declaringPlayer;
    private MatchPlayer declaredPlayer;

    public CombatPhase() {
        super.type = PhaseType.COMBAT;
    }

    public List<InternalCombatPhase> getPhases() {
        return phases;
    }

    public void setPhases(List<InternalCombatPhase> phases) {
        this.phases = phases;
    }

    public MatchPlayer getDeclaringPlayer() {
        return declaringPlayer;
    }

    public void setDeclaringPlayer(MatchPlayer declaringPlayer) {
        this.declaringPlayer = declaringPlayer;
    }

    public MatchPlayer getDeclaredPlayer() {
        return declaredPlayer;
    }

    public void setDeclaredPlayer(MatchPlayer declaredPlayer) {
        this.declaredPlayer = declaredPlayer;
    }

    @Override
    public String getName() {
        return "COMBAT PHASE";
    }
}
