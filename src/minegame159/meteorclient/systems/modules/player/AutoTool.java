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
    private final /* synthetic */ Setting<Integer> switchDelay;
    private static final /* synthetic */ Set<class_2248> EMPTY_BLOCKS;
    private final /* synthetic */ Setting<Boolean> antiBreak;
    private final /* synthetic */ Setting<EnchantPreference> prefer;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int ticks;
    private /* synthetic */ int prevSlot;
    private /* synthetic */ class_2680 blockState;
    private final /* synthetic */ Setting<Integer> breakDurability;
    private /* synthetic */ boolean shouldSwitch;
    private final /* synthetic */ Setting<Boolean> silkTouchForEnderChest;
    private final /* synthetic */ Setting<Boolean> preferMending;
    private /* synthetic */ boolean wasPressed;
    private static final /* synthetic */ Set<class_3614> EMPTY_MATERIALS;
    private final /* synthetic */ Setting<Boolean> switchBack;

    public AutoTool() {
        super(Categories.Player, "auto-tool", "Automatically switches to the most effective tool when performing an action.");
        AutoTool llllllllllllllllIlllllIIllIlllll;
        llllllllllllllllIlllllIIllIlllll.sgGeneral = llllllllllllllllIlllllIIllIlllll.settings.getDefaultGroup();
        llllllllllllllllIlllllIIllIlllll.prefer = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new EnumSetting.Builder().name("prefer").description("Either to prefer Silk Touch, Fortune, or none.").defaultValue(EnchantPreference.Fortune).build());
        llllllllllllllllIlllllIIllIlllll.preferMending = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new BoolSetting.Builder().name("prefer-mending").description("Whether or not to prefer the Mending enchantment.").defaultValue(true).build());
        llllllllllllllllIlllllIIllIlllll.silkTouchForEnderChest = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new BoolSetting.Builder().name("silk-touch-for-ender-chest").description("Mines Ender Chests only with the Silk Touch enchantment.").defaultValue(true).build());
        llllllllllllllllIlllllIIllIlllll.antiBreak = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Stops you from breaking your tool.").defaultValue(false).build());
        llllllllllllllllIlllllIIllIlllll.breakDurability = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new IntSetting.Builder().name("anti-break-durability").description("The durability to stop using a tool.").defaultValue(10).max(50).min(2).sliderMax(20).build());
        llllllllllllllllIlllllIIllIlllll.switchBack = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new BoolSetting.Builder().name("switch-back").description("Switches your hand to whatever was selected when releasing your attack key.").defaultValue(false).build());
        llllllllllllllllIlllllIIllIlllll.switchDelay = llllllllllllllllIlllllIIllIlllll.sgGeneral.add(new IntSetting.Builder().name("switch-delay").description("Delay in ticks before switching tools.").defaultValue(0).build());
    }

    @EventHandler(priority=100)
    private void onStartBreakingBlock(StartBreakingBlockEvent llllllllllllllllIlllllIIlIllllII) {
        AutoTool llllllllllllllllIlllllIIllIIIIII;
        llllllllllllllllIlllllIIllIIIIII.blockState = llllllllllllllllIlllllIIllIIIIII.mc.field_1687.method_8320(llllllllllllllllIlllllIIlIllllII.blockPos);
        if (llllllllllllllllIlllllIIllIIIIII.blockState.method_26214((class_1922)llllllllllllllllIlllllIIllIIIIII.mc.field_1687, llllllllllllllllIlllllIIlIllllII.blockPos) < 0.0f || llllllllllllllllIlllllIIllIIIIII.blockState.method_26215()) {
            return;
        }
        class_1799 llllllllllllllllIlllllIIlIlllllI = llllllllllllllllIlllllIIllIIIIII.mc.field_1724.method_6047();
        if (!AutoTool.isEffectiveOn(llllllllllllllllIlllllIIlIlllllI.method_7909(), llllllllllllllllIlllllIIllIIIIII.blockState) || llllllllllllllllIlllllIIllIIIIII.shouldStopUsing(llllllllllllllllIlllllIIlIlllllI) || !(llllllllllllllllIlllllIIlIlllllI.method_7909() instanceof class_1831)) {
            llllllllllllllllIlllllIIllIIIIII.shouldSwitch = true;
            llllllllllllllllIlllllIIllIIIIII.ticks = llllllllllllllllIlllllIIllIIIIII.switchDelay.get();
        }
        if (llllllllllllllllIlllllIIllIIIIII.shouldStopUsing(llllllllllllllllIlllllIIlIlllllI = llllllllllllllllIlllllIIllIIIIII.mc.field_1724.method_6047()) && llllllllllllllllIlllllIIlIlllllI.method_7909() instanceof class_1831) {
            llllllllllllllllIlllllIIllIIIIII.mc.field_1690.field_1886.method_23481(false);
            llllllllllllllllIlllllIIlIllllII.setCancelled(true);
        }
    }

    static {
        EMPTY_MATERIALS = new HashSet<class_3614>(0);
        EMPTY_BLOCKS = new HashSet<class_2248>(0);
    }

    private boolean shouldStopUsing(class_1799 llllllllllllllllIlllllIIlIlIIIlI) {
        AutoTool llllllllllllllllIlllllIIlIlIIIIl;
        return llllllllllllllllIlllllIIlIlIIIIl.antiBreak.get() != false && llllllllllllllllIlllllIIlIlIIIlI.method_7936() - llllllllllllllllIlllllIIlIlIIIlI.method_7919() < llllllllllllllllIlllllIIlIlIIIIl.breakDurability.get();
    }

    /*
     * WARNING - void declaration
     */
    public static boolean isEffectiveOn(class_1792 llllllllllllllllIlllllIIllIIlIll, class_2680 llllllllllllllllIlllllIIllIIlllI) {
        void llllllllllllllllIlllllIIllIIllII;
        void llllllllllllllllIlllllIIllIIllIl;
        if (llllllllllllllllIlllllIIllIIlIll.method_7856(llllllllllllllllIlllllIIllIIlllI)) {
            return true;
        }
        if (llllllllllllllllIlllllIIllIIlIll instanceof class_1810) {
            Set<class_3614> llllllllllllllllIlllllIIllIllIIl = EMPTY_MATERIALS;
            Set<class_2248> llllllllllllllllIlllllIIllIllIII = PickaxeItemAccessor.getEffectiveBlocks();
        } else if (llllllllllllllllIlllllIIllIIlIll instanceof class_1743) {
            Set<class_3614> llllllllllllllllIlllllIIllIlIlll = AxeItemAccessor.getEffectiveMaterials();
            Set<class_2248> llllllllllllllllIlllllIIllIlIllI = AxeItemAccessor.getEffectiveBlocks();
        } else if (llllllllllllllllIlllllIIllIIlIll instanceof class_1821) {
            Set<class_3614> llllllllllllllllIlllllIIllIlIlIl = EMPTY_MATERIALS;
            Set<class_2248> llllllllllllllllIlllllIIllIlIlII = ShovelItemAccessor.getEffectiveBlocks();
        } else if (llllllllllllllllIlllllIIllIIlIll instanceof class_1794) {
            Set<class_3614> llllllllllllllllIlllllIIllIlIIll = EMPTY_MATERIALS;
            Set<class_2248> llllllllllllllllIlllllIIllIlIIlI = HoeItemAccessor.getEffectiveBlocks();
        } else if (llllllllllllllllIlllllIIllIIlIll instanceof class_1829) {
            Set<class_3614> llllllllllllllllIlllllIIllIlIIIl = EMPTY_MATERIALS;
            Set<class_2248> llllllllllllllllIlllllIIllIlIIII = EMPTY_BLOCKS;
        } else {
            return false;
        }
        return llllllllllllllllIlllllIIllIIllIl.contains((Object)llllllllllllllllIlllllIIllIIlllI.method_26207()) || llllllllllllllllIlllllIIllIIllII.contains((Object)llllllllllllllllIlllllIIllIIlllI.method_26204());
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIlllllIIllIIIlIl) {
        AutoTool llllllllllllllllIlllllIIllIIIlII;
        if (llllllllllllllllIlllllIIllIIIlII.switchBack.get().booleanValue() && !llllllllllllllllIlllllIIllIIIlII.mc.field_1690.field_1886.method_1434() && llllllllllllllllIlllllIIllIIIlII.wasPressed && llllllllllllllllIlllllIIllIIIlII.prevSlot != -1) {
            InvUtils.swap(llllllllllllllllIlllllIIllIIIlII.prevSlot);
            llllllllllllllllIlllllIIllIIIlII.prevSlot = -1;
        }
        llllllllllllllllIlllllIIllIIIlII.wasPressed = llllllllllllllllIlllllIIllIIIlII.mc.field_1690.field_1886.method_1434();
        if (llllllllllllllllIlllllIIllIIIlII.shouldSwitch && llllllllllllllllIlllllIIllIIIlII.ticks <= 0) {
            llllllllllllllllIlllllIIllIIIlII.switchSlots(llllllllllllllllIlllllIIllIIIlII.blockState);
            llllllllllllllllIlllllIIllIIIlII.shouldSwitch = false;
        }
        if (llllllllllllllllIlllllIIllIIIlII.ticks > 0) {
            --llllllllllllllllIlllllIIllIIIlII.ticks;
        }
    }

    private void switchSlots(class_2680 llllllllllllllllIlllllIIlIlIllll) {
        AutoTool llllllllllllllllIlllllIIlIllIIII;
        int llllllllllllllllIlllllIIlIlIlllI = -1;
        int llllllllllllllllIlllllIIlIlIllIl = -1;
        for (int llllllllllllllllIlllllIIlIllIIIl = 0; llllllllllllllllIlllllIIlIllIIIl < 9; ++llllllllllllllllIlllllIIlIllIIIl) {
            class_1799 llllllllllllllllIlllllIIlIllIIll = llllllllllllllllIlllllIIlIllIIII.mc.field_1724.field_7514.method_5438(llllllllllllllllIlllllIIlIllIIIl);
            if (!AutoTool.isEffectiveOn(llllllllllllllllIlllllIIlIllIIll.method_7909(), llllllllllllllllIlllllIIlIlIllll) || llllllllllllllllIlllllIIlIllIIII.shouldStopUsing(llllllllllllllllIlllllIIlIllIIll) || !(llllllllllllllllIlllllIIlIllIIll.method_7909() instanceof class_1831) || llllllllllllllllIlllllIIlIllIIII.silkTouchForEnderChest.get().booleanValue() && llllllllllllllllIlllllIIlIlIllll.method_26204() == class_2246.field_10443 && class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)llllllllllllllllIlllllIIlIllIIll) == 0) continue;
            int llllllllllllllllIlllllIIlIllIIlI = 0;
            llllllllllllllllIlllllIIlIllIIlI += Math.round(llllllllllllllllIlllllIIlIllIIll.method_7924(llllllllllllllllIlllllIIlIlIllll));
            llllllllllllllllIlllllIIlIllIIlI += class_1890.method_8225((class_1887)class_1893.field_9119, (class_1799)llllllllllllllllIlllllIIlIllIIll);
            llllllllllllllllIlllllIIlIllIIlI += class_1890.method_8225((class_1887)class_1893.field_9131, (class_1799)llllllllllllllllIlllllIIlIllIIll);
            if (llllllllllllllllIlllllIIlIllIIII.preferMending.get().booleanValue()) {
                llllllllllllllllIlllllIIlIllIIlI += class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)llllllllllllllllIlllllIIlIllIIll);
            }
            if (llllllllllllllllIlllllIIlIllIIII.prefer.get() == EnchantPreference.Fortune) {
                llllllllllllllllIlllllIIlIllIIlI += class_1890.method_8225((class_1887)class_1893.field_9130, (class_1799)llllllllllllllllIlllllIIlIllIIll);
            }
            if (llllllllllllllllIlllllIIlIllIIII.prefer.get() == EnchantPreference.SilkTouch) {
                llllllllllllllllIlllllIIlIllIIlI += class_1890.method_8225((class_1887)class_1893.field_9099, (class_1799)llllllllllllllllIlllllIIlIllIIll);
            }
            if (llllllllllllllllIlllllIIlIllIIlI <= llllllllllllllllIlllllIIlIlIlllI) continue;
            llllllllllllllllIlllllIIlIlIlllI = llllllllllllllllIlllllIIlIllIIlI;
            llllllllllllllllIlllllIIlIlIllIl = llllllllllllllllIlllllIIlIllIIIl;
        }
        if (llllllllllllllllIlllllIIlIlIllIl != -1) {
            if (llllllllllllllllIlllllIIlIllIIII.prevSlot == -1) {
                llllllllllllllllIlllllIIlIllIIII.prevSlot = llllllllllllllllIlllllIIlIllIIII.mc.field_1724.field_7514.field_7545;
            }
            InvUtils.swap(llllllllllllllllIlllllIIlIlIllIl);
        }
    }

    public static enum EnchantPreference {
        None,
        Fortune,
        SilkTouch;


        private EnchantPreference() {
            EnchantPreference llIllIIlIlIlIIl;
        }
    }
}

