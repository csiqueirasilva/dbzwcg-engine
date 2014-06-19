/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.decks;

import com.dbzwcg.cards.proxycards.ProxyCard;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.users.user.User;
import com.dbzwcg.users.user.UserDLO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author csiqueira
 */
public class DeckDAO {

    private ConnectionFactory cf;

    DeckDAO() {
        this.cf = new ConnectionFactory();
    }

    public static void main (String[] args) {
        //Deck d = new DeckDAO().getDeckById(2);
        
        User p = UserDLO.getUserFromUsername("root@dbzwcg.com");
        System.out.println(new DeckDAO().removeDeckById(p, 1));
        
        //System.out.println(d.toString());
    }
    
    boolean removeDeckById(User u, Integer id) {
        Deck d = null;
        boolean ret = false;
        if(id != null) {
            this.cf.start();
            EntityManager em = this.cf.getEntityManager();
            
            Query q = em.createQuery("SELECT d FROM com.dbzwcg.decks.Deck d WHERE id = :id AND id_user = :id_user", Deck.class);
            
            try {
                d = (Deck) q.setParameter("id", id).setParameter("id_user", u.getId()).getSingleResult();
                em.remove(d);
                ret = true;
            } catch (javax.persistence.NoResultException e) {
                
            }
            
            this.cf.finish();
        }
        return ret;
    }
    
    Deck createDeckByUser(User usr) {
        Deck d = null;
        if(usr != null && usr.getId() != null) {
            d = new Deck();
            d.setOwner(usr);
            d.displayName = "Untitled Deck";
            this.cf.start();
            this.cf.getEntityManager().persist(d);
            this.cf.finish();
        }
        return d;
    }

    Deck getDeckByUserAndId (Integer id, User u) {
        Deck deck = null;

        if (id != null && u != null && u.getId() != null) {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT d FROM com.dbzwcg.decks.Deck d WHERE id = :id AND id_user = :id_user", Deck.class);
            deck = (Deck) q.setParameter("id", id).setParameter("id_user", u.getId()).getSingleResult();
            this.cf.finish();
        }

        return deck;
    }
    
    Deck getDeckById (Integer id) {
        Deck deck = null;

        if (id != null) {
            try {
                this.cf.start();
                Query q = this.cf.getEntityManager().createQuery("SELECT d FROM com.dbzwcg.decks.Deck d WHERE id = :id", Deck.class);
                deck = (Deck) q.setParameter("id", id).getSingleResult();
            } catch (NoResultException e) {
            }
            this.cf.finish();
        }

        return deck;
    }
    
    void updateDeck(Deck d) {
        if(d != null && d.getId()!= null && d.getOwner() != null) {
            this.cf.start();
            EntityManager em = this.cf.getEntityManager();
            Session session = (Session) em.getDelegate();
            session
                .createSQLQuery("DELETE FROM instance_cards WHERE id in (SELECT * FROM proxy_cards) AND id "
                    + "in (SELECT id_card FROM cards_in_deck WHERE id_deck = :id_deck);")
                .setParameter("id_deck", d.getId()).executeUpdate();

            if(d.getMainPersonality() != null) {
                for(int i = 0; i < d.getMainPersonality().size(); i++) {
                    if(d.getMainPersonality().get(i) instanceof ProxyCard && d.getMainPersonality().get(i).getId() == null) {
                        em.persist(d.getMainPersonality().get(i));
                    }
                }
            }
            
            if(d.getCards() != null) {
                for(int i = 0; i < d.getCards().size(); i++) {
                    if(d.getCards().get(i) instanceof ProxyCard && d.getCards().get(i).getId() == null) {
                        em.persist(d.getCards().get(i));
                    }
                }
            }
            
            em.merge(d);
            this.cf.finish();
        }
    }
    
    List<Deck> getDecksByUser(User usr) {
        List<Deck> decks = null;

        if (usr != null && usr.getId() != null) {
            this.cf.start();
            Query q = this.cf.getEntityManager().createQuery("SELECT d FROM com.dbzwcg.decks.Deck d WHERE id_user = :id_user", Deck.class);
            decks = (List<Deck>) q.setParameter("id_user", usr.getId()).getResultList();
            this.cf.finish();
        }

        return decks;
    }
}