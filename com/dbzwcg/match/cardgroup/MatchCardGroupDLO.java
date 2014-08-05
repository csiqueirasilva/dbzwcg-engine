/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.cardgroup;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.callback.sets.CallbackSet;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.card.MatchCardDLO;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.types.CardFieldType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchCardGroupDLO {
    public static MatchCardGroup createListMatchCard(Match m, List cards, CardFieldType type, MatchPlayer player) {
        MatchCardGroup list = null;
        if(cards != null) {
            list = new MatchCardGroup();
            InstancedCard card;
            MatchCard matchCard;
            for(int i = 0; i < cards.size(); i++) {
                card = (InstancedCard) cards.get(i);
                if(!(card instanceof MatchCard)) {
                    matchCard = MatchCardDLO.createMatchCard(m, card);
                } else {
                    matchCard = (MatchCard) card;
                }
                list.add(matchCard);
            }
            list.setFieldType(type);
            list.setOwner(player);
            
            if(cards instanceof MatchCardGroup) {
                list.setInsertCallbacks(((MatchCardGroup) cards).getInsertCallbacks());
                list.setRemoveCallbacks(((MatchCardGroup) cards).getRemoveCallbacks());
            } else {
                list.setInsertCallbacks(new CallbackSet());
                list.setRemoveCallbacks(new CallbackSet());
            }
        }
        return list;
    }
}
