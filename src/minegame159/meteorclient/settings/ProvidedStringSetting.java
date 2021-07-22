/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import java.util.function.Supplier;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.StringSetting;

public class ProvidedStringSetting
extends StringSetting {
    public final /* synthetic */ Supplier<String[]> supplier;

    public ProvidedStringSetting(String llllllllllllllllIllIlIlIlIIIIlIl, String llllllllllllllllIllIlIlIIlllllII, String llllllllllllllllIllIlIlIIllllIll, Consumer<String> llllllllllllllllIllIlIlIlIIIIIlI, Consumer<Setting<String>> llllllllllllllllIllIlIlIlIIIIIIl, IVisible llllllllllllllllIllIlIlIlIIIIIII, Supplier<String[]> llllllllllllllllIllIlIlIIlllllll) {
        super(llllllllllllllllIllIlIlIlIIIIlIl, llllllllllllllllIllIlIlIIlllllII, llllllllllllllllIllIlIlIIllllIll, llllllllllllllllIllIlIlIlIIIIIlI, llllllllllllllllIllIlIlIlIIIIIIl, llllllllllllllllIllIlIlIlIIIIIII);
        ProvidedStringSetting llllllllllllllllIllIlIlIIllllllI;
        llllllllllllllllIllIlIlIIllllllI.supplier = llllllllllllllllIllIlIlIIlllllll;
    }

    public static class Builder {
        private /* synthetic */ String defaultValue;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Supplier<String[]> supplier;
        private /* synthetic */ Consumer<Setting<String>> onModuleActivated;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<String> onChanged;
        private /* synthetic */ String description;

        public ProvidedStringSetting build() {
            Builder lllllllllllllllllIIIIIIIllIllIII;
            return new ProvidedStringSetting(lllllllllllllllllIIIIIIIllIllIII.name, lllllllllllllllllIIIIIIIllIllIII.description, lllllllllllllllllIIIIIIIllIllIII.defaultValue, lllllllllllllllllIIIIIIIllIllIII.onChanged, lllllllllllllllllIIIIIIIllIllIII.onModuleActivated, lllllllllllllllllIIIIIIIllIllIII.visible, lllllllllllllllllIIIIIIIllIllIII.supplier);
        }

        public Builder visible(IVisible lllllllllllllllllIIIIIIIlllIIIIl) {
            Builder lllllllllllllllllIIIIIIIlllIIlII;
            lllllllllllllllllIIIIIIIlllIIlII.visible = lllllllllllllllllIIIIIIIlllIIIIl;
            return lllllllllllllllllIIIIIIIlllIIlII;
        }

        public Builder defaultValue(String lllllllllllllllllIIIIIIIllllIlIl) {
            Builder lllllllllllllllllIIIIIIIllllIlII;
            lllllllllllllllllIIIIIIIllllIlII.defaultValue = lllllllllllllllllIIIIIIIllllIlIl;
            return lllllllllllllllllIIIIIIIllllIlII;
        }

        public Builder() {
            Builder lllllllllllllllllIIIIIIlIIIIIllI;
            lllllllllllllllllIIIIIIlIIIIIllI.name = "undefined";
            lllllllllllllllllIIIIIIlIIIIIllI.description = "";
        }

        public Builder onModuleActivated(Consumer<Setting<String>> lllllllllllllllllIIIIIIIlllIIlll) {
            Builder lllllllllllllllllIIIIIIIlllIlIlI;
            lllllllllllllllllIIIIIIIlllIlIlI.onModuleActivated = lllllllllllllllllIIIIIIIlllIIlll;
            return lllllllllllllllllIIIIIIIlllIlIlI;
        }

        public Builder supplier(Supplier<String[]> lllllllllllllllllIIIIIIIllIlllIl) {
            Builder lllllllllllllllllIIIIIIIllIllllI;
            lllllllllllllllllIIIIIIIllIllllI.supplier = lllllllllllllllllIIIIIIIllIlllIl;
            return lllllllllllllllllIIIIIIIllIllllI;
        }

        public Builder name(String lllllllllllllllllIIIIIIlIIIIIIIl) {
            Builder lllllllllllllllllIIIIIIlIIIIIIlI;
            lllllllllllllllllIIIIIIlIIIIIIlI.name = lllllllllllllllllIIIIIIlIIIIIIIl;
            return lllllllllllllllllIIIIIIlIIIIIIlI;
        }

        public Builder onChanged(Consumer<String> lllllllllllllllllIIIIIIIlllIllIl) {
            Builder lllllllllllllllllIIIIIIIlllIlllI;
            lllllllllllllllllIIIIIIIlllIlllI.onChanged = lllllllllllllllllIIIIIIIlllIllIl;
            return lllllllllllllllllIIIIIIIlllIlllI;
        }

        public Builder description(String lllllllllllllllllIIIIIIIlllllIll) {
            Builder lllllllllllllllllIIIIIIIlllllIlI;
            lllllllllllllllllIIIIIIIlllllIlI.description = lllllllllllllllllIIIIIIIlllllIll;
            return lllllllllllllllllIIIIIIIlllllIlI;
        }
    }
}

