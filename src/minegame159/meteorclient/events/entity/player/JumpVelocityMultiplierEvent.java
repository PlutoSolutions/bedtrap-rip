/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.entity.player;

public class JumpVelocityMultiplierEvent {
    private static final /* synthetic */ JumpVelocityMultiplierEvent INSTANCE;
    public /* synthetic */ float multiplier;

    public static JumpVelocityMultiplierEvent get() {
        JumpVelocityMultiplierEvent.INSTANCE.multiplier = 1.0f;
        return INSTANCE;
    }

    public JumpVelocityMultiplierEvent() {
        JumpVelocityMultiplierEvent lIIlllllIlIIlI;
        lIIlllllIlIIlI.multiplier = 1.0f;
    }

    static {
        INSTANCE = new JumpVelocityMultiplierEvent();
    }
}

