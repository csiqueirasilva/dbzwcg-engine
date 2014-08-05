/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.callbacks;

import com.dbzwcg.match.callback.returns.CallbackReturn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.log.MatchEvent;

/**
 *
 * @author csiqueira
 */
public abstract class Callback {
    protected Integer priority;
    protected Integer life;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }
    public abstract CallbackReturn exec(Match m, MatchEvent e);
}