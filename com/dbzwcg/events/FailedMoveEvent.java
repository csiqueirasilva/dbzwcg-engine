/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.log.MatchEvent;

/**
 *
 * @author csiqueira
 */
public class FailedMoveEvent extends MatchEvent {

    FailedMoveEvent(GameMechanic gameMechanic, MatchCard card) {
        super(gameMechanic, card);
        super.callbacks = null;
        super.nextEvent = null;
        super.sourceActor.setGameMechanic(gameMechanic);
        super.sourceActor.setCard(card);
    }
    
    @Override
    protected void phaseEffect(Match m) {
    }

    @Override
    public String logMessage() {
        return "FAILED MOVE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
