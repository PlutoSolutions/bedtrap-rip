/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ColorSetting
extends Setting<SettingColor> {
    private static final List<String> SUGGESTIONS = ImmutableList.of((Object)"0 0 0 255", (Object)"225 25 25 255", (Object)"25 225 25 255", (Object)"25 25 225 255", (Object)"255 255 255 255");

    public ColorSetting(String string, String string2, SettingColor settingColor, Consumer<SettingColor> consumer, Consumer<Setting<SettingColor>> consumer2, IVisible iVisible) {
        super(string, string2, settingColor, consumer, consumer2, iVisible);
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
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10566("value", (class_2520)((SettingColor)this.get()).toTag());
        return class_24872;
    }

    @Override
    public List<String> getSuggestions() {
        return SUGGESTIONS;
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((SettingColor)object);
    }

    @Override
    public void reset(boolean bl) {
        if (this.value == null) {
            this.value = new SettingColor((SettingColor)this.defaultValue);
        } else {
            ((SettingColor)this.value).set((Color)this.defaultValue);
        }
        if (bl) {
            this.changed();
        }
    }

    @Override
    protected SettingColor parseImpl(String string) {
        try {
            String[] arrstring = string.split(" ");
            return new SettingColor(Integer.parseInt(arrstring[0]), Integer.parseInt(arrstring[1]), Integer.parseInt(arrstring[2]), Integer.parseInt(arrstring[3]));
        }
        catch (IndexOutOfBoundsException | NumberFormatException runtimeException) {
            return null;
        }
    }

    @Override
    public SettingColor fromTag(class_2487 class_24872) {
        ((SettingColor)this.get()).fromTag(class_24872.method_10562("value"));
        this.changed();
        return (SettingColor)this.get();
    }

    @Override
    protected boolean isValueValid(SettingColor settingColor) {
        settingColor.validate();
        return true;
    }

    public static class Builder {
        private String description = "";
        private String name = "undefined";
        private Consumer<SettingColor> onChanged;
        private SettingColor defaultValue;
        private Consumer<Setting<SettingColor>> onModuleActivated;
        private IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<SettingColor>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public ColorSetting build() {
            return new ColorSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<SettingColor> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder defaultValue(SettingColor settingColor) {
            this.defaultValue = settingColor;
            return this;
        }
    }
}

