/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens.settings;

import java.util.Collection;
import java.util.Set;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.settings.LeftRightListSettingScreen;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.network.PacketUtils;
import net.minecraft.class_2596;

public class PacketBoolSettingScreen
extends LeftRightListSettingScreen<Class<? extends class_2596<?>>> {
    @Override
    protected WWidget getValueWidget(Object object) {
        return this.getValueWidget((Class)object);
    }

    @Override
    protected String getValueName(Object object) {
        return this.getValueName((Class)object);
    }

    @Override
    protected WWidget getValueWidget(Class<? extends class_2596<?>> clazz) {
        return this.theme.label(this.getValueName(clazz));
    }

    public PacketBoolSettingScreen(GuiTheme guiTheme, Setting<Set<Class<? extends class_2596<?>>>> setting) {
        super(guiTheme, "Select Packets", setting, (Collection)setting.get(), PacketUtils.REGISTRY);
    }

    @Override
    protected String getValueName(Class<? extends class_2596<?>> clazz) {
        return PacketUtils.getName(clazz);
    }
}

