package com.roominventory.roominventorysys.dto;

import com.roominventory.roominventorysys.model.Furniture;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FurnitureUpdateMessage {
    private Furniture furniture;
    private String status; // Add, Update

}
