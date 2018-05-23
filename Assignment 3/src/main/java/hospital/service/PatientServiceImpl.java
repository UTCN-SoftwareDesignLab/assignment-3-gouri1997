package hospital.service;

import hospital.dto.PatientDto;
import hospital.model.Patient;
import hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class PatientServiceImpl implements PatientService {
    PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient create(PatientDto patientDto) {
        Patient patient = new Patient(patientDto.name, patientDto.cnp, patientDto.dob, patientDto.address);
        System.out.println("patient service");
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(PatientDto patientDto) {

        Patient patient = patientRepository.getOne(patientDto.id);
        patient.setName(patientDto.name);
        patient.setCnp(patientDto.cnp);
        patient.setDob(patientDto.dob);
        patient.setAddress(patientDto.address);
        
        return patientRepository.save(patient);
    }

    @Override
    public Patient getById(Integer id) {
        return patientRepository.getOne(id);
    }


}
