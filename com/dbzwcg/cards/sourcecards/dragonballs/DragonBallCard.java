package com.dbzwcg.cards.sourcecards.dragonballs;

import com.dbzwcg.cards.sourcecards.SourceCard;
import com.dbzwcg.tools.enums.EnumJsonSerializer;
import com.dbzwcg.types.DragonBallType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="source_dragonballs")
@PrimaryKeyJoinColumn(name="id_source_card", referencedColumnName="ID")
public class DragonBallCard extends SourceCard {
    protected DragonBallType dbCode;

    @JsonSerialize(using=EnumJsonSerializer.class)
    @Column(name = "DBCODE")
    @Enumerated(EnumType.STRING)
    public DragonBallType getDbCode() {
        return dbCode;
    }

    public void setDbCode(DragonBallType dbCode) {
        this.dbCode = dbCode;
    }
}