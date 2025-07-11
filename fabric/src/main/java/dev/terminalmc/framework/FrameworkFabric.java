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
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

@SuppressWarnings("unused")
public class FrameworkFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register keybinds
        Framework.KEYBINDS.forEach(KeyBindingHelper::registerKeyBinding);

        // Register client commands
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, buildContext) ->
                new Commands<FabricClientCommandSource>().register(
                        dispatcher,
                        buildContext
                )));

        // Register client after-tick event
        ClientTickEvents.END_CLIENT_TICK.register(Framework::afterClientTick);

        // Initialize client
        Framework.init();
    }
}
