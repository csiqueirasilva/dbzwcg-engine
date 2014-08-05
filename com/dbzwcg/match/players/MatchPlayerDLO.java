/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.players;

import com.dbzwcg.events.noncombat.AfterNonCombatPhaseEvent;
import com.dbzwcg.events.noncombat.NonCombatPhaseEvent;
import com.dbzwcg.events.turnphase.declare.AfterDeclarePhaseEvent;
import com.dbzwcg.events.turnphase.declare.DeclarePhaseDeclareEvent;
import com.dbzwcg.events.turnphase.declare.DeclarePhaseEvent;
import com.dbzwcg.events.turnphase.declare.DeclarePhaseNotDeclareEvent;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.cardgroup.MatchCardGroup;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.declare.DeclarePhase;
import com.dbzwcg.match.phase.noncombat.NonCombatPhase;

/**
 *
 * @author csiqueira
 */
public class MatchPlayerDLO {

    public static void setCardGroupByType(MatchPlayer player, MatchCardGroup cardGroup) {
        if (cardGroup != null) {
            switch (cardGroup.getFieldType()) {
                case lifeDeck:
                    player.setLifeDeck(cardGroup);
                    break;
                case hand:
                    player.setHand(cardGroup);
                    break;
                case removedFromTheGame:
                    player.setRemovedFromTheGame(cardGroup);
                    break;
                case nonCombats:
                    player.setNonCombats(cardGroup);
                    break;
                case dragonballs:
                    player.setDragonballs(cardGroup);
                    break;
                case discardPile:
                    player.setDiscardPile(cardGroup);
                    break;
                case inPlay:
                    player.setInPlay(cardGroup);
                    break;
                case setAside:
                    player.setSetAside(cardGroup);
            }
        }
    }

    public static void declarePhaseSkipCombat(Match m, MatchPlayer p) {
        DeclarePhase phase = (DeclarePhase) m.getCurrentPhase();
        MatchEvent declareOption = new DeclarePhaseNotDeclareEvent(phase, p);
        MatchDLO.applyEvent(m, declareOption);
    }

    public static void declarePhaseDeclareCombat(Match m, MatchPlayer p) {
        DeclarePhase phase = (DeclarePhase) m.getCurrentPhase();
        MatchEvent declareOption = new DeclarePhaseDeclareEvent(phase, p);
        MatchDLO.applyEvent(m, declareOption);
    }

    public static void endNonCombatPhase(Match m, MatchPlayer p) {
        NonCombatPhase phase = (NonCombatPhase) m.getCurrentPhase();
        MatchEvent postNonCombatPhase = new AfterNonCombatPhaseEvent(phase, p);
        MatchDLO.applyEvent(m, postNonCombatPhase);
    }
}
