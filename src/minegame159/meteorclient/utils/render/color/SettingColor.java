/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class SettingColor
extends Color {
    public boolean rainbow;

    @Override
    public Color set(Color color) {
        return this.set(color);
    }

    public SettingColor(float f, float f2, float f3, float f4) {
        super(f, f2, f3, f4);
    }

    @Override
    public SettingColor fromTag(class_2487 class_24872) {
        super.fromTag(class_24872);
        this.rainbow = class_24872.method_10577("rainbow");
        return this;
    }

    public SettingColor(SettingColor settingColor) {
        super(settingColor);
        this.rainbow = settingColor.rainbow;
    }

    @Override
    public Color fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public SettingColor(int n, int n2, int n3, boolean bl) {
        this(n, n2, n3, 255, bl);
    }

    public SettingColor rainbow(boolean bl) {
        this.rainbow = bl;
        return this;
    }

    @Override
    public ICopyable set(ICopyable iCopyable) {
        return this.set((Color)iCopyable);
    }

    public void update() {
        if (this.rainbow) {
            this.set(RainbowColors.GLOBAL.r, RainbowColors.GLOBAL.g, RainbowColors.GLOBAL.b, this.a);
        }
    }

    public SettingColor(int n, int n2, int n3, int n4) {
        super(n, n2, n3, n4);
    }

    @Override
    public Color copy() {
        return new SettingColor(this.r, this.g, this.b, this.a, this.rainbow);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = super.toTag();
        class_24872.method_10556("rainbow", this.rainbow);
        return class_24872;
    }

    public SettingColor(int n, int n2, int n3) {
        super(n, n2, n3);
    }

    public SettingColor(int n, int n2, int n3, int n4, boolean bl) {
        super(n, n2, n3, n4);
        this.rainbow = bl;
    }

    public SettingColor() {
    }

    @Override
    public SettingColor set(Color color) {
        super.set(color);
        if (color instanceof SettingColor) {
            this.rainbow = ((SettingColor)color).rainbow;
        }
        return this;
    }

    @Override
    public ICopyable copy() {
        return this.copy();
    }
}

