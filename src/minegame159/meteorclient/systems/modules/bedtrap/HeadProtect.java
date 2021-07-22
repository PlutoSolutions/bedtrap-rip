/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;

public class HeadProtect
extends Module {
    private final /* synthetic */ Setting<Boolean> obbyPlace;
    private final /* synthetic */ Setting<Boolean> obbyPlaceUp;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ SettingGroup sgGeneral;

    private void breakTnt() {
        HeadProtect lllllllllllllllllIlllIIIIllIIlll;
        lllllllllllllllllIlllIIIIllIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, (class_2338)lllllllllllllllllIlllIIIIllIIlll.blockPos, class_2350.field_11036));
        lllllllllllllllllIlllIIIIllIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, (class_2338)lllllllllllllllllIlllIIIIllIIlll.blockPos, class_2350.field_11036));
        lllllllllllllllllIlllIIIIllIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, lllllllllllllllllIlllIIIIllIIlll.blockPos.method_10084(), class_2350.field_11036));
        lllllllllllllllllIlllIIIIllIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, lllllllllllllllllIlllIIIIllIIlll.blockPos.method_10084(), class_2350.field_11036));
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlllIIIIllIllIl) {
        HeadProtect lllllllllllllllllIlllIIIIllIlIll;
        assert (lllllllllllllllllIlllIIIIllIlIll.mc.field_1724 != null);
        assert (lllllllllllllllllIlllIIIIllIlIll.mc.field_1687 != null);
        FindItemResult lllllllllllllllllIlllIIIIllIllII = InvUtils.findInHotbar(class_1802.field_8281);
        if (!lllllllllllllllllIlllIIIIllIllII.found()) {
            return;
        }
        lllllllllllllllllIlllIIIIllIlIll.blockPos.method_10102(lllllllllllllllllIlllIIIIllIlIll.mc.field_1724.method_23317(), lllllllllllllllllIlllIIIIllIlIll.mc.field_1724.method_23318() + 2.0, lllllllllllllllllIlllIIIIllIlIll.mc.field_1724.method_23321());
        if (lllllllllllllllllIlllIIIIllIlIll.obbyPlace.get().booleanValue() && lllllllllllllllllIlllIIIIllIlIll.mc.field_1687.method_8320((class_2338)lllllllllllllllllIlllIIIIllIlIll.blockPos).method_26215()) {
            BlockUtils.place((class_2338)lllllllllllllllllIlllIIIIllIlIll.blockPos, lllllllllllllllllIlllIIIIllIllII, false, 50, true);
        }
        if (lllllllllllllllllIlllIIIIllIlIll.obbyPlaceUp.get().booleanValue() && lllllllllllllllllIlllIIIIllIlIll.mc.field_1687.method_8320(lllllllllllllllllIlllIIIIllIlIll.blockPos.method_10084()).method_26215()) {
            BlockUtils.place(lllllllllllllllllIlllIIIIllIlIll.blockPos.method_10084(), lllllllllllllllllIlllIIIIllIllII, false, 50, true);
        }
        if (lllllllllllllllllIlllIIIIllIlIll.mc.field_1687.method_8320((class_2338)lllllllllllllllllIlllIIIIllIlIll.blockPos).method_26204() == class_2246.field_10375) {
            lllllllllllllllllIlllIIIIllIlIll.breakTnt();
        }
    }

    public HeadProtect() {
        super(Categories.BedTrap, "head-protect", "Prevent from getting fucked up by TNT-aura module.");
        HeadProtect lllllllllllllllllIlllIIIIlllIIlI;
        lllllllllllllllllIlllIIIIlllIIlI.sgGeneral = lllllllllllllllllIlllIIIIlllIIlI.settings.getDefaultGroup();
        lllllllllllllllllIlllIIIIlllIIlI.blockPos = new class_2338.class_2339();
        lllllllllllllllllIlllIIIIlllIIlI.obbyPlace = lllllllllllllllllIlllIIIIlllIIlI.sgGeneral.add(new BoolSetting.Builder().name("place-obsidian").description("Place obsidian on TNT position.").defaultValue(true).build());
        lllllllllllllllllIlllIIIIlllIIlI.obbyPlaceUp = lllllllllllllllllIlllIIIIlllIIlI.sgGeneral.add(new BoolSetting.Builder().name("double-obsidian").description("Place obsidian higher the TNT position.").defaultValue(true).build());
    }
}

