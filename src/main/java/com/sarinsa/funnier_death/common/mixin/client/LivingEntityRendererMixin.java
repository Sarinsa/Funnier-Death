package com.sarinsa.funnier_death.common.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sarinsa.funnier_death.common.util.mixin.ClientMixinWork;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {

    public LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Shadow
    protected abstract float getFlipDegrees(T entity);

    @Redirect(method = "setupRotations", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;getFlipDegrees(Lnet/minecraft/world/entity/LivingEntity;)F"))
    public float replaceFlipDegrees(LivingEntityRenderer<T, M> livingRenderer, T entity) {
        return ClientMixinWork.getRendererFlipDegrees(this.getFlipDegrees(entity), entity);
    }

    @Inject(method = "scale", at = @At("HEAD"), cancellable = true)
    public void onScale(T entity, PoseStack poseStack, float partialTick, CallbackInfo ci) {
        //ClientMixinWork.onScale(entity, poseStack, partialTick, ci);
    }
}
