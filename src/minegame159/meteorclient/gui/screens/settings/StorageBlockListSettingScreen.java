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
    public StorageBlockListSettingScreen(GuiTheme lIIIlIIlIIIIlI, Setting<List<class_2591<?>>> lIIIlIIlIIIIIl) {
        super(lIIIlIIlIIIIlI, "Select storage blocks", lIIIlIIlIIIIIl, (Collection)lIIIlIIlIIIIIl.get(), StorageBlockListSetting.REGISTRY);
        StorageBlockListSettingScreen lIIIlIIlIIIIII;
    }

    @Override
    protected String getValueName(class_2591<?> lIIIlIIIllIIIl) {
        String lIIIlIIIllIIII = "Unknown";
        if (lIIIlIIIllIIIl == class_2591.field_11903) {
            lIIIlIIIllIIII = "Furnace";
        } else if (lIIIlIIIllIIIl == class_2591.field_11914) {
            lIIIlIIIllIIII = "Chest";
        } else if (lIIIlIIIllIIIl == class_2591.field_11891) {
            lIIIlIIIllIIII = "Trapped Chest";
        } else if (lIIIlIIIllIIIl == class_2591.field_11901) {
            lIIIlIIIllIIII = "Ender Chest";
        } else if (lIIIlIIIllIIIl == class_2591.field_11887) {
            lIIIlIIIllIIII = "Dispenser";
        } else if (lIIIlIIIllIIIl == class_2591.field_11899) {
            lIIIlIIIllIIII = "Dropper";
        } else if (lIIIlIIIllIIIl == class_2591.field_11888) {
            lIIIlIIIllIIII = "Hopper";
        } else if (lIIIlIIIllIIIl == class_2591.field_11896) {
            lIIIlIIIllIIII = "Shulker Box";
        } else if (lIIIlIIIllIIIl == class_2591.field_16411) {
            lIIIlIIIllIIII = "Barrel";
        } else if (lIIIlIIIllIIIl == class_2591.field_16414) {
            lIIIlIIIllIIII = "Smoker";
        } else if (lIIIlIIIllIIIl == class_2591.field_16415) {
            lIIIlIIIllIIII = "Blast Furnace";
        }
        return lIIIlIIIllIIII;
    }

    @Override
    protected WWidget getValueWidget(class_2591<?> lIIIlIIIllIllI) {
        StorageBlockListSettingScreen lIIIlIIIlllIlI;
        class_1792 lIIIlIIIlllIII = class_1802.field_8077;
        if (lIIIlIIIllIllI == class_2591.field_11903) {
            lIIIlIIIlllIII = class_1802.field_8732;
        } else if (lIIIlIIIllIllI == class_2591.field_11914) {
            lIIIlIIIlllIII = class_1802.field_8106;
        } else if (lIIIlIIIllIllI == class_2591.field_11891) {
            lIIIlIIIlllIII = class_1802.field_8247;
        } else if (lIIIlIIIllIllI == class_2591.field_11901) {
            lIIIlIIIlllIII = class_1802.field_8466;
        } else if (lIIIlIIIllIllI == class_2591.field_11887) {
            lIIIlIIIlllIII = class_1802.field_8357;
        } else if (lIIIlIIIllIllI == class_2591.field_11899) {
            lIIIlIIIlllIII = class_1802.field_8878;
        } else if (lIIIlIIIllIllI == class_2591.field_11888) {
            lIIIlIIIlllIII = class_1802.field_8239;
        } else if (lIIIlIIIllIllI == class_2591.field_11896) {
            lIIIlIIIlllIII = class_1802.field_8545;
        } else if (lIIIlIIIllIllI == class_2591.field_16411) {
            lIIIlIIIlllIII = class_1802.field_16307;
        } else if (lIIIlIIIllIllI == class_2591.field_16414) {
            lIIIlIIIlllIII = class_1802.field_16309;
        } else if (lIIIlIIIllIllI == class_2591.field_16415) {
            lIIIlIIIlllIII = class_1802.field_16306;
        }
        return lIIIlIIIlllIlI.theme.itemWithLabel(lIIIlIIIlllIII.method_7854(), lIIIlIIIlllIlI.getValueName(lIIIlIIIllIllI));
    }
}

