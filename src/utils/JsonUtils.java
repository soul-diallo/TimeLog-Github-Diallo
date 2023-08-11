package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonUtils {
    static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static void saveToJson(Object object, String filename) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T loadFromJson(String filename, Class<T> clazz) {
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8))) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static void writeJsonToFile(String json, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

