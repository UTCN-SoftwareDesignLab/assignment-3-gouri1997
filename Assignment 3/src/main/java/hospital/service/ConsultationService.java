package hospital.service;

import hospital.dto.ConsultationDto;
import hospital.model.Consultation;

import java.util.List;

public interface ConsultationService {
    List<Consultation> getAll();
    Consultation findById(Integer id);
    Consultation create (ConsultationDto consultationDto);
    Consultation update (ConsultationDto consultationDto);
    void delete(Integer id);
}