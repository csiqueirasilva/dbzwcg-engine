/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.phase.discard;

import com.dbzwcg.match.phase.Phase;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.types.PhaseType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class DiscardPhase extends Phase {

    private MatchPlayer owner;
    private List<MatchPlayer> players;
    private MatchPlayer cursor;

    public MatchPlayer getCursor() {
        return cursor;
    }

    public void setCursor(MatchPlayer cursor) {
        this.cursor = cursor;
    }
    
    public MatchPlayer getOwner() {
        return owner;
    }

    public void setOwner(MatchPlayer owner) {
        this.owner = owner;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }
    
    public DiscardPhase() {
        super.type = PhaseType.DISCARD;
    } 
    
    @Override
    public String getName() {
        return "DISCARD PHASE";
    }
    
}
