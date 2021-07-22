/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;

public class AntiAFK
extends Module {
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int messageI;
    private final /* synthetic */ Setting<Boolean> strafe;
    private /* synthetic */ int timer;
    private /* synthetic */ float prevYaw;
    private final /* synthetic */ Setting<Boolean> spin;
    private final /* synthetic */ List<String> messages;
    private final /* synthetic */ Setting<SpinMode> spinMode;
    private /* synthetic */ boolean direction;
    private final /* synthetic */ Setting<Double> pitch;
    private final /* synthetic */ Setting<Boolean> click;
    private final /* synthetic */ Random random;
    private final /* synthetic */ Setting<Integer> spinSpeed;
    private /* synthetic */ int strafeTimer;
    private final /* synthetic */ Setting<Boolean> disco;
    private final /* synthetic */ Setting<Boolean> randomMessage;
    private final /* synthetic */ Setting<Boolean> sendMessages;
    private final /* synthetic */ Setting<Boolean> jump;
    private final /* synthetic */ SettingGroup sgMessages;
    private final /* synthetic */ SettingGroup sgActions;

    public AntiAFK() {
        super(Categories.Player, "anti-afk", "Performs different actions to prevent getting kicked for AFK reasons.");
        AntiAFK lllllllllllllllllllIllIllIIIIlII;
        lllllllllllllllllllIllIllIIIIlII.sgActions = lllllllllllllllllllIllIllIIIIlII.settings.createGroup("Actions");
        lllllllllllllllllllIllIllIIIIlII.sgMessages = lllllllllllllllllllIllIllIIIIlII.settings.createGroup("Messages");
        lllllllllllllllllllIllIllIIIIlII.spin = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new BoolSetting.Builder().name("spin").description("Spins.").defaultValue(true).build());
        lllllllllllllllllllIllIllIIIIlII.spinMode = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new EnumSetting.Builder().name("spin-mode").description("The method of rotating.").defaultValue(SpinMode.Server).visible(lllllllllllllllllllIllIllIIIIlII.spin::get).build());
        lllllllllllllllllllIllIllIIIIlII.spinSpeed = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new IntSetting.Builder().name("spin-speed").description("The speed to spin you.").defaultValue(7).visible(lllllllllllllllllllIllIllIIIIlII.spin::get).build());
        lllllllllllllllllllIllIllIIIIlII.pitch = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new DoubleSetting.Builder().name("pitch").description("The pitch to set in server mode.").defaultValue(-90.0).min(-90.0).max(90.0).sliderMin(-90.0).sliderMax(90.0).visible(() -> {
            AntiAFK lllllllllllllllllllIllIlIIIIlllI;
            return lllllllllllllllllllIllIlIIIIlllI.spin.get() != false && lllllllllllllllllllIllIlIIIIlllI.spinMode.get() == SpinMode.Server;
        }).build());
        lllllllllllllllllllIllIllIIIIlII.jump = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new BoolSetting.Builder().name("jump").description("Jumps.").defaultValue(true).build());
        lllllllllllllllllllIllIllIIIIlII.click = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new BoolSetting.Builder().name("click").description("Clicks.").defaultValue(false).build());
        lllllllllllllllllllIllIllIIIIlII.disco = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new BoolSetting.Builder().name("disco").description("Sneaks and unsneaks quickly.").defaultValue(false).build());
        lllllllllllllllllllIllIllIIIIlII.strafe = lllllllllllllllllllIllIllIIIIlII.sgActions.add(new BoolSetting.Builder().name("strafe").description("Strafe right and left").defaultValue(false).onChanged(lllllllllllllllllllIllIlIIIlIIlI -> {
            AntiAFK lllllllllllllllllllIllIlIIIlIIIl;
            lllllllllllllllllllIllIlIIIlIIIl.strafeTimer = 0;
            lllllllllllllllllllIllIlIIIlIIIl.direction = false;
            if (lllllllllllllllllllIllIlIIIlIIIl.isActive()) {
                lllllllllllllllllllIllIlIIIlIIIl.mc.field_1690.field_1913.method_23481(false);
                lllllllllllllllllllIllIlIIIlIIIl.mc.field_1690.field_1849.method_23481(false);
            }
        }).build());
        lllllllllllllllllllIllIllIIIIlII.sendMessages = lllllllllllllllllllIllIllIIIIlII.sgMessages.add(new BoolSetting.Builder().name("send-messages").description("Sends messages to prevent getting kicked for AFK.").defaultValue(false).build());
        lllllllllllllllllllIllIllIIIIlII.delay = lllllllllllllllllllIllIllIIIIlII.sgMessages.add(new IntSetting.Builder().name("delay").description("The delay between specified messages in seconds.").defaultValue(2).min(0).sliderMax(20).build());
        lllllllllllllllllllIllIllIIIIlII.randomMessage = lllllllllllllllllllIllIllIIIIlII.sgMessages.add(new BoolSetting.Builder().name("random").description("Selects a random message from your message list.").defaultValue(false).build());
        lllllllllllllllllllIllIllIIIIlII.messages = new ArrayList<String>();
        lllllllllllllllllllIllIllIIIIlII.strafeTimer = 0;
        lllllllllllllllllllIllIllIIIIlII.direction = false;
        lllllllllllllllllllIllIllIIIIlII.random = new Random();
    }

    @Override
    public WWidget getWidget(GuiTheme lllllllllllllllllllIllIlIlllIIIl) {
        AntiAFK lllllllllllllllllllIllIlIlllIIlI;
        lllllllllllllllllllIllIlIlllIIlI.messages.removeIf(String::isEmpty);
        WTable lllllllllllllllllllIllIlIlllIIII = lllllllllllllllllllIllIlIlllIIIl.table();
        lllllllllllllllllllIllIlIlllIIlI.fillTable(lllllllllllllllllllIllIlIlllIIIl, lllllllllllllllllllIllIlIlllIIII);
        return lllllllllllllllllllIllIlIlllIIII;
    }

    @Override
    public void onActivate() {
        AntiAFK lllllllllllllllllllIllIllIIIIIIl;
        lllllllllllllllllllIllIllIIIIIIl.prevYaw = lllllllllllllllllllIllIllIIIIIIl.mc.field_1724.field_6031;
        lllllllllllllllllllIllIllIIIIIIl.timer = lllllllllllllllllllIllIllIIIIIIl.delay.get() * 20;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIllIlIllllIII) {
        if (Utils.canUpdate()) {
            AntiAFK lllllllllllllllllllIllIlIlllIlll;
            if (lllllllllllllllllllIllIlIlllIlll.spin.get().booleanValue()) {
                lllllllllllllllllllIllIlIlllIlll.prevYaw += (float)lllllllllllllllllllIllIlIlllIlll.spinSpeed.get().intValue();
                switch (lllllllllllllllllllIllIlIlllIlll.spinMode.get()) {
                    case Client: {
                        lllllllllllllllllllIllIlIlllIlll.mc.field_1724.field_6031 = lllllllllllllllllllIllIlIlllIlll.prevYaw;
                        break;
                    }
                    case Server: {
                        Rotations.rotate(lllllllllllllllllllIllIlIlllIlll.prevYaw, lllllllllllllllllllIllIlIlllIlll.pitch.get(), -15, null);
                    }
                }
            }
            if (lllllllllllllllllllIllIlIlllIlll.jump.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1903.method_1434()) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1903.method_23481(false);
            }
            if (lllllllllllllllllllIllIlIlllIlll.jump.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1832.method_1434()) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1832.method_23481(false);
            } else if (lllllllllllllllllllIllIlIlllIlll.jump.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.random.nextInt(99) + 1 == 50) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1903.method_23481(true);
            }
            if (lllllllllllllllllllIllIlIlllIlll.click.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.random.nextInt(99) + 1 == 45) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1886.method_23481(true);
                Utils.leftClick();
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1886.method_23481(false);
            }
            if (lllllllllllllllllllIllIlIlllIlll.disco.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.random.nextInt(24) + 1 == 15) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1832.method_23481(true);
            }
            if (lllllllllllllllllllIllIlIlllIlll.sendMessages.get().booleanValue() && !lllllllllllllllllllIllIlIlllIlll.messages.isEmpty()) {
                if (lllllllllllllllllllIllIlIlllIlll.timer <= 0) {
                    int lllllllllllllllllllIllIlIllllIlI;
                    if (lllllllllllllllllllIllIlIlllIlll.randomMessage.get().booleanValue()) {
                        int lllllllllllllllllllIllIlIllllIll = Utils.random(0, lllllllllllllllllllIllIlIlllIlll.messages.size());
                    } else {
                        if (lllllllllllllllllllIllIlIlllIlll.messageI >= lllllllllllllllllllIllIlIlllIlll.messages.size()) {
                            lllllllllllllllllllIllIlIlllIlll.messageI = 0;
                        }
                        lllllllllllllllllllIllIlIllllIlI = lllllllllllllllllllIllIlIlllIlll.messageI++;
                    }
                    lllllllllllllllllllIllIlIlllIlll.mc.field_1724.method_3142(lllllllllllllllllllIllIlIlllIlll.messages.get(lllllllllllllllllllIllIlIllllIlI));
                    lllllllllllllllllllIllIlIlllIlll.timer = lllllllllllllllllllIllIlIlllIlll.delay.get() * 20;
                } else {
                    --lllllllllllllllllllIllIlIlllIlll.timer;
                }
            }
            if (lllllllllllllllllllIllIlIlllIlll.strafe.get().booleanValue() && lllllllllllllllllllIllIlIlllIlll.strafeTimer == 20) {
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1913.method_23481(!lllllllllllllllllllIllIlIlllIlll.direction);
                lllllllllllllllllllIllIlIlllIlll.mc.field_1690.field_1849.method_23481(lllllllllllllllllllIllIlIlllIlll.direction);
                lllllllllllllllllllIllIlIlllIlll.direction = !lllllllllllllllllllIllIlIlllIlll.direction;
                lllllllllllllllllllIllIlIlllIlll.strafeTimer = 0;
            } else {
                ++lllllllllllllllllllIllIlIlllIlll.strafeTimer;
            }
        }
    }

    @Override
    public void onDeactivate() {
        AntiAFK lllllllllllllllllllIllIlIllllllI;
        if (lllllllllllllllllllIllIlIllllllI.strafe.get().booleanValue()) {
            lllllllllllllllllllIllIlIllllllI.mc.field_1690.field_1913.method_23481(false);
            lllllllllllllllllllIllIlIllllllI.mc.field_1690.field_1849.method_23481(false);
        }
    }

    @Override
    public class_2487 toTag() {
        AntiAFK lllllllllllllllllllIllIlIlIIlIlI;
        class_2487 lllllllllllllllllllIllIlIlIIllII = super.toTag();
        lllllllllllllllllllIllIlIlIIlIlI.messages.removeIf(String::isEmpty);
        class_2499 lllllllllllllllllllIllIlIlIIlIll = new class_2499();
        for (String lllllllllllllllllllIllIlIlIIlllI : lllllllllllllllllllIllIlIlIIlIlI.messages) {
            lllllllllllllllllllIllIlIlIIlIll.add((Object)class_2519.method_23256((String)lllllllllllllllllllIllIlIlIIlllI));
        }
        lllllllllllllllllllIllIlIlIIllII.method_10566("messages", (class_2520)lllllllllllllllllllIllIlIlIIlIll);
        return lllllllllllllllllllIllIlIlIIllII;
    }

    @Override
    public Module fromTag(class_2487 lllllllllllllllllllIllIlIIllllIl) {
        AntiAFK lllllllllllllllllllIllIlIIllllII;
        lllllllllllllllllllIllIlIIllllII.messages.clear();
        if (lllllllllllllllllllIllIlIIllllIl.method_10545("messages")) {
            class_2499 lllllllllllllllllllIllIlIIllllll = lllllllllllllllllllIllIlIIllllIl.method_10554("messages", 8);
            for (class_2520 lllllllllllllllllllIllIlIlIIIIII : lllllllllllllllllllIllIlIIllllll) {
                lllllllllllllllllllIllIlIIllllII.messages.add(lllllllllllllllllllIllIlIlIIIIII.method_10714());
            }
        } else {
            lllllllllllllllllllIllIlIIllllII.messages.add("This is an AntiAFK message. Meteor on Crack!");
        }
        return super.fromTag(lllllllllllllllllllIllIlIIllllIl);
    }

    private void fillTable(GuiTheme lllllllllllllllllllIllIlIlIllllI, WTable lllllllllllllllllllIllIlIlIlllIl) {
        AntiAFK lllllllllllllllllllIllIlIlIlllll;
        lllllllllllllllllllIllIlIlIlllIl.add(lllllllllllllllllllIllIlIlIllllI.horizontalSeparator("Message List")).expandX();
        for (int lllllllllllllllllllIllIlIllIIIII = 0; lllllllllllllllllllIllIlIllIIIII < lllllllllllllllllllIllIlIlIlllll.messages.size(); ++lllllllllllllllllllIllIlIllIIIII) {
            int lllllllllllllllllllIllIlIllIIlII = lllllllllllllllllllIllIlIllIIIII;
            String lllllllllllllllllllIllIlIllIIIll = lllllllllllllllllllIllIlIlIlllll.messages.get(lllllllllllllllllllIllIlIllIIIII);
            WTextBox lllllllllllllllllllIllIlIllIIIlI = lllllllllllllllllllIllIlIlIlllIl.add(lllllllllllllllllllIllIlIlIllllI.textBox(lllllllllllllllllllIllIlIllIIIll)).minWidth(100.0).expandX().widget();
            lllllllllllllllllllIllIlIllIIIlI.action = () -> {
                AntiAFK lllllllllllllllllllIllIlIIIllIlI;
                lllllllllllllllllllIllIlIIIllIlI.messages.set(lllllllllllllllllllIllIlIllIIlII, lllllllllllllllllllIllIlIllIIIlI.get());
            };
            WMinus lllllllllllllllllllIllIlIllIIIIl = lllllllllllllllllllIllIlIlIlllIl.add(lllllllllllllllllllIllIlIlIllllI.minus()).widget();
            lllllllllllllllllllIllIlIllIIIIl.action = () -> {
                AntiAFK lllllllllllllllllllIllIlIIlIIlIl;
                lllllllllllllllllllIllIlIIlIIlIl.messages.remove(lllllllllllllllllllIllIlIllIIlII);
                lllllllllllllllllllIllIlIlIlllIl.clear();
                lllllllllllllllllllIllIlIIlIIlIl.fillTable(lllllllllllllllllllIllIlIlIllllI, lllllllllllllllllllIllIlIlIlllIl);
            };
            lllllllllllllllllllIllIlIlIlllIl.row();
        }
        WPlus lllllllllllllllllllIllIlIlIlllII = lllllllllllllllllllIllIlIlIlllIl.add(lllllllllllllllllllIllIlIlIllllI.plus()).expandCellX().right().widget();
        lllllllllllllllllllIllIlIlIlllII.action = () -> {
            AntiAFK lllllllllllllllllllIllIlIIlIllll;
            lllllllllllllllllllIllIlIIlIllll.messages.add("");
            lllllllllllllllllllIllIlIlIlllIl.clear();
            lllllllllllllllllllIllIlIIlIllll.fillTable(lllllllllllllllllllIllIlIlIllllI, lllllllllllllllllllIllIlIlIlllIl);
        };
    }

    public static enum SpinMode {
        Server,
        Client;


        private SpinMode() {
            SpinMode llllIIlIIIlII;
        }
    }
}

