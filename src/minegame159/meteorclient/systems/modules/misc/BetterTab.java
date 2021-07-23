/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.MeteorPlayers;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_5251;
import net.minecraft.class_640;

public class BetterTab
extends Module {
    public final Setting<Integer> tabSize;
    private final Setting<SettingColor> selfColor;
    private final Setting<Boolean> meteor;
    private final Setting<SettingColor> meteorColor;
    private final Setting<Boolean> friends;
    private final Setting<Boolean> self;
    private final SettingGroup sgGeneral;

    public class_2561 getPlayerName(class_640 class_6402) {
        Object object;
        Color color = null;
        class_2561 class_25612 = class_6402.method_2971();
        if (class_25612 == null) {
            class_25612 = new class_2585(class_6402.method_2966().getName());
        }
        if (class_6402.method_2966().getId().toString().equals(this.mc.field_1724.method_7334().getId().toString()) && this.self.get().booleanValue()) {
            color = this.selfColor.get();
        } else if (this.friends.get().booleanValue() && Friends.get().get(class_6402.method_2966().getName()) != null) {
            object = Friends.get().get(class_6402.method_2966().getName());
            if (object != null) {
                color = Friends.get().color;
            }
        } else if (this.meteor.get().booleanValue() && MeteorPlayers.get(class_6402.method_2966().getId())) {
            color = this.meteorColor.get();
        }
        if (color != null) {
            object = class_25612.getString();
            for (class_124 class_1242 : class_124.values()) {
                if (!class_1242.method_543()) continue;
                object = ((String)object).replace(class_1242.toString(), "");
                if (-2 < 0) continue;
                return null;
            }
            class_25612 = new class_2585((String)object).method_10862(class_25612.method_10866().method_27703(new class_5251(color.getPacked())));
        }
        return class_25612;
    }

    public BetterTab() {
        super(Categories.Misc, "better-tab", "Various improvements to the tab list.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.tabSize = this.sgGeneral.add(new IntSetting.Builder().name("tablist-size").description("Bypasses the 80 player limit on the tablist.").defaultValue(100).min(1).sliderMax(1000).sliderMin(1).build());
        this.self = this.sgGeneral.add(new BoolSetting.Builder().name("highlight-self").description("Highlights yourself in the tablist.").defaultValue(true).build());
        this.selfColor = this.sgGeneral.add(new ColorSetting.Builder().name("self-color").description("The color to highlight your name with.").defaultValue(new SettingColor(250, 130, 30)).visible(this.self::get).build());
        this.friends = this.sgGeneral.add(new BoolSetting.Builder().name("highlight-friends").description("Highlights friends in the tablist.").defaultValue(true).build());
        this.meteor = this.sgGeneral.add(new BoolSetting.Builder().name("meteor-users").description("Shows if the player is using Meteor.").defaultValue(true).build());
        this.meteorColor = this.sgGeneral.add(new ColorSetting.Builder().name("meteor-color").description("The color to highlight meteor users with.").defaultValue(new SettingColor(135, 0, 255)).visible(this.meteor::get).build());
    }
}

