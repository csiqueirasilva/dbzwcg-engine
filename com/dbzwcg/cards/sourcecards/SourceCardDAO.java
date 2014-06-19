/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.sourcecards;

import com.dbzwcg.cards.sourcecards.dragonballs.DragonBallCard;
import com.dbzwcg.cards.sourcecards.personalities.PersonalityCardDLO;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.tools.DBZCCGCompiler;
import com.dbzwcg.tools.enums.EnumTools;
import com.dbzwcg.types.AlignmentType;
import com.dbzwcg.types.CardType;
import com.dbzwcg.types.DragonBallType;
import com.dbzwcg.types.sagas.CollectibleCardGameSagaType;
import com.dbzwcg.types.PersonalityType;
import com.dbzwcg.types.RarityType;
import com.dbzwcg.types.StyleType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Query;
import javax.script.ScriptException;
import org.apache.commons.io.FileUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.UniqueTag;

/**
 *
 * @author csiqueira
 */
class SourceCardDAO {

    // use this to generate the database from the javascript card objects file (it is not json)
    public static void main (String[] args) throws IOException, ScriptException, Exception {
        new SourceCardDAO().readJavascriptFiles();
//        SourceCard c = SourceCardDLO.getSourceCardById(131);
//        System.out.println("TODO");
        
        
         // Convert VEGETA level 1
        PersonalityCardDLO.convertSourceCardToPersonalityCard(112,
                Arrays.asList("0", "2000", "2200", "2400", "2600", "2800", "3000", "3200", "3400", "3600", "3800"),
                AlignmentType.ROGUE,
                2,
                1);

        // Convert VEGETA level 2
        PersonalityCardDLO.convertSourceCardToPersonalityCard(113,
                Arrays.asList("0", "4200", "4700", "5200", "5700", "6200", "6700", "7200", "7700", "8200", "8700"),
                AlignmentType.ROGUE,
                4,
                2);

        // Convert VEGETA level 3
        PersonalityCardDLO.convertSourceCardToPersonalityCard(114,
                Arrays.asList("0", "9000", "10000", "11000", "12000", "13000", "14000", "15000", "16000", "17000", "18000"),
                AlignmentType.ROGUE,
                4,
                3);

        // Convert GOKU level 1
        PersonalityCardDLO.convertSourceCardToPersonalityCard(109,
                Arrays.asList("0", "500", "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400"),
                AlignmentType.HERO,
                1,
                1);

        // Convert GOKU level 2
        PersonalityCardDLO.convertSourceCardToPersonalityCard(110,
                Arrays.asList("0", "3200", "3700", "4200", "4700", "5200", "5700", "6200", "6700", "7200", "7700"),
                AlignmentType.HERO,
                2,
                2);

        // Convert GOKU level 3
        PersonalityCardDLO.convertSourceCardToPersonalityCard(111,
                Arrays.asList("0", "8000", "8500", "9000", "9500", "10000", "10500", "11000", "11500", "12000", "12500"),
                AlignmentType.HERO,
                3,
                3);
        
    }
    
    private ConnectionFactory cf;

    public SourceCardDAO() {
        this.cf = new ConnectionFactory();
    }
    
    public static String baseFolder = "C:\\Users\\csiqueira\\Documents\\NetBeansProjects\\DBZWCG\\src\\main\\webapp";
    public static String[] listSagaFiles = {"game\\SaiyanSaga.js"};
    public static String[] blackListJavascriptCardNumber = {"Foil"};

    private <T> Set fromNativeArrayToSet(Object src, Class<T> type, String pkg) {
        Set<T> list = null;
        
        if (src instanceof NativeArray && (type.isEnum() || type == Enum.class)) {
            list = new HashSet<T>();
            
            NativeArray array = (NativeArray) src;
            Object[] ids = array.getIds();
            for (int i = 0; i < ids.length; i++) {
                if(ids[i] instanceof Integer) {
                    Object o = array.get((Integer) ids[i], (Scriptable) array);
                    // Get as ordinal
                    if(o instanceof Double) {
                        Enum enm;
                        if(pkg != null) {
                            enm = EnumTools.getEnumTypeFromPackage(((Double) o).intValue(), pkg);
                        } else {
                            enm = (Enum) type.getEnumConstants()[((Double) o).intValue()];
                        }
                        
                        list.add((T) enm);
                    }
                }
            }
        }

        return list;
    }
    
    private <T> List fromNativeArrayToList(Object src, Class<T> type, String pkg) {
        List<T> list = null;
        
        if (src instanceof NativeArray && (type.isEnum() || type == Enum.class)) {
            list = new ArrayList<T>();
            NativeArray array = (NativeArray) src;
            Object[] ids = array.getIds();
            for (int i = 0; i < ids.length; i++) {
                if(ids[i] instanceof Integer) {
                    Object o = array.get((Integer) ids[i], (Scriptable) array);
                    // Get as ordinal
                    if(o instanceof Double) {
                        Enum enm;
                        if(pkg != null) {
                            enm = EnumTools.getEnumTypeFromPackage(((Double) o).intValue(), pkg);
                        } else {
                            enm = (Enum) type.getEnumConstants()[((Double) o).intValue()];
                        }
                        
                        list.add((T) enm);
                    }
                }
            }
        }

        return list;
    }

    void readJavascriptFiles() throws IOException, ScriptException, Exception {

        this.cf.start();

        Context c = Context.enter();
        c.setOptimizationLevel(-1);

        Scriptable scope = c.initStandardObjects();
        String initializer = FileUtils.readFileToString(new File(DBZCCGCompiler.baseFolder + File.separator + "game\\Initializer.js"));
        String types = FileUtils.readFileToString(new File(DBZCCGCompiler.baseFolder + File.separator + "game\\Types.js"));
        String cards = FileUtils.readFileToString(new File(DBZCCGCompiler.baseFolder + File.separator + "game\\SaiyanSaga.js"));

        NativeObject result = (NativeObject) c.evaluateString(scope, initializer + types + cards, "<cmd>", 1, null);

        Scriptable obj = (Scriptable) scope.get("DBZCCG", scope);

//        Object[] ids = obj.getIds();
//
//        for (int j = 0; j < ids.length; j++) {
//            System.out.println(ids[j]);
//        }

        CollectibleCardGameSagaType[] ccgSagas = CollectibleCardGameSagaType.values();

        for (int i = 0; i < ccgSagas.length; i++) {
            String sagaName = EnumTools.convertMappedCharsJsonIdentifier(ccgSagas[i].toString());

            Scriptable saga = (Scriptable) obj.get(sagaName, obj);

            Object[] ids = saga.getIds();

            for (int j = 0; j < ids.length; j++) {
                if (!Arrays.asList(blackListJavascriptCardNumber).contains(ids[j].toString())) {
                    Object card;
                    if (ids[j] instanceof Integer) {
                        card = (Object) saga.get((Integer) ids[j], saga);
                    } else {
                        card = (Object) saga.get((String) ids[j], saga);
                    }

                    if (card instanceof Scriptable) {
                        Scriptable scriptableCard = (Scriptable) card;
                        String name = scriptableCard.get("name", scriptableCard).toString();
                        String number = scriptableCard.get("number", scriptableCard).toString();
                        String description = scriptableCard.get("description", scriptableCard).toString();
                        CardType type = CardType.values()[((Double) scriptableCard.get("type", scriptableCard)).intValue()];
                        StyleType style = StyleType.values()[((Double) scriptableCard.get("style", scriptableCard)).intValue()];
                        Object headshot = scriptableCard.get("headshot", scriptableCard);
                        RarityType rarity = RarityType.values()[((Double) scriptableCard.get("rarity", scriptableCard)).intValue()];
                        CollectibleCardGameSagaType cardSaga = CollectibleCardGameSagaType.values()[((Double) scriptableCard.get("saga", scriptableCard)).intValue()];
                        Object effectType = scriptableCard.get("effectType", scriptableCard);
                        Object id = scriptableCard.get("id", scriptableCard);
                        Object personality = scriptableCard.get("personality", scriptableCard);
                        Object dbCode = scriptableCard.get("dbCode", scriptableCard);
                        Object limit = scriptableCard.get("limit", scriptableCard);

                        SourceCard srcCard;
                        
                        if (dbCode instanceof Double) {
                            srcCard = new DragonBallCard();
                            DragonBallType dbType = DragonBallType.values()[((Double) dbCode).intValue()];
                            ((DragonBallCard) srcCard).setDbCode(dbType);
                        } else {
                            srcCard = new SourceCard();
                        }
                        
                        srcCard.name = name;
                        srcCard.number = number;
                        srcCard.description = description;
                        srcCard.rarity = rarity;
                        srcCard.setSaga(cardSaga);
                        Set<CardType> cardTypes = new TreeSet<CardType>();
                        cardTypes.add(type);
                        srcCard.type = cardTypes;
                        srcCard.style = style;

                        if (id instanceof UniqueTag) {
                            id = null;
                        } else {
                            id = ((Double) id).intValue();
                        }

                        srcCard.id = (Integer) id;

                        if (limit instanceof UniqueTag) {
                            limit = null;
                        } else {
                            limit = ((Double) limit).intValue();
                        }

                        srcCard.limit = (Integer) limit;

                        srcCard.headshots = fromNativeArrayToSet(headshot, PersonalityType.class, null);
                        srcCard.effectTypes = fromNativeArrayToSet(effectType, Enum.class, "com.dbzwcg.types.effects");

                        if(personality instanceof Double) {
                            srcCard.personalities = new TreeSet<PersonalityType>();
                            srcCard.personalities.add(PersonalityType.values()[((Double) personality).intValue()]);
                        } else {
                            srcCard.personalities = fromNativeArrayToSet(personality, PersonalityType.class, null);
                        }
                        
                        this.cf.getEntityManager().persist(srcCard);

                    } else {
                        this.cf.finish();
                        throw new Exception(ids[j].toString() + "Not found");
                    }

                    //                    Scriptable cardName = (Scriptable) card.get("name", card);
//                    System.out.println(cardName.toString());
                }
            }
        }

        this.cf.finish();
        Context.exit();
    }
    
    List<SourceCard> getSourceCardList () {
        this.cf.start();
        Query query = this.cf.getEntityManager().createQuery("select s from com.dbzwcg.cards.sourcecards.SourceCard s");
        List<SourceCard> sourceCards = query.getResultList();
        this.cf.finish();
        return sourceCards;
    }
    
    SourceCard getSourceCardById (Integer id) {
        SourceCard card = null;

        if (id != null) {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT c FROM com.dbzwcg.cards.sourcecards.SourceCard c WHERE id = :id", SourceCard.class);
            card = (SourceCard) q.setParameter("id", id).getSingleResult();
            this.cf.finish();
        }

        return card;
    }
    
}
