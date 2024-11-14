package content.controller;
import content.DTO.ScooterDTO;
import content.entities.User;
import content.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.notFound().build();
    }

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

    @GetMapping("/scooter/nearby")
    public ResponseEntity<List<ScooterDTO>> getNearbyScooters(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius){
        List<ScooterDTO> scooter = userService.getNearbyScooters(latitude, longitude, radius);
        if (scooter.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooter);
    }

}