/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1299
 *  net.minecraft.class_2378
 *  net.minecraft.class_3545
 */
package minegame159.meteorclient.gui.screens.settings;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.utils.Cell;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.misc.Names;
import net.minecraft.class_1299;
import net.minecraft.class_2378;
import net.minecraft.class_3545;

public class EntityTypeListSettingScreen
extends WindowScreen {
    private /* synthetic */ WSection ambient;
    /* synthetic */ int hasAmbient;
    private /* synthetic */ WSection animals;
    private /* synthetic */ WTable ambientT;
    /* synthetic */ int hasWaterAnimal;
    /* synthetic */ int hasMisc;
    private /* synthetic */ String filterText;
    private /* synthetic */ WTable monstersT;
    private /* synthetic */ WTable animalsT;
    private /* synthetic */ WTable waterAnimalsT;
    private /* synthetic */ WVerticalList list;
    private final /* synthetic */ EntityTypeListSetting setting;
    /* synthetic */ int hasAnimal;
    private /* synthetic */ WSection waterAnimals;
    private /* synthetic */ WSection misc;
    private /* synthetic */ WTable miscT;
    /* synthetic */ int hasMonster;
    private final /* synthetic */ WTextBox filter;
    private /* synthetic */ WSection monsters;

    private void tableChecked(List<class_1299<?>> llIlIIIlIllllll, boolean llIlIIIlIlllIlI) {
        EntityTypeListSettingScreen llIlIIIllIIIIII;
        boolean llIlIIIlIllllIl = false;
        for (class_1299<?> llIlIIIllIIIIIl : llIlIIIlIllllll) {
            if (llIlIIIlIlllIlI) {
                ((Object2BooleanMap)llIlIIIllIIIIII.setting.get()).put(llIlIIIllIIIIIl, true);
                llIlIIIlIllllIl = true;
                continue;
            }
            if (!((Object2BooleanMap)llIlIIIllIIIIII.setting.get()).removeBoolean(llIlIIIllIIIIIl)) continue;
            llIlIIIlIllllIl = true;
        }
        if (llIlIIIlIllllIl) {
            llIlIIIllIIIIII.list.clear();
            llIlIIIllIIIIII.initWidgets();
            llIlIIIllIIIIII.setting.changed();
        }
    }

    @Override
    public <W extends WWidget> Cell<W> add(W llIlIIlIIIIlIII) {
        EntityTypeListSettingScreen llIlIIlIIIIlIll;
        return llIlIIlIIIIlIll.list.add(llIlIIlIIIIlIII);
    }

    public EntityTypeListSettingScreen(GuiTheme llIlIIlIIIlIIlI, EntityTypeListSetting llIlIIlIIIIlllI) {
        super(llIlIIlIIIlIIlI, "Select entities");
        EntityTypeListSettingScreen llIlIIlIIIlIIll;
        llIlIIlIIIlIIll.filterText = "";
        llIlIIlIIIlIIll.hasAnimal = 0;
        llIlIIlIIIlIIll.hasWaterAnimal = 0;
        llIlIIlIIIlIIll.hasMonster = 0;
        llIlIIlIIIlIIll.hasAmbient = 0;
        llIlIIlIIIlIIll.hasMisc = 0;
        llIlIIlIIIlIIll.setting = llIlIIlIIIIlllI;
        llIlIIlIIIlIIll.filter = super.add(llIlIIlIIIlIIlI.textBox("")).minWidth(400.0).expandX().widget();
        llIlIIlIIIlIIll.filter.setFocused(true);
        llIlIIlIIIlIIll.filter.action = () -> {
            EntityTypeListSettingScreen llIlIIIIIlllIIl;
            llIlIIIIIlllIIl.filterText = llIlIIIIIlllIIl.filter.get().trim();
            llIlIIIIIlllIIl.list.clear();
            llIlIIIIIlllIIl.initWidgets();
        };
        llIlIIlIIIlIIll.list = super.add(llIlIIlIIIlIIlI.verticalList()).expandX().widget();
        llIlIIlIIIlIIll.initWidgets();
    }

    private void addEntityType(WTable llIlIIIlIllIIII, WCheckbox llIlIIIlIlIlIlI, class_1299<?> llIlIIIlIlIlllI) {
        EntityTypeListSettingScreen llIlIIIlIlIllII;
        llIlIIIlIllIIII.add(llIlIIIlIlIllII.theme.label(Names.get(llIlIIIlIlIlllI)));
        WCheckbox llIlIIIlIlIllIl = llIlIIIlIllIIII.add(llIlIIIlIlIllII.theme.checkbox(((Object2BooleanMap)llIlIIIlIlIllII.setting.get()).getBoolean(llIlIIIlIlIlllI))).expandCellX().right().widget();
        llIlIIIlIlIllIl.action = () -> {
            EntityTypeListSettingScreen llIlIIIlIlIIIll;
            if (llIlIIIlIIllllI.checked) {
                ((Object2BooleanMap)llIlIIIlIlIIIll.setting.get()).put((Object)llIlIIIlIlIlllI, true);
                switch (llIlIIIlIlIlllI.method_5891()) {
                    case field_6294: {
                        if (llIlIIIlIlIIIll.hasAnimal == 0) {
                            llIlIIIlIIlllII.checked = true;
                        }
                        ++llIlIIIlIlIIIll.hasAnimal;
                        break;
                    }
                    case field_24460: 
                    case field_6300: {
                        if (llIlIIIlIlIIIll.hasWaterAnimal == 0) {
                            llIlIIIlIIlllII.checked = true;
                        }
                        ++llIlIIIlIlIIIll.hasWaterAnimal;
                        break;
                    }
                    case field_6302: {
                        if (llIlIIIlIlIIIll.hasMonster == 0) {
                            llIlIIIlIIlllII.checked = true;
                        }
                        ++llIlIIIlIlIIIll.hasMonster;
                        break;
                    }
                    case field_6303: {
                        if (llIlIIIlIlIIIll.hasAmbient == 0) {
                            llIlIIIlIIlllII.checked = true;
                        }
                        ++llIlIIIlIlIIIll.hasAmbient;
                        break;
                    }
                    case field_17715: {
                        if (llIlIIIlIlIIIll.hasMisc == 0) {
                            llIlIIIlIIlllII.checked = true;
                        }
                        ++llIlIIIlIlIIIll.hasMisc;
                    }
                }
            } else if (((Object2BooleanMap)llIlIIIlIlIIIll.setting.get()).removeBoolean((Object)llIlIIIlIlIlllI)) {
                switch (llIlIIIlIlIlllI.method_5891()) {
                    case field_6294: {
                        --llIlIIIlIlIIIll.hasAnimal;
                        if (llIlIIIlIlIIIll.hasAnimal != 0) break;
                        llIlIIIlIIlllII.checked = false;
                        break;
                    }
                    case field_24460: 
                    case field_6300: {
                        --llIlIIIlIlIIIll.hasWaterAnimal;
                        if (llIlIIIlIlIIIll.hasWaterAnimal != 0) break;
                        llIlIIIlIIlllII.checked = false;
                        break;
                    }
                    case field_6302: {
                        --llIlIIIlIlIIIll.hasMonster;
                        if (llIlIIIlIlIIIll.hasMonster != 0) break;
                        llIlIIIlIIlllII.checked = false;
                        break;
                    }
                    case field_6303: {
                        --llIlIIIlIlIIIll.hasAmbient;
                        if (llIlIIIlIlIIIll.hasAmbient != 0) break;
                        llIlIIIlIIlllII.checked = false;
                        break;
                    }
                    case field_17715: {
                        --llIlIIIlIlIIIll.hasMisc;
                        if (llIlIIIlIlIIIll.hasMisc != 0) break;
                        llIlIIIlIIlllII.checked = false;
                    }
                }
            }
            llIlIIIlIlIIIll.setting.changed();
        };
        llIlIIIlIllIIII.row();
    }

    private void initWidgets() {
        EntityTypeListSettingScreen llIlIIIlllIlllI;
        llIlIIIlllIlllI.hasMisc = 0;
        llIlIIIlllIlllI.hasAmbient = 0;
        llIlIIIlllIlllI.hasMonster = 0;
        llIlIIIlllIlllI.hasWaterAnimal = 0;
        llIlIIIlllIlllI.hasAnimal = 0;
        for (class_1299 llIlIIIllllIIlI : ((Object2BooleanMap)llIlIIIlllIlllI.setting.get()).keySet()) {
            if (!((Object2BooleanMap)llIlIIIlllIlllI.setting.get()).getBoolean((Object)llIlIIIllllIIlI) || llIlIIIlllIlllI.setting.onlyAttackable && !EntityUtils.isAttackable(llIlIIIllllIIlI)) continue;
            switch (llIlIIIllllIIlI.method_5891()) {
                case field_6294: {
                    ++llIlIIIlllIlllI.hasAnimal;
                    break;
                }
                case field_24460: 
                case field_6300: {
                    ++llIlIIIlllIlllI.hasWaterAnimal;
                    break;
                }
                case field_6302: {
                    ++llIlIIIlllIlllI.hasMonster;
                    break;
                }
                case field_6303: {
                    ++llIlIIIlllIlllI.hasAmbient;
                    break;
                }
                case field_17715: {
                    ++llIlIIIlllIlllI.hasMisc;
                }
            }
        }
        boolean llIlIIIlllIllIl = llIlIIIlllIlllI.animals == null;
        ArrayList llIlIIIlllIllII = new ArrayList();
        WCheckbox llIlIIIlllIlIll = llIlIIIlllIlllI.theme.checkbox(llIlIIIlllIlllI.hasAnimal > 0);
        llIlIIIlllIlllI.animals = llIlIIIlllIlllI.theme.section("Animals", llIlIIIlllIlllI.animals != null && llIlIIIlllIlllI.animals.isExpanded(), llIlIIIlllIlIll);
        llIlIIIlllIlIll.action = () -> {
            EntityTypeListSettingScreen llIlIIIIIlllllI;
            llIlIIIIIlllllI.tableChecked(llIlIIIlllIllII, llIlIIIIIllllII.checked);
        };
        Cell<WSection> llIlIIIlllIlIlI = llIlIIIlllIlllI.add(llIlIIIlllIlllI.animals).expandX();
        llIlIIIlllIlllI.animalsT = llIlIIIlllIlllI.animals.add(llIlIIIlllIlllI.theme.table()).expandX().widget();
        ArrayList llIlIIIlllIlIIl = new ArrayList();
        WCheckbox llIlIIIlllIlIII = llIlIIIlllIlllI.theme.checkbox(llIlIIIlllIlllI.hasWaterAnimal > 0);
        llIlIIIlllIlllI.waterAnimals = llIlIIIlllIlllI.theme.section("Water Animals", llIlIIIlllIlllI.waterAnimals != null && llIlIIIlllIlllI.waterAnimals.isExpanded(), llIlIIIlllIlIII);
        llIlIIIlllIlIII.action = () -> {
            EntityTypeListSettingScreen llIlIIIIlIIIlll;
            llIlIIIIlIIIlll.tableChecked(llIlIIIlllIlIIl, llIlIIIIlIIIlIl.checked);
        };
        Cell<WSection> llIlIIIlllIIlll = llIlIIIlllIlllI.add(llIlIIIlllIlllI.waterAnimals).expandX();
        llIlIIIlllIlllI.waterAnimalsT = llIlIIIlllIlllI.waterAnimals.add(llIlIIIlllIlllI.theme.table()).expandX().widget();
        ArrayList llIlIIIlllIIllI = new ArrayList();
        WCheckbox llIlIIIlllIIlIl = llIlIIIlllIlllI.theme.checkbox(llIlIIIlllIlllI.hasMonster > 0);
        llIlIIIlllIlllI.monsters = llIlIIIlllIlllI.theme.section("Monsters", llIlIIIlllIlllI.monsters != null && llIlIIIlllIlllI.monsters.isExpanded(), llIlIIIlllIIlIl);
        llIlIIIlllIIlIl.action = () -> {
            EntityTypeListSettingScreen llIlIIIIlIlIIll;
            llIlIIIIlIlIIll.tableChecked(llIlIIIlllIIllI, llIlIIIIlIIlllI.checked);
        };
        Cell<WSection> llIlIIIlllIIlII = llIlIIIlllIlllI.add(llIlIIIlllIlllI.monsters).expandX();
        llIlIIIlllIlllI.monstersT = llIlIIIlllIlllI.monsters.add(llIlIIIlllIlllI.theme.table()).expandX().widget();
        ArrayList llIlIIIlllIIIll = new ArrayList();
        WCheckbox llIlIIIlllIIIlI = llIlIIIlllIlllI.theme.checkbox(llIlIIIlllIlllI.hasAmbient > 0);
        llIlIIIlllIlllI.ambient = llIlIIIlllIlllI.theme.section("Ambient", llIlIIIlllIlllI.ambient != null && llIlIIIlllIlllI.ambient.isExpanded(), llIlIIIlllIIIlI);
        llIlIIIlllIIIlI.action = () -> {
            EntityTypeListSettingScreen llIlIIIIlIlllII;
            llIlIIIIlIlllII.tableChecked(llIlIIIlllIIIll, llIlIIIIlIlIlll.checked);
        };
        Cell<WSection> llIlIIIlllIIIIl = llIlIIIlllIlllI.add(llIlIIIlllIlllI.ambient).expandX();
        llIlIIIlllIlllI.ambientT = llIlIIIlllIlllI.ambient.add(llIlIIIlllIlllI.theme.table()).expandX().widget();
        ArrayList llIlIIIlllIIIII = new ArrayList();
        WCheckbox llIlIIIllIlllll = llIlIIIlllIlllI.theme.checkbox(llIlIIIlllIlllI.hasMisc > 0);
        llIlIIIlllIlllI.misc = llIlIIIlllIlllI.theme.section("Misc", llIlIIIlllIlllI.misc != null && llIlIIIlllIlllI.misc.isExpanded(), llIlIIIllIlllll);
        llIlIIIllIlllll.action = () -> {
            EntityTypeListSettingScreen llIlIIIIllIIIlI;
            llIlIIIIllIIIlI.tableChecked(llIlIIIlllIIIII, llIlIIIIllIIIll.checked);
        };
        Cell<WSection> llIlIIIllIllllI = llIlIIIlllIlllI.add(llIlIIIlllIlllI.misc).expandX();
        llIlIIIlllIlllI.miscT = llIlIIIlllIlllI.misc.add(llIlIIIlllIlllI.theme.table()).expandX().widget();
        Consumer<class_1299> llIlIIIllIlllIl = llIlIIIIlllIlIl -> {
            EntityTypeListSettingScreen llIlIIIIlllIlII;
            if (!llIlIIIIlllIlII.setting.onlyAttackable || EntityUtils.isAttackable(llIlIIIIlllIlIl)) {
                switch (llIlIIIIlllIlIl.method_5891()) {
                    case field_6294: {
                        llIlIIIlllIllII.add(llIlIIIIlllIlIl);
                        llIlIIIIlllIlII.addEntityType(llIlIIIIlllIlII.animalsT, llIlIIIlllIlIll, (class_1299<?>)llIlIIIIlllIlIl);
                        break;
                    }
                    case field_24460: 
                    case field_6300: {
                        llIlIIIlllIlIIl.add(llIlIIIIlllIlIl);
                        llIlIIIIlllIlII.addEntityType(llIlIIIIlllIlII.waterAnimalsT, llIlIIIlllIlIII, (class_1299<?>)llIlIIIIlllIlIl);
                        break;
                    }
                    case field_6302: {
                        llIlIIIlllIIllI.add(llIlIIIIlllIlIl);
                        llIlIIIIlllIlII.addEntityType(llIlIIIIlllIlII.monstersT, llIlIIIlllIIlIl, (class_1299<?>)llIlIIIIlllIlIl);
                        break;
                    }
                    case field_6303: {
                        llIlIIIlllIIIll.add(llIlIIIIlllIlIl);
                        llIlIIIIlllIlII.addEntityType(llIlIIIIlllIlII.ambientT, llIlIIIlllIIIlI, (class_1299<?>)llIlIIIIlllIlIl);
                        break;
                    }
                    case field_17715: {
                        llIlIIIlllIIIII.add(llIlIIIIlllIlIl);
                        llIlIIIIlllIlII.addEntityType(llIlIIIIlllIlII.miscT, llIlIIIllIlllll, (class_1299<?>)llIlIIIIlllIlIl);
                    }
                }
            }
        };
        if (llIlIIIlllIlllI.filterText.isEmpty()) {
            class_2378.field_11145.forEach(llIlIIIllIlllIl);
        } else {
            ArrayList<class_3545> llIlIIIllllIIII = new ArrayList<class_3545>();
            class_2378.field_11145.forEach(llIlIIIlIIlIIlI -> {
                EntityTypeListSettingScreen llIlIIIlIIlIIII;
                int llIlIIIlIIlIIIl = Utils.search(Names.get(llIlIIIlIIlIIlI), llIlIIIlIIlIIII.filterText);
                if (llIlIIIlIIlIIIl > 0) {
                    llIlIIIllllIIII.add(new class_3545(llIlIIIlIIlIIlI, (Object)llIlIIIlIIlIIIl));
                }
            });
            llIlIIIllllIIII.sort(Comparator.comparingInt(llIlIIIlIIllIlI -> -((Integer)llIlIIIlIIllIlI.method_15441()).intValue()));
            for (class_3545 llIlIIIllllIIIl : llIlIIIllllIIII) {
                llIlIIIllIlllIl.accept((class_1299)llIlIIIllllIIIl.method_15442());
            }
        }
        if (llIlIIIlllIlllI.animalsT.cells.size() == 0) {
            llIlIIIlllIlllI.list.cells.remove(llIlIIIlllIlIlI);
        }
        if (llIlIIIlllIlllI.waterAnimalsT.cells.size() == 0) {
            llIlIIIlllIlllI.list.cells.remove(llIlIIIlllIIlll);
        }
        if (llIlIIIlllIlllI.monstersT.cells.size() == 0) {
            llIlIIIlllIlllI.list.cells.remove(llIlIIIlllIIlII);
        }
        if (llIlIIIlllIlllI.ambientT.cells.size() == 0) {
            llIlIIIlllIlllI.list.cells.remove(llIlIIIlllIIIIl);
        }
        if (llIlIIIlllIlllI.miscT.cells.size() == 0) {
            llIlIIIlllIlllI.list.cells.remove(llIlIIIllIllllI);
        }
        if (llIlIIIlllIllIl) {
            int llIlIIIlllIllll = (llIlIIIlllIlllI.hasWaterAnimal + llIlIIIlllIlllI.waterAnimals.cells.size() + llIlIIIlllIlllI.monsters.cells.size() + llIlIIIlllIlllI.ambient.cells.size() + llIlIIIlllIlllI.misc.cells.size()) / 2;
            if (llIlIIIlllIllll <= 20) {
                if (llIlIIIlllIlllI.animalsT.cells.size() > 0) {
                    llIlIIIlllIlllI.animals.setExpanded(true);
                }
                if (llIlIIIlllIlllI.waterAnimalsT.cells.size() > 0) {
                    llIlIIIlllIlllI.waterAnimals.setExpanded(true);
                }
                if (llIlIIIlllIlllI.monstersT.cells.size() > 0) {
                    llIlIIIlllIlllI.monsters.setExpanded(true);
                }
                if (llIlIIIlllIlllI.ambientT.cells.size() > 0) {
                    llIlIIIlllIlllI.ambient.setExpanded(true);
                }
                if (llIlIIIlllIlllI.miscT.cells.size() > 0) {
                    llIlIIIlllIlllI.misc.setExpanded(true);
                }
            } else {
                if (llIlIIIlllIlllI.animalsT.cells.size() > 0) {
                    llIlIIIlllIlllI.animals.setExpanded(false);
                }
                if (llIlIIIlllIlllI.waterAnimalsT.cells.size() > 0) {
                    llIlIIIlllIlllI.waterAnimals.setExpanded(false);
                }
                if (llIlIIIlllIlllI.monstersT.cells.size() > 0) {
                    llIlIIIlllIlllI.monsters.setExpanded(false);
                }
                if (llIlIIIlllIlllI.ambientT.cells.size() > 0) {
                    llIlIIIlllIlllI.ambient.setExpanded(false);
                }
                if (llIlIIIlllIlllI.miscT.cells.size() > 0) {
                    llIlIIIlllIlllI.misc.setExpanded(false);
                }
            }
        }
    }
}

