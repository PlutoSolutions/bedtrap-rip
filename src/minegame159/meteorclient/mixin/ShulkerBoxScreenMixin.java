/*
 * Decompiled with CFR 0.151.
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
    public ShulkerBoxScreenMixin(class_1733 class_17332, class_1661 class_16612, class_2561 class_25612) {
        super((class_1703)class_17332, class_16612, class_25612);
    }

    protected void method_25426() {
        super.method_25426();
        AutoSteal autoSteal = Modules.get().get(AutoSteal.class);
        if (autoSteal.isActive() && autoSteal.getStealButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 88, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Steal"), this::lambda$init$0));
        }
        if (autoSteal.isActive() && autoSteal.getDumpButtonEnabled()) {
            this.method_25411((class_339)new MeteorButtonWidget(this.field_2776 + this.field_2792 - 46, this.field_2800 + 3, 40, 12, (class_2561)new class_2585("Dump"), this::lambda$init$1));
        }
        if (autoSteal.isActive() && autoSteal.getAutoStealEnabled()) {
            this.steal((class_1733)this.field_2797);
        } else if (autoSteal.isActive() && autoSteal.getAutoDumpEnabled()) {
            this.dump((class_1733)this.field_2797);
        }
    }

    private void steal(class_1733 class_17332) {
        Modules.get().get(AutoSteal.class).stealAsync((class_1703)class_17332);
    }

    private void dump(class_1733 class_17332) {
        Modules.get().get(AutoSteal.class).dumpAsync((class_1703)class_17332);
    }

    private void lambda$init$1(MeteorButtonWidget meteorButtonWidget) {
        this.dump((class_1733)this.field_2797);
    }

    private void lambda$init$0(MeteorButtonWidget meteorButtonWidget) {
        this.steal((class_1733)this.field_2797);
    }
}

