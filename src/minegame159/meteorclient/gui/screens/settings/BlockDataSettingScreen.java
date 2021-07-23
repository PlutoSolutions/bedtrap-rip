/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.settings.IBlockData;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.IChangeable;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_437;
import org.apache.commons.lang3.StringUtils;

public class BlockDataSettingScreen
extends WindowScreen {
    private String filterText = "";
    private WTable table;
    private static final List<class_2248> BLOCKS = new ArrayList<class_2248>(100);
    private final BlockDataSetting<?> setting;

    @Override
    protected void method_25426() {
        super.method_25426();
        this.table.clear();
        this.initWidgets();
    }

    private void lambda$initWidgets$2(class_2248 class_22482, ICopyable iCopyable) {
        ((Map)this.setting.get()).remove(class_22482);
        this.setting.changed();
        if (iCopyable != null && ((IChangeable)((Object)iCopyable)).isChanged()) {
            this.table.clear();
            this.initWidgets();
        }
    }

    private void lambda$new$0(WTextBox wTextBox) {
        this.filterText = wTextBox.get().trim();
        this.table.clear();
        this.initWidgets();
    }

    private void lambda$initWidgets$1(ICopyable iCopyable, class_2248 class_22482) {
        ICopyable iCopyable2 = iCopyable;
        if (iCopyable2 == null) {
            iCopyable2 = ((ICopyable)this.setting.defaultData.get()).copy();
        }
        Utils.mc.method_1507((class_437)((IBlockData)((Object)iCopyable2)).createScreen(this.theme, class_22482, this.setting));
    }

    public BlockDataSettingScreen(GuiTheme guiTheme, BlockDataSetting<?> blockDataSetting) {
        super(guiTheme, "Configure blocks");
        this.setting = blockDataSetting;
        WTextBox wTextBox = this.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        wTextBox.setFocused(true);
        wTextBox.action = () -> this.lambda$new$0(wTextBox);
        this.table = this.add(guiTheme.table()).expandX().widget();
    }

    private <T extends ICopyable<T> & ISerializable<T> & IBlockData<T>> void initWidgets() {
        Object object;
        for (class_2248 class_22482 : class_2378.field_11146) {
            object = (ICopyable)((Map)this.setting.get()).get(class_22482);
            if (object != null && ((IChangeable)object).isChanged()) {
                BLOCKS.add(0, class_22482);
                continue;
            }
            BLOCKS.add(class_22482);
        }
        for (class_2248 class_22482 : BLOCKS) {
            object = Names.get(class_22482);
            if (!StringUtils.containsIgnoreCase((CharSequence)object, (CharSequence)this.filterText)) continue;
            ICopyable iCopyable = (ICopyable)((Map)this.setting.get()).get(class_22482);
            this.table.add(this.theme.itemWithLabel(class_22482.method_8389().method_7854(), Names.get(class_22482))).expandCellX();
            this.table.add(this.theme.label(iCopyable != null && ((IChangeable)((Object)iCopyable)).isChanged() ? "*" : " "));
            WButton wButton = this.table.add(this.theme.button(GuiRenderer.EDIT)).widget();
            wButton.action = () -> this.lambda$initWidgets$1(iCopyable, class_22482);
            WButton wButton2 = this.table.add(this.theme.button(GuiRenderer.RESET)).widget();
            wButton2.action = () -> this.lambda$initWidgets$2(class_22482, iCopyable);
            this.table.row();
        }
        BLOCKS.clear();
    }
}

