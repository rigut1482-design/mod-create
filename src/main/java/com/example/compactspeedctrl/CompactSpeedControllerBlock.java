package com.example.compactspeedctrl;

import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlock;
import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Same behaviour as Create's rotation speed controller (hold / scroll on the value box, -256..256 rpm),
 * but built as a one-block shaft-through part: shaft in on one side, out on the other, like a clutch.
 */
public class CompactSpeedControllerBlock extends SpeedControllerBlock {

    public CompactSpeedControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SpeedControllerBlockEntity> getBlockEntityType() {
        return CompactSpeedCtrlBlockEntities.COMPACT_SPEED_CONTROLLER.get();
    }
}
