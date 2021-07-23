/*
 * Decompiled with CFR 0.151.
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
    protected final Collection<T> collection;
    protected final Setting<?> setting;
    private WTable table;
    private final WTextBox filter;
    private String filterText = "";

    private void lambda$initWidgets$2(class_2378 class_23782, List list) {
        class_23782.forEach(arg_0 -> this.lambda$initWidgets$1(list, arg_0));
    }

    protected boolean skipValue(T t) {
        return false;
    }

    protected abstract WWidget getValueWidget(T var1);

    private static int lambda$abc$8(class_3545 class_35452) {
        return -((Integer)class_35452.method_15441()).intValue();
    }

    private void lambda$initWidgets$1(List list, Object object) {
        if (this.skipValue(object) || this.collection.contains(object)) {
            return;
        }
        int n = Utils.search(this.getValueName(object), this.filterText);
        if (n > 0) {
            list.add(new class_3545(object, (Object)n));
        }
    }

    private void lambda$initWidgets$3(class_2378 class_23782, Object object) {
        this.addValue(class_23782, object);
        Object object2 = this.getAdditionalValue(object);
        if (object2 != null) {
            this.addValue(class_23782, object2);
        }
    }

    protected T getAdditionalValue(T t) {
        return null;
    }

    private void removeValue(class_2378<T> class_23782, T t) {
        if (this.collection.remove(t)) {
            this.setting.changed();
            this.table.clear();
            this.initWidgets(class_23782);
        }
    }

    protected boolean includeValue(T t) {
        return true;
    }

    private WTable abc(Consumer<List<class_3545<T, Integer>>> consumer, boolean bl, Consumer<T> consumer2) {
        Cell<WTable> cell = this.table.add(this.theme.table()).top();
        WTable wTable = cell.widget();
        Consumer<Object> consumer3 = arg_0 -> this.lambda$abc$7(wTable, bl, consumer2, arg_0);
        ArrayList<class_3545> arrayList = new ArrayList<class_3545>();
        consumer.accept(arrayList);
        if (!this.filterText.isEmpty()) {
            arrayList.sort(Comparator.comparingInt(LeftRightListSettingScreen::lambda$abc$8));
        }
        for (class_3545 class_35452 : arrayList) {
            consumer3.accept(class_35452.method_15442());
        }
        if (wTable.cells.size() > 0) {
            cell.expandX();
        }
        return wTable;
    }

    private void lambda$new$0(class_2378 class_23782) {
        this.filterText = this.filter.get().trim();
        this.table.clear();
        this.initWidgets(class_23782);
    }

    private void lambda$initWidgets$4(List list) {
        for (T t : this.collection) {
            int n;
            if (this.skipValue(t) || (n = Utils.search(this.getValueName(t), this.filterText)) <= 0) continue;
            list.add(new class_3545(t, (Object)n));
        }
    }

    private void initWidgets(class_2378<T> class_23782) {
        WTable wTable = this.abc(arg_0 -> this.lambda$initWidgets$2(class_23782, arg_0), true, arg_0 -> this.lambda$initWidgets$3(class_23782, arg_0));
        if (wTable.cells.size() > 0) {
            this.table.add(this.theme.verticalSeparator()).expandWidgetY();
        }
        this.abc(this::lambda$initWidgets$4, false, arg_0 -> this.lambda$initWidgets$5(class_23782, arg_0));
    }

    private void addValue(class_2378<T> class_23782, T t) {
        if (!this.collection.contains(t)) {
            this.collection.add(t);
            this.setting.changed();
            this.table.clear();
            this.initWidgets(class_23782);
        }
    }

    protected abstract String getValueName(T var1);

    private void lambda$abc$7(WTable wTable, boolean bl, Consumer consumer, Object object) {
        if (!this.includeValue(object)) {
            return;
        }
        wTable.add(this.getValueWidget(object));
        WPressable wPressable = wTable.add(bl ? this.theme.plus() : this.theme.minus()).expandCellX().right().widget();
        wPressable.action = () -> LeftRightListSettingScreen.lambda$abc$6(consumer, object);
        wTable.row();
    }

    private static void lambda$abc$6(Consumer consumer, Object object) {
        consumer.accept(object);
    }

    public LeftRightListSettingScreen(GuiTheme guiTheme, String string, Setting<?> setting, Collection<T> collection, class_2378<T> class_23782) {
        super(guiTheme, string);
        this.setting = setting;
        this.collection = collection;
        this.filter = this.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
        this.filter.setFocused(true);
        this.filter.action = () -> this.lambda$new$0(class_23782);
        this.table = this.add(guiTheme.table()).expandX().widget();
        this.initWidgets(class_23782);
    }

    private void lambda$initWidgets$5(class_2378 class_23782, Object object) {
        this.removeValue(class_23782, object);
        Object object2 = this.getAdditionalValue(object);
        if (object2 != null) {
            this.removeValue(class_23782, object2);
        }
    }
}

