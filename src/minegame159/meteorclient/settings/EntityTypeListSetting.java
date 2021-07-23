/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.entity.EntityUtils;
import net.minecraft.class_1299;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class EntityTypeListSetting
extends Setting<Object2BooleanMap<class_1299<?>>> {
    public final boolean onlyAttackable;

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11145.method_10235();
    }

    public EntityTypeListSetting(String string, String string2, Object2BooleanMap<class_1299<?>> object2BooleanMap, Consumer<Object2BooleanMap<class_1299<?>>> consumer, Consumer<Setting<Object2BooleanMap<class_1299<?>>>> consumer2, IVisible iVisible, boolean bl) {
        super(string, string2, object2BooleanMap, consumer, consumer2, iVisible);
        this.onlyAttackable = bl;
        this.value = new Object2BooleanOpenHashMap(object2BooleanMap);
    }

    @Override
    protected boolean isValueValid(Object2BooleanMap<class_1299<?>> object2BooleanMap) {
        return true;
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_1299 class_12992 : ((Object2BooleanMap)this.get()).keySet()) {
            if (!((Object2BooleanMap)this.get()).getBoolean((Object)class_12992)) continue;
            class_24992.add((Object)class_2519.method_23256((String)class_2378.field_11145.method_10221((Object)class_12992).toString()));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Object2BooleanMap)object);
    }

    @Override
    public Object2BooleanMap<class_1299<?>> fromTag(class_2487 class_24872) {
        ((Object2BooleanMap)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            class_1299 class_12992 = (class_1299)class_2378.field_11145.method_10223(new class_2960(class_25202.method_10714()));
            if (this.onlyAttackable && !EntityUtils.isAttackable(class_12992)) continue;
            ((Object2BooleanMap)this.get()).put((Object)class_12992, true);
        }
        this.changed();
        return (Object2BooleanMap)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public void reset(boolean bl) {
        this.value = new Object2BooleanOpenHashMap((Object2BooleanMap)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    protected Object2BooleanMap<class_1299<?>> parseImpl(String string) {
        String[] stringArray = string.split(",");
        Object2BooleanOpenHashMap object2BooleanOpenHashMap = new Object2BooleanOpenHashMap(stringArray.length);
        try {
            for (String string2 : stringArray) {
                class_1299 class_12992 = (class_1299)EntityTypeListSetting.parseId(class_2378.field_11145, string2);
                if (class_12992 == null) continue;
                object2BooleanOpenHashMap.put((Object)class_12992, true);
                if (4 >= 2) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return object2BooleanOpenHashMap;
    }

    public static class Builder {
        private String description = "";
        private Object2BooleanMap<class_1299<?>> defaultValue;
        private Consumer<Object2BooleanMap<class_1299<?>>> onChanged;
        private Consumer<Setting<Object2BooleanMap<class_1299<?>>>> onModuleActivated;
        private IVisible visible;
        private boolean onlyAttackable = false;
        private String name = "undefined";

        public Builder onModuleActivated(Consumer<Setting<Object2BooleanMap<class_1299<?>>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder onChanged(Consumer<Object2BooleanMap<class_1299<?>>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder onlyAttackable() {
            this.onlyAttackable = true;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder defaultValue(Object2BooleanMap<class_1299<?>> object2BooleanMap) {
            this.defaultValue = object2BooleanMap;
            return this;
        }

        public EntityTypeListSetting build() {
            return new EntityTypeListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.onlyAttackable);
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }
    }
}

