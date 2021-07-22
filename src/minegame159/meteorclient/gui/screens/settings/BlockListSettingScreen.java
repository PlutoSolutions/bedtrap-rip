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
    private static final /* synthetic */ class_2960 ID;

    @Override
    protected boolean includeValue(class_2248 lllllllllllllllllIIllllllIIlIlll) {
        BlockListSettingScreen lllllllllllllllllIIllllllIIllIII;
        Predicate<class_2248> lllllllllllllllllIIllllllIIllIIl = ((BlockListSetting)lllllllllllllllllIIllllllIIllIII.setting).filter;
        if (lllllllllllllllllIIllllllIIllIIl == null) {
            return lllllllllllllllllIIllllllIIlIlll != class_2246.field_10124;
        }
        return lllllllllllllllllIIllllllIIllIIl.test(lllllllllllllllllIIllllllIIlIlll);
    }

    static {
        ID = new class_2960("minecraft", "");
    }

    @Override
    protected WWidget getValueWidget(class_2248 lllllllllllllllllIIllllllIIlIIlI) {
        BlockListSettingScreen lllllllllllllllllIIllllllIIlIIll;
        return lllllllllllllllllIIllllllIIlIIll.theme.itemWithLabel(lllllllllllllllllIIllllllIIlIIlI.method_8389().method_7854(), lllllllllllllllllIIllllllIIlIIll.getValueName(lllllllllllllllllIIllllllIIlIIlI));
    }

    @Override
    protected boolean skipValue(class_2248 lllllllllllllllllIIllllllIIIlIII) {
        return class_2378.field_11146.method_10221((Object)lllllllllllllllllIIllllllIIIlIII).method_12832().endsWith("_wall_banner");
    }

    public BlockListSettingScreen(GuiTheme lllllllllllllllllIIllllllIlIIIll, Setting<List<class_2248>> lllllllllllllllllIIllllllIIlllll) {
        super(lllllllllllllllllIIllllllIlIIIll, "Select Blocks", lllllllllllllllllIIllllllIIlllll, (Collection)lllllllllllllllllIIllllllIIlllll.get(), class_2378.field_11146);
        BlockListSettingScreen lllllllllllllllllIIllllllIlIIIIl;
    }

    @Override
    protected String getValueName(class_2248 lllllllllllllllllIIllllllIIIllIl) {
        return Names.get(lllllllllllllllllIIllllllIIIllIl);
    }

    @Override
    protected class_2248 getAdditionalValue(class_2248 lllllllllllllllllIIllllllIIIIlII) {
        String lllllllllllllllllIIllllllIIIIIll = class_2378.field_11146.method_10221((Object)lllllllllllllllllIIllllllIIIIlII).method_12832();
        if (!lllllllllllllllllIIllllllIIIIIll.endsWith("_banner")) {
            return null;
        }
        ((IdentifierAccessor)ID).setPath(String.valueOf(new StringBuilder().append(lllllllllllllllllIIllllllIIIIIll.substring(0, lllllllllllllllllIIllllllIIIIIll.length() - 6)).append("wall_banner")));
        return (class_2248)class_2378.field_11146.method_10223(ID);
    }
}

