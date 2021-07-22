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

public class DoubleSetting
extends Setting<Double> {
    public final /* synthetic */ int decimalPlaces;
    public final /* synthetic */ Double min;
    private final /* synthetic */ Double sliderMin;
    private final /* synthetic */ Double sliderMax;
    public final /* synthetic */ Double max;
    public final /* synthetic */ boolean onSliderRelease;

    @Override
    public Double fromTag(class_2487 lIlIIllllIIIIlI) {
        DoubleSetting lIlIIllllIIIlIl;
        lIlIIllllIIIlIl.set(lIlIIllllIIIIlI.method_10574("value"));
        return (Double)lIlIIllllIIIlIl.get();
    }

    @Override
    public class_2487 toTag() {
        DoubleSetting lIlIIllllIIlIIl;
        class_2487 lIlIIllllIIlIlI = lIlIIllllIIlIIl.saveGeneral();
        lIlIIllllIIlIlI.method_10549("value", ((Double)lIlIIllllIIlIIl.get()).doubleValue());
        return lIlIIllllIIlIlI;
    }

    public double getSliderMin() {
        DoubleSetting lIlIIllllIlIIIl;
        return lIlIIllllIlIIIl.sliderMin != null ? lIlIIllllIlIIIl.sliderMin : 0.0;
    }

    public double getSliderMax() {
        DoubleSetting lIlIIllllIIllll;
        return lIlIIllllIIllll.sliderMax != null ? lIlIIllllIIllll.sliderMax : 10.0;
    }

    @Override
    protected boolean isValueValid(Double lIlIIllllIlIlII) {
        DoubleSetting lIlIIllllIlIlll;
        return (lIlIIllllIlIlll.min == null || lIlIIllllIlIlII >= lIlIIllllIlIlll.min) && (lIlIIllllIlIlll.max == null || lIlIIllllIlIlII <= lIlIIllllIlIlll.max);
    }

    @Override
    protected Double parseImpl(String lIlIIllllIlllII) {
        try {
            return Double.parseDouble(lIlIIllllIlllII.trim());
        }
        catch (NumberFormatException lIlIIllllIllllI) {
            return null;
        }
    }

    private DoubleSetting(String lIlIIlllllIllII, String lIlIIlllllllIII, Double lIlIIllllllIlll, Consumer<Double> lIlIIlllllIlIIl, Consumer<Setting<Double>> lIlIIllllllIlIl, IVisible lIlIIlllllIIlll, Double lIlIIllllllIIll, Double lIlIIlllllIIlIl, Double lIlIIllllllIIIl, Double lIlIIlllllIIIll, boolean lIlIIlllllIllll, int lIlIIlllllIIIIl) {
        super(lIlIIlllllIllII, lIlIIlllllllIII, lIlIIllllllIlll, lIlIIlllllIlIIl, lIlIIllllllIlIl, lIlIIlllllIIlll);
        DoubleSetting lIlIIlllllllIlI;
        lIlIIlllllllIlI.min = lIlIIllllllIIll;
        lIlIIlllllllIlI.max = lIlIIlllllIIlIl;
        lIlIIlllllllIlI.sliderMin = lIlIIllllllIIIl;
        lIlIIlllllllIlI.sliderMax = lIlIIlllllIIIll;
        lIlIIlllllllIlI.decimalPlaces = lIlIIlllllIIIIl;
        lIlIIlllllllIlI.onSliderRelease = lIlIIlllllIllll;
    }

    public static class Builder {
        private /* synthetic */ boolean onSliderRelease;
        private /* synthetic */ String name;
        private /* synthetic */ Double defaultValue;
        private /* synthetic */ String description;
        private /* synthetic */ Double max;
        private /* synthetic */ Double sliderMax;
        private /* synthetic */ int decimalPlaces;
        private /* synthetic */ Consumer<Double> onChanged;
        private /* synthetic */ Consumer<Setting<Double>> onModuleActivated;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ Double min;
        private /* synthetic */ Double sliderMin;

        public Builder defaultValue(double lllllllllllllllllllllIlIIllIIlII) {
            Builder lllllllllllllllllllllIlIIllIIlIl;
            lllllllllllllllllllllIlIIllIIlIl.defaultValue = lllllllllllllllllllllIlIIllIIlII;
            return lllllllllllllllllllllIlIIllIIlIl;
        }

        public Builder name(String lllllllllllllllllllllIlIIllIlllI) {
            Builder lllllllllllllllllllllIlIIlllIIIl;
            lllllllllllllllllllllIlIIlllIIIl.name = lllllllllllllllllllllIlIIllIlllI;
            return lllllllllllllllllllllIlIIlllIIIl;
        }

        public Builder onChanged(Consumer<Double> lllllllllllllllllllllIlIIlIlllII) {
            Builder lllllllllllllllllllllIlIIlIlllIl;
            lllllllllllllllllllllIlIIlIlllIl.onChanged = lllllllllllllllllllllIlIIlIlllII;
            return lllllllllllllllllllllIlIIlIlllIl;
        }

        public Builder onSliderRelease() {
            Builder lllllllllllllllllllllIlIIIllIlIl;
            lllllllllllllllllllllIlIIIllIlIl.onSliderRelease = true;
            return lllllllllllllllllllllIlIIIllIlIl;
        }

        public Builder visible(IVisible lllllllllllllllllllllIlIIlIlIIlI) {
            Builder lllllllllllllllllllllIlIIlIlIIll;
            lllllllllllllllllllllIlIIlIlIIll.visible = lllllllllllllllllllllIlIIlIlIIlI;
            return lllllllllllllllllllllIlIIlIlIIll;
        }

        public Builder onModuleActivated(Consumer<Setting<Double>> lllllllllllllllllllllIlIIlIllIII) {
            Builder lllllllllllllllllllllIlIIlIlIlll;
            lllllllllllllllllllllIlIIlIlIlll.onModuleActivated = lllllllllllllllllllllIlIIlIllIII;
            return lllllllllllllllllllllIlIIlIlIlll;
        }

        public Builder decimalPlaces(int lllllllllllllllllllllIlIIIlIllll) {
            Builder lllllllllllllllllllllIlIIIllIIlI;
            lllllllllllllllllllllIlIIIllIIlI.decimalPlaces = lllllllllllllllllllllIlIIIlIllll;
            return lllllllllllllllllllllIlIIIllIIlI;
        }

        public Builder sliderMax(double lllllllllllllllllllllIlIIIlllIII) {
            Builder lllllllllllllllllllllIlIIIlllIIl;
            lllllllllllllllllllllIlIIIlllIIl.sliderMax = lllllllllllllllllllllIlIIIlllIII;
            return lllllllllllllllllllllIlIIIlllIIl;
        }

        public Builder() {
            Builder lllllllllllllllllllllIlIIlllIlII;
            lllllllllllllllllllllIlIIlllIlII.name = "undefined";
            lllllllllllllllllllllIlIIlllIlII.description = "";
            lllllllllllllllllllllIlIIlllIlII.decimalPlaces = 3;
        }

        public Builder min(double lllllllllllllllllllllIlIIlIIllII) {
            Builder lllllllllllllllllllllIlIIlIIlIll;
            lllllllllllllllllllllIlIIlIIlIll.min = lllllllllllllllllllllIlIIlIIllII;
            return lllllllllllllllllllllIlIIlIIlIll;
        }

        public DoubleSetting build() {
            Builder lllllllllllllllllllllIlIIIlIllII;
            return new DoubleSetting(lllllllllllllllllllllIlIIIlIllII.name, lllllllllllllllllllllIlIIIlIllII.description, lllllllllllllllllllllIlIIIlIllII.defaultValue, lllllllllllllllllllllIlIIIlIllII.onChanged, lllllllllllllllllllllIlIIIlIllII.onModuleActivated, lllllllllllllllllllllIlIIIlIllII.visible, lllllllllllllllllllllIlIIIlIllII.min, lllllllllllllllllllllIlIIIlIllII.max, lllllllllllllllllllllIlIIIlIllII.sliderMin, lllllllllllllllllllllIlIIIlIllII.sliderMax, lllllllllllllllllllllIlIIIlIllII.onSliderRelease, lllllllllllllllllllllIlIIIlIllII.decimalPlaces);
        }

        public Builder sliderMin(double lllllllllllllllllllllIlIIlIIIIII) {
            Builder lllllllllllllllllllllIlIIlIIIIIl;
            lllllllllllllllllllllIlIIlIIIIIl.sliderMin = lllllllllllllllllllllIlIIlIIIIII;
            return lllllllllllllllllllllIlIIlIIIIIl;
        }

        public Builder max(double lllllllllllllllllllllIlIIlIIIllI) {
            Builder lllllllllllllllllllllIlIIlIIIlIl;
            lllllllllllllllllllllIlIIlIIIlIl.max = lllllllllllllllllllllIlIIlIIIllI;
            return lllllllllllllllllllllIlIIlIIIlIl;
        }

        public Builder description(String lllllllllllllllllllllIlIIllIlIII) {
            Builder lllllllllllllllllllllIlIIllIlIIl;
            lllllllllllllllllllllIlIIllIlIIl.description = lllllllllllllllllllllIlIIllIlIII;
            return lllllllllllllllllllllIlIIllIlIIl;
        }
    }
}

