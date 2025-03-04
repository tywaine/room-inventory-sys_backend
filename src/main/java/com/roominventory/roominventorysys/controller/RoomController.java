package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.model.Room;
import com.roominventory.roominventorysys.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Optional<Room> room = roomService.getRoomById(id);

            if (room.isPresent()) {
                return List.of(room.get());
            } else {
                return new ArrayList<>();
            }
        }

        return roomService.getRooms();
    }

    @PostMapping
    public ResponseEntity<Room> registerNewOccupant(@RequestBody Room room) {
        try {
            Room addedRoom = roomService.addNewRoom(room);
            Optional<Room> finalRoom = roomService.getRoomById(addedRoom.getId());
            return new ResponseEntity<>(finalRoom.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteRoom(@RequestParam Integer id) {
        Optional<Room> room = roomService.getRoomById(id);

        if (room.isPresent()) {
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{roomID}")
    public ResponseEntity<String> updateRoom(
            @PathVariable("roomID") Integer roomID,
            @RequestParam(required = false) Integer blockID,
            @RequestParam(required = false) String roomNumber,
            @RequestParam(required = false) Integer floor,
            @RequestParam(required = false) Integer maxOccupants) {

        // Ensure at least one field is provided
        if (roomID == null && blockID == null && roomNumber == null && floor == null && maxOccupants == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        roomService.updateRoom(roomID, blockID, roomNumber, floor, maxOccupants);
        return new ResponseEntity<>("Room updated successfully", HttpStatus.OK);
    }
}
