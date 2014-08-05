/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.players.ai;

import com.dbzwcg.match.Match;
import com.dbzwcg.players.Player;

/**
 *
 * @author csiqueira
 */
public class AIPlayer extends Player {

    // Player Decisions
    public Boolean declarePhaseDecision(Match m) {
        return true;
    }
}