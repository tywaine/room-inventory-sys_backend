package com.roominventory.roominventorysys.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OccupantDeleteMessage {
    private Integer occupantId;
    private String message;
}
