/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IBlockData;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.IGetter;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class BlockDataSetting<T extends ICopyable<T> & ISerializable<T> & IBlockData<T>>
extends Setting<Map<class_2248, T>> {
    public final IGetter<T> defaultData;

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Map)object);
    }

    @Override
    public Map<class_2248, T> fromTag(class_2487 class_24872) {
        ((Map)this.get()).clear();
        class_2487 class_24873 = class_24872.method_10562("value");
        for (String string : class_24873.method_10541()) {
            ((Map)this.get()).put((class_2248)class_2378.field_11146.method_10223(new class_2960(string)), (ICopyable)((ISerializable)((ICopyable)this.defaultData.get()).copy()).fromTag(class_24873.method_10562(string)));
        }
        return (Map)this.get();
    }

    @Override
    protected boolean isValueValid(Map<class_2248, T> map) {
        return true;
    }

    @Override
    public void reset(boolean bl) {
        this.value = new HashMap((Map)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    public BlockDataSetting(String string, String string2, Map<class_2248, T> map, Consumer<Map<class_2248, T>> consumer, Consumer<Setting<Map<class_2248, T>>> consumer2, IGetter<T> iGetter, IVisible iVisible) {
        super(string, string2, map, consumer, consumer2, iVisible);
        this.defaultData = iGetter;
    }

    @Override
    protected Map<class_2248, T> parseImpl(String string) {
        return new HashMap(0);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2487 class_24873 = new class_2487();
        for (class_2248 class_22482 : ((Map)this.get()).keySet()) {
            class_24873.method_10566(class_2378.field_11146.method_10221((Object)class_22482).toString(), (class_2520)((ISerializable)((Object)((ICopyable)((Map)this.get()).get(class_22482)))).toTag());
        }
        class_24872.method_10566("value", (class_2520)class_24873);
        return class_24872;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public static class Builder<T extends ICopyable<T> & ISerializable<T> & IBlockData<T>> {
        private String description = "";
        private String name = "undefined";
        private Map<class_2248, T> defaultValue;
        private IGetter<T> defaultData;
        private Consumer<Setting<Map<class_2248, T>>> onModuleActivated;
        private Consumer<Map<class_2248, T>> onChanged;
        private IVisible visible;

        public Builder<T> onModuleActivated(Consumer<Setting<Map<class_2248, T>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder<T> defaultData(IGetter<T> iGetter) {
            this.defaultData = iGetter;
            return this;
        }

        public BlockDataSetting<T> build() {
            return new BlockDataSetting<T>(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.defaultData, this.visible);
        }

        public Builder<T> name(String string) {
            this.name = string;
            return this;
        }

        public Builder<T> visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder<T> defaultValue(Map<class_2248, T> map) {
            this.defaultValue = map;
            return this;
        }

        public Builder<T> onChanged(Consumer<Map<class_2248, T>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder<T> description(String string) {
            this.description = string;
            return this;
        }
    }
}

