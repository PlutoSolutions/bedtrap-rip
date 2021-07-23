/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import baritone.api.BaritoneAPI;
import baritone.api.utils.Rotation;
import java.lang.reflect.Field;

public class BaritoneUtils {
    private static Field targetField;

    public static Rotation getTarget() {
        BaritoneUtils.findField();
        if (targetField == null) {
            return null;
        }
        targetField.setAccessible(true);
        try {
            return (Rotation)targetField.get(BaritoneAPI.getProvider().getPrimaryBaritone().getLookBehavior());
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            return null;
        }
    }

    private static void findField() {
        if (targetField != null) {
            return;
        }
        Class<?> clazz = BaritoneAPI.getProvider().getPrimaryBaritone().getLookBehavior().getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType() != Rotation.class) continue;
            targetField = field;
            break;
        }
    }
}

