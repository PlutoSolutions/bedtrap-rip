/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.systems.modules.world;

import com.google.common.collect.Lists;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockIterator;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class LiquidFiller
extends Module {
    private final Setting<Integer> horizontalRadius;
    private final Setting<PlaceIn> placeInLiquids;
    private final Setting<List<class_2248>> whitelist;
    private int timer;
    private final Setting<Integer> delay;
    private final Setting<Integer> verticalRadius;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgGeneral;

    private boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1747 && this.whitelist.get().contains((Object)class_2248.method_9503((class_1792)class_17992.method_7909()));
    }

    private boolean isSource(class_2680 class_26802) {
        return class_26802.method_26227().method_15761() == 8 && class_26802.method_26227().method_15771();
    }

    private List<class_2248> getDefaultWhitelist() {
        return Lists.newArrayList((Object[])new class_2248[]{class_2246.field_10566, class_2246.field_10445, class_2246.field_10340, class_2246.field_10515, class_2246.field_10508, class_2246.field_10474, class_2246.field_10115});
    }

    private void lambda$onTick$1(FindItemResult findItemResult, class_2338 class_23382, class_2680 class_26802) {
        if (this.isSource(class_26802)) {
            class_2248 class_22482 = class_26802.method_26204();
            PlaceIn placeIn = this.placeInLiquids.get();
            if ((placeIn == PlaceIn.Both || placeIn == PlaceIn.Lava && class_22482 == class_2246.field_10164 || placeIn == PlaceIn.Water && class_22482 == class_2246.field_10382) && BlockUtils.place(class_23382, findItemResult, this.rotate.get(), 0, true)) {
                BlockIterator.disableCurrent();
            }
        }
    }

    public LiquidFiller() {
        super(Categories.World, "liquid-filler", "Places blocks inside of liquid source blocks within range of you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.placeInLiquids = this.sgGeneral.add(new EnumSetting.Builder().name("place-in").description("What type of liquids to place in.").defaultValue(PlaceIn.Lava).build());
        this.horizontalRadius = this.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for liquids.").defaultValue(4).min(0).sliderMax(6).build());
        this.verticalRadius = this.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for liquids.").defaultValue(4).min(0).sliderMax(6).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between actions in ticks.").defaultValue(1).min(0).build());
        this.whitelist = this.sgGeneral.add(new BlockListSetting.Builder().name("block-whitelist").description("The allowed blocks that it will use to fill up the liquid.").defaultValue(this.getDefaultWhitelist()).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the space targeted for filling.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.timer < this.delay.get()) {
            ++this.timer;
            return;
        }
        this.timer = 0;
        FindItemResult findItemResult = InvUtils.findInHotbar(this::lambda$onTick$0);
        if (!findItemResult.found()) {
            return;
        }
        BlockIterator.register(this.horizontalRadius.get(), this.verticalRadius.get(), (arg_0, arg_1) -> this.lambda$onTick$1(findItemResult, arg_0, arg_1));
    }

    @Override
    public void onActivate() {
        this.timer = 0;
    }

    public static enum PlaceIn {
        Lava,
        Water,
        Both;

    }
}

