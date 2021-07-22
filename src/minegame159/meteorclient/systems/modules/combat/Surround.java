/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.systems.modules.combat;

import java.util.Collections;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class Surround
extends Module {
    private final Setting<List<class_2248>> blocks;
    private final Setting<Boolean> center;
    private final Setting<Boolean> disableOnJump;
    private final SettingGroup sgGeneral;
    private boolean return_;
    private final Setting<Boolean> doubleHeight;
    private final Setting<Boolean> onlyOnGround;
    private final class_2338.class_2339 blockPos;
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> turnOff;
    private final Setting<Boolean> onlyWhenSneaking;
    private final Setting<Boolean> disableOnYChange;

    private void setBlockPos(int n, int n2, int n3) {
        this.blockPos.method_10102(this.mc.field_1724.method_23317() + (double)n, this.mc.field_1724.method_23318() + (double)n2, this.mc.field_1724.method_23321() + (double)n3);
    }

    @Override
    public void onActivate() {
        if (this.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
    }

    private boolean place(int n, int n2, int n3) {
        this.setBlockPos(n, n2, n3);
        class_2680 class_26802 = this.mc.field_1687.method_8320((class_2338)this.blockPos);
        if (!class_26802.method_26207().method_15800()) {
            return true;
        }
        if (BlockUtils.place((class_2338)this.blockPos, InvUtils.findInHotbar(this::lambda$place$0), this.rotate.get(), 100, true)) {
            this.return_ = true;
        }
        return false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.disableOnJump.get().booleanValue() && (this.mc.field_1690.field_1903.method_1434() || this.mc.field_1724.field_3913.field_3904) || this.disableOnYChange.get().booleanValue() && this.mc.field_1724.field_6036 < this.mc.field_1724.method_23318()) {
            this.toggle();
            return;
        }
        if (this.onlyOnGround.get().booleanValue() && !this.mc.field_1724.method_24828()) {
            return;
        }
        if (this.onlyWhenSneaking.get().booleanValue() && !this.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        this.return_ = false;
        boolean bl = this.place(0, -1, 0);
        if (this.return_) {
            return;
        }
        boolean bl2 = this.place(1, 0, 0);
        if (this.return_) {
            return;
        }
        boolean bl3 = this.place(-1, 0, 0);
        if (this.return_) {
            return;
        }
        boolean bl4 = this.place(0, 0, 1);
        if (this.return_) {
            return;
        }
        boolean bl5 = this.place(0, 0, -1);
        if (this.return_) {
            return;
        }
        boolean bl6 = false;
        if (this.doubleHeight.get().booleanValue()) {
            boolean bl7 = this.place(1, 1, 0);
            if (this.return_) {
                return;
            }
            boolean bl8 = this.place(-1, 1, 0);
            if (this.return_) {
                return;
            }
            boolean bl9 = this.place(0, 1, 1);
            if (this.return_) {
                return;
            }
            boolean bl10 = this.place(0, 1, -1);
            if (this.return_) {
                return;
            }
            if (bl7 && bl8 && bl9 && bl10) {
                bl6 = true;
            }
        }
        if (this.turnOff.get().booleanValue() && bl && bl2 && bl3 && bl4 && bl5 && (bl6 || !this.doubleHeight.get().booleanValue())) {
            this.toggle();
        }
    }

    public Surround() {
        super(Categories.Combat, "surround", "Surrounds you in blocks to prevent you from taking lots of damage.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.doubleHeight = this.sgGeneral.add(new BoolSetting.Builder().name("double-height").description("Places obsidian on top of the original surround blocks to prevent people from face-placing you.").defaultValue(false).build());
        this.onlyOnGround = this.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        this.onlyWhenSneaking = this.sgGeneral.add(new BoolSetting.Builder().name("only-when-sneaking").description("Places blocks only after sneaking.").defaultValue(false).build());
        this.turnOff = this.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Toggles off when all blocks are placed.").defaultValue(false).build());
        this.center = this.sgGeneral.add(new BoolSetting.Builder().name("center").description("Teleports you to the center of the block.").defaultValue(true).build());
        this.disableOnJump = this.sgGeneral.add(new BoolSetting.Builder().name("disable-on-jump").description("Automatically disables when you jump.").defaultValue(true).build());
        this.disableOnYChange = this.sgGeneral.add(new BoolSetting.Builder().name("disable-on-y-change").description("Automatically disables when your y level (step, jumping, atc).").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the obsidian being placed.").defaultValue(true).build());
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("block").description("What blocks to use for surround.").defaultValue(Collections.singletonList(class_2246.field_10540)).filter(this::blockFilter).build());
        this.blockPos = new class_2338.class_2339();
    }

    private boolean blockFilter(class_2248 class_22482) {
        return class_22482 == class_2246.field_10540 || class_22482 == class_2246.field_22423 || class_22482 == class_2246.field_22108 || class_22482 == class_2246.field_10443 || class_22482 == class_2246.field_23152;
    }

    private boolean lambda$place$0(class_1799 class_17992) {
        return this.blocks.get().contains((Object)class_2248.method_9503((class_1792)class_17992.method_7909()));
    }
}

