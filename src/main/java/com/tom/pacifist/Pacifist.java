package com.tom.pacifist;

import com.tom.pacifist.interactions.BlazeSpawnerHandler;
import com.tom.pacifist.interactions.CryingObsidianHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class Pacifist implements ModInitializer {

    public static final String MOD_ID = "pacifist";

    @Override
    public void onInitialize() {
        PacifistAttachments.registerAll();

        UseBlockCallback.EVENT.register(BlazeSpawnerHandler::onUseBlock);
        UseBlockCallback.EVENT.register(CryingObsidianHandler::onUseBlock);

        LootInjections.register();
    }
}
