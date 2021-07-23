/*
 * Decompiled with CFR 0.151.
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
    private static List<String> suggestions;

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (Module module : (List)this.get()) {
            class_24992.add((Object)class_2519.method_23256((String)module.name));
        }
        class_24872.method_10566("modules", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    protected boolean isValueValid(List<Module> list) {
        return true;
    }

    @Override
    public List<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<String>(Modules.get().getAll().size());
            for (Module module : Modules.get().getAll()) {
                suggestions.add(module.name);
            }
        }
        return suggestions;
    }

    @Override
    protected List<Module> parseImpl(String string) {
        String[] stringArray = string.split(",");
        ArrayList<Module> arrayList = new ArrayList<Module>(stringArray.length);
        try {
            for (String string2 : stringArray) {
                Module module = Modules.get().get(string2.trim());
                if (module == null) continue;
                arrayList.add(module);
                if (!false) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public List<Module> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("modules", 8);
        for (class_2520 class_25202 : class_24992) {
            Module module = Modules.get().get(class_25202.method_10714());
            if (module == null) continue;
            ((List)this.get()).add(module);
        }
        this.changed();
        return (List)this.get();
    }

    public ModuleListSetting(String string, String string2, List<Module> list, Consumer<List<Module>> consumer, Consumer<Setting<List<Module>>> consumer2, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.value = new ArrayList<Module>(list);
    }

    @Override
    public void reset(boolean bl) {
        this.value = new ArrayList((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((List)object);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public static class Builder {
        private IVisible visible;
        private Consumer<List<Module>> onChanged;
        private String description = "";
        private List<Module> defaultValue;
        private String name = "undefined";
        private Consumer<Setting<List<Module>>> onModuleActivated;

        public Builder defaultValue(List<Module> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<List<Module>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<List<Module>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public ModuleListSetting build() {
            return new ModuleListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }
    }
}

