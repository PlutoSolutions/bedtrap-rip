/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_2199
 *  net.minecraft.class_2231
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2269
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_471
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
import net.minecraft.class_2199;
import net.minecraft.class_2231;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2269;
import net.minecraft.class_2338;
import net.minecraft.class_471;

public class AutoAnvil
extends Module {
    private final /* synthetic */ Setting<Boolean> toggleOnBreak;
    private final /* synthetic */ Setting<Boolean> placeButton;
    private final /* synthetic */ Setting<Boolean> multiplace;
    private /* synthetic */ int timer;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<Integer> height;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<SortPriority> priority;

    private boolean isHole(class_2338 llllllllllllllllIllIIIIIllIlIlIl) {
        AutoAnvil llllllllllllllllIllIIIIIllIlIllI;
        class_2338.class_2339 llllllllllllllllIllIIIIIllIlIlll = new class_2338.class_2339(llllllllllllllllIllIIIIIllIlIlIl.method_10263(), llllllllllllllllIllIIIIIllIlIlIl.method_10264(), llllllllllllllllIllIIIIIllIlIlIl.method_10260());
        return llllllllllllllllIllIIIIIllIlIllI.mc.field_1687.method_8320(llllllllllllllllIllIIIIIllIlIlll.method_10069(1, 0, 0)).method_26204().method_27839(class_2246.field_10124) || llllllllllllllllIllIIIIIllIlIllI.mc.field_1687.method_8320(llllllllllllllllIllIIIIIllIlIlll.method_10069(-1, 0, 0)).method_26204().method_27839(class_2246.field_10124) || llllllllllllllllIllIIIIIllIlIllI.mc.field_1687.method_8320(llllllllllllllllIllIIIIIllIlIlll.method_10069(0, 0, 1)).method_26204().method_27839(class_2246.field_10124) || llllllllllllllllIllIIIIIllIlIllI.mc.field_1687.method_8320(llllllllllllllllIllIIIIIllIlIlll.method_10069(0, 0, -1)).method_26204().method_27839(class_2246.field_10124);
    }

    @Override
    public void onActivate() {
        llllllllllllllllIllIIIIIlllIIIIl.timer = 0;
        llllllllllllllllIllIIIIIlllIIIIl.target = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIllIIIIIllIIlIII) {
        AutoAnvil llllllllllllllllIllIIIIIllIIlIIl;
        if (llllllllllllllllIllIIIIIllIIlIIl.toggleOnBreak.get().booleanValue() && llllllllllllllllIllIIIIIllIIlIIl.target != null && llllllllllllllllIllIIIIIllIIlIIl.target.field_7514.method_7372(3).method_7960()) {
            llllllllllllllllIllIIIIIllIIlIIl.error("Target head slot is empty... disabling.", new Object[0]);
            llllllllllllllllIllIIIIIllIIlIIl.toggle();
            return;
        }
        if (TargetUtils.isBadTarget(llllllllllllllllIllIIIIIllIIlIIl.target, llllllllllllllllIllIIIIIllIIlIIl.range.get())) {
            llllllllllllllllIllIIIIIllIIlIIl.target = TargetUtils.getPlayerTarget(llllllllllllllllIllIIIIIllIIlIIl.range.get(), llllllllllllllllIllIIIIIllIIlIIl.priority.get());
        }
        if (TargetUtils.isBadTarget(llllllllllllllllIllIIIIIllIIlIIl.target, llllllllllllllllIllIIIIIllIIlIIl.range.get())) {
            return;
        }
        if (llllllllllllllllIllIIIIIllIIlIIl.placeButton.get().booleanValue()) {
            FindItemResult llllllllllllllllIllIIIIIllIIlllI = InvUtils.findInHotbar(llllllllllllllllIllIIIIIlIlllIll -> class_2248.method_9503((class_1792)llllllllllllllllIllIIIIIlIlllIll.method_7909()) instanceof class_2231 || class_2248.method_9503((class_1792)llllllllllllllllIllIIIIIlIlllIll.method_7909()) instanceof class_2269);
            BlockUtils.place(llllllllllllllllIllIIIIIllIIlIIl.target.method_24515(), llllllllllllllllIllIIIIIllIIlllI, llllllllllllllllIllIIIIIllIIlIIl.rotate.get(), 0, false);
        }
        if (llllllllllllllllIllIIIIIllIIlIIl.timer >= llllllllllllllllIllIIIIIllIIlIIl.delay.get()) {
            llllllllllllllllIllIIIIIllIIlIIl.timer = 0;
            FindItemResult llllllllllllllllIllIIIIIllIIlIlI = InvUtils.findInHotbar(llllllllllllllllIllIIIIIlIllllIl -> class_2248.method_9503((class_1792)llllllllllllllllIllIIIIIlIllllIl.method_7909()) instanceof class_2199);
            if (!llllllllllllllllIllIIIIIllIIlIlI.found()) {
                return;
            }
            for (int llllllllllllllllIllIIIIIllIIlIll = llllllllllllllllIllIIIIIllIIlIIl.height.get().intValue(); llllllllllllllllIllIIIIIllIIlIll > 1; --llllllllllllllllIllIIIIIllIIlIll) {
                class_2338 llllllllllllllllIllIIIIIllIIllII = llllllllllllllllIllIIIIIllIIlIIl.target.method_24515().method_10084().method_10069(0, llllllllllllllllIllIIIIIllIIlIll, 0);
                for (int llllllllllllllllIllIIIIIllIIllIl = 0; llllllllllllllllIllIIIIIllIIllIl < llllllllllllllllIllIIIIIllIIlIll && llllllllllllllllIllIIIIIllIIlIIl.mc.field_1687.method_8320(llllllllllllllllIllIIIIIllIIlIIl.target.method_24515().method_10086(llllllllllllllllIllIIIIIllIIllIl + 1)).method_26207().method_15800(); ++llllllllllllllllIllIIIIIllIIllIl) {
                }
                if (!BlockUtils.place(llllllllllllllllIllIIIIIllIIllII, llllllllllllllllIllIIIIIllIIlIlI, llllllllllllllllIllIIIIIllIIlIIl.rotate.get(), 0) || llllllllllllllllIllIIIIIllIIlIIl.multiplace.get().booleanValue()) {
                    continue;
                }
                break;
            }
        } else {
            ++llllllllllllllllIllIIIIIllIIlIIl.timer;
        }
    }

    public AutoAnvil() {
        super(Categories.Combat, "auto-anvil", "Automatically places anvils above players to destroy helmets.");
        AutoAnvil llllllllllllllllIllIIIIIlllIIlIl;
        llllllllllllllllIllIIIIIlllIIlIl.sgGeneral = llllllllllllllllIllIIIIIlllIIlIl.settings.getDefaultGroup();
        llllllllllllllllIllIIIIIlllIIlIl.range = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllIllIIIIIlllIIlIl.priority = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        llllllllllllllllIllIIIIIlllIIlIl.height = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new IntSetting.Builder().name("height").description("The height to place anvils at.").defaultValue(2).min(0).max(5).sliderMin(0).sliderMax(5).build());
        llllllllllllllllIllIIIIIlllIIlIl.delay = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new IntSetting.Builder().name("delay").description("The delay in between anvil placements.").min(0).defaultValue(10).sliderMax(50).build());
        llllllllllllllllIllIIIIIlllIIlIl.placeButton = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("place-at-feet").description("Automatically places a button or pressure plate at the targets feet to break the anvils.").defaultValue(true).build());
        llllllllllllllllIllIIIIIlllIIlIl.multiplace = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("multiplace").description("Places multiple anvils at once..").defaultValue(true).build());
        llllllllllllllllIllIIIIIlllIIlIl.toggleOnBreak = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-break").description("Toggles when the target's helmet slot is empty.").defaultValue(false).build());
        llllllllllllllllIllIIIIIlllIIlIl.rotate = llllllllllllllllIllIIIIIlllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the position anvils/pressure plates/buttons are placed.").defaultValue(true).build());
    }

    @Override
    public String getInfoString() {
        AutoAnvil llllllllllllllllIllIIIIIllIIIIIl;
        if (llllllllllllllllIllIIIIIllIIIIIl.target != null) {
            return llllllllllllllllIllIIIIIllIIIIIl.target.method_5820();
        }
        return null;
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent llllllllllllllllIllIIIIIllIlllIl) {
        if (llllllllllllllllIllIIIIIllIlllIl.screen instanceof class_471) {
            llllllllllllllllIllIIIIIllIlllIl.cancel();
        }
    }
}

