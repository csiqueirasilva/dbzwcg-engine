/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.rejuvenation;

import com.dbzwcg.events.turn.PlayerFinishTurnEvent;
import com.dbzwcg.gamemechanics.turns.EndTurn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class SkipRejuvenationPhaseEvent extends MatchEvent {
    public SkipRejuvenationPhaseEvent(MatchPlayer player) {
        super(player);
        super.player = player;
    }

    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe = new PlayerFinishTurnEvent(new EndTurn(), super.player);
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "SKIP REJUVENATION PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
