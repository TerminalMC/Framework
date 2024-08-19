package dev.terminalmc.framework.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.InputConstants;
import dev.terminalmc.framework.Framework;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Config {
    private static final Path DIR_PATH = Path.of("config");
    private static final String FILE_NAME = Framework.MOD_ID + ".json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Options

    public final Options options = new Options();

    public static class Options {
        // First category
        public static final boolean defaultBooleanExample = true;
        public boolean booleanExample = defaultBooleanExample;

        public static final int defaultIntExample = 7;
        public int intExample = defaultIntExample;

        public static final double defaultDoubleExample = 4.5;
        public double doubleExample = defaultDoubleExample;

        public static final String defaultLenientStringExample = "example";
        public String lenientStringExample = defaultLenientStringExample;

        public static final List<String> strictStringExampleValues = List.of("One", "Two", "Three");
        public static final String defaultStrictStringExample = strictStringExampleValues.getFirst();
        public String strictStringExample = defaultStrictStringExample;

        public static final TriState defaultEnumExample = TriState.Value1;
        public TriState enumExample = defaultEnumExample;

        // Second category
        public static final List<String> defaultStringListExample = List.of("One");
        public static final String defaultStringListExampleValue = "One";
        public List<String> stringListExample = defaultStringListExample;

        // Third Category
        public static final int defaultRgbExample = 16777215;
        public int rgbExample = defaultRgbExample;

        public static final int defaultArgbExample = -1;
        public int argbExample = defaultArgbExample;

        // Cloth Config only
        public static final int defaultKeyExample = InputConstants.KEY_J;
        public int keyExample = defaultKeyExample;

        // YACL only
        public static final String defaultItemExample = BuiltInRegistries.ITEM.getKey(Items.STONE).toString();
        public String itemExample = defaultItemExample;
    }

    public enum TriState {
        Value1,
        Value2,
        Value3
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

    // Cleanup

    private void cleanup() {
        // Called before config is saved
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
            // Catch Exception as errors in deserialization may not fall under
            // IOException or JsonParseException, but should not crash the game.
            Framework.LOG.error("Unable to load config.", e);
            return null;
        }
    }

    public static void save() {
        if (instance == null) return;
        instance.cleanup();
        try {
            if (!Files.isDirectory(DIR_PATH)) Files.createDirectories(DIR_PATH);
            Path file = DIR_PATH.resolve(FILE_NAME);
            Path tempFile = file.resolveSibling(file.getFileName() + ".tmp");

            try (FileWriter writer = new FileWriter(tempFile.toFile())) {
                writer.write(GSON.toJson(instance));
            } catch (IOException e) {
                throw new IOException(e);
            }
            Files.move(tempFile, file, StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING);
            Framework.onConfigSaved(instance);
        } catch (IOException e) {
            Framework.LOG.error("Unable to save config.", e);
        }
    }
}
