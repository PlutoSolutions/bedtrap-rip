/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2791
 *  net.minecraft.class_2806
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2791;
import net.minecraft.class_2806;

public class VoidESP
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Integer> holeHeight;
    private final /* synthetic */ Setting<Boolean> netherRoof;
    private static final /* synthetic */ class_2350[] SIDES;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Pool<Void> voidHolePool;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Integer> horizontalRadius;
    private final /* synthetic */ Setting<Boolean> airOnly;
    private final /* synthetic */ List<Void> voidHoles;
    private final /* synthetic */ Setting<SettingColor> lineColor;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIIlIlIIlIIIlIII) {
        VoidESP lllllllllllllllllIIlIlIIlIIIlIIl;
        lllllllllllllllllIIlIlIIlIIIlIIl.voidHoles.clear();
        if (PlayerUtils.getDimension() == Dimension.End) {
            return;
        }
        int lllllllllllllllllIIlIlIIlIIIIlll = lllllllllllllllllIIlIlIIlIIIlIIl.mc.field_1724.method_24515().method_10263();
        int lllllllllllllllllIIlIlIIlIIIIllI = lllllllllllllllllIIlIlIIlIIIlIIl.mc.field_1724.method_24515().method_10260();
        int lllllllllllllllllIIlIlIIlIIIIlIl = lllllllllllllllllIIlIlIIlIIIlIIl.horizontalRadius.get();
        for (int lllllllllllllllllIIlIlIIlIIIlIlI = lllllllllllllllllIIlIlIIlIIIIlll - lllllllllllllllllIIlIlIIlIIIIlIl; lllllllllllllllllIIlIlIIlIIIlIlI <= lllllllllllllllllIIlIlIIlIIIIlll + lllllllllllllllllIIlIlIIlIIIIlIl; ++lllllllllllllllllIIlIlIIlIIIlIlI) {
            for (int lllllllllllllllllIIlIlIIlIIIlIll = lllllllllllllllllIIlIlIIlIIIIllI - lllllllllllllllllIIlIlIIlIIIIlIl; lllllllllllllllllIIlIlIIlIIIlIll <= lllllllllllllllllIIlIlIIlIIIIllI + lllllllllllllllllIIlIlIIlIIIIlIl; ++lllllllllllllllllIIlIlIIlIIIlIll) {
                lllllllllllllllllIIlIlIIlIIIlIIl.blockPos.method_10103(lllllllllllllllllIIlIlIIlIIIlIlI, 0, lllllllllllllllllIIlIlIIlIIIlIll);
                if (lllllllllllllllllIIlIlIIlIIIlIIl.isHole(lllllllllllllllllIIlIlIIlIIIlIIl.blockPos, false)) {
                    lllllllllllllllllIIlIlIIlIIIlIIl.voidHoles.add(lllllllllllllllllIIlIlIIlIIIlIIl.voidHolePool.get().set(lllllllllllllllllIIlIlIIlIIIlIIl.blockPos.method_10103(lllllllllllllllllIIlIlIIlIIIlIlI, 0, lllllllllllllllllIIlIlIIlIIIlIll), false));
                }
                if (!lllllllllllllllllIIlIlIIlIIIlIIl.netherRoof.get().booleanValue() || PlayerUtils.getDimension() != Dimension.Nether) continue;
                lllllllllllllllllIIlIlIIlIIIlIIl.blockPos.method_10103(lllllllllllllllllIIlIlIIlIIIlIlI, 127, lllllllllllllllllIIlIlIIlIIIlIll);
                if (!lllllllllllllllllIIlIlIIlIIIlIIl.isHole(lllllllllllllllllIIlIlIIlIIIlIIl.blockPos, true)) continue;
                lllllllllllllllllIIlIlIIlIIIlIIl.voidHoles.add(lllllllllllllllllIIlIlIIlIIIlIIl.voidHolePool.get().set(lllllllllllllllllIIlIlIIlIIIlIIl.blockPos.method_10103(lllllllllllllllllIIlIlIIlIIIlIlI, 127, lllllllllllllllllIIlIlIIlIIIlIll), true));
            }
        }
    }

    public VoidESP() {
        super(Categories.Render, "void-esp", "Renders holes in bedrock layers that lead to the void.");
        VoidESP lllllllllllllllllIIlIlIIlIlIlIlI;
        lllllllllllllllllIIlIlIIlIlIlIlI.sgGeneral = lllllllllllllllllIIlIlIIlIlIlIlI.settings.getDefaultGroup();
        lllllllllllllllllIIlIlIIlIlIlIlI.sgRender = lllllllllllllllllIIlIlIIlIlIlIlI.settings.createGroup("Render");
        lllllllllllllllllIIlIlIIlIlIlIlI.airOnly = lllllllllllllllllIIlIlIIlIlIlIlI.sgGeneral.add(new BoolSetting.Builder().name("air-only").description("Checks bedrock only for air blocks.").defaultValue(false).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.horizontalRadius = lllllllllllllllllIIlIlIIlIlIlIlI.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(64).min(0).sliderMax(256).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.holeHeight = lllllllllllllllllIIlIlIIlIlIlIlI.sgGeneral.add(new IntSetting.Builder().name("hole-height").description("The minimum hole height to be rendered.").defaultValue(1).min(1).sliderMax(5).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.netherRoof = lllllllllllllllllIIlIlIIlIlIlIlI.sgGeneral.add(new BoolSetting.Builder().name("nether-roof").description("Check for holes in nether roof.").defaultValue(true).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.shapeMode = lllllllllllllllllIIlIlIIlIlIlIlI.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.sideColor = lllllllllllllllllIIlIlIIlIlIlIlI.sgRender.add(new ColorSetting.Builder().name("fill-color").description("The color that fills holes in the void.").defaultValue(new SettingColor(225, 25, 25, 50)).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.lineColor = lllllllllllllllllIIlIlIIlIlIlIlI.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color to draw lines of holes to the void.").defaultValue(new SettingColor(225, 25, 255)).build());
        lllllllllllllllllIIlIlIIlIlIlIlI.blockPos = new class_2338.class_2339();
        lllllllllllllllllIIlIlIIlIlIlIlI.voidHolePool = new Pool<Void>(() -> {
            VoidESP lllllllllllllllllIIlIlIIIlllIIll;
            return lllllllllllllllllIIlIlIIIlllIIll.new Void();
        });
        lllllllllllllllllIIlIlIIlIlIlIlI.voidHoles = new ArrayList<Void>();
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIIlIlIIIllllIIl) {
        VoidESP lllllllllllllllllIIlIlIIIllllIII;
        for (Void lllllllllllllllllIIlIlIIIllllIll : lllllllllllllllllIIlIlIIIllllIII.voidHoles) {
            lllllllllllllllllIIlIlIIIllllIll.render();
        }
    }

    private boolean isHole(class_2338.class_2339 lllllllllllllllllIIlIlIIlIIlIlII, boolean lllllllllllllllllIIlIlIIlIIlIIll) {
        VoidESP lllllllllllllllllIIlIlIIlIIllIII;
        for (int lllllllllllllllllIIlIlIIlIIllIIl = 0; lllllllllllllllllIIlIlIIlIIllIIl < lllllllllllllllllIIlIlIIlIIllIII.holeHeight.get(); ++lllllllllllllllllIIlIlIIlIIllIIl) {
            lllllllllllllllllIIlIlIIlIIlIlII.method_10099(lllllllllllllllllIIlIlIIlIIlIIll ? 127 - lllllllllllllllllIIlIlIIlIIllIIl : 0);
            if (!lllllllllllllllllIIlIlIIlIIllIII.isBlockWrong((class_2338)lllllllllllllllllIIlIlIIlIIlIlII)) continue;
            return false;
        }
        return true;
    }

    private boolean isBlockWrong(class_2338 lllllllllllllllllIIlIlIIlIlIIIII) {
        VoidESP lllllllllllllllllIIlIlIIlIlIIIIl;
        class_2791 lllllllllllllllllIIlIlIIlIlIIIll = lllllllllllllllllIIlIlIIlIlIIIIl.mc.field_1687.method_8402(lllllllllllllllllIIlIlIIlIlIIIII.method_10263() >> 4, lllllllllllllllllIIlIlIIlIlIIIII.method_10260() >> 4, class_2806.field_12803, false);
        if (lllllllllllllllllIIlIlIIlIlIIIll == null) {
            return true;
        }
        class_2248 lllllllllllllllllIIlIlIIlIlIIIlI = lllllllllllllllllIIlIlIIlIlIIIll.method_8320(lllllllllllllllllIIlIlIIlIlIIIII).method_26204();
        if (lllllllllllllllllIIlIlIIlIlIIIIl.airOnly.get().booleanValue()) {
            return lllllllllllllllllIIlIlIIlIlIIIlI != class_2246.field_10124;
        }
        return lllllllllllllllllIIlIlIIlIlIIIlI == class_2246.field_9987;
    }

    static {
        SIDES = new class_2350[]{class_2350.field_11034, class_2350.field_11043, class_2350.field_11035, class_2350.field_11039};
    }

    private class Void {
        private /* synthetic */ int x;
        private /* synthetic */ int excludeDir;
        private /* synthetic */ int z;
        private /* synthetic */ int y;

        public void render() {
            Void lllllllllllllllllllllllIIIlIIIIl;
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllllllllIIIlIIIIl.x, lllllllllllllllllllllllIIIlIIIIl.y, lllllllllllllllllllllllIIIlIIIIl.z, 1.0, (Color)lllllllllllllllllllllllIIIlIIIIl.VoidESP.this.sideColor.get(), (Color)lllllllllllllllllllllllIIIlIIIIl.VoidESP.this.lineColor.get(), (ShapeMode)((Object)lllllllllllllllllllllllIIIlIIIIl.VoidESP.this.shapeMode.get()), lllllllllllllllllllllllIIIlIIIIl.excludeDir);
        }

        public Void set(class_2338.class_2339 lllllllllllllllllllllllIIIlIlIII, boolean lllllllllllllllllllllllIIIlIIlll) {
            Void lllllllllllllllllllllllIIIlIllII;
            lllllllllllllllllllllllIIIlIllII.x = lllllllllllllllllllllllIIIlIlIII.method_10263();
            lllllllllllllllllllllllIIIlIllII.y = lllllllllllllllllllllllIIIlIlIII.method_10264();
            lllllllllllllllllllllllIIIlIllII.z = lllllllllllllllllllllllIIIlIlIII.method_10260();
            lllllllllllllllllllllllIIIlIllII.excludeDir = 0;
            for (class_2350 lllllllllllllllllllllllIIIlIllIl : SIDES) {
                lllllllllllllllllllllllIIIlIlIII.method_10103(lllllllllllllllllllllllIIIlIllII.x + lllllllllllllllllllllllIIIlIllIl.method_10148(), lllllllllllllllllllllllIIIlIllII.y, lllllllllllllllllllllllIIIlIllII.z + lllllllllllllllllllllllIIIlIllIl.method_10165());
                if (!lllllllllllllllllllllllIIIlIllII.VoidESP.this.isHole(lllllllllllllllllllllllIIIlIlIII, lllllllllllllllllllllllIIIlIIlll)) continue;
                lllllllllllllllllllllllIIIlIllII.excludeDir |= Dir.get(lllllllllllllllllllllllIIIlIllIl);
            }
            return lllllllllllllllllllllllIIIlIllII;
        }

        private Void() {
            Void lllllllllllllllllllllllIIIllIlll;
        }
    }
}

