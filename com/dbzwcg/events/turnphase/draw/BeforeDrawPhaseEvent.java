/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.draw;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.draw.DrawPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforeDrawPhaseEvent extends MatchEvent {

    @Override
    public String logMessage() {
        return "BEFORE DRAW PHASE EVENT";
    }

    public BeforeDrawPhaseEvent(GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getBeforeDrawPhaseCallbacks();
        super.nextEvent = DrawPhaseEvent.class;
    }

    @Override
    protected void phaseEffect(Match m) {
        DrawPhase phase = (DrawPhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setDrawPhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}