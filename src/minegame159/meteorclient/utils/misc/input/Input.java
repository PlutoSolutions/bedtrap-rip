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
    private static final /* synthetic */ boolean[] buttons;
    private static final /* synthetic */ boolean[] keys;
    private static /* synthetic */ CursorStyle lastCursorStyle;

    public static void setButtonState(int llllllllllllllllIlIlIIllIIlIlIll, boolean llllllllllllllllIlIlIIllIIlIlIlI) {
        if (llllllllllllllllIlIlIIllIIlIlIll >= 0 && llllllllllllllllIlIlIIllIIlIlIll < buttons.length) {
            Input.buttons[llllllllllllllllIlIlIIllIIlIlIll] = llllllllllllllllIlIlIIllIIlIlIlI;
        }
    }

    public static boolean isButtonPressed(int llllllllllllllllIlIlIIllIIIllIIl) {
        if (llllllllllllllllIlIlIIllIIIllIIl == -1) {
            return false;
        }
        return llllllllllllllllIlIlIIllIIIllIIl < buttons.length && buttons[llllllllllllllllIlIlIIllIIIllIIl];
    }

    public static boolean isKeyPressed(int llllllllllllllllIlIlIIllIIIlllIl) {
        if (!GuiKeyEvents.canUseKeys) {
            return false;
        }
        if (llllllllllllllllIlIlIIllIIIlllIl == -1) {
            return false;
        }
        return llllllllllllllllIlIlIIllIIIlllIl < keys.length && keys[llllllllllllllllIlIlIIllIIIlllIl];
    }

    static {
        keys = new boolean[512];
        buttons = new boolean[16];
        lastCursorStyle = CursorStyle.Default;
    }

    public Input() {
        Input llllllllllllllllIlIlIIllIIllIlII;
    }

    public static boolean isPressed(class_304 llllllllllllllllIlIlIIllIIlIIIII) {
        return Input.isKeyPressed(KeyBinds.getKey(llllllllllllllllIlIlIIllIIlIIIII));
    }

    public static void setCursorStyle(CursorStyle llllllllllllllllIlIlIIllIIIlIlll) {
        if (lastCursorStyle != llllllllllllllllIlIlIIllIIIlIlll) {
            GLFW.glfwSetCursor((long)Utils.mc.method_22683().method_4490(), (long)llllllllllllllllIlIlIIllIIIlIlll.getGlfwCursor());
            lastCursorStyle = llllllllllllllllIlIlIIllIIIlIlll;
        }
    }

    public static void setKeyState(int llllllllllllllllIlIlIIllIIlIllll, boolean llllllllllllllllIlIlIIllIIllIIII) {
        if (llllllllllllllllIlIlIIllIIlIllll >= 0 && llllllllllllllllIlIlIIllIIlIllll < keys.length) {
            Input.keys[llllllllllllllllIlIlIIllIIlIllll] = llllllllllllllllIlIlIIllIIllIIII;
        }
    }

    public static void setKeyState(class_304 llllllllllllllllIlIlIIllIIlIIlIl, boolean llllllllllllllllIlIlIIllIIlIIlII) {
        Input.setKeyState(KeyBinds.getKey(llllllllllllllllIlIlIIllIIlIIlIl), llllllllllllllllIlIlIIllIIlIIlII);
    }
}

