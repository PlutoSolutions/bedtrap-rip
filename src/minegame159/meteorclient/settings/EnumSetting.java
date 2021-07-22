/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.settings;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2487;

public class EnumSetting<T extends Enum<?>>
extends Setting<T> {
    private final /* synthetic */ List<String> suggestions;
    private /* synthetic */ T[] values;

    @Override
    public class_2487 toTag() {
        EnumSetting llllIlIllIlIllI;
        class_2487 llllIlIllIlIlIl = llllIlIllIlIllI.saveGeneral();
        llllIlIllIlIlIl.method_10582("value", ((Enum)llllIlIllIlIllI.get()).toString());
        return llllIlIllIlIlIl;
    }

    public EnumSetting(String llllIlIllllIllI, String llllIlIllllllII, T llllIlIlllllIll, Consumer<T> llllIlIlllllIlI, Consumer<Setting<T>> llllIlIllllIIlI, IVisible llllIlIlllllIII) {
        super(llllIlIllllIllI, llllIlIllllllII, llllIlIlllllIll, llllIlIlllllIlI, llllIlIllllIIlI, llllIlIlllllIII);
        EnumSetting llllIlIllllIlll;
        try {
            llllIlIllllIlll.values = (Enum[])llllIlIlllllIll.getClass().getMethod("values", new Class[0]).invoke(null, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException llllIllIIIIIIII) {
            llllIllIIIIIIII.printStackTrace();
        }
        llllIlIllllIlll.suggestions = new ArrayList<String>(llllIlIllllIlll.values.length);
        for (T llllIlIllllllll : llllIlIllllIlll.values) {
            llllIlIllllIlll.suggestions.add(((Enum)llllIlIllllllll).toString());
        }
    }

    @Override
    public List<String> getSuggestions() {
        EnumSetting llllIlIllIllIIl;
        return llllIlIllIllIIl.suggestions;
    }

    @Override
    public T fromTag(class_2487 llllIlIllIIllIl) {
        EnumSetting llllIlIllIIlllI;
        llllIlIllIIlllI.parse(llllIlIllIIllIl.method_10558("value"));
        return (T)((Enum)llllIlIllIIlllI.get());
    }

    @Override
    protected boolean isValueValid(T llllIlIllIlllII) {
        return true;
    }

    @Override
    protected T parseImpl(String llllIlIlllIIIlI) {
        EnumSetting llllIlIlllIIIll;
        for (T llllIlIlllIIllI : llllIlIlllIIIll.values) {
            if (!llllIlIlllIIIlI.equalsIgnoreCase(((Enum)llllIlIlllIIllI).toString())) continue;
            return llllIlIlllIIllI;
        }
        return null;
    }

    public static class Builder<T extends Enum<?>> {
        protected /* synthetic */ String name;
        protected /* synthetic */ Consumer<Setting<T>> onModuleActivated;
        protected /* synthetic */ String description;
        protected /* synthetic */ IVisible visible;
        protected /* synthetic */ T defaultValue;
        protected /* synthetic */ Consumer<T> onChanged;

        public Builder<T> defaultValue(T lllllllllllllllllIIIlIllIIIIlIll) {
            Builder lllllllllllllllllIIIlIllIIIIllII;
            lllllllllllllllllIIIlIllIIIIllII.defaultValue = lllllllllllllllllIIIlIllIIIIlIll;
            return lllllllllllllllllIIIlIllIIIIllII;
        }

        public Builder<T> name(String lllllllllllllllllIIIlIllIIIllIIl) {
            Builder lllllllllllllllllIIIlIllIIIllIlI;
            lllllllllllllllllIIIlIllIIIllIlI.name = lllllllllllllllllIIIlIllIIIllIIl;
            return lllllllllllllllllIIIlIllIIIllIlI;
        }

        public Builder<T> onModuleActivated(Consumer<Setting<T>> lllllllllllllllllIIIlIlIllllllll) {
            Builder lllllllllllllllllIIIlIllIIIIIIII;
            lllllllllllllllllIIIlIllIIIIIIII.onModuleActivated = lllllllllllllllllIIIlIlIllllllll;
            return lllllllllllllllllIIIlIllIIIIIIII;
        }

        public Builder<T> description(String lllllllllllllllllIIIlIllIIIlIIll) {
            Builder lllllllllllllllllIIIlIllIIIlIIlI;
            lllllllllllllllllIIIlIllIIIlIIlI.description = lllllllllllllllllIIIlIllIIIlIIll;
            return lllllllllllllllllIIIlIllIIIlIIlI;
        }

        public Builder<T> onChanged(Consumer<T> lllllllllllllllllIIIlIllIIIIIlIl) {
            Builder lllllllllllllllllIIIlIllIIIIIllI;
            lllllllllllllllllIIIlIllIIIIIllI.onChanged = lllllllllllllllllIIIlIllIIIIIlIl;
            return lllllllllllllllllIIIlIllIIIIIllI;
        }

        public Builder() {
            Builder lllllllllllllllllIIIlIllIIIllllI;
            lllllllllllllllllIIIlIllIIIllllI.name = "undefined";
            lllllllllllllllllIIIlIllIIIllllI.description = "";
        }

        public Builder<T> visible(IVisible lllllllllllllllllIIIlIlIlllllIIl) {
            Builder lllllllllllllllllIIIlIlIlllllIlI;
            lllllllllllllllllIIIlIlIlllllIlI.visible = lllllllllllllllllIIIlIlIlllllIIl;
            return lllllllllllllllllIIIlIlIlllllIlI;
        }

        public EnumSetting<T> build() {
            Builder lllllllllllllllllIIIlIlIllllIlll;
            return new EnumSetting<T>(lllllllllllllllllIIIlIlIllllIlll.name, lllllllllllllllllIIIlIlIllllIlll.description, lllllllllllllllllIIIlIlIllllIlll.defaultValue, lllllllllllllllllIIIlIlIllllIlll.onChanged, lllllllllllllllllIIIlIlIllllIlll.onModuleActivated, lllllllllllllllllIIIlIlIllllIlll.visible);
        }
    }
}

