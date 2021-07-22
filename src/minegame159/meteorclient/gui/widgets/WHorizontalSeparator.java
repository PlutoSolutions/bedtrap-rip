/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;

public abstract class WHorizontalSeparator
extends WWidget {
    protected String text;
    protected double textWidth;

    @Override
    protected void onCalculateSize() {
        if (this.text != null) {
            this.textWidth = this.theme.textWidth(this.text);
        }
        this.width = 1.0;
        this.height = this.text != null ? this.theme.textHeight() : this.theme.scale(3.0);
    }

    public WHorizontalSeparator(String string) {
        this.text = string;
    }
}

