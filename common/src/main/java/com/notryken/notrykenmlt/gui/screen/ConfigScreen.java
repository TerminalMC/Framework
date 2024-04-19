package com.notryken.notrykenmlt.gui.screen;

import com.notryken.notrykenmlt.config.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;

import static com.notryken.notrykenmlt.util.Localization.localized;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        Config.Options options = Config.get().options;

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localized("screen", "title.default"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder eb = builder.entryBuilder();
        ConfigCategory modSettings = builder.getOrCreateCategory(localized("config", "options"));

        modSettings.addEntry(eb.startIntSlider(localized("config", "options.val1"), options.val1, 0, 10)
                .setDefaultValue(options.defaultVal1)
                .setSaveConsumer(val -> options.val1 = val)
                .build());

        modSettings.addEntry(eb.startStrField(localized("config", "options.val2"), options.val2)
                .setDefaultValue(options.defaultVal2)
                .setSaveConsumer(val -> options.val2 = val)
                .build());

        return builder.build();
    }
}
