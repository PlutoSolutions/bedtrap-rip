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
    public static final int OFFHAND;
    public static final int ARMOR_END;
    public static final int MAIN_END;
    public static final int ARMOR_START;
    public static final int HOTBAR_START;
    public static final int HOTBAR_END;
    public static final int MAIN_START;

    private static int stonecutter(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 29 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 2 + (n - 9);
        }
        return -1;
    }

    private static int lectern() {
        return -1;
    }

    private static int genericContainer(int n, int n2) {
        if (SlotUtils.isHotbar(n)) {
            return (n2 + 3) * 9 + n;
        }
        if (SlotUtils.isMain(n)) {
            return n2 * 9 + (n - 9);
        }
        return -1;
    }

    public static int indexToId(int n) {
        if (Utils.mc.field_1724 == null) {
            return -1;
        }
        class_1703 class_17032 = Utils.mc.field_1724.field_7512;
        if (class_17032 instanceof class_1723) {
            return SlotUtils.survivalInventory(n);
        }
        if (class_17032 instanceof class_481.class_483) {
            return SlotUtils.creativeInventory(n);
        }
        if (class_17032 instanceof class_1707) {
            return SlotUtils.genericContainer(n, ((class_1707)class_17032).method_17388());
        }
        if (class_17032 instanceof class_1714) {
            return SlotUtils.craftingTable(n);
        }
        if (class_17032 instanceof class_3858) {
            return SlotUtils.furnace(n);
        }
        if (class_17032 instanceof class_3705) {
            return SlotUtils.furnace(n);
        }
        if (class_17032 instanceof class_3706) {
            return SlotUtils.furnace(n);
        }
        if (class_17032 instanceof class_1716) {
            return SlotUtils.generic3x3(n);
        }
        if (class_17032 instanceof class_1718) {
            return SlotUtils.enchantmentTable(n);
        }
        if (class_17032 instanceof class_1708) {
            return SlotUtils.brewingStand(n);
        }
        if (class_17032 instanceof class_1728) {
            return SlotUtils.villager(n);
        }
        if (class_17032 instanceof class_1704) {
            return SlotUtils.beacon(n);
        }
        if (class_17032 instanceof class_1706) {
            return SlotUtils.anvil(n);
        }
        if (class_17032 instanceof class_1722) {
            return SlotUtils.hopper(n);
        }
        if (class_17032 instanceof class_1733) {
            return SlotUtils.genericContainer(n, 3);
        }
        if (class_17032 instanceof class_1724) {
            return SlotUtils.horse(class_17032, n);
        }
        if (class_17032 instanceof class_3910) {
            return SlotUtils.cartographyTable(n);
        }
        if (class_17032 instanceof class_3803) {
            return SlotUtils.grindstone(n);
        }
        if (class_17032 instanceof class_3916) {
            return SlotUtils.lectern();
        }
        if (class_17032 instanceof class_1726) {
            return SlotUtils.loom(n);
        }
        if (class_17032 instanceof class_3971) {
            return SlotUtils.stonecutter(n);
        }
        return -1;
    }

    private static boolean isArmor(int n) {
        return n >= 36 && n <= 39;
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

    private static int craftingTable(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 37 + n;
        }
        if (SlotUtils.isMain(n)) {
            return n + 1;
        }
        return -1;
    }

    private static int brewingStand(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 32 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 5 + (n - 9);
        }
        return -1;
    }

    private static int loom(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 31 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 4 + (n - 9);
        }
        return -1;
    }

    private static int furnace(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 30 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 3 + (n - 9);
        }
        return -1;
    }

    private static boolean isMain(int n) {
        return n >= 9 && n <= 35;
    }

    private static int cartographyTable(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 30 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 3 + (n - 9);
        }
        return -1;
    }

    private static int generic3x3(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 36 + n;
        }
        if (SlotUtils.isMain(n)) {
            return n;
        }
        return -1;
    }

    private static int enchantmentTable(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 29 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 2 + (n - 9);
        }
        return -1;
    }

    private static int creativeInventory(int n) {
        if (!(Utils.mc.field_1755 instanceof class_481) || ((CreativeInventoryScreenAccessor)Utils.mc.field_1755).getSelectedTab() != class_1761.field_7918.method_7741()) {
            return -1;
        }
        return SlotUtils.survivalInventory(n);
    }

    private static int grindstone(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 30 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 3 + (n - 9);
        }
        return -1;
    }

    private static boolean isHotbar(int n) {
        return n >= 0 && n <= 8;
    }

    private static int hopper(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 32 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 5 + (n - 9);
        }
        return -1;
    }

    private static int survivalInventory(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 36 + n;
        }
        if (SlotUtils.isArmor(n)) {
            return 5 + (n - 36);
        }
        return n;
    }

    private static int villager(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 30 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 3 + (n - 9);
        }
        return -1;
    }

    private static int beacon(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 28 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 1 + (n - 9);
        }
        return -1;
    }

    private static int anvil(int n) {
        if (SlotUtils.isHotbar(n)) {
            return 30 + n;
        }
        if (SlotUtils.isMain(n)) {
            return 3 + (n - 9);
        }
        return -1;
    }

    private static int horse(class_1703 class_17032, int n) {
        class_1496 class_14962 = ((HorseScreenHandlerAccessor)class_17032).getEntity();
        if (class_14962 instanceof class_1501) {
            int n2 = ((class_1501)class_14962).method_6803();
            if (SlotUtils.isHotbar(n)) {
                return 2 + 3 * n2 + 28 + n;
            }
            if (SlotUtils.isMain(n)) {
                return 2 + 3 * n2 + 1 + (n - 9);
            }
        } else if (class_14962 instanceof class_1498 || class_14962 instanceof class_1506 || class_14962 instanceof class_1507) {
            if (SlotUtils.isHotbar(n)) {
                return 29 + n;
            }
            if (SlotUtils.isMain(n)) {
                return 2 + (n - 9);
            }
        } else if (class_14962 instanceof class_1492) {
            boolean bl = ((class_1492)class_14962).method_6703();
            if (SlotUtils.isHotbar(n)) {
                return (bl ? 44 : 29) + n;
            }
            if (SlotUtils.isMain(n)) {
                return (bl ? 17 : 2) + (n - 9);
            }
        }
        return -1;
    }
}

