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
    private /* synthetic */ String name;
    private /* synthetic */ WItem item;
    private /* synthetic */ WLabel label;
    private /* synthetic */ class_1799 itemStack;

    public WItemWithLabel(class_1799 lIllIIIlIlIllll, String lIllIIIlIllIIIl) {
        WItemWithLabel lIllIIIlIllIIll;
        lIllIIIlIllIIll.itemStack = lIllIIIlIlIllll;
        lIllIIIlIllIIll.name = lIllIIIlIllIIIl;
    }

    public String getLabelText() {
        WItemWithLabel lIllIIIlIIlIlll;
        return lIllIIIlIIlIlll.label == null ? lIllIIIlIIlIlll.name : lIllIIIlIIlIlll.label.get();
    }

    private String getStringToAppend() {
        List lIllIIIlIlIIlIl;
        WItemWithLabel lIllIIIlIlIIIlI;
        String lIllIIIlIlIIIll = "";
        if (lIllIIIlIlIIIlI.itemStack.method_7909() == class_1802.field_8574 && (lIllIIIlIlIIlIl = class_1844.method_8063((class_1799)lIllIIIlIlIIIlI.itemStack).method_8049()).size() > 0) {
            lIllIIIlIlIIIll = String.valueOf(new StringBuilder().append(lIllIIIlIlIIIll).append(" "));
            class_1293 lIllIIIlIlIIllI = (class_1293)lIllIIIlIlIIlIl.get(0);
            if (lIllIIIlIlIIllI.method_5578() > 0) {
                lIllIIIlIlIIIll = String.valueOf(new StringBuilder().append(lIllIIIlIlIIIll).append(lIllIIIlIlIIllI.method_5578() + 1).append(" "));
            }
            lIllIIIlIlIIIll = String.valueOf(new StringBuilder().append(lIllIIIlIlIIIll).append("(").append(class_1292.method_5577((class_1293)lIllIIIlIlIIllI, (float)1.0f)).append(")"));
        }
        return lIllIIIlIlIIIll;
    }

    @Override
    public void init() {
        WItemWithLabel lIllIIIlIlIllII;
        lIllIIIlIlIllII.item = lIllIIIlIlIllII.add(lIllIIIlIlIllII.theme.item(lIllIIIlIlIllII.itemStack)).widget();
        lIllIIIlIlIllII.label = lIllIIIlIlIllII.add(lIllIIIlIlIllII.theme.label(String.valueOf(new StringBuilder().append(lIllIIIlIlIllII.name).append(lIllIIIlIlIllII.getStringToAppend())))).widget();
    }

    public void set(class_1799 lIllIIIlIIllIll) {
        WItemWithLabel lIllIIIlIIllIlI;
        lIllIIIlIIllIlI.itemStack = lIllIIIlIIllIll;
        lIllIIIlIIllIlI.item.itemStack = lIllIIIlIIllIll;
        lIllIIIlIIllIlI.name = lIllIIIlIIllIll.method_7964().getString();
        lIllIIIlIIllIlI.label.set(String.valueOf(new StringBuilder().append(lIllIIIlIIllIlI.name).append(lIllIIIlIIllIlI.getStringToAppend())));
    }
}

