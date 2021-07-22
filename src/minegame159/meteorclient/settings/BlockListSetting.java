/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
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
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class BlockListSetting
extends Setting<List<class_2248>> {
    public final /* synthetic */ Predicate<class_2248> filter;

    @Override
    public void reset(boolean lIIlIIllIlIll) {
        BlockListSetting lIIlIIllIlllI;
        lIIlIIllIlllI.value = new ArrayList((Collection)lIIlIIllIlllI.defaultValue);
        if (lIIlIIllIlIll) {
            lIIlIIllIlllI.changed();
        }
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11146.method_10235();
    }

    @Override
    public List<class_2248> fromTag(class_2487 lIIlIIIllIlIl) {
        BlockListSetting lIIlIIIlllIIl;
        ((List)lIIlIIIlllIIl.get()).clear();
        class_2499 lIIlIIIllIlll = lIIlIIIllIlIl.method_10554("value", 8);
        for (class_2520 lIIlIIIlllIlI : lIIlIIIllIlll) {
            class_2248 lIIlIIIlllIll = (class_2248)class_2378.field_11146.method_10223(new class_2960(lIIlIIIlllIlI.method_10714()));
            if (lIIlIIIlllIIl.filter != null && !lIIlIIIlllIIl.filter.test(lIIlIIIlllIll)) continue;
            ((List)lIIlIIIlllIIl.get()).add(lIIlIIIlllIll);
        }
        lIIlIIIlllIIl.changed();
        return (List)lIIlIIIlllIIl.get();
    }

    @Override
    protected List<class_2248> parseImpl(String lIIlIIlIllllI) {
        String[] lIIlIIlIlllIl = lIIlIIlIllllI.split(",");
        ArrayList<class_2248> lIIlIIlIlllII = new ArrayList<class_2248>(lIIlIIlIlllIl.length);
        try {
            for (String lIIlIIllIIIII : lIIlIIlIlllIl) {
                BlockListSetting lIIlIIlIllIll;
                class_2248 lIIlIIllIIIIl = (class_2248)BlockListSetting.parseId(class_2378.field_11146, lIIlIIllIIIII);
                if (lIIlIIllIIIIl == null || lIIlIIlIllIll.filter != null && !lIIlIIlIllIll.filter.test(lIIlIIllIIIIl)) continue;
                lIIlIIlIlllII.add(lIIlIIllIIIIl);
            }
        }
        catch (Exception lIIlIIlIlIlll) {
            // empty catch block
        }
        return lIIlIIlIlllII;
    }

    public BlockListSetting(String lIIlIIlllIlll, String lIIlIIllllllI, List<class_2248> lIIlIIlllllIl, Consumer<List<class_2248>> lIIlIIlllllII, Consumer<Setting<List<class_2248>>> lIIlIIlllIIll, Predicate<class_2248> lIIlIIlllIIlI, IVisible lIIlIIlllIIIl) {
        super(lIIlIIlllIlll, lIIlIIllllllI, lIIlIIlllllIl, lIIlIIlllllII, lIIlIIlllIIll, lIIlIIlllIIIl);
        BlockListSetting lIIlIIllllIII;
        lIIlIIllllIII.filter = lIIlIIlllIIlI;
        lIIlIIllllIII.value = new ArrayList<class_2248>(lIIlIIlllllIl);
    }

    @Override
    public class_2487 toTag() {
        BlockListSetting lIIlIIlIIlIIl;
        class_2487 lIIlIIlIIlIII = lIIlIIlIIlIIl.saveGeneral();
        class_2499 lIIlIIlIIIlll = new class_2499();
        for (class_2248 lIIlIIlIIlIlI : (List)lIIlIIlIIlIIl.get()) {
            lIIlIIlIIIlll.add((Object)class_2519.method_23256((String)class_2378.field_11146.method_10221((Object)lIIlIIlIIlIlI).toString()));
        }
        lIIlIIlIIlIII.method_10566("value", (class_2520)lIIlIIlIIIlll);
        return lIIlIIlIIlIII;
    }

    @Override
    protected boolean isValueValid(List<class_2248> lIIlIIlIlIIIl) {
        return true;
    }

    public static class Builder {
        private /* synthetic */ Consumer<Setting<List<class_2248>>> onModuleActivated;
        private /* synthetic */ String name;
        private /* synthetic */ List<class_2248> defaultValue;
        private /* synthetic */ Predicate<class_2248> filter;
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<List<class_2248>> onChanged;
        private /* synthetic */ IVisible visible;

        public Builder description(String lllllllllllllllllllIIIIlIlllIllI) {
            Builder lllllllllllllllllllIIIIlIlllIlll;
            lllllllllllllllllllIIIIlIlllIlll.description = lllllllllllllllllllIIIIlIlllIllI;
            return lllllllllllllllllllIIIIlIlllIlll;
        }

        public Builder defaultValue(List<class_2248> lllllllllllllllllllIIIIlIlllIIlI) {
            Builder lllllllllllllllllllIIIIlIlllIIll;
            lllllllllllllllllllIIIIlIlllIIll.defaultValue = lllllllllllllllllllIIIIlIlllIIlI;
            return lllllllllllllllllllIIIIlIlllIIll;
        }

        public Builder filter(Predicate<class_2248> lllllllllllllllllllIIIIlIllIIIII) {
            Builder lllllllllllllllllllIIIIlIlIlllll;
            lllllllllllllllllllIIIIlIlIlllll.filter = lllllllllllllllllllIIIIlIllIIIII;
            return lllllllllllllllllllIIIIlIlIlllll;
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_2248>>> lllllllllllllllllllIIIIlIllIIlII) {
            Builder lllllllllllllllllllIIIIlIllIIlIl;
            lllllllllllllllllllIIIIlIllIIlIl.onModuleActivated = lllllllllllllllllllIIIIlIllIIlII;
            return lllllllllllllllllllIIIIlIllIIlIl;
        }

        public Builder name(String lllllllllllllllllllIIIIlIllllllI) {
            Builder lllllllllllllllllllIIIIlIlllllIl;
            lllllllllllllllllllIIIIlIlllllIl.name = lllllllllllllllllllIIIIlIllllllI;
            return lllllllllllllllllllIIIIlIlllllIl;
        }

        public Builder() {
            Builder lllllllllllllllllllIIIIllIIIIIll;
            lllllllllllllllllllIIIIllIIIIIll.name = "undefined";
            lllllllllllllllllllIIIIllIIIIIll.description = "";
        }

        public Builder visible(IVisible lllllllllllllllllllIIIIlIlIllIlI) {
            Builder lllllllllllllllllllIIIIlIlIllIIl;
            lllllllllllllllllllIIIIlIlIllIIl.visible = lllllllllllllllllllIIIIlIlIllIlI;
            return lllllllllllllllllllIIIIlIlIllIIl;
        }

        public Builder onChanged(Consumer<List<class_2248>> lllllllllllllllllllIIIIlIllIlIlI) {
            Builder lllllllllllllllllllIIIIlIllIllIl;
            lllllllllllllllllllIIIIlIllIllIl.onChanged = lllllllllllllllllllIIIIlIllIlIlI;
            return lllllllllllllllllllIIIIlIllIllIl;
        }

        public BlockListSetting build() {
            Builder lllllllllllllllllllIIIIlIlIlIlIl;
            return new BlockListSetting(lllllllllllllllllllIIIIlIlIlIlIl.name, lllllllllllllllllllIIIIlIlIlIlIl.description, lllllllllllllllllllIIIIlIlIlIlIl.defaultValue, lllllllllllllllllllIIIIlIlIlIlIl.onChanged, lllllllllllllllllllIIIIlIlIlIlIl.onModuleActivated, lllllllllllllllllllIIIIlIlIlIlIl.filter, lllllllllllllllllllIIIIlIlIlIlIl.visible);
        }
    }
}

