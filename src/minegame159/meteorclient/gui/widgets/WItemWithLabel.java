/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1292
 *  net.minecraft.class_1293
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 */
package minegame159.meteorclient.gui.widgets;

import java.util.List;
import minegame159.meteorclient.gui.widgets.WItem;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;

public class WItemWithLabel
extends WHorizontalList {
    private String name;
    private WItem item;
    private WLabel label;
    private class_1799 itemStack;

    public WItemWithLabel(class_1799 class_17992, String string) {
        this.itemStack = class_17992;
        this.name = string;
    }

    public String getLabelText() {
        return this.label == null ? this.name : this.label.get();
    }

    private String getStringToAppend() {
        List list;
        String string = "";
        if (this.itemStack.method_7909() == class_1802.field_8574 && (list = class_1844.method_8063((class_1799)this.itemStack).method_8049()).size() > 0) {
            string = String.valueOf(new StringBuilder().append(string).append(" "));
            class_1293 class_12932 = (class_1293)list.get(0);
            if (class_12932.method_5578() > 0) {
                string = String.valueOf(new StringBuilder().append(string).append(class_12932.method_5578() + 1).append(" "));
            }
            string = String.valueOf(new StringBuilder().append(string).append("(").append(class_1292.method_5577((class_1293)class_12932, (float)1.0f)).append(")"));
        }
        return string;
    }

    @Override
    public void init() {
        this.item = this.add(this.theme.item(this.itemStack)).widget();
        this.label = this.add(this.theme.label(String.valueOf(new StringBuilder().append(this.name).append(this.getStringToAppend())))).widget();
    }

    public void set(class_1799 class_17992) {
        this.itemStack = class_17992;
        this.item.itemStack = class_17992;
        this.name = class_17992.method_7964().getString();
        this.label.set(String.valueOf(new StringBuilder().append(this.name).append(this.getStringToAppend())));
    }
}

