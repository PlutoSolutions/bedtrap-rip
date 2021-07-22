/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.tabs.builtin;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.friends.Friend;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_437;

public class FriendsTab
extends Tab {
    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof FriendsScreen;
    }

    public FriendsTab() {
        super("Friends");
    }

    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return new FriendsScreen(guiTheme, this);
    }

    private static class FriendsScreen
    extends WindowTabScreen {
        public FriendsScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
            Settings settings = new Settings();
            SettingGroup settingGroup = settings.getDefaultGroup();
            settingGroup.add(new ColorSetting.Builder().name("color").description("The color used to show friends.").defaultValue(new SettingColor(0, 255, 180)).onChanged(Friends.get().color::set).onModuleActivated(FriendsScreen::lambda$new$0).build());
            settingGroup.add(new BoolSetting.Builder().name("attack").description("Whether to attack friends.").defaultValue(false).onChanged(FriendsScreen::lambda$new$1).onModuleActivated(FriendsScreen::lambda$new$2).build());
            settings.onActivated();
            this.add(guiTheme.settings(settings)).expandX();
            WSection wSection = this.add(guiTheme.section("Friends")).expandX().widget();
            WTable wTable = wSection.add(guiTheme.table()).expandX().widget();
            this.fillTable(wTable);
            WHorizontalList wHorizontalList = wSection.add(guiTheme.horizontalList()).expandX().widget();
            WTextBox wTextBox = wHorizontalList.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
            wTextBox.setFocused(true);
            WPlus wPlus = wHorizontalList.add(guiTheme.plus()).widget();
            this.enterAction = wPlus.action = () -> this.lambda$new$3(wTextBox, wTable);
        }

        private void fillTable(WTable wTable) {
            for (Friend friend : Friends.get()) {
                wTable.add(this.theme.label(friend.name));
                WMinus wMinus = wTable.add(this.theme.minus()).expandCellX().right().widget();
                wMinus.action = () -> this.lambda$fillTable$4(friend, wTable);
                wTable.row();
            }
        }

        private static void lambda$new$0(Setting setting) {
            setting.set(Friends.get().color);
        }

        private void lambda$fillTable$4(Friend friend, WTable wTable) {
            Friends.get().remove(friend);
            wTable.clear();
            this.fillTable(wTable);
        }

        private static void lambda$new$1(Boolean bl) {
            Friends.get().attack = bl;
        }

        private void lambda$new$3(WTextBox wTextBox, WTable wTable) {
            String string = wTextBox.get().trim();
            if (Friends.get().add(new Friend(string))) {
                wTextBox.set("");
                wTable.clear();
                this.fillTable(wTable);
            }
        }

        private static void lambda$new$2(Setting setting) {
            setting.set(Friends.get().attack);
        }
    }
}

