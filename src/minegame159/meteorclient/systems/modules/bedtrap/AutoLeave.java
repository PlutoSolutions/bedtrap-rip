/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2661
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.InvUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2661;

public class AutoLeave
extends Module {
    private final /* synthetic */ SettingGroup sgChat;
    private final /* synthetic */ Setting<Integer> totems;
    private final /* synthetic */ Setting<Boolean> disable;
    public /* synthetic */ int leavedelay;
    public /* synthetic */ int delay;
    /* synthetic */ String msg;
    private final /* synthetic */ Setting<String> leaveMessage;
    private final /* synthetic */ Setting<Mode> mode;
    /* synthetic */ String command;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<String> leaveCommand;

    @EventHandler
    private void onGameLeft(GameLeftEvent lllllIIIllIIII) {
        AutoLeave lllllIIIllIIIl;
        if (!lllllIIIllIIIl.disable.get().booleanValue()) {
            return;
        }
        lllllIIIllIIIl.toggle();
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllIIIllIllI) {
        AutoLeave lllllIIIllIlII;
        InvUtils.FindItemResult lllllIIIllIlIl = InvUtils.findItemWithCount(class_1802.field_8288);
        lllllIIIllIlII.msg = lllllIIIllIlII.leaveMessage.get();
        lllllIIIllIlII.command = lllllIIIllIlII.leaveCommand.get();
        assert (lllllIIIllIlII.mc.field_1724 != null);
        if (lllllIIIllIlIl.count <= lllllIIIllIlII.totems.get()) {
            switch (lllllIIIllIlII.mode.get()) {
                case MessageAndLeave: {
                    lllllIIIllIlII.mc.field_1724.method_3142(lllllIIIllIlII.msg);
                    if (lllllIIIllIlII.delay < lllllIIIllIlII.leavedelay) {
                        ++lllllIIIllIlII.delay;
                        return;
                    }
                    lllllIIIllIlII.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585(String.valueOf(new StringBuilder().append("[AutoLeave] Your totems <=  ").append(lllllIIIllIlII.totems.get()).append(".")))));
                    break;
                }
                case ClientAndLeave: {
                    ChatUtils.info(lllllIIIllIlII.msg, new Object[0]);
                    if (lllllIIIllIlII.delay < lllllIIIllIlII.leavedelay) {
                        ++lllllIIIllIlII.delay;
                        return;
                    }
                    lllllIIIllIlII.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585(String.valueOf(new StringBuilder().append("[AutoLeave] Your totems <=  ").append(lllllIIIllIlII.totems.get()).append(".")))));
                    break;
                }
                case CommandAndLeave: {
                    lllllIIIllIlII.mc.field_1724.method_3142(lllllIIIllIlII.command);
                    lllllIIIllIlII.toggle();
                    break;
                }
                case NoneAndLeave: {
                    lllllIIIllIlII.mc.field_1724.field_3944.method_11083(new class_2661((class_2561)new class_2585(String.valueOf(new StringBuilder().append("[AutoLeave] Your totems <=  ").append(lllllIIIllIlII.totems.get()).append(".")))));
                }
            }
        }
        lllllIIIllIlII.delay = 0;
    }

    public AutoLeave() {
        super(Categories.BedTrap, "auto-leave", "Automatically disconnects you when certain requirements are met.");
        AutoLeave lllllIIIlllIlI;
        lllllIIIlllIlI.sgGeneral = lllllIIIlllIlI.settings.getDefaultGroup();
        lllllIIIlllIlI.sgChat = lllllIIIlllIlI.settings.createGroup("Chat");
        lllllIIIlllIlI.mode = lllllIIIlllIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Sends to the chat message.").defaultValue(Mode.NoneAndLeave).build());
        lllllIIIlllIlI.totems = lllllIIIlllIlI.sgGeneral.add(new IntSetting.Builder().name("totems").description("How many totems need to disconnect.").defaultValue(2).min(1).sliderMax(36).build());
        lllllIIIlllIlI.disable = lllllIIIlllIlI.sgGeneral.add(new BoolSetting.Builder().name("auto-disable").description("prevent from pushing out of block.").defaultValue(true).build());
        lllllIIIlllIlI.leaveMessage = lllllIIIlllIlI.sgChat.add(new StringSetting.Builder().name("message").description("Chat alert to send when you leave from the server.").defaultValue("LoLFunnyLoL mode activated.").build());
        lllllIIIlllIlI.leaveCommand = lllllIIIlllIlI.sgChat.add(new StringSetting.Builder().name("command").description("Very useful for SLABIY players.").defaultValue("/home").build());
        lllllIIIlllIlI.delay = 0;
        lllllIIIlllIlI.leavedelay = 1;
    }

    public static enum Mode {
        MessageAndLeave,
        ClientAndLeave,
        CommandAndLeave,
        NoneAndLeave;


        private Mode() {
            Mode llllllllllllllllllIlllIllIIIlIIl;
        }
    }
}

