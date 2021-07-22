/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1011
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_4184
 */
package minegame159.meteorclient.systems.waypoints;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.WaypointsModule;
import minegame159.meteorclient.systems.waypoints.Waypoint;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.files.StreamUtils;
import minegame159.meteorclient.utils.misc.NbtUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_4184;

public class Waypoints
extends System<Waypoints>
implements Iterable<Waypoint> {
    public final /* synthetic */ Map<String, class_1044> icons;
    private static final /* synthetic */ Color TEXT;
    private static final /* synthetic */ String[] BUILTIN_ICONS;
    private static final /* synthetic */ Color BACKGROUND;
    private /* synthetic */ List<Waypoint> waypoints;

    public void add(Waypoint lIlIIlIIlIllIIl) {
        Waypoints lIlIIlIIlIlllII;
        lIlIIlIIlIlllII.waypoints.add(lIlIIlIIlIllIIl);
        lIlIIlIIlIlllII.save();
    }

    @Override
    public Waypoints fromTag(class_2487 lIlIIIllllIllIl) {
        Waypoints lIlIIIllllIllII;
        lIlIIIllllIllII.waypoints = NbtUtils.listFromTag(lIlIIIllllIllIl.method_10554("waypoints", 10), lIlIIIlllIllIIl -> new Waypoint().fromTag((class_2487)lIlIIIlllIllIIl));
        return lIlIIIllllIllII;
    }

    public void remove(Waypoint lIlIIlIIlIlIIll) {
        Waypoints lIlIIlIIlIlIlII;
        if (lIlIIlIIlIlIlII.waypoints.remove(lIlIIlIIlIlIIll)) {
            lIlIIlIIlIlIlII.save();
        }
    }

    static {
        BUILTIN_ICONS = new String[]{"square", "circle", "triangle", "star", "diamond", "skull"};
        BACKGROUND = new Color(0, 0, 0, 75);
        TEXT = new Color(255, 255, 255);
    }

    public Waypoints() {
        super(null);
        Waypoints lIlIIlIIllllIlI;
        lIlIIlIIllllIlI.icons = new HashMap<String, class_1044>();
        lIlIIlIIllllIlI.waypoints = new ArrayList<Waypoint>();
    }

    public static Waypoints get() {
        return Systems.get(Waypoints.class);
    }

    private void copyIcon(File lIlIIIllllIIIlI) {
        StreamUtils.copy(Waypoints.class.getResourceAsStream(String.valueOf(new StringBuilder().append("/assets/meteor-client/textures/icons/waypoints/").append(lIlIIIllllIIIlI.getName()))), lIlIIIllllIIIlI);
    }

    private boolean checkDimension(Waypoint lIlIIlIIlIIIlll) {
        Dimension lIlIIlIIlIIIllI = PlayerUtils.getDimension();
        if (lIlIIlIIlIIIlll.overworld && lIlIIlIIlIIIllI == Dimension.Overworld) {
            return true;
        }
        if (lIlIIlIIlIIIlll.nether && lIlIIlIIlIIIllI == Dimension.Nether) {
            return true;
        }
        return lIlIIlIIlIIIlll.end && lIlIIlIIlIIIllI == Dimension.End;
    }

    public ListIterator<Waypoint> iteratorReverse() {
        Waypoints lIlIIIllllIIllI;
        return lIlIIIllllIIllI.waypoints.listIterator(lIlIIIllllIIllI.waypoints.size());
    }

    @Override
    public Iterator<Waypoint> iterator() {
        Waypoints lIlIIIllllIlIIl;
        return lIlIIIllllIlIIl.waypoints.iterator();
    }

    @EventHandler(priority=-200)
    private void onGameDisconnected(GameLeftEvent lIlIIlIIlIIllII) {
        Waypoints lIlIIlIIlIIllIl;
        lIlIIlIIlIIllIl.waypoints.clear();
    }

    @Override
    public File getFile() {
        if (!Utils.canUpdate()) {
            return null;
        }
        return new File(new File(MeteorClient.FOLDER, "waypoints"), String.valueOf(new StringBuilder().append(Utils.getWorldName()).append(".nbt")));
    }

    @Override
    public void init() {
        File[] lIlIIlIIllIlIII;
        Waypoints lIlIIlIIllIIlll;
        File lIlIIlIIllIlIIl = new File(new File(MeteorClient.FOLDER, "waypoints"), "icons");
        lIlIIlIIllIlIIl.mkdirs();
        for (String lIlIIlIIllIllll : BUILTIN_ICONS) {
            File lIlIIlIIlllIIII = new File(lIlIIlIIllIlIIl, String.valueOf(new StringBuilder().append(lIlIIlIIllIllll).append(".png")));
            if (lIlIIlIIlllIIII.exists()) continue;
            lIlIIlIIllIIlll.copyIcon(lIlIIlIIlllIIII);
        }
        for (File lIlIIlIIllIlIll : lIlIIlIIllIlIII = lIlIIlIIllIlIIl.listFiles()) {
            if (!lIlIIlIIllIlIll.getName().endsWith(".png")) continue;
            try {
                String lIlIIlIIllIlllI = lIlIIlIIllIlIll.getName().replace(".png", "");
                class_1043 lIlIIlIIllIllIl = new class_1043(class_1011.method_4309((InputStream)new FileInputStream(lIlIIlIIllIlIll)));
                lIlIIlIIllIIlll.icons.put(lIlIIlIIllIlllI, (class_1044)lIlIIlIIllIllIl);
            }
            catch (IOException lIlIIlIIllIllII) {
                lIlIIlIIllIllII.printStackTrace();
            }
        }
    }

    @Override
    public class_2487 toTag() {
        Waypoints lIlIIIlllllIIlI;
        class_2487 lIlIIIlllllIIll = new class_2487();
        lIlIIIlllllIIll.method_10566("waypoints", (class_2520)NbtUtils.listToTag(lIlIIIlllllIIlI.waypoints));
        return lIlIIIlllllIIll;
    }

    public class_243 getCoords(Waypoint lIlIIlIIIlllllI) {
        double lIlIIlIIIllllIl = lIlIIlIIIlllllI.x;
        double lIlIIlIIIllllII = lIlIIlIIIlllllI.y;
        double lIlIIlIIIlllIll = lIlIIlIIIlllllI.z;
        if (lIlIIlIIIlllllI.actualDimension == Dimension.Overworld && PlayerUtils.getDimension() == Dimension.Nether) {
            lIlIIlIIIllllIl = (float)lIlIIlIIIlllllI.x / 8.0f;
            lIlIIlIIIlllIll = (float)lIlIIlIIIlllllI.z / 8.0f;
        } else if (lIlIIlIIIlllllI.actualDimension == Dimension.Nether && PlayerUtils.getDimension() == Dimension.Overworld) {
            lIlIIlIIIllllIl = lIlIIlIIIlllllI.x * 8;
            lIlIIlIIIlllIll = lIlIIlIIIlllllI.z * 8;
        }
        return new class_243(lIlIIlIIIllllIl, lIlIIlIIIllllII, lIlIIlIIIlllIll);
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent lIlIIlIIlIlIIII) {
        Waypoints lIlIIlIIlIlIIIl;
        lIlIIlIIlIlIIIl.load();
    }

    @EventHandler
    private void onRender(RenderEvent lIlIIlIIIIIllIl) {
        Waypoints lIlIIlIIIIIlllI;
        if (!Modules.get().isActive(WaypointsModule.class)) {
            return;
        }
        for (Waypoint lIlIIlIIIIIllll : lIlIIlIIIIIlllI) {
            if (!lIlIIlIIIIIllll.visible || !lIlIIlIIIIIlllI.checkDimension(lIlIIlIIIIIllll)) continue;
            class_4184 lIlIIlIIIIlllIl = Utils.mc.field_1773.method_19418();
            double lIlIIlIIIIlllII = lIlIIlIIIIIlllI.getCoords((Waypoint)lIlIIlIIIIIllll).field_1352;
            double lIlIIlIIIIllIll = lIlIIlIIIIIlllI.getCoords((Waypoint)lIlIIlIIIIIllll).field_1351;
            double lIlIIlIIIIllIlI = lIlIIlIIIIIlllI.getCoords((Waypoint)lIlIIlIIIIIllll).field_1350;
            double lIlIIlIIIIllIIl = PlayerUtils.distanceToCamera(lIlIIlIIIIlllII, lIlIIlIIIIllIll, lIlIIlIIIIllIlI);
            if (lIlIIlIIIIllIIl > (double)lIlIIlIIIIIllll.maxVisibleDistance) continue;
            double lIlIIlIIIIllIII = 0.01 * lIlIIlIIIIIllll.scale;
            if (lIlIIlIIIIllIIl > 8.0) {
                lIlIIlIIIIllIII *= lIlIIlIIIIllIIl / 8.0;
            }
            double lIlIIlIIIIlIlll = 1.0;
            if (lIlIIlIIIIllIIl < 10.0 && (lIlIIlIIIIlIlll = lIlIIlIIIIllIIl / 10.0) < 0.1) continue;
            int lIlIIlIIIIlIllI = Waypoints.BACKGROUND.a;
            int lIlIIlIIIIlIlIl = Waypoints.TEXT.a;
            Waypoints.BACKGROUND.a = (int)((double)Waypoints.BACKGROUND.a * lIlIIlIIIIlIlll);
            Waypoints.TEXT.a = (int)((double)Waypoints.TEXT.a * lIlIIlIIIIlIlll);
            double lIlIIlIIIIlIlII = Utils.mc.field_1690.field_1870 * 16;
            if (lIlIIlIIIIllIIl > lIlIIlIIIIlIlII) {
                double lIlIIlIIIlIIIIl = lIlIIlIIIIlllII - lIlIIlIIIIlllIl.method_19326().field_1352;
                double lIlIIlIIIlIIIII = lIlIIlIIIIllIll - lIlIIlIIIIlllIl.method_19326().field_1351;
                double lIlIIlIIIIlllll = lIlIIlIIIIllIlI - lIlIIlIIIIlllIl.method_19326().field_1350;
                double lIlIIlIIIIllllI = Math.sqrt(lIlIIlIIIlIIIIl * lIlIIlIIIlIIIIl + lIlIIlIIIlIIIII * lIlIIlIIIlIIIII + lIlIIlIIIIlllll * lIlIIlIIIIlllll);
                lIlIIlIIIlIIIIl /= lIlIIlIIIIllllI;
                lIlIIlIIIlIIIII /= lIlIIlIIIIllllI;
                lIlIIlIIIIlllll /= lIlIIlIIIIllllI;
                lIlIIlIIIIlllII = lIlIIlIIIIlllIl.method_19326().field_1352 + (lIlIIlIIIlIIIIl *= lIlIIlIIIIlIlII);
                lIlIIlIIIIllIll = lIlIIlIIIIlllIl.method_19326().field_1351 + (lIlIIlIIIlIIIII *= lIlIIlIIIIlIlII);
                lIlIIlIIIIllIlI = lIlIIlIIIIlllIl.method_19326().field_1350 + (lIlIIlIIIIlllll *= lIlIIlIIIIlIlII);
                lIlIIlIIIIllIII /= lIlIIlIIIIllIIl / 15.0;
                lIlIIlIIIIllIII *= lIlIIlIIIIlIlII / 15.0;
            }
            Matrices.push();
            Matrices.translate(lIlIIlIIIIlllII + 0.5 - lIlIIlIIIIIllIl.offsetX, lIlIIlIIIIllIll - lIlIIlIIIIIllIl.offsetY, lIlIIlIIIIllIlI + 0.5 - lIlIIlIIIIIllIl.offsetZ);
            Matrices.translate(0.0, -0.5 + lIlIIlIIIIIllll.scale - 1.0, 0.0);
            Matrices.rotate(-lIlIIlIIIIlllIl.method_19330(), 0.0, 1.0, 0.0);
            Matrices.rotate(lIlIIlIIIIlllIl.method_19329(), 1.0, 0.0, 0.0);
            Matrices.translate(0.0, 0.5, 0.0);
            Matrices.scale(-lIlIIlIIIIllIII, -lIlIIlIIIIllIII, lIlIIlIIIIllIII);
            String lIlIIlIIIIlIIll = String.valueOf(new StringBuilder().append(Math.round(lIlIIlIIIIllIIl)).append(" blocks"));
            TextRenderer.get().begin(1.0, false, true);
            double lIlIIlIIIIlIIlI = TextRenderer.get().getWidth(lIlIIlIIIIIllll.name) / 2.0;
            double lIlIIlIIIIlIIIl = TextRenderer.get().getWidth(lIlIIlIIIIlIIll) / 2.0;
            double lIlIIlIIIIlIIII = TextRenderer.get().getHeight();
            lIlIIlIIIIIllll.renderIcon(-8.0, lIlIIlIIIIlIIII, 0.0, lIlIIlIIIIlIlll, 16.0);
            TextRenderer.get().render(lIlIIlIIIIIllll.name, -lIlIIlIIIIlIIlI, -lIlIIlIIIIlIIII + 1.0, TEXT);
            TextRenderer.get().render(lIlIIlIIIIlIIll, -lIlIIlIIIIlIIIl, 0.0, TEXT);
            TextRenderer.get().end();
            Matrices.pop();
            Waypoints.BACKGROUND.a = lIlIIlIIIIlIllI;
            Waypoints.TEXT.a = lIlIIlIIIIlIlIl;
        }
    }
}

