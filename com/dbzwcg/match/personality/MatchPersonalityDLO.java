/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.personality;

import com.dbzwcg.cards.sourcecards.personalities.PersonalityCard;
import com.dbzwcg.match.card.MatchCard;
import com.dbzwcg.types.LimitType;

/**
 *
 * @author csiqueira
 */
public class MatchPersonalityDLO {
    private static Integer getMaxPowerStageNumber(MatchPersonality p) {
        MatchCard c = MatchPersonalityDLO.getCurrentPersonality(p);
        PersonalityCard source = (PersonalityCard) c.getSourceCard();
        Integer max = source.getPowerStages().size() - 1;
        return max;
    }
    
    public static MatchCard getCurrentPersonality(MatchPersonality p) {
        return p.getPersonalities().get(p.getCurrentPersonalityLevel() - 1);
    }

    public static Boolean setPowerStage (MatchPersonality p, Integer powerStages) {
        Boolean ret = false;
        Integer max = MatchPersonalityDLO.getMaxPowerStageNumber(p);
        Integer setChange;
        Integer minPowerStage = LimitType.MIN_POWER_STAGE.getValue();

        if(powerStages > max) {
            setChange = max;
        } else if (powerStages < minPowerStage) {
            setChange = minPowerStage;
        } else {
            setChange = powerStages;
        }
        
        if(setChange != p.getCurrentPowerStageAboveZero()) {
            ret = true;
            p.setCurrentPowerStageAboveZero(setChange);
        }
        
        return ret;
    }
    
    public static Integer losePowerStage(MatchPersonality p, Integer powerStages) {
        Integer leftover = 0;
        Integer minPowerStage = LimitType.MIN_POWER_STAGE.getValue();
        
        if((p.getCurrentPowerStageAboveZero() - powerStages) < minPowerStage) {
            leftover = -(p.getCurrentPowerStageAboveZero() - powerStages);
            p.setCurrentPowerStageAboveZero(minPowerStage);
        } else {
            p.setCurrentPowerStageAboveZero(p.getCurrentPowerStageAboveZero() - powerStages);
        }
        return leftover;
    }
    
    public static Integer gainPowerStage(MatchPersonality p, Integer powerStages) {
        Integer leftover = 0;
        Integer max = MatchPersonalityDLO.getMaxPowerStageNumber(p);
        if((p.getCurrentPowerStageAboveZero() + powerStages) > max) {
            leftover = (p.getCurrentPowerStageAboveZero() + powerStages) % max;
            p.setCurrentPowerStageAboveZero(max);
        } else {
            p.setCurrentPowerStageAboveZero(p.getCurrentPowerStageAboveZero() + powerStages);
        }
        return leftover;
    }
    
    public static Integer getPowerUpRatingAsInteger(MatchPersonality personality) {
        MatchCard card = MatchPersonalityDLO.getCurrentPersonality(personality);
        Integer pur = 0;
        
        try { 
            pur += Integer.parseInt(((PersonalityCard) card.getSourceCard()).getPUR());
        } catch (NumberFormatException e) {
        }
        
        pur += personality.getPowerUpRatingModifier();
        
        return pur;
    }
}