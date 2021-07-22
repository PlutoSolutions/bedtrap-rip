/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2396
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
import net.minecraft.class_2378;
import net.minecraft.class_2396;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class ParticleTypeListSetting
extends Setting<List<class_2396<?>>> {
    private static /* synthetic */ List<class_2960> suggestions;

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        if (suggestions == null) {
            suggestions = new ArrayList<class_2960>(class_2378.field_11141.method_10235().size());
            for (class_2960 lllIIlIlIIlIlIl : class_2378.field_11141.method_10235()) {
                class_2396 lllIIlIlIIlIllI = (class_2396)class_2378.field_11141.method_10223(lllIIlIlIIlIlIl);
                if (!(lllIIlIlIIlIllI instanceof class_2396)) continue;
                suggestions.add(lllIIlIlIIlIlIl);
            }
        }
        return suggestions;
    }

    @Override
    public List<class_2396<?>> fromTag(class_2487 lllIIlIIllllIII) {
        ParticleTypeListSetting lllIIlIIlllllII;
        ((List)lllIIlIIlllllII.get()).clear();
        class_2499 lllIIlIIllllIlI = lllIIlIIllllIII.method_10554("value", 8);
        for (class_2520 lllIIlIIlllllIl : lllIIlIIllllIlI) {
            ((List)lllIIlIIlllllII.get()).add((class_2396)class_2378.field_11141.method_10223(new class_2960(lllIIlIIlllllIl.method_10714())));
        }
        lllIIlIIlllllII.changed();
        return (List)lllIIlIIlllllII.get();
    }

    @Override
    public class_2487 toTag() {
        ParticleTypeListSetting lllIIlIlIIIIlll;
        class_2487 lllIIlIlIIIlIIl = lllIIlIlIIIIlll.saveGeneral();
        class_2499 lllIIlIlIIIlIII = new class_2499();
        for (class_2396 lllIIlIlIIIlIll : (List)lllIIlIlIIIIlll.get()) {
            lllIIlIlIIIlIII.add((Object)class_2519.method_23256((String)class_2378.field_11141.method_10221((Object)lllIIlIlIIIlIll).toString()));
        }
        lllIIlIlIIIlIIl.method_10566("value", (class_2520)lllIIlIlIIIlIII);
        return lllIIlIlIIIlIIl;
    }

    @Override
    protected boolean isValueValid(List<class_2396<?>> lllIIlIlIIllIlI) {
        return true;
    }

    @Override
    public void reset(boolean lllIIlIlIllIIlI) {
        ParticleTypeListSetting lllIIlIlIllIIll;
        lllIIlIlIllIIll.value = new ArrayList((Collection)lllIIlIlIllIIll.defaultValue);
        if (lllIIlIlIllIIlI) {
            lllIIlIlIllIIll.changed();
        }
    }

    public ParticleTypeListSetting(String lllIIlIlIllllIl, String lllIIlIllIIIIll, List<class_2396<?>> lllIIlIlIlllIll, Consumer<List<class_2396<?>>> lllIIlIllIIIIIl, Consumer<Setting<List<class_2396<?>>>> lllIIlIllIIIIII, IVisible lllIIlIlIlllIII) {
        super(lllIIlIlIllllIl, lllIIlIllIIIIll, lllIIlIlIlllIll, lllIIlIllIIIIIl, lllIIlIllIIIIII, lllIIlIlIlllIII);
        ParticleTypeListSetting lllIIlIlIlllllI;
        lllIIlIlIlllllI.value = new ArrayList(lllIIlIlIlllIll);
    }

    @Override
    protected List<class_2396<?>> parseImpl(String lllIIlIlIlIIIll) {
        String[] lllIIlIlIlIIlIl = lllIIlIlIlIIIll.split(",");
        ArrayList lllIIlIlIlIIlII = new ArrayList(lllIIlIlIlIIlIl.length);
        try {
            for (String lllIIlIlIlIlIII : lllIIlIlIlIIlIl) {
                class_2396 lllIIlIlIlIlIIl = (class_2396)ParticleTypeListSetting.parseId(class_2378.field_11141, lllIIlIlIlIlIII);
                if (!(lllIIlIlIlIlIIl instanceof class_2396)) continue;
                lllIIlIlIlIIlII.add(lllIIlIlIlIlIIl);
            }
        }
        catch (Exception lllIIlIlIlIIIII) {
            // empty catch block
        }
        return lllIIlIlIlIIlII;
    }

    public static class Builder {
        private /* synthetic */ String name;
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<Setting<List<class_2396<?>>>> onModuleActivated;
        private /* synthetic */ Consumer<List<class_2396<?>>> onChanged;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ List<class_2396<?>> defaultValue;

        public Builder defaultValue(List<class_2396<?>> lllllllllllllllllIIIIlllIllIIlIl) {
            Builder lllllllllllllllllIIIIlllIllIIlII;
            lllllllllllllllllIIIIlllIllIIlII.defaultValue = lllllllllllllllllIIIIlllIllIIlIl;
            return lllllllllllllllllIIIIlllIllIIlII;
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_2396<?>>>> lllllllllllllllllIIIIlllIlIllIIl) {
            Builder lllllllllllllllllIIIIlllIlIllIII;
            lllllllllllllllllIIIIlllIlIllIII.onModuleActivated = lllllllllllllllllIIIIlllIlIllIIl;
            return lllllllllllllllllIIIIlllIlIllIII;
        }

        public Builder description(String lllllllllllllllllIIIIlllIllIlIll) {
            Builder lllllllllllllllllIIIIlllIllIllII;
            lllllllllllllllllIIIIlllIllIllII.description = lllllllllllllllllIIIIlllIllIlIll;
            return lllllllllllllllllIIIIlllIllIllII;
        }

        public ParticleTypeListSetting build() {
            Builder lllllllllllllllllIIIIlllIlIIllll;
            return new ParticleTypeListSetting(lllllllllllllllllIIIIlllIlIIllll.name, lllllllllllllllllIIIIlllIlIIllll.description, lllllllllllllllllIIIIlllIlIIllll.defaultValue, lllllllllllllllllIIIIlllIlIIllll.onChanged, lllllllllllllllllIIIIlllIlIIllll.onModuleActivated, lllllllllllllllllIIIIlllIlIIllll.visible);
        }

        public Builder() {
            Builder lllllllllllllllllIIIIlllIlllIlIl;
            lllllllllllllllllIIIIlllIlllIlIl.name = "undefined";
            lllllllllllllllllIIIIlllIlllIlIl.description = "";
        }

        public Builder name(String lllllllllllllllllIIIIlllIllIllll) {
            Builder lllllllllllllllllIIIIlllIlllIIlI;
            lllllllllllllllllIIIIlllIlllIIlI.name = lllllllllllllllllIIIIlllIllIllll;
            return lllllllllllllllllIIIIlllIlllIIlI;
        }

        public Builder visible(IVisible lllllllllllllllllIIIIlllIlIlIIIl) {
            Builder lllllllllllllllllIIIIlllIlIlIIlI;
            lllllllllllllllllIIIIlllIlIlIIlI.visible = lllllllllllllllllIIIIlllIlIlIIIl;
            return lllllllllllllllllIIIIlllIlIlIIlI;
        }

        public Builder onChanged(Consumer<List<class_2396<?>>> lllllllllllllllllIIIIlllIlIlllIl) {
            Builder lllllllllllllllllIIIIlllIlIllllI;
            lllllllllllllllllIIIIlllIlIllllI.onChanged = lllllllllllllllllIIIIlllIlIlllIl;
            return lllllllllllllllllIIIIlllIlIllllI;
        }
    }
}

