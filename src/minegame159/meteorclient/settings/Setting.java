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
    private final /* synthetic */ Consumer<T> onChanged;
    public final /* synthetic */ Consumer<Setting<T>> onModuleActivated;
    private final /* synthetic */ IVisible visible;
    public final /* synthetic */ String title;
    public /* synthetic */ Module module;
    protected /* synthetic */ T value;
    private static final /* synthetic */ List<String> NO_SUGGESTIONS;
    public final /* synthetic */ String description;
    public final /* synthetic */ String name;
    protected final /* synthetic */ T defaultValue;
    public /* synthetic */ boolean lastWasVisible;

    public String toString() {
        Setting lllllllllllllllllIIlIIllllIIlllI;
        return lllllllllllllllllIIlIIllllIIlllI.value.toString();
    }

    public void reset() {
        Setting lllllllllllllllllIIlIIlllllIlllI;
        lllllllllllllllllIIlIIlllllIlllI.reset(true);
    }

    public int hashCode() {
        Setting lllllllllllllllllIIlIIllllIIIIll;
        return Objects.hash(lllllllllllllllllIIlIIllllIIIIll.name);
    }

    public void onActivated() {
        Setting lllllllllllllllllIIlIIllllIlllII;
        if (lllllllllllllllllIIlIIllllIlllII.onModuleActivated != null) {
            lllllllllllllllllIIlIIllllIlllII.onModuleActivated.accept(lllllllllllllllllIIlIIllllIlllII);
        }
    }

    public Iterable<class_2960> getIdentifierSuggestions() {
        return null;
    }

    public boolean parse(String lllllllllllllllllIIlIIlllllIIIll) {
        Setting lllllllllllllllllIIlIIlllllIIlll;
        T lllllllllllllllllIIlIIlllllIIlIl = lllllllllllllllllIIlIIlllllIIlll.parseImpl(lllllllllllllllllIIlIIlllllIIIll);
        if (lllllllllllllllllIIlIIlllllIIlIl != null && lllllllllllllllllIIlIIlllllIIlll.isValueValid(lllllllllllllllllIIlIIlllllIIlIl)) {
            lllllllllllllllllIIlIIlllllIIlll.value = lllllllllllllllllIIlIIlllllIIlIl;
            lllllllllllllllllIIlIIlllllIIlll.changed();
        }
        return lllllllllllllllllIIlIIlllllIIlIl != null;
    }

    public boolean equals(Object lllllllllllllllllIIlIIllllIIIllI) {
        Setting lllllllllllllllllIIlIIllllIIIlll;
        if (lllllllllllllllllIIlIIllllIIIlll == lllllllllllllllllIIlIIllllIIIllI) {
            return true;
        }
        if (lllllllllllllllllIIlIIllllIIIllI == null || lllllllllllllllllIIlIIllllIIIlll.getClass() != lllllllllllllllllIIlIIllllIIIllI.getClass()) {
            return false;
        }
        Setting lllllllllllllllllIIlIIllllIIlIII = (Setting)lllllllllllllllllIIlIIllllIIIllI;
        return Objects.equals(lllllllllllllllllIIlIIllllIIIlll.name, lllllllllllllllllIIlIIllllIIlIII.name);
    }

    public void reset(boolean lllllllllllllllllIIlIIllllllIIIl) {
        Setting lllllllllllllllllIIlIIllllllIlII;
        lllllllllllllllllIIlIIllllllIlII.value = lllllllllllllllllIIlIIllllllIlII.defaultValue;
        if (lllllllllllllllllIIlIIllllllIIIl) {
            lllllllllllllllllIIlIIllllllIlII.changed();
        }
    }

    public T getDefaultValue() {
        Setting lllllllllllllllllIIlIIlllllIlIll;
        return lllllllllllllllllIIlIIlllllIlIll.defaultValue;
    }

    public Setting(String lllllllllllllllllIIlIlIIIIIIllII, String lllllllllllllllllIIlIlIIIIIIIlII, T lllllllllllllllllIIlIlIIIIIIlIlI, Consumer<T> lllllllllllllllllIIlIlIIIIIIlIIl, Consumer<Setting<T>> lllllllllllllllllIIlIlIIIIIIlIII, IVisible lllllllllllllllllIIlIlIIIIIIIIII) {
        Setting lllllllllllllllllIIlIlIIIIIIllIl;
        lllllllllllllllllIIlIlIIIIIIllIl.name = lllllllllllllllllIIlIlIIIIIIllII;
        lllllllllllllllllIIlIlIIIIIIllIl.title = Utils.nameToTitle(lllllllllllllllllIIlIlIIIIIIllII);
        lllllllllllllllllIIlIlIIIIIIllIl.description = lllllllllllllllllIIlIlIIIIIIIlII;
        lllllllllllllllllIIlIlIIIIIIllIl.defaultValue = lllllllllllllllllIIlIlIIIIIIlIlI;
        lllllllllllllllllIIlIlIIIIIIllIl.reset(false);
        lllllllllllllllllIIlIlIIIIIIllIl.onChanged = lllllllllllllllllIIlIlIIIIIIlIIl;
        lllllllllllllllllIIlIlIIIIIIllIl.onModuleActivated = lllllllllllllllllIIlIlIIIIIIlIII;
        lllllllllllllllllIIlIlIIIIIIllIl.visible = lllllllllllllllllIIlIlIIIIIIIIII;
    }

    public static <T> T parseId(class_2378<T> lllllllllllllllllIIlIIlllIlllIlI, String lllllllllllllllllIIlIIlllIllllII) {
        class_2960 lllllllllllllllllIIlIIlllIlllIll;
        if ((lllllllllllllllllIIlIIlllIllllII = lllllllllllllllllIIlIIlllIllllII.trim()).contains(":")) {
            class_2960 lllllllllllllllllIIlIIlllIlllllI = new class_2960(lllllllllllllllllIIlIIlllIllllII);
        } else {
            lllllllllllllllllIIlIIlllIlllIll = new class_2960("minecraft", lllllllllllllllllIIlIIlllIllllII);
        }
        if (lllllllllllllllllIIlIIlllIlllIlI.method_10250(lllllllllllllllllIIlIIlllIlllIll)) {
            return (T)lllllllllllllllllIIlIIlllIlllIlI.method_10223(lllllllllllllllllIIlIIlllIlllIll);
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
        Setting lllllllllllllllllIIlIIllllllllIl;
        return lllllllllllllllllIIlIIllllllllIl.value;
    }

    public boolean set(T lllllllllllllllllIIlIIllllllIlll) {
        Setting lllllllllllllllllIIlIIlllllllIII;
        if (!lllllllllllllllllIIlIIlllllllIII.isValueValid(lllllllllllllllllIIlIIllllllIlll)) {
            return false;
        }
        lllllllllllllllllIIlIIlllllllIII.value = lllllllllllllllllIIlIIllllllIlll;
        lllllllllllllllllIIlIIlllllllIII.changed();
        return true;
    }

    static {
        NO_SUGGESTIONS = new ArrayList<String>(0);
    }

    protected class_2487 saveGeneral() {
        Setting lllllllllllllllllIIlIIllllIlIIlI;
        class_2487 lllllllllllllllllIIlIIllllIlIIll = new class_2487();
        lllllllllllllllllIIlIIllllIlIIll.method_10582("name", lllllllllllllllllIIlIIllllIlIIlI.name);
        return lllllllllllllllllIIlIIllllIlIIll;
    }

    public void changed() {
        Setting lllllllllllllllllIIlIIllllIlllll;
        if (lllllllllllllllllIIlIIllllIlllll.onChanged != null) {
            lllllllllllllllllIIlIIllllIlllll.onChanged.accept(lllllllllllllllllIIlIIllllIlllll.value);
        }
    }

    public boolean isVisible() {
        Setting lllllllllllllllllIIlIIllllIllIlI;
        return lllllllllllllllllIIlIIllllIllIlI.visible == null || lllllllllllllllllIIlIIllllIllIlI.visible.isVisible();
    }
}

