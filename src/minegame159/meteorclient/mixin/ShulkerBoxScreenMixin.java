/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1733
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_339
 *  net.minecraft.class_465
 *  net.minecraft.class_495
 *  org.spongepowered.asm.mixin.Mixin
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.AutoSteal;
import minegame159.meteorclient.utils.render.MeteorButtonWidget;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1733;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_465;
import net.minecraft.class_495;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={class_495.class})
public abstract class ShulkerBoxScreenMixin
extends class_465<class_1733> {
    public ShulkerBoxScreenMixin(class_1733 handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    protected void method_25426() {
        super.method_25426();
        AutoSteal autoSteal = Modules.get().get(AutoSteal.class);
        if (autoSteal.isActive() && autoSteal.getStealButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 88, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Steal"), button -> this.steal((class_1733)this.field_2797)));
        }
        if (autoSteal.isActive() && autoSteal.getDumpButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 46, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Dump"), button -> this.dump((class_1733)this.field_2797)));
        }
        if (autoSteal.isActive() && autoSteal.getAutoStealEnabled()) {
            this.steal((class_1733)this.field_2797);
        } else if (autoSteal.isActive() && autoSteal.getAutoDumpEnabled()) {
            this.dump((class_1733)this.field_2797);
        }
    }

    private void steal(class_1733 handler) {
        Modules.get().get(AutoSteal.class).stealAsync((class_1703)handler);
    }

    private void dump(class_1733 handler) {
        Modules.get().get(AutoSteal.class).dumpAsync((class_1703)handler);
    }
}

