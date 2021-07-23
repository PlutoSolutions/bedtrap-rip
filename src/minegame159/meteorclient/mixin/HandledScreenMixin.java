/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.InventoryTweaks;
import minegame159.meteorclient.systems.modules.render.BetterTooltips;
import minegame159.meteorclient.systems.modules.render.ItemHighlight;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.EChestMemory;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1262;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1806;
import net.minecraft.class_1937;
import net.minecraft.class_22;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_308;
import net.minecraft.class_332;
import net.minecraft.class_3872;
import net.minecraft.class_3936;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_465;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_465.class})
public abstract class HandledScreenMixin<T extends class_1703>
extends class_437
implements class_3936<T> {
    @Shadow
    @javax.annotation.Nullable
    protected class_1735 field_2787;
    @Shadow
    protected int field_2776;
    @Shadow
    protected int field_2800;
    @Shadow
    private boolean field_2783;
    private static final class_2960 TEXTURE_CONTAINER_BACKGROUND = new class_2960("meteor-client", "textures/container.png");
    private static final class_2960 TEXTURE_MAP_BACKGROUND = new class_2960("textures/map/map_background.png");
    private static final class_1799[] ITEMS = new class_1799[27];

    @Shadow
    @Nullable
    protected abstract class_1735 method_2386(double var1, double var3);

    @Shadow
    public abstract T method_17577();

    @Shadow
    protected abstract void method_2383(class_1735 var1, int var2, int var3, class_1713 var4);

    public HandledScreenMixin(class_2561 class_25612) {
        super(class_25612);
    }

    @Inject(method={"mouseDragged"}, at={@At(value="TAIL")})
    private void onMouseDragged(double d, double d2, int n, double d3, double d4, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (n != 0 || this.field_2783 || !Modules.get().get(InventoryTweaks.class).mouseDragItemMove()) {
            return;
        }
        class_1735 class_17352 = this.method_2386(d, d2);
        if (class_17352 != null && class_17352.method_7681() && HandledScreenMixin.method_25442()) {
            this.method_2383(class_17352, class_17352.field_7874, n, class_1713.field_7794);
        }
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void mouseClicked(double d, double d2, int n, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        class_1799 class_17992;
        BetterTooltips betterTooltips = Modules.get().get(BetterTooltips.class);
        if (n == 2 && this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960() && betterTooltips.middleClickOpen() && (Utils.hasItems(class_17992 = this.field_2787.method_7677()) || class_17992.method_7909() == class_1802.field_8466)) {
            callbackInfoReturnable.setReturnValue((Object)Utils.openContainer(this.field_2787.method_7677(), ITEMS, false));
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_4587 class_45872, int n, int n2, float f, CallbackInfo callbackInfo) {
        if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
            BetterTooltips betterTooltips = Modules.get().get(BetterTooltips.class);
            if (Utils.hasItems(this.field_2787.method_7677()) && betterTooltips.previewShulkers()) {
                class_2487 class_24872 = this.field_2787.method_7677().method_7941("BlockEntityTag");
                class_2371 class_23712 = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
                class_1262.method_5429((class_2487)class_24872, (class_2371)class_23712);
                this.draw(class_45872, (class_2371<class_1799>)class_23712, n, n2, Utils.getShulkerColor(this.field_2787.method_7677()));
            } else if (this.field_2787.method_7677().method_7909() == class_1802.field_8466 && betterTooltips.previewEChest()) {
                this.draw(class_45872, EChestMemory.ITEMS, n, n2, BetterTooltips.ECHEST_COLOR);
            } else if (this.field_2787.method_7677().method_7909() == class_1802.field_8204 && betterTooltips.previewMaps()) {
                this.drawMapPreview(class_45872, this.field_2787.method_7677(), n, n2, (int)(betterTooltips.mapsScale.get() * 100.0));
            } else if ((this.field_2787.method_7677().method_7909() == class_1802.field_8674 || this.field_2787.method_7677().method_7909() == class_1802.field_8360) && betterTooltips.previewBooks()) {
                this.drawBookPreview(class_45872, this.field_2787.method_7677(), n, n2);
            }
        }
    }

    @Inject(method={"drawMouseoverTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawMouseoverTooltip(class_4587 class_45872, int n, int n2, CallbackInfo callbackInfo) {
        if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
            BetterTooltips betterTooltips = Modules.get().get(BetterTooltips.class);
            if (this.field_2787.method_7677().method_7909() == class_1802.field_8204 && betterTooltips.previewMaps()) {
                callbackInfo.cancel();
            } else if (betterTooltips.previewBooks() && BetterTooltips.willRenderBookPreview(this.field_2787.method_7677()) && !betterTooltips.showVanilla.get().booleanValue()) {
                callbackInfo.cancel();
            } else if ((Utils.hasItems(this.field_2787.method_7677()) && betterTooltips.previewShulkers() || this.field_2787.method_7677().method_7909() == class_1802.field_8466 && betterTooltips.previewEChest()) && !betterTooltips.showVanilla.get().booleanValue()) {
                callbackInfo.cancel();
            }
        }
    }

    private void draw(class_4587 class_45872, class_2371<class_1799> class_23712, int n, int n2, Color color) {
        RenderSystem.disableLighting();
        RenderSystem.disableDepthTest();
        GL11.glClear((int)256);
        this.drawBackground(class_45872, n += 8, n2 -= 12, color);
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        class_308.method_22890();
        int n3 = 0;
        int n4 = 0;
        for (class_1799 class_17992 : class_23712) {
            RenderUtils.drawItem(class_17992, n + 8 + n4 * 18, n2 + 7 + n3 * 18, true);
            if (++n4 < 9) continue;
            n4 = 0;
            ++n3;
        }
        class_308.method_1450();
        RenderSystem.enableDepthTest();
    }

    private void drawBackground(class_4587 class_45872, int n, int n2, Color color) {
        RenderSystem.color4f((float)((float)color.r / 255.0f), (float)((float)color.g / 255.0f), (float)((float)color.b / 255.0f), (float)((float)color.a / 255.0f));
        this.field_22787.method_1531().method_22813(TEXTURE_CONTAINER_BACKGROUND);
        class_332.method_25291((class_4587)class_45872, (int)n, (int)n2, (int)0, (float)0.0f, (float)0.0f, (int)176, (int)67, (int)67, (int)176);
    }

    private void drawMapPreview(class_4587 class_45872, class_1799 class_17992, int n, int n2, int n3) {
        GL11.glEnable((int)3042);
        RenderSystem.pushMatrix();
        RenderSystem.disableLighting();
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int n4 = n2 - 12;
        int n5 = n4 + n3;
        int n6 = n + 8;
        int n7 = n6 + n3;
        int n8 = 300;
        this.field_22787.method_1531().method_22813(TEXTURE_MAP_BACKGROUND);
        class_289 class_2892 = class_289.method_1348();
        class_287 class_2872 = class_2892.method_1349();
        class_2872.method_1328(7, class_290.field_1585);
        class_2872.method_22912((double)n6, (double)n5, (double)n8).method_22913(0.0f, 1.0f).method_1344();
        class_2872.method_22912((double)n7, (double)n5, (double)n8).method_22913(1.0f, 1.0f).method_1344();
        class_2872.method_22912((double)n7, (double)n4, (double)n8).method_22913(1.0f, 0.0f).method_1344();
        class_2872.method_22912((double)n6, (double)n4, (double)n8).method_22913(0.0f, 0.0f).method_1344();
        class_2892.method_1350();
        class_22 class_222 = class_1806.method_8001((class_1799)class_17992, (class_1937)this.field_22787.field_1687);
        if (class_222 != null) {
            class_222.method_101((class_1657)this.field_22787.field_1724);
            n8 = 310;
            double d = (double)(n3 - 16) / 128.0;
            RenderSystem.translatef((float)(n6 += 8), (float)(n4 += 8), (float)n8);
            RenderSystem.scaled((double)d, (double)d, (double)0.0);
            class_4597.class_4598 class_45982 = this.field_22787.method_22940().method_23000();
            this.field_22787.field_1773.method_3194().method_1773(class_45872, (class_4597)class_45982, class_222, false, 0xF000F0);
        }
        RenderSystem.enableLighting();
        RenderSystem.popMatrix();
    }

    private void drawBookPreview(class_4587 class_45872, class_1799 class_17992, int n, int n2) {
        float f = 0.7f * Modules.get().get(BetterTooltips.class).booksScale.get().floatValue();
        class_2487 class_24872 = class_17992.method_7969();
        if (class_24872 == null) {
            return;
        }
        class_2499 class_24992 = class_24872.method_10554("pages", 8);
        if (class_24992.size() < 1) {
            return;
        }
        Object object = class_17992.method_7909() == class_1802.field_8674 ? new class_2585(class_24992.method_10608(0)) : class_2561.class_2562.method_10873((String)class_24992.method_10608(0));
        if (object == null) {
            return;
        }
        int n3 = n2 - 12;
        int n4 = n2 - 12 + 8;
        int n5 = n - 8;
        int n6 = n + 16;
        int n7 = 300;
        Utils.mc.method_1531().method_22813(class_3872.field_17117);
        class_332.method_25291((class_4587)class_45872, (int)n5, (int)n3, (int)n7, (float)0.0f, (float)0.0f, (int)((int)(192.0f * f)), (int)((int)(192.0f * f)), (int)179, (int)179);
        class_45872.method_22903();
        class_45872.method_22905(f, f, 1.0f);
        class_45872.method_22904(0.0, 0.0, (double)(n7 + 5));
        int n8 = 0;
        for (class_5481 class_54812 : Utils.mc.field_1772.method_1728((class_5348)object, 119)) {
            Utils.mc.field_1772.method_27528(class_45872, class_54812, (float)n6 * (1.0f / f), (float)(n4 + n8) * (1.0f / f), 0);
            n8 += 8;
        }
        class_45872.method_22909();
    }

    @Inject(method={"drawSlot"}, at={@At(value="HEAD")})
    private void onDrawSlot(class_4587 class_45872, class_1735 class_17352, CallbackInfo callbackInfo) {
        int n = Modules.get().get(ItemHighlight.class).getColor(class_17352.method_7677());
        if (n != -1) {
            HandledScreenMixin.method_25294((class_4587)class_45872, (int)class_17352.field_7873, (int)class_17352.field_7872, (int)(class_17352.field_7873 + 16), (int)(class_17352.field_7872 + 16), (int)n);
        }
    }
}

