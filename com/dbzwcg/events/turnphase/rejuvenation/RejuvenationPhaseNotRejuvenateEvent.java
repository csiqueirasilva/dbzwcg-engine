/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.rejuvenation;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class RejuvenationPhaseNotRejuvenateEvent extends MatchEvent {
    public RejuvenationPhaseNotRejuvenateEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
        super.callbacks = null;
        super.nextEvent = AfterRejuvenationPhaseEvent.class;
    }
    
    @Override
    protected void phaseEffect(Match m) {

    }

    @Override
    public String logMessage() {
        return "NOT REJUVENATING EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}