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
    private final Setting<Boolean> blockPosition;
    private final Setting<Boolean> entityPosition;
    private final Setting<Boolean> waterLogged;
    private final SettingGroup sgGeneral;

    @Override
    protected String getRight() {
        if (this.isInEditor()) {
            return this.blockPosition.get() != false ? "Obsidian [0, 0, 0]" : "Obsidian";
        }
        if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
            String string;
            String string2 = this.mc.field_1687.method_8320(((class_3965)this.mc.field_1765).method_17777()).method_26204().method_9518().getString();
            class_2338 class_23382 = ((class_3965)this.mc.field_1765).method_17777();
            String string3 = this.blockPosition.get().booleanValue() ? String.format("%s [%d, %d, %d]", string2, class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260()) : (string = string2);
            if (this.waterLogged.get().booleanValue()) {
                string = this.blockPosition.get().booleanValue() ? String.format("%s %s[%d, %d, %d]", string2, this.mc.field_1687.method_8316(class_23382).method_15767((class_3494)class_3486.field_15517) ? "(Waterlogged) " : "", class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260()) : string2;
            }
            return string;
        }
        if (this.mc.field_1765.method_17783() == class_239.class_240.field_1331) {
            String string = ((class_3966)this.mc.field_1765).method_17782().method_5476().getString();
            class_243 class_2432 = this.mc.field_1765.method_17784();
            return this.entityPosition.get().booleanValue() ? String.format("%s [%d, %d, %d]", string, (int)class_2432.field_1352, (int)class_2432.field_1351, (int)class_2432.field_1350) : string;
        }
        return "";
    }

    public LookingAtHud(HUD hUD) {
        super(hUD, "looking-at", "Displays what entity or block you are looking at.", "Looking At: ");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.blockPosition = this.sgGeneral.add(new BoolSetting.Builder().name("block-position").description("Displays block's position.").defaultValue(true).build());
        this.entityPosition = this.sgGeneral.add(new BoolSetting.Builder().name("entity-position").description("Displays entity's position.").defaultValue(true).build());
        this.waterLogged = this.sgGeneral.add(new BoolSetting.Builder().name("show-waterlogged-status").description("Displays if a block is waterlogged or not").defaultValue(true).build());
    }
}

