/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.declare;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public class DeclarePhase extends Phase {
    public DeclarePhase() {
        super.type = PhaseType.DECLARE;
    } 
    
    @Override
    public String getName() {
        return "DECLARE PHASE";
    }
}
