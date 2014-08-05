/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.combat;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.phase.combat.internal.InternalCombatPhase;
import com.dbzwcg.match.players.MatchPlayer;
import java.util.ArrayList;

/**
 *
 * @author csiqueira
 */
public class CombatPhaseDLO {
    public static CombatPhase createCombatPhase(MatchPlayer declaringPlayer, MatchPlayer declaredPlayer) {
        CombatPhase c = new CombatPhase();
        
        c.setDeclaredPlayer(declaredPlayer);
        c.setDeclaringPlayer(declaringPlayer);
        c.setPhases(new ArrayList<InternalCombatPhase>());
        
        return c;
    }
    
    public static void addInternalPhase(Match m, CombatPhase phase) {
        InternalCombatPhase newIcp = new InternalCombatPhase();
        try {
            InternalCombatPhase icp = phase.getPhases().get(phase.getPhases().size() - 1);
            newIcp.setAttackingPlayer(icp.getDefendingPlayer());
            newIcp.setDefendingPlayer(icp.getAttackingPlayer());
            newIcp.setPhaseNumber(icp.getPhaseNumber() + 1);
        } catch (NullPointerException e) {
            newIcp.setAttackingPlayer(phase.getDeclaringPlayer());
            newIcp.setDefendingPlayer(phase.getDeclaredPlayer());
            newIcp.setPhaseNumber(1);
        }
        phase.getPhases().add(newIcp);
    }
}
