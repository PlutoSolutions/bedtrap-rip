/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;

public abstract class WHorizontalSeparator
extends WWidget {
    protected /* synthetic */ String text;
    protected /* synthetic */ double textWidth;

    @Override
    protected void onCalculateSize() {
        WHorizontalSeparator llIllllllIIlI;
        if (llIllllllIIlI.text != null) {
            llIllllllIIlI.textWidth = llIllllllIIlI.theme.textWidth(llIllllllIIlI.text);
        }
        llIllllllIIlI.width = 1.0;
        llIllllllIIlI.height = llIllllllIIlI.text != null ? llIllllllIIlI.theme.textHeight() : llIllllllIIlI.theme.scale(3.0);
    }

    public WHorizontalSeparator(String llIllllllIlIl) {
        WHorizontalSeparator llIllllllIllI;
        llIllllllIllI.text = llIllllllIlIl;
    }
}

