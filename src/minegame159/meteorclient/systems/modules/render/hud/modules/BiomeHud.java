/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  org.apache.commons.lang3.StringUtils
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.util.Arrays;
import java.util.stream.Collectors;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import org.apache.commons.lang3.StringUtils;

public class BiomeHud
extends DoubleTextHudElement {
    private final /* synthetic */ class_2338.class_2339 blockPos;

    @Override
    protected String getRight() {
        BiomeHud lllllllllllllllllllIIllIIlIIlIlI;
        if (lllllllllllllllllllIIllIIlIIlIlI.isInEditor()) {
            return "Plains";
        }
        lllllllllllllllllllIIllIIlIIlIlI.blockPos.method_10102(lllllllllllllllllllIIllIIlIIlIlI.mc.field_1724.method_23317(), lllllllllllllllllllIIllIIlIIlIlI.mc.field_1724.method_23318(), lllllllllllllllllllIIllIIlIIlIlI.mc.field_1724.method_23321());
        class_2960 lllllllllllllllllllIIllIIlIIlIIl = lllllllllllllllllllIIllIIlIIlIlI.mc.field_1687.method_30349().method_30530(class_2378.field_25114).method_10221((Object)lllllllllllllllllllIIllIIlIIlIlI.mc.field_1687.method_23753((class_2338)lllllllllllllllllllIIllIIlIIlIlI.blockPos));
        if (lllllllllllllllllllIIllIIlIIlIIl == null) {
            return "Unknown";
        }
        return Arrays.stream(lllllllllllllllllllIIllIIlIIlIIl.method_12832().split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public BiomeHud(HUD lllllllllllllllllllIIllIlllIIIll) {
        super(lllllllllllllllllllIIllIlllIIIll, "biome", "Displays the biome you are in.", "Biome: ");
        BiomeHud lllllllllllllllllllIIllIlllIIlII;
        lllllllllllllllllllIIllIlllIIlII.blockPos = new class_2338.class_2339();
    }
}

