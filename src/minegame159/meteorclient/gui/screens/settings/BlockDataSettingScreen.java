/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_437
 *  org.apache.commons.lang3.StringUtils
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
    private /* synthetic */ String filterText;
    private /* synthetic */ WTable table;
    private static final /* synthetic */ List<class_2248> BLOCKS;
    private final /* synthetic */ BlockDataSetting<?> setting;

    @Override
    protected void method_25426() {
        BlockDataSettingScreen lllllllllllllllllIIIlIlllIIlIIlI;
        super.method_25426();
        lllllllllllllllllIIIlIlllIIlIIlI.table.clear();
        lllllllllllllllllIIIlIlllIIlIIlI.initWidgets();
    }

    static {
        BLOCKS = new ArrayList<class_2248>(100);
    }

    public BlockDataSettingScreen(GuiTheme lllllllllllllllllIIIlIlllIIlIllI, BlockDataSetting<?> lllllllllllllllllIIIlIlllIIllIIl) {
        super(lllllllllllllllllIIIlIlllIIlIllI, "Configure blocks");
        BlockDataSettingScreen lllllllllllllllllIIIlIlllIIlIlll;
        lllllllllllllllllIIIlIlllIIlIlll.filterText = "";
        lllllllllllllllllIIIlIlllIIlIlll.setting = lllllllllllllllllIIIlIlllIIllIIl;
        WTextBox lllllllllllllllllIIIlIlllIIllIII = lllllllllllllllllIIIlIlllIIlIlll.add(lllllllllllllllllIIIlIlllIIlIllI.textBox("")).minWidth(400.0).expandX().widget();
        lllllllllllllllllIIIlIlllIIllIII.setFocused(true);
        lllllllllllllllllIIIlIlllIIllIII.action = () -> {
            BlockDataSettingScreen lllllllllllllllllIIIlIllIllIIIIl;
            lllllllllllllllllIIIlIllIllIIIIl.filterText = lllllllllllllllllIIIlIlllIIllIII.get().trim();
            lllllllllllllllllIIIlIllIllIIIIl.table.clear();
            lllllllllllllllllIIIlIllIllIIIIl.initWidgets();
        };
        lllllllllllllllllIIIlIlllIIlIlll.table = lllllllllllllllllIIIlIlllIIlIlll.add(lllllllllllllllllIIIlIlllIIlIllI.table()).expandX().widget();
    }

    private <T extends ICopyable<T> & ISerializable<T> & IBlockData<T>> void initWidgets() {
        BlockDataSettingScreen lllllllllllllllllIIIlIlllIIIIIlI;
        for (class_2248 lllllllllllllllllIIIlIlllIIIlIII : class_2378.field_11146) {
            ICopyable lllllllllllllllllIIIlIlllIIIlIIl = (ICopyable)((Map)lllllllllllllllllIIIlIlllIIIIIlI.setting.get()).get((Object)lllllllllllllllllIIIlIlllIIIlIII);
            if (lllllllllllllllllIIIlIlllIIIlIIl != null && ((IChangeable)((Object)lllllllllllllllllIIIlIlllIIIlIIl)).isChanged()) {
                BLOCKS.add(0, lllllllllllllllllIIIlIlllIIIlIII);
                continue;
            }
            BLOCKS.add(lllllllllllllllllIIIlIlllIIIlIII);
        }
        for (class_2248 lllllllllllllllllIIIlIlllIIIIIll : BLOCKS) {
            String lllllllllllllllllIIIlIlllIIIIlll = Names.get(lllllllllllllllllIIIlIlllIIIIIll);
            if (!StringUtils.containsIgnoreCase((CharSequence)lllllllllllllllllIIIlIlllIIIIlll, (CharSequence)lllllllllllllllllIIIlIlllIIIIIlI.filterText)) continue;
            ICopyable lllllllllllllllllIIIlIlllIIIIllI = (ICopyable)((Map)lllllllllllllllllIIIlIlllIIIIIlI.setting.get()).get((Object)lllllllllllllllllIIIlIlllIIIIIll);
            lllllllllllllllllIIIlIlllIIIIIlI.table.add(lllllllllllllllllIIIlIlllIIIIIlI.theme.itemWithLabel(lllllllllllllllllIIIlIlllIIIIIll.method_8389().method_7854(), Names.get(lllllllllllllllllIIIlIlllIIIIIll))).expandCellX();
            lllllllllllllllllIIIlIlllIIIIIlI.table.add(lllllllllllllllllIIIlIlllIIIIIlI.theme.label(lllllllllllllllllIIIlIlllIIIIllI != null && ((IChangeable)((Object)lllllllllllllllllIIIlIlllIIIIllI)).isChanged() ? "*" : " "));
            WButton lllllllllllllllllIIIlIlllIIIIlIl = lllllllllllllllllIIIlIlllIIIIIlI.table.add(lllllllllllllllllIIIlIlllIIIIIlI.theme.button(GuiRenderer.EDIT)).widget();
            lllllllllllllllllIIIlIlllIIIIlIl.action = () -> {
                BlockDataSettingScreen lllllllllllllllllIIIlIllIllIllIl;
                ICopyable lllllllllllllllllIIIlIllIllIlIlI = lllllllllllllllllIIIlIlllIIIIllI;
                if (lllllllllllllllllIIIlIllIllIlIlI == null) {
                    lllllllllllllllllIIIlIllIllIlIlI = ((ICopyable)lllllllllllllllllIIIlIllIllIllIl.setting.defaultData.get()).copy();
                }
                Utils.mc.method_1507((class_437)((IBlockData)((Object)lllllllllllllllllIIIlIllIllIlIlI)).createScreen(lllllllllllllllllIIIlIllIllIllIl.theme, lllllllllllllllllIIIlIlllIIIIIll, lllllllllllllllllIIIlIllIllIllIl.setting));
            };
            WButton lllllllllllllllllIIIlIlllIIIIlII = lllllllllllllllllIIIlIlllIIIIIlI.table.add(lllllllllllllllllIIIlIlllIIIIIlI.theme.button(GuiRenderer.RESET)).widget();
            lllllllllllllllllIIIlIlllIIIIlII.action = () -> {
                BlockDataSettingScreen lllllllllllllllllIIIlIllIlllIlll;
                ((Map)lllllllllllllllllIIIlIllIlllIlll.setting.get()).remove((Object)lllllllllllllllllIIIlIlllIIIIIll);
                lllllllllllllllllIIIlIllIlllIlll.setting.changed();
                if (lllllllllllllllllIIIlIlllIIIIllI != null && ((IChangeable)((Object)lllllllllllllllllIIIlIlllIIIIllI)).isChanged()) {
                    lllllllllllllllllIIIlIllIlllIlll.table.clear();
                    lllllllllllllllllIIIlIllIlllIlll.initWidgets();
                }
            };
            lllllllllllllllllIIIlIlllIIIIIlI.table.row();
        }
        BLOCKS.clear();
    }
}

