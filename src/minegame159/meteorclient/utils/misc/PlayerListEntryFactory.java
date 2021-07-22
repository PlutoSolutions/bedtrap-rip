/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1934
 *  net.minecraft.class_2561
 *  net.minecraft.class_2703
 *  net.minecraft.class_2703$class_2705
 */
package minegame159.meteorclient.utils.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_1934;
import net.minecraft.class_2561;
import net.minecraft.class_2703;

public class PlayerListEntryFactory
extends class_2703 {
    private static final PlayerListEntryFactory INSTANCE = new PlayerListEntryFactory();

    public static class_2703.class_2705 create(GameProfile gameProfile, int n, class_1934 class_19342, class_2561 class_25612) {
        return INSTANCE._create(gameProfile, n, class_19342, class_25612);
    }

    private class_2703.class_2705 _create(GameProfile gameProfile, int n, class_1934 class_19342, class_2561 class_25612) {
        return new class_2703.class_2705((class_2703)this, gameProfile, n, class_19342, class_25612);
    }
}

