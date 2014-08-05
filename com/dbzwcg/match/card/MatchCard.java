package com.dbzwcg.match.card;

import com.dbzwcg.cards.proxycards.ProxyCard;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.card.effects.MatchCardEffect;
import com.dbzwcg.match.players.MatchPlayer;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchCard extends ProxyCard {
    protected Match match;
    protected MatchCardEffect effect;
    protected Boolean played;
    protected Integer numberOfUses;
    protected MatchPlayer control;
    protected MatchCard attachedTo;
    protected List<MatchCard> attachedCards;
    
    public MatchCardEffect getEffect() {
        return effect;
    }

    public void setEffect(MatchCardEffect effect) {
        this.effect = effect;
    }

    public Match getMatch() {
        return match;
        
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
