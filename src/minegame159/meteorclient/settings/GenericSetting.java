/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

public class GenericSetting<T extends ICopyable<T> & ISerializable<T>>
extends Setting<T> {
    @Override
    public void reset(boolean lllllllllllllllllIIIIllllllIIlll) {
        GenericSetting lllllllllllllllllIIIIllllllIlIII;
        if (lllllllllllllllllIIIIllllllIlIII.value == null) {
            lllllllllllllllllIIIIllllllIlIII.value = ((ICopyable)lllllllllllllllllIIIIllllllIlIII.defaultValue).copy();
        }
        ((ICopyable)lllllllllllllllllIIIIllllllIlIII.value).set((ICopyable)lllllllllllllllllIIIIllllllIlIII.defaultValue);
        if (lllllllllllllllllIIIIllllllIIlll) {
            lllllllllllllllllIIIIllllllIlIII.changed();
        }
    }

    @Override
    protected boolean isValueValid(T lllllllllllllllllIIIIllllllIIIIl) {
        return true;
    }

    @Override
    protected T parseImpl(String lllllllllllllllllIIIIllllllIIlII) {
        GenericSetting lllllllllllllllllIIIIllllllIIIll;
        return ((ICopyable)lllllllllllllllllIIIIllllllIIIll.defaultValue).copy();
    }

    @Override
    public T fromTag(class_2487 lllllllllllllllllIIIIlllllIlIlIl) {
        GenericSetting lllllllllllllllllIIIIlllllIllIII;
        ((ISerializable)((Object)((ICopyable)lllllllllllllllllIIIIlllllIllIII.get()))).fromTag(lllllllllllllllllIIIIlllllIlIlIl.method_10562("value"));
        return (T)((ICopyable)lllllllllllllllllIIIIlllllIllIII.get());
    }

    public GenericSetting(String lllllllllllllllllIIIIlllllllIIlI, String lllllllllllllllllIIIIllllllllIII, T lllllllllllllllllIIIIlllllllIlll, Consumer<T> lllllllllllllllllIIIIllllllIllll, Consumer<Setting<T>> lllllllllllllllllIIIIllllllIlllI, IVisible lllllllllllllllllIIIIlllllllIlII) {
        super(lllllllllllllllllIIIIlllllllIIlI, lllllllllllllllllIIIIllllllllIII, lllllllllllllllllIIIIlllllllIlll, lllllllllllllllllIIIIllllllIllll, lllllllllllllllllIIIIllllllIlllI, lllllllllllllllllIIIIlllllllIlII);
        GenericSetting lllllllllllllllllIIIIllllllllIlI;
    }

    @Override
    public class_2487 toTag() {
        GenericSetting lllllllllllllllllIIIIlllllIllllI;
        class_2487 lllllllllllllllllIIIIlllllIlllIl = lllllllllllllllllIIIIlllllIllllI.saveGeneral();
        lllllllllllllllllIIIIlllllIlllIl.method_10566("value", (class_2520)((ISerializable)((Object)((ICopyable)lllllllllllllllllIIIIlllllIllllI.get()))).toTag());
        return lllllllllllllllllIIIIlllllIlllIl;
    }

    public static class Builder<T extends ICopyable<T> & ISerializable<T>> {
        private /* synthetic */ Consumer<Setting<T>> onModuleActivated;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<T> onChanged;
        private /* synthetic */ T defaultValue;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String description;

        public Builder<T> name(String llllllllllllllllIlllIIlIIllIIIIl) {
            Builder llllllllllllllllIlllIIlIIllIIIII;
            llllllllllllllllIlllIIlIIllIIIII.name = llllllllllllllllIlllIIlIIllIIIIl;
            return llllllllllllllllIlllIIlIIllIIIII;
        }

        public Builder<T> defaultValue(T llllllllllllllllIlllIIlIIlIlIlIl) {
            Builder llllllllllllllllIlllIIlIIlIlIlII;
            llllllllllllllllIlllIIlIIlIlIlII.defaultValue = llllllllllllllllIlllIIlIIlIlIlIl;
            return llllllllllllllllIlllIIlIIlIlIlII;
        }

        public Builder<T> description(String llllllllllllllllIlllIIlIIlIllIll) {
            Builder llllllllllllllllIlllIIlIIlIllIlI;
            llllllllllllllllIlllIIlIIlIllIlI.description = llllllllllllllllIlllIIlIIlIllIll;
            return llllllllllllllllIlllIIlIIlIllIlI;
        }

        public GenericSetting<T> build() {
            Builder llllllllllllllllIlllIIlIIIllllll;
            return new GenericSetting<T>(llllllllllllllllIlllIIlIIIllllll.name, llllllllllllllllIlllIIlIIIllllll.description, llllllllllllllllIlllIIlIIIllllll.defaultValue, llllllllllllllllIlllIIlIIIllllll.onChanged, llllllllllllllllIlllIIlIIIllllll.onModuleActivated, llllllllllllllllIlllIIlIIIllllll.visible);
        }

        public Builder visible(IVisible llllllllllllllllIlllIIlIIlIIIIll) {
            Builder llllllllllllllllIlllIIlIIlIIIIlI;
            llllllllllllllllIlllIIlIIlIIIIlI.visible = llllllllllllllllIlllIIlIIlIIIIll;
            return llllllllllllllllIlllIIlIIlIIIIlI;
        }

        public Builder<T> onChanged(Consumer<T> llllllllllllllllIlllIIlIIlIIllIl) {
            Builder llllllllllllllllIlllIIlIIlIIlllI;
            llllllllllllllllIlllIIlIIlIIlllI.onChanged = llllllllllllllllIlllIIlIIlIIllIl;
            return llllllllllllllllIlllIIlIIlIIlllI;
        }

        public Builder<T> onModuleActivated(Consumer<Setting<T>> llllllllllllllllIlllIIlIIlIIlIIl) {
            Builder llllllllllllllllIlllIIlIIlIIlIII;
            llllllllllllllllIlllIIlIIlIIlIII.onModuleActivated = llllllllllllllllIlllIIlIIlIIlIIl;
            return llllllllllllllllIlllIIlIIlIIlIII;
        }

        public Builder() {
            Builder llllllllllllllllIlllIIlIIllIIllI;
            llllllllllllllllIlllIIlIIllIIllI.name = "undefined";
            llllllllllllllllIlllIIlIIllIIllI.description = "";
        }
    }
}

