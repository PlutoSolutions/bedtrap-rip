/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IPlayerMoveC2SPacket;
import net.minecraft.class_2828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={class_2828.class})
public class PlayerMoveC2SPacketMixin
implements IPlayerMoveC2SPacket {
    @Unique
    private int tag;

    @Override
    public void setTag(int n) {
        this.tag = n;
    }

    @Override
    public int getTag() {
        return this.tag;
    }
}

