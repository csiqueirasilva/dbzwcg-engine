/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events;

import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.cardgroup.MatchCardGroup;
import com.dbzwcg.match.cardgroup.MatchCardGroupDLO;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.players.MatchPlayerDLO;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author csiqueira
 */
public class ShuffleCardGroupEvent extends MatchEvent {

    private MatchCardGroup sourceGroup;
    private MatchCardGroup shuffledGroup;
    
    public ShuffleCardGroupEvent(GameMechanic gameMechanic, MatchPlayer target, MatchCardGroup sourceGroup) {
        super(gameMechanic, target);
        super.player = target;
        this.sourceGroup = sourceGroup;
    }
    
    @Override
    protected void phaseEffect(Match m) {
        this.shuffledGroup = MatchCardGroupDLO.createListMatchCard(m, (List) this.sourceGroup, this.sourceGroup.getFieldType(), super.player);
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        Collections.shuffle(this.shuffledGroup, rnd);
        MatchPlayerDLO.setCardGroupByType(super.player, this.shuffledGroup);
    }

    @Override
    public String logMessage() {
        return "SHUFFLE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
