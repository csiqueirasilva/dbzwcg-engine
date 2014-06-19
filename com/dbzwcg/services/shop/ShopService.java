/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.services.shop;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.cards.instancedcards.InstancedCardDLO;
import com.dbzwcg.exceptions.ShopException;
import com.dbzwcg.players.Player;
import com.dbzwcg.players.PlayerDLO;
import com.dbzwcg.types.sagas.CollectibleCardGameSagaType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class ShopService {
    public final static Integer pointPackPrice = 3;
    
    public static void main (String args[]) throws ShopException {
        Player u = PlayerDLO.getPlayerFromEmail("root@dbzwcg.com");
        buyPack(CollectibleCardGameSagaType.SAIYAN, u, 1);
    }
    
    public static List<InstancedCard> buyPack (Enum saga, Player usr, Integer numberOfPacks) throws ShopException {
        List<InstancedCard> list = null;
        
        if(usr != null && usr.getId() != null && saga != null) {
            if(usr.getPoints() != null && usr.getPoints() >= (pointPackPrice * numberOfPacks)) {
                list = InstancedCardDLO.addFromBoosterPack(usr, numberOfPacks, saga);
                PlayerDLO.updatePoints(usr.getId(), -(pointPackPrice * numberOfPacks));
            } else if (numberOfPacks < 0) {
                throw new ShopException(ShopException.INVALID_PURCHASE);
            } else {
                throw new ShopException(ShopException.NOT_ENOUGH_POINTS);
            }
            
        } else if (saga == null) {
            throw new ShopException(ShopException.NO_VALID_SAGA);            
        } else {
            throw new ShopException(ShopException.NO_VALID_USER);
        }
        
        return list;
    }
}
