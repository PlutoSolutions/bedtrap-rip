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
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ ProjectileEntitySimulator simulator;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> accurate;
    private final /* synthetic */ List<Path> paths;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<List<class_1792>> items;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Pool<Vec3> vec3s;

    private List<class_1792> getDefaultItems() {
        ArrayList<class_1792> llIllIlIllIllI = new ArrayList<class_1792>();
        for (class_1792 llIllIlIlllIII : class_2378.field_11142) {
            Trajectories llIllIlIllIlll;
            if (!llIllIlIllIlll.itemFilter(llIllIlIlllIII)) continue;
            llIllIlIllIllI.add(llIllIlIlllIII);
        }
        return llIllIlIllIllI;
    }

    private void calculatePath(RenderEvent llIllIlIlIIIlI) {
        Trajectories llIllIlIlIIIII;
        for (Path llIllIlIlIIlII : llIllIlIlIIIII.paths) {
            llIllIlIlIIlII.clear();
        }
        class_1799 llIllIlIlIIIIl = llIllIlIlIIIII.mc.field_1724.method_6047();
        if (llIllIlIlIIIIl == null) {
            llIllIlIlIIIIl = llIllIlIlIIIII.mc.field_1724.method_6079();
        }
        if (llIllIlIlIIIIl == null) {
            return;
        }
        if (!llIllIlIlIIIII.items.get().contains((Object)llIllIlIlIIIIl.method_7909())) {
            return;
        }
        if (!llIllIlIlIIIII.simulator.set((class_1297)llIllIlIlIIIII.mc.field_1724, llIllIlIlIIIIl, 0.0, llIllIlIlIIIII.accurate.get(), llIllIlIlIIIlI.tickDelta)) {
            return;
        }
        llIllIlIlIIIII.getEmptyPath().calculate();
        if (llIllIlIlIIIIl.method_7909() instanceof class_1764 && class_1890.method_8225((class_1887)class_1893.field_9108, (class_1799)llIllIlIlIIIIl) > 0) {
            if (!llIllIlIlIIIII.simulator.set((class_1297)llIllIlIlIIIII.mc.field_1724, llIllIlIlIIIIl, -10.0, llIllIlIlIIIII.accurate.get(), llIllIlIlIIIlI.tickDelta)) {
                return;
            }
            llIllIlIlIIIII.getEmptyPath().calculate();
            if (!llIllIlIlIIIII.simulator.set((class_1297)llIllIlIlIIIII.mc.field_1724, llIllIlIlIIIIl, 10.0, llIllIlIlIIIII.accurate.get(), llIllIlIlIIIlI.tickDelta)) {
                return;
            }
            llIllIlIlIIIII.getEmptyPath().calculate();
        }
    }

    private Path getEmptyPath() {
        Trajectories llIllIlIlIlIll;
        for (Path llIllIlIlIlllI : llIllIlIlIlIll.paths) {
            if (!llIllIlIlIlllI.points.isEmpty()) continue;
            return llIllIlIlIlllI;
        }
        Path llIllIlIlIllII = llIllIlIlIlIll.new Path();
        llIllIlIlIlIll.paths.add(llIllIlIlIllII);
        return llIllIlIlIllII;
    }

    public Trajectories() {
        super(Categories.Render, "trajectories", "Predicts the trajectory of throwable items.");
        Trajectories llIllIllIIIIlI;
        llIllIllIIIIlI.sgGeneral = llIllIllIIIIlI.settings.getDefaultGroup();
        llIllIllIIIIlI.sgRender = llIllIllIIIIlI.settings.createGroup("Render");
        llIllIllIIIIlI.items = llIllIllIIIIlI.sgGeneral.add(new ItemListSetting.Builder().name("items").description("Items to display trajectories for.").defaultValue(llIllIllIIIIlI.getDefaultItems()).filter(llIllIllIIIIlI::itemFilter).build());
        llIllIllIIIIlI.accurate = llIllIllIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("accurate").description("Calculates accurate trajectories while moving.").defaultValue(true).build());
        llIllIllIIIIlI.shapeMode = llIllIllIIIIlI.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llIllIllIIIIlI.sideColor = llIllIllIIIIlI.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 150, 0, 35)).build());
        llIllIllIIIIlI.lineColor = llIllIllIIIIlI.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 150, 0)).build());
        llIllIllIIIIlI.simulator = new ProjectileEntitySimulator();
        llIllIllIIIIlI.vec3s = new Pool<Vec3>(Vec3::new);
        llIllIllIIIIlI.paths = new ArrayList<Path>();
    }

    @EventHandler
    private void onRender(RenderEvent llIllIlIIlIllI) {
        Trajectories llIllIlIIlIlll;
        llIllIlIIlIlll.calculatePath(llIllIlIIlIllI);
        for (Path llIllIlIIllIII : llIllIlIIlIlll.paths) {
            llIllIlIIllIII.render(llIllIlIIlIllI);
        }
    }

    private boolean itemFilter(class_1792 llIllIlIllllIl) {
        return llIllIlIllllIl instanceof class_1753 || llIllIlIllllIl instanceof class_1764 || llIllIlIllllIl instanceof class_1787 || llIllIlIllllIl instanceof class_1835 || llIllIlIllllIl instanceof class_1823 || llIllIlIllllIl instanceof class_1771 || llIllIlIllllIl instanceof class_1776 || llIllIlIllllIl instanceof class_1779 || llIllIlIllllIl instanceof class_4537;
    }

    private class Path {
        private /* synthetic */ boolean hitQuadHorizontal;
        private /* synthetic */ class_1297 entity;
        private /* synthetic */ boolean hitQuad;
        private /* synthetic */ double hitQuadZ1;
        private /* synthetic */ double hitQuadY1;
        private final /* synthetic */ List<Vec3> points;
        private /* synthetic */ double hitQuadY2;
        private /* synthetic */ double hitQuadX2;
        private /* synthetic */ double hitQuadZ2;
        private /* synthetic */ double hitQuadX1;

        private Path() {
            Path llIllIIlllllIIl;
            llIllIIlllllIIl.points = new ArrayList<Vec3>();
        }

        public void calculate() {
            class_239 llIllIIlllIllIl;
            Path llIllIIlllIlIll;
            llIllIIlllIlIll.addPoint();
            while (true) {
                if ((llIllIIlllIllIl = llIllIIlllIlIll.Trajectories.this.simulator.tick()) != null) break;
                llIllIIlllIlIll.addPoint();
            }
            llIllIIlllIlIll.processHitResult(llIllIIlllIllIl);
        }

        private void processHitResult(class_239 llIllIIllIlllll) {
            Path llIllIIlllIIIlI;
            if (llIllIIllIlllll.method_17783() == class_239.class_240.field_1332) {
                class_3965 llIllIIlllIIIll = (class_3965)llIllIIllIlllll;
                llIllIIlllIIIlI.hitQuad = true;
                llIllIIlllIIIlI.hitQuadX1 = llIllIIlllIIIll.method_17784().field_1352;
                llIllIIlllIIIlI.hitQuadY1 = llIllIIlllIIIll.method_17784().field_1351;
                llIllIIlllIIIlI.hitQuadZ1 = llIllIIlllIIIll.method_17784().field_1350;
                llIllIIlllIIIlI.hitQuadX2 = llIllIIlllIIIll.method_17784().field_1352;
                llIllIIlllIIIlI.hitQuadY2 = llIllIIlllIIIll.method_17784().field_1351;
                llIllIIlllIIIlI.hitQuadZ2 = llIllIIlllIIIll.method_17784().field_1350;
                if (llIllIIlllIIIll.method_17780() == class_2350.field_11036 || llIllIIlllIIIll.method_17780() == class_2350.field_11033) {
                    llIllIIlllIIIlI.hitQuadHorizontal = true;
                    llIllIIlllIIIlI.hitQuadX1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadZ1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadX2 += 0.25;
                    llIllIIlllIIIlI.hitQuadZ2 += 0.25;
                } else if (llIllIIlllIIIll.method_17780() == class_2350.field_11043 || llIllIIlllIIIll.method_17780() == class_2350.field_11035) {
                    llIllIIlllIIIlI.hitQuadHorizontal = false;
                    llIllIIlllIIIlI.hitQuadX1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadY1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadX2 += 0.25;
                    llIllIIlllIIIlI.hitQuadY2 += 0.25;
                } else {
                    llIllIIlllIIIlI.hitQuadHorizontal = false;
                    llIllIIlllIIIlI.hitQuadZ1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadY1 -= 0.25;
                    llIllIIlllIIIlI.hitQuadZ2 += 0.25;
                    llIllIIlllIIIlI.hitQuadY2 += 0.25;
                }
                llIllIIlllIIIlI.points.add(((Vec3)llIllIIlllIIIlI.Trajectories.this.vec3s.get()).set(llIllIIllIlllll.method_17784()));
            } else if (llIllIIllIlllll.method_17783() == class_239.class_240.field_1331) {
                llIllIIlllIIIlI.entity = ((class_3966)llIllIIllIlllll).method_17782();
                llIllIIlllIIIlI.points.add(((Vec3)llIllIIlllIIIlI.Trajectories.this.vec3s.get()).set(llIllIIllIlllll.method_17784()).add(0.0, llIllIIlllIIIlI.entity.method_17682() / 2.0f, 0.0));
            }
        }

        public void clear() {
            Path llIllIIllllIIll;
            for (Vec3 llIllIIllllIlII : llIllIIllllIIll.points) {
                llIllIIllllIIll.Trajectories.this.vec3s.free(llIllIIllllIlII);
            }
            llIllIIllllIIll.points.clear();
            llIllIIllllIIll.hitQuad = false;
            llIllIIllllIIll.entity = null;
        }

        public void render(RenderEvent llIllIIllIIllll) {
            Path llIllIIllIlIIII;
            Vec3 llIllIIllIIlllI = null;
            for (Vec3 llIllIIllIlIlIl : llIllIIllIlIIII.points) {
                if (llIllIIllIIlllI != null) {
                    Renderer.LINES.line(llIllIIllIIlllI.x, llIllIIllIIlllI.y, llIllIIllIIlllI.z, llIllIIllIlIlIl.x, llIllIIllIlIlIl.y, llIllIIllIlIlIl.z, (Color)llIllIIllIlIIII.Trajectories.this.lineColor.get());
                }
                llIllIIllIIlllI = llIllIIllIlIlIl;
            }
            if (llIllIIllIlIIII.hitQuad) {
                if (llIllIIllIlIIII.hitQuadHorizontal) {
                    Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, llIllIIllIlIIII.hitQuadX1, llIllIIllIlIIII.hitQuadY1, llIllIIllIlIIII.hitQuadZ1, 0.5, (Color)llIllIIllIlIIII.Trajectories.this.sideColor.get(), (Color)llIllIIllIlIIII.Trajectories.this.lineColor.get(), (ShapeMode)((Object)llIllIIllIlIIII.Trajectories.this.shapeMode.get()));
                } else {
                    Renderer.quadWithLinesVertical(Renderer.NORMAL, Renderer.LINES, llIllIIllIlIIII.hitQuadX1, llIllIIllIlIIII.hitQuadY1, llIllIIllIlIIII.hitQuadZ1, llIllIIllIlIIII.hitQuadX2, llIllIIllIlIIII.hitQuadY2, llIllIIllIlIIII.hitQuadZ2, (Color)llIllIIllIlIIII.Trajectories.this.sideColor.get(), (Color)llIllIIllIlIIII.Trajectories.this.lineColor.get(), (ShapeMode)((Object)llIllIIllIlIIII.Trajectories.this.shapeMode.get()));
                }
            }
            if (llIllIIllIlIIII.entity != null) {
                double llIllIIllIlIlII = (llIllIIllIlIIII.entity.method_23317() - llIllIIllIlIIII.entity.field_6014) * (double)llIllIIllIIllll.tickDelta;
                double llIllIIllIlIIll = (llIllIIllIlIIII.entity.method_23318() - llIllIIllIlIIII.entity.field_6036) * (double)llIllIIllIIllll.tickDelta;
                double llIllIIllIlIIlI = (llIllIIllIlIIII.entity.method_23321() - llIllIIllIlIIII.entity.field_5969) * (double)llIllIIllIIllll.tickDelta;
                class_238 llIllIIllIlIIIl = llIllIIllIlIIII.entity.method_5829();
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llIllIIllIlIlII + llIllIIllIlIIIl.field_1323, llIllIIllIlIIll + llIllIIllIlIIIl.field_1322, llIllIIllIlIIlI + llIllIIllIlIIIl.field_1321, llIllIIllIlIlII + llIllIIllIlIIIl.field_1320, llIllIIllIlIIll + llIllIIllIlIIIl.field_1325, llIllIIllIlIIlI + llIllIIllIlIIIl.field_1324, (Color)llIllIIllIlIIII.Trajectories.this.sideColor.get(), (Color)llIllIIllIlIIII.Trajectories.this.lineColor.get(), (ShapeMode)((Object)llIllIIllIlIIII.Trajectories.this.shapeMode.get()), 0);
            }
        }

        private void addPoint() {
            Path llIllIIlllIIlll;
            llIllIIlllIIlll.points.add(((Vec3)llIllIIlllIIlll.Trajectories.this.vec3s.get()).set(((Trajectories)llIllIIlllIIlll.Trajectories.this).simulator.pos));
        }
    }
}

