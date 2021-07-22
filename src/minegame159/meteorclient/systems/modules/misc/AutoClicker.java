/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;

public class AutoClicker
extends Module {
    private final Setting<Integer> delay;
    private final Setting<Button> button;
    private final SettingGroup sgGeneral;
    private int timer;
    private final Setting<Mode> mode;

    @Override
    public void onDeactivate() {
        this.mc.field_1690.field_1886.method_23481(false);
        this.mc.field_1690.field_1904.method_23481(false);
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        block0 : switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$misc$AutoClicker$Mode[this.mode.get().ordinal()]) {
            case 1: {
                switch (this.button.get()) {
                    case Left: {
                        this.mc.field_1690.field_1886.method_23481(true);
                        break block0;
                    }
                    case Right: {
                        this.mc.field_1690.field_1904.method_23481(true);
                    }
                }
                break;
            }
            case 2: {
                ++this.timer;
                if (this.delay.get() > this.timer) break;
                switch (this.button.get()) {
                    case Left: {
                        Utils.leftClick();
                        break;
                    }
                    case Right: {
                        Utils.rightClick();
                    }
                }
                this.timer = 0;
            }
        }
    }

    public AutoClicker() {
        super(Categories.Player, "auto-clicker", "Automatically clicks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of clicking.").defaultValue(Mode.Press).build());
        this.button = this.sgGeneral.add(new EnumSetting.Builder().name("button").description("Which button to press.").defaultValue(Button.Right).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("click-delay").description("The amount of delay between clicks in ticks.").defaultValue(2).min(0).sliderMax(60).build());
    }

    @Override
    public void onActivate() {
        this.timer = 0;
        this.mc.field_1690.field_1886.method_23481(false);
        this.mc.field_1690.field_1904.method_23481(false);
    }

    public static enum Mode {
        Hold,
        Press;

    }

    public static enum Button {
        Right,
        Left;

    }
}

