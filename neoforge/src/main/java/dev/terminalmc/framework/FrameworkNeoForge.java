/*
 * Framework by TerminalMC
 *
 * To the extent possible under law, the person who associated CC0 with
 * Framework has waived all copyright and related or neighboring rights
 * to Framework.
 *
 * You should have received a copy of the CC0 legalcode along with this
 * work. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.terminalmc.framework;

import dev.terminalmc.framework.command.Commands;
import dev.terminalmc.framework.gui.screen.ConfigScreenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(
        value = Framework.MOD_ID,
        dist = Dist.CLIENT
)
@EventBusSubscriber(
        modid = Framework.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class FrameworkNeoForge {

    public FrameworkNeoForge() {
        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (mc, parent) -> ConfigScreenProvider.getConfigScreen(parent)
        );

        // Initialize client
        Framework.init();
    }

    /**
     * Registers all keybinds.
     */
    @SubscribeEvent
    static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        Framework.KEYBINDS.forEach(event::register);
    }

    @EventBusSubscriber(
            modid = Framework.MOD_ID,
            value = Dist.CLIENT
    )
    static class ClientEventHandler {

        /**
         * Registers all client-side commands.
         */
        @SubscribeEvent
        static void registerClientCommands(RegisterClientCommandsEvent event) {
            new Commands<CommandSourceStack>().register(
                    event.getDispatcher(),
                    event.getBuildContext()
            );
        }

        /**
         * Registers client after-tick event.
         */
        @SubscribeEvent
        public static void registerAfterClientTick(ClientTickEvent.Post event) {
            Framework.afterClientTick(Minecraft.getInstance());
        }
    }
}
