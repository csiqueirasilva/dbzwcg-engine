/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.gamemechanics.cardfieldsources;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.match.types.CardFieldType;

/**
 *
 * @author csiqueira
 */
public class CardFieldSource {
    private CardFieldType field;
    private InstancedCard card;
    private Integer index;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public CardFieldType getField() {
        return field;
    }

    public void setField(CardFieldType field) {
        this.field = field;
    }

    public InstancedCard getCard() {
        return card;
    }

    public void setCard(InstancedCard card) {
        this.card = card;
    }
    
    public static Boolean validate (CardFieldSource cfs) {
        return cfs != null && cfs.getField() != null && (cfs.getCard() != null || cfs.getIndex() != null);
    }
}