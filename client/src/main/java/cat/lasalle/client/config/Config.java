package cat.lasalle.client.config;


import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class Config {
    private final Logger logger = LoggerFactory.getLogger(Config.class);
    private final String configFile = "config.properties";
    private final Properties properties = new Properties();

    public static void load() {
        try (FileInputStream in = new FileInputStream(configFile)) {
            properties.load(in);
        } catch (IOException e) {
            logger.error("Error loading properties: ", e);
        }
    }

    public static void save() {
        try (FileOutputStream out = new FileOutputStream(configFile)) {
            properties.store(out, null);
        } catch (IOException e) {
            logger.error("Error saving properties: ", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }
}