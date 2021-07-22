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
    public static final /* synthetic */ class_2378<class_2591<?>> REGISTRY;
    public static final /* synthetic */ class_2591<?>[] STORAGE_BLOCKS;

    @Override
    protected List<class_2591<?>> parseImpl(String llIlIlIllllIlII) {
        String[] llIlIlIllllIIll = llIlIlIllllIlII.split(",");
        ArrayList llIlIlIllllIIlI = new ArrayList(llIlIlIllllIIll.length);
        try {
            for (String llIlIlIllllIllI : llIlIlIllllIIll) {
                class_2591 llIlIlIllllIlll = (class_2591)StorageBlockListSetting.parseId(class_2378.field_11137, llIlIlIllllIllI);
                if (llIlIlIllllIlll == null) continue;
                llIlIlIllllIIlI.add(llIlIlIllllIlll);
            }
        }
        catch (Exception llIlIlIlllIlllI) {
            // empty catch block
        }
        return llIlIlIllllIIlI;
    }

    public StorageBlockListSetting(String llIlIllIIIIlIll, String llIlIllIIIlIIIl, List<class_2591<?>> llIlIllIIIIlIIl, Consumer<List<class_2591<?>>> llIlIllIIIIllll, Consumer<Setting<List<class_2591<?>>>> llIlIllIIIIIlll, IVisible llIlIllIIIIIllI) {
        super(llIlIllIIIIlIll, llIlIllIIIlIIIl, llIlIllIIIIlIIl, llIlIllIIIIllll, llIlIllIIIIIlll, llIlIllIIIIIllI);
        StorageBlockListSetting llIlIllIIIlIIll;
        llIlIllIIIlIIll.value = new ArrayList(llIlIllIIIIlIIl);
    }

    @Override
    public List<class_2591<?>> fromTag(class_2487 llIlIlIllIIlIIl) {
        StorageBlockListSetting llIlIlIllIIllIl;
        ((List)llIlIlIllIIllIl.get()).clear();
        class_2499 llIlIlIllIIlIll = llIlIlIllIIlIIl.method_10554("value", 8);
        for (class_2520 llIlIlIllIIlllI : llIlIlIllIIlIll) {
            class_2591 llIlIlIllIIllll = (class_2591)class_2378.field_11137.method_10223(new class_2960(llIlIlIllIIlllI.method_10714()));
            if (llIlIlIllIIllll == null) continue;
            ((List)llIlIlIllIIllIl.get()).add(llIlIlIllIIllll);
        }
        llIlIlIllIIllIl.changed();
        return (List)llIlIlIllIIllIl.get();
    }

    @Override
    public class_2487 toTag() {
        StorageBlockListSetting llIlIlIllIllllI;
        class_2487 llIlIlIllIlllIl = llIlIlIllIllllI.saveGeneral();
        class_2499 llIlIlIllIlllII = new class_2499();
        for (class_2591 llIlIlIllIlllll : (List)llIlIlIllIllllI.get()) {
            class_2960 llIlIlIlllIIIII = class_2378.field_11137.method_10221((Object)llIlIlIllIlllll);
            if (llIlIlIlllIIIII == null) continue;
            llIlIlIllIlllII.add((Object)class_2519.method_23256((String)llIlIlIlllIIIII.toString()));
        }
        llIlIlIllIlllIl.method_10566("value", (class_2520)llIlIlIllIlllII);
        return llIlIlIllIlllIl;
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
    public void reset(boolean llIlIllIIIIIIlI) {
        StorageBlockListSetting llIlIllIIIIIIll;
        llIlIllIIIIIIll.value = new ArrayList((Collection)llIlIllIIIIIIll.defaultValue);
        if (llIlIllIIIIIIlI) {
            llIlIllIIIIIIll.changed();
        }
    }

    @Override
    protected boolean isValueValid(List<class_2591<?>> llIlIlIlllIlIII) {
        return true;
    }

    private static class SRegistry
    extends class_2378<class_2591<?>> {
        public Set<class_2960> method_10235() {
            return null;
        }

        @NotNull
        public Iterator<class_2591<?>> iterator() {
            return new ObjectArrayIterator((Object[])STORAGE_BLOCKS);
        }

        public SRegistry() {
            super(class_5321.method_29180((class_2960)new class_2960("meteor-client", "storage-blocks")), Lifecycle.stable());
            SRegistry llllllllllllllllllIIIlIIIlIIIllI;
        }

        @Nullable
        public class_2960 getId(class_2591<?> llllllllllllllllllIIIlIIIlIIIIll) {
            return null;
        }

        public Set<Map.Entry<class_5321<class_2591<?>>, class_2591<?>>> method_29722() {
            return null;
        }

        public int getRawId(@Nullable class_2591<?> llllllllllllllllllIIIlIIIIllllll) {
            return 0;
        }

        public boolean method_10250(class_2960 llllllllllllllllllIIIlIIIIllIlII) {
            return false;
        }

        public Lifecycle method_31138() {
            return null;
        }

        @Nullable
        public class_2591<?> get(@Nullable class_2960 llllllllllllllllllIIIlIIIIlllIll) {
            return null;
        }

        @Nullable
        public class_2591<?> get(int llllllllllllllllllIIIlIIIIllIIlI) {
            return null;
        }

        @Nullable
        public class_2591<?> get(@Nullable class_5321<class_2591<?>> llllllllllllllllllIIIlIIIIllllIl) {
            return null;
        }

        protected Lifecycle getEntryLifecycle(class_2591<?> llllllllllllllllllIIIlIIIIlllIIl) {
            return null;
        }

        public Optional<class_5321<class_2591<?>>> getKey(class_2591<?> llllllllllllllllllIIIlIIIlIIIIIl) {
            return Optional.empty();
        }
    }

    public static class Builder {
        private /* synthetic */ Consumer<Setting<List<class_2591<?>>>> onModuleActivated;
        private /* synthetic */ Consumer<List<class_2591<?>>> onChanged;
        private /* synthetic */ String description;
        private /* synthetic */ List<class_2591<?>> defaultValue;
        private /* synthetic */ String name;
        private /* synthetic */ IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<List<class_2591<?>>>> llIIlIIlIlIllII) {
            Builder llIIlIIlIlIllIl;
            llIIlIIlIlIllIl.onModuleActivated = llIIlIIlIlIllII;
            return llIIlIIlIlIllIl;
        }

        public Builder name(String llIIlIIllIIIIlI) {
            Builder llIIlIIllIIIIll;
            llIIlIIllIIIIll.name = llIIlIIllIIIIlI;
            return llIIlIIllIIIIll;
        }

        public Builder defaultValue(List<class_2591<?>> llIIlIIlIllIllI) {
            Builder llIIlIIlIllIlll;
            llIIlIIlIllIlll.defaultValue = llIIlIIlIllIllI;
            return llIIlIIlIllIlll;
        }

        public Builder visible(IVisible llIIlIIlIlIIllI) {
            Builder llIIlIIlIlIIlIl;
            llIIlIIlIlIIlIl.visible = llIIlIIlIlIIllI;
            return llIIlIIlIlIIlIl;
        }

        public Builder() {
            Builder llIIlIIllIIlIIl;
            llIIlIIllIIlIIl.name = "undefined";
            llIIlIIllIIlIIl.description = "";
        }

        public Builder onChanged(Consumer<List<class_2591<?>>> llIIlIIlIllIIII) {
            Builder llIIlIIlIllIIll;
            llIIlIIlIllIIll.onChanged = llIIlIIlIllIIII;
            return llIIlIIlIllIIll;
        }

        public StorageBlockListSetting build() {
            Builder llIIlIIlIlIIIlI;
            return new StorageBlockListSetting(llIIlIIlIlIIIlI.name, llIIlIIlIlIIIlI.description, llIIlIIlIlIIIlI.defaultValue, llIIlIIlIlIIIlI.onChanged, llIIlIIlIlIIIlI.onModuleActivated, llIIlIIlIlIIIlI.visible);
        }

        public Builder description(String llIIlIIlIlllllI) {
            Builder llIIlIIlIllllIl;
            llIIlIIlIllllIl.description = llIIlIIlIlllllI;
            return llIIlIIlIllllIl;
        }
    }
}

