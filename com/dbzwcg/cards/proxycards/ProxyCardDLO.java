/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.proxycards;

import java.util.List;

/**
 *
 * @author csiqueira
 */
public class ProxyCardDLO {
    private ProxyCardDLO () {
    }
    
    public static List<ProxyCard> getProxyCards() {
        return new ProxyCardDAO().getProxyCards();
    }
    
    public static ProxyCard getProxyCardBySourceId (Integer id) {
        return new ProxyCardDAO().getProxyCardBySourceId(id, null, null);
    }
}
