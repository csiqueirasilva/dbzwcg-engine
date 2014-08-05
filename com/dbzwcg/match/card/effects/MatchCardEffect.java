/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.card.effects;

/**
 *
 * @author csiqueira
 */
public abstract class MatchCardEffect {
    public abstract void play();
    public abstract void ifSuccessful();
    public abstract void playEffect();
    public abstract void postEffect();
    public abstract void activable();
}