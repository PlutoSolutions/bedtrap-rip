/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_3545
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
    protected WCategoryController createCategoryContainer() {
        ModulesScreen lIlIllIIIIlIIII;
        return lIlIllIIIIlIIII.new WCategoryController();
    }

    protected void createSearch(WContainer lIlIlIllllIIIlI) {
        ModulesScreen lIlIlIlllIllllI;
        WWindow lIlIlIllllIIIIl = lIlIlIlllIllllI.theme.window("Search");
        lIlIlIllllIIIIl.id = "search";
        if (lIlIlIlllIllllI.theme.categoryIcons()) {
            lIlIlIllllIIIIl.beforeHeaderInit = lIlIlIlllIIllIl -> {
                ModulesScreen lIlIlIlllIIllII;
                lIlIlIlllIIllIl.add(lIlIlIlllIIllII.theme.item(class_1802.field_8251.method_7854())).pad(2.0);
            };
        }
        lIlIlIllllIIIlI.add(lIlIlIllllIIIIl);
        lIlIlIllllIIIIl.view.scrollOnlyWhenMouseOver = true;
        lIlIlIllllIIIIl.view.hasScrollBar = false;
        lIlIlIllllIIIIl.view.maxHeight -= 20.0;
        WVerticalList lIlIlIllllIIIII = lIlIlIlllIllllI.theme.verticalList();
        WTextBox lIlIlIlllIlllll = lIlIlIllllIIIIl.add(lIlIlIlllIllllI.theme.textBox("")).minWidth(140.0).expandX().widget();
        lIlIlIlllIlllll.setFocused(true);
        lIlIlIlllIlllll.action = () -> {
            ModulesScreen lIlIlIlllIlIIll;
            lIlIlIllllIIIII.clear();
            lIlIlIlllIlIIll.createSearchW(lIlIlIllllIIIII, lIlIlIlllIlllll.get());
        };
        lIlIlIllllIIIIl.add(lIlIlIllllIIIII).expandX();
        lIlIlIlllIllllI.createSearchW(lIlIlIllllIIIII, lIlIlIlllIlllll.get());
    }

    protected void createSearchW(WContainer lIlIlIllllIlllI, String lIlIlIlllllIIII) {
        if (!lIlIlIlllllIIII.isEmpty()) {
            ModulesScreen lIlIlIlllllIIlI;
            List<class_3545<Module, Integer>> lIlIlIlllllIIll = Modules.get().searchTitles(lIlIlIlllllIIII);
            if (lIlIlIlllllIIll.size() > 0) {
                WSection lIlIlIlllllIllI = lIlIlIllllIlllI.add(lIlIlIlllllIIlI.theme.section("Modules")).expandX().widget();
                lIlIlIlllllIllI.spacing = 0.0;
                for (class_3545<Module, Integer> lIlIlIlllllIlll : lIlIlIlllllIIll) {
                    lIlIlIlllllIllI.add(lIlIlIlllllIIlI.theme.module((Module)lIlIlIlllllIlll.method_15442())).expandX();
                }
            }
            if ((lIlIlIlllllIIll = Modules.get().searchSettingTitles(lIlIlIlllllIIII)).size() > 0) {
                WSection lIlIlIlllllIlII = lIlIlIllllIlllI.add(lIlIlIlllllIIlI.theme.section("Settings")).expandX().widget();
                lIlIlIlllllIlII.spacing = 0.0;
                for (class_3545<Module, Integer> lIlIlIlllllIlIl : lIlIlIlllllIIll) {
                    lIlIlIlllllIlII.add(lIlIlIlllllIIlI.theme.module((Module)lIlIlIlllllIlIl.method_15442())).expandX();
                }
            }
        }
    }

    protected void createCategory(WContainer lIlIllIIIIIIIll, Category lIlIllIIIIIIIlI) {
        ModulesScreen lIlIllIIIIIIlII;
        WWindow lIlIllIIIIIIlIl = lIlIllIIIIIIlII.theme.window(lIlIllIIIIIIIlI.name);
        lIlIllIIIIIIlIl.id = lIlIllIIIIIIIlI.name;
        lIlIllIIIIIIlIl.padding = 0.0;
        lIlIllIIIIIIlIl.spacing = 0.0;
        if (lIlIllIIIIIIlII.theme.categoryIcons()) {
            lIlIllIIIIIIlIl.beforeHeaderInit = lIlIlIlllIIIlIl -> {
                ModulesScreen lIlIlIlllIIIlll;
                lIlIlIlllIIIlIl.add(lIlIlIlllIIIlll.theme.item(lIlIlIlllIIIllI.icon)).pad(2.0);
            };
        }
        lIlIllIIIIIIIll.add(lIlIllIIIIIIlIl);
        lIlIllIIIIIIlIl.view.scrollOnlyWhenMouseOver = true;
        lIlIllIIIIIIlIl.view.hasScrollBar = false;
        lIlIllIIIIIIlIl.view.spacing = 0.0;
        for (Module lIlIllIIIIIlIIl : Modules.get().getGroup(lIlIllIIIIIIIlI)) {
            lIlIllIIIIIIlIl.add(lIlIllIIIIIIlII.theme.module((Module)lIlIllIIIIIlIIl)).expandX().widget().tooltip = lIlIllIIIIIlIIl.description;
        }
    }

    public ModulesScreen(GuiTheme lIlIllIIIIlIlII) {
        super(lIlIllIIIIlIlII, Tabs.get().get(0));
        ModulesScreen lIlIllIIIIllIII;
        lIlIllIIIIllIII.add(lIlIllIIIIllIII.createCategoryContainer());
        WVerticalList lIlIllIIIIlIllI = lIlIllIIIIllIII.add(lIlIllIIIIlIlII.verticalList()).pad(4.0).bottom().widget();
        lIlIllIIIIlIllI.add(lIlIllIIIIlIlII.label("Left click - Toggle module"));
        lIlIllIIIIlIllI.add(lIlIllIIIIlIlII.label("Right click - Open module settings"));
    }

    protected class WCategoryController
    extends WContainer {
        protected WCategoryController() {
            WCategoryController lllIIIIIlIIIlIl;
        }

        @Override
        public void init() {
            WCategoryController lllIIIIIIllllIl;
            for (Category lllIIIIIIlllllI : Modules.loopCategories()) {
                lllIIIIIIllllIl.ModulesScreen.this.createCategory(lllIIIIIIllllIl, lllIIIIIIlllllI);
            }
            lllIIIIIIllllIl.ModulesScreen.this.createSearch(lllIIIIIIllllIl);
        }

        @Override
        protected void onCalculateWidgetPositions() {
            WCategoryController lllIIIIIIlIlIII;
            double lllIIIIIIlIllII = lllIIIIIIlIlIII.theme.scale(4.0);
            double lllIIIIIIlIlIll = lllIIIIIIlIlIII.theme.scale(40.0);
            double lllIIIIIIlIlIlI = lllIIIIIIlIlIII.x + lllIIIIIIlIllII;
            double lllIIIIIIlIlIIl = lllIIIIIIlIlIII.y;
            for (Cell lllIIIIIIlIlllI : lllIIIIIIlIlIII.cells) {
                double lllIIIIIIllIIII = Utils.getWindowWidth();
                double lllIIIIIIlIllll = Utils.getWindowHeight();
                if (lllIIIIIIlIlIlI + lllIIIIIIlIlllI.width > lllIIIIIIllIIII) {
                    lllIIIIIIlIlIlI += lllIIIIIIlIllII;
                    lllIIIIIIlIlIIl += lllIIIIIIlIlIll;
                }
                if (lllIIIIIIlIlIlI > lllIIIIIIllIIII && (lllIIIIIIlIlIlI = lllIIIIIIllIIII / 2.0 - lllIIIIIIlIlllI.width / 2.0) < 0.0) {
                    lllIIIIIIlIlIlI = 0.0;
                }
                if (lllIIIIIIlIlIIl > lllIIIIIIlIllll && (lllIIIIIIlIlIIl = lllIIIIIIlIllll / 2.0 - lllIIIIIIlIlllI.height / 2.0) < 0.0) {
                    lllIIIIIIlIlIIl = 0.0;
                }
                lllIIIIIIlIlllI.x = lllIIIIIIlIlIlI;
                lllIIIIIIlIlllI.y = lllIIIIIIlIlIIl;
                lllIIIIIIlIlllI.width = ((WWidget)lllIIIIIIlIlllI.widget()).width;
                lllIIIIIIlIlllI.height = ((WWidget)lllIIIIIIlIlllI.widget()).height;
                lllIIIIIIlIlllI.alignWidget();
                lllIIIIIIlIlIlI += lllIIIIIIlIlllI.width + lllIIIIIIlIllII;
            }
        }
    }
}

