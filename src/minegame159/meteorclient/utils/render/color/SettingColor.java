/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.utils.render.color;

import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import net.minecraft.class_2487;

public class SettingColor
extends Color {
    public /* synthetic */ boolean rainbow;

    public SettingColor(float llllllllllllllllIlIlIIlIlIIIIlIl, float llllllllllllllllIlIlIIlIlIIIlIIl, float llllllllllllllllIlIlIIlIlIIIIIll, float llllllllllllllllIlIlIIlIlIIIIlll) {
        super(llllllllllllllllIlIlIIlIlIIIIlIl, llllllllllllllllIlIlIIlIlIIIlIIl, llllllllllllllllIlIlIIlIlIIIIIll, llllllllllllllllIlIlIIlIlIIIIlll);
        SettingColor llllllllllllllllIlIlIIlIlIIIlIll;
    }

    @Override
    public SettingColor fromTag(class_2487 llllllllllllllllIlIlIIlIIlIIllII) {
        SettingColor llllllllllllllllIlIlIIlIIlIIllll;
        super.fromTag(llllllllllllllllIlIlIIlIIlIIllII);
        llllllllllllllllIlIlIIlIIlIIllll.rainbow = llllllllllllllllIlIlIIlIIlIIllII.method_10577("rainbow");
        return llllllllllllllllIlIlIIlIIlIIllll;
    }

    public SettingColor(SettingColor llllllllllllllllIlIlIIlIIllIlIlI) {
        super(llllllllllllllllIlIlIIlIIllIlIlI);
        SettingColor llllllllllllllllIlIlIIlIIllIlIll;
        llllllllllllllllIlIlIIlIIllIlIll.rainbow = llllllllllllllllIlIlIIlIIllIlIlI.rainbow;
    }

    public SettingColor(int llllllllllllllllIlIlIIlIlIlIlIII, int llllllllllllllllIlIlIIlIlIlIIIlI, int llllllllllllllllIlIlIIlIlIlIIllI, boolean llllllllllllllllIlIlIIlIlIlIIlIl) {
        llllllllllllllllIlIlIIlIlIlIlIIl(llllllllllllllllIlIlIIlIlIlIlIII, llllllllllllllllIlIlIIlIlIlIIIlI, llllllllllllllllIlIlIIlIlIlIIllI, 255, llllllllllllllllIlIlIIlIlIlIIlIl);
        SettingColor llllllllllllllllIlIlIIlIlIlIlIIl;
    }

    public SettingColor rainbow(boolean llllllllllllllllIlIlIIlIIllIIlII) {
        SettingColor llllllllllllllllIlIlIIlIIllIIlIl;
        llllllllllllllllIlIlIIlIIllIIlIl.rainbow = llllllllllllllllIlIlIIlIIllIIlII;
        return llllllllllllllllIlIlIIlIIllIIlIl;
    }

    public void update() {
        SettingColor llllllllllllllllIlIlIIlIIllIIIlI;
        if (llllllllllllllllIlIlIIlIIllIIIlI.rainbow) {
            llllllllllllllllIlIlIIlIIllIIIlI.set(RainbowColors.GLOBAL.r, RainbowColors.GLOBAL.g, RainbowColors.GLOBAL.b, llllllllllllllllIlIlIIlIIllIIIlI.a);
        }
    }

    public SettingColor(int llllllllllllllllIlIlIIlIlIIllIIl, int llllllllllllllllIlIlIIlIlIIlIIll, int llllllllllllllllIlIlIIlIlIIlIIlI, int llllllllllllllllIlIlIIlIlIIlIIIl) {
        super(llllllllllllllllIlIlIIlIlIIllIIl, llllllllllllllllIlIlIIlIlIIlIIll, llllllllllllllllIlIlIIlIlIIlIIlI, llllllllllllllllIlIlIIlIlIIlIIIl);
        SettingColor llllllllllllllllIlIlIIlIlIIllIlI;
    }

    @Override
    public Color copy() {
        SettingColor llllllllllllllllIlIlIIlIIlIllIIl;
        return new SettingColor(llllllllllllllllIlIlIIlIIlIllIIl.r, llllllllllllllllIlIlIIlIIlIllIIl.g, llllllllllllllllIlIlIIlIIlIllIIl.b, llllllllllllllllIlIlIIlIIlIllIIl.a, llllllllllllllllIlIlIIlIIlIllIIl.rainbow);
    }

    @Override
    public class_2487 toTag() {
        SettingColor llllllllllllllllIlIlIIlIIlIlIIll;
        class_2487 llllllllllllllllIlIlIIlIIlIlIlII = super.toTag();
        llllllllllllllllIlIlIIlIIlIlIlII.method_10556("rainbow", llllllllllllllllIlIlIIlIIlIlIIll.rainbow);
        return llllllllllllllllIlIlIIlIIlIlIlII;
    }

    public SettingColor(int llllllllllllllllIlIlIIlIlIllIIIl, int llllllllllllllllIlIlIIlIlIllIIII, int llllllllllllllllIlIlIIlIlIlIllll) {
        super(llllllllllllllllIlIlIIlIlIllIIIl, llllllllllllllllIlIlIIlIlIllIIII, llllllllllllllllIlIlIIlIlIlIllll);
        SettingColor llllllllllllllllIlIlIIlIlIllIllI;
    }

    public SettingColor(int llllllllllllllllIlIlIIlIIllllIlI, int llllllllllllllllIlIlIIlIIllllIIl, int llllllllllllllllIlIlIIlIIllllIII, int llllllllllllllllIlIlIIlIIlllIlll, boolean llllllllllllllllIlIlIIlIIlllIllI) {
        super(llllllllllllllllIlIlIIlIIllllIlI, llllllllllllllllIlIlIIlIIllllIIl, llllllllllllllllIlIlIIlIIllllIII, llllllllllllllllIlIlIIlIIlllIlll);
        SettingColor llllllllllllllllIlIlIIlIIllllIll;
        llllllllllllllllIlIlIIlIIllllIll.rainbow = llllllllllllllllIlIlIIlIIlllIllI;
    }

    public SettingColor() {
        SettingColor llllllllllllllllIlIlIIlIlIlllIll;
    }

    @Override
    public SettingColor set(Color llllllllllllllllIlIlIIlIIlIllIll) {
        SettingColor llllllllllllllllIlIlIIlIIlIllllI;
        super.set(llllllllllllllllIlIlIIlIIlIllIll);
        if (llllllllllllllllIlIlIIlIIlIllIll instanceof SettingColor) {
            llllllllllllllllIlIlIIlIIlIllllI.rainbow = ((SettingColor)llllllllllllllllIlIlIIlIIlIllIll).rainbow;
        }
        return llllllllllllllllIlIlIIlIIlIllllI;
    }
}

