package com.roominventory.roominventorysys.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Furniture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Furniture {
    @Id
    @Column("furnitureID")
    private Integer id;

    @Column("roomID")
    private Integer roomID;

    @Column("furniture_type")
    private String furnitureType;

    @Column("furniture_condition")
    private String furnitureCondition;

    public Furniture(Integer roomID, String furnitureType, String furnitureCondition) {
        this.roomID = roomID;
        this.furnitureType = furnitureType;
        this.furnitureCondition = furnitureCondition;
    }
}
