/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.mixin.ProjectileEntityAccessor;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.render.NametagUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1321;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_290;

public class EntityOwner
extends Module {
    private static final MeshBuilder MB = new MeshBuilder(128);
    private final Setting<Boolean> projectiles;
    private final Setting<Double> scale;
    private final SettingGroup sgGeneral;
    private static final Color TEXT;
    private static final Color BACKGROUND;
    private static final Type RESPONSE_TYPE;
    private final Map<UUID, String> uuidToName;
    private final Vec3 pos;

    @Override
    public void onDeactivate() {
        this.uuidToName.clear();
    }

    public EntityOwner() {
        super(Categories.Render, "entity-owner", "Displays the name of the player who owns the entity you're looking at.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of the text.").defaultValue(1.0).min(0.0).build());
        this.projectiles = this.sgGeneral.add(new BoolSetting.Builder().name("projectiles").description("Display owner names of projectiles.").defaultValue(false).build());
        this.pos = new Vec3();
        this.uuidToName = new HashMap<UUID, String>();
    }

    private String getOwnerName(UUID uUID) {
        class_1657 class_16572 = this.mc.field_1687.method_18470(uUID);
        if (class_16572 != null) {
            return class_16572.method_5820();
        }
        String string = this.uuidToName.get(uUID);
        if (string != null) {
            return string;
        }
        MeteorExecutor.execute(() -> this.lambda$getOwnerName$0(uUID));
        string = "Retrieving";
        this.uuidToName.put(uUID, string);
        return string;
    }

    @EventHandler
    private void onRender2D(Render2DEvent render2DEvent) {
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            UUID uUID;
            if (class_12972 instanceof class_1321) {
                uUID = ((class_1321)class_12972).method_6139();
            } else if (class_12972 instanceof class_1496) {
                uUID = ((class_1496)class_12972).method_6768();
            } else {
                if (!(class_12972 instanceof class_1676) || !this.projectiles.get().booleanValue()) continue;
                uUID = ((ProjectileEntityAccessor)class_12972).getOwnerUuid();
            }
            if (uUID == null) continue;
            this.pos.set(class_12972, render2DEvent.tickDelta);
            this.pos.add(0.0, (double)class_12972.method_18381(class_12972.method_18376()) + 0.75, 0.0);
            if (!NametagUtils.to2D(this.pos, this.scale.get())) continue;
            this.renderNametag(this.getOwnerName(uUID));
        }
    }

    static {
        BACKGROUND = new Color(0, 0, 0, 75);
        TEXT = new Color(255, 255, 255);
        RESPONSE_TYPE = new TypeToken<List<UuidNameHistoryResponseItem>>(){}.getType();
    }

    private void renderNametag(String string) {
        TextRenderer textRenderer = TextRenderer.get();
        NametagUtils.begin(this.pos);
        textRenderer.beginBig();
        double d = textRenderer.getWidth(string);
        double d2 = -d / 2.0;
        double d3 = -textRenderer.getHeight();
        MB.begin(null, DrawMode.Triangles, class_290.field_1576);
        MB.quad(d2 - 1.0, d3 - 1.0, d + 2.0, textRenderer.getHeight() + 2.0, BACKGROUND);
        MB.end();
        textRenderer.render(string, d2, d3, TEXT);
        textRenderer.end();
        NametagUtils.end();
    }

    private void lambda$getOwnerName$0(UUID uUID) {
        if (this.isActive()) {
            List list = (List)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(uUID.toString().replace("-", "")).append("/names")), RESPONSE_TYPE);
            if (this.isActive()) {
                if (list == null || list.size() <= 0) {
                    this.uuidToName.put(uUID, "Failed to get name");
                } else {
                    this.uuidToName.put(uUID, ((UuidNameHistoryResponseItem)list.get((int)(list.size() - 1))).name);
                }
            }
        }
    }

    public static class UuidNameHistoryResponseItem {
        public String name;
    }
}

