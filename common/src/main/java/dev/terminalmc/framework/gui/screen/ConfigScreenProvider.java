package dev.terminalmc.framework.gui.screen;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;

import static dev.terminalmc.framework.util.Localization.localized;

/**
 * Wraps the config screen implementation and provides a backup screen for
 * use when the config lib mod is not loaded. This allows the dependency to be
 * defined as optional.
 */
public class ConfigScreenProvider {

    public static Screen getConfigScreen(Screen parent) {
        try {
//            return ClothScreenProvider.getConfigScreen(parent);
            return YaclScreenProvider.getConfigScreen(parent);
        } catch (NoClassDefFoundError ignored) {
//            return new BackupScreen(parent, "install_cloth", "https://modrinth.com/mod/9s6osm5g");
            return new BackupScreen(parent, "install_yacl", "https://modrinth.com/mod/1eAoo2KR");
        }
    }

    static class BackupScreen extends OptionsSubScreen {
        private final String modKey;
        private final String modUrl;

        public BackupScreen(Screen parent, String modKey, String modUrl) {
            super(parent, Minecraft.getInstance().options, localized("name"));
            this.modKey = modKey;
            this.modUrl = modUrl;
        }

        @Override
        public void init() {
            MultiLineTextWidget messageWidget = new MultiLineTextWidget(
                    width / 2 - 120, height / 2 - 40,
                    localized("message", modKey),
                    minecraft.font);
            messageWidget.setMaxWidth(240);
            messageWidget.setCentered(true);
            addRenderableWidget(messageWidget);

            Button openLinkButton = Button.builder(localized("message", "go_modrinth"),
                            (button) -> minecraft.setScreen(new ConfirmLinkScreen(
                                    (open) -> {
                                        if (open) Util.getPlatform().openUri(modUrl);
                                        minecraft.setScreen(lastScreen);
                                    }, modUrl, true)))
                    .pos(width / 2 - 120, height / 2)
                    .size(115, 20)
                    .build();
            addRenderableWidget(openLinkButton);

            Button exitButton = Button.builder(CommonComponents.GUI_OK,
                            (button) -> onClose())
                    .pos(width / 2 + 5, height / 2)
                    .size(115, 20)
                    .build();
            addRenderableWidget(exitButton);
        }

        @Override
        protected void addOptions() {}
    }
}
