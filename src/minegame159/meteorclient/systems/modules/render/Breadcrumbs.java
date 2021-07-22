/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2874
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayDeque;
import java.util.Queue;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2874;

public class Breadcrumbs
extends Module {
    private final /* synthetic */ Setting<Double> sectionLength;
    private /* synthetic */ class_2874 lastDimension;
    private final /* synthetic */ Pool<Section> sectionPool;
    private final /* synthetic */ Setting<SettingColor> color;
    private final /* synthetic */ Queue<Section> sections;
    private final /* synthetic */ Setting<Integer> maxSections;
    private /* synthetic */ Section section;
    private final /* synthetic */ SettingGroup sgGeneral;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIIllIIlIlllIlll) {
        Breadcrumbs lllllllllllllllllIIllIIlIlllIllI;
        if (lllllllllllllllllIIllIIlIlllIllI.lastDimension != lllllllllllllllllIIllIIlIlllIllI.mc.field_1687.method_8597()) {
            for (Section lllllllllllllllllIIllIIlIllllIlI : lllllllllllllllllIIllIIlIlllIllI.sections) {
                lllllllllllllllllIIllIIlIlllIllI.sectionPool.free(lllllllllllllllllIIllIIlIllllIlI);
            }
            lllllllllllllllllIIllIIlIlllIllI.sections.clear();
        }
        if (lllllllllllllllllIIllIIlIlllIllI.isFarEnough(lllllllllllllllllIIllIIlIlllIllI.section.x1, lllllllllllllllllIIllIIlIlllIllI.section.y1, lllllllllllllllllIIllIIlIlllIllI.section.z1)) {
            Section lllllllllllllllllIIllIIlIllllIIl;
            lllllllllllllllllIIllIIlIlllIllI.section.set2();
            if (lllllllllllllllllIIllIIlIlllIllI.sections.size() >= lllllllllllllllllIIllIIlIlllIllI.maxSections.get() && (lllllllllllllllllIIllIIlIllllIIl = lllllllllllllllllIIllIIlIlllIllI.sections.poll()) != null) {
                lllllllllllllllllIIllIIlIlllIllI.sectionPool.free(lllllllllllllllllIIllIIlIllllIIl);
            }
            lllllllllllllllllIIllIIlIlllIllI.sections.add(lllllllllllllllllIIllIIlIlllIllI.section);
            lllllllllllllllllIIllIIlIlllIllI.section = lllllllllllllllllIIllIIlIlllIllI.sectionPool.get();
            lllllllllllllllllIIllIIlIlllIllI.section.set1();
        }
        lllllllllllllllllIIllIIlIlllIllI.lastDimension = lllllllllllllllllIIllIIlIlllIllI.mc.field_1687.method_8597();
    }

    public Breadcrumbs() {
        super(Categories.Render, "breadcrumbs", "Displays a trail behind where you have walked.");
        Breadcrumbs lllllllllllllllllIIllIIllIIIlIlI;
        lllllllllllllllllIIllIIllIIIlIlI.sgGeneral = lllllllllllllllllIIllIIllIIIlIlI.settings.getDefaultGroup();
        lllllllllllllllllIIllIIllIIIlIlI.color = lllllllllllllllllIIllIIllIIIlIlI.sgGeneral.add(new ColorSetting.Builder().name("color").description("The color of the Breadcrumbs trail.").defaultValue(new SettingColor(225, 25, 25)).build());
        lllllllllllllllllIIllIIllIIIlIlI.maxSections = lllllllllllllllllIIllIIllIIIlIlI.sgGeneral.add(new IntSetting.Builder().name("max-sections").description("The maximum number of sections.").defaultValue(1000).min(1).sliderMin(1).sliderMax(5000).build());
        lllllllllllllllllIIllIIllIIIlIlI.sectionLength = lllllllllllllllllIIllIIllIIIlIlI.sgGeneral.add(new DoubleSetting.Builder().name("section-length").description("The section length in blocks.").defaultValue(0.5).min(0.0).sliderMin(0.0).sliderMax(1.0).build());
        lllllllllllllllllIIllIIllIIIlIlI.sectionPool = new Pool<Section>(() -> {
            Breadcrumbs lllllllllllllllllIIllIIlIlIlllII;
            return lllllllllllllllllIIllIIlIlIlllII.new Section();
        });
        lllllllllllllllllIIllIIllIIIlIlI.sections = new ArrayDeque<Section>();
    }

    private boolean isFarEnough(double lllllllllllllllllIIllIIlIllIIlIl, double lllllllllllllllllIIllIIlIllIIlII, double lllllllllllllllllIIllIIlIllIIIll) {
        Breadcrumbs lllllllllllllllllIIllIIlIllIIllI;
        return Math.abs(lllllllllllllllllIIllIIlIllIIllI.mc.field_1724.method_23317() - lllllllllllllllllIIllIIlIllIIlIl) >= lllllllllllllllllIIllIIlIllIIllI.sectionLength.get() || Math.abs(lllllllllllllllllIIllIIlIllIIllI.mc.field_1724.method_23318() - lllllllllllllllllIIllIIlIllIIlII) >= lllllllllllllllllIIllIIlIllIIllI.sectionLength.get() || Math.abs(lllllllllllllllllIIllIIlIllIIllI.mc.field_1724.method_23321() - lllllllllllllllllIIllIIlIllIIIll) >= lllllllllllllllllIIllIIlIllIIllI.sectionLength.get();
    }

    @Override
    public void onActivate() {
        Breadcrumbs lllllllllllllllllIIllIIllIIIIllI;
        lllllllllllllllllIIllIIllIIIIllI.section = lllllllllllllllllIIllIIllIIIIllI.sectionPool.get();
        lllllllllllllllllIIllIIllIIIIllI.section.set1();
        lllllllllllllllllIIllIIllIIIIllI.lastDimension = lllllllllllllllllIIllIIllIIIIllI.mc.field_1687.method_8597();
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIIllIIlIllIlllI) {
        Breadcrumbs lllllllllllllllllIIllIIlIllIllll;
        for (Section lllllllllllllllllIIllIIlIlllIIII : lllllllllllllllllIIllIIlIllIllll.sections) {
            lllllllllllllllllIIllIIlIlllIIII.render();
        }
    }

    @Override
    public void onDeactivate() {
        Breadcrumbs lllllllllllllllllIIllIIllIIIIIII;
        for (Section lllllllllllllllllIIllIIllIIIIIlI : lllllllllllllllllIIllIIllIIIIIII.sections) {
            lllllllllllllllllIIllIIllIIIIIII.sectionPool.free(lllllllllllllllllIIllIIllIIIIIlI);
        }
        lllllllllllllllllIIllIIllIIIIIII.sections.clear();
    }

    private class Section {
        public /* synthetic */ float z2;
        public /* synthetic */ float y1;
        public /* synthetic */ float x1;
        public /* synthetic */ float y2;
        public /* synthetic */ float x2;
        public /* synthetic */ float z1;

        private Section() {
            Section llllllllllllllllIlllIIlIlIIllIll;
        }

        public void set1() {
            Section llllllllllllllllIlllIIlIlIIlIlll;
            llllllllllllllllIlllIIlIlIIlIlll.x1 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlll.Breadcrumbs.this).mc.field_1724.method_23317();
            llllllllllllllllIlllIIlIlIIlIlll.y1 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlll.Breadcrumbs.this).mc.field_1724.method_23318();
            llllllllllllllllIlllIIlIlIIlIlll.z1 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlll.Breadcrumbs.this).mc.field_1724.method_23321();
        }

        public void render() {
            Section llllllllllllllllIlllIIlIlIIlIIlI;
            Renderer.LINES.line(llllllllllllllllIlllIIlIlIIlIIlI.x1, llllllllllllllllIlllIIlIlIIlIIlI.y1, llllllllllllllllIlllIIlIlIIlIIlI.z1, llllllllllllllllIlllIIlIlIIlIIlI.x2, llllllllllllllllIlllIIlIlIIlIIlI.y2, llllllllllllllllIlllIIlIlIIlIIlI.z2, (Color)llllllllllllllllIlllIIlIlIIlIIlI.Breadcrumbs.this.color.get());
        }

        public void set2() {
            Section llllllllllllllllIlllIIlIlIIlIlIl;
            llllllllllllllllIlllIIlIlIIlIlIl.x2 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlIl.Breadcrumbs.this).mc.field_1724.method_23317();
            llllllllllllllllIlllIIlIlIIlIlIl.y2 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlIl.Breadcrumbs.this).mc.field_1724.method_23318();
            llllllllllllllllIlllIIlIlIIlIlIl.z2 = (float)((Breadcrumbs)llllllllllllllllIlllIIlIlIIlIlIl.Breadcrumbs.this).mc.field_1724.method_23321();
        }
    }
}

