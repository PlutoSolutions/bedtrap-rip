/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
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
    public static final /* synthetic */ int BO_RI;
    private /* synthetic */ class_2680 state;
    public static final /* synthetic */ int BO_FO;
    public static final /* synthetic */ int BA;
    public static final /* synthetic */ int TO_FO;
    public static final /* synthetic */ int LE;
    public static final /* synthetic */ int BO;
    public static final /* synthetic */ int BA_RI;
    public /* synthetic */ boolean loaded;
    public /* synthetic */ int neighbours;
    public static final /* synthetic */ int FO_RI;
    public static final /* synthetic */ int FO;
    public static final /* synthetic */ int RI;
    private static final /* synthetic */ class_2338.class_2339 blockPos;
    public final /* synthetic */ int y;
    public static final /* synthetic */ int[] SIDES;
    public static final /* synthetic */ int FO_LE;
    public static final /* synthetic */ int TO;
    public static final /* synthetic */ int BO_BA;
    public static final /* synthetic */ int TO_RI;
    public static final /* synthetic */ int TO_LE;
    public static final /* synthetic */ int TO_BA;
    public final /* synthetic */ int z;
    public final /* synthetic */ int x;
    public /* synthetic */ SGroup group;
    public static final /* synthetic */ int BO_LE;
    private static final /* synthetic */ Search search;
    public static final /* synthetic */ int BA_LE;

    public SBlock(int llllllllllllllllIllIIIlIIllIlIlI, int llllllllllllllllIllIIIlIIllIIlIl, int llllllllllllllllIllIIIlIIllIlIII) {
        SBlock llllllllllllllllIllIIIlIIllIIlll;
        llllllllllllllllIllIIIlIIllIIlll.loaded = true;
        llllllllllllllllIllIIIlIIllIIlll.x = llllllllllllllllIllIIIlIIllIlIlI;
        llllllllllllllllIllIIIlIIllIIlll.y = llllllllllllllllIllIIIlIIllIIlIl;
        llllllllllllllllIllIIIlIIllIIlll.z = llllllllllllllllIllIIIlIIllIlIII;
    }

    public void render() {
        SBlock llllllllllllllllIllIIIlIIIIlIlIl;
        double llllllllllllllllIllIIIlIIIlIIIII = llllllllllllllllIllIIIlIIIIlIlIl.x;
        double llllllllllllllllIllIIIlIIIIlllll = llllllllllllllllIllIIIlIIIIlIlIl.y;
        double llllllllllllllllIllIIIlIIIIllllI = llllllllllllllllIllIIIlIIIIlIlIl.z;
        double llllllllllllllllIllIIIlIIIIlllIl = llllllllllllllllIllIIIlIIIIlIlIl.x + 1;
        double llllllllllllllllIllIIIlIIIIlllII = llllllllllllllllIllIIIlIIIIlIlIl.y + 1;
        double llllllllllllllllIllIIIlIIIIllIll = llllllllllllllllIllIIIlIIIIlIlIl.z + 1;
        class_265 llllllllllllllllIllIIIlIIIIllIlI = llllllllllllllllIllIIIlIIIIlIlIl.state.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        if (!llllllllllllllllIllIIIlIIIIllIlI.method_1110()) {
            llllllllllllllllIllIIIlIIIlIIIII = (double)llllllllllllllllIllIIIlIIIIlIlIl.x + llllllllllllllllIllIIIlIIIIllIlI.method_1091(class_2350.class_2351.field_11048);
            llllllllllllllllIllIIIlIIIIlllll = (double)llllllllllllllllIllIIIlIIIIlIlIl.y + llllllllllllllllIllIIIlIIIIllIlI.method_1091(class_2350.class_2351.field_11052);
            llllllllllllllllIllIIIlIIIIllllI = (double)llllllllllllllllIllIIIlIIIIlIlIl.z + llllllllllllllllIllIIIlIIIIllIlI.method_1091(class_2350.class_2351.field_11051);
            llllllllllllllllIllIIIlIIIIlllIl = (double)llllllllllllllllIllIIIlIIIIlIlIl.x + llllllllllllllllIllIIIlIIIIllIlI.method_1105(class_2350.class_2351.field_11048);
            llllllllllllllllIllIIIlIIIIlllII = (double)llllllllllllllllIllIIIlIIIIlIlIl.y + llllllllllllllllIllIIIlIIIIllIlI.method_1105(class_2350.class_2351.field_11052);
            llllllllllllllllIllIIIlIIIIllIll = (double)llllllllllllllllIllIIIlIIIIlIlIl.z + llllllllllllllllIllIIIlIIIIllIlI.method_1105(class_2350.class_2351.field_11051);
        }
        SBlockData llllllllllllllllIllIIIlIIIIllIIl = search.getBlockData(llllllllllllllllIllIIIlIIIIlIlIl.state.method_26204());
        ShapeMode llllllllllllllllIllIIIlIIIIllIII = llllllllllllllllIllIIIlIIIIllIIl.shapeMode;
        SettingColor llllllllllllllllIllIIIlIIIIlIlll = llllllllllllllllIllIIIlIIIIllIIl.lineColor;
        SettingColor llllllllllllllllIllIIIlIIIIlIllI = llllllllllllllllIllIIIlIIIIllIIl.sideColor;
        if (llllllllllllllllIllIIIlIIIIlIlIl.neighbours == 0) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIllI, llllllllllllllllIllIIIlIIIIlIlll, llllllllllllllllIllIIIlIIIIllIII, 0);
        } else {
            if (llllllllllllllllIllIIIlIIIIllIII != ShapeMode.Sides) {
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) == 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) == 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x40) != 64) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) == 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) == 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x100) != 256) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) == 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) == 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x10) != 16) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) == 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) == 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 4) != 4) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x4000) != 16384 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x10000) == 65536) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x4000) != 16384 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x8000) == 32768) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x200) != 512 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x800) == 2048) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x200) != 512 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x400) == 1024) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x4000) != 16384 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x40000) == 262144) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x4000) != 16384 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20000) == 131072) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x200) != 512 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x2000) == 8192) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x200) != 512 || (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8 && (llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x1000) == 4096) {
                    Renderer.LINES.line(llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIlll);
                }
            }
            if (llllllllllllllllIllIIIlIIIIllIII != ShapeMode.Lines) {
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x4000) != 16384) {
                    Renderer.NORMAL.horizontalQuad(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIlIllI);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x200) != 512) {
                    Renderer.NORMAL.horizontalQuad(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIlIllI);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 2) != 2) {
                    Renderer.NORMAL.verticalQuad(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIllI);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x20) != 32) {
                    Renderer.NORMAL.verticalQuad(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlIllI);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 8) != 8) {
                    Renderer.NORMAL.verticalQuad(llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIIlllIl, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIllI);
                }
                if ((llllllllllllllllIllIIIlIIIIlIlIl.neighbours & 0x80) != 128) {
                    Renderer.NORMAL.verticalQuad(llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllll, llllllllllllllllIllIIIlIIIIllllI, llllllllllllllllIllIIIlIIIlIIIII, llllllllllllllllIllIIIlIIIIlllII, llllllllllllllllIllIIIlIIIIllIll, llllllllllllllllIllIIIlIIIIlIllI);
                }
            }
        }
    }

    public void update() {
        SBlock llllllllllllllllIllIIIlIIlIIlIIl;
        llllllllllllllllIllIIIlIIlIIlIIl.state = Utils.mc.field_1687.method_8320((class_2338)blockPos.method_10103(llllllllllllllllIllIIIlIIlIIlIIl.x, llllllllllllllllIllIIIlIIlIIlIIl.y, llllllllllllllllIllIIIlIIlIIlIIl.z));
        llllllllllllllllIllIIIlIIlIIlIIl.neighbours = 0;
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11035)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 2;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(1.0, 0.0, 1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 4;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11034)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 8;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(1.0, 0.0, -1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x10;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11043)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x20;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(-1.0, 0.0, -1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x40;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11039)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x80;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(-1.0, 0.0, 1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x100;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11036)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x200;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(0.0, 1.0, 1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x400;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(0.0, 1.0, -1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x800;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(1.0, 1.0, 0.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x1000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(-1.0, 1.0, 0.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x2000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbour(class_2350.field_11033)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x4000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(0.0, -1.0, 1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x8000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(0.0, -1.0, -1.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x10000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(1.0, -1.0, 0.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x20000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.isNeighbourDiagonal(-1.0, -1.0, 0.0)) {
            llllllllllllllllIllIIIlIIlIIlIIl.neighbours |= 0x40000;
        }
        if (llllllllllllllllIllIIIlIIlIIlIIl.group == null) {
            llllllllllllllllIllIIIlIIlIIlIIl.assignGroup();
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

    public static long getKey(int llllllllllllllllIllIIIlIIIIIIllI, int llllllllllllllllIllIIIlIIIIIIIlI, int llllllllllllllllIllIIIlIIIIIIlII) {
        return (long)llllllllllllllllIllIIIlIIIIIIIlI << 16 | (long)(llllllllllllllllIllIIIlIIIIIIlII & 0xF) << 8 | (long)(llllllllllllllllIllIIIlIIIIIIllI & 0xF);
    }

    public SBlock getSideBlock(int llllllllllllllllIllIIIlIIlIllllI) {
        switch (llllllllllllllllIllIIIlIIlIllllI) {
            case 2: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x, llllllllllllllllIllIIIlIIllIIIIl.y, llllllllllllllllIllIIIlIIllIIIIl.z + 1);
            }
            case 32: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x, llllllllllllllllIllIIIlIIllIIIIl.y, llllllllllllllllIllIIIlIIllIIIIl.z - 1);
            }
            case 128: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x - 1, llllllllllllllllIllIIIlIIllIIIIl.y, llllllllllllllllIllIIIlIIllIIIIl.z);
            }
            case 8: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x + 1, llllllllllllllllIllIIIlIIllIIIIl.y, llllllllllllllllIllIIIlIIllIIIIl.z);
            }
            case 512: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x, llllllllllllllllIllIIIlIIllIIIIl.y + 1, llllllllllllllllIllIIIlIIllIIIIl.z);
            }
            case 16384: {
                SBlock llllllllllllllllIllIIIlIIllIIIIl;
                return search.getBlock(llllllllllllllllIllIIIlIIllIIIIl.x, llllllllllllllllIllIIIlIIllIIIIl.y - 1, llllllllllllllllIllIIIlIIllIIIIl.z);
            }
        }
        return null;
    }

    public static long getKey(class_2338 llllllllllllllllIllIIIIlllllllll) {
        return SBlock.getKey(llllllllllllllllIllIIIIlllllllll.method_10263(), llllllllllllllllIllIIIIlllllllll.method_10264(), llllllllllllllllIllIIIIlllllllll.method_10260());
    }

    private boolean isNeighbour(class_2350 llllllllllllllllIllIIIlIIIllllIl) {
        SBlock llllllllllllllllIllIIIlIIIlllllI;
        blockPos.method_10103(llllllllllllllllIllIIIlIIIlllllI.x + llllllllllllllllIllIIIlIIIllllIl.method_10148(), llllllllllllllllIllIIIlIIIlllllI.y + llllllllllllllllIllIIIlIIIllllIl.method_10164(), llllllllllllllllIllIIIlIIIlllllI.z + llllllllllllllllIllIIIlIIIllllIl.method_10165());
        class_2680 llllllllllllllllIllIIIlIIlIIIIIl = Utils.mc.field_1687.method_8320((class_2338)blockPos);
        if (llllllllllllllllIllIIIlIIlIIIIIl.method_26204() != llllllllllllllllIllIIIlIIIlllllI.state.method_26204()) {
            return false;
        }
        class_265 llllllllllllllllIllIIIlIIlIIIIII = llllllllllllllllIllIIIlIIIlllllI.state.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        class_265 llllllllllllllllIllIIIlIIIllllll = llllllllllllllllIllIIIlIIlIIIIIl.method_26218((class_1922)Utils.mc.field_1687, (class_2338)blockPos);
        switch (llllllllllllllllIllIIIlIIIllllIl) {
            case field_11035: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1105(class_2350.class_2351.field_11051) != 1.0 || llllllllllllllllIllIIIlIIIllllll.method_1091(class_2350.class_2351.field_11051) != 0.0) break;
                return true;
            }
            case field_11043: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1091(class_2350.class_2351.field_11051) != 0.0 || llllllllllllllllIllIIIlIIIllllll.method_1105(class_2350.class_2351.field_11051) != 1.0) break;
                return true;
            }
            case field_11034: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1105(class_2350.class_2351.field_11048) != 1.0 || llllllllllllllllIllIIIlIIIllllll.method_1091(class_2350.class_2351.field_11048) != 0.0) break;
                return true;
            }
            case field_11039: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1091(class_2350.class_2351.field_11048) != 0.0 || llllllllllllllllIllIIIlIIIllllll.method_1105(class_2350.class_2351.field_11048) != 1.0) break;
                return true;
            }
            case field_11036: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1105(class_2350.class_2351.field_11052) != 1.0 || llllllllllllllllIllIIIlIIIllllll.method_1091(class_2350.class_2351.field_11052) != 0.0) break;
                return true;
            }
            case field_11033: {
                if (llllllllllllllllIllIIIlIIlIIIIII.method_1091(class_2350.class_2351.field_11052) != 0.0 || llllllllllllllllIllIIIlIIIllllll.method_1105(class_2350.class_2351.field_11052) != 1.0) break;
                return true;
            }
        }
        return false;
    }

    private boolean isNeighbourDiagonal(double llllllllllllllllIllIIIlIIIllIlII, double llllllllllllllllIllIIIlIIIlIllll, double llllllllllllllllIllIIIlIIIlIlllI) {
        SBlock llllllllllllllllIllIIIlIIIllIlIl;
        blockPos.method_10102((double)llllllllllllllllIllIIIlIIIllIlIl.x + llllllllllllllllIllIIIlIIIllIlII, (double)llllllllllllllllIllIIIlIIIllIlIl.y + llllllllllllllllIllIIIlIIIlIllll, (double)llllllllllllllllIllIIIlIIIllIlIl.z + llllllllllllllllIllIIIlIIIlIlllI);
        return llllllllllllllllIllIIIlIIIllIlIl.state.method_26204() == Utils.mc.field_1687.method_8320((class_2338)blockPos).method_26204();
    }

    private void assignGroup() {
        SBlock llllllllllllllllIllIIIlIIlIlIIlI;
        SGroup llllllllllllllllIllIIIlIIlIlIIll = null;
        for (int llllllllllllllllIllIIIlIIlIlIlIl : SIDES) {
            SBlock llllllllllllllllIllIIIlIIlIlIllI;
            if ((llllllllllllllllIllIIIlIIlIlIIlI.neighbours & llllllllllllllllIllIIIlIIlIlIlIl) != llllllllllllllllIllIIIlIIlIlIlIl || (llllllllllllllllIllIIIlIIlIlIllI = llllllllllllllllIllIIIlIIlIlIIlI.getSideBlock(llllllllllllllllIllIIIlIIlIlIlIl)) == null || llllllllllllllllIllIIIlIIlIlIllI.group == null) continue;
            if (llllllllllllllllIllIIIlIIlIlIIll == null) {
                llllllllllllllllIllIIIlIIlIlIIll = llllllllllllllllIllIIIlIIlIlIllI.group;
                continue;
            }
            if (llllllllllllllllIllIIIlIIlIlIIll == llllllllllllllllIllIIIlIIlIlIllI.group) continue;
            llllllllllllllllIllIIIlIIlIlIIll.merge(llllllllllllllllIllIIIlIIlIlIllI.group);
        }
        if (llllllllllllllllIllIIIlIIlIlIIll == null) {
            llllllllllllllllIllIIIlIIlIlIIll = search.newGroup(llllllllllllllllIllIIIlIIlIlIIlI.state.method_26204());
        }
        llllllllllllllllIllIIIlIIlIlIIll.add(llllllllllllllllIllIIIlIIlIlIIlI);
    }
}

