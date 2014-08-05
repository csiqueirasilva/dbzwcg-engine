/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.events.turnphase.draw;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.events.CardMoveEvent;
import com.dbzwcg.gamemechanics.GameMechanic;
import com.dbzwcg.gamemechanics.play.MoveCardMechanic;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.MatchDLO;
import com.dbzwcg.match.players.MatchPlayer;
import com.dbzwcg.match.log.MatchEvent;
import com.dbzwcg.match.phase.Phase;

/**
 *
 * @author csiqueira
 */
public class DrawPhaseEvent extends MatchEvent {

    public DrawPhaseEvent(GameMechanic gameMechanic, MatchPlayer target) {
        super(gameMechanic, target);
        super.player = target;
        super.callbacks = target.getDrawPhaseCallbacks();
        super.nextEvent = AfterDrawPhaseEvent.class;
    }

    @Override
    protected void phaseEffect (Match m) {
        Phase drawPhase = (Phase) super.sourceActor.getGameMechanic();
        
        for(int i = 0; i < super.player.getDrawPhaseQuantityDraw(); i++) {
            InstancedCard card = super.player.getLifeDeck().get(super.player.getLifeDeck().size() - 1 - i);
            MoveCardMechanic mcm = new MoveCardMechanic(card, super.player.getHand(), super.player.getLifeDeck());
            CardMoveEvent drawCardEvent = new CardMoveEvent(drawPhase, super.player, mcm);
            MatchDLO.applyEvent(m, drawCardEvent);
        }
    }
    
    @Override
    public String logMessage() {
        return "DRAW PHASE EVENT";
    }

    @Override
    public void applyFromDatabase(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}