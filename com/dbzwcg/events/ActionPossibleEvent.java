/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.log.MatchEventCreator;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.players.ai.AIPlayer;

/**
 *
 * @author csiqueira
 */
public class ActionPossibleEvent extends MatchEvent {

    ActionPossibleEvent(MatchEventCreator source, MatchEventCreator target, Class chainEvent, MatchPlayer player) {
        super.sourceActor = source;
        super.targetActor = target;
        super.player = player;
        super.nextEvent = chainEvent;
        super.callbacks = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        if(super.player.getPlayer() instanceof AIPlayer) {
            // solve usewhenneed callback for AIPlayer
        } else {
            m.setWaitingExternalInteraction(true);
        }
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
