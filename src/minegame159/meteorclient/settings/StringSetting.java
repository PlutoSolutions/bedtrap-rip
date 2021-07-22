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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class StringSetting
extends Setting<String> {
    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((String)object);
    }

    @Override
    public String fromTag(class_2487 class_24872) {
        this.set(class_24872.method_10558("value"));
        return (String)this.get();
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    protected String parseImpl(String string) {
        return string;
    }

    @Override
    protected boolean isValueValid(String string) {
        return true;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public StringSetting(String string, String string2, String string3, Consumer<String> consumer, Consumer<Setting<String>> consumer2, IVisible iVisible) {
        super(string, string2, string3, consumer, consumer2, iVisible);
        this.value = string3;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10582("value", (String)this.get());
        return class_24872;
    }

    @Override
    public void reset(boolean bl) {
        this.value = this.defaultValue;
        if (bl) {
            this.changed();
        }
    }

    public static class Builder {
        private IVisible visible;
        private String description = "";
        private String defaultValue;
        private String name = "undefined";
        private Consumer<String> onChanged;
        private Consumer<Setting<String>> onModuleActivated;

        public Builder onModuleActivated(Consumer<Setting<String>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder onChanged(Consumer<String> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public StringSetting build() {
            return new StringSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder defaultValue(String string) {
            this.defaultValue = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }
    }
}

