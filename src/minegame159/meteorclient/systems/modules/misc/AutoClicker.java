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
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Button> button;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int timer;
    private final /* synthetic */ Setting<Mode> mode;

    @Override
    public void onDeactivate() {
        AutoClicker lllllllllllllllllIllllIIlllIIlll;
        lllllllllllllllllIllllIIlllIIlll.mc.field_1690.field_1886.method_23481(false);
        lllllllllllllllllIllllIIlllIIlll.mc.field_1690.field_1904.method_23481(false);
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIllllIIllIllllI) {
        AutoClicker lllllllllllllllllIllllIIllIlllIl;
        block0 : switch (lllllllllllllllllIllllIIllIlllIl.mode.get()) {
            case Hold: {
                switch (lllllllllllllllllIllllIIllIlllIl.button.get()) {
                    case Left: {
                        lllllllllllllllllIllllIIllIlllIl.mc.field_1690.field_1886.method_23481(true);
                        break block0;
                    }
                    case Right: {
                        lllllllllllllllllIllllIIllIlllIl.mc.field_1690.field_1904.method_23481(true);
                    }
                }
                break;
            }
            case Press: {
                ++lllllllllllllllllIllllIIllIlllIl.timer;
                if (lllllllllllllllllIllllIIllIlllIl.delay.get() > lllllllllllllllllIllllIIllIlllIl.timer) break;
                switch (lllllllllllllllllIllllIIllIlllIl.button.get()) {
                    case Left: {
                        Utils.leftClick();
                        break;
                    }
                    case Right: {
                        Utils.rightClick();
                    }
                }
                lllllllllllllllllIllllIIllIlllIl.timer = 0;
            }
        }
    }

    public AutoClicker() {
        super(Categories.Player, "auto-clicker", "Automatically clicks.");
        AutoClicker lllllllllllllllllIllllIIllllIIll;
        lllllllllllllllllIllllIIllllIIll.sgGeneral = lllllllllllllllllIllllIIllllIIll.settings.getDefaultGroup();
        lllllllllllllllllIllllIIllllIIll.mode = lllllllllllllllllIllllIIllllIIll.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of clicking.").defaultValue(Mode.Press).build());
        lllllllllllllllllIllllIIllllIIll.button = lllllllllllllllllIllllIIllllIIll.sgGeneral.add(new EnumSetting.Builder().name("button").description("Which button to press.").defaultValue(Button.Right).build());
        lllllllllllllllllIllllIIllllIIll.delay = lllllllllllllllllIllllIIllllIIll.sgGeneral.add(new IntSetting.Builder().name("click-delay").description("The amount of delay between clicks in ticks.").defaultValue(2).min(0).sliderMax(60).build());
    }

    @Override
    public void onActivate() {
        AutoClicker lllllllllllllllllIllllIIlllIllll;
        lllllllllllllllllIllllIIlllIllll.timer = 0;
        lllllllllllllllllIllllIIlllIllll.mc.field_1690.field_1886.method_23481(false);
        lllllllllllllllllIllllIIlllIllll.mc.field_1690.field_1904.method_23481(false);
    }

    public static enum Mode {
        Hold,
        Press;


        private Mode() {
            Mode lIIIIIIIIlIIlII;
        }
    }

    public static enum Button {
        Right,
        Left;


        private Button() {
            Button lllllllllllllllllllIlllIlIIIIIIl;
        }
    }
}

