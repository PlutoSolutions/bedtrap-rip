/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 *  org.apache.commons.lang3.StringUtils
 */
package minegame159.meteorclient.gui.tabs.builtin;

import java.util.List;
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
import minegame159.meteorclient.systems.profiles.Profile;
import minegame159.meteorclient.systems.profiles.Profiles;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;
import org.apache.commons.lang3.StringUtils;

public class ProfilesTab
extends Tab {
    public ProfilesTab() {
        super("Profiles");
        ProfilesTab llllllllllllllllllllIlIIIlIIIIll;
    }

    @Override
    protected TabScreen createScreen(GuiTheme llllllllllllllllllllIlIIIIllllII) {
        ProfilesTab llllllllllllllllllllIlIIIIllllll;
        return new ProfilesScreen(llllllllllllllllllllIlIIIIllllII, llllllllllllllllllllIlIIIIllllll);
    }

    @Override
    public boolean isScreen(class_437 llllllllllllllllllllIlIIIIlllIII) {
        return llllllllllllllllllllIlIIIIlllIII instanceof ProfilesScreen;
    }

    private static class EditProfileScreen
    extends WindowScreen {
        private final /* synthetic */ Profile newProfile;
        private final /* synthetic */ Runnable action;
        private final /* synthetic */ Profile oldProfile;
        private final /* synthetic */ boolean isNew;

        public void initWidgets(Profile llllIIIIllIIlI, List<String> llllIIIlIIlIII) {
            EditProfileScreen llllIIIlIIlIll;
            WTable llllIIIlIIIlll = llllIIIlIIlIll.add(llllIIIlIIlIll.theme.table()).expandX().widget();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Name:"));
            WTextBox llllIIIlIIIlIl = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.textBox(llllIIIIllIIlI.name, (arg_0, arg_1) -> llllIIIlIIlIll.nameFilter(arg_0, arg_1))).minWidth(400.0).expandX().widget();
            llllIIIlIIIlIl.action = () -> {
                lllIlllIlIIIIl.newProfile.name = llllIIIlIIIlIl.get();
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.horizontalSeparator()).expandX();
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Load on Launch:"));
            WCheckbox llllIIIlIIIIll = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.onLaunch)).widget();
            llllIIIlIIIIll.action = () -> {
                lllIlllIlIIlll.newProfile.onLaunch = lllIlllIlIlIII.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Load when Joining:"));
            WTable llllIIIlIIIIlI = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.table()).widget();
            llllIIIlIIlIll.fillTable(llllIIIlIIIIlI, llllIIIlIIlIII);
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.horizontalSeparator()).expandX();
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Accounts:"));
            WCheckbox llllIIIlIIIIII = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.accounts)).widget();
            llllIIIlIIIIII.action = () -> {
                lllIlllIlIllIl.newProfile.accounts = lllIlllIlIllII.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Config:"));
            WCheckbox llllIIIIllllll = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.config)).widget();
            llllIIIIllllll.action = () -> {
                lllIlllIllIIll.newProfile.config = lllIlllIllIIlI.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Friends:"));
            WCheckbox llllIIIIlllllI = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.friends)).widget();
            llllIIIIlllllI.action = () -> {
                lllIlllIlllIIl.newProfile.friends = lllIlllIlllIlI.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Macros:"));
            WCheckbox llllIIIIllllII = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.macros)).widget();
            llllIIIIllllII.action = () -> {
                lllIllllIIIIIl.newProfile.macros = lllIllllIIIIII.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Modules:"));
            WCheckbox llllIIIIlllIlI = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.modules)).widget();
            llllIIIIlllIlI.action = () -> {
                lllIllllIIIlll.newProfile.modules = lllIllllIIIllI.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.label("Waypoints:"));
            WCheckbox llllIIIIlllIII = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.checkbox(llllIIIIllIIlI.waypoints)).widget();
            llllIIIIlllIII.action = () -> {
                lllIllllIIlIll.newProfile.waypoints = lllIllllIIlIlI.checked;
            };
            llllIIIlIIIlll.row();
            llllIIIlIIIlll.add(llllIIIlIIlIll.theme.horizontalSeparator()).expandX();
            llllIIIlIIIlll.row();
            WButton llllIIIIllIllI = llllIIIlIIIlll.add(llllIIIlIIlIll.theme.button("Save")).expandX().widget();
            llllIIIlIIlIll.enterAction = llllIIIIllIllI.action = () -> {
                EditProfileScreen lllIllllIlIIll;
                if (lllIllllIlIIll.newProfile.name.isEmpty()) {
                    return;
                }
                for (Profile lllIllllIlIlII : Profiles.get()) {
                    if (!lllIllllIlIIll.newProfile.equals(lllIllllIlIlII) || lllIllllIlIIll.oldProfile.equals(lllIllllIlIlII)) continue;
                    return;
                }
                lllIllllIlIIll.oldProfile.set(lllIllllIlIIll.newProfile);
                if (lllIllllIlIIll.isNew) {
                    Profiles.get().add(lllIllllIlIIll.oldProfile);
                } else {
                    Profiles.get().save();
                }
                lllIllllIlIIll.method_25419();
            };
        }

        private boolean ipFilter(String llllIIIIIlIIll, char llllIIIIIlIIlI) {
            if (llllIIIIIlIIll.contains(":") && llllIIIIIlIIlI == ':') {
                return false;
            }
            return llllIIIIIlIIlI >= 'a' && llllIIIIIlIIlI <= 'z' || llllIIIIIlIIlI >= 'A' && llllIIIIIlIIlI <= 'Z' || llllIIIIIlIIlI >= '0' && llllIIIIIlIIlI <= '9' || llllIIIIIlIIlI == '.';
        }

        public EditProfileScreen(GuiTheme llllIIlIIIIIIl, Profile llllIIIlllllll, Runnable llllIIlIIIIlIl) {
            super(llllIIlIIIIIIl, llllIIIlllllll == null ? "New Profile" : "Edit Profile");
            EditProfileScreen llllIIlIIIlIIl;
            llllIIlIIIlIIl.isNew = llllIIIlllllll == null;
            llllIIlIIIlIIl.newProfile = new Profile();
            llllIIlIIIlIIl.oldProfile = llllIIlIIIlIIl.isNew ? new Profile() : llllIIIlllllll;
            llllIIlIIIlIIl.action = llllIIlIIIIlIl;
            llllIIlIIIlIIl.newProfile.set(llllIIlIIIlIIl.oldProfile);
            llllIIlIIIlIIl.initWidgets(llllIIlIIIlIIl.oldProfile, llllIIlIIIlIIl.newProfile.loadOnJoinIps);
        }

        @Override
        protected void onClosed() {
            EditProfileScreen lllIllllllIlII;
            if (lllIllllllIlII.action != null) {
                lllIllllllIlII.action.run();
            }
        }

        private void fillTable(WTable lllIlllllllIll, List<String> lllIlllllllIlI) {
            if (lllIlllllllIlI.isEmpty()) {
                lllIlllllllIlI.add("");
            }
            for (int llllIIIIIIIIII = 0; llllIIIIIIIIII < lllIlllllllIlI.size(); ++llllIIIIIIIIII) {
                EditProfileScreen lllIllllllllll;
                int llllIIIIIIIIlI = llllIIIIIIIIII;
                WTextBox llllIIIIIIIIIl = lllIlllllllIll.add(lllIllllllllll.theme.textBox(lllIlllllllIlI.get(llllIIIIIIIIlI), (arg_0, arg_1) -> lllIllllllllll.ipFilter(arg_0, arg_1))).minWidth(400.0).expandX().widget();
                llllIIIIIIIIIl.action = () -> {
                    String lllIllllIlllII = llllIIIIIIIIIl.get().trim();
                    if (!lllIllllIlllII.contains(".") || StringUtils.containsWhitespace((CharSequence)lllIllllIlllII)) {
                        return;
                    }
                    lllIlllllllIlI.set(llllIIIIIIIIlI, lllIllllIlllII);
                };
                if (llllIIIIIIIIlI != lllIlllllllIlI.size() - 1) {
                    WMinus llllIIIIIIIlII = lllIlllllllIll.add(lllIllllllllll.theme.minus()).widget();
                    llllIIIIIIIlII.action = () -> {
                        EditProfileScreen lllIlllllIlIIl;
                        lllIlllllllIlI.remove(llllIIIIIIIIlI);
                        lllIlllllIlIIl.clear();
                        lllIlllllIlIIl.initWidgets(lllIlllllIlIIl.newProfile, lllIlllllllIlI);
                    };
                } else {
                    WPlus llllIIIIIIIIll = lllIlllllllIll.add(lllIllllllllll.theme.plus()).widget();
                    llllIIIIIIIIll.action = () -> {
                        EditProfileScreen lllIllllllIIII;
                        lllIlllllllIlI.add("");
                        lllIllllllIIII.clear();
                        lllIllllllIIII.initWidgets(lllIllllllIIII.newProfile, lllIlllllllIlI);
                    };
                }
                lllIlllllllIll.row();
            }
        }

        private boolean nameFilter(String llllIIIlllIIlI, char llllIIIlllIIII) {
            return llllIIIlllIIII >= 'a' && llllIIIlllIIII <= 'z' || llllIIIlllIIII >= 'A' && llllIIIlllIIII <= 'Z' || llllIIIlllIIII >= '0' && llllIIIlllIIII <= '9' || llllIIIlllIIII == '-' || llllIIIlllIIII == '.';
        }
    }

    private static class ProfilesScreen
    extends WindowTabScreen {
        private void initWidget() {
            ProfilesScreen lllllllllllllllllIIlllIlIIlIIIlI;
            lllllllllllllllllIIlllIlIIlIIIlI.clear();
            WTable lllllllllllllllllIIlllIlIIlIIIIl = lllllllllllllllllIIlllIlIIlIIIlI.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.table()).expandX().minWidth(300.0).widget();
            for (Profile lllllllllllllllllIIlllIlIIlIIIll : Profiles.get()) {
                lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.label(lllllllllllllllllIIlllIlIIlIIIll.name)).expandCellX();
                WButton lllllllllllllllllIIlllIlIIlIIlll = lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.button("Save")).widget();
                lllllllllllllllllIIlllIlIIlIIlll.action = lllllllllllllllllIIlllIlIIlIIIll::save;
                WButton lllllllllllllllllIIlllIlIIlIIllI = lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.button("Load")).widget();
                lllllllllllllllllIIlllIlIIlIIllI.action = lllllllllllllllllIIlllIlIIlIIIll::load;
                WButton lllllllllllllllllIIlllIlIIlIIlIl = lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.button(GuiRenderer.EDIT)).widget();
                lllllllllllllllllIIlllIlIIlIIlIl.action = () -> {
                    ProfilesScreen lllllllllllllllllIIlllIlIIIIlIlI;
                    Utils.mc.method_1507((class_437)new EditProfileScreen(lllllllllllllllllIIlllIlIIIIlIlI.theme, lllllllllllllllllIIlllIlIIlIIIll, lllllllllllllllllIIlllIlIIIIlIlI::initWidget));
                };
                WMinus lllllllllllllllllIIlllIlIIlIIlII = lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.minus()).widget();
                lllllllllllllllllIIlllIlIIlIIlII.action = () -> {
                    ProfilesScreen lllllllllllllllllIIlllIlIIIlIIII;
                    Profiles.get().remove(lllllllllllllllllIIlllIlIIlIIIll);
                    lllllllllllllllllIIlllIlIIIlIIII.initWidget();
                };
                lllllllllllllllllIIlllIlIIlIIIIl.row();
            }
            lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.horizontalSeparator()).expandX();
            lllllllllllllllllIIlllIlIIlIIIIl.row();
            WButton lllllllllllllllllIIlllIlIIlIIIII = lllllllllllllllllIIlllIlIIlIIIIl.add(lllllllllllllllllIIlllIlIIlIIIlI.theme.button("Create")).expandX().widget();
            lllllllllllllllllIIlllIlIIlIIIII.action = () -> {
                ProfilesScreen lllllllllllllllllIIlllIlIIIlIllI;
                Utils.mc.method_1507((class_437)new EditProfileScreen(lllllllllllllllllIIlllIlIIIlIllI.theme, null, lllllllllllllllllIIlllIlIIIlIllI::initWidget));
            };
        }

        @Override
        protected void method_25426() {
            ProfilesScreen lllllllllllllllllIIlllIlIIllIIIl;
            super.method_25426();
            lllllllllllllllllIIlllIlIIllIIIl.initWidget();
        }

        public ProfilesScreen(GuiTheme lllllllllllllllllIIlllIlIIllIlII, Tab lllllllllllllllllIIlllIlIIllIllI) {
            super(lllllllllllllllllIIlllIlIIllIlII, lllllllllllllllllIIlllIlIIllIllI);
            ProfilesScreen lllllllllllllllllIIlllIlIIllIlIl;
        }
    }
}

