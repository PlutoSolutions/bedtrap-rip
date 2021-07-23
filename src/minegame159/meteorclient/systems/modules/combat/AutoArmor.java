/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnchListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.ChestSwap;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1738;
import net.minecraft.class_1770;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_310;

public class AutoArmor
extends Module {
    private final ArmorPiece leggings;
    private int timer;
    private final ArmorPiece helmet;
    private final ArmorPiece boots;
    private final Object2IntMap<class_1887> enchantments;
    private final ArmorPiece chestplate;
    private final SettingGroup sgGeneral;
    private final Setting<List<class_1887>> avoidedEnchantments;
    private final Setting<Boolean> antiBreak;
    private final Setting<Boolean> blastLeggings;
    private final ArmorPiece[] armorPieces;
    private final Setting<Boolean> ignoreElytra;
    private final Setting<Integer> delay;
    private final Setting<Protection> preferredProtection;

    static int access$100(AutoArmor autoArmor, class_1799 class_17992) {
        return autoArmor.getScore(class_17992);
    }

    private boolean hasAvoidedEnchantment() {
        for (class_1887 class_18872 : this.avoidedEnchantments.get()) {
            if (!this.enchantments.containsKey((Object)class_18872)) continue;
            return true;
        }
        return false;
    }

    private boolean cannotSwap() {
        return this.timer > 0;
    }

    static class_310 access$300(AutoArmor autoArmor) {
        return autoArmor.mc;
    }

    public AutoArmor() {
        super(Categories.Combat, "auto-armor", "Automatically equips armor.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.preferredProtection = this.sgGeneral.add(new EnumSetting.Builder().name("preferred-protection").description("Which type of protection to prefer.").defaultValue(Protection.Protection).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("swap-delay").description("The delay between equipping armor pieces.").defaultValue(1).min(0).sliderMax(5).build());
        this.avoidedEnchantments = this.sgGeneral.add(new EnchListSetting.Builder().name("avoided-enchantments").description("Enchantments that should be avoided.").defaultValue(Lists.newArrayList((Object[])new class_1887[]{class_1893.field_9113, class_1893.field_9122})).build());
        this.blastLeggings = this.sgGeneral.add(new BoolSetting.Builder().name("blast-prot-leggings").description("Uses blast protection for leggings regardless of preferred protection.").defaultValue(true).build());
        this.antiBreak = this.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Takes off armor if it is about to break.").defaultValue(false).build());
        this.ignoreElytra = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-elytra").description("Will not replace your elytra if you have it equipped.").defaultValue(true).build());
        this.enchantments = new Object2IntOpenHashMap();
        this.armorPieces = new ArmorPiece[4];
        this.helmet = new ArmorPiece(this, 3);
        this.chestplate = new ArmorPiece(this, 2);
        this.leggings = new ArmorPiece(this, 1);
        this.boots = new ArmorPiece(this, 0);
        this.armorPieces[0] = this.helmet;
        this.armorPieces[1] = this.chestplate;
        this.armorPieces[2] = this.leggings;
        this.armorPieces[3] = this.boots;
    }

    private void swap(int n, int n2) {
        InvUtils.move().from(n).toArmor(n2);
        this.timer = this.delay.get();
    }

    private int getItemSlotId(class_1799 class_17992) {
        if (class_17992.method_7909() instanceof class_1770) {
            return 2;
        }
        return ((class_1738)class_17992.method_7909()).method_7685().method_5927();
    }

    static Object2IntMap access$500(AutoArmor autoArmor) {
        return autoArmor.enchantments;
    }

    static Setting access$900(AutoArmor autoArmor) {
        return autoArmor.avoidedEnchantments;
    }

    static void access$700(AutoArmor autoArmor, int n, int n2) {
        autoArmor.swap(n, n2);
    }

    static Setting access$600(AutoArmor autoArmor) {
        return autoArmor.antiBreak;
    }

    static void access$800(AutoArmor autoArmor, int n) {
        autoArmor.moveToEmpty(n);
    }

    private void moveToEmpty(int n) {
        for (int i = 0; i < this.mc.field_1724.field_7514.field_7547.size(); ++i) {
            if (!this.mc.field_1724.field_7514.method_5438(i).method_7960()) continue;
            InvUtils.move().fromArmor(n).to(i);
            this.timer = this.delay.get();
            break;
        }
    }

    @Override
    public void onActivate() {
        this.timer = 0;
    }

    static Setting access$400(AutoArmor autoArmor) {
        return autoArmor.ignoreElytra;
    }

    static boolean access$200(AutoArmor autoArmor) {
        return autoArmor.cannotSwap();
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre pre) {
        if (this.timer > 0) {
            --this.timer;
            return;
        }
        for (ArmorPiece armorPiece : this.armorPieces) {
            armorPiece.reset();
            if (-3 <= 0) continue;
            return;
        }
        block7: for (int i = 0; i < this.mc.field_1724.field_7514.field_7547.size(); ++i) {
            class_1799 class_17992 = this.mc.field_1724.field_7514.method_5438(i);
            if (class_17992.method_7960() || !(class_17992.method_7909() instanceof class_1738) || this.antiBreak.get().booleanValue() && class_17992.method_7963() && class_17992.method_7936() - class_17992.method_7919() <= 10) continue;
            Utils.getEnchantments(class_17992, this.enchantments);
            if (this.hasAvoidedEnchantment()) continue;
            switch (this.getItemSlotId(class_17992)) {
                case 0: {
                    this.boots.add(class_17992, i);
                    continue block7;
                }
                case 1: {
                    this.leggings.add(class_17992, i);
                    continue block7;
                }
                case 2: {
                    this.chestplate.add(class_17992, i);
                    continue block7;
                }
                case 3: {
                    this.helmet.add(class_17992, i);
                }
            }
            if (null == null) continue;
            return;
        }
        for (ArmorPiece armorPiece : this.armorPieces) {
            armorPiece.calculate();
            if (-3 < 0) continue;
            return;
        }
        Arrays.sort(this.armorPieces, Comparator.comparingInt(ArmorPiece::getSortScore));
        for (ArmorPiece armorPiece : this.armorPieces) {
            armorPiece.apply();
            if (-1 == -1) continue;
            return;
        }
    }

    private int getScore(class_1799 class_17992) {
        if (class_17992.method_7960()) {
            return 0;
        }
        int n = 0;
        class_1887 class_18872 = Protection.access$000(this.preferredProtection.get());
        if (class_17992.method_7909() instanceof class_1738 && this.blastLeggings.get().booleanValue() && this.getItemSlotId(class_17992) == 1) {
            class_18872 = class_1893.field_9107;
        }
        n += 3 * this.enchantments.getInt((Object)class_18872);
        n += this.enchantments.getInt((Object)class_1893.field_9111);
        n += this.enchantments.getInt((Object)class_1893.field_9107);
        n += this.enchantments.getInt((Object)class_1893.field_9095);
        n += this.enchantments.getInt((Object)class_1893.field_9096);
        n += this.enchantments.getInt((Object)class_1893.field_9119);
        n += 2 * this.enchantments.getInt((Object)class_1893.field_9101);
        n = (int)((float)(n += class_17992.method_7909() instanceof class_1738 ? ((class_1738)class_17992.method_7909()).method_7687() : 0) + (class_17992.method_7909() instanceof class_1738 ? ((class_1738)class_17992.method_7909()).method_26353() : 0.0f));
        return n;
    }

    public static enum Protection {
        Protection(class_1893.field_9111),
        BlastProtection(class_1893.field_9107),
        FireProtection(class_1893.field_9095),
        ProjectileProtection(class_1893.field_9096);

        private final class_1887 enchantment;

        private Protection(class_1887 class_18872) {
            this.enchantment = class_18872;
        }

        static class_1887 access$000(Protection protection) {
            return protection.enchantment;
        }
    }

    private class ArmorPiece {
        private int score;
        private int durability;
        private int bestSlot;
        private final int id;
        final AutoArmor this$0;
        private int bestScore;

        private int decreaseScoreByAvoidedEnchantments(int n) {
            for (class_1887 class_18872 : (List)AutoArmor.access$900(this.this$0).get()) {
                n -= 2 * AutoArmor.access$500(this.this$0).getInt((Object)class_18872);
            }
            return n;
        }

        public void calculate() {
            if (AutoArmor.access$200(this.this$0)) {
                return;
            }
            class_1799 class_17992 = AutoArmor.access$300((AutoArmor)this.this$0).field_1724.field_7514.method_7372(this.id);
            if ((((Boolean)AutoArmor.access$400(this.this$0).get()).booleanValue() || Modules.get().isActive(ChestSwap.class)) && class_17992.method_7909() == class_1802.field_8833) {
                this.score = Integer.MAX_VALUE;
                return;
            }
            Utils.getEnchantments(class_17992, (Object2IntMap<class_1887>)AutoArmor.access$500(this.this$0));
            if (AutoArmor.access$500(this.this$0).containsKey((Object)class_1893.field_9113)) {
                this.score = Integer.MAX_VALUE;
                return;
            }
            this.score = AutoArmor.access$100(this.this$0, class_17992);
            this.score = this.decreaseScoreByAvoidedEnchantments(this.score);
            this.score = this.applyAntiBreakScore(this.score, class_17992);
            if (!class_17992.method_7960()) {
                this.durability = class_17992.method_7936() - class_17992.method_7919();
            }
        }

        private int applyAntiBreakScore(int n, class_1799 class_17992) {
            if (((Boolean)AutoArmor.access$600(this.this$0).get()).booleanValue() && class_17992.method_7963() && class_17992.method_7936() - class_17992.method_7919() <= 10) {
                return -1;
            }
            return n;
        }

        public void apply() {
            if (AutoArmor.access$200(this.this$0) || this.score == Integer.MAX_VALUE) {
                return;
            }
            if (this.bestScore > this.score) {
                AutoArmor.access$700(this.this$0, this.bestSlot, this.id);
            } else if (((Boolean)AutoArmor.access$600(this.this$0).get()).booleanValue() && this.durability <= 10) {
                AutoArmor.access$800(this.this$0, this.id);
            }
        }

        public void reset() {
            this.bestSlot = -1;
            this.bestScore = -1;
            this.score = -1;
            this.durability = Integer.MAX_VALUE;
        }

        public ArmorPiece(AutoArmor autoArmor, int n) {
            this.this$0 = autoArmor;
            this.id = n;
        }

        public void add(class_1799 class_17992, int n) {
            int n2 = AutoArmor.access$100(this.this$0, class_17992);
            if (n2 > this.bestScore) {
                this.bestScore = n2;
                this.bestSlot = n;
            }
        }

        public int getSortScore() {
            if (((Boolean)AutoArmor.access$600(this.this$0).get()).booleanValue() && this.durability <= 10) {
                return -1;
            }
            return this.bestScore;
        }
    }
}

