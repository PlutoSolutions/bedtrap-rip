/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
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
    private final /* synthetic */ List<WCheckbox> checkboxes;

    @Override
    protected void method_25426() {
        ProxiesScreen llllllllllllllllllllIIllIlllIIlI;
        super.method_25426();
        llllllllllllllllllllIIllIlllIIlI.initWidgets();
    }

    protected void openEditProxyScreen(Proxy llllllllllllllllllllIIllIlllIllI) {
        ProxiesScreen llllllllllllllllllllIIllIlllIlIl;
        Utils.mc.method_1507((class_437)new EditProxyScreen(llllllllllllllllllllIIllIlllIlIl.theme, llllllllllllllllllllIIllIlllIllI));
    }

    private void initWidgets() {
        ProxiesScreen llllllllllllllllllllIIllIlIIllIl;
        llllllllllllllllllllIIllIlIIllIl.clear();
        llllllllllllllllllllIIllIlIIllIl.checkboxes.clear();
        WTable llllllllllllllllllllIIllIlIIllII = llllllllllllllllllllIIllIlIIllIl.add(llllllllllllllllllllIIllIlIIllIl.theme.table()).expandX().widget();
        int llllllllllllllllllllIIllIlIIlIll = 0;
        for (Proxy llllllllllllllllllllIIllIlIIlllI : Proxies.get()) {
            int llllllllllllllllllllIIllIlIlIlIl = llllllllllllllllllllIIllIlIIlIll++;
            WCheckbox llllllllllllllllllllIIllIlIlIlII = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.checkbox(llllllllllllllllllllIIllIlIIlllI.enabled)).widget();
            llllllllllllllllllllIIllIlIIllIl.checkboxes.add(llllllllllllllllllllIIllIlIlIlII);
            llllllllllllllllllllIIllIlIlIlII.action = () -> {
                ProxiesScreen llllllllllllllllllllIIllIIlIIllI;
                boolean llllllllllllllllllllIIllIIlIIIlI = llllllllllllllllllllIIllIIlIIIII.checked;
                Proxies.get().setEnabled(llllllllllllllllllllIIllIlIIlllI, llllllllllllllllllllIIllIIlIIIlI);
                for (WCheckbox llllllllllllllllllllIIllIIlIIlll : llllllllllllllllllllIIllIIlIIllI.checkboxes) {
                    llllllllllllllllllllIIllIIlIIlll.checked = false;
                }
                llllllllllllllllllllIIllIIlIIllI.checkboxes.get((int)llllllllllllllllllllIIllIIIllllI).checked = llllllllllllllllllllIIllIIlIIIlI;
            };
            WLabel llllllllllllllllllllIIllIlIlIIll = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.label(llllllllllllllllllllIIllIlIIlllI.name)).widget();
            llllllllllllllllllllIIllIlIlIIll.color = llllllllllllllllllllIIllIlIIllIl.theme.textColor();
            WLabel llllllllllllllllllllIIllIlIlIIlI = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.label(String.valueOf(new StringBuilder().append("(").append((Object)llllllllllllllllllllIIllIlIIlllI.type).append(")")))).widget();
            llllllllllllllllllllIIllIlIlIIlI.color = llllllllllllllllllllIIllIlIIllIl.theme.textSecondaryColor();
            WHorizontalList llllllllllllllllllllIIllIlIlIIIl = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.horizontalList()).expandCellX().widget();
            llllllllllllllllllllIIllIlIlIIIl.spacing = 0.0;
            llllllllllllllllllllIIllIlIlIIIl.add(llllllllllllllllllllIIllIlIIllIl.theme.label(llllllllllllllllllllIIllIlIIlllI.ip));
            llllllllllllllllllllIIllIlIlIIIl.add(llllllllllllllllllllIIllIlIIllIl.theme.label((String)":")).widget().color = llllllllllllllllllllIIllIlIIllIl.theme.textSecondaryColor();
            llllllllllllllllllllIIllIlIlIIIl.add(llllllllllllllllllllIIllIlIIllIl.theme.label(Integer.toString(llllllllllllllllllllIIllIlIIlllI.port)));
            WButton llllllllllllllllllllIIllIlIlIIII = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.button(GuiRenderer.EDIT)).widget();
            llllllllllllllllllllIIllIlIlIIII.action = () -> {
                ProxiesScreen llllllllllllllllllllIIllIIllIIlI;
                llllllllllllllllllllIIllIIllIIlI.openEditProxyScreen(llllllllllllllllllllIIllIlIIlllI);
            };
            WMinus llllllllllllllllllllIIllIlIIllll = llllllllllllllllllllIIllIlIIllII.add(llllllllllllllllllllIIllIlIIllIl.theme.minus()).widget();
            llllllllllllllllllllIIllIlIIllll.action = () -> {
                ProxiesScreen llllllllllllllllllllIIllIIllIllI;
                Proxies.get().remove(llllllllllllllllllllIIllIlIIlllI);
                llllllllllllllllllllIIllIIllIllI.initWidgets();
            };
            llllllllllllllllllllIIllIlIIllII.row();
        }
        WButton llllllllllllllllllllIIllIlIIlIlI = llllllllllllllllllllIIllIlIIllIl.add(llllllllllllllllllllIIllIlIIllIl.theme.button("New")).expandX().widget();
        llllllllllllllllllllIIllIlIIlIlI.action = () -> {
            ProxiesScreen llllllllllllllllllllIIllIIlllIll;
            llllllllllllllllllllIIllIIlllIll.openEditProxyScreen(null);
        };
    }

    public ProxiesScreen(GuiTheme llllllllllllllllllllIIllIllllIlI) {
        super(llllllllllllllllllllIIllIllllIlI, "Proxies");
        ProxiesScreen llllllllllllllllllllIIllIllllIll;
        llllllllllllllllllllIIllIllllIll.checkboxes = new ArrayList<WCheckbox>();
    }

    protected static class EditProxyScreen
    extends WindowScreen {
        public EditProxyScreen(GuiTheme llllllllllllllllIlllIlIllIlIIlIl, Proxy llllllllllllllllIlllIlIllIlIIlII) {
            super(llllllllllllllllIlllIlIllIlIIlIl, llllllllllllllllIlllIlIllIlIIlII == null ? "New Proxy" : "Edit Proxy");
            EditProxyScreen llllllllllllllllIlllIlIllIllIlII;
            boolean llllllllllllllllIlllIlIllIllIIIl = llllllllllllllllIlllIlIllIlIIlII == null;
            Proxy llllllllllllllllIlllIlIllIllIIII = llllllllllllllllIlllIlIllIlIIlII == null ? new Proxy() : llllllllllllllllIlllIlIllIlIIlII;
            WTable llllllllllllllllIlllIlIllIlIllll = llllllllllllllllIlllIlIllIllIlII.add(llllllllllllllllIlllIlIllIlIIlIl.table()).expandX().widget();
            llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.label("Proxy Name:"));
            WTextBox llllllllllllllllIlllIlIllIlIlllI = llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.textBox(llllllllllllllllIlllIlIllIllIIII.name)).expandX().widget();
            llllllllllllllllIlllIlIllIlIlllI.action = () -> {
                llllllllllllllllIlllIlIlIllIllIl.name = llllllllllllllllIlllIlIllIlIlllI.get();
            };
            llllllllllllllllIlllIlIllIlIllll.row();
            llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.label("Type:"));
            WDropdown<ProxyType> llllllllllllllllIlllIlIllIlIllIl = llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.dropdown(llllllllllllllllIlllIlIllIllIIII.type)).widget();
            llllllllllllllllIlllIlIllIlIllIl.action = () -> {
                llllllllllllllllIlllIlIlIlllIIll.type = (ProxyType)((Object)((Object)llllllllllllllllIlllIlIllIlIllIl.get()));
            };
            llllllllllllllllIlllIlIllIlIllll.row();
            llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.label("IP:"));
            WTextBox llllllllllllllllIlllIlIllIlIllII = llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.textBox(llllllllllllllllIlllIlIllIllIIII.ip)).minWidth(400.0).expandX().widget();
            llllllllllllllllIlllIlIllIlIllII.action = () -> {
                llllllllllllllllIlllIlIlIllllIll.ip = llllllllllllllllIlllIlIllIlIllII.get();
            };
            llllllllllllllllIlllIlIllIlIllll.row();
            llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.label("Port:"));
            WIntEdit llllllllllllllllIlllIlIllIlIlIll = llllllllllllllllIlllIlIllIlIllll.add(llllllllllllllllIlllIlIllIlIIlIl.intEdit(llllllllllllllllIlllIlIllIllIIII.port, 0, 0)).expandX().widget();
            llllllllllllllllIlllIlIllIlIlIll.min = 0;
            llllllllllllllllIlllIlIllIlIlIll.max = 65535;
            llllllllllllllllIlllIlIllIlIlIll.action = () -> {
                llllllllllllllllIlllIlIllIIIIIIl.port = llllllllllllllllIlllIlIllIlIlIll.get();
            };
            llllllllllllllllIlllIlIllIllIlII.add(llllllllllllllllIlllIlIllIlIIlIl.horizontalSeparator("Optional")).expandX().widget();
            WTable llllllllllllllllIlllIlIllIlIlIlI = llllllllllllllllIlllIlIllIllIlII.add(llllllllllllllllIlllIlIllIlIIlIl.table()).expandX().widget();
            llllllllllllllllIlllIlIllIlIlIlI.add(llllllllllllllllIlllIlIllIlIIlIl.label("Username:"));
            WTextBox llllllllllllllllIlllIlIllIlIlIIl = llllllllllllllllIlllIlIllIlIlIlI.add(llllllllllllllllIlllIlIllIlIIlIl.textBox(llllllllllllllllIlllIlIllIllIIII.username)).expandX().widget();
            llllllllllllllllIlllIlIllIlIlIIl.action = () -> {
                llllllllllllllllIlllIlIllIIIIlll.username = llllllllllllllllIlllIlIllIlIlIIl.get();
            };
            llllllllllllllllIlllIlIllIlIlIlI.row();
            llllllllllllllllIlllIlIllIlIlIlI.add(llllllllllllllllIlllIlIllIlIIlIl.label("Password:"));
            WTextBox llllllllllllllllIlllIlIllIlIlIII = llllllllllllllllIlllIlIllIlIlIlI.add(llllllllllllllllIlllIlIllIlIIlIl.textBox(llllllllllllllllIlllIlIllIllIIII.password)).expandX().widget();
            llllllllllllllllIlllIlIllIlIlIII.action = () -> {
                llllllllllllllllIlllIlIllIIIllIl.password = llllllllllllllllIlllIlIllIlIlIII.get();
            };
            llllllllllllllllIlllIlIllIllIlII.add(llllllllllllllllIlllIlIllIlIIlIl.horizontalSeparator()).expandX();
            WButton llllllllllllllllIlllIlIllIlIIlll = llllllllllllllllIlllIlIllIllIlII.add(llllllllllllllllIlllIlIllIlIIlIl.button(llllllllllllllllIlllIlIllIllIIIl ? "Add" : "Save")).expandX().widget();
            llllllllllllllllIlllIlIllIllIlII.enterAction = llllllllllllllllIlllIlIllIlIIlll.action = () -> {
                if (llllllllllllllllIlllIlIllIllIIII.isValid() && (!llllllllllllllllIlllIlIllIllIIIl || Proxies.get().add(llllllllllllllllIlllIlIllIllIIII))) {
                    EditProxyScreen llllllllllllllllIlllIlIllIIlIIlI;
                    llllllllllllllllIlllIlIllIIlIIlI.method_25419();
                }
            };
        }
    }
}

