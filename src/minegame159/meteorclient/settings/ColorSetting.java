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

public class ColorSetting
extends Setting<SettingColor> {
    private static final /* synthetic */ List<String> SUGGESTIONS;

    public ColorSetting(String lIlIllllllIIlIl, String lIlIllllllIlIll, SettingColor lIlIllllllIlIlI, Consumer<SettingColor> lIlIllllllIlIIl, Consumer<Setting<SettingColor>> lIlIllllllIIIIl, IVisible lIlIllllllIIIII) {
        super(lIlIllllllIIlIl, lIlIllllllIlIll, lIlIllllllIlIlI, lIlIllllllIlIIl, lIlIllllllIIIIl, lIlIllllllIIIII);
        ColorSetting lIlIllllllIIllI;
    }

    @Override
    public class_2487 toTag() {
        ColorSetting lIlIlllllIIlIlI;
        class_2487 lIlIlllllIIlIIl = lIlIlllllIIlIlI.saveGeneral();
        lIlIlllllIIlIIl.method_10566("value", (class_2520)((SettingColor)lIlIlllllIIlIlI.get()).toTag());
        return lIlIlllllIIlIIl;
    }

    @Override
    public List<String> getSuggestions() {
        return SUGGESTIONS;
    }

    static {
        SUGGESTIONS = ImmutableList.of((Object)"0 0 0 255", (Object)"225 25 25 255", (Object)"25 225 25 255", (Object)"25 25 225 255", (Object)"255 255 255 255");
    }

    @Override
    public void reset(boolean lIlIlllllIlIlII) {
        ColorSetting lIlIlllllIlIlIl;
        if (lIlIlllllIlIlIl.value == null) {
            lIlIlllllIlIlIl.value = new SettingColor((SettingColor)lIlIlllllIlIlIl.defaultValue);
        } else {
            ((SettingColor)lIlIlllllIlIlIl.value).set((Color)lIlIlllllIlIlIl.defaultValue);
        }
        if (lIlIlllllIlIlII) {
            lIlIlllllIlIlIl.changed();
        }
    }

    @Override
    protected SettingColor parseImpl(String lIlIlllllIllIlI) {
        try {
            String[] lIlIlllllIlllIl = lIlIlllllIllIlI.split(" ");
            return new SettingColor(Integer.parseInt(lIlIlllllIlllIl[0]), Integer.parseInt(lIlIlllllIlllIl[1]), Integer.parseInt(lIlIlllllIlllIl[2]), Integer.parseInt(lIlIlllllIlllIl[3]));
        }
        catch (IndexOutOfBoundsException | NumberFormatException lIlIlllllIlllII) {
            return null;
        }
    }

    @Override
    public SettingColor fromTag(class_2487 lIlIlllllIIIIll) {
        ColorSetting lIlIlllllIIIlII;
        ((SettingColor)lIlIlllllIIIlII.get()).fromTag(lIlIlllllIIIIll.method_10562("value"));
        lIlIlllllIIIlII.changed();
        return (SettingColor)lIlIlllllIIIlII.get();
    }

    @Override
    protected boolean isValueValid(SettingColor lIlIlllllIIlllI) {
        lIlIlllllIIlllI.validate();
        return true;
    }

    public static class Builder {
        private /* synthetic */ String description;
        private /* synthetic */ String name;
        private /* synthetic */ Consumer<SettingColor> onChanged;
        private /* synthetic */ SettingColor defaultValue;
        private /* synthetic */ Consumer<Setting<SettingColor>> onModuleActivated;
        private /* synthetic */ IVisible visible;

        public Builder onModuleActivated(Consumer<Setting<SettingColor>> lllllllllllllllllIllllIIIlIIllII) {
            Builder lllllllllllllllllIllllIIIlIIllIl;
            lllllllllllllllllIllllIIIlIIllIl.onModuleActivated = lllllllllllllllllIllllIIIlIIllII;
            return lllllllllllllllllIllllIIIlIIllIl;
        }

        public ColorSetting build() {
            Builder lllllllllllllllllIllllIIIlIIIIIl;
            return new ColorSetting(lllllllllllllllllIllllIIIlIIIIIl.name, lllllllllllllllllIllllIIIlIIIIIl.description, lllllllllllllllllIllllIIIlIIIIIl.defaultValue, lllllllllllllllllIllllIIIlIIIIIl.onChanged, lllllllllllllllllIllllIIIlIIIIIl.onModuleActivated, lllllllllllllllllIllllIIIlIIIIIl.visible);
        }

        public Builder visible(IVisible lllllllllllllllllIllllIIIlIIIlII) {
            Builder lllllllllllllllllIllllIIIlIIIlll;
            lllllllllllllllllIllllIIIlIIIlll.visible = lllllllllllllllllIllllIIIlIIIlII;
            return lllllllllllllllllIllllIIIlIIIlll;
        }

        public Builder name(String lllllllllllllllllIllllIIIllIIlII) {
            Builder lllllllllllllllllIllllIIIllIIIll;
            lllllllllllllllllIllllIIIllIIIll.name = lllllllllllllllllIllllIIIllIIlII;
            return lllllllllllllllllIllllIIIllIIIll;
        }

        public Builder onChanged(Consumer<SettingColor> lllllllllllllllllIllllIIIlIlIIlI) {
            Builder lllllllllllllllllIllllIIIlIlIIIl;
            lllllllllllllllllIllllIIIlIlIIIl.onChanged = lllllllllllllllllIllllIIIlIlIIlI;
            return lllllllllllllllllIllllIIIlIlIIIl;
        }

        public Builder description(String lllllllllllllllllIllllIIIlIlllII) {
            Builder lllllllllllllllllIllllIIIlIlllIl;
            lllllllllllllllllIllllIIIlIlllIl.description = lllllllllllllllllIllllIIIlIlllII;
            return lllllllllllllllllIllllIIIlIlllIl;
        }

        public Builder defaultValue(SettingColor lllllllllllllllllIllllIIIlIllIII) {
            Builder lllllllllllllllllIllllIIIlIlIlll;
            lllllllllllllllllIllllIIIlIlIlll.defaultValue = lllllllllllllllllIllllIIIlIllIII;
            return lllllllllllllllllIllllIIIlIlIlll;
        }

        public Builder() {
            Builder lllllllllllllllllIllllIIIllIlIIl;
            lllllllllllllllllIllllIIIllIlIIl.name = "undefined";
            lllllllllllllllllIllllIIIllIlIIl.description = "";
        }
    }
}

