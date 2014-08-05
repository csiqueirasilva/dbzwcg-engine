/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.cardgroup;

import com.dbzwcg.events.CardMoveEvent;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.callback.returns.CallbackReturn;
import com.dbzwcg.match.callback.sets.CallbackSet;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.types.CardFieldType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchCardGroup extends ArrayList<MatchCard> {

    protected MatchPlayer owner;
    protected CallbackSet removeCallbacks;
    protected CallbackSet insertCallbacks;
    protected CardFieldType fieldType;

    public MatchCardGroup() {
    }

    public MatchCardGroup(List<MatchCard> list) {
        super(list);
    }
    
    public CardFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(CardFieldType fieldType) {
        this.fieldType = fieldType;
    }
    
    public MatchPlayer getOwner() {
        return owner;
    }

    public void setOwner(MatchPlayer owner) {
        this.owner = owner;
    }

    public CallbackSet getRemoveCallbacks() {
        return removeCallbacks;
    }

    public void setRemoveCallbacks(CallbackSet removeCallbacks) {
        this.removeCallbacks = removeCallbacks;
    }

    public CallbackSet getInsertCallbacks() {
        return insertCallbacks;
    }

    public void setInsertCallbacks(CallbackSet insertCallbacks) {
        this.insertCallbacks = insertCallbacks;
    }

    public Boolean insertCardByIdx(Match m, MatchPlayer p, MatchCard card, CardMoveEvent event) {
        Boolean funcRet = false;

        CallbackReturn ret = solveInsertCallbacks(m, p, event);
        
        if (!ret.getSkipEventEffect()) {
            this.add(event.getTarget().getIndex(), card);
            funcRet = true;
        }

        return funcRet;
    }

    public CallbackReturn solveInsertCallbacks(Match m, MatchPlayer p, CardMoveEvent event) {
        return this.insertCallbacks.resolveCallbacks(m, event);
    }

    public CallbackReturn solveRemoveCallbacks(Match m, MatchPlayer p, CardMoveEvent event) {
        return this.removeCallbacks.resolveCallbacks(m, event);
    }

    public MatchCard removeCardByIdx(Match m, MatchPlayer p, CardMoveEvent event) {
        MatchCard ic = null;

        CallbackReturn ret = solveRemoveCallbacks(m, p, event);

        if (!ret.getSkipEventEffect()) {
            try {
                ic = this.get(event.getSource().getIndex());
                this.remove(ic);
            } catch (IndexOutOfBoundsException e) {
                if(this.fieldType == CardFieldType.lifeDeck) {
                    MatchDLO.checkGameOver(m, p, event);
                }
            }
        }

        return ic;
    }
}