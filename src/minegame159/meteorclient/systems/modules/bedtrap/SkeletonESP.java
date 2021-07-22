/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1007
 *  net.minecraft.class_1158
 *  net.minecraft.class_1159
 *  net.minecraft.class_1160
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_591
 *  net.minecraft.class_630
 *  net.minecraft.class_922
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
    private final /* synthetic */ Setting<SettingColor> skeletonColor;
    private final /* synthetic */ SettingGroup sgGeneral;

    private class_243 getEntityRenderPosition(class_1657 lIlIllIlIlIIIIl, double lIlIllIlIlIIllI) {
        SkeletonESP lIlIllIlIlIlIII;
        double lIlIllIlIlIIlIl = lIlIllIlIlIIIIl.field_6014 + (lIlIllIlIlIIIIl.method_23317() - lIlIllIlIlIIIIl.field_6014) * lIlIllIlIlIIllI - lIlIllIlIlIlIII.mc.method_1561().field_4686.method_19326().field_1352;
        double lIlIllIlIlIIlII = lIlIllIlIlIIIIl.field_6036 + (lIlIllIlIlIIIIl.method_23318() - lIlIllIlIlIIIIl.field_6036) * lIlIllIlIlIIllI - lIlIllIlIlIlIII.mc.method_1561().field_4686.method_19326().field_1351;
        double lIlIllIlIlIIIll = lIlIllIlIlIIIIl.field_5969 + (lIlIllIlIlIIIIl.method_23321() - lIlIllIlIlIIIIl.field_5969) * lIlIllIlIlIIllI - lIlIllIlIlIlIII.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(lIlIllIlIlIIlIl, lIlIllIlIlIIlII, lIlIllIlIlIIIll);
    }

    public SkeletonESP() {
        super(Categories.BedTrap, "skeleton-esp", "Renders a skeleton for players.");
        SkeletonESP lIlIllIlIllllII;
        lIlIllIlIllllII.sgGeneral = lIlIllIlIllllII.settings.getDefaultGroup();
        lIlIllIlIllllII.skeletonColor = lIlIllIlIllllII.sgGeneral.add(new ColorSetting.Builder().name("skeleton-color").description("The skeletons color.").defaultValue(new SettingColor(255, 255, 255)).build());
    }

    @EventHandler
    private void onRender(RenderEvent lIlIllIlIlllIII) {
        SkeletonESP lIlIllIlIlllIIl;
        assert (lIlIllIlIlllIIl.mc.field_1687 != null);
        lIlIllIlIlllIIl.mc.field_1687.method_18112().forEach(lIlIllIIllIlIIl -> {
            SkeletonESP lIlIllIIllIlIll;
            if (lIlIllIIllIlIIl instanceof class_1657 && (lIlIllIIllIlIIl != lIlIllIIllIlIll.mc.field_1724 || Modules.get().isActive(Freecam.class)) && lIlIllIIllIlIIl.method_5805()) {
                class_4587 lIlIllIlIIIIIlI = lIlIllIIllIIlll.matrices;
                float lIlIllIlIIIIIIl = lIlIllIIllIIlll.tickDelta;
                class_1657 lIlIllIlIIIIIII = (class_1657)lIlIllIIllIlIIl;
                SettingColor lIlIllIIlllllll = Friends.get().isFriend(lIlIllIlIIIIIII) ? Friends.get().color : (minegame159.meteorclient.utils.render.color.Color)lIlIllIIllIlIll.skeletonColor.get();
                Color lIlIllIIllllllI = new Color(lIlIllIIlllllll.r, lIlIllIIlllllll.g, lIlIllIIlllllll.b);
                RenderSystem.disableTexture();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask((boolean)class_310.method_29611());
                RenderSystem.enableCull();
                class_243 lIlIllIIlllllIl = lIlIllIIllIlIll.getEntityRenderPosition(lIlIllIlIIIIIII, lIlIllIlIIIIIIl);
                class_1007 lIlIllIIlllllII = (class_1007)((class_922)lIlIllIIllIlIll.mc.method_1561().method_3953((class_1297)lIlIllIlIIIIIII));
                class_591 lIlIllIIllllIll = (class_591)lIlIllIIlllllII.method_4038();
                float lIlIllIIllllIlI = class_3532.method_17821((float)lIlIllIlIIIIIIl, (float)lIlIllIlIIIIIII.field_6220, (float)lIlIllIlIIIIIII.field_6283);
                float lIlIllIIllllIIl = class_3532.method_17821((float)lIlIllIlIIIIIIl, (float)lIlIllIlIIIIIII.field_6259, (float)lIlIllIlIIIIIII.field_6241);
                float lIlIllIIllllIII = lIlIllIlIIIIIII.field_6249 - lIlIllIlIIIIIII.field_6225 * (1.0f - lIlIllIlIIIIIIl);
                float lIlIllIIlllIlll = class_3532.method_16439((float)lIlIllIlIIIIIIl, (float)lIlIllIlIIIIIII.field_6211, (float)lIlIllIlIIIIIII.field_6225);
                float lIlIllIIlllIllI = (float)lIlIllIlIIIIIII.field_6012 + lIlIllIlIIIIIIl;
                float lIlIllIIlllIlIl = lIlIllIIllllIIl - lIlIllIIllllIlI;
                float lIlIllIIlllIlII = class_3532.method_16439((float)lIlIllIlIIIIIIl, (float)lIlIllIlIIIIIII.field_6004, (float)lIlIllIlIIIIIII.field_5965);
                lIlIllIIllllIll.method_17086((class_1309)lIlIllIlIIIIIII, lIlIllIIllllIII, lIlIllIIlllIlll, lIlIllIlIIIIIIl);
                lIlIllIIllllIll.method_17087((class_1309)lIlIllIlIIIIIII, lIlIllIIllllIII, lIlIllIIlllIlll, lIlIllIIlllIllI, lIlIllIIlllIlIl, lIlIllIIlllIlII);
                boolean lIlIllIIlllIIll = lIlIllIlIIIIIII.method_5715();
                class_630 lIlIllIIlllIIlI = lIlIllIIllllIll.field_3398;
                class_630 lIlIllIIlllIIIl = lIlIllIIllllIll.field_3390;
                class_630 lIlIllIIlllIIII = lIlIllIIllllIll.field_3401;
                class_630 lIlIllIIllIllll = lIlIllIIllllIll.field_3397;
                class_630 lIlIllIIllIlllI = lIlIllIIllllIll.field_3392;
                lIlIllIlIIIIIlI.method_22904(lIlIllIIlllllIl.field_1352, lIlIllIIlllllIl.field_1351, lIlIllIIlllllIl.field_1350);
                if (lIlIllIlIIIIIII.method_20232()) {
                    lIlIllIlIIIIIlI.method_22904(0.0, (double)0.35f, 0.0);
                }
                lIlIllIlIIIIIlI.method_22907(new class_1158(new class_1160(0.0f, -1.0f, 0.0f), lIlIllIlIIIIIII.field_6283 + 180.0f, true));
                if (lIlIllIlIIIIIII.method_20232()) {
                    lIlIllIlIIIIIlI.method_22907(new class_1158(new class_1160(-1.0f, 0.0f, 0.0f), 90.0f + lIlIllIlIIIIIII.field_5965, true));
                    lIlIllIlIIIIIlI.method_22904(0.0, (double)-0.95f, 0.0);
                }
                class_287 lIlIllIIllIllIl = class_289.method_1348().method_1349();
                lIlIllIIllIllIl.method_1328(1, class_290.field_1576);
                class_1159 lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, lIlIllIIlllIIll ? 0.6f : 0.7f, lIlIllIIlllIIll ? 0.23f : 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, lIlIllIIlllIIll ? 1.05f : 1.4f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, -0.37f, lIlIllIIlllIIll ? 1.05f : 1.35f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.37f, lIlIllIIlllIIll ? 1.05f : 1.35f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, -0.15f, lIlIllIIlllIIll ? 0.6f : 0.7f, lIlIllIIlllIIll ? 0.23f : 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.15f, lIlIllIIlllIIll ? 0.6f : 0.7f, lIlIllIIlllIIll ? 0.23f : 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22903();
                lIlIllIlIIIIIlI.method_22904(0.0, lIlIllIIlllIIll ? (double)1.05f : (double)1.4f, 0.0);
                lIlIllIIllIlIll.rotate(lIlIllIlIIIIIlI, lIlIllIIlllIIlI);
                lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.0f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.15f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22909();
                lIlIllIlIIIIIlI.method_22903();
                lIlIllIlIIIIIlI.method_22904((double)0.15f, lIlIllIIlllIIll ? (double)0.6f : (double)0.7f, lIlIllIIlllIIll ? (double)0.23f : 0.0);
                lIlIllIIllIlIll.rotate(lIlIllIlIIIIIlI, lIlIllIIllIlllI);
                lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.0f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, -0.6f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22909();
                lIlIllIlIIIIIlI.method_22903();
                lIlIllIlIIIIIlI.method_22904((double)-0.15f, lIlIllIIlllIIll ? (double)0.6f : (double)0.7f, lIlIllIIlllIIll ? (double)0.23f : 0.0);
                lIlIllIIllIlIll.rotate(lIlIllIlIIIIIlI, lIlIllIIllIllll);
                lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.0f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, -0.6f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22909();
                lIlIllIlIIIIIlI.method_22903();
                lIlIllIlIIIIIlI.method_22904((double)0.37f, lIlIllIIlllIIll ? (double)1.05f : (double)1.35f, 0.0);
                lIlIllIIllIlIll.rotate(lIlIllIlIIIIIlI, lIlIllIIlllIIII);
                lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.0f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, -0.55f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22909();
                lIlIllIlIIIIIlI.method_22903();
                lIlIllIlIIIIIlI.method_22904((double)-0.37f, lIlIllIIlllIIll ? (double)1.05f : (double)1.35f, 0.0);
                lIlIllIIllIlIll.rotate(lIlIllIlIIIIIlI, lIlIllIIlllIIIl);
                lIlIllIIllIllII = lIlIllIlIIIIIlI.method_23760().method_23761();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, 0.0f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIIllIllIl.method_22918(lIlIllIIllIllII, 0.0f, -0.55f, 0.0f).method_1336(lIlIllIIllllllI.getRed(), lIlIllIIllllllI.getGreen(), lIlIllIIllllllI.getBlue(), lIlIllIIllllllI.getAlpha()).method_1344();
                lIlIllIlIIIIIlI.method_22909();
                lIlIllIIllIllIl.method_1326();
                class_286.method_1309((class_287)lIlIllIIllIllIl);
                RenderSystem.enableTexture();
                RenderSystem.disableCull();
                RenderSystem.disableBlend();
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask((boolean)true);
                if (lIlIllIlIIIIIII.method_20232()) {
                    lIlIllIlIIIIIlI.method_22904(0.0, (double)0.95f, 0.0);
                    lIlIllIlIIIIIlI.method_22907(new class_1158(new class_1160(1.0f, 0.0f, 0.0f), 90.0f + lIlIllIlIIIIIII.field_5965, true));
                    lIlIllIlIIIIIlI.method_22904(0.0, (double)-0.35f, 0.0);
                }
                lIlIllIlIIIIIlI.method_22907(new class_1158(new class_1160(0.0f, 1.0f, 0.0f), lIlIllIlIIIIIII.field_6283 + 180.0f, true));
                lIlIllIlIIIIIlI.method_22904(-lIlIllIIlllllIl.field_1352, -lIlIllIIlllllIl.field_1351, -lIlIllIIlllllIl.field_1350);
            }
        });
    }

    private void rotate(class_4587 lIlIllIlIllIIII, class_630 lIlIllIlIlIllll) {
        if (lIlIllIlIlIllll.field_3674 != 0.0f) {
            lIlIllIlIllIIII.method_22907(class_1160.field_20707.method_23626(lIlIllIlIlIllll.field_3674));
        }
        if (lIlIllIlIlIllll.field_3675 != 0.0f) {
            lIlIllIlIllIIII.method_22907(class_1160.field_20704.method_23626(lIlIllIlIlIllll.field_3675));
        }
        if (lIlIllIlIlIllll.field_3654 != 0.0f) {
            lIlIllIlIllIIII.method_22907(class_1160.field_20702.method_23626(lIlIllIlIlIllll.field_3654));
        }
    }
}

