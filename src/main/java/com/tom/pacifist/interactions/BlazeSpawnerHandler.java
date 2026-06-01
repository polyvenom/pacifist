package com.tom.pacifist.interactions;

import com.tom.pacifist.PacifistAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.BlockHitResult;

public final class BlazeSpawnerHandler {

    private static final long COOLDOWN_TICKS = 12000L; // 10 minutes
    private static final int MIN_POWDER = 1;
    private static final int MAX_POWDER = 2;

    private BlazeSpawnerHandler() {}

    public static InteractionResult onUseBlock(Player player, Level level, InteractionHand hand, BlockHitResult hit) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;

        ItemStack held = player.getMainHandItem();
        if (!held.is(Items.BRUSH)) return InteractionResult.PASS;

        BlockPos pos = hit.getBlockPos();
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof SpawnerBlockEntity spawnerBE)) return InteractionResult.PASS;

        if (!(spawnerBE.getSpawner().getOrCreateDisplayEntity(level, pos) instanceof Blaze)) {
            return InteractionResult.PASS;
        }

        long now = level.getGameTime();
        Long nextAvailable = spawnerBE.getAttached(PacifistAttachments.NEXT_SIPHON_TIME);
        if (nextAvailable != null && now < nextAvailable) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            int count = MIN_POWDER + level.getRandom().nextInt(MAX_POWDER - MIN_POWDER + 1);
            ItemStack powder = new ItemStack(Items.BLAZE_POWDER, count);

            double dropX = pos.getX() + 0.5;
            double dropY = pos.getY() + 1.0;
            double dropZ = pos.getZ() + 0.5;

            ItemEntity drop = new ItemEntity(level, dropX, dropY, dropZ, powder);
            drop.setDeltaMovement(0.0, 0.2, 0.0);
            level.addFreshEntity(drop);

            level.playSound(null, dropX, dropY, dropZ,
                    SoundEvents.BRUSH_GENERIC, SoundSource.BLOCKS, 1.0f, 0.6f);
            level.playSound(null, dropX, dropY, dropZ,
                    SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.6f, 1.2f);

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                        dropX, dropY + 0.2, dropZ, 12, 0.3, 0.3, 0.3, 0.02);
            }

            held.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);

            spawnerBE.setAttached(PacifistAttachments.NEXT_SIPHON_TIME, now + COOLDOWN_TICKS);
            spawnerBE.setChanged();
        }

        player.swing(hand, true);
        return InteractionResult.SUCCESS;
    }
}
