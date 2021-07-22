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
    public PotionSetting(String string, String string2, MyPotion myPotion, Consumer<MyPotion> consumer, Consumer<Setting<MyPotion>> consumer2, IVisible iVisible) {
        super(string, string2, myPotion, consumer, consumer2, iVisible);
    }

    public static class Builder
    extends EnumSetting.Builder<MyPotion> {
        @Override
        public EnumSetting<MyPotion> build() {
            return new PotionSetting(this.name, this.description, (MyPotion)this.defaultValue, this.onChanged, this.onModuleActivated, this.visible);
        }
    }
}

