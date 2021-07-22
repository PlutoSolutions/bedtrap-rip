/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  net.minecraft.class_4587
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import com.g00fy2.versioncompare.Version;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.screens.NewUpdateScreen;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.network.HttpUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2561;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_442.class})
public class TitleScreenMixin
extends class_437 {
    private final int WHITE = Color.fromRGBA(255, 255, 255, 255);
    private final int GRAY = Color.fromRGBA(175, 175, 175, 255);
    private String text1;
    private int text1Length;
    private String text2;
    private int text2Length;
    private String text3;
    private int text3Length;
    private String text4;
    private int text4Length;
    private String text5;
    private int text5Length;
    private String text6;
    private int fullLength;
    private int prevWidth;

    public TitleScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo info) {
        this.text1 = "Bed";
        this.text2 = "Trap";
        this.text3 = " by ";
        this.text4 = "Eureka";
        this.text5 = " | ";
        this.text6 = "murphy#0429";
        this.text1Length = this.field_22793.method_1727(this.text1);
        this.text2Length = this.field_22793.method_1727(this.text2);
        this.text3Length = this.field_22793.method_1727(this.text3);
        this.text4Length = this.field_22793.method_1727(this.text4);
        this.text5Length = this.field_22793.method_1727(this.text5);
        int text6Length = this.field_22793.method_1727(this.text6);
        this.fullLength = this.text1Length + this.text2Length + this.text3Length + this.text4Length + this.text5Length + text6Length;
        this.prevWidth = 0;
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/TitleScreen;drawStringWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal=0)})
    private void onRenderIdkDude(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        if (Utils.firstTimeTitleScreen) {
            Utils.firstTimeTitleScreen = false;
            MeteorClient.LOG.info("Checking latest version of Meteor Client");
            MeteorExecutor.execute(() -> HttpUtils.getLines("http://meteorclient.com/api/version", s -> {
                Version latestVer = new Version((String)s);
                if (latestVer.isHigherThan(Config.get().version)) {
                    Utils.mc.method_1507((class_437)new NewUpdateScreen(GuiThemes.get(), latestVer));
                }
            }));
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
        if (!Config.get().titleScreenCredits) {
            return;
        }
        this.prevWidth = 0;
        this.field_22793.method_1720(matrices, this.text1, (float)(this.field_22789 - this.fullLength - 3), 3.0f, this.WHITE);
        this.prevWidth += this.text1Length;
        this.field_22793.method_1720(matrices, this.text2, (float)(this.field_22789 - this.fullLength + this.prevWidth - 3), 3.0f, this.GRAY);
        this.prevWidth += this.text2Length;
        this.field_22793.method_1720(matrices, this.text3, (float)(this.field_22789 - this.fullLength + this.prevWidth - 3), 3.0f, this.WHITE);
        this.prevWidth += this.text3Length;
        this.field_22793.method_1720(matrices, this.text4, (float)(this.field_22789 - this.fullLength + this.prevWidth - 3), 3.0f, this.GRAY);
        this.prevWidth += this.text4Length;
        this.field_22793.method_1720(matrices, this.text5, (float)(this.field_22789 - this.fullLength + this.prevWidth - 3), 3.0f, this.WHITE);
        this.prevWidth += this.text5Length;
        this.field_22793.method_1720(matrices, this.text6, (float)(this.field_22789 - this.fullLength + this.prevWidth - 3), 3.0f, this.GRAY);
    }
}

