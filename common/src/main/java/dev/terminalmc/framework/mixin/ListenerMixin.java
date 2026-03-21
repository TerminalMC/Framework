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

package dev.terminalmc.framework.mixin;

import com.mojang.blaze3d.audio.Listener;
import com.mojang.blaze3d.audio.ListenerTransform;
import dev.terminalmc.framework.Framework;
import org.lwjgl.openal.AL10;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Listener.class)
public class ListenerMixin {

    @Inject(
            method = "setTransform",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/audio/ListenerTransform;position()Lnet/minecraft/world/phys/Vec3;"
            ),
            cancellable = true
    )
    private void setTransform(ListenerTransform transform, CallbackInfo ci) {
        Framework.listener = transform.position().toVector3f();
        AL10.alListener3f(4100, 0.0F, 0.0F, 0.0F);
        ci.cancel();
    }
}
