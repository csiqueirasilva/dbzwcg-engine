/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.powerup;

import com.dbzwcg.cards.sourcecards.personalities.PersonalityCard;
import com.dbzwcg.cards.sourcecards.personalities.PersonalityCardDLO;
import com.dbzwcg.events.PowerStageGainEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.mainpersonality.MatchMainPersonality;
import com.dbzwcg.match.personality.MatchPersonality;
import com.dbzwcg.match.personality.MatchPersonalityDLO;

/**
 *
 * @author csiqueira
 */
public class PowerUpPhaseEvent extends MatchEvent {

    public PowerUpPhaseEvent(GameMechanic gameMechanic, MatchPlayer player) {
        super(gameMechanic, player);
        super.player = player;
        super.nextEvent = AfterPowerUpPhaseEvent.class;
        super.callbacks = player.getPowerUpPhaseCallbacks();
    }

    @Override
    public String logMessage() {
        return "POWER UP PHASE EVENT";
    }

    private void powerUpPersonality(Match m, MatchPersonality personality) {
        Integer rating = MatchPersonalityDLO.getPowerUpRatingAsInteger(personality);
        PowerStageGainEvent psge = new PowerStageGainEvent(super.sourceActor.getGameMechanic(), personality, super.player, rating);
        MatchDLO.applyEvent(m, psge);
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchMainPersonality mp = super.player.getMainPersonality();
        powerUpPersonality(m, mp);
        
        // do it for allies
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}