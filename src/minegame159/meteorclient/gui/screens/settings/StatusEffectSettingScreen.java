/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.class_1291
 *  org.apache.commons.lang3.StringUtils
 */
package minegame159.meteorclient.gui.screens.settings;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_1291;
import org.apache.commons.lang3.StringUtils;

public class StatusEffectSettingScreen
extends WindowScreen {
    private /* synthetic */ WTable table;
    private final /* synthetic */ WTextBox filter;
    private /* synthetic */ String filterText;
    private final /* synthetic */ Setting<Object2IntMap<class_1291>> setting;

    public StatusEffectSettingScreen(GuiTheme lllllllllllllllllIIllIIllIlllIll, Setting<Object2IntMap<class_1291>> lllllllllllllllllIIllIIllIlllIlI) {
        super(lllllllllllllllllIIllIIllIlllIll, "Select potions");
        StatusEffectSettingScreen lllllllllllllllllIIllIIllIllllII;
        lllllllllllllllllIIllIIllIllllII.filterText = "";
        lllllllllllllllllIIllIIllIllllII.setting = lllllllllllllllllIIllIIllIlllIlI;
        lllllllllllllllllIIllIIllIllllII.filter = lllllllllllllllllIIllIIllIllllII.add(lllllllllllllllllIIllIIllIlllIll.textBox("")).minWidth(400.0).expandX().widget();
        lllllllllllllllllIIllIIllIllllII.filter.setFocused(true);
        lllllllllllllllllIIllIIllIllllII.filter.action = () -> {
            StatusEffectSettingScreen lllllllllllllllllIIllIIllIIllllI;
            lllllllllllllllllIIllIIllIIllllI.filterText = lllllllllllllllllIIllIIllIIllllI.filter.get().trim();
            lllllllllllllllllIIllIIllIIllllI.table.clear();
            lllllllllllllllllIIllIIllIIllllI.initWidgets();
        };
        lllllllllllllllllIIllIIllIllllII.table = lllllllllllllllllIIllIIllIllllII.add(lllllllllllllllllIIllIIllIlllIll.table()).expandX().widget();
        lllllllllllllllllIIllIIllIllllII.initWidgets();
    }

    private void initWidgets() {
        StatusEffectSettingScreen lllllllllllllllllIIllIIllIlIlllI;
        ArrayList<class_1291> lllllllllllllllllIIllIIllIlIllll = new ArrayList<class_1291>((Collection<class_1291>)lllllllllllllllllIIllIIllIlIlllI.setting.get().keySet());
        lllllllllllllllllIIllIIllIlIllll.sort(Comparator.comparing(Names::get));
        for (class_1291 lllllllllllllllllIIllIIllIllIIIl : lllllllllllllllllIIllIIllIlIllll) {
            String lllllllllllllllllIIllIIllIllIIll = Names.get(lllllllllllllllllIIllIIllIllIIIl);
            if (!StringUtils.containsIgnoreCase((CharSequence)lllllllllllllllllIIllIIllIllIIll, (CharSequence)lllllllllllllllllIIllIIllIlIlllI.filterText)) continue;
            lllllllllllllllllIIllIIllIlIlllI.table.add(lllllllllllllllllIIllIIllIlIlllI.theme.label(lllllllllllllllllIIllIIllIllIIll)).expandCellX();
            WIntEdit lllllllllllllllllIIllIIllIllIIlI = lllllllllllllllllIIllIIllIlIlllI.theme.intEdit(lllllllllllllllllIIllIIllIlIlllI.setting.get().getInt((Object)lllllllllllllllllIIllIIllIllIIIl), 0, 0);
            lllllllllllllllllIIllIIllIllIIlI.hasSlider = false;
            lllllllllllllllllIIllIIllIllIIlI.action = () -> {
                StatusEffectSettingScreen lllllllllllllllllIIllIIllIlIIlIl;
                lllllllllllllllllIIllIIllIlIIlIl.setting.get().put((Object)lllllllllllllllllIIllIIllIllIIIl, lllllllllllllllllIIllIIllIllIIlI.get());
                lllllllllllllllllIIllIIllIlIIlIl.setting.changed();
            };
            lllllllllllllllllIIllIIllIlIlllI.table.add(lllllllllllllllllIIllIIllIllIIlI).minWidth(50.0);
            lllllllllllllllllIIllIIllIlIlllI.table.row();
        }
    }
}

