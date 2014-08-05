/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.personality;

import com.dbzwcg.match.card.MatchCard;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchPersonality {
    protected Integer currentPersonalityLevel;
    protected Integer currentPowerStageAboveZero;
    protected List<MatchCard> personalities;
    protected Integer powerUpRatingModifier;

    public Integer getPowerUpRatingModifier() {
        return powerUpRatingModifier;
    }

    public void setPowerUpRatingModifier(Integer powerUpRatingModifier) {
        this.powerUpRatingModifier = powerUpRatingModifier;
    }
    
    public List<MatchCard> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(List<MatchCard> personalities) {
        this.personalities = personalities;
    }
    
    public Integer getCurrentPersonalityLevel() {
        return currentPersonalityLevel;
    }

    public void setCurrentPersonalityLevel(Integer currentMainPersonalityLevel) {
        this.currentPersonalityLevel = currentMainPersonalityLevel;
    }
    
    public Integer getCurrentPowerStageAboveZero() {
        return currentPowerStageAboveZero;
    }

    public void setCurrentPowerStageAboveZero(Integer currentPowerStageAboveZero) {
        this.currentPowerStageAboveZero = currentPowerStageAboveZero;
    }
    
}
