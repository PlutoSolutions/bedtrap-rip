/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1263
 *  net.minecraft.class_1277
 *  net.minecraft.class_1733
 *  net.minecraft.class_1799
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_495
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
    private final /* synthetic */ class_2960 TEXTURE;
    private final /* synthetic */ class_1799[] contents;
    private final /* synthetic */ class_1799 storageBlock;

    public boolean method_25404(int llllllIIl, int llllllIII, int lllllIlll) {
        if (llllllIIl == 256) {
            PeekScreen llllllIlI;
            llllllIlI.method_25419();
            return true;
        }
        return false;
    }

    public boolean method_25402(double lIIIIIIlll, double lIIIIIIllI, int lIIIIIIlIl) {
        PeekScreen lIIIIIlIII;
        BetterTooltips lIIIIIIlII = Modules.get().get(BetterTooltips.class);
        if (lIIIIIIlIl == 2 && lIIIIIlIII.field_2787 != null && !lIIIIIlIII.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960() && lIIIIIIlII.middleClickOpen()) {
            return Utils.openContainer(lIIIIIlIII.field_2787.method_7677(), lIIIIIlIII.contents, false);
        }
        return false;
    }

    protected void method_2389(class_4587 lllIllllI, float llllIIlIl, int llllIIlII, int llllIIIll) {
        PeekScreen lllIlllll;
        Color llllIIIlI = Utils.getShulkerColor(lllIlllll.storageBlock);
        RenderSystem.color4f((float)((float)llllIIIlI.r / 255.0f), (float)((float)llllIIIlI.g / 255.0f), (float)((float)llllIIIlI.b / 255.0f), (float)1.0f);
        lllIlllll.field_22787.method_1531().method_22813(lllIlllll.TEXTURE);
        int llllIIIIl = (lllIlllll.field_22789 - lllIlllll.field_2792) / 2;
        int llllIIIII = (lllIlllll.field_22790 - lllIlllll.field_2779) / 2;
        lllIlllll.method_25302(lllIllllI, llllIIIIl, llllIIIII, 0, 0, lllIlllll.field_2792, lllIlllll.field_2779);
    }

    public PeekScreen(class_1799 lIIIIIllIl, class_1799[] lIIIIIllll) {
        super(new class_1733(0, Utils.mc.field_1724.field_7514, (class_1263)new class_1277(lIIIIIllll)), Utils.mc.field_1724.field_7514, lIIIIIllIl.method_7964());
        PeekScreen lIIIIlIIIl;
        lIIIIlIIIl.TEXTURE = new class_2960("textures/gui/container/shulker_box.png");
        lIIIIlIIIl.contents = lIIIIIllll;
        lIIIIlIIIl.storageBlock = lIIIIIllIl;
    }

    public boolean method_25406(double lllllllll, double llllllllI, int lllllllIl) {
        return false;
    }

    public boolean method_16803(int lllllIIIl, int lllllIIII, int llllIllll) {
        if (lllllIIIl == 256) {
            PeekScreen llllIlllI;
            llllIlllI.method_25419();
            return true;
        }
        return false;
    }
}

