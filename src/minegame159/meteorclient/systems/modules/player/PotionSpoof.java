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
    private final Setting<Object2IntMap<class_1291>> potions;
    private final SettingGroup sgGeneral;

    public PotionSpoof() {
        super(Categories.Player, "potion-spoof", "Spoofs specified potion effects for you. SOME effects DO NOT work.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.potions = this.sgGeneral.add(new StatusEffectSetting.Builder().name("potions").description("Potions to add.").defaultValue(Utils.createStatusEffectMap()).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        for (class_1291 class_12912 : this.potions.get().keySet()) {
            int n = this.potions.get().getInt((Object)class_12912);
            if (n <= 0) continue;
            if (this.mc.field_1724.method_6059(class_12912)) {
                class_1293 class_12932 = this.mc.field_1724.method_6112(class_12912);
                ((StatusEffectInstanceAccessor)class_12932).setAmplifier(n - 1);
                if (class_12932.method_5584() >= 20) continue;
                ((StatusEffectInstanceAccessor)class_12932).setDuration(20);
                continue;
            }
            this.mc.field_1724.method_6092(new class_1293(class_12912, 20, n - 1));
        }
    }
}

