/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.gamemechanics.turns;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.phase.combat.CombatPhase;
import com.dbzwcg.match.phase.declare.DeclarePhase;
import com.dbzwcg.match.phase.discard.DiscardPhase;
import com.dbzwcg.match.phase.draw.DrawPhase;
import com.dbzwcg.match.phase.noncombat.NonCombatPhase;
import com.dbzwcg.match.phase.pur.PowerUpPhase;
import com.dbzwcg.match.phase.rejuvenation.RejuvenationPhase;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class Turn extends GameMechanic {

    private MatchPlayer owner;
    private Integer turnNumber;
    private DrawPhase drawPhase;
    private NonCombatPhase nonCombatPhase;
    private PowerUpPhase powerUpPhase;
    private DeclarePhase declarePhase;
    private CombatPhase CombatPhase;
    private DiscardPhase discardPhase;
    private RejuvenationPhase rejuvenationPhase;

    public MatchPlayer getOwner() {
        return owner;
    }

    public void setOwner(MatchPlayer owner) {
        this.owner = owner;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public DrawPhase getDrawPhase() {
        return drawPhase;
    }

    public void setDrawPhase(DrawPhase drawPhase) {
        this.drawPhase = drawPhase;
    }

    public NonCombatPhase getNonCombatPhase() {
        return nonCombatPhase;
    }

    public void setNonCombatPhase(NonCombatPhase nonCombatPhase) {
        this.nonCombatPhase = nonCombatPhase;
    }

    public PowerUpPhase getPowerUpPhase() {
        return powerUpPhase;
    }

    public void setPowerUpPhase(PowerUpPhase powerUpPhase) {
        this.powerUpPhase = powerUpPhase;
    }

    public DeclarePhase getDeclarePhase() {
        return declarePhase;
    }

    public void setDeclarePhase(DeclarePhase declarePhase) {
        this.declarePhase = declarePhase;
    }

    public CombatPhase getCombatPhase() {
        return CombatPhase;
    }

    public void setCombatPhase(CombatPhase CombatPhase) {
        this.CombatPhase = CombatPhase;
    }

    public DiscardPhase getDiscardPhase() {
        return discardPhase;
    }

    public void setDiscardPhase(DiscardPhase discardPhase) {
        this.discardPhase = discardPhase;
    }

    public RejuvenationPhase getRejuvenationPhase() {
        return rejuvenationPhase;
    }

    public void setRejuvenationPhase(RejuvenationPhase rejuvenationPhase) {
        this.rejuvenationPhase = rejuvenationPhase;
    }
    
    @Override
    public String getName() {
        return "TURN";
    }
}
