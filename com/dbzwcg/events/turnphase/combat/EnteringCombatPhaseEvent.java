/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.combat;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.combat.CombatPhase;
import com.dbzwcg.match.phase.combat.CombatPhaseDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class EnteringCombatPhaseEvent extends MatchEvent {

    public EnteringCombatPhaseEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        // solve involved players when entering combat callbacks
        CombatPhase phase = (CombatPhase) super.getSourceActor().getGameMechanic();
        CombatPhaseDLO.addInternalPhase(m, phase);
        // invoke player action (attack) event for the current phase, 
        // the event will set the match to waitingexternalinput (for human players) or call the matching AI method
        
        // if the player does an action, the event for player action must be called.
    }

    @Override
    public String logMessage() {
        return "ENTERING COMBAT PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}