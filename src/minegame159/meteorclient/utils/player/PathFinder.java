/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2183$class_2184
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.utils.player;

import java.util.ArrayList;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_2183;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class PathFinder {
    private static final /* synthetic */ int SOUTH;
    private static final /* synthetic */ int QUAD_2;
    private static final /* synthetic */ int NORTH;
    private static final /* synthetic */ int PATH_AHEAD;
    private /* synthetic */ class_1297 target;
    private final /* synthetic */ ArrayList<PathBlock> path;
    private /* synthetic */ PathBlock currentPathBlock;
    private static final /* synthetic */ int QUAD_1;

    public class_2680 getBlockStateAtPos(class_2338 llllllllllllllllIlllIlIIIIllIIll) {
        if (Utils.mc.field_1687 != null) {
            return Utils.mc.field_1687.method_8320(llllllllllllllllIlllIlIIIIllIIll);
        }
        return null;
    }

    @EventHandler
    private void moveEventListener(PlayerMoveEvent llllllllllllllllIlllIlIIIIIIllll) {
        PathFinder llllllllllllllllIlllIlIIIIIlIIII;
        if (llllllllllllllllIlllIlIIIIIlIIII.target != null && Utils.mc.field_1724 != null) {
            if (Utils.mc.field_1724.method_5739(llllllllllllllllIlllIlIIIIIlIIII.target) > 3.0f) {
                if (llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock == null) {
                    llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock = llllllllllllllllIlllIlIIIIIlIIII.getNextPathBlock();
                }
                class_243 class_2432 = new class_243((double)llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock.blockPos.method_10263(), (double)llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock.blockPos.method_10264(), (double)llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock.blockPos.method_10260());
                if (Utils.mc.field_1724.method_19538().method_1022(class_2432) < 0.1) {
                    llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock = llllllllllllllllIlllIlIIIIIlIIII.getNextPathBlock();
                }
                llllllllllllllllIlllIlIIIIIlIIII.lookAtDestination(llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock);
                if (!Utils.mc.field_1690.field_1894.method_1434()) {
                    Utils.mc.field_1690.field_1894.method_23481(true);
                }
            } else {
                if (Utils.mc.field_1690.field_1894.method_1434()) {
                    Utils.mc.field_1690.field_1894.method_23481(false);
                }
                llllllllllllllllIlllIlIIIIIlIIII.path.clear();
                llllllllllllllllIlllIlIIIIIlIIII.currentPathBlock = null;
            }
        }
    }

    public int getDrop(class_2338 llllllllllllllllIlllIlIIIlIlllll) {
        PathFinder llllllllllllllllIlllIlIIIllIIIII;
        int llllllllllllllllIlllIlIIIllIIIIl;
        for (llllllllllllllllIlllIlIIIllIIIIl = 0; !llllllllllllllllIlllIlIIIllIIIII.isSolidFloor(llllllllllllllllIlllIlIIIlIlllll) && llllllllllllllllIlllIlIIIllIIIIl < 3; ++llllllllllllllllIlllIlIIIllIIIIl) {
            llllllllllllllllIlllIlIIIlIlllll = new class_2338(llllllllllllllllIlllIlIIIlIlllll.method_10263(), llllllllllllllllIlllIlIIIlIlllll.method_10264() - 1, llllllllllllllllIlllIlIIIlIlllll.method_10260());
        }
        return llllllllllllllllIlllIlIIIllIIIIl;
    }

    public class_243 getNextStraightPos() {
        class_243 llllllllllllllllIlllIlIIIlIlIlII = new class_243(Utils.mc.field_1724.method_23317(), Utils.mc.field_1724.method_23318(), Utils.mc.field_1724.method_23321());
        double llllllllllllllllIlllIlIIIlIlIIll = 1.0;
        while (llllllllllllllllIlllIlIIIlIlIlII == Utils.mc.field_1724.method_19538()) {
            llllllllllllllllIlllIlIIIlIlIlII = new class_243((double)((int)(Utils.mc.field_1724.method_23317() + llllllllllllllllIlllIlIIIlIlIIll * Math.cos(Math.toRadians(Utils.mc.field_1724.field_6031)))), (double)((int)Utils.mc.field_1724.method_23318()), (double)((int)(Utils.mc.field_1724.method_23321() + llllllllllllllllIlllIlIIIlIlIIll * Math.sin(Math.toRadians(Utils.mc.field_1724.field_6031)))));
            llllllllllllllllIlllIlIIIlIlIIll += 0.1;
        }
        return llllllllllllllllIlllIlIIIlIlIlII;
    }

    public boolean isSolidFloor(class_2338 llllllllllllllllIlllIlIIIIlIIIII) {
        PathFinder llllllllllllllllIlllIlIIIIlIIIIl;
        return llllllllllllllllIlllIlIIIIlIIIIl.isAir(llllllllllllllllIlllIlIIIIlIIIIl.getBlockAtPos(llllllllllllllllIlllIlIIIIlIIIII));
    }

    public int getDirection() {
        PathFinder llllllllllllllllIlllIlIIIIlllIII;
        if (llllllllllllllllIlllIlIIIIlllIII.target == null || Utils.mc.field_1724 == null) {
            return 0;
        }
        class_243 llllllllllllllllIlllIlIIIIlllIlI = llllllllllllllllIlllIlIIIIlllIII.target.method_19538();
        class_243 llllllllllllllllIlllIlIIIIlllIIl = Utils.mc.field_1724.method_19538();
        if (llllllllllllllllIlllIlIIIIlllIlI.field_1352 == llllllllllllllllIlllIlIIIIlllIIl.field_1352 && llllllllllllllllIlllIlIIIIlllIlI.field_1350 > llllllllllllllllIlllIlIIIIlllIIl.field_1350) {
            return 0;
        }
        if (llllllllllllllllIlllIlIIIIlllIlI.field_1352 == llllllllllllllllIlllIlIIIIlllIIl.field_1352 && llllllllllllllllIlllIlIIIIlllIlI.field_1350 < llllllllllllllllIlllIlIIIIlllIIl.field_1350) {
            return 180;
        }
        if (llllllllllllllllIlllIlIIIIlllIlI.field_1352 < llllllllllllllllIlllIlIIIIlllIIl.field_1352) {
            return 1;
        }
        if (llllllllllllllllIlllIlIIIIlllIlI.field_1352 > llllllllllllllllIlllIlIIIIlllIIl.field_1352) {
            return 2;
        }
        return 0;
    }

    public void disable() {
        PathFinder llllllllllllllllIlllIlIIIIIIIllI;
        llllllllllllllllIlllIlIIIIIIIllI.target = null;
        llllllllllllllllIlllIlIIIIIIIllI.path.clear();
        if (Utils.mc.field_1690.field_1894.method_1434()) {
            Utils.mc.field_1690.field_1894.method_23481(false);
        }
        MeteorClient.EVENT_BUS.unsubscribe(llllllllllllllllIlllIlIIIIIIIllI);
    }

    static {
        QUAD_1 = 1;
        PATH_AHEAD = 3;
        SOUTH = 0;
        NORTH = 180;
        QUAD_2 = 2;
    }

    public boolean isAirAbove(class_2338 llllllllllllllllIlllIlIIIlIllIII) {
        PathFinder llllllllllllllllIlllIlIIIlIllIll;
        if (!llllllllllllllllIlllIlIIIlIllIll.getBlockStateAtPos(llllllllllllllllIlllIlIIIlIllIII.method_10263(), llllllllllllllllIlllIlIIIlIllIII.method_10264(), llllllllllllllllIlllIlIIIlIllIII.method_10260()).method_26215()) {
            return false;
        }
        return llllllllllllllllIlllIlIIIlIllIll.getBlockStateAtPos(llllllllllllllllIlllIlIIIlIllIII.method_10263(), llllllllllllllllIlllIlIIIlIllIII.method_10264() + 1, llllllllllllllllIlllIlIIIlIllIII.method_10260()).method_26215();
    }

    public int getYawToTarget() {
        PathFinder llllllllllllllllIlllIlIIIlIIIlII;
        if (llllllllllllllllIlllIlIIIlIIIlII.target == null || Utils.mc.field_1724 == null) {
            return Integer.MAX_VALUE;
        }
        class_243 llllllllllllllllIlllIlIIIlIIlIIl = llllllllllllllllIlllIlIIIlIIIlII.target.method_19538();
        class_243 llllllllllllllllIlllIlIIIlIIlIII = Utils.mc.field_1724.method_19538();
        int llllllllllllllllIlllIlIIIlIIIlll = 0;
        int llllllllllllllllIlllIlIIIlIIIllI = llllllllllllllllIlllIlIIIlIIIlII.getDirection();
        double llllllllllllllllIlllIlIIIlIIIlIl = (llllllllllllllllIlllIlIIIlIIlIIl.field_1350 - llllllllllllllllIlllIlIIIlIIlIII.field_1350) / (llllllllllllllllIlllIlIIIlIIlIIl.field_1352 - llllllllllllllllIlllIlIIIlIIlIII.field_1352);
        if (llllllllllllllllIlllIlIIIlIIIllI == 1) {
            llllllllllllllllIlllIlIIIlIIIlll = (int)(1.5707963267948966 - Math.atan(llllllllllllllllIlllIlIIIlIIIlIl));
        } else if (llllllllllllllllIlllIlIIIlIIIllI == 2) {
            llllllllllllllllIlllIlIIIlIIIlll = (int)(-1.5707963267948966 - Math.atan(llllllllllllllllIlllIlIIIlIIIlIl));
        } else {
            return llllllllllllllllIlllIlIIIlIIIllI;
        }
        return llllllllllllllllIlllIlIIIlIIIlll;
    }

    public PathFinder() {
        PathFinder llllllllllllllllIlllIlIIIlllIIII;
        llllllllllllllllIlllIlIIIlllIIII.path = new ArrayList(3);
    }

    public PathBlock getNextPathBlock() {
        PathFinder llllllllllllllllIlllIlIIIllIlIIl;
        PathBlock llllllllllllllllIlllIlIIIllIlIlI = llllllllllllllllIlllIlIIIllIlIIl.new PathBlock(new class_2338(llllllllllllllllIlllIlIIIllIlIIl.getNextStraightPos()));
        if (llllllllllllllllIlllIlIIIllIlIIl.isSolidFloor(llllllllllllllllIlllIlIIIllIlIlI.blockPos) && llllllllllllllllIlllIlIIIllIlIIl.isAirAbove(llllllllllllllllIlllIlIIIllIlIlI.blockPos)) {
            return llllllllllllllllIlllIlIIIllIlIlI;
        }
        if (!llllllllllllllllIlllIlIIIllIlIIl.isSolidFloor(llllllllllllllllIlllIlIIIllIlIlI.blockPos) && llllllllllllllllIlllIlIIIllIlIIl.isAirAbove(llllllllllllllllIlllIlIIIllIlIlI.blockPos)) {
            int llllllllllllllllIlllIlIIIllIllII = llllllllllllllllIlllIlIIIllIlIIl.getDrop(llllllllllllllllIlllIlIIIllIlIlI.blockPos);
            if (llllllllllllllllIlllIlIIIllIlIIl.getDrop(llllllllllllllllIlllIlIIIllIlIlI.blockPos) < 3) {
                llllllllllllllllIlllIlIIIllIlIlI = llllllllllllllllIlllIlIIIllIlIIl.new PathBlock(new class_2338(llllllllllllllllIlllIlIIIllIlIlI.blockPos.method_10263(), llllllllllllllllIlllIlIIIllIlIlI.blockPos.method_10264() - llllllllllllllllIlllIlIIIllIllII, llllllllllllllllIlllIlIIIllIlIlI.blockPos.method_10260()));
            }
        }
        return llllllllllllllllIlllIlIIIllIlIlI;
    }

    public boolean isAir(class_2248 llllllllllllllllIlllIlIIIIIllIll) {
        return llllllllllllllllIlllIlIIIIIllIll.method_27839(class_2246.field_10124);
    }

    public void lookAtDestination(PathBlock llllllllllllllllIlllIlIIIIIlIIll) {
        if (Utils.mc.field_1724 != null) {
            Utils.mc.field_1724.method_5702(class_2183.class_2184.field_9851, new class_243((double)llllllllllllllllIlllIlIIIIIlIIll.blockPos.method_10263(), (double)((float)llllllllllllllllIlllIlIIIIIlIIll.blockPos.method_10264() + Utils.mc.field_1724.method_5751()), (double)llllllllllllllllIlllIlIIIIIlIIll.blockPos.method_10260()));
        }
    }

    public class_2680 getBlockStateAtPos(int llllllllllllllllIlllIlIIIIlIlIlI, int llllllllllllllllIlllIlIIIIlIlIIl, int llllllllllllllllIlllIlIIIIlIlIII) {
        if (Utils.mc.field_1687 != null) {
            return Utils.mc.field_1687.method_8320(new class_2338(llllllllllllllllIlllIlIIIIlIlIlI, llllllllllllllllIlllIlIIIIlIlIIl, llllllllllllllllIlllIlIIIIlIlIII));
        }
        return null;
    }

    public void initiate(class_1297 llllllllllllllllIlllIlIIIIIIlIlI) {
        PathFinder llllllllllllllllIlllIlIIIIIIlIIl;
        llllllllllllllllIlllIlIIIIIIlIIl.target = llllllllllllllllIlllIlIIIIIIlIlI;
        if (llllllllllllllllIlllIlIIIIIIlIIl.target != null) {
            llllllllllllllllIlllIlIIIIIIlIIl.currentPathBlock = llllllllllllllllIlllIlIIIIIIlIIl.getNextPathBlock();
        }
        MeteorClient.EVENT_BUS.subscribe(llllllllllllllllIlllIlIIIIIIlIIl);
    }

    public class_2248 getBlockAtPos(class_2338 llllllllllllllllIlllIlIIIIlIIlII) {
        if (Utils.mc.field_1687 != null) {
            return Utils.mc.field_1687.method_8320(llllllllllllllllIlllIlIIIIlIIlII).method_26204();
        }
        return null;
    }

    public boolean isWater(class_2248 llllllllllllllllIlllIlIIIIIlIllI) {
        return llllllllllllllllIlllIlIIIIIlIllI.method_27839(class_2246.field_10382);
    }

    public class PathBlock {
        public final /* synthetic */ class_2338 blockPos;
        public final /* synthetic */ class_2248 block;
        public /* synthetic */ double yaw;
        public final /* synthetic */ class_2680 blockState;

        public PathBlock(class_2248 lllllllllllllllllIlllIIlIllllIlI, class_2338 lllllllllllllllllIlllIIlIllllIIl) {
            PathBlock lllllllllllllllllIlllIIlIllllIII;
            lllllllllllllllllIlllIIlIllllIII.block = lllllllllllllllllIlllIIlIllllIlI;
            lllllllllllllllllIlllIIlIllllIII.blockPos = lllllllllllllllllIlllIIlIllllIIl;
            lllllllllllllllllIlllIIlIllllIII.blockState = lllllllllllllllllIlllIIlIllllIII.PathFinder.this.getBlockStateAtPos(lllllllllllllllllIlllIIlIllllIII.blockPos);
        }

        public PathBlock(class_2338 lllllllllllllllllIlllIIlIllIllll) {
            PathBlock lllllllllllllllllIlllIIlIlllIIIl;
            lllllllllllllllllIlllIIlIlllIIIl.blockPos = lllllllllllllllllIlllIIlIllIllll;
            lllllllllllllllllIlllIIlIlllIIIl.block = lllllllllllllllllIlllIIlIlllIIIl.PathFinder.this.getBlockAtPos(lllllllllllllllllIlllIIlIllIllll);
            lllllllllllllllllIlllIIlIlllIIIl.blockState = lllllllllllllllllIlllIIlIlllIIIl.PathFinder.this.getBlockStateAtPos(lllllllllllllllllIlllIIlIlllIIIl.blockPos);
        }

        public PathBlock(class_2248 lllllllllllllllllIlllIIllIIIIIll, class_2338 lllllllllllllllllIlllIIllIIIIlll, class_2680 lllllllllllllllllIlllIIllIIIIllI) {
            PathBlock lllllllllllllllllIlllIIllIIIIlIl;
            lllllllllllllllllIlllIIllIIIIlIl.block = lllllllllllllllllIlllIIllIIIIIll;
            lllllllllllllllllIlllIIllIIIIlIl.blockPos = lllllllllllllllllIlllIIllIIIIlll;
            lllllllllllllllllIlllIIllIIIIlIl.blockState = lllllllllllllllllIlllIIllIIIIllI;
        }
    }
}

