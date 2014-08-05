/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.gamemechanics.play;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.cardgroup.MatchCardGroup;

/**
 *
 * @author csiqueira
 */
public class MoveCardMechanic extends GameMechanic {

    public MoveCardMechanic(InstancedCard card, MatchCardGroup target, MatchCardGroup source) {
        this.targetGroup = target;
        this.cardMoved = card;
        this.sourceGroup = source;
    }

    protected InstancedCard cardMoved;
    protected MatchCardGroup targetGroup;
    protected MatchCardGroup sourceGroup;

    public InstancedCard getCardMoved() {
        return cardMoved;
    }

    public void setCardMoved(InstancedCard cardPlayed) {
        this.cardMoved = cardPlayed;
    }

    public MatchCardGroup getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(MatchCardGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    public MatchCardGroup getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(MatchCardGroup sourceGroup) {
        this.sourceGroup = sourceGroup;
    }
    
    @Override
    public String getName() {
        return "Moving " + this.cardMoved.getSourceCard().getName();
    }
    
}
