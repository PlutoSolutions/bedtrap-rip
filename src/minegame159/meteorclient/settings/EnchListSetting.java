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
import net.minecraft.class_1887;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class EnchListSetting
extends Setting<List<class_1887>> {
    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((List)object);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_1887 class_18872 : (List)this.get()) {
            try {
                class_24992.add((Object)class_2519.method_23256((String)class_2378.field_11160.method_10221((Object)class_18872).toString()));
            }
            catch (NullPointerException nullPointerException) {}
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    public void reset(boolean bl) {
        this.value = new ArrayList((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    public List<class_1887> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            ((List)this.get()).add((class_1887)class_2378.field_11160.method_10223(new class_2960(class_25202.method_10714())));
        }
        this.changed();
        return (List)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11160.method_10235();
    }

    @Override
    protected boolean isValueValid(List<class_1887> list) {
        return true;
    }

    @Override
    protected List<class_1887> parseImpl(String string) {
        String[] stringArray = string.split(",");
        ArrayList<class_1887> arrayList = new ArrayList<class_1887>(stringArray.length);
        try {
            for (String string2 : stringArray) {
                class_1887 class_18872 = (class_1887)EnchListSetting.parseId(class_2378.field_11160, string2);
                if (class_18872 == null) continue;
                arrayList.add(class_18872);
                if (null == null) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    public EnchListSetting(String string, String string2, List<class_1887> list, Consumer<List<class_1887>> consumer, Consumer<Setting<List<class_1887>>> consumer2, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.value = new ArrayList<class_1887>(list);
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    public static class Builder {
        private IVisible visible;
        private String name = "undefined";
        private List<class_1887> defaultValue;
        private Consumer<List<class_1887>> onChanged;
        private String description = "";
        private Consumer<Setting<List<class_1887>>> onModuleActivated;

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<List<class_1887>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder defaultValue(List<class_1887> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public EnchListSetting build() {
            return new EnchListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_1887>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }
    }
}

