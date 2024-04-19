package com.notryken.notrykenmlt.mixin;

import com.notryken.notrykenmlt.NotRykenMLT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(method = "init()V", at = @At("HEAD"))
    private void init(CallbackInfo info) {
        NotRykenMLT.LOG.info("This line is printed by an example mod mixin from Fabric!");
        NotRykenMLT.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}
