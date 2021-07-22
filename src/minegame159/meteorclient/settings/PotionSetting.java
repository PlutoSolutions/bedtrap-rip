/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.MyPotion;

public class PotionSetting
extends EnumSetting<MyPotion> {
    public PotionSetting(String llIIIIIllIllIII, String llIIIIIllIlIlll, MyPotion llIIIIIllIIllll, Consumer<MyPotion> llIIIIIllIlIlIl, Consumer<Setting<MyPotion>> llIIIIIllIIllIl, IVisible llIIIIIllIIllII) {
        super(llIIIIIllIllIII, llIIIIIllIlIlll, llIIIIIllIIllll, llIIIIIllIlIlIl, llIIIIIllIIllIl, llIIIIIllIIllII);
        PotionSetting llIIIIIllIllIIl;
    }

    public static class Builder
    extends EnumSetting.Builder<MyPotion> {
        public Builder() {
            Builder llIIlIIIIIIIllI;
        }

        @Override
        public EnumSetting<MyPotion> build() {
            Builder llIIlIIIIIIIIll;
            return new PotionSetting(llIIlIIIIIIIIll.name, llIIlIIIIIIIIll.description, (MyPotion)llIIlIIIIIIIIll.defaultValue, llIIlIIIIIIIIll.onChanged, llIIlIIIIIIIIll.onModuleActivated, llIIlIIIIIIIIll.visible);
        }
    }
}

