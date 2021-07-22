/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1299
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.entity.EntityUtils;
import net.minecraft.class_1299;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class EntityTypeListSetting
extends Setting<Object2BooleanMap<class_1299<?>>> {
    public final /* synthetic */ boolean onlyAttackable;

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11145.method_10235();
    }

    public EntityTypeListSetting(String lllllllllllllllllIlIIIlIlIIlllIl, String lllllllllllllllllIlIIIlIlIIlllII, Object2BooleanMap<class_1299<?>> lllllllllllllllllIlIIIlIlIIllIll, Consumer<Object2BooleanMap<class_1299<?>>> lllllllllllllllllIlIIIlIlIIllIlI, Consumer<Setting<Object2BooleanMap<class_1299<?>>>> lllllllllllllllllIlIIIlIlIIlIIIl, IVisible lllllllllllllllllIlIIIlIlIIlIIII, boolean lllllllllllllllllIlIIIlIlIIlIlll) {
        super(lllllllllllllllllIlIIIlIlIIlllIl, lllllllllllllllllIlIIIlIlIIlllII, lllllllllllllllllIlIIIlIlIIllIll, lllllllllllllllllIlIIIlIlIIllIlI, lllllllllllllllllIlIIIlIlIIlIIIl, lllllllllllllllllIlIIIlIlIIlIIII);
        EntityTypeListSetting lllllllllllllllllIlIIIlIlIIlIllI;
        lllllllllllllllllIlIIIlIlIIlIllI.onlyAttackable = lllllllllllllllllIlIIIlIlIIlIlll;
        lllllllllllllllllIlIIIlIlIIlIllI.value = new Object2BooleanOpenHashMap(lllllllllllllllllIlIIIlIlIIllIll);
    }

    @Override
    protected boolean isValueValid(Object2BooleanMap<class_1299<?>> lllllllllllllllllIlIIIlIIllIIIIl) {
        return true;
    }

    @Override
    public class_2487 toTag() {
        EntityTypeListSetting lllllllllllllllllIlIIIlIIlIIlIll;
        class_2487 lllllllllllllllllIlIIIlIIlIIlIIl = lllllllllllllllllIlIIIlIIlIIlIll.saveGeneral();
        class_2499 lllllllllllllllllIlIIIlIIlIIIlll = new class_2499();
        for (class_1299 lllllllllllllllllIlIIIlIIlIIllIl : ((Object2BooleanMap)lllllllllllllllllIlIIIlIIlIIlIll.get()).keySet()) {
            if (!((Object2BooleanMap)lllllllllllllllllIlIIIlIIlIIlIll.get()).getBoolean((Object)lllllllllllllllllIlIIIlIIlIIllIl)) continue;
            lllllllllllllllllIlIIIlIIlIIIlll.add((Object)class_2519.method_23256((String)class_2378.field_11145.method_10221((Object)lllllllllllllllllIlIIIlIIlIIllIl).toString()));
        }
        lllllllllllllllllIlIIIlIIlIIlIIl.method_10566("value", (class_2520)lllllllllllllllllIlIIIlIIlIIIlll);
        return lllllllllllllllllIlIIIlIIlIIlIIl;
    }

    @Override
    public Object2BooleanMap<class_1299<?>> fromTag(class_2487 lllllllllllllllllIlIIIlIIIlIlIII) {
        EntityTypeListSetting lllllllllllllllllIlIIIlIIIllIIII;
        ((Object2BooleanMap)lllllllllllllllllIlIIIlIIIllIIII.get()).clear();
        class_2499 lllllllllllllllllIlIIIlIIIlIllII = lllllllllllllllllIlIIIlIIIlIlIII.method_10554("value", 8);
        for (class_2520 lllllllllllllllllIlIIIlIIIllIIlI : lllllllllllllllllIlIIIlIIIlIllII) {
            class_1299 lllllllllllllllllIlIIIlIIIllIIll = (class_1299)class_2378.field_11145.method_10223(new class_2960(lllllllllllllllllIlIIIlIIIllIIlI.method_10714()));
            if (lllllllllllllllllIlIIIlIIIllIIII.onlyAttackable && !EntityUtils.isAttackable(lllllllllllllllllIlIIIlIIIllIIll)) continue;
            ((Object2BooleanMap)lllllllllllllllllIlIIIlIIIllIIII.get()).put((Object)lllllllllllllllllIlIIIlIIIllIIll, true);
        }
        lllllllllllllllllIlIIIlIIIllIIII.changed();
        return (Object2BooleanMap)lllllllllllllllllIlIIIlIIIllIIII.get();
    }

    @Override
    public void reset(boolean lllllllllllllllllIlIIIlIlIIIIllI) {
        EntityTypeListSetting lllllllllllllllllIlIIIlIlIIIIlll;
        lllllllllllllllllIlIIIlIlIIIIlll.value = new Object2BooleanOpenHashMap((Object2BooleanMap)lllllllllllllllllIlIIIlIlIIIIlll.defaultValue);
        if (lllllllllllllllllIlIIIlIlIIIIllI) {
            lllllllllllllllllIlIIIlIlIIIIlll.changed();
        }
    }

    @Override
    protected Object2BooleanMap<class_1299<?>> parseImpl(String lllllllllllllllllIlIIIlIIlllIllI) {
        String[] lllllllllllllllllIlIIIlIIlllIIlI = lllllllllllllllllIlIIIlIIlllIllI.split(",");
        Object2BooleanOpenHashMap lllllllllllllllllIlIIIlIIlllIIII = new Object2BooleanOpenHashMap(lllllllllllllllllIlIIIlIIlllIIlI.length);
        try {
            for (String lllllllllllllllllIlIIIlIIllllIII : lllllllllllllllllIlIIIlIIlllIIlI) {
                class_1299 lllllllllllllllllIlIIIlIIllllIIl = (class_1299)EntityTypeListSetting.parseId(class_2378.field_11145, lllllllllllllllllIlIIIlIIllllIII);
                if (lllllllllllllllllIlIIIlIIllllIIl == null) continue;
                lllllllllllllllllIlIIIlIIlllIIII.put((Object)lllllllllllllllllIlIIIlIIllllIIl, true);
            }
        }
        catch (Exception lllllllllllllllllIlIIIlIIllIlIll) {
            // empty catch block
        }
        return lllllllllllllllllIlIIIlIIlllIIII;
    }

    public static class Builder {
        private /* synthetic */ String description;
        private /* synthetic */ Object2BooleanMap<class_1299<?>> defaultValue;
        private /* synthetic */ Consumer<Object2BooleanMap<class_1299<?>>> onChanged;
        private /* synthetic */ Consumer<Setting<Object2BooleanMap<class_1299<?>>>> onModuleActivated;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ boolean onlyAttackable;
        private /* synthetic */ String name;

        public Builder onModuleActivated(Consumer<Setting<Object2BooleanMap<class_1299<?>>>> llIlllIlIllI) {
            Builder llIlllIlIlll;
            llIlllIlIlll.onModuleActivated = llIlllIlIllI;
            return llIlllIlIlll;
        }

        public Builder onChanged(Consumer<Object2BooleanMap<class_1299<?>>> llIlllIllllI) {
            Builder llIlllIlllll;
            llIlllIlllll.onChanged = llIlllIllllI;
            return llIlllIlllll;
        }

        public Builder description(String llIllllIlIlI) {
            Builder llIllllIlIIl;
            llIllllIlIIl.description = llIllllIlIlI;
            return llIllllIlIIl;
        }

        public Builder() {
            Builder llIlllllIlIl;
            llIlllllIlIl.name = "undefined";
            llIlllllIlIl.description = "";
            llIlllllIlIl.onlyAttackable = false;
        }

        public Builder onlyAttackable() {
            Builder llIlllIIlllI;
            llIlllIIlllI.onlyAttackable = true;
            return llIlllIIlllI;
        }

        public Builder visible(IVisible llIlllIlIIII) {
            Builder llIlllIlIIIl;
            llIlllIlIIIl.visible = llIlllIlIIII;
            return llIlllIlIIIl;
        }

        public Builder defaultValue(Object2BooleanMap<class_1299<?>> llIllllIIlII) {
            Builder llIllllIIlIl;
            llIllllIIlIl.defaultValue = llIllllIIlII;
            return llIllllIIlIl;
        }

        public EntityTypeListSetting build() {
            Builder llIlllIIlIll;
            return new EntityTypeListSetting(llIlllIIlIll.name, llIlllIIlIll.description, llIlllIIlIll.defaultValue, llIlllIIlIll.onChanged, llIlllIIlIll.onModuleActivated, llIlllIIlIll.visible, llIlllIIlIll.onlyAttackable);
        }

        public Builder name(String llIlllllIIII) {
            Builder llIllllIllll;
            llIllllIllll.name = llIlllllIIII;
            return llIllllIllll;
        }
    }
}

