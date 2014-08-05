/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turn;

import com.dbzwcg.events.turnphase.draw.DecideDrawPhaseEvent;
import com.dbzwcg.gamemechanics.turns.StartTurn;
import com.dbzwcg.gamemechanics.turns.Turn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;

/**
 *
 * @author csiqueira
 */
public class PlayerStartTurnEvent extends MatchEvent {

    private Turn turn;

    @Override
    public String logMessage() {
        return "Starting Turn " + this.turn.getTurnNumber();
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void phaseEffect(Match m) {
        this.turn = MatchDLO.addTurn(m, super.player);
        DecideDrawPhaseEvent bdpe = new DecideDrawPhaseEvent(super.player);
        MatchDLO.applyEvent(m, bdpe);
    }
    
    public PlayerStartTurnEvent(StartTurn gameMechanic, MatchPlayer target) {
        super(gameMechanic, target);
        super.player = target;
        super.callbacks = target.getStartTurnCallbacks();
        super.nextEvent = null;
    }
}
