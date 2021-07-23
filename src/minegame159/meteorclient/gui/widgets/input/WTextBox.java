/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets.input;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import minegame159.meteorclient.gui.GuiKeyEvents;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.utils.CharFilter;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.Utils;

public abstract class WTextBox
extends WWidget {
    public Runnable action;
    public Runnable actionOnUnfocused;
    protected DoubleList textWidths = new DoubleArrayList();
    protected CharFilter filter;
    protected double textStart;
    protected String text;
    protected boolean focused;
    protected int cursor;

    @Override
    public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
        if (this.mouseOver && !bl) {
            if (n == 1) {
                if (!this.text.isEmpty()) {
                    this.text = "";
                    this.cursor = 0;
                    this.runAction();
                }
            } else {
                double d3 = this.getOverflowWidthForRender();
                double d4 = d - this.x + d3;
                double d5 = this.pad();
                double d6 = Double.MAX_VALUE;
                this.cursor = 0;
                for (int i = 0; i < this.textWidths.size(); ++i) {
                    double d7 = Math.abs(this.textWidths.getDouble(i) + d5 - d4);
                    if (!(d7 < d6)) continue;
                    d6 = d7;
                    this.cursor = i;
                    if (3 > 0) continue;
                    return false;
                }
                this.cursorChanged();
            }
            this.setFocused(true);
            return true;
        }
        if (this.focused) {
            this.setFocused(false);
        }
        return false;
    }

    protected double getCursorTextWidth(int n) {
        if (this.textWidths.isEmpty()) {
            return 0.0;
        }
        int n2 = this.cursor + n;
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 >= this.textWidths.size()) {
            n2 = this.textWidths.size() - 1;
        }
        return this.textWidths.getDouble(n2);
    }

    public String get() {
        return this.text;
    }

    @Override
    public boolean onCharTyped(char c) {
        if (!this.focused) {
            return false;
        }
        if (this.filter.filter(this.text, c)) {
            this.text = String.valueOf(new StringBuilder().append(this.text.substring(0, this.cursor)).append(c).append(this.text.substring(this.cursor)));
            ++this.cursor;
            this.runAction();
            return true;
        }
        return false;
    }

    protected void onCursorChanged() {
    }

    protected double getOverflowWidthForRender() {
        return this.textStart;
    }

    private void calculateTextWidths() {
        this.textWidths.clear();
        for (int i = 0; i <= this.text.length(); ++i) {
            this.textWidths.add(this.theme.textWidth(this.text, i, false));
            if (-5 < 0) continue;
            return;
        }
    }

    public void setCursorMax() {
        this.cursor = this.text.length();
    }

    private void cursorChanged() {
        double d = this.getCursorTextWidth(-2);
        if (d < this.textStart) {
            this.textStart -= this.textStart - d;
        }
        if ((d = this.getCursorTextWidth(2)) > this.textStart + this.maxTextWidth()) {
            this.textStart += d - (this.textStart + this.maxTextWidth());
        }
        this.textStart = Utils.clamp(this.textStart, 0.0, Math.max(this.textWidth() - this.maxTextWidth(), 0.0));
        this.onCursorChanged();
    }

    private double textWidth() {
        return this.textWidths.isEmpty() ? 0.0 : this.textWidths.getDouble(this.textWidths.size() - 1);
    }

    @Override
    public boolean onKeyRepeated(int n, int n2) {
        boolean bl;
        if (!this.focused) {
            return false;
        }
        boolean bl2 = n2 == 2 || n2 == 8;
        boolean bl3 = bl = n2 == 1;
        if (n == 259) {
            if (this.cursor > 0) {
                String string = this.text;
                int n3 = bl2 ? this.countToNextSpace(true) : 1;
                this.text = String.valueOf(new StringBuilder().append(this.text.substring(0, this.cursor - n3)).append(this.text.substring(this.cursor)));
                this.cursor -= n3;
                if (!this.text.equals(string)) {
                    this.runAction();
                }
            }
            return true;
        }
        if (n == 261) {
            if (this.cursor < this.text.length()) {
                String string = this.text;
                int n4 = bl2 ? this.countToNextSpace(false) : 1;
                this.text = String.valueOf(new StringBuilder().append(this.text.substring(0, this.cursor)).append(this.text.substring(this.cursor + n4)));
                if (!this.text.equals(string)) {
                    this.runAction();
                }
            }
            return true;
        }
        if (n == 263 || bl && n == 324) {
            if (this.cursor > 0) {
                this.cursor -= bl2 ? this.countToNextSpace(true) : 1;
                this.cursorChanged();
            }
            return true;
        }
        if (n == 262 || bl && n == 326) {
            if (this.cursor < this.text.length()) {
                this.cursor += bl2 ? this.countToNextSpace(false) : 1;
                this.cursorChanged();
            }
            return true;
        }
        return false;
    }

    private void runAction() {
        this.calculateTextWidths();
        this.cursorChanged();
        if (this.action != null) {
            this.action.run();
        }
    }

    public boolean isFocused() {
        return this.focused;
    }

    protected double getCursorTextWidth() {
        return this.getCursorTextWidth(0);
    }

    @Override
    protected void onCalculateSize() {
        double d = this.pad();
        double d2 = this.theme.textHeight();
        this.width = d + d2 + d;
        this.height = d + d2 + d;
        this.calculateTextWidths();
    }

    public WTextBox(String string, CharFilter charFilter) {
        this.text = string;
        this.filter = charFilter;
    }

    private int countToNextSpace(boolean bl) {
        int n = 0;
        boolean bl2 = false;
        int n2 = this.cursor;
        while (bl ? n2 >= 0 : n2 < this.text.length()) {
            int n3 = n2;
            if (bl) {
                --n3;
            }
            if (n3 < this.text.length()) {
                if (n3 < 0 || bl2 && this.text.charAt(n3) == ' ') break;
                if (this.text.charAt(n3) != ' ') {
                    bl2 = true;
                }
                ++n;
            }
            n2 += bl ? -1 : 1;
        }
        return n;
    }

    @Override
    public boolean render(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.isFocused()) {
            GuiKeyEvents.canUseKeys = false;
        }
        return super.render(guiRenderer, d, d2, d3);
    }

    public void setFocused(boolean bl) {
        if (this.focused && !bl && this.actionOnUnfocused != null) {
            this.actionOnUnfocused.run();
        }
        boolean bl2 = bl && !this.focused;
        this.focused = bl;
        if (bl2) {
            this.onCursorChanged();
        }
    }

    @Override
    public boolean onKeyPressed(int n, int n2) {
        boolean bl;
        if (!this.focused) {
            return false;
        }
        boolean bl2 = bl = n2 == 2 || n2 == 8;
        if (n == 86 && bl) {
            String string = this.text;
            String string2 = Utils.mc.field_1774.method_1460();
            int n3 = 0;
            StringBuilder stringBuilder = new StringBuilder(this.text.length() + string2.length());
            stringBuilder.append(this.text, 0, this.cursor);
            for (int i = 0; i < string2.length(); ++i) {
                char c = string2.charAt(i);
                if (!this.filter.filter(this.text, c)) continue;
                stringBuilder.append(c);
                ++n3;
                if (-1 != 2) continue;
                return false;
            }
            stringBuilder.append(this.text, this.cursor, this.text.length());
            this.text = String.valueOf(stringBuilder);
            this.cursor += n3;
            if (!this.text.equals(string)) {
                this.runAction();
            }
            return true;
        }
        if (n == 67 && bl) {
            Utils.mc.field_1774.method_1455(this.text);
            return true;
        }
        if (n == 88 && bl) {
            String string = this.text;
            Utils.mc.field_1774.method_1455(this.text);
            this.text = "";
            this.cursor = 0;
            if (!this.text.equals(string)) {
                this.runAction();
            }
            return true;
        }
        if (n == 257 || n == 335) {
            this.setFocused(false);
            if (this.actionOnUnfocused != null) {
                this.actionOnUnfocused.run();
            }
            return true;
        }
        return this.onKeyRepeated(n, n2);
    }

    protected double maxTextWidth() {
        return this.width - this.pad() * 2.0;
    }

    public void set(String string) {
        this.text = string;
        this.cursor = Utils.clamp(this.cursor, 0, string.length());
        this.calculateTextWidths();
        this.cursorChanged();
    }
}

