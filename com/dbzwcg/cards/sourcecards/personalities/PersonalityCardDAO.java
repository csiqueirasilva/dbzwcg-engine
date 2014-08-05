/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.sourcecards.personalities;

import com.dbzwcg.cards.sourcecards.SourceCard;
import com.dbzwcg.cards.sourcecards.SourceCardDLO;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.types.AlignmentType;
import java.util.List;

/**
 *
 * @author csiqueira
 */
class PersonalityCardDAO {

    ConnectionFactory cf;
    
    PersonalityCardDAO() {
        this.cf = new ConnectionFactory();
    }
    
    void convertSourceCardToPersonalityCard(Integer id, List<String> powerStages, AlignmentType alignment, String PUR, Integer level) {
        SourceCard c = SourceCardDLO.getSourceCardById(id);
        PersonalityCard p = new PersonalityCard();

        p.setSaga(c.getSaga());
        p.setRarity(c.getRarity());
        p.setStyle(c.getStyle());
        p.setType(c.getType());
        p.setName(c.getName());
        p.setDescription(c.getDescription());
        p.setEffectTypes(c.getEffectTypes());
        p.setLimit(c.getLimit());
        p.setPersonalities(c.getPersonalities());
        p.setHeadshots(c.getHeadshots());
        p.setNumber(c.getNumber());
        p.setCollectionType(c.getCollectionType());

        p.setPowerStages(powerStages);
        p.setPUR(PUR);
        p.setAlignment(alignment);
        p.setLevel(level);

        this.cf.start();

        this.cf.getEntityManager().remove(this.cf.getEntityManager().find(SourceCard.class, id));

        this.cf.finish();

        this.cf.start();

        this.cf.getEntityManager().persist(p);

        this.cf.finish();
    }
}