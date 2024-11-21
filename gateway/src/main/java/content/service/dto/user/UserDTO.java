package content.service.dto.user;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    @NotNull( message = "User is a required field." )
    @NotEmpty( message = "User is a required field." )
    private String username;

    @NotNull( message = "Password is a required field." )
    @NotEmpty( message = "Password is a required field." )
    private String password;

    @NotNull( message = "Roles is a required field.." )
    @NotEmpty( message = "Roles is a required field.." )
    private Set<String> authorities;
}
