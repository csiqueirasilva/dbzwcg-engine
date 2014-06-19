/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.cards.sourcecards.personalities.PersonalityCard;
import com.dbzwcg.decks.Deck;
import com.dbzwcg.decks.DeckDLO;
import com.dbzwcg.decks.DeckEligibleReference;
import com.dbzwcg.players.Player;
import com.dbzwcg.types.LimitType;
import com.dbzwcg.users.user.UserDLO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchDLO {
    
    public static List<DeckEligibleReference> listPlayerDeckEligibility(Player p) {
        List<Deck> decks = DeckDLO.getDecksByUser(p);
        List<DeckEligibleReference> refs = new ArrayList<DeckEligibleReference>();
        
        for(int i = 0; i < decks.size(); i++) {
            DeckEligibleReference ref = new DeckEligibleReference();
            refs.add(ref);
            ref.setDeck(decks.get(i));
            ref.setEligible(DeckDLO.checkDeckPlayable(decks.get(i)));
        }
        
        return refs;
    }
    
    public static void main (String args[]) {
        assemblePlayerMatchData(3, (Player) UserDLO.getUserFromUsername("root@dbzwcg.com"));
    }
    
    public static MatchPlayer assemblePlayerMatchData(Integer deckId, Player player) {
        Deck d = DeckDLO.getDeckById(deckId);
        MatchPlayer p = null;
        
        if(d != null && d.getOwner() != null && d.getOwner().getEmail() != null 
                && player != null && player.getEmail() != null 
                && d.getOwner().getEmail().equals(player.getEmail())) {
            p = getAsMatchPlayer(d, player);
        }
        
        return p;
    }
    
    public static MatchPlayer getAsMatchPlayer(Deck deck, Player player) {
        MatchPlayer p = new MatchPlayer();
        
        MatchMainPersonality mp = new MatchMainPersonality();
        
        if(deck.getAlignment() != null) {
            mp.setAlignment(deck.getAlignment().ordinal());
        }
        
        mp.setAngerLevelNeededToLevel(5);
        mp.setCurrentMainPersonalityLevel(1);
        mp.setCurrentPowerStageAboveZero(5);
        mp.setCurrentAngerLevel(0);
        
        if(player != null) {
            p.setName(player.getDisplayName());
        }
    
        InstancedCard[] mainPersonalityFinalList = new InstancedCard[deck.getMainPersonality().size()];
        
        int addedLevel = LimitType.LEVEL_MIN.getValue() - 1;
        for(int i = 0; i < mainPersonalityFinalList.length; i++) {
            int level = ((PersonalityCard) deck.getMainPersonality().get(0).getSourceCard()).getLevel();
            int index = 0;
            for(int j = 1; j < deck.getMainPersonality().size(); j++) {
                int itLevel = ((PersonalityCard) deck.getMainPersonality().get(j).getSourceCard()).getLevel();
                if(itLevel < level && itLevel > addedLevel) {
                    index = j;
                    level = itLevel;
                }
            }
            mainPersonalityFinalList[i] = deck.getMainPersonality().get(index);
            addedLevel = level;
        }
        
        mp.setPersonalities(Arrays.asList(mainPersonalityFinalList));
        p.setMainPersonality(mp);
        p.setLifeDeck(deck.getCards());
        
        return p;
    }
    
}
