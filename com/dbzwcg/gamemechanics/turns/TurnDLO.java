/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.gamemechanics.turns;

import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class TurnDLO {
    public static Turn createTurn(Integer turnNumber, MatchPlayer owner) {
        Turn turn = new Turn();
        turn.setTurnNumber(turnNumber);
        turn.setOwner(owner);
        return turn;
    }
}
