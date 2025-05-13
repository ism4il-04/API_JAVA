package util;

import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Data
public class DBConfigLoader {
    public static Properties chargeDBConfig(String chemin, String prefix){
        Properties conf = null;
        try (FileInputStream fis = new FileInputStream(chemin)) {
            Properties confGlobale = new Properties(9);
            confGlobale.load(fis);

            conf = new Properties(3);
            for (String key : confGlobale.stringPropertyNames()) {
                if (key.startsWith(prefix+".")) {
                    String newKey = key.substring(prefix.length()+1);
                    conf.setProperty(newKey, confGlobale.getProperty(key));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conf;
    }
}
