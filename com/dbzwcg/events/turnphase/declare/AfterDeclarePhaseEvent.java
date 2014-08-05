/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.declare;

import com.dbzwcg.events.turnphase.combat.DecideCombatPhaseEvent;
import com.dbzwcg.events.turnphase.discard.BeforeDiscardPhaseEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.turns.Turn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class AfterDeclarePhaseEvent extends MatchEvent {

    private MatchPlayer declaredPlayer;
    
    public AfterDeclarePhaseEvent (GameMechanic generator, MatchPlayer target, MatchPlayer declaredPlayer) {
        this(generator, target);
        this.declaredPlayer = declaredPlayer;
    }
    
    public AfterDeclarePhaseEvent (GameMechanic generator, MatchPlayer target) {
        super(generator, target);
        super.player = target;
        super.callbacks = target.getAfterDeclarePhaseCallbacks();
        super.nextEvent = null;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchEvent bdpe; 
        Turn turn = MatchDLO.getLastTurn(m);
        if(this.declaredPlayer == null) {
            bdpe = new BeforeDiscardPhaseEvent(turn.getDiscardPhase(), super.player);
        } else {
            bdpe = new DecideCombatPhaseEvent(super.player, this.declaredPlayer);
        }
        MatchDLO.applyEvent(m, bdpe);
    }

    @Override
    public String logMessage() {
        return "AFTER DECLARE PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}