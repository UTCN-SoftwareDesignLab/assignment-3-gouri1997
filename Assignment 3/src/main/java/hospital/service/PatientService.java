package hospital.service;

import hospital.dto.PatientDto;
import hospital.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Patient create(PatientDto patientDto);
    Patient update(PatientDto patientDto);
    Patient getById(Integer id);
}
