/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.card;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.card.effects.MatchCardEffect;
import com.dbzwcg.match.card.effects.MatchCardEffectDLO;

/**
 *
 * @author csiqueira
 */
public class MatchCardDLO {
    
    public static void setCardEffect(MatchCard matchCard, InstancedCard card) {
        MatchCardEffect mce = MatchCardEffectDLO.getEffect(card);
        matchCard.setEffect(mce);
    }
    
    public static MatchCard createMatchCard(Match m, InstancedCard card) {
        MatchCard matchCard = null;
        if(card != null) {
            matchCard = new MatchCard();
            matchCard.setAlternativeArt(card.getAlternativeArt());
            matchCard.setFoil(card.getFoil());
            matchCard.setOfferTrade(false);
            matchCard.setTradeable(false);
            matchCard.setSourceCard(card.getSourceCard());
            matchCard.setSpecularMapPath(card.getSpecularMapPath());
            matchCard.setOwner(card.getOwner());
            matchCard.setTexturePath(card.getTexturePath());
            matchCard.setMatch(m);
            setCardEffect(matchCard, card);
        }
        return matchCard;
    }
}