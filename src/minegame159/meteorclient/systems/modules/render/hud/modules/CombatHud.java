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
    private final Setting<SettingColor> healthColor2;
    private final Setting<SettingColor> distColor1;
    private final Setting<SettingColor> pingColor3;
    private final Setting<List<class_1887>> displayedEnchantments;
    private final Setting<SettingColor> pingColor2;
    private static final Color BLACK;
    private final Setting<SettingColor> healthColor3;
    private final Setting<SettingColor> backgroundColor;
    private static final Color GREEN;
    private static final Color WHITE;
    private class_1657 playerEntity;
    private final Setting<Boolean> displayDistance;
    private final Setting<SettingColor> distColor2;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> displayPing;
    private final Setting<Double> range;
    private final Setting<SettingColor> pingColor1;
    private final Setting<SettingColor> enchantmentTextColor;
    private final Setting<Double> scale;
    private static final Color RED;
    private final Setting<SettingColor> distColor3;
    private final Setting<SettingColor> healthColor1;

    public CombatHud(HUD hUD) {
        super(hUD, "combat-info", "Displays information about your combat target.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of combat info.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(5.0).build());
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The range to target players.").defaultValue(100.0).min(1.0).sliderMax(200.0).build());
        this.displayPing = this.sgGeneral.add(new BoolSetting.Builder().name("ping").description("Shows the player's ping.").defaultValue(true).build());
        this.displayDistance = this.sgGeneral.add(new BoolSetting.Builder().name("distance").description("Shows the distance between you and the player.").defaultValue(true).build());
        this.displayedEnchantments = this.sgGeneral.add(new EnchListSetting.Builder().name("displayed-enchantments").description("The enchantments that are shown on nametags.").defaultValue(CombatHud.getDefaultEnchantments()).build());
        this.backgroundColor = this.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of background.").defaultValue(new SettingColor(0, 0, 0, 64)).build());
        this.enchantmentTextColor = this.sgGeneral.add(new ColorSetting.Builder().name("enchantment-color").description("Color of enchantment text.").defaultValue(new SettingColor(255, 255, 255)).build());
        this.pingColor1 = this.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-1").description("Color of ping text when under 75.").defaultValue(new SettingColor(15, 255, 15)).visible(this.displayPing::get).build());
        this.pingColor2 = this.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-2").description("Color of ping text when between 75 and 200.").defaultValue(new SettingColor(255, 150, 15)).visible(this.displayPing::get).build());
        this.pingColor3 = this.sgGeneral.add(new ColorSetting.Builder().name("ping-stage-3").description("Color of ping text when over 200.").defaultValue(new SettingColor(255, 15, 15)).visible(this.displayPing::get).build());
        this.distColor1 = this.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-1").description("The color when a player is within 10 blocks of you.").defaultValue(new SettingColor(255, 15, 15)).visible(this.displayDistance::get).build());
        this.distColor2 = this.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-2").description("The color when a player is within 50 blocks of you.").defaultValue(new SettingColor(255, 150, 15)).visible(this.displayDistance::get).build());
        this.distColor3 = this.sgGeneral.add(new ColorSetting.Builder().name("distance-stage-3").description("The color when a player is greater then 50 blocks away from you.").defaultValue(new SettingColor(15, 255, 15)).visible(this.displayDistance::get).build());
        this.healthColor1 = this.sgGeneral.add(new ColorSetting.Builder().name("healh-stage-1").description("The color on the left of the health gradient.").defaultValue(new SettingColor(255, 15, 15)).build());
        this.healthColor2 = this.sgGeneral.add(new ColorSetting.Builder().name("health-stage-2").description("The color in the middle of the health gradient.").defaultValue(new SettingColor(255, 150, 15)).build());
        this.healthColor3 = this.sgGeneral.add(new ColorSetting.Builder().name("health-stage-3").description("The color on the right of the health gradient.").defaultValue(new SettingColor(15, 255, 15)).build());
    }

    private void lambda$render$0() {
        double d;
        double d2 = this.box.getX();
        double d3 = this.box.getY();
        this.playerEntity = this.isInEditor() ? FakeClientPlayer.getPlayer() : TargetUtils.getPlayerTarget(this.range.get(), SortPriority.LowestDistance);
        if (this.playerEntity == null) {
            return;
        }
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.quad(d2, d3, this.box.width, this.box.height, this.backgroundColor.get());
        Renderer.NORMAL.end();
        class_490.method_2486((int)((int)(d2 + 25.0 * this.scale.get())), (int)((int)(d3 + 66.0 * this.scale.get())), (int)((int)(30.0 * this.scale.get())), (float)(-class_3532.method_15393((float)(this.playerEntity.field_5982 + (this.playerEntity.field_6031 - this.playerEntity.field_5982) * this.mc.method_1488()))), (float)(-this.playerEntity.field_5965), (class_1309)this.playerEntity);
        d2 += 50.0 * this.scale.get();
        d3 += 5.0 * this.scale.get();
        String string = " | ";
        String string2 = this.playerEntity.method_5820();
        Color color = PlayerUtils.getPlayerColor(this.playerEntity, this.hud.primaryColor.get());
        int n = EntityUtils.getPing(this.playerEntity);
        String string3 = String.valueOf(new StringBuilder().append(n).append("ms"));
        Color color2 = n <= 75 ? (Color)this.pingColor1.get() : (n <= 200 ? (Color)this.pingColor2.get() : (Color)this.pingColor3.get());
        double d4 = 0.0;
        if (!this.isInEditor()) {
            d4 = (double)Math.round((double)this.mc.field_1724.method_5739((class_1297)this.playerEntity) * 100.0) / 100.0;
        }
        String string4 = String.valueOf(new StringBuilder().append(d4).append("m"));
        Color color3 = d4 <= 10.0 ? (Color)this.distColor1.get() : (d4 <= 50.0 ? (Color)this.distColor2.get() : (Color)this.distColor3.get());
        String string5 = "Unknown";
        Color color4 = this.hud.primaryColor.get();
        if (Friends.get().isFriend(this.playerEntity)) {
            string5 = "Friend";
            color4 = Friends.get().color;
        } else {
            int n2;
            boolean bl = true;
            for (n2 = 3; n2 >= 0; --n2) {
                class_1799 class_17992 = this.getItem(n2);
                if (class_17992.method_7960()) continue;
                bl = false;
                if (3 > 2) continue;
                return;
            }
            if (bl) {
                string5 = "Naked";
                color4 = GREEN;
            } else {
                n2 = 0;
                for (int i = 5; i >= 0; --i) {
                    class_1799 class_17993 = this.getItem(i);
                    if (!(class_17993.method_7909() instanceof class_1829) && class_17993.method_7909() != class_1802.field_8301 && class_17993.method_7909() != class_1802.field_23141 && !(class_17993.method_7909() instanceof class_1748)) continue;
                    n2 = 1;
                    if (-2 <= 0) continue;
                    return;
                }
                if (n2 != 0) {
                    string5 = "Threat";
                    color4 = RED;
                }
            }
        }
        TextRenderer.get().begin(0.45 * this.scale.get(), false, true);
        double d5 = TextRenderer.get().getWidth(string);
        double d6 = TextRenderer.get().getWidth(string3);
        double d7 = TextRenderer.get().getWidth(string5);
        TextRenderer.get().render(string2, d2, d3, color != null ? color : (Color)this.hud.primaryColor.get());
        TextRenderer.get().render(string5, d2, d3 += TextRenderer.get().getHeight(), color4);
        if (this.displayPing.get().booleanValue()) {
            TextRenderer.get().render(string, d2 + d7, d3, this.hud.secondaryColor.get());
            TextRenderer.get().render(string3, d2 + d7 + d5, d3, color2);
            if (this.displayDistance.get().booleanValue()) {
                TextRenderer.get().render(string, d2 + d7 + d5 + d6, d3, this.hud.secondaryColor.get());
                TextRenderer.get().render(string4, d2 + d7 + d5 + d6 + d5, d3, color3);
            }
        } else if (this.displayDistance.get().booleanValue()) {
            TextRenderer.get().render(string, d2 + d7, d3, this.hud.secondaryColor.get());
            TextRenderer.get().render(string4, d2 + d7 + d5, d3, color3);
        }
        TextRenderer.get().end();
        d3 += 10.0 * this.scale.get();
        int n3 = 5;
        RenderSystem.pushMatrix();
        RenderSystem.scaled((double)this.scale.get(), (double)this.scale.get(), (double)1.0);
        d2 /= this.scale.get().doubleValue();
        d3 /= this.scale.get().doubleValue();
        TextRenderer.get().begin(0.35, false, true);
        for (int i = 0; i < 6; ++i) {
            double d8 = d2 + (double)(i * 20);
            double d9 = d3;
            class_1799 class_17994 = this.getItem(n3);
            RenderUtils.drawItem(class_17994, (int)d8, (int)d9, true);
            d9 += 18.0;
            Map map = class_1890.method_8222((class_1799)class_17994);
            HashMap<class_1887, Integer> hashMap = new HashMap<class_1887, Integer>();
            for (class_1887 class_18872 : this.displayedEnchantments.get()) {
                if (!map.containsKey((Object)class_18872)) continue;
                hashMap.put(class_18872, (Integer)map.get((Object)class_18872));
            }
            for (class_1887 class_18872 : hashMap.keySet()) {
                String string6 = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(class_18872, 3)).append(" ").append(hashMap.get((Object)class_18872)));
                d = d8 + 8.0 - TextRenderer.get().getWidth(string6) / 2.0;
                TextRenderer.get().render(string6, d, d9, class_18872.method_8195() ? RED : (Color)this.enchantmentTextColor.get());
                d9 += TextRenderer.get().getHeight();
            }
            --n3;
            if (-4 < 0) continue;
            return;
        }
        TextRenderer.get().end();
        RenderSystem.popMatrix();
        d3 = (int)(this.box.getY() + 75.0 * this.scale.get());
        d2 = this.box.getX();
        RenderSystem.pushMatrix();
        RenderSystem.scaled((double)this.scale.get(), (double)this.scale.get(), (double)1.0);
        d2 /= this.scale.get().doubleValue();
        d3 /= this.scale.get().doubleValue();
        Renderer.LINES.begin(null, DrawMode.Lines, class_290.field_1576);
        Renderer.LINES.boxEdges(d2 += 5.0, d3 += 5.0, 165.0, 11.0, BLACK);
        Renderer.LINES.end();
        float f = this.playerEntity.method_6063();
        int n4 = 16;
        int n5 = (int)(f + (float)n4);
        int n6 = (int)(161.0f * f / (float)n5);
        int n7 = 161 * n4 / n5;
        float f2 = this.playerEntity.method_6032();
        float f3 = this.playerEntity.method_6067();
        d = f2 / f;
        double d10 = f3 / (float)n4;
        int n8 = (int)((double)n6 * d);
        int n9 = (int)((double)n7 * d10);
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.horizontalGradientQuad(d2 += 2.0, d3 += 2.0, n8, 7.0, this.healthColor1.get(), this.healthColor2.get());
        Renderer.NORMAL.horizontalGradientQuad(d2 + (double)n8, d3, n9, 7.0, this.healthColor2.get(), this.healthColor3.get());
        Renderer.NORMAL.end();
        RenderSystem.popMatrix();
    }

    static {
        WHITE = new Color(255, 255, 255);
        GREEN = new Color(15, 255, 15);
        RED = new Color(255, 15, 15);
        BLACK = new Color(0, 0, 0, 255);
    }

    public static List<class_1887> getDefaultEnchantments() {
        ArrayList<class_1887> arrayList = new ArrayList<class_1887>();
        for (class_1887 class_18872 : class_2378.field_11160) {
            arrayList.add(class_18872);
        }
        return arrayList;
    }

    private class_1799 getItem(int n) {
        if (this.isInEditor()) {
            switch (n) {
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
        if (this.playerEntity == null) {
            return class_1799.field_8037;
        }
        if (n == 5) {
            return this.playerEntity.method_6047();
        }
        if (n == 4) {
            return this.playerEntity.method_6079();
        }
        return this.playerEntity.field_7514.method_7372(n);
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        hudRenderer.addPostTask(this::lambda$render$0);
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(175.0 * this.scale.get(), 95.0 * this.scale.get());
    }
}

