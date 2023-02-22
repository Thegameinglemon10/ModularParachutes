package net.lemon.mdlpar;

import net.lemon.mdlpar.event.KeyInputHandler;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MdlParachutesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyInputHandler.register();
	}
}
