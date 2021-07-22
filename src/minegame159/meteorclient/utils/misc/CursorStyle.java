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

    private long cursor;
    private boolean created;

    public long getGlfwCursor() {
        if (!this.created) {
            switch (1.$SwitchMap$minegame159$meteorclient$utils$misc$CursorStyle[this.ordinal()]) {
                case 1: {
                    this.cursor = GLFW.glfwCreateStandardCursor((int)221188);
                    break;
                }
                case 2: {
                    this.cursor = GLFW.glfwCreateStandardCursor((int)221186);
                }
            }
            this.created = true;
        }
        return this.cursor;
    }
}

