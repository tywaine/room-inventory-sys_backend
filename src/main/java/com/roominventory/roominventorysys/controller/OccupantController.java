package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.model.Occupant;
import com.roominventory.roominventorysys.service.OccupantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/occupants")
public class OccupantController {
    private final OccupantService occupantService;

    public OccupantController(OccupantService occupantService) {
        this.occupantService = occupantService;
    }

    @GetMapping
    public List<Occupant> getOccupants(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Optional<Occupant> occupant = occupantService.getOccupantById(id);

            if (occupant.isPresent()) {
                return List.of(occupant.get());
            }
            else {
                return new ArrayList<>();
            }
        }

        return occupantService.getOccupants();
    }

    @PostMapping
    public ResponseEntity<Occupant> registerNewOccupant(@RequestBody Occupant occupant) {
        try {
            Occupant addedOccupant = occupantService.addNewOccupant(occupant);
            return new ResponseEntity<>(addedOccupant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteOccupant(@RequestParam Integer id) {
        Optional<Occupant> occupant = occupantService.getOccupantById(id);

        if (occupant.isPresent()) {
            occupantService.deleteOccupant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{occupantID}")
    public ResponseEntity<String> updateOccupant(
            @PathVariable("occupantID") Integer occupantID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String idNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Integer roomID) {

        // Ensure at least one field is provided
        if (occupantID == null && firstName == null && lastName == null && idNumber == null && phoneNumber == null && email == null && roomID == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        try{
            occupantService.updateOccupant(occupantID, firstName, lastName, idNumber, email, phoneNumber, roomID);
            return new ResponseEntity<>("Occupant updated successfully", HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
