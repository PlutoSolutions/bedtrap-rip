/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.Map;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_304.class})
public interface KeyBindingAccessor {
    @Accessor(value="categoryOrderMap")
    public static Map<String, Integer> getCategoryOrderMap() {
        return null;
    }

    @Accessor(value="boundKey")
    public class_3675.class_306 getKey();
}

