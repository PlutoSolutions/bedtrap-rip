/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1794
 *  net.minecraft.class_1799
 *  net.minecraft.class_1810
 *  net.minecraft.class_1821
 *  net.minecraft.class_1829
 *  net.minecraft.class_1831
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2680
 *  net.minecraft.class_3614
 */
package minegame159.meteorclient.systems.modules.player;

import java.util.HashSet;
import java.util.Set;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.StartBreakingBlockEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.AxeItemAccessor;
import minegame159.meteorclient.mixin.HoeItemAccessor;
import minegame159.meteorclient.mixin.PickaxeItemAccessor;
import minegame159.meteorclient.mixin.ShovelItemAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1799;
import net.minecraft.class_1810;
import net.minecraft.class_1821;
import net.minecraft.class_1829;
import net.minecraft.class_1831;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_3614;

public class AutoTool
extends Module {
    private final Setting<Integer> switchDelay;
    private static final Set<class_2248> EMPTY_BLOCKS;
    private final Setting<Boolean> antiBreak;
    private final Setting<EnchantPreference> prefer;
    private final SettingGroup sgGeneral;
    private int ticks;
    private int prevSlot;
    private class_2680 blockState;
    private final Setting<Integer> breakDurability;
    private boolean shouldSwitch;
    private final Setting<Boolean> silkTouchForEnderChest;
    private final Setting<Boolean> preferMending;
    private boolean wasPressed;
    private static final Set<class_3614> EMPTY_MATERIALS;
    private final Setting<Boolean> switchBack;

    public AutoTool() {
        super(Categories.Player, "auto-tool", "Automatically switches to the most effective tool when performing an action.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.prefer = this.sgGeneral.add(new EnumSetting.Builder().name("prefer").description("Either to prefer Silk Touch, Fortune, or none.").defaultValue(EnchantPreference.Fortune).build());
        this.preferMending = this.sgGeneral.add(new BoolSetting.Builder().name("prefer-mending").description("Whether or not to prefer the Mending enchantment.").defaultValue(true).build());
        this.silkTouchForEnderChest = this.sgGeneral.add(new BoolSetting.Builder().name("silk-touch-for-ender-chest").description("Mines Ender Chests only with the Silk Touch enchantment.").defaultValue(true).build());
        this.antiBreak = this.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Stops you from breaking your tool.").defaultValue(false).build());
        this.breakDurability = this.sgGeneral.add(new IntSetting.Builder().name("anti-break-durability").description("The durability to stop using a tool.").defaultValue(10).max(50).min(2).sliderMax(20).build());
        this.switchBack = this.sgGeneral.add(new BoolSetting.Builder().name("switch-back").description("Switches your hand to whatever was selected when releasing your attack key.").defaultValue(false).build());
        this.switchDelay = this.sgGeneral.add(new IntSetting.Builder().name("switch-delay").description("Delay in ticks before switching tools.").defaultValue(0).build());
    }

    @EventHandler(priority=100)
    private void onStartBreakingBlock(StartBreakingBlockEvent startBreakingBlockEvent) {
        this.blockState = this.mc.field_1687.method_8320(startBreakingBlockEvent.blockPos);
        if (this.blockState.method_26214((class_1922)this.mc.field_1687, startBreakingBlockEvent.blockPos) < 0.0f || this.blockState.method_26215()) {
            return;
        }
        class_1799 class_17992 = this.mc.field_1724.method_6047();
        if (!AutoTool.isEffectiveOn(class_17992.method_7909(), this.blockState) || this.shouldStopUsing(class_17992) || !(class_17992.method_7909() instanceof class_1831)) {
            this.shouldSwitch = true;
            this.ticks = this.switchDelay.get();
        }
        if (this.shouldStopUsing(class_17992 = this.mc.field_1724.method_6047()) && class_17992.method_7909() instanceof class_1831) {
            this.mc.field_1690.field_1886.method_23481(false);
            startBreakingBlockEvent.setCancelled(true);
        }
    }

    static {
        EMPTY_MATERIALS = new HashSet<class_3614>(0);
        EMPTY_BLOCKS = new HashSet<class_2248>(0);
    }

    private boolean shouldStopUsing(class_1799 class_17992) {
        return this.antiBreak.get() != false && class_17992.method_7936() - class_17992.method_7919() < this.breakDurability.get();
    }

    public static boolean isEffectiveOn(class_1792 class_17922, class_2680 class_26802) {
        Set<class_2248> set;
        Set<class_3614> set2;
        if (class_17922.method_7856(class_26802)) {
            return true;
        }
        if (class_17922 instanceof class_1810) {
            set2 = EMPTY_MATERIALS;
            set = PickaxeItemAccessor.getEffectiveBlocks();
        } else if (class_17922 instanceof class_1743) {
            set2 = AxeItemAccessor.getEffectiveMaterials();
            set = AxeItemAccessor.getEffectiveBlocks();
        } else if (class_17922 instanceof class_1821) {
            set2 = EMPTY_MATERIALS;
            set = ShovelItemAccessor.getEffectiveBlocks();
        } else if (class_17922 instanceof class_1794) {
            set2 = EMPTY_MATERIALS;
            set = HoeItemAccessor.getEffectiveBlocks();
        } else if (class_17922 instanceof class_1829) {
            set2 = EMPTY_MATERIALS;
            set = EMPTY_BLOCKS;
        } else {
            return false;
        }
        return set2.contains((Object)class_26802.method_26207()) || set.contains((Object)class_26802.method_26204());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.switchBack.get().booleanValue() && !this.mc.field_1690.field_1886.method_1434() && this.wasPressed && this.prevSlot != -1) {
            InvUtils.swap(this.prevSlot);
            this.prevSlot = -1;
        }
        this.wasPressed = this.mc.field_1690.field_1886.method_1434();
        if (this.shouldSwitch && this.ticks <= 0) {
            this.switchSlots(this.blockState);
            this.shouldSwitch = false;
        }
        if (this.ticks > 0) {
            --this.ticks;
        }
    }

    private void switchSlots(class_2680 class_26802) {
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < 9; ++i) {
            class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
            if (!AutoTool.isEffectiveOn(class_17992.method_7909(), class_26802) || this.shouldStopUsing(class_17992) || !(class_17992.method_7909() instanceof class_1831) || this.silkTouchForEnderChest.get().booleanValue() && class_26802.method_26204() == class_2246.field_10443 && class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)class_17992) == 0) continue;
            int n3 = 0;
            n3 += Math.round(class_17992.method_7924(class_26802));
            n3 += class_1890.method_8225((class_1887)class_1893.field_9119, (class_1799)class_17992);
            n3 += class_1890.method_8225((class_1887)class_1893.field_9131, (class_1799)class_17992);
            if (this.preferMending.get().booleanValue()) {
                n3 += class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)class_17992);
            }
            if (this.prefer.get() == EnchantPreference.Fortune) {
                n3 += class_1890.method_8225((class_1887)class_1893.field_9130, (class_1799)class_17992);
            }
            if (this.prefer.get() == EnchantPreference.SilkTouch) {
                n3 += class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)class_17992);
            }
            if (n3 <= n) continue;
            n = n3;
            n2 = i;
            if (null == null) continue;
            return;
        }
        if (n2 != -1) {
            if (this.prevSlot == -1) {
                this.prevSlot = this.mc.field_1724.field_7514.field_7545;
            }
            InvUtils.swap(n2);
        }
    }

    public static enum EnchantPreference {
        None,
        Fortune,
        SilkTouch;

    }
}

