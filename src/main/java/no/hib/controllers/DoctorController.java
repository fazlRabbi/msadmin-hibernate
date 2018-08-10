package no.hib.controllers;

import no.hib.logic.ConsultationAPI;
import no.hib.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    ConsultationAPI consultationAPI;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        List<Doctor> doctors = consultationAPI.getAllDoctors();
        return new ModelAndView("doctors","doctors",doctors);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createDoctor")
    public ModelAndView createDoctor() {
        return new ModelAndView("createDoctor", "doctor",new Doctor());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editDoctor/{ssn}")
    public ModelAndView editDoctor(@PathVariable(value = "ssn") String ssn) {
        Doctor doctor = consultationAPI.getDoctor(ssn);
        return new ModelAndView("editDoctor","doctor",doctor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteDoctor/{ssn}")
    public String deleteDoctor(@PathVariable(value = "ssn") String ssn) {
        consultationAPI.deleteDoctor(ssn);
        return "redirect:/doctors";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateDoctor")
    public String updateDoctor(@ModelAttribute Doctor newDoctor) {
        consultationAPI.createOrUpdateDoctor(newDoctor);
        return "redirect:/doctors";
    }

        @RequestMapping(method = RequestMethod.POST, value = "/addDoctor")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        consultationAPI.createOrUpdateDoctor(doctor);
        return "redirect:/doctors";
    }
}
