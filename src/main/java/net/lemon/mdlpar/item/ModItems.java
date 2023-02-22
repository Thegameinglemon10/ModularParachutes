package net.lemon.mdlpar.item;

import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lemon.mdlpar.MdlParachutes;
import net.lemon.mdlpar.trinkets.ParachuteHandler;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
	public static final Item PARACHUTE = registerItem("parachute",
			new Item(new FabricItemSettings().group(ModItemGroup.MDL_PARACHUTES).maxCount(16)));

	public static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(MdlParachutes.MOD_ID, name), item);
	}

	public static void registerModItems() {
		MdlParachutes.LOGGER.debug("Registering items for " + MdlParachutes.MOD_ID);
		TrinketsApi.registerTrinket(PARACHUTE, new ParachuteHandler());
	}
}
