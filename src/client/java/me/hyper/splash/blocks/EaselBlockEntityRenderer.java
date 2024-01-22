package me.hyper.splash.blocks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class EaselBlockEntityRenderer implements BlockEntityRenderer<EaselBlockEntity> {

    public EaselBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(EaselBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!entity.usingCanvas) return;

        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        matrices.push();
        matrices.scale(1, 1, 1);
        switch (entity.getFacing()) {
            case NORTH:
                matrices.translate(0.5, 0.82, 0.34);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(22.5f));
                break;
            case SOUTH:
                matrices.translate(0.5, 0.82, 0.67);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-22.5f));
                break;
            case EAST:
                matrices.translate(0.67, 0.82, 0.5);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90f));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-22.5f));
                break;
            case WEST:
                matrices.translate(0.34, 0.82, 0.5);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-22.5f));
                break;
        }
        itemRenderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
