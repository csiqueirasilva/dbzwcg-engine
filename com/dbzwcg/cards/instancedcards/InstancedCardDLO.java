/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.instancedcards;

import com.dbzwcg.types.FoilType;
import com.dbzwcg.users.user.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csiqueira
 */
public class InstancedCardDLO {
    
    public static String getDefaultTexturePath(Enum saga, String number) {
        String path = "images/cardimages/%prefix%/%saga%/%number%.jpg";
        
        String prefix = "..";
        try {
            prefix = ((String) saga.getDeclaringClass().getField("dbPrefix").get(null)).replace("_","");
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return path.replace("%prefix%", prefix).replace("%number%", number).replace("%saga%", saga.toString().toLowerCase());
    }
    
    public static FoilType getFoilType (Enum saga, String number) {
        FoilType ft;
        
        ft = FoilType.valueOf(saga.toString() + "_DEFAULT");
        
        return ft;
    }
    
    public static String getSpecularMapPath(Enum saga, String number) {
        String path = "images/cardimages/%prefix%/%saga%/specularmap.jpg";
        
        String prefix = "..";
        try {
            prefix = ((String) saga.getDeclaringClass().getField("dbPrefix").get(null)).replace("_","");
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return path.replace("%prefix%", prefix).replace("%number%", number).replace("%saga%", saga.toString().toLowerCase());
    }
    
    public static List<InstancedCard> getCollectionByUser(User usr) {
        return new InstancedCardDAO().getCollectionByUser(usr);
    }

    public static List<InstancedCard> addFromBoosterPack(final User usr, final Integer numberOfPacks, final Enum saga) {
        return new InstancedCardDAO().addFromBoosterPack(usr, numberOfPacks, saga);
    }
    
    public static InstancedCard getInstancedCardById (Integer id) { 
        return new InstancedCardDAO().getInstancedCardById(id);
    }
}
