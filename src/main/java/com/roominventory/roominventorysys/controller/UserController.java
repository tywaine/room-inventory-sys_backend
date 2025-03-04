package com.roominventory.roominventorysys.controller;

import com.roominventory.roominventorysys.model.User;
import com.roominventory.roominventorysys.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Optional<User> user = userService.getUserById(id);

            if (user.isPresent()) {
                return List.of(user.get());
            } else {
                return new ArrayList<>();
            }
        }

        return userService.getUsers();
    }

    @GetMapping("/admin/exists")
    public ResponseEntity<Map<String, Boolean>> doesAdminExist() {
        boolean exists = userService.doesAdminExist();
        Map<String, Boolean> response = Map.of("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<User> registerNewOccupant(@RequestBody User user) {
        try {
            User addedUser = userService.addNewUser(user);
            Optional<User> finalUser = userService.getUserById(addedUser.getId());
            return new ResponseEntity<>(finalUser.get(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.authenticate(username, password);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{userID}")
    public ResponseEntity<String> updateUser(
            @PathVariable("userID") Integer userID,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String passwordHash,
            @RequestParam(required = false) String role) {

        // Ensure at least one field is provided
        if (username == null && passwordHash == null && role == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        try{
            userService.updateUser(userID, username, passwordHash, role);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
