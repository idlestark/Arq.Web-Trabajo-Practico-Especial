package content.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import content.service.dto.UserService;
import content.service.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser( @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }
}
