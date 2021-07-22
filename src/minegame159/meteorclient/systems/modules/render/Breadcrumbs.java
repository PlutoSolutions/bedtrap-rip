/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2874
 *  net.minecraft.class_310
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
import net.minecraft.class_310;

public class Breadcrumbs
extends Module {
    private final Setting<Double> sectionLength;
    private class_2874 lastDimension;
    private final Pool<Section> sectionPool;
    private final Setting<SettingColor> color;
    private final Queue<Section> sections;
    private final Setting<Integer> maxSections;
    private Section section;
    private final SettingGroup sgGeneral;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        Object object;
        if (this.lastDimension != this.mc.field_1687.method_8597()) {
            object = this.sections.iterator();
            while (object.hasNext()) {
                Section section = (Section)object.next();
                this.sectionPool.free(section);
            }
            this.sections.clear();
        }
        if (this.isFarEnough(this.section.x1, this.section.y1, this.section.z1)) {
            this.section.set2();
            if (this.sections.size() >= this.maxSections.get() && (object = this.sections.poll()) != null) {
                this.sectionPool.free((Section)object);
            }
            this.sections.add(this.section);
            this.section = this.sectionPool.get();
            this.section.set1();
        }
        this.lastDimension = this.mc.field_1687.method_8597();
    }

    static class_310 access$400(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    public Breadcrumbs() {
        super(Categories.Render, "breadcrumbs", "Displays a trail behind where you have walked.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.color = this.sgGeneral.add(new ColorSetting.Builder().name("color").description("The color of the Breadcrumbs trail.").defaultValue(new SettingColor(225, 25, 25)).build());
        this.maxSections = this.sgGeneral.add(new IntSetting.Builder().name("max-sections").description("The maximum number of sections.").defaultValue(1000).min(1).sliderMin(1).sliderMax(5000).build());
        this.sectionLength = this.sgGeneral.add(new DoubleSetting.Builder().name("section-length").description("The section length in blocks.").defaultValue(0.5).min(0.0).sliderMin(0.0).sliderMax(1.0).build());
        this.sectionPool = new Pool<Section>(this::lambda$new$0);
        this.sections = new ArrayDeque<Section>();
    }

    static Setting access$600(Breadcrumbs breadcrumbs) {
        return breadcrumbs.color;
    }

    private boolean isFarEnough(double d, double d2, double d3) {
        return Math.abs(this.mc.field_1724.method_23317() - d) >= this.sectionLength.get() || Math.abs(this.mc.field_1724.method_23318() - d2) >= this.sectionLength.get() || Math.abs(this.mc.field_1724.method_23321() - d3) >= this.sectionLength.get();
    }

    static class_310 access$300(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    static class_310 access$500(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    static class_310 access$200(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    static class_310 access$100(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    static class_310 access$000(Breadcrumbs breadcrumbs) {
        return breadcrumbs.mc;
    }

    @Override
    public void onActivate() {
        this.section = this.sectionPool.get();
        this.section.set1();
        this.lastDimension = this.mc.field_1687.method_8597();
    }

    private Section lambda$new$0() {
        return new Section(this, null);
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        for (Section section : this.sections) {
            section.render();
        }
    }

    @Override
    public void onDeactivate() {
        for (Section section : this.sections) {
            this.sectionPool.free(section);
        }
        this.sections.clear();
    }

    private class Section {
        public float z2;
        public float y1;
        final Breadcrumbs this$0;
        public float x1;
        public float y2;
        public float x2;
        public float z1;

        private Section(Breadcrumbs breadcrumbs) {
            this.this$0 = breadcrumbs;
        }

        public void set1() {
            this.x1 = (float)Breadcrumbs.access$000((Breadcrumbs)this.this$0).field_1724.method_23317();
            this.y1 = (float)Breadcrumbs.access$100((Breadcrumbs)this.this$0).field_1724.method_23318();
            this.z1 = (float)Breadcrumbs.access$200((Breadcrumbs)this.this$0).field_1724.method_23321();
        }

        public void render() {
            Renderer.LINES.line(this.x1, this.y1, this.z1, this.x2, this.y2, this.z2, (Color)Breadcrumbs.access$600(this.this$0).get());
        }

        Section(Breadcrumbs breadcrumbs, 1 var2_2) {
            this(breadcrumbs);
        }

        public void set2() {
            this.x2 = (float)Breadcrumbs.access$300((Breadcrumbs)this.this$0).field_1724.method_23317();
            this.y2 = (float)Breadcrumbs.access$400((Breadcrumbs)this.this$0).field_1724.method_23318();
            this.z2 = (float)Breadcrumbs.access$500((Breadcrumbs)this.this$0).field_1724.method_23321();
        }
    }
}

