/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.AbstractBlockAccessor;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_3532;

public class Anchor
extends Module {
    private int holeX;
    public double deltaX;
    private final Setting<Integer> maxHeight;
    private final Setting<Boolean> pull;
    private int holeZ;
    private final Setting<Boolean> cancelMove;
    public boolean cancelJump;
    public double deltaZ;
    public boolean controlMovement;
    private boolean foundHole;
    private final Setting<Double> pullSpeed;
    private boolean wasInHole;
    private final class_2338.class_2339 blockPos;
    private final SettingGroup sgGeneral;
    private final Setting<Integer> minPitch;

    @EventHandler
    private void onPostTick(TickEvent.Post post) {
        int n;
        int n2;
        this.controlMovement = false;
        int n3 = class_3532.method_15357((double)this.mc.field_1724.method_23317());
        if (this.isHole(n3, n2 = class_3532.method_15357((double)this.mc.field_1724.method_23318()), n = class_3532.method_15357((double)this.mc.field_1724.method_23321()))) {
            this.wasInHole = true;
            this.holeX = n3;
            this.holeZ = n;
            return;
        }
        if (this.wasInHole && this.holeX == n3 && this.holeZ == n) {
            return;
        }
        if (this.wasInHole) {
            this.wasInHole = false;
        }
        if (this.mc.field_1724.field_5965 < (float)this.minPitch.get().intValue()) {
            return;
        }
        this.foundHole = false;
        double d = 0.0;
        double d2 = 0.0;
        for (int i = 0; i < this.maxHeight.get() && --n2 > 0 && this.isAir(n3, n2, n); ++i) {
            if (!this.isHole(n3, n2, n)) continue;
            this.foundHole = true;
            d = (double)n3 + 0.5;
            d2 = (double)n + 0.5;
            break;
        }
        if (this.foundHole) {
            this.controlMovement = true;
            this.deltaX = Utils.clamp(d - this.mc.field_1724.method_23317(), -0.05, 0.05);
            this.deltaZ = Utils.clamp(d2 - this.mc.field_1724.method_23321(), -0.05, 0.05);
            ((IVec3d)this.mc.field_1724.method_18798()).set(this.deltaX, this.mc.field_1724.method_18798().field_1351 - (this.pull.get() != false ? this.pullSpeed.get() : 0.0), this.deltaZ);
        }
    }

    private boolean isAir(int n, int n2, int n3) {
        this.blockPos.method_10103(n, n2, n3);
        return !((AbstractBlockAccessor)this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26204()).isCollidable();
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre pre) {
        this.cancelJump = this.foundHole && this.cancelMove.get() != false && this.mc.field_1724.field_5965 >= (float)this.minPitch.get().intValue();
    }

    public Anchor() {
        super(Categories.Movement, "anchor", "Helps you get into holes by stopping your movement completely over a hole.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.maxHeight = this.sgGeneral.add(new IntSetting.Builder().name("max-height").description("The maximum height Anchor will work at.").defaultValue(10).min(0).max(255).sliderMax(20).build());
        this.minPitch = this.sgGeneral.add(new IntSetting.Builder().name("min-pitch").description("The minimum pitch at which anchor will work.").defaultValue(-90).min(-90).max(90).sliderMin(-90).sliderMax(90).build());
        this.cancelMove = this.sgGeneral.add(new BoolSetting.Builder().name("cancel-jump-in-hole").description("Prevents you from jumping when Anchor is active and Min Pitch is met.").defaultValue(false).build());
        this.pull = this.sgGeneral.add(new BoolSetting.Builder().name("pull").description("The pull strength of Anchor.").defaultValue(false).build());
        this.pullSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("pull-speed").description("How fast to pull towards the hole in blocks per second.").defaultValue(0.3).min(0.0).sliderMax(5.0).build());
        this.blockPos = new class_2338.class_2339();
    }

    @Override
    public void onActivate() {
        this.wasInHole = false;
        this.holeZ = 0;
        this.holeX = 0;
    }

    private boolean isHoleBlock(int n, int n2, int n3) {
        this.blockPos.method_10103(n, n2, n3);
        class_2248 class_22482 = this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26204();
        return class_22482 == class_2246.field_9987 || class_22482 == class_2246.field_10540 || class_22482 == class_2246.field_22423;
    }

    private boolean isHole(int n, int n2, int n3) {
        return this.isHoleBlock(n, n2 - 1, n3) && this.isHoleBlock(n + 1, n2, n3) && this.isHoleBlock(n - 1, n2, n3) && this.isHoleBlock(n, n2, n3 + 1) && this.isHoleBlock(n, n2, n3 - 1);
    }
}

