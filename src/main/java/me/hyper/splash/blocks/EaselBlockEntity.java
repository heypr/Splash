package me.hyper.splash.blocks;

import me.hyper.splash.Splash;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.state.property.Properties.FACING;

public class EaselBlockEntity extends BlockEntity {

    public boolean usingCanvas;

    public EaselBlockEntity(BlockPos pos, BlockState state) {
        super(Splash.EASEL_BLOCK_ENTITY, pos, state);
    }

    public ItemStack getRenderStack() {
        return new ItemStack(Splash.CANVAS, 1);
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void setUsingCanvas(boolean usingCanvas) {
        this.usingCanvas = usingCanvas;
        createNbt().putBoolean("in_use", usingCanvas);
        markDirty();
    }

    public Direction getFacing() {
        return getCachedState().get(FACING);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putBoolean("in_use", usingCanvas);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        usingCanvas = nbt.getBoolean("in_use");
        super.readNbt(nbt);
    }
}
