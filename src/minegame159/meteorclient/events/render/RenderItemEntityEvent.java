/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1542
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_918
 */
package minegame159.meteorclient.events.render;

import java.util.Random;
import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1542;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_918;

public class RenderItemEntityEvent
extends Cancellable {
    public /* synthetic */ class_1542 itemEntity;
    private static final /* synthetic */ RenderItemEntityEvent INSTANCE;
    public /* synthetic */ int light;
    public /* synthetic */ class_918 itemRenderer;
    public /* synthetic */ float tickDelta;
    public /* synthetic */ float f;
    public /* synthetic */ class_4587 matrixStack;
    public /* synthetic */ Random random;
    public /* synthetic */ class_4597 vertexConsumerProvider;

    static {
        INSTANCE = new RenderItemEntityEvent();
    }

    public RenderItemEntityEvent() {
        RenderItemEntityEvent lIIIIIIIlIIlIll;
    }

    public static RenderItemEntityEvent get(class_1542 lIIIIIIIlIIIIIl, float lIIIIIIIlIIIIII, float lIIIIIIIIllllll, class_4587 lIIIIIIIIlllllI, class_4597 lIIIIIIIIllIlIl, int lIIIIIIIIllllII, Random lIIIIIIIIlllIll, class_918 lIIIIIIIIlllIlI) {
        INSTANCE.setCancelled(false);
        RenderItemEntityEvent.INSTANCE.itemEntity = lIIIIIIIlIIIIIl;
        RenderItemEntityEvent.INSTANCE.f = lIIIIIIIlIIIIII;
        RenderItemEntityEvent.INSTANCE.tickDelta = lIIIIIIIIllllll;
        RenderItemEntityEvent.INSTANCE.matrixStack = lIIIIIIIIlllllI;
        RenderItemEntityEvent.INSTANCE.vertexConsumerProvider = lIIIIIIIIllIlIl;
        RenderItemEntityEvent.INSTANCE.light = lIIIIIIIIllllII;
        RenderItemEntityEvent.INSTANCE.random = lIIIIIIIIlllIll;
        RenderItemEntityEvent.INSTANCE.itemRenderer = lIIIIIIIIlllIlI;
        return INSTANCE;
    }
}

