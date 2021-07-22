/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_3545
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2378;
import net.minecraft.class_3545;

public abstract class LeftRightListSettingScreen<T>
extends WindowScreen {
    protected final /* synthetic */ Collection<T> collection;
    protected final /* synthetic */ Setting<?> setting;
    private /* synthetic */ WTable table;
    private final /* synthetic */ WTextBox filter;
    private /* synthetic */ String filterText;

    protected boolean skipValue(T lllllllllllllllllIIlIllIlIIllIlI) {
        return false;
    }

    protected abstract WWidget getValueWidget(T var1);

    protected T getAdditionalValue(T lllllllllllllllllIIlIllIlIIllIII) {
        return null;
    }

    private void removeValue(class_2378<T> lllllllllllllllllIIlIllIlIllllll, T lllllllllllllllllIIlIllIlIlllIll) {
        LeftRightListSettingScreen lllllllllllllllllIIlIllIlIllllIl;
        if (lllllllllllllllllIIlIllIlIllllIl.collection.remove(lllllllllllllllllIIlIllIlIlllIll)) {
            lllllllllllllllllIIlIllIlIllllIl.setting.changed();
            lllllllllllllllllIIlIllIlIllllIl.table.clear();
            lllllllllllllllllIIlIllIlIllllIl.initWidgets(lllllllllllllllllIIlIllIlIllllll);
        }
    }

    protected boolean includeValue(T lllllllllllllllllIIlIllIlIIlllII) {
        return true;
    }

    private WTable abc(Consumer<List<class_3545<T, Integer>>> lllllllllllllllllIIlIllIlIlIIllI, boolean lllllllllllllllllIIlIllIlIlIllIl, Consumer<T> lllllllllllllllllIIlIllIlIlIIlII) {
        LeftRightListSettingScreen lllllllllllllllllIIlIllIlIlIIlll;
        Cell<WTable> lllllllllllllllllIIlIllIlIlIlIll = lllllllllllllllllIIlIllIlIlIIlll.table.add(lllllllllllllllllIIlIllIlIlIIlll.theme.table()).top();
        WTable lllllllllllllllllIIlIllIlIlIlIlI = lllllllllllllllllIIlIllIlIlIlIll.widget();
        Consumer<Object> lllllllllllllllllIIlIllIlIlIlIIl = lllllllllllllllllIIlIllIlIIIIlII -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIlIIIlIII;
            if (!lllllllllllllllllIIlIllIlIIIlIII.includeValue(lllllllllllllllllIIlIllIlIIIIlII)) {
                return;
            }
            lllllllllllllllllIIlIllIlIlIlIlI.add(lllllllllllllllllIIlIllIlIIIlIII.getValueWidget(lllllllllllllllllIIlIllIlIIIIlII));
            WPressable lllllllllllllllllIIlIllIlIIIlIIl = lllllllllllllllllIIlIllIlIlIlIlI.add(lllllllllllllllllIIlIllIlIlIllIl ? lllllllllllllllllIIlIllIlIIIlIII.theme.plus() : lllllllllllllllllIIlIllIlIIIlIII.theme.minus()).expandCellX().right().widget();
            lllllllllllllllllIIlIllIlIIIlIIl.action = () -> lllllllllllllllllIIlIllIlIlIIlII.accept(lllllllllllllllllIIlIllIlIIIIlII);
            lllllllllllllllllIIlIllIlIlIlIlI.row();
        };
        ArrayList<class_3545> lllllllllllllllllIIlIllIlIlIlIII = new ArrayList<class_3545>();
        lllllllllllllllllIIlIllIlIlIIllI.accept(lllllllllllllllllIIlIllIlIlIlIII);
        if (!lllllllllllllllllIIlIllIlIlIIlll.filterText.isEmpty()) {
            lllllllllllllllllIIlIllIlIlIlIII.sort(Comparator.comparingInt(lllllllllllllllllIIlIllIlIIlIlIl -> -((Integer)lllllllllllllllllIIlIllIlIIlIlIl.method_15441()).intValue()));
        }
        for (class_3545 lllllllllllllllllIIlIllIlIllIIII : lllllllllllllllllIIlIllIlIlIlIII) {
            lllllllllllllllllIIlIllIlIlIlIIl.accept(lllllllllllllllllIIlIllIlIllIIII.method_15442());
        }
        if (lllllllllllllllllIIlIllIlIlIlIlI.cells.size() > 0) {
            lllllllllllllllllIIlIllIlIlIlIll.expandX();
        }
        return lllllllllllllllllIIlIllIlIlIlIlI;
    }

    private void initWidgets(class_2378<T> lllllllllllllllllIIlIllIllIlIIIl) {
        LeftRightListSettingScreen lllllllllllllllllIIlIllIllIIllll;
        WTable lllllllllllllllllIIlIllIllIlIIII = lllllllllllllllllIIlIllIllIIllll.abc(lllllllllllllllllIIlIllIIlIIlllI -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIIlIlIIII;
            lllllllllllllllllIIlIllIllIlIIIl.forEach(lllllllllllllllllIIlIllIIlIIIlll -> {
                LeftRightListSettingScreen lllllllllllllllllIIlIllIIlIIlIIl;
                if (lllllllllllllllllIIlIllIIlIIlIIl.skipValue(lllllllllllllllllIIlIllIIlIIIlll) || lllllllllllllllllIIlIllIIlIIlIIl.collection.contains(lllllllllllllllllIIlIllIIlIIIlll)) {
                    return;
                }
                int lllllllllllllllllIIlIllIIlIIIllI = Utils.search(lllllllllllllllllIIlIllIIlIIlIIl.getValueName(lllllllllllllllllIIlIllIIlIIIlll), lllllllllllllllllIIlIllIIlIIlIIl.filterText);
                if (lllllllllllllllllIIlIllIIlIIIllI > 0) {
                    lllllllllllllllllIIlIllIIlIIlllI.add(new class_3545(lllllllllllllllllIIlIllIIlIIIlll, (Object)lllllllllllllllllIIlIllIIlIIIllI));
                }
            });
        }, true, lllllllllllllllllIIlIllIIlIlllII -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIIlIllllI;
            lllllllllllllllllIIlIllIIlIllllI.addValue(lllllllllllllllllIIlIllIllIlIIIl, lllllllllllllllllIIlIllIIlIlllII);
            Object lllllllllllllllllIIlIllIIlIllIll = lllllllllllllllllIIlIllIIlIllllI.getAdditionalValue(lllllllllllllllllIIlIllIIlIlllII);
            if (lllllllllllllllllIIlIllIIlIllIll != null) {
                lllllllllllllllllIIlIllIIlIllllI.addValue(lllllllllllllllllIIlIllIllIlIIIl, lllllllllllllllllIIlIllIIlIllIll);
            }
        });
        if (lllllllllllllllllIIlIllIllIlIIII.cells.size() > 0) {
            lllllllllllllllllIIlIllIllIIllll.table.add(lllllllllllllllllIIlIllIllIIllll.theme.verticalSeparator()).expandWidgetY();
        }
        lllllllllllllllllIIlIllIllIIllll.abc(lllllllllllllllllIIlIllIIllIIllI -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIIllIIlll;
            for (T lllllllllllllllllIIlIllIIllIlIlI : lllllllllllllllllIIlIllIIllIIlll.collection) {
                int lllllllllllllllllIIlIllIIllIlIll;
                if (lllllllllllllllllIIlIllIIllIIlll.skipValue(lllllllllllllllllIIlIllIIllIlIlI) || (lllllllllllllllllIIlIllIIllIlIll = Utils.search(lllllllllllllllllIIlIllIIllIIlll.getValueName(lllllllllllllllllIIlIllIIllIlIlI), lllllllllllllllllIIlIllIIllIIlll.filterText)) <= 0) continue;
                lllllllllllllllllIIlIllIIllIIllI.add(new class_3545(lllllllllllllllllIIlIllIIllIlIlI, (Object)lllllllllllllllllIIlIllIIllIlIll));
            }
        }, false, lllllllllllllllllIIlIllIIlllIIlI -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIIllllIII;
            lllllllllllllllllIIlIllIIllllIII.removeValue(lllllllllllllllllIIlIllIllIlIIIl, lllllllllllllllllIIlIllIIlllIIlI);
            Object lllllllllllllllllIIlIllIIlllIlIl = lllllllllllllllllIIlIllIIllllIII.getAdditionalValue(lllllllllllllllllIIlIllIIlllIIlI);
            if (lllllllllllllllllIIlIllIIlllIlIl != null) {
                lllllllllllllllllIIlIllIIllllIII.removeValue(lllllllllllllllllIIlIllIllIlIIIl, lllllllllllllllllIIlIllIIlllIlIl);
            }
        });
    }

    private void addValue(class_2378<T> lllllllllllllllllIIlIllIllIIlIII, T lllllllllllllllllIIlIllIllIIIlII) {
        LeftRightListSettingScreen lllllllllllllllllIIlIllIllIIlIIl;
        if (!lllllllllllllllllIIlIllIllIIlIIl.collection.contains(lllllllllllllllllIIlIllIllIIIlII)) {
            lllllllllllllllllIIlIllIllIIlIIl.collection.add(lllllllllllllllllIIlIllIllIIIlII);
            lllllllllllllllllIIlIllIllIIlIIl.setting.changed();
            lllllllllllllllllIIlIllIllIIlIIl.table.clear();
            lllllllllllllllllIIlIllIllIIlIIl.initWidgets(lllllllllllllllllIIlIllIllIIlIII);
        }
    }

    protected abstract String getValueName(T var1);

    public LeftRightListSettingScreen(GuiTheme lllllllllllllllllIIlIllIllIllIlI, String lllllllllllllllllIIlIllIllIllIIl, Setting<?> lllllllllllllllllIIlIllIllIllllI, Collection<T> lllllllllllllllllIIlIllIllIlIlll, class_2378<T> lllllllllllllllllIIlIllIllIlIllI) {
        super(lllllllllllllllllIIlIllIllIllIlI, lllllllllllllllllIIlIllIllIllIIl);
        LeftRightListSettingScreen lllllllllllllllllIIlIllIlllIIIIl;
        lllllllllllllllllIIlIllIlllIIIIl.filterText = "";
        lllllllllllllllllIIlIllIlllIIIIl.setting = lllllllllllllllllIIlIllIllIllllI;
        lllllllllllllllllIIlIllIlllIIIIl.collection = lllllllllllllllllIIlIllIllIlIlll;
        lllllllllllllllllIIlIllIlllIIIIl.filter = lllllllllllllllllIIlIllIlllIIIIl.add(lllllllllllllllllIIlIllIllIllIlI.textBox("")).minWidth(400.0).expandX().widget();
        lllllllllllllllllIIlIllIlllIIIIl.filter.setFocused(true);
        lllllllllllllllllIIlIllIlllIIIIl.filter.action = () -> {
            LeftRightListSettingScreen lllllllllllllllllIIlIllIIIllllll;
            lllllllllllllllllIIlIllIIIllllll.filterText = lllllllllllllllllIIlIllIIIllllll.filter.get().trim();
            lllllllllllllllllIIlIllIIIllllll.table.clear();
            lllllllllllllllllIIlIllIIIllllll.initWidgets(lllllllllllllllllIIlIllIllIlIllI);
        };
        lllllllllllllllllIIlIllIlllIIIIl.table = lllllllllllllllllIIlIllIlllIIIIl.add(lllllllllllllllllIIlIllIllIllIlI.table()).expandX().widget();
        lllllllllllllllllIIlIllIlllIIIIl.initWidgets(lllllllllllllllllIIlIllIllIlIllI);
    }
}

