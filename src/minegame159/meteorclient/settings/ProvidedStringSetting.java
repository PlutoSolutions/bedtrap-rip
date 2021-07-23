/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import java.util.function.Supplier;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.StringSetting;

public class ProvidedStringSetting
extends StringSetting {
    public final Supplier<String[]> supplier;

    public ProvidedStringSetting(String string, String string2, String string3, Consumer<String> consumer, Consumer<Setting<String>> consumer2, IVisible iVisible, Supplier<String[]> supplier) {
        super(string, string2, string3, consumer, consumer2, iVisible);
        this.supplier = supplier;
    }

    public static class Builder {
        private String defaultValue;
        private IVisible visible;
        private Supplier<String[]> supplier;
        private Consumer<Setting<String>> onModuleActivated;
        private String name = "undefined";
        private Consumer<String> onChanged;
        private String description = "";

        public ProvidedStringSetting build() {
            return new ProvidedStringSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.supplier);
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder defaultValue(String string) {
            this.defaultValue = string;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<String>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder supplier(Supplier<String[]> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<String> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }
    }
}

