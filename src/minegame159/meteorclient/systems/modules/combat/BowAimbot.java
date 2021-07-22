/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1657
 *  net.minecraft.class_1744
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.combat;

import baritone.api.BaritoneAPI;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_1744;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_243;

public class BowAimbot
extends Module {
    private final /* synthetic */ Setting<SortPriority> priority;
    private /* synthetic */ boolean wasPathing;
    private final /* synthetic */ Setting<Boolean> babies;
    private final /* synthetic */ Setting<Boolean> pauseOnCombat;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private /* synthetic */ class_1297 target;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Setting<Boolean> nametagged;
    private final /* synthetic */ SettingGroup sgGeneral;

    private boolean itemInHand() {
        BowAimbot llllllllllllllllllIlIlllIlllllll;
        return llllllllllllllllllIlIlllIlllllll.mc.field_1724.method_6047().method_7909() instanceof class_1753 || llllllllllllllllllIlIlllIlllllll.mc.field_1724.method_6047().method_7909() instanceof class_1764;
    }

    @Override
    public String getInfoString() {
        BowAimbot llllllllllllllllllIlIllllIIIIIlI;
        if (llllllllllllllllllIlIllllIIIIIlI.target != null) {
            if (llllllllllllllllllIlIllllIIIIIlI.target instanceof class_1657) {
                return llllllllllllllllllIlIllllIIIIIlI.target.method_5820();
            }
            return llllllllllllllllllIlIllllIIIIIlI.target.method_5864().method_5897().getString();
        }
        return null;
    }

    private void aim(double llllllllllllllllllIlIlllIllIlllI) {
        BowAimbot llllllllllllllllllIlIlllIllIIIIl;
        float llllllllllllllllllIlIlllIllIllIl = (float)(llllllllllllllllllIlIlllIllIIIIl.mc.field_1724.method_6048() - llllllllllllllllllIlIlllIllIIIIl.mc.field_1724.method_6014()) / 20.0f;
        if ((llllllllllllllllllIlIlllIllIllIl = (llllllllllllllllllIlIlllIllIllIl * llllllllllllllllllIlIlllIllIllIl + llllllllllllllllllIlIlllIllIllIl * 2.0f) / 3.0f) > 1.0f) {
            llllllllllllllllllIlIlllIllIllIl = 1.0f;
        }
        double llllllllllllllllllIlIlllIllIllII = llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10216() + (llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10216() - llllllllllllllllllIlIlllIllIIIIl.target.field_6014) * llllllllllllllllllIlIlllIllIlllI;
        double llllllllllllllllllIlIlllIllIlIll = llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10214() + (llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10214() - llllllllllllllllllIlIlllIllIIIIl.target.field_6036) * llllllllllllllllllIlIlllIllIlllI;
        double llllllllllllllllllIlIlllIllIlIlI = llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10215() + (llllllllllllllllllIlIlllIllIIIIl.target.method_19538().method_10215() - llllllllllllllllllIlIlllIllIIIIl.target.field_5969) * llllllllllllllllllIlIlllIllIlllI;
        double llllllllllllllllllIlIlllIllIlIIl = llllllllllllllllllIlIlllIllIllII - llllllllllllllllllIlIlllIllIIIIl.mc.field_1724.method_23317();
        double llllllllllllllllllIlIlllIllIlIII = (llllllllllllllllllIlIlllIllIlIll -= (double)(1.9f - llllllllllllllllllIlIlllIllIIIIl.target.method_17682())) - llllllllllllllllllIlIlllIllIIIIl.mc.field_1724.method_23318();
        float llllllllllllllllllIlIlllIllIIIll = llllllllllllllllllIlIlllIllIllIl * llllllllllllllllllIlIlllIllIllIl;
        float llllllllllllllllllIlIlllIllIIlII = 0.006f;
        double llllllllllllllllllIlIlllIllIIlll = llllllllllllllllllIlIlllIllIlIlI - llllllllllllllllllIlIlllIllIIIIl.mc.field_1724.method_23321();
        double llllllllllllllllllIlIlllIllIIllI = Math.sqrt(llllllllllllllllllIlIlllIllIlIIl * llllllllllllllllllIlIlllIllIlIIl + llllllllllllllllllIlIlllIllIIlll * llllllllllllllllllIlIlllIllIIlll);
        double llllllllllllllllllIlIlllIllIIlIl = llllllllllllllllllIlIlllIllIIllI * llllllllllllllllllIlIlllIllIIllI;
        float llllllllllllllllllIlIlllIllIIIlI = (float)(-Math.toDegrees(Math.atan(((double)llllllllllllllllllIlIlllIllIIIll - Math.sqrt((double)(llllllllllllllllllIlIlllIllIIIll * llllllllllllllllllIlIlllIllIIIll) - (double)llllllllllllllllllIlIlllIllIIlII * ((double)llllllllllllllllllIlIlllIllIIlII * llllllllllllllllllIlIlllIllIIlIl + 2.0 * llllllllllllllllllIlIlllIllIlIII * (double)llllllllllllllllllIlIlllIllIIIll))) / ((double)llllllllllllllllllIlIlllIllIIlII * llllllllllllllllllIlIlllIllIIllI))));
        if (Float.isNaN(llllllllllllllllllIlIlllIllIIIlI)) {
            Rotations.rotate(Rotations.getYaw(llllllllllllllllllIlIlllIllIIIIl.target), Rotations.getPitch(llllllllllllllllllIlIlllIllIIIIl.target));
        } else {
            Rotations.rotate(Rotations.getYaw(new class_243(llllllllllllllllllIlIlllIllIllII, llllllllllllllllllIlIlllIllIlIll, llllllllllllllllllIlIlllIllIlIlI)), llllllllllllllllllIlIlllIllIIIlI);
        }
    }

    private boolean playerIsDead() {
        BowAimbot llllllllllllllllllIlIlllIlIlIIlI;
        return llllllllllllllllllIlIlllIlIlIIlI.mc.field_1724.method_29504() || !llllllllllllllllllIlIlllIlIlIIlI.mc.field_1724.method_5805();
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllllIlIllllIIIIlII) {
        BowAimbot llllllllllllllllllIlIllllIIIIlll;
        if (llllllllllllllllllIlIllllIIIIlll.playerIsDead() || !llllllllllllllllllIlIllllIIIIlll.itemInHand()) {
            return;
        }
        if (!InvUtils.find(llllllllllllllllllIlIlllIlIIlIII -> llllllllllllllllllIlIlllIlIIlIII.method_7909() instanceof class_1744).found()) {
            return;
        }
        llllllllllllllllllIlIllllIIIIlll.target = TargetUtils.get(llllllllllllllllllIlIlllIlIIlIll -> {
            BowAimbot llllllllllllllllllIlIlllIlIIllII;
            if (llllllllllllllllllIlIlllIlIIlIll == llllllllllllllllllIlIlllIlIIllII.mc.field_1724 || llllllllllllllllllIlIlllIlIIlIll == llllllllllllllllllIlIlllIlIIllII.mc.field_1719) {
                return false;
            }
            if (llllllllllllllllllIlIlllIlIIlIll instanceof class_1309 && ((class_1309)llllllllllllllllllIlIlllIlIIlIll).method_29504() || !llllllllllllllllllIlIlllIlIIlIll.method_5805()) {
                return false;
            }
            if ((double)llllllllllllllllllIlIlllIlIIlIll.method_5739((class_1297)llllllllllllllllllIlIlllIlIIllII.mc.field_1724) > llllllllllllllllllIlIlllIlIIllII.range.get()) {
                return false;
            }
            if (!llllllllllllllllllIlIlllIlIIllII.entities.get().getBoolean((Object)llllllllllllllllllIlIlllIlIIlIll.method_5864())) {
                return false;
            }
            if (!llllllllllllllllllIlIlllIlIIllII.nametagged.get().booleanValue() && llllllllllllllllllIlIlllIlIIlIll.method_16914()) {
                return false;
            }
            if (!PlayerUtils.canSeeEntity(llllllllllllllllllIlIlllIlIIlIll)) {
                return false;
            }
            if (llllllllllllllllllIlIlllIlIIlIll instanceof class_1657) {
                if (((class_1657)llllllllllllllllllIlIlllIlIIlIll).method_7337()) {
                    return false;
                }
                if (!Friends.get().shouldAttack((class_1657)llllllllllllllllllIlIlllIlIIlIll)) {
                    return false;
                }
            }
            return !(llllllllllllllllllIlIlllIlIIlIll instanceof class_1429) || llllllllllllllllllIlIlllIlIIllII.babies.get() != false || !((class_1429)llllllllllllllllllIlIlllIlIIlIll).method_6109();
        }, llllllllllllllllllIlIllllIIIIlll.priority.get());
        if (llllllllllllllllllIlIllllIIIIlll.target == null) {
            if (llllllllllllllllllIlIllllIIIIlll.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
                llllllllllllllllllIlIllllIIIIlll.wasPathing = false;
            }
            return;
        }
        if (llllllllllllllllllIlIllllIIIIlll.mc.field_1690.field_1904.method_1434() && llllllllllllllllllIlIllllIIIIlll.itemInHand()) {
            if (llllllllllllllllllIlIllllIIIIlll.pauseOnCombat.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && !llllllllllllllllllIlIllllIIIIlll.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
                llllllllllllllllllIlIllllIIIIlll.wasPathing = true;
            }
            llllllllllllllllllIlIllllIIIIlll.aim(llllllllllllllllllIlIllllIIIIlII.tickDelta);
        }
    }

    @Override
    public void onDeactivate() {
        llllllllllllllllllIlIllllIIIlIlI.target = null;
        llllllllllllllllllIlIllllIIIlIlI.wasPathing = false;
    }

    public BowAimbot() {
        super(Categories.Combat, "Bow Aimbot", "Automatically aims your bow for you.");
        BowAimbot llllllllllllllllllIlIllllIIIlllI;
        llllllllllllllllllIlIllllIIIlllI.sgGeneral = llllllllllllllllllIlIllllIIIlllI.settings.getDefaultGroup();
        llllllllllllllllllIlIllllIIIlllI.range = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The maximum range the entity can be to aim at it.").defaultValue(20.0).min(0.0).max(100.0).sliderMax(100.0).build());
        llllllllllllllllllIlIllllIIIlllI.entities = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        llllllllllllllllllIlIllllIIIlllI.priority = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new EnumSetting.Builder().name("priority").description("What type of entities to target.").defaultValue(SortPriority.LowestHealth).build());
        llllllllllllllllllIlIllllIIIlllI.babies = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new BoolSetting.Builder().name("babies").description("Whether or not to attack baby variants of the entity.").defaultValue(true).build());
        llllllllllllllllllIlIllllIIIlllI.nametagged = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new BoolSetting.Builder().name("nametagged").description("Whether or not to attack mobs with a name tag.").defaultValue(false).build());
        llllllllllllllllllIlIllllIIIlllI.pauseOnCombat = llllllllllllllllllIlIllllIIIlllI.sgGeneral.add(new BoolSetting.Builder().name("pause-on-combat").description("Freezes Baritone temporarily until you released the bow.").defaultValue(false).build());
    }
}

