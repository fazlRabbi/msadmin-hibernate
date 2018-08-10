package no.hib.controllers;

import no.hib.logic.ConsultationAPI;
import no.hib.models.OtherSubject;
import no.hib.models.Settings;
import no.hib.models.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/settings")
public class SettingsController {

    @Autowired
    ConsultationAPI consultationAPI;

    @RequestMapping(method = RequestMethod.GET, value = "/editSymptom/{uuid}")
    public String editSymptom(@PathVariable(value = "uuid") String uuid, ModelMap modelMap) {
        Settings settings = consultationAPI.getSettings();
        Symptom symptom = null;
        for (Symptom symp : settings.getDefaultSymptoms()) {
            if (symp.getId().equals(uuid)) {
                symptom = symp;
                break;
            }
        }

        modelMap.addAttribute("symptom", symptom);
        return "editSymptom";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteSymptom/{uuid}")
    public String deleteSymptom(@PathVariable(value = "uuid") String uuid) {
        consultationAPI.deleteSymptom(uuid);
        return "redirect:/settings/symptoms";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateSymptom")
    public String updateSymptom(@ModelAttribute Symptom newSymptom) {
        consultationAPI.updateSymptom(newSymptom);
        return "redirect:/settings/symptoms";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createSymptom")
    public ModelAndView createSymptom() {
        return new ModelAndView("createSymptom", "symptom", new Symptom());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSymptom")
    public String addSymptom(@ModelAttribute Symptom symptom) {
        symptom.setChange("");
        symptom.setSeverity("");
        consultationAPI.addNewSymptom(symptom);
        return "redirect:/settings/symptoms";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editSubject/{uuid}")
    public String editSubject(@PathVariable(value = "uuid") String uuid, ModelMap modelMap) {
        Settings settings = consultationAPI.getSettings();
        OtherSubject subject = null;
        for (OtherSubject sub : settings.getDefaultOtherSubjects()) {
            if (sub.getId().equals(uuid)) {
                subject = sub;
                break;
            }
        }

        modelMap.addAttribute("subject", subject);
        return "editSubject";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteSubject/{uuid}")
    public String deleteSubject(@PathVariable(value = "uuid") String uuid) {
        consultationAPI.deleteSubject(uuid);
        return "redirect:/settings/subjects";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateSubject")
    public String updateSubject(@ModelAttribute OtherSubject newSubject) {
        consultationAPI.updateSubject(newSubject);
        return "redirect:/settings/subjects";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createSubject")
    public ModelAndView createSubject() {
        return new ModelAndView("createSubject", "subject", new OtherSubject());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSubject")
    public String addSubject(@ModelAttribute OtherSubject subject) {
        consultationAPI.addNewSubject(subject);
        return "redirect:/settings/subjects";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updatePreperationStart")
    public String updatePreperationStart(@ModelAttribute Settings settings) {
        Settings oldSettings = consultationAPI.getSettings();
        oldSettings.setAppointmentPreperationMinStart(settings.getAppointmentPreperationMinStart());
        consultationAPI.updateSettings(oldSettings);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView settings() {
        Settings settings = consultationAPI.getSettings();
        ModelAndView model = new ModelAndView("settings", "settings", settings);
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/symptoms")
    public ModelAndView symptoms() {
        Settings settings = consultationAPI.getSettings();
        return new ModelAndView("symptoms", "settings", settings);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subjects")
    public ModelAndView subjects() {
        Settings settings = consultationAPI.getSettings();
        return new ModelAndView("subjects", "settings", settings);
    }

}
