package net.lemon.mdlpar.event;

import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.lemon.mdlpar.networking.ModPackets;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
	public static final String KEY_CATEGORY_MDLP = "key.category.mdlpar.mdlp";
	public static final String KEY_ACTIVATE_PARACHUTE = "key.mdlpar.activate_parachute";
	public static KeyBind activationKey;

	public static void registerKeyInputs() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (activationKey.wasPressed()) {
				ClientPlayNetworking.send(ModPackets.PSHANDLER_ID, PacketByteBufs.create());
			}
		});
	}

	public static void register() {

		activationKey = KeyBindingHelper.registerKeyBinding(new KeyBind(
				KEY_ACTIVATE_PARACHUTE,
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				KEY_CATEGORY_MDLP
		));

		registerKeyInputs();
	}
}
