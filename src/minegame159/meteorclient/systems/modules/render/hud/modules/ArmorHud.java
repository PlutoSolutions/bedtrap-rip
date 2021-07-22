/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.RenderUtils;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class ArmorHud
extends HudElement {
    private final /* synthetic */ Setting<Orientation> orientation;
    private final /* synthetic */ Setting<Boolean> flipOrder;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Durability> durability;

    private class_1799 getItem(int llllllllllllllllllIIIIlIllllIlll) {
        ArmorHud llllllllllllllllllIIIIlIlllllIlI;
        if (llllllllllllllllllIIIIlIlllllIlI.isInEditor()) {
            switch (llllllllllllllllllIIIIlIllllIlll) {
                default: {
                    return class_1802.field_22030.method_7854();
                }
                case 1: {
                    return class_1802.field_22029.method_7854();
                }
                case 2: {
                    return class_1802.field_22028.method_7854();
                }
                case 3: 
            }
            return class_1802.field_22027.method_7854();
        }
        return llllllllllllllllllIIIIlIlllllIlI.mc.field_1724.field_7514.method_7372(llllllllllllllllllIIIIlIllllIlll);
    }

    public ArmorHud(HUD llllllllllllllllllIIIIllIIlIIlII) {
        super(llllllllllllllllllIIIIllIIlIIlII, "armor", "Displays information about your armor.");
        ArmorHud llllllllllllllllllIIIIllIIlIIlIl;
        llllllllllllllllllIIIIllIIlIIlIl.sgGeneral = llllllllllllllllllIIIIllIIlIIlIl.settings.getDefaultGroup();
        llllllllllllllllllIIIIllIIlIIlIl.flipOrder = llllllllllllllllllIIIIllIIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("flip-order").description("Flips the order of armor items.").defaultValue(true).build());
        llllllllllllllllllIIIIllIIlIIlIl.orientation = llllllllllllllllllIIIIllIIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("orientation").description("How to display armor.").defaultValue(Orientation.Horizontal).build());
        llllllllllllllllllIIIIllIIlIIlIl.durability = llllllllllllllllllIIIIllIIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("durability").description("How to display armor durability.").defaultValue(Durability.Default).build());
        llllllllllllllllllIIIIllIIlIIlIl.scale = llllllllllllllllllIIIIllIIlIIlIl.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of armor.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(5.0).build());
    }

    @Override
    public void render(HudRenderer llllllllllllllllllIIIIllIIIIIllI) {
        ArmorHud llllllllllllllllllIIIIllIIIIllII;
        double llllllllllllllllllIIIIllIIIIlIlI = llllllllllllllllllIIIIllIIIIllII.box.getX();
        double llllllllllllllllllIIIIllIIIIlIIl = llllllllllllllllllIIIIllIIIIllII.box.getY();
        int llllllllllllllllllIIIIllIIIIlIII = llllllllllllllllllIIIIllIIIIllII.flipOrder.get() != false ? 3 : 0;
        for (int llllllllllllllllllIIIIllIIIIllIl = 0; llllllllllllllllllIIIIllIIIIllIl < 4; ++llllllllllllllllllIIIIllIIIIllIl) {
            double llllllllllllllllllIIIIllIIIIlllI;
            double llllllllllllllllllIIIIllIIIIllll;
            class_1799 llllllllllllllllllIIIIllIIIlIIII = llllllllllllllllllIIIIllIIIIllII.getItem(llllllllllllllllllIIIIllIIIIlIII);
            RenderSystem.pushMatrix();
            RenderSystem.scaled((double)llllllllllllllllllIIIIllIIIIllII.scale.get(), (double)llllllllllllllllllIIIIllIIIIllII.scale.get(), (double)1.0);
            if (llllllllllllllllllIIIIllIIIIllII.orientation.get() == Orientation.Vertical) {
                double llllllllllllllllllIIIIllIIIlIlII = llllllllllllllllllIIIIllIIIIlIlI / llllllllllllllllllIIIIllIIIIllII.scale.get();
                double llllllllllllllllllIIIIllIIIlIIll = llllllllllllllllllIIIIllIIIIlIIl / llllllllllllllllllIIIIllIIIIllII.scale.get() + (double)(llllllllllllllllllIIIIllIIIIllIl * 18);
            } else {
                llllllllllllllllllIIIIllIIIIllll = llllllllllllllllllIIIIllIIIIlIlI / llllllllllllllllllIIIIllIIIIllII.scale.get() + (double)(llllllllllllllllllIIIIllIIIIllIl * 18);
                llllllllllllllllllIIIIllIIIIlllI = llllllllllllllllllIIIIllIIIIlIIl / llllllllllllllllllIIIIllIIIIllII.scale.get();
            }
            RenderUtils.drawItem(llllllllllllllllllIIIIllIIIlIIII, (int)llllllllllllllllllIIIIllIIIIllll, (int)llllllllllllllllllIIIIllIIIIlllI, llllllllllllllllllIIIIllIIIlIIII.method_7963() && llllllllllllllllllIIIIllIIIIllII.durability.get() == Durability.Default);
            if (llllllllllllllllllIIIIllIIIlIIII.method_7963() && !llllllllllllllllllIIIIllIIIIllII.isInEditor() && llllllllllllllllllIIIIllIIIIllII.durability.get() != Durability.Default && llllllllllllllllllIIIIllIIIIllII.durability.get() != Durability.None) {
                String llllllllllllllllllIIIIllIIIlIIlI = "err";
                switch (llllllllllllllllllIIIIllIIIIllII.durability.get()) {
                    case Numbers: {
                        llllllllllllllllllIIIIllIIIlIIlI = Integer.toString(llllllllllllllllllIIIIllIIIlIIII.method_7936() - llllllllllllllllllIIIIllIIIlIIII.method_7919());
                        break;
                    }
                    case Percentage: {
                        llllllllllllllllllIIIIllIIIlIIlI = Integer.toString(Math.round((float)(llllllllllllllllllIIIIllIIIlIIII.method_7936() - llllllllllllllllllIIIIllIIIlIIII.method_7919()) * 100.0f / (float)llllllllllllllllllIIIIllIIIlIIII.method_7936()));
                    }
                }
                double llllllllllllllllllIIIIllIIIlIIIl = llllllllllllllllllIIIIllIIIIIllI.textWidth(llllllllllllllllllIIIIllIIIlIIlI);
                if (llllllllllllllllllIIIIllIIIIllII.orientation.get() == Orientation.Vertical) {
                    llllllllllllllllllIIIIllIIIIllll = llllllllllllllllllIIIIllIIIIlIlI + 8.0 * llllllllllllllllllIIIIllIIIIllII.scale.get() - llllllllllllllllllIIIIllIIIlIIIl / 2.0;
                    llllllllllllllllllIIIIllIIIIlllI = llllllllllllllllllIIIIllIIIIlIIl + (double)(18 * llllllllllllllllllIIIIllIIIIllIl) * llllllllllllllllllIIIIllIIIIllII.scale.get() + (18.0 * llllllllllllllllllIIIIllIIIIllII.scale.get() - llllllllllllllllllIIIIllIIIIIllI.textHeight());
                } else {
                    llllllllllllllllllIIIIllIIIIllll = llllllllllllllllllIIIIllIIIIlIlI + (double)(18 * llllllllllllllllllIIIIllIIIIllIl) * llllllllllllllllllIIIIllIIIIllII.scale.get() + 8.0 * llllllllllllllllllIIIIllIIIIllII.scale.get() - llllllllllllllllllIIIIllIIIlIIIl / 2.0;
                    llllllllllllllllllIIIIllIIIIlllI = llllllllllllllllllIIIIllIIIIlIIl + (llllllllllllllllllIIIIllIIIIllII.box.height - llllllllllllllllllIIIIllIIIIIllI.textHeight());
                }
                llllllllllllllllllIIIIllIIIIIllI.text(llllllllllllllllllIIIIllIIIlIIlI, llllllllllllllllllIIIIllIIIIllll, llllllllllllllllllIIIIllIIIIlllI, llllllllllllllllllIIIIllIIIIllII.hud.primaryColor.get());
            }
            RenderSystem.popMatrix();
            if (llllllllllllllllllIIIIllIIIIllII.flipOrder.get().booleanValue()) {
                --llllllllllllllllllIIIIllIIIIlIII;
                continue;
            }
            ++llllllllllllllllllIIIIllIIIIlIII;
        }
    }

    @Override
    public void update(HudRenderer llllllllllllllllllIIIIllIIlIIIIl) {
        ArmorHud llllllllllllllllllIIIIllIIlIIIII;
        switch (llllllllllllllllllIIIIllIIlIIIII.orientation.get()) {
            case Horizontal: {
                llllllllllllllllllIIIIllIIlIIIII.box.setSize(16.0 * llllllllllllllllllIIIIllIIlIIIII.scale.get() * 4.0 + 8.0, 16.0 * llllllllllllllllllIIIIllIIlIIIII.scale.get());
                break;
            }
            case Vertical: {
                llllllllllllllllllIIIIllIIlIIIII.box.setSize(16.0 * llllllllllllllllllIIIIllIIlIIIII.scale.get(), 16.0 * llllllllllllllllllIIIIllIIlIIIII.scale.get() * 4.0 + 8.0);
            }
        }
    }

    public static enum Durability {
        None,
        Default,
        Numbers,
        Percentage;


        private Durability() {
            Durability lIIllIIlIlIIllI;
        }
    }

    public static enum Orientation {
        Horizontal,
        Vertical;


        private Orientation() {
            Orientation lllllllllllllllllllIIllIllIllIIl;
        }
    }
}

