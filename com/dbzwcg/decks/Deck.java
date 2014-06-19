package com.dbzwcg.decks;

import com.dbzwcg.cards.instancedcards.InstancedCard;
import com.dbzwcg.tools.enums.EnumJsonSerializer;
import com.dbzwcg.types.AlignmentType;
import com.dbzwcg.users.authority.Authority;
import com.dbzwcg.users.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author csiqueira
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jid")
@Entity(name = "com.dbzwcg.decks.Deck")
@SequenceGenerator(name = Deck.DECK_SEQUENCE_NAME, sequenceName = Deck.DECK_SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@org.hibernate.annotations.Table(appliesTo = "DECKS")
@Table(name = "DECKS")
public class Deck implements Serializable {

    public static final String DECK_SEQUENCE_NAME = "SEQ_DECKS";
    protected String displayName;
    protected List<InstancedCard> cards;
    protected User owner;
    protected Integer id;
    protected AlignmentType alignment;
    protected List<InstancedCard> mainPersonality;
    
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "alignment")
    public AlignmentType getAlignment() {
        return this.alignment;
    }

    public void setAlignment(AlignmentType alingment) {
        this.alignment = alingment;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mainpersonalitycards_in_deck",
            joinColumns =
            @JoinColumn(name = "id_deck"),
            inverseJoinColumns =
            @JoinColumn(name = "id_card"))
    public List<InstancedCard> getMainPersonality() {
        return this.mainPersonality;
    }
    
    
    public void setMainPersonality(List<InstancedCard> mainPersonality) {
        this.mainPersonality = mainPersonality;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cards_in_deck",
            joinColumns =
            @JoinColumn(name = "id_deck"),
            inverseJoinColumns =
            @JoinColumn(name = "id_card"))
    public List<InstancedCard> getCards() {
        return this.cards;
    }

    public void setCards(List<InstancedCard> cards) {
        this.cards = cards;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DECK_SEQUENCE_NAME)
    @Column(name = "ID")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}