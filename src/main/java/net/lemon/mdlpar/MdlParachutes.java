package net.lemon.mdlpar;

import net.lemon.mdlpar.item.ModItems;
import net.lemon.mdlpar.networking.ModPackets;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MdlParachutes implements ModInitializer {
	public static final String MOD_ID = "mdlpar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(ModContainer mod) {
		if (QuiltLoader.isModLoaded("trinkets")) {
			ModItems.registerModItems();
			ModPackets.registerC2SPackets();
			ModPackets.registerS2CPackets();
		}
	}
}