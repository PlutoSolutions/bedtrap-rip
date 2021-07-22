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
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.NbtUtils;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Settings
implements ISerializable<Settings>,
Iterable<SettingGroup> {
    public final List<SettingGroup> groups = new ArrayList<SettingGroup>(1);
    private SettingGroup defaultGroup;

    public Setting<?> get(String string) {
        for (SettingGroup settingGroup : this) {
            for (Setting<?> setting : settingGroup) {
                if (!string.equalsIgnoreCase(setting.name)) continue;
                return setting;
            }
        }
        return null;
    }

    public SettingGroup getGroup(String string) {
        for (SettingGroup settingGroup : this) {
            if (!settingGroup.name.equals(string)) continue;
            return settingGroup;
        }
        return null;
    }

    @Override
    public Iterator<SettingGroup> iterator() {
        return this.groups.iterator();
    }

    public SettingGroup createGroup(String string, boolean bl) {
        SettingGroup settingGroup = new SettingGroup(string, bl);
        this.groups.add(settingGroup);
        return settingGroup;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("groups", (class_2520)NbtUtils.listToTag(this.groups));
        return class_24872;
    }

    public SettingGroup createGroup(String string) {
        return this.createGroup(string, true);
    }

    public void onActivated() {
        for (SettingGroup settingGroup : this.groups) {
            for (Setting<?> setting : settingGroup) {
                setting.onActivated();
            }
        }
    }

    public int sizeGroups() {
        return this.groups.size();
    }

    public void registerColorSettings(Module module) {
        for (SettingGroup settingGroup : this) {
            for (Setting<SettingColor> setting : settingGroup) {
                setting.module = module;
                if (!(setting instanceof ColorSetting)) continue;
                RainbowColors.addSetting(setting);
            }
        }
    }

    @Override
    public Settings fromTag(class_2487 class_24872) {
        class_2499 class_24992 = class_24872.method_10554("groups", 10);
        for (class_2520 class_25202 : class_24992) {
            class_2487 class_24873 = (class_2487)class_25202;
            SettingGroup settingGroup = this.getGroup(class_24873.method_10558("name"));
            if (settingGroup == null) continue;
            settingGroup.fromTag(class_24873);
        }
        return this;
    }

    public void unregisterColorSettings() {
        for (SettingGroup settingGroup : this) {
            for (Setting<SettingColor> setting : settingGroup) {
                if (!(setting instanceof ColorSetting)) continue;
                RainbowColors.removeSetting(setting);
            }
        }
    }

    public void tick(WContainer wContainer, GuiTheme guiTheme) {
        for (SettingGroup settingGroup : this.groups) {
            for (Setting<?> setting : settingGroup) {
                boolean bl = setting.isVisible();
                if (bl != setting.lastWasVisible) {
                    wContainer.clear();
                    wContainer.add(guiTheme.settings(this)).expandX();
                    setting.lastWasVisible = bl;
                    return;
                }
                setting.lastWasVisible = bl;
            }
        }
    }

    public SettingGroup getDefaultGroup() {
        if (this.defaultGroup == null) {
            this.defaultGroup = this.createGroup("General");
        }
        return this.defaultGroup;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }
}

