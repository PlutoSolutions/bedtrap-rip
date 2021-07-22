/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2828
 *  net.minecraft.class_3612
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.movement;

import baritone.api.BaritoneAPI;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.PlayerMoveC2SPacketAccessor;
import minegame159.meteorclient.mixininterface.IPlayerMoveC2SPacket;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_2828;
import net.minecraft.class_3612;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class NoFall
extends Module {
    private /* synthetic */ boolean centeredPlayer;
    private final /* synthetic */ Setting<Boolean> elytra;
    private /* synthetic */ int fallHeightBaritone;
    private /* synthetic */ double z;
    private final /* synthetic */ Setting<Integer> minFallHeight;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ double x;
    private final /* synthetic */ Setting<Boolean> anchor;
    private /* synthetic */ boolean placedWater;
    private final /* synthetic */ Setting<PlaceMode> airplaceMode;

    public NoFall() {
        super(Categories.Movement, "no-fall", "Prevents you from taking fall damage.");
        NoFall lIIIIllIlllIl;
        lIIIIllIlllIl.sgGeneral = lIIIIllIlllIl.settings.getDefaultGroup();
        lIIIIllIlllIl.mode = lIIIIllIlllIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The way you are saved from fall damage.").defaultValue(Mode.Packet).build());
        lIIIIllIlllIl.anchor = lIIIIllIlllIl.sgGeneral.add(new BoolSetting.Builder().name("anchor").description("Centers the player and reduces movement when using bucket mode.").defaultValue(true).visible(() -> {
            NoFall lIIIIlIlIIIlI;
            return lIIIIlIlIIIlI.mode.get() == Mode.Bucket;
        }).build());
        lIIIIllIlllIl.airplaceMode = lIIIIllIlllIl.sgGeneral.add(new EnumSetting.Builder().name("place-mode").description("Whether place mode places before you die or before you take damage.").defaultValue(PlaceMode.BeforeDeath).visible(() -> {
            NoFall lIIIIlIlIIlII;
            return lIIIIlIlIIlII.mode.get() == Mode.AirPlace;
        }).build());
        lIIIIllIlllIl.elytra = lIIIIllIlllIl.sgGeneral.add(new BoolSetting.Builder().name("elytra-compatibility").description("Stops No Fall from working when using an elytra.").defaultValue(true).build());
        lIIIIllIlllIl.minFallHeight = lIIIIllIlllIl.sgGeneral.add(new IntSetting.Builder().name("min-fall-height").description("The minimum height to fall from for no fall to work.").defaultValue(3).min(1).sliderMax(10).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIIIIllIIIlIl) {
        NoFall lIIIIllIIIllI;
        if (lIIIIllIIIllI.mc.field_1724.field_7503.field_7477) {
            return;
        }
        if (lIIIIllIIIllI.mode.get() == Mode.AirPlace && (lIIIIllIIIllI.airplaceMode.get() == PlaceMode.BeforeDamage && lIIIIllIIIllI.mc.field_1724.field_6017 > 2.0f || lIIIIllIIIllI.airplaceMode.get() == PlaceMode.BeforeDeath && lIIIIllIIIllI.mc.field_1724.method_6032() + lIIIIllIIIllI.mc.field_1724.method_6067() < lIIIIllIIIllI.mc.field_1724.field_6017)) {
            PlayerUtils.centerPlayer();
            BlockUtils.place(lIIIIllIIIllI.mc.field_1724.method_24515().method_10087(2), InvUtils.findInHotbar(lIIIIlIlIlIII -> lIIIIlIlIlIII.method_7909() instanceof class_1747), true, 50, true);
        } else if (lIIIIllIIIllI.mode.get() == Mode.Bucket) {
            if (lIIIIllIIIllI.placedWater) {
                FindItemResult lIIIIllIIlIIl = InvUtils.findInHotbar(class_1802.field_8550);
                if (lIIIIllIIlIIl.isHotbar() && lIIIIllIIIllI.mc.field_1724.method_16212().method_26227().method_15772() == class_3612.field_15910) {
                    lIIIIllIIIllI.useBucket(lIIIIllIIlIIl.getSlot(), false);
                }
                lIIIIllIIIllI.centeredPlayer = false;
            } else if (lIIIIllIIIllI.mc.field_1724.field_6017 > 3.0f && !EntityUtils.isAboveWater((class_1297)lIIIIllIIIllI.mc.field_1724)) {
                class_3965 lIIIIllIIlIII;
                FindItemResult lIIIIllIIIlll = InvUtils.findInHotbar(class_1802.field_8705);
                if (lIIIIllIIIllI.anchor.get().booleanValue() && (!lIIIIllIIIllI.centeredPlayer || lIIIIllIIIllI.x != lIIIIllIIIllI.mc.field_1724.method_23317() || lIIIIllIIIllI.z != lIIIIllIIIllI.mc.field_1724.method_23321())) {
                    PlayerUtils.centerPlayer();
                    lIIIIllIIIllI.x = lIIIIllIIIllI.mc.field_1724.method_23317();
                    lIIIIllIIIllI.z = lIIIIllIIIllI.mc.field_1724.method_23321();
                    lIIIIllIIIllI.centeredPlayer = true;
                }
                if (lIIIIllIIIlll.isHotbar() && (lIIIIllIIlIII = lIIIIllIIIllI.mc.field_1687.method_17742(new class_3959(lIIIIllIIIllI.mc.field_1724.method_19538(), lIIIIllIIIllI.mc.field_1724.method_19538().method_1023(0.0, 5.0, 0.0), class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)lIIIIllIIIllI.mc.field_1724))) != null && lIIIIllIIlIII.method_17783() == class_239.class_240.field_1332) {
                    lIIIIllIIIllI.useBucket(lIIIIllIIIlll.getSlot(), true);
                }
            }
        }
        if (lIIIIllIIIllI.mc.field_1724.field_6017 == 0.0f) {
            lIIIIllIIIllI.placedWater = false;
        }
    }

    @Override
    public void onActivate() {
        lIIIIlllIIIII.fallHeightBaritone = (Integer)BaritoneAPI.getSettings().maxFallHeightNoWater.get();
        BaritoneAPI.getSettings().maxFallHeightNoWater.value = 255;
        lIIIIlllIIIII.placedWater = false;
        lIIIIlllIIIII.centeredPlayer = false;
    }

    @Override
    public String getInfoString() {
        NoFall lIIIIlIllIlll;
        return lIIIIlIllIlll.mode.get().toString();
    }

    private void useBucket(int lIIIIlIlllIlI, boolean lIIIIlIlllIIl) {
        NoFall lIIIIlIlllIll;
        Rotations.rotate(lIIIIlIlllIll.mc.field_1724.field_6031, 90.0, 10, true, () -> {
            NoFall lIIIIlIlIllIl;
            int lIIIIlIlIlllI = lIIIIlIlIllIl.mc.field_1724.field_7514.field_7545;
            InvUtils.swap(lIIIIlIlllIlI);
            lIIIIlIlIllIl.mc.field_1761.method_2919((class_1657)lIIIIlIlIllIl.mc.field_1724, (class_1937)lIIIIlIlIllIl.mc.field_1687, class_1268.field_5808);
            InvUtils.swap(lIIIIlIlIlllI);
            lIIIIlIlIllIl.placedWater = lIIIIlIlllIIl;
        });
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send lIIIIllIlIIIl) {
        NoFall lIIIIllIlIIlI;
        if (lIIIIllIlIIlI.mc.field_1724.field_7503.field_7477) {
            return;
        }
        if (lIIIIllIlIIIl.packet instanceof class_2828) {
            if (lIIIIllIlIIlI.elytra.get().booleanValue() && lIIIIllIlIIlI.mc.field_1724.method_6128()) {
                for (int lIIIIllIlIIll = 0; lIIIIllIlIIll <= lIIIIllIlIIlI.minFallHeight.get(); ++lIIIIllIlIIll) {
                    class_2338 lIIIIllIlIlII = lIIIIllIlIIlI.mc.field_1724.method_24515().method_10087(lIIIIllIlIIll);
                    if (lIIIIllIlIIlI.mc.field_1687.method_8320(lIIIIllIlIlII).method_26207().method_15800()) continue;
                    ((PlayerMoveC2SPacketAccessor)lIIIIllIlIIIl.packet).setOnGround(true);
                    return;
                }
            } else if (lIIIIllIlIIlI.mode.get() == Mode.Packet && ((IPlayerMoveC2SPacket)lIIIIllIlIIIl.packet).getTag() != 1337) {
                ((PlayerMoveC2SPacketAccessor)lIIIIllIlIIIl.packet).setOnGround(true);
            }
        }
    }

    @Override
    public void onDeactivate() {
        NoFall lIIIIllIllIIl;
        BaritoneAPI.getSettings().maxFallHeightNoWater.value = lIIIIllIllIIl.fallHeightBaritone;
    }

    public static enum Mode {
        Packet,
        AirPlace,
        Bucket;


        private Mode() {
            Mode llIIllllIlllll;
        }
    }

    public static enum PlaceMode {
        BeforeDeath,
        BeforeDamage;


        private PlaceMode() {
            PlaceMode llllllllllllllllllIlIlIllIlIlIll;
        }
    }
}

