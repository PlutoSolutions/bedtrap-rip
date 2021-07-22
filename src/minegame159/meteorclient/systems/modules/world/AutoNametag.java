/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1807
 */
package minegame159.meteorclient.systems.modules.world;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1807;

public class AutoNametag
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ int preSlot;
    private final /* synthetic */ Setting<Double> distance;
    private /* synthetic */ class_1297 entity;
    private /* synthetic */ boolean offHand;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Boolean> rotate;

    public AutoNametag() {
        super(Categories.World, "auto-nametag", "Automatically uses nametags on entities without a nametag. WILL nametag ALL entities in the specified distance.");
        AutoNametag lllllllllllllllllIIIlIlIllIIlIll;
        lllllllllllllllllIIIlIlIllIIlIll.sgGeneral = lllllllllllllllllIIIlIlIllIIlIll.settings.getDefaultGroup();
        lllllllllllllllllIIIlIlIllIIlIll.entities = lllllllllllllllllIIIlIlIllIIlIll.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Which entities to nametag.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).build());
        lllllllllllllllllIIIlIlIllIIlIll.distance = lllllllllllllllllIIIlIlIllIIlIll.sgGeneral.add(new DoubleSetting.Builder().name("distance").description("The maximum distance a nametagged entity can be to be nametagged.").min(0.0).defaultValue(5.0).build());
        lllllllllllllllllIIIlIlIllIIlIll.rotate = lllllllllllllllllIIIlIlIllIIlIll.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically faces towards the mob being nametagged.").defaultValue(true).build());
    }

    private void interact() {
        AutoNametag lllllllllllllllllIIIlIlIlIlIllll;
        lllllllllllllllllIIIlIlIlIlIllll.mc.field_1761.method_2905((class_1657)lllllllllllllllllIIIlIlIlIlIllll.mc.field_1724, lllllllllllllllllIIIlIlIlIlIllll.entity, lllllllllllllllllIIIlIlIlIlIllll.offHand ? class_1268.field_5810 : class_1268.field_5808);
        InvUtils.swap(lllllllllllllllllIIIlIlIlIlIllll.preSlot);
    }

    @Override
    public void onDeactivate() {
        lllllllllllllllllIIIlIlIllIIIlll.entity = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIIIlIlIlIlllIIl) {
        AutoNametag lllllllllllllllllIIIlIlIlIlllIlI;
        lllllllllllllllllIIIlIlIlIlllIlI.entity = null;
        for (class_1297 lllllllllllllllllIIIlIlIlIlllIll : lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1687.method_18112()) {
            boolean lllllllllllllllllIIIlIlIlIllllII;
            if (!lllllllllllllllllIIIlIlIlIlllIlI.entities.get().getBoolean((Object)lllllllllllllllllIIIlIlIlIlllIll.method_5864()) || lllllllllllllllllIIIlIlIlIlllIll.method_16914() || (double)lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1724.method_5739(lllllllllllllllllIIIlIlIlIlllIll) > lllllllllllllllllIIIlIlIlIlllIlI.distance.get()) continue;
            boolean lllllllllllllllllIIIlIlIlIllllIl = true;
            if (lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1724.field_7514.method_7391().method_7909() instanceof class_1807) {
                lllllllllllllllllIIIlIlIlIllllIl = false;
            } else if (((class_1799)lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1724.field_7514.field_7544.get(0)).method_7909() instanceof class_1807) {
                lllllllllllllllllIIIlIlIlIllllIl = false;
                lllllllllllllllllIIIlIlIlIlllIlI.offHand = true;
            }
            boolean bl = lllllllllllllllllIIIlIlIlIllllII = !lllllllllllllllllIIIlIlIlIllllIl;
            if (lllllllllllllllllIIIlIlIlIllllIl) {
                for (int lllllllllllllllllIIIlIlIlIlllllI = 0; lllllllllllllllllIIIlIlIlIlllllI < 9; ++lllllllllllllllllIIIlIlIlIlllllI) {
                    class_1799 lllllllllllllllllIIIlIlIlIllllll = lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1724.field_7514.method_5438(lllllllllllllllllIIIlIlIlIlllllI);
                    if (!(lllllllllllllllllIIIlIlIlIllllll.method_7909() instanceof class_1807)) continue;
                    lllllllllllllllllIIIlIlIlIlllIlI.preSlot = lllllllllllllllllIIIlIlIlIlllIlI.mc.field_1724.field_7514.field_7545;
                    InvUtils.swap(lllllllllllllllllIIIlIlIlIlllllI);
                    lllllllllllllllllIIIlIlIlIllllII = true;
                    break;
                }
            }
            if (!lllllllllllllllllIIIlIlIlIllllII) continue;
            lllllllllllllllllIIIlIlIlIlllIlI.entity = lllllllllllllllllIIIlIlIlIlllIll;
            if (lllllllllllllllllIIIlIlIlIlllIlI.rotate.get().booleanValue()) {
                Rotations.rotate(Rotations.getYaw(lllllllllllllllllIIIlIlIlIlllIll), Rotations.getPitch(lllllllllllllllllIIIlIlIlIlllIll), -100, lllllllllllllllllIIIlIlIlIlllIlI::interact);
            } else {
                lllllllllllllllllIIIlIlIlIlllIlI.interact();
            }
            return;
        }
    }
}

