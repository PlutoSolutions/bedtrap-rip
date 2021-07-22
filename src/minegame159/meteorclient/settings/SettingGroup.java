/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.NbtUtils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public class SettingGroup
implements ISerializable<SettingGroup>,
Iterable<Setting<?>> {
    public /* synthetic */ boolean sectionExpanded;
    final /* synthetic */ List<Setting<?>> settings;
    public final /* synthetic */ String name;

    @Override
    public SettingGroup fromTag(class_2487 llIIllIIlIIlI) {
        SettingGroup llIIllIIlIIII;
        llIIllIIlIIII.sectionExpanded = llIIllIIlIIlI.method_10577("sectionExpanded");
        class_2499 llIIllIIlIIIl = llIIllIIlIIlI.method_10554("settings", 10);
        for (class_2520 llIIllIIlIlII : llIIllIIlIIIl) {
            class_2487 llIIllIIlIllI = (class_2487)llIIllIIlIlII;
            Setting<?> llIIllIIlIlIl = llIIllIIlIIII.get(llIIllIIlIllI.method_10558("name"));
            if (llIIllIIlIlIl == null) continue;
            llIIllIIlIlIl.fromTag(llIIllIIlIllI);
        }
        return llIIllIIlIIII;
    }

    @Override
    public class_2487 toTag() {
        SettingGroup llIIllIlIIIIl;
        class_2487 llIIllIlIIIII = new class_2487();
        llIIllIlIIIII.method_10582("name", llIIllIlIIIIl.name);
        llIIllIlIIIII.method_10556("sectionExpanded", llIIllIlIIIIl.sectionExpanded);
        llIIllIlIIIII.method_10566("settings", (class_2520)NbtUtils.listToTag(llIIllIlIIIIl.settings));
        return llIIllIlIIIII;
    }

    public <T> Setting<T> add(Setting<T> llIIllIlIIlll) {
        SettingGroup llIIllIlIlIII;
        llIIllIlIlIII.settings.add(llIIllIlIIlll);
        return llIIllIlIIlll;
    }

    SettingGroup(String llIIllIlllIIl, boolean llIIllIlllIll) {
        SettingGroup llIIllIllllIl;
        llIIllIllllIl.settings = new ArrayList(1);
        llIIllIllllIl.name = llIIllIlllIIl;
        llIIllIllllIl.sectionExpanded = llIIllIlllIll;
    }

    public Setting<?> get(String llIIllIlIllll) {
        SettingGroup llIIllIllIIlI;
        for (Setting<?> llIIllIllIIll : llIIllIllIIlI) {
            if (!llIIllIllIIll.name.equals(llIIllIlIllll)) continue;
            return llIIllIllIIll;
        }
        return null;
    }

    @Override
    public Iterator<Setting<?>> iterator() {
        SettingGroup llIIllIlIIlII;
        return llIIllIlIIlII.settings.iterator();
    }
}

