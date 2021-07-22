/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.IGetter;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

public abstract class Setting<T>
implements IGetter<T>,
ISerializable<T> {
    private final Consumer<T> onChanged;
    public final Consumer<Setting<T>> onModuleActivated;
    private final IVisible visible;
    public final String title;
    public Module module;
    protected T value;
    private static final List<String> NO_SUGGESTIONS = new ArrayList<String>(0);
    public final String description;
    public final String name;
    protected final T defaultValue;
    public boolean lastWasVisible;

    public String toString() {
        return this.value.toString();
    }

    public void reset() {
        this.reset(true);
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public void onActivated() {
        if (this.onModuleActivated != null) {
            this.onModuleActivated.accept(this);
        }
    }

    public Iterable<class_2960> getIdentifierSuggestions() {
        return null;
    }

    public boolean parse(String string) {
        T t = this.parseImpl(string);
        if (t != null && this.isValueValid(t)) {
            this.value = t;
            this.changed();
        }
        return t != null;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Setting setting = (Setting)object;
        return Objects.equals(this.name, setting.name);
    }

    public void reset(boolean bl) {
        this.value = this.defaultValue;
        if (bl) {
            this.changed();
        }
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public Setting(String string, String string2, T t, Consumer<T> consumer, Consumer<Setting<T>> consumer2, IVisible iVisible) {
        this.name = string;
        this.title = Utils.nameToTitle(string);
        this.description = string2;
        this.defaultValue = t;
        this.reset(false);
        this.onChanged = consumer;
        this.onModuleActivated = consumer2;
        this.visible = iVisible;
    }

    public static <T> T parseId(class_2378<T> class_23782, String string) {
        class_2960 class_29602 = (string = string.trim()).contains(":") ? new class_2960(string) : new class_2960("minecraft", string);
        if (class_23782.method_10250(class_29602)) {
            return (T)class_23782.method_10223(class_29602);
        }
        return null;
    }

    protected abstract boolean isValueValid(T var1);

    protected abstract T parseImpl(String var1);

    public List<String> getSuggestions() {
        return NO_SUGGESTIONS;
    }

    @Override
    public T get() {
        return this.value;
    }

    public boolean set(T t) {
        if (!this.isValueValid(t)) {
            return false;
        }
        this.value = t;
        this.changed();
        return true;
    }

    protected class_2487 saveGeneral() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        return class_24872;
    }

    public void changed() {
        if (this.onChanged != null) {
            this.onChanged.accept(this.value);
        }
    }

    public boolean isVisible() {
        return this.visible == null || this.visible.isVisible();
    }
}

