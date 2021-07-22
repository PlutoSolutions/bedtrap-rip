/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  javax.annotation.Nullable
 *  net.minecraft.class_1262
 *  net.minecraft.class_1657
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1806
 *  net.minecraft.class_1937
 *  net.minecraft.class_22
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2561
 *  net.minecraft.class_2561$class_2562
 *  net.minecraft.class_2585
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_2960
 *  net.minecraft.class_308
 *  net.minecraft.class_332
 *  net.minecraft.class_3872
 *  net.minecraft.class_3936
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_465
 *  net.minecraft.class_5348
 *  net.minecraft.class_5481
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
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

    public HandledScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"mouseDragged"}, at={@At(value="TAIL")})
    private void onMouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> info) {
        if (button != 0 || this.field_2783 || !Modules.get().get(InventoryTweaks.class).mouseDragItemMove()) {
            return;
        }
        class_1735 slot = this.method_2386(mouseX, mouseY);
        if (slot != null && slot.method_7681() && HandledScreenMixin.method_25442()) {
            this.method_2383(slot, slot.field_7874, button, class_1713.field_7794);
        }
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        class_1799 itemStack;
        BetterTooltips toolips = Modules.get().get(BetterTooltips.class);
        if (button == 2 && this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960() && toolips.middleClickOpen() && (Utils.hasItems(itemStack = this.field_2787.method_7677()) || itemStack.method_7909() == class_1802.field_8466)) {
            cir.setReturnValue((Object)Utils.openContainer(this.field_2787.method_7677(), ITEMS, false));
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
            BetterTooltips toolips = Modules.get().get(BetterTooltips.class);
            if (Utils.hasItems(this.field_2787.method_7677()) && toolips.previewShulkers()) {
                class_2487 compoundTag = this.field_2787.method_7677().method_7941("BlockEntityTag");
                class_2371 itemStacks = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
                class_1262.method_5429((class_2487)compoundTag, (class_2371)itemStacks);
                this.draw(matrices, (class_2371<class_1799>)itemStacks, mouseX, mouseY, Utils.getShulkerColor(this.field_2787.method_7677()));
            } else if (this.field_2787.method_7677().method_7909() == class_1802.field_8466 && toolips.previewEChest()) {
                this.draw(matrices, EChestMemory.ITEMS, mouseX, mouseY, BetterTooltips.ECHEST_COLOR);
            } else if (this.field_2787.method_7677().method_7909() == class_1802.field_8204 && toolips.previewMaps()) {
                this.drawMapPreview(matrices, this.field_2787.method_7677(), mouseX, mouseY, (int)(toolips.mapsScale.get() * 100.0));
            } else if ((this.field_2787.method_7677().method_7909() == class_1802.field_8674 || this.field_2787.method_7677().method_7909() == class_1802.field_8360) && toolips.previewBooks()) {
                this.drawBookPreview(matrices, this.field_2787.method_7677(), mouseX, mouseY);
            }
        }
    }

    @Inject(method={"drawMouseoverTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawMouseoverTooltip(class_4587 matrices, int x, int y, CallbackInfo info) {
        if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
            BetterTooltips toolips = Modules.get().get(BetterTooltips.class);
            if (this.field_2787.method_7677().method_7909() == class_1802.field_8204 && toolips.previewMaps()) {
                info.cancel();
            } else if (toolips.previewBooks() && BetterTooltips.willRenderBookPreview(this.field_2787.method_7677()) && !toolips.showVanilla.get().booleanValue()) {
                info.cancel();
            } else if ((Utils.hasItems(this.field_2787.method_7677()) && toolips.previewShulkers() || this.field_2787.method_7677().method_7909() == class_1802.field_8466 && toolips.previewEChest()) && !toolips.showVanilla.get().booleanValue()) {
                info.cancel();
            }
        }
    }

    private void draw(class_4587 matrices, class_2371<class_1799> itemStacks, int mouseX, int mouseY, Color color) {
        RenderSystem.disableLighting();
        RenderSystem.disableDepthTest();
        GL11.glClear((int)256);
        this.drawBackground(matrices, mouseX += 8, mouseY -= 12, color);
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        class_308.method_22890();
        int row = 0;
        int i = 0;
        for (class_1799 itemStack : itemStacks) {
            RenderUtils.drawItem(itemStack, mouseX + 8 + i * 18, mouseY + 7 + row * 18, true);
            if (++i < 9) continue;
            i = 0;
            ++row;
        }
        class_308.method_1450();
        RenderSystem.enableDepthTest();
    }

    private void drawBackground(class_4587 matrices, int x, int y, Color color) {
        RenderSystem.color4f((float)((float)color.r / 255.0f), (float)((float)color.g / 255.0f), (float)((float)color.b / 255.0f), (float)((float)color.a / 255.0f));
        this.field_22787.method_1531().method_22813(TEXTURE_CONTAINER_BACKGROUND);
        class_332.method_25291((class_4587)matrices, (int)x, (int)y, (int)0, (float)0.0f, (float)0.0f, (int)176, (int)67, (int)67, (int)176);
    }

    private void drawMapPreview(class_4587 matrices, class_1799 stack, int x, int y, int dimensions) {
        GL11.glEnable((int)3042);
        RenderSystem.pushMatrix();
        RenderSystem.disableLighting();
        RenderSystem.color4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int y1 = y - 12;
        int y2 = y1 + dimensions;
        int x1 = x + 8;
        int x2 = x1 + dimensions;
        int z = 300;
        this.field_22787.method_1531().method_22813(TEXTURE_MAP_BACKGROUND);
        class_289 tessellator = class_289.method_1348();
        class_287 buffer = tessellator.method_1349();
        buffer.method_1328(7, class_290.field_1585);
        buffer.method_22912((double)x1, (double)y2, (double)z).method_22913(0.0f, 1.0f).method_1344();
        buffer.method_22912((double)x2, (double)y2, (double)z).method_22913(1.0f, 1.0f).method_1344();
        buffer.method_22912((double)x2, (double)y1, (double)z).method_22913(1.0f, 0.0f).method_1344();
        buffer.method_22912((double)x1, (double)y1, (double)z).method_22913(0.0f, 0.0f).method_1344();
        tessellator.method_1350();
        class_22 mapState = class_1806.method_8001((class_1799)stack, (class_1937)this.field_22787.field_1687);
        if (mapState != null) {
            mapState.method_101((class_1657)this.field_22787.field_1724);
            z = 310;
            double scale = (double)(dimensions - 16) / 128.0;
            RenderSystem.translatef((float)(x1 += 8), (float)(y1 += 8), (float)z);
            RenderSystem.scaled((double)scale, (double)scale, (double)0.0);
            class_4597.class_4598 consumer = this.field_22787.method_22940().method_23000();
            this.field_22787.field_1773.method_3194().method_1773(matrices, (class_4597)consumer, mapState, false, 0xF000F0);
        }
        RenderSystem.enableLighting();
        RenderSystem.popMatrix();
    }

    private void drawBookPreview(class_4587 matrices, class_1799 stack, int x, int y) {
        float scale = 0.7f * Modules.get().get(BetterTooltips.class).booksScale.get().floatValue();
        class_2487 tag = stack.method_7969();
        if (tag == null) {
            return;
        }
        class_2499 ltag = tag.method_10554("pages", 8);
        if (ltag.size() < 1) {
            return;
        }
        Object page = stack.method_7909() == class_1802.field_8674 ? new class_2585(ltag.method_10608(0)) : class_2561.class_2562.method_10873((String)ltag.method_10608(0));
        if (page == null) {
            return;
        }
        int y1 = y - 12;
        int y2 = y - 12 + 8;
        int x1 = x - 8;
        int x2 = x + 16;
        int z = 300;
        Utils.mc.method_1531().method_22813(class_3872.field_17117);
        class_332.method_25291((class_4587)matrices, (int)x1, (int)y1, (int)z, (float)0.0f, (float)0.0f, (int)((int)(192.0f * scale)), (int)((int)(192.0f * scale)), (int)179, (int)179);
        matrices.method_22903();
        matrices.method_22905(scale, scale, 1.0f);
        matrices.method_22904(0.0, 0.0, (double)(z + 5));
        int offset = 0;
        for (class_5481 line : Utils.mc.field_1772.method_1728((class_5348)page, 119)) {
            Utils.mc.field_1772.method_27528(matrices, line, (float)x2 * (1.0f / scale), (float)(y2 + offset) * (1.0f / scale), 0);
            offset += 8;
        }
        matrices.method_22909();
    }

    @Inject(method={"drawSlot"}, at={@At(value="HEAD")})
    private void onDrawSlot(class_4587 matrices, class_1735 slot, CallbackInfo info) {
        int color = Modules.get().get(ItemHighlight.class).getColor(slot.method_7677());
        if (color != -1) {
            HandledScreenMixin.method_25294((class_4587)matrices, (int)slot.field_7873, (int)slot.field_7872, (int)(slot.field_7873 + 16), (int)(slot.field_7872 + 16), (int)color);
        }
    }
}

