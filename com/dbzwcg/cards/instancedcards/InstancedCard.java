/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbzwcg.cards.instancedcards;

import com.dbzwcg.cards.sourcecards.SourceCard;
import com.dbzwcg.tools.enums.EnumJsonSerializer;
import com.dbzwcg.tools.objects.IdValueJsonSerializer;
import com.dbzwcg.types.FoilType;
import com.dbzwcg.users.user.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "com.dbzwcg.cards.instancedcards.InstancedCard")
@SequenceGenerator(name = InstancedCard.SEQUENCE_NAME, sequenceName = InstancedCard.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@org.hibernate.annotations.Table(appliesTo = "instance_cards")
@Table(name = "instance_cards")
@Inheritance(strategy = InheritanceType.JOINED)
public class InstancedCard implements Serializable {

    public final static String SEQUENCE_NAME = "seq_instance_cards";
    protected Integer id;
    protected String texturePath;
    protected SourceCard sourceCard;
    protected FoilType foil;
    protected Boolean alternativeArt;
    protected String specularMapPath;
    protected Boolean offerTrade;
    protected User owner;
    protected Boolean tradeable;

    @JsonSerialize(using=IdValueJsonSerializer.class)
    @OneToOne(optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id_source_card", nullable = false)
    public SourceCard getSourceCard() {
        return this.sourceCard;
    }

    public void setSourceCard(SourceCard sourceCard) {
        this.sourceCard = sourceCard;
    }

    @Column(name = "tradeable")
    public Boolean getTradeable() {
        return tradeable;
    }

    public void setTradeable(Boolean tradeable) {
        this.tradeable = tradeable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "texture")
    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "foil")
    public FoilType getFoil() {
        return foil;
    }

    public void setFoil(FoilType foil) {
        this.foil = foil;
    }

    @Column(name = "alt_art")
    public Boolean getAlternativeArt() {
        return alternativeArt;
    }

    public void setAlternativeArt(Boolean alternativeArt) {
        this.alternativeArt = alternativeArt;
    }

    @Column(name = "specular_map")
    public String getSpecularMapPath() {
        return specularMapPath;
    }

    public void setSpecularMapPath(String specularMapPath) {
        this.specularMapPath = specularMapPath;
    }

    @Column(name = "offer_trade")
    public Boolean getOfferTrade() {
        return offerTrade;
    }

    public void setOfferTrade(Boolean offerTrade) {
        this.offerTrade = offerTrade;
    }

    @JsonSerialize(using=IdValueJsonSerializer.class)
    @OneToOne(optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id_user", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}