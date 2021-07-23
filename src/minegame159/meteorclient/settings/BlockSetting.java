/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import java.util.function.Predicate;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class BlockSetting
extends Setting<class_2248> {
    public final Predicate<class_2248> filter;

    @Override
    protected class_2248 parseImpl(String string) {
        return (class_2248)BlockSetting.parseId(class_2378.field_11146, string);
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11146.method_10235();
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((class_2248)object);
    }

    @Override
    protected boolean isValueValid(class_2248 class_22482) {
        return this.filter == null || this.filter.test(class_22482);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("value", class_2378.field_11146.method_10221((Object)((class_2248)this.get())).toString());
        return class_24872;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public class_2248 fromTag(class_2487 class_24872) {
        this.value = class_2378.field_11146.method_10223(new class_2960(class_24872.method_10558("value")));
        if (this.filter != null && !this.filter.test((class_2248)this.value)) {
            for (class_2248 class_22482 : class_2378.field_11146) {
                if (!this.filter.test(class_22482)) continue;
                this.value = class_22482;
                break;
            }
        }
        this.changed();
        return (class_2248)this.get();
    }

    public BlockSetting(String string, String string2, class_2248 class_22482, Consumer<class_2248> consumer, Consumer<Setting<class_2248>> consumer2, IVisible iVisible, Predicate<class_2248> predicate) {
        super(string, string2, class_22482, consumer, consumer2, iVisible);
        this.filter = predicate;
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    public static class Builder {
        private String name = "undefined";
        private IVisible visible;
        private String description = "";
        private Consumer<class_2248> onChanged;
        private class_2248 defaultValue;
        private Consumer<Setting<class_2248>> onModuleActivated;
        private Predicate<class_2248> filter;

        public Builder filter(Predicate<class_2248> predicate) {
            this.filter = predicate;
            return this;
        }

        public Builder defaultValue(class_2248 class_22482) {
            this.defaultValue = class_22482;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<class_2248> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public BlockSetting build() {
            return new BlockSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.filter);
        }

        public Builder onModuleActivated(Consumer<Setting<class_2248>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }
    }
}

