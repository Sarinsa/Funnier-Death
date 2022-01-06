package com.sarinsa.funnier_death.common.mixin.client;

import com.sarinsa.funnier_death.common.util.mixin.RandomFlipDegrees;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
@Implements(@Interface(iface = RandomFlipDegrees.class, prefix = "_$"))
public abstract class LivingEntityMixin extends Entity {

    @Unique
    private float randomFlipDegrees = 90.0F;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public float _$getRandomFlipDegrees() {
        return this.randomFlipDegrees;
    }

    public void _$setRandomFlipDegrees(float flipDegrees) {
        this.randomFlipDegrees = flipDegrees;
    }
}
