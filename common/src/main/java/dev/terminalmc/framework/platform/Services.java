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

package dev.terminalmc.framework.platform;

import dev.terminalmc.framework.Framework;
import dev.terminalmc.framework.platform.services.IPlatformServices;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformServices PLATFORM = load(IPlatformServices.class);

    public static <T> T load(Class<T> service) {
        final T loadedService = ServiceLoader.load(service, service.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException(
                        "Failed to load service for " + service.getName()));
        Framework.LOG.debug("Loaded {} for service {}", loadedService, service);
        return loadedService;
    }
}
