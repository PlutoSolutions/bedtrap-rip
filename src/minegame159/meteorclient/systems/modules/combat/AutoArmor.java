/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.class_1738
 *  net.minecraft.class_1770
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1893
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

public class AutoArmor
extends Module {
    private final /* synthetic */ ArmorPiece leggings;
    private /* synthetic */ int timer;
    private final /* synthetic */ ArmorPiece helmet;
    private final /* synthetic */ ArmorPiece boots;
    private final /* synthetic */ Object2IntMap<class_1887> enchantments;
    private final /* synthetic */ ArmorPiece chestplate;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<List<class_1887>> avoidedEnchantments;
    private final /* synthetic */ Setting<Boolean> antiBreak;
    private final /* synthetic */ Setting<Boolean> blastLeggings;
    private final /* synthetic */ ArmorPiece[] armorPieces;
    private final /* synthetic */ Setting<Boolean> ignoreElytra;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Protection> preferredProtection;

    private boolean hasAvoidedEnchantment() {
        AutoArmor lllIllIIllIlIl;
        for (class_1887 lllIllIIllIlll : lllIllIIllIlIl.avoidedEnchantments.get()) {
            if (!lllIllIIllIlIl.enchantments.containsKey((Object)lllIllIIllIlll)) continue;
            return true;
        }
        return false;
    }

    private boolean cannotSwap() {
        AutoArmor lllIllIIlIIIII;
        return lllIllIIlIIIII.timer > 0;
    }

    public AutoArmor() {
        super(Categories.Combat, "auto-armor", "Automatically equips armor.");
        AutoArmor lllIllIlIIllll;
        lllIllIlIIllll.sgGeneral = lllIllIlIIllll.settings.getDefaultGroup();
        lllIllIlIIllll.preferredProtection = lllIllIlIIllll.sgGeneral.add(new EnumSetting.Builder().name("preferred-protection").description("Which type of protection to prefer.").defaultValue(Protection.Protection).build());
        lllIllIlIIllll.delay = lllIllIlIIllll.sgGeneral.add(new IntSetting.Builder().name("swap-delay").description("The delay between equipping armor pieces.").defaultValue(1).min(0).sliderMax(5).build());
        lllIllIlIIllll.avoidedEnchantments = lllIllIlIIllll.sgGeneral.add(new EnchListSetting.Builder().name("avoided-enchantments").description("Enchantments that should be avoided.").defaultValue(Lists.newArrayList((Object[])new class_1887[]{class_1893.field_9113, class_1893.field_9122})).build());
        lllIllIlIIllll.blastLeggings = lllIllIlIIllll.sgGeneral.add(new BoolSetting.Builder().name("blast-prot-leggings").description("Uses blast protection for leggings regardless of preferred protection.").defaultValue(true).build());
        lllIllIlIIllll.antiBreak = lllIllIlIIllll.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Takes off armor if it is about to break.").defaultValue(false).build());
        lllIllIlIIllll.ignoreElytra = lllIllIlIIllll.sgGeneral.add(new BoolSetting.Builder().name("ignore-elytra").description("Will not replace your elytra if you have it equipped.").defaultValue(true).build());
        lllIllIlIIllll.enchantments = new Object2IntOpenHashMap();
        lllIllIlIIllll.armorPieces = new ArmorPiece[4];
        lllIllIlIIllll.helmet = lllIllIlIIllll.new ArmorPiece(3);
        lllIllIlIIllll.chestplate = lllIllIlIIllll.new ArmorPiece(2);
        lllIllIlIIllll.leggings = lllIllIlIIllll.new ArmorPiece(1);
        lllIllIlIIllll.boots = lllIllIlIIllll.new ArmorPiece(0);
        lllIllIlIIllll.armorPieces[0] = lllIllIlIIllll.helmet;
        lllIllIlIIllll.armorPieces[1] = lllIllIlIIllll.chestplate;
        lllIllIlIIllll.armorPieces[2] = lllIllIlIIllll.leggings;
        lllIllIlIIllll.armorPieces[3] = lllIllIlIIllll.boots;
    }

    private void swap(int lllIllIIIllIII, int lllIllIIIlIlll) {
        AutoArmor lllIllIIIllIIl;
        InvUtils.move().from(lllIllIIIllIII).toArmor(lllIllIIIlIlll);
        lllIllIIIllIIl.timer = lllIllIIIllIIl.delay.get();
    }

    private int getItemSlotId(class_1799 lllIllIIllIIII) {
        if (lllIllIIllIIII.method_7909() instanceof class_1770) {
            return 2;
        }
        return ((class_1738)lllIllIIllIIII.method_7909()).method_7685().method_5927();
    }

    private void moveToEmpty(int lllIllIIIIllll) {
        AutoArmor lllIllIIIlIIlI;
        for (int lllIllIIIlIIll = 0; lllIllIIIlIIll < lllIllIIIlIIlI.mc.field_1724.field_7514.field_7547.size(); ++lllIllIIIlIIll) {
            if (!lllIllIIIlIIlI.mc.field_1724.field_7514.method_5438(lllIllIIIlIIll).method_7960()) continue;
            InvUtils.move().fromArmor(lllIllIIIIllll).to(lllIllIIIlIIll);
            lllIllIIIlIIlI.timer = lllIllIIIlIIlI.delay.get();
            break;
        }
    }

    @Override
    public void onActivate() {
        lllIllIlIIllIl.timer = 0;
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre lllIllIlIIIIII) {
        AutoArmor lllIllIlIIIIIl;
        if (lllIllIlIIIIIl.timer > 0) {
            --lllIllIlIIIIIl.timer;
            return;
        }
        ArmorPiece[] lllIllIIlllllI = lllIllIlIIIIIl.armorPieces;
        int lllIllIIllllIl = lllIllIIlllllI.length;
        for (int lllIllIIllllII = 0; lllIllIIllllII < lllIllIIllllIl; ++lllIllIIllllII) {
            ArmorPiece lllIllIlIIIllI = lllIllIIlllllI[lllIllIIllllII];
            lllIllIlIIIllI.reset();
        }
        block7: for (int lllIllIlIIIlII = 0; lllIllIlIIIlII < lllIllIlIIIIIl.mc.field_1724.field_7514.field_7547.size(); ++lllIllIlIIIlII) {
            class_1799 lllIllIlIIIlIl = lllIllIlIIIIIl.mc.field_1724.field_7514.method_5438(lllIllIlIIIlII);
            if (lllIllIlIIIlIl.method_7960() || !(lllIllIlIIIlIl.method_7909() instanceof class_1738) || lllIllIlIIIIIl.antiBreak.get().booleanValue() && lllIllIlIIIlIl.method_7963() && lllIllIlIIIlIl.method_7936() - lllIllIlIIIlIl.method_7919() <= 10) continue;
            Utils.getEnchantments(lllIllIlIIIlIl, lllIllIlIIIIIl.enchantments);
            if (lllIllIlIIIIIl.hasAvoidedEnchantment()) continue;
            switch (lllIllIlIIIIIl.getItemSlotId(lllIllIlIIIlIl)) {
                case 0: {
                    lllIllIlIIIIIl.boots.add(lllIllIlIIIlIl, lllIllIlIIIlII);
                    continue block7;
                }
                case 1: {
                    lllIllIlIIIIIl.leggings.add(lllIllIlIIIlIl, lllIllIlIIIlII);
                    continue block7;
                }
                case 2: {
                    lllIllIlIIIIIl.chestplate.add(lllIllIlIIIlIl, lllIllIlIIIlII);
                    continue block7;
                }
                case 3: {
                    lllIllIlIIIIIl.helmet.add(lllIllIlIIIlIl, lllIllIlIIIlII);
                }
            }
        }
        for (ArmorPiece lllIllIlIIIIll : lllIllIlIIIIIl.armorPieces) {
            lllIllIlIIIIll.calculate();
        }
        Arrays.sort(lllIllIlIIIIIl.armorPieces, Comparator.comparingInt(ArmorPiece::getSortScore));
        for (ArmorPiece lllIllIlIIIIlI : lllIllIlIIIIIl.armorPieces) {
            lllIllIlIIIIlI.apply();
        }
    }

    private int getScore(class_1799 lllIllIIlIlIIl) {
        AutoArmor lllIllIIlIIllI;
        if (lllIllIIlIlIIl.method_7960()) {
            return 0;
        }
        int lllIllIIlIlIII = 0;
        class_1887 lllIllIIlIIlll = lllIllIIlIIllI.preferredProtection.get().enchantment;
        if (lllIllIIlIlIIl.method_7909() instanceof class_1738 && lllIllIIlIIllI.blastLeggings.get().booleanValue() && lllIllIIlIIllI.getItemSlotId(lllIllIIlIlIIl) == 1) {
            lllIllIIlIIlll = class_1893.field_9107;
        }
        lllIllIIlIlIII += 3 * lllIllIIlIIllI.enchantments.getInt((Object)lllIllIIlIIlll);
        lllIllIIlIlIII += lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9111);
        lllIllIIlIlIII += lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9107);
        lllIllIIlIlIII += lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9095);
        lllIllIIlIlIII += lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9096);
        lllIllIIlIlIII += lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9119);
        lllIllIIlIlIII += 2 * lllIllIIlIIllI.enchantments.getInt((Object)class_1893.field_9101);
        lllIllIIlIlIII = (int)((float)(lllIllIIlIlIII += lllIllIIlIlIIl.method_7909() instanceof class_1738 ? ((class_1738)lllIllIIlIlIIl.method_7909()).method_7687() : 0) + (lllIllIIlIlIIl.method_7909() instanceof class_1738 ? ((class_1738)lllIllIIlIlIIl.method_7909()).method_26353() : 0.0f));
        return lllIllIIlIlIII;
    }

    public static enum Protection {
        Protection(class_1893.field_9111),
        BlastProtection(class_1893.field_9107),
        FireProtection(class_1893.field_9095),
        ProjectileProtection(class_1893.field_9096);

        private final /* synthetic */ class_1887 enchantment;

        private Protection(class_1887 lllIIlIlllIlIlI) {
            Protection lllIIlIlllIllll;
            lllIIlIlllIllll.enchantment = lllIIlIlllIlIlI;
        }
    }

    private class ArmorPiece {
        private /* synthetic */ int score;
        private /* synthetic */ int durability;
        private /* synthetic */ int bestSlot;
        private final /* synthetic */ int id;
        private /* synthetic */ int bestScore;

        private int decreaseScoreByAvoidedEnchantments(int lllllllllllllllllIlllIIllIllIIIl) {
            ArmorPiece lllllllllllllllllIlllIIllIllIIII;
            for (class_1887 lllllllllllllllllIlllIIllIllIIll : (List)lllllllllllllllllIlllIIllIllIIII.AutoArmor.this.avoidedEnchantments.get()) {
                lllllllllllllllllIlllIIllIllIIIl -= 2 * lllllllllllllllllIlllIIllIllIIII.AutoArmor.this.enchantments.getInt((Object)lllllllllllllllllIlllIIllIllIIll);
            }
            return lllllllllllllllllIlllIIllIllIIIl;
        }

        public void calculate() {
            ArmorPiece lllllllllllllllllIlllIIllIllllll;
            if (lllllllllllllllllIlllIIllIllllll.AutoArmor.this.cannotSwap()) {
                return;
            }
            class_1799 lllllllllllllllllIlllIIlllIIIIII = ((AutoArmor)lllllllllllllllllIlllIIllIllllll.AutoArmor.this).mc.field_1724.field_7514.method_7372(lllllllllllllllllIlllIIllIllllll.id);
            if ((((Boolean)lllllllllllllllllIlllIIllIllllll.AutoArmor.this.ignoreElytra.get()).booleanValue() || Modules.get().isActive(ChestSwap.class)) && lllllllllllllllllIlllIIlllIIIIII.method_7909() == class_1802.field_8833) {
                lllllllllllllllllIlllIIllIllllll.score = Integer.MAX_VALUE;
                return;
            }
            Utils.getEnchantments(lllllllllllllllllIlllIIlllIIIIII, (Object2IntMap<class_1887>)lllllllllllllllllIlllIIllIllllll.AutoArmor.this.enchantments);
            if (lllllllllllllllllIlllIIllIllllll.AutoArmor.this.enchantments.containsKey((Object)class_1893.field_9113)) {
                lllllllllllllllllIlllIIllIllllll.score = Integer.MAX_VALUE;
                return;
            }
            lllllllllllllllllIlllIIllIllllll.score = lllllllllllllllllIlllIIllIllllll.AutoArmor.this.getScore(lllllllllllllllllIlllIIlllIIIIII);
            lllllllllllllllllIlllIIllIllllll.score = lllllllllllllllllIlllIIllIllllll.decreaseScoreByAvoidedEnchantments(lllllllllllllllllIlllIIllIllllll.score);
            lllllllllllllllllIlllIIllIllllll.score = lllllllllllllllllIlllIIllIllllll.applyAntiBreakScore(lllllllllllllllllIlllIIllIllllll.score, lllllllllllllllllIlllIIlllIIIIII);
            if (!lllllllllllllllllIlllIIlllIIIIII.method_7960()) {
                lllllllllllllllllIlllIIllIllllll.durability = lllllllllllllllllIlllIIlllIIIIII.method_7936() - lllllllllllllllllIlllIIlllIIIIII.method_7919();
            }
        }

        private int applyAntiBreakScore(int lllllllllllllllllIlllIIllIlIIlIl, class_1799 lllllllllllllllllIlllIIllIlIIlll) {
            ArmorPiece lllllllllllllllllIlllIIllIlIIllI;
            if (((Boolean)lllllllllllllllllIlllIIllIlIIllI.AutoArmor.this.antiBreak.get()).booleanValue() && lllllllllllllllllIlllIIllIlIIlll.method_7963() && lllllllllllllllllIlllIIllIlIIlll.method_7936() - lllllllllllllllllIlllIIllIlIIlll.method_7919() <= 10) {
                return -1;
            }
            return lllllllllllllllllIlllIIllIlIIlIl;
        }

        public void apply() {
            ArmorPiece lllllllllllllllllIlllIIllIlllIIl;
            if (lllllllllllllllllIlllIIllIlllIIl.AutoArmor.this.cannotSwap() || lllllllllllllllllIlllIIllIlllIIl.score == Integer.MAX_VALUE) {
                return;
            }
            if (lllllllllllllllllIlllIIllIlllIIl.bestScore > lllllllllllllllllIlllIIllIlllIIl.score) {
                lllllllllllllllllIlllIIllIlllIIl.AutoArmor.this.swap(lllllllllllllllllIlllIIllIlllIIl.bestSlot, lllllllllllllllllIlllIIllIlllIIl.id);
            } else if (((Boolean)lllllllllllllllllIlllIIllIlllIIl.AutoArmor.this.antiBreak.get()).booleanValue() && lllllllllllllllllIlllIIllIlllIIl.durability <= 10) {
                lllllllllllllllllIlllIIllIlllIIl.AutoArmor.this.moveToEmpty(lllllllllllllllllIlllIIllIlllIIl.id);
            }
        }

        public void reset() {
            lllllllllllllllllIlllIIlllIlIIIl.bestSlot = -1;
            lllllllllllllllllIlllIIlllIlIIIl.bestScore = -1;
            lllllllllllllllllIlllIIlllIlIIIl.score = -1;
            lllllllllllllllllIlllIIlllIlIIIl.durability = Integer.MAX_VALUE;
        }

        public ArmorPiece(int lllllllllllllllllIlllIIlllIlIllI) {
            ArmorPiece lllllllllllllllllIlllIIlllIlIlll;
            lllllllllllllllllIlllIIlllIlIlll.id = lllllllllllllllllIlllIIlllIlIllI;
        }

        public void add(class_1799 lllllllllllllllllIlllIIlllIIlIlI, int lllllllllllllllllIlllIIlllIIIlIl) {
            ArmorPiece lllllllllllllllllIlllIIlllIIIlll;
            int lllllllllllllllllIlllIIlllIIlIII = lllllllllllllllllIlllIIlllIIIlll.AutoArmor.this.getScore(lllllllllllllllllIlllIIlllIIlIlI);
            if (lllllllllllllllllIlllIIlllIIlIII > lllllllllllllllllIlllIIlllIIIlll.bestScore) {
                lllllllllllllllllIlllIIlllIIIlll.bestScore = lllllllllllllllllIlllIIlllIIlIII;
                lllllllllllllllllIlllIIlllIIIlll.bestSlot = lllllllllllllllllIlllIIlllIIIlIl;
            }
        }

        public int getSortScore() {
            ArmorPiece lllllllllllllllllIlllIIllIlllIll;
            if (((Boolean)lllllllllllllllllIlllIIllIlllIll.AutoArmor.this.antiBreak.get()).booleanValue() && lllllllllllllllllIlllIIllIlllIll.durability <= 10) {
                return -1;
            }
            return lllllllllllllllllIlllIIllIlllIll.bestScore;
        }
    }
}

