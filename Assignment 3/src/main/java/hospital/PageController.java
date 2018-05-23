package hospital;

import hospital.service.ConsultationService;
import hospital.service.PatientService;
import hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    PatientService patientService;

    @Autowired
    UserService userService;

    @Autowired
    ConsultationService consultationService;

    @RequestMapping(value = "/secretary")
    public String secretary(Model model){
        model.addAttribute("Patients",patientService.getAll());
        model.addAttribute("Consultations",consultationService.getAll());
        model.addAttribute("DoctorSec", userService.getDoctors());
        return "secretary";
    }

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("Users", userService.getAll());
        //create list of roles("ADMIN", "SECRETARY", "DOCTOR")
        return "admin";
    }

    @RequestMapping(value = "/doctor")
    public String doctor(Model model){
        model.addAttribute("PatientsDoc",patientService.getAll());
        model.addAttribute("Doctor", userService.getAll());
        return "doctor";
    }
}
