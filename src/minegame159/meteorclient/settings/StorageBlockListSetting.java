/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2591
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  org.apache.logging.log4j.core.util.ObjectArrayIterator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package minegame159.meteorclient.settings;

import com.mojang.serialization.Lifecycle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2591;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import org.apache.logging.log4j.core.util.ObjectArrayIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StorageBlockListSetting
extends Setting<List<class_2591<?>>> {
    public static final class_2378<class_2591<?>> REGISTRY;
    public static final class_2591<?>[] STORAGE_BLOCKS;

    @Override
    protected List<class_2591<?>> parseImpl(String string) {
        String[] arrstring = string.split(",");
        ArrayList arrayList = new ArrayList(arrstring.length);
        try {
            for (String string2 : arrstring) {
                class_2591 class_25912 = (class_2591)StorageBlockListSetting.parseId(class_2378.field_11137, string2);
                if (class_25912 == null) continue;
                arrayList.add(class_25912);
                if (-1 != 3) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public StorageBlockListSetting(String string, String string2, List<class_2591<?>> list, Consumer<List<class_2591<?>>> consumer, Consumer<Setting<List<class_2591<?>>>> consumer2, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.value = new ArrayList(list);
    }

    @Override
    public List<class_2591<?>> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            class_2591 class_25912 = (class_2591)class_2378.field_11137.method_10223(new class_2960(class_25202.method_10714()));
            if (class_25912 == null) continue;
            ((List)this.get()).add(class_25912);
        }
        this.changed();
        return (List)this.get();
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_2591 class_25912 : (List)this.get()) {
            class_2960 class_29602 = class_2378.field_11137.method_10221((Object)class_25912);
            if (class_29602 == null) continue;
            class_24992.add((Object)class_2519.method_23256((String)class_29602.toString()));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11137.method_10235();
    }

    static {
        STORAGE_BLOCKS = new class_2591[]{class_2591.field_11903, class_2591.field_11914, class_2591.field_11891, class_2591.field_11901, class_2591.field_11887, class_2591.field_11899, class_2591.field_11888, class_2591.field_11896, class_2591.field_16411, class_2591.field_16414, class_2591.field_16415};
        REGISTRY = new SRegistry();
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
    public void reset(boolean bl) {
        this.value = new ArrayList((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    protected boolean isValueValid(List<class_2591<?>> list) {
        return true;
    }

    private static class SRegistry
    extends class_2378<class_2591<?>> {
        public int method_10206(@Nullable Object object) {
            return this.getRawId((class_2591)object);
        }

        @Nullable
        public Object method_10223(@Nullable class_2960 class_29602) {
            return this.get(class_29602);
        }

        public Set<class_2960> method_10235() {
            return null;
        }

        @NotNull
        public Iterator<class_2591<?>> iterator() {
            return new ObjectArrayIterator((Object[])STORAGE_BLOCKS);
        }

        public SRegistry() {
            super(class_5321.method_29180((class_2960)new class_2960("meteor-client", "storage-blocks")), Lifecycle.stable());
        }

        @Nullable
        public class_2960 getId(class_2591<?> class_25912) {
            return null;
        }

        public Set<Map.Entry<class_5321<class_2591<?>>, class_2591<?>>> method_29722() {
            return null;
        }

        @Nullable
        public class_2960 method_10221(Object object) {
            return this.getId((class_2591)object);
        }

        @Nullable
        public Object method_29107(@Nullable class_5321 class_53212) {
            return this.get(class_53212);
        }

        public int getRawId(@Nullable class_2591<?> class_25912) {
            return 0;
        }

        public boolean method_10250(class_2960 class_29602) {
            return false;
        }

        public Optional method_29113(Object object) {
            return this.getKey((class_2591)object);
        }

        protected Lifecycle method_31139(Object object) {
            return this.getEntryLifecycle((class_2591)object);
        }

        public Lifecycle method_31138() {
            return null;
        }

        @Nullable
        public class_2591<?> get(@Nullable class_2960 class_29602) {
            return null;
        }

        @Nullable
        public Object method_10200(int n) {
            return this.get(n);
        }

        @Nullable
        public class_2591<?> get(int n) {
            return null;
        }

        @Nullable
        public class_2591<?> get(@Nullable class_5321<class_2591<?>> class_53212) {
            return null;
        }

        protected Lifecycle getEntryLifecycle(class_2591<?> class_25912) {
            return null;
        }

        public Optional<class_5321<class_2591<?>>> getKey(class_2591<?> class_25912) {
            return Optional.empty();
        }
    }

    public static class Builder {
        private Consumer<Setting<List<class_2591<?>>>> onModuleActivated;
        private Consumer<List<class_2591<?>>> onChanged;
        private String description = "";
        private List<class_2591<?>> defaultValue;
        private String name = "undefined";
        private IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<List<class_2591<?>>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder defaultValue(List<class_2591<?>> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder onChanged(Consumer<List<class_2591<?>>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public StorageBlockListSetting build() {
            return new StorageBlockListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }
    }
}

