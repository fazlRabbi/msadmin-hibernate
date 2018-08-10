package no.hib.controllers;

import no.hib.logic.ConsultationAPI;
import no.hib.models.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
public class AppointmentController {

    @Autowired
    ConsultationAPI consultationAPI;

    @RequestMapping(method = RequestMethod.GET, value = {"/appointments","/"})
    public ModelAndView index(){
        List<Appointment> tmp = consultationAPI.getAllAppointments();

        List<Appointment> appointments = new ArrayList<>();

        for(Appointment appointment : tmp){
            Patient patient = consultationAPI.getPatient(appointment.getPatientSsn());
            Doctor doctor = consultationAPI.getDoctor(appointment.getDoctorSsn());
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointments.add(appointment);
        }
        Collections.sort(appointments);
        return new ModelAndView("appointments","appointments",appointments);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/appointments/createAppointment")
    public ModelAndView createAppointment() {
        ModelAndView mav = new ModelAndView("createAppointment");
        List<Patient> patients = consultationAPI.getAllPatients();
        List<Doctor> doctors = consultationAPI.getAllDoctors();
        Map<String,String> patientsMap = new LinkedHashMap<String,String>();
        for(Patient patient : patients){
            patientsMap.put(patient.getSsn(), patient.getFullName() + " - " + patient.getSsn());
        }

        Map<String,String> doctorsMap = new LinkedHashMap<String,String>();
        for(Doctor doctor : doctors){
            doctorsMap.put(doctor.getSsn(), doctor.getFullName() + " - " + doctor.getSsn());
        }
        mav.addObject("patients",patientsMap);
        mav.addObject("doctors",doctorsMap);
        mav.addObject("appointment",new ClientAppointment());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/appointments/createMultipleAppointments")
    public ModelAndView createMultipleAppointmenst() {
        ModelAndView mav = new ModelAndView("createMultipleAppointments");
        List<Patient> patients = consultationAPI.getAllPatients();
        List<Doctor> doctors = consultationAPI.getAllDoctors();
        Map<String,String> patientsMap = new LinkedHashMap<String,String>();
        for(Patient patient : patients){
            patientsMap.put(patient.getSsn(), patient.getFullName() + " - " + patient.getSsn());
        }

        Map<String,String> doctorsMap = new LinkedHashMap<String,String>();
        for(Doctor doctor : doctors){
            doctorsMap.put(doctor.getSsn(), doctor.getFullName() + " - " + doctor.getSsn());
        }
        mav.addObject("patients",patientsMap);
        mav.addObject("doctors",doctorsMap);
        mav.addObject("multipleAppointments",new MultipleAppointments());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/appointments/editAppointment/{uuid}")
    public ModelAndView editAppointment(@PathVariable(value = "uuid") String uuid) {
        Appointment appointment = consultationAPI.getAppointment(uuid);
        Patient patient = consultationAPI.getPatient(appointment.getPatientSsn());
        Doctor doctor = consultationAPI.getDoctor(appointment.getDoctorSsn());

        int year = appointment.getAppointmentDate().getYear();
        int month = appointment.getAppointmentDate().getMonthValue();
        int day = appointment.getAppointmentDate().getDayOfMonth();
        int hour = appointment.getAppointmentTime().getHour();
        int minute = appointment.getAppointmentTime().getMinute();

        ClientAppointment clientAppointment = new ClientAppointment(appointment.getUuid(),new DateTime(year,month,day,hour,minute), patient, doctor, appointment.getPatientSsn(), appointment.getDoctorSsn());

        List<Doctor> doctors = consultationAPI.getAllDoctors();

        Map<String,String> doctorsMap = new LinkedHashMap<String,String>();
        for(Doctor doctor1 : doctors){
            doctorsMap.put(doctor1.getSsn(), doctor1.getFullName() + " - " + doctor1.getSsn());
        }
        ModelAndView mav = new ModelAndView("editAppointment");
        mav.addObject("appointment", clientAppointment);
        mav.addObject("patientInfo",patient);
        mav.addObject("doctors",doctorsMap);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/appointments/deleteAppointment/{uuid}")
    public String deleteAppointment(@PathVariable(value = "uuid") String uuid) {
        consultationAPI.deleteAppointment(uuid);
        return "redirect:/appointments";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/appointments/updateAppointment")
    public String updateAppointment(@ModelAttribute ClientAppointment appointment) {
        Appointment updated = getAppointment(appointment);
        consultationAPI.createOrUpdateAppointment(updated);
        return "redirect:/appointments";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/appointments/addAppointment")
    public String addAppointment(@ModelAttribute ClientAppointment appointment) {
        Appointment created = getAppointment(appointment);
        consultationAPI.createOrUpdateAppointment(created);
        return "redirect:/appointments";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/appointments/addMultipleAppointments")
    public String addMultipleAppointments(@ModelAttribute MultipleAppointments appointments) {
        for(int i = 0; i < appointments.getNumberOfConsecutiveAppointments(); i++){
            String patientSsn = appointments.getPatientSsn();
            String doctorSsn = appointments.getDoctorSsn();
            DateTime dateTime = appointments.getStartDateTime();
            DateTime newDateTime = dateTime.plusMonths(i);
            Appointment appointment = getAppointment(new ClientAppointment(newDateTime,patientSsn,doctorSsn));
            consultationAPI.createOrUpdateAppointment(appointment);
        }
        return "redirect:/appointments";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(DateTime.class, new CustomDateEditor(sdf, true));
    }

    private Appointment getAppointment(ClientAppointment appointment){
        int year = appointment.getAppointmentTime().getYear();
        int month = appointment.getAppointmentTime().getMonthOfYear();
        int day = appointment.getAppointmentTime().getDayOfMonth();
        int hour = appointment.getAppointmentTime().getHourOfDay();
        int minute = appointment.getAppointmentTime().getMinuteOfHour();

        Appointment created = new Appointment(appointment.getUuid(), LocalDate.of(year,month,day), LocalTime.of(hour,minute),
                appointment.getPatientSsn(), appointment.getDoctorSsn());
        return created;
    }
}
