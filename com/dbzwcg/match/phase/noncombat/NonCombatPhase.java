/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.noncombat;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public class NonCombatPhase extends Phase {
    public NonCombatPhase() {
        super.type = PhaseType.NON$COMBAT;
    } 
    
    @Override
    public String getName() {
        return "NON COMBAT PHASE";
    }
}
