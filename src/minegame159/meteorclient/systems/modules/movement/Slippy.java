/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.Collections;
import java.util.List;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2248;

public class Slippy
extends Module {
    public final Setting<List<class_2248>> blocks;
    public final Setting<Double> slippness;
    private final SettingGroup sgGeneral;

    public Slippy() {
        super(Categories.Movement, "slippy", "Makes blocks slippery like ice.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.slippness = this.sgGeneral.add(new DoubleSetting.Builder().name("slippness").description("Decide how slippery blocks should be").min(0.0).max(1.1).sliderMax(1.1).defaultValue(1.02).build());
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("ignored blocks").description("Decide which blocks not to slip on").defaultValue(Collections.emptyList()).build());
    }
}

