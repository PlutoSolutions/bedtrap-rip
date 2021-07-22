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
    private final /* synthetic */ Setting<Boolean> bedBool;
    private final /* synthetic */ Setting<Boolean> anchorBool;
    private final /* synthetic */ SettingGroup sgGeneral;

    @EventHandler
    private void onSendPacket(PacketEvent.Send llllllllllllllllIllIIlIlIlIIlIlI) {
        AntiRespawnLose llllllllllllllllIllIIlIlIlIlIIlI;
        if (llllllllllllllllIllIIlIlIlIlIIlI.mc.field_1687 == null) {
            return;
        }
        if (!(llllllllllllllllIllIIlIlIlIIlIlI.packet instanceof class_2885)) {
            return;
        }
        class_2338 llllllllllllllllIllIIlIlIlIlIIII = ((class_2885)llllllllllllllllIllIIlIlIlIIlIlI.packet).method_12543().method_17777();
        boolean llllllllllllllllIllIIlIlIlIIllll = llllllllllllllllIllIIlIlIlIlIIlI.mc.field_1687.method_8597().method_29956();
        boolean llllllllllllllllIllIIlIlIlIIlllI = llllllllllllllllIllIIlIlIlIlIIlI.mc.field_1687.method_8597().method_29957();
        boolean llllllllllllllllIllIIlIlIlIIllIl = llllllllllllllllIllIIlIlIlIlIIlI.mc.field_1687.method_8320(llllllllllllllllIllIIlIlIlIlIIII).method_26204() instanceof class_2244;
        boolean llllllllllllllllIllIIlIlIlIIllII = llllllllllllllllIllIIlIlIlIlIIlI.mc.field_1687.method_8320(llllllllllllllllIllIIlIlIlIlIIII).method_26204().equals((Object)class_2246.field_23152);
        if (llllllllllllllllIllIIlIlIlIlIIlI.bedBool.get().booleanValue() && llllllllllllllllIllIIlIlIlIIllIl && llllllllllllllllIllIIlIlIlIIllll) {
            llllllllllllllllIllIIlIlIlIIlIlI.cancel();
        }
        if (llllllllllllllllIllIIlIlIlIlIIlI.anchorBool.get().booleanValue() && llllllllllllllllIllIIlIlIlIIllII && llllllllllllllllIllIIlIlIlIIlllI) {
            llllllllllllllllIllIIlIlIlIIlIlI.cancel();
        }
    }

    public AntiRespawnLose() {
        super(Categories.BedTrap, "anti-respawn-lose", "Prevent the player from losing the respawn point.");
        AntiRespawnLose llllllllllllllllIllIIlIlIlIllIll;
        llllllllllllllllIllIIlIlIlIllIll.sgGeneral = llllllllllllllllIllIIlIlIlIllIll.settings.getDefaultGroup();
        llllllllllllllllIllIIlIlIlIllIll.bedBool = llllllllllllllllIllIIlIlIlIllIll.sgGeneral.add(new BoolSetting.Builder().name("Bed").description("Prevent from loosing Bed respawn point.").defaultValue(true).build());
        llllllllllllllllIllIIlIlIlIllIll.anchorBool = llllllllllllllllIllIIlIlIlIllIll.sgGeneral.add(new BoolSetting.Builder().name("Anchor").description("Prevent from loosing Anchor respawn point.").defaultValue(true).build());
    }
}

