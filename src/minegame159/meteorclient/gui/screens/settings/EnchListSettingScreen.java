/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_1887;
import net.minecraft.class_2378;

public class EnchListSettingScreen
extends LeftRightListSettingScreen<class_1887> {
    @Override
    protected String getValueName(class_1887 class_18872) {
        return Names.get(class_18872);
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_1887)object);
    }

    @Override
    protected WWidget getValueWidget(class_1887 class_18872) {
        return this.theme.label(this.getValueName(class_18872));
    }

    public EnchListSettingScreen(GuiTheme guiTheme, Setting<List<class_1887>> setting) {
        super(guiTheme, "Select items", setting, (Collection)setting.get(), class_2378.field_11160);
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_1887)object);
    }
}

