/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
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
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class Surround
extends Module {
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ Setting<Boolean> center;
    private final /* synthetic */ Setting<Boolean> disableOnJump;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean return_;
    private final /* synthetic */ Setting<Boolean> doubleHeight;
    private final /* synthetic */ Setting<Boolean> onlyOnGround;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> turnOff;
    private final /* synthetic */ Setting<Boolean> onlyWhenSneaking;
    private final /* synthetic */ Setting<Boolean> disableOnYChange;

    private void setBlockPos(int lllIlIIlIllllI, int lllIlIIlIlllIl, int lllIlIIlIlllII) {
        Surround lllIlIIlIlllll;
        lllIlIIlIlllll.blockPos.method_10102(lllIlIIlIlllll.mc.field_1724.method_23317() + (double)lllIlIIlIllllI, lllIlIIlIlllll.mc.field_1724.method_23318() + (double)lllIlIIlIlllIl, lllIlIIlIlllll.mc.field_1724.method_23321() + (double)lllIlIIlIlllII);
    }

    @Override
    public void onActivate() {
        Surround lllIlIlIIlllIl;
        if (lllIlIlIIlllIl.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
    }

    private boolean place(int lllIlIIllIlIll, int lllIlIIllIllll, int lllIlIIllIlIIl) {
        Surround lllIlIIlllIIIl;
        lllIlIIlllIIIl.setBlockPos(lllIlIIllIlIll, lllIlIIllIllll, lllIlIIllIlIIl);
        class_2680 lllIlIIllIllIl = lllIlIIlllIIIl.mc.field_1687.method_8320((class_2338)lllIlIIlllIIIl.blockPos);
        if (!lllIlIIllIllIl.method_26207().method_15800()) {
            return true;
        }
        if (BlockUtils.place((class_2338)lllIlIIlllIIIl.blockPos, InvUtils.findInHotbar(lllIlIIlIlIllI -> {
            Surround lllIlIIlIlIlll;
            return lllIlIIlIlIlll.blocks.get().contains((Object)class_2248.method_9503((class_1792)lllIlIIlIlIllI.method_7909()));
        }), lllIlIIlllIIIl.rotate.get(), 100, true)) {
            lllIlIIlllIIIl.return_ = true;
        }
        return false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllIlIlIIIllII) {
        Surround lllIlIlIIIIlIl;
        if (lllIlIlIIIIlIl.disableOnJump.get().booleanValue() && (lllIlIlIIIIlIl.mc.field_1690.field_1903.method_1434() || lllIlIlIIIIlIl.mc.field_1724.field_3913.field_3904) || lllIlIlIIIIlIl.disableOnYChange.get().booleanValue() && lllIlIlIIIIlIl.mc.field_1724.field_6036 < lllIlIlIIIIlIl.mc.field_1724.method_23318()) {
            lllIlIlIIIIlIl.toggle();
            return;
        }
        if (lllIlIlIIIIlIl.onlyOnGround.get().booleanValue() && !lllIlIlIIIIlIl.mc.field_1724.method_24828()) {
            return;
        }
        if (lllIlIlIIIIlIl.onlyWhenSneaking.get().booleanValue() && !lllIlIlIIIIlIl.mc.field_1690.field_1832.method_1434()) {
            return;
        }
        lllIlIlIIIIlIl.return_ = false;
        boolean lllIlIlIIIlIll = lllIlIlIIIIlIl.place(0, -1, 0);
        if (lllIlIlIIIIlIl.return_) {
            return;
        }
        boolean lllIlIlIIIlIlI = lllIlIlIIIIlIl.place(1, 0, 0);
        if (lllIlIlIIIIlIl.return_) {
            return;
        }
        boolean lllIlIlIIIlIIl = lllIlIlIIIIlIl.place(-1, 0, 0);
        if (lllIlIlIIIIlIl.return_) {
            return;
        }
        boolean lllIlIlIIIlIII = lllIlIlIIIIlIl.place(0, 0, 1);
        if (lllIlIlIIIIlIl.return_) {
            return;
        }
        boolean lllIlIlIIIIlll = lllIlIlIIIIlIl.place(0, 0, -1);
        if (lllIlIlIIIIlIl.return_) {
            return;
        }
        boolean lllIlIlIIIIllI = false;
        if (lllIlIlIIIIlIl.doubleHeight.get().booleanValue()) {
            boolean lllIlIlIIlIIIl = lllIlIlIIIIlIl.place(1, 1, 0);
            if (lllIlIlIIIIlIl.return_) {
                return;
            }
            boolean lllIlIlIIlIIII = lllIlIlIIIIlIl.place(-1, 1, 0);
            if (lllIlIlIIIIlIl.return_) {
                return;
            }
            boolean lllIlIlIIIllll = lllIlIlIIIIlIl.place(0, 1, 1);
            if (lllIlIlIIIIlIl.return_) {
                return;
            }
            boolean lllIlIlIIIlllI = lllIlIlIIIIlIl.place(0, 1, -1);
            if (lllIlIlIIIIlIl.return_) {
                return;
            }
            if (lllIlIlIIlIIIl && lllIlIlIIlIIII && lllIlIlIIIllll && lllIlIlIIIlllI) {
                lllIlIlIIIIllI = true;
            }
        }
        if (lllIlIlIIIIlIl.turnOff.get().booleanValue() && lllIlIlIIIlIll && lllIlIlIIIlIlI && lllIlIlIIIlIIl && lllIlIlIIIlIII && lllIlIlIIIIlll && (lllIlIlIIIIllI || !lllIlIlIIIIlIl.doubleHeight.get().booleanValue())) {
            lllIlIlIIIIlIl.toggle();
        }
    }

    public Surround() {
        super(Categories.Combat, "surround", "Surrounds you in blocks to prevent you from taking lots of damage.");
        Surround lllIlIlIlIIIII;
        lllIlIlIlIIIII.sgGeneral = lllIlIlIlIIIII.settings.getDefaultGroup();
        lllIlIlIlIIIII.doubleHeight = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("double-height").description("Places obsidian on top of the original surround blocks to prevent people from face-placing you.").defaultValue(false).build());
        lllIlIlIlIIIII.onlyOnGround = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Works only when you standing on blocks.").defaultValue(true).build());
        lllIlIlIlIIIII.onlyWhenSneaking = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("only-when-sneaking").description("Places blocks only after sneaking.").defaultValue(false).build());
        lllIlIlIlIIIII.turnOff = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Toggles off when all blocks are placed.").defaultValue(false).build());
        lllIlIlIlIIIII.center = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("center").description("Teleports you to the center of the block.").defaultValue(true).build());
        lllIlIlIlIIIII.disableOnJump = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("disable-on-jump").description("Automatically disables when you jump.").defaultValue(true).build());
        lllIlIlIlIIIII.disableOnYChange = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("disable-on-y-change").description("Automatically disables when your y level (step, jumping, atc).").defaultValue(true).build());
        lllIlIlIlIIIII.rotate = lllIlIlIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the obsidian being placed.").defaultValue(true).build());
        lllIlIlIlIIIII.blocks = lllIlIlIlIIIII.sgGeneral.add(new BlockListSetting.Builder().name("block").description("What blocks to use for surround.").defaultValue(Collections.singletonList(class_2246.field_10540)).filter(lllIlIlIlIIIII::blockFilter).build());
        lllIlIlIlIIIII.blockPos = new class_2338.class_2339();
    }

    private boolean blockFilter(class_2248 lllIlIIllllIII) {
        return lllIlIIllllIII == class_2246.field_10540 || lllIlIIllllIII == class_2246.field_22423 || lllIlIIllllIII == class_2246.field_22108 || lllIlIIllllIII == class_2246.field_10443 || lllIlIIllllIII == class_2246.field_23152;
    }
}

