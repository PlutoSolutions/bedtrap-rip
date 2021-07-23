/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderItemEntityEvent;
import minegame159.meteorclient.mixininterface.IItemEntity;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1087;
import net.minecraft.class_1160;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1798;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2484;
import net.minecraft.class_265;
import net.minecraft.class_3486;
import net.minecraft.class_3494;
import net.minecraft.class_3726;
import net.minecraft.class_4608;
import net.minecraft.class_809;

public class ItemPhysics
extends Module {
    public ItemPhysics() {
        super(Categories.Render, "item-physics", "Applies physics to items on the ground.");
    }

    private int getRenderedAmount(class_1799 class_17992) {
        int n = 1;
        if (class_17992.method_7947() > 48) {
            n = 5;
        } else if (class_17992.method_7947() > 32) {
            n = 4;
        } else if (class_17992.method_7947() > 16) {
            n = 3;
        } else if (class_17992.method_7947() > 1) {
            n = 2;
        }
        return n;
    }

    @EventHandler
    private void onRenderItemEntity(RenderItemEntityEvent renderItemEntityEvent) {
        float f;
        float f2;
        float f3;
        class_1792 class_17922;
        class_265 class_2652;
        class_1799 class_17992 = renderItemEntityEvent.itemEntity.method_6983();
        int n = class_17992.method_7960() ? 187 : class_1792.method_7880((class_1792)class_17992.method_7909()) + class_17992.method_7919();
        renderItemEntityEvent.random.setSeed(n);
        renderItemEntityEvent.matrixStack.method_22903();
        class_1087 class_10872 = renderItemEntityEvent.itemRenderer.method_4019(class_17992, renderItemEntityEvent.itemEntity.field_6002, null);
        boolean bl = class_10872.method_4712();
        int n2 = this.getRenderedAmount(class_17992);
        IItemEntity iItemEntity = (IItemEntity)renderItemEntityEvent.itemEntity;
        boolean bl2 = false;
        if (renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1747 && !(renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1798) && (class_2652 = (class_17922 = ((class_1747)renderItemEntityEvent.itemEntity.method_6983().method_7909()).method_7711()).method_9530(class_17922.method_9564(), (class_1922)renderItemEntityEvent.itemEntity.field_6002, renderItemEntityEvent.itemEntity.method_24515(), class_3726.method_16194())).method_1105(class_2350.class_2351.field_11052) <= 0.5) {
            bl2 = true;
        }
        if ((class_17922 = renderItemEntityEvent.itemEntity.method_6983().method_7909()) instanceof class_1747 && !(class_17922 instanceof class_1798) && !bl2) {
            renderItemEntityEvent.matrixStack.method_22904(0.0, -0.06, 0.0);
        }
        if (!bl2) {
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.185, 0.0);
            renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20703.method_23626(1.571f));
            renderItemEntityEvent.matrixStack.method_22904(0.0, -0.185, -0.0);
        }
        boolean bl3 = renderItemEntityEvent.itemEntity.field_6002.method_8320(renderItemEntityEvent.itemEntity.method_24515()).method_26227().method_15772().method_15791((class_3494)class_3486.field_15517);
        if (!(renderItemEntityEvent.itemEntity.method_24828() || renderItemEntityEvent.itemEntity.method_5869() || bl3)) {
            f3 = ((float)renderItemEntityEvent.itemEntity.method_6985() + renderItemEntityEvent.tickDelta) / 20.0f + renderItemEntityEvent.itemEntity.field_7203;
            if (!bl2) {
                renderItemEntityEvent.matrixStack.method_22904(0.0, 0.185, 0.0);
                renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20707.method_23626(f3));
                renderItemEntityEvent.matrixStack.method_22904(0.0, -0.185, 0.0);
                iItemEntity.setRotation(new class_243(0.0, 0.0, (double)f3));
            } else {
                renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20705.method_23626(f3));
                iItemEntity.setRotation(new class_243(0.0, (double)f3, 0.0));
                renderItemEntityEvent.matrixStack.method_22904(0.0, -0.065, 0.0);
            }
            if (renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1798) {
                renderItemEntityEvent.matrixStack.method_22904(0.0, 0.0, 0.195);
            } else if (!(renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1747)) {
                renderItemEntityEvent.matrixStack.method_22904(0.0, 0.0, 0.195);
            }
        } else if (renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1798) {
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.185, 0.0);
            renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20707.method_23626((float)iItemEntity.getRotation().field_1350));
            renderItemEntityEvent.matrixStack.method_22904(0.0, -0.185, 0.0);
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.0, 0.195);
        } else if (bl2) {
            renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20705.method_23626((float)iItemEntity.getRotation().field_1351));
            renderItemEntityEvent.matrixStack.method_22904(0.0, -0.065, 0.0);
        } else {
            if (!(renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1747)) {
                renderItemEntityEvent.matrixStack.method_22904(0.0, 0.0, 0.195);
            }
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.185, 0.0);
            renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20707.method_23626((float)iItemEntity.getRotation().field_1350));
            renderItemEntityEvent.matrixStack.method_22904(0.0, -0.185, 0.0);
        }
        if (renderItemEntityEvent.itemEntity.field_6002.method_8320(renderItemEntityEvent.itemEntity.method_24515()).method_26204().equals(class_2246.field_10114)) {
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.0, -0.1);
        }
        if (renderItemEntityEvent.itemEntity.method_6983().method_7909() instanceof class_1747 && ((class_1747)renderItemEntityEvent.itemEntity.method_6983().method_7909()).method_7711() instanceof class_2484) {
            renderItemEntityEvent.matrixStack.method_22904(0.0, 0.11, 0.0);
        }
        f3 = class_10872.method_4709().field_4303.field_4285.method_4943();
        float f4 = class_10872.method_4709().field_4303.field_4285.method_4945();
        float f5 = class_10872.method_4709().field_4303.field_4285.method_4947();
        if (!bl) {
            float f6 = -0.0f * (float)n2 * 0.5f * f3;
            f2 = -0.0f * (float)n2 * 0.5f * f4;
            f = -0.09375f * (float)n2 * 0.5f * f5;
            renderItemEntityEvent.matrixStack.method_22904((double)f6, (double)f2, (double)f);
        }
        for (int i = 0; i < n2; ++i) {
            renderItemEntityEvent.matrixStack.method_22903();
            if (i > 0) {
                if (bl) {
                    f2 = (renderItemEntityEvent.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    f = (renderItemEntityEvent.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f7 = (renderItemEntityEvent.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    renderItemEntityEvent.matrixStack.method_22904((double)f2, (double)f, (double)f7);
                } else {
                    f2 = (renderItemEntityEvent.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    f = (renderItemEntityEvent.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    renderItemEntityEvent.matrixStack.method_22904((double)f2, (double)f, 0.0);
                    renderItemEntityEvent.matrixStack.method_22907(class_1160.field_20707.method_23626(renderItemEntityEvent.random.nextFloat()));
                }
            }
            renderItemEntityEvent.itemRenderer.method_23179(class_17992, class_809.class_811.field_4318, false, renderItemEntityEvent.matrixStack, renderItemEntityEvent.vertexConsumerProvider, renderItemEntityEvent.light, class_4608.field_21444, class_10872);
            renderItemEntityEvent.matrixStack.method_22909();
            if (bl) continue;
            renderItemEntityEvent.matrixStack.method_22904((double)(0.0f * f3), (double)(0.0f * f4), (double)(0.0625f * f5));
            if (1 <= 3) continue;
            return;
        }
        renderItemEntityEvent.matrixStack.method_22909();
        renderItemEntityEvent.setCancelled(true);
    }
}

