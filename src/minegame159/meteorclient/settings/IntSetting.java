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
public class IntSetting
extends Setting<Integer> {
    private final Integer sliderMax;
    private final Integer sliderMin;
    public final Integer min;
    public final Integer max;

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Integer)object);
    }

    private IntSetting(String string, String string2, Integer n, Consumer<Integer> consumer, Consumer<Setting<Integer>> consumer2, IVisible iVisible, Integer n2, Integer n3, Integer n4, Integer n5) {
        super(string, string2, n, consumer, consumer2, iVisible);
        this.min = n2;
        this.max = n3;
        this.sliderMin = n4;
        this.sliderMax = n5;
    }

    @Override
    protected boolean isValueValid(Integer n) {
        return !(this.min != null && n < this.min || this.max != null && n > this.max);
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    IntSetting(String string, String string2, Integer n, Consumer consumer, Consumer consumer2, IVisible iVisible, Integer n2, Integer n3, Integer n4, Integer n5, 1 var11_11) {
        this(string, string2, n, consumer, consumer2, iVisible, n2, n3, n4, n5);
    }

    public int getSliderMax() {
        return this.sliderMax != null ? this.sliderMax : 10;
    }

    @Override
    public Integer fromTag(class_2487 class_24872) {
        this.set(class_24872.method_10550("value"));
        return (Integer)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10569("value", ((Integer)this.get()).intValue());
        return class_24872;
    }

    @Override
    protected Integer parseImpl(String string) {
        try {
            return Integer.parseInt(string.trim());
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    public int getSliderMin() {
        return this.sliderMin != null ? this.sliderMin : 0;
    }

    public static class Builder {
        private String name = "undefined";
        private Consumer<Setting<Integer>> onModuleActivated;
        private Integer sliderMin;
        private String description = "";
        private Integer sliderMax;
        private Consumer<Integer> onChanged;
        private Integer min;
        private Integer max;
        private Integer defaultValue;
        private IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<Integer>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder defaultValue(int n) {
            this.defaultValue = n;
            return this;
        }

        public Builder sliderMax(int n) {
            this.sliderMax = n;
            return this;
        }

        public IntSetting build() {
            return new IntSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.min, this.max, this.sliderMin, this.sliderMax, null);
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder min(int n) {
            this.min = n;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder sliderMin(int n) {
            this.sliderMin = n;
            return this;
        }

        public Builder onChanged(Consumer<Integer> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder max(int n) {
            this.max = n;
            return this;
        }
    }
}

