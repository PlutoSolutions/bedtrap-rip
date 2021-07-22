/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 */
package minegame159.meteorclient.systems.modules.combat;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1299;

public class Hitboxes
extends Module {
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Double> value;
    private final SettingGroup sgGeneral;

    public Hitboxes() {
        super(Categories.Combat, "hitboxes", "Expands an entity's hitboxes.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Which entities to target.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        this.value = this.sgGeneral.add(new DoubleSetting.Builder().name("expand").description("How much to expand the hitbox of the entity.").defaultValue(0.5).build());
    }

    public double getEntityValue(class_1297 class_12972) {
        if (!this.isActive()) {
            return 0.0;
        }
        if (this.entities.get().getBoolean((Object)class_12972.method_5864())) {
            return this.value.get();
        }
        return 0.0;
    }
}

