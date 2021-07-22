/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_2960;

public class Chams
extends Module {
    private final SettingGroup sgHand;
    public final Setting<SettingColor> playersColor;
    public final Setting<Boolean> renderFrame2;
    public final Setting<Double> crystalsScale;
    public final Setting<Double> playersScale;
    public static final class_2960 BLANK = new class_2960("meteor-client", "textures/blank.png");
    public final Setting<Boolean> renderFrame1;
    public final Setting<Boolean> handTexture;
    private final SettingGroup sgCrystals;
    public final Setting<Double> crystalsBounce;
    public final Setting<SettingColor> handColor;
    public final Setting<Boolean> players;
    private final SettingGroup sgPlayers;
    public final Setting<Boolean> playersTexture;
    private final SettingGroup sgThroughWalls;
    public final Setting<Boolean> hand;
    public final Setting<Boolean> crystals;
    public final Setting<SettingColor> crystalsCoreColor;
    public final Setting<Boolean> crystalsTexture;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    public final Setting<SettingColor> crystalsFrame2Color;
    public final Setting<SettingColor> crystalsFrame1Color;
    public final Setting<Boolean> ignoreSelf;
    public final Setting<Boolean> renderCore;
    public final Setting<Double> crystalsRotationSpeed;

    public Chams() {
        super(Categories.Render, "chams", "Tweaks rendering of entities.");
        this.sgThroughWalls = this.settings.createGroup("Through Walls");
        this.sgPlayers = this.settings.createGroup("Players");
        this.sgCrystals = this.settings.createGroup("Crystals");
        this.sgHand = this.settings.createGroup("Hand");
        this.entities = this.sgThroughWalls.add(new EntityTypeListSetting.Builder().name("entities").description("Select entities to show through walls.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        this.players = this.sgPlayers.add(new BoolSetting.Builder().name("players").description("Enables model tweaks for players.").defaultValue(true).build());
        this.ignoreSelf = this.sgPlayers.add(new BoolSetting.Builder().name("ignore-self").description("Ignores yourself when tweaking player models.").defaultValue(true).visible(this.players::get).build());
        this.playersScale = this.sgPlayers.add(new DoubleSetting.Builder().name("scale").description("Players scale.").defaultValue(1.0).min(0.0).visible(this.players::get).build());
        this.playersTexture = this.sgPlayers.add(new BoolSetting.Builder().name("texture").description("Enables player model textures.").defaultValue(false).visible(this.players::get).build());
        this.playersColor = this.sgPlayers.add(new ColorSetting.Builder().name("color").description("The color of player models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(this.players::get).build());
        this.crystals = this.sgCrystals.add(new BoolSetting.Builder().name("crystals").description("Enables model tweaks for end crystals.").defaultValue(true).build());
        this.crystalsScale = this.sgCrystals.add(new DoubleSetting.Builder().name("scale").description("Crystal scale.").defaultValue(0.6).min(0.0).visible(this.crystals::get).build());
        this.crystalsBounce = this.sgCrystals.add(new DoubleSetting.Builder().name("bounce").description("How high crystals bounce.").defaultValue(0.3).min(0.0).visible(this.crystals::get).build());
        this.crystalsRotationSpeed = this.sgCrystals.add(new DoubleSetting.Builder().name("rotation-speed").description("Multiplies the roation speed of the crystal.").defaultValue(3.0).min(0.0).visible(this.crystals::get).build());
        this.crystalsTexture = this.sgCrystals.add(new BoolSetting.Builder().name("texture").description("Whether to render crystal model textures.").defaultValue(false).visible(this.crystals::get).build());
        this.renderCore = this.sgCrystals.add(new BoolSetting.Builder().name("render-core").description("Enables rendering of the core of the crystal.").defaultValue(false).visible(this.crystals::get).build());
        this.crystalsCoreColor = this.sgCrystals.add(new ColorSetting.Builder().name("core-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(this.renderCore::get).build());
        this.renderFrame1 = this.sgCrystals.add(new BoolSetting.Builder().name("render-inner-frame").description("Enables rendering of the frame of the crystal.").defaultValue(true).visible(this.crystals::get).build());
        this.crystalsFrame1Color = this.sgCrystals.add(new ColorSetting.Builder().name("inner-frame-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(this.renderFrame1::get).build());
        this.renderFrame2 = this.sgCrystals.add(new BoolSetting.Builder().name("render-outer-frame").description("Enables rendering of the frame of the crystal.").defaultValue(true).visible(this.crystals::get).build());
        this.crystalsFrame2Color = this.sgCrystals.add(new ColorSetting.Builder().name("outer-frame-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(this.renderFrame2::get).build());
        this.hand = this.sgHand.add(new BoolSetting.Builder().name("enabled").description("Enables tweaks of hand rendering.").defaultValue(true).build());
        this.handTexture = this.sgHand.add(new BoolSetting.Builder().name("texture").description("Whether to render hand textures.").defaultValue(false).build());
        this.handColor = this.sgHand.add(new ColorSetting.Builder().name("hand-color").description("The color of your hand.").defaultValue(new SettingColor(0, 255, 255, 100)).build());
    }

    public boolean shouldRender(class_1297 class_12972) {
        return this.isActive() && this.entities.get().getBoolean((Object)class_12972.method_5864());
    }
}

