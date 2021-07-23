/*
 * Decompiled with CFR 0.151.
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
    private boolean didMove;
    private final Setting<List<class_1792>> blacklist;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> autoDisable;
    private final Setting<Boolean> force;

    private int getEmptySlot() {
        for (int i = 0; i < this.mc.field_1724.field_7514.field_7547.size(); ++i) {
            if (!this.mc.field_1724.field_7514.method_5438(i).method_7960()) continue;
            return i;
        }
        return -1;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.shouldWait()) {
            return;
        }
        int n = this.getSlot();
        if (n == -1) {
            if (this.autoDisable.get().booleanValue()) {
                this.info("Repaired all items, disabling", new Object[0]);
                if (this.didMove) {
                    int n2 = this.getEmptySlot();
                    InvUtils.move().fromOffhand().to(n2);
                }
                this.toggle();
            }
        } else {
            InvUtils.move().from(n).toOffhand();
            this.didMove = true;
        }
    }

    private int getSlot() {
        for (int i = 0; i < this.mc.field_1724.field_7514.field_7547.size(); ++i) {
            class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
            if (this.blacklist.get().contains(class_17992.method_7909()) || class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)class_17992) <= 0 || class_17992.method_7919() <= 0) continue;
            return i;
        }
        return -1;
    }

    public AutoMend() {
        super(Categories.Player, "auto-mend", "Automatically replaces items in your offhand with mending when fully repaired.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.blacklist = this.sgGeneral.add(new ItemListSetting.Builder().name("blacklist").description("Item blacklist.").defaultValue(new ArrayList<class_1792>(0)).filter(class_1792::method_7846).build());
        this.force = this.sgGeneral.add(new BoolSetting.Builder().name("force").description("Replaces item in offhand even if there is some other non-repairable item.").defaultValue(false).build());
        this.autoDisable = this.sgGeneral.add(new BoolSetting.Builder().name("auto-disable").description("Automatically disables when there are no more items to repair.").defaultValue(true).build());
    }

    @Override
    public void onActivate() {
        this.didMove = false;
    }

    private boolean shouldWait() {
        class_1799 class_17992 = this.mc.field_1724.method_6079();
        if (class_17992.method_7960()) {
            return false;
        }
        if (class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)class_17992) > 0) {
            return class_17992.method_7919() != 0;
        }
        return this.force.get() == false;
    }
}

