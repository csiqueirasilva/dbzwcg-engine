package com.dbzwcg.cards.sourcecards.personalities;

import com.dbzwcg.cards.sourcecards.SourceCard;
import com.dbzwcg.cards.sourcecards.SourceCardDLO;
import com.dbzwcg.services.sql.ConnectionFactory;
import com.dbzwcg.tools.enums.EnumJsonSerializer;
import com.dbzwcg.types.AlignmentType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="source_personalities")
@PrimaryKeyJoinColumn(name="id_source_card", referencedColumnName="ID")
public class PersonalityCard extends SourceCard {
    
    protected AlignmentType alignment;

    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "alignment")
    @Enumerated(EnumType.STRING)
    public AlignmentType getAlignment() {
        return this.alignment;
    }

    public void setAlignment(AlignmentType alignment) {
        this.alignment = alignment;
    }
    
    protected List<String> powerStages;
    
    @JoinTable(name = "personalities_power_stages", joinColumns = @JoinColumn(name = "id_source_card"))
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "powerstage")
    public List<String> getPowerStages() {
        return this.powerStages;
    }

    public void setPowerStages(List<String> powerStages) {
        this.powerStages = powerStages;
    }
    
    protected Integer PUR;

    @Column(name = "PUR")
    public Integer getPUR() {
        return this.PUR;
    }

    public void setPUR(Integer PUR) {
        this.PUR = PUR;
    }
    
    protected Integer level;
    
    @Column(name = "PERSONALITYLEVEL")    
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
}