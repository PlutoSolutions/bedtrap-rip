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
    protected WWidget getValueWidget(class_3414 llllllllllllllllllIIlllIIlllllIl) {
        SoundEventListSettingScreen llllllllllllllllllIIlllIIlllllII;
        return llllllllllllllllllIIlllIIlllllII.theme.label(llllllllllllllllllIIlllIIlllllII.getValueName(llllllllllllllllllIIlllIIlllllIl));
    }

    @Override
    protected String getValueName(class_3414 llllllllllllllllllIIlllIIllllIII) {
        return Names.getSoundName(llllllllllllllllllIIlllIIllllIII.method_14833());
    }

    public SoundEventListSettingScreen(GuiTheme llllllllllllllllllIIlllIlIIIIIlI, Setting<List<class_3414>> llllllllllllllllllIIlllIlIIIIIIl) {
        super(llllllllllllllllllIIlllIlIIIIIlI, "Select sounds", llllllllllllllllllIIlllIlIIIIIIl, (Collection)llllllllllllllllllIIlllIlIIIIIIl.get(), class_2378.field_11156);
        SoundEventListSettingScreen llllllllllllllllllIIlllIlIIIIllI;
    }
}

