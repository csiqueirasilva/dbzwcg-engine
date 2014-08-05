/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.log;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.players.Player;
/**
 *
 * @author csiqueira
 */
public class MatchEventCreator {
    private Player player;
    private InstancedCard card;
    private GameMechanic gameMechanic;
    
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.card = null;
        this.gameMechanic = null;
        this.player = player;
    }

    public InstancedCard getCard() {
        return this.card;
    }

    public void setCard(InstancedCard card) {
        this.player = null;
        this.gameMechanic = null;
        this.card = card;
    }

    public GameMechanic getGameMechanic() {
        return this.gameMechanic;
    }

    public void setGameMechanic(GameMechanic gameMechanic) {
        this.player = null;
        this.card = null;
        this.gameMechanic = gameMechanic;
    }
}
