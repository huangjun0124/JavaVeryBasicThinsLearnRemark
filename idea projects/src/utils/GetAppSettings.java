package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.configuration.AppSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetAppSettings {
    private static AppSettings settings;

    public static AppSettings GetSettings(){
        return settings;
    }

    public static void LoadSettings(String path) {

        String jsonStr = readFileAsString(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            settings = mapper.readValue(jsonStr,AppSettings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileAsString(String fileName)
    {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
