/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.card.effects.interfaces;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public interface UseWhenNeededEffect {
    public Boolean checkUseWhenNeeded(Match m, MatchEvent event, MatchPlayer player, Boolean before);
}