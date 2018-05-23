package hospital;

import ch.qos.logback.core.net.SyslogOutputStream;
import hospital.dto.ConsultationDto;
import hospital.model.Consultation;
import hospital.service.ConsultationDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    ConsultationDoctorService consultationDoctorService;

    @RequestMapping(value = "/getConsultations", method = RequestMethod.GET)
    public List<Consultation> getConsultations(){
        return consultationDoctorService.getAll();
    }

    @RequestMapping(value = "/updateConsultation",method = RequestMethod.POST)
    public Consultation updateConsultation(@RequestBody ConsultationDto consultationDto){
        System.out.println(consultationDto.patientId);
        return consultationDoctorService.update(consultationDto);
    }
}
