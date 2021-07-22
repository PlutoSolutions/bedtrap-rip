/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.widgets.WLabel;

public abstract class WMultiLabel
extends WLabel {
    protected double maxWidth;
    protected List<String> lines = new ArrayList<String>(2);

    @Override
    public void set(String string) {
        if (!string.equals(this.text)) {
            this.invalidate();
        }
        this.text = string;
    }

    @Override
    protected void onCalculateSize() {
        this.lines.clear();
        String[] arrstring = this.text.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        double d = this.theme.textWidth(" ", 1, this.title);
        double d2 = this.theme.scale(this.maxWidth);
        double d3 = 0.0;
        double d4 = 0.0;
        int n = 0;
        for (int i = 0; i < arrstring.length; ++i) {
            double d5;
            double d6 = d5 = this.theme.textWidth(arrstring[i], arrstring[i].length(), this.title);
            if (n > 0) {
                d6 += d;
            }
            if (d3 + d6 > d2) {
                this.lines.add(String.valueOf(stringBuilder));
                stringBuilder.setLength(0);
                d3 = 0.0;
                n = 0;
                --i;
                continue;
            }
            if (n > 0) {
                stringBuilder.append(' ');
                d3 += d;
            }
            stringBuilder.append(arrstring[i]);
            d4 = Math.max(d4, d3 += d5);
            ++n;
            if (2 <= 3) continue;
            return;
        }
        if (stringBuilder.length() > 0) {
            this.lines.add(String.valueOf(stringBuilder));
        }
        this.width = d4;
        this.height = this.theme.textHeight(this.title) * (double)this.lines.size();
    }

    public WMultiLabel(String string, boolean bl, double d) {
        super(string, bl);
        this.maxWidth = d;
    }
}

