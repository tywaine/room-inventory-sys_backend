package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.model.Furniture;
import com.roominventory.roominventorysys.service.FurnitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/furniture")
public class FurnitureController {
    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService){
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public List<Furniture> getFurniture(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Optional<Furniture> furniture = furnitureService.getFurnitureById(id);

            if (furniture.isPresent()) {
                return List.of(furniture.get());
            } else {
                return new ArrayList<>();
            }
        }

        return furnitureService.getFurniture();
    }

    @PostMapping
    public ResponseEntity<Furniture> registerNewFurniture(@RequestBody Furniture furniture) {
        try {
            Furniture addedFurniture = furnitureService.addNewFurniture(furniture);
            return new ResponseEntity<>(addedFurniture, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteFurniture(@RequestParam Integer id) {
        Optional<Furniture> furniture = furnitureService.getFurnitureById(id);

        if (furniture.isPresent()) {
            furnitureService.deleteFurniture(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{furnitureID}")
    public ResponseEntity<String> updateFurniture(
            @PathVariable("furnitureID") Integer furnitureID,
            @RequestParam(required = false) Integer roomID,
            @RequestParam(required = false) String furnitureType,
            @RequestParam(required = false) String furnitureCondition) {

        // Ensure at least one field is provided
        if (furnitureID == null && roomID == null && furnitureType == null && furnitureCondition == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        furnitureService.updateFurniture(furnitureID, roomID, furnitureType, furnitureCondition);
        return new ResponseEntity<>("Furniture updated successfully", HttpStatus.OK);
    }
}
