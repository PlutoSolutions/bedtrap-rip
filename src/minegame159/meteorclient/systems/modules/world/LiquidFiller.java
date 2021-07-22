/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
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
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2680;

public class LiquidFiller
extends Module {
    private final /* synthetic */ Setting<Integer> horizontalRadius;
    private final /* synthetic */ Setting<PlaceIn> placeInLiquids;
    private final /* synthetic */ Setting<List<class_2248>> whitelist;
    private /* synthetic */ int timer;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Integer> verticalRadius;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgGeneral;

    private boolean isSource(class_2680 lllllllllllllllllllIIllIlIlIlIll) {
        return lllllllllllllllllllIIllIlIlIlIll.method_26227().method_15761() == 8 && lllllllllllllllllllIIllIlIlIlIll.method_26227().method_15771();
    }

    private List<class_2248> getDefaultWhitelist() {
        return Lists.newArrayList((Object[])new class_2248[]{class_2246.field_10566, class_2246.field_10445, class_2246.field_10340, class_2246.field_10515, class_2246.field_10508, class_2246.field_10474, class_2246.field_10115});
    }

    public LiquidFiller() {
        super(Categories.World, "liquid-filler", "Places blocks inside of liquid source blocks within range of you.");
        LiquidFiller lllllllllllllllllllIIllIlIlllIIl;
        lllllllllllllllllllIIllIlIlllIIl.sgGeneral = lllllllllllllllllllIIllIlIlllIIl.settings.getDefaultGroup();
        lllllllllllllllllllIIllIlIlllIIl.placeInLiquids = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new EnumSetting.Builder().name("place-in").description("What type of liquids to place in.").defaultValue(PlaceIn.Lava).build());
        lllllllllllllllllllIIllIlIlllIIl.horizontalRadius = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for liquids.").defaultValue(4).min(0).sliderMax(6).build());
        lllllllllllllllllllIIllIlIlllIIl.verticalRadius = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for liquids.").defaultValue(4).min(0).sliderMax(6).build());
        lllllllllllllllllllIIllIlIlllIIl.delay = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between actions in ticks.").defaultValue(1).min(0).build());
        lllllllllllllllllllIIllIlIlllIIl.whitelist = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new BlockListSetting.Builder().name("block-whitelist").description("The allowed blocks that it will use to fill up the liquid.").defaultValue(lllllllllllllllllllIIllIlIlllIIl.getDefaultWhitelist()).build());
        lllllllllllllllllllIIllIlIlllIIl.rotate = lllllllllllllllllllIIllIlIlllIIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the space targeted for filling.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIIllIlIllIIlI) {
        LiquidFiller lllllllllllllllllllIIllIlIllIIll;
        if (lllllllllllllllllllIIllIlIllIIll.timer < lllllllllllllllllllIIllIlIllIIll.delay.get()) {
            ++lllllllllllllllllllIIllIlIllIIll.timer;
            return;
        }
        lllllllllllllllllllIIllIlIllIIll.timer = 0;
        FindItemResult lllllllllllllllllllIIllIlIllIIIl = InvUtils.findInHotbar(lllllllllllllllllllIIllIlIIlIIlI -> {
            LiquidFiller lllllllllllllllllllIIllIlIIlIIll;
            return lllllllllllllllllllIIllIlIIlIIlI.method_7909() instanceof class_1747 && lllllllllllllllllllIIllIlIIlIIll.whitelist.get().contains((Object)class_2248.method_9503((class_1792)lllllllllllllllllllIIllIlIIlIIlI.method_7909()));
        });
        if (!lllllllllllllllllllIIllIlIllIIIl.found()) {
            return;
        }
        BlockIterator.register(lllllllllllllllllllIIllIlIllIIll.horizontalRadius.get(), lllllllllllllllllllIIllIlIllIIll.verticalRadius.get(), (lllllllllllllllllllIIllIlIIllIll, lllllllllllllllllllIIllIlIIllIlI) -> {
            LiquidFiller lllllllllllllllllllIIllIlIlIIIIl;
            if (lllllllllllllllllllIIllIlIlIIIIl.isSource((class_2680)lllllllllllllllllllIIllIlIIllIlI)) {
                class_2248 lllllllllllllllllllIIllIlIlIIIll = lllllllllllllllllllIIllIlIIllIlI.method_26204();
                PlaceIn lllllllllllllllllllIIllIlIlIIIlI = lllllllllllllllllllIIllIlIlIIIIl.placeInLiquids.get();
                if ((lllllllllllllllllllIIllIlIlIIIlI == PlaceIn.Both || lllllllllllllllllllIIllIlIlIIIlI == PlaceIn.Lava && lllllllllllllllllllIIllIlIlIIIll == class_2246.field_10164 || lllllllllllllllllllIIllIlIlIIIlI == PlaceIn.Water && lllllllllllllllllllIIllIlIlIIIll == class_2246.field_10382) && BlockUtils.place(lllllllllllllllllllIIllIlIIllIll, lllllllllllllllllllIIllIlIllIIIl, lllllllllllllllllllIIllIlIlIIIIl.rotate.get(), 0, true)) {
                    BlockIterator.disableCurrent();
                }
            }
        });
    }

    @Override
    public void onActivate() {
        lllllllllllllllllllIIllIlIllIllI.timer = 0;
    }

    public static enum PlaceIn {
        Lava,
        Water,
        Both;


        private PlaceIn() {
            PlaceIn lllllllllllllllllIlIlllIllIIIlIl;
        }
    }
}

