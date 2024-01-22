package me.hyper.splash;

import me.hyper.splash.blocks.EaselBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class SplashClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(Splash.EASEL, RenderLayer.getCutout());

		BlockEntityRendererFactories.register(Splash.EASEL_BLOCK_ENTITY, EaselBlockEntityRenderer::new);
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x3495eb, Splash.EASEL);
	}
}
