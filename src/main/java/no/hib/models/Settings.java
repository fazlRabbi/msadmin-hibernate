package no.hib.models;

import java.util.List;

public class Settings {

    private String uuid;
    private int appointmentPreperationMinStart;
    private List<Symptom> defaultSymptoms;
    private List<OtherSubject> defaultOtherSubjects;

    public Settings(String uuid, int appointmentPreperationMinStart, List<Symptom> defaultSymptoms, List<OtherSubject> defaultOtherSubjects) {
        this.uuid = uuid;
        this.appointmentPreperationMinStart = appointmentPreperationMinStart;
        this.defaultSymptoms = defaultSymptoms;
        this.defaultOtherSubjects = defaultOtherSubjects;
    }

    public Settings(){

    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getAppointmentPreperationMinStart() {
        return appointmentPreperationMinStart;
    }

    public void setAppointmentPreperationMinStart(int appointmentPreperationMinStart) {
        this.appointmentPreperationMinStart = appointmentPreperationMinStart;
    }

    public List<Symptom> getDefaultSymptoms() {
        return defaultSymptoms;
    }

    public void setDefaultSymptoms(List<Symptom> defaultSymptoms) {
        this.defaultSymptoms = defaultSymptoms;
    }

    public List<OtherSubject> getDefaultOtherSubjects() {
        return defaultOtherSubjects;
    }

    public void setDefaultOtherSubjects(List<OtherSubject> defaultOtherSubjects) {
        this.defaultOtherSubjects = defaultOtherSubjects;
    }
}
