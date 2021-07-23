/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.systems.modules.Module;

public class ModuleBindChangedEvent {
    private static final ModuleBindChangedEvent INSTANCE = new ModuleBindChangedEvent();
    public Module module;

    public static ModuleBindChangedEvent get(Module module) {
        ModuleBindChangedEvent.INSTANCE.module = module;
        return INSTANCE;
    }
}

