package net.lemon.mdlpar.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.lemon.mdlpar.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class ParachuteHandler implements Trinket {
	public static boolean falling = false;

	public static void activate() {
		falling = true;
	}

	public static void reset(@Nullable PlayerEntity player) {
		falling = false;

		if (player != null) {
			player.setNoGravity(false);
			player.setMovementSpeed(0.1F);
		}
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if (!entity.getWorld().isClient) {
			PlayerEntity player = (PlayerEntity) entity;
			TrinketComponent comp = TrinketsApi.getTrinketComponent(player).get();

			if (falling) {
				//if (!player.hasNoGravity()) {
					//player.setNoGravity(true); // Remove all gravity
				//}

				//player.updateVelocity(5, new Vec3d(0, -100000, 0));
				//player.setVelocity(0, 100000, 0); // Manually set velocity
				//player.setVelocityClient(0, 100000, 0); // Manually set velocity
				//player.setVelocity(player.getVelocity().add(0,10000,0));
				//double posY = player.getPos().y;
				//player.setPosition(player.getPos().add(0,-1,0));
				player.setMovementSpeed(1F);

				//System.out.println("Velocity: " + player.getVelocity().y);

				if (player.isOnGround()) {
					int curCount = stack.getCount();
					stack.setCount((curCount - 1));
					reset(player);

				} else if (!comp.isEquipped(ModItems.PARACHUTE)) {
					reset(player);
				}
			}
		}
	}

	@Override
	public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if (!entity.getWorld().isClient) {
			PlayerEntity player = (PlayerEntity) entity;
			reset(player);
		}
	}
}