/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2635
 *  net.minecraft.class_2678
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_1657;
import net.minecraft.class_2635;
import net.minecraft.class_2678;

public class AutoEz
extends Module {
    /* synthetic */ long killTime;
    /* synthetic */ String msg;
    public /* synthetic */ class_1657 target;
    private final /* synthetic */ SettingGroup sgGeneral;
    /* synthetic */ int kills;
    private final /* synthetic */ Setting<Mode> mode;

    @EventHandler
    public void gameJoin(PacketEvent llllllllllllllllllIlIllllllllIll) {
        if (!(llllllllllllllllllIlIllllllllIll.packet instanceof class_2678)) {
            return;
        }
        llllllllllllllllllIlIllllllllIlI.kills = 0;
    }

    public AutoEz() {
        super(Categories.BedTrap, "auto-ez", "Says 'EzZz' on every kill");
        AutoEz llllllllllllllllllIllIIIIIlIIIlI;
        llllllllllllllllllIllIIIIIlIIIlI.kills = 0;
        llllllllllllllllllIllIIIIIlIIIlI.killTime = 0L;
        llllllllllllllllllIllIIIIIlIIIlI.sgGeneral = llllllllllllllllllIllIIIIIlIIIlI.settings.getDefaultGroup();
        llllllllllllllllllIllIIIIIlIIIlI.mode = llllllllllllllllllIllIIIIIlIIIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Sends to the chat message on behalf of the player.").defaultValue(Mode.Message).build());
    }

    @EventHandler
    public void onKill(PacketEvent.Receive llllllllllllllllllIllIIIIIIIlIll) {
        if (!(llllllllllllllllllIllIIIIIIIlIll.packet instanceof class_2635)) {
            return;
        }
        String llllllllllllllllllIllIIIIIIIlIlI = ((class_2635)llllllllllllllllllIllIIIIIIIlIll.packet).method_11388().getString().toLowerCase();
        String[] llllllllllllllllllIllIIIIIIIlIIl = new String[]{"by", "slain", "fucked", "killed", "\u0447\u0438\u0445\u043d\u0443\u043b", "\u043e\u0431\u043d\u0438\u043c\u0430\u0448\u043a\u0438", "\u0443\u0431\u0438\u0442", "separated", "punched", "shoved", "crystal", "nuked", "\u043f\u043e\u0434\u043e\u0440\u0432\u0430\u043b"};
        SortPriority llllllllllllllllllIllIIIIIIIlIII = SortPriority.LowestDistance;
        llllllllllllllllllIllIIIIIIIllII.target = TargetUtils.getPlayerTarget(5.0, llllllllllllllllllIllIIIIIIIlIII);
        for (String llllllllllllllllllIllIIIIIIIllIl : llllllllllllllllllIllIIIIIIIlIIl) {
            AutoEz llllllllllllllllllIllIIIIIIIllII;
            if (llllllllllllllllllIllIIIIIIIlIlI.contains(String.valueOf(new StringBuilder().append(llllllllllllllllllIllIIIIIIIllII.mc.field_1724.method_5477().method_10851().toLowerCase()).append(" ").append(llllllllllllllllllIllIIIIIIIllIl)))) {
                return;
            }
            if (!((class_2635)llllllllllllllllllIllIIIIIIIlIll.packet).method_29175().toString().contains("000000000") || !llllllllllllllllllIllIIIIIIIlIlI.contains(llllllllllllllllllIllIIIIIIIllII.mc.field_1724.method_5477().method_10851().toLowerCase()) || !llllllllllllllllllIllIIIIIIIlIlI.contains(llllllllllllllllllIllIIIIIIIllIl)) continue;
            llllllllllllllllllIllIIIIIIIllII.killTime = System.currentTimeMillis();
            break;
        }
    }

    @Override
    public void onDeactivate() {
        llllllllllllllllllIllIIIIIIllllI.kills = 0;
        llllllllllllllllllIllIIIIIIllllI.killTime = 0L;
    }

    @Override
    public String getInfoString() {
        AutoEz llllllllllllllllllIlIlllllllIlll;
        return Integer.toString(llllllllllllllllllIlIlllllllIlll.kills);
    }

    @EventHandler
    public void onTick(TickEvent.Post llllllllllllllllllIllIIIIIIllIlI) {
        AutoEz llllllllllllllllllIllIIIIIIllIII;
        Mode llllllllllllllllllIllIIIIIIllIIl = llllllllllllllllllIllIIIIIIllIII.mode.get();
        if (llllllllllllllllllIllIIIIIIllIII.killTime != 0L && System.currentTimeMillis() - llllllllllllllllllIllIIIIIIllIII.killTime > 200L && !llllllllllllllllllIllIIIIIIllIII.mc.field_1724.method_29504()) {
            ++llllllllllllllllllIllIIIIIIllIII.kills;
            llllllllllllllllllIllIIIIIIllIII.msg = String.valueOf(new StringBuilder().append("U got nae`d by BedTrap 0.3.1! KillStreak: ").append(String.valueOf(llllllllllllllllllIllIIIIIIllIII.kills)));
            if (llllllllllllllllllIllIIIIIIllIIl == Mode.Message) {
                llllllllllllllllllIllIIIIIIllIII.mc.field_1724.method_3142(llllllllllllllllllIllIIIIIIllIII.msg);
            } else if (llllllllllllllllllIllIIIIIIllIIl == Mode.Client) {
                ChatUtils.info(llllllllllllllllllIllIIIIIIllIII.msg, new Object[0]);
            } else if (llllllllllllllllllIllIIIIIIllIIl == Mode.None) {
                // empty if block
            }
            llllllllllllllllllIllIIIIIIllIII.killTime = 0L;
        }
        if (llllllllllllllllllIllIIIIIIllIII.mc.field_1724.method_29504()) {
            llllllllllllllllllIllIIIIIIllIII.kills = 0;
            llllllllllllllllllIllIIIIIIllIII.killTime = 0L;
        }
    }

    public static enum Mode {
        Message,
        Client,
        None;


        private Mode() {
            Mode lllllllllllllllllllIlllIlIlIIlII;
        }
    }
}

