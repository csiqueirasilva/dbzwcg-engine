/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.card.effects.interfaces;

import com.dbzwcg.match.damage.MatchDamage;

/**
 *
 * @author csiqueira
 */
public interface AttackEffect {
    public MatchDamage calculateDamage();
}
