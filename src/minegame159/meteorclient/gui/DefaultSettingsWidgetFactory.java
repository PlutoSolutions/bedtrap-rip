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
import java.util.Objects;
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
import minegame159.meteorclient.gui.utils.Cell;
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
    private final GuiTheme theme;
    private final Map<Class<?>, Factory> factories = new HashMap();

    private void lambda$soundEventListW$51(SoundEventListSetting soundEventListSetting) {
        Utils.mc.method_1507((class_437)new SoundEventListSettingScreen(this.theme, soundEventListSetting));
    }

    private void keybindW(WTable wTable, KeybindSetting keybindSetting) {
        WKeybind wKeybind = wTable.add(this.theme.keybind((Keybind)keybindSetting.get(), ((Keybind)keybindSetting.getDefaultValue()).getValue())).expandX().widget();
        Objects.requireNonNull(keybindSetting);
        wKeybind.action = keybindSetting::changed;
        keybindSetting.widget = wKeybind;
    }

    private void lambda$enchListW$47(EnchListSetting enchListSetting) {
        Utils.mc.method_1507((class_437)new EnchListSettingScreen(this.theme, enchListSetting));
    }

    private void stringW(WTable wTable, StringSetting stringSetting) {
        WTextBox wTextBox = wTable.add(this.theme.textBox((String)stringSetting.get())).expandX().widget();
        wTextBox.action = () -> DefaultSettingsWidgetFactory.lambda$stringW$36(stringSetting, wTextBox);
        this.reset(wTable, stringSetting, () -> DefaultSettingsWidgetFactory.lambda$stringW$37(wTextBox, stringSetting));
    }

    private void lambda$new$14(WTable wTable, Setting setting) {
        this.enchListW(wTable, (EnchListSetting)setting);
    }

    private void colorW(WTable wTable, ColorSetting colorSetting) {
        WHorizontalList wHorizontalList = wTable.add(this.theme.horizontalList()).expandX().widget();
        WQuad wQuad = wHorizontalList.add(this.theme.quad((Color)colorSetting.get())).widget();
        WButton wButton = wHorizontalList.add(this.theme.button(GuiRenderer.EDIT)).widget();
        wButton.action = () -> this.lambda$colorW$34(colorSetting);
        this.reset(wTable, colorSetting, () -> DefaultSettingsWidgetFactory.lambda$colorW$35(wQuad, colorSetting));
    }

    private void soundEventListW(WTable wTable, SoundEventListSetting soundEventListSetting) {
        this.selectW(wTable, soundEventListSetting, () -> this.lambda$soundEventListW$51(soundEventListSetting));
    }

    private void blockDataSettingW(WTable wTable, BlockDataSetting<?> blockDataSetting) {
        WButton wButton = wTable.add(this.theme.button(GuiRenderer.EDIT)).expandCellX().widget();
        wButton.action = () -> this.lambda$blockDataSettingW$54(blockDataSetting);
        this.reset(wTable, blockDataSetting, null);
    }

    private void doubleW(WTable wTable, DoubleSetting doubleSetting) {
        WDoubleEdit wDoubleEdit = this.theme.doubleEdit((Double)doubleSetting.get(), doubleSetting.getSliderMin(), doubleSetting.getSliderMax());
        wDoubleEdit.min = doubleSetting.min;
        wDoubleEdit.max = doubleSetting.max;
        wDoubleEdit.decimalPlaces = doubleSetting.decimalPlaces;
        wTable.add(wDoubleEdit).expandX();
        Runnable runnable = () -> DefaultSettingsWidgetFactory.lambda$doubleW$27(doubleSetting, wDoubleEdit);
        if (doubleSetting.onSliderRelease) {
            wDoubleEdit.actionOnRelease = runnable;
        } else {
            wDoubleEdit.action = runnable;
        }
        this.reset(wTable, doubleSetting, () -> DefaultSettingsWidgetFactory.lambda$doubleW$28(wDoubleEdit, doubleSetting));
    }

    private void lambda$new$6(WTable wTable, Setting setting) {
        this.stringW(wTable, (StringSetting)setting);
    }

    private static void lambda$potionW$33(WItemWithLabel wItemWithLabel, PotionSetting potionSetting) {
        wItemWithLabel.set(((MyPotion)potionSetting.get()).potion);
    }

    private static void lambda$group$22(SettingGroup settingGroup, WSection wSection) {
        settingGroup.sectionExpanded = wSection.isExpanded();
    }

    private static void lambda$boolW$24(WCheckbox wCheckbox, BoolSetting boolSetting) {
        wCheckbox.checked = (Boolean)boolSetting.get();
    }

    private void lambda$packetBoolW$49(PacketBoolSetting packetBoolSetting) {
        Utils.mc.method_1507((class_437)new PacketBoolSettingScreen(this.theme, packetBoolSetting));
    }

    private static void lambda$intW$26(WIntEdit wIntEdit, IntSetting intSetting) {
        wIntEdit.set((Integer)intSetting.get());
    }

    private static void lambda$blockW$40(WItem wItem, BlockSetting blockSetting) {
        wItem.set(((class_2248)blockSetting.get()).method_8389().method_7854());
    }

    private void selectW(WContainer wContainer, Setting<?> setting, Runnable runnable) {
        WButton wButton = wContainer.add(this.theme.button("Select")).expandCellX().widget();
        wButton.action = runnable;
        this.reset(wContainer, setting, null);
    }

    private void boolW(WTable wTable, BoolSetting boolSetting) {
        WCheckbox wCheckbox = wTable.add(this.theme.checkbox((Boolean)boolSetting.get())).expandCellX().widget();
        wCheckbox.action = () -> DefaultSettingsWidgetFactory.lambda$boolW$23(boolSetting, wCheckbox);
        this.reset(wTable, boolSetting, () -> DefaultSettingsWidgetFactory.lambda$boolW$24(wCheckbox, boolSetting));
    }

    private static void lambda$doubleW$28(WDoubleEdit wDoubleEdit, DoubleSetting doubleSetting) {
        wDoubleEdit.set((Double)doubleSetting.get());
    }

    private void lambda$new$16(WTable wTable, Setting setting) {
        this.packetBoolW(wTable, (PacketBoolSetting)setting);
    }

    private static void lambda$providedStringW$39(WDropdown wDropdown, ProvidedStringSetting providedStringSetting) {
        wDropdown.set((String)providedStringSetting.get());
    }

    private void lambda$new$8(WTable wTable, Setting setting) {
        this.blockW(wTable, (BlockSetting)setting);
    }

    private void lambda$new$18(WTable wTable, Setting setting) {
        this.soundEventListW(wTable, (SoundEventListSetting)setting);
    }

    private static void lambda$enumW$29(EnumSetting enumSetting, WDropdown wDropdown) {
        enumSetting.set((Enum)wDropdown.get());
    }

    private void lambda$itemListW$45(ItemListSetting itemListSetting) {
        Utils.mc.method_1507((class_437)new ItemListSettingScreen(this.theme, itemListSetting));
    }

    private void lambda$moduleListW$48(ModuleListSetting moduleListSetting) {
        Utils.mc.method_1507((class_437)new ModuleListSettingScreen(this.theme, moduleListSetting));
    }

    private static void lambda$boolW$23(BoolSetting boolSetting, WCheckbox wCheckbox) {
        boolSetting.set(wCheckbox.checked);
    }

    private static void lambda$enumW$30(WDropdown wDropdown, EnumSetting enumSetting) {
        wDropdown.set((Enum)enumSetting.get());
    }

    private static void lambda$blockW$42(WItem wItem, BlockSetting blockSetting) {
        wItem.set(((class_2248)blockSetting.get()).method_8389().method_7854());
    }

    private void entityTypeListW(WTable wTable, EntityTypeListSetting entityTypeListSetting) {
        this.selectW(wTable, entityTypeListSetting, () -> this.lambda$entityTypeListW$46(entityTypeListSetting));
    }

    private void reset(WContainer wContainer, Setting<?> setting, Runnable runnable) {
        WButton wButton = wContainer.add(this.theme.button(GuiRenderer.RESET)).widget();
        wButton.action = () -> DefaultSettingsWidgetFactory.lambda$reset$55(setting, runnable);
    }

    private void potionW(WTable wTable, PotionSetting potionSetting) {
        WHorizontalList wHorizontalList = wTable.add(this.theme.horizontalList()).expandX().widget();
        WItemWithLabel wItemWithLabel = wHorizontalList.add(this.theme.itemWithLabel(((MyPotion)potionSetting.get()).potion, ((MyPotion)potionSetting.get()).potion.method_7964().getString())).widget();
        WButton wButton = wHorizontalList.add(this.theme.button("Select")).expandCellX().widget();
        wButton.action = () -> this.lambda$potionW$32(potionSetting, wItemWithLabel);
        this.reset(wHorizontalList, potionSetting, () -> DefaultSettingsWidgetFactory.lambda$potionW$33(wItemWithLabel, potionSetting));
    }

    private void lambda$blockDataSettingW$54(BlockDataSetting blockDataSetting) {
        Utils.mc.method_1507((class_437)new BlockDataSettingScreen(this.theme, blockDataSetting));
    }

    private void blockW(WTable wTable, BlockSetting blockSetting) {
        WHorizontalList wHorizontalList = wTable.add(this.theme.horizontalList()).expandX().widget();
        WItem wItem = wHorizontalList.add(this.theme.item(((class_2248)blockSetting.get()).method_8389().method_7854())).widget();
        WButton wButton = wHorizontalList.add(this.theme.button("Select")).widget();
        wButton.action = () -> this.lambda$blockW$41(blockSetting, wItem);
        this.reset(wTable, blockSetting, () -> DefaultSettingsWidgetFactory.lambda$blockW$42(wItem, blockSetting));
    }

    private void lambda$new$2(WTable wTable, Setting setting) {
        this.doubleW(wTable, (DoubleSetting)setting);
    }

    private void providedStringW(WTable wTable, ProvidedStringSetting providedStringSetting) {
        WDropdown<String> wDropdown = wTable.add(this.theme.dropdown(providedStringSetting.supplier.get(), (String)providedStringSetting.get())).expandCellX().widget();
        wDropdown.action = () -> DefaultSettingsWidgetFactory.lambda$providedStringW$38(providedStringSetting, wDropdown);
        this.reset(wTable, providedStringSetting, () -> DefaultSettingsWidgetFactory.lambda$providedStringW$39(wDropdown, providedStringSetting));
    }

    private void lambda$new$10(WTable wTable, Setting setting) {
        this.genericW(wTable, (GenericSetting)setting);
    }

    private void lambda$new$5(WTable wTable, Setting setting) {
        this.colorW(wTable, (ColorSetting)setting);
    }

    private void lambda$new$13(WTable wTable, Setting setting) {
        this.entityTypeListW(wTable, (EntityTypeListSetting)setting);
    }

    private void lambda$entityTypeListW$46(EntityTypeListSetting entityTypeListSetting) {
        Utils.mc.method_1507((class_437)new EntityTypeListSettingScreen(this.theme, entityTypeListSetting));
    }

    private void lambda$new$19(WTable wTable, Setting setting) {
        this.statusEffectW(wTable, (StatusEffectSetting)setting);
    }

    private void lambda$genericW$43(GenericSetting genericSetting) {
        Utils.mc.method_1507((class_437)((IScreenFactory)genericSetting.get()).createScreen(this.theme));
    }

    private void lambda$statusEffectW$52(StatusEffectSetting statusEffectSetting) {
        Utils.mc.method_1507((class_437)new StatusEffectSettingScreen(this.theme, statusEffectSetting));
    }

    public DefaultSettingsWidgetFactory(GuiTheme guiTheme) {
        this.theme = guiTheme;
        this.factories.put(BoolSetting.class, (arg_0, arg_1) -> this.lambda$new$0(arg_0, arg_1));
        this.factories.put(IntSetting.class, (arg_0, arg_1) -> this.lambda$new$1(arg_0, arg_1));
        this.factories.put(DoubleSetting.class, (arg_0, arg_1) -> this.lambda$new$2(arg_0, arg_1));
        this.factories.put(EnumSetting.class, (arg_0, arg_1) -> this.lambda$new$3(arg_0, arg_1));
        this.factories.put(PotionSetting.class, (arg_0, arg_1) -> this.lambda$new$4(arg_0, arg_1));
        this.factories.put(ColorSetting.class, (arg_0, arg_1) -> this.lambda$new$5(arg_0, arg_1));
        this.factories.put(StringSetting.class, (arg_0, arg_1) -> this.lambda$new$6(arg_0, arg_1));
        this.factories.put(ProvidedStringSetting.class, (arg_0, arg_1) -> this.lambda$new$7(arg_0, arg_1));
        this.factories.put(BlockSetting.class, (arg_0, arg_1) -> this.lambda$new$8(arg_0, arg_1));
        this.factories.put(KeybindSetting.class, (arg_0, arg_1) -> this.lambda$new$9(arg_0, arg_1));
        this.factories.put(GenericSetting.class, (arg_0, arg_1) -> this.lambda$new$10(arg_0, arg_1));
        this.factories.put(BlockListSetting.class, (arg_0, arg_1) -> this.lambda$new$11(arg_0, arg_1));
        this.factories.put(ItemListSetting.class, (arg_0, arg_1) -> this.lambda$new$12(arg_0, arg_1));
        this.factories.put(EntityTypeListSetting.class, (arg_0, arg_1) -> this.lambda$new$13(arg_0, arg_1));
        this.factories.put(EnchListSetting.class, (arg_0, arg_1) -> this.lambda$new$14(arg_0, arg_1));
        this.factories.put(ModuleListSetting.class, (arg_0, arg_1) -> this.lambda$new$15(arg_0, arg_1));
        this.factories.put(PacketBoolSetting.class, (arg_0, arg_1) -> this.lambda$new$16(arg_0, arg_1));
        this.factories.put(ParticleTypeListSetting.class, (arg_0, arg_1) -> this.lambda$new$17(arg_0, arg_1));
        this.factories.put(SoundEventListSetting.class, (arg_0, arg_1) -> this.lambda$new$18(arg_0, arg_1));
        this.factories.put(StatusEffectSetting.class, (arg_0, arg_1) -> this.lambda$new$19(arg_0, arg_1));
        this.factories.put(StorageBlockListSetting.class, (arg_0, arg_1) -> this.lambda$new$20(arg_0, arg_1));
        this.factories.put(BlockDataSetting.class, (arg_0, arg_1) -> this.lambda$new$21(arg_0, arg_1));
    }

    private void lambda$new$15(WTable wTable, Setting setting) {
        this.moduleListW(wTable, (ModuleListSetting)setting);
    }

    private void lambda$blockListW$44(BlockListSetting blockListSetting) {
        Utils.mc.method_1507((class_437)new BlockListSettingScreen(this.theme, blockListSetting));
    }

    private void blockListW(WTable wTable, BlockListSetting blockListSetting) {
        this.selectW(wTable, blockListSetting, () -> this.lambda$blockListW$44(blockListSetting));
    }

    private void lambda$new$9(WTable wTable, Setting setting) {
        this.keybindW(wTable, (KeybindSetting)setting);
    }

    private void lambda$particleEffectListW$50(ParticleTypeListSetting particleTypeListSetting) {
        Utils.mc.method_1507((class_437)new ParticleTypeListSettingScreen(this.theme, particleTypeListSetting));
    }

    private void lambda$storageBlockListW$53(StorageBlockListSetting storageBlockListSetting) {
        Utils.mc.method_1507((class_437)new StorageBlockListSettingScreen(this.theme, storageBlockListSetting));
    }

    private void lambda$new$3(WTable wTable, Setting setting) {
        this.enumW(wTable, (EnumSetting)setting);
    }

    private <T extends Enum<?>> void enumW(WTable wTable, EnumSetting<T> enumSetting) {
        WDropdown<Enum> wDropdown = wTable.add(this.theme.dropdown((Enum)enumSetting.get())).expandCellX().widget();
        wDropdown.action = () -> DefaultSettingsWidgetFactory.lambda$enumW$29(enumSetting, wDropdown);
        this.reset(wTable, enumSetting, () -> DefaultSettingsWidgetFactory.lambda$enumW$30(wDropdown, enumSetting));
    }

    private static void lambda$stringW$37(WTextBox wTextBox, StringSetting stringSetting) {
        wTextBox.set((String)stringSetting.get());
    }

    private static void lambda$reset$55(Setting setting, Runnable runnable) {
        setting.reset();
        if (runnable != null) {
            runnable.run();
        }
    }

    private void lambda$new$0(WTable wTable, Setting setting) {
        this.boolW(wTable, (BoolSetting)setting);
    }

    private void lambda$new$17(WTable wTable, Setting setting) {
        this.particleEffectListW(wTable, (ParticleTypeListSetting)setting);
    }

    private void packetBoolW(WTable wTable, PacketBoolSetting packetBoolSetting) {
        this.selectW(wTable, packetBoolSetting, () -> this.lambda$packetBoolW$49(packetBoolSetting));
    }

    private void itemListW(WTable wTable, ItemListSetting itemListSetting) {
        this.selectW(wTable, itemListSetting, () -> this.lambda$itemListW$45(itemListSetting));
    }

    private void lambda$new$21(WTable wTable, Setting setting) {
        this.blockDataSettingW(wTable, (BlockDataSetting)setting);
    }

    private static void lambda$doubleW$27(DoubleSetting doubleSetting, WDoubleEdit wDoubleEdit) {
        if (!doubleSetting.set(wDoubleEdit.get())) {
            wDoubleEdit.set((Double)doubleSetting.get());
        }
    }

    private void statusEffectW(WTable wTable, StatusEffectSetting statusEffectSetting) {
        this.selectW(wTable, statusEffectSetting, () -> this.lambda$statusEffectW$52(statusEffectSetting));
    }

    private void storageBlockListW(WTable wTable, StorageBlockListSetting storageBlockListSetting) {
        this.selectW(wTable, storageBlockListSetting, () -> this.lambda$storageBlockListW$53(storageBlockListSetting));
    }

    private void lambda$new$1(WTable wTable, Setting setting) {
        this.intW(wTable, (IntSetting)setting);
    }

    private void lambda$blockW$41(BlockSetting blockSetting, WItem wItem) {
        BlockSettingScreen blockSettingScreen = new BlockSettingScreen(this.theme, blockSetting);
        blockSettingScreen.onClosed(() -> DefaultSettingsWidgetFactory.lambda$blockW$40(wItem, blockSetting));
        Utils.mc.method_1507((class_437)blockSettingScreen);
    }

    private static void lambda$providedStringW$38(ProvidedStringSetting providedStringSetting, WDropdown wDropdown) {
        providedStringSetting.set((String)wDropdown.get());
    }

    private void lambda$new$12(WTable wTable, Setting setting) {
        this.itemListW(wTable, (ItemListSetting)setting);
    }

    private static void lambda$potionW$31(WItemWithLabel wItemWithLabel, PotionSetting potionSetting) {
        wItemWithLabel.set(((MyPotion)potionSetting.get()).potion);
    }

    private void genericW(WTable wTable, GenericSetting<?> genericSetting) {
        WButton wButton = wTable.add(this.theme.button(GuiRenderer.EDIT)).widget();
        wButton.action = () -> this.lambda$genericW$43(genericSetting);
        this.reset(wTable, genericSetting, null);
    }

    private void particleEffectListW(WTable wTable, ParticleTypeListSetting particleTypeListSetting) {
        this.selectW(wTable, particleTypeListSetting, () -> this.lambda$particleEffectListW$50(particleTypeListSetting));
    }

    private void lambda$new$20(WTable wTable, Setting setting) {
        this.storageBlockListW(wTable, (StorageBlockListSetting)setting);
    }

    private static void lambda$stringW$36(StringSetting stringSetting, WTextBox wTextBox) {
        stringSetting.set(wTextBox.get());
    }

    private void moduleListW(WTable wTable, ModuleListSetting moduleListSetting) {
        this.selectW(wTable, moduleListSetting, () -> this.lambda$moduleListW$48(moduleListSetting));
    }

    private void lambda$colorW$34(ColorSetting colorSetting) {
        Utils.mc.method_1507((class_437)new ColorSettingScreen(this.theme, colorSetting));
    }

    private void lambda$potionW$32(PotionSetting potionSetting, WItemWithLabel wItemWithLabel) {
        PotionSettingScreen potionSettingScreen = new PotionSettingScreen(this.theme, potionSetting);
        potionSettingScreen.onClosed(() -> DefaultSettingsWidgetFactory.lambda$potionW$31(wItemWithLabel, potionSetting));
        Utils.mc.method_1507((class_437)potionSettingScreen);
    }

    private void group(WVerticalList wVerticalList, SettingGroup settingGroup, String string, List<RemoveInfo> list) {
        WSection wSection = wVerticalList.add(this.theme.section(settingGroup.name, settingGroup.sectionExpanded)).expandX().widget();
        wSection.action = () -> DefaultSettingsWidgetFactory.lambda$group$22(settingGroup, wSection);
        WTable wTable = wSection.add(this.theme.table()).expandX().widget();
        RemoveInfo removeInfo = null;
        for (Setting<?> setting : settingGroup) {
            boolean bl;
            if (!StringUtils.containsIgnoreCase((CharSequence)setting.title, (CharSequence)string)) continue;
            setting.lastWasVisible = bl = setting.isVisible();
            if (!bl) {
                if (removeInfo == null) {
                    removeInfo = new RemoveInfo(wSection, wTable);
                }
                removeInfo.markRowForRemoval();
            }
            wTable.add(this.theme.label((String)setting.title)).widget().tooltip = setting.description;
            Factory factory = this.factories.get(setting.getClass());
            if (factory != null) {
                factory.create(wTable, setting);
            }
            wTable.row();
        }
        if (removeInfo != null) {
            list.add(removeInfo);
        }
    }

    private static void lambda$intW$25(IntSetting intSetting, WIntEdit wIntEdit) {
        if (!intSetting.set(wIntEdit.get())) {
            wIntEdit.set((Integer)intSetting.get());
        }
    }

    private void intW(WTable wTable, IntSetting intSetting) {
        WIntEdit wIntEdit = wTable.add(this.theme.intEdit((Integer)intSetting.get(), intSetting.getSliderMin(), intSetting.getSliderMax())).expandX().widget();
        wIntEdit.min = intSetting.min;
        wIntEdit.max = intSetting.max;
        wIntEdit.actionOnRelease = () -> DefaultSettingsWidgetFactory.lambda$intW$25(intSetting, wIntEdit);
        this.reset(wTable, intSetting, () -> DefaultSettingsWidgetFactory.lambda$intW$26(wIntEdit, intSetting));
    }

    private void lambda$new$11(WTable wTable, Setting setting) {
        this.blockListW(wTable, (BlockListSetting)setting);
    }

    private void lambda$new$7(WTable wTable, Setting setting) {
        this.providedStringW(wTable, (ProvidedStringSetting)setting);
    }

    private static void lambda$colorW$35(WQuad wQuad, ColorSetting colorSetting) {
        wQuad.color = (Color)colorSetting.get();
    }

    @Override
    public WWidget create(GuiTheme guiTheme, Settings settings, String string) {
        WVerticalList wVerticalList = guiTheme.verticalList();
        ArrayList<RemoveInfo> arrayList = new ArrayList<RemoveInfo>();
        for (SettingGroup object : settings.groups) {
            this.group(wVerticalList, object, string, arrayList);
        }
        wVerticalList.calculateSize();
        wVerticalList.minWidth = wVerticalList.width;
        for (RemoveInfo removeInfo : arrayList) {
            removeInfo.remove(wVerticalList);
        }
        return wVerticalList;
    }

    private void lambda$new$4(WTable wTable, Setting setting) {
        this.potionW(wTable, (PotionSetting)setting);
    }

    private void enchListW(WTable wTable, EnchListSetting enchListSetting) {
        this.selectW(wTable, enchListSetting, () -> this.lambda$enchListW$47(enchListSetting));
    }

    private static class RemoveInfo {
        private final WTable table;
        private final IntList rowIds = new IntArrayList();
        private final WSection section;

        public void markRowForRemoval() {
            this.rowIds.add(this.table.rowI());
        }

        private boolean lambda$remove$0(Cell cell) {
            return cell.widget() == this.section;
        }

        public void remove(WVerticalList wVerticalList) {
            for (int i = 0; i < this.rowIds.size(); ++i) {
                this.table.removeRow(this.rowIds.getInt(i) - i);
                if (3 > 0) continue;
                return;
            }
            if (this.table.cells.isEmpty()) {
                wVerticalList.cells.removeIf(this::lambda$remove$0);
            }
        }

        public RemoveInfo(WSection wSection, WTable wTable) {
            this.section = wSection;
            this.table = wTable;
        }
    }

    protected static interface Factory {
        public void create(WTable var1, Setting<?> var2);
    }
}

