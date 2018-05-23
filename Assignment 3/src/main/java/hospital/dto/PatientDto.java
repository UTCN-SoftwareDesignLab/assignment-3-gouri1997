package hospital.dto;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class PatientDto {

    public Integer id;

    @Size(min = 1, max = 20, message = "Name cannot be null")
    public String name;

    @Pattern(regexp = "^[0-9]")
    @Size(min=1, max=20, message = "The cnp should be between 1 and 20")
    public String cnp;

    @Pattern(regexp = "[a-zA-Z0-9., ]+", message ="Address can contain only letters, digits, . and ,")
    public String address;

    @Past(message = "Date must be in the past")
    public Date dob;

}
