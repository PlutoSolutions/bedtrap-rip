/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1657;

public class MessageAura
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<String> message;
    private final /* synthetic */ Setting<Boolean> ignoreFriends;

    public MessageAura() {
        super(Categories.Misc, "message-aura", "Sends a specified message to any player that enters render distance.");
        MessageAura lIIlIIlIllIIIII;
        lIIlIIlIllIIIII.sgGeneral = lIIlIIlIllIIIII.settings.getDefaultGroup();
        lIIlIIlIllIIIII.message = lIIlIIlIllIIIII.sgGeneral.add(new StringSetting.Builder().name("message").description("The specified message sent to the player.").defaultValue("Meteor on Crack!").build());
        lIIlIIlIllIIIII.ignoreFriends = lIIlIIlIllIIIII.sgGeneral.add(new BoolSetting.Builder().name("ignore-friends").description("Will not send any messages to people friended.").defaultValue(false).build());
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent lIIlIIlIlIlllII) {
        MessageAura lIIlIIlIlIllIll;
        if (!(lIIlIIlIlIlllII.entity instanceof class_1657) || lIIlIIlIlIlllII.entity.method_5667().equals(lIIlIIlIlIllIll.mc.field_1724.method_5667())) {
            return;
        }
        if (!lIIlIIlIlIllIll.ignoreFriends.get().booleanValue() || lIIlIIlIlIllIll.ignoreFriends.get().booleanValue() && !Friends.get().isFriend((class_1657)lIIlIIlIlIlllII.entity)) {
            lIIlIIlIlIllIll.mc.field_1724.method_3142(String.valueOf(new StringBuilder().append("/msg ").append(lIIlIIlIlIlllII.entity.method_5820()).append(" ").append(lIIlIIlIlIllIll.message.get())));
        }
    }
}

