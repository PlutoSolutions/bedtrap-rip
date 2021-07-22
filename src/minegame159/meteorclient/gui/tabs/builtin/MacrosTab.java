/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.tabs.builtin;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.gui.widgets.WKeybind;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.systems.macros.Macro;
import minegame159.meteorclient.systems.macros.Macros;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public class MacrosTab
extends Tab {
    @Override
    public TabScreen createScreen(GuiTheme lIlIIIIllIIl) {
        MacrosTab lIlIIIIllIlI;
        return new MacrosScreen(lIlIIIIllIIl, lIlIIIIllIlI);
    }

    public MacrosTab() {
        super("Macros");
        MacrosTab lIlIIIIlllIl;
    }

    @Override
    public boolean isScreen(class_437 lIlIIIIlIlII) {
        return lIlIIIIlIlII instanceof MacrosScreen;
    }

    private static class MacrosScreen
    extends WindowTabScreen {
        @Override
        protected void method_25426() {
            MacrosScreen llllllllllllllllllIIlIIlllllIlll;
            super.method_25426();
            llllllllllllllllllIIlIIlllllIlll.clear();
            llllllllllllllllllIIlIIlllllIlll.initWidgets();
        }

        private void initWidgets() {
            MacrosScreen llllllllllllllllllIIlIIllllIllII;
            if (Macros.get().getAll().size() > 0) {
                WTable llllllllllllllllllIIlIIllllIllIl = llllllllllllllllllIIlIIllllIllII.add(llllllllllllllllllIIlIIllllIllII.theme.table()).expandX().widget();
                for (Macro llllllllllllllllllIIlIIllllIlllI : Macros.get()) {
                    llllllllllllllllllIIlIIllllIllIl.add(llllllllllllllllllIIlIIllllIllII.theme.label(String.valueOf(new StringBuilder().append(llllllllllllllllllIIlIIllllIlllI.name).append(" (").append(llllllllllllllllllIIlIIllllIlllI.keybind).append(")"))));
                    WButton llllllllllllllllllIIlIIlllllIIII = llllllllllllllllllIIlIIllllIllIl.add(llllllllllllllllllIIlIIllllIllII.theme.button(GuiRenderer.EDIT)).expandCellX().right().widget();
                    llllllllllllllllllIIlIIlllllIIII.action = () -> {
                        MacrosScreen llllllllllllllllllIIlIIlllIllIIl;
                        Utils.mc.method_1507((class_437)new MacroEditorScreen(llllllllllllllllllIIlIIlllIllIIl.theme, llllllllllllllllllIIlIIllllIlllI));
                    };
                    WMinus llllllllllllllllllIIlIIllllIllll = llllllllllllllllllIIlIIllllIllIl.add(llllllllllllllllllIIlIIllllIllII.theme.minus()).widget();
                    llllllllllllllllllIIlIIllllIllll.action = () -> {
                        MacrosScreen llllllllllllllllllIIlIIlllIlllIl;
                        Macros.get().remove(llllllllllllllllllIIlIIllllIlllI);
                        llllllllllllllllllIIlIIlllIlllIl.clear();
                        llllllllllllllllllIIlIIlllIlllIl.initWidgets();
                    };
                    llllllllllllllllllIIlIIllllIllIl.row();
                }
            }
            WButton llllllllllllllllllIIlIIllllIlIll = llllllllllllllllllIIlIIllllIllII.add(llllllllllllllllllIIlIIllllIllII.theme.button("Create")).expandX().widget();
            llllllllllllllllllIIlIIllllIlIll.action = () -> {
                MacrosScreen llllllllllllllllllIIlIIllllIIIll;
                Utils.mc.method_1507((class_437)new MacroEditorScreen(llllllllllllllllllIIlIIllllIIIll.theme, null));
            };
        }

        public MacrosScreen(GuiTheme llllllllllllllllllIIlIIllllllIll, Tab llllllllllllllllllIIlIIlllllllIl) {
            super(llllllllllllllllllIIlIIllllllIll, llllllllllllllllllIIlIIlllllllIl);
            MacrosScreen llllllllllllllllllIIlIIlllllllll;
        }
    }

    private static class MacroEditorScreen
    extends WindowScreen {
        private /* synthetic */ boolean binding;
        private final /* synthetic */ Macro macro;
        private final /* synthetic */ boolean isNewMacro;
        private /* synthetic */ WKeybind keybind;

        private void fillMessagesTable(WTable llllllllllllllllllIIllIllIllllIl) {
            MacroEditorScreen llllllllllllllllllIIllIllIlllllI;
            if (llllllllllllllllllIIllIllIlllllI.macro.messages.isEmpty()) {
                llllllllllllllllllIIllIllIlllllI.macro.addMessage("");
            }
            for (int llllllllllllllllllIIllIlllIIIIIl = 0; llllllllllllllllllIIllIlllIIIIIl < llllllllllllllllllIIllIllIlllllI.macro.messages.size(); ++llllllllllllllllllIIllIlllIIIIIl) {
                int llllllllllllllllllIIllIlllIIIIll = llllllllllllllllllIIllIlllIIIIIl;
                WTextBox llllllllllllllllllIIllIlllIIIIlI = llllllllllllllllllIIllIllIllllIl.add(llllllllllllllllllIIllIllIlllllI.theme.textBox(llllllllllllllllllIIllIllIlllllI.macro.messages.get(llllllllllllllllllIIllIlllIIIIIl))).minWidth(400.0).expandX().widget();
                llllllllllllllllllIIllIlllIIIIlI.action = () -> {
                    MacroEditorScreen llllllllllllllllllIIllIllIIlIlll;
                    llllllllllllllllllIIllIllIIlIlll.macro.messages.set(llllllllllllllllllIIllIlllIIIIll, llllllllllllllllllIIllIlllIIIIlI.get().trim());
                };
                if (llllllllllllllllllIIllIlllIIIIIl != llllllllllllllllllIIllIllIlllllI.macro.messages.size() - 1) {
                    WMinus llllllllllllllllllIIllIlllIIIlIl = llllllllllllllllllIIllIllIllllIl.add(llllllllllllllllllIIllIllIlllllI.theme.minus()).widget();
                    llllllllllllllllllIIllIlllIIIlIl.action = () -> {
                        MacroEditorScreen llllllllllllllllllIIllIllIIllllI;
                        llllllllllllllllllIIllIllIIllllI.macro.removeMessage(llllllllllllllllllIIllIlllIIIIll);
                        llllllllllllllllllIIllIllIIllllI.clear();
                        llllllllllllllllllIIllIllIIllllI.initWidgets(llllllllllllllllllIIllIllIIllllI.macro);
                    };
                } else {
                    WPlus llllllllllllllllllIIllIlllIIIlII = llllllllllllllllllIIllIllIllllIl.add(llllllllllllllllllIIllIllIlllllI.theme.plus()).widget();
                    llllllllllllllllllIIllIlllIIIlII.action = () -> {
                        MacroEditorScreen llllllllllllllllllIIllIllIlIIIlI;
                        llllllllllllllllllIIllIllIlIIIlI.macro.addMessage("");
                        llllllllllllllllllIIllIllIlIIIlI.clear();
                        llllllllllllllllllIIllIllIlIIIlI.initWidgets(llllllllllllllllllIIllIllIlIIIlI.macro);
                    };
                }
                llllllllllllllllllIIllIllIllllIl.row();
            }
        }

        private boolean onAction(boolean llllllllllllllllllIIllIllIlIlIII, int llllllllllllllllllIIllIllIlIIlII) {
            MacroEditorScreen llllllllllllllllllIIllIllIlIIllI;
            if (llllllllllllllllllIIllIllIlIIllI.binding) {
                llllllllllllllllllIIllIllIlIIllI.keybind.onAction(llllllllllllllllllIIllIllIlIlIII, llllllllllllllllllIIllIllIlIIlII);
                llllllllllllllllllIIllIllIlIIllI.binding = false;
                return true;
            }
            return false;
        }

        @EventHandler(priority=200)
        private void onButton(MouseButtonEvent llllllllllllllllllIIllIllIlIllIl) {
            MacroEditorScreen llllllllllllllllllIIllIllIlIlllI;
            if (llllllllllllllllllIIllIllIlIlllI.onAction(false, llllllllllllllllllIIllIllIlIllIl.button)) {
                llllllllllllllllllIIllIllIlIllIl.cancel();
            }
        }

        private void initWidgets(Macro llllllllllllllllllIIllIlllIlIllI) {
            MacroEditorScreen llllllllllllllllllIIllIlllIlIIIl;
            WTable llllllllllllllllllIIllIlllIlIlIl = llllllllllllllllllIIllIlllIlIIIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.table()).widget();
            llllllllllllllllllIIllIlllIlIlIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.label("Name:"));
            WTextBox llllllllllllllllllIIllIlllIlIlII = llllllllllllllllllIIllIlllIlIlIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.textBox(llllllllllllllllllIIllIlllIlIllI == null ? "" : llllllllllllllllllIIllIlllIlIIIl.macro.name)).minWidth(400.0).expandX().widget();
            llllllllllllllllllIIllIlllIlIlII.setFocused(true);
            llllllllllllllllllIIllIlllIlIlII.action = () -> {
                llllllllllllllllllIIllIllIIIIlll.macro.name = llllllllllllllllllIIllIlllIlIlII.get().trim();
            };
            llllllllllllllllllIIllIlllIlIlIl.row();
            llllllllllllllllllIIllIlllIlIlIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.label("Messages:")).padTop(4.0).top();
            WTable llllllllllllllllllIIllIlllIlIIll = llllllllllllllllllIIllIlllIlIlIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.table()).widget();
            llllllllllllllllllIIllIlllIlIIIl.fillMessagesTable(llllllllllllllllllIIllIlllIlIIll);
            llllllllllllllllllIIllIlllIlIIIl.keybind = llllllllllllllllllIIllIlllIlIIIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.keybind(llllllllllllllllllIIllIlllIlIIIl.macro.keybind)).expandX().widget();
            llllllllllllllllllIIllIlllIlIIIl.keybind.actionOnSet = () -> {
                llllllllllllllllllIIllIllIIIllIl.binding = true;
            };
            WButton llllllllllllllllllIIllIlllIlIIlI = llllllllllllllllllIIllIlllIlIIIl.add(llllllllllllllllllIIllIlllIlIIIl.theme.button(llllllllllllllllllIIllIlllIlIIIl.isNewMacro ? "Add" : "Apply")).expandX().widget();
            llllllllllllllllllIIllIlllIlIIIl.enterAction = llllllllllllllllllIIllIlllIlIIlI.action = () -> {
                MacroEditorScreen llllllllllllllllllIIllIllIIIllll;
                if (llllllllllllllllllIIllIllIIIllll.isNewMacro) {
                    if (llllllllllllllllllIIllIllIIIllll.macro.name != null && !llllllllllllllllllIIllIllIIIllll.macro.name.isEmpty() && llllllllllllllllllIIllIllIIIllll.macro.messages.size() > 0 && llllllllllllllllllIIllIllIIIllll.macro.keybind.isSet()) {
                        Macros.get().add(llllllllllllllllllIIllIllIIIllll.macro);
                        llllllllllllllllllIIllIllIIIllll.method_25419();
                    }
                } else {
                    Macros.get().save();
                    llllllllllllllllllIIllIllIIIllll.method_25419();
                }
            };
        }

        @EventHandler(priority=200)
        private void onKey(KeyEvent llllllllllllllllllIIllIllIllIlIl) {
            MacroEditorScreen llllllllllllllllllIIllIllIllIlII;
            if (llllllllllllllllllIIllIllIllIlII.onAction(true, llllllllllllllllllIIllIllIllIlIl.key)) {
                llllllllllllllllllIIllIllIllIlIl.cancel();
            }
        }

        public MacroEditorScreen(GuiTheme llllllllllllllllllIIllIlllIlllll, Macro llllllllllllllllllIIllIllllIIIIl) {
            super(llllllllllllllllllIIllIlllIlllll, llllllllllllllllllIIllIllllIIIIl == null ? "Create Macro" : "Edit Macro");
            MacroEditorScreen llllllllllllllllllIIllIllllIIIll;
            llllllllllllllllllIIllIllllIIIll.isNewMacro = llllllllllllllllllIIllIllllIIIIl == null;
            llllllllllllllllllIIllIllllIIIll.macro = llllllllllllllllllIIllIllllIIIll.isNewMacro ? new Macro() : llllllllllllllllllIIllIllllIIIIl;
            llllllllllllllllllIIllIllllIIIll.initWidgets(llllllllllllllllllIIllIllllIIIIl);
        }
    }
}

