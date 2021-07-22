/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.widgets.WLabel;

public abstract class WMultiLabel
extends WLabel {
    protected /* synthetic */ double maxWidth;
    protected /* synthetic */ List<String> lines;

    @Override
    public void set(String lllIIlllIIlll) {
        WMultiLabel lllIIlllIlIII;
        if (!lllIIlllIIlll.equals(lllIIlllIlIII.text)) {
            lllIIlllIlIII.invalidate();
        }
        lllIIlllIlIII.text = lllIIlllIIlll;
    }

    @Override
    protected void onCalculateSize() {
        WMultiLabel lllIIllllIlll;
        lllIIllllIlll.lines.clear();
        String[] lllIIlllllllI = lllIIllllIlll.text.split(" ");
        StringBuilder lllIIllllllIl = new StringBuilder();
        double lllIIllllllII = lllIIllllIlll.theme.textWidth(" ", 1, lllIIllllIlll.title);
        double lllIIlllllIll = lllIIllllIlll.theme.scale(lllIIllllIlll.maxWidth);
        double lllIIlllllIlI = 0.0;
        double lllIIlllllIIl = 0.0;
        int lllIIlllllIII = 0;
        for (int lllIlIIIIIIII = 0; lllIlIIIIIIII < lllIIlllllllI.length; ++lllIlIIIIIIII) {
            double lllIlIIIIIIlI;
            double lllIlIIIIIIIl = lllIlIIIIIIlI = lllIIllllIlll.theme.textWidth(lllIIlllllllI[lllIlIIIIIIII], lllIIlllllllI[lllIlIIIIIIII].length(), lllIIllllIlll.title);
            if (lllIIlllllIII > 0) {
                lllIlIIIIIIIl += lllIIllllllII;
            }
            if (lllIIlllllIlI + lllIlIIIIIIIl > lllIIlllllIll) {
                lllIIllllIlll.lines.add(String.valueOf(lllIIllllllIl));
                lllIIllllllIl.setLength(0);
                lllIIlllllIlI = 0.0;
                lllIIlllllIII = 0;
                --lllIlIIIIIIII;
                continue;
            }
            if (lllIIlllllIII > 0) {
                lllIIllllllIl.append(' ');
                lllIIlllllIlI += lllIIllllllII;
            }
            lllIIllllllIl.append(lllIIlllllllI[lllIlIIIIIIII]);
            lllIIlllllIIl = Math.max(lllIIlllllIIl, lllIIlllllIlI += lllIlIIIIIIlI);
            ++lllIIlllllIII;
        }
        if (lllIIllllllIl.length() > 0) {
            lllIIllllIlll.lines.add(String.valueOf(lllIIllllllIl));
        }
        lllIIllllIlll.width = lllIIlllllIIl;
        lllIIllllIlll.height = lllIIllllIlll.theme.textHeight(lllIIllllIlll.title) * (double)lllIIllllIlll.lines.size();
    }

    public WMultiLabel(String lllIlIIIlIIII, boolean lllIlIIIIllll, double lllIlIIIlIIlI) {
        super(lllIlIIIlIIII, lllIlIIIIllll);
        WMultiLabel lllIlIIIlIIIl;
        lllIlIIIlIIIl.lines = new ArrayList<String>(2);
        lllIlIIIlIIIl.maxWidth = lllIlIIIlIIlI;
    }
}

