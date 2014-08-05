/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.log;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;

/**
 *
 * @author csiqueira
 */
public class ResumeEvent extends MatchEvent {
    private MatchEvent resumeEvent;
    
    public ResumeEvent (MatchPlayer player, MatchEvent resumeEvent) {
        super(player);
        this.player = player;
        this.resumeEvent = resumeEvent;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        MatchDLO.resolveEvent(m, this.resumeEvent);
    }

    @Override
    public String logMessage() {
        return "RESUMING EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}