/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.players.ai;

import com.dbzwcg.events.turnphase.discard.DiscardPhaseDiscardCardEvent;
import com.dbzwcg.events.turnphase.rejuvenation.RejuvenationPhaseRejuvenateEvent;
import com.dbzwcg.gamemechanics.play.MoveCardMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.players.MatchPlayerDLO;
import com.dbzwcg.players.Player;

/**
 *
 * @author csiqueira
 */
public class AIPlayerDLO {
    public static AIPlayer createFromPlayer(Player p) {
        AIPlayer aiPlayer = new AIPlayer();

        aiPlayer.setDisplayName(p.getDisplayName());
        aiPlayer.setEmail(p.getEmail());
        aiPlayer.setEnable(p.getEnable());
        aiPlayer.setId(p.getId());
        aiPlayer.setPassword(p.getPassword());
        aiPlayer.setPoints(p.getPoints());
        aiPlayer.setRoles(p.getRoles());
        
        return aiPlayer;
    }

    public static void discardPhasePlay(Match m, MatchPlayer p) {
        Integer cardIndex = (int) ((p.getHand().size() - 1) * Math.random());
        MoveCardMechanic mcm = new MoveCardMechanic(p.getHand().get(cardIndex), p.getDiscardPile(), p.getHand());
        DiscardPhaseDiscardCardEvent dpdce = new DiscardPhaseDiscardCardEvent(m.getCurrentPhase(), p, mcm);
        MatchDLO.applyEvent(m, dpdce);
    }
    
    public static void rejuvenationPhasePlay(Match m, MatchPlayer p) {
        RejuvenationPhase phase = (RejuvenationPhase) m.getCurrentPhase();
        RejuvenationPhaseRejuvenateEvent rpre = new RejuvenationPhaseRejuvenateEvent(phase, p);
        MatchDLO.applyEvent(m, rpre);
    }
    
    public static void useWhenNeededPlay(Match m, MatchPlayer p) {

    }
    
    public static void defenderDefendsPlay(Match m, MatchPlayer p) {
        
    }
    
    public static void attackerAttacksPlay(Match m, MatchPlayer p) {
        
    }
    
    public static void declarePhasePlay(Match m, MatchPlayer p) {
        MatchPlayerDLO.declarePhaseSkipCombat(m, p);
    }
    
    public static void nonCombatPhasePlay(Match m, MatchPlayer p) {
        MatchPlayerDLO.endNonCombatPhase(m, p);
    }
}