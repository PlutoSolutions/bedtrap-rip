/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2199;
import net.minecraft.class_2231;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2269;
import net.minecraft.class_2338;
import net.minecraft.class_471;

public class AutoAnvil
extends Module {
    private final Setting<Boolean> toggleOnBreak;
    private final Setting<Boolean> placeButton;
    private final Setting<Boolean> multiplace;
    private int timer;
    private class_1657 target;
    private final Setting<Integer> height;
    private final Setting<Integer> delay;
    private final Setting<Double> range;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> rotate;
    private final Setting<SortPriority> priority;

    private boolean isHole(class_2338 class_23382) {
        class_2338.class_2339 class_23392 = new class_2338.class_2339(class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260());
        return this.mc.field_1687.method_8320(class_23392.method_10069(1, 0, 0)).method_26204().method_27839(class_2246.field_10124) || this.mc.field_1687.method_8320(class_23392.method_10069(-1, 0, 0)).method_26204().method_27839(class_2246.field_10124) || this.mc.field_1687.method_8320(class_23392.method_10069(0, 0, 1)).method_26204().method_27839(class_2246.field_10124) || this.mc.field_1687.method_8320(class_23392.method_10069(0, 0, -1)).method_26204().method_27839(class_2246.field_10124);
    }

    private static boolean lambda$onTick$1(class_1799 class_17992) {
        return class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2199;
    }

    @Override
    public void onActivate() {
        this.timer = 0;
        this.target = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        FindItemResult findItemResult;
        if (this.toggleOnBreak.get().booleanValue() && this.target != null && this.target.field_7514.method_7372(3).method_7960()) {
            this.error("Target head slot is empty... disabling.", new Object[0]);
            this.toggle();
            return;
        }
        if (TargetUtils.isBadTarget(this.target, this.range.get())) {
            this.target = TargetUtils.getPlayerTarget(this.range.get(), this.priority.get());
        }
        if (TargetUtils.isBadTarget(this.target, this.range.get())) {
            return;
        }
        if (this.placeButton.get().booleanValue()) {
            findItemResult = InvUtils.findInHotbar(AutoAnvil::lambda$onTick$0);
            BlockUtils.place(this.target.method_24515(), findItemResult, this.rotate.get(), 0, false);
        }
        if (this.timer >= this.delay.get()) {
            this.timer = 0;
            findItemResult = InvUtils.findInHotbar(AutoAnvil::lambda$onTick$1);
            if (!findItemResult.found()) {
                return;
            }
            for (int i = this.height.get().intValue(); i > 1; --i) {
                class_2338 class_23382 = this.target.method_24515().method_10084().method_10069(0, i, 0);
                for (int j = 0; j < i && this.mc.field_1687.method_8320(this.target.method_24515().method_10086(j + 1)).method_26207().method_15800(); ++j) {
                    if (3 != -1) continue;
                    return;
                }
                if (!BlockUtils.place(class_23382, findItemResult, this.rotate.get(), 0) || this.multiplace.get().booleanValue()) {
                    if (null == null) continue;
                    return;
                }
                break;
            }
        } else {
            ++this.timer;
        }
    }

    public AutoAnvil() {
        super(Categories.Combat, "auto-anvil", "Automatically places anvils above players to destroy helmets.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        this.height = this.sgGeneral.add(new IntSetting.Builder().name("height").description("The height to place anvils at.").defaultValue(2).min(0).max(5).sliderMin(0).sliderMax(5).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("The delay in between anvil placements.").min(0).defaultValue(10).sliderMax(50).build());
        this.placeButton = this.sgGeneral.add(new BoolSetting.Builder().name("place-at-feet").description("Automatically places a button or pressure plate at the targets feet to break the anvils.").defaultValue(true).build());
        this.multiplace = this.sgGeneral.add(new BoolSetting.Builder().name("multiplace").description("Places multiple anvils at once..").defaultValue(true).build());
        this.toggleOnBreak = this.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-break").description("Toggles when the target's helmet slot is empty.").defaultValue(false).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the position anvils/pressure plates/buttons are placed.").defaultValue(true).build());
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2231 || class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2269;
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        if (openScreenEvent.screen instanceof class_471) {
            openScreenEvent.cancel();
        }
    }
}

