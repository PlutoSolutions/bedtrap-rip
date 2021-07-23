/*
 * Decompiled with CFR 0.151.
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
    private final class_2338.class_2339 blockPos = new class_2338.class_2339();

    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            return "Plains";
        }
        this.blockPos.method_10102(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318(), this.mc.field_1724.method_23321());
        class_2960 class_29602 = this.mc.field_1687.method_30349().method_30530(class_2378.field_25114).method_10221((Object)this.mc.field_1687.method_23753((class_2338)this.blockPos));
        if (class_29602 == null) {
            return "Unknown";
        }
        return Arrays.stream(class_29602.method_12832().split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public BiomeHud(HUD hUD) {
        super(hUD, "biome", "Displays the biome you are in.", "Biome: ");
    }
}

