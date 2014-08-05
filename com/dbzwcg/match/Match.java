/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match;

import com.dbzwcg.gamemechanics.turns.Turn;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.log.ResumeEvent;
import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.players.Player;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author csiqueira
 */
public class Match {

    protected Integer id;
    protected List<MatchPlayer> players;
    protected Boolean terminated;
    protected Player winner;
    protected Phase currentPhase;
    protected List<Turn> turns;
    protected List<MatchEvent> pastEvents;
    protected Queue<ResumeEvent> resumeEvents;
    protected Queue<MatchEvent> interruptedEvents;
    protected Queue<MatchEvent> queuedEvents;
    protected Boolean waitingExternalInteraction;
    protected Boolean executingEffect;

    public Queue<ResumeEvent> getResumeEvents() {
        return resumeEvents;
    }

    public void setResumeEvents(Queue<ResumeEvent> resumeEvents) {
        this.resumeEvents = resumeEvents;
    }

    public Queue<MatchEvent> getInterruptedEvents() {
        return interruptedEvents;
    }

    public void setInterruptedEvents(Queue<MatchEvent> interruptedEvents) {
        this.interruptedEvents = interruptedEvents;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public Boolean getExecutingEffect() {
        return executingEffect;
    }

    public void setExecutingEffect(Boolean executingEffect) {
        this.executingEffect = executingEffect;
    }

    public Boolean getWaitingExternalInteraction() {
        return waitingExternalInteraction;
    }

    public void setWaitingExternalInteraction(Boolean waitingExternalInteraction) {
        this.waitingExternalInteraction = waitingExternalInteraction;
    }

    public Queue<MatchEvent> getQueuedEvents() {
        return queuedEvents;
    }

    public void setQueuedEvents(Queue<MatchEvent> events) {
        this.queuedEvents = events;
    }

    public List<MatchEvent> getPastEvents() {
        return pastEvents;
    }

    public void setPastEvents(List<MatchEvent> events) {
        this.pastEvents = events;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }

    public Boolean getTerminated() {
        return terminated;
    }

    public void setTerminated(Boolean terminated) {
        this.terminated = terminated;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}