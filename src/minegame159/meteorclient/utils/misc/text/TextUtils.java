/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_5251
 */
package minegame159.meteorclient.utils.misc.text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import minegame159.meteorclient.utils.misc.text.ColoredText;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_2561;
import net.minecraft.class_5251;

public class TextUtils {
    private static void preOrderTraverse(class_2561 llllllllllllllllIllIIIIIIIllIIIl, Stack<ColoredText> llllllllllllllllIllIIIIIIIllIIII, List<ColoredText> llllllllllllllllIllIIIIIIIlIllll) {
        Color llllllllllllllllIllIIIIIIIlIllII;
        if (llllllllllllllllIllIIIIIIIllIIIl == null) {
            return;
        }
        String llllllllllllllllIllIIIIIIIlIlllI = llllllllllllllllIllIIIIIIIllIIIl.method_10851();
        class_5251 llllllllllllllllIllIIIIIIIlIllIl = llllllllllllllllIllIIIIIIIllIIIl.method_10866().method_10973();
        if (llllllllllllllllIllIIIIIIIlIllIl == null) {
            if (llllllllllllllllIllIIIIIIIllIIII.empty()) {
                Color llllllllllllllllIllIIIIIIIllIlII = new Color(255, 255, 255);
            } else {
                Color llllllllllllllllIllIIIIIIIllIIll = llllllllllllllllIllIIIIIIIllIIII.peek().getColor();
            }
        } else {
            llllllllllllllllIllIIIIIIIlIllII = new Color(llllllllllllllllIllIIIIIIIllIIIl.method_10866().method_10973().method_27716() | 0xFF000000);
        }
        ColoredText llllllllllllllllIllIIIIIIIlIlIll = new ColoredText(llllllllllllllllIllIIIIIIIlIlllI, llllllllllllllllIllIIIIIIIlIllII);
        llllllllllllllllIllIIIIIIIlIllll.add(llllllllllllllllIllIIIIIIIlIlIll);
        llllllllllllllllIllIIIIIIIllIIII.push(llllllllllllllllIllIIIIIIIlIlIll);
        for (class_2561 llllllllllllllllIllIIIIIIIllIIlI : llllllllllllllllIllIIIIIIIllIIIl.method_10855()) {
            TextUtils.preOrderTraverse(llllllllllllllllIllIIIIIIIllIIlI, llllllllllllllllIllIIIIIIIllIIII, llllllllllllllllIllIIIIIIIlIllll);
        }
        llllllllllllllllIllIIIIIIIllIIII.pop();
    }

    public static Color getMostPopularColor(class_2561 llllllllllllllllIllIIIIIIlIIlIll) {
        Comparator llllllllllllllllIllIIIIIIlIIllIl = Comparator.naturalOrder();
        Optional llllllllllllllllIllIIIIIIlIIllII = TextUtils.getColoredCharacterCount(TextUtils.toColoredTextList(llllllllllllllllIllIIIIIIlIIlIll)).entrySet().stream().max((llllllllllllllllIllIIIIIIIIllIlI, llllllllllllllllIllIIIIIIIIllIIl) -> llllllllllllllllIllIIIIIIlIIllIl.compare((Integer)llllllllllllllllIllIIIIIIIIllIlI.getValue(), (Integer)llllllllllllllllIllIIIIIIIIllIIl.getValue()));
        return llllllllllllllllIllIIIIIIlIIllII.map(Map.Entry::getKey).orElse(new Color(255, 255, 255));
    }

    public static Map<Color, Integer> getColoredCharacterCount(List<ColoredText> llllllllllllllllIllIIIIIIlIIIIll) {
        HashMap<Color, Integer> llllllllllllllllIllIIIIIIlIIIIlI = new HashMap<Color, Integer>();
        for (ColoredText llllllllllllllllIllIIIIIIlIIIlII : llllllllllllllllIllIIIIIIlIIIIll) {
            if (llllllllllllllllIllIIIIIIlIIIIlI.containsKey(llllllllllllllllIllIIIIIIlIIIlII.getColor())) {
                llllllllllllllllIllIIIIIIlIIIIlI.put(llllllllllllllllIllIIIIIIlIIIlII.getColor(), (Integer)llllllllllllllllIllIIIIIIlIIIIlI.get(llllllllllllllllIllIIIIIIlIIIlII.getColor()) + llllllllllllllllIllIIIIIIlIIIlII.getText().length());
                continue;
            }
            llllllllllllllllIllIIIIIIlIIIIlI.put(llllllllllllllllIllIIIIIIlIIIlII.getColor(), llllllllllllllllIllIIIIIIlIIIlII.getText().length());
        }
        return llllllllllllllllIllIIIIIIlIIIIlI;
    }

    public static List<ColoredText> toColoredTextList(class_2561 llllllllllllllllIllIIIIIIlIlIlll) {
        Stack<ColoredText> llllllllllllllllIllIIIIIIlIlIllI = new Stack<ColoredText>();
        ArrayList<ColoredText> llllllllllllllllIllIIIIIIlIlIlIl = new ArrayList<ColoredText>();
        TextUtils.preOrderTraverse(llllllllllllllllIllIIIIIIlIlIlll, llllllllllllllllIllIIIIIIlIlIllI, llllllllllllllllIllIIIIIIlIlIlIl);
        llllllllllllllllIllIIIIIIlIlIlIl.removeIf(llllllllllllllllIllIIIIIIIIlIllI -> llllllllllllllllIllIIIIIIIIlIllI.getText().equals(""));
        return llllllllllllllllIllIIIIIIlIlIlIl;
    }

    public TextUtils() {
        TextUtils llllllllllllllllIllIIIIIIlIlllII;
    }
}

