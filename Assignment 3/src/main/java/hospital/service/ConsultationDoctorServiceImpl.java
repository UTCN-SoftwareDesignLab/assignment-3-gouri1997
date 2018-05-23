package hospital.service;

import hospital.dto.ConsultationDto;
import hospital.model.Consultation;
import hospital.model.Patient;
import hospital.model.User;
import hospital.repository.ConsultationRepository;
import hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationDoctorServiceImpl implements ConsultationDoctorService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation findById(Integer id) {
        return consultationRepository.findById(id);
    }

    @Override
    public Consultation update(ConsultationDto consultationDto) {
        Patient patientId = patientRepository.getOne(consultationDto.patientId);
        Consultation consultation = consultationRepository.findById(consultationDto.id);
        consultation.setDiagnosis(consultationDto.diagnosis);
        consultation.setPatientId(patientId);
        return consultationRepository.save(consultation);
    }
}
