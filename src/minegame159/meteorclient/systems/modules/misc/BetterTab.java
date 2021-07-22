/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_5251
 *  net.minecraft.class_640
 */
package minegame159.meteorclient.systems.modules.misc;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friend;
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
    public final /* synthetic */ Setting<Integer> tabSize;
    private final /* synthetic */ Setting<SettingColor> selfColor;
    private final /* synthetic */ Setting<Boolean> meteor;
    private final /* synthetic */ Setting<SettingColor> meteorColor;
    private final /* synthetic */ Setting<Boolean> friends;
    private final /* synthetic */ Setting<Boolean> self;
    private final /* synthetic */ SettingGroup sgGeneral;

    public class_2561 getPlayerName(class_640 lllllllIlIIIIll) {
        BetterTab lllllllIlIIlIII;
        Color lllllllIlIIIlIl = null;
        class_2561 lllllllIlIIIllI = lllllllIlIIIIll.method_2971();
        if (lllllllIlIIIllI == null) {
            lllllllIlIIIllI = new class_2585(lllllllIlIIIIll.method_2966().getName());
        }
        if (lllllllIlIIIIll.method_2966().getId().toString().equals(lllllllIlIIlIII.mc.field_1724.method_7334().getId().toString()) && lllllllIlIIlIII.self.get().booleanValue()) {
            lllllllIlIIIlIl = lllllllIlIIlIII.selfColor.get();
        } else if (lllllllIlIIlIII.friends.get().booleanValue() && Friends.get().get(lllllllIlIIIIll.method_2966().getName()) != null) {
            Friend lllllllIlIIlIll = Friends.get().get(lllllllIlIIIIll.method_2966().getName());
            if (lllllllIlIIlIll != null) {
                lllllllIlIIIlIl = Friends.get().color;
            }
        } else if (lllllllIlIIlIII.meteor.get().booleanValue() && MeteorPlayers.get(lllllllIlIIIIll.method_2966().getId())) {
            lllllllIlIIIlIl = lllllllIlIIlIII.meteorColor.get();
        }
        if (lllllllIlIIIlIl != null) {
            String lllllllIlIIlIIl = lllllllIlIIIllI.getString();
            for (class_124 lllllllIlIIlIlI : class_124.values()) {
                if (!lllllllIlIIlIlI.method_543()) continue;
                lllllllIlIIlIIl = lllllllIlIIlIIl.replace(lllllllIlIIlIlI.toString(), "");
            }
            lllllllIlIIIllI = new class_2585(lllllllIlIIlIIl).method_10862(lllllllIlIIIllI.method_10866().method_27703(new class_5251(lllllllIlIIIlIl.getPacked())));
        }
        return lllllllIlIIIllI;
    }

    public BetterTab() {
        super(Categories.Misc, "better-tab", "Various improvements to the tab list.");
        BetterTab lllllllIlIlIllI;
        lllllllIlIlIllI.sgGeneral = lllllllIlIlIllI.settings.getDefaultGroup();
        lllllllIlIlIllI.tabSize = lllllllIlIlIllI.sgGeneral.add(new IntSetting.Builder().name("tablist-size").description("Bypasses the 80 player limit on the tablist.").defaultValue(100).min(1).sliderMax(1000).sliderMin(1).build());
        lllllllIlIlIllI.self = lllllllIlIlIllI.sgGeneral.add(new BoolSetting.Builder().name("highlight-self").description("Highlights yourself in the tablist.").defaultValue(true).build());
        lllllllIlIlIllI.selfColor = lllllllIlIlIllI.sgGeneral.add(new ColorSetting.Builder().name("self-color").description("The color to highlight your name with.").defaultValue(new SettingColor(250, 130, 30)).visible(lllllllIlIlIllI.self::get).build());
        lllllllIlIlIllI.friends = lllllllIlIlIllI.sgGeneral.add(new BoolSetting.Builder().name("highlight-friends").description("Highlights friends in the tablist.").defaultValue(true).build());
        lllllllIlIlIllI.meteor = lllllllIlIlIllI.sgGeneral.add(new BoolSetting.Builder().name("meteor-users").description("Shows if the player is using Meteor.").defaultValue(true).build());
        lllllllIlIlIllI.meteorColor = lllllllIlIlIllI.sgGeneral.add(new ColorSetting.Builder().name("meteor-color").description("The color to highlight meteor users with.").defaultValue(new SettingColor(135, 0, 255)).visible(lllllllIlIlIllI.meteor::get).build());
    }
}

