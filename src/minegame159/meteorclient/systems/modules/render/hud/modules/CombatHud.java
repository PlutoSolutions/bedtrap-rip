/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1748
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1829
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_2378
 *  net.minecraft.class_290
 *  net.minecraft.class_3532
 *  net.minecraft.class_490
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnchListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.misc.FakeClientPlayer;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1748;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_2378;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_490;

public class CombatHud
extends HudElement {
    private final /* synthetic */ Setting<SettingColor> healthColor2;
    private final /* synthetic */ Setting<SettingColor> distColor1;
    private final /* synthetic */ Setting<SettingColor> pingColor3;
    private final /* synthetic */ Setting<List<class_1887>> displayedEnchantments;
    private final /* synthetic */ Setting<SettingColor> pingColor2;
    private static final /* synthetic */ Color BLACK;
    private final /* synthetic */ Setting<SettingColor> healthColor3;
    private final /* synthetic */ Setting<SettingColor> backgroundColor;
    private static final /* synthetic */ Color GREEN;
    private static final /* synthetic */ Color WHITE;
    private /* synthetic */ class_1657 playerEntity;
    private final /* synthetic */ Setting<Boolean> displayDistance;
    private final /* synthetic */ Setting<SettingColor> distColor2;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> displayPing;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Setting<SettingColor> pingColor1;
    private final /* synthetic */ Setting<SettingColor> enchantmentTextColor;
    private final /* synthetic */ Setting<Double> scale;
    private static final /* synthetic */ Color RED;
    private final /* synthetic */ Setting<SettingColor> distColor3;
    private final /* synthetic */ Setting<SettingColor> healthColor1;

    public CombatHud(HUD lIIllllllIllII) {
        super(lIIllllllIllII, "combat-info", "Displays information about your combat target.", false);
        CombatHud lIIllllllIllIl;
        lIIllllllIllIl.sgGeneral = lIIllllllIllIl.settings.getDefaultGroup();
        lIIllllllIllIl.scale = lIIllllllIllIl.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of combat info.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(5.0).build());
        lIIllllllIllIl.range = lIIllllllIllIl.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The range to target players.").defaultValue(100.0).min(1.0).sliderMax(200.0).build());
        lIIllllllIllIl.displayPing = lIIllllllIllIl.sgGeneral.add(new BoolSetting.Builder().name("ping").description("Shows the player's ping.").defaultValue(true).build());
        lIIllllllIllIl.displayDistance = lIIllllllIllIl.sgGeneral.add(new BoolSetting.Builder().name("distance").description("Shows the distance between you and the player.").defaultValue(true).build());
        lIIllllllIllIl.displayedEnchantments = lIIllllllIllIl.sgGeneral.add(new EnchListSetting.Builder().name("displayed-enchantments").description("The enchantments that are shown on nametags.").defaultValue(CombatHud.getDefaultEnchantments()).build());
        lIIllllllIllIl.backgroundColor = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of background.").defaultValue(new SettingColor(0, 0, 0, 64)).build());
        lIIllllllIllIl.enchantmentTextColor = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("enchantment-color").description("Color of enchantment text.").defaultValue(new SettingColor(255, 255, 255)).build());
        lIIllllllIllIl.pingColor1 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-1").description("Color of ping text when under 75.").defaultValue(new SettingColor(15, 255, 15)).visible(lIIllllllIllIl.displayPing::get).build());
        lIIllllllIllIl.pingColor2 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-2").description("Color of ping text when between 75 and 200.").defaultValue(new SettingColor(255, 150, 15)).visible(lIIllllllIllIl.displayPing::get).build());
        lIIllllllIllIl.pingColor3 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-3").description("Color of ping text when over 200.").defaultValue(new SettingColor(255, 15, 15)).visible(lIIllllllIllIl.displayPing::get).build());
        lIIllllllIllIl.distColor1 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-1").description("The color when a player is within 10 blocks of you.").defaultValue(new SettingColor(255, 15, 15)).visible(lIIllllllIllIl.displayDistance::get).build());
        lIIllllllIllIl.distColor2 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-2").description("The color when a player is within 50 blocks of you.").defaultValue(new SettingColor(255, 150, 15)).visible(lIIllllllIllIl.displayDistance::get).build());
        lIIllllllIllIl.distColor3 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-3").description("The color when a player is greater then 50 blocks away from you.").defaultValue(new SettingColor(15, 255, 15)).visible(lIIllllllIllIl.displayDistance::get).build());
        lIIllllllIllIl.healthColor1 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("healh-stage-1").description("The color on the left of the health gradient.").defaultValue(new SettingColor(255, 15, 15)).build());
        lIIllllllIllIl.healthColor2 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("health-stage-2").description("The color in the middle of the health gradient.").defaultValue(new SettingColor(255, 150, 15)).build());
        lIIllllllIllIl.healthColor3 = lIIllllllIllIl.sgGeneral.add(new ColorSetting.Builder().name("health-stage-3").description("The color on the right of the health gradient.").defaultValue(new SettingColor(15, 255, 15)).build());
    }

    static {
        WHITE = new Color(255, 255, 255);
        GREEN = new Color(15, 255, 15);
        RED = new Color(255, 15, 15);
        BLACK = new Color(0, 0, 0, 255);
    }

    public static List<class_1887> getDefaultEnchantments() {
        ArrayList<class_1887> lIIlllllIlIlll = new ArrayList<class_1887>();
        for (class_1887 lIIlllllIllIII : class_2378.field_11160) {
            lIIlllllIlIlll.add(lIIlllllIllIII);
        }
        return lIIlllllIlIlll;
    }

    private class_1799 getItem(int lIIlllllIlllII) {
        CombatHud lIIlllllIlllll;
        if (lIIlllllIlllll.isInEditor()) {
            switch (lIIlllllIlllII) {
                case 0: {
                    return class_1802.field_8301.method_7854();
                }
                case 1: {
                    return class_1802.field_22030.method_7854();
                }
                case 2: {
                    return class_1802.field_22029.method_7854();
                }
                case 3: {
                    return class_1802.field_22028.method_7854();
                }
                case 4: {
                    return class_1802.field_22027.method_7854();
                }
                case 5: {
                    return class_1802.field_8288.method_7854();
                }
            }
        }
        if (lIIlllllIlllll.playerEntity == null) {
            return class_1799.field_8037;
        }
        if (lIIlllllIlllII == 5) {
            return lIIlllllIlllll.playerEntity.method_6047();
        }
        if (lIIlllllIlllII == 4) {
            return lIIlllllIlllll.playerEntity.method_6079();
        }
        return lIIlllllIlllll.playerEntity.field_7514.method_7372(lIIlllllIlllII);
    }

    @Override
    public void render(HudRenderer lIIllllllIIlII) {
        CombatHud lIIllllllIIIll;
        lIIllllllIIlII.addPostTask(() -> {
            Color lIIllllIIlIIII;
            Color lIIllllIIlIIll;
            CombatHud lIIlllIllllllI;
            double lIIllllIIllIlI = lIIlllIllllllI.box.getX();
            double lIIllllIIllIIl = lIIlllIllllllI.box.getY();
            lIIlllIllllllI.playerEntity = lIIlllIllllllI.isInEditor() ? FakeClientPlayer.getPlayer() : TargetUtils.getPlayerTarget(lIIlllIllllllI.range.get(), SortPriority.LowestDistance);
            if (lIIlllIllllllI.playerEntity == null) {
                return;
            }
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            Renderer.NORMAL.quad(lIIllllIIllIlI, lIIllllIIllIIl, lIIlllIllllllI.box.width, lIIlllIllllllI.box.height, lIIlllIllllllI.backgroundColor.get());
            Renderer.NORMAL.end();
            class_490.method_2486((int)((int)(lIIllllIIllIlI + 25.0 * lIIlllIllllllI.scale.get())), (int)((int)(lIIllllIIllIIl + 66.0 * lIIlllIllllllI.scale.get())), (int)((int)(30.0 * lIIlllIllllllI.scale.get())), (float)(-class_3532.method_15393((float)(lIIlllIllllllI.playerEntity.field_5982 + (lIIlllIllllllI.playerEntity.field_6031 - lIIlllIllllllI.playerEntity.field_5982) * lIIlllIllllllI.mc.method_1488()))), (float)(-lIIlllIllllllI.playerEntity.field_5965), (class_1309)lIIlllIllllllI.playerEntity);
            lIIllllIIllIlI += 50.0 * lIIlllIllllllI.scale.get();
            lIIllllIIllIIl += 5.0 * lIIlllIllllllI.scale.get();
            String lIIllllIIllIII = " | ";
            String lIIllllIIlIlll = lIIlllIllllllI.playerEntity.method_5820();
            Color lIIllllIIlIllI = PlayerUtils.getPlayerColor(lIIlllIllllllI.playerEntity, lIIlllIllllllI.hud.primaryColor.get());
            int lIIllllIIlIlIl = EntityUtils.getPing(lIIlllIllllllI.playerEntity);
            String lIIllllIIlIlII = String.valueOf(new StringBuilder().append(lIIllllIIlIlIl).append("ms"));
            if (lIIllllIIlIlIl <= 75) {
                Color lIIllllIlIllll = lIIlllIllllllI.pingColor1.get();
            } else if (lIIllllIIlIlIl <= 200) {
                Color lIIllllIlIlllI = lIIlllIllllllI.pingColor2.get();
            } else {
                lIIllllIIlIIll = lIIlllIllllllI.pingColor3.get();
            }
            double lIIllllIIlIIlI = 0.0;
            if (!lIIlllIllllllI.isInEditor()) {
                lIIllllIIlIIlI = (double)Math.round((double)lIIlllIllllllI.mc.field_1724.method_5739((class_1297)lIIlllIllllllI.playerEntity) * 100.0) / 100.0;
            }
            String lIIllllIIlIIIl = String.valueOf(new StringBuilder().append(lIIllllIIlIIlI).append("m"));
            if (lIIllllIIlIIlI <= 10.0) {
                Color lIIllllIlIllIl = lIIlllIllllllI.distColor1.get();
            } else if (lIIllllIIlIIlI <= 50.0) {
                Color lIIllllIlIllII = lIIlllIllllllI.distColor2.get();
            } else {
                lIIllllIIlIIII = lIIlllIllllllI.distColor3.get();
            }
            String lIIllllIIIllll = "Unknown";
            Color lIIllllIIIlllI = lIIlllIllllllI.hud.primaryColor.get();
            if (Friends.get().isFriend(lIIlllIllllllI.playerEntity)) {
                lIIllllIIIllll = "Friend";
                lIIllllIIIlllI = Friends.get().color;
            } else {
                boolean lIIllllIlIIllI = true;
                for (int lIIllllIlIlIlI = 3; lIIllllIlIlIlI >= 0; --lIIllllIlIlIlI) {
                    class_1799 lIIllllIlIlIll = lIIlllIllllllI.getItem(lIIllllIlIlIlI);
                    if (lIIllllIlIlIll.method_7960()) continue;
                    lIIllllIlIIllI = false;
                }
                if (lIIllllIlIIllI) {
                    lIIllllIIIllll = "Naked";
                    lIIllllIIIlllI = GREEN;
                } else {
                    boolean lIIllllIlIIlll = false;
                    for (int lIIllllIlIlIII = 5; lIIllllIlIlIII >= 0; --lIIllllIlIlIII) {
                        class_1799 lIIllllIlIlIIl = lIIlllIllllllI.getItem(lIIllllIlIlIII);
                        if (!(lIIllllIlIlIIl.method_7909() instanceof class_1829) && lIIllllIlIlIIl.method_7909() != class_1802.field_8301 && lIIllllIlIlIIl.method_7909() != class_1802.field_23141 && !(lIIllllIlIlIIl.method_7909() instanceof class_1748)) continue;
                        lIIllllIlIIlll = true;
                    }
                    if (lIIllllIlIIlll) {
                        lIIllllIIIllll = "Threat";
                        lIIllllIIIlllI = RED;
                    }
                }
            }
            TextRenderer.get().begin(0.45 * lIIlllIllllllI.scale.get(), false, true);
            double lIIllllIIIllIl = TextRenderer.get().getWidth(lIIllllIIllIII);
            double lIIllllIIIllII = TextRenderer.get().getWidth(lIIllllIIlIlII);
            double lIIllllIIIlIll = TextRenderer.get().getWidth(lIIllllIIIllll);
            TextRenderer.get().render(lIIllllIIlIlll, lIIllllIIllIlI, lIIllllIIllIIl, lIIllllIIlIllI != null ? lIIllllIIlIllI : (Color)lIIlllIllllllI.hud.primaryColor.get());
            TextRenderer.get().render(lIIllllIIIllll, lIIllllIIllIlI, lIIllllIIllIIl += TextRenderer.get().getHeight(), lIIllllIIIlllI);
            if (lIIlllIllllllI.displayPing.get().booleanValue()) {
                TextRenderer.get().render(lIIllllIIllIII, lIIllllIIllIlI + lIIllllIIIlIll, lIIllllIIllIIl, lIIlllIllllllI.hud.secondaryColor.get());
                TextRenderer.get().render(lIIllllIIlIlII, lIIllllIIllIlI + lIIllllIIIlIll + lIIllllIIIllIl, lIIllllIIllIIl, lIIllllIIlIIll);
                if (lIIlllIllllllI.displayDistance.get().booleanValue()) {
                    TextRenderer.get().render(lIIllllIIllIII, lIIllllIIllIlI + lIIllllIIIlIll + lIIllllIIIllIl + lIIllllIIIllII, lIIllllIIllIIl, lIIlllIllllllI.hud.secondaryColor.get());
                    TextRenderer.get().render(lIIllllIIlIIIl, lIIllllIIllIlI + lIIllllIIIlIll + lIIllllIIIllIl + lIIllllIIIllII + lIIllllIIIllIl, lIIllllIIllIIl, lIIllllIIlIIII);
                }
            } else if (lIIlllIllllllI.displayDistance.get().booleanValue()) {
                TextRenderer.get().render(lIIllllIIllIII, lIIllllIIllIlI + lIIllllIIIlIll, lIIllllIIllIIl, lIIlllIllllllI.hud.secondaryColor.get());
                TextRenderer.get().render(lIIllllIIlIIIl, lIIllllIIllIlI + lIIllllIIIlIll + lIIllllIIIllIl, lIIllllIIllIIl, lIIllllIIlIIII);
            }
            TextRenderer.get().end();
            lIIllllIIllIIl += 10.0 * lIIlllIllllllI.scale.get();
            int lIIllllIIIlIlI = 5;
            RenderSystem.pushMatrix();
            RenderSystem.scaled((double)lIIlllIllllllI.scale.get(), (double)lIIlllIllllllI.scale.get(), (double)1.0);
            lIIllllIIllIlI /= lIIlllIllllllI.scale.get().doubleValue();
            lIIllllIIllIIl /= lIIlllIllllllI.scale.get().doubleValue();
            TextRenderer.get().begin(0.35, false, true);
            for (int lIIllllIIlllII = 0; lIIllllIIlllII < 6; ++lIIllllIIlllII) {
                double lIIllllIIllllI = lIIllllIIllIlI + (double)(lIIllllIIlllII * 20);
                double lIIllllIIlllIl = lIIllllIIllIIl;
                class_1799 lIIllllIlIIIIl = lIIlllIllllllI.getItem(lIIllllIIIlIlI);
                RenderUtils.drawItem(lIIllllIlIIIIl, (int)lIIllllIIllllI, (int)lIIllllIIlllIl, true);
                lIIllllIIlllIl += 18.0;
                Map lIIllllIlIIIII = class_1890.method_8222((class_1799)lIIllllIlIIIIl);
                HashMap<class_1887, Integer> lIIllllIIlllll = new HashMap<class_1887, Integer>();
                for (class_1887 lIIllllIlIIlIl : lIIlllIllllllI.displayedEnchantments.get()) {
                    if (!lIIllllIlIIIII.containsKey((Object)lIIllllIlIIlIl)) continue;
                    lIIllllIIlllll.put(lIIllllIlIIlIl, (Integer)lIIllllIlIIIII.get((Object)lIIllllIlIIlIl));
                }
                for (class_1887 lIIllllIlIIIlI : lIIllllIIlllll.keySet()) {
                    String lIIllllIlIIlII = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(lIIllllIlIIIlI, 3)).append(" ").append(lIIllllIIlllll.get((Object)lIIllllIlIIIlI)));
                    double lIIllllIlIIIll = lIIllllIIllllI + 8.0 - TextRenderer.get().getWidth(lIIllllIlIIlII) / 2.0;
                    TextRenderer.get().render(lIIllllIlIIlII, lIIllllIlIIIll, lIIllllIIlllIl, lIIllllIlIIIlI.method_8195() ? RED : (Color)lIIlllIllllllI.enchantmentTextColor.get());
                    lIIllllIIlllIl += TextRenderer.get().getHeight();
                }
                --lIIllllIIIlIlI;
            }
            TextRenderer.get().end();
            RenderSystem.popMatrix();
            lIIllllIIllIIl = (int)(lIIlllIllllllI.box.getY() + 75.0 * lIIlllIllllllI.scale.get());
            lIIllllIIllIlI = lIIlllIllllllI.box.getX();
            RenderSystem.pushMatrix();
            RenderSystem.scaled((double)lIIlllIllllllI.scale.get(), (double)lIIlllIllllllI.scale.get(), (double)1.0);
            lIIllllIIllIlI /= lIIlllIllllllI.scale.get().doubleValue();
            lIIllllIIllIIl /= lIIlllIllllllI.scale.get().doubleValue();
            Renderer.LINES.begin(null, DrawMode.Lines, class_290.field_1576);
            Renderer.LINES.boxEdges(lIIllllIIllIlI += 5.0, lIIllllIIllIIl += 5.0, 165.0, 11.0, BLACK);
            Renderer.LINES.end();
            float lIIllllIIIlIIl = lIIlllIllllllI.playerEntity.method_6063();
            int lIIllllIIIlIII = 16;
            int lIIllllIIIIlll = (int)(lIIllllIIIlIIl + (float)lIIllllIIIlIII);
            int lIIllllIIIIllI = (int)(161.0f * lIIllllIIIlIIl / (float)lIIllllIIIIlll);
            int lIIllllIIIIlIl = 161 * lIIllllIIIlIII / lIIllllIIIIlll;
            float lIIllllIIIIlII = lIIlllIllllllI.playerEntity.method_6032();
            float lIIllllIIIIIll = lIIlllIllllllI.playerEntity.method_6067();
            double lIIllllIIIIIlI = lIIllllIIIIlII / lIIllllIIIlIIl;
            double lIIllllIIIIIIl = lIIllllIIIIIll / (float)lIIllllIIIlIII;
            int lIIllllIIIIIII = (int)((double)lIIllllIIIIllI * lIIllllIIIIIlI);
            int lIIlllIlllllll = (int)((double)lIIllllIIIIlIl * lIIllllIIIIIIl);
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            Renderer.NORMAL.horizontalGradientQuad(lIIllllIIllIlI += 2.0, lIIllllIIllIIl += 2.0, lIIllllIIIIIII, 7.0, lIIlllIllllllI.healthColor1.get(), lIIlllIllllllI.healthColor2.get());
            Renderer.NORMAL.horizontalGradientQuad(lIIllllIIllIlI + (double)lIIllllIIIIIII, lIIllllIIllIIl, lIIlllIlllllll, 7.0, lIIlllIllllllI.healthColor2.get(), lIIlllIllllllI.healthColor3.get());
            Renderer.NORMAL.end();
            RenderSystem.popMatrix();
        });
    }

    @Override
    public void update(HudRenderer lIIllllllIlIIl) {
        CombatHud lIIllllllIlIII;
        lIIllllllIlIII.box.setSize(175.0 * lIIllllllIlIII.scale.get(), 95.0 * lIIllllllIlIII.scale.get());
    }
}

