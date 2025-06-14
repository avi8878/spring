package com.avi.batch.controller;



import com.avi.batch.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * REST controller for User resource.
 * Provides CRUD operations for User entity.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {


    /**
     * Get a list of all users.
     * @return List of User objects.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return Arrays.asList(new User(1,"avi"));
    }

    /**
     * Get a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity with User if found, 404 otherwise.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = new User(1,"avi");
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create a new user.
     * Validates the User object using javax.validation.
     * @param user The User object to create.
     * @return The created User.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = new User(1,"avi");
        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    /**
     * Update an existing user.
     * Validates the User object using javax.validation.
     * @param id The ID of the user to update.
     * @param userDetails The updated User details.
     * @return ResponseEntity with updated User if found, 404 otherwise.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        User updatedUser = new User(1,"avi");
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity(updatedUser,HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a user by their ID.
     * @param id The ID of the user to delete.
     * @return ResponseEntity with 204 if deleted, 404 otherwise.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = false;
        if (deleted) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }
}
