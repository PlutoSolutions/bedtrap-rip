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
    private /* synthetic */ int holeX;
    public /* synthetic */ double deltaX;
    private final /* synthetic */ Setting<Integer> maxHeight;
    private final /* synthetic */ Setting<Boolean> pull;
    private /* synthetic */ int holeZ;
    private final /* synthetic */ Setting<Boolean> cancelMove;
    public /* synthetic */ boolean cancelJump;
    public /* synthetic */ double deltaZ;
    public /* synthetic */ boolean controlMovement;
    private /* synthetic */ boolean foundHole;
    private final /* synthetic */ Setting<Double> pullSpeed;
    private /* synthetic */ boolean wasInHole;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> minPitch;

    @EventHandler
    private void onPostTick(TickEvent.Post lllllllllllllllllIlIlIlIIIllIIIl) {
        int lllllllllllllllllIlIlIlIIIlIlllI;
        int lllllllllllllllllIlIlIlIIIlIllll;
        Anchor lllllllllllllllllIlIlIlIIIllIIlI;
        lllllllllllllllllIlIlIlIIIllIIlI.controlMovement = false;
        int lllllllllllllllllIlIlIlIIIllIIII = class_3532.method_15357((double)lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_23317());
        if (lllllllllllllllllIlIlIlIIIllIIlI.isHole(lllllllllllllllllIlIlIlIIIllIIII, lllllllllllllllllIlIlIlIIIlIllll = class_3532.method_15357((double)lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_23318()), lllllllllllllllllIlIlIlIIIlIlllI = class_3532.method_15357((double)lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_23321()))) {
            lllllllllllllllllIlIlIlIIIllIIlI.wasInHole = true;
            lllllllllllllllllIlIlIlIIIllIIlI.holeX = lllllllllllllllllIlIlIlIIIllIIII;
            lllllllllllllllllIlIlIlIIIllIIlI.holeZ = lllllllllllllllllIlIlIlIIIlIlllI;
            return;
        }
        if (lllllllllllllllllIlIlIlIIIllIIlI.wasInHole && lllllllllllllllllIlIlIlIIIllIIlI.holeX == lllllllllllllllllIlIlIlIIIllIIII && lllllllllllllllllIlIlIlIIIllIIlI.holeZ == lllllllllllllllllIlIlIlIIIlIlllI) {
            return;
        }
        if (lllllllllllllllllIlIlIlIIIllIIlI.wasInHole) {
            lllllllllllllllllIlIlIlIIIllIIlI.wasInHole = false;
        }
        if (lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.field_5965 < (float)lllllllllllllllllIlIlIlIIIllIIlI.minPitch.get().intValue()) {
            return;
        }
        lllllllllllllllllIlIlIlIIIllIIlI.foundHole = false;
        double lllllllllllllllllIlIlIlIIIlIllIl = 0.0;
        double lllllllllllllllllIlIlIlIIIlIllII = 0.0;
        for (int lllllllllllllllllIlIlIlIIIllIIll = 0; lllllllllllllllllIlIlIlIIIllIIll < lllllllllllllllllIlIlIlIIIllIIlI.maxHeight.get() && --lllllllllllllllllIlIlIlIIIlIllll > 0 && lllllllllllllllllIlIlIlIIIllIIlI.isAir(lllllllllllllllllIlIlIlIIIllIIII, lllllllllllllllllIlIlIlIIIlIllll, lllllllllllllllllIlIlIlIIIlIlllI); ++lllllllllllllllllIlIlIlIIIllIIll) {
            if (!lllllllllllllllllIlIlIlIIIllIIlI.isHole(lllllllllllllllllIlIlIlIIIllIIII, lllllllllllllllllIlIlIlIIIlIllll, lllllllllllllllllIlIlIlIIIlIlllI)) continue;
            lllllllllllllllllIlIlIlIIIllIIlI.foundHole = true;
            lllllllllllllllllIlIlIlIIIlIllIl = (double)lllllllllllllllllIlIlIlIIIllIIII + 0.5;
            lllllllllllllllllIlIlIlIIIlIllII = (double)lllllllllllllllllIlIlIlIIIlIlllI + 0.5;
            break;
        }
        if (lllllllllllllllllIlIlIlIIIllIIlI.foundHole) {
            lllllllllllllllllIlIlIlIIIllIIlI.controlMovement = true;
            lllllllllllllllllIlIlIlIIIllIIlI.deltaX = Utils.clamp(lllllllllllllllllIlIlIlIIIlIllIl - lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_23317(), -0.05, 0.05);
            lllllllllllllllllIlIlIlIIIllIIlI.deltaZ = Utils.clamp(lllllllllllllllllIlIlIlIIIlIllII - lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_23321(), -0.05, 0.05);
            ((IVec3d)lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_18798()).set(lllllllllllllllllIlIlIlIIIllIIlI.deltaX, lllllllllllllllllIlIlIlIIIllIIlI.mc.field_1724.method_18798().field_1351 - (lllllllllllllllllIlIlIlIIIllIIlI.pull.get() != false ? lllllllllllllllllIlIlIlIIIllIIlI.pullSpeed.get() : 0.0), lllllllllllllllllIlIlIlIIIllIIlI.deltaZ);
        }
    }

    private boolean isAir(int lllllllllllllllllIlIlIlIIIIIIlII, int lllllllllllllllllIlIlIlIIIIIIIll, int lllllllllllllllllIlIlIIllllllllI) {
        Anchor lllllllllllllllllIlIlIlIIIIIIlIl;
        lllllllllllllllllIlIlIlIIIIIIlIl.blockPos.method_10103(lllllllllllllllllIlIlIlIIIIIIlII, lllllllllllllllllIlIlIlIIIIIIIll, lllllllllllllllllIlIlIIllllllllI);
        return !((AbstractBlockAccessor)lllllllllllllllllIlIlIlIIIIIIlIl.mc.field_1687.method_8320((class_2338)lllllllllllllllllIlIlIlIIIIIIlIl.blockPos).method_26204()).isCollidable();
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre lllllllllllllllllIlIlIlIIIllllII) {
        Anchor lllllllllllllllllIlIlIlIIIlllIll;
        lllllllllllllllllIlIlIlIIIlllIll.cancelJump = lllllllllllllllllIlIlIlIIIlllIll.foundHole && lllllllllllllllllIlIlIlIIIlllIll.cancelMove.get() != false && lllllllllllllllllIlIlIlIIIlllIll.mc.field_1724.field_5965 >= (float)lllllllllllllllllIlIlIlIIIlllIll.minPitch.get().intValue();
    }

    public Anchor() {
        super(Categories.Movement, "anchor", "Helps you get into holes by stopping your movement completely over a hole.");
        Anchor lllllllllllllllllIlIlIlIIlIIIIll;
        lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral = lllllllllllllllllIlIlIlIIlIIIIll.settings.getDefaultGroup();
        lllllllllllllllllIlIlIlIIlIIIIll.maxHeight = lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral.add(new IntSetting.Builder().name("max-height").description("The maximum height Anchor will work at.").defaultValue(10).min(0).max(255).sliderMax(20).build());
        lllllllllllllllllIlIlIlIIlIIIIll.minPitch = lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral.add(new IntSetting.Builder().name("min-pitch").description("The minimum pitch at which anchor will work.").defaultValue(-90).min(-90).max(90).sliderMin(-90).sliderMax(90).build());
        lllllllllllllllllIlIlIlIIlIIIIll.cancelMove = lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral.add(new BoolSetting.Builder().name("cancel-jump-in-hole").description("Prevents you from jumping when Anchor is active and Min Pitch is met.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIIlIIIIll.pull = lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral.add(new BoolSetting.Builder().name("pull").description("The pull strength of Anchor.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIIlIIIIll.pullSpeed = lllllllllllllllllIlIlIlIIlIIIIll.sgGeneral.add(new DoubleSetting.Builder().name("pull-speed").description("How fast to pull towards the hole in blocks per second.").defaultValue(0.3).min(0.0).sliderMax(5.0).build());
        lllllllllllllllllIlIlIlIIlIIIIll.blockPos = new class_2338.class_2339();
    }

    @Override
    public void onActivate() {
        lllllllllllllllllIlIlIlIIIllllll.wasInHole = false;
        lllllllllllllllllIlIlIlIIIllllll.holeZ = 0;
        lllllllllllllllllIlIlIlIIIllllll.holeX = 0;
    }

    private boolean isHoleBlock(int lllllllllllllllllIlIlIlIIIIlIIlI, int lllllllllllllllllIlIlIlIIIIIllII, int lllllllllllllllllIlIlIlIIIIlIIII) {
        Anchor lllllllllllllllllIlIlIlIIIIIlllI;
        lllllllllllllllllIlIlIlIIIIIlllI.blockPos.method_10103(lllllllllllllllllIlIlIlIIIIlIIlI, lllllllllllllllllIlIlIlIIIIIllII, lllllllllllllllllIlIlIlIIIIlIIII);
        class_2248 lllllllllllllllllIlIlIlIIIIIllll = lllllllllllllllllIlIlIlIIIIIlllI.mc.field_1687.method_8320((class_2338)lllllllllllllllllIlIlIlIIIIIlllI.blockPos).method_26204();
        return lllllllllllllllllIlIlIlIIIIIllll == class_2246.field_9987 || lllllllllllllllllIlIlIlIIIIIllll == class_2246.field_10540 || lllllllllllllllllIlIlIlIIIIIllll == class_2246.field_22423;
    }

    private boolean isHole(int lllllllllllllllllIlIlIlIIIIllIll, int lllllllllllllllllIlIlIlIIIIllllI, int lllllllllllllllllIlIlIlIIIIlllIl) {
        Anchor lllllllllllllllllIlIlIlIIIlIIIII;
        return lllllllllllllllllIlIlIlIIIlIIIII.isHoleBlock(lllllllllllllllllIlIlIlIIIIllIll, lllllllllllllllllIlIlIlIIIIllllI - 1, lllllllllllllllllIlIlIlIIIIlllIl) && lllllllllllllllllIlIlIlIIIlIIIII.isHoleBlock(lllllllllllllllllIlIlIlIIIIllIll + 1, lllllllllllllllllIlIlIlIIIIllllI, lllllllllllllllllIlIlIlIIIIlllIl) && lllllllllllllllllIlIlIlIIIlIIIII.isHoleBlock(lllllllllllllllllIlIlIlIIIIllIll - 1, lllllllllllllllllIlIlIlIIIIllllI, lllllllllllllllllIlIlIlIIIIlllIl) && lllllllllllllllllIlIlIlIIIlIIIII.isHoleBlock(lllllllllllllllllIlIlIlIIIIllIll, lllllllllllllllllIlIlIlIIIIllllI, lllllllllllllllllIlIlIlIIIIlllIl + 1) && lllllllllllllllllIlIlIlIIIlIIIII.isHoleBlock(lllllllllllllllllIlIlIlIIIIllIll, lllllllllllllllllIlIlIlIIIIllllI, lllllllllllllllllIlIlIlIIIIlllIl - 1);
    }
}

