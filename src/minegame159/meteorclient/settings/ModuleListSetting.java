/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;

public class ModuleListSetting
extends Setting<List<Module>> {
    private static /* synthetic */ List<String> suggestions;

    @Override
    public class_2487 toTag() {
        ModuleListSetting lIllIlIllllIlIl;
        class_2487 lIllIlIllllIlII = lIllIlIllllIlIl.saveGeneral();
        class_2499 lIllIlIllllIIll = new class_2499();
        for (Module lIllIlIllllIllI : (List)lIllIlIllllIlIl.get()) {
            lIllIlIllllIIll.add((Object)class_2519.method_23256((String)lIllIlIllllIllI.name));
        }
        lIllIlIllllIlII.method_10566("modules", (class_2520)lIllIlIllllIIll);
        return lIllIlIllllIlII;
    }

    @Override
    protected boolean isValueValid(List<Module> lIllIllIIIIIIlI) {
        return true;
    }

    @Override
    public List<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<String>(Modules.get().getAll().size());
            for (Module lIllIlIllllllll : Modules.get().getAll()) {
                suggestions.add(lIllIlIllllllll.name);
            }
        }
        return suggestions;
    }

    @Override
    protected List<Module> parseImpl(String lIllIllIIIIlIll) {
        String[] lIllIllIIIIllIl = lIllIllIIIIlIll.split(",");
        ArrayList<Module> lIllIllIIIIllII = new ArrayList<Module>(lIllIllIIIIllIl.length);
        try {
            for (String lIllIllIIIlIIII : lIllIllIIIIllIl) {
                Module lIllIllIIIlIIIl = Modules.get().get(lIllIllIIIlIIII.trim());
                if (lIllIllIIIlIIIl == null) continue;
                lIllIllIIIIllII.add(lIllIllIIIlIIIl);
            }
        }
        catch (Exception lIllIllIIIIlIII) {
            // empty catch block
        }
        return lIllIllIIIIllII;
    }

    @Override
    public List<Module> fromTag(class_2487 lIllIlIlllIIIIl) {
        ModuleListSetting lIllIlIlllIIlIl;
        ((List)lIllIlIlllIIlIl.get()).clear();
        class_2499 lIllIlIlllIIIll = lIllIlIlllIIIIl.method_10554("modules", 8);
        for (class_2520 lIllIlIlllIIllI : lIllIlIlllIIIll) {
            Module lIllIlIlllIIlll = Modules.get().get(lIllIlIlllIIllI.method_10714());
            if (lIllIlIlllIIlll == null) continue;
            ((List)lIllIlIlllIIlIl.get()).add(lIllIlIlllIIlll);
        }
        lIllIlIlllIIlIl.changed();
        return (List)lIllIlIlllIIlIl.get();
    }

    public ModuleListSetting(String lIllIllIIlIllII, String lIllIllIIlIIlII, List<Module> lIllIllIIlIIIll, Consumer<List<Module>> lIllIllIIlIIIlI, Consumer<Setting<List<Module>>> lIllIllIIlIlIII, IVisible lIllIllIIlIIIII) {
        super(lIllIllIIlIllII, lIllIllIIlIIlII, lIllIllIIlIIIll, lIllIllIIlIIIlI, lIllIllIIlIlIII, lIllIllIIlIIIII);
        ModuleListSetting lIllIllIIlIIllI;
        lIllIllIIlIIllI.value = new ArrayList<Module>(lIllIllIIlIIIll);
    }

    @Override
    public void reset(boolean lIllIllIIIlllII) {
        ModuleListSetting lIllIllIIIllIll;
        lIllIllIIIllIll.value = new ArrayList((Collection)lIllIllIIIllIll.defaultValue);
        if (lIllIllIIIlllII) {
            lIllIllIIIllIll.changed();
        }
    }

    public static class Builder {
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Consumer<List<Module>> onChanged;
        private /* synthetic */ String description;
        private /* synthetic */ List<Module> defaultValue;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<Setting<List<Module>>> onModuleActivated;

        public Builder defaultValue(List<Module> lllIIIIIllllIll) {
            Builder lllIIIIIlllllII;
            lllIIIIIlllllII.defaultValue = lllIIIIIllllIll;
            return lllIIIIIlllllII;
        }

        public Builder name(String lllIIIIlIIIlIIl) {
            Builder lllIIIIlIIIlIlI;
            lllIIIIlIIIlIlI.name = lllIIIIlIIIlIIl;
            return lllIIIIlIIIlIlI;
        }

        public Builder onChanged(Consumer<List<Module>> lllIIIIIlllIlIl) {
            Builder lllIIIIIlllIllI;
            lllIIIIIlllIllI.onChanged = lllIIIIIlllIlIl;
            return lllIIIIIlllIllI;
        }

        public Builder onModuleActivated(Consumer<Setting<List<Module>>> lllIIIIIlllIIIl) {
            Builder lllIIIIIlllIIII;
            lllIIIIIlllIIII.onModuleActivated = lllIIIIIlllIIIl;
            return lllIIIIIlllIIII;
        }

        public Builder description(String lllIIIIlIIIIIIl) {
            Builder lllIIIIlIIIIIlI;
            lllIIIIlIIIIIlI.description = lllIIIIlIIIIIIl;
            return lllIIIIlIIIIIlI;
        }

        public Builder visible(IVisible lllIIIIIllIlIll) {
            Builder lllIIIIIllIlIlI;
            lllIIIIIllIlIlI.visible = lllIIIIIllIlIll;
            return lllIIIIIllIlIlI;
        }

        public ModuleListSetting build() {
            Builder lllIIIIIllIIllI;
            return new ModuleListSetting(lllIIIIIllIIllI.name, lllIIIIIllIIllI.description, lllIIIIIllIIllI.defaultValue, lllIIIIIllIIllI.onChanged, lllIIIIIllIIllI.onModuleActivated, lllIIIIIllIIllI.visible);
        }

        public Builder() {
            Builder lllIIIIlIIIllIl;
            lllIIIIlIIIllIl.name = "undefined";
            lllIIIIlIIIllIl.description = "";
        }
    }
}

