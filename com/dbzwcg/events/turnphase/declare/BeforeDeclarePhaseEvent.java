/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.declare;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.declare.DeclarePhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class BeforeDeclarePhaseEvent extends MatchEvent {

    public BeforeDeclarePhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getBeforeDeclarePhaseCallbacks();
        super.nextEvent = DeclarePhaseEvent.class;
    }

    @Override
    public String logMessage() {
        return "BEFORE DECLARE PHASE EVENT";
    }

    @Override
    protected void phaseEffect(Match m) {
        DeclarePhase phase = (DeclarePhase) super.sourceActor.getGameMechanic();
        m.setCurrentPhase(phase);
        MatchDLO.getLastTurn(m).setDeclarePhase(phase);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
