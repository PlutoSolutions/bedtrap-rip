/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
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
    protected WWidget getValueWidget(Class<? extends class_2596<?>> lIlllIllIlllI) {
        PacketBoolSettingScreen lIlllIllIllll;
        return lIlllIllIllll.theme.label(lIlllIllIllll.getValueName(lIlllIllIlllI));
    }

    public PacketBoolSettingScreen(GuiTheme lIlllIlllIlIl, Setting<Set<Class<? extends class_2596<?>>>> lIlllIlllIlll) {
        super(lIlllIlllIlIl, "Select Packets", lIlllIlllIlll, (Collection)lIlllIlllIlll.get(), PacketUtils.REGISTRY);
        PacketBoolSettingScreen lIlllIllllIIl;
    }

    @Override
    protected String getValueName(Class<? extends class_2596<?>> lIlllIllIlIlI) {
        return PacketUtils.getName(lIlllIllIlIlI);
    }
}

