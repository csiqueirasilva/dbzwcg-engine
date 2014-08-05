/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.card.effects;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.cards.sourcecards.SourceCard;

/**
 *
 * @author csiqueira
 */
public class MatchCardEffectDLO {

    public static MatchCardEffect getEffect(InstancedCard card) {
        SourceCard source = card.getSourceCard();
        String collection = source.getCollectionType().getValue().toLowerCase();
        String saga = source.getSaga().toString().toLowerCase();
        String number = source.getNumber().toLowerCase();
        String classPath = "com.dbzwcg.match.card." + collection + "." + saga + ".C" + number;

        MatchCardEffect mce = null;

        try {
            mce = (MatchCardEffect) Class.forName(classPath).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("ERROR CLASS CREATION ERROR: " + classPath);
            System.exit(1);
        }

        return mce;
    }
}