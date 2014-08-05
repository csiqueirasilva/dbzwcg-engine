/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.personality.MatchPersonality;
import com.dbzwcg.match.personality.MatchPersonalityDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class PowerStageLoseEvent extends MatchEvent {
    private Integer powerStageChange;
    private MatchPersonality personality;
    
    public PowerStageLoseEvent(GameMechanic gameMechanic, MatchPersonality personality, MatchPlayer player, Integer powerStageGain) {
        super(gameMechanic, MatchPersonalityDLO.getCurrentPersonality(personality));
        super.player = player;
        super.callbacks = player.getPowerStageGainCallbacks();
        super.nextEvent = null;
        super.sourceActor.setGameMechanic(gameMechanic);
        super.sourceActor.setCard(MatchPersonalityDLO.getCurrentPersonality(personality));
        this.powerStageChange = powerStageGain;
        this.personality = personality;
    }
    
    @Override
    public String logMessage() {
        return "POWER STAGE LOST";
    }

    @Override
    protected void phaseEffect(Match m) {
        MatchPersonalityDLO.losePowerStage(this.personality, this.powerStageChange);
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
