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

package dev.terminalmc.framework.platform.services;

import dev.terminalmc.framework.platform.Services;

import java.nio.file.Path;

@SuppressWarnings("unused")
public interface PlatformServices {

    PlatformServices INSTANCE = Services.load(PlatformServices.class);

    static PlatformServices getInstance() {
        return INSTANCE;
    }

    /**
     * @return {@code true} if in a development environment.
     */
    boolean isDevEnv();

    /**
     * @return {@code true} if the mod is loaded.
     */
    boolean isModLoaded(String modId);

    /**
     * @return the name of the current platform.
     */
    String getPlatformName();

    /**
     * @return the game directory of the instance.
     */
    Path getGameDir();

    /**
     * @return the configuration directory of the instance.
     */
    Path getConfigDir();

    /**
     * @return the name of the environment type.
     */
    default String getEnvName() {
        return isDevEnv() ? "development" : "production";
    }
}
