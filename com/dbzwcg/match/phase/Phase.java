/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.types.PhaseType;

/**
 *
 * @author csiqueira
 */
public abstract class Phase extends GameMechanic {
    protected PhaseType type;
            
    public PhaseType getType() {
        return this.type;
    }
}