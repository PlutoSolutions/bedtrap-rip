/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1802;

public class SmartSurround
extends Module {
    private /* synthetic */ int rPosX;
    private /* synthetic */ int rPosZ;
    private final /* synthetic */ Setting<Double> minDamage;
    private /* synthetic */ class_1297 crystal;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgGeneral;

    public SmartSurround() {
        super(Categories.Combat, "smart-surround", "Attempts to save you from crystals automatically.");
        SmartSurround lIlIlIIlIllIIl;
        lIlIlIIlIllIIl.sgGeneral = lIlIlIIlIllIIl.settings.getDefaultGroup();
        lIlIlIIlIllIIl.minDamage = lIlIlIIlIllIIl.sgGeneral.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage before this activates.").defaultValue(5.5).build());
        lIlIlIIlIllIIl.rotate = lIlIlIIlIllIIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Forces you to rotate towards the blocks being placed.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIlIlIIIllIIIl) {
        SmartSurround lIlIlIIIlIlllI;
        FindItemResult lIlIlIIIllIIII = InvUtils.findInHotbar(class_1802.field_8281, class_1802.field_22421);
        if (!lIlIlIIIllIIII.found()) {
            return;
        }
        int lIlIlIIIlIllll = lIlIlIIIlIlllI.mc.field_1724.field_7514.field_7545;
        if (lIlIlIIIlIlllI.rPosX >= 2 && lIlIlIIIlIlllI.rPosZ == 0) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX - 1, 0, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX > 1 && lIlIlIIIlIlllI.rPosZ > 1) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX, lIlIlIIIlIlllI.rPosZ - 1, lIlIlIIIllIIII);
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX - 1, lIlIlIIIlIlllI.rPosZ, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX == 0 && lIlIlIIIlIlllI.rPosZ >= 2) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, 0, lIlIlIIIlIlllI.rPosZ - 1, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX < -1 && lIlIlIIIlIlllI.rPosZ < -1) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX, lIlIlIIIlIlllI.rPosZ + 1, lIlIlIIIllIIII);
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX + 1, lIlIlIIIlIlllI.rPosZ, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX == 0 && lIlIlIIIlIlllI.rPosZ <= -2) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, 0, lIlIlIIIlIlllI.rPosZ + 1, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX > 1 && lIlIlIIIlIlllI.rPosZ < -1) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX, lIlIlIIIlIlllI.rPosZ + 1, lIlIlIIIllIIII);
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX - 1, lIlIlIIIlIlllI.rPosZ, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX <= -2 && lIlIlIIIlIlllI.rPosZ == 0) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX + 1, 0, lIlIlIIIllIIII);
        } else if (lIlIlIIIlIlllI.rPosX < -1 && lIlIlIIIlIlllI.rPosZ > 1) {
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX, lIlIlIIIlIlllI.rPosZ - 1, lIlIlIIIllIIII);
            lIlIlIIIlIlllI.placeObi(lIlIlIIIlIlllI.crystal, lIlIlIIIlIlllI.rPosX + 1, lIlIlIIIlIlllI.rPosZ, lIlIlIIIllIIII);
        }
        InvUtils.swap(lIlIlIIIlIllll);
    }

    private void placeObi(class_1297 lIlIlIIIlIIIII, int lIlIlIIIlIIlII, int lIlIlIIIIllllI, FindItemResult lIlIlIIIIlllIl) {
        SmartSurround lIlIlIIIlIIllI;
        BlockUtils.place(lIlIlIIIlIIIII.method_24515().method_10069(lIlIlIIIlIIlII, -1, lIlIlIIIIllllI), lIlIlIIIIlllIl, lIlIlIIIlIIllI.rotate.get(), 100, false, true, false);
    }

    @EventHandler
    private void onSpawn(EntityAddedEvent lIlIlIIlIIlllI) {
        SmartSurround lIlIlIIlIlIIlI;
        lIlIlIIlIlIIlI.crystal = lIlIlIIlIIlllI.entity;
        if (lIlIlIIlIIlllI.entity.method_5864() == class_1299.field_6110 && DamageCalcUtils.crystalDamage((class_1309)lIlIlIIlIlIIlI.mc.field_1724, lIlIlIIlIIlllI.entity.method_19538()) > lIlIlIIlIlIIlI.minDamage.get()) {
            lIlIlIIlIlIIlI.rPosX = lIlIlIIlIlIIlI.mc.field_1724.method_24515().method_10263() - lIlIlIIlIIlllI.entity.method_24515().method_10263();
            lIlIlIIlIlIIlI.rPosZ = lIlIlIIlIlIIlI.mc.field_1724.method_24515().method_10260() - lIlIlIIlIIlllI.entity.method_24515().method_10260();
        }
    }
}

