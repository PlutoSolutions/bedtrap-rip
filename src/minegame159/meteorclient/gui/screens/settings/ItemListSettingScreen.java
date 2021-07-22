/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2378
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.function.Predicate;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2378;

public class ItemListSettingScreen
extends LeftRightListSettingScreen<class_1792> {
    @Override
    protected String getValueName(class_1792 lllllllllllllllllIlllIllIlIIIIll) {
        return Names.get(lllllllllllllllllIlllIllIlIIIIll);
    }

    @Override
    protected WWidget getValueWidget(class_1792 lllllllllllllllllIlllIllIlIIIllI) {
        ItemListSettingScreen lllllllllllllllllIlllIllIlIIlIIl;
        return lllllllllllllllllIlllIllIlIIlIIl.theme.itemWithLabel(lllllllllllllllllIlllIllIlIIIllI.method_7854());
    }

    @Override
    protected boolean includeValue(class_1792 lllllllllllllllllIlllIllIlIlIIII) {
        ItemListSettingScreen lllllllllllllllllIlllIllIlIlIIIl;
        Predicate<class_1792> lllllllllllllllllIlllIllIlIIllll = ((ItemListSetting)lllllllllllllllllIlllIllIlIlIIIl.setting).filter;
        if (lllllllllllllllllIlllIllIlIIllll != null && !lllllllllllllllllIlllIllIlIIllll.test(lllllllllllllllllIlllIllIlIlIIII)) {
            return false;
        }
        return lllllllllllllllllIlllIllIlIlIIII != class_1802.field_8162;
    }

    public ItemListSettingScreen(GuiTheme lllllllllllllllllIlllIllIlIllIIl, ItemListSetting lllllllllllllllllIlllIllIlIlIlIl) {
        super(lllllllllllllllllIlllIllIlIllIIl, "Select items", lllllllllllllllllIlllIllIlIlIlIl, (Collection)lllllllllllllllllIlllIllIlIlIlIl.get(), class_2378.field_11142);
        ItemListSettingScreen lllllllllllllllllIlllIllIlIllIlI;
    }
}

