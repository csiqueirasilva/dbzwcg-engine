/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.events.turn.PlayerStartTurnEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.turns.StartTurn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.log.MatchEventCreator;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class GameStartEvent extends MatchEvent {

    public GameStartEvent(GameMechanic gameMechanic, Match m) {
        this.sourceActor = new MatchEventCreator();
        this.sourceActor.setGameMechanic(gameMechanic);
        
        List<MatchPlayer> players = m.getPlayers();
        Integer firstPlayerIndex = (int) (Math.random() * (players.size() - 1));
        super.player = players.get(firstPlayerIndex);
        
        this.targetActor = new MatchEventCreator();
        this.targetActor.setPlayer(super.player.getPlayer());
    }
    
    @Override
    public String logMessage() {
        return "GAME START EVENT";
    }

    @Override
    public void phaseEffect(Match m) {
        List<MatchPlayer> players = m.getPlayers();
        
        for(int i = 0; i < players.size(); i++) {
            ShuffleCardGroupEvent scge = new ShuffleCardGroupEvent(this.sourceActor.getGameMechanic(), players.get(i), players.get(i).getLifeDeck());
            MatchDLO.applyEvent(m, scge);
        }
        
        PlayerStartTurnEvent pste = new PlayerStartTurnEvent(new StartTurn(), super.player);
        MatchDLO.applyEvent(m, pste);
    }

    @Override
    public void applyFromDatabase(Match m) {
    }
}
