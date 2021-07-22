/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1299
 *  net.minecraft.class_1429
 *  net.minecraft.class_1657
 *  net.minecraft.class_2248
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
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> babies;
    private final /* synthetic */ Setting<Boolean> friends;
    private final /* synthetic */ Setting<Boolean> nametagged;

    public AntiHit() {
        super(Categories.Combat, "anti-hit", "Prevents you from attacking certain entities.");
        AntiHit llllllllllllllllIllIIlIllIlIIlII;
        llllllllllllllllIllIIlIllIlIIlII.sgGeneral = llllllllllllllllIllIIlIllIlIIlII.settings.getDefaultGroup();
        llllllllllllllllIllIIlIllIlIIlII.entities = llllllllllllllllIllIIlIllIlIIlII.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to avoid attacking.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        llllllllllllllllIllIIlIllIlIIlII.friends = llllllllllllllllIllIIlIllIlIIlII.sgGeneral.add(new BoolSetting.Builder().name("friends").description("Doesn't allow friends to be attacked.").defaultValue(true).build());
        llllllllllllllllIllIIlIllIlIIlII.babies = llllllllllllllllIllIIlIllIlIIlII.sgGeneral.add(new BoolSetting.Builder().name("babies").description("Doesn't allow babies to be attacked.").defaultValue(true).build());
        llllllllllllllllIllIIlIllIlIIlII.nametagged = llllllllllllllllIllIIlIllIlIIlII.sgGeneral.add(new BoolSetting.Builder().name("nametagged").description("Doesn't allow nametagged enities to be attacked.").defaultValue(false).build());
        llllllllllllllllIllIIlIllIlIIlII.blocks = llllllllllllllllIllIIlIllIlIIlII.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks to avoid hitting.").defaultValue(new ArrayList<class_2248>()).build());
    }

    @EventHandler(priority=100)
    private void onStartBreakingBlockEvent(StartBreakingBlockEvent llllllllllllllllIllIIlIllIIlIlll) {
        AntiHit llllllllllllllllIllIIlIllIIllIlI;
        if (llllllllllllllllIllIIlIllIIllIlI.blocks.get().contains((Object)llllllllllllllllIllIIlIllIIllIlI.mc.field_1687.method_8320(llllllllllllllllIllIIlIllIIlIlll.blockPos).method_26204())) {
            llllllllllllllllIllIIlIllIIlIlll.cancel();
        }
    }

    @EventHandler(priority=100)
    private void onAttackEntity(AttackEntityEvent llllllllllllllllIllIIlIllIIlllll) {
        AntiHit llllllllllllllllIllIIlIllIlIIIII;
        if (llllllllllllllllIllIIlIllIlIIIII.friends.get().booleanValue() && llllllllllllllllIllIIlIllIIlllll.entity instanceof class_1657 && !Friends.get().shouldAttack((class_1657)llllllllllllllllIllIIlIllIIlllll.entity)) {
            llllllllllllllllIllIIlIllIIlllll.cancel();
        }
        if (llllllllllllllllIllIIlIllIlIIIII.babies.get().booleanValue() && llllllllllllllllIllIIlIllIIlllll.entity instanceof class_1429 && ((class_1429)llllllllllllllllIllIIlIllIIlllll.entity).method_6109()) {
            llllllllllllllllIllIIlIllIIlllll.cancel();
        }
        if (llllllllllllllllIllIIlIllIlIIIII.nametagged.get().booleanValue() && llllllllllllllllIllIIlIllIIlllll.entity.method_16914()) {
            llllllllllllllllIllIIlIllIIlllll.cancel();
        }
        if (llllllllllllllllIllIIlIllIlIIIII.entities.get().getBoolean((Object)llllllllllllllllIllIIlIllIIlllll.entity.method_5864())) {
            llllllllllllllllIllIIlIllIIlllll.cancel();
        }
    }
}

