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
    private final Setting<Integer> toggle;
    private int delayLeft;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgRender;
    private final Setting<Double> range;
    private final Setting<Integer> delay;
    private final SettingGroup sgGeneral;
    private boolean mining;
    private class_2338 mineTarget;
    private final Setting<Boolean> chatInfo;
    private final Setting<Boolean> ironPickaxe;
    private final Setting<Boolean> swing;
    private class_1657 target;
    static final boolean $assertionsDisabled = !InstaAutoCity.class.desiredAssertionStatus();
    private final Setting<SettingColor> lineColor;
    private class_2350 direction;
    private final Setting<Boolean> autoToggle;
    private int count;
    private final Setting<Boolean> render;
    private final Setting<SettingColor> sideColor;
    private final Setting<Boolean> support;
    private final Setting<ShapeMode> shapeMode;
    private class_2338 targetBlockPos;

    @Override
    public String getInfoString() {
        return this.target != null ? this.target.method_5820() : null;
    }

    public InstaAutoCity() {
        super(Categories.BedTrap, "insta-auto-city", "Automatically instamines the closest city block.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The maximum range a city-able block will be found.").defaultValue(5.0).min(0.0).sliderMax(20.0).build());
        this.support = this.sgGeneral.add(new BoolSetting.Builder().name("support").description("If there is no block below a city block it will place one before mining.").defaultValue(true).build());
        this.chatInfo = this.sgGeneral.add(new BoolSetting.Builder().name("chat-info").description("Sends a client-side message if you city a player.").defaultValue(true).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(1).min(0).sliderMax(10).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        this.swing = this.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Only server side rotations.").defaultValue(true).build());
        this.ironPickaxe = this.sgGeneral.add(new BoolSetting.Builder().name("iron-pickaxe").description("bruh.").defaultValue(true).build());
        this.autoToggle = this.sgGeneral.add(new BoolSetting.Builder().name("auto-toggle").description("bruh.").defaultValue(true).build());
        this.toggle = this.sgGeneral.add(new IntSetting.Builder().name("auto-toggle-delay").description("Amount of ticks the block has to be air to auto toggle off.").defaultValue(20).min(0).sliderMax(40).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
    }

    @Override
    public void onDeactivate() {
        if (this.mineTarget != null) {
            this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, this.mineTarget, this.direction));
        }
        this.mineTarget = null;
        this.target = null;
    }

    @Override
    public void onActivate() {
        this.count = 0;
        this.mining = false;
        this.target = CityUtils.getPlayerTarget(this.range.get() + 1.0);
        if (this.target != null && CityUtils.getTargetBlock(this.target) != null) {
            class_243 class_2432 = new class_243(this.mc.field_1724.method_19538().field_1352, this.mc.field_1724.method_19538().field_1351 + 1.0, this.mc.field_1724.method_19538().field_1350);
            class_243 class_2433 = new class_243((double)CityUtils.getTargetBlock(this.target).method_10263(), (double)CityUtils.getTargetBlock(this.target).method_10264(), (double)CityUtils.getTargetBlock(this.target).method_10260());
            if (class_2432.method_1022(class_2433) <= this.range.get()) {
                this.mineTarget = CityUtils.getTargetBlock(this.target);
            }
        }
        if (this.mineTarget != null && this.target != null) {
            if ((double)class_3532.method_15368((double)this.mc.field_1724.method_5649((double)this.mineTarget.method_10263(), (double)this.mineTarget.method_10264(), (double)this.mineTarget.method_10260())) > this.range.get()) {
                if (this.chatInfo.get().booleanValue()) {
                    ChatUtils.info("Target block out of reach... disabling.", new Object[0]);
                }
                this.toggle();
                return;
            }
            if (this.chatInfo.get().booleanValue()) {
                ChatUtils.info(String.valueOf(new StringBuilder().append("Attempting to city ").append(this.target.method_7334().getName())), new Object[0]);
            }
            this.targetBlockPos = this.target.method_24515();
            int n = InvUtils.findInHotbar(class_1802.field_8077).getSlot();
            if (this.ironPickaxe.get().booleanValue() && n == -1) {
                n = InvUtils.findInHotbar(class_1802.field_8403).getSlot();
            }
            if (n == -1) {
                n = InvUtils.findInHotbar(class_1802.field_22024).getSlot();
            }
            if (n == -1) {
                n = InvUtils.findInHotbar(class_1802.field_8377).getSlot();
            }
            if (this.mc.field_1724.field_7503.field_7477) {
                n = this.mc.field_1724.field_7514.field_7545;
            }
            if (n == -1) {
                if (this.chatInfo.get().booleanValue()) {
                    ChatUtils.info("No pick found... disabling.", new Object[0]);
                }
                this.toggle();
                return;
            }
            if (this.support.get().booleanValue()) {
                int n2 = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8281}).slot;
                class_2338 class_23382 = this.mineTarget.method_10087(1);
                if (!BlockUtils.canPlace(class_23382) && this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_10540 && this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_9987 && this.chatInfo.get().booleanValue()) {
                    ChatUtils.info("Couldn't place support block, mining anyway.", new Object[0]);
                } else if (n2 == -1) {
                    if (this.chatInfo.get().booleanValue()) {
                        ChatUtils.info("No obsidian found for support, mining anyway.", new Object[0]);
                    }
                } else {
                    minegame159.meteorclient.utils.bedtrap.BlockUtils.place(class_23382, class_1268.field_5808, n2, this.rotate.get(), 0, true);
                }
            }
            this.mc.field_1724.field_7514.field_7545 = n;
        } else {
            this.mineTarget = null;
            this.target = null;
            if (this.chatInfo.get().booleanValue()) {
                ChatUtils.info("No target block found... disabling.", new Object[0]);
                this.toggle();
            }
        }
    }

    private void doMine() {
        --this.delayLeft;
        if (!this.mining) {
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(this.mineTarget), Rotations.getPitch(this.mineTarget));
            }
            if (this.swing.get().booleanValue()) {
                this.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            } else {
                this.mc.field_1724.method_6104(class_1268.field_5808);
            }
            this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, this.mineTarget, this.direction));
            this.mining = true;
        }
        if (this.delayLeft <= 0) {
            if (this.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(this.mineTarget), Rotations.getPitch(this.mineTarget));
            }
            this.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, this.mineTarget, this.direction));
            this.delayLeft = this.delay.get();
        }
    }

    public class_2338 getMineTarget() {
        return this.mineTarget != null ? this.mineTarget : null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (this.autoToggle.get().booleanValue()) {
            this.direction = minegame159.meteorclient.utils.bedtrap.BlockUtils.rayTraceCheck(this.mineTarget, true);
            if (!this.mc.field_1687.method_22347(this.mineTarget)) {
                this.doMine();
            } else {
                ++this.count;
            }
            if (this.target == null || !this.target.method_5805() || this.count >= this.toggle.get() || !this.mineTarget.method_19769((class_2374)this.mc.field_1724.method_19538(), this.range.get().doubleValue()) || this.target.method_24515() != this.targetBlockPos) {
                this.toggle();
            }
        } else {
            this.direction = minegame159.meteorclient.utils.bedtrap.BlockUtils.rayTraceCheck(this.mineTarget, true);
            if (!this.mc.field_1687.method_22347(this.mineTarget)) {
                this.doMine();
            }
            if (this.target == null || !this.target.method_5805() || !this.mineTarget.method_19769((class_2374)this.mc.field_1724.method_19538(), this.range.get().doubleValue()) || this.target.method_24515() != this.targetBlockPos) {
                this.toggle();
            }
        }
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.render.get().booleanValue()) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, this.mineTarget, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
        }
    }
}

