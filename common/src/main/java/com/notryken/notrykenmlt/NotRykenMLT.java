package com.notryken.notrykenmlt;

import com.mojang.blaze3d.platform.InputConstants;
import com.notryken.notrykenmlt.config.Config;
import com.notryken.notrykenmlt.gui.screen.ConfigScreenProvider;
import com.notryken.notrykenmlt.util.ModLogger;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import static com.notryken.notrykenmlt.util.Localization.translationKey;

public class NotRykenMLT {
    public static final String MOD_ID = "notrykenmlt";
    public static final String MOD_NAME = "NotRykenMLT";
    public static final ModLogger LOG = new ModLogger(MOD_NAME);
    public static final Component PREFIX = Component.empty()
            .append(Component.literal("[").withStyle(ChatFormatting.DARK_GRAY))
            .append(Component.literal(MOD_NAME).withStyle(ChatFormatting.GOLD))
            .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
            .withStyle(ChatFormatting.GRAY);
    public static final KeyMapping EXAMPLE_KEY = new KeyMapping(
            translationKey("key", "main.example"), InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(), translationKey("keygroup", "main"));

    public static void init() {
        Config.getAndSave();
    }

    public static void onEndTick(Minecraft mc) {
        while (EXAMPLE_KEY.consumeClick()) {
            mc.setScreen(ConfigScreenProvider.getConfigScreen(mc.screen));
        }
    }
}
