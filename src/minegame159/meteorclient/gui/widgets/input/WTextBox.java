/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
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
    public /* synthetic */ Runnable action;
    public /* synthetic */ Runnable actionOnUnfocused;
    protected /* synthetic */ DoubleList textWidths;
    protected /* synthetic */ CharFilter filter;
    protected /* synthetic */ double textStart;
    protected /* synthetic */ String text;
    protected /* synthetic */ boolean focused;
    protected /* synthetic */ int cursor;

    @Override
    public boolean onMouseClicked(double llllllllllllllllIllllIIIlIllIIll, double llllllllllllllllIllllIIIlIlllIII, int llllllllllllllllIllllIIIlIllIIlI, boolean llllllllllllllllIllllIIIlIllIIIl) {
        WTextBox llllllllllllllllIllllIIIlIllIlIl;
        if (llllllllllllllllIllllIIIlIllIlIl.mouseOver && !llllllllllllllllIllllIIIlIllIIIl) {
            if (llllllllllllllllIllllIIIlIllIIlI == 1) {
                if (!llllllllllllllllIllllIIIlIllIlIl.text.isEmpty()) {
                    llllllllllllllllIllllIIIlIllIlIl.text = "";
                    llllllllllllllllIllllIIIlIllIlIl.cursor = 0;
                    llllllllllllllllIllllIIIlIllIlIl.runAction();
                }
            } else {
                double llllllllllllllllIllllIIIlIlllllI = llllllllllllllllIllllIIIlIllIlIl.getOverflowWidthForRender();
                double llllllllllllllllIllllIIIlIllllIl = llllllllllllllllIllllIIIlIllIIll - llllllllllllllllIllllIIIlIllIlIl.x + llllllllllllllllIllllIIIlIlllllI;
                double llllllllllllllllIllllIIIlIllllII = llllllllllllllllIllllIIIlIllIlIl.pad();
                double llllllllllllllllIllllIIIlIlllIll = Double.MAX_VALUE;
                llllllllllllllllIllllIIIlIllIlIl.cursor = 0;
                for (int llllllllllllllllIllllIIIlIllllll = 0; llllllllllllllllIllllIIIlIllllll < llllllllllllllllIllllIIIlIllIlIl.textWidths.size(); ++llllllllllllllllIllllIIIlIllllll) {
                    double llllllllllllllllIllllIIIllIIIIII = Math.abs(llllllllllllllllIllllIIIlIllIlIl.textWidths.getDouble(llllllllllllllllIllllIIIlIllllll) + llllllllllllllllIllllIIIlIllllII - llllllllllllllllIllllIIIlIllllIl);
                    if (!(llllllllllllllllIllllIIIllIIIIII < llllllllllllllllIllllIIIlIlllIll)) continue;
                    llllllllllllllllIllllIIIlIlllIll = llllllllllllllllIllllIIIllIIIIII;
                    llllllllllllllllIllllIIIlIllIlIl.cursor = llllllllllllllllIllllIIIlIllllll;
                }
                llllllllllllllllIllllIIIlIllIlIl.cursorChanged();
            }
            llllllllllllllllIllllIIIlIllIlIl.setFocused(true);
            return true;
        }
        if (llllllllllllllllIllllIIIlIllIlIl.focused) {
            llllllllllllllllIllllIIIlIllIlIl.setFocused(false);
        }
        return false;
    }

    protected double getCursorTextWidth(int llllllllllllllllIllllIIIIIllIlll) {
        WTextBox llllllllllllllllIllllIIIIIlllIII;
        if (llllllllllllllllIllllIIIIIlllIII.textWidths.isEmpty()) {
            return 0.0;
        }
        int llllllllllllllllIllllIIIIIllIllI = llllllllllllllllIllllIIIIIlllIII.cursor + llllllllllllllllIllllIIIIIllIlll;
        if (llllllllllllllllIllllIIIIIllIllI < 0) {
            llllllllllllllllIllllIIIIIllIllI = 0;
        } else if (llllllllllllllllIllllIIIIIllIllI >= llllllllllllllllIllllIIIIIlllIII.textWidths.size()) {
            llllllllllllllllIllllIIIIIllIllI = llllllllllllllllIllllIIIIIlllIII.textWidths.size() - 1;
        }
        return llllllllllllllllIllllIIIIIlllIII.textWidths.getDouble(llllllllllllllllIllllIIIIIllIllI);
    }

    public String get() {
        WTextBox llllllllllllllllIllllIIIIIlIlIll;
        return llllllllllllllllIllllIIIIIlIlIll.text;
    }

    @Override
    public boolean onCharTyped(char llllllllllllllllIllllIIIIlllIIII) {
        WTextBox llllllllllllllllIllllIIIIlllIIIl;
        if (!llllllllllllllllIllllIIIIlllIIIl.focused) {
            return false;
        }
        if (llllllllllllllllIllllIIIIlllIIIl.filter.filter(llllllllllllllllIllllIIIIlllIIIl.text, llllllllllllllllIllllIIIIlllIIII)) {
            llllllllllllllllIllllIIIIlllIIIl.text = String.valueOf(new StringBuilder().append(llllllllllllllllIllllIIIIlllIIIl.text.substring(0, llllllllllllllllIllllIIIIlllIIIl.cursor)).append(llllllllllllllllIllllIIIIlllIIII).append(llllllllllllllllIllllIIIIlllIIIl.text.substring(llllllllllllllllIllllIIIIlllIIIl.cursor)));
            ++llllllllllllllllIllllIIIIlllIIIl.cursor;
            llllllllllllllllIllllIIIIlllIIIl.runAction();
            return true;
        }
        return false;
    }

    protected void onCursorChanged() {
    }

    protected double getOverflowWidthForRender() {
        WTextBox llllllllllllllllIllllIIIIIlIllIl;
        return llllllllllllllllIllllIIIIIlIllIl.textStart;
    }

    private void calculateTextWidths() {
        WTextBox llllllllllllllllIllllIIIIlIIlIll;
        llllllllllllllllIllllIIIIlIIlIll.textWidths.clear();
        for (int llllllllllllllllIllllIIIIlIIllII = 0; llllllllllllllllIllllIIIIlIIllII <= llllllllllllllllIllllIIIIlIIlIll.text.length(); ++llllllllllllllllIllllIIIIlIIllII) {
            llllllllllllllllIllllIIIIlIIlIll.textWidths.add(llllllllllllllllIllllIIIIlIIlIll.theme.textWidth(llllllllllllllllIllllIIIIlIIlIll.text, llllllllllllllllIllllIIIIlIIllII, false));
        }
    }

    public void setCursorMax() {
        WTextBox llllllllllllllllIllllIIIIIIlIlIl;
        llllllllllllllllIllllIIIIIIlIlIl.cursor = llllllllllllllllIllllIIIIIIlIlIl.text.length();
    }

    private void cursorChanged() {
        WTextBox llllllllllllllllIllllIIIIIlllllI;
        double llllllllllllllllIllllIIIIIllllll = llllllllllllllllIllllIIIIIlllllI.getCursorTextWidth(-2);
        if (llllllllllllllllIllllIIIIIllllll < llllllllllllllllIllllIIIIIlllllI.textStart) {
            llllllllllllllllIllllIIIIIlllllI.textStart -= llllllllllllllllIllllIIIIIlllllI.textStart - llllllllllllllllIllllIIIIIllllll;
        }
        if ((llllllllllllllllIllllIIIIIllllll = llllllllllllllllIllllIIIIIlllllI.getCursorTextWidth(2)) > llllllllllllllllIllllIIIIIlllllI.textStart + llllllllllllllllIllllIIIIIlllllI.maxTextWidth()) {
            llllllllllllllllIllllIIIIIlllllI.textStart += llllllllllllllllIllllIIIIIllllll - (llllllllllllllllIllllIIIIIlllllI.textStart + llllllllllllllllIllllIIIIIlllllI.maxTextWidth());
        }
        llllllllllllllllIllllIIIIIlllllI.textStart = Utils.clamp(llllllllllllllllIllllIIIIIlllllI.textStart, 0.0, Math.max(llllllllllllllllIllllIIIIIlllllI.textWidth() - llllllllllllllllIllllIIIIIlllllI.maxTextWidth(), 0.0));
        llllllllllllllllIllllIIIIIlllllI.onCursorChanged();
    }

    private double textWidth() {
        WTextBox llllllllllllllllIllllIIIIlIIIIll;
        return llllllllllllllllIllllIIIIlIIIIll.textWidths.isEmpty() ? 0.0 : llllllllllllllllIllllIIIIlIIIIll.textWidths.getDouble(llllllllllllllllIllllIIIIlIIIIll.textWidths.size() - 1);
    }

    @Override
    public boolean onKeyRepeated(int llllllllllllllllIllllIIIIllllIll, int llllllllllllllllIllllIIIIllllIlI) {
        boolean llllllllllllllllIllllIIIIlllllIl;
        WTextBox llllllllllllllllIllllIIIlIIIIIIl;
        if (!llllllllllllllllIllllIIIlIIIIIIl.focused) {
            return false;
        }
        boolean llllllllllllllllIllllIIIIllllllI = llllllllllllllllIllllIIIIllllIlI == 2 || llllllllllllllllIllllIIIIllllIlI == 8;
        boolean bl = llllllllllllllllIllllIIIIlllllIl = llllllllllllllllIllllIIIIllllIlI == 1;
        if (llllllllllllllllIllllIIIIllllIll == 259) {
            if (llllllllllllllllIllllIIIlIIIIIIl.cursor > 0) {
                String llllllllllllllllIllllIIIlIIIIlIl = llllllllllllllllIllllIIIlIIIIIIl.text;
                int llllllllllllllllIllllIIIlIIIIlII = llllllllllllllllIllllIIIIllllllI ? llllllllllllllllIllllIIIlIIIIIIl.countToNextSpace(true) : 1;
                llllllllllllllllIllllIIIlIIIIIIl.text = String.valueOf(new StringBuilder().append(llllllllllllllllIllllIIIlIIIIIIl.text.substring(0, llllllllllllllllIllllIIIlIIIIIIl.cursor - llllllllllllllllIllllIIIlIIIIlII)).append(llllllllllllllllIllllIIIlIIIIIIl.text.substring(llllllllllllllllIllllIIIlIIIIIIl.cursor)));
                llllllllllllllllIllllIIIlIIIIIIl.cursor -= llllllllllllllllIllllIIIlIIIIlII;
                if (!llllllllllllllllIllllIIIlIIIIIIl.text.equals(llllllllllllllllIllllIIIlIIIIlIl)) {
                    llllllllllllllllIllllIIIlIIIIIIl.runAction();
                }
            }
            return true;
        }
        if (llllllllllllllllIllllIIIIllllIll == 261) {
            if (llllllllllllllllIllllIIIlIIIIIIl.cursor < llllllllllllllllIllllIIIlIIIIIIl.text.length()) {
                String llllllllllllllllIllllIIIlIIIIIll = llllllllllllllllIllllIIIlIIIIIIl.text;
                int llllllllllllllllIllllIIIlIIIIIlI = llllllllllllllllIllllIIIIllllllI ? llllllllllllllllIllllIIIlIIIIIIl.countToNextSpace(false) : 1;
                llllllllllllllllIllllIIIlIIIIIIl.text = String.valueOf(new StringBuilder().append(llllllllllllllllIllllIIIlIIIIIIl.text.substring(0, llllllllllllllllIllllIIIlIIIIIIl.cursor)).append(llllllllllllllllIllllIIIlIIIIIIl.text.substring(llllllllllllllllIllllIIIlIIIIIIl.cursor + llllllllllllllllIllllIIIlIIIIIlI)));
                if (!llllllllllllllllIllllIIIlIIIIIIl.text.equals(llllllllllllllllIllllIIIlIIIIIll)) {
                    llllllllllllllllIllllIIIlIIIIIIl.runAction();
                }
            }
            return true;
        }
        if (llllllllllllllllIllllIIIIllllIll == 263 || llllllllllllllllIllllIIIIlllllIl && llllllllllllllllIllllIIIIllllIll == 324) {
            if (llllllllllllllllIllllIIIlIIIIIIl.cursor > 0) {
                llllllllllllllllIllllIIIlIIIIIIl.cursor -= llllllllllllllllIllllIIIIllllllI ? llllllllllllllllIllllIIIlIIIIIIl.countToNextSpace(true) : 1;
                llllllllllllllllIllllIIIlIIIIIIl.cursorChanged();
            }
            return true;
        }
        if (llllllllllllllllIllllIIIIllllIll == 262 || llllllllllllllllIllllIIIIlllllIl && llllllllllllllllIllllIIIIllllIll == 326) {
            if (llllllllllllllllIllllIIIlIIIIIIl.cursor < llllllllllllllllIllllIIIlIIIIIIl.text.length()) {
                llllllllllllllllIllllIIIlIIIIIIl.cursor += llllllllllllllllIllllIIIIllllllI ? llllllllllllllllIllllIIIlIIIIIIl.countToNextSpace(false) : 1;
                llllllllllllllllIllllIIIlIIIIIIl.cursorChanged();
            }
            return true;
        }
        return false;
    }

    private void runAction() {
        WTextBox llllllllllllllllIllllIIIIlIIIllI;
        llllllllllllllllIllllIIIIlIIIllI.calculateTextWidths();
        llllllllllllllllIllllIIIIlIIIllI.cursorChanged();
        if (llllllllllllllllIllllIIIIlIIIllI.action != null) {
            llllllllllllllllIllllIIIIlIIIllI.action.run();
        }
    }

    public boolean isFocused() {
        WTextBox llllllllllllllllIllllIIIIIlIIIlI;
        return llllllllllllllllIllllIIIIIlIIIlI.focused;
    }

    protected double getCursorTextWidth() {
        WTextBox llllllllllllllllIllllIIIIIllIIIl;
        return llllllllllllllllIllllIIIIIllIIIl.getCursorTextWidth(0);
    }

    @Override
    protected void onCalculateSize() {
        WTextBox llllllllllllllllIllllIIIllIlIIII;
        double llllllllllllllllIllllIIIllIlIIlI = llllllllllllllllIllllIIIllIlIIII.pad();
        double llllllllllllllllIllllIIIllIlIIIl = llllllllllllllllIllllIIIllIlIIII.theme.textHeight();
        llllllllllllllllIllllIIIllIlIIII.width = llllllllllllllllIllllIIIllIlIIlI + llllllllllllllllIllllIIIllIlIIIl + llllllllllllllllIllllIIIllIlIIlI;
        llllllllllllllllIllllIIIllIlIIII.height = llllllllllllllllIllllIIIllIlIIlI + llllllllllllllllIllllIIIllIlIIIl + llllllllllllllllIllllIIIllIlIIlI;
        llllllllllllllllIllllIIIllIlIIII.calculateTextWidths();
    }

    public WTextBox(String llllllllllllllllIllllIIIllIllIll, CharFilter llllllllllllllllIllllIIIllIlIlll) {
        WTextBox llllllllllllllllIllllIIIllIlllII;
        llllllllllllllllIllllIIIllIlllII.textWidths = new DoubleArrayList();
        llllllllllllllllIllllIIIllIlllII.text = llllllllllllllllIllllIIIllIllIll;
        llllllllllllllllIllllIIIllIlllII.filter = llllllllllllllllIllllIIIllIlIlll;
    }

    private int countToNextSpace(boolean llllllllllllllllIllllIIIIlIlIlll) {
        WTextBox llllllllllllllllIllllIIIIlIlIlII;
        int llllllllllllllllIllllIIIIlIlIllI = 0;
        boolean llllllllllllllllIllllIIIIlIlIlIl = false;
        int llllllllllllllllIllllIIIIlIllIIl = llllllllllllllllIllllIIIIlIlIlII.cursor;
        while (llllllllllllllllIllllIIIIlIlIlll ? llllllllllllllllIllllIIIIlIllIIl >= 0 : llllllllllllllllIllllIIIIlIllIIl < llllllllllllllllIllllIIIIlIlIlII.text.length()) {
            int llllllllllllllllIllllIIIIlIllIlI = llllllllllllllllIllllIIIIlIllIIl;
            if (llllllllllllllllIllllIIIIlIlIlll) {
                --llllllllllllllllIllllIIIIlIllIlI;
            }
            if (llllllllllllllllIllllIIIIlIllIlI < llllllllllllllllIllllIIIIlIlIlII.text.length()) {
                if (llllllllllllllllIllllIIIIlIllIlI < 0 || llllllllllllllllIllllIIIIlIlIlIl && llllllllllllllllIllllIIIIlIlIlII.text.charAt(llllllllllllllllIllllIIIIlIllIlI) == ' ') break;
                if (llllllllllllllllIllllIIIIlIlIlII.text.charAt(llllllllllllllllIllllIIIIlIllIlI) != ' ') {
                    llllllllllllllllIllllIIIIlIlIlIl = true;
                }
                ++llllllllllllllllIllllIIIIlIlIllI;
            }
            llllllllllllllllIllllIIIIlIllIIl += llllllllllllllllIllllIIIIlIlIlll ? -1 : 1;
        }
        return llllllllllllllllIllllIIIIlIlIllI;
    }

    @Override
    public boolean render(GuiRenderer llllllllllllllllIllllIIIIllIIlII, double llllllllllllllllIllllIIIIllIlIII, double llllllllllllllllIllllIIIIllIIlll, double llllllllllllllllIllllIIIIllIIllI) {
        WTextBox llllllllllllllllIllllIIIIllIlIlI;
        if (llllllllllllllllIllllIIIIllIlIlI.isFocused()) {
            GuiKeyEvents.canUseKeys = false;
        }
        return super.render(llllllllllllllllIllllIIIIllIIlII, llllllllllllllllIllllIIIIllIlIII, llllllllllllllllIllllIIIIllIIlll, llllllllllllllllIllllIIIIllIIllI);
    }

    public void setFocused(boolean llllllllllllllllIllllIIIIIIlllII) {
        WTextBox llllllllllllllllIllllIIIIIIlllIl;
        if (llllllllllllllllIllllIIIIIIlllIl.focused && !llllllllllllllllIllllIIIIIIlllII && llllllllllllllllIllllIIIIIIlllIl.actionOnUnfocused != null) {
            llllllllllllllllIllllIIIIIIlllIl.actionOnUnfocused.run();
        }
        boolean llllllllllllllllIllllIIIIIIllIll = llllllllllllllllIllllIIIIIIlllII && !llllllllllllllllIllllIIIIIIlllIl.focused;
        llllllllllllllllIllllIIIIIIlllIl.focused = llllllllllllllllIllllIIIIIIlllII;
        if (llllllllllllllllIllllIIIIIIllIll) {
            llllllllllllllllIllllIIIIIIlllIl.onCursorChanged();
        }
    }

    @Override
    public boolean onKeyPressed(int llllllllllllllllIllllIIIlIIllIIl, int llllllllllllllllIllllIIIlIIlIlII) {
        boolean llllllllllllllllIllllIIIlIIlIlll;
        WTextBox llllllllllllllllIllllIIIlIIlIllI;
        if (!llllllllllllllllIllllIIIlIIlIllI.focused) {
            return false;
        }
        boolean bl = llllllllllllllllIllllIIIlIIlIlll = llllllllllllllllIllllIIIlIIlIlII == 2 || llllllllllllllllIllllIIIlIIlIlII == 8;
        if (llllllllllllllllIllllIIIlIIllIIl == 86 && llllllllllllllllIllllIIIlIIlIlll) {
            String llllllllllllllllIllllIIIlIIlllll = llllllllllllllllIllllIIIlIIlIllI.text;
            String llllllllllllllllIllllIIIlIIllllI = Utils.mc.field_1774.method_1460();
            int llllllllllllllllIllllIIIlIIlllIl = 0;
            StringBuilder llllllllllllllllIllllIIIlIIlllII = new StringBuilder(llllllllllllllllIllllIIIlIIlIllI.text.length() + llllllllllllllllIllllIIIlIIllllI.length());
            llllllllllllllllIllllIIIlIIlllII.append(llllllllllllllllIllllIIIlIIlIllI.text, 0, llllllllllllllllIllllIIIlIIlIllI.cursor);
            for (int llllllllllllllllIllllIIIlIlIIIII = 0; llllllllllllllllIllllIIIlIlIIIII < llllllllllllllllIllllIIIlIIllllI.length(); ++llllllllllllllllIllllIIIlIlIIIII) {
                char llllllllllllllllIllllIIIlIlIIIIl = llllllllllllllllIllllIIIlIIllllI.charAt(llllllllllllllllIllllIIIlIlIIIII);
                if (!llllllllllllllllIllllIIIlIIlIllI.filter.filter(llllllllllllllllIllllIIIlIIlIllI.text, llllllllllllllllIllllIIIlIlIIIIl)) continue;
                llllllllllllllllIllllIIIlIIlllII.append(llllllllllllllllIllllIIIlIlIIIIl);
                ++llllllllllllllllIllllIIIlIIlllIl;
            }
            llllllllllllllllIllllIIIlIIlllII.append(llllllllllllllllIllllIIIlIIlIllI.text, llllllllllllllllIllllIIIlIIlIllI.cursor, llllllllllllllllIllllIIIlIIlIllI.text.length());
            llllllllllllllllIllllIIIlIIlIllI.text = String.valueOf(llllllllllllllllIllllIIIlIIlllII);
            llllllllllllllllIllllIIIlIIlIllI.cursor += llllllllllllllllIllllIIIlIIlllIl;
            if (!llllllllllllllllIllllIIIlIIlIllI.text.equals(llllllllllllllllIllllIIIlIIlllll)) {
                llllllllllllllllIllllIIIlIIlIllI.runAction();
            }
            return true;
        }
        if (llllllllllllllllIllllIIIlIIllIIl == 67 && llllllllllllllllIllllIIIlIIlIlll) {
            Utils.mc.field_1774.method_1455(llllllllllllllllIllllIIIlIIlIllI.text);
            return true;
        }
        if (llllllllllllllllIllllIIIlIIllIIl == 88 && llllllllllllllllIllllIIIlIIlIlll) {
            String llllllllllllllllIllllIIIlIIllIll = llllllllllllllllIllllIIIlIIlIllI.text;
            Utils.mc.field_1774.method_1455(llllllllllllllllIllllIIIlIIlIllI.text);
            llllllllllllllllIllllIIIlIIlIllI.text = "";
            llllllllllllllllIllllIIIlIIlIllI.cursor = 0;
            if (!llllllllllllllllIllllIIIlIIlIllI.text.equals(llllllllllllllllIllllIIIlIIllIll)) {
                llllllllllllllllIllllIIIlIIlIllI.runAction();
            }
            return true;
        }
        if (llllllllllllllllIllllIIIlIIllIIl == 257 || llllllllllllllllIllllIIIlIIllIIl == 335) {
            llllllllllllllllIllllIIIlIIlIllI.setFocused(false);
            if (llllllllllllllllIllllIIIlIIlIllI.actionOnUnfocused != null) {
                llllllllllllllllIllllIIIlIIlIllI.actionOnUnfocused.run();
            }
            return true;
        }
        return llllllllllllllllIllllIIIlIIlIllI.onKeyRepeated(llllllllllllllllIllllIIIlIIllIIl, llllllllllllllllIllllIIIlIIlIlII);
    }

    protected double maxTextWidth() {
        WTextBox llllllllllllllllIllllIIIllIIlIll;
        return llllllllllllllllIllllIIIllIIlIll.width - llllllllllllllllIllllIIIllIIlIll.pad() * 2.0;
    }

    public void set(String llllllllllllllllIllllIIIIIlIIllI) {
        WTextBox llllllllllllllllIllllIIIIIlIIlIl;
        llllllllllllllllIllllIIIIIlIIlIl.text = llllllllllllllllIllllIIIIIlIIllI;
        llllllllllllllllIllllIIIIIlIIlIl.cursor = Utils.clamp(llllllllllllllllIllllIIIIIlIIlIl.cursor, 0, llllllllllllllllIllllIIIIIlIIllI.length());
        llllllllllllllllIllllIIIIIlIIlIl.calculateTextWidths();
        llllllllllllllllIllllIIIIIlIIlIl.cursorChanged();
    }
}

