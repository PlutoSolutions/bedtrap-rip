/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class NoSlow
extends Module {
    private final Setting<Boolean> web;
    private final Setting<Boolean> soulSand;
    private final Setting<Boolean> airStrict;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> slimeBlock;
    private final Setting<Boolean> sneaking;
    private final Setting<Boolean> items;

    public boolean web() {
        return this.isActive() && this.web.get() != false;
    }

    public boolean soulSand() {
        return this.isActive() && this.soulSand.get() != false;
    }

    public boolean sneaking() {
        return this.isActive() && this.sneaking.get() != false;
    }

    public NoSlow() {
        super(Categories.Movement, "no-slow", "Allows you to move normally when using objects that will slow you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.items = this.sgGeneral.add(new BoolSetting.Builder().name("items").description("Whether or not using items will slow you.").defaultValue(true).build());
        this.web = this.sgGeneral.add(new BoolSetting.Builder().name("web").description("Whether or not cobwebs will not slow you down.").defaultValue(true).build());
        this.soulSand = this.sgGeneral.add(new BoolSetting.Builder().name("soul-sand").description("Whether or not Soul Sand will not slow you down.").defaultValue(true).build());
        this.slimeBlock = this.sgGeneral.add(new BoolSetting.Builder().name("slime-block").description("Whether or not slime blocks will not slow you down.").defaultValue(true).build());
        this.airStrict = this.sgGeneral.add(new BoolSetting.Builder().name("air-strict").description("Will attempt to bypass anti-cheats like 2b2t's. Only works while in air.").defaultValue(false).build());
        this.sneaking = this.sgGeneral.add(new BoolSetting.Builder().name("sneaking").description("Whether or not sneaking will not slow you down.").defaultValue(false).build());
    }

    public boolean slimeBlock() {
        return this.isActive() && this.slimeBlock.get() != false;
    }

    public boolean airStrict() {
        return this.isActive() && this.airStrict.get() != false && this.mc.field_1724.method_6115() && !this.mc.field_1724.method_24828();
    }

    public boolean items() {
        return this.isActive() && this.items.get() != false;
    }
}

