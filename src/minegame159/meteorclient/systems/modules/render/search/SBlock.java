/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.search;

import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.search.SBlockData;
import minegame159.meteorclient.systems.modules.render.search.SGroup;
import minegame159.meteorclient.systems.modules.render.search.Search;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;

public class SBlock {
    public static final int BO_RI;
    private class_2680 state;
    public static final int BO_FO;
    public static final int BA;
    public static final int TO_FO;
    public static final int LE;
    public static final int BO;
    public static final int BA_RI;
    public boolean loaded = true;
    public int neighbours;
    public static final int FO_RI;
    public static final int FO;
    public static final int RI;
    private static final class_2338.class_2339 blockPos;
    public final int y;
    public static final int[] SIDES;
    public static final int FO_LE;
    public static final int TO;
    public static final int BO_BA;
    public static final int TO_RI;
    public static final int TO_LE;
    public static final int TO_BA;
    public final int z;
    public final int x;
    public SGroup group;
    public static final int BO_LE;
    private static final Search search;
    public static final int BA_LE;

    public SBlock(int n, int n2, int n3) {
        this.x = n;
        this.y = n2;
        this.z = n3;
    }

    public void render() {
        double d = this.x;
        double d2 = this.y;
        double d3 = this.z;
        double d4 = this.x + 1;
        double d5 = this.y + 1;
        double d6 = this.z + 1;
        class_265 class_2652 = this.state.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        if (!class_2652.method_1110()) {
            d = (double)this.x + class_2652.method_1091(class_2350.class_2351.field_11048);
            d2 = (double)this.y + class_2652.method_1091(class_2350.class_2351.field_11052);
            d3 = (double)this.z + class_2652.method_1091(class_2350.class_2351.field_11051);
            d4 = (double)this.x + class_2652.method_1105(class_2350.class_2351.field_11048);
            d5 = (double)this.y + class_2652.method_1105(class_2350.class_2351.field_11052);
            d6 = (double)this.z + class_2652.method_1105(class_2350.class_2351.field_11051);
        }
        SBlockData sBlockData = search.getBlockData(this.state.method_26204());
        ShapeMode shapeMode = sBlockData.shapeMode;
        SettingColor settingColor = sBlockData.lineColor;
        SettingColor settingColor2 = sBlockData.sideColor;
        if (this.neighbours == 0) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d, d2, d3, d4, d5, d6, settingColor2, settingColor, shapeMode, 0);
        } else {
            if (shapeMode != ShapeMode.Sides) {
                if ((this.neighbours & 0x80) != 128 && (this.neighbours & 0x20) != 32 || (this.neighbours & 0x80) == 128 && (this.neighbours & 0x20) == 32 && (this.neighbours & 0x40) != 64) {
                    Renderer.LINES.line(d, d2, d3, d, d5, d3, settingColor);
                }
                if ((this.neighbours & 0x80) != 128 && (this.neighbours & 2) != 2 || (this.neighbours & 0x80) == 128 && (this.neighbours & 2) == 2 && (this.neighbours & 0x100) != 256) {
                    Renderer.LINES.line(d, d2, d6, d, d5, d6, settingColor);
                }
                if ((this.neighbours & 8) != 8 && (this.neighbours & 0x20) != 32 || (this.neighbours & 8) == 8 && (this.neighbours & 0x20) == 32 && (this.neighbours & 0x10) != 16) {
                    Renderer.LINES.line(d4, d2, d3, d4, d5, d3, settingColor);
                }
                if ((this.neighbours & 8) != 8 && (this.neighbours & 2) != 2 || (this.neighbours & 8) == 8 && (this.neighbours & 2) == 2 && (this.neighbours & 4) != 4) {
                    Renderer.LINES.line(d4, d2, d6, d4, d5, d6, settingColor);
                }
                if ((this.neighbours & 0x20) != 32 && (this.neighbours & 0x4000) != 16384 || (this.neighbours & 0x20) != 32 && (this.neighbours & 0x10000) == 65536) {
                    Renderer.LINES.line(d, d2, d3, d4, d2, d3, settingColor);
                }
                if ((this.neighbours & 2) != 2 && (this.neighbours & 0x4000) != 16384 || (this.neighbours & 2) != 2 && (this.neighbours & 0x8000) == 32768) {
                    Renderer.LINES.line(d, d2, d6, d4, d2, d6, settingColor);
                }
                if ((this.neighbours & 0x20) != 32 && (this.neighbours & 0x200) != 512 || (this.neighbours & 0x20) != 32 && (this.neighbours & 0x800) == 2048) {
                    Renderer.LINES.line(d, d5, d3, d4, d5, d3, settingColor);
                }
                if ((this.neighbours & 2) != 2 && (this.neighbours & 0x200) != 512 || (this.neighbours & 2) != 2 && (this.neighbours & 0x400) == 1024) {
                    Renderer.LINES.line(d, d5, d6, d4, d5, d6, settingColor);
                }
                if ((this.neighbours & 0x80) != 128 && (this.neighbours & 0x4000) != 16384 || (this.neighbours & 0x80) != 128 && (this.neighbours & 0x40000) == 262144) {
                    Renderer.LINES.line(d, d2, d3, d, d2, d6, settingColor);
                }
                if ((this.neighbours & 8) != 8 && (this.neighbours & 0x4000) != 16384 || (this.neighbours & 8) != 8 && (this.neighbours & 0x20000) == 131072) {
                    Renderer.LINES.line(d4, d2, d3, d4, d2, d6, settingColor);
                }
                if ((this.neighbours & 0x80) != 128 && (this.neighbours & 0x200) != 512 || (this.neighbours & 0x80) != 128 && (this.neighbours & 0x2000) == 8192) {
                    Renderer.LINES.line(d, d5, d3, d, d5, d6, settingColor);
                }
                if ((this.neighbours & 8) != 8 && (this.neighbours & 0x200) != 512 || (this.neighbours & 8) != 8 && (this.neighbours & 0x1000) == 4096) {
                    Renderer.LINES.line(d4, d5, d3, d4, d5, d6, settingColor);
                }
            }
            if (shapeMode != ShapeMode.Lines) {
                if ((this.neighbours & 0x4000) != 16384) {
                    Renderer.NORMAL.horizontalQuad(d, d3, d4, d6, d2, settingColor2);
                }
                if ((this.neighbours & 0x200) != 512) {
                    Renderer.NORMAL.horizontalQuad(d, d3, d4, d6, d5, settingColor2);
                }
                if ((this.neighbours & 2) != 2) {
                    Renderer.NORMAL.verticalQuad(d, d2, d6, d4, d5, d6, settingColor2);
                }
                if ((this.neighbours & 0x20) != 32) {
                    Renderer.NORMAL.verticalQuad(d, d2, d3, d4, d5, d3, settingColor2);
                }
                if ((this.neighbours & 8) != 8) {
                    Renderer.NORMAL.verticalQuad(d4, d2, d3, d4, d5, d6, settingColor2);
                }
                if ((this.neighbours & 0x80) != 128) {
                    Renderer.NORMAL.verticalQuad(d, d2, d3, d, d5, d6, settingColor2);
                }
            }
        }
    }

    public void update() {
        this.state = Utils.mc.field_1687.method_8320((class_2338)blockPos.method_10103(this.x, this.y, this.z));
        this.neighbours = 0;
        if (this.isNeighbour(class_2350.field_11035)) {
            this.neighbours |= 2;
        }
        if (this.isNeighbourDiagonal(1.0, 0.0, 1.0)) {
            this.neighbours |= 4;
        }
        if (this.isNeighbour(class_2350.field_11034)) {
            this.neighbours |= 8;
        }
        if (this.isNeighbourDiagonal(1.0, 0.0, -1.0)) {
            this.neighbours |= 0x10;
        }
        if (this.isNeighbour(class_2350.field_11043)) {
            this.neighbours |= 0x20;
        }
        if (this.isNeighbourDiagonal(-1.0, 0.0, -1.0)) {
            this.neighbours |= 0x40;
        }
        if (this.isNeighbour(class_2350.field_11039)) {
            this.neighbours |= 0x80;
        }
        if (this.isNeighbourDiagonal(-1.0, 0.0, 1.0)) {
            this.neighbours |= 0x100;
        }
        if (this.isNeighbour(class_2350.field_11036)) {
            this.neighbours |= 0x200;
        }
        if (this.isNeighbourDiagonal(0.0, 1.0, 1.0)) {
            this.neighbours |= 0x400;
        }
        if (this.isNeighbourDiagonal(0.0, 1.0, -1.0)) {
            this.neighbours |= 0x800;
        }
        if (this.isNeighbourDiagonal(1.0, 1.0, 0.0)) {
            this.neighbours |= 0x1000;
        }
        if (this.isNeighbourDiagonal(-1.0, 1.0, 0.0)) {
            this.neighbours |= 0x2000;
        }
        if (this.isNeighbour(class_2350.field_11033)) {
            this.neighbours |= 0x4000;
        }
        if (this.isNeighbourDiagonal(0.0, -1.0, 1.0)) {
            this.neighbours |= 0x8000;
        }
        if (this.isNeighbourDiagonal(0.0, -1.0, -1.0)) {
            this.neighbours |= 0x10000;
        }
        if (this.isNeighbourDiagonal(1.0, -1.0, 0.0)) {
            this.neighbours |= 0x20000;
        }
        if (this.isNeighbourDiagonal(-1.0, -1.0, 0.0)) {
            this.neighbours |= 0x40000;
        }
        if (this.group == null) {
            this.assignGroup();
        }
    }

    static {
        TO_FO = 1024;
        LE = 128;
        BO_RI = 131072;
        FO_RI = 4;
        BO_FO = 32768;
        BA_RI = 16;
        FO_LE = 256;
        TO = 512;
        FO = 2;
        BA = 32;
        BO = 16384;
        BO_BA = 65536;
        RI = 8;
        BA_LE = 64;
        BO_LE = 262144;
        TO_BA = 2048;
        TO_RI = 4096;
        TO_LE = 8192;
        blockPos = new class_2338.class_2339();
        search = Modules.get().get(Search.class);
        SIDES = new int[]{2, 32, 128, 8, 512, 16384};
    }

    public static long getKey(int n, int n2, int n3) {
        return (long)n2 << 16 | (long)(n3 & 0xF) << 8 | (long)(n & 0xF);
    }

    public SBlock getSideBlock(int n) {
        switch (n) {
            case 2: {
                return search.getBlock(this.x, this.y, this.z + 1);
            }
            case 32: {
                return search.getBlock(this.x, this.y, this.z - 1);
            }
            case 128: {
                return search.getBlock(this.x - 1, this.y, this.z);
            }
            case 8: {
                return search.getBlock(this.x + 1, this.y, this.z);
            }
            case 512: {
                return search.getBlock(this.x, this.y + 1, this.z);
            }
            case 16384: {
                return search.getBlock(this.x, this.y - 1, this.z);
            }
        }
        return null;
    }

    public static long getKey(class_2338 class_23382) {
        return SBlock.getKey(class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260());
    }

    private boolean isNeighbour(class_2350 class_23502) {
        blockPos.method_10103(this.x + class_23502.method_10148(), this.y + class_23502.method_10164(), this.z + class_23502.method_10165());
        class_2680 class_26802 = Utils.mc.field_1687.method_8320((class_2338)blockPos);
        if (class_26802.method_26204() != this.state.method_26204()) {
            return false;
        }
        class_265 class_2652 = this.state.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        class_265 class_2654 = class_26802.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        switch (1.$SwitchMap$net$minecraft$util$math$Direction[class_23502.ordinal()]) {
            case 1: {
                if (class_2652.method_1105(class_2350.class_2351.field_11051) != 1.0 || class_2654.method_1091(class_2350.class_2351.field_11051) != 0.0) break;
                return true;
            }
            case 2: {
                if (class_2652.method_1091(class_2350.class_2351.field_11051) != 0.0 || class_2654.method_1105(class_2350.class_2351.field_11051) != 1.0) break;
                return true;
            }
            case 3: {
                if (class_2652.method_1105(class_2350.class_2351.field_11048) != 1.0 || class_2654.method_1091(class_2350.class_2351.field_11048) != 0.0) break;
                return true;
            }
            case 4: {
                if (class_2652.method_1091(class_2350.class_2351.field_11048) != 0.0 || class_2654.method_1105(class_2350.class_2351.field_11048) != 1.0) break;
                return true;
            }
            case 5: {
                if (class_2652.method_1105(class_2350.class_2351.field_11052) != 1.0 || class_2654.method_1091(class_2350.class_2351.field_11052) != 0.0) break;
                return true;
            }
            case 6: {
                if (class_2652.method_1091(class_2350.class_2351.field_11052) != 0.0 || class_2654.method_1105(class_2350.class_2351.field_11052) != 1.0) break;
                return true;
            }
        }
        return false;
    }

    private boolean isNeighbourDiagonal(double d, double d2, double d3) {
        blockPos.method_10102((double)this.x + d, (double)this.y + d2, (double)this.z + d3);
        return this.state.method_26204() == Utils.mc.field_1687.method_8320((class_2338)blockPos).method_26204();
    }

    private void assignGroup() {
        SGroup sGroup = null;
        for (int n : SIDES) {
            SBlock sBlock;
            if ((this.neighbours & n) != n || (sBlock = this.getSideBlock(n)) == null || sBlock.group == null) continue;
            if (sGroup == null) {
                sGroup = sBlock.group;
                continue;
            }
            if (sGroup == sBlock.group) continue;
            sGroup.merge(sBlock.group);
            if (1 < 4) continue;
            return;
        }
        if (sGroup == null) {
            sGroup = search.newGroup(this.state.method_26204());
        }
        sGroup.add(this);
    }
}

