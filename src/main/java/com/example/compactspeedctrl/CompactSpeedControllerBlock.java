package com.example.compactspeedctrl;

import com.simibubi.create.content.kinetics.base.HorizontalAxisKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * One-block shaft-through speed controller, like a clutch.
 * Right click: +16 (up to 256). Shift + right click: -16 (down to -256).
 */
public class CompactSpeedControllerBlock extends HorizontalAxisKineticBlock implements IBE<CompactSpeedControllerBlockEntity> {

    public CompactSpeedControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<CompactSpeedControllerBlockEntity> getBlockEntityClass() {
        return CompactSpeedControllerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CompactSpeedControllerBlockEntity> getBlockEntityType() {
        return CompactSpeedCtrlBlockEntities.COMPACT_SPEED_CONTROLLER.get();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        withBlockEntityDo(level, pos, be -> {
            be.changeValue(player.isShiftKeyDown() ? -16 : 16);
            player.displayClientMessage(Component.literal("Speed: " + be.getValue() + " (" + (be.getValue() * 100 / 256) + "%)"), true);
        });
        return InteractionResult.SUCCESS;
    }
}
