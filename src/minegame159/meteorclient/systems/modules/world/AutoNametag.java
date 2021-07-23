/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1807;

public class AutoNametag
extends Module {
    private final SettingGroup sgGeneral;
    private int preSlot;
    private final Setting<Double> distance;
    private class_1297 entity;
    private boolean offHand;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Boolean> rotate;

    public AutoNametag() {
        super(Categories.World, "auto-nametag", "Automatically uses nametags on entities without a nametag. WILL nametag ALL entities in the specified distance.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Which entities to nametag.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).build());
        this.distance = this.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance a nametagged entity can be to be nametagged.").min(0.0).defaultValue(5.0).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the mob being nametagged.").defaultValue(true).build());
    }

    private void interact() {
        this.mc.field_1761.method_2905((class_1657)this.mc.field_1724, this.entity, this.offHand ? class_1268.field_5810 : class_1268.field_5808);
        InvUtils.swap(this.preSlot);
    }

    @Override
    public void onDeactivate() {
        this.entity = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.entity = null;
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            boolean bl;
            if (!this.entities.get().getBoolean((Object)class_12972.method_5864()) || class_12972.method_16914() || (double)this.mc.field_1724.method_5739(class_12972) > this.distance.get()) continue;
            boolean bl2 = true;
            if (this.mc.field_1724.field_7514.method_7391().method_7909() instanceof class_1807) {
                bl2 = false;
            } else if (((class_1799)this.mc.field_1724.field_7514.field_7544.get(0)).method_7909() instanceof class_1807) {
                bl2 = false;
                this.offHand = true;
            }
            boolean bl3 = bl = !bl2;
            if (bl2) {
                for (int i = 0; i < 9; ++i) {
                    class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
                    if (!(class_17992.method_7909() instanceof class_1807)) continue;
                    this.preSlot = this.mc.field_1724.field_7514.field_7545;
                    InvUtils.swap(i);
                    bl = true;
                    break;
                }
            }
            if (!bl) continue;
            this.entity = class_12972;
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(class_12972), Rotations.getPitch(class_12972), -100, this::interact);
            } else {
                this.interact();
            }
            return;
        }
    }
}

