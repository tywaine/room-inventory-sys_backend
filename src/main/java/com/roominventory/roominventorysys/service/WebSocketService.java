package com.roominventory.roominventorysys.service;

import com.roominventory.roominventorysys.dto.FurnitureDeleteMessage;
import com.roominventory.roominventorysys.dto.FurnitureUpdateMessage;
import com.roominventory.roominventorysys.dto.OccupantDeleteMessage;
import com.roominventory.roominventorysys.dto.OccupantUpdateMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendOccupantUpdate(OccupantUpdateMessage message) {
        messagingTemplate.convertAndSend("/topic/occupantUpdates", message);
    }

    public void sendOccupantDelete(OccupantDeleteMessage message) {
        messagingTemplate.convertAndSend("/topic/occupantDeletes", message);
    }

    public void sendFurnitureUpdate(FurnitureUpdateMessage message) {
        messagingTemplate.convertAndSend("/topic/furnitureUpdates", message);
    }

    public void sendFurnitureDelete(FurnitureDeleteMessage message) {
        messagingTemplate.convertAndSend("/topic/furnitureDeletes", message);
    }
}
