/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2879
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.CityUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2879;
import net.minecraft.class_3532;

public class InstaAutoCity
extends Module {
    private final /* synthetic */ Setting<Integer> toggle;
    private /* synthetic */ int delayLeft;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean mining;
    private /* synthetic */ class_2338 mineTarget;
    private final /* synthetic */ Setting<Boolean> chatInfo;
    private final /* synthetic */ Setting<Boolean> ironPickaxe;
    private final /* synthetic */ Setting<Boolean> swing;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private /* synthetic */ class_2350 direction;
    private final /* synthetic */ Setting<Boolean> autoToggle;
    private /* synthetic */ int count;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Boolean> support;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private /* synthetic */ class_2338 targetBlockPos;

    @Override
    public String getInfoString() {
        InstaAutoCity lIlIIllIIllIlll;
        return lIlIIllIIllIlll.target != null ? lIlIIllIIllIlll.target.method_5820() : null;
    }

    public InstaAutoCity() {
        super(Categories.BedTrap, "insta-auto-city", "Automatically instamines the closest city block.");
        InstaAutoCity lIlIIllIlIllIII;
        lIlIIllIlIllIII.sgGeneral = lIlIIllIlIllIII.settings.getDefaultGroup();
        lIlIIllIlIllIII.sgRender = lIlIIllIlIllIII.settings.createGroup("Render");
        lIlIIllIlIllIII.range = lIlIIllIlIllIII.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The maximum range a city-able block will be found.").defaultValue(5.0).min(0.0).sliderMax(20.0).build());
        lIlIIllIlIllIII.support = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("support").description("If there is no block below a city block it will place one before mining.").defaultValue(true).build());
        lIlIIllIlIllIII.chatInfo = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("chat-info").description("Sends a client-side message if you city a player.").defaultValue(true).build());
        lIlIIllIlIllIII.delay = lIlIIllIlIllIII.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(1).min(0).sliderMax(10).build());
        lIlIIllIlIllIII.rotate = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        lIlIIllIlIllIII.swing = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Only server side rotations.").defaultValue(true).build());
        lIlIIllIlIllIII.ironPickaxe = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("iron-pickaxe").description("bruh.").defaultValue(true).build());
        lIlIIllIlIllIII.autoToggle = lIlIIllIlIllIII.sgGeneral.add(new BoolSetting.Builder().name("auto-toggle").description("bruh.").defaultValue(true).build());
        lIlIIllIlIllIII.toggle = lIlIIllIlIllIII.sgGeneral.add(new IntSetting.Builder().name("auto-toggle-delay").description("Amount of ticks the block has to be air to auto toggle off.").defaultValue(20).min(0).sliderMax(40).build());
        lIlIIllIlIllIII.render = lIlIIllIlIllIII.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        lIlIIllIlIllIII.shapeMode = lIlIIllIlIllIII.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIlIIllIlIllIII.sideColor = lIlIIllIlIllIII.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lIlIIllIlIllIII.lineColor = lIlIIllIlIllIII.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
    }

    @Override
    public void onDeactivate() {
        InstaAutoCity lIlIIllIlIIlIII;
        if (lIlIIllIlIIlIII.mineTarget != null) {
            lIlIIllIlIIlIII.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, lIlIIllIlIIlIII.mineTarget, lIlIIllIlIIlIII.direction));
        }
        lIlIIllIlIIlIII.mineTarget = null;
        lIlIIllIlIIlIII.target = null;
    }

    @Override
    public void onActivate() {
        InstaAutoCity lIlIIllIlIIlllI;
        lIlIIllIlIIlllI.count = 0;
        lIlIIllIlIIlllI.mining = false;
        lIlIIllIlIIlllI.target = CityUtils.getPlayerTarget(lIlIIllIlIIlllI.range.get() + 1.0);
        if (lIlIIllIlIIlllI.target != null && CityUtils.getTargetBlock(lIlIIllIlIIlllI.target) != null) {
            class_243 class_2432 = new class_243(lIlIIllIlIIlllI.mc.field_1724.method_19538().field_1352, lIlIIllIlIIlllI.mc.field_1724.method_19538().field_1351 + 1.0, lIlIIllIlIIlllI.mc.field_1724.method_19538().field_1350);
            class_243 class_2433 = new class_243((double)CityUtils.getTargetBlock(lIlIIllIlIIlllI.target).method_10263(), (double)CityUtils.getTargetBlock(lIlIIllIlIIlllI.target).method_10264(), (double)CityUtils.getTargetBlock(lIlIIllIlIIlllI.target).method_10260());
            if (class_2432.method_1022(class_2433) <= lIlIIllIlIIlllI.range.get()) {
                lIlIIllIlIIlllI.mineTarget = CityUtils.getTargetBlock(lIlIIllIlIIlllI.target);
            }
        }
        if (lIlIIllIlIIlllI.mineTarget != null && lIlIIllIlIIlllI.target != null) {
            if ((double)class_3532.method_15368((double)lIlIIllIlIIlllI.mc.field_1724.method_5649((double)lIlIIllIlIIlllI.mineTarget.method_10263(), (double)lIlIIllIlIIlllI.mineTarget.method_10264(), (double)lIlIIllIlIIlllI.mineTarget.method_10260())) > lIlIIllIlIIlllI.range.get()) {
                if (lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                    ChatUtils.info("Target block out of reach... disabling.", new Object[0]);
                }
                lIlIIllIlIIlllI.toggle();
                return;
            }
            if (lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                ChatUtils.info(String.valueOf(new StringBuilder().append("Attempting to city ").append(lIlIIllIlIIlllI.target.method_7334().getName())), new Object[0]);
            }
            lIlIIllIlIIlllI.targetBlockPos = lIlIIllIlIIlllI.target.method_24515();
            int lIlIIllIlIlIIII = InvUtils.findInHotbar(class_1802.field_8077).getSlot();
            if (lIlIIllIlIIlllI.ironPickaxe.get().booleanValue() && lIlIIllIlIlIIII == -1) {
                lIlIIllIlIlIIII = InvUtils.findInHotbar(class_1802.field_8403).getSlot();
            }
            if (lIlIIllIlIlIIII == -1) {
                lIlIIllIlIlIIII = InvUtils.findInHotbar(class_1802.field_22024).getSlot();
            }
            if (lIlIIllIlIlIIII == -1) {
                lIlIIllIlIlIIII = InvUtils.findInHotbar(class_1802.field_8377).getSlot();
            }
            if (lIlIIllIlIIlllI.mc.field_1724.field_7503.field_7477) {
                lIlIIllIlIlIIII = lIlIIllIlIIlllI.mc.field_1724.field_7514.field_7545;
            }
            if (lIlIIllIlIlIIII == -1) {
                if (lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                    ChatUtils.info("No pick found... disabling.", new Object[0]);
                }
                lIlIIllIlIIlllI.toggle();
                return;
            }
            if (lIlIIllIlIIlllI.support.get().booleanValue()) {
                int lIlIIllIlIlIIlI = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8281}).slot;
                class_2338 lIlIIllIlIlIIIl = lIlIIllIlIIlllI.mineTarget.method_10087(1);
                if (!BlockUtils.canPlace(lIlIIllIlIlIIIl) && lIlIIllIlIIlllI.mc.field_1687.method_8320(lIlIIllIlIlIIIl).method_26204() != class_2246.field_10540 && lIlIIllIlIIlllI.mc.field_1687.method_8320(lIlIIllIlIlIIIl).method_26204() != class_2246.field_9987 && lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                    ChatUtils.info("Couldn't place support block, mining anyway.", new Object[0]);
                } else if (lIlIIllIlIlIIlI == -1) {
                    if (lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                        ChatUtils.info("No obsidian found for support, mining anyway.", new Object[0]);
                    }
                } else {
                    minegame159.meteorclient.utils.bedtrap.BlockUtils.place(lIlIIllIlIlIIIl, class_1268.field_5808, lIlIIllIlIlIIlI, lIlIIllIlIIlllI.rotate.get(), 0, true);
                }
            }
            lIlIIllIlIIlllI.mc.field_1724.field_7514.field_7545 = lIlIIllIlIlIIII;
        } else {
            lIlIIllIlIIlllI.mineTarget = null;
            lIlIIllIlIIlllI.target = null;
            if (lIlIIllIlIIlllI.chatInfo.get().booleanValue()) {
                ChatUtils.info("No target block found... disabling.", new Object[0]);
                lIlIIllIlIIlllI.toggle();
            }
        }
    }

    private void doMine() {
        InstaAutoCity lIlIIllIlIIIIlI;
        --lIlIIllIlIIIIlI.delayLeft;
        if (!lIlIIllIlIIIIlI.mining) {
            if (lIlIIllIlIIIIlI.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(lIlIIllIlIIIIlI.mineTarget), Rotations.getPitch(lIlIIllIlIIIIlI.mineTarget));
            }
            if (lIlIIllIlIIIIlI.swing.get().booleanValue()) {
                lIlIIllIlIIIIlI.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            } else {
                lIlIIllIlIIIIlI.mc.field_1724.method_6104(class_1268.field_5808);
            }
            lIlIIllIlIIIIlI.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, lIlIIllIlIIIIlI.mineTarget, lIlIIllIlIIIIlI.direction));
            lIlIIllIlIIIIlI.mining = true;
        }
        if (lIlIIllIlIIIIlI.delayLeft <= 0) {
            if (lIlIIllIlIIIIlI.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(lIlIIllIlIIIIlI.mineTarget), Rotations.getPitch(lIlIIllIlIIIIlI.mineTarget));
            }
            lIlIIllIlIIIIlI.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            lIlIIllIlIIIIlI.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, lIlIIllIlIIIIlI.mineTarget, lIlIIllIlIIIIlI.direction));
            lIlIIllIlIIIIlI.delayLeft = lIlIIllIlIIIIlI.delay.get();
        }
    }

    public class_2338 getMineTarget() {
        InstaAutoCity lIlIIllIIlllIll;
        return lIlIIllIIlllIll.mineTarget != null ? lIlIIllIIlllIll.mineTarget : null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIlIIllIlIIIlIl) {
        InstaAutoCity lIlIIllIlIIIlII;
        assert (lIlIIllIlIIIlII.mc.field_1687 != null);
        if (lIlIIllIlIIIlII.autoToggle.get().booleanValue()) {
            lIlIIllIlIIIlII.direction = minegame159.meteorclient.utils.bedtrap.BlockUtils.rayTraceCheck(lIlIIllIlIIIlII.mineTarget, true);
            if (!lIlIIllIlIIIlII.mc.field_1687.method_22347(lIlIIllIlIIIlII.mineTarget)) {
                lIlIIllIlIIIlII.doMine();
            } else {
                ++lIlIIllIlIIIlII.count;
            }
            if (lIlIIllIlIIIlII.target == null || !lIlIIllIlIIIlII.target.method_5805() || lIlIIllIlIIIlII.count >= lIlIIllIlIIIlII.toggle.get() || !lIlIIllIlIIIlII.mineTarget.method_19769((class_2374)lIlIIllIlIIIlII.mc.field_1724.method_19538(), lIlIIllIlIIIlII.range.get().doubleValue()) || lIlIIllIlIIIlII.target.method_24515() != lIlIIllIlIIIlII.targetBlockPos) {
                lIlIIllIlIIIlII.toggle();
            }
        } else {
            lIlIIllIlIIIlII.direction = minegame159.meteorclient.utils.bedtrap.BlockUtils.rayTraceCheck(lIlIIllIlIIIlII.mineTarget, true);
            if (!lIlIIllIlIIIlII.mc.field_1687.method_22347(lIlIIllIlIIIlII.mineTarget)) {
                lIlIIllIlIIIlII.doMine();
            }
            if (lIlIIllIlIIIlII.target == null || !lIlIIllIlIIIlII.target.method_5805() || !lIlIIllIlIIIlII.mineTarget.method_19769((class_2374)lIlIIllIlIIIlII.mc.field_1724.method_19538(), lIlIIllIlIIIlII.range.get().doubleValue()) || lIlIIllIlIIIlII.target.method_24515() != lIlIIllIlIIIlII.targetBlockPos) {
                lIlIIllIlIIIlII.toggle();
            }
        }
    }

    @EventHandler
    private void onRender(RenderEvent lIlIIllIIlllllI) {
        InstaAutoCity lIlIIllIIllllIl;
        if (lIlIIllIIllllIl.render.get().booleanValue()) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lIlIIllIIllllIl.mineTarget, lIlIIllIIllllIl.sideColor.get(), lIlIIllIIllllIl.lineColor.get(), lIlIIllIIllllIl.shapeMode.get(), 0);
        }
    }
}

