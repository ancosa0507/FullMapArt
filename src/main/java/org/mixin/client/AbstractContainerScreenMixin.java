package org.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.Items;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Inject(method = "renderSlot", at = @At("HEAD"))
    private void beforeRenderSlot(GuiGraphics guiGraphics, Slot slot, int i, int j, CallbackInfo ci) {
        if (slot.hasItem() && slot.getItem().is(Items.FILLED_MAP)) {
            guiGraphics.pose().pushMatrix();

            float scale = 1.125f;
            int x = slot.x;
            int y = slot.y;
            guiGraphics.pose().translate(x + 8, y + 8);
            guiGraphics.pose().scale(scale, scale);
            guiGraphics.pose().translate(-(x + 8), -(y + 8));
        }
    }

    @Inject(method = "renderSlot", at = @At("TAIL"))
    private void afterRenderSlot(GuiGraphics guiGraphics, Slot slot, int i, int j, CallbackInfo ci) {
        if (slot.hasItem() && slot.getItem().is(Items.FILLED_MAP)){
            guiGraphics.pose().popMatrix();
        }
    }
}