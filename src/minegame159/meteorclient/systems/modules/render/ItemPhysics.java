/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1087
 *  net.minecraft.class_1160
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1798
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_243
 *  net.minecraft.class_2484
 *  net.minecraft.class_265
 *  net.minecraft.class_3486
 *  net.minecraft.class_3494
 *  net.minecraft.class_3726
 *  net.minecraft.class_4608
 *  net.minecraft.class_809$class_811
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
import net.minecraft.class_2248;
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
        ItemPhysics lllIlIlIIIll;
    }

    private int getRenderedAmount(class_1799 lllIIllIIIIl) {
        int lllIIllIIIII = 1;
        if (lllIIllIIIIl.method_7947() > 48) {
            lllIIllIIIII = 5;
        } else if (lllIIllIIIIl.method_7947() > 32) {
            lllIIllIIIII = 4;
        } else if (lllIIllIIIIl.method_7947() > 16) {
            lllIIllIIIII = 3;
        } else if (lllIIllIIIIl.method_7947() > 1) {
            lllIIllIIIII = 2;
        }
        return lllIIllIIIII;
    }

    @EventHandler
    private void onRenderItemEntity(RenderItemEntityEvent lllIIlllIlIl) {
        class_1792 lllIIllllIll;
        class_2248 lllIlIIlIIII;
        class_265 lllIlIIIllll;
        ItemPhysics lllIIlllIllI;
        class_1799 lllIlIIIIIlI = lllIIlllIlIl.itemEntity.method_6983();
        int lllIlIIIIIIl = lllIlIIIIIlI.method_7960() ? 187 : class_1792.method_7880((class_1792)lllIlIIIIIlI.method_7909()) + lllIlIIIIIlI.method_7919();
        lllIIlllIlIl.random.setSeed(lllIlIIIIIIl);
        lllIIlllIlIl.matrixStack.method_22903();
        class_1087 lllIlIIIIIII = lllIIlllIlIl.itemRenderer.method_4019(lllIlIIIIIlI, lllIIlllIlIl.itemEntity.field_6002, null);
        boolean lllIIlllllll = lllIlIIIIIII.method_4712();
        int lllIIllllllI = lllIIlllIllI.getRenderedAmount(lllIlIIIIIlI);
        IItemEntity lllIIlllllIl = (IItemEntity)lllIIlllIlIl.itemEntity;
        boolean lllIIlllllII = false;
        if (lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1747 && !(lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1798) && (lllIlIIIllll = (lllIlIIlIIII = ((class_1747)lllIIlllIlIl.itemEntity.method_6983().method_7909()).method_7711()).method_9530(lllIlIIlIIII.method_9564(), (class_1922)lllIIlllIlIl.itemEntity.field_6002, lllIIlllIlIl.itemEntity.method_24515(), class_3726.method_16194())).method_1105(class_2350.class_2351.field_11052) <= 0.5) {
            lllIIlllllII = true;
        }
        if ((lllIIllllIll = lllIIlllIlIl.itemEntity.method_6983().method_7909()) instanceof class_1747 && !(lllIIllllIll instanceof class_1798) && !lllIIlllllII) {
            lllIIlllIlIl.matrixStack.method_22904(0.0, -0.06, 0.0);
        }
        if (!lllIIlllllII) {
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.185, 0.0);
            lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20703.method_23626(1.571f));
            lllIIlllIlIl.matrixStack.method_22904(0.0, -0.185, -0.0);
        }
        boolean lllIIllllIlI = lllIIlllIlIl.itemEntity.field_6002.method_8320(lllIIlllIlIl.itemEntity.method_24515()).method_26227().method_15772().method_15791((class_3494)class_3486.field_15517);
        if (!(lllIIlllIlIl.itemEntity.method_24828() || lllIIlllIlIl.itemEntity.method_5869() || lllIIllllIlI)) {
            float lllIlIIIlllI = ((float)lllIIlllIlIl.itemEntity.method_6985() + lllIIlllIlIl.tickDelta) / 20.0f + lllIIlllIlIl.itemEntity.field_7203;
            if (!lllIIlllllII) {
                lllIIlllIlIl.matrixStack.method_22904(0.0, 0.185, 0.0);
                lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20707.method_23626(lllIlIIIlllI));
                lllIIlllIlIl.matrixStack.method_22904(0.0, -0.185, 0.0);
                lllIIlllllIl.setRotation(new class_243(0.0, 0.0, (double)lllIlIIIlllI));
            } else {
                lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20705.method_23626(lllIlIIIlllI));
                lllIIlllllIl.setRotation(new class_243(0.0, (double)lllIlIIIlllI, 0.0));
                lllIIlllIlIl.matrixStack.method_22904(0.0, -0.065, 0.0);
            }
            if (lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1798) {
                lllIIlllIlIl.matrixStack.method_22904(0.0, 0.0, 0.195);
            } else if (!(lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1747)) {
                lllIIlllIlIl.matrixStack.method_22904(0.0, 0.0, 0.195);
            }
        } else if (lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1798) {
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.185, 0.0);
            lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20707.method_23626((float)lllIIlllllIl.getRotation().field_1350));
            lllIIlllIlIl.matrixStack.method_22904(0.0, -0.185, 0.0);
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.0, 0.195);
        } else if (lllIIlllllII) {
            lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20705.method_23626((float)lllIIlllllIl.getRotation().field_1351));
            lllIIlllIlIl.matrixStack.method_22904(0.0, -0.065, 0.0);
        } else {
            if (!(lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1747)) {
                lllIIlllIlIl.matrixStack.method_22904(0.0, 0.0, 0.195);
            }
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.185, 0.0);
            lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20707.method_23626((float)lllIIlllllIl.getRotation().field_1350));
            lllIIlllIlIl.matrixStack.method_22904(0.0, -0.185, 0.0);
        }
        if (lllIIlllIlIl.itemEntity.field_6002.method_8320(lllIIlllIlIl.itemEntity.method_24515()).method_26204().equals((Object)class_2246.field_10114)) {
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.0, -0.1);
        }
        if (lllIIlllIlIl.itemEntity.method_6983().method_7909() instanceof class_1747 && ((class_1747)lllIIlllIlIl.itemEntity.method_6983().method_7909()).method_7711() instanceof class_2484) {
            lllIIlllIlIl.matrixStack.method_22904(0.0, 0.11, 0.0);
        }
        float lllIIllllIIl = lllIlIIIIIII.method_4709().field_4303.field_4285.method_4943();
        float lllIIllllIII = lllIlIIIIIII.method_4709().field_4303.field_4285.method_4945();
        float lllIIlllIlll = lllIlIIIIIII.method_4709().field_4303.field_4285.method_4947();
        if (!lllIIlllllll) {
            float lllIlIIIllIl = -0.0f * (float)lllIIllllllI * 0.5f * lllIIllllIIl;
            float lllIlIIIllII = -0.0f * (float)lllIIllllllI * 0.5f * lllIIllllIII;
            float lllIlIIIlIll = -0.09375f * (float)lllIIllllllI * 0.5f * lllIIlllIlll;
            lllIIlllIlIl.matrixStack.method_22904((double)lllIlIIIllIl, (double)lllIlIIIllII, (double)lllIlIIIlIll);
        }
        for (int lllIlIIIIlIl = 0; lllIlIIIIlIl < lllIIllllllI; ++lllIlIIIIlIl) {
            lllIIlllIlIl.matrixStack.method_22903();
            if (lllIlIIIIlIl > 0) {
                if (lllIIlllllll) {
                    float lllIlIIIlIIl = (lllIIlllIlIl.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float lllIlIIIlIII = (lllIIlllIlIl.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float lllIlIIIlIlI = (lllIIlllIlIl.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    lllIIlllIlIl.matrixStack.method_22904((double)lllIlIIIlIIl, (double)lllIlIIIlIII, (double)lllIlIIIlIlI);
                } else {
                    float lllIlIIIIlll = (lllIIlllIlIl.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    float lllIlIIIIllI = (lllIIlllIlIl.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    lllIIlllIlIl.matrixStack.method_22904((double)lllIlIIIIlll, (double)lllIlIIIIllI, 0.0);
                    lllIIlllIlIl.matrixStack.method_22907(class_1160.field_20707.method_23626(lllIIlllIlIl.random.nextFloat()));
                }
            }
            lllIIlllIlIl.itemRenderer.method_23179(lllIlIIIIIlI, class_809.class_811.field_4318, false, lllIIlllIlIl.matrixStack, lllIIlllIlIl.vertexConsumerProvider, lllIIlllIlIl.light, class_4608.field_21444, lllIlIIIIIII);
            lllIIlllIlIl.matrixStack.method_22909();
            if (lllIIlllllll) continue;
            lllIIlllIlIl.matrixStack.method_22904((double)(0.0f * lllIIllllIIl), (double)(0.0f * lllIIllllIII), (double)(0.0625f * lllIIlllIlll));
        }
        lllIIlllIlIl.matrixStack.method_22909();
        lllIIlllIlIl.setCancelled(true);
    }
}

