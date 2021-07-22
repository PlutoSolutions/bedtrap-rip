/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_3414
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_3414;

public class SoundEventListSetting
extends Setting<List<class_3414>> {
    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11156.method_10235();
    }

    @Override
    protected boolean isValueValid(List<class_3414> lllIllllllIlllI) {
        return true;
    }

    @Override
    protected List<class_3414> parseImpl(String lllIllllllllIlI) {
        String[] lllIllllllllIIl = lllIllllllllIlI.split(",");
        ArrayList<class_3414> lllIllllllllIII = new ArrayList<class_3414>(lllIllllllllIIl.length);
        try {
            for (String lllIlllllllllII : lllIllllllllIIl) {
                class_3414 lllIlllllllllIl = (class_3414)SoundEventListSetting.parseId(class_2378.field_11156, lllIlllllllllII);
                if (lllIlllllllllIl == null) continue;
                lllIllllllllIII.add(lllIlllllllllIl);
            }
        }
        catch (Exception lllIlllllllIlII) {
            // empty catch block
        }
        return lllIllllllllIII;
    }

    @Override
    public void reset(boolean llllIIIIIIIlIII) {
        SoundEventListSetting llllIIIIIIIIlll;
        llllIIIIIIIIlll.value = new ArrayList((Collection)llllIIIIIIIIlll.defaultValue);
        if (llllIIIIIIIlIII) {
            llllIIIIIIIIlll.changed();
        }
    }

    public SoundEventListSetting(String llllIIIIIIllIII, String llllIIIIIIlIlll, List<class_3414> llllIIIIIIlIllI, Consumer<List<class_3414>> llllIIIIIIIlllI, Consumer<Setting<List<class_3414>>> llllIIIIIIlIlII, IVisible llllIIIIIIlIIll) {
        super(llllIIIIIIllIII, llllIIIIIIlIlll, llllIIIIIIlIllI, llllIIIIIIIlllI, llllIIIIIIlIlII, llllIIIIIIlIIll);
        SoundEventListSetting llllIIIIIIlIIlI;
        llllIIIIIIlIIlI.value = new ArrayList<class_3414>(llllIIIIIIlIllI);
    }

    @Override
    public class_2487 toTag() {
        SoundEventListSetting lllIllllllIIIll;
        class_2487 lllIllllllIIlIl = lllIllllllIIIll.saveGeneral();
        class_2499 lllIllllllIIlII = new class_2499();
        for (class_3414 lllIllllllIIlll : (List)lllIllllllIIIll.get()) {
            lllIllllllIIlII.add((Object)class_2519.method_23256((String)class_2378.field_11156.method_10221((Object)lllIllllllIIlll).toString()));
        }
        lllIllllllIIlIl.method_10566("value", (class_2520)lllIllllllIIlII);
        return lllIllllllIIlIl;
    }

    @Override
    public List<class_3414> fromTag(class_2487 lllIlllllIlIlll) {
        SoundEventListSetting lllIlllllIlIlIl;
        ((List)lllIlllllIlIlIl.get()).clear();
        class_2499 lllIlllllIlIllI = lllIlllllIlIlll.method_10554("value", 8);
        for (class_2520 lllIlllllIllIIl : lllIlllllIlIllI) {
            ((List)lllIlllllIlIlIl.get()).add((class_3414)class_2378.field_11156.method_10223(new class_2960(lllIlllllIllIIl.method_10714())));
        }
        lllIlllllIlIlIl.changed();
        return (List)lllIlllllIlIlIl.get();
    }

    public static class Builder {
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<Setting<List<class_3414>>> onModuleActivated;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Consumer<List<class_3414>> onChanged;
        private /* synthetic */ String name;
        private /* synthetic */ List<class_3414> defaultValue;

        public Builder() {
            Builder lIIIIllllIIlll;
            lIIIIllllIIlll.name = "undefined";
            lIIIIllllIIlll.description = "";
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_3414>>> lIIIIlllIIlIll) {
            Builder lIIIIlllIIllII;
            lIIIIlllIIllII.onModuleActivated = lIIIIlllIIlIll;
            return lIIIIlllIIllII;
        }

        public Builder defaultValue(List<class_3414> lIIIIlllIlIlll) {
            Builder lIIIIlllIlIllI;
            lIIIIlllIlIllI.defaultValue = lIIIIlllIlIlll;
            return lIIIIlllIlIllI;
        }

        public Builder description(String lIIIIlllIlllIl) {
            Builder lIIIIlllIlllII;
            lIIIIlllIlllII.description = lIIIIlllIlllIl;
            return lIIIIlllIlllII;
        }

        public SoundEventListSetting build() {
            Builder lIIIIlllIIIIIl;
            return new SoundEventListSetting(lIIIIlllIIIIIl.name, lIIIIlllIIIIIl.description, lIIIIlllIIIIIl.defaultValue, lIIIIlllIIIIIl.onChanged, lIIIIlllIIIIIl.onModuleActivated, lIIIIlllIIIIIl.visible);
        }

        public Builder name(String lIIIIllllIIIll) {
            Builder lIIIIllllIIlII;
            lIIIIllllIIlII.name = lIIIIllllIIIll;
            return lIIIIllllIIlII;
        }

        public Builder onChanged(Consumer<List<class_3414>> lIIIIlllIIllll) {
            Builder lIIIIlllIlIIlI;
            lIIIIlllIlIIlI.onChanged = lIIIIlllIIllll;
            return lIIIIlllIlIIlI;
        }

        public Builder visible(IVisible lIIIIlllIIIIll) {
            Builder lIIIIlllIIIlII;
            lIIIIlllIIIlII.visible = lIIIIlllIIIIll;
            return lIIIIlllIIIlII;
        }
    }
}

