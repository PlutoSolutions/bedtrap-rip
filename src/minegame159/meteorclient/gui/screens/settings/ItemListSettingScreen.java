/*
 * Decompiled with CFR 0.151.
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
    protected String getValueName(class_1792 class_17922) {
        return Names.get(class_17922);
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_1792)object);
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_1792)object);
    }

    @Override
    protected WWidget getValueWidget(class_1792 class_17922) {
        return this.theme.itemWithLabel(class_17922.method_7854());
    }

    @Override
    protected boolean includeValue(class_1792 class_17922) {
        Predicate<class_1792> predicate = ((ItemListSetting)this.setting).filter;
        if (predicate != null && !predicate.test(class_17922)) {
            return false;
        }
        return class_17922 != class_1802.field_8162;
    }

    public ItemListSettingScreen(GuiTheme guiTheme, ItemListSetting itemListSetting) {
        super(guiTheme, "Select items", itemListSetting, (Collection)itemListSetting.get(), class_2378.field_11142);
    }

    @Override
    protected boolean includeValue(Object object) {
        return this.includeValue((class_1792)object);
    }
}

