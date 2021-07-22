/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.PlayerUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public class AutoBuild
extends Module {
    private final /* synthetic */ Setting<Build> buildPlacement;
    private final /* synthetic */ SettingGroup sgHighway;
    private /* synthetic */ boolean sentMessage;
    /* synthetic */ int tickskip;
    private final /* synthetic */ Setting<Integer> size;
    private static final /* synthetic */ String[] BUILD;
    private /* synthetic */ DirectionLite directionLite;
    private final /* synthetic */ Setting<Boolean> selfToggle;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Setting<Boolean> sideBlocks;
    private final /* synthetic */ Setting<Boolean> center;
    private /* synthetic */ int prevSlot;
    private final /* synthetic */ Setting<Integer> cooldown;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ Direction direction;
    private /* synthetic */ boolean return_;

    private boolean findSlot() {
        AutoBuild lIIlllIIlIllI;
        assert (lIIlllIIlIllI.mc.field_1724 != null);
        lIIlllIIlIllI.prevSlot = lIIlllIIlIllI.mc.field_1724.field_7514.field_7545;
        for (int lIIlllIIlIlll = 0; lIIlllIIlIlll < 9; ++lIIlllIIlIlll) {
            class_1792 lIIlllIIllIII = lIIlllIIlIllI.mc.field_1724.field_7514.method_5438(lIIlllIIlIlll).method_7909();
            if (!(lIIlllIIllIII instanceof class_1747) || lIIlllIIllIII != class_1802.field_8281) continue;
            lIIlllIIlIllI.mc.field_1724.field_7514.field_7545 = lIIlllIIlIlll;
            return true;
        }
        return false;
    }

    @Override
    public void onActivate() {
        AutoBuild lIlllIlIIlIIl;
        if (lIlllIlIIlIIl.center.get().booleanValue()) {
            assert (lIlllIlIIlIIl.mc.field_1724 != null);
            double lIlllIlIIllII = (double)class_3532.method_15357((double)lIlllIlIIlIIl.mc.field_1724.method_23317()) + 0.5;
            double lIlllIlIIlIll = (double)class_3532.method_15357((double)lIlllIlIIlIIl.mc.field_1724.method_23321()) + 0.5;
            lIlllIlIIlIIl.mc.field_1724.method_5814(lIlllIlIIllII, lIlllIlIIlIIl.mc.field_1724.method_23318(), lIlllIlIIlIll);
            lIlllIlIIlIIl.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lIlllIlIIlIIl.mc.field_1724.method_23317(), lIlllIlIIlIIl.mc.field_1724.method_23318(), lIlllIlIIlIIl.mc.field_1724.method_23321(), lIlllIlIIlIIl.mc.field_1724.method_24828()));
        }
        assert (lIlllIlIIlIIl.mc.field_1724 != null);
        lIlllIlIIlIIl.blockPos.method_10101((class_2382)lIlllIlIIlIIl.mc.field_1724.method_24515());
        lIlllIlIIlIIl.direction = lIlllIlIIlIIl.getDirection((class_1657)lIlllIlIIlIIl.mc.field_1724);
        lIlllIlIIlIIl.directionLite = lIlllIlIIlIIl.getDirectionLite((class_1657)lIlllIlIIlIIl.mc.field_1724);
    }

    private void setBlockPos(int lIIlllIIllllI, int lIIlllIlIIIIl, int lIIlllIIlllII) {
        AutoBuild lIIlllIlIIIll;
        assert (lIIlllIlIIIll.mc.field_1724 != null);
        lIIlllIlIIIll.blockPos.method_10102(lIIlllIlIIIll.mc.field_1724.method_23317() + (double)lIIlllIIllllI, lIIlllIlIIIll.mc.field_1724.method_23318() + (double)lIIlllIlIIIIl, lIIlllIlIIIll.mc.field_1724.method_23321() + (double)lIIlllIIlllII);
    }

    static {
        BUILD = new String[4];
        AutoBuild.BUILD[0] = "[NomadHut]";
        AutoBuild.BUILD[1] = "[Penis]";
        AutoBuild.BUILD[2] = "[Swastika]";
        AutoBuild.BUILD[3] = "[Highway]";
    }

    private DirectionLite getDirectionLite(class_1657 lIIlllIIIllII) {
        double lIIlllIIIlIll = lIIlllIIIllII.field_6031;
        if (lIIlllIIIlIll == 0.0) {
            return DirectionLite.SOUTH;
        }
        if (lIIlllIIIlIll < 0.0) {
            if ((lIIlllIIIlIll -= (double)(class_3532.method_15384((double)(lIIlllIIIlIll / 360.0)) * 360)) < -180.0) {
                lIIlllIIIlIll = 360.0 + lIIlllIIIlIll;
            }
        } else if ((lIIlllIIIlIll -= (double)(class_3532.method_15357((double)(lIIlllIIIlIll / 360.0)) * 360)) > 180.0) {
            lIIlllIIIlIll = -360.0 + lIIlllIIIlIll;
        }
        if (lIIlllIIIlIll >= 135.5 || lIIlllIIIlIll < -135.5) {
            return DirectionLite.NORTH;
        }
        if (lIIlllIIIlIll >= -135.5 && lIIlllIIIlIll < -44.5) {
            return DirectionLite.EAST;
        }
        if (lIIlllIIIlIll >= -44.5 && lIIlllIIIlIll <= 44.5) {
            return DirectionLite.SOUTH;
        }
        if (lIIlllIIIlIll >= 44.5 && lIIlllIIIlIll < 135.5) {
            return DirectionLite.WEST;
        }
        return DirectionLite.SOUTH;
    }

    @Override
    public String getInfoString() {
        AutoBuild lIIllIlllllll;
        if (lIIllIlllllll.buildPlacement.get() == Build.NomadHut) {
            return BUILD[0];
        }
        if (lIIllIlllllll.buildPlacement.get() == Build.Penis) {
            return BUILD[1];
        }
        if (lIIllIlllllll.buildPlacement.get() == Build.Swastika) {
            return BUILD[2];
        }
        if (lIIllIlllllll.buildPlacement.get() == Build.Highway) {
            return BUILD[3];
        }
        return "None";
    }

    private void resetSlot() {
        AutoBuild lIIlllIIlIIIl;
        assert (lIIlllIIlIIIl.mc.field_1724 != null);
        lIIlllIIlIIIl.mc.field_1724.field_7514.field_7545 = lIIlllIIlIIIl.prevSlot;
    }

    private boolean place(int lIIlllIllIlIl, int lIIlllIlIllIl, int lIIlllIllIIll) {
        boolean lIIlllIllIIII;
        AutoBuild lIIlllIlIllll;
        lIIlllIlIllll.setBlockPos(lIIlllIllIlIl, lIIlllIlIllIl, lIIlllIllIIll);
        assert (lIIlllIlIllll.mc.field_1687 != null);
        class_2680 lIIlllIllIIlI = lIIlllIlIllll.mc.field_1687.method_8320((class_2338)lIIlllIlIllll.blockPos);
        boolean lIIlllIllIIIl = lIIlllIllIIlI.method_26204() == class_2246.field_10540;
        boolean bl = lIIlllIllIIII = !lIIlllIllIIlI.method_26207().method_15800();
        if (!lIIlllIllIIII && lIIlllIlIllll.findSlot()) {
            boolean lIIlllIllIlll;
            lIIlllIllIIII = PlayerUtils.placeBlock((class_2338)lIIlllIlIllll.blockPos, class_1268.field_5808, true);
            lIIlllIlIllll.resetSlot();
            boolean bl2 = lIIlllIllIlll = lIIlllIlIllll.mc.field_1687.method_8320((class_2338)lIIlllIlIllll.blockPos).method_26204() == class_2246.field_10540;
            if (!lIIlllIllIIIl && lIIlllIllIlll) {
                lIIlllIlIllll.return_ = true;
            }
        }
        return lIIlllIllIIII;
    }

    @EventHandler
    private void onTick(TickEvent.Post lIlIIIllIIlIl) {
        AutoBuild lIlIIIllIIllI;
        int lIlIIIllIIlII = -1;
        for (int lIllIlIlIIIll = 0; lIllIlIlIIIll < 9; ++lIllIlIlIIIll) {
            assert (lIlIIIllIIllI.mc.field_1724 != null);
            if (lIlIIIllIIllI.mc.field_1724.field_7514.method_5438(lIllIlIlIIIll).method_7909() != class_2246.field_10540.method_8389()) continue;
            lIlIIIllIIlII = lIllIlIlIIIll;
            break;
        }
        if (lIlIIIllIIlII == -1 && lIlIIIllIIllI.selfToggle.get().booleanValue()) {
            if (!lIlIIIllIIllI.sentMessage) {
                ChatUtils.warning("No obsidian found\u2026 disabling.", new Object[0]);
                lIlIIIllIIllI.sentMessage = true;
            }
            lIlIIIllIIllI.toggle();
            return;
        }
        if (lIlIIIllIIlII == -1) {
            return;
        }
        int lIlIIIllIIIll = lIlIIIllIIllI.mc.field_1724.field_7514.field_7545;
        if (lIlIIIllIIllI.buildPlacement.get() == Build.NomadHut) {
            lIlIIIllIIllI.mc.field_1724.field_7514.field_7545 = lIlIIIllIIlII;
            if (lIlIIIllIIllI.tickskip != 0) {
                --lIlIIIllIIllI.tickskip;
                return;
            }
            lIlIIIllIIllI.tickskip = lIlIIIllIIllI.cooldown.get();
            lIlIIIllIIllI.return_ = false;
            switch (lIlIIIllIIllI.directionLite) {
                case EAST: {
                    boolean lIllIlIlIIIlI = lIlIIIllIIllI.place(2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIlIIIIl = lIlIIIllIIllI.place(2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIlIIIII = lIlIIIllIIllI.place(1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlllll = lIlIIIllIIllI.place(0, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIllllI = lIlIIIllIIllI.place(-1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlllIl = lIlIIIllIIllI.place(-2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlllII = lIlIIIllIIllI.place(-2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIllIll = lIlIIIllIIllI.place(-2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIllIlI = lIlIIIllIIllI.place(-1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIllIIl = lIlIIIllIIllI.place(0, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIllIII = lIlIIIllIIllI.place(1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIlll = lIlIIIllIIllI.place(2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIllI = lIlIIIllIIllI.place(2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIlIl = lIlIIIllIIllI.place(1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIlII = lIlIIIllIIllI.place(-1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIIll = lIlIIIllIIllI.place(-2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIIlI = lIlIIIllIIllI.place(-2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIIIl = lIlIIIllIIllI.place(-1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIlIIII = lIlIIIllIIllI.place(1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIllll = lIlIIIllIIllI.place(2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIlllI = lIlIIIllIIllI.place(2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIllIl = lIlIIIllIIllI.place(2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIllII = lIlIIIllIIllI.place(1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIlIll = lIlIIIllIIllI.place(0, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIlIlI = lIlIIIllIIllI.place(-1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIlIIl = lIlIIIllIIllI.place(-2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIlIII = lIlIIIllIIllI.place(-2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIlll = lIlIIIllIIllI.place(-2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIllI = lIlIIIllIIllI.place(-1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIlIl = lIlIIIllIIllI.place(0, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIlII = lIlIIIllIIllI.place(1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIIll = lIlIIIllIIllI.place(1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIIlI = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIIIl = lIlIIIllIIllI.place(1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIlIIIIIII = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllllll = lIlIIIllIIllI.place(0, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllllllI = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllllIl = lIlIIIllIIllI.place(-1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllllII = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllllIll = lIlIIIllIIllI.place(-1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIllIlIlIIIlI && lIllIlIlIIIIl && lIllIlIlIIIII && lIllIlIIlllll && lIllIlIIllllI && lIllIlIIlllIl && lIllIlIIlllII && lIllIlIIllIll && lIllIlIIllIlI && lIllIlIIllIIl && lIllIlIIllIII && lIllIlIIlIlll && lIllIlIIlIllI && lIllIlIIlIlIl && lIllIlIIlIlII && lIllIlIIlIIll && lIllIlIIlIIlI && lIllIlIIlIIIl && lIllIlIIlIIII && lIllIlIIIllll && lIllIlIIIlllI && lIllIlIIIllIl && lIllIlIIIllII && lIllIlIIIlIll && lIllIlIIIlIlI && lIllIlIIIlIIl && lIllIlIIIlIII && lIllIlIIIIlll && lIllIlIIIIllI && lIllIlIIIIlIl && lIllIlIIIIlII && lIllIlIIIIIll && lIllIlIIIIIlI && lIllIlIIIIIIl && lIllIlIIIIIII && lIllIIlllllll && lIllIIllllllI && lIllIIlllllIl && lIllIIlllllII && lIllIIllllIll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case NORTH: {
                    boolean lIllIIllllIlI = lIlIIIllIIllI.place(2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllllIIl = lIlIIIllIIllI.place(2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllllIII = lIlIIIllIIllI.place(2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIlll = lIlIIIllIIllI.place(1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIllI = lIlIIIllIIllI.place(0, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIlIl = lIlIIIllIIllI.place(-1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIlII = lIlIIIllIIllI.place(-2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIIll = lIlIIIllIIllI.place(-2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIIlI = lIlIIIllIIllI.place(-2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIIIl = lIlIIIllIIllI.place(-1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlllIIII = lIlIIIllIIllI.place(1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIllll = lIlIIIllIIllI.place(2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIlllI = lIlIIIllIIllI.place(2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIllIl = lIlIIIllIIllI.place(1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIllII = lIlIIIllIIllI.place(-1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIlIll = lIlIIIllIIllI.place(-2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIlIlI = lIlIIIllIIllI.place(-2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIlIIl = lIlIIIllIIllI.place(-1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIlIII = lIlIIIllIIllI.place(1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIlll = lIlIIIllIIllI.place(2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIllI = lIlIIIllIIllI.place(2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIlIl = lIlIIIllIIllI.place(2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIlII = lIlIIIllIIllI.place(1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIIll = lIlIIIllIIllI.place(0, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIIlI = lIlIIIllIIllI.place(-1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIIIl = lIlIIIllIIllI.place(-2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIllIIIII = lIlIIIllIIllI.place(-2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlllll = lIlIIIllIIllI.place(-2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIllllI = lIlIIIllIIllI.place(-1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlllIl = lIlIIIllIIllI.place(0, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlllII = lIlIIIllIIllI.place(1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIllIll = lIlIIIllIIllI.place(1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIllIlI = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIllIIl = lIlIIIllIIllI.place(1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIllIII = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIlll = lIlIIIllIIllI.place(0, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIllI = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIlIl = lIlIIIllIIllI.place(-1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIlII = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIIll = lIlIIIllIIllI.place(-1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIllIIllllIlI && lIllIIllllIIl && lIllIIllllIII && lIllIIlllIlll && lIllIIlllIllI && lIllIIlllIlIl && lIllIIlllIlII && lIllIIlllIIll && lIllIIlllIIlI && lIllIIlllIIIl && lIllIIlllIIII && lIllIIllIllll && lIllIIllIlllI && lIllIIllIllIl && lIllIIllIllII && lIllIIllIlIll && lIllIIllIlIlI && lIllIIllIlIIl && lIllIIllIlIII && lIllIIllIIlll && lIllIIllIIllI && lIllIIllIIlIl && lIllIIllIIlII && lIllIIllIIIll && lIllIIllIIIlI && lIllIIllIIIIl && lIllIIllIIIII && lIllIIlIlllll && lIllIIlIllllI && lIllIIlIlllIl && lIllIIlIlllII && lIllIIlIllIll && lIllIIlIllIlI && lIllIIlIllIIl && lIllIIlIllIII && lIllIIlIlIlll && lIllIIlIlIllI && lIllIIlIlIlIl && lIllIIlIlIlII && lIllIIlIlIIll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case WEST: {
                    boolean lIllIIlIlIIlI = lIlIIIllIIllI.place(2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIIIl = lIlIIIllIIllI.place(2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIlIIII = lIlIIIllIIllI.place(2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIllll = lIlIIIllIIllI.place(1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIlllI = lIlIIIllIIllI.place(0, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIllIl = lIlIIIllIIllI.place(-1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIllII = lIlIIIllIIllI.place(-2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIlIll = lIlIIIllIIllI.place(-2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIlIlI = lIlIIIllIIllI.place(-1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIlIIl = lIlIIIllIIllI.place(0, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIlIII = lIlIIIllIIllI.place(1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIlll = lIlIIIllIIllI.place(2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIllI = lIlIIIllIIllI.place(2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIlIl = lIlIIIllIIllI.place(1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIlII = lIlIIIllIIllI.place(-1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIIll = lIlIIIllIIllI.place(-2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIIlI = lIlIIIllIIllI.place(-2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIIIl = lIlIIIllIIllI.place(-1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIlIIIIII = lIlIIIllIIllI.place(1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllllll = lIlIIIllIIllI.place(2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlllllI = lIlIIIllIIllI.place(2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllllIl = lIlIIIllIIllI.place(2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllllII = lIlIIIllIIllI.place(1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlllIll = lIlIIIllIIllI.place(0, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlllIlI = lIlIIIllIIllI.place(-1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlllIIl = lIlIIIllIIllI.place(-2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlllIII = lIlIIIllIIllI.place(-2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIlll = lIlIIIllIIllI.place(-2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIllI = lIlIIIllIIllI.place(-1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIlIl = lIlIIIllIIllI.place(0, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIlII = lIlIIIllIIllI.place(1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIIll = lIlIIIllIIllI.place(1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIIlI = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIIIl = lIlIIIllIIllI.place(1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIllIIII = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIllll = lIlIIIllIIllI.place(0, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIlllI = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIllIl = lIlIIIllIIllI.place(-1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIllII = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIlIll = lIlIIIllIIllI.place(-1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIllIIlIlIIlI && lIllIIlIlIIIl && lIllIIlIlIIII && lIllIIlIIllll && lIllIIlIIlllI && lIllIIlIIllIl && lIllIIlIIllII && lIllIIlIIlIll && lIllIIlIIlIlI && lIllIIlIIlIIl && lIllIIlIIlIII && lIllIIlIIIlll && lIllIIlIIIllI && lIllIIlIIIlIl && lIllIIlIIIlII && lIllIIlIIIIll && lIllIIlIIIIlI && lIllIIlIIIIIl && lIllIIlIIIIII && lIllIIIllllll && lIllIIIlllllI && lIllIIIllllIl && lIllIIIllllII && lIllIIIlllIll && lIllIIIlllIlI && lIllIIIlllIIl && lIllIIIlllIII && lIllIIIllIlll && lIllIIIllIllI && lIllIIIllIlIl && lIllIIIllIlII && lIllIIIllIIll && lIllIIIllIIlI && lIllIIIllIIIl && lIllIIIllIIII && lIllIIIlIllll && lIllIIIlIlllI && lIllIIIlIllIl && lIllIIIlIllII && lIllIIIlIlIll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case SOUTH: {
                    boolean lIllIIIlIlIlI = lIlIIIllIIllI.place(2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIlIIl = lIlIIIllIIllI.place(2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIlIII = lIlIIIllIIllI.place(2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIlll = lIlIIIllIIllI.place(1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIllI = lIlIIIllIIllI.place(-1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIlIl = lIlIIIllIIllI.place(-2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIlII = lIlIIIllIIllI.place(-2, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIIll = lIlIIIllIIllI.place(-2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIIlI = lIlIIIllIIllI.place(-1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIIIl = lIlIIIllIIllI.place(0, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIlIIIII = lIlIIIllIIllI.place(1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlllll = lIlIIIllIIllI.place(2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIllllI = lIlIIIllIIllI.place(2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlllIl = lIlIIIllIIllI.place(1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlllII = lIlIIIllIIllI.place(-1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIllIll = lIlIIIllIIllI.place(-2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIllIlI = lIlIIIllIIllI.place(-2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIllIIl = lIlIIIllIIllI.place(-1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIllIII = lIlIIIllIIllI.place(1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIlll = lIlIIIllIIllI.place(2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIllI = lIlIIIllIIllI.place(2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIlIl = lIlIIIllIIllI.place(2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIlII = lIlIIIllIIllI.place(1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIIll = lIlIIIllIIllI.place(0, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIIlI = lIlIIIllIIllI.place(-1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIIIl = lIlIIIllIIllI.place(-2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIlIIII = lIlIIIllIIllI.place(-2, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIllll = lIlIIIllIIllI.place(-2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIlllI = lIlIIIllIIllI.place(-1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIllIl = lIlIIIllIIllI.place(0, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIllII = lIlIIIllIIllI.place(1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIlIll = lIlIIIllIIllI.place(1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIlIlI = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIlIIl = lIlIIIllIIllI.place(1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIlIII = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIlll = lIlIIIllIIllI.place(0, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIllI = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIlIl = lIlIIIllIIllI.place(-1, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIlII = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIIll = lIlIIIllIIllI.place(-1, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIllIIIlIlIlI && lIllIIIlIlIIl && lIllIIIlIlIII && lIllIIIlIIlll && lIllIIIlIIllI && lIllIIIlIIlIl && lIllIIIlIIlII && lIllIIIlIIIll && lIllIIIlIIIlI && lIllIIIlIIIIl && lIllIIIlIIIII && lIllIIIIlllll && lIllIIIIllllI && lIllIIIIlllIl && lIllIIIIlllII && lIllIIIIllIll && lIllIIIIllIlI && lIllIIIIllIIl && lIllIIIIllIII && lIllIIIIlIlll && lIllIIIIlIllI && lIllIIIIlIlIl && lIllIIIIlIlII && lIllIIIIlIIll && lIllIIIIlIIlI && lIllIIIIlIIIl && lIllIIIIlIIII && lIllIIIIIllll && lIllIIIIIlllI && lIllIIIIIllIl && lIllIIIIIllII && lIllIIIIIlIll && lIllIIIIIlIlI && lIllIIIIIlIIl && lIllIIIIIlIII && lIllIIIIIIlll && lIllIIIIIIllI && lIllIIIIIIlIl && lIllIIIIIIlII && lIllIIIIIIIll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
            }
        }
        if (lIlIIIllIIllI.buildPlacement.get() == Build.Penis) {
            lIlIIIllIIllI.mc.field_1724.field_7514.field_7545 = lIlIIIllIIlII;
            if (lIlIIIllIIllI.tickskip != 0) {
                --lIlIIIllIIllI.tickskip;
                return;
            }
            lIlIIIllIIllI.tickskip = lIlIIIllIIllI.cooldown.get();
            lIlIIIllIIllI.return_ = false;
            switch (lIlIIIllIIllI.directionLite) {
                case EAST: {
                    boolean lIllIIIIIIIlI = lIlIIIllIIllI.place(1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIIIl = lIlIIIllIIllI.place(1, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIllIIIIIIIII = lIlIIIllIIllI.place(1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllllll = lIlIIIllIIllI.place(1, 1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllllllI = lIlIIIllIIllI.place(1, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllllIl = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIllIIIIIIIlI && lIllIIIIIIIIl && lIllIIIIIIIII && lIlIlllllllll && lIlIllllllllI && lIlIlllllllIl) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case NORTH: {
                    boolean lIlIlllllllII = lIlIIIllIIllI.place(-1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllllIll = lIlIIIllIIllI.place(0, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllllIlI = lIlIIIllIIllI.place(1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllllIIl = lIlIIIllIIllI.place(0, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllllIII = lIlIIIllIIllI.place(0, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIlll = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIlllllllII && lIlIllllllIll && lIlIllllllIlI && lIlIllllllIIl && lIlIllllllIII && lIlIlllllIlll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case WEST: {
                    boolean lIlIlllllIllI = lIlIIIllIIllI.place(-1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIlIl = lIlIIIllIIllI.place(-1, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIlII = lIlIIIllIIllI.place(-1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIIll = lIlIIIllIIllI.place(-1, 1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIIlI = lIlIIIllIIllI.place(-1, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllllIIIl = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIlllllIllI && lIlIlllllIlIl && lIlIlllllIlII && lIlIlllllIIll && lIlIlllllIIlI && lIlIlllllIIIl) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case SOUTH: {
                    boolean lIlIlllllIIII = lIlIIIllIIllI.place(-1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIllll = lIlIIIllIIllI.place(0, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIlllI = lIlIIIllIIllI.place(1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIllIl = lIlIIIllIIllI.place(0, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIllII = lIlIIIllIIllI.place(0, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIlIll = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIlllllIIII && lIlIllllIllll && lIlIllllIlllI && lIlIllllIllIl && lIlIllllIllII && lIlIllllIlIll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
            }
        }
        if (lIlIIIllIIllI.buildPlacement.get() == Build.Swastika) {
            lIlIIIllIIllI.mc.field_1724.field_7514.field_7545 = lIlIIIllIIlII;
            if (lIlIIIllIIllI.tickskip != 0) {
                --lIlIIIllIIllI.tickskip;
                return;
            }
            lIlIIIllIIllI.tickskip = lIlIIIllIIllI.cooldown.get();
            lIlIIIllIIllI.return_ = false;
            switch (lIlIIIllIIllI.directionLite) {
                case EAST: {
                    boolean lIlIllllIlIlI = lIlIIIllIIllI.place(1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIlIIl = lIlIIIllIIllI.place(1, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIlIII = lIlIIIllIIllI.place(1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIlll = lIlIIIllIIllI.place(1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIllI = lIlIIIllIIllI.place(1, 1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIlIl = lIlIIIllIIllI.place(1, 1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIlII = lIlIIIllIIllI.place(1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIIll = lIlIIIllIIllI.place(1, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIIlI = lIlIIIllIIllI.place(1, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIIIl = lIlIIIllIIllI.place(1, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllllIIIII = lIlIIIllIIllI.place(1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlllll = lIlIIIllIIllI.place(1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIllllI = lIlIIIllIIllI.place(1, 3, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlllIl = lIlIIIllIIllI.place(1, 4, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlllII = lIlIIIllIIllI.place(1, 4, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIllIll = lIlIIIllIIllI.place(1, 4, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIllIlI = lIlIIIllIIllI.place(1, 4, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIllllIlIlI && lIlIllllIlIIl && lIlIllllIlIII && lIlIllllIIlll && lIlIllllIIllI && lIlIllllIIlIl && lIlIllllIIlII && lIlIllllIIIll && lIlIllllIIIlI && lIlIllllIIIIl && lIlIllllIIIII && lIlIlllIlllll && lIlIlllIllllI && lIlIlllIlllIl && lIlIlllIlllII && lIlIlllIllIll && lIlIlllIllIlI) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case NORTH: {
                    boolean lIlIlllIllIIl = lIlIIIllIIllI.place(2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIllIII = lIlIIIllIIllI.place(0, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIlll = lIlIIIllIIllI.place(-1, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIllI = lIlIIIllIIllI.place(-2, 0, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIlIl = lIlIIIllIIllI.place(2, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIlII = lIlIIIllIIllI.place(0, 1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIIll = lIlIIIllIIllI.place(2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIIlI = lIlIIIllIIllI.place(1, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIIIl = lIlIIIllIIllI.place(0, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIlIIII = lIlIIIllIIllI.place(-1, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIllll = lIlIIIllIIllI.place(-2, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIlllI = lIlIIIllIIllI.place(0, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIllIl = lIlIIIllIIllI.place(-2, 3, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIllII = lIlIIIllIIllI.place(2, 4, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIlIll = lIlIIIllIIllI.place(1, 4, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIlIlI = lIlIIIllIIllI.place(0, 4, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIlIIl = lIlIIIllIIllI.place(-2, 4, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIlllIllIIl && lIlIlllIllIII && lIlIlllIlIlll && lIlIlllIlIllI && lIlIlllIlIlIl && lIlIlllIlIlII && lIlIlllIlIIll && lIlIlllIlIIlI && lIlIlllIlIIIl && lIlIlllIlIIII && lIlIlllIIllll && lIlIlllIIlllI && lIlIlllIIllIl && lIlIlllIIllII && lIlIlllIIlIll && lIlIlllIIlIlI && lIlIlllIIlIIl) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case WEST: {
                    boolean lIlIlllIIlIII = lIlIIIllIIllI.place(-1, 0, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIlll = lIlIIIllIIllI.place(-1, 0, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIllI = lIlIIIllIIllI.place(-1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIlIl = lIlIIIllIIllI.place(-1, 0, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIlII = lIlIIIllIIllI.place(-1, 1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIIll = lIlIIIllIIllI.place(-1, 1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIIlI = lIlIIIllIIllI.place(-1, 2, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIIIl = lIlIIIllIIllI.place(-1, 2, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlllIIIIII = lIlIIIllIIllI.place(-1, 2, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllllll = lIlIIIllIIllI.place(-1, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlllllI = lIlIIIllIIllI.place(-1, 2, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllllIl = lIlIIIllIIllI.place(-1, 3, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllllII = lIlIIIllIIllI.place(-1, 3, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlllIll = lIlIIIllIIllI.place(-1, 4, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlllIlI = lIlIIIllIIllI.place(-1, 4, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlllIIl = lIlIIIllIIllI.place(-1, 4, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlllIII = lIlIIIllIIllI.place(-1, 4, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIlllIIlIII && lIlIlllIIIlll && lIlIlllIIIllI && lIlIlllIIIlIl && lIlIlllIIIlII && lIlIlllIIIIll && lIlIlllIIIIlI && lIlIlllIIIIIl && lIlIlllIIIIII && lIlIllIllllll && lIlIllIlllllI && lIlIllIllllIl && lIlIllIllllII && lIlIllIlllIll && lIlIllIlllIlI && lIlIllIlllIIl && lIlIllIlllIII) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
                case SOUTH: {
                    boolean lIlIllIllIlll = lIlIIIllIIllI.place(-2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIllI = lIlIIIllIIllI.place(0, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIlIl = lIlIIIllIIllI.place(1, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIlII = lIlIIIllIIllI.place(2, 0, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIIll = lIlIIIllIIllI.place(-2, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIIlI = lIlIIIllIIllI.place(0, 1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIIIl = lIlIIIllIIllI.place(-2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIllIIII = lIlIIIllIIllI.place(-1, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIllll = lIlIIIllIIllI.place(0, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIlllI = lIlIIIllIIllI.place(1, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIllIl = lIlIIIllIIllI.place(2, 2, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIllII = lIlIIIllIIllI.place(0, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIlIll = lIlIIIllIIllI.place(2, 3, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIlIlI = lIlIIIllIIllI.place(-2, 4, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIlIIl = lIlIIIllIIllI.place(-1, 4, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIlIII = lIlIIIllIIllI.place(0, 4, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIlIIlll = lIlIIIllIIllI.place(2, 4, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIllIllIlll && lIlIllIllIllI && lIlIllIllIlIl && lIlIllIllIlII && lIlIllIllIIll && lIlIllIllIIlI && lIlIllIllIIIl && lIlIllIllIIII && lIlIllIlIllll && lIlIllIlIlllI && lIlIllIlIllIl && lIlIllIlIllII && lIlIllIlIlIll && lIlIllIlIlIlI && lIlIllIlIlIIl && lIlIllIlIlIII && lIlIllIlIIlll) {
                        lIlIIIllIIllI.toggle();
                        break;
                    }
                    return;
                }
            }
        }
        if (lIlIIIllIIllI.buildPlacement.get() == Build.Highway) {
            lIlIIIllIIllI.mc.field_1724.field_7514.field_7545 = lIlIIIllIIlII;
            if (lIlIIIllIIllI.tickskip != 0) {
                --lIlIIIllIIllI.tickskip;
                return;
            }
            lIlIIIllIIllI.tickskip = lIlIIIllIIllI.cooldown.get();
            lIlIIIllIIllI.return_ = false;
            switch (lIlIIIllIIllI.direction) {
                case EAST: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIllIlIIlII = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIlIIllI = lIlIIIllIIllI.place(1, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIllIlIIlIl = lIlIIIllIIllI.place(1, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIlIIllI && lIlIllIlIIlIl) {
                                return;
                            }
                        }
                        if (lIlIllIlIIlII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIllIlIIIIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIlIIIII = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlllll = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIlIIIll = lIlIIIllIIllI.place(1, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIllIlIIIlI = lIlIIIllIIllI.place(1, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIlIIIll && lIlIllIlIIIlI) {
                                return;
                            }
                        }
                        if (lIlIllIlIIIIl && lIlIllIlIIIII && lIlIllIIlllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIllIIlllII = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIllIll = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIllIlI = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIllIIl = lIlIIIllIIllI.place(1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIllIII = lIlIIIllIIllI.place(1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIIllllI = lIlIIIllIIllI.place(1, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIllIIlllIl = lIlIIIllIIllI.place(1, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIIllllI && lIlIllIIlllIl) {
                                return;
                            }
                        }
                        if (lIlIllIIlllII && lIlIllIIllIll && lIlIllIIllIlI && lIlIllIIllIIl && lIlIllIIllIII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIllIIlIlIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlIlII = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlIIll = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlIIlI = lIlIIIllIIllI.place(1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlIIIl = lIlIIIllIIllI.place(1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIlIIII = lIlIIIllIIllI.place(1, -1, 3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIIllll = lIlIIIllIIllI.place(1, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIIlIlll = lIlIIIllIIllI.place(1, 0, 4);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIllIIlIllI = lIlIIIllIIllI.place(1, 0, -4);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIIlIlll && lIlIllIIlIllI) {
                                return;
                            }
                        }
                        if (lIlIllIIlIlIl && lIlIllIIlIlII && lIlIllIIlIIll && lIlIllIIlIIlI && lIlIllIIlIIIl && lIlIllIIlIIII && lIlIllIIIllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIllIIIllII = lIlIIIllIIllI.place(1, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIlIll = lIlIIIllIIllI.place(1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIlIlI = lIlIIIllIIllI.place(1, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIlIIl = lIlIIIllIIllI.place(1, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIlIII = lIlIIIllIIllI.place(1, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIIlll = lIlIIIllIIllI.place(1, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIIllI = lIlIIIllIIllI.place(1, -1, -3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIIlIl = lIlIIIllIIllI.place(1, -1, 4);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIllIIIIlII = lIlIIIllIIllI.place(1, -1, -4);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIllIIIlllI = lIlIIIllIIllI.place(1, 0, 5);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIllIIIllIl = lIlIIIllIIllI.place(1, 0, -5);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIllIIIlllI && lIlIllIIIllIl) {
                            return;
                        }
                    }
                    if (!lIlIllIIIllII || !lIlIllIIIlIll || !lIlIllIIIlIlI || !lIlIllIIIlIIl || !lIlIllIIIlIII || !lIlIllIIIIlll || !lIlIllIIIIllI || !lIlIllIIIIlIl || !lIlIllIIIIlII) break;
                    return;
                }
                case NORTH: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIllIIIIIIl = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIIIIIll = lIlIIIllIIllI.place(1, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIllIIIIIlI = lIlIIIllIIllI.place(-1, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIIIIIll && lIlIllIIIIIlI) {
                                return;
                            }
                        }
                        if (lIlIllIIIIIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIlIllllllI = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllllIl = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllllII = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIllIIIIIII = lIlIIIllIIllI.place(2, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlllllll = lIlIIIllIIllI.place(-2, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIllIIIIIII && lIlIlIlllllll) {
                                return;
                            }
                        }
                        if (lIlIlIllllllI && lIlIlIlllllIl && lIlIlIlllllII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIlIllllIIl = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllllIII = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllIlll = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllIllI = lIlIIIllIIllI.place(2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllIlIl = lIlIIIllIIllI.place(-2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIllllIll = lIlIIIllIIllI.place(3, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIllllIlI = lIlIIIllIIllI.place(-3, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIllllIll && lIlIlIllllIlI) {
                                return;
                            }
                        }
                        if (lIlIlIllllIIl && lIlIlIllllIII && lIlIlIlllIlll && lIlIlIlllIllI && lIlIlIlllIlIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIlIlllIIlI = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllIIIl = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlllIIII = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllIllll = lIlIIIllIIllI.place(2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllIlllI = lIlIIIllIIllI.place(-2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllIllIl = lIlIIIllIIllI.place(3, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllIllII = lIlIIIllIIllI.place(-3, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIlllIlII = lIlIIIllIIllI.place(4, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlllIIll = lIlIIIllIIllI.place(-4, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIlllIlII && lIlIlIlllIIll) {
                                return;
                            }
                        }
                        if (lIlIlIlllIIlI && lIlIlIlllIIIl && lIlIlIlllIIII && lIlIlIllIllll && lIlIlIllIlllI && lIlIlIllIllIl && lIlIlIllIllII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIlIllIlIIl = lIlIIIllIIllI.place(0, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIlIII = lIlIIIllIIllI.place(1, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIlll = lIlIIIllIIllI.place(-1, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIllI = lIlIIIllIIllI.place(2, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIlIl = lIlIIIllIIllI.place(-2, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIlII = lIlIIIllIIllI.place(3, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIIll = lIlIIIllIIllI.place(-3, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIIlI = lIlIIIllIIllI.place(4, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIllIIIIl = lIlIIIllIIllI.place(-4, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIlIllIlIll = lIlIIIllIIllI.place(5, 0, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIllIlIlI = lIlIIIllIIllI.place(-5, 0, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIlIllIlIll && lIlIlIllIlIlI) {
                            return;
                        }
                    }
                    if (!lIlIlIllIlIIl || !lIlIlIllIlIII || !lIlIlIllIIlll || !lIlIlIllIIllI || !lIlIlIllIIlIl || !lIlIlIllIIlII || !lIlIlIllIIIll || !lIlIlIllIIIlI || !lIlIlIllIIIIl) break;
                    return;
                }
                case WEST: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIlIlIllllI = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIllIIIII = lIlIIIllIIllI.place(-1, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlIlllll = lIlIIIllIIllI.place(-1, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIllIIIII && lIlIlIlIlllll) {
                                return;
                            }
                        }
                        if (lIlIlIlIllllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIlIlIllIll = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIllIlI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIllIIl = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIlIlllIl = lIlIIIllIIllI.place(-1, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlIlllII = lIlIIIllIIllI.place(-1, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIlIlllIl && lIlIlIlIlllII) {
                                return;
                            }
                        }
                        if (lIlIlIlIllIll && lIlIlIlIllIlI && lIlIlIlIllIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIlIlIlIllI = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIlIlIl = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIlIlII = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIlIIll = lIlIIIllIIllI.place(-1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIlIIlI = lIlIIIllIIllI.place(-1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIlIllIII = lIlIIIllIIllI.place(-1, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlIlIlll = lIlIIIllIIllI.place(-1, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIlIllIII && lIlIlIlIlIlll) {
                                return;
                            }
                        }
                        if (lIlIlIlIlIllI && lIlIlIlIlIlIl && lIlIlIlIlIlII && lIlIlIlIlIIll && lIlIlIlIlIIlI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIlIlIIllll = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIlllI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIllIl = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIllII = lIlIIIllIIllI.place(-1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIlIll = lIlIIIllIIllI.place(-1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIlIlI = lIlIIIllIIllI.place(-1, -1, 3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIlIIl = lIlIIIllIIllI.place(-1, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIlIlIIIl = lIlIIIllIIllI.place(-1, 0, 4);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIlIlIIII = lIlIIIllIIllI.place(-1, 0, -4);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIlIlIIIl && lIlIlIlIlIIII) {
                                return;
                            }
                        }
                        if (lIlIlIlIIllll && lIlIlIlIIlllI && lIlIlIlIIllIl && lIlIlIlIIllII && lIlIlIlIIlIll && lIlIlIlIIlIlI && lIlIlIlIIlIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIlIlIIIllI = lIlIIIllIIllI.place(-1, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIlIl = lIlIIIllIIllI.place(-1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIlII = lIlIIIllIIllI.place(-1, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIIll = lIlIIIllIIllI.place(-1, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIIlI = lIlIIIllIIllI.place(-1, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIIIl = lIlIIIllIIllI.place(-1, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIlIIIIII = lIlIIIllIIllI.place(-1, -1, -3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIllllll = lIlIIIllIIllI.place(-1, -1, 4);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIlllllI = lIlIIIllIIllI.place(-1, -1, -4);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIlIlIIlIII = lIlIIIllIIllI.place(-1, 0, 5);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIlIIIlll = lIlIIIllIIllI.place(-1, 0, -5);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIlIlIIlIII && lIlIlIlIIIlll) {
                            return;
                        }
                    }
                    if (!lIlIlIlIIIllI || !lIlIlIlIIIlIl || !lIlIlIlIIIlII || !lIlIlIlIIIIll || !lIlIlIlIIIIlI || !lIlIlIlIIIIIl || !lIlIlIlIIIIII || !lIlIlIIllllll || !lIlIlIIlllllI) break;
                    return;
                }
                case SOUTH: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIlIIlllIll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIllllIl = lIlIIIllIIllI.place(1, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIllllII = lIlIIIllIIllI.place(-1, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIllllIl && lIlIlIIllllII) {
                                return;
                            }
                        }
                        if (lIlIlIIlllIll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIlIIlllIII = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIllIlll = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIllIllI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIlllIlI = lIlIIIllIIllI.place(2, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIlllIIl = lIlIIIllIIllI.place(-2, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIlllIlI && lIlIlIIlllIIl) {
                                return;
                            }
                        }
                        if (lIlIlIIlllIII && lIlIlIIllIlll && lIlIlIIllIllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIlIIllIIll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIllIIlI = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIllIIIl = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIllIIII = lIlIIIllIIllI.place(2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIllll = lIlIIIllIIllI.place(-2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIllIlIl = lIlIIIllIIllI.place(3, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIllIlII = lIlIIIllIIllI.place(-3, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIllIlIl && lIlIlIIllIlII) {
                                return;
                            }
                        }
                        if (lIlIlIIllIIll && lIlIlIIllIIlI && lIlIlIIllIIIl && lIlIlIIllIIII && lIlIlIIlIllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIlIIlIllII = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIlIll = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIlIlI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIlIIl = lIlIIIllIIllI.place(2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIlIII = lIlIIIllIIllI.place(-2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIIlll = lIlIIIllIIllI.place(3, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIIllI = lIlIIIllIIllI.place(-3, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIlIlllI = lIlIIIllIIllI.place(4, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIlIllIl = lIlIIIllIIllI.place(-4, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIlIlllI && lIlIlIIlIllIl) {
                                return;
                            }
                        }
                        if (lIlIlIIlIllII && lIlIlIIlIlIll && lIlIlIIlIlIlI && lIlIlIIlIlIIl && lIlIlIIlIlIII && lIlIlIIlIIlll && lIlIlIIlIIllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIlIIlIIIll = lIlIIIllIIllI.place(0, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIlIIIlI = lIlIIIllIIllI.place(1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIlIIIIl = lIlIIIllIIllI.place(-1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIlIIIII = lIlIIIllIIllI.place(2, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIIlllll = lIlIIIllIIllI.place(-2, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIIllllI = lIlIIIllIIllI.place(3, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIIlllIl = lIlIIIllIIllI.place(-3, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIIlllII = lIlIIIllIIllI.place(4, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIlIIIllIll = lIlIIIllIIllI.place(-4, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIlIIlIIlIl = lIlIIIllIIllI.place(5, 0, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIlIIlII = lIlIIIllIIllI.place(-5, 0, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIlIIlIIlIl && lIlIlIIlIIlII) {
                            return;
                        }
                    }
                    if (!lIlIlIIlIIIll || !lIlIlIIlIIIlI || !lIlIlIIlIIIIl || !lIlIlIIlIIIII || !lIlIlIIIlllll || !lIlIlIIIllllI || !lIlIlIIIlllIl || !lIlIlIIIlllII || !lIlIlIIIllIll) break;
                    return;
                }
                case EAST_SOUTH: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIlIIIllIII = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIlIlll = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIlIllI = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIIllIlI = lIlIIIllIIllI.place(2, 0, 0);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIIllIIl = lIlIIIllIIllI.place(0, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIIllIlI && lIlIlIIIllIIl) {
                                return;
                            }
                        }
                        if (lIlIlIIIllIII && lIlIlIIIlIlll && lIlIlIIIlIllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIlIIIlIIll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIlIIlI = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIlIIIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIlIIII = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIllll = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIIlIlIl = lIlIIIllIIllI.place(2, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIIlIlII = lIlIIIllIIllI.place(-1, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIIlIlIl && lIlIlIIIlIlII) {
                                return;
                            }
                        }
                        if (lIlIlIIIlIIll && lIlIlIIIlIIlI && lIlIlIIIlIIIl && lIlIlIIIlIIII && lIlIlIIIIllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIlIIIIllII = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIlIll = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIlIlI = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIlIIl = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIlIII = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIIlll = lIlIIIllIIllI.place(2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIIllI = lIlIIIllIIllI.place(-1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIIIlllI = lIlIIIllIIllI.place(3, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIIIllIl = lIlIIIllIIllI.place(-1, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIIIlllI && lIlIlIIIIllIl) {
                                return;
                            }
                        }
                        if (lIlIlIIIIllII && lIlIlIIIIlIll && lIlIlIIIIlIlI && lIlIlIIIIlIIl && lIlIlIIIIlIII && lIlIlIIIIIlll && lIlIlIIIIIllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIlIIIIIIll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIIIlI = lIlIIIllIIllI.place(1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIIIIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIlIIIIIIII = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllllllll = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllllllI = lIlIIIllIIllI.place(2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllllllIl = lIlIIIllIIllI.place(-1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllllllII = lIlIIIllIIllI.place(3, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllllIll = lIlIIIllIIllI.place(-1, -1, 3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIlIIIIIlIl = lIlIIIllIIllI.place(3, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIlIIIIIlII = lIlIIIllIIllI.place(-2, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIlIIIIIlIl && lIlIlIIIIIlII) {
                                return;
                            }
                        }
                        if (lIlIlIIIIIIll && lIlIlIIIIIIlI && lIlIlIIIIIIIl && lIlIlIIIIIIII && lIlIIllllllll && lIlIIlllllllI && lIlIIllllllIl && lIlIIllllllII && lIlIIlllllIll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIIlllllIII = lIlIIIllIIllI.place(0, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIlll = lIlIIIllIIllI.place(1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIllI = lIlIIIllIIllI.place(1, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIlIl = lIlIIIllIIllI.place(2, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIlII = lIlIIIllIIllI.place(0, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIIll = lIlIIIllIIllI.place(2, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIIlI = lIlIIIllIIllI.place(-1, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIIIl = lIlIIIllIIllI.place(3, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllllIIII = lIlIIIllIIllI.place(-1, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIlllIllll = lIlIIIllIIllI.place(3, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIlllIlllI = lIlIIIllIIllI.place(-2, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIIlllllIlI = lIlIIIllIIllI.place(4, 0, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllllIIl = lIlIIIllIIllI.place(-2, 0, 4);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIlllllIlI && lIlIIlllllIIl) {
                            return;
                        }
                    }
                    if (!lIlIIlllllIII || !lIlIIllllIlll || !lIlIIllllIllI || !lIlIIllllIlIl || !lIlIIllllIlII || !lIlIIllllIIll || !lIlIIllllIIlI || !lIlIIllllIIIl || !lIlIIllllIIII || !lIlIIlllIllll || !lIlIIlllIlllI) break;
                    return;
                }
                case SOUTH_WEST: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIIlllIlIll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIlIlI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIlIIl = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlllIllIl = lIlIIIllIIllI.place(0, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlllIllII = lIlIIIllIIllI.place(-2, 0, 0);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlllIllIl && lIlIIlllIllII) {
                                return;
                            }
                        }
                        if (lIlIIlllIlIll && lIlIIlllIlIlI && lIlIIlllIlIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIIlllIIllI = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIIlIl = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIIlII = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIIIll = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlllIIIlI = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlllIlIII = lIlIIIllIIllI.place(-2, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlllIIlll = lIlIIIllIIllI.place(1, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlllIlIII && lIlIIlllIIlll) {
                                return;
                            }
                        }
                        if (lIlIIlllIIllI && lIlIIlllIIlIl && lIlIIlllIIlII && lIlIIlllIIIll && lIlIIlllIIIlI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIIllIlllll = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIllllI = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlllIl = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlllII = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIllIll = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIllIlI = lIlIIIllIIllI.place(1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIllIIl = lIlIIIllIIllI.place(-2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlllIIIIl = lIlIIIllIIllI.place(-3, 0, -1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlllIIIII = lIlIIIllIIllI.place(1, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlllIIIIl && lIlIIlllIIIII) {
                                return;
                            }
                        }
                        if (lIlIIllIlllll && lIlIIllIllllI && lIlIIllIlllIl && lIlIIllIlllII && lIlIIllIllIll && lIlIIllIllIlI && lIlIIllIllIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIIllIlIllI = lIlIIIllIIllI.place(0, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIlIl = lIlIIIllIIllI.place(-1, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIlII = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIIll = lIlIIIllIIllI.place(0, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIIlI = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIIIl = lIlIIIllIIllI.place(1, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIlIIII = lIlIIIllIIllI.place(-2, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIIllll = lIlIIIllIIllI.place(-3, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIIlllI = lIlIIIllIIllI.place(1, -1, 3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIllIllIII = lIlIIIllIIllI.place(-3, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIllIlIlll = lIlIIIllIIllI.place(2, 0, 3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIllIllIII && lIlIIllIlIlll) {
                                return;
                            }
                        }
                        if (lIlIIllIlIllI && lIlIIllIlIlIl && lIlIIllIlIlII && lIlIIllIlIIll && lIlIIllIlIIlI && lIlIIllIlIIIl && lIlIIllIlIIII && lIlIIllIIllll && lIlIIllIIlllI) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIIllIIlIll = lIlIIIllIIllI.place(0, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIlIlI = lIlIIIllIIllI.place(-1, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIlIIl = lIlIIIllIIllI.place(-1, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIlIII = lIlIIIllIIllI.place(0, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIlll = lIlIIIllIIllI.place(-2, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIllI = lIlIIIllIIllI.place(1, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIlIl = lIlIIIllIIllI.place(-2, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIlII = lIlIIIllIIllI.place(-3, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIIll = lIlIIIllIIllI.place(1, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIIlI = lIlIIIllIIllI.place(-3, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIllIIIIIl = lIlIIIllIIllI.place(2, -1, 3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIIllIIllIl = lIlIIIllIIllI.place(2, 0, 4);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIllIIllII = lIlIIIllIIllI.place(-4, 0, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIllIIllIl && lIlIIllIIllII) {
                            return;
                        }
                    }
                    if (!lIlIIllIIlIll || !lIlIIllIIlIlI || !lIlIIllIIlIIl || !lIlIIllIIlIII || !lIlIIllIIIlll || !lIlIIllIIIllI || !lIlIIllIIIlIl || !lIlIIllIIIlII || !lIlIIllIIIIll || !lIlIIllIIIIlI || !lIlIIllIIIIIl) break;
                    return;
                }
                case WEST_NORTH: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIIlIlllllI = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllllIl = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllllII = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIllIIIIII = lIlIIIllIIllI.place(0, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIllllll = lIlIIIllIIllI.place(-2, 0, 0);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIllIIIIII && lIlIIlIllllll) {
                                return;
                            }
                        }
                        if (lIlIIlIlllllI && lIlIIlIllllIl && lIlIIlIllllII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIIlIlllIIl = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlllIII = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllIlll = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllIllI = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllIlIl = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIlllIll = lIlIIIllIIllI.place(-2, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIlllIlI = lIlIIIllIIllI.place(1, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIlllIll && lIlIIlIlllIlI) {
                                return;
                            }
                        }
                        if (lIlIIlIlllIIl && lIlIIlIlllIII && lIlIIlIllIlll && lIlIIlIllIllI && lIlIIlIllIlIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIIlIllIIlI = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllIIIl = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIllIIII = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIllll = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIlllI = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIllIl = lIlIIIllIIllI.place(-2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIllII = lIlIIIllIIllI.place(1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIllIlII = lIlIIIllIIllI.place(-3, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIllIIll = lIlIIIllIIllI.place(1, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIllIlII && lIlIIlIllIIll) {
                                return;
                            }
                        }
                        if (lIlIIlIllIIlI && lIlIIlIllIIIl && lIlIIlIllIIII && lIlIIlIlIllll && lIlIIlIlIlllI && lIlIIlIlIllIl && lIlIIlIlIllII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIIlIlIlIIl = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIlIII = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIlll = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIllI = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIlIl = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIlII = lIlIIIllIIllI.place(-2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIIll = lIlIIIllIIllI.place(1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIIlI = lIlIIIllIIllI.place(-3, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIlIIIIl = lIlIIIllIIllI.place(1, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIlIlIll = lIlIIIllIIllI.place(-3, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIlIlIlI = lIlIIIllIIllI.place(2, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIlIlIll && lIlIIlIlIlIlI) {
                                return;
                            }
                        }
                        if (lIlIIlIlIlIIl && lIlIIlIlIlIII && lIlIIlIlIIlll && lIlIIlIlIIllI && lIlIIlIlIIlIl && lIlIIlIlIIlII && lIlIIlIlIIIll && lIlIIlIlIIIlI && lIlIIlIlIIIIl) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 5) {
                        boolean lIlIIlIIllllI = lIlIIIllIIllI.place(-1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlllIl = lIlIIIllIIllI.place(-1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlllII = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIllIll = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIllIlI = lIlIIIllIIllI.place(-2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIllIIl = lIlIIIllIIllI.place(-2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIllIII = lIlIIIllIIllI.place(1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlIlll = lIlIIIllIIllI.place(-3, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlIllI = lIlIIIllIIllI.place(1, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlIlIl = lIlIIIllIIllI.place(-3, -1, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlIlII = lIlIIIllIIllI.place(2, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIlIIIII = lIlIIIllIIllI.place(-4, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIIlllll = lIlIIIllIIllI.place(2, 0, -4);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIlIIIII && lIlIIlIIlllll) {
                                return;
                            }
                        }
                        if (lIlIIlIIllllI && lIlIIlIIlllIl && lIlIIlIIlllII && lIlIIlIIllIll && lIlIIlIIllIlI && lIlIIlIIllIIl && lIlIIlIIllIII && lIlIIlIIlIlll && lIlIIlIIlIllI && lIlIIlIIlIlIl && lIlIIlIIlIlII) {
                            return;
                        }
                    }
                }
                case NORTH_EAST: {
                    if (lIlIIIllIIllI.size.get() == 1) {
                        boolean lIlIIlIIlIIIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIlIIII = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIllll = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIIlIIll = lIlIIIllIIllI.place(0, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIIlIIlI = lIlIIIllIIllI.place(2, 0, 0);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIIlIIll && lIlIIlIIlIIlI) {
                                return;
                            }
                        }
                        if (lIlIIlIIlIIIl && lIlIIlIIlIIII && lIlIIlIIIllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 2) {
                        boolean lIlIIlIIIllII = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIlIll = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIlIlI = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIlIIl = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIlIII = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIIIlllI = lIlIIIllIIllI.place(-1, 0, -2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIIIllIl = lIlIIIllIIllI.place(2, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIIIlllI && lIlIIlIIIllIl) {
                                return;
                            }
                        }
                        if (lIlIIlIIIllII && lIlIIlIIIlIll && lIlIIlIIIlIlI && lIlIIlIIIlIIl && lIlIIlIIIlIII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 3) {
                        boolean lIlIIlIIIIlIl = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIIlII = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIIIll = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIIIlI = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIIIIl = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIlIIIIIII = lIlIIIllIIllI.place(2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllllll = lIlIIIllIIllI.place(-1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIlIIIIlll = lIlIIIllIIllI.place(-1, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIlIIIIllI = lIlIIIllIIllI.place(3, 0, 1);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIlIIIIlll && lIlIIlIIIIllI) {
                                return;
                            }
                        }
                        if (lIlIIlIIIIlIl && lIlIIlIIIIlII && lIlIIlIIIIIll && lIlIIlIIIIIlI && lIlIIlIIIIIIl && lIlIIlIIIIIII && lIlIIIlllllll) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() == 4) {
                        boolean lIlIIIlllllII = lIlIIIllIIllI.place(1, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIllllIll = lIlIIIllIIllI.place(1, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIllllIlI = lIlIIIllIIllI.place(0, -1, -1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIllllIIl = lIlIIIllIIllI.place(0, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIllllIII = lIlIIIllIIllI.place(2, -1, 0);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllIlll = lIlIIIllIIllI.place(2, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllIllI = lIlIIIllIIllI.place(-1, -1, -2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllIlIl = lIlIIIllIIllI.place(-1, -1, -3);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllIlII = lIlIIIllIIllI.place(3, -1, 1);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                            boolean lIlIIIllllllI = lIlIIIllIIllI.place(-2, 0, -3);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            boolean lIlIIIlllllIl = lIlIIIllIIllI.place(3, 0, 2);
                            if (lIlIIIllIIllI.return_) {
                                return;
                            }
                            if (lIlIIIllllllI && lIlIIIlllllIl) {
                                return;
                            }
                        }
                        if (lIlIIIlllllII && lIlIIIllllIll && lIlIIIllllIlI && lIlIIIllllIIl && lIlIIIllllIII && lIlIIIlllIlll && lIlIIIlllIllI && lIlIIIlllIlIl && lIlIIIlllIlII) {
                            return;
                        }
                    }
                    if (lIlIIIllIIllI.size.get() != 5) break;
                    boolean lIlIIIlllIIIl = lIlIIIllIIllI.place(1, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIlllIIII = lIlIIIllIIllI.place(1, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIllll = lIlIIIllIIllI.place(0, -1, -1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIlllI = lIlIIIllIIllI.place(0, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIllIl = lIlIIIllIIllI.place(2, -1, 0);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIllII = lIlIIIllIIllI.place(2, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIlIll = lIlIIIllIIllI.place(-1, -1, -2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIlIlI = lIlIIIllIIllI.place(-1, -1, -3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIlIIl = lIlIIIllIIllI.place(3, -1, 1);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIlIII = lIlIIIllIIllI.place(-2, -1, -3);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    boolean lIlIIIllIIlll = lIlIIIllIIllI.place(3, -1, 2);
                    if (lIlIIIllIIllI.return_) {
                        return;
                    }
                    if (lIlIIIllIIllI.sideBlocks.get().booleanValue()) {
                        boolean lIlIIIlllIIll = lIlIIIllIIllI.place(-2, 0, -4);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        boolean lIlIIIlllIIlI = lIlIIIllIIllI.place(4, 0, 2);
                        if (lIlIIIllIIllI.return_) {
                            return;
                        }
                        if (lIlIIIlllIIll && lIlIIIlllIIlI) {
                            return;
                        }
                    }
                    if (!lIlIIIlllIIIl || !lIlIIIlllIIII || !lIlIIIllIllll || !lIlIIIllIlllI || !lIlIIIllIllIl || !lIlIIIllIllII || !lIlIIIllIlIll || !lIlIIIllIlIlI || !lIlIIIllIlIIl || !lIlIIIllIlIII || !lIlIIIllIIlll) break;
                    return;
                }
            }
        }
    }

    public AutoBuild() {
        super(Categories.BedTrap, "auto-build", "Places build that you choose.");
        AutoBuild lIlllIlIlIIII;
        lIlllIlIlIIII.sgGeneral = lIlllIlIlIIII.settings.getDefaultGroup();
        lIlllIlIlIIII.sgHighway = lIlllIlIlIIII.settings.createGroup("Highway");
        lIlllIlIlIIII.buildPlacement = lIlllIlIlIIII.sgGeneral.add(new EnumSetting.Builder().name("structure").description("Which structure gonna build.").defaultValue(Build.Swastika).build());
        lIlllIlIlIIII.cooldown = lIlllIlIlIIII.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("Block per tick.").defaultValue(0).min(0).sliderMax(20).build());
        lIlllIlIlIIII.center = lIlllIlIlIIII.sgGeneral.add(new BoolSetting.Builder().name("center").description("Moves you to the center of the block.").defaultValue(true).build());
        lIlllIlIlIIII.selfToggle = lIlllIlIlIIII.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Toggles when you run out of obsidian.").defaultValue(false).build());
        lIlllIlIlIIII.size = lIlllIlIlIIII.sgHighway.add(new IntSetting.Builder().name("highway-size").description("The size of highway.").defaultValue(3).min(1).sliderMin(1).max(5).sliderMax(5).build());
        lIlllIlIlIIII.sideBlocks = lIlllIlIlIIII.sgHighway.add(new BoolSetting.Builder().name("side-blocks").description("Placing side blocks.").defaultValue(true).build());
        lIlllIlIlIIII.sentMessage = false;
        lIlllIlIlIIII.blockPos = new class_2338.class_2339();
        lIlllIlIlIIII.tickskip = lIlllIlIlIIII.cooldown.get();
    }

    private Direction getDirection(class_1657 lIIlllIIIIIll) {
        double lIIlllIIIIlII = lIIlllIIIIIll.field_6031;
        if (lIIlllIIIIlII == 0.0) {
            return Direction.SOUTH;
        }
        if (lIIlllIIIIlII < 0.0) {
            if ((lIIlllIIIIlII -= (double)(class_3532.method_15384((double)(lIIlllIIIIlII / 360.0)) * 360)) < -180.0) {
                lIIlllIIIIlII = 360.0 + lIIlllIIIIlII;
            }
        } else if ((lIIlllIIIIlII -= (double)(class_3532.method_15357((double)(lIIlllIIIIlII / 360.0)) * 360)) > 180.0) {
            lIIlllIIIIlII = -360.0 + lIIlllIIIIlII;
        }
        if (lIIlllIIIIlII >= 157.5 || lIIlllIIIIlII < -157.5) {
            return Direction.NORTH;
        }
        if (lIIlllIIIIlII >= -157.5 && lIIlllIIIIlII < -112.5) {
            return Direction.NORTH_EAST;
        }
        if (lIIlllIIIIlII >= -112.5 && lIIlllIIIIlII < -67.5) {
            return Direction.EAST;
        }
        if (lIIlllIIIIlII >= -67.5 && lIIlllIIIIlII < -22.5) {
            return Direction.EAST_SOUTH;
        }
        if (lIIlllIIIIlII >= -22.5 && lIIlllIIIIlII <= 0.0 || lIIlllIIIIlII > 0.0 && lIIlllIIIIlII < 22.5) {
            return Direction.SOUTH;
        }
        if (lIIlllIIIIlII >= 22.5 && lIIlllIIIIlII < 67.5) {
            return Direction.SOUTH_WEST;
        }
        if (lIIlllIIIIlII >= 67.5 && lIIlllIIIIlII < 112.5) {
            return Direction.WEST;
        }
        if (lIIlllIIIIlII >= 112.5 && lIIlllIIIIlII < 157.5) {
            return Direction.WEST_NORTH;
        }
        return Direction.SOUTH;
    }

    private static enum Direction {
        SOUTH,
        SOUTH_WEST,
        WEST,
        WEST_NORTH,
        NORTH,
        NORTH_EAST,
        EAST,
        EAST_SOUTH;


        private Direction() {
            Direction llllllllllllllllIllIlIlllIlIlIII;
        }
    }

    private static enum DirectionLite {
        SOUTH,
        WEST,
        NORTH,
        EAST;


        private DirectionLite() {
            DirectionLite llllllllllllllllllllIIlIIIllIIIl;
        }
    }

    public static enum Build {
        NomadHut,
        Penis,
        Swastika,
        Highway;


        private Build() {
            Build lIlIlIIlIIllIII;
        }
    }
}

