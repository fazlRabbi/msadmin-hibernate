package no.hib.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private InputStream inputStream;
    private String propFileName = "config.properties";
    private Properties properties;

    public Config() {
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String getPropertyValue(String key) {
        String value = properties.getProperty(key);
        return value;
    }
}
