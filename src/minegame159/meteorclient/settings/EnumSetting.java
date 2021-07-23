/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2487;

public class EnumSetting<T extends Enum<?>>
extends Setting<T> {
    private final List<String> suggestions;
    private T[] values;

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10582("value", ((Enum)this.get()).toString());
        return class_24872;
    }

    public EnumSetting(String string, String string2, T t, Consumer<T> consumer, Consumer<Setting<T>> consumer2, IVisible iVisible) {
        super(string, string2, t, consumer, consumer2, iVisible);
        try {
            this.values = (Enum[])t.getClass().getMethod("values", new Class[0]).invoke(null, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
        this.suggestions = new ArrayList<String>(this.values.length);
        for (T t2 : this.values) {
            this.suggestions.add(((Enum)t2).toString());
            if (true) continue;
            throw null;
        }
    }

    @Override
    public List<String> getSuggestions() {
        return this.suggestions;
    }

    @Override
    public T fromTag(class_2487 class_24872) {
        this.parse(class_24872.method_10558("value"));
        return (T)((Enum)this.get());
    }

    @Override
    protected boolean isValueValid(T t) {
        return true;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((T)((Enum)object));
    }

    @Override
    protected T parseImpl(String string) {
        for (T t : this.values) {
            if (!string.equalsIgnoreCase(((Enum)t).toString())) continue;
            return t;
        }
        return null;
    }

    public static class Builder<T extends Enum<?>> {
        protected String name = "undefined";
        protected Consumer<Setting<T>> onModuleActivated;
        protected String description = "";
        protected IVisible visible;
        protected T defaultValue;
        protected Consumer<T> onChanged;

        public Builder<T> defaultValue(T t) {
            this.defaultValue = t;
            return this;
        }

        public Builder<T> name(String string) {
            this.name = string;
            return this;
        }

        public Builder<T> onModuleActivated(Consumer<Setting<T>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder<T> description(String string) {
            this.description = string;
            return this;
        }

        public Builder<T> onChanged(Consumer<T> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder<T> visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public EnumSetting<T> build() {
            return new EnumSetting<T>(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }
    }
}

