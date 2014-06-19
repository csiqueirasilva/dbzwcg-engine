package com.dbzwcg.cards.sourcecards;

import com.dbzwcg.tools.enums.EnumJsonSerializer;
import com.dbzwcg.tools.enums.EnumTools;
import com.dbzwcg.tools.enums.CollectionEnumJsonSerializer;
import com.dbzwcg.types.CardType;
import com.dbzwcg.types.CollectionType;
import com.dbzwcg.types.PersonalityType;
import com.dbzwcg.types.RarityType;
import com.dbzwcg.types.StyleType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "com.dbzwcg.cards.sourcecards.SourceCard")
@org.hibernate.annotations.Table(appliesTo = "source_cards")
@SequenceGenerator(name = SourceCard.SOURCE_CARD_SEQUENCE_NAME, sequenceName = SourceCard.SOURCE_CARD_SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@Table(name = "source_cards")
@Inheritance(strategy=InheritanceType.JOINED)
public class SourceCard implements Serializable {

    protected Integer id;
    protected Enum saga;
    protected String number;
    protected String name;
    protected String description;
    protected Set<CardType> type;
    protected Set<Enum> effectTypes;
    protected RarityType rarity;
    protected Set<PersonalityType> personalities;
    protected Set<PersonalityType> headshots;
    protected Integer limit;
    protected StyleType style;
    protected CollectionType collectionType;
    
    
    public final static String SOURCE_CARD_SEQUENCE_NAME = "SEQ_SOURCE_CARDS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SOURCE_CARD_SEQUENCE_NAME)
    @Column(name = "ID")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "COLLECTION")
    @Convert(converter = EnumTools.DatabaseConverter.class)
    public CollectionType getCollectionType () {
        return this.collectionType;
    }

    public void setCollectionType (CollectionType c) {
        this.collectionType = c;
    }
    
    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "SAGA")
    @Convert(converter = EnumTools.DatabaseConverter.class)
    public Enum getSaga() {
        return this.saga;
    }

    public void setSaga(Enum saga) {
        try {
            this.collectionType = (CollectionType) saga.getClass().getField("collection").get(null);
            this.saga = saga;
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(SourceCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(SourceCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SourceCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SourceCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Column(name = "NUMBER")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSerialize(using=CollectionEnumJsonSerializer.class)
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = CardType.class, fetch = FetchType.EAGER)
    @JoinTable(name = "types_source_cards", joinColumns = @JoinColumn(name = "id_source_card"))
    public Set<CardType> getType() {
        return this.type;
    }

    public void setType(Set<CardType> type) {
        this.type = type;
    }

    @JsonSerialize(using=CollectionEnumJsonSerializer.class)
    @Convert(converter = EnumTools.DatabaseConverter.class)
    @ElementCollection(targetClass = Enum.class, fetch = FetchType.EAGER)
    @JoinTable(name = "effects_source_cards", joinColumns = @JoinColumn(name = "id_source_card"))
    @Column(name = "effect")
    public Set<Enum> getEffectTypes() {
        return this.effectTypes;
    }

    public void setEffectTypes(Set<Enum> effectTypes) {
        this.effectTypes = effectTypes;
    }

    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "RARITY")
    @Enumerated(EnumType.STRING)
    public RarityType getRarity() {
        return this.rarity;
    }

    public void setRarity(RarityType rarity) {
        this.rarity = rarity;
    }

    @JsonSerialize(using=CollectionEnumJsonSerializer.class)
    @Convert(converter = EnumTools.DatabaseConverter.class)
    @ElementCollection(targetClass = Enum.class, fetch = FetchType.EAGER)
    @JoinTable(name = "personalities_source_cards", joinColumns = @JoinColumn(name = "id_source_card"))
    @Column(name = "personality")
    public Set<PersonalityType> getPersonalities() {
        return this.personalities;
    }

    public void setPersonalities(Set<PersonalityType> personalities) {
        this.personalities = personalities;
    }

    @JsonSerialize(using=CollectionEnumJsonSerializer.class)
    @Convert(converter = EnumTools.DatabaseConverter.class)
    @ElementCollection(targetClass = Enum.class, fetch = FetchType.EAGER)
    @JoinTable(name = "headshots_source_cards", joinColumns = @JoinColumn(name = "id_source_card"))
    @Column(name = "personality")
    public Set<PersonalityType> getHeadshots() {
        return this.headshots;
    }

    public void setHeadshots(Set<PersonalityType> headshots) {
        this.headshots = headshots;
    }

    @Column(name = "DECKLIMIT")
    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "STYLE")
    @Enumerated(EnumType.STRING)
    public StyleType getStyle() {
        return this.style;
    }

    public void setStyle(StyleType style) {
        this.style = style;
    }
}