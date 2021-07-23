/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud;

import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;
import net.minecraft.class_2487;
import net.minecraft.class_2514;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class BoundingBox
implements ISerializable<BoundingBox> {
    public double yOffset;
    public double width;
    public double xOffset;
    public double height;
    public AlignmentY y;
    public AlignmentX x = AlignmentX.Left;

    public double alignX(double d) {
        switch (this.x) {
            default: {
                return 0.0;
            }
            case Center: {
                return this.width / 2.0 - d / 2.0;
            }
            case Right: 
        }
        return this.width - d;
    }

    public double getX() {
        switch (this.x) {
            default: {
                return this.xOffset;
            }
            case Center: {
                return (double)Utils.getWindowWidth() / 2.0 - this.width / 2.0 + this.xOffset;
            }
            case Right: 
        }
        return (double)Utils.getWindowWidth() - this.width + this.xOffset;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("x", this.x.name());
        class_24872.method_10582("y", this.y.name());
        class_24872.method_10549("xOffset", this.xOffset);
        class_24872.method_10549("yOffset", this.yOffset);
        return class_24872;
    }

    public void setY(int n) {
        this.addPos(0.0, (double)n - this.getY());
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public double getY() {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$render$AlignmentY[this.y.ordinal()]) {
            default: {
                return this.yOffset;
            }
            case 2: {
                return (double)Utils.getWindowHeight() / 2.0 - this.height / 2.0 + this.yOffset;
            }
            case 3: 
        }
        return (double)Utils.getWindowHeight() - this.height + this.yOffset;
    }

    public void addPos(double d, double d2) {
        double d3;
        double d4;
        this.xOffset += d;
        this.yOffset += d2;
        double d5 = this.getX();
        double d6 = this.getY();
        switch (this.x) {
            case Left: {
                d4 = (double)Utils.getWindowWidth() / 3.0;
                if (!(d5 >= d4 - this.width / 2.0)) break;
                this.x = AlignmentX.Center;
                this.xOffset = -d4 / 2.0 + d5 - d4 + this.width / 2.0;
                break;
            }
            case Center: {
                d4 = (double)Utils.getWindowWidth() / 3.0;
                d3 = (double)Utils.getWindowWidth() / 3.0 * 2.0;
                if (d5 > d3 - this.width / 2.0) {
                    this.x = AlignmentX.Right;
                    this.xOffset = -(d4 - this.width) + (d4 - ((double)Utils.getWindowWidth() - d5));
                    break;
                }
                if (!(d5 < d4 - this.width / 2.0)) break;
                this.x = AlignmentX.Left;
                this.xOffset = d5;
                break;
            }
            case Right: {
                d4 = (double)Utils.getWindowWidth() / 3.0;
                d3 = (double)Utils.getWindowWidth() / 3.0 * 2.0;
                if (!(d5 <= d3 - this.width / 2.0)) break;
                this.x = AlignmentX.Center;
                this.xOffset = -d4 / 2.0 + d5 - d4 + this.width / 2.0;
                break;
            }
        }
        if (this.x == AlignmentX.Left && this.xOffset < 0.0) {
            this.xOffset = 0.0;
        } else if (this.x == AlignmentX.Right && this.xOffset > 0.0) {
            this.xOffset = 0.0;
        }
        switch (1.$SwitchMap$minegame159$meteorclient$utils$render$AlignmentY[this.y.ordinal()]) {
            case 1: {
                d4 = (double)Utils.getWindowHeight() / 3.0;
                if (!(d6 >= d4 - this.height / 2.0)) break;
                this.y = AlignmentY.Center;
                this.yOffset = -d4 / 2.0 + d6 - d4 + this.height / 2.0;
                break;
            }
            case 2: {
                d4 = (double)Utils.getWindowHeight() / 3.0;
                d3 = (double)Utils.getWindowHeight() / 3.0 * 2.0;
                if (d6 > d3 - this.height / 2.0) {
                    this.y = AlignmentY.Bottom;
                    this.yOffset = -(d4 - this.height) + (d4 - ((double)Utils.getWindowHeight() - d6));
                    break;
                }
                if (!(d6 < d4 - this.height / 2.0)) break;
                this.y = AlignmentY.Top;
                this.yOffset = d6;
                break;
            }
            case 3: {
                d4 = (double)Utils.getWindowHeight() / 3.0;
                d3 = (double)Utils.getWindowHeight() / 3.0 * 2.0;
                if (!(d6 <= d3 - this.height / 2.0)) break;
                this.y = AlignmentY.Center;
                this.yOffset = -d4 / 2.0 + d6 - d4 + this.height / 2.0;
                break;
            }
        }
        if (this.y == AlignmentY.Top && this.yOffset < 0.0) {
            this.yOffset = 0.0;
        } else if (this.y == AlignmentY.Bottom && this.yOffset > 0.0) {
            this.yOffset = 0.0;
        }
    }

    @Override
    public BoundingBox fromTag(class_2487 class_24872) {
        this.x = AlignmentX.valueOf(class_24872.method_10558("x"));
        this.y = AlignmentY.valueOf(class_24872.method_10558("y"));
        this.xOffset = ((class_2514)class_24872.method_10580("xOffset")).method_10697();
        this.yOffset = ((class_2514)class_24872.method_10580("yOffset")).method_10697();
        return this;
    }

    public BoundingBox() {
        this.y = AlignmentY.Top;
    }

    public boolean isOver(double d, double d2) {
        double d3 = this.getX();
        double d4 = this.getY();
        return d >= d3 && d <= d3 + this.width && d2 >= d4 && d2 <= d4 + this.height;
    }

    public void setSize(double d, double d2) {
        this.width = d;
        this.height = d2;
    }

    public void setX(int n) {
        this.addPos((double)n - this.getX(), 0.0);
    }
}

