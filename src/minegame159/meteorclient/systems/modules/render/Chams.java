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
    private final /* synthetic */ SettingGroup sgHand;
    public final /* synthetic */ Setting<SettingColor> playersColor;
    public final /* synthetic */ Setting<Boolean> renderFrame2;
    public final /* synthetic */ Setting<Double> crystalsScale;
    public final /* synthetic */ Setting<Double> playersScale;
    public static final /* synthetic */ class_2960 BLANK;
    public final /* synthetic */ Setting<Boolean> renderFrame1;
    public final /* synthetic */ Setting<Boolean> handTexture;
    private final /* synthetic */ SettingGroup sgCrystals;
    public final /* synthetic */ Setting<Double> crystalsBounce;
    public final /* synthetic */ Setting<SettingColor> handColor;
    public final /* synthetic */ Setting<Boolean> players;
    private final /* synthetic */ SettingGroup sgPlayers;
    public final /* synthetic */ Setting<Boolean> playersTexture;
    private final /* synthetic */ SettingGroup sgThroughWalls;
    public final /* synthetic */ Setting<Boolean> hand;
    public final /* synthetic */ Setting<Boolean> crystals;
    public final /* synthetic */ Setting<SettingColor> crystalsCoreColor;
    public final /* synthetic */ Setting<Boolean> crystalsTexture;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    public final /* synthetic */ Setting<SettingColor> crystalsFrame2Color;
    public final /* synthetic */ Setting<SettingColor> crystalsFrame1Color;
    public final /* synthetic */ Setting<Boolean> ignoreSelf;
    public final /* synthetic */ Setting<Boolean> renderCore;
    public final /* synthetic */ Setting<Double> crystalsRotationSpeed;

    public Chams() {
        super(Categories.Render, "chams", "Tweaks rendering of entities.");
        Chams lllIIlIIlIlII;
        lllIIlIIlIlII.sgThroughWalls = lllIIlIIlIlII.settings.createGroup("Through Walls");
        lllIIlIIlIlII.sgPlayers = lllIIlIIlIlII.settings.createGroup("Players");
        lllIIlIIlIlII.sgCrystals = lllIIlIIlIlII.settings.createGroup("Crystals");
        lllIIlIIlIlII.sgHand = lllIIlIIlIlII.settings.createGroup("Hand");
        lllIIlIIlIlII.entities = lllIIlIIlIlII.sgThroughWalls.add(new EntityTypeListSetting.Builder().name("entities").description("Select entities to show through walls.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        lllIIlIIlIlII.players = lllIIlIIlIlII.sgPlayers.add(new BoolSetting.Builder().name("players").description("Enables model tweaks for players.").defaultValue(true).build());
        lllIIlIIlIlII.ignoreSelf = lllIIlIIlIlII.sgPlayers.add(new BoolSetting.Builder().name("ignore-self").description("Ignores yourself when tweaking player models.").defaultValue(true).visible(lllIIlIIlIlII.players::get).build());
        lllIIlIIlIlII.playersScale = lllIIlIIlIlII.sgPlayers.add(new DoubleSetting.Builder().name("scale").description("Players scale.").defaultValue(1.0).min(0.0).visible(lllIIlIIlIlII.players::get).build());
        lllIIlIIlIlII.playersTexture = lllIIlIIlIlII.sgPlayers.add(new BoolSetting.Builder().name("texture").description("Enables player model textures.").defaultValue(false).visible(lllIIlIIlIlII.players::get).build());
        lllIIlIIlIlII.playersColor = lllIIlIIlIlII.sgPlayers.add(new ColorSetting.Builder().name("color").description("The color of player models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(lllIIlIIlIlII.players::get).build());
        lllIIlIIlIlII.crystals = lllIIlIIlIlII.sgCrystals.add(new BoolSetting.Builder().name("crystals").description("Enables model tweaks for end crystals.").defaultValue(true).build());
        lllIIlIIlIlII.crystalsScale = lllIIlIIlIlII.sgCrystals.add(new DoubleSetting.Builder().name("scale").description("Crystal scale.").defaultValue(0.6).min(0.0).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsBounce = lllIIlIIlIlII.sgCrystals.add(new DoubleSetting.Builder().name("bounce").description("How high crystals bounce.").defaultValue(0.3).min(0.0).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsRotationSpeed = lllIIlIIlIlII.sgCrystals.add(new DoubleSetting.Builder().name("rotation-speed").description("Multiplies the roation speed of the crystal.").defaultValue(3.0).min(0.0).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsTexture = lllIIlIIlIlII.sgCrystals.add(new BoolSetting.Builder().name("texture").description("Whether to render crystal model textures.").defaultValue(false).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.renderCore = lllIIlIIlIlII.sgCrystals.add(new BoolSetting.Builder().name("render-core").description("Enables rendering of the core of the crystal.").defaultValue(false).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsCoreColor = lllIIlIIlIlII.sgCrystals.add(new ColorSetting.Builder().name("core-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(lllIIlIIlIlII.renderCore::get).build());
        lllIIlIIlIlII.renderFrame1 = lllIIlIIlIlII.sgCrystals.add(new BoolSetting.Builder().name("render-inner-frame").description("Enables rendering of the frame of the crystal.").defaultValue(true).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsFrame1Color = lllIIlIIlIlII.sgCrystals.add(new ColorSetting.Builder().name("inner-frame-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(lllIIlIIlIlII.renderFrame1::get).build());
        lllIIlIIlIlII.renderFrame2 = lllIIlIIlIlII.sgCrystals.add(new BoolSetting.Builder().name("render-outer-frame").description("Enables rendering of the frame of the crystal.").defaultValue(true).visible(lllIIlIIlIlII.crystals::get).build());
        lllIIlIIlIlII.crystalsFrame2Color = lllIIlIIlIlII.sgCrystals.add(new ColorSetting.Builder().name("outer-frame-color").description("The color of end crystal models.").defaultValue(new SettingColor(0, 255, 255, 100)).visible(lllIIlIIlIlII.renderFrame2::get).build());
        lllIIlIIlIlII.hand = lllIIlIIlIlII.sgHand.add(new BoolSetting.Builder().name("enabled").description("Enables tweaks of hand rendering.").defaultValue(true).build());
        lllIIlIIlIlII.handTexture = lllIIlIIlIlII.sgHand.add(new BoolSetting.Builder().name("texture").description("Whether to render hand textures.").defaultValue(false).build());
        lllIIlIIlIlII.handColor = lllIIlIIlIlII.sgHand.add(new ColorSetting.Builder().name("hand-color").description("The color of your hand.").defaultValue(new SettingColor(0, 255, 255, 100)).build());
    }

    public boolean shouldRender(class_1297 lllIIlIIIlllI) {
        Chams lllIIlIIIllll;
        return lllIIlIIIllll.isActive() && lllIIlIIIllll.entities.get().getBoolean((Object)lllIIlIIIlllI.method_5864());
    }

    static {
        BLANK = new class_2960("meteor-client", "textures/blank.png");
    }
}

