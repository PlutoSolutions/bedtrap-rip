/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.class_1291
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1291;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class StatusEffectSetting
extends Setting<Object2IntMap<class_1291>> {
    @Override
    protected boolean isValueValid(Object2IntMap<class_1291> lIIlIlIIlIllIlI) {
        return true;
    }

    @Override
    public void reset(boolean lIIlIlIIllllIlI) {
        StatusEffectSetting lIIlIlIIllllIll;
        lIIlIlIIllllIll.value = new Object2IntArrayMap((Object2IntMap)lIIlIlIIllllIll.defaultValue);
        if (lIIlIlIIllllIlI) {
            lIIlIlIIllllIll.changed();
        }
    }

    @Override
    public Object2IntMap<class_1291> fromTag(class_2487 lIIlIlIIIllllll) {
        StatusEffectSetting lIIlIlIIlIIIIII;
        ((Object2IntMap)lIIlIlIIlIIIIII.get()).clear();
        class_2487 lIIlIlIIIlllllI = lIIlIlIIIllllll.method_10562("value");
        for (String lIIlIlIIlIIIIIl : lIIlIlIIIlllllI.method_10541()) {
            class_1291 lIIlIlIIlIIIIlI = (class_1291)class_2378.field_11159.method_10223(new class_2960(lIIlIlIIlIIIIIl));
            if (lIIlIlIIlIIIIlI == null) continue;
            ((Object2IntMap)lIIlIlIIlIIIIII.get()).put((Object)lIIlIlIIlIIIIlI, lIIlIlIIIlllllI.method_10550(lIIlIlIIlIIIIIl));
        }
        lIIlIlIIlIIIIII.changed();
        return (Object2IntMap)lIIlIlIIlIIIIII.get();
    }

    @Override
    protected Object2IntMap<class_1291> parseImpl(String lIIlIlIIllIlIII) {
        String[] lIIlIlIIllIIlll = lIIlIlIIllIlIII.split(",");
        Object2IntMap<class_1291> lIIlIlIIllIIllI = Utils.createStatusEffectMap();
        try {
            for (String lIIlIlIIllIlIlI : lIIlIlIIllIIlll) {
                String[] lIIlIlIIllIllIl = lIIlIlIIllIlIlI.split(" ");
                class_1291 lIIlIlIIllIllII = (class_1291)StatusEffectSetting.parseId(class_2378.field_11159, lIIlIlIIllIllIl[0]);
                int lIIlIlIIllIlIll = Integer.parseInt(lIIlIlIIllIllIl[1]);
                lIIlIlIIllIIllI.put((Object)lIIlIlIIllIllII, lIIlIlIIllIlIll);
            }
        }
        catch (Exception lIIlIlIIllIIIlI) {
            // empty catch block
        }
        return lIIlIlIIllIIllI;
    }

    public StatusEffectSetting(String lIIlIlIlIIIlIlI, String lIIlIlIlIIIIIlI, Object2IntMap<class_1291> lIIlIlIlIIIlIII, Consumer<Object2IntMap<class_1291>> lIIlIlIlIIIIIII, Consumer<Setting<Object2IntMap<class_1291>>> lIIlIlIlIIIIllI, IVisible lIIlIlIlIIIIlIl) {
        super(lIIlIlIlIIIlIlI, lIIlIlIlIIIIIlI, lIIlIlIlIIIlIII, lIIlIlIlIIIIIII, lIIlIlIlIIIIllI, lIIlIlIlIIIIlIl);
        StatusEffectSetting lIIlIlIlIIIlIll;
    }

    @Override
    public class_2487 toTag() {
        StatusEffectSetting lIIlIlIIlIlIIIl;
        class_2487 lIIlIlIIlIlIIII = lIIlIlIIlIlIIIl.saveGeneral();
        class_2487 lIIlIlIIlIIllll = new class_2487();
        for (class_1291 lIIlIlIIlIlIIlI : ((Object2IntMap)lIIlIlIIlIlIIIl.get()).keySet()) {
            class_2960 lIIlIlIIlIlIIll = class_2378.field_11159.method_10221((Object)lIIlIlIIlIlIIlI);
            if (lIIlIlIIlIlIIll == null) continue;
            lIIlIlIIlIIllll.method_10569(lIIlIlIIlIlIIll.toString(), ((Object2IntMap)lIIlIlIIlIlIIIl.get()).getInt((Object)lIIlIlIIlIlIIlI));
        }
        lIIlIlIIlIlIIII.method_10566("value", (class_2520)lIIlIlIIlIIllll);
        return lIIlIlIIlIlIIII;
    }

    public static class Builder {
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<Setting<Object2IntMap<class_1291>>> onModuleActivated;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<Object2IntMap<class_1291>> onChanged;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Object2IntMap<class_1291> defaultValue;

        public StatusEffectSetting build() {
            Builder lIlIlllIllIl;
            return new StatusEffectSetting(lIlIlllIllIl.name, lIlIlllIllIl.description, lIlIlllIllIl.defaultValue, lIlIlllIllIl.onChanged, lIlIlllIllIl.onModuleActivated, lIlIlllIllIl.visible);
        }

        public Builder onChanged(Consumer<Object2IntMap<class_1291>> lIllIIIIIlIl) {
            Builder lIllIIIIIlll;
            lIllIIIIIlll.onChanged = lIllIIIIIlIl;
            return lIllIIIIIlll;
        }

        public Builder() {
            Builder lIllIIllIIll;
            lIllIIllIIll.name = "undefined";
            lIllIIllIIll.description = "";
        }

        public Builder description(String lIllIIIlIIlI) {
            Builder lIllIIIlIlII;
            lIllIIIlIlII.description = lIllIIIlIIlI;
            return lIllIIIlIlII;
        }

        public Builder onModuleActivated(Consumer<Setting<Object2IntMap<class_1291>>> lIlIlllllIII) {
            Builder lIlIlllllIlI;
            lIlIlllllIlI.onModuleActivated = lIlIlllllIII;
            return lIlIlllllIlI;
        }

        public Builder visible(IVisible lIlIllllIIII) {
            Builder lIlIllllIIIl;
            lIlIllllIIIl.visible = lIlIllllIIII;
            return lIlIllllIIIl;
        }

        public Builder name(String lIllIIlIIIII) {
            Builder lIllIIlIIllI;
            lIllIIlIIllI.name = lIllIIlIIIII;
            return lIllIIlIIllI;
        }

        public Builder defaultValue(Object2IntMap<class_1291> lIllIIIIllIl) {
            Builder lIllIIIIllII;
            lIllIIIIllII.defaultValue = lIllIIIIllIl;
            return lIllIIIIllII;
        }
    }
}

