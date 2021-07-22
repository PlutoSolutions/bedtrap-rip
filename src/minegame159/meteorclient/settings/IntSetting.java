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

public class IntSetting
extends Setting<Integer> {
    private final /* synthetic */ Integer sliderMax;
    private final /* synthetic */ Integer sliderMin;
    public final /* synthetic */ Integer min;
    public final /* synthetic */ Integer max;

    private IntSetting(String llllllllllllllllllIIIllIlllllllI, String llllllllllllllllllIIIllIllllIIlI, Integer llllllllllllllllllIIIllIllllIIIl, Consumer<Integer> llllllllllllllllllIIIllIllllIIII, Consumer<Setting<Integer>> llllllllllllllllllIIIllIlllIllll, IVisible llllllllllllllllllIIIllIlllllIIl, Integer llllllllllllllllllIIIllIlllIllIl, Integer llllllllllllllllllIIIllIllllIlll, Integer llllllllllllllllllIIIllIllllIllI, Integer llllllllllllllllllIIIllIlllIlIlI) {
        super(llllllllllllllllllIIIllIlllllllI, llllllllllllllllllIIIllIllllIIlI, llllllllllllllllllIIIllIllllIIIl, llllllllllllllllllIIIllIllllIIII, llllllllllllllllllIIIllIlllIllll, llllllllllllllllllIIIllIlllllIIl);
        IntSetting llllllllllllllllllIIIllIllllIlII;
        llllllllllllllllllIIIllIllllIlII.min = llllllllllllllllllIIIllIlllIllIl;
        llllllllllllllllllIIIllIllllIlII.max = llllllllllllllllllIIIllIllllIlll;
        llllllllllllllllllIIIllIllllIlII.sliderMin = llllllllllllllllllIIIllIllllIllI;
        llllllllllllllllllIIIllIllllIlII.sliderMax = llllllllllllllllllIIIllIlllIlIlI;
    }

    @Override
    protected boolean isValueValid(Integer llllllllllllllllllIIIllIllIlllll) {
        IntSetting llllllllllllllllllIIIllIlllIIIII;
        return !(llllllllllllllllllIIIllIlllIIIII.min != null && llllllllllllllllllIIIllIllIlllll < llllllllllllllllllIIIllIlllIIIII.min || llllllllllllllllllIIIllIlllIIIII.max != null && llllllllllllllllllIIIllIllIlllll > llllllllllllllllllIIIllIlllIIIII.max);
    }

    public int getSliderMax() {
        IntSetting llllllllllllllllllIIIllIllIllIII;
        return llllllllllllllllllIIIllIllIllIII.sliderMax != null ? llllllllllllllllllIIIllIllIllIII.sliderMax : 10;
    }

    @Override
    public Integer fromTag(class_2487 llllllllllllllllllIIIllIllIIlIll) {
        IntSetting llllllllllllllllllIIIllIllIIlllI;
        llllllllllllllllllIIIllIllIIlllI.set(llllllllllllllllllIIIllIllIIlIll.method_10550("value"));
        return (Integer)llllllllllllllllllIIIllIllIIlllI.get();
    }

    @Override
    public class_2487 toTag() {
        IntSetting llllllllllllllllllIIIllIllIlIIlI;
        class_2487 llllllllllllllllllIIIllIllIlIIll = llllllllllllllllllIIIllIllIlIIlI.saveGeneral();
        llllllllllllllllllIIIllIllIlIIll.method_10569("value", ((Integer)llllllllllllllllllIIIllIllIlIIlI.get()).intValue());
        return llllllllllllllllllIIIllIllIlIIll;
    }

    @Override
    protected Integer parseImpl(String llllllllllllllllllIIIllIlllIIlII) {
        try {
            return Integer.parseInt(llllllllllllllllllIIIllIlllIIlII.trim());
        }
        catch (NumberFormatException llllllllllllllllllIIIllIlllIIlll) {
            return null;
        }
    }

    public int getSliderMin() {
        IntSetting llllllllllllllllllIIIllIllIllIll;
        return llllllllllllllllllIIIllIllIllIll.sliderMin != null ? llllllllllllllllllIIIllIllIllIll.sliderMin : 0;
    }

    public static class Builder {
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<Setting<Integer>> onModuleActivated;
        private /* synthetic */ Integer sliderMin;
        private /* synthetic */ String description;
        private /* synthetic */ Integer sliderMax;
        private /* synthetic */ Consumer<Integer> onChanged;
        private /* synthetic */ Integer min;
        private /* synthetic */ Integer max;
        private /* synthetic */ Integer defaultValue;
        private /* synthetic */ IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<Integer>> lllllllllllllllllllllllIlIIIIlII) {
            Builder lllllllllllllllllllllllIlIIIIlIl;
            lllllllllllllllllllllllIlIIIIlIl.onModuleActivated = lllllllllllllllllllllllIlIIIIlII;
            return lllllllllllllllllllllllIlIIIIlIl;
        }

        public Builder defaultValue(int lllllllllllllllllllllllIlIIlIIII) {
            Builder lllllllllllllllllllllllIlIIlIIll;
            lllllllllllllllllllllllIlIIlIIll.defaultValue = lllllllllllllllllllllllIlIIlIIII;
            return lllllllllllllllllllllllIlIIlIIll;
        }

        public Builder sliderMax(int lllllllllllllllllllllllIIllIIllI) {
            Builder lllllllllllllllllllllllIIllIlIIl;
            lllllllllllllllllllllllIIllIlIIl.sliderMax = lllllllllllllllllllllllIIllIIllI;
            return lllllllllllllllllllllllIIllIlIIl;
        }

        public IntSetting build() {
            Builder lllllllllllllllllllllllIIllIIIll;
            return new IntSetting(lllllllllllllllllllllllIIllIIIll.name, lllllllllllllllllllllllIIllIIIll.description, lllllllllllllllllllllllIIllIIIll.defaultValue, lllllllllllllllllllllllIIllIIIll.onChanged, lllllllllllllllllllllllIIllIIIll.onModuleActivated, lllllllllllllllllllllllIIllIIIll.visible, lllllllllllllllllllllllIIllIIIll.min, lllllllllllllllllllllllIIllIIIll.max, lllllllllllllllllllllllIIllIIIll.sliderMin, lllllllllllllllllllllllIIllIIIll.sliderMax);
        }

        public Builder name(String lllllllllllllllllllllllIlIIllllI) {
            Builder lllllllllllllllllllllllIlIIlllll;
            lllllllllllllllllllllllIlIIlllll.name = lllllllllllllllllllllllIlIIllllI;
            return lllllllllllllllllllllllIlIIlllll;
        }

        public Builder() {
            Builder lllllllllllllllllllllllIlIlIIIll;
            lllllllllllllllllllllllIlIlIIIll.name = "undefined";
            lllllllllllllllllllllllIlIlIIIll.description = "";
        }

        public Builder visible(IVisible lllllllllllllllllllllllIIllllllI) {
            Builder lllllllllllllllllllllllIIlllllll;
            lllllllllllllllllllllllIIlllllll.visible = lllllllllllllllllllllllIIllllllI;
            return lllllllllllllllllllllllIIlllllll;
        }

        public Builder min(int lllllllllllllllllllllllIIllllIlI) {
            Builder lllllllllllllllllllllllIIllllIll;
            lllllllllllllllllllllllIIllllIll.min = lllllllllllllllllllllllIIllllIlI;
            return lllllllllllllllllllllllIIllllIll;
        }

        public Builder description(String lllllllllllllllllllllllIlIIlIllI) {
            Builder lllllllllllllllllllllllIlIIlIlll;
            lllllllllllllllllllllllIlIIlIlll.description = lllllllllllllllllllllllIlIIlIllI;
            return lllllllllllllllllllllllIlIIlIlll;
        }

        public Builder sliderMin(int lllllllllllllllllllllllIIllIlllI) {
            Builder lllllllllllllllllllllllIIllIllIl;
            lllllllllllllllllllllllIIllIllIl.sliderMin = lllllllllllllllllllllllIIllIlllI;
            return lllllllllllllllllllllllIIllIllIl;
        }

        public Builder onChanged(Consumer<Integer> lllllllllllllllllllllllIlIIIllII) {
            Builder lllllllllllllllllllllllIlIIIlIll;
            lllllllllllllllllllllllIlIIIlIll.onChanged = lllllllllllllllllllllllIlIIIllII;
            return lllllllllllllllllllllllIlIIIlIll;
        }

        public Builder max(int lllllllllllllllllllllllIIlllIIlI) {
            Builder lllllllllllllllllllllllIIlllIIll;
            lllllllllllllllllllllllIIlllIIll.max = lllllllllllllllllllllllIIlllIIlI;
            return lllllllllllllllllllllllIIlllIIll;
        }
    }
}

