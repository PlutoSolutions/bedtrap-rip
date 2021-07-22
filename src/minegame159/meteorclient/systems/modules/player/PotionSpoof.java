/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 */
package minegame159.meteorclient.systems.modules.player;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.StatusEffectInstanceAccessor;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StatusEffectSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1291;
import net.minecraft.class_1293;

public class PotionSpoof
extends Module {
    private final /* synthetic */ Setting<Object2IntMap<class_1291>> potions;
    private final /* synthetic */ SettingGroup sgGeneral;

    public PotionSpoof() {
        super(Categories.Player, "potion-spoof", "Spoofs specified potion effects for you. SOME effects DO NOT work.");
        PotionSpoof lllllllllllllllllIIIllIlIlIIIIlI;
        lllllllllllllllllIIIllIlIlIIIIlI.sgGeneral = lllllllllllllllllIIIllIlIlIIIIlI.settings.getDefaultGroup();
        lllllllllllllllllIIIllIlIlIIIIlI.potions = lllllllllllllllllIIIllIlIlIIIIlI.sgGeneral.add(new StatusEffectSetting.Builder().name("potions").description("Potions to add.").defaultValue(Utils.createStatusEffectMap()).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIIIllIlIIlllIII) {
        PotionSpoof lllllllllllllllllIIIllIlIIllIlll;
        for (class_1291 lllllllllllllllllIIIllIlIIlllIlI : lllllllllllllllllIIIllIlIIllIlll.potions.get().keySet()) {
            int lllllllllllllllllIIIllIlIIlllIll = lllllllllllllllllIIIllIlIIllIlll.potions.get().getInt((Object)lllllllllllllllllIIIllIlIIlllIlI);
            if (lllllllllllllllllIIIllIlIIlllIll <= 0) continue;
            if (lllllllllllllllllIIIllIlIIllIlll.mc.field_1724.method_6059(lllllllllllllllllIIIllIlIIlllIlI)) {
                class_1293 lllllllllllllllllIIIllIlIIllllII = lllllllllllllllllIIIllIlIIllIlll.mc.field_1724.method_6112(lllllllllllllllllIIIllIlIIlllIlI);
                ((StatusEffectInstanceAccessor)lllllllllllllllllIIIllIlIIllllII).setAmplifier(lllllllllllllllllIIIllIlIIlllIll - 1);
                if (lllllllllllllllllIIIllIlIIllllII.method_5584() >= 20) continue;
                ((StatusEffectInstanceAccessor)lllllllllllllllllIIIllIlIIllllII).setDuration(20);
                continue;
            }
            lllllllllllllllllIIIllIlIIllIlll.mc.field_1724.method_6092(new class_1293(lllllllllllllllllIIIllIlIIlllIlI, 20, lllllllllllllllllIIIllIlIIlllIll - 1));
        }
    }
}

