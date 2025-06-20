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

import java.nio.file.Path;

public interface IPlatformInfo {

    /**
     * @return the name of the current platform.
     */
    String getPlatformName();

    /**
     * @return true if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * @return the game directory of the instance.
     */
    Path getGameDir();

    /**
     * @return the configuration directory of the instance.
     */
    Path getConfigDir();

    /**
     * @return true if in a development environment, false otherwise.
     */
    boolean isDevEnv();

    /**
     * @return the name of the environment type.
     */
    default String getEnvName() {
        return isDevEnv() ? "development" : "production";
    }
}
