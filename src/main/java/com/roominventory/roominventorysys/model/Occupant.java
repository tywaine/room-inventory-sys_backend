package com.roominventory.roominventorysys.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Occupants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Occupant {
    @Id
    @Column("occupantID")
    private Integer id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("id_Number")
    private String idNumber;

    @Column("email")
    private String email;

    @Column("phone_number")
    private String phoneNumber;

    @Column("roomID")
    private Integer roomID;

    @Column("date_added")
    private LocalDateTime dateAdded;

    public Occupant(String firstName, String lastName, String idNumber, String email, String phoneNumber, Integer roomID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roomID = roomID;
    }

    public Occupant(String firstName, String lastName, String idNumber, String email, String phoneNumber, Integer roomID, LocalDateTime dateAdded) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roomID = roomID;
        this.dateAdded = dateAdded;
    }
}
