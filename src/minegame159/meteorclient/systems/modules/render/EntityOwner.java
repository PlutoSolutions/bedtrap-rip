/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  net.minecraft.class_1297
 *  net.minecraft.class_1321
 *  net.minecraft.class_1496
 *  net.minecraft.class_1657
 *  net.minecraft.class_1676
 *  net.minecraft.class_290
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
    private static final /* synthetic */ MeshBuilder MB;
    private final /* synthetic */ Setting<Boolean> projectiles;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;
    private static final /* synthetic */ Color TEXT;
    private static final /* synthetic */ Color BACKGROUND;
    private static final /* synthetic */ Type RESPONSE_TYPE;
    private final /* synthetic */ Map<UUID, String> uuidToName;
    private final /* synthetic */ Vec3 pos;

    @Override
    public void onDeactivate() {
        EntityOwner lIIIIllIIIIlIlI;
        lIIIIllIIIIlIlI.uuidToName.clear();
    }

    public EntityOwner() {
        super(Categories.Render, "entity-owner", "Displays the name of the player who owns the entity you're looking at.");
        EntityOwner lIIIIllIIIIllIl;
        lIIIIllIIIIllIl.sgGeneral = lIIIIllIIIIllIl.settings.getDefaultGroup();
        lIIIIllIIIIllIl.scale = lIIIIllIIIIllIl.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of the text.").defaultValue(1.0).min(0.0).build());
        lIIIIllIIIIllIl.projectiles = lIIIIllIIIIllIl.sgGeneral.add(new BoolSetting.Builder().name("projectiles").description("Display owner names of projectiles.").defaultValue(false).build());
        lIIIIllIIIIllIl.pos = new Vec3();
        lIIIIllIIIIllIl.uuidToName = new HashMap<UUID, String>();
    }

    private String getOwnerName(UUID lIIIIlIllIllllI) {
        EntityOwner lIIIIlIllIlllll;
        class_1657 lIIIIlIlllIIIIl = lIIIIlIllIlllll.mc.field_1687.method_18470(lIIIIlIllIllllI);
        if (lIIIIlIlllIIIIl != null) {
            return lIIIIlIlllIIIIl.method_5820();
        }
        String lIIIIlIlllIIIII = lIIIIlIllIlllll.uuidToName.get(lIIIIlIllIllllI);
        if (lIIIIlIlllIIIII != null) {
            return lIIIIlIlllIIIII;
        }
        MeteorExecutor.execute(() -> {
            EntityOwner lIIIIlIllIlIlll;
            if (lIIIIlIllIlIlll.isActive()) {
                List lIIIIlIllIllIII = (List)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(lIIIIlIllIllllI.toString().replace("-", "")).append("/names")), RESPONSE_TYPE);
                if (lIIIIlIllIlIlll.isActive()) {
                    if (lIIIIlIllIllIII == null || lIIIIlIllIllIII.size() <= 0) {
                        lIIIIlIllIlIlll.uuidToName.put(lIIIIlIllIllllI, "Failed to get name");
                    } else {
                        lIIIIlIllIlIlll.uuidToName.put(lIIIIlIllIllllI, ((UuidNameHistoryResponseItem)lIIIIlIllIllIII.get((int)(lIIIIlIllIllIII.size() - 1))).name);
                    }
                }
            }
        });
        lIIIIlIlllIIIII = "Retrieving";
        lIIIIlIllIlllll.uuidToName.put(lIIIIlIllIllllI, lIIIIlIlllIIIII);
        return lIIIIlIlllIIIII;
    }

    @EventHandler
    private void onRender2D(Render2DEvent lIIIIlIllllllIl) {
        EntityOwner lIIIIllIIIIIIII;
        for (class_1297 lIIIIllIIIIIIIl : lIIIIllIIIIIIII.mc.field_1687.method_18112()) {
            UUID lIIIIllIIIIIIlI;
            if (lIIIIllIIIIIIIl instanceof class_1321) {
                UUID lIIIIllIIIIIlII = ((class_1321)lIIIIllIIIIIIIl).method_6139();
            } else if (lIIIIllIIIIIIIl instanceof class_1496) {
                UUID lIIIIllIIIIIIll = ((class_1496)lIIIIllIIIIIIIl).method_6768();
            } else {
                if (!(lIIIIllIIIIIIIl instanceof class_1676) || !lIIIIllIIIIIIII.projectiles.get().booleanValue()) continue;
                lIIIIllIIIIIIlI = ((ProjectileEntityAccessor)lIIIIllIIIIIIIl).getOwnerUuid();
            }
            if (lIIIIllIIIIIIlI == null) continue;
            lIIIIllIIIIIIII.pos.set(lIIIIllIIIIIIIl, lIIIIlIllllllIl.tickDelta);
            lIIIIllIIIIIIII.pos.add(0.0, (double)lIIIIllIIIIIIIl.method_18381(lIIIIllIIIIIIIl.method_18376()) + 0.75, 0.0);
            if (!NametagUtils.to2D(lIIIIllIIIIIIII.pos, lIIIIllIIIIIIII.scale.get())) continue;
            lIIIIllIIIIIIII.renderNametag(lIIIIllIIIIIIII.getOwnerName(lIIIIllIIIIIIlI));
        }
    }

    static {
        MB = new MeshBuilder(128);
        BACKGROUND = new Color(0, 0, 0, 75);
        TEXT = new Color(255, 255, 255);
        RESPONSE_TYPE = new TypeToken<List<UuidNameHistoryResponseItem>>(){
            {
                1 lIIllIIIllIllII;
            }
        }.getType();
    }

    private void renderNametag(String lIIIIlIllllIIlI) {
        EntityOwner lIIIIlIllllIIll;
        TextRenderer lIIIIlIllllIIIl = TextRenderer.get();
        NametagUtils.begin(lIIIIlIllllIIll.pos);
        lIIIIlIllllIIIl.beginBig();
        double lIIIIlIllllIIII = lIIIIlIllllIIIl.getWidth(lIIIIlIllllIIlI);
        double lIIIIlIlllIllll = -lIIIIlIllllIIII / 2.0;
        double lIIIIlIlllIlllI = -lIIIIlIllllIIIl.getHeight();
        MB.begin(null, DrawMode.Triangles, class_290.field_1576);
        MB.quad(lIIIIlIlllIllll - 1.0, lIIIIlIlllIlllI - 1.0, lIIIIlIllllIIII + 2.0, lIIIIlIllllIIIl.getHeight() + 2.0, BACKGROUND);
        MB.end();
        lIIIIlIllllIIIl.render(lIIIIlIllllIIlI, lIIIIlIlllIllll, lIIIIlIlllIlllI, TEXT);
        lIIIIlIllllIIIl.end();
        NametagUtils.end();
    }

    public static class UuidNameHistoryResponseItem {
        public /* synthetic */ String name;

        public UuidNameHistoryResponseItem() {
            UuidNameHistoryResponseItem lIIIllIIIlIlIl;
        }
    }
}

