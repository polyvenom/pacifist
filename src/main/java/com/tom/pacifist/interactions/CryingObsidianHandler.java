package com.tom.pacifist.interactions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public final class CryingObsidianHandler {

    private static final float TEAR_CHANCE = 0.15f;

    private CryingObsidianHandler() {}

    public static InteractionResult onUseBlock(Player player, Level level, InteractionHand hand, BlockHitResult hit) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;

        ItemStack held = player.getMainHandItem();
        if (!held.is(Items.GLASS_BOTTLE)) return InteractionResult.PASS;

        BlockPos pos = hit.getBlockPos();
        if (!level.getBlockState(pos).is(Blocks.CRYING_OBSIDIAN)) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            double dropX = pos.getX() + 0.5;
            double dropY = pos.getY() + 1.0;
            double dropZ = pos.getZ() + 0.5;

            boolean success = level.getRandom().nextFloat() < TEAR_CHANCE;

            if (!player.isCreative()) {
                held.shrink(1);
            }

            if (success) {
                ItemStack tear = new ItemStack(Items.GHAST_TEAR, 1);
                ItemEntity drop = new ItemEntity(level, dropX, dropY, dropZ, tear);
                drop.setDeltaMovement(0.0, 0.1, 0.0);
                level.addFreshEntity(drop);

                level.playSound(null, dropX, dropY, dropZ,
                        SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
            } else {
                level.playSound(null, dropX, dropY, dropZ,
                        SoundEvents.GHAST_AMBIENT, SoundSource.BLOCKS, 0.3f, 1.5f);
            }

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
                        dropX, dropY - 0.5, dropZ, 6, 0.25, 0.1, 0.25, 0.0);
            }
        }

        player.swing(hand, true);
        return InteractionResult.SUCCESS;
    }
}
