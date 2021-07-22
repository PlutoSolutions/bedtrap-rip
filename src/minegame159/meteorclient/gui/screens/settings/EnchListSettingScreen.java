/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1887
 *  net.minecraft.class_2378
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
    protected String getValueName(class_1887 lllllllllllllllllIIlIIIIIlIlllII) {
        return Names.get(lllllllllllllllllIIlIIIIIlIlllII);
    }

    @Override
    protected WWidget getValueWidget(class_1887 lllllllllllllllllIIlIIIIIlIlllll) {
        EnchListSettingScreen lllllllllllllllllIIlIIIIIllIIIII;
        return lllllllllllllllllIIlIIIIIllIIIII.theme.label(lllllllllllllllllIIlIIIIIllIIIII.getValueName(lllllllllllllllllIIlIIIIIlIlllll));
    }

    public EnchListSettingScreen(GuiTheme lllllllllllllllllIIlIIIIIllIlIIl, Setting<List<class_1887>> lllllllllllllllllIIlIIIIIllIIlIl) {
        super(lllllllllllllllllIIlIIIIIllIlIIl, "Select items", lllllllllllllllllIIlIIIIIllIIlIl, (Collection)lllllllllllllllllIIlIIIIIllIIlIl.get(), class_2378.field_11160);
        EnchListSettingScreen lllllllllllllllllIIlIIIIIllIIlll;
    }
}

