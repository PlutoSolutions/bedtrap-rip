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
    long killTime = 0L;
    String msg;
    public class_1657 target;
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
    int kills = 0;
    private final Setting<Mode> mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Sends to the chat message on behalf of the player.").defaultValue(Mode.Message).build());

    @EventHandler
    public void gameJoin(PacketEvent packetEvent) {
        if (!(packetEvent.packet instanceof class_2678)) {
            return;
        }
        this.kills = 0;
    }

    public AutoEz() {
        super(Categories.BedTrap, "auto-ez", "Says 'EzZz' on every kill");
    }

    @EventHandler
    public void onKill(PacketEvent.Receive receive) {
        if (!(receive.packet instanceof class_2635)) {
            return;
        }
        String string = ((class_2635)receive.packet).method_11388().getString().toLowerCase();
        String[] arrstring = new String[]{"by", "slain", "fucked", "killed", "\u0447\u0438\u0445\u043d\u0443\u043b", "\u043e\u0431\u043d\u0438\u043c\u0430\u0448\u043a\u0438", "\u0443\u0431\u0438\u0442", "separated", "punched", "shoved", "crystal", "nuked", "\u043f\u043e\u0434\u043e\u0440\u0432\u0430\u043b"};
        SortPriority sortPriority = SortPriority.LowestDistance;
        this.target = TargetUtils.getPlayerTarget(5.0, sortPriority);
        for (String string2 : arrstring) {
            if (string.contains(String.valueOf(new StringBuilder().append(this.mc.field_1724.method_5477().method_10851().toLowerCase()).append(" ").append(string2)))) {
                return;
            }
            if (!((class_2635)receive.packet).method_29175().toString().contains("000000000") || !string.contains(this.mc.field_1724.method_5477().method_10851().toLowerCase()) || !string.contains(string2)) continue;
            this.killTime = System.currentTimeMillis();
            break;
        }
    }

    @Override
    public void onDeactivate() {
        this.kills = 0;
        this.killTime = 0L;
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.kills);
    }

    @EventHandler
    public void onTick(TickEvent.Post post) {
        Mode mode = this.mode.get();
        if (this.killTime != 0L && System.currentTimeMillis() - this.killTime > 200L && !this.mc.field_1724.method_29504()) {
            ++this.kills;
            this.msg = String.valueOf(new StringBuilder().append("U got nae`d by BedTrap 0.3.1! KillStreak: ").append(String.valueOf(this.kills)));
            if (mode == Mode.Message) {
                this.mc.field_1724.method_3142(this.msg);
            } else if (mode == Mode.Client) {
                ChatUtils.info(this.msg, new Object[0]);
            } else if (mode == Mode.None) {
                // empty if block
            }
            this.killTime = 0L;
        }
        if (this.mc.field_1724.method_29504()) {
            this.kills = 0;
            this.killTime = 0L;
        }
    }

    public static enum Mode {
        Message,
        Client,
        None;

    }
}

