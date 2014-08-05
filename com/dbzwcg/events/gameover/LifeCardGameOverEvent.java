/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.gameover;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class LifeCardGameOverEvent extends GameOverEvent {
    
    @Override
    public String logMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Boolean runSpecificMatchEnd(Match m) {
        Boolean ret = true;
        // check if there is only one player "standing"
        MatchPlayer lastStandingPlayer = null;
        Integer countStandingPlayer = 0;
        
        for(int i = 0; i < m.getPlayers().size(); i++) {
            MatchPlayer p = m.getPlayers().get(i);
            if(!p.getLoser()) {
                lastStandingPlayer = p;
                countStandingPlayer++;
            }
        }
        
        if(countStandingPlayer > 1) {
            ret = false;
        } else {
            MatchDLO.setMatchWinner(m, lastStandingPlayer);
        }
        
        return ret;
    }

    @Override
    protected void phaseEffect(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}