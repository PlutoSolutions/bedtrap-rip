/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
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
import java.util.function.Predicate;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_1792;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class ItemListSetting
extends Setting<List<class_1792>> {
    public final /* synthetic */ Predicate<class_1792> filter;

    @Override
    protected boolean isValueValid(List<class_1792> llllllllllllllllllIIIIlllIIIIllI) {
        return true;
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11142.method_10235();
    }

    @Override
    public void reset(boolean llllllllllllllllllIIIIlllIIIlIlI) {
        ItemListSetting llllllllllllllllllIIIIlllIIIlIIl;
        llllllllllllllllllIIIIlllIIIlIIl.value = new ArrayList((Collection)llllllllllllllllllIIIIlllIIIlIIl.defaultValue);
        if (llllllllllllllllllIIIIlllIIIlIlI) {
            llllllllllllllllllIIIIlllIIIlIIl.changed();
        }
    }

    @Override
    public List<class_1792> fromTag(class_2487 llllllllllllllllllIIIIllIllIlIlI) {
        ItemListSetting llllllllllllllllllIIIIllIllIlIll;
        ((List)llllllllllllllllllIIIIllIllIlIll.get()).clear();
        class_2499 llllllllllllllllllIIIIllIllIllII = llllllllllllllllllIIIIllIllIlIlI.method_10554("value", 8);
        for (class_2520 llllllllllllllllllIIIIllIllIllll : llllllllllllllllllIIIIllIllIllII) {
            class_1792 llllllllllllllllllIIIIllIlllIIII = (class_1792)class_2378.field_11142.method_10223(new class_2960(llllllllllllllllllIIIIllIllIllll.method_10714()));
            if (llllllllllllllllllIIIIllIllIlIll.filter != null && !llllllllllllllllllIIIIllIllIlIll.filter.test(llllllllllllllllllIIIIllIlllIIII)) continue;
            ((List)llllllllllllllllllIIIIllIllIlIll.get()).add(llllllllllllllllllIIIIllIlllIIII);
        }
        llllllllllllllllllIIIIllIllIlIll.changed();
        return (List)llllllllllllllllllIIIIllIllIlIll.get();
    }

    @Override
    public class_2487 toTag() {
        ItemListSetting llllllllllllllllllIIIIllIllllIll;
        class_2487 llllllllllllllllllIIIIllIlllllIl = llllllllllllllllllIIIIllIllllIll.saveGeneral();
        class_2499 llllllllllllllllllIIIIllIlllllII = new class_2499();
        for (class_1792 llllllllllllllllllIIIIllIlllllll : (List)llllllllllllllllllIIIIllIllllIll.get()) {
            llllllllllllllllllIIIIllIlllllII.add((Object)class_2519.method_23256((String)class_2378.field_11142.method_10221((Object)llllllllllllllllllIIIIllIlllllll).toString()));
        }
        llllllllllllllllllIIIIllIlllllIl.method_10566("value", (class_2520)llllllllllllllllllIIIIllIlllllII);
        return llllllllllllllllllIIIIllIlllllIl;
    }

    public ItemListSetting(String llllllllllllllllllIIIIlllIlIllII, String llllllllllllllllllIIIIlllIllIIll, List<class_1792> llllllllllllllllllIIIIlllIllIIlI, Consumer<List<class_1792>> llllllllllllllllllIIIIlllIlIlIIl, Consumer<Setting<List<class_1792>>> llllllllllllllllllIIIIlllIllIIII, IVisible llllllllllllllllllIIIIlllIlIIlll, Predicate<class_1792> llllllllllllllllllIIIIlllIlIIllI) {
        super(llllllllllllllllllIIIIlllIlIllII, llllllllllllllllllIIIIlllIllIIll, llllllllllllllllllIIIIlllIllIIlI, llllllllllllllllllIIIIlllIlIlIIl, llllllllllllllllllIIIIlllIllIIII, llllllllllllllllllIIIIlllIlIIlll);
        ItemListSetting llllllllllllllllllIIIIlllIllIlIl;
        llllllllllllllllllIIIIlllIllIlIl.value = new ArrayList<class_1792>(llllllllllllllllllIIIIlllIllIIlI);
        llllllllllllllllllIIIIlllIllIlIl.filter = llllllllllllllllllIIIIlllIlIIllI;
    }

    @Override
    protected List<class_1792> parseImpl(String llllllllllllllllllIIIIlllIIllIIl) {
        String[] llllllllllllllllllIIIIlllIIllIII = llllllllllllllllllIIIIlllIIllIIl.split(",");
        ArrayList<class_1792> llllllllllllllllllIIIIlllIIlIlll = new ArrayList<class_1792>(llllllllllllllllllIIIIlllIIllIII.length);
        try {
            for (String llllllllllllllllllIIIIlllIIllIll : llllllllllllllllllIIIIlllIIllIII) {
                ItemListSetting llllllllllllllllllIIIIlllIIlIllI;
                class_1792 llllllllllllllllllIIIIlllIIlllII = (class_1792)ItemListSetting.parseId(class_2378.field_11142, llllllllllllllllllIIIIlllIIllIll);
                if (llllllllllllllllllIIIIlllIIlllII == null || llllllllllllllllllIIIIlllIIlIllI.filter != null && !llllllllllllllllllIIIIlllIIlIllI.filter.test(llllllllllllllllllIIIIlllIIlllII)) continue;
                llllllllllllllllllIIIIlllIIlIlll.add(llllllllllllllllllIIIIlllIIlllII);
            }
        }
        catch (Exception llllllllllllllllllIIIIlllIIlIIlI) {
            // empty catch block
        }
        return llllllllllllllllllIIIIlllIIlIlll;
    }

    public static class Builder {
        private /* synthetic */ String name;
        private /* synthetic */ List<class_1792> defaultValue;
        private /* synthetic */ Consumer<Setting<List<class_1792>>> onModuleActivated;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String description;
        private /* synthetic */ Predicate<class_1792> filter;
        private /* synthetic */ Consumer<List<class_1792>> onChanged;

        public Builder filter(Predicate<class_1792> lIllIIIIIIlIllI) {
            Builder lIllIIIIIIlIlll;
            lIllIIIIIIlIlll.filter = lIllIIIIIIlIllI;
            return lIllIIIIIIlIlll;
        }

        public Builder onChanged(Consumer<List<class_1792>> lIllIIIIIlIIllI) {
            Builder lIllIIIIIlIIlll;
            lIllIIIIIlIIlll.onChanged = lIllIIIIIlIIllI;
            return lIllIIIIIlIIlll;
        }

        public Builder defaultValue(List<class_1792> lIllIIIIIlIllII) {
            Builder lIllIIIIIlIllIl;
            lIllIIIIIlIllIl.defaultValue = lIllIIIIIlIllII;
            return lIllIIIIIlIllIl;
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_1792>>> lIllIIIIIlIIIII) {
            Builder lIllIIIIIlIIIll;
            lIllIIIIIlIIIll.onModuleActivated = lIllIIIIIlIIIII;
            return lIllIIIIIlIIIll;
        }

        public Builder() {
            Builder lIllIIIIIllllll;
            lIllIIIIIllllll.name = "undefined";
            lIllIIIIIllllll.description = "";
        }

        public Builder visible(IVisible lIllIIIIIIllIlI) {
            Builder lIllIIIIIIllIll;
            lIllIIIIIIllIll.visible = lIllIIIIIIllIlI;
            return lIllIIIIIIllIll;
        }

        public ItemListSetting build() {
            Builder lIllIIIIIIlIIIl;
            return new ItemListSetting(lIllIIIIIIlIIIl.name, lIllIIIIIIlIIIl.description, lIllIIIIIIlIIIl.defaultValue, lIllIIIIIIlIIIl.onChanged, lIllIIIIIIlIIIl.onModuleActivated, lIllIIIIIIlIIIl.visible, lIllIIIIIIlIIIl.filter);
        }

        public Builder description(String lIllIIIIIllIIlI) {
            Builder lIllIIIIIllIIll;
            lIllIIIIIllIIll.description = lIllIIIIIllIIlI;
            return lIllIIIIIllIIll;
        }

        public Builder name(String lIllIIIIIlllIlI) {
            Builder lIllIIIIIlllIll;
            lIllIIIIIlllIll.name = lIllIIIIIlllIlI;
            return lIllIIIIIlllIll;
        }
    }
}

