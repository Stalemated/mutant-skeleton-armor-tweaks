package com.stalemated.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    public static final String MOD_ID = "mutant-skeleton-armor-tweaks";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final File file;
    private JsonObject json;

    public ConfigManager(String fileName) {
        this.file = FabricLoader.getInstance().getConfigDir().resolve(fileName).toFile();
        load();
    }

    // Load config file
    private void load() {
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                json = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (Exception e) {
                LOGGER.error("Exception reading config file, writing a new one...", e);
                json = new JsonObject();
            }
        } else {
            LOGGER.warn("Could not load config file, writing a new one...");
            json = new JsonObject();
        }
    }

    // Save config file
    public void save() {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            LOGGER.error("Could not save config file", e);
        }
    }

    // Get key values from the config file
    public boolean getOrDefault(String key, boolean defaultValue) {
        if (json.has(key)) {
            try {
                JsonElement element = json.get(key);

                if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isBoolean()) {
                    return element.getAsBoolean();
                }

                LOGGER.warn("Invalid value type for key '{}', expected boolean. Resetting to default.", key);
            } catch (Exception e) {
                LOGGER.warn("Error parsing key '{}', resetting to default.", key);
            }
        } else {
            LOGGER.warn("Key '{}' could not be found, adding it to the config file with default value.", key);
        }

        json.addProperty(key, defaultValue);
        save();
        return defaultValue;
    }
}