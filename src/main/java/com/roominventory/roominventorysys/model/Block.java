package com.roominventory.roominventorysys.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.management.ConstructorParameters;

@Table("Blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Block {
    @Id
    @Column("blockID")
    private Integer id;

    @Column("name")
    private Character name;

    @Column("max_rooms")
    private Integer maxRooms;

    public Block(Character name, Integer maxRooms) {
        this.name = name;
        this.maxRooms = maxRooms;
    }
}
