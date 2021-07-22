/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1887
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
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
    public class_2487 toTag() {
        EnchListSetting llIllIIIIIIl;
        class_2487 llIllIIIIIII = llIllIIIIIIl.saveGeneral();
        class_2499 llIlIlllllll = new class_2499();
        for (class_1887 llIllIIIIIlI : (List)llIllIIIIIIl.get()) {
            try {
                llIlIlllllll.add((Object)class_2519.method_23256((String)class_2378.field_11160.method_10221((Object)llIllIIIIIlI).toString()));
            }
            catch (NullPointerException llIlIllllIIl) {}
        }
        llIllIIIIIII.method_10566("value", (class_2520)llIlIlllllll);
        return llIllIIIIIII;
    }

    @Override
    public void reset(boolean llIllIlIIlII) {
        EnchListSetting llIllIlIIIll;
        llIllIlIIIll.value = new ArrayList((Collection)llIllIlIIIll.defaultValue);
        if (llIllIlIIlII) {
            llIllIlIIIll.changed();
        }
    }

    @Override
    public List<class_1887> fromTag(class_2487 llIlIlllIIIl) {
        EnchListSetting llIlIllIllll;
        ((List)llIlIllIllll.get()).clear();
        class_2499 llIlIlllIIII = llIlIlllIIIl.method_10554("value", 8);
        for (class_2520 llIlIlllIIll : llIlIlllIIII) {
            ((List)llIlIllIllll.get()).add((class_1887)class_2378.field_11160.method_10223(new class_2960(llIlIlllIIll.method_10714())));
        }
        llIlIllIllll.changed();
        return (List)llIlIllIllll.get();
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11160.method_10235();
    }

    @Override
    protected boolean isValueValid(List<class_1887> llIllIIIlIlI) {
        return true;
    }

    @Override
    protected List<class_1887> parseImpl(String llIllIIlIllI) {
        String[] llIllIIlIlIl = llIllIIlIllI.split(",");
        ArrayList<class_1887> llIllIIlIlII = new ArrayList<class_1887>(llIllIIlIlIl.length);
        try {
            for (String llIllIIllIII : llIllIIlIlIl) {
                class_1887 llIllIIllIIl = (class_1887)EnchListSetting.parseId(class_2378.field_11160, llIllIIllIII);
                if (llIllIIllIIl == null) continue;
                llIllIIlIlII.add(llIllIIllIIl);
            }
        }
        catch (Exception llIllIIlIIII) {
            // empty catch block
        }
        return llIllIIlIlII;
    }

    public EnchListSetting(String llIllIllIlII, String llIllIlIllII, List<class_1887> llIllIllIIlI, Consumer<List<class_1887>> llIllIlIlIlI, Consumer<Setting<List<class_1887>>> llIllIllIIII, IVisible llIllIlIlIII) {
        super(llIllIllIlII, llIllIlIllII, llIllIllIIlI, llIllIlIlIlI, llIllIllIIII, llIllIlIlIII);
        EnchListSetting llIllIllIlIl;
        llIllIllIlIl.value = new ArrayList<class_1887>(llIllIllIIlI);
    }

    public static class Builder {
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String name;
        private /* synthetic */ List<class_1887> defaultValue;
        private /* synthetic */ Consumer<List<class_1887>> onChanged;
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<Setting<List<class_1887>>> onModuleActivated;

        public Builder name(String llIIIIlIIllIl) {
            Builder llIIIIlIIlllI;
            llIIIIlIIlllI.name = llIIIIlIIllIl;
            return llIIIIlIIlllI;
        }

        public Builder onChanged(Consumer<List<class_1887>> llIIIIIlllIIl) {
            Builder llIIIIIlllIlI;
            llIIIIIlllIlI.onChanged = llIIIIIlllIIl;
            return llIIIIIlllIlI;
        }

        public Builder defaultValue(List<class_1887> llIIIIlIIIIIl) {
            Builder llIIIIlIIIIII;
            llIIIIlIIIIII.defaultValue = llIIIIlIIIIIl;
            return llIIIIlIIIIII;
        }

        public Builder() {
            Builder llIIIIlIlIIIl;
            llIIIIlIlIIIl.name = "undefined";
            llIIIIlIlIIIl.description = "";
        }

        public Builder visible(IVisible llIIIIIlIllll) {
            Builder llIIIIIllIIII;
            llIIIIIllIIII.visible = llIIIIIlIllll;
            return llIIIIIllIIII;
        }

        public EnchListSetting build() {
            Builder llIIIIIlIlIll;
            return new EnchListSetting(llIIIIIlIlIll.name, llIIIIIlIlIll.description, llIIIIIlIlIll.defaultValue, llIIIIIlIlIll.onChanged, llIIIIIlIlIll.onModuleActivated, llIIIIIlIlIll.visible);
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_1887>>> llIIIIIllIIll) {
            Builder llIIIIIllIlII;
            llIIIIIllIlII.onModuleActivated = llIIIIIllIIll;
            return llIIIIIllIlII;
        }

        public Builder description(String llIIIIlIIIlIl) {
            Builder llIIIIlIIlIII;
            llIIIIlIIlIII.description = llIIIIlIIIlIl;
            return llIIIIlIIlIII;
        }
    }
}

