package com.roominventory.roominventorysys.service;

import com.roominventory.roominventorysys.dto.OccupantDeleteMessage;
import com.roominventory.roominventorysys.dto.OccupantUpdateMessage;
import com.roominventory.roominventorysys.model.Occupant;
import com.roominventory.roominventorysys.repository.OccupantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OccupantService {
    private final OccupantRepository occupantRepository;
    private final WebSocketService webSocketService;

    @Autowired
    public OccupantService(OccupantRepository occupantRepository, WebSocketService webSocketService) {
        this.occupantRepository = occupantRepository;
        this.webSocketService = webSocketService;
    }

    public Occupant addNewOccupant(Occupant occupant) {
        Optional<Occupant> occupantByIdNumber = occupantRepository.findByIdNumber(occupant.getIdNumber());
        Optional<Occupant> occupantByEmail = occupantRepository.findByEmail(occupant.getEmail());
        Optional<Occupant> occupantByPhoneNumber = occupantRepository.findByPhoneNumber(occupant.getPhoneNumber());

        if (occupantByPhoneNumber.isPresent() || occupantByEmail.isPresent() || occupantByIdNumber.isPresent()) {
            throw new IllegalArgumentException("Occupant already exists");
        }

        System.out.println(occupant);
        Occupant savedOccupant = occupantRepository.save(occupant);
        Optional<Occupant> finalOccupant = getOccupantById(savedOccupant.getId());
        webSocketService.sendOccupantUpdate(new OccupantUpdateMessage(finalOccupant.get(), "ADD"));
        return finalOccupant.get();
    }

    // Get all occupants
    public List<Occupant> getOccupants() {
        return (List<Occupant>) occupantRepository.findAll();
    }

    // Get occupant by ID
    public Optional<Occupant> getOccupantById(Integer id) {
        return occupantRepository.findById(id);
    }

    public Occupant getOccupantByIdNumber(String idNumber) {
        Optional<Occupant> occupantByIdNumber = occupantRepository.findByIdNumber(idNumber);

        if(occupantByIdNumber.isEmpty()) {
            throw new IllegalArgumentException("Occupant with email " + idNumber + " does not exist");
        }

        return occupantByIdNumber.get();
    }

    // Get occupant by email
    public Occupant getOccupantByEmail(String email) {
        Optional<Occupant> occupantByEmail = occupantRepository.findByEmail(email);

        if(occupantByEmail.isEmpty()) {
            throw new IllegalArgumentException("Occupant with email " + email + " does not exist");
        }

        return occupantByEmail.get();
    }

    // Get occupant by phoneNumber
    public Occupant getOccupantByPhoneNumber(String phoneNumber) {
        Optional<Occupant> occupantByPhoneNumber = occupantRepository.findByPhoneNumber(phoneNumber);

        if(occupantByPhoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Occupant with email " + phoneNumber + " does not exist");
        }

        return occupantByPhoneNumber.get();
    }

    @Transactional
    public void updateOccupant(Integer occupantID, String firstName, String lastName, String idNumber, String email, String phoneNumber, Integer roomID) {
        Occupant occupant = occupantRepository.findById(occupantID)
                .orElseThrow(() -> new IllegalArgumentException("Occupant with id " + occupantID + " does not exist"));

        // Check if the id_number exists
        if (idNumber != null && !idNumber.equals(occupant.getIdNumber())) {
            Optional<Occupant> occupantByIdNumber = occupantRepository.findByIdNumber(idNumber);
            if (occupantByIdNumber.isPresent()) {
                throw new IllegalArgumentException("Id Number already in use by another occupant");
            }
        }

        // Check if the email is changing
        if (email != null && !email.equals(occupant.getEmail())) {
            Optional<Occupant> occupantByEmail = occupantRepository.findByEmail(email);
            if (occupantByEmail.isPresent()) {
                throw new IllegalArgumentException("Email already in use by another customer");
            }
        }

        // Check if the phone number is changing
        if (phoneNumber != null && !phoneNumber.equals(occupant.getPhoneNumber())) {
            Optional<Occupant> occupantByPhoneNumber = occupantRepository.findByPhoneNumber(phoneNumber);
            if (occupantByPhoneNumber.isPresent()) {
                throw new IllegalArgumentException("Phone number already in use by another occupant");
            }
        }

        if(firstName != null && !firstName.equals(occupant.getFirstName())) {
            occupant.setFirstName(firstName);
        }

        if(lastName != null && !lastName.equals(occupant.getLastName())) {
            occupant.setLastName(lastName);
        }

        if(idNumber != null && !idNumber.equals(occupant.getIdNumber())) {
            occupant.setIdNumber(firstName);
        }

        if(email != null && !email.equals(occupant.getEmail())) {
            occupant.setEmail(email);
        }

        if(phoneNumber != null && !phoneNumber.equals(occupant.getPhoneNumber())) {
            occupant.setPhoneNumber(phoneNumber);
        }

        if(roomID != null && !roomID.equals(occupant.getRoomID())) {
            occupant.setRoomID(roomID);
        }

        occupantRepository.save(occupant);
        webSocketService.sendOccupantUpdate(new OccupantUpdateMessage(occupant, "UPDATE"));
    }

    // Delete occupant by ID
    public void deleteOccupant(Integer id) {
        occupantRepository.deleteById(id);
        webSocketService.sendOccupantDelete(new OccupantDeleteMessage(id, "DELETED OCCUPANT." + " id= " + id));
    }
}
