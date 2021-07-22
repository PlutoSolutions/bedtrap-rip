/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1771
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1787
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1823
 *  net.minecraft.class_1835
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_2350
 *  net.minecraft.class_2378
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_4537
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.ProjectileEntitySimulator;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1787;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1823;
import net.minecraft.class_1835;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2350;
import net.minecraft.class_2378;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_4537;

public class Trajectories
extends Module {
    private final Setting<SettingColor> lineColor;
    private final ProjectileEntitySimulator simulator;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> accurate;
    private final List<Path> paths;
    private final SettingGroup sgRender;
    private final Setting<List<class_1792>> items;
    private final Setting<SettingColor> sideColor;
    private final Setting<ShapeMode> shapeMode;
    private final Pool<Vec3> vec3s;

    private List<class_1792> getDefaultItems() {
        ArrayList<class_1792> arrayList = new ArrayList<class_1792>();
        for (class_1792 class_17922 : class_2378.field_11142) {
            if (!this.itemFilter(class_17922)) continue;
            arrayList.add(class_17922);
        }
        return arrayList;
    }

    static Setting access$600(Trajectories trajectories) {
        return trajectories.shapeMode;
    }

    static ProjectileEntitySimulator access$300(Trajectories trajectories) {
        return trajectories.simulator;
    }

    static Setting access$500(Trajectories trajectories) {
        return trajectories.sideColor;
    }

    static Setting access$400(Trajectories trajectories) {
        return trajectories.lineColor;
    }

    private void calculatePath(RenderEvent renderEvent) {
        for (Path path : this.paths) {
            path.clear();
        }
        class_1799 class_17992 = this.mc.field_1724.method_6047();
        if (class_17992 == null) {
            class_17992 = this.mc.field_1724.method_6079();
        }
        if (class_17992 == null) {
            return;
        }
        if (!this.items.get().contains((Object)class_17992.method_7909())) {
            return;
        }
        if (!this.simulator.set((class_1297)this.mc.field_1724, class_17992, 0.0, this.accurate.get(), renderEvent.tickDelta)) {
            return;
        }
        this.getEmptyPath().calculate();
        if (class_17992.method_7909() instanceof class_1764 && class_1890.method_8225((class_1887)class_1893.field_9108, (class_1799)class_17992) > 0) {
            if (!this.simulator.set((class_1297)this.mc.field_1724, class_17992, -10.0, this.accurate.get(), renderEvent.tickDelta)) {
                return;
            }
            this.getEmptyPath().calculate();
            if (!this.simulator.set((class_1297)this.mc.field_1724, class_17992, 10.0, this.accurate.get(), renderEvent.tickDelta)) {
                return;
            }
            this.getEmptyPath().calculate();
        }
    }

    private Path getEmptyPath() {
        for (Path path : this.paths) {
            if (!Path.access$000(path).isEmpty()) continue;
            return path;
        }
        Path path = new Path(this, null);
        this.paths.add(path);
        return path;
    }

    public Trajectories() {
        super(Categories.Render, "trajectories", "Predicts the trajectory of throwable items.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.items = this.sgGeneral.add(new ItemListSetting.Builder().name("items").description("Items to display trajectories for.").defaultValue(this.getDefaultItems()).filter(this::itemFilter).build());
        this.accurate = this.sgGeneral.add(new BoolSetting.Builder().name("accurate").description("Calculates accurate trajectories while moving.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 150, 0, 35)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 150, 0)).build());
        this.simulator = new ProjectileEntitySimulator();
        this.vec3s = new Pool<Vec3>(Vec3::new);
        this.paths = new ArrayList<Path>();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.calculatePath(renderEvent);
        for (Path path : this.paths) {
            path.render(renderEvent);
        }
    }

    static Pool access$200(Trajectories trajectories) {
        return trajectories.vec3s;
    }

    private boolean itemFilter(class_1792 class_17922) {
        return class_17922 instanceof class_1753 || class_17922 instanceof class_1764 || class_17922 instanceof class_1787 || class_17922 instanceof class_1835 || class_17922 instanceof class_1823 || class_17922 instanceof class_1771 || class_17922 instanceof class_1776 || class_17922 instanceof class_1779 || class_17922 instanceof class_4537;
    }

    private class Path {
        private boolean hitQuadHorizontal;
        private class_1297 entity;
        private boolean hitQuad;
        private double hitQuadZ1;
        private double hitQuadY1;
        private final List<Vec3> points;
        private double hitQuadY2;
        private double hitQuadX2;
        private double hitQuadZ2;
        final Trajectories this$0;
        private double hitQuadX1;

        static List access$000(Path path) {
            return path.points;
        }

        private Path(Trajectories trajectories) {
            this.this$0 = trajectories;
            this.points = new ArrayList<Vec3>();
        }

        Path(Trajectories trajectories, 1 var2_2) {
            this(trajectories);
        }

        public void calculate() {
            class_239 class_2392;
            this.addPoint();
            while (true) {
                if ((class_2392 = Trajectories.access$300(this.this$0).tick()) != null) break;
                this.addPoint();
            }
            this.processHitResult(class_2392);
        }

        private void processHitResult(class_239 class_2392) {
            if (class_2392.method_17783() == class_239.class_240.field_1332) {
                class_3965 class_39652 = (class_3965)class_2392;
                this.hitQuad = true;
                this.hitQuadX1 = class_39652.method_17784().field_1352;
                this.hitQuadY1 = class_39652.method_17784().field_1351;
                this.hitQuadZ1 = class_39652.method_17784().field_1350;
                this.hitQuadX2 = class_39652.method_17784().field_1352;
                this.hitQuadY2 = class_39652.method_17784().field_1351;
                this.hitQuadZ2 = class_39652.method_17784().field_1350;
                if (class_39652.method_17780() == class_2350.field_11036 || class_39652.method_17780() == class_2350.field_11033) {
                    this.hitQuadHorizontal = true;
                    this.hitQuadX1 -= 0.25;
                    this.hitQuadZ1 -= 0.25;
                    this.hitQuadX2 += 0.25;
                    this.hitQuadZ2 += 0.25;
                } else if (class_39652.method_17780() == class_2350.field_11043 || class_39652.method_17780() == class_2350.field_11035) {
                    this.hitQuadHorizontal = false;
                    this.hitQuadX1 -= 0.25;
                    this.hitQuadY1 -= 0.25;
                    this.hitQuadX2 += 0.25;
                    this.hitQuadY2 += 0.25;
                } else {
                    this.hitQuadHorizontal = false;
                    this.hitQuadZ1 -= 0.25;
                    this.hitQuadY1 -= 0.25;
                    this.hitQuadZ2 += 0.25;
                    this.hitQuadY2 += 0.25;
                }
                this.points.add(((Vec3)Trajectories.access$200(this.this$0).get()).set(class_2392.method_17784()));
            } else if (class_2392.method_17783() == class_239.class_240.field_1331) {
                this.entity = ((class_3966)class_2392).method_17782();
                this.points.add(((Vec3)Trajectories.access$200(this.this$0).get()).set(class_2392.method_17784()).add(0.0, this.entity.method_17682() / 2.0f, 0.0));
            }
        }

        public void clear() {
            for (Vec3 vec3 : this.points) {
                Trajectories.access$200(this.this$0).free(vec3);
            }
            this.points.clear();
            this.hitQuad = false;
            this.entity = null;
        }

        public void render(RenderEvent renderEvent) {
            Vec3 vec3 = null;
            for (Vec3 vec32 : this.points) {
                if (vec3 != null) {
                    Renderer.LINES.line(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z, (Color)Trajectories.access$400(this.this$0).get());
                }
                vec3 = vec32;
            }
            if (this.hitQuad) {
                if (this.hitQuadHorizontal) {
                    Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, this.hitQuadX1, this.hitQuadY1, this.hitQuadZ1, 0.5, (Color)Trajectories.access$500(this.this$0).get(), (Color)Trajectories.access$400(this.this$0).get(), (ShapeMode)((Object)Trajectories.access$600(this.this$0).get()));
                } else {
                    Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, this.hitQuadX1, this.hitQuadY1, this.hitQuadZ1, this.hitQuadX2, this.hitQuadY2, this.hitQuadZ2, (Color)Trajectories.access$500(this.this$0).get(), (Color)Trajectories.access$400(this.this$0).get(), (ShapeMode)((Object)Trajectories.access$600(this.this$0).get()));
                }
            }
            if (this.entity != null) {
                double d = (this.entity.method_23317() - this.entity.field_6014) * (double)renderEvent.tickDelta;
                double d2 = (this.entity.method_23318() - this.entity.field_6036) * (double)renderEvent.tickDelta;
                double d3 = (this.entity.method_23321() - this.entity.field_5969) * (double)renderEvent.tickDelta;
                class_238 class_2383 = this.entity.method_5829();
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d + class_2383.field_1323, d2 + class_2383.field_1322, d3 + class_2383.field_1321, d + class_2383.field_1320, d2 + class_2383.field_1325, d3 + class_2383.field_1324, (Color)Trajectories.access$500(this.this$0).get(), (Color)Trajectories.access$400(this.this$0).get(), (ShapeMode)((Object)Trajectories.access$600(this.this$0).get()), 0);
            }
        }

        private void addPoint() {
            this.points.add(((Vec3)Trajectories.access$200(this.this$0).get()).set(Trajectories.access$300((Trajectories)this.this$0).pos));
        }
    }
}

