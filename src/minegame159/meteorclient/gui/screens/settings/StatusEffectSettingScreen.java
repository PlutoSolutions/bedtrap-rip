/*
 * Decompiled with CFR 0.151.
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
    private WTable table;
    private final WTextBox filter;
    private String filterText = "";
    private final Setting<Object2IntMap<class_1291>> setting;

    public StatusEffectSettingScreen(GuiTheme guiTheme, Setting<Object2IntMap<class_1291>> setting) {
        super(guiTheme, "Select potions");
        this.setting = setting;
        this.filter = this.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        this.filter.setFocused(true);
        this.filter.action = this::lambda$new$0;
        this.table = this.add(guiTheme.table()).expandX().widget();
        this.initWidgets();
    }

    private void lambda$initWidgets$1(class_1291 class_12912, WIntEdit wIntEdit) {
        this.setting.get().put((Object)class_12912, wIntEdit.get());
        this.setting.changed();
    }

    private void initWidgets() {
        ArrayList<class_1291> arrayList = new ArrayList<class_1291>((Collection<class_1291>)this.setting.get().keySet());
        arrayList.sort(Comparator.comparing(Names::get));
        for (class_1291 class_12912 : arrayList) {
            String string = Names.get(class_12912);
            if (!StringUtils.containsIgnoreCase((CharSequence)string, (CharSequence)this.filterText)) continue;
            this.table.add(this.theme.label(string)).expandCellX();
            WIntEdit wIntEdit = this.theme.intEdit(this.setting.get().getInt((Object)class_12912), 0, 0);
            wIntEdit.hasSlider = false;
            wIntEdit.action = () -> this.lambda$initWidgets$1(class_12912, wIntEdit);
            this.table.add(wIntEdit).minWidth(50.0);
            this.table.row();
        }
    }

    private void lambda$new$0() {
        this.filterText = this.filter.get().trim();
        this.table.clear();
        this.initWidgets();
    }
}

