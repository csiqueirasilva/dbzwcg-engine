/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.gameover;

import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.players.Player;

/**
 *
 * @author csiqueira
 */
abstract class GameOverEvent extends MatchEvent {
    protected Player loser;

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }
    
    protected abstract Boolean runSpecificMatchEnd(Match m);
    
    @Override
    public void resolveEffect(Match m) {
        if(this.loser != null) {
            if(this.runSpecificMatchEnd(m)) {
                m.setTerminated(true);
            }
        }
    }
}
