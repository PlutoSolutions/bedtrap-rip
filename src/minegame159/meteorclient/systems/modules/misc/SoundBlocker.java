/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3414
 */
package minegame159.meteorclient.systems.modules.misc;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.PlaySoundEvent;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.SoundEventListSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_3414;

public class SoundBlocker
extends Module {
    private final Setting<List<class_3414>> sounds;
    private final SettingGroup sgGeneral;

    @EventHandler
    private void onPlaySound(PlaySoundEvent playSoundEvent) {
        for (class_3414 class_34142 : this.sounds.get()) {
            if (!class_34142.method_14833().equals((Object)playSoundEvent.sound.method_4775())) continue;
            playSoundEvent.cancel();
            break;
        }
    }

    public SoundBlocker() {
        super(Categories.Misc, "sound-blocker", "Cancels out selected sounds.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sounds = this.sgGeneral.add(new SoundEventListSetting.Builder().name("sounds").description("Sounds to block.").defaultValue(new ArrayList<class_3414>(0)).build());
    }
}

