/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  org.lwjgl.glfw.GLFW
 */
package minegame159.meteorclient.utils.misc.input;

import minegame159.meteorclient.gui.GuiKeyEvents;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.CursorStyle;
import minegame159.meteorclient.utils.misc.input.KeyBinds;
import net.minecraft.class_304;
import org.lwjgl.glfw.GLFW;

public class Input {
    private static final boolean[] buttons;
    private static final boolean[] keys;
    private static CursorStyle lastCursorStyle;

    public static void setButtonState(int n, boolean bl) {
        if (n >= 0 && n < buttons.length) {
            Input.buttons[n] = bl;
        }
    }

    public static boolean isButtonPressed(int n) {
        if (n == -1) {
            return false;
        }
        return n < buttons.length && buttons[n];
    }

    public static boolean isKeyPressed(int n) {
        if (!GuiKeyEvents.canUseKeys) {
            return false;
        }
        if (n == -1) {
            return false;
        }
        return n < keys.length && keys[n];
    }

    static {
        keys = new boolean[512];
        buttons = new boolean[16];
        lastCursorStyle = CursorStyle.Default;
    }

    public static boolean isPressed(class_304 class_3042) {
        return Input.isKeyPressed(KeyBinds.getKey(class_3042));
    }

    public static void setCursorStyle(CursorStyle cursorStyle) {
        if (lastCursorStyle != cursorStyle) {
            GLFW.glfwSetCursor((long)Utils.mc.method_22683().method_4490(), (long)cursorStyle.getGlfwCursor());
            lastCursorStyle = cursorStyle;
        }
    }

    public static void setKeyState(int n, boolean bl) {
        if (n >= 0 && n < keys.length) {
            Input.keys[n] = bl;
        }
    }

    public static void setKeyState(class_304 class_3042, boolean bl) {
        Input.setKeyState(KeyBinds.getKey(class_3042), bl);
    }
}

