package com.notryken.notrykenmlt.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.notryken.notrykenmlt.NotRykenMLT;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Config {
    private static final Path DIR_PATH = Path.of("config");
    private static final String FILE_NAME = NotRykenMLT.MOD_ID + ".json";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    // Options

    public final Options options = new Options();

    public static class Options {
        public int defaultVal1 = 1;
        public String defaultVal2 = "two";

        public int val1 = defaultVal1;
        public String val2 = defaultVal2;
    }


    // Instance management

    private static Config instance = null;

    public static Config get() {
        if (instance == null) {
            instance = Config.load();
        }
        return instance;
    }

    public static Config getAndSave() {
        get();
        save();
        return instance;
    }

    public static Config resetAndSave() {
        instance = new Config();
        save();
        return instance;
    }


    // Load and save

    public static @NotNull Config load() {
        Path file = DIR_PATH.resolve(FILE_NAME);
        Config config = null;
        if (Files.exists(file)) {
            config = load(file, GSON);
        }
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private static @Nullable Config load(Path file, Gson gson) {
        try (FileReader reader = new FileReader(file.toFile())) {
            return gson.fromJson(reader, Config.class);
        } catch (Exception e) {
            NotRykenMLT.LOG.error("Unable to load config.", e);
            return null;
        }
    }

    public static void save() {
        if (instance == null) return;
        try {
            if (!Files.isDirectory(DIR_PATH)) {
                Files.createDirectories(DIR_PATH);
            }
            Path file = DIR_PATH.resolve(FILE_NAME);
            Path tempFile = file.resolveSibling(file.getFileName() + ".tmp");

            try (FileWriter writer = new FileWriter(tempFile.toFile())) {
                writer.write(GSON.toJson(instance));
            }
            catch (IOException e) {
                throw new IOException(e);
            }
            Files.move(tempFile, file, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            NotRykenMLT.LOG.error("Unable to save config.", e);
        }
    }
}
