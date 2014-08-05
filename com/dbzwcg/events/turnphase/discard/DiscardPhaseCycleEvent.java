/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.discard;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.phase.discard.DiscardPhase;
import com.dbzwcg.match.phase.discard.DiscardPhaseDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DiscardPhaseCycleEvent extends DiscardPhaseEvent {

    public DiscardPhaseCycleEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
    }
    
    @Override
    public void phaseEffect(Match m) {
        DiscardPhase discardPhase = (DiscardPhase) super.getSourceActor().getGameMechanic();
        discardPhase.setCursor(super.player);
        if(DiscardPhaseDLO.checkCycleEndCondition(m, super.player)) {
            DiscardPhaseDLO.cycleDiscardPhase(m);
        } else {
            DiscardPhaseDLO.playerDiscardPhase(m);
        }
    }
}
