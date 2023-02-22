package net.lemon.mdlpar.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lemon.mdlpar.MdlParachutes;
import net.lemon.mdlpar.item.ModItemGroup;
import net.lemon.mdlpar.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

//public class ParachuteTrinket extends TrinketItem {
public class ParachuteTrinket {
	/*
	public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		var modifiers = super.getModifiers(stack, slot, entity, uuid);
		return modifiers;
	}
	 */

	//public static final Item PARACHUTE = registerItem("parachute",
			//new Item(new FabricItemSettings().group(ModItemGroup.MDL_PARACHUTES)));

	//public static void init() {

	//}

	/*
	public static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(MdlParachutes.MOD_ID, name), item);
	}

	public static void registerModItems() {
		MdlParachutes.LOGGER.debug("Registering items for " + MdlParachutes.MOD_ID);
	}

	//public ParachuteTrinket(Settings settings) {
		//super(settings);
	//}

	 */
}
