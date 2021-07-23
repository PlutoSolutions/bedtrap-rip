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
import net.minecraft.class_2378;
import net.minecraft.class_2396;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class ParticleTypeListSetting
extends Setting<List<class_2396<?>>> {
    private static List<class_2960> suggestions;

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<class_2960>(class_2378.field_11141.method_10235().size());
            for (class_2960 class_29602 : class_2378.field_11141.method_10235()) {
                class_2396 class_23962 = (class_2396)class_2378.field_11141.method_10223(class_29602);
                if (!(class_23962 instanceof class_2396)) continue;
                suggestions.add(class_29602);
            }
        }
        return suggestions;
    }

    @Override
    public List<class_2396<?>> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            ((List)this.get()).add((class_2396)class_2378.field_11141.method_10223(new class_2960(class_25202.method_10714())));
        }
        this.changed();
        return (List)this.get();
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((List)object);
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_2396 class_23962 : (List)this.get()) {
            class_24992.add((Object)class_2519.method_23256((String)class_2378.field_11141.method_10221((Object)class_23962).toString()));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    protected boolean isValueValid(List<class_2396<?>> list) {
        return true;
    }

    @Override
    public void reset(boolean bl) {
        this.value = new ArrayList((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    public ParticleTypeListSetting(String string, String string2, List<class_2396<?>> list, Consumer<List<class_2396<?>>> consumer, Consumer<Setting<List<class_2396<?>>>> consumer2, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.value = new ArrayList(list);
    }

    @Override
    protected List<class_2396<?>> parseImpl(String string) {
        String[] stringArray = string.split(",");
        ArrayList arrayList = new ArrayList(stringArray.length);
        try {
            for (String string2 : stringArray) {
                class_2396 class_23962 = (class_2396)ParticleTypeListSetting.parseId(class_2378.field_11141, string2);
                if (!(class_23962 instanceof class_2396)) continue;
                arrayList.add(class_23962);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    public static class Builder {
        private String name = "undefined";
        private String description = "";
        private Consumer<Setting<List<class_2396<?>>>> onModuleActivated;
        private Consumer<List<class_2396<?>>> onChanged;
        private IVisible visible;
        private List<class_2396<?>> defaultValue;

        public Builder defaultValue(List<class_2396<?>> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_2396<?>>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public ParticleTypeListSetting build() {
            return new ParticleTypeListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder onChanged(Consumer<List<class_2396<?>>> consumer) {
            this.onChanged = consumer;
            return this;
        }
    }
}

