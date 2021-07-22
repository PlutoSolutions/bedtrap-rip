/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_339
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_500
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import java.util.Objects;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.NameProtect;
import minegame159.meteorclient.systems.proxies.Proxies;
import minegame159.meteorclient.systems.proxies.Proxy;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_500.class})
public class MultiplayerScreenMixin
extends class_437 {
    private int textColor1;
    private int textColor2;
    private String loggedInAs;
    private int loggedInAsLength;

    public MultiplayerScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo info) {
        this.textColor1 = Color.fromRGBA(255, 255, 255, 255);
        this.textColor2 = Color.fromRGBA(175, 175, 175, 255);
        this.loggedInAs = "Logged in as ";
        this.loggedInAsLength = this.field_22793.method_1727(this.loggedInAs);
        this.method_25411((class_339)new class_4185(this.field_22789 - 75 - 3, 3, 75, 20, (class_2561)new class_2585("Accounts"), button -> this.field_22787.method_1507((class_437)GuiThemes.get().accountsScreen())));
        this.method_25411((class_339)new class_4185(this.field_22789 - 75 - 3 - 75 - 2, 3, 75, 20, (class_2561)new class_2585("Proxies"), button -> this.field_22787.method_1507((class_437)GuiThemes.get().proxiesScreen())));
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        float x = 3.0f;
        float y = 3.0f;
        this.field_22793.method_1720(matrices, this.loggedInAs, x, y, this.textColor1);
        this.field_22793.method_1720(matrices, Modules.get().get(NameProtect.class).getName(this.field_22787.method_1548().method_1676()), x + (float)this.loggedInAsLength, y, this.textColor2);
        Objects.requireNonNull(this.field_22793);
        Proxy proxy = Proxies.get().getEnabled();
        String left = proxy != null ? "Using proxy " : "Not using a proxy";
        String right = proxy != null ? "(" + proxy.name + ") " + proxy.ip + ":" + proxy.port : null;
        this.field_22793.method_1720(matrices, left, x, y += (float)11, this.textColor1);
        if (right != null) {
            this.field_22793.method_1720(matrices, right, x + (float)this.field_22793.method_1727(left), y, this.textColor2);
        }
    }
}

