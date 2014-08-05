/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.draw;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public class DrawPhase extends Phase {
    public DrawPhase() {
        super.type = PhaseType.DRAW;
    }
    
    @Override
    public String getName() {
        return "DRAW PHASE";
    }
}