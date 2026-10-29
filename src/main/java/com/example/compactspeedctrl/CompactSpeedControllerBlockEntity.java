package com.example.compactspeedctrl;

import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompactSpeedControllerBlockEntity extends SpeedControllerBlockEntity {

    public CompactSpeedControllerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
