/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Waypoints
extends System<Waypoints>
implements Iterable<Waypoint> {
    public final Map<String, class_1044> icons = new HashMap<String, class_1044>();
    private static final Color TEXT;
    private static final String[] BUILTIN_ICONS;
    private static final Color BACKGROUND;
    private List<Waypoint> waypoints = new ArrayList<Waypoint>();

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public void add(Waypoint waypoint) {
        this.waypoints.add(waypoint);
        this.save();
    }

    @Override
    public Waypoints fromTag(class_2487 class_24872) {
        this.waypoints = NbtUtils.listFromTag(class_24872.method_10554("waypoints", 10), Waypoints::lambda$fromTag$0);
        return this;
    }

    public void remove(Waypoint waypoint) {
        if (this.waypoints.remove(waypoint)) {
            this.save();
        }
    }

    static {
        BUILTIN_ICONS = new String[]{"square", "circle", "triangle", "star", "diamond", "skull"};
        BACKGROUND = new Color(0, 0, 0, 75);
        TEXT = new Color(255, 255, 255);
    }

    public Waypoints() {
        super(null);
    }

    public static Waypoints get() {
        return Systems.get(Waypoints.class);
    }

    private void copyIcon(File file) {
        StreamUtils.copy(Waypoints.class.getResourceAsStream(String.valueOf(new StringBuilder().append("/assets/meteor-client/textures/icons/waypoints/").append(file.getName()))), file);
    }

    private boolean checkDimension(Waypoint waypoint) {
        Dimension dimension = PlayerUtils.getDimension();
        if (waypoint.overworld && dimension == Dimension.Overworld) {
            return true;
        }
        if (waypoint.nether && dimension == Dimension.Nether) {
            return true;
        }
        return waypoint.end && dimension == Dimension.End;
    }

    public ListIterator<Waypoint> iteratorReverse() {
        return this.waypoints.listIterator(this.waypoints.size());
    }

    @Override
    public Iterator<Waypoint> iterator() {
        return this.waypoints.iterator();
    }

    private static Waypoint lambda$fromTag$0(class_2520 class_25202) {
        return new Waypoint().fromTag((class_2487)class_25202);
    }

    @EventHandler(priority=-200)
    private void onGameDisconnected(GameLeftEvent gameLeftEvent) {
        this.waypoints.clear();
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
        File file = new File(new File(MeteorClient.FOLDER, "waypoints"), "icons");
        file.mkdirs();
        Object[] objectArray = BUILTIN_ICONS;
        int n = objectArray.length;
        for (int i = 0; i < n; ++i) {
            String string = objectArray[i];
            Object object = new File(file, String.valueOf(new StringBuilder().append(string).append(".png")));
            if (((File)object).exists()) continue;
            this.copyIcon((File)object);
            if (3 >= 0) continue;
            return;
        }
        for (Object object : objectArray = file.listFiles()) {
            if (!((File)object).getName().endsWith(".png")) continue;
            try {
                String string = ((File)object).getName().replace(".png", "");
                class_1043 class_10432 = new class_1043(class_1011.method_4309((InputStream)new FileInputStream((File)object)));
                this.icons.put(string, (class_1044)class_10432);
                continue;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            if (4 > -1) continue;
            return;
        }
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("waypoints", (class_2520)NbtUtils.listToTag(this.waypoints));
        return class_24872;
    }

    public class_243 getCoords(Waypoint waypoint) {
        double d = waypoint.x;
        double d2 = waypoint.y;
        double d3 = waypoint.z;
        if (waypoint.actualDimension == Dimension.Overworld && PlayerUtils.getDimension() == Dimension.Nether) {
            d = (float)waypoint.x / 8.0f;
            d3 = (float)waypoint.z / 8.0f;
        } else if (waypoint.actualDimension == Dimension.Nether && PlayerUtils.getDimension() == Dimension.Overworld) {
            d = waypoint.x * 8;
            d3 = waypoint.z * 8;
        }
        return new class_243(d, d2, d3);
    }

    @EventHandler
    private void onGameJoined(GameJoinedEvent gameJoinedEvent) {
        this.load();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (!Modules.get().isActive(WaypointsModule.class)) {
            return;
        }
        for (Waypoint waypoint : this) {
            if (!waypoint.visible || !this.checkDimension(waypoint)) continue;
            class_4184 class_41842 = Utils.mc.field_1773.method_19418();
            double d = this.getCoords((Waypoint)waypoint).field_1352;
            double d2 = this.getCoords((Waypoint)waypoint).field_1351;
            double d3 = this.getCoords((Waypoint)waypoint).field_1350;
            double d4 = PlayerUtils.distanceToCamera(d, d2, d3);
            if (d4 > (double)waypoint.maxVisibleDistance) continue;
            double d5 = 0.01 * waypoint.scale;
            if (d4 > 8.0) {
                d5 *= d4 / 8.0;
            }
            double d6 = 1.0;
            if (d4 < 10.0 && (d6 = d4 / 10.0) < 0.1) continue;
            int n = Waypoints.BACKGROUND.a;
            int n2 = Waypoints.TEXT.a;
            Waypoints.BACKGROUND.a = (int)((double)Waypoints.BACKGROUND.a * d6);
            Waypoints.TEXT.a = (int)((double)Waypoints.TEXT.a * d6);
            double d7 = Utils.mc.field_1690.field_1870 * 16;
            if (d4 > d7) {
                double d8 = d - class_41842.method_19326().field_1352;
                double d9 = d2 - class_41842.method_19326().field_1351;
                double d10 = d3 - class_41842.method_19326().field_1350;
                double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
                d8 /= d11;
                d9 /= d11;
                d10 /= d11;
                d = class_41842.method_19326().field_1352 + (d8 *= d7);
                d2 = class_41842.method_19326().field_1351 + (d9 *= d7);
                d3 = class_41842.method_19326().field_1350 + (d10 *= d7);
                d5 /= d4 / 15.0;
                d5 *= d7 / 15.0;
            }
            Matrices.push();
            Matrices.translate(d + 0.5 - renderEvent.offsetX, d2 - renderEvent.offsetY, d3 + 0.5 - renderEvent.offsetZ);
            Matrices.translate(0.0, -0.5 + waypoint.scale - 1.0, 0.0);
            Matrices.rotate(-class_41842.method_19330(), 0.0, 1.0, 0.0);
            Matrices.rotate(class_41842.method_19329(), 1.0, 0.0, 0.0);
            Matrices.translate(0.0, 0.5, 0.0);
            Matrices.scale(-d5, -d5, d5);
            String string = String.valueOf(new StringBuilder().append(Math.round(d4)).append(" blocks"));
            TextRenderer.get().begin(1.0, false, true);
            double d12 = TextRenderer.get().getWidth(waypoint.name) / 2.0;
            double d13 = TextRenderer.get().getWidth(string) / 2.0;
            double d14 = TextRenderer.get().getHeight();
            waypoint.renderIcon(-8.0, d14, 0.0, d6, 16.0);
            TextRenderer.get().render(waypoint.name, -d12, -d14 + 1.0, TEXT);
            TextRenderer.get().render(string, -d13, 0.0, TEXT);
            TextRenderer.get().end();
            Matrices.pop();
            Waypoints.BACKGROUND.a = n;
            Waypoints.TEXT.a = n2;
        }
    }
}

