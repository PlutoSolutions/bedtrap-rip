/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_290
 *  net.minecraft.class_308
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
import net.minecraft.class_4184;
import net.minecraft.class_640;

public class LogoutSpots
extends Module {
    private static final /* synthetic */ Color GREEN;
    private final /* synthetic */ SettingGroup sgRender;
    private static final /* synthetic */ Color RED;
    private /* synthetic */ int timer;
    private final /* synthetic */ Setting<SettingColor> nameColor;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ SettingGroup sgGeneral;
    private static final /* synthetic */ Color ORANGE;
    private final /* synthetic */ List<Entry> players;
    private final /* synthetic */ Setting<Boolean> fullHeight;
    private static final /* synthetic */ MeshBuilder MB;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ List<class_640> lastPlayerList;
    private final /* synthetic */ List<class_1657> lastPlayers;
    private /* synthetic */ Dimension lastDimension;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ Setting<SettingColor> nameBackgroundColor;

    @EventHandler
    private void onTick(TickEvent.Post lIIIllIlIllIl) {
        LogoutSpots lIIIllIlIlllI;
        if (lIIIllIlIlllI.mc.method_1562().method_2880().size() != lIIIllIlIlllI.lastPlayerList.size()) {
            for (class_640 lIIIllIlIllll : lIIIllIlIlllI.lastPlayerList) {
                if (lIIIllIlIlllI.mc.method_1562().method_2880().stream().anyMatch(lIIIlIllllIlI -> lIIIlIllllIlI.method_2966().equals((Object)lIIIllIlIllll.method_2966()))) continue;
                for (class_1657 lIIIllIllIIII : lIIIllIlIlllI.lastPlayers) {
                    if (!lIIIllIllIIII.method_5667().equals(lIIIllIlIllll.method_2966().getId())) continue;
                    lIIIllIlIlllI.add(lIIIllIlIlllI.new Entry(lIIIllIllIIII));
                }
            }
            lIIIllIlIlllI.lastPlayerList.clear();
            lIIIllIlIlllI.lastPlayerList.addAll(lIIIllIlIlllI.mc.method_1562().method_2880());
            lIIIllIlIlllI.updateLastPlayers();
        }
        if (lIIIllIlIlllI.timer <= 0) {
            lIIIllIlIlllI.updateLastPlayers();
            lIIIllIlIlllI.timer = 10;
        } else {
            --lIIIllIlIlllI.timer;
        }
        Dimension lIIIllIlIllII = PlayerUtils.getDimension();
        if (lIIIllIlIllII != lIIIllIlIlllI.lastDimension) {
            lIIIllIlIlllI.players.clear();
        }
        lIIIllIlIlllI.lastDimension = lIIIllIlIllII;
    }

    @Override
    public String getInfoString() {
        LogoutSpots lIIIllIIIlIlI;
        return Integer.toString(lIIIllIIIlIlI.players.size());
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent lIIIllIlllIII) {
        if (lIIIllIlllIII.entity instanceof class_1657) {
            LogoutSpots lIIIllIlllIll;
            int lIIIllIllllII = -1;
            for (int lIIIllIllllIl = 0; lIIIllIllllIl < lIIIllIlllIll.players.size(); ++lIIIllIllllIl) {
                if (!lIIIllIlllIll.players.get((int)lIIIllIllllIl).uuid.equals(lIIIllIlllIII.entity.method_5667())) continue;
                lIIIllIllllII = lIIIllIllllIl;
                break;
            }
            if (lIIIllIllllII != -1) {
                lIIIllIlllIll.players.remove(lIIIllIllllII);
            }
        }
    }

    @EventHandler
    private void onRender(RenderEvent lIIIllIIIlllI) {
        LogoutSpots lIIIllIIIllll;
        for (Entry lIIIllIIlIIlI : lIIIllIIIllll.players) {
            lIIIllIIlIIlI.render(lIIIllIIIlllI);
        }
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        class_308.method_1450();
        RenderSystem.enableBlend();
    }

    @Override
    public void onActivate() {
        LogoutSpots lIIIlllIIllIl;
        lIIIlllIIllIl.lastPlayerList.addAll(lIIIlllIIllIl.mc.method_1562().method_2880());
        lIIIlllIIllIl.updateLastPlayers();
        lIIIlllIIllIl.timer = 10;
        lIIIlllIIllIl.lastDimension = PlayerUtils.getDimension();
    }

    private void add(Entry lIIIllIIlllll) {
        LogoutSpots lIIIllIlIIlII;
        lIIIllIlIIlII.players.removeIf(lIIIllIIIIIlI -> lIIIllIIIIIlI.uuid.equals(lIIIllIIIIIll.uuid));
        lIIIllIlIIlII.players.add(lIIIllIIlllll);
    }

    static {
        MB = new MeshBuilder(64);
        GREEN = new Color(25, 225, 25);
        ORANGE = new Color(225, 105, 25);
        RED = new Color(225, 25, 25);
    }

    public LogoutSpots() {
        super(Categories.Render, "logout-spots", "Displays a box where another player has logged out at.");
        LogoutSpots lIIIlllIlIIII;
        lIIIlllIlIIII.sgGeneral = lIIIlllIlIIII.settings.getDefaultGroup();
        lIIIlllIlIIII.sgRender = lIIIlllIlIIII.settings.createGroup("Render");
        lIIIlllIlIIII.scale = lIIIlllIlIIII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale.").defaultValue(1.0).min(0.0).build());
        lIIIlllIlIIII.fullHeight = lIIIlllIlIIII.sgGeneral.add(new BoolSetting.Builder().name("full-height").description("Displays the height as the player's full height.").defaultValue(true).build());
        lIIIlllIlIIII.shapeMode = lIIIlllIlIIII.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIIIlllIlIIII.sideColor = lIIIlllIlIIII.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 0, 255, 55)).build());
        lIIIlllIlIIII.lineColor = lIIIlllIlIIII.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 0, 255)).build());
        lIIIlllIlIIII.nameColor = lIIIlllIlIIII.sgRender.add(new ColorSetting.Builder().name("name-color").description("The name color.").defaultValue(new SettingColor(255, 255, 255)).build());
        lIIIlllIlIIII.nameBackgroundColor = lIIIlllIlIIII.sgRender.add(new ColorSetting.Builder().name("name-background-color").description("The name background color.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        lIIIlllIlIIII.players = new ArrayList<Entry>();
        lIIIlllIlIIII.lastPlayerList = new ArrayList<class_640>();
        lIIIlllIlIIII.lastPlayers = new ArrayList<class_1657>();
        lIIIlllIlIIII.lineColor.changed();
    }

    @Override
    public void onDeactivate() {
        LogoutSpots lIIIlllIIlIlI;
        lIIIlllIIlIlI.players.clear();
        lIIIlllIIlIlI.lastPlayerList.clear();
    }

    private void updateLastPlayers() {
        LogoutSpots lIIIlllIIIlII;
        lIIIlllIIIlII.lastPlayers.clear();
        for (class_1297 lIIIlllIIIllI : lIIIlllIIIlII.mc.field_1687.method_18112()) {
            if (!(lIIIlllIIIllI instanceof class_1657)) continue;
            lIIIlllIIIlII.lastPlayers.add((class_1657)lIIIlllIIIllI);
        }
    }

    private class Entry {
        public final /* synthetic */ String name;
        public final /* synthetic */ UUID uuid;
        public final /* synthetic */ double z;
        public final /* synthetic */ double x;
        public final /* synthetic */ String healthText;
        public final /* synthetic */ double xWidth;
        public final /* synthetic */ double y;
        public final /* synthetic */ double height;
        public final /* synthetic */ int health;
        public final /* synthetic */ double zWidth;
        public final /* synthetic */ int maxHealth;

        public Entry(class_1657 lllllllllllllllllllIlllIlllIlllI) {
            Entry lllllllllllllllllllIlllIlllIllIl;
            lllllllllllllllllllIlllIlllIllIl.x = lllllllllllllllllllIlllIlllIlllI.method_23317();
            lllllllllllllllllllIlllIlllIllIl.y = lllllllllllllllllllIlllIlllIlllI.method_23318();
            lllllllllllllllllllIlllIlllIllIl.z = lllllllllllllllllllIlllIlllIlllI.method_23321();
            lllllllllllllllllllIlllIlllIllIl.xWidth = lllllllllllllllllllIlllIlllIlllI.method_5829().method_17939();
            lllllllllllllllllllIlllIlllIllIl.zWidth = lllllllllllllllllllIlllIlllIlllI.method_5829().method_17941();
            lllllllllllllllllllIlllIlllIllIl.height = lllllllllllllllllllIlllIlllIlllI.method_5829().method_17940();
            lllllllllllllllllllIlllIlllIllIl.uuid = lllllllllllllllllllIlllIlllIlllI.method_5667();
            lllllllllllllllllllIlllIlllIllIl.name = lllllllllllllllllllIlllIlllIlllI.method_5820();
            lllllllllllllllllllIlllIlllIllIl.health = Math.round(lllllllllllllllllllIlllIlllIlllI.method_6032() + lllllllllllllllllllIlllIlllIlllI.method_6067());
            lllllllllllllllllllIlllIlllIllIl.maxHealth = Math.round(lllllllllllllllllllIlllIlllIlllI.method_6063() + lllllllllllllllllllIlllIlllIlllI.method_6067());
            lllllllllllllllllllIlllIlllIllIl.healthText = String.valueOf(new StringBuilder().append(" ").append(lllllllllllllllllllIlllIlllIllIl.health));
        }

        public void render(RenderEvent lllllllllllllllllllIlllIllIllllI) {
            Color lllllllllllllllllllIlllIllIllIIl;
            Entry lllllllllllllllllllIlllIllIlllll;
            class_4184 lllllllllllllllllllIlllIllIlllIl = ((LogoutSpots)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this).mc.field_1773.method_19418();
            double lllllllllllllllllllIlllIllIlllII = 0.025;
            double lllllllllllllllllllIlllIllIllIll = PlayerUtils.distanceToCamera(lllllllllllllllllllIlllIllIlllll.x, lllllllllllllllllllIlllIllIlllll.y, lllllllllllllllllllIlllIllIlllll.z);
            if (lllllllllllllllllllIlllIllIllIll > 8.0) {
                lllllllllllllllllllIlllIllIlllII *= lllllllllllllllllllIlllIllIllIll / 8.0 * (Double)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.scale.get();
            }
            if (lllllllllllllllllllIlllIllIllIll > (double)(((LogoutSpots)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this).mc.field_1690.field_1870 * 16)) {
                return;
            }
            double lllllllllllllllllllIlllIllIllIlI = (double)lllllllllllllllllllIlllIllIlllll.health / (double)lllllllllllllllllllIlllIllIlllll.maxHealth;
            if (((Boolean)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.fullHeight.get()).booleanValue()) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllllIlllIllIlllll.x, lllllllllllllllllllIlllIllIlllll.y, lllllllllllllllllllIlllIllIlllll.z, lllllllllllllllllllIlllIllIlllll.x + lllllllllllllllllllIlllIllIlllll.xWidth, lllllllllllllllllllIlllIllIlllll.y + lllllllllllllllllllIlllIllIlllll.height, lllllllllllllllllllIlllIllIlllll.z + lllllllllllllllllllIlllIllIlllll.zWidth, (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.sideColor.get(), (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.lineColor.get(), (ShapeMode)((Object)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.shapeMode.get()), 0);
            } else {
                Renderer.quadWithLinesHorizontal(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllllIlllIllIlllll.x, lllllllllllllllllllIlllIllIlllll.y, lllllllllllllllllllIlllIllIlllll.z, lllllllllllllllllllIlllIllIlllll.xWidth, (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.sideColor.get(), (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.lineColor.get(), (ShapeMode)((Object)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.shapeMode.get()));
            }
            if (lllllllllllllllllllIlllIllIllIlI <= 0.333) {
                Color lllllllllllllllllllIlllIlllIIIIl = RED;
            } else if (lllllllllllllllllllIlllIllIllIlI <= 0.666) {
                Color lllllllllllllllllllIlllIlllIIIII = ORANGE;
            } else {
                lllllllllllllllllllIlllIllIllIIl = GREEN;
            }
            Matrices.push();
            Matrices.translate(lllllllllllllllllllIlllIllIlllll.x + lllllllllllllllllllIlllIllIlllll.xWidth / 2.0 - lllllllllllllllllllIlllIllIllllI.offsetX, lllllllllllllllllllIlllIllIlllll.y + ((Boolean)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.fullHeight.get() != false ? lllllllllllllllllllIlllIllIlllll.height + 0.5 : 0.5) - lllllllllllllllllllIlllIllIllllI.offsetY, lllllllllllllllllllIlllIllIlllll.z + lllllllllllllllllllIlllIllIlllll.zWidth / 2.0 - lllllllllllllllllllIlllIllIllllI.offsetZ);
            Matrices.rotate(-lllllllllllllllllllIlllIllIlllIl.method_19330(), 0.0, 1.0, 0.0);
            Matrices.rotate(lllllllllllllllllllIlllIllIlllIl.method_19329(), 1.0, 0.0, 0.0);
            Matrices.scale(-lllllllllllllllllllIlllIllIlllII, -lllllllllllllllllllIlllIllIlllII, lllllllllllllllllllIlllIllIlllII);
            double lllllllllllllllllllIlllIllIllIII = TextRenderer.get().getWidth(lllllllllllllllllllIlllIllIlllll.name) / 2.0 + TextRenderer.get().getWidth(lllllllllllllllllllIlllIllIlllll.healthText) / 2.0;
            MB.begin(null, DrawMode.Triangles, class_290.field_1576);
            MB.quad(-lllllllllllllllllllIlllIllIllIII - 1.0, -1.0, 0.0, -lllllllllllllllllllIlllIllIllIII - 1.0, 8.0, 0.0, lllllllllllllllllllIlllIllIllIII + 1.0, 8.0, 0.0, lllllllllllllllllllIlllIllIllIII + 1.0, -1.0, 0.0, (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.nameBackgroundColor.get());
            MB.end();
            TextRenderer.get().begin(1.0, false, true);
            double lllllllllllllllllllIlllIllIlIlll = TextRenderer.get().render(lllllllllllllllllllIlllIllIlllll.name, -lllllllllllllllllllIlllIllIllIII, 0.0, (Color)lllllllllllllllllllIlllIllIlllll.LogoutSpots.this.nameColor.get());
            TextRenderer.get().render(lllllllllllllllllllIlllIllIlllll.healthText, lllllllllllllllllllIlllIllIlIlll, 0.0, lllllllllllllllllllIlllIllIllIIl);
            TextRenderer.get().end();
            Matrices.pop();
        }
    }
}

