/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Boolean> message;
    private final SettingGroup sgGeneral;

    public MiddleClickFriend() {
        super(Categories.Misc, "middle-click-friend", "Adds or removes a player as a friend via middle click.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.message = this.sgGeneral.add(new BoolSetting.Builder().name("message").description("Sends a message to the player when you add them as a friend.").defaultValue(false).build());
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent mouseButtonEvent) {
        if (mouseButtonEvent.action == KeyAction.Press && mouseButtonEvent.button == 2 && this.mc.field_1755 == null && this.mc.field_1692 != null && this.mc.field_1692 instanceof class_1657) {
            if (!Friends.get().isFriend((class_1657)this.mc.field_1692)) {
                Friends.get().add(new Friend((class_1657)this.mc.field_1692));
                if (this.message.get().booleanValue()) {
                    this.mc.field_1724.method_3142(String.valueOf(new StringBuilder().append("/msg ").append(this.mc.field_1692.method_5820()).append(" I just friended you on Meteor.")));
                }
            } else {
                Friends.get().remove(Friends.get().get((class_1657)this.mc.field_1692));
            }
        }
    }
}

