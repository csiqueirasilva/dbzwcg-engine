/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.players;

import com.dbzwcg.match.cardgroup.MatchCardGroup;
import com.dbzwcg.match.mainpersonality.MatchMainPersonality;
import com.dbzwcg.match.callback.sets.CallbackSet;
import com.dbzwcg.players.Player;

/**
 *
 * @author csiqueira
 */
public class MatchPlayer {
//    {name: 'CPU', mainPersonality: {alignment: DBZCCG.Personality.alignment.Villain, currentMainPersonalityLevel: 1, currentPowerStageAboveZero: 5, currentAngerLevel: 0,
//                    angerLevelNeededToLevel: 5, personalities: [DBZCCG.Saiyan['173'], DBZCCG.Saiyan['174'], DBZCCG.Saiyan['175']]}
    /* JSON : id */

    private Player player;
    /* JSON : data as default conversion */
    private String name;
    private Integer id;
    private MatchCardGroup lifeDeck;
    private MatchCardGroup discardPile;
    private MatchCardGroup hand;
    private MatchCardGroup removedFromTheGame;
    private MatchCardGroup inPlay;
    private MatchCardGroup dragonballs;
    private MatchCardGroup nonCombats;
    private MatchCardGroup setAside;
    private MatchMainPersonality mainPersonality;
    private Boolean canLose;
    private Boolean loser;
    private Boolean handOnTable;
    private Boolean canMPPWin;
    private Boolean canDBWin;
    private Boolean skipCombatPhase;
    private Boolean skipDiscardPhase;
    private Boolean skipDrawPhase;
    private Boolean skipPowerUpPhase;
    private Boolean skipRejuvenationPhase;
    private Boolean skipDeclarePhase;
    private Boolean skipNonCombatPhase;
    private Integer drawPhaseQuantityDraw;
    private Integer discardPhaseHandSize;

    /* JSON : skip this in json */
    private CallbackSet powerStageGainCallbacks;
    private CallbackSet powerStageLoseCallbacks;
    private CallbackSet powerStageSetCallbacks;
    private CallbackSet startTurnCallbacks;
    private CallbackSet endTurnCallbacks;
    private CallbackSet beforeDrawPhaseCallbacks;
    private CallbackSet drawPhaseCallbacks;
    private CallbackSet afterDrawPhaseCallbacks;
    private CallbackSet beforeNonCombatPhaseCallbacks;
    private CallbackSet nonCombatPhaseCallbacks;
    private CallbackSet afterNonCombatPhaseCallbacks;
    private CallbackSet beforePowerUpPhaseCallbacks;
    private CallbackSet powerUpPhaseCallbacks;
    private CallbackSet afterPowerUpPhaseCallbacks;
    private CallbackSet beforeDeclarePhaseCallbacks;
    private CallbackSet declarePhaseCallbacks;
    private CallbackSet afterDeclarePhaseCallbacks;
    private CallbackSet beforeCombatPhaseCallbacks;
    private CallbackSet combatPhaseCallbacks;
    private CallbackSet afterCombatPhaseCallbacks;
    private CallbackSet beforeDiscardPhaseCallbacks;
    private CallbackSet discardPhaseCallbacks;
    private CallbackSet afterDiscardPhaseCallbacks;
    private CallbackSet beforeRejuvenationPhaseCallbacks;
    private CallbackSet rejuvenationPhaseCallbacks;
    private CallbackSet afterRejuvenationPhaseCallbacks;
    private CallbackSet transferCallbacks;
    private CallbackSet enteringCombatPhaseCallbacks;
    private CallbackSet attackPhaseCallbacks;
    private CallbackSet defendPhaseCallbacks;

    public CallbackSet getAttackPhaseCallbacks() {
        return attackPhaseCallbacks;
    }

    public void setAttackPhaseCallbacks(CallbackSet attackPhaseCallback) {
        this.attackPhaseCallbacks = attackPhaseCallback;
    }

    public CallbackSet getDefendPhaseCallbacks() {
        return defendPhaseCallbacks;
    }

    public void setDefendPhaseCallbacks(CallbackSet defendPhaseCallback) {
        this.defendPhaseCallbacks = defendPhaseCallback;
    }

    public CallbackSet getEnteringCombatPhaseCallbacks() {
        return enteringCombatPhaseCallbacks;
    }

    public void setEnteringCombatPhaseCallbacks(CallbackSet enteringCombatPhaseCallbacks) {
        this.enteringCombatPhaseCallbacks = enteringCombatPhaseCallbacks;
    }

    public Boolean getSkipCombatPhase() {
        return skipCombatPhase;
    }

    public void setSkipCombatPhase(Boolean skipCombatPhase) {
        this.skipCombatPhase = skipCombatPhase;
    }

    public Integer getDiscardPhaseHandSize() {
        return discardPhaseHandSize;
    }

    public void setDiscardPhaseHandSize(Integer discardPhaseHandSize) {
        this.discardPhaseHandSize = discardPhaseHandSize;
    }

    public Boolean getSkipDiscardPhase() {
        return skipDiscardPhase;
    }

    public void setSkipDiscardPhase(Boolean skipDiscardPhase) {
        this.skipDiscardPhase = skipDiscardPhase;
    }

    public Boolean getSkipDrawPhase() {
        return skipDrawPhase;
    }

    public void setSkipDrawPhase(Boolean skippedDrawPhase) {
        this.skipDrawPhase = skippedDrawPhase;
    }

    public Boolean getSkipPowerUpPhase() {
        return skipPowerUpPhase;
    }

    public void setSkipPowerUpPhase(Boolean skipPowerUpPhase) {
        this.skipPowerUpPhase = skipPowerUpPhase;
    }

    public Boolean getSkipRejuvenationPhase() {
        return skipRejuvenationPhase;
    }

    public void setSkipRejuvenationPhase(Boolean skipRejuvenationPhase) {
        this.skipRejuvenationPhase = skipRejuvenationPhase;
    }

    public Boolean getSkipDeclarePhase() {
        return skipDeclarePhase;
    }

    public void setSkipDeclarePhase(Boolean skipDeclarePhase) {
        this.skipDeclarePhase = skipDeclarePhase;
    }

    public Boolean getSkipNonCombatPhase() {
        return skipNonCombatPhase;
    }

    public void setSkipNonCombatPhase(Boolean skipNonCombatPhase) {
        this.skipNonCombatPhase = skipNonCombatPhase;
    }

    public CallbackSet getEndTurnCallbacks() {
        return endTurnCallbacks;
    }

    public void setEndTurnCallbacks(CallbackSet endTurnCallbacks) {
        this.endTurnCallbacks = endTurnCallbacks;
    }

    public CallbackSet getPowerStageGainCallbacks() {
        return powerStageGainCallbacks;
    }

    public void setPowerStageGainCallbacks(CallbackSet powerStageGainCallbacks) {
        this.powerStageGainCallbacks = powerStageGainCallbacks;
    }

    public CallbackSet getPowerStageLoseCallbacks() {
        return powerStageLoseCallbacks;
    }

    public void setPowerStageLoseCallbacks(CallbackSet powerStageLoseCallbacks) {
        this.powerStageLoseCallbacks = powerStageLoseCallbacks;
    }

    public CallbackSet getPowerStageSetCallbacks() {
        return powerStageSetCallbacks;
    }

    public void setPowerStageSetCallbacks(CallbackSet powerStageSetCallbacks) {
        this.powerStageSetCallbacks = powerStageSetCallbacks;
    }

    public Integer getDrawPhaseQuantityDraw() {
        return drawPhaseQuantityDraw;
    }

    public void setDrawPhaseQuantityDraw(Integer drawPhaseQuantityDraw) {
        this.drawPhaseQuantityDraw = drawPhaseQuantityDraw;
    }

    public CallbackSet getTransferCallbacks() {
        return transferCallbacks;
    }

    public void setTransferCallbacks(CallbackSet transferCallbacks) {
        this.transferCallbacks = transferCallbacks;
    }

    public CallbackSet getBeforeNonCombatPhaseCallbacks() {
        return beforeNonCombatPhaseCallbacks;
    }

    public void setBeforeNonCombatPhaseCallbacks(CallbackSet beforeNonCombatPhaseCallbacks) {
        this.beforeNonCombatPhaseCallbacks = beforeNonCombatPhaseCallbacks;
    }

    public CallbackSet getNonCombatPhaseCallbacks() {
        return nonCombatPhaseCallbacks;
    }

    public void setNonCombatPhaseCallbacks(CallbackSet nonCombatPhaseCallbacks) {
        this.nonCombatPhaseCallbacks = nonCombatPhaseCallbacks;
    }

    public CallbackSet getAfterNonCombatPhaseCallbacks() {
        return afterNonCombatPhaseCallbacks;
    }

    public void setAfterNonCombatPhaseCallbacks(CallbackSet afterNonCombatPhaseCallbacks) {
        this.afterNonCombatPhaseCallbacks = afterNonCombatPhaseCallbacks;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CallbackSet getBeforeDrawPhaseCallbacks() {
        return beforeDrawPhaseCallbacks;
    }

    public void setBeforeDrawPhaseCallbacks(CallbackSet beforeDrawPhaseCallbacks) {
        this.beforeDrawPhaseCallbacks = beforeDrawPhaseCallbacks;
    }

    public CallbackSet getDrawPhaseCallbacks() {
        return drawPhaseCallbacks;
    }

    public void setDrawPhaseCallbacks(CallbackSet drawPhaseCallbacks) {
        this.drawPhaseCallbacks = drawPhaseCallbacks;
    }

    public CallbackSet getAfterDrawPhaseCallbacks() {
        return afterDrawPhaseCallbacks;
    }

    public void setAfterDrawPhaseCallbacks(CallbackSet afterDrawPhaseCallbacks) {
        this.afterDrawPhaseCallbacks = afterDrawPhaseCallbacks;
    }

    public CallbackSet getBeforePowerUpPhaseCallbacks() {
        return beforePowerUpPhaseCallbacks;
    }

    public void setBeforePowerUpPhaseCallbacks(CallbackSet beforePowerUpPhaseCallbacks) {
        this.beforePowerUpPhaseCallbacks = beforePowerUpPhaseCallbacks;
    }

    public CallbackSet getPowerUpPhaseCallbacks() {
        return powerUpPhaseCallbacks;
    }

    public void setPowerUpPhaseCallbacks(CallbackSet powerUpPhaseCallbacks) {
        this.powerUpPhaseCallbacks = powerUpPhaseCallbacks;
    }

    public CallbackSet getAfterPowerUpPhaseCallbacks() {
        return afterPowerUpPhaseCallbacks;
    }

    public void setAfterPowerUpPhaseCallbacks(CallbackSet afterPowerUpPhaseCallbacks) {
        this.afterPowerUpPhaseCallbacks = afterPowerUpPhaseCallbacks;
    }

    public CallbackSet getBeforeDeclarePhaseCallbacks() {
        return beforeDeclarePhaseCallbacks;
    }

    public void setBeforeDeclarePhaseCallbacks(CallbackSet beforeDeclarePhaseCallbacks) {
        this.beforeDeclarePhaseCallbacks = beforeDeclarePhaseCallbacks;
    }

    public CallbackSet getDeclarePhaseCallbacks() {
        return declarePhaseCallbacks;
    }

    public void setDeclarePhaseCallbacks(CallbackSet declarePhaseCallbacks) {
        this.declarePhaseCallbacks = declarePhaseCallbacks;
    }

    public CallbackSet getAfterDeclarePhaseCallbacks() {
        return afterDeclarePhaseCallbacks;
    }

    public void setAfterDeclarePhaseCallbacks(CallbackSet afterDeclarePhaseCallbacks) {
        this.afterDeclarePhaseCallbacks = afterDeclarePhaseCallbacks;
    }

    public CallbackSet getBeforeCombatPhaseCallbacks() {
        return beforeCombatPhaseCallbacks;
    }

    public void setBeforeCombatPhaseCallbacks(CallbackSet beforeCombatPhaseCallbacks) {
        this.beforeCombatPhaseCallbacks = beforeCombatPhaseCallbacks;
    }

    public CallbackSet getCombatPhaseCallbacks() {
        return combatPhaseCallbacks;
    }

    public void setCombatPhaseCallbacks(CallbackSet combatPhaseCallbacks) {
        this.combatPhaseCallbacks = combatPhaseCallbacks;
    }

    public CallbackSet getAfterCombatPhaseCallbacks() {
        return afterCombatPhaseCallbacks;
    }

    public void setAfterCombatPhaseCallbacks(CallbackSet afterCombatPhaseCallbacks) {
        this.afterCombatPhaseCallbacks = afterCombatPhaseCallbacks;
    }

    public CallbackSet getBeforeDiscardPhaseCallbacks() {
        return beforeDiscardPhaseCallbacks;
    }

    public void setBeforeDiscardPhaseCallbacks(CallbackSet beforeDiscardPhaseCallbacks) {
        this.beforeDiscardPhaseCallbacks = beforeDiscardPhaseCallbacks;
    }

    public CallbackSet getDiscardPhaseCallbacks() {
        return discardPhaseCallbacks;
    }

    public void setDiscardPhaseCallbacks(CallbackSet discardPhaseCallbacks) {
        this.discardPhaseCallbacks = discardPhaseCallbacks;
    }

    public CallbackSet getAfterDiscardPhaseCallbacks() {
        return afterDiscardPhaseCallbacks;
    }

    public void setAfterDiscardPhaseCallbacks(CallbackSet afterDiscardPhaseCallbacks) {
        this.afterDiscardPhaseCallbacks = afterDiscardPhaseCallbacks;
    }

    public CallbackSet getBeforeRejuvenationPhaseCallbacks() {
        return beforeRejuvenationPhaseCallbacks;
    }

    public void setBeforeRejuvenationPhaseCallbacks(CallbackSet beforeRejuvenationPhaseCallbacks) {
        this.beforeRejuvenationPhaseCallbacks = beforeRejuvenationPhaseCallbacks;
    }

    public CallbackSet getRejuvenationPhaseCallbacks() {
        return rejuvenationPhaseCallbacks;
    }

    public void setRejuvenationPhaseCallbacks(CallbackSet rejuvenationPhaseCallbacks) {
        this.rejuvenationPhaseCallbacks = rejuvenationPhaseCallbacks;
    }

    public CallbackSet getAfterRejuvenationPhaseCallbacks() {
        return afterRejuvenationPhaseCallbacks;
    }

    public void setAfterRejuvenationPhaseCallbacks(CallbackSet afterRejuvenationPhaseCallbacks) {
        this.afterRejuvenationPhaseCallbacks = afterRejuvenationPhaseCallbacks;
    }

    public Boolean getCanMPPWin() {
        return canMPPWin;
    }

    public void setCanMPPWin(Boolean canMPPWin) {
        this.canMPPWin = canMPPWin;
    }

    public Boolean getCanDBWin() {
        return canDBWin;
    }

    public void setCanDBWin(Boolean canDBWin) {
        this.canDBWin = canDBWin;
    }

    public Boolean getHandOnTable() {
        return handOnTable;
    }

    public void setHandOnTable(Boolean handOnTable) {
        this.handOnTable = handOnTable;
    }

    public Boolean getCanLose() {
        return canLose;
    }

    public void setCanLose(Boolean canLose) {
        this.canLose = canLose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLoser() {
        return loser;
    }

    public void setLoser(Boolean loser) {
        this.loser = loser;
    }

    public CallbackSet getStartTurnCallbacks() {
        return startTurnCallbacks;
    }

    public void setStartTurnCallbacks(CallbackSet startTurnCallbacks) {
        this.startTurnCallbacks = startTurnCallbacks;
    }

    public MatchMainPersonality getMainPersonality() {
        return mainPersonality;
    }

    public void setMainPersonality(MatchMainPersonality mainPersonality) {
        this.mainPersonality = mainPersonality;
    }

    public MatchCardGroup getLifeDeck() {
        return lifeDeck;
    }

    public void setLifeDeck(MatchCardGroup lifeDeck) {
        this.lifeDeck = lifeDeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MatchCardGroup getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(MatchCardGroup discardPile) {
        this.discardPile = discardPile;
    }

    public MatchCardGroup getHand() {
        return hand;
    }

    public void setHand(MatchCardGroup hand) {
        this.hand = hand;
    }

    public MatchCardGroup getRemovedFromTheGame() {
        return removedFromTheGame;
    }

    public void setRemovedFromTheGame(MatchCardGroup removedFromTheGame) {
        this.removedFromTheGame = removedFromTheGame;
    }

    public MatchCardGroup getInPlay() {
        return inPlay;
    }

    public void setInPlay(MatchCardGroup inPlay) {
        this.inPlay = inPlay;
    }

    public MatchCardGroup getDragonballs() {
        return dragonballs;
    }

    public void setDragonballs(MatchCardGroup dragonballs) {
        this.dragonballs = dragonballs;
    }

    public MatchCardGroup getNonCombats() {
        return nonCombats;
    }

    public void setNonCombats(MatchCardGroup nonCombats) {
        this.nonCombats = nonCombats;
    }

    public MatchCardGroup getSetAside() {
        return setAside;
    }

    public void setSetAside(MatchCardGroup setAside) {
        this.setAside = setAside;
    }
}