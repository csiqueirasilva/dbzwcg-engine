/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.match.callback.sets;

import com.dbzwcg.match.callback.returns.CallbackReturn;
import com.dbzwcg.match.Match;
import com.dbzwcg.match.callbacks.Callback;
import com.dbzwcg.match.log.MatchEvent;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author csiqueira
 */
public class CallbackSet extends TreeSet<Callback> implements SortedSet<Callback> {
    final private static Comparator<? super Callback> comparator = new Comparator<Callback>() {
        public int compare(Callback o1, Callback o2) {
            return o1.getPriority() - o2.getPriority();
        }
    };
    
    public CallbackReturn resolveCallbacks (Match m, MatchEvent event) {
        CallbackReturn ret = null;
        
        for(int i = 0; i < this.size(); i++) {
            Callback callback = this.last();
            ret = callback.exec(m, event);
            
            if(callback.getLife() == 0) {
                this.pollLast();
            }
        }
        
        if(ret == null) {
            ret = new CallbackReturn();
        }
        
        return ret;
    }
    
    @Override
    public Comparator<? super Callback> comparator() {
        return CallbackSet.comparator;
    }
}