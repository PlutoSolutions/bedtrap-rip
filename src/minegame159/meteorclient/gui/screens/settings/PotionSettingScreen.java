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
    private void lambda$new$0(PotionSetting potionSetting, MyPotion myPotion) {
        potionSetting.set(myPotion);
        this.method_25419();
    }

    public PotionSettingScreen(GuiTheme guiTheme, PotionSetting potionSetting) {
        super(guiTheme, "Select potion");
        WTable wTable = this.add(guiTheme.table()).expandX().widget();
        for (MyPotion myPotion : MyPotion.values()) {
            wTable.add(guiTheme.itemWithLabel(myPotion.potion, myPotion.potion.method_7964().getString()));
            WButton wButton = wTable.add(guiTheme.button("Select")).widget();
            wButton.action = () -> this.lambda$new$0(potionSetting, myPotion);
            wTable.row();
        }
    }
}

