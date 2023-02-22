package net.lemon.mdlpar.networking.packet;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lemon.mdlpar.MdlParachutes;
import net.lemon.mdlpar.item.ModItems;
import net.lemon.mdlpar.trinkets.ParachuteHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketByteBufs;

public class ParachuteServerC2SPacket {
	public static void activate(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		// Server
		TrinketComponent comp = TrinketsApi.getTrinketComponent(player).get();

		if (comp.isEquipped(ModItems.PARACHUTE)) {
			if (ParachuteHandler.falling) {
				ParachuteHandler.reset(null);
				player.sendMessage(Text.of("Closed Parachute!"),true);

			} else if (!player.isOnGround()) {
				ParachuteHandler.activate();
				player.sendMessage(Text.of("Activated Parachute!"),true);
			}
		}
	}
}
