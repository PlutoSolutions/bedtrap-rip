/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IBlockData;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.IGetter;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class BlockDataSetting<T extends ICopyable<T> & ISerializable<T> & IBlockData<T>>
extends Setting<Map<class_2248, T>> {
    public final /* synthetic */ IGetter<T> defaultData;

    @Override
    public Map<class_2248, T> fromTag(class_2487 lllllllIlIllll) {
        BlockDataSetting lllllllIllIIll;
        ((Map)lllllllIllIIll.get()).clear();
        class_2487 lllllllIllIIIl = lllllllIlIllll.method_10562("value");
        for (String lllllllIllIlII : lllllllIllIIIl.method_10541()) {
            ((Map)lllllllIllIIll.get()).put((class_2248)class_2378.field_11146.method_10223(new class_2960(lllllllIllIlII)), (ICopyable)((ISerializable)((ICopyable)lllllllIllIIll.defaultData.get()).copy()).fromTag(lllllllIllIIIl.method_10562(lllllllIllIlII)));
        }
        return (Map)lllllllIllIIll.get();
    }

    @Override
    protected boolean isValueValid(Map<class_2248, T> llllllllIIlIII) {
        return true;
    }

    @Override
    public void reset(boolean llllllllIIllII) {
        BlockDataSetting llllllllIIllIl;
        llllllllIIllIl.value = new HashMap((Map)llllllllIIllIl.defaultValue);
        if (llllllllIIllII) {
            llllllllIIllIl.changed();
        }
    }

    public BlockDataSetting(String lllllllllIIIII, String llllllllIlIlll, Map<class_2248, T> llllllllIlIllI, Consumer<Map<class_2248, T>> llllllllIlllIl, Consumer<Setting<Map<class_2248, T>>> llllllllIlllII, IGetter<T> llllllllIllIll, IVisible llllllllIllIlI) {
        super(lllllllllIIIII, llllllllIlIlll, llllllllIlIllI, llllllllIlllIl, llllllllIlllII, llllllllIllIlI);
        BlockDataSetting lllllllllIIIIl;
        lllllllllIIIIl.defaultData = llllllllIllIll;
    }

    @Override
    protected Map<class_2248, T> parseImpl(String llllllllIIlIlI) {
        return new HashMap(0);
    }

    @Override
    public class_2487 toTag() {
        BlockDataSetting lllllllIlllllI;
        class_2487 llllllllIIIIII = lllllllIlllllI.saveGeneral();
        class_2487 lllllllIllllll = new class_2487();
        for (class_2248 llllllllIIIIlI : ((Map)lllllllIlllllI.get()).keySet()) {
            lllllllIllllll.method_10566(class_2378.field_11146.method_10221((Object)llllllllIIIIlI).toString(), (class_2520)((ISerializable)((Object)((ICopyable)((Map)lllllllIlllllI.get()).get((Object)llllllllIIIIlI)))).toTag());
        }
        llllllllIIIIII.method_10566("value", (class_2520)lllllllIllllll);
        return llllllllIIIIII;
    }

    public static class Builder<T extends ICopyable<T> & ISerializable<T> & IBlockData<T>> {
        private /* synthetic */ String description;
        private /* synthetic */ String name;
        private /* synthetic */ Map<class_2248, T> defaultValue;
        private /* synthetic */ IGetter<T> defaultData;
        private /* synthetic */ Consumer<Setting<Map<class_2248, T>>> onModuleActivated;
        private /* synthetic */ Consumer<Map<class_2248, T>> onChanged;
        private /* synthetic */ IVisible visible;

        public Builder() {
            Builder lllllIlIIIIIIIl;
            lllllIlIIIIIIIl.name = "undefined";
            lllllIlIIIIIIIl.description = "";
        }

        public Builder<T> onModuleActivated(Consumer<Setting<Map<class_2248, T>>> lllllIIlllIIlII) {
            Builder lllllIIlllIIIll;
            lllllIIlllIIIll.onModuleActivated = lllllIIlllIIlII;
            return lllllIIlllIIIll;
        }

        public Builder<T> defaultData(IGetter<T> lllllIIllIlllII) {
            Builder lllllIIllIlllll;
            lllllIIllIlllll.defaultData = lllllIIllIlllII;
            return lllllIIllIlllll;
        }

        public BlockDataSetting<T> build() {
            Builder lllllIIllIlIlII;
            return new BlockDataSetting<T>(lllllIIllIlIlII.name, lllllIIllIlIlII.description, lllllIIllIlIlII.defaultValue, lllllIIllIlIlII.onChanged, lllllIIllIlIlII.onModuleActivated, lllllIIllIlIlII.defaultData, lllllIIllIlIlII.visible);
        }

        public Builder<T> name(String lllllIIlllllIlI) {
            Builder lllllIIllllllIl;
            lllllIIllllllIl.name = lllllIIlllllIlI;
            return lllllIIllllllIl;
        }

        public Builder<T> visible(IVisible lllllIIllIlIllI) {
            Builder lllllIIllIllIIl;
            lllllIIllIllIIl.visible = lllllIIllIlIllI;
            return lllllIIllIllIIl;
        }

        public Builder<T> defaultValue(Map<class_2248, T> lllllIIllllIIII) {
            Builder lllllIIllllIIIl;
            lllllIIllllIIIl.defaultValue = lllllIIllllIIII;
            return lllllIIllllIIIl;
        }

        public Builder<T> onChanged(Consumer<Map<class_2248, T>> lllllIIlllIlIlI) {
            Builder lllllIIlllIlIll;
            lllllIIlllIlIll.onChanged = lllllIIlllIlIlI;
            return lllllIIlllIlIll;
        }

        public Builder<T> description(String lllllIIllllIllI) {
            Builder lllllIIllllIlIl;
            lllllIIllllIlIl.description = lllllIIllllIllI;
            return lllllIIllllIlIl;
        }
    }
}

