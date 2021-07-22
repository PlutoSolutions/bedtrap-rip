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
    private static final /* synthetic */ PlayerListEntryFactory INSTANCE;

    public static class_2703.class_2705 create(GameProfile lllllllllllllllllIllllIIIIIIllII, int lllllllllllllllllIllllIIIIIIlIll, class_1934 lllllllllllllllllIllllIIIIIIlIlI, class_2561 lllllllllllllllllIllllIIIIIIlIIl) {
        return INSTANCE._create(lllllllllllllllllIllllIIIIIIllII, lllllllllllllllllIllllIIIIIIlIll, lllllllllllllllllIllllIIIIIIlIlI, lllllllllllllllllIllllIIIIIIlIIl);
    }

    static {
        INSTANCE = new PlayerListEntryFactory();
    }

    public PlayerListEntryFactory() {
        PlayerListEntryFactory lllllllllllllllllIllllIIIIIlIllI;
    }

    private class_2703.class_2705 _create(GameProfile lllllllllllllllllIlllIllllllllIl, int lllllllllllllllllIllllIIIIIIIIIl, class_1934 lllllllllllllllllIllllIIIIIIIIII, class_2561 lllllllllllllllllIlllIllllllllll) {
        PlayerListEntryFactory lllllllllllllllllIllllIIIIIIIIll;
        return new class_2703.class_2705((class_2703)lllllllllllllllllIllllIIIIIIIIll, lllllllllllllllllIlllIllllllllIl, lllllllllllllllllIllllIIIIIIIIIl, lllllllllllllllllIllllIIIIIIIIII, lllllllllllllllllIlllIllllllllll);
    }
}

