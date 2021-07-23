/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.AttackEntityEvent;
import minegame159.meteorclient.events.entity.player.StartBreakingBlockEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1299;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_2248;

public class AntiHit
extends Module {
    private final Setting<List<class_2248>> blocks;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> babies;
    private final Setting<Boolean> friends;
    private final Setting<Boolean> nametagged;

    public AntiHit() {
        super(Categories.Combat, "anti-hit", "Prevents you from attacking certain entities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to avoid attacking.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        this.friends = this.sgGeneral.add(new BoolSetting.Builder().name("friends").description("Doesn't allow friends to be attacked.").defaultValue(true).build());
        this.babies = this.sgGeneral.add(new BoolSetting.Builder().name("babies").description("Doesn't allow babies to be attacked.").defaultValue(true).build());
        this.nametagged = this.sgGeneral.add(new BoolSetting.Builder().name("nametagged").description("Doesn't allow nametagged enities to be attacked.").defaultValue(false).build());
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks to avoid hitting.").defaultValue(new ArrayList<class_2248>()).build());
    }

    @EventHandler(priority=100)
    private void onStartBreakingBlockEvent(StartBreakingBlockEvent startBreakingBlockEvent) {
        if (this.blocks.get().contains(this.mc.field_1687.method_8320(startBreakingBlockEvent.blockPos).method_26204())) {
            startBreakingBlockEvent.cancel();
        }
    }

    @EventHandler(priority=100)
    private void onAttackEntity(AttackEntityEvent attackEntityEvent) {
        if (this.friends.get().booleanValue() && attackEntityEvent.entity instanceof class_1657 && !Friends.get().shouldAttack((class_1657)attackEntityEvent.entity)) {
            attackEntityEvent.cancel();
        }
        if (this.babies.get().booleanValue() && attackEntityEvent.entity instanceof class_1429 && ((class_1429)attackEntityEvent.entity).method_6109()) {
            attackEntityEvent.cancel();
        }
        if (this.nametagged.get().booleanValue() && attackEntityEvent.entity.method_16914()) {
            attackEntityEvent.cancel();
        }
        if (this.entities.get().getBoolean((Object)attackEntityEvent.entity.method_5864())) {
            attackEntityEvent.cancel();
        }
    }
}

