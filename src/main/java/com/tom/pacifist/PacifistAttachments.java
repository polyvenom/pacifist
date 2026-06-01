package com.tom.pacifist;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public final class PacifistAttachments {

    public static final AttachmentType<Long> NEXT_SIPHON_TIME = AttachmentRegistry.createPersistent(
            Identifier.fromNamespaceAndPath(Pacifist.MOD_ID, "next_siphon_time"),
            Codec.LONG
    );

    private PacifistAttachments() {}

    public static void registerAll() {}
}
