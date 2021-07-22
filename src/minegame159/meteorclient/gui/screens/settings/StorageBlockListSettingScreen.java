/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2591
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.List;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.StorageBlockListSetting;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2591;

public class StorageBlockListSettingScreen
extends LeftRightListSettingScreen<class_2591<?>> {
    @Override
    protected String getValueName(Object object) {
        return this.getValueName((class_2591)object);
    }

    public StorageBlockListSettingScreen(GuiTheme guiTheme, Setting<List<class_2591<?>>> setting) {
        super(guiTheme, "Select storage blocks", setting, (Collection)setting.get(), StorageBlockListSetting.REGISTRY);
    }

    @Override
    protected String getValueName(class_2591<?> class_25912) {
        String string = "Unknown";
        if (class_25912 == class_2591.field_11903) {
            string = "Furnace";
        } else if (class_25912 == class_2591.field_11914) {
            string = "Chest";
        } else if (class_25912 == class_2591.field_11891) {
            string = "Trapped Chest";
        } else if (class_25912 == class_2591.field_11901) {
            string = "Ender Chest";
        } else if (class_25912 == class_2591.field_11887) {
            string = "Dispenser";
        } else if (class_25912 == class_2591.field_11899) {
            string = "Dropper";
        } else if (class_25912 == class_2591.field_11888) {
            string = "Hopper";
        } else if (class_25912 == class_2591.field_11896) {
            string = "Shulker Box";
        } else if (class_25912 == class_2591.field_16411) {
            string = "Barrel";
        } else if (class_25912 == class_2591.field_16414) {
            string = "Smoker";
        } else if (class_25912 == class_2591.field_16415) {
            string = "Blast Furnace";
        }
        return string;
    }

    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((class_2591)object);
    }

    @Override
    protected WWidget getValueWidget(class_2591<?> class_25912) {
        class_1792 class_17922 = class_1802.field_8077;
        if (class_25912 == class_2591.field_11903) {
            class_17922 = class_1802.field_8732;
        } else if (class_25912 == class_2591.field_11914) {
            class_17922 = class_1802.field_8106;
        } else if (class_25912 == class_2591.field_11891) {
            class_17922 = class_1802.field_8247;
        } else if (class_25912 == class_2591.field_11901) {
            class_17922 = class_1802.field_8466;
        } else if (class_25912 == class_2591.field_11887) {
            class_17922 = class_1802.field_8357;
        } else if (class_25912 == class_2591.field_11899) {
            class_17922 = class_1802.field_8878;
        } else if (class_25912 == class_2591.field_11888) {
            class_17922 = class_1802.field_8239;
        } else if (class_25912 == class_2591.field_11896) {
            class_17922 = class_1802.field_8545;
        } else if (class_25912 == class_2591.field_16411) {
            class_17922 = class_1802.field_16307;
        } else if (class_25912 == class_2591.field_16414) {
            class_17922 = class_1802.field_16309;
        } else if (class_25912 == class_2591.field_16415) {
            class_17922 = class_1802.field_16306;
        }
        return this.theme.itemWithLabel(class_17922.method_7854(), this.getValueName(class_25912));
    }
}

