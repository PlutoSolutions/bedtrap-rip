/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.systems.proxies.Proxies;
import minegame159.meteorclient.systems.proxies.Proxy;
import minegame159.meteorclient.systems.proxies.ProxyType;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public class ProxiesScreen
extends WindowScreen {
    private final List<WCheckbox> checkboxes = new ArrayList<WCheckbox>();

    @Override
    protected void method_25426() {
        super.method_25426();
        this.initWidgets();
    }

    private void lambda$initWidgets$0(WCheckbox wCheckbox, Proxy proxy, int n) {
        boolean bl = wCheckbox.checked;
        Proxies.get().setEnabled(proxy, bl);
        for (WCheckbox wCheckbox2 : this.checkboxes) {
            wCheckbox2.checked = false;
        }
        this.checkboxes.get((int)n).checked = bl;
    }

    private void lambda$initWidgets$1(Proxy proxy) {
        this.openEditProxyScreen(proxy);
    }

    private void lambda$initWidgets$3() {
        this.openEditProxyScreen(null);
    }

    protected void openEditProxyScreen(Proxy proxy) {
        Utils.mc.method_1507((class_437)new EditProxyScreen(this.theme, proxy));
    }

    private void initWidgets() {
        this.clear();
        this.checkboxes.clear();
        WTable wTable = this.add(this.theme.table()).expandX().widget();
        int n = 0;
        for (Proxy proxy : Proxies.get()) {
            int n2 = n++;
            WCheckbox wCheckbox = wTable.add(this.theme.checkbox(proxy.enabled)).widget();
            this.checkboxes.add(wCheckbox);
            wCheckbox.action = () -> this.lambda$initWidgets$0(wCheckbox, proxy, n2);
            WLabel wLabel = wTable.add(this.theme.label(proxy.name)).widget();
            wLabel.color = this.theme.textColor();
            WLabel wLabel2 = wTable.add(this.theme.label(String.valueOf(new StringBuilder().append("(").append((Object)proxy.type).append(")")))).widget();
            wLabel2.color = this.theme.textSecondaryColor();
            WHorizontalList wHorizontalList = wTable.add(this.theme.horizontalList()).expandCellX().widget();
            wHorizontalList.spacing = 0.0;
            wHorizontalList.add(this.theme.label(proxy.ip));
            wHorizontalList.add(this.theme.label((String)":")).widget().color = this.theme.textSecondaryColor();
            wHorizontalList.add(this.theme.label(Integer.toString(proxy.port)));
            WButton wButton = wTable.add(this.theme.button(GuiRenderer.EDIT)).widget();
            wButton.action = () -> this.lambda$initWidgets$1(proxy);
            WMinus wMinus = wTable.add(this.theme.minus()).widget();
            wMinus.action = () -> this.lambda$initWidgets$2(proxy);
            wTable.row();
        }
        WButton wButton = this.add(this.theme.button("New")).expandX().widget();
        wButton.action = this::lambda$initWidgets$3;
    }

    public ProxiesScreen(GuiTheme guiTheme) {
        super(guiTheme, "Proxies");
    }

    private void lambda$initWidgets$2(Proxy proxy) {
        Proxies.get().remove(proxy);
        this.initWidgets();
    }

    protected static class EditProxyScreen
    extends WindowScreen {
        public EditProxyScreen(GuiTheme guiTheme, Proxy proxy) {
            super(guiTheme, proxy == null ? "New Proxy" : "Edit Proxy");
            boolean bl = proxy == null;
            Proxy proxy2 = proxy == null ? new Proxy() : proxy;
            WTable wTable = this.add(guiTheme.table()).expandX().widget();
            wTable.add(guiTheme.label("Proxy Name:"));
            WTextBox wTextBox = wTable.add(guiTheme.textBox(proxy2.name)).expandX().widget();
            wTextBox.action = () -> EditProxyScreen.lambda$new$0(proxy2, wTextBox);
            wTable.row();
            wTable.add(guiTheme.label("Type:"));
            WDropdown<ProxyType> wDropdown = wTable.add(guiTheme.dropdown(proxy2.type)).widget();
            wDropdown.action = () -> EditProxyScreen.lambda$new$1(proxy2, wDropdown);
            wTable.row();
            wTable.add(guiTheme.label("IP:"));
            WTextBox wTextBox2 = wTable.add(guiTheme.textBox(proxy2.ip)).minWidth(400.0).expandX().widget();
            wTextBox2.action = () -> EditProxyScreen.lambda$new$2(proxy2, wTextBox2);
            wTable.row();
            wTable.add(guiTheme.label("Port:"));
            WIntEdit wIntEdit = wTable.add(guiTheme.intEdit(proxy2.port, 0, 0)).expandX().widget();
            wIntEdit.min = 0;
            wIntEdit.max = 65535;
            wIntEdit.action = () -> EditProxyScreen.lambda$new$3(proxy2, wIntEdit);
            this.add(guiTheme.horizontalSeparator("Optional")).expandX().widget();
            WTable wTable2 = this.add(guiTheme.table()).expandX().widget();
            wTable2.add(guiTheme.label("Username:"));
            WTextBox wTextBox3 = wTable2.add(guiTheme.textBox(proxy2.username)).expandX().widget();
            wTextBox3.action = () -> EditProxyScreen.lambda$new$4(proxy2, wTextBox3);
            wTable2.row();
            wTable2.add(guiTheme.label("Password:"));
            WTextBox wTextBox4 = wTable2.add(guiTheme.textBox(proxy2.password)).expandX().widget();
            wTextBox4.action = () -> EditProxyScreen.lambda$new$5(proxy2, wTextBox4);
            this.add(guiTheme.horizontalSeparator()).expandX();
            WButton wButton = this.add(guiTheme.button(bl ? "Add" : "Save")).expandX().widget();
            this.enterAction = wButton.action = () -> this.lambda$new$6(proxy2, bl);
        }

        private static void lambda$new$3(Proxy proxy, WIntEdit wIntEdit) {
            proxy.port = wIntEdit.get();
        }

        private static void lambda$new$0(Proxy proxy, WTextBox wTextBox) {
            proxy.name = wTextBox.get();
        }

        private static void lambda$new$5(Proxy proxy, WTextBox wTextBox) {
            proxy.password = wTextBox.get();
        }

        private static void lambda$new$1(Proxy proxy, WDropdown wDropdown) {
            proxy.type = (ProxyType)((Object)wDropdown.get());
        }

        private static void lambda$new$4(Proxy proxy, WTextBox wTextBox) {
            proxy.username = wTextBox.get();
        }

        private static void lambda$new$2(Proxy proxy, WTextBox wTextBox) {
            proxy.ip = wTextBox.get();
        }

        private void lambda$new$6(Proxy proxy, boolean bl) {
            if (proxy.isValid() && (!bl || Proxies.get().add(proxy))) {
                this.method_25419();
            }
        }
    }
}

