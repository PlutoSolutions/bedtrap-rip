/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2791
 *  net.minecraft.class_2806
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2791;
import net.minecraft.class_2806;

public class VoidESP
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<ShapeMode> shapeMode;
    private final class_2338.class_2339 blockPos;
    private final Setting<Integer> holeHeight;
    private final Setting<Boolean> netherRoof;
    private static final class_2350[] SIDES = new class_2350[]{class_2350.field_11034, class_2350.field_11043, class_2350.field_11035, class_2350.field_11039};
    private final Setting<SettingColor> sideColor;
    private final Pool<Void> voidHolePool;
    private final SettingGroup sgRender;
    private final Setting<Integer> horizontalRadius;
    private final Setting<Boolean> airOnly;
    private final List<Void> voidHoles;
    private final Setting<SettingColor> lineColor;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.voidHoles.clear();
        if (PlayerUtils.getDimension() == Dimension.End) {
            return;
        }
        int n = this.mc.field_1724.method_24515().method_10263();
        int n2 = this.mc.field_1724.method_24515().method_10260();
        int n3 = this.horizontalRadius.get();
        for (int i = n - n3; i <= n + n3; ++i) {
            for (int j = n2 - n3; j <= n2 + n3; ++j) {
                this.blockPos.method_10103(i, 0, j);
                if (this.isHole(this.blockPos, false)) {
                    this.voidHoles.add(this.voidHolePool.get().set(this.blockPos.method_10103(i, 0, j), false));
                }
                if (!this.netherRoof.get().booleanValue() || PlayerUtils.getDimension() != Dimension.Nether) continue;
                this.blockPos.method_10103(i, 127, j);
                if (!this.isHole(this.blockPos, true)) continue;
                this.voidHoles.add(this.voidHolePool.get().set(this.blockPos.method_10103(i, 127, j), true));
                if (-1 != 2) continue;
                return;
            }
            if (-3 <= 0) continue;
            return;
        }
    }

    public VoidESP() {
        super(Categories.Render, "void-esp", "Renders holes in bedrock layers that lead to the void.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.airOnly = this.sgGeneral.add(new BoolSetting.Builder().name("air-only").description("Checks bedrock only for air blocks.").defaultValue(false).build());
        this.horizontalRadius = this.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(64).min(0).sliderMax(256).build());
        this.holeHeight = this.sgGeneral.add(new IntSetting.Builder().name("hole-height").description("The minimum hole height to be rendered.").defaultValue(1).min(1).sliderMax(5).build());
        this.netherRoof = this.sgGeneral.add(new BoolSetting.Builder().name("nether-roof").description("Check for holes in nether roof.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("fill-color").description("The color that fills holes in the void.").defaultValue(new SettingColor(225, 25, 25, 50)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color to draw lines of holes to the void.").defaultValue(new SettingColor(225, 25, 255)).build());
        this.blockPos = new class_2338.class_2339();
        this.voidHolePool = new Pool<Void>(this::lambda$new$0);
        this.voidHoles = new ArrayList<Void>();
    }

    static class_2350[] access$000() {
        return SIDES;
    }

    static boolean access$100(VoidESP voidESP, class_2338.class_2339 class_23392, boolean bl) {
        return voidESP.isHole(class_23392, bl);
    }

    static Setting access$400(VoidESP voidESP) {
        return voidESP.shapeMode;
    }

    private Void lambda$new$0() {
        return new Void(this, null);
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        for (Void void_ : this.voidHoles) {
            void_.render();
        }
    }

    private boolean isHole(class_2338.class_2339 class_23392, boolean bl) {
        for (int i = 0; i < this.holeHeight.get(); ++i) {
            class_23392.method_10099(bl ? 127 - i : 0);
            if (!this.isBlockWrong((class_2338)class_23392)) continue;
            return false;
        }
        return true;
    }

    private boolean isBlockWrong(class_2338 class_23382) {
        class_2791 class_27912 = this.mc.field_1687.method_8402(class_23382.method_10263() >> 4, class_23382.method_10260() >> 4, class_2806.field_12803, false);
        if (class_27912 == null) {
            return true;
        }
        class_2248 class_22482 = class_27912.method_8320(class_23382).method_26204();
        if (this.airOnly.get().booleanValue()) {
            return class_22482 != class_2246.field_10124;
        }
        return class_22482 == class_2246.field_9987;
    }

    static Setting access$300(VoidESP voidESP) {
        return voidESP.lineColor;
    }

    static Setting access$200(VoidESP voidESP) {
        return voidESP.sideColor;
    }

    private class Void {
        private int x;
        private int excludeDir;
        private int z;
        final VoidESP this$0;
        private int y;

        Void(VoidESP voidESP, 1 var2_2) {
            this(voidESP);
        }

        public void render() {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, this.x, this.y, this.z, 1.0, (Color)VoidESP.access$200(this.this$0).get(), (Color)VoidESP.access$300(this.this$0).get(), (ShapeMode)((Object)VoidESP.access$400(this.this$0).get()), this.excludeDir);
        }

        public Void set(class_2338.class_2339 class_23392, boolean bl) {
            this.x = class_23392.method_10263();
            this.y = class_23392.method_10264();
            this.z = class_23392.method_10260();
            this.excludeDir = 0;
            for (class_2350 class_23502 : VoidESP.access$000()) {
                class_23392.method_10103(this.x + class_23502.method_10148(), this.y, this.z + class_23502.method_10165());
                if (!VoidESP.access$100(this.this$0, class_23392, bl)) continue;
                this.excludeDir |= Dir.get(class_23502);
            }
            return this;
        }

        private Void(VoidESP voidESP) {
            this.this$0 = voidESP;
        }
    }
}

