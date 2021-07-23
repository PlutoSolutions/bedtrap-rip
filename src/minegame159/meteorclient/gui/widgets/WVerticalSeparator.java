/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;

public class WVerticalSeparator
extends WWidget {
    @Override
    protected void onCalculateSize() {
        this.width = this.theme.scale(3.0);
        this.height = 1.0;
    }
}

