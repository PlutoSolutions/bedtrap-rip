/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2596
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
    private static /* synthetic */ List<String> suggestions;

    @Override
    protected Set<Class<? extends class_2596<?>>> parseImpl(String llllllllllllllllllIlIIlIIIIIIlIl) {
        String[] llllllllllllllllllIlIIlIIIIIIlII = llllllllllllllllllIlIIlIIIIIIlIl.split(",");
        ObjectOpenHashSet llllllllllllllllllIlIIlIIIIIIIll = new ObjectOpenHashSet(llllllllllllllllllIlIIlIIIIIIlII.length);
        try {
            for (String llllllllllllllllllIlIIlIIIIIIlll : llllllllllllllllllIlIIlIIIIIIlII) {
                Class<? extends class_2596<?>> llllllllllllllllllIlIIlIIIIIlIII = PacketUtils.getPacket(llllllllllllllllllIlIIlIIIIIIlll.trim());
                if (llllllllllllllllllIlIIlIIIIIlIII == null) continue;
                llllllllllllllllllIlIIlIIIIIIIll.add(llllllllllllllllllIlIIlIIIIIlIII);
            }
        }
        catch (Exception llllllllllllllllllIlIIIlllllllll) {
            // empty catch block
        }
        return llllllllllllllllllIlIIlIIIIIIIll;
    }

    @Override
    protected boolean isValueValid(Set<Class<? extends class_2596<?>>> llllllllllllllllllIlIIIllllllIIl) {
        return true;
    }

    @Override
    public class_2487 toTag() {
        PacketBoolSetting llllllllllllllllllIlIIIllllIlIll;
        class_2487 llllllllllllllllllIlIIIllllIlIlI = llllllllllllllllllIlIIIllllIlIll.saveGeneral();
        class_2499 llllllllllllllllllIlIIIllllIlIIl = new class_2499();
        for (Class llllllllllllllllllIlIIIllllIllII : (Set)llllllllllllllllllIlIIIllllIlIll.get()) {
            llllllllllllllllllIlIIIllllIlIIl.add((Object)class_2519.method_23256((String)PacketUtils.getName(llllllllllllllllllIlIIIllllIllII)));
        }
        llllllllllllllllllIlIIIllllIlIlI.method_10566("value", (class_2520)llllllllllllllllllIlIIIllllIlIIl);
        return llllllllllllllllllIlIIIllllIlIlI;
    }

    @Override
    public List<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<String>(PacketUtils.getC2SPackets().size() + PacketUtils.getS2CPackets().size());
            for (Class<? extends class_2596<?>> class_ : PacketUtils.getC2SPackets()) {
                suggestions.add(PacketUtils.getName(class_));
            }
            for (Class<? extends class_2596<?>> class_ : PacketUtils.getS2CPackets()) {
                suggestions.add(PacketUtils.getName(class_));
            }
        }
        return suggestions;
    }

    @Override
    public Set<Class<? extends class_2596<?>>> fromTag(class_2487 llllllllllllllllllIlIIIlllIlIlll) {
        PacketBoolSetting llllllllllllllllllIlIIIlllIllIll;
        ((Set)llllllllllllllllllIlIIIlllIllIll.get()).clear();
        class_2520 llllllllllllllllllIlIIIlllIllIIl = llllllllllllllllllIlIIIlllIlIlll.method_10580("value");
        if (llllllllllllllllllIlIIIlllIllIIl instanceof class_2499) {
            for (class_2520 llllllllllllllllllIlIIIlllIlllII : (class_2499)llllllllllllllllllIlIIIlllIllIIl) {
                Class<? extends class_2596<?>> llllllllllllllllllIlIIIlllIlllIl = PacketUtils.getPacket(llllllllllllllllllIlIIIlllIlllII.method_10714());
                if (llllllllllllllllllIlIIIlllIlllIl == null) continue;
                ((Set)llllllllllllllllllIlIIIlllIllIll.get()).add(llllllllllllllllllIlIIIlllIlllIl);
            }
        }
        llllllllllllllllllIlIIIlllIllIll.changed();
        return (Set)llllllllllllllllllIlIIIlllIllIll.get();
    }

    @Override
    public void reset(boolean llllllllllllllllllIlIIlIIIIlIIll) {
        PacketBoolSetting llllllllllllllllllIlIIlIIIIlIIlI;
        llllllllllllllllllIlIIlIIIIlIIlI.value = new ObjectOpenHashSet((Collection)llllllllllllllllllIlIIlIIIIlIIlI.defaultValue);
        if (llllllllllllllllllIlIIlIIIIlIIll) {
            llllllllllllllllllIlIIlIIIIlIIlI.changed();
        }
    }

    public PacketBoolSetting(String llllllllllllllllllIlIIlIIIlIIIll, String llllllllllllllllllIlIIlIIIlIIIlI, Set<Class<? extends class_2596<?>>> llllllllllllllllllIlIIlIIIIllIlI, Consumer<Set<Class<? extends class_2596<?>>>> llllllllllllllllllIlIIlIIIIllIIl, Consumer<Setting<Set<Class<? extends class_2596<?>>>>> llllllllllllllllllIlIIlIIIIllIII, IVisible llllllllllllllllllIlIIlIIIIllllI) {
        super(llllllllllllllllllIlIIlIIIlIIIll, llllllllllllllllllIlIIlIIIlIIIlI, llllllllllllllllllIlIIlIIIIllIlI, llllllllllllllllllIlIIlIIIIllIIl, llllllllllllllllllIlIIlIIIIllIII, llllllllllllllllllIlIIlIIIIllllI);
        PacketBoolSetting llllllllllllllllllIlIIlIIIIlllIl;
        llllllllllllllllllIlIIlIIIIlllIl.value = new ObjectOpenHashSet(llllllllllllllllllIlIIlIIIIllIlI);
    }

    public static class Builder {
        private /* synthetic */ Consumer<Set<Class<? extends class_2596<?>>>> onChanged;
        private /* synthetic */ Set<Class<? extends class_2596<?>>> defaultValue;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<Setting<Set<Class<? extends class_2596<?>>>>> onModuleActivated;
        private /* synthetic */ String description;
        private /* synthetic */ IVisible visible;

        public Builder() {
            Builder lIIllIlIlIlIlII;
            lIIllIlIlIlIlII.name = "undefined";
            lIIllIlIlIlIlII.description = "";
        }

        public Builder name(String lIIllIlIlIIllll) {
            Builder lIIllIlIlIIlllI;
            lIIllIlIlIIlllI.name = lIIllIlIlIIllll;
            return lIIllIlIlIIlllI;
        }

        public Builder description(String lIIllIlIlIIIlll) {
            Builder lIIllIlIlIIlIII;
            lIIllIlIlIIlIII.description = lIIllIlIlIIIlll;
            return lIIllIlIlIIlIII;
        }

        public Builder defaultValue(Set<Class<? extends class_2596<?>>> lIIllIlIlIIIIIl) {
            Builder lIIllIlIlIIIlII;
            lIIllIlIlIIIlII.defaultValue = lIIllIlIlIIIIIl;
            return lIIllIlIlIIIlII;
        }

        public Builder onChanged(Consumer<Set<Class<? extends class_2596<?>>>> lIIllIlIIllllIl) {
            Builder lIIllIlIIlllllI;
            lIIllIlIIlllllI.onChanged = lIIllIlIIllllIl;
            return lIIllIlIIlllllI;
        }

        public PacketBoolSetting build() {
            Builder lIIllIlIIlIllIl;
            return new PacketBoolSetting(lIIllIlIIlIllIl.name, lIIllIlIIlIllIl.description, lIIllIlIIlIllIl.defaultValue, lIIllIlIIlIllIl.onChanged, lIIllIlIIlIllIl.onModuleActivated, lIIllIlIIlIllIl.visible);
        }

        public Builder visible(IVisible lIIllIlIIllIIIl) {
            Builder lIIllIlIIllIIlI;
            lIIllIlIIllIIlI.visible = lIIllIlIIllIIIl;
            return lIIllIlIIllIIlI;
        }

        public Builder onModuleActivated(Consumer<Setting<Set<Class<? extends class_2596<?>>>>> lIIllIlIIllIlll) {
            Builder lIIllIlIIllIllI;
            lIIllIlIIllIllI.onModuleActivated = lIIllIlIIllIlll;
            return lIIllIlIIllIllI;
        }
    }
}

