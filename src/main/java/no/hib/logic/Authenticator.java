package no.hib.logic;

import com.google.gson.Gson;
import no.hib.config.Config;
import no.hib.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("authenticator")
@Transactional
public class Authenticator {

    public boolean validUser(User user){
        Config config = new Config();
        try {

            URL obj = new URL(config.getPropertyValue("servicesUrl") + "login/admin");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json; charset=utf-8");
            //con.setRequestProperty("Authorization", "Bearer " + config.getPropertyValue("Token"));

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(new Gson().toJson(user));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            return responseCode == 200;
            
        } catch (Exception ex) {
            System.err.println("Exception authenticating user" + ex);
            return false;
        }
    }
}
