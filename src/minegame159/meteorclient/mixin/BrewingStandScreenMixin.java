/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.AutoBrewer;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1708;
import net.minecraft.class_2561;
import net.minecraft.class_465;
import net.minecraft.class_472;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={class_472.class})
public abstract class BrewingStandScreenMixin
extends class_465<class_1708> {
    public BrewingStandScreenMixin(class_1708 class_17082, class_1661 class_16612, class_2561 class_25612) {
        super((class_1703)class_17082, class_16612, class_25612);
    }

    public void method_25393() {
        super.method_25393();
        if (Modules.get().isActive(AutoBrewer.class)) {
            Modules.get().get(AutoBrewer.class).tick((class_1708)this.field_2797);
        }
    }

    public void method_25419() {
        if (Modules.get().isActive(AutoBrewer.class)) {
            Modules.get().get(AutoBrewer.class).onBrewingStandClose();
        }
        super.method_25419();
    }
}

