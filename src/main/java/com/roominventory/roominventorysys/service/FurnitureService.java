package com.roominventory.roominventorysys.service;

import com.roominventory.roominventorysys.dto.FurnitureDeleteMessage;
import com.roominventory.roominventorysys.dto.FurnitureUpdateMessage;
import com.roominventory.roominventorysys.model.Furniture;
import com.roominventory.roominventorysys.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final WebSocketService webSocketService;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository, WebSocketService webSocketService) {
        this.furnitureRepository = furnitureRepository;
        this.webSocketService = webSocketService;
    }

    public Furniture addNewFurniture(Furniture furniture) {
        System.out.println(furniture);
        Furniture furnitureSaved = furnitureRepository.save(furniture);
        Optional<Furniture> finalFurniture = getFurnitureById(furnitureSaved.getId());
        webSocketService.sendFurnitureUpdate(new FurnitureUpdateMessage(finalFurniture.get(), "ADD"));
        return finalFurniture.get();
    }

    // Get all furniture
    public List<Furniture> getFurniture() {
        return (List<Furniture>) furnitureRepository.findAll();
    }

    // Get furniture by ID
    public Optional<Furniture> getFurnitureById(Integer id) {
        return furnitureRepository.findById(id);
    }

    @Transactional
    public void updateFurniture(Integer furnitureID, Integer roomID, String furnitureType, String furnitureCondition) {
        Furniture furniture = furnitureRepository.findById(furnitureID)
                .orElseThrow(() -> new IllegalArgumentException("Furniture with id " + furnitureID + " does not exist"));

        if(roomID != null && !roomID.equals(furniture.getRoomID())) {
            furniture.setRoomID(roomID);
        }

        if(furnitureType != null && !furnitureType.equals(furniture.getFurnitureType())) {
            furniture.setFurnitureType(furnitureType);
        }

        if(furnitureCondition != null && !furnitureCondition.equals(furniture.getFurnitureCondition())) {
            furniture.setFurnitureCondition(furnitureCondition);
        }

        furnitureRepository.save(furniture);
        webSocketService.sendFurnitureUpdate(new FurnitureUpdateMessage(furniture, "UPDATE"));
    }

    // Delete furniture by ID
    public void deleteFurniture(Integer id) {
        furnitureRepository.deleteById(id);
        webSocketService.sendFurnitureDelete(new FurnitureDeleteMessage(id, "DELETED FURNITURE." + " id= " + id));
    }
}
