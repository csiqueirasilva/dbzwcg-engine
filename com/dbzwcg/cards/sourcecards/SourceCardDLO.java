/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.sourcecards;

import com.dbzwcg.tools.enums.EnumTools;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;

/**
 *
 * @author csiqueira
 */
public class SourceCardDLO {

    private SourceCardDLO() {
    }
    
    public static void main (String args[]) {
        SourceCardDLO.getSourceCardHashMapBySagaNumber();
    }
    
    public static HashMap<String, HashMap<String, HashMap<String, SourceCard>>> getSourceCardHashMapBySagaNumber() {
        HashMap<String, HashMap<String, HashMap<String, SourceCard>>> map = new HashMap<String, HashMap<String, HashMap<String, SourceCard>>>();
        List<SourceCard> list = SourceCardDLO.getSourceCardList();

        Reflections reflections = new Reflections("com.dbzwcg.types.sagas");

        Object[] sagas = (Object[]) reflections.getSubTypesOf(Enum.class).toArray();

        for (SourceCard c : list) {
            try {
                for (int i = 0; i < sagas.length; i++) {
                    Enum[] sagaTypes = (Enum[]) ((Class) sagas[i]).getEnumConstants();
                    int k = Arrays.binarySearch(sagaTypes, c.getSaga());
                    if (k != -1) {
                        Enum saga = sagaTypes[k];
                        String dbPrefix = ((String) saga.getClass().getField("dbPrefix").get(null)).replace("_","");
                        if (map.get(dbPrefix) == null) {
                            map.put(dbPrefix, new HashMap<String, HashMap<String, SourceCard>>());
                        }
                        
                        HashMap<String, HashMap<String, SourceCard>> collectionMap = map.get(dbPrefix);
                        
                        String sagaStringIdentifier = EnumTools.convertMappedCharsJsonIdentifier(saga.toString());
                        
                        if(collectionMap.get(sagaStringIdentifier) == null) {
                            collectionMap.put(sagaStringIdentifier, new HashMap<String, SourceCard>());
                        }
                        
                        HashMap<String, SourceCard> sagaCards = collectionMap.get(sagaStringIdentifier);
                        
                        sagaCards.put(c.number, c);
                    }
                }
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(SourceCardDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(SourceCardDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SourceCardDLO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SourceCardDLO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return map;
    }

    public static HashMap<Integer, SourceCard> getSourceCardHashMapById() {
        HashMap<Integer, SourceCard> map = new HashMap<Integer, SourceCard>();
        List<SourceCard> list = SourceCardDLO.getSourceCardList();

        for (SourceCard c : list) {
            map.put(c.id, c);
        }

        return map;
    }

    public static List<SourceCard> getSourceCardList() {
        return new SourceCardDAO().getSourceCardList();
    }

    public static SourceCard getSourceCardById(Integer id) {
        return new SourceCardDAO().getSourceCardById(id);
    }
}
