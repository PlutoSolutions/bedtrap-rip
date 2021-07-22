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
    private final /* synthetic */ Setting<List<class_3414>> sounds;
    private final /* synthetic */ SettingGroup sgGeneral;

    @EventHandler
    private void onPlaySound(PlaySoundEvent llllllllllllllllllllIllIlIllIlll) {
        SoundBlocker llllllllllllllllllllIllIlIlllIll;
        for (class_3414 llllllllllllllllllllIllIlIllllII : llllllllllllllllllllIllIlIlllIll.sounds.get()) {
            if (!llllllllllllllllllllIllIlIllllII.method_14833().equals((Object)llllllllllllllllllllIllIlIllIlll.sound.method_4775())) continue;
            llllllllllllllllllllIllIlIllIlll.cancel();
            break;
        }
    }

    public SoundBlocker() {
        super(Categories.Misc, "sound-blocker", "Cancels out selected sounds.");
        SoundBlocker llllllllllllllllllllIllIllIIllIl;
        llllllllllllllllllllIllIllIIllIl.sgGeneral = llllllllllllllllllllIllIllIIllIl.settings.getDefaultGroup();
        llllllllllllllllllllIllIllIIllIl.sounds = llllllllllllllllllllIllIllIIllIl.sgGeneral.add(new SoundEventListSetting.Builder().name("sounds").description("Sounds to block.").defaultValue(new ArrayList<class_3414>(0)).build());
    }
}

