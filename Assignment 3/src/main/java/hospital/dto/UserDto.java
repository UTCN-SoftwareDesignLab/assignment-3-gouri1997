package hospital.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    @Size(min = 4, message = "Username is too short.\n")
    public String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least 8 characters(at least 1 uppercase and 1 lowercase), a digit and a special character.\n")
    public String password;

    @NotNull
    public String role;
}
