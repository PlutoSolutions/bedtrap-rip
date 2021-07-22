/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.mixin.IdentifierAccessor;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2960;

public class BlockListSettingScreen
extends LeftRightListSettingScreen<class_2248> {
    private static final class_2960 ID = new class_2960("minecraft", "");

    @Override
    protected boolean includeValue(class_2248 class_22482) {
        Predicate<class_2248> predicate = ((BlockListSetting)this.setting).filter;
        if (predicate == null) {
            return class_22482 != class_2246.field_10124;
        }
        return predicate.test(class_22482);
    }

    @Override
    protected boolean skipValue(Object object) {
        return this.skipValue((class_2248)object);
    }

    @Override
    protected Object getAdditionalValue(Object object) {
        return this.getAdditionalValue((class_2248)object);
    }

    @Override
    protected WWidget getValueWidget(class_2248 class_22482) {
        return this.theme.itemWithLabel(class_22482.method_8389().method_7854(), this.getValueName(class_22482));
    }

    @Override
    protected boolean skipValue(class_2248 class_22482) {
        return class_2378.field_11146.method_10221((Object)class_22482).method_12832().endsWith("_wall_banner");
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_2248)object);
    }

    @Override
    protected boolean includeValue(Object object) {
        return this.includeValue((class_2248)object);
    }

    public BlockListSettingScreen(GuiTheme guiTheme, Setting<List<class_2248>> setting) {
        super(guiTheme, "Select Blocks", setting, (Collection)setting.get(), class_2378.field_11146);
    }

    @Override
    protected String getValueName(class_2248 class_22482) {
        return Names.get(class_22482);
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_2248)object);
    }

    @Override
    protected class_2248 getAdditionalValue(class_2248 class_22482) {
        String string = class_2378.field_11146.method_10221((Object)class_22482).method_12832();
        if (!string.endsWith("_banner")) {
            return null;
        }
        ((IdentifierAccessor)ID).setPath(String.valueOf(new StringBuilder().append(string.substring(0, string.length() - 6)).append("wall_banner")));
        return (class_2248)class_2378.field_11146.method_10223(ID);
    }
}

