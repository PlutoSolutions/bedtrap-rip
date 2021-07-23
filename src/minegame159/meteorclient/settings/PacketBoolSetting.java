/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.network.PacketUtils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2596;

public class PacketBoolSetting
extends Setting<Set<Class<? extends class_2596<?>>>> {
    private static List<String> suggestions;

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    protected Set<Class<? extends class_2596<?>>> parseImpl(String string) {
        String[] stringArray = string.split(",");
        ObjectOpenHashSet objectOpenHashSet = new ObjectOpenHashSet(stringArray.length);
        try {
            for (String string2 : stringArray) {
                Class<? extends class_2596<?>> clazz = PacketUtils.getPacket(string2.trim());
                if (clazz == null) continue;
                objectOpenHashSet.add(clazz);
                if (-4 <= 0) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return objectOpenHashSet;
    }

    @Override
    protected boolean isValueValid(Set<Class<? extends class_2596<?>>> set) {
        return true;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (Class clazz : (Set)this.get()) {
            class_24992.add((Object)class_2519.method_23256((String)PacketUtils.getName(clazz)));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Set)object);
    }

    @Override
    public List<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<String>(PacketUtils.getC2SPackets().size() + PacketUtils.getS2CPackets().size());
            for (Class<? extends class_2596<?>> clazz : PacketUtils.getC2SPackets()) {
                suggestions.add(PacketUtils.getName(clazz));
            }
            for (Class<? extends class_2596<?>> clazz : PacketUtils.getS2CPackets()) {
                suggestions.add(PacketUtils.getName(clazz));
            }
        }
        return suggestions;
    }

    @Override
    public Set<Class<? extends class_2596<?>>> fromTag(class_2487 class_24872) {
        ((Set)this.get()).clear();
        class_2520 class_25202 = class_24872.method_10580("value");
        if (class_25202 instanceof class_2499) {
            for (class_2520 class_25203 : (class_2499)class_25202) {
                Class<? extends class_2596<?>> clazz = PacketUtils.getPacket(class_25203.method_10714());
                if (clazz == null) continue;
                ((Set)this.get()).add(clazz);
            }
        }
        this.changed();
        return (Set)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public void reset(boolean bl) {
        this.value = new ObjectOpenHashSet((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    public PacketBoolSetting(String string, String string2, Set<Class<? extends class_2596<?>>> set, Consumer<Set<Class<? extends class_2596<?>>>> consumer, Consumer<Setting<Set<Class<? extends class_2596<?>>>>> consumer2, IVisible iVisible) {
        super(string, string2, set, consumer, consumer2, iVisible);
        this.value = new ObjectOpenHashSet(set);
    }

    public static class Builder {
        private Consumer<Set<Class<? extends class_2596<?>>>> onChanged;
        private Set<Class<? extends class_2596<?>>> defaultValue;
        private String name = "undefined";
        private Consumer<Setting<Set<Class<? extends class_2596<?>>>>> onModuleActivated;
        private String description = "";
        private IVisible visible;

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder defaultValue(Set<Class<? extends class_2596<?>>> set) {
            this.defaultValue = set;
            return this;
        }

        public Builder onChanged(Consumer<Set<Class<? extends class_2596<?>>>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public PacketBoolSetting build() {
            return new PacketBoolSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<Set<Class<? extends class_2596<?>>>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }
    }
}

