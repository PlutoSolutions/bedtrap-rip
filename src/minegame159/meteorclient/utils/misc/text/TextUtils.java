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
    private static void preOrderTraverse(class_2561 class_25612, Stack<ColoredText> stack, List<ColoredText> list) {
        if (class_25612 == null) {
            return;
        }
        String string = class_25612.method_10851();
        class_5251 class_52512 = class_25612.method_10866().method_10973();
        Color color = class_52512 == null ? (stack.empty() ? new Color(255, 255, 255) : stack.peek().getColor()) : new Color(class_25612.method_10866().method_10973().method_27716() | 0xFF000000);
        ColoredText coloredText = new ColoredText(string, color);
        list.add(coloredText);
        stack.push(coloredText);
        for (class_2561 class_25613 : class_25612.method_10855()) {
            TextUtils.preOrderTraverse(class_25613, stack, list);
        }
        stack.pop();
    }

    public static Color getMostPopularColor(class_2561 class_25612) {
        Comparator comparator = Comparator.naturalOrder();
        Optional optional = TextUtils.getColoredCharacterCount(TextUtils.toColoredTextList(class_25612)).entrySet().stream().max((arg_0, arg_1) -> TextUtils.lambda$getMostPopularColor$1(comparator, arg_0, arg_1));
        return optional.map(Map.Entry::getKey).orElse(new Color(255, 255, 255));
    }

    public static Map<Color, Integer> getColoredCharacterCount(List<ColoredText> list) {
        HashMap<Color, Integer> hashMap = new HashMap<Color, Integer>();
        for (ColoredText coloredText : list) {
            if (hashMap.containsKey(coloredText.getColor())) {
                hashMap.put(coloredText.getColor(), (Integer)hashMap.get(coloredText.getColor()) + coloredText.getText().length());
                continue;
            }
            hashMap.put(coloredText.getColor(), coloredText.getText().length());
        }
        return hashMap;
    }

    private static boolean lambda$toColoredTextList$0(ColoredText coloredText) {
        return coloredText.getText().equals("");
    }

    private static int lambda$getMostPopularColor$1(Comparator comparator, Map.Entry entry, Map.Entry entry2) {
        return comparator.compare((Integer)entry.getValue(), (Integer)entry2.getValue());
    }

    public static List<ColoredText> toColoredTextList(class_2561 class_25612) {
        Stack<ColoredText> stack = new Stack<ColoredText>();
        ArrayList<ColoredText> arrayList = new ArrayList<ColoredText>();
        TextUtils.preOrderTraverse(class_25612, stack, arrayList);
        arrayList.removeIf(TextUtils::lambda$toColoredTextList$0);
        return arrayList;
    }
}

