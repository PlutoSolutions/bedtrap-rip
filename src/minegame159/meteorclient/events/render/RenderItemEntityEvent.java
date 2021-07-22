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
    public class_1542 itemEntity;
    private static final RenderItemEntityEvent INSTANCE = new RenderItemEntityEvent();
    public int light;
    public class_918 itemRenderer;
    public float tickDelta;
    public float f;
    public class_4587 matrixStack;
    public Random random;
    public class_4597 vertexConsumerProvider;

    public static RenderItemEntityEvent get(class_1542 class_15422, float f, float f2, class_4587 class_45872, class_4597 class_45972, int n, Random random, class_918 class_9182) {
        INSTANCE.setCancelled(false);
        RenderItemEntityEvent.INSTANCE.itemEntity = class_15422;
        RenderItemEntityEvent.INSTANCE.f = f;
        RenderItemEntityEvent.INSTANCE.tickDelta = f2;
        RenderItemEntityEvent.INSTANCE.matrixStack = class_45872;
        RenderItemEntityEvent.INSTANCE.vertexConsumerProvider = class_45972;
        RenderItemEntityEvent.INSTANCE.light = n;
        RenderItemEntityEvent.INSTANCE.random = random;
        RenderItemEntityEvent.INSTANCE.itemRenderer = class_9182;
        return INSTANCE;
    }
}

