package com.notryken.notrykenmlt;

import com.notryken.notrykenmlt.command.Commands;
import com.notryken.notrykenmlt.gui.screen.ConfigScreenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.TickEvent;


@Mod(NotRykenMLT.MOD_ID)
@Mod.EventBusSubscriber(modid = NotRykenMLT.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NotRykenMLTNeoForge {
    public NotRykenMLTNeoForge() {
        // Config screen
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (mc, parent) -> ConfigScreenProvider.getConfigScreen(parent))
                );

        // Main initialization
        NotRykenMLT.init();
    }

    // Keybindings
    @SubscribeEvent
    static void registerKeyMappingsEvent(RegisterKeyMappingsEvent event) {
        event.register(NotRykenMLT.EXAMPLE_KEY);
    }

    @Mod.EventBusSubscriber(modid = NotRykenMLT.MOD_ID, value = Dist.CLIENT)
    static class ClientEventHandler {
        // Commands
        @SubscribeEvent
        static void registerClientCommands(RegisterClientCommandsEvent event) {
            new Commands<CommandSourceStack>().register(Minecraft.getInstance(), event.getDispatcher(), event.getBuildContext());
        }

        // Tick events
        @SubscribeEvent
        public static void clientTickEvent(TickEvent.ClientTickEvent event) {
            if(event.phase.equals(TickEvent.Phase.END)) {
                NotRykenMLT.onEndTick(Minecraft.getInstance());
            }
        }
    }
}
