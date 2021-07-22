/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.settings;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2487;

public class BoolSetting
extends Setting<Boolean> {
    private static final /* synthetic */ List<String> SUGGESTIONS;

    @Override
    protected boolean isValueValid(Boolean lllllllllllllllllIIIlllIlllIIlll) {
        return true;
    }

    @Override
    public Boolean fromTag(class_2487 lllllllllllllllllIIIlllIllIllIlI) {
        BoolSetting lllllllllllllllllIIIlllIllIlllIl;
        lllllllllllllllllIIIlllIllIlllIl.set(lllllllllllllllllIIIlllIllIllIlI.method_10577("value"));
        return (Boolean)lllllllllllllllllIIIlllIllIlllIl.get();
    }

    @Override
    protected Boolean parseImpl(String lllllllllllllllllIIIlllIlllIlIIl) {
        if (lllllllllllllllllIIIlllIlllIlIIl.equalsIgnoreCase("true") || lllllllllllllllllIIIlllIlllIlIIl.equalsIgnoreCase("1")) {
            return true;
        }
        if (lllllllllllllllllIIIlllIlllIlIIl.equalsIgnoreCase("false") || lllllllllllllllllIIIlllIlllIlIIl.equalsIgnoreCase("0")) {
            return false;
        }
        if (lllllllllllllllllIIIlllIlllIlIIl.equalsIgnoreCase("toggle")) {
            BoolSetting lllllllllllllllllIIIlllIlllIllII;
            return (Boolean)lllllllllllllllllIIIlllIlllIllII.get() == false;
        }
        return null;
    }

    @Override
    public List<String> getSuggestions() {
        return SUGGESTIONS;
    }

    @Override
    public class_2487 toTag() {
        BoolSetting lllllllllllllllllIIIlllIlllIIIll;
        class_2487 lllllllllllllllllIIIlllIlllIIIlI = lllllllllllllllllIIIlllIlllIIIll.saveGeneral();
        lllllllllllllllllIIIlllIlllIIIlI.method_10556("value", ((Boolean)lllllllllllllllllIIIlllIlllIIIll.get()).booleanValue());
        return lllllllllllllllllIIIlllIlllIIIlI;
    }

    private BoolSetting(String lllllllllllllllllIIIlllIlllllIll, String lllllllllllllllllIIIlllIllllIIll, Boolean lllllllllllllllllIIIlllIllllIIlI, Consumer<Boolean> lllllllllllllllllIIIlllIlllllIII, Consumer<Setting<Boolean>> lllllllllllllllllIIIlllIllllIIII, IVisible lllllllllllllllllIIIlllIllllIllI) {
        super(lllllllllllllllllIIIlllIlllllIll, lllllllllllllllllIIIlllIllllIIll, lllllllllllllllllIIIlllIllllIIlI, lllllllllllllllllIIIlllIlllllIII, lllllllllllllllllIIIlllIllllIIII, lllllllllllllllllIIIlllIllllIllI);
        BoolSetting lllllllllllllllllIIIlllIllllllII;
    }

    static {
        SUGGESTIONS = ImmutableList.of((Object)"true", (Object)"false", (Object)"toggle");
    }

    public static class Builder {
        private /* synthetic */ String description;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Boolean defaultValue;
        private /* synthetic */ Consumer<Boolean> onChanged;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<Setting<Boolean>> onModuleActivated;

        public Builder() {
            Builder llllllllllllllllIllIIIIlllIlllll;
            llllllllllllllllIllIIIIlllIlllll.name = "undefined";
            llllllllllllllllIllIIIIlllIlllll.description = "";
        }

        public Builder defaultValue(boolean llllllllllllllllIllIIIIlllIIllll) {
            Builder llllllllllllllllIllIIIIlllIlIIII;
            llllllllllllllllIllIIIIlllIlIIII.defaultValue = llllllllllllllllIllIIIIlllIIllll;
            return llllllllllllllllIllIIIIlllIlIIII;
        }

        public Builder name(String llllllllllllllllIllIIIIlllIllIll) {
            Builder llllllllllllllllIllIIIIlllIllIlI;
            llllllllllllllllIllIIIIlllIllIlI.name = llllllllllllllllIllIIIIlllIllIll;
            return llllllllllllllllIllIIIIlllIllIlI;
        }

        public Builder description(String llllllllllllllllIllIIIIlllIlIIll) {
            Builder llllllllllllllllIllIIIIlllIlIlII;
            llllllllllllllllIllIIIIlllIlIlII.description = llllllllllllllllIllIIIIlllIlIIll;
            return llllllllllllllllIllIIIIlllIlIlII;
        }

        public Builder onModuleActivated(Consumer<Setting<Boolean>> llllllllllllllllIllIIIIlllIIIIll) {
            Builder llllllllllllllllIllIIIIlllIIIlII;
            llllllllllllllllIllIIIIlllIIIlII.onModuleActivated = llllllllllllllllIllIIIIlllIIIIll;
            return llllllllllllllllIllIIIIlllIIIlII;
        }

        public BoolSetting build() {
            Builder llllllllllllllllIllIIIIllIlllIIl;
            return new BoolSetting(llllllllllllllllIllIIIIllIlllIIl.name, llllllllllllllllIllIIIIllIlllIIl.description, llllllllllllllllIllIIIIllIlllIIl.defaultValue, llllllllllllllllIllIIIIllIlllIIl.onChanged, llllllllllllllllIllIIIIllIlllIIl.onModuleActivated, llllllllllllllllIllIIIIllIlllIIl.visible);
        }

        public Builder visible(IVisible llllllllllllllllIllIIIIllIllllIl) {
            Builder llllllllllllllllIllIIIIllIlllllI;
            llllllllllllllllIllIIIIllIlllllI.visible = llllllllllllllllIllIIIIllIllllIl;
            return llllllllllllllllIllIIIIllIlllllI;
        }

        public Builder onChanged(Consumer<Boolean> llllllllllllllllIllIIIIlllIIlIIl) {
            Builder llllllllllllllllIllIIIIlllIIlIlI;
            llllllllllllllllIllIIIIlllIIlIlI.onChanged = llllllllllllllllIllIIIIlllIIlIIl;
            return llllllllllllllllIllIIIIlllIIlIlI;
        }
    }
}

