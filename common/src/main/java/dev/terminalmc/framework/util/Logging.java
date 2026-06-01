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

package dev.terminalmc.framework.util;

import dev.terminalmc.framework.platform.services.PlatformServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.apache.logging.log4j.message.FormattedMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

@SuppressWarnings("unused")
public class Logging {

    private Logging() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static Logger getLogger(String name) {
        if (PlatformServices.getInstance().isDevEnv()
                || PlatformServices.getInstance().hasNamedLogger()) {
            return LogManager.getLogger(name);
        } else {
            return LogManager.getLogger(name, new PrefixingMessageFactory("[" + name + "/]: "));
        }
    }

    private static final class PrefixingMessageFactory extends AbstractMessageFactory {

        private final String prefix;

        public PrefixingMessageFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Message newMessage(String message) {
            return new SimpleMessage(prefix + message);
        }

        @Override
        public Message newMessage(String message, Object... params) {
            return new FormattedMessage(prefix + message, params);
        }
    }
}
