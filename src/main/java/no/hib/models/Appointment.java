package no.hib.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements Comparable<Appointment>{

    private String uuid;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Patient patient;
    private Doctor doctor;
    private String patientSsn;
    private String doctorSsn;

    public Appointment(String uuid, LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Doctor doctor, String patientSsn, String doctorSsn) {
        this.uuid = uuid;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.doctor = doctor;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public Appointment(String uuid, LocalDate appointmentDate, LocalTime appointmentTime, String patientSsn, String doctorSsn) {
        this.uuid = uuid;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Doctor doctor, String patientSsn, String doctorSsn) {
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, String patientSsn, String doctorSsn) {
        this.appointmentTime = appointmentTime;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
        this.appointmentDate = appointmentDate;
    }

    public Appointment() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPatientSsn() {
        return patientSsn;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    public String getDoctorSsn() {
        return doctorSsn;
    }

    public void setDoctorSsn(String doctorSsn) {
        this.doctorSsn = doctorSsn;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public int compareTo(Appointment o) {
        if(this.getAppointmentDate().isBefore(o.getAppointmentDate())){
            return -1;
        }
        if(this.getAppointmentDate().isAfter(o.getAppointmentDate())){
            return 1;
        }
        if(this.getAppointmentTime().isBefore(o.getAppointmentTime())){
            return -1;
        }
        if(this.getAppointmentTime().isAfter(o.getAppointmentTime())){
            return 1;
        }
        return 0;
    }
}
