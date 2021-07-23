/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1429;
import net.minecraft.class_1657;

public class AutoBreed
extends Module {
    private final Setting<class_1268> hand;
    private final SettingGroup sgGeneral;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Double> range;
    private final List<class_1297> animalsFed;
    private final Setting<Boolean> ignoreBabies;

    @Override
    public void onActivate() {
        this.animalsFed.clear();
    }

    public AutoBreed() {
        super(Categories.World, "auto-breed", "Automatically breeds specified animals.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to breed.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(class_1299.field_6139, class_1299.field_6067, class_1299.field_6085, class_1299.field_6143, class_1299.field_6115, class_1299.field_6093, class_1299.field_6132, class_1299.field_6055, class_1299.field_16281, class_1299.field_6081, class_1299.field_6140, class_1299.field_6074, class_1299.field_6113, class_1299.field_6146, class_1299.field_17943, class_1299.field_20346, class_1299.field_23214, class_1299.field_21973)).onlyAttackable().build());
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("How far away the animals can be to be bred.").min(0.0).defaultValue(4.5).build());
        this.hand = this.sgGeneral.add(new EnumSetting.Builder().name("hand-for-breeding").description("The hand to use for breeding.").defaultValue(class_1268.field_5808).build());
        this.ignoreBabies = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-babies").description("Whether or not to ignore the baby variants of the specified entity.").defaultValue(true).build());
        this.animalsFed = new ArrayList<class_1297>();
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1429)) continue;
            class_1429 class_14292 = (class_1429)class_12972;
            if (!this.entities.get().getBoolean((Object)class_14292.method_5864()) || class_14292.method_6109() && !this.ignoreBabies.get().booleanValue() || this.animalsFed.contains(class_14292) || (double)this.mc.field_1724.method_5739((class_1297)class_14292) > this.range.get() || !class_14292.method_6481(this.hand.get() == class_1268.field_5808 ? this.mc.field_1724.method_6047() : this.mc.field_1724.method_6079())) continue;
            Rotations.rotate(Rotations.getYaw(class_12972), Rotations.getPitch(class_12972), -100, () -> this.lambda$onTick$0(class_14292));
            return;
        }
    }

    private void lambda$onTick$0(class_1429 class_14292) {
        this.mc.field_1761.method_2905((class_1657)this.mc.field_1724, (class_1297)class_14292, this.hand.get());
        this.mc.field_1724.method_6104(this.hand.get());
        this.animalsFed.add((class_1297)class_14292);
    }
}

