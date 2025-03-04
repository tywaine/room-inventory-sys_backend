package com.roominventory.roominventorysys.dto;

import com.roominventory.roominventorysys.model.Occupant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OccupantUpdateMessage {
    private Occupant occupant;
    private String status; // Add, Update
}
