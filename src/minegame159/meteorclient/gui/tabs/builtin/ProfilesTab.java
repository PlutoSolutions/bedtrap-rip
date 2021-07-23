/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs.builtin;

import java.util.List;
import java.util.Objects;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.systems.profiles.Profile;
import minegame159.meteorclient.systems.profiles.Profiles;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;
import org.apache.commons.lang3.StringUtils;

public class ProfilesTab
extends Tab {
    public ProfilesTab() {
        super("Profiles");
    }

    @Override
    protected TabScreen createScreen(GuiTheme guiTheme) {
        return new ProfilesScreen(guiTheme, this);
    }

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof ProfilesScreen;
    }

    private static class EditProfileScreen
    extends WindowScreen {
        private final Profile newProfile;
        private final Runnable action;
        private final Profile oldProfile;
        private final boolean isNew;

        private void lambda$initWidgets$0(WTextBox wTextBox) {
            this.newProfile.name = wTextBox.get();
        }

        private void lambda$initWidgets$3(WCheckbox wCheckbox) {
            this.newProfile.config = wCheckbox.checked;
        }

        public void initWidgets(Profile profile, List<String> list) {
            WTable wTable = this.add(this.theme.table()).expandX().widget();
            wTable.add(this.theme.label("Name:"));
            WTextBox wTextBox = wTable.add(this.theme.textBox(profile.name, this::nameFilter)).minWidth(400.0).expandX().widget();
            wTextBox.action = () -> this.lambda$initWidgets$0(wTextBox);
            wTable.row();
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(this.theme.label("Load on Launch:"));
            WCheckbox wCheckbox = wTable.add(this.theme.checkbox(profile.onLaunch)).widget();
            wCheckbox.action = () -> this.lambda$initWidgets$1(wCheckbox);
            wTable.row();
            wTable.add(this.theme.label("Load when Joining:"));
            WTable wTable2 = wTable.add(this.theme.table()).widget();
            this.fillTable(wTable2, list);
            wTable.row();
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(this.theme.label("Accounts:"));
            WCheckbox wCheckbox2 = wTable.add(this.theme.checkbox(profile.accounts)).widget();
            wCheckbox2.action = () -> this.lambda$initWidgets$2(wCheckbox2);
            wTable.row();
            wTable.add(this.theme.label("Config:"));
            WCheckbox wCheckbox3 = wTable.add(this.theme.checkbox(profile.config)).widget();
            wCheckbox3.action = () -> this.lambda$initWidgets$3(wCheckbox3);
            wTable.row();
            wTable.add(this.theme.label("Friends:"));
            WCheckbox wCheckbox4 = wTable.add(this.theme.checkbox(profile.friends)).widget();
            wCheckbox4.action = () -> this.lambda$initWidgets$4(wCheckbox4);
            wTable.row();
            wTable.add(this.theme.label("Macros:"));
            WCheckbox wCheckbox5 = wTable.add(this.theme.checkbox(profile.macros)).widget();
            wCheckbox5.action = () -> this.lambda$initWidgets$5(wCheckbox5);
            wTable.row();
            wTable.add(this.theme.label("Modules:"));
            WCheckbox wCheckbox6 = wTable.add(this.theme.checkbox(profile.modules)).widget();
            wCheckbox6.action = () -> this.lambda$initWidgets$6(wCheckbox6);
            wTable.row();
            wTable.add(this.theme.label("Waypoints:"));
            WCheckbox wCheckbox7 = wTable.add(this.theme.checkbox(profile.waypoints)).widget();
            wCheckbox7.action = () -> this.lambda$initWidgets$7(wCheckbox7);
            wTable.row();
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            WButton wButton = wTable.add(this.theme.button("Save")).expandX().widget();
            this.enterAction = wButton.action = this::lambda$initWidgets$8;
        }

        private void lambda$fillTable$10(List list, int n) {
            list.remove(n);
            this.clear();
            this.initWidgets(this.newProfile, list);
        }

        private void lambda$initWidgets$2(WCheckbox wCheckbox) {
            this.newProfile.accounts = wCheckbox.checked;
        }

        private void lambda$initWidgets$8() {
            if (this.newProfile.name.isEmpty()) {
                return;
            }
            for (Profile profile : Profiles.get()) {
                if (!this.newProfile.equals(profile) || this.oldProfile.equals(profile)) continue;
                return;
            }
            this.oldProfile.set(this.newProfile);
            if (this.isNew) {
                Profiles.get().add(this.oldProfile);
            } else {
                Profiles.get().save();
            }
            this.method_25419();
        }

        private void lambda$initWidgets$6(WCheckbox wCheckbox) {
            this.newProfile.modules = wCheckbox.checked;
        }

        private void lambda$fillTable$11(List list) {
            list.add("");
            this.clear();
            this.initWidgets(this.newProfile, list);
        }

        private void lambda$initWidgets$1(WCheckbox wCheckbox) {
            this.newProfile.onLaunch = wCheckbox.checked;
        }

        private boolean ipFilter(String string, char c) {
            if (string.contains(":") && c == ':') {
                return false;
            }
            return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '.';
        }

        private void lambda$initWidgets$5(WCheckbox wCheckbox) {
            this.newProfile.macros = wCheckbox.checked;
        }

        public EditProfileScreen(GuiTheme guiTheme, Profile profile, Runnable runnable) {
            super(guiTheme, profile == null ? "New Profile" : "Edit Profile");
            this.isNew = profile == null;
            this.newProfile = new Profile();
            this.oldProfile = this.isNew ? new Profile() : profile;
            this.action = runnable;
            this.newProfile.set(this.oldProfile);
            this.initWidgets(this.oldProfile, this.newProfile.loadOnJoinIps);
        }

        private static void lambda$fillTable$9(WTextBox wTextBox, List list, int n) {
            String string = wTextBox.get().trim();
            if (!string.contains(".") || StringUtils.containsWhitespace((CharSequence)string)) {
                return;
            }
            list.set(n, string);
        }

        @Override
        protected void onClosed() {
            if (this.action != null) {
                this.action.run();
            }
        }

        private void fillTable(WTable wTable, List<String> list) {
            if (list.isEmpty()) {
                list.add("");
            }
            for (int i = 0; i < list.size(); ++i) {
                WPressable wPressable;
                int n = i;
                WTextBox wTextBox = wTable.add(this.theme.textBox(list.get(n), this::ipFilter)).minWidth(400.0).expandX().widget();
                wTextBox.action = () -> EditProfileScreen.lambda$fillTable$9(wTextBox, list, n);
                if (n != list.size() - 1) {
                    wPressable = wTable.add(this.theme.minus()).widget();
                    wPressable.action = () -> this.lambda$fillTable$10(list, n);
                } else {
                    wPressable = wTable.add(this.theme.plus()).widget();
                    ((WPlus)wPressable).action = () -> this.lambda$fillTable$11(list);
                }
                wTable.row();
                if (0 > -1) continue;
                return;
            }
        }

        private void lambda$initWidgets$7(WCheckbox wCheckbox) {
            this.newProfile.waypoints = wCheckbox.checked;
        }

        private boolean nameFilter(String string, char c) {
            return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '-' || c == '.';
        }

        private void lambda$initWidgets$4(WCheckbox wCheckbox) {
            this.newProfile.friends = wCheckbox.checked;
        }
    }

    private static class ProfilesScreen
    extends WindowTabScreen {
        private void initWidget() {
            this.clear();
            WTable wTable = this.add(this.theme.table()).expandX().minWidth(300.0).widget();
            for (Profile profile : Profiles.get()) {
                wTable.add(this.theme.label(profile.name)).expandCellX();
                WButton wButton = wTable.add(this.theme.button("Save")).widget();
                Objects.requireNonNull(profile);
                wButton.action = profile::save;
                WButton wButton2 = wTable.add(this.theme.button("Load")).widget();
                Objects.requireNonNull(profile);
                wButton2.action = profile::load;
                WButton wButton3 = wTable.add(this.theme.button(GuiRenderer.EDIT)).widget();
                wButton3.action = () -> this.lambda$initWidget$0(profile);
                WMinus wMinus = wTable.add(this.theme.minus()).widget();
                wMinus.action = () -> this.lambda$initWidget$1(profile);
                wTable.row();
            }
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            WButton wButton = wTable.add(this.theme.button("Create")).expandX().widget();
            wButton.action = this::lambda$initWidget$2;
        }

        private void lambda$initWidget$1(Profile profile) {
            Profiles.get().remove(profile);
            this.initWidget();
        }

        private void lambda$initWidget$0(Profile profile) {
            Utils.mc.method_1507((class_437)new EditProfileScreen(this.theme, profile, this::initWidget));
        }

        @Override
        protected void method_25426() {
            super.method_25426();
            this.initWidget();
        }

        private void lambda$initWidget$2() {
            Utils.mc.method_1507((class_437)new EditProfileScreen(this.theme, null, this::initWidget));
        }

        public ProfilesScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
        }
    }
}

