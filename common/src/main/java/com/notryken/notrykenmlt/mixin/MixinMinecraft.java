package com.notryken.notrykenmlt.mixin;

import com.notryken.notrykenmlt.NotRykenMLT;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        NotRykenMLT.LOG.info("This line is printed by an example mod common mixin!");
        NotRykenMLT.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}
