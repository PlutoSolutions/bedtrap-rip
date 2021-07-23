/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Double> scale;
    private final Setting<Boolean> displayGameMode;
    private final SettingGroup sgItems;
    private final double[] itemWidths;
    private final Setting<Double> enchantTextScale;
    private final Color GOLD;
    private final Setting<Boolean> displayDistance;
    private final SettingGroup sgGeneral;
    private final Setting<Double> maxCullRange;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Boolean> yourself;
    private final Color METEOR;
    private final Setting<Integer> enchantLength;
    private final SettingGroup sgPlayers;
    private final Vec3 pos;
    private final Setting<Position> enchantPos;
    private final Setting<SettingColor> background;
    private final Setting<List<class_1887>> displayedEnchantments;
    private final Color RED;
    private final Setting<Integer> maxCullCount;
    private final Setting<Double> itemSpacing;
    private final Color AMBER;
    private final Color BLUE;
    private final Setting<Boolean> itemCount;
    private final Setting<Boolean> ignoreEmpty;
    private final Setting<Boolean> displayItemEnchants;
    private final List<class_1297> entityList;
    private final Color WHITE;
    private final Color GREY;
    private final Setting<Boolean> displayItems;
    private final Setting<Boolean> culling;
    private final Setting<SettingColor> names;
    private final Color GREEN;
    private final Setting<Boolean> displayPing;
    private final Setting<Boolean> displayMeteor;
    private final Map<class_1887, Integer> enchantmentsToShowScale;

    private List<class_1887> setDefaultList() {
        ArrayList<class_1887> arrayList = new ArrayList<class_1887>();
        for (class_1887 class_18872 : class_2378.field_11160) {
            arrayList.add(class_18872);
        }
        return arrayList;
    }

    private void renderNametagItem(class_1799 class_17992) {
        TextRenderer textRenderer = TextRenderer.get();
        NametagUtils.begin(this.pos);
        String string = class_17992.method_7964().getString();
        String string2 = String.valueOf(new StringBuilder().append(" x").append(class_17992.method_7947()));
        double d = textRenderer.getWidth(string);
        double d2 = textRenderer.getWidth(string2);
        double d3 = textRenderer.getHeight();
        double d4 = d;
        if (this.itemCount.get().booleanValue()) {
            d4 += d2;
        }
        double d5 = d4 / 2.0;
        this.drawBg(-d5, -d3, d4, d3);
        textRenderer.beginBig();
        double d6 = -d5;
        double d7 = -d3;
        d6 = textRenderer.render(string, d6, d7, this.WHITE);
        if (this.itemCount.get().booleanValue()) {
            textRenderer.render(string2, d6, d7, this.GOLD);
        }
        textRenderer.end();
        NametagUtils.end();
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.entityList.clear();
        boolean bl = !Modules.get().isActive(Freecam.class);
        class_243 class_2432 = this.mc.field_1773.method_19418().method_19326();
        this.mc.field_1687.method_18112().forEach(arg_0 -> this.lambda$onTick$0(bl, class_2432, arg_0));
        this.entityList.sort(Comparator.comparing(arg_0 -> Nametags.lambda$onTick$1(class_2432, arg_0)));
    }

    private void renderTntNametag(class_1541 class_15412) {
        TextRenderer textRenderer = TextRenderer.get();
        NametagUtils.begin(this.pos);
        String string = Nametags.ticksToTime(class_15412.method_6968());
        double d = textRenderer.getWidth(string);
        double d2 = textRenderer.getHeight();
        double d3 = d / 2.0;
        this.drawBg(-d3, -d2, d, d2);
        textRenderer.beginBig();
        double d4 = -d3;
        double d5 = -d2;
        textRenderer.render(string, d4, d5, this.names.get());
        textRenderer.end();
        NametagUtils.end();
    }

    @EventHandler
    private void onRender2D(Render2DEvent render2DEvent) {
        int n = this.getRenderCount();
        for (int i = n - 1; i > -1; --i) {
            class_1297 class_12972 = this.entityList.get(i);
            this.pos.set(class_12972, render2DEvent.tickDelta);
            this.pos.add(0.0, this.getHeight(class_12972), 0.0);
            class_1299 class_12992 = class_12972.method_5864();
            if (!NametagUtils.to2D(this.pos, this.scale.get())) continue;
            if (class_12992 == class_1299.field_6097) {
                this.renderNametagPlayer((class_1657)class_12972);
                continue;
            }
            if (class_12992 == class_1299.field_6052) {
                this.renderNametagItem(((class_1542)class_12972).method_6983());
                continue;
            }
            if (class_12992 == class_1299.field_6043) {
                this.renderNametagItem(((class_1533)class_12972).method_6940());
                continue;
            }
            if (class_12992 == class_1299.field_6063) {
                this.renderTntNametag((class_1541)class_12972);
                continue;
            }
            if (!(class_12972 instanceof class_1309)) continue;
            this.renderGenericNametag((class_1309)class_12972);
            if (-1 <= 1) continue;
            return;
        }
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.getRenderCount());
    }

    public Nametags() {
        super(Categories.Render, "nametags", "Displays customizable nametags above players.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgPlayers = this.settings.createGroup("Players");
        this.sgItems = this.settings.createGroup("Items");
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Select entities to draw nametags on.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(class_1299.field_6097, class_1299.field_6052)).build());
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of the nametag.").defaultValue(1.5).min(0.1).build());
        this.yourself = this.sgGeneral.add(new BoolSetting.Builder().name("self-nametag").description("Displays a nametag on your player if you're in Freecam.").defaultValue(true).build());
        this.background = this.sgGeneral.add(new ColorSetting.Builder().name("background").description("The color of the nametag background.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        this.names = this.sgGeneral.add(new ColorSetting.Builder().name("names").description("The color of the nametag names.").defaultValue(new SettingColor()).build());
        this.culling = this.sgGeneral.add(new BoolSetting.Builder().name("culling").description("Only render a certain number of nametags at a certain distance.").defaultValue(false).build());
        this.maxCullRange = this.sgGeneral.add(new DoubleSetting.Builder().name("cull-range").description("Only render nametags within this distance of your player.").defaultValue(20.0).min(0.0).sliderMax(200.0).visible(this.culling::get).build());
        this.maxCullCount = this.sgGeneral.add(new IntSetting.Builder().name("cull-count").description("Only render this many nametags.").defaultValue(50).min(1).sliderMin(1).sliderMax(100).visible(this.culling::get).build());
        this.displayItems = this.sgPlayers.add(new BoolSetting.Builder().name("display-items").description("Displays armor and hand items above the name tags.").defaultValue(true).build());
        this.itemSpacing = this.sgPlayers.add(new DoubleSetting.Builder().name("item-spacing").description("The spacing between items.").defaultValue(2.0).min(0.0).max(10.0).sliderMax(5.0).visible(this.displayItems::get).build());
        this.ignoreEmpty = this.sgPlayers.add(new BoolSetting.Builder().name("ignore-empty").description("Doesn't add spacing where an empty item stack would be.").defaultValue(true).visible(this.displayItems::get).build());
        this.displayItemEnchants = this.sgPlayers.add(new BoolSetting.Builder().name("display-enchants").description("Displays item enchantments on the items.").defaultValue(true).visible(this.displayItems::get).build());
        this.enchantPos = this.sgPlayers.add(new EnumSetting.Builder().name("enchantment-position").description("Where the enchantments are rendered.").defaultValue(Position.Above).visible(this.displayItemEnchants::get).build());
        this.enchantLength = this.sgPlayers.add(new IntSetting.Builder().name("enchant-name-length").description("The length enchantment names are trimmed to.").defaultValue(3).min(1).max(5).sliderMax(5).visible(this.displayItemEnchants::get).build());
        this.displayedEnchantments = this.sgPlayers.add(new EnchListSetting.Builder().name("displayed-enchantments").description("The enchantments that are shown on nametags.").defaultValue(this.setDefaultList()).visible(this.displayItemEnchants::get).build());
        this.enchantTextScale = this.sgPlayers.add(new DoubleSetting.Builder().name("enchant-text-scale").description("The scale of the enchantment text.").defaultValue(1.0).min(0.1).max(2.0).sliderMin(0.1).sliderMax(2.0).visible(this.displayItemEnchants::get).build());
        this.displayMeteor = this.sgPlayers.add(new BoolSetting.Builder().name("meteor").description("Shows if the player is using Meteor.").defaultValue(true).build());
        this.displayGameMode = this.sgPlayers.add(new BoolSetting.Builder().name("gamemode").description("Shows the player's GameMode.").defaultValue(true).build());
        this.displayPing = this.sgPlayers.add(new BoolSetting.Builder().name("ping").description("Shows the player's ping.").defaultValue(true).build());
        this.displayDistance = this.sgPlayers.add(new BoolSetting.Builder().name("distance").description("Shows the distance between you and the player.").defaultValue(true).build());
        this.itemCount = this.sgItems.add(new BoolSetting.Builder().name("count").description("Displays the number of items in the stack.").defaultValue(true).build());
        this.pos = new Vec3();
        this.itemWidths = new double[6];
        this.WHITE = new Color(255, 255, 255);
        this.RED = new Color(255, 25, 25);
        this.AMBER = new Color(255, 105, 25);
        this.GREEN = new Color(25, 252, 25);
        this.GOLD = new Color(232, 185, 35);
        this.GREY = new Color(150, 150, 150);
        this.METEOR = new Color(135, 0, 255);
        this.BLUE = new Color(20, 170, 170);
        this.enchantmentsToShowScale = new HashMap<class_1887, Integer>();
        this.entityList = new ArrayList<class_1297>();
    }

    private void lambda$onTick$0(boolean bl, class_243 class_2432, class_1297 class_12972) {
        class_1299 class_12992 = class_12972.method_5864();
        if (!this.entities.get().containsKey((Object)class_12992)) {
            return;
        }
        if (class_12992 == class_1299.field_6097 && (!this.yourself.get().booleanValue() || bl) && class_12972 == this.mc.field_1724) {
            return;
        }
        if (!this.culling.get().booleanValue() || class_12972.method_19538().method_1022(class_2432) < this.maxCullRange.get()) {
            this.entityList.add(class_12972);
        }
    }

    private class_1799 getItem(class_1657 class_16572, int n) {
        switch (n) {
            case 0: {
                return class_16572.method_6047();
            }
            case 1: {
                return (class_1799)class_16572.field_7514.field_7548.get(3);
            }
            case 2: {
                return (class_1799)class_16572.field_7514.field_7548.get(2);
            }
            case 3: {
                return (class_1799)class_16572.field_7514.field_7548.get(1);
            }
            case 4: {
                return (class_1799)class_16572.field_7514.field_7548.get(0);
            }
            case 5: {
                return class_16572.method_6079();
            }
        }
        return class_1799.field_8037;
    }

    private void drawBg(double d, double d2, double d3, double d4) {
        Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
        Renderer.NORMAL.quad(d - 1.0, d2 - 1.0, d3 + 2.0, d4 + 2.0, this.background.get());
        Renderer.NORMAL.end();
    }

    private int getRenderCount() {
        int n = this.culling.get() != false ? this.maxCullCount.get().intValue() : this.entityList.size();
        n = class_3532.method_15340((int)n, (int)0, (int)this.entityList.size());
        return n;
    }

    private static Double lambda$onTick$1(class_243 class_2432, class_1297 class_12972) {
        return class_12972.method_5707(class_2432);
    }

    private void renderNametagPlayer(class_1657 class_16572) {
        TextRenderer textRenderer = TextRenderer.get();
        NametagUtils.begin(this.pos);
        String string = "";
        if (this.displayMeteor.get().booleanValue() && MeteorPlayers.get(class_16572)) {
            string = "M ";
        }
        class_1934 class_19342 = EntityUtils.getGameMode(class_16572);
        String string2 = "BOT";
        if (class_19342 != null) {
            switch (class_19342) {
                case field_9219: {
                    string2 = "Sp";
                    break;
                }
                case field_9215: {
                    string2 = "S";
                    break;
                }
                case field_9220: {
                    string2 = "C";
                    break;
                }
                case field_9216: {
                    string2 = "A";
                }
            }
        }
        string2 = String.valueOf(new StringBuilder().append("[").append(string2).append("] "));
        Color color = PlayerUtils.getPlayerColor(class_16572, this.names.get());
        String string3 = class_16572 == this.mc.field_1724 ? Modules.get().get(NameProtect.class).getName(class_16572.method_5820()) : class_16572.method_5820();
        string3 = String.valueOf(new StringBuilder().append(string3).append(" "));
        float f = class_16572.method_6067();
        int n = Math.round(class_16572.method_6032() + f);
        double d = (float)n / (class_16572.method_6063() + f);
        String string4 = String.valueOf(n);
        Color color2 = d <= 0.333 ? this.RED : (d <= 0.666 ? this.AMBER : this.GREEN);
        int n2 = EntityUtils.getPing(class_16572);
        String string5 = String.valueOf(new StringBuilder().append(" [").append(n2).append("ms]"));
        double d2 = (double)Math.round(PlayerUtils.distanceToCamera((class_1297)class_16572) * 10.0) / 10.0;
        String string6 = String.valueOf(new StringBuilder().append(" ").append(d2).append("m"));
        double d3 = textRenderer.getWidth(string);
        double d4 = textRenderer.getWidth(string2);
        double d5 = textRenderer.getWidth(string3);
        double d6 = textRenderer.getWidth(string4);
        double d7 = textRenderer.getWidth(string5);
        double d8 = textRenderer.getWidth(string6);
        double d9 = d5 + d6;
        if (this.displayMeteor.get().booleanValue()) {
            d9 += d3;
        }
        if (this.displayGameMode.get().booleanValue()) {
            d9 += d4;
        }
        if (this.displayPing.get().booleanValue()) {
            d9 += d7;
        }
        if (this.displayDistance.get().booleanValue()) {
            d9 += d8;
        }
        double d10 = d9 / 2.0;
        double d11 = textRenderer.getHeight();
        this.drawBg(-d10, -d11, d9, d11);
        textRenderer.beginBig();
        double d12 = -d10;
        double d13 = -d11;
        if (this.displayMeteor.get().booleanValue()) {
            d12 = textRenderer.render(string, d12, d13, this.METEOR);
        }
        if (this.displayGameMode.get().booleanValue()) {
            d12 = textRenderer.render(string2, d12, d13, this.GOLD);
        }
        d12 = textRenderer.render(string3, d12, d13, color);
        d12 = textRenderer.render(string4, d12, d13, color2);
        if (this.displayPing.get().booleanValue()) {
            d12 = textRenderer.render(string5, d12, d13, this.BLUE);
        }
        if (this.displayDistance.get().booleanValue()) {
            textRenderer.render(string6, d12, d13, this.GREY);
        }
        textRenderer.end();
        if (this.displayItems.get().booleanValue()) {
            Arrays.fill(this.itemWidths, 0.0);
            boolean bl = false;
            int n3 = 0;
            for (int i = 0; i < 6; ++i) {
                class_1799 class_17992 = this.getItem(class_16572, i);
                if (!(this.itemWidths[i] != 0.0 || this.ignoreEmpty.get().booleanValue() && class_17992.method_7960())) {
                    this.itemWidths[i] = 32.0 + this.itemSpacing.get();
                }
                if (!class_17992.method_7960()) {
                    bl = true;
                }
                if (!this.displayItemEnchants.get().booleanValue()) continue;
                Map map = class_1890.method_8222((class_1799)class_17992);
                this.enchantmentsToShowScale.clear();
                for (class_1887 d17 : this.displayedEnchantments.get()) {
                    if (!map.containsKey(d17)) continue;
                    this.enchantmentsToShowScale.put(d17, (Integer)map.get(d17));
                }
                for (class_1887 class_18872 : this.enchantmentsToShowScale.keySet()) {
                    String string7 = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(class_18872, this.enchantLength.get())).append(" ").append(this.enchantmentsToShowScale.get(class_18872)));
                    this.itemWidths[i] = Math.max(this.itemWidths[i], textRenderer.getWidth(string7) / 2.0);
                }
                n3 = Math.max(n3, this.enchantmentsToShowScale.size());
                if (2 >= 2) continue;
                return;
            }
            double d14 = bl ? 32 : 0;
            double d15 = 0.0;
            for (double d16 : this.itemWidths) {
                d15 += d16;
                if (-1 != 4) continue;
                return;
            }
            double d17 = d15 / 2.0;
            double d18 = -d11 - 7.0 - d14;
            double d19 = -d17;
            for (int i = 0; i < 6; ++i) {
                class_1799 class_17992 = this.getItem(class_16572, i);
                GL11.glPushMatrix();
                GL11.glScaled((double)2.0, (double)2.0, (double)1.0);
                this.mc.method_1480().method_4010(class_17992, (int)(d19 / 2.0), (int)(d18 / 2.0));
                this.mc.method_1480().method_4025(this.mc.field_1772, class_17992, (int)(d19 / 2.0), (int)(d18 / 2.0));
                GL11.glPopMatrix();
                if (n3 > 0 && this.displayItemEnchants.get().booleanValue()) {
                    textRenderer.begin(0.5 * this.enchantTextScale.get(), false, true);
                    Map map = class_1890.method_8222((class_1799)class_17992);
                    HashMap<class_1887, Integer> hashMap = new HashMap<class_1887, Integer>();
                    for (class_1887 class_18873 : this.displayedEnchantments.get()) {
                        if (!map.containsKey(class_18873)) continue;
                        hashMap.put(class_18873, (Integer)map.get(class_18873));
                    }
                    double d20 = this.itemWidths[i];
                    double d21 = 0.0;
                    double d22 = 0.0;
                    switch (this.enchantPos.get()) {
                        case Above: {
                            d22 = -((double)(hashMap.size() + 1) * textRenderer.getHeight());
                            break;
                        }
                        case OnTop: {
                            d22 = (d14 - (double)hashMap.size() * textRenderer.getHeight()) / 2.0;
                        }
                    }
                    double d23 = d19;
                    for (class_1887 class_18874 : hashMap.keySet()) {
                        String string8 = String.valueOf(new StringBuilder().append(Utils.getEnchantSimpleName(class_18874, this.enchantLength.get())).append(" ").append(hashMap.get(class_18874)));
                        Color color3 = this.WHITE;
                        if (class_18874.method_8195()) {
                            color3 = this.RED;
                        }
                        switch (this.enchantPos.get()) {
                            case Above: {
                                d23 = d19 + d20 / 2.0 - textRenderer.getWidth(string8) / 2.0;
                                break;
                            }
                            case OnTop: {
                                d23 = d19 + (d20 - textRenderer.getWidth(string8)) / 2.0;
                            }
                        }
                        textRenderer.render(string8, d23, d18 + d22 + d21, color3);
                        d21 += textRenderer.getHeight();
                    }
                    textRenderer.end();
                }
                d19 += this.itemWidths[i];
                if (!false) continue;
                return;
            }
        } else if (this.displayItemEnchants.get().booleanValue()) {
            this.displayItemEnchants.set(false);
        }
        NametagUtils.end();
    }

    private void renderGenericNametag(class_1309 class_13092) {
        TextRenderer textRenderer = TextRenderer.get();
        NametagUtils.begin(this.pos);
        String string = class_13092.method_5864().method_5897().getString();
        string = String.valueOf(new StringBuilder().append(string).append(" "));
        float f = class_13092.method_6067();
        int n = Math.round(class_13092.method_6032() + f);
        double d = (float)n / (class_13092.method_6063() + f);
        String string2 = String.valueOf(n);
        Color color = d <= 0.333 ? this.RED : (d <= 0.666 ? this.AMBER : this.GREEN);
        double d2 = textRenderer.getWidth(string);
        double d3 = textRenderer.getWidth(string2);
        double d4 = textRenderer.getHeight();
        double d5 = d2 + d3;
        double d6 = d5 / 2.0;
        this.drawBg(-d6, -d4, d5, d4);
        textRenderer.beginBig();
        double d7 = -d6;
        double d8 = -d4;
        d7 = textRenderer.render(string, d7, d8, this.names.get());
        textRenderer.render(string2, d7, d8, color);
        textRenderer.end();
        NametagUtils.end();
    }

    private static String ticksToTime(int n) {
        if (n > 72000) {
            int n2 = n / 20 / 3600;
            return String.valueOf(new StringBuilder().append(n2).append(" h"));
        }
        if (n > 1200) {
            int n3 = n / 20 / 60;
            return String.valueOf(new StringBuilder().append(n3).append(" m"));
        }
        int n4 = n / 20;
        int n5 = n % 20 / 2;
        return String.valueOf(new StringBuilder().append(n4).append(".").append(n5).append(" s"));
    }

    private double getHeight(class_1297 class_12972) {
        double d = class_12972.method_18381(class_12972.method_18376());
        d = class_12972.method_5864() == class_1299.field_6052 || class_12972.method_5864() == class_1299.field_6043 ? (d += 0.2) : (d += 0.5);
        return d;
    }

    public static enum Position {
        Above,
        OnTop;

    }
}

