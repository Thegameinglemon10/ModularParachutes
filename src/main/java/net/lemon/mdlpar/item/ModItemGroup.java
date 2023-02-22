package net.lemon.mdlpar.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lemon.mdlpar.MdlParachutes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
	public static final ItemGroup MDL_PARACHUTES = FabricItemGroupBuilder.build(
			new Identifier(MdlParachutes.MOD_ID, "mdlpar_itemgroup"), () -> new ItemStack(ModItems.PARACHUTE)
	);
}
