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
public class MatchPlayer {
//    {name: 'CPU', mainPersonality: {alignment: DBZCCG.Personality.alignment.Villain, currentMainPersonalityLevel: 1, currentPowerStageAboveZero: 5, currentAngerLevel: 0,
//                    angerLevelNeededToLevel: 5, personalities: [DBZCCG.Saiyan['173'], DBZCCG.Saiyan['174'], DBZCCG.Saiyan['175']]}
    private String name;
    private List<InstancedCard> lifeDeck;
    private MatchMainPersonality mainPersonality;

    public MatchMainPersonality getMainPersonality() {
        return mainPersonality;
    }

    public void setMainPersonality(MatchMainPersonality mainPersonality) {
        this.mainPersonality = mainPersonality;
    }
    
    public List<InstancedCard> getLifeDeck() {
        return lifeDeck;
    }

    public void setLifeDeck(List<InstancedCard> lifeDeck) {
        this.lifeDeck = lifeDeck;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}