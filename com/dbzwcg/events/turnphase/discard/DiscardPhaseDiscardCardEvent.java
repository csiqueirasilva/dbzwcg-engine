/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.discard;

import com.dbzwcg.events.CardMoveEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.play.MoveCardMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.phase.discard.DiscardPhaseDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class DiscardPhaseDiscardCardEvent extends CardMoveEvent {
    public DiscardPhaseDiscardCardEvent(GameMechanic gameMechanic, MatchPlayer player, MoveCardMechanic mcm) {
        super(gameMechanic, player, mcm);
    }
    
    @Override
    public void phaseEffect(Match m) {
        super.phaseEffect(m);
        if(DiscardPhaseDLO.checkCycleEndCondition(m, super.player)) {
            DiscardPhaseDLO.cycleDiscardPhase(m);
        } else {
            DiscardPhaseDLO.playerDiscardPhase(m);
        }
    }
    
    @Override
    public String logMessage() {
        return "DISCARD CARD IN DISCARD PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("NYI");
    }
}
