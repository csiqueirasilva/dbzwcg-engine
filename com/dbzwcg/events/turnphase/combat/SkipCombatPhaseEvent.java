/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.combat;

import com.dbzwcg.events.turnphase.discard.BeforeDiscardPhaseEvent;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.discard.DiscardPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class SkipCombatPhaseEvent extends MatchEvent {
      public SkipCombatPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }

    @Override
    protected void phaseEffect(Match m) {
        DiscardPhase phase = new DiscardPhase();
        BeforeDiscardPhaseEvent bdpe = new BeforeDiscardPhaseEvent(phase, super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "SKIP COMBAT PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}