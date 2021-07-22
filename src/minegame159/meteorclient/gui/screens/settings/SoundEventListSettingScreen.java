/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_3414
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_2378;
import net.minecraft.class_3414;

public class SoundEventListSettingScreen
extends LeftRightListSettingScreen<class_3414> {
    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_3414)object);
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_3414)object);
    }

    @Override
    protected WWidget getValueWidget(class_3414 class_34142) {
        return this.theme.label(this.getValueName(class_34142));
    }

    @Override
    protected String getValueName(class_3414 class_34142) {
        return Names.getSoundName(class_34142.method_14833());
    }

    public SoundEventListSettingScreen(GuiTheme guiTheme, Setting<List<class_3414>> setting) {
        super(guiTheme, "Select sounds", setting, (Collection)setting.get(), class_2378.field_11156);
    }
}

