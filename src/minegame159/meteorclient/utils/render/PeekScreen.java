/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.BetterTooltips;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1733;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_495;

public class PeekScreen
extends class_495 {
    private final class_2960 TEXTURE = new class_2960("textures/gui/container/shulker_box.png");
    private final class_1799[] contents;
    private final class_1799 storageBlock;

    public boolean method_25404(int n, int n2, int n3) {
        if (n == 256) {
            this.method_25419();
            return true;
        }
        return false;
    }

    public boolean method_25402(double d, double d2, int n) {
        BetterTooltips betterTooltips = Modules.get().get(BetterTooltips.class);
        if (n == 2 && this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960() && betterTooltips.middleClickOpen()) {
            return Utils.openContainer(this.field_2787.method_7677(), this.contents, false);
        }
        return false;
    }

    protected void method_2389(class_4587 class_45872, float f, int n, int n2) {
        Color color = Utils.getShulkerColor(this.storageBlock);
        RenderSystem.color4f((float)((float)color.r / 255.0f), (float)((float)color.g / 255.0f), (float)((float)color.b / 255.0f), (float)1.0f);
        this.field_22787.method_1531().method_22813(this.TEXTURE);
        int n3 = (this.field_22789 - this.field_2792) / 2;
        int n4 = (this.field_22790 - this.field_2779) / 2;
        this.method_25302(class_45872, n3, n4, 0, 0, this.field_2792, this.field_2779);
    }

    public PeekScreen(class_1799 class_17992, class_1799[] class_1799Array) {
        super(new class_1733(0, Utils.mc.field_1724.field_7514, (class_1263)new class_1277(class_1799Array)), Utils.mc.field_1724.field_7514, class_17992.method_7964());
        this.contents = class_1799Array;
        this.storageBlock = class_17992;
    }

    public boolean method_25406(double d, double d2, int n) {
        return false;
    }

    public boolean method_16803(int n, int n2, int n3) {
        if (n == 256) {
            this.method_25419();
            return true;
        }
        return false;
    }
}

