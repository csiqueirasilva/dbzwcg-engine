/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.pur;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public class PowerUpPhase extends Phase {
    public PowerUpPhase() {
        super.type = PhaseType.POWER_UP;
    } 
    
    @Override
    public String getName() {
        return "POWER UP RATING PHASE";
    }
}
