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
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.friends.Friend;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_437;

public class FriendsTab
extends Tab {
    @Override
    public boolean isScreen(class_437 lIllllllllIl) {
        return lIllllllllIl instanceof FriendsScreen;
    }

    public FriendsTab() {
        super("Friends");
        FriendsTab llIIIIIIIlll;
    }

    @Override
    public TabScreen createScreen(GuiTheme llIIIIIIIIll) {
        FriendsTab llIIIIIIIIlI;
        return new FriendsScreen(llIIIIIIIIll, llIIIIIIIIlI);
    }

    private static class FriendsScreen
    extends WindowTabScreen {
        public FriendsScreen(GuiTheme llllllllllllllllIllIlIIlllllIlll, Tab llllllllllllllllIllIlIlIIIIIIIII) {
            super(llllllllllllllllIllIlIIlllllIlll, llllllllllllllllIllIlIlIIIIIIIII);
            FriendsScreen llllllllllllllllIllIlIIllllllIII;
            Settings llllllllllllllllIllIlIIlllllllll = new Settings();
            SettingGroup llllllllllllllllIllIlIIllllllllI = llllllllllllllllIllIlIIlllllllll.getDefaultGroup();
            llllllllllllllllIllIlIIllllllllI.add(new ColorSetting.Builder().name("color").description("The color used to show friends.").defaultValue(new SettingColor(0, 255, 180)).onChanged(Friends.get().color::set).onModuleActivated(llllllllllllllllIllIlIIlllIIIIll -> llllllllllllllllIllIlIIlllIIIIll.set(Friends.get().color)).build());
            llllllllllllllllIllIlIIllllllllI.add(new BoolSetting.Builder().name("attack").description("Whether to attack friends.").defaultValue(false).onChanged(llllllllllllllllIllIlIIlllIIIlll -> {
                Friends.get().attack = llllllllllllllllIllIlIIlllIIIlll;
            }).onModuleActivated(llllllllllllllllIllIlIIlllIIlIIl -> llllllllllllllllIllIlIIlllIIlIIl.set(Friends.get().attack)).build());
            llllllllllllllllIllIlIIlllllllll.onActivated();
            llllllllllllllllIllIlIIllllllIII.add(llllllllllllllllIllIlIIlllllIlll.settings(llllllllllllllllIllIlIIlllllllll)).expandX();
            WSection llllllllllllllllIllIlIIlllllllIl = llllllllllllllllIllIlIIllllllIII.add(llllllllllllllllIllIlIIlllllIlll.section("Friends")).expandX().widget();
            WTable llllllllllllllllIllIlIIlllllllII = llllllllllllllllIllIlIIlllllllIl.add(llllllllllllllllIllIlIIlllllIlll.table()).expandX().widget();
            llllllllllllllllIllIlIIllllllIII.fillTable(llllllllllllllllIllIlIIlllllllII);
            WHorizontalList llllllllllllllllIllIlIIllllllIll = llllllllllllllllIllIlIIlllllllIl.add(llllllllllllllllIllIlIIlllllIlll.horizontalList()).expandX().widget();
            WTextBox llllllllllllllllIllIlIIllllllIlI = llllllllllllllllIllIlIIllllllIll.add(llllllllllllllllIllIlIIlllllIlll.textBox("")).minWidth(400.0).expandX().widget();
            llllllllllllllllIllIlIIllllllIlI.setFocused(true);
            WPlus llllllllllllllllIllIlIIllllllIIl = llllllllllllllllIllIlIIllllllIll.add(llllllllllllllllIllIlIIlllllIlll.plus()).widget();
            llllllllllllllllIllIlIIllllllIII.enterAction = llllllllllllllllIllIlIIllllllIIl.action = () -> {
                String llllllllllllllllIllIlIIlllIlIIII = llllllllllllllllIllIlIIllllllIlI.get().trim();
                if (Friends.get().add(new Friend(llllllllllllllllIllIlIIlllIlIIII))) {
                    FriendsScreen llllllllllllllllIllIlIIlllIIllll;
                    llllllllllllllllIllIlIIllllllIlI.set("");
                    llllllllllllllllIllIlIIlllllllII.clear();
                    llllllllllllllllIllIlIIlllIIllll.fillTable(llllllllllllllllIllIlIIlllllllII);
                }
            };
        }

        private void fillTable(WTable llllllllllllllllIllIlIIllllIIlII) {
            for (Friend llllllllllllllllIllIlIIllllIlIII : Friends.get()) {
                FriendsScreen llllllllllllllllIllIlIIllllIIlIl;
                llllllllllllllllIllIlIIllllIIlII.add(llllllllllllllllIllIlIIllllIIlIl.theme.label(llllllllllllllllIllIlIIllllIlIII.name));
                WMinus llllllllllllllllIllIlIIllllIlIIl = llllllllllllllllIllIlIIllllIIlII.add(llllllllllllllllIllIlIIllllIIlIl.theme.minus()).expandCellX().right().widget();
                llllllllllllllllIllIlIIllllIlIIl.action = () -> {
                    FriendsScreen llllllllllllllllIllIlIIlllIlllIl;
                    Friends.get().remove(llllllllllllllllIllIlIIllllIlIII);
                    llllllllllllllllIllIlIIllllIIlII.clear();
                    llllllllllllllllIllIlIIlllIlllIl.fillTable(llllllllllllllllIllIlIIllllIIlII);
                };
                llllllllllllllllIllIlIIllllIIlII.row();
            }
        }
    }
}

