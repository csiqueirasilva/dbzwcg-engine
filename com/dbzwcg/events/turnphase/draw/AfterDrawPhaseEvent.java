package com.dbzwcg.events.turnphase.draw;

import com.dbzwcg.events.noncombat.DecideNonCombatPhaseEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AfterDrawPhaseEvent extends MatchEvent {

    @Override
    public String logMessage() {
        return "AFTER DRAW PHASE EVENT";
    }

    public AfterDrawPhaseEvent(GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getAfterDrawPhaseCallbacks();
        super.nextEvent = null;
    }

    @Override
    protected void phaseEffect(Match m) {
        DecideNonCombatPhaseEvent bdpe = new DecideNonCombatPhaseEvent(super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}