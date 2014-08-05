/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.gamemechanics.combat;

import com.dbzwcg.gamemechanics.GameMechanic;

/**
 *
 * @author csiqueira
 */
public class FinalPhysicalAttack extends GameMechanic {
    final static private String FINAL_PHYSICAL_ATTACK_NAME = "Final Physical Attack";
    
    @Override
    public String getName() {
        return FINAL_PHYSICAL_ATTACK_NAME;
    }   
}