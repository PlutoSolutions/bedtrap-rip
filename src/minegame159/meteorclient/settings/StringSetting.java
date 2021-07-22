/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2487;

public class StringSetting
extends Setting<String> {
    @Override
    public String fromTag(class_2487 lllllIllIlIll) {
        StringSetting lllllIllIlIlI;
        lllllIllIlIlI.set(lllllIllIlIll.method_10558("value"));
        return (String)lllllIllIlIlI.get();
    }

    @Override
    protected String parseImpl(String lllllIllllllI) {
        return lllllIllllllI;
    }

    @Override
    protected boolean isValueValid(String lllllIlllIlIl) {
        return true;
    }

    public StringSetting(String llllllIIIllIl, String llllllIIIIlIl, String llllllIIIlIll, Consumer<String> llllllIIIIIll, Consumer<Setting<String>> llllllIIIlIIl, IVisible llllllIIIIIIl) {
        super(llllllIIIllIl, llllllIIIIlIl, llllllIIIlIll, llllllIIIIIll, llllllIIIlIIl, llllllIIIIIIl);
        StringSetting llllllIIIlllI;
        llllllIIIlllI.value = llllllIIIlIll;
    }

    @Override
    public class_2487 toTag() {
        StringSetting lllllIlllIIII;
        class_2487 lllllIlllIIIl = lllllIlllIIII.saveGeneral();
        lllllIlllIIIl.method_10582("value", (String)lllllIlllIIII.get());
        return lllllIlllIIIl;
    }

    @Override
    public void reset(boolean lllllIllllIIl) {
        StringSetting lllllIllllIlI;
        lllllIllllIlI.value = lllllIllllIlI.defaultValue;
        if (lllllIllllIIl) {
            lllllIllllIlI.changed();
        }
    }

    public static class Builder {
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String description;
        private /* synthetic */ String defaultValue;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<String> onChanged;
        private /* synthetic */ Consumer<Setting<String>> onModuleActivated;

        public Builder onModuleActivated(Consumer<Setting<String>> llllllIlIlIIll) {
            Builder llllllIlIlIIlI;
            llllllIlIlIIlI.onModuleActivated = llllllIlIlIIll;
            return llllllIlIlIIlI;
        }

        public Builder onChanged(Consumer<String> llllllIlIlIlll) {
            Builder llllllIlIllIII;
            llllllIlIllIII.onChanged = llllllIlIlIlll;
            return llllllIlIllIII;
        }

        public Builder() {
            Builder llllllIllIllll;
            llllllIllIllll.name = "undefined";
            llllllIllIllll.description = "";
        }

        public StringSetting build() {
            Builder llllllIlIIlIIl;
            return new StringSetting(llllllIlIIlIIl.name, llllllIlIIlIIl.description, llllllIlIIlIIl.defaultValue, llllllIlIIlIIl.onChanged, llllllIlIIlIIl.onModuleActivated, llllllIlIIlIIl.visible);
        }

        public Builder description(String llllllIllIIlIl) {
            Builder llllllIllIIlII;
            llllllIllIIlII.description = llllllIllIIlIl;
            return llllllIllIIlII;
        }

        public Builder defaultValue(String llllllIlIlllll) {
            Builder llllllIllIIIII;
            llllllIllIIIII.defaultValue = llllllIlIlllll;
            return llllllIllIIIII;
        }

        public Builder visible(IVisible llllllIlIIlIll) {
            Builder llllllIlIIllII;
            llllllIlIIllII.visible = llllllIlIIlIll;
            return llllllIlIIllII;
        }

        public Builder name(String llllllIllIlIIl) {
            Builder llllllIllIlIlI;
            llllllIllIlIlI.name = llllllIllIlIIl;
            return llllllIllIlIlI;
        }
    }
}

