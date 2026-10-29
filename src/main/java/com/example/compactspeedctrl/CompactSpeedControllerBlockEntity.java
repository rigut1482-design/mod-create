package com.example.compactspeedctrl;

import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class CompactSpeedControllerBlockEntity extends SplitShaftBlockEntity {

    /** -256..256. 256 = pass the input speed through unchanged, 128 = half, 0 = stopped, negative = reversed. */
    private int value = 256;

    public CompactSpeedControllerBlockEntity(BlockPos pos, BlockState state) {
        super(CompactSpeedCtrlBlockEntities.COMPACT_SPEED_CONTROLLER.get(), pos, state);
    }

    public int getValue() {
        return value;
    }

    public void changeValue(int delta) {
        value = Math.max(-256, Math.min(256, value + delta));
        setChanged();
        sendData();
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        return value / 256f;
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(tag, registries, clientPacket);
        tag.putInt("Value", value);
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(tag, registries, clientPacket);
        value = tag.contains("Value") ? tag.getInt("Value") : 256;
    }
}
