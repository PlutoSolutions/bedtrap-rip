/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.settings;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2248;

public interface IBlockData<T extends ICopyable<T> & ISerializable<T> & IBlockData<T>> {
    public WidgetScreen createScreen(GuiTheme var1, class_2248 var2, BlockDataSetting<T> var3);
}

