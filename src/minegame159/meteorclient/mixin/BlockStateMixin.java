/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_4971
 *  org.spongepowered.asm.mixin.Mixin
 */
package minegame159.meteorclient.mixin;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.BlockActivateEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={class_2680.class})
public abstract class BlockStateMixin
extends class_4970.class_4971 {
    public BlockStateMixin(class_2248 block, ImmutableMap<class_2769<?>, Comparable<?>> propertyMap, MapCodec<class_2680> mapCodec) {
        super(block, propertyMap, mapCodec);
    }

    public class_1269 method_26174(class_1937 world, class_1657 player, class_1268 hand, class_3965 hit) {
        MeteorClient.EVENT_BUS.post(BlockActivateEvent.get((class_2680)this));
        return super.method_26174(world, player, hand, hit);
    }
}

