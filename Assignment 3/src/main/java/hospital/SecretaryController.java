package hospital;

import hospital.configuration.Message;
import hospital.dto.ConsultationDto;
import hospital.dto.PatientDto;
import hospital.model.Consultation;
import hospital.model.Patient;
import hospital.service.ConsultationService;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SecretaryController{

    @Autowired
    PatientService patientService;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/getPatients", method = RequestMethod.GET)
    public List<Patient> getPatients(){
        return patientService.getAll();
    }

    @RequestMapping(value = "/createPatient", method = RequestMethod.POST)
    public Patient createPatient(@RequestBody @Valid PatientDto patientDto){
        return patientService.create(patientDto);
    }

    @RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
    public Patient updatePatient(@RequestBody @Valid PatientDto patientDto){
        return patientService.update(patientDto);
    }

    @RequestMapping(value = "/getSecConsultations", method = RequestMethod.GET)
    public List<Consultation> getSecConsultation(){
        return consultationService.getAll();
    }

    @RequestMapping(value = "/createSecConsultation", method = RequestMethod.POST)
    public Consultation createSecConsultation(@RequestBody ConsultationDto consultationDto){
        Message message = new Message();
        message.setContent("You have an appointment with "+patientService.getById(consultationDto.patientId).getName()+"\n");
        messagingTemplate.convertAndSendToUser(consultationDto.doctor, "/queue/reply", message);
        return consultationService.create(consultationDto);
    }

    @RequestMapping(value = "/updateSecConsultation", method = RequestMethod.POST)
    public Consultation updateSecConsultation(@RequestBody ConsultationDto consultationDto){
        return consultationService.update(consultationDto);
    }

    @RequestMapping(value = "/deleteConsultation", method = RequestMethod.POST)
    public void deleteConsultation(@RequestBody ConsultationDto consultationDto){
        consultationService.delete(consultationDto.id);
    }

}
