/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friend;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import net.minecraft.class_1657;

public class MiddleClickFriend
extends Module {
    private final /* synthetic */ Setting<Boolean> message;
    private final /* synthetic */ SettingGroup sgGeneral;

    public MiddleClickFriend() {
        super(Categories.Misc, "middle-click-friend", "Adds or removes a player as a friend via middle click.");
        MiddleClickFriend lIIIIlIIllllII;
        lIIIIlIIllllII.sgGeneral = lIIIIlIIllllII.settings.getDefaultGroup();
        lIIIIlIIllllII.message = lIIIIlIIllllII.sgGeneral.add(new BoolSetting.Builder().name("message").description("Sends a message to the player when you add them as a friend.").defaultValue(false).build());
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent lIIIIlIIlllIII) {
        MiddleClickFriend lIIIIlIIllIlll;
        if (lIIIIlIIlllIII.action == KeyAction.Press && lIIIIlIIlllIII.button == 2 && lIIIIlIIllIlll.mc.field_1755 == null && lIIIIlIIllIlll.mc.field_1692 != null && lIIIIlIIllIlll.mc.field_1692 instanceof class_1657) {
            if (!Friends.get().isFriend((class_1657)lIIIIlIIllIlll.mc.field_1692)) {
                Friends.get().add(new Friend((class_1657)lIIIIlIIllIlll.mc.field_1692));
                if (lIIIIlIIllIlll.message.get().booleanValue()) {
                    lIIIIlIIllIlll.mc.field_1724.method_3142(String.valueOf(new StringBuilder().append("/msg ").append(lIIIIlIIllIlll.mc.field_1692.method_5820()).append(" I just friended you on Meteor.")));
                }
            } else {
                Friends.get().remove(Friends.get().get((class_1657)lIIIIlIIllIlll.mc.field_1692));
            }
        }
    }
}

