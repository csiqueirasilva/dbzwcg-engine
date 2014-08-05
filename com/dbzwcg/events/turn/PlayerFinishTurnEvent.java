/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turn;

import com.dbzwcg.gamemechanics.turns.EndTurn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class PlayerFinishTurnEvent extends MatchEvent {
    public PlayerFinishTurnEvent(EndTurn gameMechanic, MatchPlayer target) {
        super(gameMechanic, target);
        super.player = target;
        super.callbacks = target.getEndTurnCallbacks();
        super.nextEvent = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String logMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
