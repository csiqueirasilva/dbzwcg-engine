/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.cardfieldsources.CardFieldSource;
import com.dbzwcg.gamemechanics.cardfieldsources.CardFieldSourceDLO;
import com.dbzwcg.gamemechanics.piles.PileOutOfCards;
import com.dbzwcg.gamemechanics.play.MoveCardMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.cardgroup.MatchCardGroup;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.callback.returns.CallbackReturn;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.types.CardFieldType;

/**
 *
 * @author csiqueira
 */
public class CardMoveEvent extends MatchEvent {

    private CardFieldSource source;
    private CardFieldSource target;

    public CardFieldSource getSource() {
        return source;
    }

    public void setSource(CardFieldSource source) {
        this.source = source;
    }

    public CardFieldSource getTarget() {
        return target;
    }

    public void setTarget(CardFieldSource target) {
        this.target = target;
    }

    @Override
    public String logMessage() {
        return "CARD MOVE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void phaseEffect(Match m) {
        if (CardFieldSource.validate(this.source) && CardFieldSource.validate(this.target)) {
            MatchCardGroup sourceGroup = CardFieldSourceDLO.getGroupFromSource(super.player, this.source);
            MatchCardGroup targetGroup = CardFieldSourceDLO.getGroupFromSource(super.player, this.target);
            
            CallbackReturn crRemove = sourceGroup.solveRemoveCallbacks(m, super.player, this);
            CallbackReturn crInsert = targetGroup.solveInsertCallbacks(m, super.player, this);

            if(!crRemove.getSkipEventEffect() && !crInsert.getSkipEventEffect() && sourceGroup.size() > 0) {
                MatchCard c = sourceGroup.removeCardByIdx(m, super.player, this);
                targetGroup.insertCardByIdx(m, super.player, c, this);
            } else {
                GameMechanic negator;
                if(sourceGroup.size() == 0) {
                    negator = new PileOutOfCards();
                } else {
                    negator = crRemove.getMechanicNegator() != null ? crRemove.getMechanicNegator() : crInsert.getMechanicNegator();
                }
                MatchDLO.applyEvent(m, new FailedMoveEvent(negator, sourceGroup.get(this.source.getIndex())));
            }
        }
    }

    public CardMoveEvent(GameMechanic source, MatchPlayer player, MoveCardMechanic cardMechanic) {
        super(source, player);
        super.player = player;
        super.nextEvent = null;
        super.callbacks = player.getTransferCallbacks();
        this.source = new CardFieldSource();
        this.target = new CardFieldSource();
       
        InstancedCard card = cardMechanic.getCardMoved();
        Integer idx = cardMechanic.getSourceGroup().indexOf(card);
        CardFieldType ft = cardMechanic.getSourceGroup().getFieldType();

        this.source.setCard(card);
        this.source.setField(ft);
        this.source.setIndex(idx);

        ft = cardMechanic.getTargetGroup().getFieldType();
        
        this.target.setIndex(cardMechanic.getTargetGroup().size());
        this.target.setField(ft);
    }
    
    public CardMoveEvent(GameMechanic source, MatchPlayer player, MoveCardMechanic cardMechanic, Integer targetIndex) {
        this(source, player, cardMechanic);
        this.target.setIndex(targetIndex);
    }
}