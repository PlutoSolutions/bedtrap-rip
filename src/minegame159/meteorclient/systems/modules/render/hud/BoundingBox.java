/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2514
 */
package minegame159.meteorclient.systems.modules.render.hud;

import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;
import net.minecraft.class_2487;
import net.minecraft.class_2514;

public class BoundingBox
implements ISerializable<BoundingBox> {
    public /* synthetic */ double yOffset;
    public /* synthetic */ double width;
    public /* synthetic */ double xOffset;
    public /* synthetic */ double height;
    public /* synthetic */ AlignmentY y;
    public /* synthetic */ AlignmentX x;

    public double alignX(double lllllllllllllllllIIIllIIIIllllII) {
        BoundingBox lllllllllllllllllIIIllIIIIlllIll;
        switch (lllllllllllllllllIIIllIIIIlllIll.x) {
            default: {
                return 0.0;
            }
            case Center: {
                return lllllllllllllllllIIIllIIIIlllIll.width / 2.0 - lllllllllllllllllIIIllIIIIllllII / 2.0;
            }
            case Right: 
        }
        return lllllllllllllllllIIIllIIIIlllIll.width - lllllllllllllllllIIIllIIIIllllII;
    }

    public double getX() {
        BoundingBox lllllllllllllllllIIIllIIIIIIIllI;
        switch (lllllllllllllllllIIIllIIIIIIIllI.x) {
            default: {
                return lllllllllllllllllIIIllIIIIIIIllI.xOffset;
            }
            case Center: {
                return (double)Utils.getWindowWidth() / 2.0 - lllllllllllllllllIIIllIIIIIIIllI.width / 2.0 + lllllllllllllllllIIIllIIIIIIIllI.xOffset;
            }
            case Right: 
        }
        return (double)Utils.getWindowWidth() - lllllllllllllllllIIIllIIIIIIIllI.width + lllllllllllllllllIIIllIIIIIIIllI.xOffset;
    }

    @Override
    public class_2487 toTag() {
        BoundingBox lllllllllllllllllIIIlIlllllIlllI;
        class_2487 lllllllllllllllllIIIlIlllllIllll = new class_2487();
        lllllllllllllllllIIIlIlllllIllll.method_10582("x", lllllllllllllllllIIIlIlllllIlllI.x.name());
        lllllllllllllllllIIIlIlllllIllll.method_10582("y", lllllllllllllllllIIIlIlllllIlllI.y.name());
        lllllllllllllllllIIIlIlllllIllll.method_10549("xOffset", lllllllllllllllllIIIlIlllllIlllI.xOffset);
        lllllllllllllllllIIIlIlllllIllll.method_10549("yOffset", lllllllllllllllllIIIlIlllllIlllI.yOffset);
        return lllllllllllllllllIIIlIlllllIllll;
    }

    public void setY(int lllllllllllllllllIIIllIIIIllIIII) {
        BoundingBox lllllllllllllllllIIIllIIIIlIllll;
        lllllllllllllllllIIIllIIIIlIllll.addPos(0.0, (double)lllllllllllllllllIIIllIIIIllIIII - lllllllllllllllllIIIllIIIIlIllll.getY());
    }

    public double getY() {
        BoundingBox lllllllllllllllllIIIllIIIIIIIIlI;
        switch (lllllllllllllllllIIIllIIIIIIIIlI.y) {
            default: {
                return lllllllllllllllllIIIllIIIIIIIIlI.yOffset;
            }
            case Center: {
                return (double)Utils.getWindowHeight() / 2.0 - lllllllllllllllllIIIllIIIIIIIIlI.height / 2.0 + lllllllllllllllllIIIllIIIIIIIIlI.yOffset;
            }
            case Bottom: 
        }
        return (double)Utils.getWindowHeight() - lllllllllllllllllIIIllIIIIIIIIlI.height + lllllllllllllllllIIIllIIIIIIIIlI.yOffset;
    }

    public void addPos(double lllllllllllllllllIIIllIIIIIlIllI, double lllllllllllllllllIIIllIIIIIllIlI) {
        BoundingBox lllllllllllllllllIIIllIIIIIlllII;
        lllllllllllllllllIIIllIIIIIlllII.xOffset += lllllllllllllllllIIIllIIIIIlIllI;
        lllllllllllllllllIIIllIIIIIlllII.yOffset += lllllllllllllllllIIIllIIIIIllIlI;
        double lllllllllllllllllIIIllIIIIIllIIl = lllllllllllllllllIIIllIIIIIlllII.getX();
        double lllllllllllllllllIIIllIIIIIllIII = lllllllllllllllllIIIllIIIIIlllII.getY();
        switch (lllllllllllllllllIIIllIIIIIlllII.x) {
            case Left: {
                double lllllllllllllllllIIIllIIIIlIIllI = (double)Utils.getWindowWidth() / 3.0;
                if (!(lllllllllllllllllIIIllIIIIIllIIl >= lllllllllllllllllIIIllIIIIlIIllI - lllllllllllllllllIIIllIIIIIlllII.width / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.x = AlignmentX.Center;
                lllllllllllllllllIIIllIIIIIlllII.xOffset = -lllllllllllllllllIIIllIIIIlIIllI / 2.0 + lllllllllllllllllIIIllIIIIIllIIl - lllllllllllllllllIIIllIIIIlIIllI + lllllllllllllllllIIIllIIIIIlllII.width / 2.0;
                break;
            }
            case Center: {
                double lllllllllllllllllIIIllIIIIlIIlIl = (double)Utils.getWindowWidth() / 3.0;
                double lllllllllllllllllIIIllIIIIlIIlII = (double)Utils.getWindowWidth() / 3.0 * 2.0;
                if (lllllllllllllllllIIIllIIIIIllIIl > lllllllllllllllllIIIllIIIIlIIlII - lllllllllllllllllIIIllIIIIIlllII.width / 2.0) {
                    lllllllllllllllllIIIllIIIIIlllII.x = AlignmentX.Right;
                    lllllllllllllllllIIIllIIIIIlllII.xOffset = -(lllllllllllllllllIIIllIIIIlIIlIl - lllllllllllllllllIIIllIIIIIlllII.width) + (lllllllllllllllllIIIllIIIIlIIlIl - ((double)Utils.getWindowWidth() - lllllllllllllllllIIIllIIIIIllIIl));
                    break;
                }
                if (!(lllllllllllllllllIIIllIIIIIllIIl < lllllllllllllllllIIIllIIIIlIIlIl - lllllllllllllllllIIIllIIIIIlllII.width / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.x = AlignmentX.Left;
                lllllllllllllllllIIIllIIIIIlllII.xOffset = lllllllllllllllllIIIllIIIIIllIIl;
                break;
            }
            case Right: {
                double lllllllllllllllllIIIllIIIIlIIIll = (double)Utils.getWindowWidth() / 3.0;
                double lllllllllllllllllIIIllIIIIlIIIlI = (double)Utils.getWindowWidth() / 3.0 * 2.0;
                if (!(lllllllllllllllllIIIllIIIIIllIIl <= lllllllllllllllllIIIllIIIIlIIIlI - lllllllllllllllllIIIllIIIIIlllII.width / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.x = AlignmentX.Center;
                lllllllllllllllllIIIllIIIIIlllII.xOffset = -lllllllllllllllllIIIllIIIIlIIIll / 2.0 + lllllllllllllllllIIIllIIIIIllIIl - lllllllllllllllllIIIllIIIIlIIIll + lllllllllllllllllIIIllIIIIIlllII.width / 2.0;
                break;
            }
        }
        if (lllllllllllllllllIIIllIIIIIlllII.x == AlignmentX.Left && lllllllllllllllllIIIllIIIIIlllII.xOffset < 0.0) {
            lllllllllllllllllIIIllIIIIIlllII.xOffset = 0.0;
        } else if (lllllllllllllllllIIIllIIIIIlllII.x == AlignmentX.Right && lllllllllllllllllIIIllIIIIIlllII.xOffset > 0.0) {
            lllllllllllllllllIIIllIIIIIlllII.xOffset = 0.0;
        }
        switch (lllllllllllllllllIIIllIIIIIlllII.y) {
            case Top: {
                double lllllllllllllllllIIIllIIIIlIIIIl = (double)Utils.getWindowHeight() / 3.0;
                if (!(lllllllllllllllllIIIllIIIIIllIII >= lllllllllllllllllIIIllIIIIlIIIIl - lllllllllllllllllIIIllIIIIIlllII.height / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.y = AlignmentY.Center;
                lllllllllllllllllIIIllIIIIIlllII.yOffset = -lllllllllllllllllIIIllIIIIlIIIIl / 2.0 + lllllllllllllllllIIIllIIIIIllIII - lllllllllllllllllIIIllIIIIlIIIIl + lllllllllllllllllIIIllIIIIIlllII.height / 2.0;
                break;
            }
            case Center: {
                double lllllllllllllllllIIIllIIIIlIIIII = (double)Utils.getWindowHeight() / 3.0;
                double lllllllllllllllllIIIllIIIIIlllll = (double)Utils.getWindowHeight() / 3.0 * 2.0;
                if (lllllllllllllllllIIIllIIIIIllIII > lllllllllllllllllIIIllIIIIIlllll - lllllllllllllllllIIIllIIIIIlllII.height / 2.0) {
                    lllllllllllllllllIIIllIIIIIlllII.y = AlignmentY.Bottom;
                    lllllllllllllllllIIIllIIIIIlllII.yOffset = -(lllllllllllllllllIIIllIIIIlIIIII - lllllllllllllllllIIIllIIIIIlllII.height) + (lllllllllllllllllIIIllIIIIlIIIII - ((double)Utils.getWindowHeight() - lllllllllllllllllIIIllIIIIIllIII));
                    break;
                }
                if (!(lllllllllllllllllIIIllIIIIIllIII < lllllllllllllllllIIIllIIIIlIIIII - lllllllllllllllllIIIllIIIIIlllII.height / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.y = AlignmentY.Top;
                lllllllllllllllllIIIllIIIIIlllII.yOffset = lllllllllllllllllIIIllIIIIIllIII;
                break;
            }
            case Bottom: {
                double lllllllllllllllllIIIllIIIIIllllI = (double)Utils.getWindowHeight() / 3.0;
                double lllllllllllllllllIIIllIIIIIlllIl = (double)Utils.getWindowHeight() / 3.0 * 2.0;
                if (!(lllllllllllllllllIIIllIIIIIllIII <= lllllllllllllllllIIIllIIIIIlllIl - lllllllllllllllllIIIllIIIIIlllII.height / 2.0)) break;
                lllllllllllllllllIIIllIIIIIlllII.y = AlignmentY.Center;
                lllllllllllllllllIIIllIIIIIlllII.yOffset = -lllllllllllllllllIIIllIIIIIllllI / 2.0 + lllllllllllllllllIIIllIIIIIllIII - lllllllllllllllllIIIllIIIIIllllI + lllllllllllllllllIIIllIIIIIlllII.height / 2.0;
                break;
            }
        }
        if (lllllllllllllllllIIIllIIIIIlllII.y == AlignmentY.Top && lllllllllllllllllIIIllIIIIIlllII.yOffset < 0.0) {
            lllllllllllllllllIIIllIIIIIlllII.yOffset = 0.0;
        } else if (lllllllllllllllllIIIllIIIIIlllII.y == AlignmentY.Bottom && lllllllllllllllllIIIllIIIIIlllII.yOffset > 0.0) {
            lllllllllllllllllIIIllIIIIIlllII.yOffset = 0.0;
        }
    }

    @Override
    public BoundingBox fromTag(class_2487 lllllllllllllllllIIIlIlllllIlIIl) {
        BoundingBox lllllllllllllllllIIIlIlllllIlIlI;
        lllllllllllllllllIIIlIlllllIlIlI.x = AlignmentX.valueOf(lllllllllllllllllIIIlIlllllIlIIl.method_10558("x"));
        lllllllllllllllllIIIlIlllllIlIlI.y = AlignmentY.valueOf(lllllllllllllllllIIIlIlllllIlIIl.method_10558("y"));
        lllllllllllllllllIIIlIlllllIlIlI.xOffset = ((class_2514)lllllllllllllllllIIIlIlllllIlIIl.method_10580("xOffset")).method_10697();
        lllllllllllllllllIIIlIlllllIlIlI.yOffset = ((class_2514)lllllllllllllllllIIIlIlllllIlIIl.method_10580("yOffset")).method_10697();
        return lllllllllllllllllIIIlIlllllIlIlI;
    }

    public BoundingBox() {
        BoundingBox lllllllllllllllllIIIllIIIlIIIIII;
        lllllllllllllllllIIIllIIIlIIIIII.x = AlignmentX.Left;
        lllllllllllllllllIIIllIIIlIIIIII.y = AlignmentY.Top;
    }

    public boolean isOver(double lllllllllllllllllIIIlIlllllllIll, double lllllllllllllllllIIIlIlllllllIlI) {
        BoundingBox lllllllllllllllllIIIlIllllllIlll;
        double lllllllllllllllllIIIlIlllllllIIl = lllllllllllllllllIIIlIllllllIlll.getX();
        double lllllllllllllllllIIIlIlllllllIII = lllllllllllllllllIIIlIllllllIlll.getY();
        return lllllllllllllllllIIIlIlllllllIll >= lllllllllllllllllIIIlIlllllllIIl && lllllllllllllllllIIIlIlllllllIll <= lllllllllllllllllIIIlIlllllllIIl + lllllllllllllllllIIIlIllllllIlll.width && lllllllllllllllllIIIlIlllllllIlI >= lllllllllllllllllIIIlIlllllllIII && lllllllllllllllllIIIlIlllllllIlI <= lllllllllllllllllIIIlIlllllllIII + lllllllllllllllllIIIlIllllllIlll.height;
    }

    public void setSize(double lllllllllllllllllIIIllIIIIIIllII, double lllllllllllllllllIIIllIIIIIIlIll) {
        lllllllllllllllllIIIllIIIIIIllIl.width = lllllllllllllllllIIIllIIIIIIllII;
        lllllllllllllllllIIIllIIIIIIllIl.height = lllllllllllllllllIIIllIIIIIIlIll;
    }

    public void setX(int lllllllllllllllllIIIllIIIIllIlII) {
        BoundingBox lllllllllllllllllIIIllIIIIllIlll;
        lllllllllllllllllIIIllIIIIllIlll.addPos((double)lllllllllllllllllIIIllIIIIllIlII - lllllllllllllllllIIIllIIIIllIlll.getX(), 0.0);
    }
}

