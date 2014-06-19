/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.proxycards;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.types.FoilType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@PrimaryKeyJoinColumn(name="id_instance_card", referencedColumnName="ID")
@Entity(name = "com.dbzwcg.cards.proxycards.ProxyCard")
@Table(name = "proxy_cards")
public class ProxyCard extends InstancedCard implements Serializable {

    public ProxyCard() {
        this.foil = null;
        this.tradeable = false;
        this.offerTrade = false;
        this.alternativeArt = false;
    }
    
    @Override
    public void setFoil(FoilType f) {
    }
    
    @Override
    public void setTradeable(Boolean tradeable) {
    }
        
    @Override
    public void setOfferTrade(Boolean tradeable) {
    }
    
    @Override
    public void setAlternativeArt(Boolean alternativeArt) {
    }

}
