/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.sourcecards.personalities;

import com.dbzwcg.types.AlignmentType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class PersonalityCardDLO {

    private PersonalityCardDLO() {
    }
    
    public static void convertSourceCardToPersonalityCard(Integer id, List<String> powerStages, AlignmentType alignment, String PUR, Integer level) {
        (new PersonalityCardDAO()).convertSourceCardToPersonalityCard(id, powerStages, alignment, PUR, level);
    }
}