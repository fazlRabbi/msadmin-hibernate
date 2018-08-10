package no.hib.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import no.hib.config.Config;
import no.hib.models.*;
import no.hib.utils.UtfConverter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("consultationAPI")
public class ConsultationAPI {

	static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	static Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
	//  static Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-ddHH:mm:ss").create();
	
	
	public static void main(String[] args) {
		System.out.println("Test...");
		
		Patient thePatient = new Patient();
		thePatient.setBirthday(Calendar.getInstance().getTime());
		
		System.out.println(gson2.toJson(thePatient));
		
		List<Appointment> a = new ArrayList<Appointment>();
		Appointment theApp = new Appointment();
		theApp.setAppointmentDate(LocalDate.of(1982,4,5));
		a.add(theApp); 
		System.out.println(gson2.toJson(a));
		
		String str = "[{\"_id\":4,\"_rev\":0,\"uuid\":\"519c3019-3fe2-4aab-9652-af797df5111d\",\"appointmentDate\":\"2018-04-04T02:00:00.000\",\"appointmentTime\":\"2018-04-04T12:30:00.000\",\"appointmentPreperation\":{\"_id\":5,\"_rev\":0,\"uuid\":\"3e505b00-e033-403b-80f1-0276fe4b79e3\",\"appointmentDate\":\"2018-04-04T02:00:00.000\",\"appointmentTime\":\"2018-04-04T12:30:00.000\",\"symptoms\":[],\"sideEffectsAreImportant\":false,\"otherSubjects\":[],\"appointmentUuid\":\"519c3019-3fe2-4aab-9652-af797df5111d\"},\"patientSsn\":\"03028221389\",\"doctorSsn\":\"12129112121\"}]";
		Type type = new TypeToken<List<Appointment>>() {
        }.getType();
		List<Appointment> apps = gson2.fromJson(str, type);
		
	}
	
	
    private Config config;

    public ConsultationAPI() {
        config = new Config();
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList();
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "appointments");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Type type = new TypeToken<List<Appointment>>() {
            }.getType();
            
            System.out.println(response.toString());
            appointments = gson2.fromJson(response.toString(), type);
        } catch (Exception ex) {
            System.err.println("Exception getting all appointments: " + ex);
        }
        return appointments;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "patients");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Type type = new TypeToken<List<Patient>>() {
            }.getType();
            
            
            System.out.println(response.toString());
            List<Patient> tmp = gson.fromJson(response.toString(), type);
            for(Patient patient : tmp){
                patients.add(convertToUtf(patient));
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting all patients: ", ex);
        }
        return patients;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "doctors");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Type type = new TypeToken<List<Doctor>>() {
            }.getType();
            List<Doctor> tmp = gson.fromJson(response.toString(), type);
            for(Doctor doctor : tmp){
                doctors.add(convertToUtf(doctor));
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting all doctors: ", ex);
        }
        return doctors;
    }

    public Settings getSettings() {
        Settings settings;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            settings = gson.fromJson(response.toString(), Settings.class);
            settings = convertToUtfSettings(settings);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting settings: ", ex);
        }
        return settings;
    }

    public Patient getPatient(String patientSsn) {
        Patient patient;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "patients/" + patientSsn);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            patient = gson.fromJson(response.toString(), Patient.class);
            patient = convertToUtf(patient);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting patient with ssn: " + patientSsn, ex);
        }
        return patient;
    }

    public Doctor getDoctor(String doctorSsn) {
        Doctor doctor;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "doctors/" + doctorSsn);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            doctor = gson.fromJson(response.toString(), Doctor.class);
            doctor = convertToUtf(doctor);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting doctor with ssn: " + doctorSsn, ex);
        }
        return doctor;
    }

    public Appointment getAppointment(String uuid) {
        Appointment appointment;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "appointments/" + uuid);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            appointment = gson2.fromJson(response.toString(), Appointment.class);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception getting appointment with uuid: " + uuid, ex);
        }
        return appointment;
    }

    public void createOrUpdateAppointment(Appointment appointment) {
        try {
            HttpURLConnection con;
            if (appointment.getUuid() == null || appointment.getUuid().isEmpty()) { //CREATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "appointments");
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");

            } else { //UPDATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "appointments/" + appointment.getUuid());
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");
            }

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            
            System.out.println("appointmentDate:: " + appointment.getAppointmentDate());
            System.out.println("appointmentTime:: " + appointment.getAppointmentTime());
            
            System.out.println(gson.toJson(appointment));
            wr.write(gson.toJson(appointment));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Saving or updating appointment: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception creating or upodating appointment", ex);
        }
    }

    public void createOrUpdateDoctor(Doctor doctor) {
        try {
            HttpURLConnection con;
            if (doctor.getUuid() == null || doctor.getUuid().isEmpty()) { //CREATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "doctors");
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");

            } else { //UPDATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "doctors/" + doctor.getSsn());
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
            }

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            Doctor doctor1 = convertFromUtf(doctor);
            wr.write(gson.toJson(doctor1));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Saving or updating doctor: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception creating or updating doctor", ex);
        }
    }

    public void createOrUpdatePatient(Patient patient) {
        try {
            HttpURLConnection con;
            if (patient.getUuid() == null || patient.getUuid().isEmpty()) { //CREATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "patients");
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");

            } else { //UPDATE
                URL obj = new URL(config.getPropertyValue("servicesUrl") + "patients/" + patient.getSsn());
                con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");
            }

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            Patient patient1 = convertFromUtf(patient);
            
            
            
    		System.out.println(gson.toJson(patient1));
    		
            
            wr.write(gson.toJson(patient1));
            //wr.write(gson.toJson(patient1));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Saving or updating patient: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception creating or updating patient", ex);
        }
    }

    public void updateSettings(Settings settings) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/" + settings.getUuid());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            Settings settings1 = convertFromUtfSettings(settings);
            wr.write(gson.toJson(settings1));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Updating settings: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception updating settings", ex);
        }
    }

    public void deleteAppointment(String uuid) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "appointments/" + uuid);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception deleting appointment with uuid: " + uuid, ex);
        }
    }

    public void deletePatient(String patientSsn) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "patients/" + patientSsn);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception deleting patient with ssn: " + patientSsn, ex);
        }
    }

    public void deleteDoctor(String doctorSsn) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "doctors/" + doctorSsn);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception deleting doctor with ssn: " + doctorSsn, ex);
        }
    }

    public void updateSymptom(Symptom newSymptom) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/symptoms/" + newSymptom.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            Symptom symptom = convertFromUtfSymptom(newSymptom);
            wr.write(gson.toJson(symptom));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Updating symptom: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception updating symptom", ex);
        }
    }

    public Symptom addNewSymptom(Symptom symptom) {
        Symptom created;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/symptoms");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            Symptom symptom1 = convertFromUtfSymptom(symptom);
            wr.write(gson.toJson(symptom1));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            created = gson.fromJson(response.toString(), Symptom.class);
            System.out.println("Creating symptom: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception creating symptom", ex);
        }
        return created;
    }

    public void updateSubject(OtherSubject subject) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/othersubjects/" + subject.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            OtherSubject otherSubject = convertFromUtfOtherSubject(subject);
            wr.write(gson.toJson(otherSubject));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Updating symptom: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception updating symptom", ex);
        }
    }

    public OtherSubject addNewSubject(OtherSubject subject) {
        OtherSubject created;
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/othersubjects");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
            OtherSubject otherSubject = convertFromUtfOtherSubject(subject);
            wr.write(gson.toJson(otherSubject));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            created = gson.fromJson(response.toString(), OtherSubject.class);
            System.out.println("Creating subject: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception creating subject", ex);
        }
        return created;
    }

    public void deleteSubject(String uuid) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/othersubjects/" + uuid);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception deleting subject with uuid: " + uuid, ex);
        }
    }

    public void deleteSymptom(String uuid) {
        try {
            URL obj = new URL(config.getPropertyValue("servicesUrl") + "settings/symptoms/" + uuid);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
            System.out.println("Deleting symptom: " + responseCode);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception deleting symptom with uuid: " + uuid, ex);
        }
    }

    private Settings convertToUtfSettings(Settings settings) {
        List<Symptom> nonSymptoms = new ArrayList<>();
        for (Symptom symptom : settings.getDefaultSymptoms()) {
            symptom.setDescription(UtfConverter.convertToUtf(symptom.getDescription()));
            symptom.setName(UtfConverter.convertToUtf(symptom.getName()));
            nonSymptoms.add(symptom);
        }

        List<OtherSubject> nonOthers = new ArrayList<>();
        for (OtherSubject otherSubject : settings.getDefaultOtherSubjects()) {
            otherSubject.setName(UtfConverter.convertToUtf(otherSubject.getName()));
            nonOthers.add(otherSubject);
        }

        settings.setDefaultSymptoms(nonSymptoms);
        settings.setDefaultOtherSubjects(nonOthers);

        return settings;
    }

    private Settings convertFromUtfSettings(Settings settings) {
        List<Symptom> nonSymptoms = new ArrayList<>();
        for (Symptom symptom : settings.getDefaultSymptoms()) {
            symptom.setDescription(UtfConverter.convertFromUtf(symptom.getDescription()));
            symptom.setName(UtfConverter.convertFromUtf(symptom.getName()));
            nonSymptoms.add(symptom);
        }

        List<OtherSubject> nonOthers = new ArrayList<>();
        for (OtherSubject otherSubject : settings.getDefaultOtherSubjects()) {
            otherSubject.setName(UtfConverter.convertFromUtf(otherSubject.getName()));
            nonOthers.add(otherSubject);
        }

        settings.setDefaultSymptoms(nonSymptoms);
        settings.setDefaultOtherSubjects(nonOthers);

        return settings;
    }


    private List<Symptom> convertToUtfSymptoms(List<Symptom> symptoms) {
        List<Symptom> nonSymptoms = new ArrayList<>();
        for (Symptom symptom : symptoms) {
            symptom.setDescription(UtfConverter.convertToUtf(symptom.getDescription()));
            symptom.setName(UtfConverter.convertToUtf(symptom.getName()));
            nonSymptoms.add(symptom);
        }
        return nonSymptoms;
    }

    private List<Symptom> convertFromUtfSymptoms(List<Symptom> symptoms) {
        List<Symptom> nonSymptoms = new ArrayList<>();
        for (Symptom symptom : symptoms) {
            symptom.setDescription(UtfConverter.convertFromUtf(symptom.getDescription()));
            symptom.setName(UtfConverter.convertFromUtf(symptom.getName()));
            nonSymptoms.add(symptom);
        }
        return nonSymptoms;
    }

    private List<OtherSubject> convertToUtfOtherSubjects(List<OtherSubject> otherSubjects) {
        List<OtherSubject> nonOthers = new ArrayList<>();
        for (OtherSubject otherSubject : otherSubjects) {
            otherSubject.setName(UtfConverter.convertToUtf(otherSubject.getName()));
            nonOthers.add(otherSubject);
        }
        return nonOthers;
    }

    private List<OtherSubject> convertFromUtfOtherSubjects(List<OtherSubject> otherSubjects) {
        List<OtherSubject> nonOthers = new ArrayList<>();
        for (OtherSubject otherSubject : otherSubjects) {
            otherSubject.setName(UtfConverter.convertFromUtf(otherSubject.getName()));
            nonOthers.add(otherSubject);
        }

        return nonOthers;
    }

    private Symptom convertToUtfSymptom(Symptom symptom) {
        symptom.setDescription(UtfConverter.convertToUtf(symptom.getDescription()));
        symptom.setName(UtfConverter.convertToUtf(symptom.getName()));
        return symptom;
    }

    private Symptom convertFromUtfSymptom(Symptom symptom) {
        symptom.setDescription(UtfConverter.convertFromUtf(symptom.getDescription()));
        symptom.setName(UtfConverter.convertFromUtf(symptom.getName()));
        return symptom;
    }

    private OtherSubject convertToUtfOtherSubject(OtherSubject otherSubject) {
        otherSubject.setName(UtfConverter.convertToUtf(otherSubject.getName()));
        return otherSubject;
    }

    private OtherSubject convertFromUtfOtherSubject(OtherSubject otherSubject) {
        otherSubject.setName(UtfConverter.convertFromUtf(otherSubject.getName()));
        return otherSubject;
    }

    private Patient convertToUtf(Patient patient){
        patient.setFirstName(UtfConverter.convertToUtf(patient.getFirstName()));
        patient.setLastName(UtfConverter.convertToUtf(patient.getLastName()));
        patient.setAddress(UtfConverter.convertToUtf(patient.getAddress()));
        patient.setCity(UtfConverter.convertToUtf(patient.getCity()));
        return patient;
    }

    private Patient convertFromUtf(Patient patient) {
        patient.setFirstName(UtfConverter.convertFromUtf(patient.getFirstName()));
        patient.setLastName(UtfConverter.convertFromUtf(patient.getLastName()));
        patient.setAddress(UtfConverter.convertFromUtf(patient.getAddress()));
        patient.setCity(UtfConverter.convertFromUtf(patient.getCity()));
        return patient;
    }

    private Doctor convertToUtf(Doctor doctor){
        doctor.setFirstName(UtfConverter.convertToUtf(doctor.getFirstName()));
        doctor.setLastName(UtfConverter.convertToUtf(doctor.getLastName()));
        doctor.setSpecialization(UtfConverter.convertToUtf(doctor.getSpecialization()));
        return doctor;
    }

    private Doctor convertFromUtf(Doctor doctor) {
        doctor.setFirstName(UtfConverter.convertFromUtf(doctor.getFirstName()));
        doctor.setLastName(UtfConverter.convertFromUtf(doctor.getLastName()));
        doctor.setSpecialization(UtfConverter.convertFromUtf(doctor.getSpecialization()));
        return doctor;
    }
}
