/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_1944
 *  net.minecraft.class_2189
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2382
 *  net.minecraft.class_2482
 *  net.minecraft.class_2510
 *  net.minecraft.class_259
 *  net.minecraft.class_2680
 *  net.minecraft.class_2760
 *  net.minecraft.class_2769
 *  net.minecraft.class_2771
 *  net.minecraft.class_290
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockIterator;
import net.minecraft.class_1922;
import net.minecraft.class_1944;
import net.minecraft.class_2189;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2482;
import net.minecraft.class_2510;
import net.minecraft.class_259;
import net.minecraft.class_2680;
import net.minecraft.class_2760;
import net.minecraft.class_2769;
import net.minecraft.class_2771;
import net.minecraft.class_290;

public class LightOverlay
extends Module {
    private final /* synthetic */ Setting<Integer> horizontalRange;
    private final /* synthetic */ MeshBuilder mb;
    private final /* synthetic */ Pool<Cross> crossPool;
    private final /* synthetic */ SettingGroup sgColors;
    private final /* synthetic */ Setting<Integer> verticalRange;
    private final /* synthetic */ class_2338.class_2339 bp;
    private final /* synthetic */ List<Cross> crosses;
    private final /* synthetic */ Setting<SettingColor> potentialColor;
    private final /* synthetic */ Setting<SettingColor> color;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> seeThroughBlocks;

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIIllIllllIIIIll) {
        LightOverlay lllllllllllllllllIIllIllllIIIllI;
        if (lllllllllllllllllIIllIllllIIIllI.crosses.isEmpty()) {
            return;
        }
        lllllllllllllllllIIllIllllIIIllI.mb.depthTest = lllllllllllllllllIIllIllllIIIllI.seeThroughBlocks.get() == false;
        lllllllllllllllllIIllIllllIIIllI.mb.begin(lllllllllllllllllIIllIllllIIIIll, DrawMode.Lines, class_290.field_1576);
        for (Cross lllllllllllllllllIIllIllllIIIlll : lllllllllllllllllIIllIllllIIIllI.crosses) {
            lllllllllllllllllIIllIllllIIIlll.render();
        }
        lllllllllllllllllIIllIllllIIIllI.mb.end();
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIIllIllllIIllll) {
        LightOverlay lllllllllllllllllIIllIllllIlIIII;
        for (Cross lllllllllllllllllIIllIllllIlIIIl : lllllllllllllllllIIllIllllIlIIII.crosses) {
            lllllllllllllllllIIllIllllIlIIII.crossPool.free(lllllllllllllllllIIllIllllIlIIIl);
        }
        lllllllllllllllllIIllIllllIlIIII.crosses.clear();
        BlockIterator.register(lllllllllllllllllIIllIllllIlIIII.horizontalRange.get(), lllllllllllllllllIIllIllllIlIIII.verticalRange.get(), (lllllllllllllllllIIllIlllIlIllll, lllllllllllllllllIIllIlllIlIlIll) -> {
            LightOverlay lllllllllllllllllIIllIlllIllIIII;
            switch (lllllllllllllllllIIllIlllIllIIII.validSpawn((class_2338)lllllllllllllllllIIllIlllIlIllll, (class_2680)lllllllllllllllllIIllIlllIlIlIll)) {
                case Never: {
                    break;
                }
                case Potential: {
                    lllllllllllllllllIIllIlllIllIIII.crosses.add(lllllllllllllllllIIllIlllIllIIII.crossPool.get().set((class_2338)lllllllllllllllllIIllIlllIlIllll, true));
                    break;
                }
                case Always: {
                    lllllllllllllllllIIllIlllIllIIII.crosses.add(lllllllllllllllllIIllIlllIllIIII.crossPool.get().set((class_2338)lllllllllllllllllIIllIlllIlIllll, false));
                }
            }
        });
    }

    public LightOverlay() {
        super(Categories.Render, "light-overlay", "Shows blocks where mobs can spawn.");
        LightOverlay lllllllllllllllllIIllIllllIlIllI;
        lllllllllllllllllIIllIllllIlIllI.sgGeneral = lllllllllllllllllIIllIllllIlIllI.settings.getDefaultGroup();
        lllllllllllllllllIIllIllllIlIllI.sgColors = lllllllllllllllllIIllIllllIlIllI.settings.createGroup("Colors");
        lllllllllllllllllIIllIllllIlIllI.horizontalRange = lllllllllllllllllIIllIllllIlIllI.sgGeneral.add(new IntSetting.Builder().name("horizontal-range").description("Horizontal range in blocks.").defaultValue(8).min(0).build());
        lllllllllllllllllIIllIllllIlIllI.verticalRange = lllllllllllllllllIIllIllllIlIllI.sgGeneral.add(new IntSetting.Builder().name("vertical-range").description("Vertical range in blocks.").defaultValue(4).min(0).build());
        lllllllllllllllllIIllIllllIlIllI.seeThroughBlocks = lllllllllllllllllIIllIllllIlIllI.sgGeneral.add(new BoolSetting.Builder().name("see-through-blocks").description("Allows you to see the lines through blocks.").defaultValue(false).build());
        lllllllllllllllllIIllIllllIlIllI.color = lllllllllllllllllIIllIllllIlIllI.sgColors.add(new ColorSetting.Builder().name("color").description("Color of places where mobs can currently spawn.").defaultValue(new SettingColor(225, 25, 25)).build());
        lllllllllllllllllIIllIllllIlIllI.potentialColor = lllllllllllllllllIIllIllllIlIllI.sgColors.add(new ColorSetting.Builder().name("potential-color").description("Color of places where mobs can potentially spawn (eg at night).").defaultValue(new SettingColor(225, 225, 25)).build());
        lllllllllllllllllIIllIllllIlIllI.crossPool = new Pool<Cross>(() -> {
            LightOverlay lllllllllllllllllIIllIlllIlIlIII;
            return lllllllllllllllllIIllIlllIlIlIII.new Cross();
        });
        lllllllllllllllllIIllIllllIlIllI.crosses = new ArrayList<Cross>();
        lllllllllllllllllIIllIllllIlIllI.bp = new class_2338.class_2339();
        lllllllllllllllllIIllIllllIlIllI.mb = new MeshBuilder();
    }

    private boolean topSurface(class_2680 lllllllllllllllllIIllIlllIllIlII) {
        if (lllllllllllllllllIIllIlllIllIlII.method_26204() instanceof class_2482 && lllllllllllllllllIIllIlllIllIlII.method_11654((class_2769)class_2482.field_11501) == class_2771.field_12679) {
            return true;
        }
        return lllllllllllllllllIIllIlllIllIlII.method_26204() instanceof class_2510 && lllllllllllllllllIIllIlllIllIlII.method_11654((class_2769)class_2510.field_11572) == class_2760.field_12619;
    }

    private Spawn validSpawn(class_2338 lllllllllllllllllIIllIlllIlllIIl, class_2680 lllllllllllllllllIIllIlllIlllIll) {
        LightOverlay lllllllllllllllllIIllIlllIllllIl;
        if (!(lllllllllllllllllIIllIlllIlllIll.method_26204() instanceof class_2189)) {
            return Spawn.Never;
        }
        lllllllllllllllllIIllIlllIllllIl.bp.method_10101((class_2382)lllllllllllllllllIIllIlllIlllIIl).method_10100(0, -1, 0);
        if (!lllllllllllllllllIIllIlllIllllIl.topSurface(lllllllllllllllllIIllIlllIllllIl.mc.field_1687.method_8320((class_2338)lllllllllllllllllIIllIlllIllllIl.bp))) {
            if (lllllllllllllllllIIllIlllIllllIl.mc.field_1687.method_8320((class_2338)lllllllllllllllllIIllIlllIllllIl.bp).method_26220((class_1922)lllllllllllllllllIIllIlllIllllIl.mc.field_1687, (class_2338)lllllllllllllllllIIllIlllIllllIl.bp) != class_259.method_1077()) {
                return Spawn.Never;
            }
            if (lllllllllllllllllIIllIlllIllllIl.mc.field_1687.method_8320((class_2338)lllllllllllllllllIIllIlllIllllIl.bp).method_26167((class_1922)lllllllllllllllllIIllIlllIllllIl.mc.field_1687, (class_2338)lllllllllllllllllIIllIlllIllllIl.bp)) {
                return Spawn.Never;
            }
        }
        if (lllllllllllllllllIIllIlllIllllIl.mc.field_1687.method_22346(lllllllllllllllllIIllIlllIlllIIl, 0) <= 7) {
            return Spawn.Potential;
        }
        if (lllllllllllllllllIIllIlllIllllIl.mc.field_1687.method_8314(class_1944.field_9282, lllllllllllllllllIIllIlllIlllIIl) <= 7) {
            return Spawn.Always;
        }
        return Spawn.Never;
    }

    private class Cross {
        private /* synthetic */ double x;
        private /* synthetic */ boolean potential;
        private /* synthetic */ double y;
        private /* synthetic */ double z;

        public Cross set(class_2338 llllllllllllllllIlIlIllIllIIlllI, boolean llllllllllllllllIlIlIllIllIIllIl) {
            Cross llllllllllllllllIlIlIllIllIIllII;
            llllllllllllllllIlIlIllIllIIllII.x = llllllllllllllllIlIlIllIllIIlllI.method_10263();
            llllllllllllllllIlIlIllIllIIllII.y = (double)llllllllllllllllIlIlIllIllIIlllI.method_10264() + 0.0075;
            llllllllllllllllIlIlIllIllIIllII.z = llllllllllllllllIlIlIllIllIIlllI.method_10260();
            llllllllllllllllIlIlIllIllIIllII.potential = llllllllllllllllIlIlIllIllIIllIl;
            return llllllllllllllllIlIlIllIllIIllII;
        }

        public void render() {
            Cross llllllllllllllllIlIlIllIllIIIlll;
            Color llllllllllllllllIlIlIllIllIIIllI = llllllllllllllllIlIlIllIllIIIlll.potential ? (Color)llllllllllllllllIlIlIllIllIIIlll.LightOverlay.this.potentialColor.get() : (Color)llllllllllllllllIlIlIllIllIIIlll.LightOverlay.this.color.get();
            llllllllllllllllIlIlIllIllIIIlll.LightOverlay.this.mb.line(llllllllllllllllIlIlIllIllIIIlll.x, llllllllllllllllIlIlIllIllIIIlll.y, llllllllllllllllIlIlIllIllIIIlll.z, llllllllllllllllIlIlIllIllIIIlll.x + 1.0, llllllllllllllllIlIlIllIllIIIlll.y, llllllllllllllllIlIlIllIllIIIlll.z + 1.0, llllllllllllllllIlIlIllIllIIIllI);
            llllllllllllllllIlIlIllIllIIIlll.LightOverlay.this.mb.line(llllllllllllllllIlIlIllIllIIIlll.x + 1.0, llllllllllllllllIlIlIllIllIIIlll.y, llllllllllllllllIlIlIllIllIIIlll.z, llllllllllllllllIlIlIllIllIIIlll.x, llllllllllllllllIlIlIllIllIIIlll.y, llllllllllllllllIlIlIllIllIIIlll.z + 1.0, llllllllllllllllIlIlIllIllIIIllI);
        }

        private Cross() {
            Cross llllllllllllllllIlIlIllIllIlIlIl;
        }
    }

    public static enum Spawn {
        Never,
        Potential,
        Always;


        private Spawn() {
            Spawn lllllllllllllllllllIlllIIlIllIll;
        }
    }
}

