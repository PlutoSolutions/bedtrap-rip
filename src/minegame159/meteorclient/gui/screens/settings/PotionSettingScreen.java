/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens.settings;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.PotionSetting;
import minegame159.meteorclient.utils.misc.MyPotion;

public class PotionSettingScreen
extends WindowScreen {
    public PotionSettingScreen(GuiTheme lllIIIlIIlIIII, PotionSetting lllIIIlIIIlIll) {
        super(lllIIIlIIlIIII, "Select potion");
        PotionSettingScreen lllIIIlIIIllIl;
        WTable lllIIIlIIIlllI = lllIIIlIIIllIl.add(lllIIIlIIlIIII.table()).expandX().widget();
        for (MyPotion lllIIIlIIlIIlI : MyPotion.values()) {
            lllIIIlIIIlllI.add(lllIIIlIIlIIII.itemWithLabel(lllIIIlIIlIIlI.potion, lllIIIlIIlIIlI.potion.method_7964().getString()));
            WButton lllIIIlIIlIIll = lllIIIlIIIlllI.add(lllIIIlIIlIIII.button("Select")).widget();
            lllIIIlIIlIIll.action = () -> {
                PotionSettingScreen lllIIIlIIIIIIl;
                lllIIIlIIIlIll.set(lllIIIlIIlIIlI);
                lllIIIlIIIIIIl.method_25419();
            };
            lllIIIlIIIlllI.row();
        }
    }
}

