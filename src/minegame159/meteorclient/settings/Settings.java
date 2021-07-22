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

public class Settings
implements ISerializable<Settings>,
Iterable<SettingGroup> {
    public final /* synthetic */ List<SettingGroup> groups;
    private /* synthetic */ SettingGroup defaultGroup;

    public Setting<?> get(String llllllllllllllllIllIIIlllIlIllll) {
        Settings llllllllllllllllIllIIIlllIllIIII;
        for (SettingGroup llllllllllllllllIllIIIlllIllIIIl : llllllllllllllllIllIIIlllIllIIII) {
            for (Setting<?> llllllllllllllllIllIIIlllIllIIlI : llllllllllllllllIllIIIlllIllIIIl) {
                if (!llllllllllllllllIllIIIlllIlIllll.equalsIgnoreCase(llllllllllllllllIllIIIlllIllIIlI.name)) continue;
                return llllllllllllllllIllIIIlllIllIIlI;
            }
        }
        return null;
    }

    public SettingGroup getGroup(String llllllllllllllllIllIIIlllIlIIIII) {
        Settings llllllllllllllllIllIIIlllIlIIIll;
        for (SettingGroup llllllllllllllllIllIIIlllIlIIlII : llllllllllllllllIllIIIlllIlIIIll) {
            if (!llllllllllllllllIllIIIlllIlIIlII.name.equals(llllllllllllllllIllIIIlllIlIIIII)) continue;
            return llllllllllllllllIllIIIlllIlIIlII;
        }
        return null;
    }

    @Override
    public Iterator<SettingGroup> iterator() {
        Settings llllllllllllllllIllIIIllIlIlIIIl;
        return llllllllllllllllIllIIIllIlIlIIIl.groups.iterator();
    }

    public SettingGroup createGroup(String llllllllllllllllIllIIIlllIIlIIlI, boolean llllllllllllllllIllIIIlllIIIllIl) {
        Settings llllllllllllllllIllIIIlllIIlIIll;
        SettingGroup llllllllllllllllIllIIIlllIIlIIII = new SettingGroup(llllllllllllllllIllIIIlllIIlIIlI, llllllllllllllllIllIIIlllIIIllIl);
        llllllllllllllllIllIIIlllIIlIIll.groups.add(llllllllllllllllIllIIIlllIIlIIII);
        return llllllllllllllllIllIIIlllIIlIIII;
    }

    @Override
    public class_2487 toTag() {
        Settings llllllllllllllllIllIIIllIlIIlIll;
        class_2487 llllllllllllllllIllIIIllIlIIllII = new class_2487();
        llllllllllllllllIllIIIllIlIIllII.method_10566("groups", (class_2520)NbtUtils.listToTag(llllllllllllllllIllIIIllIlIIlIll.groups));
        return llllllllllllllllIllIIIllIlIIllII;
    }

    public SettingGroup createGroup(String llllllllllllllllIllIIIlllIIIlIII) {
        Settings llllllllllllllllIllIIIlllIIIIlll;
        return llllllllllllllllIllIIIlllIIIIlll.createGroup(llllllllllllllllIllIIIlllIIIlIII, true);
    }

    public Settings() {
        Settings llllllllllllllllIllIIIllllIIIllI;
        llllllllllllllllIllIIIllllIIIllI.groups = new ArrayList<SettingGroup>(1);
    }

    public void onActivated() {
        Settings llllllllllllllllIllIIIlllIlllllI;
        for (SettingGroup llllllllllllllllIllIIIlllIllllll : llllllllllllllllIllIIIlllIlllllI.groups) {
            for (Setting<?> llllllllllllllllIllIIIllllIIIIII : llllllllllllllllIllIIIlllIllllll) {
                llllllllllllllllIllIIIllllIIIIII.onActivated();
            }
        }
    }

    public int sizeGroups() {
        Settings llllllllllllllllIllIIIlllIIlllII;
        return llllllllllllllllIllIIIlllIIlllII.groups.size();
    }

    public void registerColorSettings(Module llllllllllllllllIllIIIllIllllIlI) {
        Settings llllllllllllllllIllIIIllIllllIll;
        for (SettingGroup llllllllllllllllIllIIIllIllllllI : llllllllllllllllIllIIIllIllllIll) {
            for (Setting<SettingColor> llllllllllllllllIllIIIllIlllllll : llllllllllllllllIllIIIllIllllllI) {
                llllllllllllllllIllIIIllIlllllll.module = llllllllllllllllIllIIIllIllllIlI;
                if (!(llllllllllllllllIllIIIllIlllllll instanceof ColorSetting)) continue;
                RainbowColors.addSetting(llllllllllllllllIllIIIllIlllllll);
            }
        }
    }

    @Override
    public Settings fromTag(class_2487 llllllllllllllllIllIIIllIIlllIll) {
        Settings llllllllllllllllIllIIIllIIllllII;
        class_2499 llllllllllllllllIllIIIllIIllllIl = llllllllllllllllIllIIIllIIlllIll.method_10554("groups", 10);
        for (class_2520 llllllllllllllllIllIIIllIlIIIIII : llllllllllllllllIllIIIllIIllllIl) {
            class_2487 llllllllllllllllIllIIIllIlIIIIlI = (class_2487)llllllllllllllllIllIIIllIlIIIIII;
            SettingGroup llllllllllllllllIllIIIllIlIIIIIl = llllllllllllllllIllIIIllIIllllII.getGroup(llllllllllllllllIllIIIllIlIIIIlI.method_10558("name"));
            if (llllllllllllllllIllIIIllIlIIIIIl == null) continue;
            llllllllllllllllIllIIIllIlIIIIIl.fromTag(llllllllllllllllIllIIIllIlIIIIlI);
        }
        return llllllllllllllllIllIIIllIIllllII;
    }

    public void unregisterColorSettings() {
        Settings llllllllllllllllIllIIIllIllIlllI;
        for (SettingGroup llllllllllllllllIllIIIllIllIllll : llllllllllllllllIllIIIllIllIlllI) {
            for (Setting<SettingColor> llllllllllllllllIllIIIllIlllIIII : llllllllllllllllIllIIIllIllIllll) {
                if (!(llllllllllllllllIllIIIllIlllIIII instanceof ColorSetting)) continue;
                RainbowColors.removeSetting(llllllllllllllllIllIIIllIlllIIII);
            }
        }
    }

    public void tick(WContainer llllllllllllllllIllIIIllIlIllIIl, GuiTheme llllllllllllllllIllIIIllIlIllIII) {
        Settings llllllllllllllllIllIIIllIlIlllIl;
        for (SettingGroup llllllllllllllllIllIIIllIlIllllI : llllllllllllllllIllIIIllIlIlllIl.groups) {
            for (Setting<?> llllllllllllllllIllIIIllIlIlllll : llllllllllllllllIllIIIllIlIllllI) {
                boolean llllllllllllllllIllIIIllIllIIIII = llllllllllllllllIllIIIllIlIlllll.isVisible();
                if (llllllllllllllllIllIIIllIllIIIII != llllllllllllllllIllIIIllIlIlllll.lastWasVisible) {
                    llllllllllllllllIllIIIllIlIllIIl.clear();
                    llllllllllllllllIllIIIllIlIllIIl.add(llllllllllllllllIllIIIllIlIllIII.settings(llllllllllllllllIllIIIllIlIlllIl)).expandX();
                    llllllllllllllllIllIIIllIlIlllll.lastWasVisible = llllllllllllllllIllIIIllIllIIIII;
                    return;
                }
                llllllllllllllllIllIIIllIlIlllll.lastWasVisible = llllllllllllllllIllIIIllIllIIIII;
            }
        }
    }

    public SettingGroup getDefaultGroup() {
        Settings llllllllllllllllIllIIIlllIIllIII;
        if (llllllllllllllllIllIIIlllIIllIII.defaultGroup == null) {
            llllllllllllllllIllIIIlllIIllIII.defaultGroup = llllllllllllllllIllIIIlllIIllIII.createGroup("General");
        }
        return llllllllllllllllIllIIIlllIIllIII.defaultGroup;
    }
}

