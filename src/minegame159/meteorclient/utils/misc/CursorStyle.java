/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFW
 */
package minegame159.meteorclient.utils.misc;

import org.lwjgl.glfw.GLFW;

public enum CursorStyle {
    Default,
    Click,
    Type;

    private /* synthetic */ long cursor;
    private /* synthetic */ boolean created;

    public long getGlfwCursor() {
        CursorStyle llllllllllllllllIllIlllIlIlIIIlI;
        if (!llllllllllllllllIllIlllIlIlIIIlI.created) {
            switch (llllllllllllllllIllIlllIlIlIIIlI) {
                case Click: {
                    llllllllllllllllIllIlllIlIlIIIlI.cursor = GLFW.glfwCreateStandardCursor((int)221188);
                    break;
                }
                case Type: {
                    llllllllllllllllIllIlllIlIlIIIlI.cursor = GLFW.glfwCreateStandardCursor((int)221186);
                }
            }
            llllllllllllllllIllIlllIlIlIIIlI.created = true;
        }
        return llllllllllllllllIllIlllIlIlIIIlI.cursor;
    }

    private CursorStyle() {
        CursorStyle llllllllllllllllIllIlllIlIlIlIII;
    }
}

