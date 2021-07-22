/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.entity.player;

public class JumpVelocityMultiplierEvent {
    private static final JumpVelocityMultiplierEvent INSTANCE = new JumpVelocityMultiplierEvent();
    public float multiplier = 1.0f;

    public static JumpVelocityMultiplierEvent get() {
        JumpVelocityMultiplierEvent.INSTANCE.multiplier = 1.0f;
        return INSTANCE;
    }
}

