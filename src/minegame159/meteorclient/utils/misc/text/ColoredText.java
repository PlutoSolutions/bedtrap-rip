/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc.text;

import java.util.Objects;
import minegame159.meteorclient.utils.render.color.Color;

public class ColoredText {
    private final /* synthetic */ String text;
    private final /* synthetic */ Color color;

    public boolean equals(Object llllllllllllllllllIlIlIIllIIllll) {
        ColoredText llllllllllllllllllIlIlIIllIlIIll;
        if (llllllllllllllllllIlIlIIllIlIIll == llllllllllllllllllIlIlIIllIIllll) {
            return true;
        }
        if (llllllllllllllllllIlIlIIllIIllll == null || llllllllllllllllllIlIlIIllIlIIll.getClass() != llllllllllllllllllIlIlIIllIIllll.getClass()) {
            return false;
        }
        ColoredText llllllllllllllllllIlIlIIllIlIIIl = (ColoredText)llllllllllllllllllIlIlIIllIIllll;
        return llllllllllllllllllIlIlIIllIlIIll.text.equals(llllllllllllllllllIlIlIIllIlIIIl.text) && llllllllllllllllllIlIlIIllIlIIll.color.equals(llllllllllllllllllIlIlIIllIlIIIl.color);
    }

    public Color getColor() {
        ColoredText llllllllllllllllllIlIlIIllIlIlll;
        return llllllllllllllllllIlIlIIllIlIlll.color;
    }

    public String getText() {
        ColoredText llllllllllllllllllIlIlIIllIllIll;
        return llllllllllllllllllIlIlIIllIllIll.text;
    }

    public int hashCode() {
        ColoredText llllllllllllllllllIlIlIIllIIlIll;
        return Objects.hash(llllllllllllllllllIlIlIIllIIlIll.text, llllllllllllllllllIlIlIIllIIlIll.color);
    }

    public ColoredText(String llllllllllllllllllIlIlIIllIllllI, Color llllllllllllllllllIlIlIIllIlllIl) {
        ColoredText llllllllllllllllllIlIlIIlllIIIlI;
        llllllllllllllllllIlIlIIlllIIIlI.text = llllllllllllllllllIlIlIIllIllllI;
        llllllllllllllllllIlIlIIlllIIIlI.color = llllllllllllllllllIlIlIIllIlllIl;
    }
}

