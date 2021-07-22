/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2394
 *  net.minecraft.class_2396
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
import net.minecraft.class_2394;
import net.minecraft.class_2396;

public class ParticleTypeListSettingScreen
extends LeftRightListSettingScreen<class_2396<?>> {
    @Override
    protected boolean skipValue(class_2396<?> class_23962) {
        return !(class_23962 instanceof class_2394);
    }

    public ParticleTypeListSettingScreen(GuiTheme guiTheme, Setting<List<class_2396<?>>> setting) {
        super(guiTheme, "Select particles", setting, (Collection)setting.get(), class_2378.field_11141);
    }

    @Override
    protected boolean skipValue(Object object) {
        return this.skipValue((class_2396)object);
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_2396)object);
    }

    @Override
    protected String getValueName(class_2396<?> class_23962) {
        return Names.get(class_23962);
    }

    @Override
    protected WWidget getValueWidget(class_2396<?> class_23962) {
        return this.theme.label(this.getValueName(class_23962));
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_2396)object);
    }
}

