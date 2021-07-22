/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.systems.modules.Module;

public class ModuleBindChangedEvent {
    private static final /* synthetic */ ModuleBindChangedEvent INSTANCE;
    public /* synthetic */ Module module;

    public static ModuleBindChangedEvent get(Module lllllllllllllllllIIIIlIlIlIIlIlI) {
        ModuleBindChangedEvent.INSTANCE.module = lllllllllllllllllIIIIlIlIlIIlIlI;
        return INSTANCE;
    }

    static {
        INSTANCE = new ModuleBindChangedEvent();
    }

    public ModuleBindChangedEvent() {
        ModuleBindChangedEvent lllllllllllllllllIIIIlIlIlIIllIl;
    }
}

