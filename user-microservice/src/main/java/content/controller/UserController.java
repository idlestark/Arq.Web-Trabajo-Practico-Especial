package content.controller;
import content.DTO.ScooterDTO;
import content.entities.User;
import content.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users", description = "Gets a list of all users")
    @ApiResponse(responseCode = "200", description = "Users list obtained successfully")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Get user by ID", description = "Gets a single user specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Create user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @Operation(summary = "Delete user", description = "Delete an existent user specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Update user", description = "Updates an existent user with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUsers = userService.findUserById(id);

        if (existingUsers == null) {
            return ResponseEntity.notFound().build();
        }

        existingUsers.setName(user.getName());
        existingUsers.setLastName(user.getLastName());
        existingUsers.setPhoneNumber(user.getPhoneNumber());
        existingUsers.setEmail(user.getEmail());

        User updatedUser = userService.updateUser(existingUsers);

        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary = "Get nearby scooters", description = "Gets a list of all nearby scooters")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping("/scooter/nearby")
    public ResponseEntity<List<ScooterDTO>> getNearbyScooters(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius){
        List<ScooterDTO> scooter = userService.getNearbyScooters(latitude, longitude, radius);
        if (scooter.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooter);
    }

}