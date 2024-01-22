package me.hyper.splash;

import me.hyper.splash.blocks.Easel;
import me.hyper.splash.blocks.EaselBlockEntity;
import me.hyper.splash.items.Canvas;
import me.hyper.splash.util.Utilities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Splash implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Splash");

	public static final Block EASEL = new Easel(FabricBlockSettings.create().solid().burnable().pistonBehavior(PistonBehavior.DESTROY).nonOpaque().strength(1.0f));

	public static final BlockEntityType<EaselBlockEntity> EASEL_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier("splash", "easel_block_entity"),
			FabricBlockEntityTypeBuilder.create(EaselBlockEntity::new, EASEL).build()
	);

	public static final Item CANVAS = new Canvas(new FabricItemSettings().maxCount(1));


	@Override
	public void onInitialize() {
		Utilities.registerBlock("easel", EASEL);

		Utilities.registerItem("easel", new BlockItem(EASEL, new FabricItemSettings()));
		Utilities.registerItem("canvas", CANVAS);
		LOGGER.info("Initialized Splash.");
	}

}