/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.gui.utils;

import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class WindowConfig
implements ISerializable<WindowConfig> {
    public boolean expanded = true;
    public double x = -1.0;
    public double y = -1.0;

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10556("expanded", this.expanded);
        class_24872.method_10549("x", this.x);
        class_24872.method_10549("y", this.y);
        return class_24872;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public WindowConfig fromTag(class_2487 class_24872) {
        this.expanded = class_24872.method_10577("expanded");
        this.x = class_24872.method_10574("x");
        this.y = class_24872.method_10574("y");
        return this;
    }
}

