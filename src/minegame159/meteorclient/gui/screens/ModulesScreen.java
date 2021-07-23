/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens;

import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.Tabs;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.containers.WWindow;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1802;
import net.minecraft.class_3545;

public class ModulesScreen
extends TabScreen {
    private void lambda$createCategory$0(Category category, WContainer wContainer) {
        wContainer.add(this.theme.item(category.icon)).pad(2.0);
    }

    protected WCategoryController createCategoryContainer() {
        return new WCategoryController(this);
    }

    protected void createSearch(WContainer wContainer) {
        WWindow wWindow = this.theme.window("Search");
        wWindow.id = "search";
        if (this.theme.categoryIcons()) {
            wWindow.beforeHeaderInit = this::lambda$createSearch$1;
        }
        wContainer.add(wWindow);
        wWindow.view.scrollOnlyWhenMouseOver = true;
        wWindow.view.hasScrollBar = false;
        wWindow.view.maxHeight -= 20.0;
        WVerticalList wVerticalList = this.theme.verticalList();
        WTextBox wTextBox = wWindow.add(this.theme.textBox("")).minWidth(140.0).expandX().widget();
        wTextBox.setFocused(true);
        wTextBox.action = () -> this.lambda$createSearch$2(wVerticalList, wTextBox);
        wWindow.add(wVerticalList).expandX();
        this.createSearchW(wVerticalList, wTextBox.get());
    }

    protected void createSearchW(WContainer wContainer, String string) {
        if (!string.isEmpty()) {
            WSection wSection;
            List<class_3545<Module, Integer>> list = Modules.get().searchTitles(string);
            if (list.size() > 0) {
                wSection = wContainer.add(this.theme.section("Modules")).expandX().widget();
                wSection.spacing = 0.0;
                for (class_3545<Module, Integer> class_35452 : list) {
                    wSection.add(this.theme.module((Module)class_35452.method_15442())).expandX();
                }
            }
            if ((list = Modules.get().searchSettingTitles(string)).size() > 0) {
                wSection = wContainer.add(this.theme.section("Settings")).expandX().widget();
                wSection.spacing = 0.0;
                for (class_3545<Module, Integer> class_35452 : list) {
                    wSection.add(this.theme.module((Module)class_35452.method_15442())).expandX();
                }
            }
        }
    }

    private void lambda$createSearch$2(WVerticalList wVerticalList, WTextBox wTextBox) {
        wVerticalList.clear();
        this.createSearchW(wVerticalList, wTextBox.get());
    }

    protected void createCategory(WContainer wContainer, Category category) {
        WWindow wWindow = this.theme.window(category.name);
        wWindow.id = category.name;
        wWindow.padding = 0.0;
        wWindow.spacing = 0.0;
        if (this.theme.categoryIcons()) {
            wWindow.beforeHeaderInit = arg_0 -> this.lambda$createCategory$0(category, arg_0);
        }
        wContainer.add(wWindow);
        wWindow.view.scrollOnlyWhenMouseOver = true;
        wWindow.view.hasScrollBar = false;
        wWindow.view.spacing = 0.0;
        for (Module module : Modules.get().getGroup(category)) {
            wWindow.add(this.theme.module((Module)module)).expandX().widget().tooltip = module.description;
        }
    }

    private void lambda$createSearch$1(WContainer wContainer) {
        wContainer.add(this.theme.item(class_1802.field_8251.method_7854())).pad(2.0);
    }

    public ModulesScreen(GuiTheme guiTheme) {
        super(guiTheme, Tabs.get().get(0));
        this.add(this.createCategoryContainer());
        WVerticalList wVerticalList = this.add(guiTheme.verticalList()).pad(4.0).bottom().widget();
        wVerticalList.add(guiTheme.label("Left click - Toggle module"));
        wVerticalList.add(guiTheme.label("Right click - Open module settings"));
    }

    protected class WCategoryController
    extends WContainer {
        final ModulesScreen this$0;

        protected WCategoryController(ModulesScreen modulesScreen) {
            this.this$0 = modulesScreen;
        }

        @Override
        public void init() {
            for (Category category : Modules.loopCategories()) {
                this.this$0.createCategory(this, category);
            }
            this.this$0.createSearch(this);
        }

        @Override
        protected void onCalculateWidgetPositions() {
            double d = this.theme.scale(4.0);
            double d2 = this.theme.scale(40.0);
            double d3 = this.x + d;
            double d4 = this.y;
            for (Cell cell : this.cells) {
                double d5 = Utils.getWindowWidth();
                double d6 = Utils.getWindowHeight();
                if (d3 + cell.width > d5) {
                    d3 += d;
                    d4 += d2;
                }
                if (d3 > d5 && (d3 = d5 / 2.0 - cell.width / 2.0) < 0.0) {
                    d3 = 0.0;
                }
                if (d4 > d6 && (d4 = d6 / 2.0 - cell.height / 2.0) < 0.0) {
                    d4 = 0.0;
                }
                cell.x = d3;
                cell.y = d4;
                cell.width = ((WWidget)cell.widget()).width;
                cell.height = ((WWidget)cell.widget()).height;
                cell.alignWidget();
                d3 += cell.width + d;
            }
        }
    }
}

