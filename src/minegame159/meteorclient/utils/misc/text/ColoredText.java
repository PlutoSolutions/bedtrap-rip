/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc.text;

import java.util.Objects;
import minegame159.meteorclient.utils.render.color.Color;

public class ColoredText {
    private final String text;
    private final Color color;

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ColoredText coloredText = (ColoredText)object;
        return this.text.equals(coloredText.text) && this.color.equals(coloredText.color);
    }

    public Color getColor() {
        return this.color;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        return Objects.hash(this.text, this.color);
    }

    public ColoredText(String string, Color color) {
        this.text = string;
        this.color = color;
    }
}

