package com.example.compactspeedctrl;

import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CompactSpeedControllerBlockEntity extends SpeedControllerBlockEntity {

    public CompactSpeedControllerBlockEntity(BlockPos pos, BlockState state) {
        super(CompactSpeedCtrlBlockEntities.COMPACT_SPEED_CONTROLLER.get(), pos, state);
    }
}
