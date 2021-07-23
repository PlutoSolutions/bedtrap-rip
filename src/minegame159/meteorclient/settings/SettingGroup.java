/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class SettingGroup
implements ISerializable<SettingGroup>,
Iterable<Setting<?>> {
    public boolean sectionExpanded;
    final List<Setting<?>> settings = new ArrayList(1);
    public final String name;

    @Override
    public SettingGroup fromTag(class_2487 class_24872) {
        this.sectionExpanded = class_24872.method_10577("sectionExpanded");
        class_2499 class_24992 = class_24872.method_10554("settings", 10);
        for (class_2520 class_25202 : class_24992) {
            class_2487 class_24873 = (class_2487)class_25202;
            Setting<?> setting = this.get(class_24873.method_10558("name"));
            if (setting == null) continue;
            setting.fromTag(class_24873);
        }
        return this;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        class_24872.method_10556("sectionExpanded", this.sectionExpanded);
        class_24872.method_10566("settings", (class_2520)NbtUtils.listToTag(this.settings));
        return class_24872;
    }

    public <T> Setting<T> add(Setting<T> setting) {
        this.settings.add(setting);
        return setting;
    }

    SettingGroup(String string, boolean bl) {
        this.name = string;
        this.sectionExpanded = bl;
    }

    public Setting<?> get(String string) {
        for (Setting<?> setting : this) {
            if (!setting.name.equals(string)) continue;
            return setting;
        }
        return null;
    }

    @Override
    public Iterator<Setting<?>> iterator() {
        return this.settings.iterator();
    }
}

