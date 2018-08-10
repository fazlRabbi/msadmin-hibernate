package no.hib.models;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class MultipleAppointments {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private DateTime startDateTime;
    private String patientSsn;
    private String doctorSsn;
    private int numberOfConsecutiveAppointments;

    public MultipleAppointments(DateTime startDateTime, String patientSsn, String doctorSsn, int numberOfConsecutiveAppointments) {
        this.startDateTime = startDateTime;
        this.patientSsn = patientSsn;
        this.doctorSsn = doctorSsn;
        this.numberOfConsecutiveAppointments = numberOfConsecutiveAppointments;
    }

    public MultipleAppointments() {

    }

    public int getNumberOfConsecutiveAppointments() {
        return numberOfConsecutiveAppointments;
    }

    public void setNumberOfConsecutiveAppointments(int numberOfConsecutiveAppointments) {
        this.numberOfConsecutiveAppointments = numberOfConsecutiveAppointments;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
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
