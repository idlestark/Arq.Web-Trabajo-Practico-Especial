package content.service.dto.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull( message = "User is a required field." )
    @NotEmpty( message = "User is a required field." )
    private String username;

    @NotNull( message = "Password is a required field." )
    @NotEmpty( message = "Password is a required field." )
    private String password;

    public String toString(){
        return "Username: " + username + ", Password: [FORBIDDEN] ";
    }
}
