/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1044
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_290
 */
package minegame159.meteorclient.systems.waypoints;

import java.util.Map;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.systems.waypoints.Waypoints;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1044;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_290;

public class Waypoint
implements ISerializable<Waypoint> {
    public /* synthetic */ boolean overworld;
    public /* synthetic */ boolean visible;
    private static final /* synthetic */ MeshBuilder MB;
    public /* synthetic */ Dimension actualDimension;
    public /* synthetic */ boolean nether;
    public /* synthetic */ String icon;
    public /* synthetic */ SettingColor color;
    public /* synthetic */ String name;
    public /* synthetic */ int x;
    public /* synthetic */ int maxVisibleDistance;
    public /* synthetic */ int y;
    public /* synthetic */ double scale;
    public /* synthetic */ int z;
    public /* synthetic */ boolean end;

    private int correctIconIndex(int llllllllllllllllllIIllllIllIllIl) {
        if (llllllllllllllllllIIllllIllIllIl < 0) {
            return Waypoints.get().icons.size() + llllllllllllllllllIIllllIllIllIl;
        }
        if (llllllllllllllllllIIllllIllIllIl >= Waypoints.get().icons.size()) {
            return llllllllllllllllllIIllllIllIllIl - Waypoints.get().icons.size();
        }
        return llllllllllllllllllIIllllIllIllIl;
    }

    private String getIcon(int llllllllllllllllllIIllllIllIIIlI) {
        Waypoint llllllllllllllllllIIllllIllIIllI;
        llllllllllllllllllIIllllIllIIIlI = llllllllllllllllllIIllllIllIIllI.correctIconIndex(llllllllllllllllllIIllllIllIIIlI);
        int llllllllllllllllllIIllllIllIIlII = 0;
        for (String llllllllllllllllllIIllllIllIIlll : Waypoints.get().icons.keySet()) {
            if (llllllllllllllllllIIllllIllIIlII == llllllllllllllllllIIllllIllIIIlI) {
                return llllllllllllllllllIIllllIllIIlll;
            }
            ++llllllllllllllllllIIllllIllIIlII;
        }
        return "Square";
    }

    private int findIconIndex() {
        int llllllllllllllllllIIllllIlllIlIl = 0;
        for (String llllllllllllllllllIIllllIlllIlll : Waypoints.get().icons.keySet()) {
            Waypoint llllllllllllllllllIIllllIlllIllI;
            if (llllllllllllllllllIIllllIlllIllI.icon.equals(llllllllllllllllllIIllllIlllIlll)) {
                return llllllllllllllllllIIllllIlllIlIl;
            }
            ++llllllllllllllllllIIllllIlllIlIl;
        }
        return -1;
    }

    @Override
    public Waypoint fromTag(class_2487 llllllllllllllllllIIllllIlIIllll) {
        Waypoint llllllllllllllllllIIllllIlIlIIII;
        llllllllllllllllllIIllllIlIlIIII.name = llllllllllllllllllIIllllIlIIllll.method_10558("name");
        llllllllllllllllllIIllllIlIlIIII.icon = llllllllllllllllllIIllllIlIIllll.method_10558("icon");
        llllllllllllllllllIIllllIlIlIIII.color.fromTag(llllllllllllllllllIIllllIlIIllll.method_10562("color"));
        llllllllllllllllllIIllllIlIlIIII.x = llllllllllllllllllIIllllIlIIllll.method_10550("x");
        llllllllllllllllllIIllllIlIlIIII.y = llllllllllllllllllIIllllIlIIllll.method_10550("y");
        llllllllllllllllllIIllllIlIlIIII.z = llllllllllllllllllIIllllIlIIllll.method_10550("z");
        llllllllllllllllllIIllllIlIlIIII.visible = llllllllllllllllllIIllllIlIIllll.method_10577("visible");
        llllllllllllllllllIIllllIlIlIIII.maxVisibleDistance = llllllllllllllllllIIllllIlIIllll.method_10550("maxVisibleDistance");
        llllllllllllllllllIIllllIlIlIIII.scale = llllllllllllllllllIIllllIlIIllll.method_10574("scale");
        llllllllllllllllllIIllllIlIlIIII.actualDimension = Dimension.valueOf(llllllllllllllllllIIllllIlIIllll.method_10558("dimension"));
        llllllllllllllllllIIllllIlIlIIII.overworld = llllllllllllllllllIIllllIlIIllll.method_10577("overworld");
        llllllllllllllllllIIllllIlIlIIII.nether = llllllllllllllllllIIllllIlIIllll.method_10577("nether");
        llllllllllllllllllIIllllIlIlIIII.end = llllllllllllllllllIIllllIlIIllll.method_10577("end");
        if (!Waypoints.get().icons.containsKey(llllllllllllllllllIIllllIlIlIIII.icon)) {
            llllllllllllllllllIIllllIlIlIIII.icon = "Square";
        }
        return llllllllllllllllllIIllllIlIlIIII;
    }

    public void renderIcon(double llllllllllllllllllIIlllllIIIlIlI, double llllllllllllllllllIIlllllIIIlIIl, double llllllllllllllllllIIlllllIIIlIII, double llllllllllllllllllIIlllllIIIIlll, double llllllllllllllllllIIllllIllllllI) {
        Waypoint llllllllllllllllllIIlllllIIIIIll;
        llllllllllllllllllIIlllllIIIIIll.validateIcon();
        class_1044 llllllllllllllllllIIlllllIIIIlIl = Waypoints.get().icons.get(llllllllllllllllllIIlllllIIIIIll.icon);
        if (llllllllllllllllllIIlllllIIIIlIl == null) {
            return;
        }
        MB.begin(null, DrawMode.Triangles, class_290.field_1575);
        int llllllllllllllllllIIlllllIIIIlII = llllllllllllllllllIIlllllIIIIIll.color.a;
        llllllllllllllllllIIlllllIIIIIll.color.a = (int)((double)llllllllllllllllllIIlllllIIIIIll.color.a * llllllllllllllllllIIlllllIIIIlll);
        MB.pos(llllllllllllllllllIIlllllIIIlIlI, llllllllllllllllllIIlllllIIIlIIl, llllllllllllllllllIIlllllIIIlIII).texture(0.0, 0.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        MB.pos(llllllllllllllllllIIlllllIIIlIlI + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIIl, llllllllllllllllllIIlllllIIIlIII).texture(1.0, 0.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        MB.pos(llllllllllllllllllIIlllllIIIlIlI + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIIl + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIII).texture(1.0, 1.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        MB.pos(llllllllllllllllllIIlllllIIIlIlI, llllllllllllllllllIIlllllIIIlIIl, llllllllllllllllllIIlllllIIIlIII).texture(0.0, 0.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        MB.pos(llllllllllllllllllIIlllllIIIlIlI + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIIl + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIII).texture(1.0, 1.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        MB.pos(llllllllllllllllllIIlllllIIIlIlI, llllllllllllllllllIIlllllIIIlIIl + llllllllllllllllllIIllllIllllllI, llllllllllllllllllIIlllllIIIlIII).texture(0.0, 1.0).color(llllllllllllllllllIIlllllIIIIIll.color).endVertex();
        llllllllllllllllllIIlllllIIIIlIl.method_23207();
        MB.end();
        llllllllllllllllllIIlllllIIIIIll.color.a = llllllllllllllllllIIlllllIIIIlII;
    }

    public void nextIcon() {
        Waypoint llllllllllllllllllIIllllIlIllIIl;
        llllllllllllllllllIIllllIlIllIIl.icon = llllllllllllllllllIIllllIlIllIIl.getIcon(llllllllllllllllllIIllllIlIllIIl.findIconIndex() + 1);
    }

    public void validateIcon() {
        Waypoint llllllllllllllllllIIlllllIIlIllI;
        Map<String, class_1044> llllllllllllllllllIIlllllIIllIII = Waypoints.get().icons;
        class_1044 llllllllllllllllllIIlllllIIlIlll = llllllllllllllllllIIlllllIIllIII.get(llllllllllllllllllIIlllllIIlIllI.icon);
        if (llllllllllllllllllIIlllllIIlIlll == null && !llllllllllllllllllIIlllllIIllIII.isEmpty()) {
            llllllllllllllllllIIlllllIIlIllI.icon = llllllllllllllllllIIlllllIIllIII.keySet().iterator().next();
        }
    }

    @Override
    public class_2487 toTag() {
        Waypoint llllllllllllllllllIIllllIlIlIlII;
        class_2487 llllllllllllllllllIIllllIlIlIlIl = new class_2487();
        llllllllllllllllllIIllllIlIlIlIl.method_10582("name", llllllllllllllllllIIllllIlIlIlII.name);
        llllllllllllllllllIIllllIlIlIlIl.method_10582("icon", llllllllllllllllllIIllllIlIlIlII.icon);
        llllllllllllllllllIIllllIlIlIlIl.method_10566("color", (class_2520)llllllllllllllllllIIllllIlIlIlII.color.toTag());
        llllllllllllllllllIIllllIlIlIlIl.method_10569("x", llllllllllllllllllIIllllIlIlIlII.x);
        llllllllllllllllllIIllllIlIlIlIl.method_10569("y", llllllllllllllllllIIllllIlIlIlII.y);
        llllllllllllllllllIIllllIlIlIlIl.method_10569("z", llllllllllllllllllIIllllIlIlIlII.z);
        llllllllllllllllllIIllllIlIlIlIl.method_10556("visible", llllllllllllllllllIIllllIlIlIlII.visible);
        llllllllllllllllllIIllllIlIlIlIl.method_10569("maxVisibleDistance", llllllllllllllllllIIllllIlIlIlII.maxVisibleDistance);
        llllllllllllllllllIIllllIlIlIlIl.method_10549("scale", llllllllllllllllllIIllllIlIlIlII.scale);
        llllllllllllllllllIIllllIlIlIlIl.method_10582("dimension", llllllllllllllllllIIllllIlIlIlII.actualDimension.name());
        llllllllllllllllllIIllllIlIlIlIl.method_10556("overworld", llllllllllllllllllIIllllIlIlIlII.overworld);
        llllllllllllllllllIIllllIlIlIlIl.method_10556("nether", llllllllllllllllllIIllllIlIlIlII.nether);
        llllllllllllllllllIIllllIlIlIlIl.method_10556("end", llllllllllllllllllIIllllIlIlIlII.end);
        return llllllllllllllllllIIllllIlIlIlIl;
    }

    static {
        MB = new MeshBuilder(128);
        Waypoint.MB.texture = true;
    }

    public void prevIcon() {
        Waypoint llllllllllllllllllIIllllIlIlllII;
        llllllllllllllllllIIllllIlIlllII.icon = llllllllllllllllllIIllllIlIlllII.getIcon(llllllllllllllllllIIllllIlIlllII.findIconIndex() - 1);
    }

    public Waypoint() {
        Waypoint llllllllllllllllllIIlllllIIllllI;
        llllllllllllllllllIIlllllIIllllI.name = "BedTrap on Crack!";
        llllllllllllllllllIIlllllIIllllI.icon = "Square";
        llllllllllllllllllIIlllllIIllllI.color = new SettingColor(225, 25, 25);
        llllllllllllllllllIIlllllIIllllI.visible = true;
        llllllllllllllllllIIlllllIIllllI.maxVisibleDistance = 1000;
        llllllllllllllllllIIlllllIIllllI.scale = 1.0;
    }
}

