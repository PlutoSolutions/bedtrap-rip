/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class DoubleSetting
extends Setting<Double> {
    public final int decimalPlaces;
    public final Double min;
    private final Double sliderMin;
    private final Double sliderMax;
    public final Double max;
    public final boolean onSliderRelease;

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Double)object);
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @Override
    public Double fromTag(class_2487 class_24872) {
        this.set(class_24872.method_10574("value"));
        return (Double)this.get();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10549("value", ((Double)this.get()).doubleValue());
        return class_24872;
    }

    DoubleSetting(String string, String string2, Double d, Consumer consumer, Consumer consumer2, IVisible iVisible, Double d2, Double d3, Double d4, Double d5, boolean bl, int n, 1 var13_13) {
        this(string, string2, d, consumer, consumer2, iVisible, d2, d3, d4, d5, bl, n);
    }

    public double getSliderMin() {
        return this.sliderMin != null ? this.sliderMin : 0.0;
    }

    public double getSliderMax() {
        return this.sliderMax != null ? this.sliderMax : 10.0;
    }

    @Override
    protected boolean isValueValid(Double d) {
        return (this.min == null || d >= this.min) && (this.max == null || d <= this.max);
    }

    @Override
    protected Double parseImpl(String string) {
        try {
            return Double.parseDouble(string.trim());
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    private DoubleSetting(String string, String string2, Double d, Consumer<Double> consumer, Consumer<Setting<Double>> consumer2, IVisible iVisible, Double d2, Double d3, Double d4, Double d5, boolean bl, int n) {
        super(string, string2, d, consumer, consumer2, iVisible);
        this.min = d2;
        this.max = d3;
        this.sliderMin = d4;
        this.sliderMax = d5;
        this.decimalPlaces = n;
        this.onSliderRelease = bl;
    }

    public static class Builder {
        private boolean onSliderRelease;
        private String name = "undefined";
        private Double defaultValue;
        private String description = "";
        private Double max;
        private Double sliderMax;
        private int decimalPlaces = 3;
        private Consumer<Double> onChanged;
        private Consumer<Setting<Double>> onModuleActivated;
        private IVisible visible;
        private Double min;
        private Double sliderMin;

        public Builder defaultValue(double d) {
            this.defaultValue = d;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder onChanged(Consumer<Double> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder onSliderRelease() {
            this.onSliderRelease = true;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<Double>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder decimalPlaces(int n) {
            this.decimalPlaces = n;
            return this;
        }

        public Builder sliderMax(double d) {
            this.sliderMax = d;
            return this;
        }

        public Builder min(double d) {
            this.min = d;
            return this;
        }

        public DoubleSetting build() {
            return new DoubleSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.min, this.max, this.sliderMin, this.sliderMax, this.onSliderRelease, this.decimalPlaces, null);
        }

        public Builder sliderMin(double d) {
            this.sliderMin = d;
            return this;
        }

        public Builder max(double d) {
            this.max = d;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }
    }
}

