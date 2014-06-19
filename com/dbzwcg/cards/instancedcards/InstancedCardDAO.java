/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.instancedcards;

import com.dbzwcg.cards.sourcecards.SourceCardDLO;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.types.FoilType;
import com.dbzwcg.types.sagas.CollectibleCardGameSagaType;
import com.dbzwcg.users.user.User;
import com.dbzwcg.users.user.UserDLO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

/**
 *
 * @author csiqueira
 */
public class InstancedCardDAO {
    
    private ConnectionFactory cf;
    
    InstancedCardDAO() {
        this.cf = new ConnectionFactory();
    }
    
    public static void main (String args[]) {
//        User u = UserDLO.getUserFromUsername("root@dbzwcg.com");
//        new InstancedCardDAO().addFromBoosterPack(u, 1, CollectibleCardGameSagaType.SAIYAN);
//        new InstancedCardDAO().getCollectionByUser(u);
        InstancedCard c = new InstancedCardDAO().getInstancedCardById(8);
        System.out.println(c.toString());
    }

    InstancedCard getInstancedCardById (Integer id) {
        InstancedCard card = null;

        if (id != null) {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT c FROM com.dbzwcg.cards.instancedcards.InstancedCard c WHERE id = :id", InstancedCard.class);
            card = (InstancedCard) q.setParameter("id", id).getSingleResult();
            this.cf.finish();
        }

        return card;
    }
    
    List<InstancedCard> getCollectionByUser (User usr) {
        List<InstancedCard> list = null;
        
        if(usr != null && usr.getId() != null) {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT i FROM com.dbzwcg.cards.instancedcards.InstancedCard i WHERE id_user = :id_user", InstancedCard.class);
            list = (List<InstancedCard>) q.setParameter("id_user", usr.getId()).getResultList();
            this.cf.finish();
        }
        
        return list;
    }
    
    List<InstancedCard> addFromBoosterPack (final User usr, final Integer numberOfPacks, final Enum saga) {
        List<InstancedCard> cards = null;

        if (usr != null && usr.getId() != null && saga != null && numberOfPacks != null && numberOfPacks > 0) {
            this.cf.start();
            
            Session session = (Session) this.cf.getEntityManager().getDelegate();
            
            final EntityManager em = this.cf.getEntityManager();
            
            cards = session.doReturningWork(new ReturningWork<List<InstancedCard>>() {

                public List<InstancedCard> execute(Connection cnctn) throws SQLException {
                    List<InstancedCard> cards = new ArrayList<InstancedCard>();
                    
                    PreparedStatement ps = cnctn.prepareStatement("SELECT * FROM generate_packs(?, ?) f(packnumber integer, cardid bigint, cardname text, rarity text, foil boolean, number text)");
                    ps.setInt(1, numberOfPacks);
                    
                    try {
                        ps.setString(2, ((String) saga.getDeclaringClass().getField("dbPrefix").get(null)) + saga.toString());
                    } catch (NoSuchFieldException ex) {
                        Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(InstancedCardDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ResultSet rs = ps.executeQuery();
                    
                    while(rs.next()) {
                        Integer idCard = rs.getInt(2);
                        String cardNumber = rs.getString(6);
                        Boolean foil = rs.getBoolean(5);
                                                
                        InstancedCard ic = new InstancedCard();
                        
                        // class for name to get card properties
                        ic.setSourceCard(SourceCardDLO.getSourceCardById(idCard));
                        ic.setTexturePath(InstancedCardDLO.getDefaultTexturePath(saga, cardNumber));
                        
                        if(foil) {
                            ic.setFoil(InstancedCardDLO.getFoilType(saga, cardNumber));
                        }
                        
                        ic.setOwner(usr);
                        ic.setTradeable(true);
                        ic.setOfferTrade(false);
                        ic.setSpecularMapPath(InstancedCardDLO.getSpecularMapPath(saga, cardNumber));
                        ic.setAlternativeArt(false);
                        
                        em.persist(ic);
                        cards.add(ic);
                    }

                    return cards;
                }
            });
            
            this.cf.finish();
        }
        
        return cards;
    }
    
}
