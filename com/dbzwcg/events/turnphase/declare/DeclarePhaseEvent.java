/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.declare;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.players.ai.AIPlayer;
import com.dbzwcg.players.ai.AIPlayerDLO;

/**
 *
 * @author csiqueira
 */
public class DeclarePhaseEvent extends MatchEvent {

    public DeclarePhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getDeclarePhaseCallbacks();
        super.nextEvent = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        // do the declaration
        if(super.player.getPlayer() instanceof AIPlayer) {
            AIPlayerDLO.declarePhasePlay(m, super.player);
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