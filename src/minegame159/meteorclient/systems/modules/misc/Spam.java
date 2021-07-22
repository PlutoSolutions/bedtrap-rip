/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.modules.misc;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;

public class Spam
extends Module {
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int messageI;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int timer;
    private final /* synthetic */ List<String> messages;
    private final /* synthetic */ Setting<Boolean> random;

    @Override
    public void onActivate() {
        Spam llllllllllllllllllIIlIIlIlllIIll;
        llllllllllllllllllIIlIIlIlllIIll.timer = llllllllllllllllllIIlIIlIlllIIll.delay.get() * 20;
        llllllllllllllllllIIlIIlIlllIIll.messageI = 0;
    }

    @Override
    public class_2487 toTag() {
        Spam llllllllllllllllllIIlIIlIlIIIIIl;
        class_2487 llllllllllllllllllIIlIIlIlIIIIII = super.toTag();
        llllllllllllllllllIIlIIlIlIIIIIl.messages.removeIf(String::isEmpty);
        class_2499 llllllllllllllllllIIlIIlIIllllll = new class_2499();
        for (String llllllllllllllllllIIlIIlIlIIIIlI : llllllllllllllllllIIlIIlIlIIIIIl.messages) {
            llllllllllllllllllIIlIIlIIllllll.add((Object)class_2519.method_23256((String)llllllllllllllllllIIlIIlIlIIIIlI));
        }
        llllllllllllllllllIIlIIlIlIIIIII.method_10566("messages", (class_2520)llllllllllllllllllIIlIIlIIllllll);
        return llllllllllllllllllIIlIIlIlIIIIII;
    }

    @Override
    public WWidget getWidget(GuiTheme llllllllllllllllllIIlIIlIllIIIlI) {
        Spam llllllllllllllllllIIlIIlIllIIIll;
        llllllllllllllllllIIlIIlIllIIIll.messages.removeIf(String::isEmpty);
        WTable llllllllllllllllllIIlIIlIllIIlII = llllllllllllllllllIIlIIlIllIIIlI.table();
        llllllllllllllllllIIlIIlIllIIIll.fillTable(llllllllllllllllllIIlIIlIllIIIlI, llllllllllllllllllIIlIIlIllIIlII);
        return llllllllllllllllllIIlIIlIllIIlII;
    }

    public Spam() {
        super(Categories.Misc, "spam", "Spams specified messages in chat.");
        Spam llllllllllllllllllIIlIIlIlllIlIl;
        llllllllllllllllllIIlIIlIlllIlIl.sgGeneral = llllllllllllllllllIIlIIlIlllIlIl.settings.getDefaultGroup();
        llllllllllllllllllIIlIIlIlllIlIl.delay = llllllllllllllllllIIlIIlIlllIlIl.sgGeneral.add(new IntSetting.Builder().name("delay").description("The delay between specified messages in seconds.").defaultValue(2).min(0).sliderMax(20).build());
        llllllllllllllllllIIlIIlIlllIlIl.random = llllllllllllllllllIIlIIlIlllIlIl.sgGeneral.add(new BoolSetting.Builder().name("random").description("Selects a random message from your spam message list.").defaultValue(false).build());
        llllllllllllllllllIIlIIlIlllIlIl.messages = new ArrayList<String>();
    }

    private void fillTable(GuiTheme llllllllllllllllllIIlIIlIlIIlllI, WTable llllllllllllllllllIIlIIlIlIIllIl) {
        Spam llllllllllllllllllIIlIIlIlIIllll;
        llllllllllllllllllIIlIIlIlIIllIl.add(llllllllllllllllllIIlIIlIlIIlllI.horizontalSeparator("Messages")).expandX();
        llllllllllllllllllIIlIIlIlIIllIl.row();
        for (int llllllllllllllllllIIlIIlIlIlIlII = 0; llllllllllllllllllIIlIIlIlIlIlII < llllllllllllllllllIIlIIlIlIIllll.messages.size(); ++llllllllllllllllllIIlIIlIlIlIlII) {
            int llllllllllllllllllIIlIIlIlIllIII = llllllllllllllllllIIlIIlIlIlIlII;
            String llllllllllllllllllIIlIIlIlIlIlll = llllllllllllllllllIIlIIlIlIIllll.messages.get(llllllllllllllllllIIlIIlIlIlIlII);
            WTextBox llllllllllllllllllIIlIIlIlIlIllI = llllllllllllllllllIIlIIlIlIIllIl.add(llllllllllllllllllIIlIIlIlIIlllI.textBox(llllllllllllllllllIIlIIlIlIlIlll)).minWidth(100.0).expandX().widget();
            llllllllllllllllllIIlIIlIlIlIllI.action = () -> {
                Spam llllllllllllllllllIIlIIlIIIIlllI;
                llllllllllllllllllIIlIIlIIIIlllI.messages.set(llllllllllllllllllIIlIIlIlIllIII, llllllllllllllllllIIlIIlIlIlIllI.get());
            };
            WMinus llllllllllllllllllIIlIIlIlIlIlIl = llllllllllllllllllIIlIIlIlIIllIl.add(llllllllllllllllllIIlIIlIlIIlllI.minus()).widget();
            llllllllllllllllllIIlIIlIlIlIlIl.action = () -> {
                Spam llllllllllllllllllIIlIIlIIIlIlIl;
                llllllllllllllllllIIlIIlIIIlIlIl.messages.remove(llllllllllllllllllIIlIIlIlIllIII);
                llllllllllllllllllIIlIIlIlIIllIl.clear();
                llllllllllllllllllIIlIIlIIIlIlIl.fillTable(llllllllllllllllllIIlIIlIlIIlllI, llllllllllllllllllIIlIIlIlIIllIl);
            };
            llllllllllllllllllIIlIIlIlIIllIl.row();
        }
        WPlus llllllllllllllllllIIlIIlIlIlIIII = llllllllllllllllllIIlIIlIlIIllIl.add(llllllllllllllllllIIlIIlIlIIlllI.plus()).expandCellX().right().widget();
        llllllllllllllllllIIlIIlIlIlIIII.action = () -> {
            Spam llllllllllllllllllIIlIIlIIlIIIII;
            llllllllllllllllllIIlIIlIIlIIIII.messages.add("");
            llllllllllllllllllIIlIIlIlIIllIl.clear();
            llllllllllllllllllIIlIIlIIlIIIII.fillTable(llllllllllllllllllIIlIIlIlIIlllI, llllllllllllllllllIIlIIlIlIIllIl);
        };
    }

    @Override
    public Module fromTag(class_2487 llllllllllllllllllIIlIIlIIllIIIl) {
        Spam llllllllllllllllllIIlIIlIIllIIlI;
        llllllllllllllllllIIlIIlIIllIIlI.messages.clear();
        if (llllllllllllllllllIIlIIlIIllIIIl.method_10545("messages")) {
            class_2499 llllllllllllllllllIIlIIlIIllIIll = llllllllllllllllllIIlIIlIIllIIIl.method_10554("messages", 8);
            for (class_2520 llllllllllllllllllIIlIIlIIllIlII : llllllllllllllllllIIlIIlIIllIIll) {
                llllllllllllllllllIIlIIlIIllIIlI.messages.add(llllllllllllllllllIIlIIlIIllIlII.method_10714());
            }
        } else {
            llllllllllllllllllIIlIIlIIllIIlI.messages.add("Meteor on Crack!");
        }
        return super.fromTag(llllllllllllllllllIIlIIlIIllIIIl);
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIIlIIlIllIllII) {
        Spam llllllllllllllllllIIlIIlIllIllIl;
        if (llllllllllllllllllIIlIIlIllIllIl.messages.isEmpty()) {
            return;
        }
        if (llllllllllllllllllIIlIIlIllIllIl.timer <= 0) {
            int llllllllllllllllllIIlIIlIllIlllI;
            if (llllllllllllllllllIIlIIlIllIllIl.random.get().booleanValue()) {
                int llllllllllllllllllIIlIIlIllIllll = Utils.random(0, llllllllllllllllllIIlIIlIllIllIl.messages.size());
            } else {
                if (llllllllllllllllllIIlIIlIllIllIl.messageI >= llllllllllllllllllIIlIIlIllIllIl.messages.size()) {
                    llllllllllllllllllIIlIIlIllIllIl.messageI = 0;
                }
                llllllllllllllllllIIlIIlIllIlllI = llllllllllllllllllIIlIIlIllIllIl.messageI++;
            }
            llllllllllllllllllIIlIIlIllIllIl.mc.field_1724.method_3142(llllllllllllllllllIIlIIlIllIllIl.messages.get(llllllllllllllllllIIlIIlIllIlllI));
            llllllllllllllllllIIlIIlIllIllIl.timer = llllllllllllllllllIIlIIlIllIllIl.delay.get() * 20;
        } else {
            --llllllllllllllllllIIlIIlIllIllIl.timer;
        }
    }
}

