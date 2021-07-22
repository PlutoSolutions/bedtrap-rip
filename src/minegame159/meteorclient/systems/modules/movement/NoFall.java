/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
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
import net.minecraft.class_1799;
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
    private boolean centeredPlayer;
    private final Setting<Boolean> elytra;
    private int fallHeightBaritone;
    private double z;
    private final Setting<Integer> minFallHeight;
    private final Setting<Mode> mode;
    private final SettingGroup sgGeneral;
    private double x;
    private final Setting<Boolean> anchor;
    private boolean placedWater;
    private final Setting<PlaceMode> airplaceMode;

    public NoFall() {
        super(Categories.Movement, "no-fall", "Prevents you from taking fall damage.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The way you are saved from fall damage.").defaultValue(Mode.Packet).build());
        this.anchor = this.sgGeneral.add(new BoolSetting.Builder().name("anchor").description("Centers the player and reduces movement when using bucket mode.").defaultValue(true).visible(this::lambda$new$0).build());
        this.airplaceMode = this.sgGeneral.add(new EnumSetting.Builder().name("place-mode").description("Whether place mode places before you die or before you take damage.").defaultValue(PlaceMode.BeforeDeath).visible(this::lambda$new$1).build());
        this.elytra = this.sgGeneral.add(new BoolSetting.Builder().name("elytra-compatibility").description("Stops No Fall from working when using an elytra.").defaultValue(true).build());
        this.minFallHeight = this.sgGeneral.add(new IntSetting.Builder().name("min-fall-height").description("The minimum height to fall from for no fall to work.").defaultValue(3).min(1).sliderMax(10).build());
    }

    private static boolean lambda$onTick$2(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1747;
    }

    private boolean lambda$new$1() {
        return this.mode.get() == Mode.AirPlace;
    }

    private void lambda$useBucket$3(int n, boolean bl) {
        int n2 = this.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(n);
        this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5808);
        InvUtils.swap(n2);
        this.placedWater = bl;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1724.field_7503.field_7477) {
            return;
        }
        if (this.mode.get() == Mode.AirPlace && (this.airplaceMode.get() == PlaceMode.BeforeDamage && this.mc.field_1724.field_6017 > 2.0f || this.airplaceMode.get() == PlaceMode.BeforeDeath && this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067() < this.mc.field_1724.field_6017)) {
            PlayerUtils.centerPlayer();
            BlockUtils.place(this.mc.field_1724.method_24515().method_10087(2), InvUtils.findInHotbar(NoFall::lambda$onTick$2), true, 50, true);
        } else if (this.mode.get() == Mode.Bucket) {
            if (this.placedWater) {
                FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8550);
                if (findItemResult.isHotbar() && this.mc.field_1724.method_16212().method_26227().method_15772() == class_3612.field_15910) {
                    this.useBucket(findItemResult.getSlot(), false);
                }
                this.centeredPlayer = false;
            } else if (this.mc.field_1724.field_6017 > 3.0f && !EntityUtils.isAboveWater((class_1297)this.mc.field_1724)) {
                class_3965 class_39652;
                FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8705);
                if (this.anchor.get().booleanValue() && (!this.centeredPlayer || this.x != this.mc.field_1724.method_23317() || this.z != this.mc.field_1724.method_23321())) {
                    PlayerUtils.centerPlayer();
                    this.x = this.mc.field_1724.method_23317();
                    this.z = this.mc.field_1724.method_23321();
                    this.centeredPlayer = true;
                }
                if (findItemResult.isHotbar() && (class_39652 = this.mc.field_1687.method_17742(new class_3959(this.mc.field_1724.method_19538(), this.mc.field_1724.method_19538().method_1023(0.0, 5.0, 0.0), class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724))) != null && class_39652.method_17783() == class_239.class_240.field_1332) {
                    this.useBucket(findItemResult.getSlot(), true);
                }
            }
        }
        if (this.mc.field_1724.field_6017 == 0.0f) {
            this.placedWater = false;
        }
    }

    private boolean lambda$new$0() {
        return this.mode.get() == Mode.Bucket;
    }

    @Override
    public void onActivate() {
        this.fallHeightBaritone = (Integer)BaritoneAPI.getSettings().maxFallHeightNoWater.get();
        BaritoneAPI.getSettings().maxFallHeightNoWater.value = 255;
        this.placedWater = false;
        this.centeredPlayer = false;
    }

    @Override
    public String getInfoString() {
        return this.mode.get().toString();
    }

    private void useBucket(int n, boolean bl) {
        Rotations.rotate(this.mc.field_1724.field_6031, 90.0, 10, true, () -> this.lambda$useBucket$3(n, bl));
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (this.mc.field_1724.field_7503.field_7477) {
            return;
        }
        if (send.packet instanceof class_2828) {
            if (this.elytra.get().booleanValue() && this.mc.field_1724.method_6128()) {
                for (int i = 0; i <= this.minFallHeight.get(); ++i) {
                    class_2338 class_23382 = this.mc.field_1724.method_24515().method_10087(i);
                    if (this.mc.field_1687.method_8320(class_23382).method_26207().method_15800()) continue;
                    ((PlayerMoveC2SPacketAccessor)send.packet).setOnGround(true);
                    return;
                }
            } else if (this.mode.get() == Mode.Packet && ((IPlayerMoveC2SPacket)send.packet).getTag() != 1337) {
                ((PlayerMoveC2SPacketAccessor)send.packet).setOnGround(true);
            }
        }
    }

    @Override
    public void onDeactivate() {
        BaritoneAPI.getSettings().maxFallHeightNoWater.value = this.fallHeightBaritone;
    }

    public static enum Mode {
        Packet,
        AirPlace,
        Bucket;

    }

    public static enum PlaceMode {
        BeforeDeath,
        BeforeDamage;

    }
}

