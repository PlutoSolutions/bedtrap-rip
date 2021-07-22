/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1707
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_339
 *  net.minecraft.class_3936
 *  net.minecraft.class_465
 *  net.minecraft.class_476
 *  org.spongepowered.asm.mixin.Mixin
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.AutoSteal;
import minegame159.meteorclient.utils.render.MeteorButtonWidget;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_3936;
import net.minecraft.class_465;
import net.minecraft.class_476;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={class_476.class})
public abstract class GenericContainerScreenMixin
extends class_465<class_1707>
implements class_3936<class_1707> {
    public GenericContainerScreenMixin(class_1707 container, class_1661 playerInventory, class_2561 name) {
        super((class_1703)container, playerInventory, name);
    }

    protected void method_25426() {
        super.method_25426();
        AutoSteal autoSteal = Modules.get().get(AutoSteal.class);
        if (autoSteal.isActive() && autoSteal.getStealButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 88, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Steal"), button -> this.steal((class_1707)this.field_2797)));
        }
        if (autoSteal.isActive() && autoSteal.getDumpButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 46, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Dump"), button -> this.dump((class_1707)this.field_2797)));
        }
        if (autoSteal.isActive() && autoSteal.getAutoStealEnabled()) {
            this.steal((class_1707)this.field_2797);
        } else if (autoSteal.isActive() && autoSteal.getAutoDumpEnabled()) {
            this.dump((class_1707)this.field_2797);
        }
    }

    private void steal(class_1707 handler) {
        Modules.get().get(AutoSteal.class).stealAsync((class_1703)handler);
    }

    private void dump(class_1707 handler) {
        Modules.get().get(AutoSteal.class).dumpAsync((class_1703)handler);
    }
}

