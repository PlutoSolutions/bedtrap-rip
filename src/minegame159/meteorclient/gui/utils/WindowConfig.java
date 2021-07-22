/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.gui.utils;

import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;

public class WindowConfig
implements ISerializable<WindowConfig> {
    public /* synthetic */ boolean expanded;
    public /* synthetic */ double x;
    public /* synthetic */ double y;

    @Override
    public class_2487 toTag() {
        WindowConfig lllllllllllIIlI;
        class_2487 lllllllllllIIll = new class_2487();
        lllllllllllIIll.method_10556("expanded", lllllllllllIIlI.expanded);
        lllllllllllIIll.method_10549("x", lllllllllllIIlI.x);
        lllllllllllIIll.method_10549("y", lllllllllllIIlI.y);
        return lllllllllllIIll;
    }

    public WindowConfig() {
        WindowConfig llllllllllllIII;
        llllllllllllIII.expanded = true;
        llllllllllllIII.x = -1.0;
        llllllllllllIII.y = -1.0;
    }

    @Override
    public WindowConfig fromTag(class_2487 llllllllllIllIl) {
        WindowConfig llllllllllIlllI;
        llllllllllIlllI.expanded = llllllllllIllIl.method_10577("expanded");
        llllllllllIlllI.x = llllllllllIllIl.method_10574("x");
        llllllllllIlllI.y = llllllllllIllIl.method_10574("y");
        return llllllllllIlllI;
    }
}

