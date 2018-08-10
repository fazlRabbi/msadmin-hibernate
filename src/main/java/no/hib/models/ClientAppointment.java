package no.hib.models;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ClientAppointment {

    private String uuid;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private DateTime appointmentTime;
    private Patient patient;
    private Doctor doctor;
    private String patientSsn;
    private String doctorSsn;

    public ClientAppointment(String uuid, DateTime appointmentTime, Patient patient, Doctor doctor, String patientSsn, String doctorSsn) {
        this.uuid = uuid;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.doctor = doctor;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public ClientAppointment( DateTime appointmentTime, Patient patient, Doctor doctor, String patientSsn, String doctorSsn) {
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.doctor = doctor;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public ClientAppointment( DateTime appointmentTime, String patientSsn, String doctorSsn) {
        this.appointmentTime = appointmentTime;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
    }

    public ClientAppointment() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(DateTime appointmentTime) {
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
}
