/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2831
 */
package minegame159.meteorclient.systems.modules.combat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class Quiver
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean shooting;
    private final /* synthetic */ Setting<Integer> charge;
    private final /* synthetic */ Setting<Boolean> chatInfo;
    private /* synthetic */ boolean foundSpeed;
    private /* synthetic */ boolean shotStrength;
    private final /* synthetic */ Setting<Boolean> checkEffects;
    private /* synthetic */ int speedSlot;
    private /* synthetic */ int prevSlot;
    private /* synthetic */ ArrowType shootingArrow;
    private /* synthetic */ boolean shouldShoot;
    private /* synthetic */ boolean foundStrength;
    private /* synthetic */ int strengthSlot;
    private /* synthetic */ boolean shotSpeed;

    private Map<ArrowType, Integer> getAllArrows() {
        Quiver llllllllllllllllllllllIIlIlllIIl;
        HashMap<ArrowType, Integer> llllllllllllllllllllllIIlIllllII = new HashMap<ArrowType, Integer>();
        boolean llllllllllllllllllllllIIlIlllIll = llllllllllllllllllllllIIlIlllIIl.mc.field_1724.method_6088().containsKey((Object)class_1294.field_5910);
        boolean llllllllllllllllllllllIIlIlllIlI = llllllllllllllllllllllIIlIlllIIl.mc.field_1724.method_6088().containsKey((Object)class_1294.field_5904);
        for (int llllllllllllllllllllllIIlIlllllI = 35; llllllllllllllllllllllIIlIlllllI >= 0; --llllllllllllllllllllllIIlIlllllI) {
            if (llllllllllllllllllllllIIlIlllIIl.mc.field_1724.field_7514.method_5438(llllllllllllllllllllllIIlIlllllI).method_7909() != class_1802.field_8087 || llllllllllllllllllllllIIlIlllllI == llllllllllllllllllllllIIlIlllIIl.mc.field_1724.field_7514.field_7545) continue;
            if (llllllllllllllllllllllIIlIlllIIl.checkEffects.get().booleanValue()) {
                if (llllllllllllllllllllllIIlIlllIIl.isType("effect.minecraft.strength", llllllllllllllllllllllIIlIlllllI) && !llllllllllllllllllllllIIlIlllIll) {
                    llllllllllllllllllllllIIlIllllII.put(ArrowType.Strength, llllllllllllllllllllllIIlIlllllI);
                    continue;
                }
                if (!llllllllllllllllllllllIIlIlllIIl.isType("effect.minecraft.speed", llllllllllllllllllllllIIlIlllllI) || llllllllllllllllllllllIIlIlllIlI) continue;
                llllllllllllllllllllllIIlIllllII.put(ArrowType.Speed, llllllllllllllllllllllIIlIlllllI);
                continue;
            }
            if (llllllllllllllllllllllIIlIlllIIl.isType("effect.minecraft.strength", llllllllllllllllllllllIIlIlllllI)) {
                llllllllllllllllllllllIIlIllllII.put(ArrowType.Strength, llllllllllllllllllllllIIlIlllllI);
                continue;
            }
            if (!llllllllllllllllllllllIIlIlllIIl.isType("effect.minecraft.speed", llllllllllllllllllllllIIlIlllllI)) continue;
            llllllllllllllllllllllIIlIllllII.put(ArrowType.Speed, llllllllllllllllllllllIIlIlllllI);
        }
        return llllllllllllllllllllllIIlIllllII;
    }

    private void endShooting(int llllllllllllllllllllllIIllIIIlII) {
        Quiver llllllllllllllllllllllIIllIIIlIl;
        llllllllllllllllllllllIIllIIIlIl.setPressed(false);
        llllllllllllllllllllllIIllIIIlIl.mc.field_1724.method_6075();
        llllllllllllllllllllllIIllIIIlIl.mc.field_1761.method_2897((class_1657)llllllllllllllllllllllIIllIIIlIl.mc.field_1724);
        if (llllllllllllllllllllllIIllIIIlII != 9) {
            llllllllllllllllllllllIIllIIIlIl.moveItems(9, llllllllllllllllllllllIIllIIIlII);
        }
        llllllllllllllllllllllIIllIIIlIl.shooting = false;
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllllllIIllIlIIll) {
        Quiver llllllllllllllllllllllIIllIlIlII;
        llllllllllllllllllllllIIllIlIlII.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2831(llllllllllllllllllllllIIllIlIlII.mc.field_1724.field_6031, -90.0f, llllllllllllllllllllllIIllIlIlII.mc.field_1724.method_24828()));
        Rotations.setCamRotation(llllllllllllllllllllllIIllIlIlII.mc.field_1724.field_6031, -90.0);
        boolean llllllllllllllllllllllIIllIlIIlI = false;
        if (llllllllllllllllllllllIIllIlIlII.shooting && llllllllllllllllllllllIIllIlIlII.mc.field_1724.method_6048() >= llllllllllllllllllllllIIllIlIlII.charge.get()) {
            if (llllllllllllllllllllllIIllIlIlII.shootingArrow == ArrowType.Strength) {
                llllllllllllllllllllllIIllIlIlII.endShooting(llllllllllllllllllllllIIllIlIlII.strengthSlot);
            }
            if (llllllllllllllllllllllIIllIlIlII.shootingArrow == ArrowType.Speed) {
                llllllllllllllllllllllIIllIlIlII.endShooting(llllllllllllllllllllllIIllIlIlII.speedSlot);
            }
            llllllllllllllllllllllIIllIlIIlI = true;
        }
        if (llllllllllllllllllllllIIllIlIlII.shotStrength && llllllllllllllllllllllIIllIlIlII.shotSpeed && llllllllllllllllllllllIIllIlIIlI) {
            if (llllllllllllllllllllllIIllIlIlII.chatInfo.get().booleanValue()) {
                llllllllllllllllllllllIIllIlIlII.info("Quiver complete... disabling.", new Object[0]);
            }
            llllllllllllllllllllllIIllIlIlII.toggle();
            return;
        }
        if (llllllllllllllllllllllIIllIlIlII.shouldShoot) {
            if (!llllllllllllllllllllllIIllIlIlII.shooting && !llllllllllllllllllllllIIllIlIlII.shotStrength && llllllllllllllllllllllIIllIlIlII.foundStrength) {
                llllllllllllllllllllllIIllIlIlII.shoot(llllllllllllllllllllllIIllIlIlII.strengthSlot);
                llllllllllllllllllllllIIllIlIlII.shootingArrow = ArrowType.Strength;
                if (llllllllllllllllllllllIIllIlIlII.chatInfo.get().booleanValue()) {
                    llllllllllllllllllllllIIllIlIlII.info("Quivering a strength arrow.", new Object[0]);
                }
                llllllllllllllllllllllIIllIlIlII.shotStrength = true;
            }
            if (!llllllllllllllllllllllIIllIlIlII.shooting && !llllllllllllllllllllllIIllIlIlII.shotSpeed && llllllllllllllllllllllIIllIlIlII.foundSpeed && llllllllllllllllllllllIIllIlIlII.shotStrength) {
                llllllllllllllllllllllIIllIlIlII.shoot(llllllllllllllllllllllIIllIlIlII.speedSlot);
                llllllllllllllllllllllIIllIlIlII.shootingArrow = ArrowType.Speed;
                if (llllllllllllllllllllllIIllIlIlII.chatInfo.get().booleanValue()) {
                    llllllllllllllllllllllIIllIlIlII.info("Quivering a speed arrow.", new Object[0]);
                }
                llllllllllllllllllllllIIllIlIlII.shotSpeed = true;
            }
        }
    }

    public Quiver() {
        super(Categories.Combat, "quiver", "Automatically shoots positive effect arrows at you.");
        Quiver llllllllllllllllllllllIIlllIlIIl;
        llllllllllllllllllllllIIlllIlIIl.sgGeneral = llllllllllllllllllllllIIlllIlIIl.settings.getDefaultGroup();
        llllllllllllllllllllllIIlllIlIIl.charge = llllllllllllllllllllllIIlllIlIIl.sgGeneral.add(new IntSetting.Builder().name("charge-delay").description("The amount of delay for bow charging in ticks.").defaultValue(6).min(5).max(20).sliderMin(5).sliderMax(20).build());
        llllllllllllllllllllllIIlllIlIIl.checkEffects = llllllllllllllllllllllIIlllIlIIl.sgGeneral.add(new BoolSetting.Builder().name("check-effects").description("Won't shoot you with effects you already have active.").defaultValue(true).build());
        llllllllllllllllllllllIIlllIlIIl.chatInfo = llllllllllllllllllllllIIlllIlIIl.sgGeneral.add(new BoolSetting.Builder().name("chat-info").description("Sends you information about the module when toggled.").defaultValue(true).build());
    }

    private int findBow() {
        Quiver llllllllllllllllllllllIIlIIlIIIl;
        int llllllllllllllllllllllIIlIIlIIII = -1;
        assert (llllllllllllllllllllllIIlIIlIIIl.mc.field_1724 != null);
        for (int llllllllllllllllllllllIIlIIlIIlI = 0; llllllllllllllllllllllIIlIIlIIlI < 9; ++llllllllllllllllllllllIIlIIlIIlI) {
            if (llllllllllllllllllllllIIlIIlIIIl.mc.field_1724.field_7514.method_5438(llllllllllllllllllllllIIlIIlIIlI).method_7909() != class_1802.field_8102) continue;
            llllllllllllllllllllllIIlIIlIIII = llllllllllllllllllllllIIlIIlIIlI;
        }
        return llllllllllllllllllllllIIlIIlIIII;
    }

    private void shoot(int llllllllllllllllllllllIIllIIllII) {
        Quiver llllllllllllllllllllllIIllIIllIl;
        if (llllllllllllllllllllllIIllIIllII != 9) {
            llllllllllllllllllllllIIllIIllIl.moveItems(llllllllllllllllllllllIIllIIllII, 9);
        }
        llllllllllllllllllllllIIllIIllIl.setPressed(true);
        llllllllllllllllllllllIIllIIllIl.shooting = true;
    }

    @Override
    public void onActivate() {
        Quiver llllllllllllllllllllllIIllIllllI;
        llllllllllllllllllllllIIllIllllI.shooting = false;
        int llllllllllllllllllllllIIlllIIIII = 0;
        llllllllllllllllllllllIIllIllllI.prevSlot = llllllllllllllllllllllIIllIllllI.mc.field_1724.field_7514.field_7545;
        llllllllllllllllllllllIIllIllllI.shotStrength = false;
        llllllllllllllllllllllIIllIllllI.shotSpeed = false;
        llllllllllllllllllllllIIllIllllI.foundStrength = false;
        llllllllllllllllllllllIIllIllllI.foundSpeed = false;
        llllllllllllllllllllllIIllIllllI.shootingArrow = null;
        llllllllllllllllllllllIIllIllllI.strengthSlot = -1;
        llllllllllllllllllllllIIllIllllI.speedSlot = -1;
        int llllllllllllllllllllllIIllIlllll = llllllllllllllllllllllIIllIllllI.findBow();
        if (llllllllllllllllllllllIIllIlllll == -1) {
            if (llllllllllllllllllllllIIllIllllI.chatInfo.get().booleanValue()) {
                llllllllllllllllllllllIIllIllllI.error("No bow found... disabling.", new Object[0]);
            }
            llllllllllllllllllllllIIllIllllI.toggle();
            return;
        }
        InvUtils.swap(llllllllllllllllllllllIIllIlllll);
        for (Map.Entry<ArrowType, Integer> llllllllllllllllllllllIIlllIIIlI : llllllllllllllllllllllIIllIllllI.getAllArrows().entrySet()) {
            if (llllllllllllllllllllllIIlllIIIlI.getKey() == ArrowType.Strength && !llllllllllllllllllllllIIllIllllI.foundStrength) {
                llllllllllllllllllllllIIllIllllI.strengthSlot = llllllllllllllllllllllIIlllIIIlI.getValue();
                llllllllllllllllllllllIIllIllllI.foundStrength = true;
            }
            if (llllllllllllllllllllllIIlllIIIlI.getKey() != ArrowType.Speed || llllllllllllllllllllllIIllIllllI.foundSpeed) continue;
            llllllllllllllllllllllIIllIllllI.speedSlot = llllllllllllllllllllllIIlllIIIlI.getValue();
            llllllllllllllllllllllIIllIllllI.foundSpeed = true;
        }
        if (llllllllllllllllllllllIIllIllllI.strengthSlot != -1) {
            ++llllllllllllllllllllllIIlllIIIII;
        }
        if (llllllllllllllllllllllIIllIllllI.speedSlot != -1) {
            ++llllllllllllllllllllllIIlllIIIII;
        }
        if (llllllllllllllllllllllIIlllIIIII == 0) {
            if (llllllllllllllllllllllIIllIllllI.chatInfo.get().booleanValue()) {
                llllllllllllllllllllllIIllIllllI.error("No appropriate arrows found... disabling.", new Object[0]);
            }
            llllllllllllllllllllllIIllIllllI.toggle();
            return;
        }
        llllllllllllllllllllllIIllIllllI.shouldShoot = true;
        if (!llllllllllllllllllllllIIllIllllI.foundSpeed) {
            llllllllllllllllllllllIIllIllllI.shotSpeed = true;
        }
        if (!llllllllllllllllllllllIIllIllllI.foundStrength) {
            llllllllllllllllllllllIIllIllllI.shotStrength = true;
        }
    }

    private boolean isType(String llllllllllllllllllllllIIlIlIIlll, int llllllllllllllllllllllIIlIlIlIlI) {
        List llllllllllllllllllllllIIlIlIllIl;
        Quiver llllllllllllllllllllllIIlIlIllII;
        assert (llllllllllllllllllllllIIlIlIllII.mc.field_1724 != null);
        class_1799 llllllllllllllllllllllIIlIlIlIIl = llllllllllllllllllllllIIlIlIllII.mc.field_1724.field_7514.method_5438(llllllllllllllllllllllIIlIlIlIlI);
        if (llllllllllllllllllllllIIlIlIlIIl.method_7909() == class_1802.field_8087 && (llllllllllllllllllllllIIlIlIllIl = class_1844.method_8063((class_1799)llllllllllllllllllllllIIlIlIlIIl).method_8049()).size() > 0) {
            class_1293 llllllllllllllllllllllIIlIlIlllI = (class_1293)llllllllllllllllllllllIIlIlIllIl.get(0);
            return llllllllllllllllllllllIIlIlIlllI.method_5586().equals(llllllllllllllllllllllIIlIlIIlll);
        }
        return false;
    }

    @Override
    public void onDeactivate() {
        Quiver llllllllllllllllllllllIIllIlIlll;
        InvUtils.swap(llllllllllllllllllllllIIllIlIlll.prevSlot);
    }

    private void setPressed(boolean llllllllllllllllllllllIIlIIlllIl) {
        Quiver llllllllllllllllllllllIIlIIllllI;
        llllllllllllllllllllllIIlIIllllI.mc.field_1690.field_1904.method_23481(llllllllllllllllllllllIIlIIlllIl);
    }

    private void moveItems(int llllllllllllllllllllllIIlIIlIlll, int llllllllllllllllllllllIIlIIllIII) {
        InvUtils.move().from(llllllllllllllllllllllIIlIIlIlll).to(llllllllllllllllllllllIIlIIllIII);
    }

    public static enum ArrowType {
        Strength,
        Speed;


        private ArrowType() {
            ArrowType llllllllllllllllIlllIIlIlIlIlllI;
        }
    }
}

