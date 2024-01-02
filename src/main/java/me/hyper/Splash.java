package me.hyper;

import me.hyper.util.Utilities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Splash implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Splash");


	public static final Block EASEL = new Block(FabricBlockSettings.create().solid().burnable().pistonBehavior(PistonBehavior.DESTROY));

	@Override
	public void onInitialize() {

		Utilities.registerBlock("easel", EASEL);

		LOGGER.info("Initialized Splash.");
	}

}