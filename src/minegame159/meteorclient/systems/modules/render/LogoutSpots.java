/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_290
 *  net.minecraft.class_308
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.systems.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Matrices;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_290;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_640;

public class LogoutSpots
extends Module {
    private static final Color GREEN;
    private final SettingGroup sgRender;
    private static final Color RED;
    private int timer;
    private final Setting<SettingColor> nameColor;
    private final Setting<SettingColor> lineColor;
    private final SettingGroup sgGeneral;
    private static final Color ORANGE;
    private final List<Entry> players;
    private final Setting<Boolean> fullHeight;
    private static final MeshBuilder MB;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> sideColor;
    private final List<class_640> lastPlayerList;
    private final List<class_1657> lastPlayers;
    private Dimension lastDimension;
    private final Setting<Double> scale;
    private final Setting<SettingColor> nameBackgroundColor;

    static Setting access$100(LogoutSpots logoutSpots) {
        return logoutSpots.scale;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.method_1562().method_2880().size() != this.lastPlayerList.size()) {
            for (class_640 class_6402 : this.lastPlayerList) {
                if (this.mc.method_1562().method_2880().stream().anyMatch(arg_0 -> LogoutSpots.lambda$onTick$0(class_6402, arg_0))) continue;
                for (class_1657 class_16572 : this.lastPlayers) {
                    if (!class_16572.method_5667().equals(class_6402.method_2966().getId())) continue;
                    this.add(new Entry(this, class_16572));
                }
            }
            this.lastPlayerList.clear();
            this.lastPlayerList.addAll(this.mc.method_1562().method_2880());
            this.updateLastPlayers();
        }
        if (this.timer <= 0) {
            this.updateLastPlayers();
            this.timer = 10;
        } else {
            --this.timer;
        }
        Object object = PlayerUtils.getDimension();
        if (object != this.lastDimension) {
            this.players.clear();
        }
        this.lastDimension = object;
    }

    static Color access$800() {
        return ORANGE;
    }

    static Color access$700() {
        return RED;
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.players.size());
    }

    static Setting access$400(LogoutSpots logoutSpots) {
        return logoutSpots.sideColor;
    }

    static Setting access$300(LogoutSpots logoutSpots) {
        return logoutSpots.fullHeight;
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent entityAddedEvent) {
        if (entityAddedEvent.entity instanceof class_1657) {
            int n = -1;
            for (int i = 0; i < this.players.size(); ++i) {
                if (!this.players.get((int)i).uuid.equals(entityAddedEvent.entity.method_5667())) continue;
                n = i;
                break;
            }
            if (n != -1) {
                this.players.remove(n);
            }
        }
    }

    static class_310 access$000(LogoutSpots logoutSpots) {
        return logoutSpots.mc;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        for (Entry entry : this.players) {
            entry.render(renderEvent);
        }
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        class_308.method_1450();
        RenderSystem.enableBlend();
    }

    @Override
    public void onActivate() {
        this.lastPlayerList.addAll(this.mc.method_1562().method_2880());
        this.updateLastPlayers();
        this.timer = 10;
        this.lastDimension = PlayerUtils.getDimension();
    }

    private void add(Entry entry) {
        this.players.removeIf(arg_0 -> LogoutSpots.lambda$add$1(entry, arg_0));
        this.players.add(entry);
    }

    private static boolean lambda$add$1(Entry entry, Entry entry2) {
        return entry2.uuid.equals(entry.uuid);
    }

    static {
        MB = new MeshBuilder(64);
        GREEN = new Color(25, 225, 25);
        ORANGE = new Color(225, 105, 25);
        RED = new Color(225, 25, 25);
    }

    static Setting access$600(LogoutSpots logoutSpots) {
        return logoutSpots.shapeMode;
    }

    public LogoutSpots() {
        super(Categories.Render, "logout-spots", "Displays a box where another player has logged out at.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale.").defaultValue(1.0).min(0.0).build());
        this.fullHeight = this.sgGeneral.add(new BoolSetting.Builder().name("full-height").description("Displays the height as the player's full height.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 0, 255, 55)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 0, 255)).build());
        this.nameColor = this.sgRender.add(new ColorSetting.Builder().name("name-color").description("The name color.").defaultValue(new SettingColor(255, 255, 255)).build());
        this.nameBackgroundColor = this.sgRender.add(new ColorSetting.Builder().name("name-background-color").description("The name background color.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        this.players = new ArrayList<Entry>();
        this.lastPlayerList = new ArrayList<class_640>();
        this.lastPlayers = new ArrayList<class_1657>();
        this.lineColor.changed();
    }

    static Setting access$1200(LogoutSpots logoutSpots) {
        return logoutSpots.nameColor;
    }

    static Setting access$1100(LogoutSpots logoutSpots) {
        return logoutSpots.nameBackgroundColor;
    }

    private static boolean lambda$onTick$0(class_640 class_6402, class_640 class_6403) {
        return class_6403.method_2966().equals((Object)class_6402.method_2966());
    }

    static Color access$900() {
        return GREEN;
    }

    static MeshBuilder access$1000() {
        return MB;
    }

    static class_310 access$200(LogoutSpots logoutSpots) {
        return logoutSpots.mc;
    }

    @Override
    public void onDeactivate() {
        this.players.clear();
        this.lastPlayerList.clear();
    }

    private void updateLastPlayers() {
        this.lastPlayers.clear();
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1657)) continue;
            this.lastPlayers.add((class_1657)class_12972);
        }
    }

    static Setting access$500(LogoutSpots logoutSpots) {
        return logoutSpots.lineColor;
    }

    private class Entry {
        public final String name;
        public final UUID uuid;
        public final double z;
        public final double x;
        public final String healthText;
        public final double xWidth;
        public final double y;
        public final double height;
        public final int health;
        final LogoutSpots this$0;
        public final double zWidth;
        public final int maxHealth;

        public Entry(LogoutSpots logoutSpots, class_1657 class_16572) {
            this.this$0 = logoutSpots;
            this.x = class_16572.method_23317();
            this.y = class_16572.method_23318();
            this.z = class_16572.method_23321();
            this.xWidth = class_16572.method_5829().method_17939();
            this.zWidth = class_16572.method_5829().method_17941();
            this.height = class_16572.method_5829().method_17940();
            this.uuid = class_16572.method_5667();
            this.name = class_16572.method_5820();
            this.health = Math.round(class_16572.method_6032() + class_16572.method_6067());
            this.maxHealth = Math.round(class_16572.method_6063() + class_16572.method_6067());
            this.healthText = String.valueOf(new StringBuilder().append(" ").append(this.health));
        }

        public void render(RenderEvent renderEvent) {
            class_4184 class_41842 = LogoutSpots.access$000((LogoutSpots)this.this$0).field_1773.method_19418();
            double d = 0.025;
            double d2 = PlayerUtils.distanceToCamera(this.x, this.y, this.z);
            if (d2 > 8.0) {
                d *= d2 / 8.0 * (Double)LogoutSpots.access$100(this.this$0).get();
            }
            if (d2 > (double)(LogoutSpots.access$200((LogoutSpots)this.this$0).field_1690.field_1870 * 16)) {
                return;
            }
            double d3 = (double)this.health / (double)this.maxHealth;
            if (((Boolean)LogoutSpots.access$300(this.this$0).get()).booleanValue()) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, this.x, this.y, this.z, this.x + this.xWidth, this.y + this.height, this.z + this.zWidth, (Color)LogoutSpots.access$400(this.this$0).get(), (Color)LogoutSpots.access$500(this.this$0).get(), (ShapeMode)((Object)LogoutSpots.access$600(this.this$0).get()), 0);
            } else {
                Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, this.x, this.y, this.z, this.xWidth, (Color)LogoutSpots.access$400(this.this$0).get(), (Color)LogoutSpots.access$500(this.this$0).get(), (ShapeMode)((Object)LogoutSpots.access$600(this.this$0).get()));
            }
            Color color = d3 <= 0.333 ? LogoutSpots.access$700() : (d3 <= 0.666 ? LogoutSpots.access$800() : LogoutSpots.access$900());
            Matrices.push();
            Matrices.translate(this.x + this.xWidth / 2.0 - renderEvent.offsetX, this.y + ((Boolean)LogoutSpots.access$300(this.this$0).get() != false ? this.height + 0.5 : 0.5) - renderEvent.offsetY, this.z + this.zWidth / 2.0 - renderEvent.offsetZ);
            Matrices.rotate(-class_41842.method_19330(), 0.0, 1.0, 0.0);
            Matrices.rotate(class_41842.method_19329(), 1.0, 0.0, 0.0);
            Matrices.scale(-d, -d, d);
            double d4 = TextRenderer.get().getWidth(this.name) / 2.0 + TextRenderer.get().getWidth(this.healthText) / 2.0;
            LogoutSpots.access$1000().begin(null, DrawMode.Triangles, class_290.field_1576);
            LogoutSpots.access$1000().quad(-d4 - 1.0, -1.0, 0.0, -d4 - 1.0, 8.0, 0.0, d4 + 1.0, 8.0, 0.0, d4 + 1.0, -1.0, 0.0, (Color)LogoutSpots.access$1100(this.this$0).get());
            LogoutSpots.access$1000().end();
            TextRenderer.get().begin(1.0, false, true);
            double d5 = TextRenderer.get().render(this.name, -d4, 0.0, (Color)LogoutSpots.access$1200(this.this$0).get());
            TextRenderer.get().render(this.healthText, d5, 0.0, color);
            TextRenderer.get().end();
            Matrices.pop();
        }
    }
}

