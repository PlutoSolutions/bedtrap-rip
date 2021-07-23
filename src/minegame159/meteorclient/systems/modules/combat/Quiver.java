/*
 * Decompiled with CFR 0.151.
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
    private final SettingGroup sgGeneral;
    private boolean shooting;
    private final Setting<Integer> charge;
    static final boolean $assertionsDisabled = !Quiver.class.desiredAssertionStatus();
    private final Setting<Boolean> chatInfo;
    private boolean foundSpeed;
    private boolean shotStrength;
    private final Setting<Boolean> checkEffects;
    private int speedSlot;
    private int prevSlot;
    private ArrowType shootingArrow;
    private boolean shouldShoot;
    private boolean foundStrength;
    private int strengthSlot;
    private boolean shotSpeed;

    private Map<ArrowType, Integer> getAllArrows() {
        HashMap<ArrowType, Integer> hashMap = new HashMap<ArrowType, Integer>();
        boolean bl = this.mc.field_1724.method_6088().containsKey(class_1294.field_5910);
        boolean bl2 = this.mc.field_1724.method_6088().containsKey(class_1294.field_5904);
        for (int i = 35; i >= 0; --i) {
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_1802.field_8087 || i == this.mc.field_1724.field_7514.field_7545) continue;
            if (this.checkEffects.get().booleanValue()) {
                if (this.isType("effect.minecraft.strength", i) && !bl) {
                    hashMap.put(ArrowType.Strength, i);
                    continue;
                }
                if (!this.isType("effect.minecraft.speed", i) || bl2) continue;
                hashMap.put(ArrowType.Speed, i);
                continue;
            }
            if (this.isType("effect.minecraft.strength", i)) {
                hashMap.put(ArrowType.Strength, i);
                continue;
            }
            if (!this.isType("effect.minecraft.speed", i)) continue;
            hashMap.put(ArrowType.Speed, i);
            if (3 != 0) continue;
            return null;
        }
        return hashMap;
    }

    private void endShooting(int n) {
        this.setPressed(false);
        this.mc.field_1724.method_6075();
        this.mc.field_1761.method_2897((class_1657)this.mc.field_1724);
        if (n != 9) {
            this.moveItems(9, n);
        }
        this.shooting = false;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2831(this.mc.field_1724.field_6031, -90.0f, this.mc.field_1724.method_24828()));
        Rotations.setCamRotation(this.mc.field_1724.field_6031, -90.0);
        boolean bl = false;
        if (this.shooting && this.mc.field_1724.method_6048() >= this.charge.get()) {
            if (this.shootingArrow == ArrowType.Strength) {
                this.endShooting(this.strengthSlot);
            }
            if (this.shootingArrow == ArrowType.Speed) {
                this.endShooting(this.speedSlot);
            }
            bl = true;
        }
        if (this.shotStrength && this.shotSpeed && bl) {
            if (this.chatInfo.get().booleanValue()) {
                this.info("Quiver complete... disabling.", new Object[0]);
            }
            this.toggle();
            return;
        }
        if (this.shouldShoot) {
            if (!this.shooting && !this.shotStrength && this.foundStrength) {
                this.shoot(this.strengthSlot);
                this.shootingArrow = ArrowType.Strength;
                if (this.chatInfo.get().booleanValue()) {
                    this.info("Quivering a strength arrow.", new Object[0]);
                }
                this.shotStrength = true;
            }
            if (!this.shooting && !this.shotSpeed && this.foundSpeed && this.shotStrength) {
                this.shoot(this.speedSlot);
                this.shootingArrow = ArrowType.Speed;
                if (this.chatInfo.get().booleanValue()) {
                    this.info("Quivering a speed arrow.", new Object[0]);
                }
                this.shotSpeed = true;
            }
        }
    }

    public Quiver() {
        super(Categories.Combat, "quiver", "Automatically shoots positive effect arrows at you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.charge = this.sgGeneral.add(new IntSetting.Builder().name("charge-delay").description("The amount of delay for bow charging in ticks.").defaultValue(6).min(5).max(20).sliderMin(5).sliderMax(20).build());
        this.checkEffects = this.sgGeneral.add(new BoolSetting.Builder().name("check-effects").description("Won't shoot you with effects you already have active.").defaultValue(true).build());
        this.chatInfo = this.sgGeneral.add(new BoolSetting.Builder().name("chat-info").description("Sends you information about the module when toggled.").defaultValue(true).build());
    }

    private int findBow() {
        int n = -1;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_1802.field_8102) continue;
            n = i;
            if (-1 < 4) continue;
            return 0;
        }
        return n;
    }

    private void shoot(int n) {
        if (n != 9) {
            this.moveItems(n, 9);
        }
        this.setPressed(true);
        this.shooting = true;
    }

    @Override
    public void onActivate() {
        this.shooting = false;
        int n = 0;
        this.prevSlot = this.mc.field_1724.field_7514.field_7545;
        this.shotStrength = false;
        this.shotSpeed = false;
        this.foundStrength = false;
        this.foundSpeed = false;
        this.shootingArrow = null;
        this.strengthSlot = -1;
        this.speedSlot = -1;
        int n2 = this.findBow();
        if (n2 == -1) {
            if (this.chatInfo.get().booleanValue()) {
                this.error("No bow found... disabling.", new Object[0]);
            }
            this.toggle();
            return;
        }
        InvUtils.swap(n2);
        for (Map.Entry<ArrowType, Integer> entry : this.getAllArrows().entrySet()) {
            if (entry.getKey() == ArrowType.Strength && !this.foundStrength) {
                this.strengthSlot = entry.getValue();
                this.foundStrength = true;
            }
            if (entry.getKey() != ArrowType.Speed || this.foundSpeed) continue;
            this.speedSlot = entry.getValue();
            this.foundSpeed = true;
        }
        if (this.strengthSlot != -1) {
            ++n;
        }
        if (this.speedSlot != -1) {
            ++n;
        }
        if (n == 0) {
            if (this.chatInfo.get().booleanValue()) {
                this.error("No appropriate arrows found... disabling.", new Object[0]);
            }
            this.toggle();
            return;
        }
        this.shouldShoot = true;
        if (!this.foundSpeed) {
            this.shotSpeed = true;
        }
        if (!this.foundStrength) {
            this.shotStrength = true;
        }
    }

    private boolean isType(String string, int n) {
        List list;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(n);
        if (class_17992.method_7909() == class_1802.field_8087 && (list = class_1844.method_8063((class_1799)class_17992).method_8049()).size() > 0) {
            class_1293 class_12932 = (class_1293)list.get(0);
            return class_12932.method_5586().equals(string);
        }
        return false;
    }

    @Override
    public void onDeactivate() {
        InvUtils.swap(this.prevSlot);
    }

    private void setPressed(boolean bl) {
        this.mc.field_1690.field_1904.method_23481(bl);
    }

    private void moveItems(int n, int n2) {
        InvUtils.move().from(n).to(n2);
    }

    public static enum ArrowType {
        Strength,
        Speed;

    }
}

