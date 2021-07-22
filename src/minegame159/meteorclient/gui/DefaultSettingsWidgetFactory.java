/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  net.minecraft.class_2248
 *  net.minecraft.class_437
 *  org.apache.commons.lang3.StringUtils
 */
package minegame159.meteorclient.gui;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.screens.settings.BlockDataSettingScreen;
import minegame159.meteorclient.gui.screens.settings.BlockListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.BlockSettingScreen;
import minegame159.meteorclient.gui.screens.settings.ColorSettingScreen;
import minegame159.meteorclient.gui.screens.settings.EnchListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.EntityTypeListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.ItemListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.ModuleListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.PacketBoolSettingScreen;
import minegame159.meteorclient.gui.screens.settings.ParticleTypeListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.PotionSettingScreen;
import minegame159.meteorclient.gui.screens.settings.SoundEventListSettingScreen;
import minegame159.meteorclient.gui.screens.settings.StatusEffectSettingScreen;
import minegame159.meteorclient.gui.screens.settings.StorageBlockListSettingScreen;
import minegame159.meteorclient.gui.utils.IScreenFactory;
import minegame159.meteorclient.gui.utils.SettingsWidgetFactory;
import minegame159.meteorclient.gui.widgets.WItem;
import minegame159.meteorclient.gui.widgets.WItemWithLabel;
import minegame159.meteorclient.gui.widgets.WKeybind;
import minegame159.meteorclient.gui.widgets.WQuad;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.input.WDoubleEdit;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BlockSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnchListSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.GenericSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.KeybindSetting;
import minegame159.meteorclient.settings.ModuleListSetting;
import minegame159.meteorclient.settings.PacketBoolSetting;
import minegame159.meteorclient.settings.ParticleTypeListSetting;
import minegame159.meteorclient.settings.PotionSetting;
import minegame159.meteorclient.settings.ProvidedStringSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.settings.SoundEventListSetting;
import minegame159.meteorclient.settings.StatusEffectSetting;
import minegame159.meteorclient.settings.StorageBlockListSetting;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.misc.MyPotion;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2248;
import net.minecraft.class_437;
import org.apache.commons.lang3.StringUtils;

public class DefaultSettingsWidgetFactory
implements SettingsWidgetFactory {
    private final /* synthetic */ GuiTheme theme;
    private final /* synthetic */ Map<Class<?>, Factory> factories;

    private void keybindW(WTable lllllllllllllllllIIIIIlllllllIII, KeybindSetting lllllllllllllllllIIIIIllllllIlll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllllllllIl;
        WKeybind lllllllllllllllllIIIIIlllllllIlI = lllllllllllllllllIIIIIlllllllIII.add(lllllllllllllllllIIIIIllllllllIl.theme.keybind((Keybind)lllllllllllllllllIIIIIllllllIlll.get(), ((Keybind)lllllllllllllllllIIIIIllllllIlll.getDefaultValue()).getValue())).expandX().widget();
        lllllllllllllllllIIIIIlllllllIlI.action = lllllllllllllllllIIIIIllllllIlll::changed;
        lllllllllllllllllIIIIIllllllIlll.widget = lllllllllllllllllIIIIIlllllllIlI;
    }

    private void stringW(WTable lllllllllllllllllIIIIlIIIIlIIllI, StringSetting lllllllllllllllllIIIIlIIIIlIIIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIIlIIIll;
        WTextBox lllllllllllllllllIIIIlIIIIlIIlII = lllllllllllllllllIIIIlIIIIlIIllI.add(lllllllllllllllllIIIIlIIIIlIIIll.theme.textBox((String)lllllllllllllllllIIIIlIIIIlIIIIl.get())).expandX().widget();
        lllllllllllllllllIIIIlIIIIlIIlII.action = () -> lllllllllllllllllIIIIlIIIIlIIIIl.set(lllllllllllllllllIIIIlIIIIlIIlII.get());
        lllllllllllllllllIIIIlIIIIlIIIll.reset(lllllllllllllllllIIIIlIIIIlIIllI, lllllllllllllllllIIIIlIIIIlIIIIl, () -> lllllllllllllllllIIIIlIIIIlIIlII.set((String)lllllllllllllllllIIIIlIIIIlIIIIl.get()));
    }

    private void colorW(WTable lllllllllllllllllIIIIlIIIIllIIII, ColorSetting lllllllllllllllllIIIIlIIIIlIllll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIIllIIIl;
        WHorizontalList lllllllllllllllllIIIIlIIIIllIlII = lllllllllllllllllIIIIlIIIIllIIII.add(lllllllllllllllllIIIIlIIIIllIIIl.theme.horizontalList()).expandX().widget();
        WQuad lllllllllllllllllIIIIlIIIIllIIll = lllllllllllllllllIIIIlIIIIllIlII.add(lllllllllllllllllIIIIlIIIIllIIIl.theme.quad((Color)lllllllllllllllllIIIIlIIIIlIllll.get())).widget();
        WButton lllllllllllllllllIIIIlIIIIllIIlI = lllllllllllllllllIIIIlIIIIllIlII.add(lllllllllllllllllIIIIlIIIIllIIIl.theme.button(GuiRenderer.EDIT)).widget();
        lllllllllllllllllIIIIlIIIIllIIlI.action = () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIllllIlI;
            Utils.mc.method_1507((class_437)new ColorSettingScreen(lllllllllllllllllIIIIIlIIllllIlI.theme, lllllllllllllllllIIIIlIIIIlIllll));
        };
        lllllllllllllllllIIIIlIIIIllIIIl.reset(lllllllllllllllllIIIIlIIIIllIIII, lllllllllllllllllIIIIlIIIIlIllll, () -> {
            lllllllllllllllllIIIIIlIIllllllI.color = (Color)lllllllllllllllllIIIIlIIIIlIllll.get();
        });
    }

    private void soundEventListW(WTable lllllllllllllllllIIIIIlllIlIIIll, SoundEventListSetting lllllllllllllllllIIIIIlllIlIIlIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIlIIlll;
        lllllllllllllllllIIIIIlllIlIIlll.selectW(lllllllllllllllllIIIIIlllIlIIIll, lllllllllllllllllIIIIIlllIlIIlIl, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIlIIlIIl;
            Utils.mc.method_1507((class_437)new SoundEventListSettingScreen(lllllllllllllllllIIIIIllIlIIlIIl.theme, lllllllllllllllllIIIIIlllIlIIlIl));
        });
    }

    private void blockDataSettingW(WTable lllllllllllllllllIIIIIlllIIIIllI, BlockDataSetting<?> lllllllllllllllllIIIIIlllIIIIlIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIIIIlll;
        WButton lllllllllllllllllIIIIIlllIIIlIII = lllllllllllllllllIIIIIlllIIIIllI.add(lllllllllllllllllIIIIIlllIIIIlll.theme.button(GuiRenderer.EDIT)).expandCellX().widget();
        lllllllllllllllllIIIIIlllIIIlIII.action = () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIlIlllIl;
            Utils.mc.method_1507((class_437)new BlockDataSettingScreen(lllllllllllllllllIIIIIllIlIlllIl.theme, lllllllllllllllllIIIIIlllIIIIlIl));
        };
        lllllllllllllllllIIIIIlllIIIIlll.reset(lllllllllllllllllIIIIIlllIIIIllI, lllllllllllllllllIIIIIlllIIIIlIl, null);
    }

    private void doubleW(WTable lllllllllllllllllIIIIlIIIllIIlII, DoubleSetting lllllllllllllllllIIIIlIIIllIIIll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIllIIIII;
        WDoubleEdit lllllllllllllllllIIIIlIIIllIIIlI = lllllllllllllllllIIIIlIIIllIIIII.theme.doubleEdit((Double)lllllllllllllllllIIIIlIIIllIIIll.get(), lllllllllllllllllIIIIlIIIllIIIll.getSliderMin(), lllllllllllllllllIIIIlIIIllIIIll.getSliderMax());
        lllllllllllllllllIIIIlIIIllIIIlI.min = lllllllllllllllllIIIIlIIIllIIIll.min;
        lllllllllllllllllIIIIlIIIllIIIlI.max = lllllllllllllllllIIIIlIIIllIIIll.max;
        lllllllllllllllllIIIIlIIIllIIIlI.decimalPlaces = lllllllllllllllllIIIIlIIIllIIIll.decimalPlaces;
        lllllllllllllllllIIIIlIIIllIIlII.add(lllllllllllllllllIIIIlIIIllIIIlI).expandX();
        Runnable lllllllllllllllllIIIIlIIIllIIIIl = () -> {
            if (!lllllllllllllllllIIIIlIIIllIIIll.set(lllllllllllllllllIIIIlIIIllIIIlI.get())) {
                lllllllllllllllllIIIIlIIIllIIIlI.set((Double)lllllllllllllllllIIIIlIIIllIIIll.get());
            }
        };
        if (lllllllllllllllllIIIIlIIIllIIIll.onSliderRelease) {
            lllllllllllllllllIIIIlIIIllIIIlI.actionOnRelease = lllllllllllllllllIIIIlIIIllIIIIl;
        } else {
            lllllllllllllllllIIIIlIIIllIIIlI.action = lllllllllllllllllIIIIlIIIllIIIIl;
        }
        lllllllllllllllllIIIIlIIIllIIIII.reset(lllllllllllllllllIIIIlIIIllIIlII, lllllllllllllllllIIIIlIIIllIIIll, () -> lllllllllllllllllIIIIlIIIllIIIlI.set((Double)lllllllllllllllllIIIIlIIIllIIIll.get()));
    }

    private void selectW(WContainer lllllllllllllllllIIIIIllIllllIII, Setting<?> lllllllllllllllllIIIIIllIlllllII, Runnable lllllllllllllllllIIIIIllIllllIll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIllllIIl;
        WButton lllllllllllllllllIIIIIllIllllIlI = lllllllllllllllllIIIIIllIllllIII.add(lllllllllllllllllIIIIIllIllllIIl.theme.button("Select")).expandCellX().widget();
        lllllllllllllllllIIIIIllIllllIlI.action = lllllllllllllllllIIIIIllIllllIll;
        lllllllllllllllllIIIIIllIllllIIl.reset(lllllllllllllllllIIIIIllIllllIII, lllllllllllllllllIIIIIllIlllllII, null);
    }

    private void boolW(WTable lllllllllllllllllIIIIlIIIllllIIl, BoolSetting lllllllllllllllllIIIIlIIIlllllII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIllllllI;
        WCheckbox lllllllllllllllllIIIIlIIIllllIll = lllllllllllllllllIIIIlIIIllllIIl.add(lllllllllllllllllIIIIlIIIllllllI.theme.checkbox((Boolean)lllllllllllllllllIIIIlIIIlllllII.get())).expandCellX().widget();
        lllllllllllllllllIIIIlIIIllllIll.action = () -> lllllllllllllllllIIIIlIIIlllllII.set(lllllllllllllllllIIIIIlIIIlIllll.checked);
        lllllllllllllllllIIIIlIIIllllllI.reset(lllllllllllllllllIIIIlIIIllllIIl, lllllllllllllllllIIIIlIIIlllllII, () -> {
            lllllllllllllllllIIIIIlIIIlllIII.checked = (Boolean)lllllllllllllllllIIIIlIIIlllllII.get();
        });
    }

    private void entityTypeListW(WTable lllllllllllllllllIIIIIllllIlIIll, EntityTypeListSetting lllllllllllllllllIIIIIllllIlIIlI) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllllIlIIIl;
        lllllllllllllllllIIIIIllllIlIIIl.selectW(lllllllllllllllllIIIIIllllIlIIll, lllllllllllllllllIIIIIllllIlIIlI, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIIlIIlll;
            Utils.mc.method_1507((class_437)new EntityTypeListSettingScreen(lllllllllllllllllIIIIIllIIlIIlll.theme, lllllllllllllllllIIIIIllllIlIIlI));
        });
    }

    private void reset(WContainer lllllllllllllllllIIIIIllIllIlllI, Setting<?> lllllllllllllllllIIIIIllIllIllIl, Runnable lllllllllllllllllIIIIIllIllIIlll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIllIllll;
        WButton lllllllllllllllllIIIIIllIllIlIll = lllllllllllllllllIIIIIllIllIlllI.add(lllllllllllllllllIIIIIllIllIllll.theme.button(GuiRenderer.RESET)).widget();
        lllllllllllllllllIIIIIllIllIlIll.action = () -> {
            lllllllllllllllllIIIIIllIllIllIl.reset();
            if (lllllllllllllllllIIIIIllIllIIlll != null) {
                lllllllllllllllllIIIIIllIllIIlll.run();
            }
        };
    }

    private void potionW(WTable lllllllllllllllllIIIIlIIIlIIIIlI, PotionSetting lllllllllllllllllIIIIlIIIlIIIIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIlIIlIIl;
        WHorizontalList lllllllllllllllllIIIIlIIIlIIIllI = lllllllllllllllllIIIIlIIIlIIIIlI.add(lllllllllllllllllIIIIlIIIlIIlIIl.theme.horizontalList()).expandX().widget();
        WItemWithLabel lllllllllllllllllIIIIlIIIlIIIlIl = lllllllllllllllllIIIIlIIIlIIIllI.add(lllllllllllllllllIIIIlIIIlIIlIIl.theme.itemWithLabel(((MyPotion)lllllllllllllllllIIIIlIIIlIIIIIl.get()).potion, ((MyPotion)lllllllllllllllllIIIIlIIIlIIIIIl.get()).potion.method_7964().getString())).widget();
        WButton lllllllllllllllllIIIIlIIIlIIIlII = lllllllllllllllllIIIIlIIIlIIIllI.add(lllllllllllllllllIIIIlIIIlIIlIIl.theme.button("Select")).expandCellX().widget();
        lllllllllllllllllIIIIlIIIlIIIlII.action = () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIllIlIII;
            PotionSettingScreen lllllllllllllllllIIIIIlIIllIlIIl = new PotionSettingScreen(lllllllllllllllllIIIIIlIIllIlIII.theme, lllllllllllllllllIIIIlIIIlIIIIIl);
            lllllllllllllllllIIIIIlIIllIlIIl.onClosed(() -> lllllllllllllllllIIIIlIIIlIIIlIl.set(((MyPotion)lllllllllllllllllIIIIIlIIllIIIIl.get()).potion));
            Utils.mc.method_1507((class_437)lllllllllllllllllIIIIIlIIllIlIIl);
        };
        lllllllllllllllllIIIIlIIIlIIlIIl.reset(lllllllllllllllllIIIIlIIIlIIIllI, lllllllllllllllllIIIIlIIIlIIIIIl, () -> lllllllllllllllllIIIIlIIIlIIIlIl.set(((MyPotion)lllllllllllllllllIIIIIlIIlllIIIl.get()).potion));
    }

    private void blockW(WTable lllllllllllllllllIIIIlIIIIIIIllI, BlockSetting lllllllllllllllllIIIIlIIIIIIIlIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIIIIllIl;
        WHorizontalList lllllllllllllllllIIIIlIIIIIIlIlI = lllllllllllllllllIIIIlIIIIIIIllI.add(lllllllllllllllllIIIIlIIIIIIllIl.theme.horizontalList()).expandX().widget();
        WItem lllllllllllllllllIIIIlIIIIIIlIIl = lllllllllllllllllIIIIlIIIIIIlIlI.add(lllllllllllllllllIIIIlIIIIIIllIl.theme.item(((class_2248)lllllllllllllllllIIIIlIIIIIIIlIl.get()).method_8389().method_7854())).widget();
        WButton lllllllllllllllllIIIIlIIIIIIlIII = lllllllllllllllllIIIIlIIIIIIlIlI.add(lllllllllllllllllIIIIlIIIIIIllIl.theme.button("Select")).widget();
        lllllllllllllllllIIIIlIIIIIIlIII.action = () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIlIlIIlII;
            BlockSettingScreen lllllllllllllllllIIIIIlIlIlIIlIl = new BlockSettingScreen(lllllllllllllllllIIIIIlIlIlIIlII.theme, lllllllllllllllllIIIIlIIIIIIIlIl);
            lllllllllllllllllIIIIIlIlIlIIlIl.onClosed(() -> lllllllllllllllllIIIIlIIIIIIlIIl.set(((class_2248)lllllllllllllllllIIIIlIIIIIIIlIl.get()).method_8389().method_7854()));
            Utils.mc.method_1507((class_437)lllllllllllllllllIIIIIlIlIlIIlIl);
        };
        lllllllllllllllllIIIIlIIIIIIllIl.reset(lllllllllllllllllIIIIlIIIIIIIllI, lllllllllllllllllIIIIlIIIIIIIlIl, () -> lllllllllllllllllIIIIlIIIIIIlIIl.set(((class_2248)lllllllllllllllllIIIIlIIIIIIIlIl.get()).method_8389().method_7854()));
    }

    private void providedStringW(WTable lllllllllllllllllIIIIlIIIIIllIlI, ProvidedStringSetting lllllllllllllllllIIIIlIIIIIllIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIIIllIll;
        WDropdown<String> lllllllllllllllllIIIIlIIIIIllIII = lllllllllllllllllIIIIlIIIIIllIlI.add(lllllllllllllllllIIIIlIIIIIllIll.theme.dropdown(lllllllllllllllllIIIIlIIIIIllIIl.supplier.get(), (String)lllllllllllllllllIIIIlIIIIIllIIl.get())).expandCellX().widget();
        lllllllllllllllllIIIIlIIIIIllIII.action = () -> lllllllllllllllllIIIIlIIIIIllIIl.set((String)lllllllllllllllllIIIIlIIIIIllIII.get());
        lllllllllllllllllIIIIlIIIIIllIll.reset(lllllllllllllllllIIIIlIIIIIllIlI, lllllllllllllllllIIIIlIIIIIllIIl, () -> lllllllllllllllllIIIIlIIIIIllIII.set((String)lllllllllllllllllIIIIlIIIIIllIIl.get()));
    }

    public DefaultSettingsWidgetFactory(GuiTheme lllllllllllllllllIIIIlIIllIIIIII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIllIIIIIl;
        lllllllllllllllllIIIIlIIllIIIIIl.factories = new HashMap();
        lllllllllllllllllIIIIlIIllIIIIIl.theme = lllllllllllllllllIIIIlIIllIIIIII;
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(BoolSetting.class, (lllllllllllllllllIIIIIIlIllIIlll, lllllllllllllllllIIIIIIlIllIIllI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlIllIIlIl;
            lllllllllllllllllIIIIIIlIllIIlIl.boolW(lllllllllllllllllIIIIIIlIllIIlll, (BoolSetting)lllllllllllllllllIIIIIIlIllIIllI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(IntSetting.class, (lllllllllllllllllIIIIIIlIllIllIl, lllllllllllllllllIIIIIIlIllIllll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlIllIlllI;
            lllllllllllllllllIIIIIIlIllIlllI.intW(lllllllllllllllllIIIIIIlIllIllIl, (IntSetting)lllllllllllllllllIIIIIIlIllIllll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(DoubleSetting.class, (lllllllllllllllllIIIIIIlIlllIllI, lllllllllllllllllIIIIIIlIllllIII) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlIlllIlll;
            lllllllllllllllllIIIIIIlIlllIlll.doubleW(lllllllllllllllllIIIIIIlIlllIllI, (DoubleSetting)lllllllllllllllllIIIIIIlIllllIII);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(EnumSetting.class, (lllllllllllllllllIIIIIIlIlllllll, lllllllllllllllllIIIIIIllIIIIIIl) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIIIIIll;
            lllllllllllllllllIIIIIIllIIIIIll.enumW(lllllllllllllllllIIIIIIlIlllllll, (EnumSetting)lllllllllllllllllIIIIIIllIIIIIIl);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(PotionSetting.class, (lllllllllllllllllIIIIIIllIIIlIII, lllllllllllllllllIIIIIIllIIIlIlI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIIIlIIl;
            lllllllllllllllllIIIIIIllIIIlIIl.potionW(lllllllllllllllllIIIIIIllIIIlIII, (PotionSetting)lllllllllllllllllIIIIIIllIIIlIlI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(ColorSetting.class, (lllllllllllllllllIIIIIIllIIlIlII, lllllllllllllllllIIIIIIllIIlIIll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIIlIIlI;
            lllllllllllllllllIIIIIIllIIlIIlI.colorW(lllllllllllllllllIIIIIIllIIlIlII, (ColorSetting)lllllllllllllllllIIIIIIllIIlIIll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(StringSetting.class, (lllllllllllllllllIIIIIIllIIllIlI, lllllllllllllllllIIIIIIllIIllIIl) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIIllllI;
            lllllllllllllllllIIIIIIllIIllllI.stringW(lllllllllllllllllIIIIIIllIIllIlI, (StringSetting)lllllllllllllllllIIIIIIllIIllIIl);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(ProvidedStringSetting.class, (lllllllllllllllllIIIIIIllIlIIIll, lllllllllllllllllIIIIIIllIlIIIlI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIlIIlll;
            lllllllllllllllllIIIIIIllIlIIlll.providedStringW(lllllllllllllllllIIIIIIllIlIIIll, (ProvidedStringSetting)lllllllllllllllllIIIIIIllIlIIIlI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(BlockSetting.class, (lllllllllllllllllIIIIIIllIlIllII, lllllllllllllllllIIIIIIllIlIlIll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIlIllIl;
            lllllllllllllllllIIIIIIllIlIllIl.blockW(lllllllllllllllllIIIIIIllIlIllII, (BlockSetting)lllllllllllllllllIIIIIIllIlIlIll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(KeybindSetting.class, (lllllllllllllllllIIIIIIllIllIlIl, lllllllllllllllllIIIIIIllIllIlll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIlllIIl;
            lllllllllllllllllIIIIIIllIlllIIl.keybindW(lllllllllllllllllIIIIIIllIllIlIl, (KeybindSetting)lllllllllllllllllIIIIIIllIllIlll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(GenericSetting.class, (lllllllllllllllllIIIIIIllIlllllI, lllllllllllllllllIIIIIIlllIIIIII) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllIllllll;
            lllllllllllllllllIIIIIIllIllllll.genericW(lllllllllllllllllIIIIIIllIlllllI, (GenericSetting)lllllllllllllllllIIIIIIlllIIIIII);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(BlockListSetting.class, (lllllllllllllllllIIIIIIlllIIlIlI, lllllllllllllllllIIIIIIlllIIIllI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlllIIlIII;
            lllllllllllllllllIIIIIIlllIIlIII.blockListW(lllllllllllllllllIIIIIIlllIIlIlI, (BlockListSetting)lllllllllllllllllIIIIIIlllIIIllI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(ItemListSetting.class, (lllllllllllllllllIIIIIIlllIlIIll, lllllllllllllllllIIIIIIlllIlIIlI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlllIlIIIl;
            lllllllllllllllllIIIIIIlllIlIIIl.itemListW(lllllllllllllllllIIIIIIlllIlIIll, (ItemListSetting)lllllllllllllllllIIIIIIlllIlIIlI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(EntityTypeListSetting.class, (lllllllllllllllllIIIIIIlllIlllII, lllllllllllllllllIIIIIIlllIllIll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIlllIlllIl;
            lllllllllllllllllIIIIIIlllIlllIl.entityTypeListW(lllllllllllllllllIIIIIIlllIlllII, (EntityTypeListSetting)lllllllllllllllllIIIIIIlllIllIll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(EnchListSetting.class, (lllllllllllllllllIIIIIIllllIIIlI, lllllllllllllllllIIIIIIllllIIIIl) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllllIIIll;
            lllllllllllllllllIIIIIIllllIIIll.enchListW(lllllllllllllllllIIIIIIllllIIIlI, (EnchListSetting)lllllllllllllllllIIIIIIllllIIIIl);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(ModuleListSetting.class, (lllllllllllllllllIIIIIIllllIlllI, lllllllllllllllllIIIIIIllllIllIl) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllllIllll;
            lllllllllllllllllIIIIIIllllIllll.moduleListW(lllllllllllllllllIIIIIIllllIlllI, (ModuleListSetting)lllllllllllllllllIIIIIIllllIllIl);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(PacketBoolSetting.class, (lllllllllllllllllIIIIIIlllllIlII, lllllllllllllllllIIIIIIlllllIIll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllllllIII;
            lllllllllllllllllIIIIIIllllllIII.packetBoolW(lllllllllllllllllIIIIIIlllllIlII, (PacketBoolSetting)lllllllllllllllllIIIIIIlllllIIll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(ParticleTypeListSetting.class, (lllllllllllllllllIIIIIlIIIIIIIII, lllllllllllllllllIIIIIIlllllllll) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIIllllllllI;
            lllllllllllllllllIIIIIIllllllllI.particleEffectListW(lllllllllllllllllIIIIIlIIIIIIIII, (ParticleTypeListSetting)lllllllllllllllllIIIIIIlllllllll);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(SoundEventListSetting.class, (lllllllllllllllllIIIIIlIIIIIlIIl, lllllllllllllllllIIIIIlIIIIIlIII) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIIIIlIlI;
            lllllllllllllllllIIIIIlIIIIIlIlI.soundEventListW(lllllllllllllllllIIIIIlIIIIIlIIl, (SoundEventListSetting)lllllllllllllllllIIIIIlIIIIIlIII);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(StatusEffectSetting.class, (lllllllllllllllllIIIIIlIIIIIllll, lllllllllllllllllIIIIIlIIIIIlllI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIIIlIIII;
            lllllllllllllllllIIIIIlIIIIlIIII.statusEffectW(lllllllllllllllllIIIIIlIIIIIllll, (StatusEffectSetting)lllllllllllllllllIIIIIlIIIIIlllI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(StorageBlockListSetting.class, (lllllllllllllllllIIIIIlIIIIllIll, lllllllllllllllllIIIIIlIIIIllIlI) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIIIlllII;
            lllllllllllllllllIIIIIlIIIIlllII.storageBlockListW(lllllllllllllllllIIIIIlIIIIllIll, (StorageBlockListSetting)lllllllllllllllllIIIIIlIIIIllIlI);
        });
        lllllllllllllllllIIIIlIIllIIIIIl.factories.put(BlockDataSetting.class, (lllllllllllllllllIIIIIlIIIlIIlII, lllllllllllllllllIIIIIlIIIlIIIII) -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIIIlIIIlI;
            lllllllllllllllllIIIIIlIIIlIIIlI.blockDataSettingW(lllllllllllllllllIIIIIlIIIlIIlII, (BlockDataSetting)lllllllllllllllllIIIIIlIIIlIIIII);
        });
    }

    private void blockListW(WTable lllllllllllllllllIIIIIlllllIIlIl, BlockListSetting lllllllllllllllllIIIIIlllllIIIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllllIIIll;
        lllllllllllllllllIIIIIlllllIIIll.selectW(lllllllllllllllllIIIIIlllllIIlIl, lllllllllllllllllIIIIIlllllIIIIl, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIlIlllIlI;
            Utils.mc.method_1507((class_437)new BlockListSettingScreen(lllllllllllllllllIIIIIlIlIlllIlI.theme, lllllllllllllllllIIIIIlllllIIIIl));
        });
    }

    private <T extends Enum<?>> void enumW(WTable lllllllllllllllllIIIIlIIIlIlIIlI, EnumSetting<T> lllllllllllllllllIIIIlIIIlIlIlIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIlIlIIll;
        WDropdown<Enum> lllllllllllllllllIIIIlIIIlIlIlII = lllllllllllllllllIIIIlIIIlIlIIlI.add(lllllllllllllllllIIIIlIIIlIlIIll.theme.dropdown((Enum)lllllllllllllllllIIIIlIIIlIlIlIl.get())).expandCellX().widget();
        lllllllllllllllllIIIIlIIIlIlIlII.action = () -> lllllllllllllllllIIIIlIIIlIlIlIl.set((Enum)lllllllllllllllllIIIIlIIIlIlIlII.get());
        lllllllllllllllllIIIIlIIIlIlIIll.reset(lllllllllllllllllIIIIlIIIlIlIIlI, lllllllllllllllllIIIIlIIIlIlIlIl, () -> lllllllllllllllllIIIIlIIIlIlIlII.set((Enum)lllllllllllllllllIIIIlIIIlIlIlIl.get()));
    }

    private void packetBoolW(WTable lllllllllllllllllIIIIIlllIllIlIl, PacketBoolSetting lllllllllllllllllIIIIIlllIllIlII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIlllIIl;
        lllllllllllllllllIIIIIlllIlllIIl.selectW(lllllllllllllllllIIIIIlllIllIlIl, lllllllllllllllllIIIIIlllIllIlII, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIIllllII;
            Utils.mc.method_1507((class_437)new PacketBoolSettingScreen(lllllllllllllllllIIIIIllIIllllII.theme, lllllllllllllllllIIIIIlllIllIlII));
        });
    }

    private void itemListW(WTable lllllllllllllllllIIIIIllllIlllII, ItemListSetting lllllllllllllllllIIIIIllllIllIll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllllIlllIl;
        lllllllllllllllllIIIIIllllIlllIl.selectW(lllllllllllllllllIIIIIllllIlllII, lllllllllllllllllIIIIIllllIllIll, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIIlIIIII;
            Utils.mc.method_1507((class_437)new ItemListSettingScreen(lllllllllllllllllIIIIIllIIlIIIII.theme, lllllllllllllllllIIIIIllllIllIll));
        });
    }

    private void statusEffectW(WTable lllllllllllllllllIIIIIlllIIllIlI, StatusEffectSetting lllllllllllllllllIIIIIlllIIllIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIIllIll;
        lllllllllllllllllIIIIIlllIIllIll.selectW(lllllllllllllllllIIIIIlllIIllIlI, lllllllllllllllllIIIIIlllIIllIIl, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIlIIllll;
            Utils.mc.method_1507((class_437)new StatusEffectSettingScreen(lllllllllllllllllIIIIIllIlIIllll.theme, lllllllllllllllllIIIIIlllIIllIIl));
        });
    }

    private void storageBlockListW(WTable lllllllllllllllllIIIIIlllIIlIIIl, StorageBlockListSetting lllllllllllllllllIIIIIlllIIlIIII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIIlIlIl;
        lllllllllllllllllIIIIIlllIIlIlIl.selectW(lllllllllllllllllIIIIIlllIIlIIIl, lllllllllllllllllIIIIIlllIIlIIII, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIlIlIlIl;
            Utils.mc.method_1507((class_437)new StorageBlockListSettingScreen(lllllllllllllllllIIIIIllIlIlIlIl.theme, lllllllllllllllllIIIIIlllIIlIIII));
        });
    }

    private void genericW(WTable lllllllllllllllllIIIIIlllllIllII, GenericSetting<?> lllllllllllllllllIIIIIlllllIllll) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllllIllIl;
        WButton lllllllllllllllllIIIIIlllllIlllI = lllllllllllllllllIIIIIlllllIllII.add(lllllllllllllllllIIIIIlllllIllIl.theme.button(GuiRenderer.EDIT)).widget();
        lllllllllllllllllIIIIIlllllIlllI.action = () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlIlIllIllI;
            Utils.mc.method_1507((class_437)((IScreenFactory)lllllllllllllllllIIIIIlllllIllll.get()).createScreen(lllllllllllllllllIIIIIlIlIllIllI.theme));
        };
        lllllllllllllllllIIIIIlllllIllIl.reset(lllllllllllllllllIIIIIlllllIllII, lllllllllllllllllIIIIIlllllIllll, null);
    }

    private void particleEffectListW(WTable lllllllllllllllllIIIIIlllIlIllll, ParticleTypeListSetting lllllllllllllllllIIIIIlllIlIlllI) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIlllIllIIII;
        lllllllllllllllllIIIIIlllIllIIII.selectW(lllllllllllllllllIIIIIlllIlIllll, lllllllllllllllllIIIIIlllIlIlllI, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIlIIIlIl;
            Utils.mc.method_1507((class_437)new ParticleTypeListSettingScreen(lllllllllllllllllIIIIIllIlIIIlIl.theme, lllllllllllllllllIIIIIlllIlIlllI));
        });
    }

    private void moduleListW(WTable lllllllllllllllllIIIIIllllIIIIIl, ModuleListSetting lllllllllllllllllIIIIIllllIIIIII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllllIIIIlI;
        lllllllllllllllllIIIIIllllIIIIlI.selectW(lllllllllllllllllIIIIIllllIIIIIl, lllllllllllllllllIIIIIllllIIIIII, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIIllIllI;
            Utils.mc.method_1507((class_437)new ModuleListSettingScreen(lllllllllllllllllIIIIIllIIllIllI.theme, lllllllllllllllllIIIIIllllIIIIII));
        });
    }

    private void group(WVerticalList lllllllllllllllllIIIIlIIlIIIllIl, SettingGroup lllllllllllllllllIIIIlIIlIIIllII, String lllllllllllllllllIIIIlIIlIIIlIll, List<RemoveInfo> lllllllllllllllllIIIIlIIlIIIlIlI) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIlIIlIllI;
        WSection lllllllllllllllllIIIIlIIlIIlIIIl = lllllllllllllllllIIIIlIIlIIIllIl.add(lllllllllllllllllIIIIlIIlIIlIllI.theme.section(lllllllllllllllllIIIIlIIlIIIllII.name, lllllllllllllllllIIIIlIIlIIIllII.sectionExpanded)).expandX().widget();
        lllllllllllllllllIIIIlIIlIIlIIIl.action = () -> {
            lllllllllllllllllIIIIIlIIIlIlIlI.sectionExpanded = lllllllllllllllllIIIIlIIlIIlIIIl.isExpanded();
        };
        WTable lllllllllllllllllIIIIlIIlIIlIIII = lllllllllllllllllIIIIlIIlIIlIIIl.add(lllllllllllllllllIIIIlIIlIIlIllI.theme.table()).expandX().widget();
        RemoveInfo lllllllllllllllllIIIIlIIlIIIllll = null;
        for (Setting<?> lllllllllllllllllIIIIlIIlIIlIlll : lllllllllllllllllIIIIlIIlIIIllII) {
            boolean lllllllllllllllllIIIIlIIlIIllIIl;
            if (!StringUtils.containsIgnoreCase((CharSequence)lllllllllllllllllIIIIlIIlIIlIlll.title, (CharSequence)lllllllllllllllllIIIIlIIlIIIlIll)) continue;
            lllllllllllllllllIIIIlIIlIIlIlll.lastWasVisible = lllllllllllllllllIIIIlIIlIIllIIl = lllllllllllllllllIIIIlIIlIIlIlll.isVisible();
            if (!lllllllllllllllllIIIIlIIlIIllIIl) {
                if (lllllllllllllllllIIIIlIIlIIIllll == null) {
                    lllllllllllllllllIIIIlIIlIIIllll = new RemoveInfo(lllllllllllllllllIIIIlIIlIIlIIIl, lllllllllllllllllIIIIlIIlIIlIIII);
                }
                lllllllllllllllllIIIIlIIlIIIllll.markRowForRemoval();
            }
            lllllllllllllllllIIIIlIIlIIlIIII.add(lllllllllllllllllIIIIlIIlIIlIllI.theme.label((String)lllllllllllllllllIIIIlIIlIIlIlll.title)).widget().tooltip = lllllllllllllllllIIIIlIIlIIlIlll.description;
            Factory lllllllllllllllllIIIIlIIlIIllIII = lllllllllllllllllIIIIlIIlIIlIllI.factories.get(lllllllllllllllllIIIIlIIlIIlIlll.getClass());
            if (lllllllllllllllllIIIIlIIlIIllIII != null) {
                lllllllllllllllllIIIIlIIlIIllIII.create(lllllllllllllllllIIIIlIIlIIlIIII, lllllllllllllllllIIIIlIIlIIlIlll);
            }
            lllllllllllllllllIIIIlIIlIIlIIII.row();
        }
        if (lllllllllllllllllIIIIlIIlIIIllll != null) {
            lllllllllllllllllIIIIlIIlIIIlIlI.add(lllllllllllllllllIIIIlIIlIIIllll);
        }
    }

    private void intW(WTable lllllllllllllllllIIIIlIIIllIllIl, IntSetting lllllllllllllllllIIIIlIIIlllIIII) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIIlllIIlI;
        WIntEdit lllllllllllllllllIIIIlIIIllIllll = lllllllllllllllllIIIIlIIIllIllIl.add(lllllllllllllllllIIIIlIIIlllIIlI.theme.intEdit((Integer)lllllllllllllllllIIIIlIIIlllIIII.get(), lllllllllllllllllIIIIlIIIlllIIII.getSliderMin(), lllllllllllllllllIIIIlIIIlllIIII.getSliderMax())).expandX().widget();
        lllllllllllllllllIIIIlIIIllIllll.min = lllllllllllllllllIIIIlIIIlllIIII.min;
        lllllllllllllllllIIIIlIIIllIllll.max = lllllllllllllllllIIIIlIIIlllIIII.max;
        lllllllllllllllllIIIIlIIIllIllll.actionOnRelease = () -> {
            if (!lllllllllllllllllIIIIlIIIlllIIII.set(lllllllllllllllllIIIIlIIIllIllll.get())) {
                lllllllllllllllllIIIIlIIIllIllll.set((Integer)lllllllllllllllllIIIIlIIIlllIIII.get());
            }
        };
        lllllllllllllllllIIIIlIIIlllIIlI.reset(lllllllllllllllllIIIIlIIIllIllIl, lllllllllllllllllIIIIlIIIlllIIII, () -> lllllllllllllllllIIIIlIIIllIllll.set((Integer)lllllllllllllllllIIIIlIIIlllIIII.get()));
    }

    @Override
    public WWidget create(GuiTheme lllllllllllllllllIIIIlIIlIllIIlI, Settings lllllllllllllllllIIIIlIIlIlIlIll, String lllllllllllllllllIIIIlIIlIllIIII) {
        WVerticalList lllllllllllllllllIIIIlIIlIlIllll = lllllllllllllllllIIIIlIIlIllIIlI.verticalList();
        ArrayList<RemoveInfo> lllllllllllllllllIIIIlIIlIlIlllI = new ArrayList<RemoveInfo>();
        for (SettingGroup lllllllllllllllllIIIIlIIlIllIlIl : lllllllllllllllllIIIIlIIlIlIlIll.groups) {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIlIIlIllIIll;
            lllllllllllllllllIIIIlIIlIllIIll.group(lllllllllllllllllIIIIlIIlIlIllll, lllllllllllllllllIIIIlIIlIllIlIl, lllllllllllllllllIIIIlIIlIllIIII, lllllllllllllllllIIIIlIIlIlIlllI);
        }
        lllllllllllllllllIIIIlIIlIlIllll.calculateSize();
        lllllllllllllllllIIIIlIIlIlIllll.minWidth = lllllllllllllllllIIIIlIIlIlIllll.width;
        for (RemoveInfo lllllllllllllllllIIIIlIIlIllIlII : lllllllllllllllllIIIIlIIlIlIlllI) {
            lllllllllllllllllIIIIlIIlIllIlII.remove(lllllllllllllllllIIIIlIIlIlIllll);
        }
        return lllllllllllllllllIIIIlIIlIlIllll;
    }

    private void enchListW(WTable lllllllllllllllllIIIIIllllIIlIlI, EnchListSetting lllllllllllllllllIIIIIllllIIlIIl) {
        DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllllIIlIll;
        lllllllllllllllllIIIIIllllIIlIll.selectW(lllllllllllllllllIIIIIllllIIlIlI, lllllllllllllllllIIIIIllllIIlIIl, () -> {
            DefaultSettingsWidgetFactory lllllllllllllllllIIIIIllIIlIllIl;
            Utils.mc.method_1507((class_437)new EnchListSettingScreen(lllllllllllllllllIIIIIllIIlIllIl.theme, lllllllllllllllllIIIIIllllIIlIIl));
        });
    }

    private static class RemoveInfo {
        private final /* synthetic */ WTable table;
        private final /* synthetic */ IntList rowIds;
        private final /* synthetic */ WSection section;

        public void markRowForRemoval() {
            RemoveInfo lIIllIIlIllI;
            lIIllIIlIllI.rowIds.add(lIIllIIlIllI.table.rowI());
        }

        public void remove(WVerticalList lIIllIIIllIl) {
            RemoveInfo lIIllIIlIIII;
            for (int lIIllIIlIIIl = 0; lIIllIIlIIIl < lIIllIIlIIII.rowIds.size(); ++lIIllIIlIIIl) {
                lIIllIIlIIII.table.removeRow(lIIllIIlIIII.rowIds.getInt(lIIllIIlIIIl) - lIIllIIlIIIl);
            }
            if (lIIllIIlIIII.table.cells.isEmpty()) {
                lIIllIIIllIl.cells.removeIf(lIIllIIIlIII -> {
                    RemoveInfo lIIllIIIIlll;
                    return lIIllIIIlIII.widget() == lIIllIIIIlll.section;
                });
            }
        }

        public RemoveInfo(WSection lIIllIIlllII, WTable lIIllIIllIll) {
            RemoveInfo lIIllIIlllIl;
            lIIllIIlllIl.rowIds = new IntArrayList();
            lIIllIIlllIl.section = lIIllIIlllII;
            lIIllIIlllIl.table = lIIllIIllIll;
        }
    }

    protected static interface Factory {
        public void create(WTable var1, Setting<?> var2);
    }
}

