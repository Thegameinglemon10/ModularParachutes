package net.lemon.mdlpar.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lemon.mdlpar.MdlParachutes;
import net.lemon.mdlpar.networking.packet.ParachuteServerC2SPacket;
import net.minecraft.util.Identifier;

public class ModPackets {
	public static final Identifier PSHANDLER_ID = new Identifier(MdlParachutes.MOD_ID, "parachute_server_handler");
	public static final Identifier SYNC_ID = new Identifier(MdlParachutes.MOD_ID, "sync");

	public static void registerC2SPackets() {
		ServerPlayNetworking.registerGlobalReceiver(PSHANDLER_ID, ParachuteServerC2SPacket::activate);
	}

	public static void registerS2CPackets() {

	}
}
