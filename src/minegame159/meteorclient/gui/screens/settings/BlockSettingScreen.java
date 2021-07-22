/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  org.apache.commons.lang3.StringUtils
 */
package minegame159.meteorclient.gui.screens.settings;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.widgets.WItemWithLabel;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.BlockSetting;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import org.apache.commons.lang3.StringUtils;

public class BlockSettingScreen
extends WindowScreen {
    private /* synthetic */ WTable table;
    private final /* synthetic */ WTextBox filter;
    private /* synthetic */ String filterText;
    private final /* synthetic */ BlockSetting setting;

    public BlockSettingScreen(GuiTheme lIIIlIIlIIIIIII, BlockSetting lIIIlIIlIIIIIlI) {
        super(lIIIlIIlIIIIIII, "Select blocks");
        BlockSettingScreen lIIIlIIlIIIIIIl;
        lIIIlIIlIIIIIIl.filterText = "";
        lIIIlIIlIIIIIIl.setting = lIIIlIIlIIIIIlI;
        lIIIlIIlIIIIIIl.filter = lIIIlIIlIIIIIIl.add(lIIIlIIlIIIIIII.textBox("")).minWidth(400.0).expandX().widget();
        lIIIlIIlIIIIIIl.filter.setFocused(true);
        lIIIlIIlIIIIIIl.filter.action = () -> {
            BlockSettingScreen lIIIlIIIllIIlII;
            lIIIlIIIllIIlII.filterText = lIIIlIIIllIIlII.filter.get().trim();
            lIIIlIIIllIIlII.table.clear();
            lIIIlIIIllIIlII.initWidgets();
        };
        lIIIlIIlIIIIIIl.table = lIIIlIIlIIIIIIl.add(lIIIlIIlIIIIIII.table()).expandX().widget();
        lIIIlIIlIIIIIIl.initWidgets();
    }

    protected boolean skipValue(class_2248 lIIIlIIIllIlllI) {
        return class_2378.field_11146.method_10221((Object)lIIIlIIIllIlllI).method_12832().endsWith("_wall_banner");
    }

    private void initWidgets() {
        for (class_2248 lIIIlIIIlllIlll : class_2378.field_11146) {
            BlockSettingScreen lIIIlIIIlllIllI;
            if ((lIIIlIIIlllIllI.setting.filter == null ? lIIIlIIIlllIlll == class_2246.field_10124 : !lIIIlIIIlllIllI.setting.filter.test(lIIIlIIIlllIlll)) || lIIIlIIIlllIllI.skipValue(lIIIlIIIlllIlll)) continue;
            WItemWithLabel lIIIlIIIllllIIl = lIIIlIIIlllIllI.theme.itemWithLabel(lIIIlIIIlllIlll.method_8389().method_7854(), Names.get(lIIIlIIIlllIlll));
            if (!lIIIlIIIlllIllI.filterText.isEmpty() && !StringUtils.containsIgnoreCase((CharSequence)lIIIlIIIllllIIl.getLabelText(), (CharSequence)lIIIlIIIlllIllI.filterText)) continue;
            lIIIlIIIlllIllI.table.add(lIIIlIIIllllIIl);
            WButton lIIIlIIIllllIII = lIIIlIIIlllIllI.table.add(lIIIlIIIlllIllI.theme.button("Select")).expandCellX().right().widget();
            lIIIlIIIllllIII.action = () -> {
                BlockSettingScreen lIIIlIIIllIlIII;
                lIIIlIIIllIlIII.setting.set(lIIIlIIIlllIlll);
                lIIIlIIIllIlIII.method_25419();
            };
            lIIIlIIIlllIllI.table.row();
        }
    }
}

