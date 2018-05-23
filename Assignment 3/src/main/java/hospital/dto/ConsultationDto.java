package hospital.dto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ConsultationDto {
    public Integer id;

    @Min(value = 0, message = "Enter valid patient ID.")
    public Integer patientId;

    @NotNull(message = "Enter the doctor's name.")
    public String doctor;

    @Future(message = "Enter valid date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;

    @NotNull(message = "Diagnosis cannot be null.")
    public String diagnosis;
}
