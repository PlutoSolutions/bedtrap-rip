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
    protected boolean skipValue(class_2396<?> lIlIlIIlIllllll) {
        return !(lIlIlIIlIllllll instanceof class_2394);
    }

    public ParticleTypeListSettingScreen(GuiTheme lIlIlIIllIIllIl, Setting<List<class_2396<?>>> lIlIlIIllIIllII) {
        super(lIlIlIIllIIllIl, "Select particles", lIlIlIIllIIllII, (Collection)lIlIlIIllIIllII.get(), class_2378.field_11141);
        ParticleTypeListSettingScreen lIlIlIIllIlIIIl;
    }

    @Override
    protected String getValueName(class_2396<?> lIlIlIIllIIIIll) {
        return Names.get(lIlIlIIllIIIIll);
    }

    @Override
    protected WWidget getValueWidget(class_2396<?> lIlIlIIllIIlIII) {
        ParticleTypeListSettingScreen lIlIlIIllIIIlll;
        return lIlIlIIllIIIlll.theme.label(lIlIlIIllIIIlll.getValueName(lIlIlIIllIIlIII));
    }
}

