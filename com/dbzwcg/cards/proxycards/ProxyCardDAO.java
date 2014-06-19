/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.proxycards;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.cards.instancedcards.InstancedCardDLO;
import com.dbzwcg.cards.sourcecards.SourceCard;
import com.dbzwcg.cards.sourcecards.SourceCardDLO;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.tools.enums.EnumTools;
import com.dbzwcg.types.FoilType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

/**
 *
 * @author csiqueira
 */
class ProxyCardDAO {

    private ConnectionFactory cf;

    ProxyCardDAO() {
        this.cf = new ConnectionFactory();
    }

    public static void main (String args[]) {
        List<ProxyCard> cards = new ProxyCardDAO().getProxyCards();
        System.out.println(cards);
    }
    
    ProxyCard getProxyCardBySourceId(Integer id, String altArt, FoilType foil) {
        this.cf.start();

        EntityManager em = this.cf.getEntityManager();
        Query query = em.createQuery("select s from com.dbzwcg.cards.sourcecards.SourceCard s where id = :id");

        SourceCard srcCard = (SourceCard) query.setParameter("id", id).getSingleResult();
        ProxyCard card = new ProxyCard();
        String cardNumber = srcCard.getNumber();
        Enum saga = srcCard.getSaga();
        card.setSourceCard(srcCard);
        card.setTexturePath(altArt != null ? altArt : InstancedCardDLO.getDefaultTexturePath(saga, cardNumber));
        card.setSpecularMapPath(InstancedCardDLO.getSpecularMapPath(saga, cardNumber));
        card.setFoil(foil);
        card.setAlternativeArt(altArt != null ? true : false);
        
        this.cf.finish();

        return card;
    }
    
    List<ProxyCard> getProxyCards() {
        this.cf.start();
        Session session = (Session) this.cf.getEntityManager().getDelegate();

        EntityManager em = this.cf.getEntityManager();
        
        Query query = em.createQuery("select s from com.dbzwcg.cards.sourcecards.SourceCard s");
        List<SourceCard> sourceCards = query.getResultList();
        
        List<ProxyCard> cards = new ArrayList<ProxyCard>();
        for(int i = 0; i < sourceCards.size(); i++) {
            String cardNumber = sourceCards.get(i).getNumber();
            Enum saga = sourceCards.get(i).getSaga();
            
            ProxyCard ic = new ProxyCard();

            ic.setSourceCard(sourceCards.get(i));
            ic.setTexturePath(InstancedCardDLO.getDefaultTexturePath(saga, cardNumber));
            ic.setSpecularMapPath(InstancedCardDLO.getSpecularMapPath(saga, cardNumber));

            cards.add(ic);
        }

        this.cf.finish();

        return cards;
    }
}