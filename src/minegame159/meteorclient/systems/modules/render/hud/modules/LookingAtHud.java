/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3486
 *  net.minecraft.class_3494
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3486;
import net.minecraft.class_3494;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class LookingAtHud
extends DoubleTextHudElement {
    private final /* synthetic */ Setting<Boolean> blockPosition;
    private final /* synthetic */ Setting<Boolean> entityPosition;
    private final /* synthetic */ Setting<Boolean> waterLogged;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    protected String getRight() {
        LookingAtHud lllllllllllllllllllIIlIIlIIlIlII;
        if (lllllllllllllllllllIIlIIlIIlIlII.isInEditor()) {
            return lllllllllllllllllllIIlIIlIIlIlII.blockPosition.get() != false ? "Obsidian [0, 0, 0]" : "Obsidian";
        }
        if (lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
            String lllllllllllllllllllIIlIIlIIlIlll;
            String lllllllllllllllllllIIlIIlIIllIIl = lllllllllllllllllllIIlIIlIIlIlII.mc.field_1687.method_8320(((class_3965)lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765).method_17777()).method_26204().method_9518().getString();
            class_2338 lllllllllllllllllllIIlIIlIIllIII = ((class_3965)lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765).method_17777();
            String string = lllllllllllllllllllIIlIIlIIlIlII.blockPosition.get().booleanValue() ? String.format("%s [%d, %d, %d]", lllllllllllllllllllIIlIIlIIllIIl, lllllllllllllllllllIIlIIlIIllIII.method_10263(), lllllllllllllllllllIIlIIlIIllIII.method_10264(), lllllllllllllllllllIIlIIlIIllIII.method_10260()) : (lllllllllllllllllllIIlIIlIIlIlll = lllllllllllllllllllIIlIIlIIllIIl);
            if (lllllllllllllllllllIIlIIlIIlIlII.waterLogged.get().booleanValue()) {
                lllllllllllllllllllIIlIIlIIlIlll = lllllllllllllllllllIIlIIlIIlIlII.blockPosition.get().booleanValue() ? String.format("%s %s[%d, %d, %d]", lllllllllllllllllllIIlIIlIIllIIl, lllllllllllllllllllIIlIIlIIlIlII.mc.field_1687.method_8316(lllllllllllllllllllIIlIIlIIllIII).method_15767((class_3494)class_3486.field_15517) ? "(Waterlogged) " : "", lllllllllllllllllllIIlIIlIIllIII.method_10263(), lllllllllllllllllllIIlIIlIIllIII.method_10264(), lllllllllllllllllllIIlIIlIIllIII.method_10260()) : lllllllllllllllllllIIlIIlIIllIIl;
            }
            return lllllllllllllllllllIIlIIlIIlIlll;
        }
        if (lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765.method_17783() == class_239.class_240.field_1331) {
            String lllllllllllllllllllIIlIIlIIlIllI = ((class_3966)lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765).method_17782().method_5476().getString();
            class_243 lllllllllllllllllllIIlIIlIIlIlIl = lllllllllllllllllllIIlIIlIIlIlII.mc.field_1765.method_17784();
            return lllllllllllllllllllIIlIIlIIlIlII.entityPosition.get().booleanValue() ? String.format("%s [%d, %d, %d]", lllllllllllllllllllIIlIIlIIlIllI, (int)lllllllllllllllllllIIlIIlIIlIlIl.field_1352, (int)lllllllllllllllllllIIlIIlIIlIlIl.field_1351, (int)lllllllllllllllllllIIlIIlIIlIlIl.field_1350) : lllllllllllllllllllIIlIIlIIlIllI;
        }
        return "";
    }

    public LookingAtHud(HUD lllllllllllllllllllIIlIIlIlIIIII) {
        super(lllllllllllllllllllIIlIIlIlIIIII, "looking-at", "Displays what entity or block you are looking at.", "Looking At: ");
        LookingAtHud lllllllllllllllllllIIlIIlIlIIIIl;
        lllllllllllllllllllIIlIIlIlIIIIl.sgGeneral = lllllllllllllllllllIIlIIlIlIIIIl.settings.getDefaultGroup();
        lllllllllllllllllllIIlIIlIlIIIIl.blockPosition = lllllllllllllllllllIIlIIlIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("block-position").description("Displays block's position.").defaultValue(true).build());
        lllllllllllllllllllIIlIIlIlIIIIl.entityPosition = lllllllllllllllllllIIlIIlIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("entity-position").description("Displays entity's position.").defaultValue(true).build());
        lllllllllllllllllllIIlIIlIlIIIIl.waterLogged = lllllllllllllllllllIIlIIlIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("show-waterlogged-status").description("Displays if a block is waterlogged or not").defaultValue(true).build());
    }
}

