/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

public class GenericSetting<T extends ICopyable<T> & ISerializable<T>>
extends Setting<T> {
    @Override
    public void reset(boolean bl) {
        if (this.value == null) {
            this.value = ((ICopyable)this.defaultValue).copy();
        }
        ((ICopyable)this.value).set((ICopyable)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    protected boolean isValueValid(T t) {
        return true;
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    protected T parseImpl(String string) {
        return ((ICopyable)this.defaultValue).copy();
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((T)((ICopyable)object));
    }

    @Override
    public T fromTag(class_2487 class_24872) {
        ((ISerializable)((Object)((ICopyable)this.get()))).fromTag(class_24872.method_10562("value"));
        return (T)((ICopyable)this.get());
    }

    public GenericSetting(String string, String string2, T t, Consumer<T> consumer, Consumer<Setting<T>> consumer2, IVisible iVisible) {
        super(string, string2, t, consumer, consumer2, iVisible);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10566("value", (class_2520)((ISerializable)((Object)((ICopyable)this.get()))).toTag());
        return class_24872;
    }

    public static class Builder<T extends ICopyable<T> & ISerializable<T>> {
        private Consumer<Setting<T>> onModuleActivated;
        private String name = "undefined";
        private Consumer<T> onChanged;
        private T defaultValue;
        private IVisible visible;
        private String description = "";

        public Builder<T> name(String string) {
            this.name = string;
            return this;
        }

        public Builder<T> defaultValue(T t) {
            this.defaultValue = t;
            return this;
        }

        public Builder<T> description(String string) {
            this.description = string;
            return this;
        }

        public GenericSetting<T> build() {
            return new GenericSetting<T>(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder<T> onChanged(Consumer<T> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder<T> onModuleActivated(Consumer<Setting<T>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }
    }
}

