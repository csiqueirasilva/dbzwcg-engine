/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match;

import com.dbzwcg.match.mainpersonality.MatchMainPersonality;
import com.dbzwcg.match.cardgroup.MatchCardGroup;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.cards.sourcecards.personalities.PersonalityCard;
import com.dbzwcg.decks.Deck;
import com.dbzwcg.decks.DeckDLO;
import com.dbzwcg.decks.DeckEligibleReference;
import com.dbzwcg.events.CardMoveEvent;
import com.dbzwcg.events.GameStartEvent;
import com.dbzwcg.events.gameover.GameOverVictoryEvent;
import com.dbzwcg.events.gameover.LifeCardGameOverEvent;
import com.dbzwcg.gamemechanics.GameStart;
import com.dbzwcg.gamemechanics.turns.Turn;
import com.dbzwcg.gamemechanics.turns.TurnDLO;
import com.dbzwcg.match.callback.sets.CallbackSet;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.card.MatchCardDLO;
import com.dbzwcg.match.cardgroup.MatchCardGroupDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.log.ResumeEvent;
import com.dbzwcg.match.types.CardFieldType;
import com.dbzwcg.players.Player;
import com.dbzwcg.players.ai.AIPlayer;
import com.dbzwcg.players.ai.AIPlayerDLO;
import com.dbzwcg.types.LimitType;
import com.dbzwcg.users.user.UserDLO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author csiqueira
 */
public class MatchDLO {
    
    public static Turn getLastTurn(Match m) {
        return m.getTurns().get(m.getTurns().size() - 1);
    }

    public static Turn addTurn(Match m, MatchPlayer player) {
        Turn turn = TurnDLO.createTurn(m.getTurns().size(), player);
        m.getTurns().add(turn);
        return turn;
    }

    public static Boolean checkEventPossible(MatchPlayer p, MatchEvent e) {
        Boolean ret = true;

        return ret;
    }

    public static Boolean validateUnfinishedMatch(Match m) {
        return m != null && m.getTerminated() == false && m.getPastEvents() != null && m.getQueuedEvents() != null;
    }

    public static void resolveEvent(Match m, MatchEvent nextEvent) {
        m.getPastEvents().add(nextEvent);
        nextEvent.resolveEffect(m);
        System.out.println(nextEvent.logMessage());
    }
    
    public static void resolveQueuedEvents(Match m) {
        while (MatchDLO.validateUnfinishedMatch(m) && m.getQueuedEvents().size() > 0 && !m.getWaitingExternalInteraction()) {
            MatchEvent nextEvent = m.getQueuedEvents().poll();
            MatchDLO.resolveEvent(m, nextEvent);
        }
    }

    public static Boolean applyResumeEvent(Match m, ResumeEvent e) {
        Boolean ret = false;
        if (MatchDLO.validateUnfinishedMatch(m)) {
            m.getResumeEvents().add(e);
            ret = true;
        }
        return ret;
    }
    
    public static Boolean applyEvent(Match m, MatchEvent e) {
        Boolean ret = false;
        if (MatchDLO.validateUnfinishedMatch(m)) {
            m.getQueuedEvents().add(e);
            ret = true;
        }
        return ret;
    }

    public static List<DeckEligibleReference> listPlayerDeckEligibility(Player p) {
        List<Deck> decks = DeckDLO.getDecksByUser(p);
        List<DeckEligibleReference> refs = new ArrayList<DeckEligibleReference>();

        for (int i = 0; i < decks.size(); i++) {
            DeckEligibleReference ref = new DeckEligibleReference();
            refs.add(ref);
            ref.setDeck(decks.get(i));
            ref.setEligible(DeckDLO.checkDeckPlayable(decks.get(i)));
        }

        return refs;
    }

    public static Match createMatch() {
        Match m = new Match();
        m.setTerminated(false);
        m.setWaitingExternalInteraction(false);
        List<MatchEvent> pastEvents = new ArrayList<MatchEvent>();
        List<MatchPlayer> players = new ArrayList<MatchPlayer>();
        Queue<MatchEvent> queuedEvents = new LinkedList<MatchEvent>();
        List<Turn> turns = new ArrayList<Turn>();
        m.setTurns(turns);
        m.setQueuedEvents(queuedEvents);
        m.setPastEvents(pastEvents);
        m.setPlayers(players);
        return m;
    }
    
    public static void main(String args[]) {
        Match m = MatchDLO.createMatch();
        AIPlayer aiPlayer1 = AIPlayerDLO.createFromPlayer((Player) UserDLO.getUserFromUsername("root@dbzwcg.com"));
        AIPlayer aiPlayer2 = AIPlayerDLO.createFromPlayer((Player) UserDLO.getUserFromUsername("root@dbzwcg.com"));

        MatchPlayer p1 = assemblePlayerMatchData(m, 8, aiPlayer1);
        MatchPlayer p2 = assemblePlayerMatchData(m, 8, aiPlayer2);

        m.getPlayers().add(p1);
        m.getPlayers().add(p2);

        m.getQueuedEvents().add(new GameStartEvent(new GameStart(), m));

        resolveQueuedEvents(m);
    }

    public static MatchPlayer assemblePlayerMatchData(Match m, Integer deckId, Player player) {
        Deck d = DeckDLO.getDeckById(deckId);
        MatchPlayer p = null;

        if (d != null && d.getOwner() != null && d.getOwner().getEmail() != null
                && player != null && player.getEmail() != null
                && d.getOwner().getEmail().equals(player.getEmail())) {
            p = getAsMatchPlayer(m, d, player);
        }

        return p;
    }

    public static MatchPlayer getAsMatchPlayer(Match m, Deck deck, Player player) {
        MatchPlayer p = new MatchPlayer();

        MatchMainPersonality mp = new MatchMainPersonality();

        if (deck.getAlignment() != null) {
            mp.setAlignment(deck.getAlignment().ordinal());
        }

        mp.setAngerLevelNeededToLevel(LimitType.BEGINNING_ANGER_NEEDED_TO_LEVEL.getValue());
        mp.setCurrentPersonalityLevel(LimitType.BEGINNING_MAIN_PERSONALITY_LEVEL.getValue());
        mp.setCurrentPowerStageAboveZero(LimitType.BEGINNING_POWER_STAGE.getValue());
        mp.setCurrentAngerLevel(LimitType.BEGINNING_ANGER_LEVEL.getValue());
        mp.setPowerUpRatingModifier(LimitType.BEGINNING_POWER_UP_RATING_MODIFIER.getValue());

        if (player != null) {
            p.setName(player.getDisplayName());
        }

        MatchCard[] mainPersonalityFinalList = new MatchCard[deck.getMainPersonality().size()];

        int addedLevel = LimitType.LEVEL_MIN.getValue() - 1;
        for (int i = 0; i < mainPersonalityFinalList.length; i++) {
            int level = ((PersonalityCard) deck.getMainPersonality().get(0).getSourceCard()).getLevel();
            int index = 0;
            for (int j = 1; j < deck.getMainPersonality().size(); j++) {
                int itLevel = ((PersonalityCard) deck.getMainPersonality().get(j).getSourceCard()).getLevel();
                if (itLevel < level && itLevel > addedLevel) {
                    index = j;
                    level = itLevel;
                }
            }
            mainPersonalityFinalList[i] = MatchCardDLO.createMatchCard(m, deck.getMainPersonality().get(index));
            addedLevel = level;
        }

        mp.setPersonalities(Arrays.asList(mainPersonalityFinalList));
        p.setMainPersonality(mp);

        MatchCardGroup lifeDeck = MatchCardGroupDLO.createListMatchCard(m, deck.getCards(), CardFieldType.lifeDeck, p);
        MatchCardGroup discardPile = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.discardPile, p);
        MatchCardGroup dragonballs = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.dragonballs, p);
        MatchCardGroup nonCombats = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.nonCombats, p);
        MatchCardGroup inPlay = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.inPlay, p);
        MatchCardGroup hand = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.hand, p);
        MatchCardGroup removedFromTheGame = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.removedFromTheGame, p);
        MatchCardGroup setAside = MatchCardGroupDLO.createListMatchCard(m, new ArrayList(), CardFieldType.setAside, p);

        p.setHandOnTable(false);
        p.setLoser(false);
        p.setCanLose(true);
        p.setCanDBWin(true);
        p.setCanMPPWin(true);

        p.setSkipDrawPhase(false);
        p.setSkipNonCombatPhase(false);
        p.setSkipPowerUpPhase(false);
        p.setSkipDeclarePhase(false);
        p.setSkipCombatPhase(false);
        p.setSkipDiscardPhase(false);
        p.setSkipRejuvenationPhase(false);

        p.setDrawPhaseQuantityDraw(LimitType.DRAW_PHASE_QUANTITY_DEFAULT.getValue());
        p.setDiscardPhaseHandSize(LimitType.DISCARD_PHASE_LIMIT_DEFAULT.getValue());

        p.setRemovedFromTheGame(removedFromTheGame);
        p.setSetAside(setAside);
        p.setDragonballs(dragonballs);
        p.setDiscardPile(discardPile);
        p.setNonCombats(nonCombats);
        p.setInPlay(inPlay);
        p.setHand(hand);
        p.setLifeDeck(lifeDeck);

        p.setAfterCombatPhaseCallbacks(new CallbackSet());
        p.setAfterDeclarePhaseCallbacks(new CallbackSet());
        p.setAfterDiscardPhaseCallbacks(new CallbackSet());
        p.setAfterDrawPhaseCallbacks(new CallbackSet());
        p.setAfterNonCombatPhaseCallbacks(new CallbackSet());
        p.setAfterPowerUpPhaseCallbacks(new CallbackSet());
        p.setAfterRejuvenationPhaseCallbacks(new CallbackSet());

        p.setBeforeCombatPhaseCallbacks(new CallbackSet());
        p.setBeforeDeclarePhaseCallbacks(new CallbackSet());
        p.setBeforeDiscardPhaseCallbacks(new CallbackSet());
        p.setBeforeDrawPhaseCallbacks(new CallbackSet());
        p.setBeforeNonCombatPhaseCallbacks(new CallbackSet());
        p.setBeforePowerUpPhaseCallbacks(new CallbackSet());
        p.setBeforeRejuvenationPhaseCallbacks(new CallbackSet());

        p.setCombatPhaseCallbacks(new CallbackSet());
        p.setDeclarePhaseCallbacks(new CallbackSet());
        p.setDiscardPhaseCallbacks(new CallbackSet());
        p.setDrawPhaseCallbacks(new CallbackSet());
        p.setNonCombatPhaseCallbacks(new CallbackSet());
        p.setPowerUpPhaseCallbacks(new CallbackSet());
        p.setRejuvenationPhaseCallbacks(new CallbackSet());

        p.setPowerStageGainCallbacks(new CallbackSet());
        p.setPowerStageLoseCallbacks(new CallbackSet());
        p.setPowerStageSetCallbacks(new CallbackSet());

        p.setEnteringCombatPhaseCallbacks(new CallbackSet());

        p.setAttackPhaseCallbacks(new CallbackSet());
        p.setDefendPhaseCallbacks(new CallbackSet());

        p.setStartTurnCallbacks(new CallbackSet());
        p.setEndTurnCallbacks(new CallbackSet());

        p.setTransferCallbacks(new CallbackSet());

        p.setPlayer(player);

        return p;
    }

    public static void lifeCardGameOver(Match m, MatchPlayer p) {
        m.getQueuedEvents().clear();
        LifeCardGameOverEvent goe = new LifeCardGameOverEvent();
        Player databasePlayer = (Player) UserDLO.getUserFromId(p.getId());
        goe.setLoser(databasePlayer);
        MatchDLO.applyEvent(m, goe);
    }

    public static void setMatchWinner(Match m, MatchPlayer p) {
        GameOverVictoryEvent gove = new GameOverVictoryEvent();
        gove.setWinner(p);
        MatchDLO.applyEvent(m, gove);
    }

    public static void checkGameOver(Match m, MatchPlayer p, MatchEvent event) {
        if (p.getLifeDeck().size() == 0 && p.getCanLose() && event instanceof CardMoveEvent && ((CardMoveEvent) event).getSource().getField() == CardFieldType.lifeDeck) { /* Gameover */
            MatchDLO.lifeCardGameOver(m, p);
        }
    }
}