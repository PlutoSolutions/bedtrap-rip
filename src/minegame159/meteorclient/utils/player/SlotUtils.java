/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1492
 *  net.minecraft.class_1496
 *  net.minecraft.class_1498
 *  net.minecraft.class_1501
 *  net.minecraft.class_1506
 *  net.minecraft.class_1507
 *  net.minecraft.class_1703
 *  net.minecraft.class_1704
 *  net.minecraft.class_1706
 *  net.minecraft.class_1707
 *  net.minecraft.class_1708
 *  net.minecraft.class_1714
 *  net.minecraft.class_1716
 *  net.minecraft.class_1718
 *  net.minecraft.class_1722
 *  net.minecraft.class_1723
 *  net.minecraft.class_1724
 *  net.minecraft.class_1726
 *  net.minecraft.class_1728
 *  net.minecraft.class_1733
 *  net.minecraft.class_1761
 *  net.minecraft.class_3705
 *  net.minecraft.class_3706
 *  net.minecraft.class_3803
 *  net.minecraft.class_3858
 *  net.minecraft.class_3910
 *  net.minecraft.class_3916
 *  net.minecraft.class_3971
 *  net.minecraft.class_481
 *  net.minecraft.class_481$class_483
 */
package minegame159.meteorclient.utils.player;

import minegame159.meteorclient.mixin.CreativeInventoryScreenAccessor;
import minegame159.meteorclient.mixin.HorseScreenHandlerAccessor;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1492;
import net.minecraft.class_1496;
import net.minecraft.class_1498;
import net.minecraft.class_1501;
import net.minecraft.class_1506;
import net.minecraft.class_1507;
import net.minecraft.class_1703;
import net.minecraft.class_1704;
import net.minecraft.class_1706;
import net.minecraft.class_1707;
import net.minecraft.class_1708;
import net.minecraft.class_1714;
import net.minecraft.class_1716;
import net.minecraft.class_1718;
import net.minecraft.class_1722;
import net.minecraft.class_1723;
import net.minecraft.class_1724;
import net.minecraft.class_1726;
import net.minecraft.class_1728;
import net.minecraft.class_1733;
import net.minecraft.class_1761;
import net.minecraft.class_3705;
import net.minecraft.class_3706;
import net.minecraft.class_3803;
import net.minecraft.class_3858;
import net.minecraft.class_3910;
import net.minecraft.class_3916;
import net.minecraft.class_3971;
import net.minecraft.class_481;

public class SlotUtils {
    public static final /* synthetic */ int OFFHAND;
    public static final /* synthetic */ int ARMOR_END;
    public static final /* synthetic */ int MAIN_END;
    public static final /* synthetic */ int ARMOR_START;
    public static final /* synthetic */ int HOTBAR_START;
    public static final /* synthetic */ int HOTBAR_END;
    public static final /* synthetic */ int MAIN_START;

    private static int stonecutter(int lllllllllllllllllIIlIIlIlIllIIIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIllIIIl)) {
            return 29 + lllllllllllllllllIIlIIlIlIllIIIl;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIllIIIl)) {
            return 2 + (lllllllllllllllllIIlIIlIlIllIIIl - 9);
        }
        return -1;
    }

    private static int lectern() {
        return -1;
    }

    private static int genericContainer(int lllllllllllllllllIIlIIlIlllIIlll, int lllllllllllllllllIIlIIlIlllIIllI) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlllIIlll)) {
            return (lllllllllllllllllIIlIIlIlllIIllI + 3) * 9 + lllllllllllllllllIIlIIlIlllIIlll;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlllIIlll)) {
            return lllllllllllllllllIIlIIlIlllIIllI * 9 + (lllllllllllllllllIIlIIlIlllIIlll - 9);
        }
        return -1;
    }

    public static int indexToId(int lllllllllllllllllIIlIIlIllllIIll) {
        if (Utils.mc.field_1724 == null) {
            return -1;
        }
        class_1703 lllllllllllllllllIIlIIlIllllIIlI = Utils.mc.field_1724.field_7512;
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1723) {
            return SlotUtils.survivalInventory(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_481.class_483) {
            return SlotUtils.creativeInventory(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1707) {
            return SlotUtils.genericContainer(lllllllllllllllllIIlIIlIllllIIll, ((class_1707)lllllllllllllllllIIlIIlIllllIIlI).method_17388());
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1714) {
            return SlotUtils.craftingTable(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3858) {
            return SlotUtils.furnace(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3705) {
            return SlotUtils.furnace(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3706) {
            return SlotUtils.furnace(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1716) {
            return SlotUtils.generic3x3(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1718) {
            return SlotUtils.enchantmentTable(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1708) {
            return SlotUtils.brewingStand(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1728) {
            return SlotUtils.villager(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1704) {
            return SlotUtils.beacon(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1706) {
            return SlotUtils.anvil(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1722) {
            return SlotUtils.hopper(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1733) {
            return SlotUtils.genericContainer(lllllllllllllllllIIlIIlIllllIIll, 3);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1724) {
            return SlotUtils.horse(lllllllllllllllllIIlIIlIllllIIlI, lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3910) {
            return SlotUtils.cartographyTable(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3803) {
            return SlotUtils.grindstone(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3916) {
            return SlotUtils.lectern();
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_1726) {
            return SlotUtils.loom(lllllllllllllllllIIlIIlIllllIIll);
        }
        if (lllllllllllllllllIIlIIlIllllIIlI instanceof class_3971) {
            return SlotUtils.stonecutter(lllllllllllllllllIIlIIlIllllIIll);
        }
        return -1;
    }

    private static boolean isArmor(int lllllllllllllllllIIlIIlIlIlIIlll) {
        return lllllllllllllllllIIlIIlIlIlIIlll >= 36 && lllllllllllllllllIIlIIlIlIlIIlll <= 39;
    }

    static {
        MAIN_START = 9;
        MAIN_END = 35;
        HOTBAR_START = 0;
        ARMOR_START = 36;
        HOTBAR_END = 8;
        ARMOR_END = 39;
        OFFHAND = 45;
    }

    private static int craftingTable(int lllllllllllllllllIIlIIlIlllIIIIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlllIIIIl)) {
            return 37 + lllllllllllllllllIIlIIlIlllIIIIl;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlllIIIIl)) {
            return lllllllllllllllllIIlIIlIlllIIIIl + 1;
        }
        return -1;
    }

    private static int brewingStand(int lllllllllllllllllIIlIIlIllIlIlIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIlIlIl)) {
            return 32 + lllllllllllllllllIIlIIlIllIlIlIl;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIlIlIl)) {
            return 5 + (lllllllllllllllllIIlIIlIllIlIlIl - 9);
        }
        return -1;
    }

    private static int loom(int lllllllllllllllllIIlIIlIlIllIIll) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIllIIll)) {
            return 31 + lllllllllllllllllIIlIIlIlIllIIll;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIllIIll)) {
            return 4 + (lllllllllllllllllIIlIIlIlIllIIll - 9);
        }
        return -1;
    }

    private static int furnace(int lllllllllllllllllIIlIIlIllIllllI) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIllllI)) {
            return 30 + lllllllllllllllllIIlIIlIllIllllI;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIllllI)) {
            return 3 + (lllllllllllllllllIIlIIlIllIllllI - 9);
        }
        return -1;
    }

    private static boolean isMain(int lllllllllllllllllIIlIIlIlIlIlIll) {
        return lllllllllllllllllIIlIIlIlIlIlIll >= 9 && lllllllllllllllllIIlIIlIlIlIlIll <= 35;
    }

    private static int cartographyTable(int lllllllllllllllllIIlIIlIlIlllIIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIlllIIl)) {
            return 30 + lllllllllllllllllIIlIIlIlIlllIIl;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIlllIIl)) {
            return 3 + (lllllllllllllllllIIlIIlIlIlllIIl - 9);
        }
        return -1;
    }

    private static int generic3x3(int lllllllllllllllllIIlIIlIllIllIll) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIllIll)) {
            return 36 + lllllllllllllllllIIlIIlIllIllIll;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIllIll)) {
            return lllllllllllllllllIIlIIlIllIllIll;
        }
        return -1;
    }

    private static int enchantmentTable(int lllllllllllllllllIIlIIlIllIllIII) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIllIII)) {
            return 29 + lllllllllllllllllIIlIIlIllIllIII;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIllIII)) {
            return 2 + (lllllllllllllllllIIlIIlIllIllIII - 9);
        }
        return -1;
    }

    private static int creativeInventory(int lllllllllllllllllIIlIIlIlllIlIlI) {
        if (!(Utils.mc.field_1755 instanceof class_481) || ((CreativeInventoryScreenAccessor)Utils.mc.field_1755).getSelectedTab() != class_1761.field_7918.method_7741()) {
            return -1;
        }
        return SlotUtils.survivalInventory(lllllllllllllllllIIlIIlIlllIlIlI);
    }

    private static int grindstone(int lllllllllllllllllIIlIIlIlIllIllI) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIllIllI)) {
            return 30 + lllllllllllllllllIIlIIlIlIllIllI;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIllIllI)) {
            return 3 + (lllllllllllllllllIIlIIlIlIllIllI - 9);
        }
        return -1;
    }

    private static boolean isHotbar(int lllllllllllllllllIIlIIlIlIlIllIl) {
        return lllllllllllllllllIIlIIlIlIlIllIl >= 0 && lllllllllllllllllIIlIIlIlIlIllIl <= 8;
    }

    private static int hopper(int lllllllllllllllllIIlIIlIllIIlIlI) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIIlIlI)) {
            return 32 + lllllllllllllllllIIlIIlIllIIlIlI;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIIlIlI)) {
            return 5 + (lllllllllllllllllIIlIIlIllIIlIlI - 9);
        }
        return -1;
    }

    public SlotUtils() {
        SlotUtils lllllllllllllllllIIlIIlIllllIllI;
    }

    private static int survivalInventory(int lllllllllllllllllIIlIIlIlllIllIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlllIllIl)) {
            return 36 + lllllllllllllllllIIlIIlIlllIllIl;
        }
        if (SlotUtils.isArmor(lllllllllllllllllIIlIIlIlllIllIl)) {
            return 5 + (lllllllllllllllllIIlIIlIlllIllIl - 36);
        }
        return lllllllllllllllllIIlIIlIlllIllIl;
    }

    private static int villager(int lllllllllllllllllIIlIIlIllIlIIll) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIlIIll)) {
            return 30 + lllllllllllllllllIIlIIlIllIlIIll;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIlIIll)) {
            return 3 + (lllllllllllllllllIIlIIlIllIlIIll - 9);
        }
        return -1;
    }

    private static int beacon(int lllllllllllllllllIIlIIlIllIlIIII) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIlIIII)) {
            return 28 + lllllllllllllllllIIlIIlIllIlIIII;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIlIIII)) {
            return 1 + (lllllllllllllllllIIlIIlIllIlIIII - 9);
        }
        return -1;
    }

    private static int anvil(int lllllllllllllllllIIlIIlIllIIllIl) {
        if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIllIIllIl)) {
            return 30 + lllllllllllllllllIIlIIlIllIIllIl;
        }
        if (SlotUtils.isMain(lllllllllllllllllIIlIIlIllIIllIl)) {
            return 3 + (lllllllllllllllllIIlIIlIllIIllIl - 9);
        }
        return -1;
    }

    private static int horse(class_1703 lllllllllllllllllIIlIIlIlIllllll, int lllllllllllllllllIIlIIlIlIlllllI) {
        class_1496 lllllllllllllllllIIlIIlIllIIIIII = ((HorseScreenHandlerAccessor)lllllllllllllllllIIlIIlIlIllllll).getEntity();
        if (lllllllllllllllllIIlIIlIllIIIIII instanceof class_1501) {
            int lllllllllllllllllIIlIIlIllIIIlII = ((class_1501)lllllllllllllllllIIlIIlIllIIIIII).method_6803();
            if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIlllllI)) {
                return 2 + 3 * lllllllllllllllllIIlIIlIllIIIlII + 28 + lllllllllllllllllIIlIIlIlIlllllI;
            }
            if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIlllllI)) {
                return 2 + 3 * lllllllllllllllllIIlIIlIllIIIlII + 1 + (lllllllllllllllllIIlIIlIlIlllllI - 9);
            }
        } else if (lllllllllllllllllIIlIIlIllIIIIII instanceof class_1498 || lllllllllllllllllIIlIIlIllIIIIII instanceof class_1506 || lllllllllllllllllIIlIIlIllIIIIII instanceof class_1507) {
            if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIlllllI)) {
                return 29 + lllllllllllllllllIIlIIlIlIlllllI;
            }
            if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIlllllI)) {
                return 2 + (lllllllllllllllllIIlIIlIlIlllllI - 9);
            }
        } else if (lllllllllllllllllIIlIIlIllIIIIII instanceof class_1492) {
            boolean lllllllllllllllllIIlIIlIllIIIIll = ((class_1492)lllllllllllllllllIIlIIlIllIIIIII).method_6703();
            if (SlotUtils.isHotbar(lllllllllllllllllIIlIIlIlIlllllI)) {
                return (lllllllllllllllllIIlIIlIllIIIIll ? 44 : 29) + lllllllllllllllllIIlIIlIlIlllllI;
            }
            if (SlotUtils.isMain(lllllllllllllllllIIlIIlIlIlllllI)) {
                return (lllllllllllllllllIIlIIlIllIIIIll ? 17 : 2) + (lllllllllllllllllIIlIIlIlIlllllI - 9);
            }
        }
        return -1;
    }
}

