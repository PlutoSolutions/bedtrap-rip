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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ SettingGroup sgAutoDrop;
    private final /* synthetic */ Setting<Boolean> autoDropExcludeHotbar;
    private final /* synthetic */ Setting<List<class_1792>> antiDropItems;
    private final /* synthetic */ Setting<List<class_1792>> autoDropItems;
    private final /* synthetic */ Setting<Boolean> mouseDragItemMove;

    @EventHandler
    private void onTick(TickEvent.Post lIllIIllllllII) {
        int lIllIIlllllllI;
        InventoryTweaks lIllIIllllllIl;
        if (lIllIIllllllIl.mc.field_1755 instanceof class_465 || lIllIIllllllIl.autoDropItems.get().isEmpty()) {
            return;
        }
        int n = lIllIIlllllllI = lIllIIllllllIl.autoDropExcludeHotbar.get() != false ? 9 : 0;
        while (lIllIIlllllllI < lIllIIllllllIl.mc.field_1724.field_7514.method_5439()) {
            if (lIllIIllllllIl.autoDropItems.get().contains((Object)lIllIIllllllIl.mc.field_1724.field_7514.method_5438(lIllIIlllllllI).method_7909())) {
                InvUtils.drop().slot(lIllIIlllllllI);
            }
            ++lIllIIlllllllI;
        }
    }

    public boolean mouseDragItemMove() {
        InventoryTweaks lIllIIllllIIIl;
        return lIllIIllllIIIl.isActive() && lIllIIllllIIIl.mouseDragItemMove.get() != false;
    }

    public InventoryTweaks() {
        super(Categories.Misc, "inventory-tweaks", "Various inventory related utilities.");
        InventoryTweaks lIllIlIIIIIIlI;
        lIllIlIIIIIIlI.sgGeneral = lIllIlIIIIIIlI.settings.getDefaultGroup();
        lIllIlIIIIIIlI.sgAutoDrop = lIllIlIIIIIIlI.settings.createGroup("Auto Drop");
        lIllIlIIIIIIlI.mouseDragItemMove = lIllIlIIIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("mouse-drag-item-move").description("Moving mouse over items while holding shift will transfer it to the other container.").defaultValue(true).build());
        lIllIlIIIIIIlI.antiDropItems = lIllIlIIIIIIlI.sgGeneral.add(new ItemListSetting.Builder().name("anti-drop-items").description("Items to prevent dropping. Doesn't work in creative inventory screen.").defaultValue(new ArrayList<class_1792>(0)).build());
        lIllIlIIIIIIlI.autoDropItems = lIllIlIIIIIIlI.sgAutoDrop.add(new ItemListSetting.Builder().name("auto-drop-items").description("Items to drop.").defaultValue(new ArrayList<class_1792>(0)).build());
        lIllIlIIIIIIlI.autoDropExcludeHotbar = lIllIlIIIIIIlI.sgAutoDrop.add(new BoolSetting.Builder().name("auto-drop-exclude-hotbar").description("Whether or not to drop items from your hotbar.").defaultValue(false).build());
    }

    @EventHandler
    private void onDropItems(DropItemsEvent lIllIIllllIllI) {
        InventoryTweaks lIllIIllllIlll;
        if (lIllIIllllIlll.antiDropItems.get().contains((Object)lIllIIllllIllI.itemStack.method_7909())) {
            lIllIIllllIllI.cancel();
        }
    }
}

