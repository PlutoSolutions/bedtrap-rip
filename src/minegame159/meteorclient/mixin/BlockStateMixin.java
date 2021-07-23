/*
 * Decompiled with CFR 0.151.
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
    public BlockStateMixin(class_2248 class_22482, ImmutableMap<class_2769<?>, Comparable<?>> immutableMap, MapCodec<class_2680> mapCodec) {
        super(class_22482, immutableMap, mapCodec);
    }

    public class_1269 method_26174(class_1937 class_19372, class_1657 class_16572, class_1268 class_12682, class_3965 class_39652) {
        MeteorClient.EVENT_BUS.post(BlockActivateEvent.get((class_2680)this));
        return super.method_26174(class_19372, class_16572, class_12682, class_39652);
    }
}

