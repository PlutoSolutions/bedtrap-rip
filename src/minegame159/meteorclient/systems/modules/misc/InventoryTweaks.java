/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_465
 */
package minegame159.meteorclient.systems.modules.misc;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.DropItemsEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ItemListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1792;
import net.minecraft.class_465;

public class InventoryTweaks
extends Module {
    private final SettingGroup sgGeneral;
    private final SettingGroup sgAutoDrop;
    private final Setting<Boolean> autoDropExcludeHotbar;
    private final Setting<List<class_1792>> antiDropItems;
    private final Setting<List<class_1792>> autoDropItems;
    private final Setting<Boolean> mouseDragItemMove;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        int n;
        if (this.mc.field_1755 instanceof class_465 || this.autoDropItems.get().isEmpty()) {
            return;
        }
        int n2 = n = this.autoDropExcludeHotbar.get() != false ? 9 : 0;
        while (n < this.mc.field_1724.field_7514.method_5439()) {
            if (this.autoDropItems.get().contains((Object)this.mc.field_1724.field_7514.method_5438(n).method_7909())) {
                InvUtils.drop().slot(n);
            }
            ++n;
            if (1 <= 2) continue;
            return;
        }
    }

    public boolean mouseDragItemMove() {
        return this.isActive() && this.mouseDragItemMove.get() != false;
    }

    public InventoryTweaks() {
        super(Categories.Misc, "inventory-tweaks", "Various inventory related utilities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgAutoDrop = this.settings.createGroup("Auto Drop");
        this.mouseDragItemMove = this.sgGeneral.add(new BoolSetting.Builder().name("mouse-drag-item-move").description("Moving mouse over items while holding shift will transfer it to the other container.").defaultValue(true).build());
        this.antiDropItems = this.sgGeneral.add(new ItemListSetting.Builder().name("anti-drop-items").description("Items to prevent dropping. Doesn't work in creative inventory screen.").defaultValue(new ArrayList<class_1792>(0)).build());
        this.autoDropItems = this.sgAutoDrop.add(new ItemListSetting.Builder().name("auto-drop-items").description("Items to drop.").defaultValue(new ArrayList<class_1792>(0)).build());
        this.autoDropExcludeHotbar = this.sgAutoDrop.add(new BoolSetting.Builder().name("auto-drop-exclude-hotbar").description("Whether or not to drop items from your hotbar.").defaultValue(false).build());
    }

    @EventHandler
    private void onDropItems(DropItemsEvent dropItemsEvent) {
        if (this.antiDropItems.get().contains((Object)dropItemsEvent.itemStack.method_7909())) {
            dropItemsEvent.cancel();
        }
    }
}

