package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;
import java.util.regex.*;

import java.io.*;
import java.util.stream.Collectors;

public class FileUtils {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void fileShouldExist(String fileName) {
        fileShouldExist(new File(fileName));
    }

    public static void fileShouldExist(File file) {
        // If file does not exist, create it
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // create empty file
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ensureJsonObject(File file) {
        fileShouldExist(file);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            JsonObject object = gson.fromJson(br, JsonObject.class);
            if (object == null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                object = new JsonObject();
                bw.write(gson.toJson(object));
                bw.close();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ensureJsonArray(File file) {
        fileShouldExist(file);
        try {
            JsonArray array = readJsonArray(file);
            if (array == null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                array = new JsonArray();
                bw.write(gson.toJson(array));
                bw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToJsonArray(File file, Object value, Boolean allowDouble) {
        try {
            JsonArray array = readJsonArray(file);
            if (allowDouble) {
                array.add(gson.toJsonTree(value));
            } else if (!array.contains(gson.toJsonTree(value))) {
                array.add(gson.toJsonTree(value));
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(gson.toJson(array));
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToJsonObject(File file, String key, Object value) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            JsonObject object = gson.fromJson(br, JsonObject.class);
            object.add(key, gson.toJsonTree(value));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(gson.toJson(object));
            bw.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String res = br.lines().collect(Collectors.joining("\r\n"));
            br.close();
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(File file, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonArray readJsonArray(File file) {
        BufferedReader br;
        JsonArray array;
        try {
            br = new BufferedReader(new FileReader(file));
            array = gson.fromJson(br, JsonArray.class);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return array;
    }

    public static JsonObject readJsonObject(File file) {
        BufferedReader br;
        JsonObject object;
        try {
            br = new BufferedReader(new FileReader(file));
            object = gson.fromJson(br, JsonObject.class);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public static void replacePlaceholdersInFile(File file, Map<String, String> valueMap) {
        String content = readFile(file);
        for (Map.Entry<String, String> entry : valueMap.entrySet()) {
            content = content.replaceAll("\\{\\{" + entry.getKey() + "}}", entry.getValue());
        }
        writeFile(file, content);
    }
}
