package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.dto.FurnitureDeleteMessage;
import com.roominventory.roominventorysys.dto.FurnitureUpdateMessage;
import com.roominventory.roominventorysys.dto.OccupantDeleteMessage;
import com.roominventory.roominventorysys.dto.OccupantUpdateMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/occupantUpdates")
    @SendTo("/topic/occupantUpdates")
    public OccupantUpdateMessage sendOccupantUpdate(OccupantUpdateMessage message) {
        return message;
    }

    @MessageMapping("/occupantDeletes")
    @SendTo("/topic/occupantDeletes")
    public OccupantDeleteMessage sendOccupantDelete(OccupantDeleteMessage message) {
        return message;
    }

    @MessageMapping("/furnitureUpdates")
    @SendTo("/topic/furnitureUpdates")
    public FurnitureUpdateMessage sendFurnitureUpdate(FurnitureUpdateMessage message) {
        return message;
    }

    @MessageMapping("/furnitureDeletes")
    @SendTo("/topic/furnitureDeletes")
    public FurnitureDeleteMessage sendFurnitureDelete(FurnitureDeleteMessage message) {
        return message;
    }
}
