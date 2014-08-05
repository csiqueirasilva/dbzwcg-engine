/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.mainpersonality;

import com.dbzwcg.match.personality.MatchPersonality;

/**
 *
 * @author csiqueira
 */
public class MatchMainPersonality extends MatchPersonality {
    protected Integer alignment;
    protected Integer currentAngerLevel;

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

    public Integer getAngerLevelNeededToLevel() {
        return angerLevelNeededToLevel;
    }

    public void setAngerLevelNeededToLevel(Integer angerLevelNeededToLevel) {
        this.angerLevelNeededToLevel = angerLevelNeededToLevel;
    }
}
