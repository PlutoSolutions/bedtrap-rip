/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class AntiAFK
extends Module {
    private final Setting<Integer> delay;
    private int messageI;
    private final Setting<Boolean> strafe;
    private int timer;
    private float prevYaw;
    private final Setting<Boolean> spin;
    private final List<String> messages;
    private final Setting<SpinMode> spinMode;
    private boolean direction;
    private final Setting<Double> pitch;
    private final Setting<Boolean> click;
    private final Random random;
    private final Setting<Integer> spinSpeed;
    private int strafeTimer;
    private final Setting<Boolean> disco;
    private final Setting<Boolean> randomMessage;
    private final Setting<Boolean> sendMessages;
    private final Setting<Boolean> jump;
    private final SettingGroup sgMessages;
    private final SettingGroup sgActions;

    private void lambda$fillTable$4(WTable wTable, GuiTheme guiTheme) {
        this.messages.add("");
        wTable.clear();
        this.fillTable(guiTheme, wTable);
    }

    public AntiAFK() {
        super(Categories.Player, "anti-afk", "Performs different actions to prevent getting kicked for AFK reasons.");
        this.sgActions = this.settings.createGroup("Actions");
        this.sgMessages = this.settings.createGroup("Messages");
        this.spin = this.sgActions.add(new BoolSetting.Builder().name("spin").description("Spins.").defaultValue(true).build());
        this.spinMode = this.sgActions.add(new EnumSetting.Builder().name("spin-mode").description("The method of rotating.").defaultValue(SpinMode.Server).visible(this.spin::get).build());
        this.spinSpeed = this.sgActions.add(new IntSetting.Builder().name("spin-speed").description("The speed to spin you.").defaultValue(7).visible(this.spin::get).build());
        this.pitch = this.sgActions.add(new DoubleSetting.Builder().name("pitch").description("The pitch to set in server mode.").defaultValue(-90.0).min(-90.0).max(90.0).sliderMin(-90.0).sliderMax(90.0).visible(this::lambda$new$0).build());
        this.jump = this.sgActions.add(new BoolSetting.Builder().name("jump").description("Jumps.").defaultValue(true).build());
        this.click = this.sgActions.add(new BoolSetting.Builder().name("click").description("Clicks.").defaultValue(false).build());
        this.disco = this.sgActions.add(new BoolSetting.Builder().name("disco").description("Sneaks and unsneaks quickly.").defaultValue(false).build());
        this.strafe = this.sgActions.add(new BoolSetting.Builder().name("strafe").description("Strafe right and left").defaultValue(false).onChanged(this::lambda$new$1).build());
        this.sendMessages = this.sgMessages.add(new BoolSetting.Builder().name("send-messages").description("Sends messages to prevent getting kicked for AFK.").defaultValue(false).build());
        this.delay = this.sgMessages.add(new IntSetting.Builder().name("delay").description("The delay between specified messages in seconds.").defaultValue(2).min(0).sliderMax(20).build());
        this.randomMessage = this.sgMessages.add(new BoolSetting.Builder().name("random").description("Selects a random message from your message list.").defaultValue(false).build());
        this.messages = new ArrayList<String>();
        this.strafeTimer = 0;
        this.direction = false;
        this.random = new Random();
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        this.messages.removeIf(String::isEmpty);
        WTable wTable = guiTheme.table();
        this.fillTable(guiTheme, wTable);
        return wTable;
    }

    private void lambda$fillTable$3(int n, WTable wTable, GuiTheme guiTheme) {
        this.messages.remove(n);
        wTable.clear();
        this.fillTable(guiTheme, wTable);
    }

    @Override
    public void onActivate() {
        this.prevYaw = this.mc.field_1724.field_6031;
        this.timer = this.delay.get() * 20;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (Utils.canUpdate()) {
            if (this.spin.get().booleanValue()) {
                this.prevYaw += (float)this.spinSpeed.get().intValue();
                switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$AntiAFK$SpinMode[this.spinMode.get().ordinal()]) {
                    case 1: {
                        this.mc.field_1724.field_6031 = this.prevYaw;
                        break;
                    }
                    case 2: {
                        Rotations.rotate(this.prevYaw, this.pitch.get(), -15, null);
                    }
                }
            }
            if (this.jump.get().booleanValue() && this.mc.field_1690.field_1903.method_1434()) {
                this.mc.field_1690.field_1903.method_23481(false);
            }
            if (this.jump.get().booleanValue() && this.mc.field_1690.field_1832.method_1434()) {
                this.mc.field_1690.field_1832.method_23481(false);
            } else if (this.jump.get().booleanValue() && this.random.nextInt(99) + 1 == 50) {
                this.mc.field_1690.field_1903.method_23481(true);
            }
            if (this.click.get().booleanValue() && this.random.nextInt(99) + 1 == 45) {
                this.mc.field_1690.field_1886.method_23481(true);
                Utils.leftClick();
                this.mc.field_1690.field_1886.method_23481(false);
            }
            if (this.disco.get().booleanValue() && this.random.nextInt(24) + 1 == 15) {
                this.mc.field_1690.field_1832.method_23481(true);
            }
            if (this.sendMessages.get().booleanValue() && !this.messages.isEmpty()) {
                if (this.timer <= 0) {
                    int n;
                    if (this.randomMessage.get().booleanValue()) {
                        n = Utils.random(0, this.messages.size());
                    } else {
                        if (this.messageI >= this.messages.size()) {
                            this.messageI = 0;
                        }
                        n = this.messageI++;
                    }
                    this.mc.field_1724.method_3142(this.messages.get(n));
                    this.timer = this.delay.get() * 20;
                } else {
                    --this.timer;
                }
            }
            if (this.strafe.get().booleanValue() && this.strafeTimer == 20) {
                this.mc.field_1690.field_1913.method_23481(!this.direction);
                this.mc.field_1690.field_1849.method_23481(this.direction);
                this.direction = !this.direction;
                this.strafeTimer = 0;
            } else {
                ++this.strafeTimer;
            }
        }
    }

    @Override
    public void onDeactivate() {
        if (this.strafe.get().booleanValue()) {
            this.mc.field_1690.field_1913.method_23481(false);
            this.mc.field_1690.field_1849.method_23481(false);
        }
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = super.toTag();
        this.messages.removeIf(String::isEmpty);
        class_2499 class_24992 = new class_2499();
        for (String string : this.messages) {
            class_24992.add((Object)class_2519.method_23256((String)string));
        }
        class_24872.method_10566("messages", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    public Module fromTag(class_2487 class_24872) {
        this.messages.clear();
        if (class_24872.method_10545("messages")) {
            class_2499 class_24992 = class_24872.method_10554("messages", 8);
            for (class_2520 class_25202 : class_24992) {
                this.messages.add(class_25202.method_10714());
            }
        } else {
            this.messages.add("This is an AntiAFK message. Meteor on Crack!");
        }
        return super.fromTag(class_24872);
    }

    private void lambda$fillTable$2(int n, WTextBox wTextBox) {
        this.messages.set(n, wTextBox.get());
    }

    private void fillTable(GuiTheme guiTheme, WTable wTable) {
        wTable.add(guiTheme.horizontalSeparator("Message List")).expandX();
        for (int i = 0; i < this.messages.size(); ++i) {
            int n = i;
            String string = this.messages.get(i);
            WTextBox wTextBox = wTable.add(guiTheme.textBox(string)).minWidth(100.0).expandX().widget();
            wTextBox.action = () -> this.lambda$fillTable$2(n, wTextBox);
            WMinus wMinus = wTable.add(guiTheme.minus()).widget();
            wMinus.action = () -> this.lambda$fillTable$3(n, wTable, guiTheme);
            wTable.row();
            if (false <= true) continue;
            return;
        }
        WPlus wPlus = wTable.add(guiTheme.plus()).expandCellX().right().widget();
        wPlus.action = () -> this.lambda$fillTable$4(wTable, guiTheme);
    }

    private void lambda$new$1(Boolean bl) {
        this.strafeTimer = 0;
        this.direction = false;
        if (this.isActive()) {
            this.mc.field_1690.field_1913.method_23481(false);
            this.mc.field_1690.field_1849.method_23481(false);
        }
    }

    private boolean lambda$new$0() {
        return this.spin.get() != false && this.spinMode.get() == SpinMode.Server;
    }

    public static enum SpinMode {
        Server,
        Client;

    }
}

