/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

public class ItemHighlight
extends Module {
    private final Setting<List<class_1792>> items;
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> color;

    public ItemHighlight() {
        super(Categories.Render, "item-highlight", "Highlights selected items when in guis");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.items = this.sgGeneral.add(new ItemListSetting.Builder().name("items").description("Items to highlight.").defaultValue(new ArrayList<class_1792>()).build());
        this.color = this.sgGeneral.add(new ColorSetting.Builder().name("color").description("The color to highlight the items with.").defaultValue(new SettingColor(225, 25, 255, 50)).build());
    }

    public int getColor(class_1799 class_17992) {
        if (this.items.get().contains((Object)class_17992.method_7909()) && this.isActive()) {
            return this.color.get().getPacked();
        }
        return -1;
    }
}

