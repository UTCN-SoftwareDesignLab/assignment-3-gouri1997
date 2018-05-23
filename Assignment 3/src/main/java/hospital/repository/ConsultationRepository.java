package hospital.repository;

import hospital.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository <Consultation, Integer>{
    Consultation findById(Integer id);
}
