package com.roominventory.roominventorysys.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {

    @Id
    @Column("roomID")
    private Integer id;

    @Column("blockID")
    private Integer blockID;

    @Column("room_number")
    private String roomNumber;

    @Column("floor")
    private Integer floor;

    @Column("max_occupants")
    private Integer maxOccupants;

    public Room(Integer blockID, String roomNumber, Integer floor, Integer maxOccupants) {
        this.blockID = blockID;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.maxOccupants = maxOccupants;
    }
}
