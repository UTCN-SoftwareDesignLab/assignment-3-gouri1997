package hospital.service;

import hospital.dto.ConsultationDto;
import hospital.model.Consultation;

import java.util.List;

public interface ConsultationDoctorService {
    List<Consultation> getAll();
    Consultation findById(Integer id);
    Consultation update(ConsultationDto consultationDto);
}
