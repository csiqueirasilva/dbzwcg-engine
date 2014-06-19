/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class MatchMainPersonality {
    private Integer alignment;
    private Integer currentMainPersonalityLevel;
    private Integer currentPowerStageAboveZero;
    private Integer currentAngerLevel;

    public Integer getCurrentAngerLevel() {
        return currentAngerLevel;
    }

    public void setCurrentAngerLevel(Integer currentAngerLevel) {
        this.currentAngerLevel = currentAngerLevel;
    }
    private Integer angerLevelNeededToLevel;
    
    public Integer getAlignment() {
        return alignment;
    }

    public void setAlignment(Integer alignment) {
        this.alignment = alignment;
    }

    public Integer getCurrentMainPersonalityLevel() {
        return currentMainPersonalityLevel;
    }

    public void setCurrentMainPersonalityLevel(Integer currentMainPersonalityLevel) {
        this.currentMainPersonalityLevel = currentMainPersonalityLevel;
    }

    public Integer getCurrentPowerStageAboveZero() {
        return currentPowerStageAboveZero;
    }

    public void setCurrentPowerStageAboveZero(Integer currentPowerStageAboveZero) {
        this.currentPowerStageAboveZero = currentPowerStageAboveZero;
    }

    public Integer getAngerLevelNeededToLevel() {
        return angerLevelNeededToLevel;
    }

    public void setAngerLevelNeededToLevel(Integer angerLevelNeededToLevel) {
        this.angerLevelNeededToLevel = angerLevelNeededToLevel;
    }

    public List<InstancedCard> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(List<InstancedCard> personalities) {
        this.personalities = personalities;
    }
    private List<InstancedCard> personalities;
    
}
