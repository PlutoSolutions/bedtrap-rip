/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_3414
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_3414;

public class SoundEventListSetting
extends Setting<List<class_3414>> {
    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11156.method_10235();
    }

    @Override
    protected boolean isValueValid(List<class_3414> list) {
        return true;
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((List)object);
    }

    @Override
    protected List<class_3414> parseImpl(String string) {
        String[] arrstring = string.split(",");
        ArrayList<class_3414> arrayList = new ArrayList<class_3414>(arrstring.length);
        try {
            for (String string2 : arrstring) {
                class_3414 class_34142 = (class_3414)SoundEventListSetting.parseId(class_2378.field_11156, string2);
                if (class_34142 == null) continue;
                arrayList.add(class_34142);
                if (-3 <= 0) continue;
                return null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
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

    public SoundEventListSetting(String string, String string2, List<class_3414> list, Consumer<List<class_3414>> consumer, Consumer<Setting<List<class_3414>>> consumer2, IVisible iVisible) {
        super(string, string2, list, consumer, consumer2, iVisible);
        this.value = new ArrayList<class_3414>(list);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_2499 class_24992 = new class_2499();
        for (class_3414 class_34142 : (List)this.get()) {
            class_24992.add((Object)class_2519.method_23256((String)class_2378.field_11156.method_10221((Object)class_34142).toString()));
        }
        class_24872.method_10566("value", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    public List<class_3414> fromTag(class_2487 class_24872) {
        ((List)this.get()).clear();
        class_2499 class_24992 = class_24872.method_10554("value", 8);
        for (class_2520 class_25202 : class_24992) {
            ((List)this.get()).add((class_3414)class_2378.field_11156.method_10223(new class_2960(class_25202.method_10714())));
        }
        this.changed();
        return (List)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public static class Builder {
        private String description = "";
        private Consumer<Setting<List<class_3414>>> onModuleActivated;
        private IVisible visible;
        private Consumer<List<class_3414>> onChanged;
        private String name = "undefined";
        private List<class_3414> defaultValue;

        public Builder onModuleActivated(Consumer<Setting<List<class_3414>>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder defaultValue(List<class_3414> list) {
            this.defaultValue = list;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public SoundEventListSetting build() {
            return new SoundEventListSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<List<class_3414>> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }
    }
}

