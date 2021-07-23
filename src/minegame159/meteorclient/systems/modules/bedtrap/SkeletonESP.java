/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1007;
import net.minecraft.class_1158;
import net.minecraft.class_1159;
import net.minecraft.class_1160;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_922;

public class SkeletonESP
extends Module {
    private final Setting<SettingColor> skeletonColor;
    private final SettingGroup sgGeneral;
    static final boolean $assertionsDisabled = !SkeletonESP.class.desiredAssertionStatus();

    private class_243 getEntityRenderPosition(class_1657 class_16572, double d) {
        double d2 = class_16572.field_6014 + (class_16572.method_23317() - class_16572.field_6014) * d - this.mc.method_1561().field_4686.method_19326().field_1352;
        double d3 = class_16572.field_6036 + (class_16572.method_23318() - class_16572.field_6036) * d - this.mc.method_1561().field_4686.method_19326().field_1351;
        double d4 = class_16572.field_5969 + (class_16572.method_23321() - class_16572.field_5969) * d - this.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(d2, d3, d4);
    }

    public SkeletonESP() {
        super(Categories.BedTrap, "skeleton-esp", "Renders a skeleton for players.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.skeletonColor = this.sgGeneral.add(new ColorSetting.Builder().name("skeleton-color").description("The skeletons color.").defaultValue(new SettingColor(255, 255, 255)).build());
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        this.mc.field_1687.method_18112().forEach(arg_0 -> this.lambda$onRender$0(renderEvent, arg_0));
    }

    private void lambda$onRender$0(RenderEvent renderEvent, class_1297 class_12972) {
        if (class_12972 instanceof class_1657 && (class_12972 != this.mc.field_1724 || Modules.get().isActive(Freecam.class)) && class_12972.method_5805()) {
            class_4587 class_45872 = renderEvent.matrices;
            float f = renderEvent.tickDelta;
            class_1657 class_16572 = (class_1657)class_12972;
            SettingColor settingColor = Friends.get().isFriend(class_16572) ? Friends.get().color : (minegame159.meteorclient.utils.render.color.Color)this.skeletonColor.get();
            Color color = new Color(settingColor.r, settingColor.g, settingColor.b);
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask((boolean)class_310.method_29611());
            RenderSystem.enableCull();
            class_243 class_2432 = this.getEntityRenderPosition(class_16572, f);
            class_1007 class_10072 = (class_1007)((class_922)this.mc.method_1561().method_3953((class_1297)class_16572));
            class_591 class_5912 = (class_591)class_10072.method_4038();
            float f2 = class_3532.method_17821((float)f, (float)class_16572.field_6220, (float)class_16572.field_6283);
            float f3 = class_3532.method_17821((float)f, (float)class_16572.field_6259, (float)class_16572.field_6241);
            float f4 = class_16572.field_6249 - class_16572.field_6225 * (1.0f - f);
            float f5 = class_3532.method_16439((float)f, (float)class_16572.field_6211, (float)class_16572.field_6225);
            float f6 = (float)class_16572.field_6012 + f;
            float f7 = f3 - f2;
            float f8 = class_3532.method_16439((float)f, (float)class_16572.field_6004, (float)class_16572.field_5965);
            class_5912.method_17086((class_1309)class_16572, f4, f5, f);
            class_5912.method_17087((class_1309)class_16572, f4, f5, f6, f7, f8);
            boolean bl = class_16572.method_5715();
            class_630 class_6302 = class_5912.field_3398;
            class_630 class_6303 = class_5912.field_3390;
            class_630 class_6304 = class_5912.field_3401;
            class_630 class_6305 = class_5912.field_3397;
            class_630 class_6306 = class_5912.field_3392;
            class_45872.method_22904(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
            if (class_16572.method_20232()) {
                class_45872.method_22904(0.0, (double)0.35f, 0.0);
            }
            class_45872.method_22907(new class_1158(new class_1160(0.0f, -1.0f, 0.0f), class_16572.field_6283 + 180.0f, true));
            if (class_16572.method_20232()) {
                class_45872.method_22907(new class_1158(new class_1160(-1.0f, 0.0f, 0.0f), 90.0f + class_16572.field_5965, true));
                class_45872.method_22904(0.0, (double)-0.95f, 0.0);
            }
            class_287 class_2872 = class_289.method_1348().method_1349();
            class_2872.method_1328(1, class_290.field_1576);
            class_1159 class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, bl ? 0.6f : 0.7f, bl ? 0.23f : 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, bl ? 1.05f : 1.4f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, -0.37f, bl ? 1.05f : 1.35f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.37f, bl ? 1.05f : 1.35f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, -0.15f, bl ? 0.6f : 0.7f, bl ? 0.23f : 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.15f, bl ? 0.6f : 0.7f, bl ? 0.23f : 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22903();
            class_45872.method_22904(0.0, bl ? (double)1.05f : (double)1.4f, 0.0);
            this.rotate(class_45872, class_6302);
            class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, 0.0f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, 0.15f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22909();
            class_45872.method_22903();
            class_45872.method_22904((double)0.15f, bl ? (double)0.6f : (double)0.7f, bl ? (double)0.23f : 0.0);
            this.rotate(class_45872, class_6306);
            class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, 0.0f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, -0.6f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22909();
            class_45872.method_22903();
            class_45872.method_22904((double)-0.15f, bl ? (double)0.6f : (double)0.7f, bl ? (double)0.23f : 0.0);
            this.rotate(class_45872, class_6305);
            class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, 0.0f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, -0.6f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22909();
            class_45872.method_22903();
            class_45872.method_22904((double)0.37f, bl ? (double)1.05f : (double)1.35f, 0.0);
            this.rotate(class_45872, class_6304);
            class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, 0.0f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, -0.55f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22909();
            class_45872.method_22903();
            class_45872.method_22904((double)-0.37f, bl ? (double)1.05f : (double)1.35f, 0.0);
            this.rotate(class_45872, class_6303);
            class_11592 = class_45872.method_23760().method_23761();
            class_2872.method_22918(class_11592, 0.0f, 0.0f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_2872.method_22918(class_11592, 0.0f, -0.55f, 0.0f).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            class_45872.method_22909();
            class_2872.method_1326();
            class_286.method_1309((class_287)class_2872);
            RenderSystem.enableTexture();
            RenderSystem.disableCull();
            RenderSystem.disableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)true);
            if (class_16572.method_20232()) {
                class_45872.method_22904(0.0, (double)0.95f, 0.0);
                class_45872.method_22907(new class_1158(new class_1160(1.0f, 0.0f, 0.0f), 90.0f + class_16572.field_5965, true));
                class_45872.method_22904(0.0, (double)-0.35f, 0.0);
            }
            class_45872.method_22907(new class_1158(new class_1160(0.0f, 1.0f, 0.0f), class_16572.field_6283 + 180.0f, true));
            class_45872.method_22904(-class_2432.field_1352, -class_2432.field_1351, -class_2432.field_1350);
        }
    }

    private void rotate(class_4587 class_45872, class_630 class_6302) {
        if (class_6302.field_3674 != 0.0f) {
            class_45872.method_22907(class_1160.field_20707.method_23626(class_6302.field_3674));
        }
        if (class_6302.field_3675 != 0.0f) {
            class_45872.method_22907(class_1160.field_20704.method_23626(class_6302.field_3675));
        }
        if (class_6302.field_3654 != 0.0f) {
            class_45872.method_22907(class_1160.field_20702.method_23626(class_6302.field_3654));
        }
    }
}

