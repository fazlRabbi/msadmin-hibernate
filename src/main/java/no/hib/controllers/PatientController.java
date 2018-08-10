package no.hib.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import no.hib.logic.ConsultationAPI;
import no.hib.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    ConsultationAPI consultationAPI;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        List<Patient> patients = consultationAPI.getAllPatients();
        return new ModelAndView("patients","patients",patients);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createPatient")
    public ModelAndView createPatient() {
        ModelAndView mav = new ModelAndView("createPatient");
        Map<String,String> genders = new LinkedHashMap<String,String>();
        genders.put("male", "male");
        genders.put("female", "female");
        mav.addObject("patient",new Patient());
        mav.addObject("genders",genders);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editPatient/{ssn}")
    public ModelAndView editPatient(@PathVariable(value = "ssn") String ssn) {
        Patient patient = consultationAPI.getPatient(ssn);
        ModelAndView mav = new ModelAndView("editPatient");
        Map<String,String> genders = new LinkedHashMap<String,String>();
        genders.put("male", "male");
        genders.put("female", "female");
        mav.addObject("patient",patient);
        mav.addObject("genders",genders);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deletePatient/{ssn}")
    public String deletePatient(@PathVariable(value = "ssn") String ssn) {
        consultationAPI.deletePatient(ssn);
        return "redirect:/patients";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updatePatient")
    public String updatePatient(@ModelAttribute Patient newPatient) {
        consultationAPI.createOrUpdatePatient(newPatient);
        return "redirect:/patients";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addPatient")
    public String addPatient(@ModelAttribute Patient newPatient) {
    	
    	System.out.println(newPatient.getBirthday());
    	System.out.println("simple json:: ");
        System.out.println(new Gson().toJson(newPatient));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		System.out.println("jsonbuilder.format(yyyy-MM-dd) ::" + gson.toJson(newPatient));
		
        consultationAPI.createOrUpdatePatient(newPatient);
        return "redirect:/patients";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("##-----------------------------------------------------------INSIDE INITBINDER------------------------------------------------------------###############");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
