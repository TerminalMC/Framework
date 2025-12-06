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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(value = Framework.MOD_ID)
@EventBusSubscriber(
        modid = Framework.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class FrameworkForge {

    public FrameworkForge() {
        // Register config screen
        //noinspection removal
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (minecraft, parent) -> ConfigScreenProvider.getConfigScreen(parent))
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
        public static void registerAfterClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase.equals(TickEvent.Phase.END)) {
                Framework.afterClientTick(Minecraft.getInstance());
            }
        }
    }
}
