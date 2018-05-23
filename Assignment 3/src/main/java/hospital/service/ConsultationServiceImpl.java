package hospital.service;

import hospital.dto.ConsultationDto;
import hospital.model.Consultation;
import hospital.model.Patient;
import hospital.model.User;
import hospital.repository.ConsultationRepository;
import hospital.repository.PatientRepository;
import hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Consultation create(ConsultationDto consultationDto) {
        User doctor = userRepository.getOne(consultationDto.doctor);
        Patient patientId = patientRepository.getOne(consultationDto.patientId);
        Consultation consultation = new Consultation(patientId, consultationDto.date, doctor);
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(ConsultationDto consultationDto) {
        User doctor = userRepository.getOne(consultationDto.doctor);
        Patient patientId = patientRepository.getOne(consultationDto.patientId);
        Consultation consultation = consultationRepository.getOne(consultationDto.id);
        consultation.setPatientId(patientId);
        consultation.setDate(consultationDto.date);
        consultation.setDoctor(doctor);
        return consultationRepository.save(consultation);
    }

    @Override
    public void delete(Integer id) {
        consultationRepository.delete(id);
    }
}
