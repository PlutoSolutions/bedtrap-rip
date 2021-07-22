/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;

public class BlockListSetting
extends Setting<List<class_2248>> {
    public final Predicate<class_2248> filter;

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((List)object);
    }

    @Override
    public void reset(boolean bl) {
        this.value = new ArrayList((Collection)this.defaultValue);
        if (bl) {
            this.changed();
        }
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11146.method_10235();
    }

    @Override
    public List<class_2248> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            class_2248 class_22482 = (class_2248)class_2378.field_11146.method_10223(new class_2960(class_25202.method_10714()));
            if (this.filter != null && !this.filter.test(class_22482)) continue;
            ((List)this.get()).add(class_22482);
        }
        this.changed();
        return (List)this.get();
    }

    @Override
    protected List<class_2248> parseImpl(String string) {
        String[] arrstring = string.split(",");
        ArrayList<class_2248> arrayList = new ArrayList<class_2248>(arrstring.length);
        try {
            for (String string2 : arrstring) {
                class_2248 class_22482 = (class_2248)BlockListSetting.parseId(class_2378.field_11146, string2);
                if (class_22482 == null || this.filter != null && !this.filter.test(class_22482)) continue;
                arrayList.add(class_22482);
                if (!false) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    public BlockListSetting(String string, String string2, List<class_2248> list, Consumer<List<class_2248>> consumer, Consumer<Setting<List<class_2248>>> consumer2, Predicate<class_2248> predicate, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.filter = predicate;
        this.value = new ArrayList<class_2248>(list);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_2248 class_22482 : (List)this.get()) {
            class_24992.add((Object)class_2519.method_23256((String)class_2378.field_11146.method_10221((Object)class_22482).toString()));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    protected boolean isValueValid(List<class_2248> list) {
        return true;
    }

    public static class Builder {
        private Consumer<Setting<List<class_2248>>> onModuleActivated;
        private String name = "undefined";
        private List<class_2248> defaultValue;
        private Predicate<class_2248> filter;
        private String description = "";
        private Consumer<List<class_2248>> onChanged;
        private IVisible visible;

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder defaultValue(List<class_2248> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder filter(Predicate<class_2248> predicate) {
            this.filter = predicate;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<List<class_2248>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder onChanged(Consumer<List<class_2248>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public BlockListSetting build() {
            return new BlockListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.filter, this.visible);
        }
    }
}

