/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class NoSlow
extends Module {
    private final /* synthetic */ Setting<Boolean> web;
    private final /* synthetic */ Setting<Boolean> soulSand;
    private final /* synthetic */ Setting<Boolean> airStrict;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> slimeBlock;
    private final /* synthetic */ Setting<Boolean> sneaking;
    private final /* synthetic */ Setting<Boolean> items;

    public boolean web() {
        NoSlow llIIlIIIlllllII;
        return llIIlIIIlllllII.isActive() && llIIlIIIlllllII.web.get() != false;
    }

    public boolean soulSand() {
        NoSlow llIIlIIIllllIII;
        return llIIlIIIllllIII.isActive() && llIIlIIIllllIII.soulSand.get() != false;
    }

    public boolean sneaking() {
        NoSlow llIIlIIIlllIIll;
        return llIIlIIIlllIIll.isActive() && llIIlIIIlllIIll.sneaking.get() != false;
    }

    public NoSlow() {
        super(Categories.Movement, "no-slow", "Allows you to move normally when using objects that will slow you.");
        NoSlow llIIlIIlIIIIlII;
        llIIlIIlIIIIlII.sgGeneral = llIIlIIlIIIIlII.settings.getDefaultGroup();
        llIIlIIlIIIIlII.items = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("items").description("Whether or not using items will slow you.").defaultValue(true).build());
        llIIlIIlIIIIlII.web = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("web").description("Whether or not cobwebs will not slow you down.").defaultValue(true).build());
        llIIlIIlIIIIlII.soulSand = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("soul-sand").description("Whether or not Soul Sand will not slow you down.").defaultValue(true).build());
        llIIlIIlIIIIlII.slimeBlock = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("slime-block").description("Whether or not slime blocks will not slow you down.").defaultValue(true).build());
        llIIlIIlIIIIlII.airStrict = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("air-strict").description("Will attempt to bypass anti-cheats like 2b2t's. Only works while in air.").defaultValue(false).build());
        llIIlIIlIIIIlII.sneaking = llIIlIIlIIIIlII.sgGeneral.add(new BoolSetting.Builder().name("sneaking").description("Whether or not sneaking will not slow you down.").defaultValue(false).build());
    }

    public boolean slimeBlock() {
        NoSlow llIIlIIIlllIlIl;
        return llIIlIIIlllIlIl.isActive() && llIIlIIIlllIlIl.slimeBlock.get() != false;
    }

    public boolean airStrict() {
        NoSlow llIIlIIlIIIIIlI;
        return llIIlIIlIIIIIlI.isActive() && llIIlIIlIIIIIlI.airStrict.get() != false && llIIlIIlIIIIIlI.mc.field_1724.method_6115() && !llIIlIIlIIIIIlI.mc.field_1724.method_24828();
    }

    public boolean items() {
        NoSlow llIIlIIIllllllI;
        return llIIlIIIllllllI.isActive() && llIIlIIIllllllI.items.get() != false;
    }
}

