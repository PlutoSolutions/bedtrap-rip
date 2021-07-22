/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2244
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2885
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2885;

public class AntiRespawnLose
extends Module {
    private final Setting<Boolean> bedBool;
    private final Setting<Boolean> anchorBool;
    private final SettingGroup sgGeneral;

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (this.mc.field_1687 == null) {
            return;
        }
        if (!(send.packet instanceof class_2885)) {
            return;
        }
        class_2338 class_23382 = ((class_2885)send.packet).method_12543().method_17777();
        boolean bl = this.mc.field_1687.method_8597().method_29956();
        boolean bl2 = this.mc.field_1687.method_8597().method_29957();
        boolean bl3 = this.mc.field_1687.method_8320(class_23382).method_26204() instanceof class_2244;
        boolean bl4 = this.mc.field_1687.method_8320(class_23382).method_26204().equals((Object)class_2246.field_23152);
        if (this.bedBool.get().booleanValue() && bl3 && bl) {
            send.cancel();
        }
        if (this.anchorBool.get().booleanValue() && bl4 && bl2) {
            send.cancel();
        }
    }

    public AntiRespawnLose() {
        super(Categories.BedTrap, "anti-respawn-lose", "Prevent the player from losing the respawn point.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.bedBool = this.sgGeneral.add(new BoolSetting.Builder().name("Bed").description("Prevent from loosing Bed respawn point.").defaultValue(true).build());
        this.anchorBool = this.sgGeneral.add(new BoolSetting.Builder().name("Anchor").description("Prevent from loosing Anchor respawn point.").defaultValue(true).build());
    }
}

