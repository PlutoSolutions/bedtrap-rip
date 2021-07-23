/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1291;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class StatusEffectSetting
extends Setting<Object2IntMap<class_1291>> {
    @Override
    protected boolean isValueValid(Object2IntMap<class_1291> object2IntMap) {
        return true;
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public void reset(boolean bl) {
        this.value = new Object2IntArrayMap((Object2IntMap)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    public Object2IntMap<class_1291> fromTag(class_2487 class_24872) {
        ((Object2IntMap)this.get()).clear();
        class_2487 class_24873 = class_24872.method_10562("value");
        for (String string : class_24873.method_10541()) {
            class_1291 class_12912 = (class_1291)class_2378.field_11159.method_10223(new class_2960(string));
            if (class_12912 == null) continue;
            ((Object2IntMap)this.get()).put((Object)class_12912, class_24873.method_10550(string));
        }
        this.changed();
        return (Object2IntMap)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    protected Object2IntMap<class_1291> parseImpl(String string) {
        String[] stringArray = string.split(",");
        Object2IntMap<class_1291> object2IntMap = Utils.createStatusEffectMap();
        try {
            for (String string2 : stringArray) {
                String[] stringArray2 = string2.split(" ");
                class_1291 class_12912 = (class_1291)StatusEffectSetting.parseId(class_2378.field_11159, stringArray2[0]);
                int n = Integer.parseInt(stringArray2[1]);
                object2IntMap.put((Object)class_12912, n);
                if (2 <= 2) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return object2IntMap;
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Object2IntMap<class_1291>)((Object2IntMap)object));
    }

    public StatusEffectSetting(String string, String string2, Object2IntMap<class_1291> object2IntMap, Consumer<Object2IntMap<class_1291>> consumer, Consumer<Setting<Object2IntMap<class_1291>>> consumer2, IVisible iVisible) {
        super(string, string2, object2IntMap, consumer, consumer2, iVisible);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2487 class_24873 = new class_2487();
        for (class_1291 class_12912 : ((Object2IntMap)this.get()).keySet()) {
            class_2960 class_29602 = class_2378.field_11159.method_10221((Object)class_12912);
            if (class_29602 == null) continue;
            class_24873.method_10569(class_29602.toString(), ((Object2IntMap)this.get()).getInt((Object)class_12912));
        }
        class_24872.method_10566("value", (class_2520)class_24873);
        return class_24872;
    }

    public static class Builder {
        private String description = "";
        private Consumer<Setting<Object2IntMap<class_1291>>> onModuleActivated;
        private String name = "undefined";
        private Consumer<Object2IntMap<class_1291>> onChanged;
        private IVisible visible;
        private Object2IntMap<class_1291> defaultValue;

        public StatusEffectSetting build() {
            return new StatusEffectSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder onChanged(Consumer<Object2IntMap<class_1291>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<Object2IntMap<class_1291>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder defaultValue(Object2IntMap<class_1291> object2IntMap) {
            this.defaultValue = object2IntMap;
            return this;
        }
    }
}

