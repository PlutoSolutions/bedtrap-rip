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
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.systems.macros.Macro;
import minegame159.meteorclient.systems.macros.Macros;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public class MacrosTab
extends Tab {
    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return new MacrosScreen(guiTheme, this);
    }

    public MacrosTab() {
        super("Macros");
    }

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof MacrosScreen;
    }

    private static class MacrosScreen
    extends WindowTabScreen {
        @Override
        protected void method_25426() {
            super.method_25426();
            this.clear();
            this.initWidgets();
        }

        private void initWidgets() {
            WWidget wWidget;
            if (Macros.get().getAll().size() > 0) {
                wWidget = this.add(this.theme.table()).expandX().widget();
                for (Macro macro : Macros.get()) {
                    ((WTable)wWidget).add(this.theme.label(String.valueOf(new StringBuilder().append(macro.name).append(" (").append(macro.keybind).append(")"))));
                    WButton wButton = ((WTable)wWidget).add(this.theme.button(GuiRenderer.EDIT)).expandCellX().right().widget();
                    wButton.action = () -> this.lambda$initWidgets$0(macro);
                    WMinus wMinus = ((WTable)wWidget).add(this.theme.minus()).widget();
                    wMinus.action = () -> this.lambda$initWidgets$1(macro);
                    ((WTable)wWidget).row();
                }
            }
            wWidget = this.add(this.theme.button("Create")).expandX().widget();
            ((WButton)wWidget).action = this::lambda$initWidgets$2;
        }

        private void lambda$initWidgets$0(Macro macro) {
            Utils.mc.method_1507((class_437)new MacroEditorScreen(this.theme, macro));
        }

        public MacrosScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
        }

        private void lambda$initWidgets$1(Macro macro) {
            Macros.get().remove(macro);
            this.clear();
            this.initWidgets();
        }

        private void lambda$initWidgets$2() {
            Utils.mc.method_1507((class_437)new MacroEditorScreen(this.theme, null));
        }
    }

    private static class MacroEditorScreen
    extends WindowScreen {
        private boolean binding;
        private final Macro macro;
        private final boolean isNewMacro;
        private WKeybind keybind;

        private void fillMessagesTable(WTable wTable) {
            if (this.macro.messages.isEmpty()) {
                this.macro.addMessage("");
            }
            for (int i = 0; i < this.macro.messages.size(); ++i) {
                WPressable wPressable;
                int n = i;
                WTextBox wTextBox = wTable.add(this.theme.textBox(this.macro.messages.get(i))).minWidth(400.0).expandX().widget();
                wTextBox.action = () -> this.lambda$fillMessagesTable$3(n, wTextBox);
                if (i != this.macro.messages.size() - 1) {
                    wPressable = wTable.add(this.theme.minus()).widget();
                    wPressable.action = () -> this.lambda$fillMessagesTable$4(n);
                } else {
                    wPressable = wTable.add(this.theme.plus()).widget();
                    ((WPlus)wPressable).action = this::lambda$fillMessagesTable$5;
                }
                wTable.row();
                if (-3 <= 0) continue;
                return;
            }
        }

        private boolean onAction(boolean bl, int n) {
            if (this.binding) {
                this.keybind.onAction(bl, n);
                this.binding = false;
                return true;
            }
            return false;
        }

        @EventHandler(priority=200)
        private void onButton(MouseButtonEvent mouseButtonEvent) {
            if (this.onAction(false, mouseButtonEvent.button)) {
                mouseButtonEvent.cancel();
            }
        }

        private void initWidgets(Macro macro) {
            WTable wTable = this.add(this.theme.table()).widget();
            wTable.add(this.theme.label("Name:"));
            WTextBox wTextBox = wTable.add(this.theme.textBox(macro == null ? "" : this.macro.name)).minWidth(400.0).expandX().widget();
            wTextBox.setFocused(true);
            wTextBox.action = () -> this.lambda$initWidgets$0(wTextBox);
            wTable.row();
            wTable.add(this.theme.label("Messages:")).padTop(4.0).top();
            WTable wTable2 = wTable.add(this.theme.table()).widget();
            this.fillMessagesTable(wTable2);
            this.keybind = this.add(this.theme.keybind(this.macro.keybind)).expandX().widget();
            this.keybind.actionOnSet = this::lambda$initWidgets$1;
            WButton wButton = this.add(this.theme.button(this.isNewMacro ? "Add" : "Apply")).expandX().widget();
            this.enterAction = wButton.action = this::lambda$initWidgets$2;
        }

        private void lambda$fillMessagesTable$4(int n) {
            this.macro.removeMessage(n);
            this.clear();
            this.initWidgets(this.macro);
        }

        private void lambda$initWidgets$2() {
            if (this.isNewMacro) {
                if (this.macro.name != null && !this.macro.name.isEmpty() && this.macro.messages.size() > 0 && this.macro.keybind.isSet()) {
                    Macros.get().add(this.macro);
                    this.method_25419();
                }
            } else {
                Macros.get().save();
                this.method_25419();
            }
        }

        @EventHandler(priority=200)
        private void onKey(KeyEvent keyEvent) {
            if (this.onAction(true, keyEvent.key)) {
                keyEvent.cancel();
            }
        }

        private void lambda$fillMessagesTable$5() {
            this.macro.addMessage("");
            this.clear();
            this.initWidgets(this.macro);
        }

        private void lambda$fillMessagesTable$3(int n, WTextBox wTextBox) {
            this.macro.messages.set(n, wTextBox.get().trim());
        }

        private void lambda$initWidgets$1() {
            this.binding = true;
        }

        private void lambda$initWidgets$0(WTextBox wTextBox) {
            this.macro.name = wTextBox.get().trim();
        }

        public MacroEditorScreen(GuiTheme guiTheme, Macro macro) {
            super(guiTheme, macro == null ? "Create Macro" : "Edit Macro");
            this.isNewMacro = macro == null;
            this.macro = this.isNewMacro ? new Macro() : macro;
            this.initWidgets(macro);
        }
    }
}

