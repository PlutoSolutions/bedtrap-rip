/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1533
 *  net.minecraft.class_1541
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1934
 *  net.minecraft.class_2378
 *  net.minecraft.class_243
 *  net.minecraft.class_290
 *  net.minecraft.class_3532
 *  org.lwjgl.opengl.GL11
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnchListSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.NameProtect;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.misc.MeteorPlayers;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.NametagUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1533;
import net.minecraft.class_1541;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1934;
import net.minecraft.class_2378;
import net.minecraft.class_243;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import org.lwjgl.opengl.GL11;

public class Nametags
extends Module {
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ Setting<Boolean> displayGameMode;
    private final /* synthetic */ SettingGroup sgItems;
    private final /* synthetic */ double[] itemWidths;
    private final /* synthetic */ Setting<Double> enchantTextScale;
    private final /* synthetic */ Color GOLD;
    private final /* synthetic */ Setting<Boolean> displayDistance;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> maxCullRange;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Boolean> yourself;
    private final /* synthetic */ Color METEOR;
    private final /* synthetic */ Setting<Integer> enchantLength;
    private final /* synthetic */ SettingGroup sgPlayers;
    private final /* synthetic */ Vec3 pos;
    private final /* synthetic */ Setting<Position> enchantPos;
    private final /* synthetic */ Setting<SettingColor> background;
    private final /* synthetic */ Setting<List<class_1887>> displayedEnchantments;
    private final /* synthetic */ Color RED;
    private final /* synthetic */ Setting<Integer> maxCullCount;
    private final /* synthetic */ Setting<Double> itemSpacing;
    private final /* synthetic */ Color AMBER;
    private final /* synthetic */ Color BLUE;
    private final /* synthetic */ Setting<Boolean> itemCount;
    private final /* synthetic */ Setting<Boolean> ignoreEmpty;
    private final /* synthetic */ Setting<Boolean> displayItemEnchants;
    private final /* synthetic */ List<class_1297> entityList;
    private final /* synthetic */ Color WHITE;
    private final /* synthetic */ Color GREY;
    private final /* synthetic */ Setting<Boolean> displayItems;
    private final /* synthetic */ Setting<Boolean> culling;
    private final /* synthetic */ Setting<SettingColor> names;
    private final /* synthetic */ Color GREEN;
    private final /* synthetic */ Setting<Boolean> displayPing;
    private final /* synthetic */ Setting<Boolean> displayMeteor;
    private final /* synthetic */ Map<class_1887, Integer> enchantmentsToShowScale;

    private List<class_1887> setDefaultList() {
        ArrayList<class_1887> lllllllllllllllllIlIllllIlIlIlII = new ArrayList<class_1887>();
        for (class_1887 lllllllllllllllllIlIllllIlIlIllI : class_2378.field_11160) {
            lllllllllllllllllIlIllllIlIlIlII.add(lllllllllllllllllIlIllllIlIlIllI);
        }
        return lllllllllllllllllIlIllllIlIlIlII;
    }

    private void renderNametagItem(class_1799 lllllllllllllllllIlIlllllIllIIIl) {
        Nametags lllllllllllllllllIlIlllllIllIIlI;
        TextRenderer lllllllllllllllllIlIlllllIllllII = TextRenderer.get();
        NametagUtils.begin(lllllllllllllllllIlIlllllIllIIlI.pos);
        String lllllllllllllllllIlIlllllIlllIll = lllllllllllllllllIlIlllllIllIIIl.method_7964().getString();
        String lllllllllllllllllIlIlllllIlllIlI = String.valueOf(new StringBuilder().append(" x").append(lllllllllllllllllIlIlllllIllIIIl.method_7947()));
        double lllllllllllllllllIlIlllllIlllIIl = lllllllllllllllllIlIlllllIllllII.getWidth(lllllllllllllllllIlIlllllIlllIll);
        double lllllllllllllllllIlIlllllIlllIII = lllllllllllllllllIlIlllllIllllII.getWidth(lllllllllllllllllIlIlllllIlllIlI);
        double lllllllllllllllllIlIlllllIllIlll = lllllllllllllllllIlIlllllIllllII.getHeight();
        double lllllllllllllllllIlIlllllIllIllI = lllllllllllllllllIlIlllllIlllIIl;
        if (lllllllllllllllllIlIlllllIllIIlI.itemCount.get().booleanValue()) {
            lllllllllllllllllIlIlllllIllIllI += lllllllllllllllllIlIlllllIlllIII;
        }
        double lllllllllllllllllIlIlllllIllIlIl = lllllllllllllllllIlIlllllIllIllI / 2.0;
        lllllllllllllllllIlIlllllIllIIlI.drawBg(-lllllllllllllllllIlIlllllIllIlIl, -lllllllllllllllllIlIlllllIllIlll, lllllllllllllllllIlIlllllIllIllI, lllllllllllllllllIlIlllllIllIlll);
        lllllllllllllllllIlIlllllIllllII.beginBig();
        double lllllllllllllllllIlIlllllIllIlII = -lllllllllllllllllIlIlllllIllIlIl;
        double lllllllllllllllllIlIlllllIllIIll = -lllllllllllllllllIlIlllllIllIlll;
        lllllllllllllllllIlIlllllIllIlII = lllllllllllllllllIlIlllllIllllII.render(lllllllllllllllllIlIlllllIlllIll, lllllllllllllllllIlIlllllIllIlII, lllllllllllllllllIlIlllllIllIIll, lllllllllllllllllIlIlllllIllIIlI.WHITE);
        if (lllllllllllllllllIlIlllllIllIIlI.itemCount.get().booleanValue()) {
            lllllllllllllllllIlIlllllIllllII.render(lllllllllllllllllIlIlllllIlllIlI, lllllllllllllllllIlIlllllIllIlII, lllllllllllllllllIlIlllllIllIIll, lllllllllllllllllIlIlllllIllIIlI.GOLD);
        }
        lllllllllllllllllIlIlllllIllllII.end();
        NametagUtils.end();
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIllIIIIlIlIIlIl) {
        Nametags lllllllllllllllllIllIIIIlIlIIIlI;
        lllllllllllllllllIllIIIIlIlIIIlI.entityList.clear();
        boolean lllllllllllllllllIllIIIIlIlIIlII = !Modules.get().isActive(Freecam.class);
        class_243 lllllllllllllllllIllIIIIlIlIIIll = lllllllllllllllllIllIIIIlIlIIIlI.mc.field_1773.method_19418().method_19326();
        lllllllllllllllllIllIIIIlIlIIIlI.mc.field_1687.method_18112().forEach(lllllllllllllllllIlIllllIIIlllII -> {
            Nametags lllllllllllllllllIlIllllIIIlllll;
            class_1299 lllllllllllllllllIlIllllIIlIIIII = lllllllllllllllllIlIllllIIIlllII.method_5864();
            if (!lllllllllllllllllIlIllllIIIlllll.entities.get().containsKey((Object)lllllllllllllllllIlIllllIIlIIIII)) {
                return;
            }
            if (lllllllllllllllllIlIllllIIlIIIII == class_1299.field_6097 && (!lllllllllllllllllIlIllllIIIlllll.yourself.get().booleanValue() || lllllllllllllllllIllIIIIlIlIIlII) && lllllllllllllllllIlIllllIIIlllII == lllllllllllllllllIlIllllIIIlllll.mc.field_1724) {
                return;
            }
            if (!lllllllllllllllllIlIllllIIIlllll.culling.get().booleanValue() || lllllllllllllllllIlIllllIIIlllII.method_19538().method_1022(lllllllllllllllllIllIIIIlIlIIIll) < lllllllllllllllllIlIllllIIIlllll.maxCullRange.get()) {
                lllllllllllllllllIlIllllIIIlllll.entityList.add((class_1297)lllllllllllllllllIlIllllIIIlllII);
            }
        });
        lllllllllllllllllIllIIIIlIlIIIlI.entityList.sort(Comparator.comparing(lllllllllllllllllIlIllllIIlIllII -> lllllllllllllllllIlIllllIIlIllII.method_5707(lllllllllllllllllIllIIIIlIlIIIll)));
    }

    private void renderTntNametag(class_1541 lllllllllllllllllIlIllllIllIIIIl) {
        Nametags lllllllllllllllllIlIllllIllIIIlI;
        TextRenderer lllllllllllllllllIlIllllIllIlIIl = TextRenderer.get();
        NametagUtils.begin(lllllllllllllllllIlIllllIllIIIlI.pos);
        String lllllllllllllllllIlIllllIllIlIII = Nametags.ticksToTime(lllllllllllllllllIlIllllIllIIIIl.method_6968());
        double lllllllllllllllllIlIllllIllIIlll = lllllllllllllllllIlIllllIllIlIIl.getWidth(lllllllllllllllllIlIllllIllIlIII);
        double lllllllllllllllllIlIllllIllIIllI = lllllllllllllllllIlIllllIllIlIIl.getHeight();
        double lllllllllllllllllIlIllllIllIIlIl = lllllllllllllllllIlIllllIllIIlll / 2.0;
        lllllllllllllllllIlIllllIllIIIlI.drawBg(-lllllllllllllllllIlIllllIllIIlIl, -lllllllllllllllllIlIllllIllIIllI, lllllllllllllllllIlIllllIllIIlll, lllllllllllllllllIlIllllIllIIllI);
        lllllllllllllllllIlIllllIllIlIIl.beginBig();
        double lllllllllllllllllIlIllllIllIIlII = -lllllllllllllllllIlIllllIllIIlIl;
        double lllllllllllllllllIlIllllIllIIIll = -lllllllllllllllllIlIllllIllIIllI;
        lllllllllllllllllIlIllllIllIlIIl.render(lllllllllllllllllIlIllllIllIlIII, lllllllllllllllllIlIllllIllIIlII, lllllllllllllllllIlIllllIllIIIll, lllllllllllllllllIlIllllIllIIIlI.names.get());
        lllllllllllllllllIlIllllIllIlIIl.end();
        NametagUtils.end();
    }

    @EventHandler
    private void onRender2D(Render2DEvent lllllllllllllllllIllIIIIlIIlIlIl) {
        Nametags lllllllllllllllllIllIIIIlIIlIllI;
        int lllllllllllllllllIllIIIIlIIlIlII = lllllllllllllllllIllIIIIlIIlIllI.getRenderCount();
        for (int lllllllllllllllllIllIIIIlIIlIlll = lllllllllllllllllIllIIIIlIIlIlII - 1; lllllllllllllllllIllIIIIlIIlIlll > -1; --lllllllllllllllllIllIIIIlIIlIlll) {
            class_1297 lllllllllllllllllIllIIIIlIIllIIl = lllllllllllllllllIllIIIIlIIlIllI.entityList.get(lllllllllllllllllIllIIIIlIIlIlll);
            lllllllllllllllllIllIIIIlIIlIllI.pos.set(lllllllllllllllllIllIIIIlIIllIIl, lllllllllllllllllIllIIIIlIIlIlIl.tickDelta);
            lllllllllllllllllIllIIIIlIIlIllI.pos.add(0.0, lllllllllllllllllIllIIIIlIIlIllI.getHeight(lllllllllllllllllIllIIIIlIIllIIl), 0.0);
            class_1299 lllllllllllllllllIllIIIIlIIllIII = lllllllllllllllllIllIIIIlIIllIIl.method_5864();
            if (!NametagUtils.to2D(lllllllllllllllllIllIIIIlIIlIllI.pos, lllllllllllllllllIllIIIIlIIlIllI.scale.get())) continue;
            if (lllllllllllllllllIllIIIIlIIllIII == class_1299.field_6097) {
                lllllllllllllllllIllIIIIlIIlIllI.renderNametagPlayer((class_1657)lllllllllllllllllIllIIIIlIIllIIl);
                continue;
            }
            if (lllllllllllllllllIllIIIIlIIllIII == class_1299.field_6052) {
                lllllllllllllllllIllIIIIlIIlIllI.renderNametagItem(((class_1542)lllllllllllllllllIllIIIIlIIllIIl).method_6983());
                continue;
            }
            if (lllllllllllllllllIllIIIIlIIllIII == class_1299.field_6043) {
                lllllllllllllllllIllIIIIlIIlIllI.renderNametagItem(((class_1533)lllllllllllllllllIllIIIIlIIllIIl).method_6940());
                continue;
            }
            if (lllllllllllllllllIllIIIIlIIllIII == class_1299.field_6063) {
                lllllllllllllllllIllIIIIlIIlIllI.renderTntNametag((class_1541)lllllllllllllllllIllIIIIlIIllIIl);
                continue;
            }
            if (!(lllllllllllllllllIllIIIIlIIllIIl instanceof class_1309)) continue;
            lllllllllllllllllIllIIIIlIIlIllI.renderGenericNametag((class_1309)lllllllllllllllllIllIIIIlIIllIIl);
        }
    }

    @Override
    public String getInfoString() {
        Nametags lllllllllllllllllIllIIIIlIIIIlIl;
        return Integer.toString(lllllllllllllllllIllIIIIlIIIIlIl.getRenderCount());
    }

    public Nametags() {
        super(Categories.Render, "nametags", "Displays customizable nametags above players.");
        Nametags lllllllllllllllllIllIIIIlIlIlIll;
        lllllllllllllllllIllIIIIlIlIlIll.sgGeneral = lllllllllllllllllIllIIIIlIlIlIll.settings.getDefaultGroup();
        lllllllllllllllllIllIIIIlIlIlIll.sgPlayers = lllllllllllllllllIllIIIIlIlIlIll.settings.createGroup("Players");
        lllllllllllllllllIllIIIIlIlIlIll.sgItems = lllllllllllllllllIllIIIIlIlIlIll.settings.createGroup("Items");
        lllllllllllllllllIllIIIIlIlIlIll.entities = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Select entities to draw nametags on.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097, class_1299.field_6052})).build());
        lllllllllllllllllIllIIIIlIlIlIll.scale = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of the nametag.").defaultValue(1.5).min(0.1).build());
        lllllllllllllllllIllIIIIlIlIlIll.yourself = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new BoolSetting.Builder().name("self-nametag").description("Displays a nametag on your player if you're in Freecam.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.background = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new ColorSetting.Builder().name("background").description("The color of the nametag background.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        lllllllllllllllllIllIIIIlIlIlIll.names = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new ColorSetting.Builder().name("names").description("The color of the nametag names.").defaultValue(new SettingColor()).build());
        lllllllllllllllllIllIIIIlIlIlIll.culling = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new BoolSetting.Builder().name("culling").description("Only render a certain number of nametags at a certain distance.").defaultValue(false).build());
        lllllllllllllllllIllIIIIlIlIlIll.maxCullRange = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new DoubleSetting.Builder().name("cull-range").description("Only render nametags within this distance of your player.").defaultValue(20.0).min(0.0).sliderMax(200.0).visible(lllllllllllllllllIllIIIIlIlIlIll.culling::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.maxCullCount = lllllllllllllllllIllIIIIlIlIlIll.sgGeneral.add(new IntSetting.Builder().name("cull-count").description("Only render this many nametags.").defaultValue(50).min(1).sliderMin(1).sliderMax(100).visible(lllllllllllllllllIllIIIIlIlIlIll.culling::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayItems = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("display-items").description("Displays armor and hand items above the name tags.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.itemSpacing = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new DoubleSetting.Builder().name("item-spacing").description("The spacing between items.").defaultValue(2.0).min(0.0).max(10.0).sliderMax(5.0).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItems::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.ignoreEmpty = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("ignore-empty").description("Doesn't add spacing where an empty item stack would be.").defaultValue(true).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItems::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayItemEnchants = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("display-enchants").description("Displays item enchantments on the items.").defaultValue(true).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItems::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.enchantPos = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new EnumSetting.Builder().name("enchantment-position").description("Where the enchantments are rendered.").defaultValue(Position.Above).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItemEnchants::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.enchantLength = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new IntSetting.Builder().name("enchant-name-length").description("The length enchantment names are trimmed to.").defaultValue(3).min(1).max(5).sliderMax(5).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItemEnchants::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayedEnchantments = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new EnchListSetting.Builder().name("displayed-enchantments").description("The enchantments that are shown on nametags.").defaultValue(lllllllllllllllllIllIIIIlIlIlIll.setDefaultList()).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItemEnchants::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.enchantTextScale = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new DoubleSetting.Builder().name("enchant-text-scale").description("The scale of the enchantment text.").defaultValue(1.0).min(0.1).max(2.0).sliderMin(0.1).sliderMax(2.0).visible(lllllllllllllllllIllIIIIlIlIlIll.displayItemEnchants::get).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayMeteor = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("meteor").description("Shows if the player is using Meteor.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayGameMode = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("gamemode").description("Shows the player's GameMode.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayPing = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("ping").description("Shows the player's ping.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.displayDistance = lllllllllllllllllIllIIIIlIlIlIll.sgPlayers.add(new BoolSetting.Builder().name("distance").description("Shows the distance between you and the player.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.itemCount = lllllllllllllllllIllIIIIlIlIlIll.sgItems.add(new BoolSetting.Builder().name("count").description("Displays the number of items in the stack.").defaultValue(true).build());
        lllllllllllllllllIllIIIIlIlIlIll.pos = new Vec3();
        lllllllllllllllllIllIIIIlIlIlIll.itemWidths = new double[6];
        lllllllllllllllllIllIIIIlIlIlIll.WHITE = new Color(255, 255, 255);
        lllllllllllllllllIllIIIIlIlIlIll.RED = new Color(255, 25, 25);
        lllllllllllllllllIllIIIIlIlIlIll.AMBER = new Color(255, 105, 25);
        lllllllllllllllllIllIIIIlIlIlIll.GREEN = new Color(25, 252, 25);
        lllllllllllllllllIllIIIIlIlIlIll.GOLD = new Color(232, 185, 35);
        lllllllllllllllllIllIIIIlIlIlIll.GREY = new Color(150, 150, 150);
        lllllllllllllllllIllIIIIlIlIlIll.METEOR = new Color(135, 0, 255);
        lllllllllllllllllIllIIIIlIlIlIll.BLUE = new Color(20, 170, 170);
        lllllllllllllllllIllIIIIlIlIlIll.enchantmentsToShowScale = new HashMap<class_1887, Integer>();
        lllllllllllllllllIllIIIIlIlIlIll.entityList = new ArrayList<class_1297>();
    }

    private class_1799 getItem(class_1657 lllllllllllllllllIlIllllIlIIllIl, int lllllllllllllllllIlIllllIlIIllII) {
        switch (lllllllllllllllllIlIllllIlIIllII) {
            case 0: {
                return lllllllllllllllllIlIllllIlIIllIl.method_6047();
            }
            case 1: {
                return (class_1799)lllllllllllllllllIlIllllIlIIllIl.field_7514.field_7548.get(3);
            }
            case 2: {
                return (class_1799)lllllllllllllllllIlIllllIlIIllIl.field_7514.field_7548.get(2);
            }
            case 3: {
                return (class_1799)lllllllllllllllllIlIllllIlIIllIl.field_7514.field_7548.get(1);
            }
            case 4: {
                return (class_1799)lllllllllllllllllIlIllllIlIIllIl.field_7514.field_7548.get(0);
            }
            case 5: {
                return lllllllllllllllllIlIllllIlIIllIl.method_6079();
            }
        }
        return class_1799.field_8037;
    }

    private void drawBg(double lllllllllllllllllIlIllllIIlllllI, double lllllllllllllllllIlIllllIlIIIIlI, double lllllllllllllllllIlIllllIlIIIIIl, double lllllllllllllllllIlIllllIIlllIll) {
        Nametags lllllllllllllllllIlIllllIIllllll;
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.quad(lllllllllllllllllIlIllllIIlllllI - 1.0, lllllllllllllllllIlIllllIlIIIIlI - 1.0, lllllllllllllllllIlIllllIlIIIIIl + 2.0, lllllllllllllllllIlIllllIIlllIll + 2.0, lllllllllllllllllIlIllllIIllllll.background.get());
        Renderer.NORMAL.end();
    }

    private int getRenderCount() {
        Nametags lllllllllllllllllIllIIIIlIIIlIll;
        int lllllllllllllllllIllIIIIlIIIlIlI = lllllllllllllllllIllIIIIlIIIlIll.culling.get() != false ? lllllllllllllllllIllIIIIlIIIlIll.maxCullCount.get().intValue() : lllllllllllllllllIllIIIIlIIIlIll.entityList.size();
        lllllllllllllllllIllIIIIlIIIlIlI = class_3532.method_15340((int)lllllllllllllllllIllIIIIlIIIlIlI, (int)0, (int)lllllllllllllllllIllIIIIlIIIlIll.entityList.size());
        return lllllllllllllllllIllIIIIlIIIlIlI;
    }

    private void renderNametagPlayer(class_1657 lllllllllllllllllIlIllllllllllIl) {
        Color lllllllllllllllllIllIIIIIIIIlllI;
        String lllllllllllllllllIllIIIIIIIlIlII;
        Nametags lllllllllllllllllIllIIIIIIIllIlI;
        TextRenderer lllllllllllllllllIllIIIIIIIllIII = TextRenderer.get();
        NametagUtils.begin(lllllllllllllllllIllIIIIIIIllIlI.pos);
        String lllllllllllllllllIllIIIIIIIlIlll = "";
        if (lllllllllllllllllIllIIIIIIIllIlI.displayMeteor.get().booleanValue() && MeteorPlayers.get(lllllllllllllllllIlIllllllllllIl)) {
            lllllllllllllllllIllIIIIIIIlIlll = "M ";
        }
        class_1934 lllllllllllllllllIllIIIIIIIlIllI = EntityUtils.getGameMode(lllllllllllllllllIlIllllllllllIl);
        String lllllllllllllllllIllIIIIIIIlIlIl = "BOT";
        if (lllllllllllllllllIllIIIIIIIlIllI != null) {
            switch (lllllllllllllllllIllIIIIIIIlIllI) {
                case field_9219: {
                    lllllllllllllllllIllIIIIIIIlIlIl = "Sp";
                    break;
                }
                case field_9215: {
                    lllllllllllllllllIllIIIIIIIlIlIl = "S";
                    break;
                }
                case field_9220: {
                    lllllllllllllllllIllIIIIIIIlIlIl = "C";
                    break;
                }
                case field_9216: {
                    lllllllllllllllllIllIIIIIIIlIlIl = "A";
                }
            }
        }
        lllllllllllllllllIllIIIIIIIlIlIl = String.valueOf(new StringBuilder().append("[").append(lllllllllllllllllIllIIIIIIIlIlIl).append("] "));
        Color lllllllllllllllllIllIIIIIIIlIIll = PlayerUtils.getPlayerColor(lllllllllllllllllIlIllllllllllIl, lllllllllllllllllIllIIIIIIIllIlI.names.get());
        if (lllllllllllllllllIlIllllllllllIl == lllllllllllllllllIllIIIIIIIllIlI.mc.field_1724) {
            String lllllllllllllllllIllIIIIIIllIlll = Modules.get().get(NameProtect.class).getName(lllllllllllllllllIlIllllllllllIl.method_5820());
        } else {
            lllllllllllllllllIllIIIIIIIlIlII = lllllllllllllllllIlIllllllllllIl.method_5820();
        }
        lllllllllllllllllIllIIIIIIIlIlII = String.valueOf(new StringBuilder().append(lllllllllllllllllIllIIIIIIIlIlII).append(" "));
        float lllllllllllllllllIllIIIIIIIlIIlI = lllllllllllllllllIlIllllllllllIl.method_6067();
        int lllllllllllllllllIllIIIIIIIlIIIl = Math.round(lllllllllllllllllIlIllllllllllIl.method_6032() + lllllllllllllllllIllIIIIIIIlIIlI);
        double lllllllllllllllllIllIIIIIIIlIIII = (float)lllllllllllllllllIllIIIIIIIlIIIl / (lllllllllllllllllIlIllllllllllIl.method_6063() + lllllllllllllllllIllIIIIIIIlIIlI);
        String lllllllllllllllllIllIIIIIIIIllll = String.valueOf(lllllllllllllllllIllIIIIIIIlIIIl);
        if (lllllllllllllllllIllIIIIIIIlIIII <= 0.333) {
            Color lllllllllllllllllIllIIIIIIllIllI = lllllllllllllllllIllIIIIIIIllIlI.RED;
        } else if (lllllllllllllllllIllIIIIIIIlIIII <= 0.666) {
            Color lllllllllllllllllIllIIIIIIllIlIl = lllllllllllllllllIllIIIIIIIllIlI.AMBER;
        } else {
            lllllllllllllllllIllIIIIIIIIlllI = lllllllllllllllllIllIIIIIIIllIlI.GREEN;
        }
        int lllllllllllllllllIllIIIIIIIIllIl = EntityUtils.getPing(lllllllllllllllllIlIllllllllllIl);
        String lllllllllllllllllIllIIIIIIIIllII = String.valueOf(new StringBuilder().append(" [").append(lllllllllllllllllIllIIIIIIIIllIl).append("ms]"));
        double lllllllllllllllllIllIIIIIIIIlIll = (double)Math.round(PlayerUtils.distanceToCamera((class_1297)lllllllllllllllllIlIllllllllllIl) * 10.0) / 10.0;
        String lllllllllllllllllIllIIIIIIIIlIlI = String.valueOf(new StringBuilder().append(" ").append(lllllllllllllllllIllIIIIIIIIlIll).append("m"));
        double lllllllllllllllllIllIIIIIIIIlIIl = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIlIlll);
        double lllllllllllllllllIllIIIIIIIIlIII = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIlIlIl);
        double lllllllllllllllllIllIIIIIIIIIlll = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIlIlII);
        double lllllllllllllllllIllIIIIIIIIIllI = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIIllll);
        double lllllllllllllllllIllIIIIIIIIIlIl = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIIllII);
        double lllllllllllllllllIllIIIIIIIIIlII = lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIIIlIlI);
        double lllllllllllllllllIllIIIIIIIIIIll = lllllllllllllllllIllIIIIIIIIIlll + lllllllllllllllllIllIIIIIIIIIllI;
        if (lllllllllllllllllIllIIIIIIIllIlI.displayMeteor.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIll += lllllllllllllllllIllIIIIIIIIlIIl;
        }
        if (lllllllllllllllllIllIIIIIIIllIlI.displayGameMode.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIll += lllllllllllllllllIllIIIIIIIIlIII;
        }
        if (lllllllllllllllllIllIIIIIIIllIlI.displayPing.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIll += lllllllllllllllllIllIIIIIIIIIlIl;
        }
        if (lllllllllllllllllIllIIIIIIIllIlI.displayDistance.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIll += lllllllllllllllllIllIIIIIIIIIlII;
        }
        double lllllllllllllllllIllIIIIIIIIIIlI = lllllllllllllllllIllIIIIIIIIIIll / 2.0;
        double lllllllllllllllllIllIIIIIIIIIIIl = lllllllllllllllllIllIIIIIIIllIII.getHeight();
        lllllllllllllllllIllIIIIIIIllIlI.drawBg(-lllllllllllllllllIllIIIIIIIIIIlI, -lllllllllllllllllIllIIIIIIIIIIIl, lllllllllllllllllIllIIIIIIIIIIll, lllllllllllllllllIllIIIIIIIIIIIl);
        lllllllllllllllllIllIIIIIIIllIII.beginBig();
        double lllllllllllllllllIllIIIIIIIIIIII = -lllllllllllllllllIllIIIIIIIIIIlI;
        double lllllllllllllllllIlIllllllllllll = -lllllllllllllllllIllIIIIIIIIIIIl;
        if (lllllllllllllllllIllIIIIIIIllIlI.displayMeteor.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIII = lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIlIlll, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIllIlI.METEOR);
        }
        if (lllllllllllllllllIllIIIIIIIllIlI.displayGameMode.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIII = lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIlIlIl, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIllIlI.GOLD);
        }
        lllllllllllllllllIllIIIIIIIIIIII = lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIlIlII, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIlIIll);
        lllllllllllllllllIllIIIIIIIIIIII = lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIIllll, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIIlllI);
        if (lllllllllllllllllIllIIIIIIIllIlI.displayPing.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIIIIII = lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIIllII, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIllIlI.BLUE);
        }
        if (lllllllllllllllllIllIIIIIIIllIlI.displayDistance.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIIIlIlI, lllllllllllllllllIllIIIIIIIIIIII, lllllllllllllllllIlIllllllllllll, lllllllllllllllllIllIIIIIIIllIlI.GREY);
        }
        lllllllllllllllllIllIIIIIIIllIII.end();
        if (lllllllllllllllllIllIIIIIIIllIlI.displayItems.get().booleanValue()) {
            Arrays.fill(lllllllllllllllllIllIIIIIIIllIlI.itemWidths, 0.0);
            boolean lllllllllllllllllIllIIIIIIlIIIIl = false;
            int lllllllllllllllllIllIIIIIIlIIIII = 0;
            for (int lllllllllllllllllIllIIIIIIlIllll = 0; lllllllllllllllllIllIIIIIIlIllll < 6; ++lllllllllllllllllIllIIIIIIlIllll) {
                class_1799 lllllllllllllllllIllIIIIIIllIIII = lllllllllllllllllIllIIIIIIIllIlI.getItem(lllllllllllllllllIlIllllllllllIl, lllllllllllllllllIllIIIIIIlIllll);
                if (!(lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIllll] != 0.0 || lllllllllllllllllIllIIIIIIIllIlI.ignoreEmpty.get().booleanValue() && lllllllllllllllllIllIIIIIIllIIII.method_7960())) {
                    lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIllll] = 32.0 + lllllllllllllllllIllIIIIIIIllIlI.itemSpacing.get();
                }
                if (!lllllllllllllllllIllIIIIIIllIIII.method_7960()) {
                    lllllllllllllllllIllIIIIIIlIIIIl = true;
                }
                if (!lllllllllllllllllIllIIIIIIIllIlI.displayItemEnchants.get().booleanValue()) continue;
                Map lllllllllllllllllIllIIIIIIllIIIl = class_1890.method_8222((class_1799)lllllllllllllllllIllIIIIIIllIIII);
                lllllllllllllllllIllIIIIIIIllIlI.enchantmentsToShowScale.clear();
                for (class_1887 class_18872 : lllllllllllllllllIllIIIIIIIllIlI.displayedEnchantments.get()) {
                    if (!lllllllllllllllllIllIIIIIIllIIIl.containsKey((Object)class_18872)) continue;
                    lllllllllllllllllIllIIIIIIIllIlI.enchantmentsToShowScale.put(class_18872, (Integer)lllllllllllllllllIllIIIIIIllIIIl.get((Object)class_18872));
                }
                for (class_1887 class_18873 : lllllllllllllllllIllIIIIIIIllIlI.enchantmentsToShowScale.keySet()) {
                    String lllllllllllllllllIllIIIIIIllIIll = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(class_18873, lllllllllllllllllIllIIIIIIIllIlI.enchantLength.get())).append(" ").append(lllllllllllllllllIllIIIIIIIllIlI.enchantmentsToShowScale.get((Object)class_18873)));
                    lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIllll] = Math.max(lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIllll], lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIllIIll) / 2.0);
                }
                lllllllllllllllllIllIIIIIIlIIIII = Math.max(lllllllllllllllllIllIIIIIIlIIIII, lllllllllllllllllIllIIIIIIIllIlI.enchantmentsToShowScale.size());
            }
            double lllllllllllllllllIllIIIIIIIlllll = lllllllllllllllllIllIIIIIIlIIIIl ? 32 : 0;
            double lllllllllllllllllIllIIIIIIIllllI = 0.0;
            for (double lllllllllllllllllIllIIIIIIlIlllI : lllllllllllllllllIllIIIIIIIllIlI.itemWidths) {
                lllllllllllllllllIllIIIIIIIllllI += lllllllllllllllllIllIIIIIIlIlllI;
            }
            double d = lllllllllllllllllIllIIIIIIIllllI / 2.0;
            double lllllllllllllllllIllIIIIIIIlllII = -lllllllllllllllllIllIIIIIIIIIIIl - 7.0 - lllllllllllllllllIllIIIIIIIlllll;
            double lllllllllllllllllIllIIIIIIIllIll = -d;
            for (int lllllllllllllllllIllIIIIIIlIIIlI = 0; lllllllllllllllllIllIIIIIIlIIIlI < 6; ++lllllllllllllllllIllIIIIIIlIIIlI) {
                class_1799 lllllllllllllllllIllIIIIIIlIIIll = lllllllllllllllllIllIIIIIIIllIlI.getItem(lllllllllllllllllIlIllllllllllIl, lllllllllllllllllIllIIIIIIlIIIlI);
                GL11.glPushMatrix();
                GL11.glScaled((double)2.0, (double)2.0, (double)1.0);
                lllllllllllllllllIllIIIIIIIllIlI.mc.method_1480().method_4010(lllllllllllllllllIllIIIIIIlIIIll, (int)(lllllllllllllllllIllIIIIIIIllIll / 2.0), (int)(lllllllllllllllllIllIIIIIIIlllII / 2.0));
                lllllllllllllllllIllIIIIIIIllIlI.mc.method_1480().method_4025(lllllllllllllllllIllIIIIIIIllIlI.mc.field_1772, lllllllllllllllllIllIIIIIIlIIIll, (int)(lllllllllllllllllIllIIIIIIIllIll / 2.0), (int)(lllllllllllllllllIllIIIIIIIlllII / 2.0));
                GL11.glPopMatrix();
                if (lllllllllllllllllIllIIIIIIlIIIII > 0 && lllllllllllllllllIllIIIIIIIllIlI.displayItemEnchants.get().booleanValue()) {
                    lllllllllllllllllIllIIIIIIIllIII.begin(0.5 * lllllllllllllllllIllIIIIIIIllIlI.enchantTextScale.get(), false, true);
                    Map lllllllllllllllllIllIIIIIIlIlIIl = class_1890.method_8222((class_1799)lllllllllllllllllIllIIIIIIlIIIll);
                    HashMap<class_1887, Integer> lllllllllllllllllIllIIIIIIlIlIII = new HashMap<class_1887, Integer>();
                    for (class_1887 lllllllllllllllllIllIIIIIIlIllIl : lllllllllllllllllIllIIIIIIIllIlI.displayedEnchantments.get()) {
                        if (!lllllllllllllllllIllIIIIIIlIlIIl.containsKey((Object)lllllllllllllllllIllIIIIIIlIllIl)) continue;
                        lllllllllllllllllIllIIIIIIlIlIII.put(lllllllllllllllllIllIIIIIIlIllIl, (Integer)lllllllllllllllllIllIIIIIIlIlIIl.get((Object)lllllllllllllllllIllIIIIIIlIllIl));
                    }
                    double lllllllllllllllllIllIIIIIIlIIlll = lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIIIlI];
                    double lllllllllllllllllIllIIIIIIlIIllI = 0.0;
                    double lllllllllllllllllIllIIIIIIlIIlIl = 0.0;
                    switch (lllllllllllllllllIllIIIIIIIllIlI.enchantPos.get()) {
                        case Above: {
                            lllllllllllllllllIllIIIIIIlIIlIl = -((double)(lllllllllllllllllIllIIIIIIlIlIII.size() + 1) * lllllllllllllllllIllIIIIIIIllIII.getHeight());
                            break;
                        }
                        case OnTop: {
                            lllllllllllllllllIllIIIIIIlIIlIl = (lllllllllllllllllIllIIIIIIIlllll - (double)lllllllllllllllllIllIIIIIIlIlIII.size() * lllllllllllllllllIllIIIIIIIllIII.getHeight()) / 2.0;
                        }
                    }
                    double lllllllllllllllllIllIIIIIIlIIlII = lllllllllllllllllIllIIIIIIIllIll;
                    for (class_1887 lllllllllllllllllIllIIIIIIlIlIlI : lllllllllllllllllIllIIIIIIlIlIII.keySet()) {
                        String lllllllllllllllllIllIIIIIIlIllII = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(lllllllllllllllllIllIIIIIIlIlIlI, lllllllllllllllllIllIIIIIIIllIlI.enchantLength.get())).append(" ").append(lllllllllllllllllIllIIIIIIlIlIII.get((Object)lllllllllllllllllIllIIIIIIlIlIlI)));
                        Color lllllllllllllllllIllIIIIIIlIlIll = lllllllllllllllllIllIIIIIIIllIlI.WHITE;
                        if (lllllllllllllllllIllIIIIIIlIlIlI.method_8195()) {
                            lllllllllllllllllIllIIIIIIlIlIll = lllllllllllllllllIllIIIIIIIllIlI.RED;
                        }
                        switch (lllllllllllllllllIllIIIIIIIllIlI.enchantPos.get()) {
                            case Above: {
                                lllllllllllllllllIllIIIIIIlIIlII = lllllllllllllllllIllIIIIIIIllIll + lllllllllllllllllIllIIIIIIlIIlll / 2.0 - lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIlIllII) / 2.0;
                                break;
                            }
                            case OnTop: {
                                lllllllllllllllllIllIIIIIIlIIlII = lllllllllllllllllIllIIIIIIIllIll + (lllllllllllllllllIllIIIIIIlIIlll - lllllllllllllllllIllIIIIIIIllIII.getWidth(lllllllllllllllllIllIIIIIIlIllII)) / 2.0;
                            }
                        }
                        lllllllllllllllllIllIIIIIIIllIII.render(lllllllllllllllllIllIIIIIIlIllII, lllllllllllllllllIllIIIIIIlIIlII, lllllllllllllllllIllIIIIIIIlllII + lllllllllllllllllIllIIIIIIlIIlIl + lllllllllllllllllIllIIIIIIlIIllI, lllllllllllllllllIllIIIIIIlIlIll);
                        lllllllllllllllllIllIIIIIIlIIllI += lllllllllllllllllIllIIIIIIIllIII.getHeight();
                    }
                    lllllllllllllllllIllIIIIIIIllIII.end();
                }
                lllllllllllllllllIllIIIIIIIllIll += lllllllllllllllllIllIIIIIIIllIlI.itemWidths[lllllllllllllllllIllIIIIIIlIIIlI];
            }
        } else if (lllllllllllllllllIllIIIIIIIllIlI.displayItemEnchants.get().booleanValue()) {
            lllllllllllllllllIllIIIIIIIllIlI.displayItemEnchants.set(false);
        }
        NametagUtils.end();
    }

    private void renderGenericNametag(class_1309 lllllllllllllllllIlIlllllIIlIIll) {
        Color lllllllllllllllllIlIlllllIIIllII;
        Nametags lllllllllllllllllIlIlllllIIlIlII;
        TextRenderer lllllllllllllllllIlIlllllIIlIIlI = TextRenderer.get();
        NametagUtils.begin(lllllllllllllllllIlIlllllIIlIlII.pos);
        String lllllllllllllllllIlIlllllIIlIIIl = lllllllllllllllllIlIlllllIIlIIll.method_5864().method_5897().getString();
        lllllllllllllllllIlIlllllIIlIIIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIlIlllllIIlIIIl).append(" "));
        float lllllllllllllllllIlIlllllIIlIIII = lllllllllllllllllIlIlllllIIlIIll.method_6067();
        int lllllllllllllllllIlIlllllIIIllll = Math.round(lllllllllllllllllIlIlllllIIlIIll.method_6032() + lllllllllllllllllIlIlllllIIlIIII);
        double lllllllllllllllllIlIlllllIIIlllI = (float)lllllllllllllllllIlIlllllIIIllll / (lllllllllllllllllIlIlllllIIlIIll.method_6063() + lllllllllllllllllIlIlllllIIlIIII);
        String lllllllllllllllllIlIlllllIIIllIl = String.valueOf(lllllllllllllllllIlIlllllIIIllll);
        if (lllllllllllllllllIlIlllllIIIlllI <= 0.333) {
            Color lllllllllllllllllIlIlllllIIlIllI = lllllllllllllllllIlIlllllIIlIlII.RED;
        } else if (lllllllllllllllllIlIlllllIIIlllI <= 0.666) {
            Color lllllllllllllllllIlIlllllIIlIlIl = lllllllllllllllllIlIlllllIIlIlII.AMBER;
        } else {
            lllllllllllllllllIlIlllllIIIllII = lllllllllllllllllIlIlllllIIlIlII.GREEN;
        }
        double lllllllllllllllllIlIlllllIIIlIll = lllllllllllllllllIlIlllllIIlIIlI.getWidth(lllllllllllllllllIlIlllllIIlIIIl);
        double lllllllllllllllllIlIlllllIIIlIlI = lllllllllllllllllIlIlllllIIlIIlI.getWidth(lllllllllllllllllIlIlllllIIIllIl);
        double lllllllllllllllllIlIlllllIIIlIIl = lllllllllllllllllIlIlllllIIlIIlI.getHeight();
        double lllllllllllllllllIlIlllllIIIlIII = lllllllllllllllllIlIlllllIIIlIll + lllllllllllllllllIlIlllllIIIlIlI;
        double lllllllllllllllllIlIlllllIIIIlll = lllllllllllllllllIlIlllllIIIlIII / 2.0;
        lllllllllllllllllIlIlllllIIlIlII.drawBg(-lllllllllllllllllIlIlllllIIIIlll, -lllllllllllllllllIlIlllllIIIlIIl, lllllllllllllllllIlIlllllIIIlIII, lllllllllllllllllIlIlllllIIIlIIl);
        lllllllllllllllllIlIlllllIIlIIlI.beginBig();
        double lllllllllllllllllIlIlllllIIIIllI = -lllllllllllllllllIlIlllllIIIIlll;
        double lllllllllllllllllIlIlllllIIIIlIl = -lllllllllllllllllIlIlllllIIIlIIl;
        lllllllllllllllllIlIlllllIIIIllI = lllllllllllllllllIlIlllllIIlIIlI.render(lllllllllllllllllIlIlllllIIlIIIl, lllllllllllllllllIlIlllllIIIIllI, lllllllllllllllllIlIlllllIIIIlIl, lllllllllllllllllIlIlllllIIlIlII.names.get());
        lllllllllllllllllIlIlllllIIlIIlI.render(lllllllllllllllllIlIlllllIIIllIl, lllllllllllllllllIlIlllllIIIIllI, lllllllllllllllllIlIlllllIIIIlIl, lllllllllllllllllIlIlllllIIIllII);
        lllllllllllllllllIlIlllllIIlIIlI.end();
        NametagUtils.end();
    }

    private static String ticksToTime(int lllllllllllllllllIlIllllIIllIIll) {
        if (lllllllllllllllllIlIllllIIllIIll > 72000) {
            int lllllllllllllllllIlIllllIIllIlll = lllllllllllllllllIlIllllIIllIIll / 20 / 3600;
            return String.valueOf(new StringBuilder().append(lllllllllllllllllIlIllllIIllIlll).append(" h"));
        }
        if (lllllllllllllllllIlIllllIIllIIll > 1200) {
            int lllllllllllllllllIlIllllIIllIllI = lllllllllllllllllIlIllllIIllIIll / 20 / 60;
            return String.valueOf(new StringBuilder().append(lllllllllllllllllIlIllllIIllIllI).append(" m"));
        }
        int lllllllllllllllllIlIllllIIllIlIl = lllllllllllllllllIlIllllIIllIIll / 20;
        int lllllllllllllllllIlIllllIIllIlII = lllllllllllllllllIlIllllIIllIIll % 20 / 2;
        return String.valueOf(new StringBuilder().append(lllllllllllllllllIlIllllIIllIlIl).append(".").append(lllllllllllllllllIlIllllIIllIlII).append(" s"));
    }

    private double getHeight(class_1297 lllllllllllllllllIllIIIIIlllllll) {
        double lllllllllllllllllIllIIIIlIIIIIII = lllllllllllllllllIllIIIIIlllllll.method_18381(lllllllllllllllllIllIIIIIlllllll.method_18376());
        lllllllllllllllllIllIIIIlIIIIIII = lllllllllllllllllIllIIIIIlllllll.method_5864() == class_1299.field_6052 || lllllllllllllllllIllIIIIIlllllll.method_5864() == class_1299.field_6043 ? (lllllllllllllllllIllIIIIlIIIIIII += 0.2) : (lllllllllllllllllIllIIIIlIIIIIII += 0.5);
        return lllllllllllllllllIllIIIIlIIIIIII;
    }

    public static enum Position {
        Above,
        OnTop;


        private Position() {
            Position llllllllllllllllllIIlIIIllIIIlII;
        }
    }
}

