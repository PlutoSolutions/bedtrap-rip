/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 */
package minegame159.meteorclient.systems.modules.player;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;

public class AutoMend
extends Module {
    private /* synthetic */ boolean didMove;
    private final /* synthetic */ Setting<List<class_1792>> blacklist;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> autoDisable;
    private final /* synthetic */ Setting<Boolean> force;

    private int getEmptySlot() {
        AutoMend lllllllllllllllllllllIIIIIllIIII;
        for (int lllllllllllllllllllllIIIIIllIIIl = 0; lllllllllllllllllllllIIIIIllIIIl < lllllllllllllllllllllIIIIIllIIII.mc.field_1724.field_7514.field_7547.size(); ++lllllllllllllllllllllIIIIIllIIIl) {
            if (!lllllllllllllllllllllIIIIIllIIII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllllIIIIIllIIIl).method_7960()) continue;
            return lllllllllllllllllllllIIIIIllIIIl;
        }
        return -1;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllllIIIIlIIIlll) {
        AutoMend lllllllllllllllllllllIIIIlIIIlIl;
        if (lllllllllllllllllllllIIIIlIIIlIl.shouldWait()) {
            return;
        }
        int lllllllllllllllllllllIIIIlIIIllI = lllllllllllllllllllllIIIIlIIIlIl.getSlot();
        if (lllllllllllllllllllllIIIIlIIIllI == -1) {
            if (lllllllllllllllllllllIIIIlIIIlIl.autoDisable.get().booleanValue()) {
                lllllllllllllllllllllIIIIlIIIlIl.info("Repaired all items, disabling", new Object[0]);
                if (lllllllllllllllllllllIIIIlIIIlIl.didMove) {
                    int lllllllllllllllllllllIIIIlIIlIIl = lllllllllllllllllllllIIIIlIIIlIl.getEmptySlot();
                    InvUtils.move().fromOffhand().to(lllllllllllllllllllllIIIIlIIlIIl);
                }
                lllllllllllllllllllllIIIIlIIIlIl.toggle();
            }
        } else {
            InvUtils.move().from(lllllllllllllllllllllIIIIlIIIllI).toOffhand();
            lllllllllllllllllllllIIIIlIIIlIl.didMove = true;
        }
    }

    private int getSlot() {
        AutoMend lllllllllllllllllllllIIIIIllIlll;
        for (int lllllllllllllllllllllIIIIIlllIII = 0; lllllllllllllllllllllIIIIIlllIII < lllllllllllllllllllllIIIIIllIlll.mc.field_1724.field_7514.field_7547.size(); ++lllllllllllllllllllllIIIIIlllIII) {
            class_1799 lllllllllllllllllllllIIIIIlllIIl = lllllllllllllllllllllIIIIIllIlll.mc.field_1724.field_7514.method_5438(lllllllllllllllllllllIIIIIlllIII);
            if (lllllllllllllllllllllIIIIIllIlll.blacklist.get().contains((Object)lllllllllllllllllllllIIIIIlllIIl.method_7909()) || class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)lllllllllllllllllllllIIIIIlllIIl) <= 0 || lllllllllllllllllllllIIIIIlllIIl.method_7919() <= 0) continue;
            return lllllllllllllllllllllIIIIIlllIII;
        }
        return -1;
    }

    public AutoMend() {
        super(Categories.Player, "auto-mend", "Automatically replaces items in your offhand with mending when fully repaired.");
        AutoMend lllllllllllllllllllllIIIIlIlIIII;
        lllllllllllllllllllllIIIIlIlIIII.sgGeneral = lllllllllllllllllllllIIIIlIlIIII.settings.getDefaultGroup();
        lllllllllllllllllllllIIIIlIlIIII.blacklist = lllllllllllllllllllllIIIIlIlIIII.sgGeneral.add(new ItemListSetting.Builder().name("blacklist").description("Item blacklist.").defaultValue(new ArrayList<class_1792>(0)).filter(class_1792::method_7846).build());
        lllllllllllllllllllllIIIIlIlIIII.force = lllllllllllllllllllllIIIIlIlIIII.sgGeneral.add(new BoolSetting.Builder().name("force").description("Replaces item in offhand even if there is some other non-repairable item.").defaultValue(false).build());
        lllllllllllllllllllllIIIIlIlIIII.autoDisable = lllllllllllllllllllllIIIIlIlIIII.sgGeneral.add(new BoolSetting.Builder().name("auto-disable").description("Automatically disables when there are no more items to repair.").defaultValue(true).build());
    }

    @Override
    public void onActivate() {
        lllllllllllllllllllllIIIIlIIlllI.didMove = false;
    }

    private boolean shouldWait() {
        AutoMend lllllllllllllllllllllIIIIlIIIIII;
        class_1799 lllllllllllllllllllllIIIIIllllll = lllllllllllllllllllllIIIIlIIIIII.mc.field_1724.method_6079();
        if (lllllllllllllllllllllIIIIIllllll.method_7960()) {
            return false;
        }
        if (class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)lllllllllllllllllllllIIIIIllllll) > 0) {
            return lllllllllllllllllllllIIIIIllllll.method_7919() != 0;
        }
        return lllllllllllllllllllllIIIIlIIIIII.force.get() == false;
    }
}

