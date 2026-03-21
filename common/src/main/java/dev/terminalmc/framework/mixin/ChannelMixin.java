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

import com.mojang.blaze3d.audio.Channel;
import dev.terminalmc.framework.Framework;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.lwjgl.openal.AL10;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Channel.class)
public class ChannelMixin {

    @Shadow
    @Final
    private int source;

    @Inject(
            method = "setSelfPosition",
            at = @At("HEAD"),
            cancellable = true
    )
    private void setSelfPosition(Vec3 source, CallbackInfo ci) {
        Vector3f vec = Framework.listener;
        float distance = (float) source.distanceTo(new Vec3(vec.x, vec.y, vec.z));
        AL10.alSourcefv(this.source, 4100, new float[]{0.0F, 0.0F, distance});
        ci.cancel();
    }
}
