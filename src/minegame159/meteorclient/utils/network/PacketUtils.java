/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.network;

import com.google.common.collect.Iterators;
import com.mojang.serialization.Lifecycle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.class_2378;
import net.minecraft.class_2596;
import net.minecraft.class_2604;
import net.minecraft.class_2606;
import net.minecraft.class_2610;
import net.minecraft.class_2612;
import net.minecraft.class_2613;
import net.minecraft.class_2616;
import net.minecraft.class_2617;
import net.minecraft.class_2620;
import net.minecraft.class_2622;
import net.minecraft.class_2623;
import net.minecraft.class_2626;
import net.minecraft.class_2629;
import net.minecraft.class_2632;
import net.minecraft.class_2635;
import net.minecraft.class_2637;
import net.minecraft.class_2639;
import net.minecraft.class_2641;
import net.minecraft.class_2644;
import net.minecraft.class_2645;
import net.minecraft.class_2648;
import net.minecraft.class_2649;
import net.minecraft.class_2651;
import net.minecraft.class_2653;
import net.minecraft.class_2656;
import net.minecraft.class_2658;
import net.minecraft.class_2660;
import net.minecraft.class_2661;
import net.minecraft.class_2663;
import net.minecraft.class_2664;
import net.minecraft.class_2666;
import net.minecraft.class_2668;
import net.minecraft.class_2670;
import net.minecraft.class_2672;
import net.minecraft.class_2673;
import net.minecraft.class_2675;
import net.minecraft.class_2676;
import net.minecraft.class_2678;
import net.minecraft.class_2683;
import net.minecraft.class_2684;
import net.minecraft.class_2692;
import net.minecraft.class_2693;
import net.minecraft.class_2695;
import net.minecraft.class_2696;
import net.minecraft.class_2698;
import net.minecraft.class_2703;
import net.minecraft.class_2707;
import net.minecraft.class_2708;
import net.minecraft.class_2713;
import net.minecraft.class_2716;
import net.minecraft.class_2718;
import net.minecraft.class_2720;
import net.minecraft.class_2724;
import net.minecraft.class_2726;
import net.minecraft.class_2729;
import net.minecraft.class_2730;
import net.minecraft.class_2734;
import net.minecraft.class_2735;
import net.minecraft.class_2736;
import net.minecraft.class_2739;
import net.minecraft.class_2740;
import net.minecraft.class_2743;
import net.minecraft.class_2744;
import net.minecraft.class_2748;
import net.minecraft.class_2749;
import net.minecraft.class_2751;
import net.minecraft.class_2752;
import net.minecraft.class_2755;
import net.minecraft.class_2757;
import net.minecraft.class_2759;
import net.minecraft.class_2761;
import net.minecraft.class_2762;
import net.minecraft.class_2765;
import net.minecraft.class_2767;
import net.minecraft.class_2770;
import net.minecraft.class_2772;
import net.minecraft.class_2774;
import net.minecraft.class_2775;
import net.minecraft.class_2777;
import net.minecraft.class_2779;
import net.minecraft.class_2781;
import net.minecraft.class_2783;
import net.minecraft.class_2788;
import net.minecraft.class_2790;
import net.minecraft.class_2793;
import net.minecraft.class_2795;
import net.minecraft.class_2797;
import net.minecraft.class_2799;
import net.minecraft.class_2803;
import net.minecraft.class_2805;
import net.minecraft.class_2809;
import net.minecraft.class_2811;
import net.minecraft.class_2813;
import net.minecraft.class_2815;
import net.minecraft.class_2817;
import net.minecraft.class_2820;
import net.minecraft.class_2822;
import net.minecraft.class_2824;
import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.minecraft.class_2833;
import net.minecraft.class_2836;
import net.minecraft.class_2838;
import net.minecraft.class_2840;
import net.minecraft.class_2842;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2851;
import net.minecraft.class_2853;
import net.minecraft.class_2855;
import net.minecraft.class_2856;
import net.minecraft.class_2859;
import net.minecraft.class_2863;
import net.minecraft.class_2866;
import net.minecraft.class_2868;
import net.minecraft.class_2870;
import net.minecraft.class_2871;
import net.minecraft.class_2873;
import net.minecraft.class_2875;
import net.minecraft.class_2877;
import net.minecraft.class_2879;
import net.minecraft.class_2884;
import net.minecraft.class_2885;
import net.minecraft.class_2886;
import net.minecraft.class_2889;
import net.minecraft.class_2899;
import net.minecraft.class_2901;
import net.minecraft.class_2905;
import net.minecraft.class_2907;
import net.minecraft.class_2909;
import net.minecraft.class_2913;
import net.minecraft.class_2915;
import net.minecraft.class_2917;
import net.minecraft.class_2923;
import net.minecraft.class_2924;
import net.minecraft.class_2935;
import net.minecraft.class_2937;
import net.minecraft.class_2960;
import net.minecraft.class_3753;
import net.minecraft.class_3895;
import net.minecraft.class_3943;
import net.minecraft.class_3944;
import net.minecraft.class_4210;
import net.minecraft.class_4211;
import net.minecraft.class_4273;
import net.minecraft.class_4282;
import net.minecraft.class_4463;
import net.minecraft.class_5194;
import net.minecraft.class_5321;
import net.minecraft.class_5427;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PacketUtils {
    private static final Map<Class<? extends class_2596<?>>, String> S2C_PACKETS;
    private static final Map<String, Class<? extends class_2596<?>>> S2C_PACKETS_R;
    private static final Map<Class<? extends class_2596<?>>, String> C2S_PACKETS;
    private static final Map<String, Class<? extends class_2596<?>>> C2S_PACKETS_R;
    public static final class_2378<Class<? extends class_2596<?>>> REGISTRY;

    public static Set<Class<? extends class_2596<?>>> getS2CPackets() {
        return S2C_PACKETS.keySet();
    }

    public static Set<Class<? extends class_2596<?>>> getC2SPackets() {
        return C2S_PACKETS.keySet();
    }

    public static String getName(Class<? extends class_2596<?>> clazz) {
        String string = S2C_PACKETS.get(clazz);
        if (string != null) {
            return string;
        }
        return C2S_PACKETS.get(clazz);
    }

    static Map access$000() {
        return S2C_PACKETS;
    }

    public static Class<? extends class_2596<?>> getPacket(String string) {
        Class<? extends class_2596<?>> clazz = S2C_PACKETS_R.get(string);
        if (clazz != null) {
            return clazz;
        }
        return C2S_PACKETS_R.get(string);
    }

    static {
        REGISTRY = new PacketRegistry();
        S2C_PACKETS = new HashMap();
        C2S_PACKETS = new HashMap();
        S2C_PACKETS_R = new HashMap();
        C2S_PACKETS_R = new HashMap();
        S2C_PACKETS.put(class_2604.class, "EntitySpawnS2CPacket");
        S2C_PACKETS.put(class_2606.class, "ExperienceOrbSpawnS2CPacket");
        S2C_PACKETS.put(class_2612.class, "PaintingSpawnS2CPacket");
        S2C_PACKETS.put(class_2610.class, "MobSpawnS2CPacket");
        S2C_PACKETS.put(class_2616.class, "EntityAnimationS2CPacket");
        S2C_PACKETS.put(class_2617.class, "StatisticsS2CPacket");
        S2C_PACKETS.put(class_4463.class, "PlayerActionResponseS2CPacket");
        S2C_PACKETS.put(class_2613.class, "PlayerSpawnS2CPacket");
        S2C_PACKETS.put(class_2620.class, "BlockBreakingProgressS2CPacket");
        S2C_PACKETS.put(class_2622.class, "BlockEntityUpdateS2CPacket");
        S2C_PACKETS.put(class_2623.class, "BlockEventS2CPacket");
        S2C_PACKETS.put(class_2626.class, "BlockUpdateS2CPacket");
        S2C_PACKETS.put(class_2632.class, "DifficultyS2CPacket");
        S2C_PACKETS.put(class_2635.class, "GameMessageS2CPacket");
        S2C_PACKETS.put(class_2629.class, "BossBarS2CPacket");
        S2C_PACKETS.put(class_2644.class, "ConfirmScreenActionS2CPacket");
        S2C_PACKETS.put(class_2639.class, "CommandSuggestionsS2CPacket");
        S2C_PACKETS.put(class_2645.class, "CloseScreenS2CPacket");
        S2C_PACKETS.put(class_2649.class, "InventoryS2CPacket");
        S2C_PACKETS.put(class_2651.class, "ScreenHandlerPropertyUpdateS2CPacket");
        S2C_PACKETS.put(class_2656.class, "CooldownUpdateS2CPacket");
        S2C_PACKETS.put(class_2653.class, "ScreenHandlerSlotUpdateS2CPacket");
        S2C_PACKETS.put(class_2641.class, "CommandTreeS2CPacket");
        S2C_PACKETS.put(class_2661.class, "DisconnectS2CPacket");
        S2C_PACKETS.put(class_2663.class, "EntityStatusS2CPacket");
        S2C_PACKETS.put(class_2666.class, "UnloadChunkS2CPacket");
        S2C_PACKETS.put(class_2664.class, "ExplosionS2CPacket");
        S2C_PACKETS.put(class_2668.class, "GameStateChangeS2CPacket");
        S2C_PACKETS.put(class_2648.class, "OpenHorseScreenS2CPacket");
        S2C_PACKETS.put(class_2670.class, "KeepAliveS2CPacket");
        S2C_PACKETS.put(class_2673.class, "WorldEventS2CPacket");
        S2C_PACKETS.put(class_2675.class, "ParticleS2CPacket");
        S2C_PACKETS.put(class_2672.class, "ChunkDataS2CPacket");
        S2C_PACKETS.put(class_2658.class, "CustomPayloadS2CPacket");
        S2C_PACKETS.put(class_2660.class, "PlaySoundIdS2CPacket");
        S2C_PACKETS.put(class_2678.class, "GameJoinS2CPacket");
        S2C_PACKETS.put(class_3943.class, "SetTradeOffersS2CPacket");
        S2C_PACKETS.put(class_2692.class, "VehicleMoveS2CPacket");
        S2C_PACKETS.put(class_2684.class, "EntityS2CPacket");
        S2C_PACKETS.put(class_3895.class, "OpenWrittenBookS2CPacket");
        S2C_PACKETS.put(class_3944.class, "OpenScreenS2CPacket");
        S2C_PACKETS.put(class_2676.class, "LightUpdateS2CPacket");
        S2C_PACKETS.put(class_2695.class, "CraftFailedResponseS2CPacket");
        S2C_PACKETS.put(class_2683.class, "MapUpdateS2CPacket");
        S2C_PACKETS.put(class_2696.class, "PlayerAbilitiesS2CPacket");
        S2C_PACKETS.put(class_2698.class, "CombatEventS2CPacket");
        S2C_PACKETS.put(class_2707.class, "LookAtS2CPacket");
        S2C_PACKETS.put(class_2708.class, "PlayerPositionLookS2CPacket");
        S2C_PACKETS.put(class_2703.class, "PlayerListS2CPacket");
        S2C_PACKETS.put(class_2713.class, "UnlockRecipesS2CPacket");
        S2C_PACKETS.put(class_2718.class, "RemoveEntityStatusEffectS2CPacket");
        S2C_PACKETS.put(class_2720.class, "ResourcePackSendS2CPacket");
        S2C_PACKETS.put(class_2726.class, "EntitySetHeadYawS2CPacket");
        S2C_PACKETS.put(class_2724.class, "PlayerRespawnS2CPacket");
        S2C_PACKETS.put(class_2729.class, "SelectAdvancementTabS2CPacket");
        S2C_PACKETS.put(class_2637.class, "ChunkDeltaUpdateS2CPacket");
        S2C_PACKETS.put(class_2734.class, "SetCameraEntityS2CPacket");
        S2C_PACKETS.put(class_2735.class, "HeldItemChangeS2CPacket");
        S2C_PACKETS.put(class_2730.class, "WorldBorderS2CPacket");
        S2C_PACKETS.put(class_4273.class, "ChunkLoadDistanceS2CPacket");
        S2C_PACKETS.put(class_2716.class, "EntitiesDestroyS2CPacket");
        S2C_PACKETS.put(class_2693.class, "SignEditorOpenS2CPacket");
        S2C_PACKETS.put(class_2739.class, "EntityTrackerUpdateS2CPacket");
        S2C_PACKETS.put(class_4282.class, "ChunkRenderDistanceCenterS2CPacket");
        S2C_PACKETS.put(class_2740.class, "EntityAttachS2CPacket");
        S2C_PACKETS.put(class_2736.class, "ScoreboardDisplayS2CPacket");
        S2C_PACKETS.put(class_2743.class, "EntityVelocityUpdateS2CPacket");
        S2C_PACKETS.put(class_2749.class, "HealthUpdateS2CPacket");
        S2C_PACKETS.put(class_2744.class, "EntityEquipmentUpdateS2CPacket");
        S2C_PACKETS.put(class_2759.class, "PlayerSpawnPositionS2CPacket");
        S2C_PACKETS.put(class_2748.class, "ExperienceBarUpdateS2CPacket");
        S2C_PACKETS.put(class_2751.class, "ScoreboardObjectiveUpdateS2CPacket");
        S2C_PACKETS.put(class_2755.class, "TeamS2CPacket");
        S2C_PACKETS.put(class_2752.class, "EntityPassengersSetS2CPacket");
        S2C_PACKETS.put(class_2757.class, "ScoreboardPlayerUpdateS2CPacket");
        S2C_PACKETS.put(class_2762.class, "TitleS2CPacket");
        S2C_PACKETS.put(class_2765.class, "PlaySoundFromEntityS2CPacket");
        S2C_PACKETS.put(class_2761.class, "WorldTimeUpdateS2CPacket");
        S2C_PACKETS.put(class_2772.class, "PlayerListHeaderS2CPacket");
        S2C_PACKETS.put(class_2770.class, "StopSoundS2CPacket");
        S2C_PACKETS.put(class_2775.class, "ItemPickupAnimationS2CPacket");
        S2C_PACKETS.put(class_2774.class, "TagQueryResponseS2CPacket");
        S2C_PACKETS.put(class_2767.class, "PlaySoundS2CPacket");
        S2C_PACKETS.put(class_2779.class, "AdvancementUpdateS2CPacket");
        S2C_PACKETS.put(class_2777.class, "EntityPositionS2CPacket");
        S2C_PACKETS.put(class_2781.class, "EntityAttributesS2CPacket");
        S2C_PACKETS.put(class_2783.class, "EntityStatusEffectS2CPacket");
        S2C_PACKETS.put(class_2790.class, "SynchronizeTagsS2CPacket");
        S2C_PACKETS.put(class_2788.class, "SynchronizeRecipesS2CPacket");
        S2C_PACKETS.put(class_2899.class, "LoginQueryRequestS2CPacket");
        S2C_PACKETS.put(class_2901.class, "LoginSuccessS2CPacket");
        S2C_PACKETS.put(class_2907.class, "LoginCompressionS2CPacket");
        S2C_PACKETS.put(class_2905.class, "LoginHelloS2CPacket");
        S2C_PACKETS.put(class_2909.class, "LoginDisconnectS2CPacket");
        S2C_PACKETS.put(class_2924.class, "QueryResponseS2CPacket");
        S2C_PACKETS.put(class_2923.class, "QueryPongS2CPacket");
        C2S_PACKETS.put(class_2793.class, "TeleportConfirmC2SPacket");
        C2S_PACKETS.put(class_2795.class, "QueryBlockNbtC2SPacket");
        C2S_PACKETS.put(class_4210.class, "UpdateDifficultyC2SPacket");
        C2S_PACKETS.put(class_2797.class, "ChatMessageC2SPacket");
        C2S_PACKETS.put(class_2799.class, "ClientStatusC2SPacket");
        C2S_PACKETS.put(class_2803.class, "ClientSettingsC2SPacket");
        C2S_PACKETS.put(class_2805.class, "RequestCommandCompletionsC2SPacket");
        C2S_PACKETS.put(class_2809.class, "ConfirmScreenActionC2SPacket");
        C2S_PACKETS.put(class_2811.class, "ButtonClickC2SPacket");
        C2S_PACKETS.put(class_2815.class, "CloseHandledScreenC2SPacket");
        C2S_PACKETS.put(class_2813.class, "ClickSlotC2SPacket");
        C2S_PACKETS.put(class_2820.class, "BookUpdateC2SPacket");
        C2S_PACKETS.put(class_2822.class, "QueryEntityNbtC2SPacket");
        C2S_PACKETS.put(class_5194.class, "JigsawGeneratingC2SPacket");
        C2S_PACKETS.put(class_2827.class, "KeepAliveC2SPacket");
        C2S_PACKETS.put(class_4211.class, "UpdateDifficultyLockC2SPacket");
        C2S_PACKETS.put(class_2817.class, "CustomPayloadC2SPacket");
        C2S_PACKETS.put(class_2824.class, "PlayerInteractEntityC2SPacket");
        C2S_PACKETS.put(class_2828.class, "PlayerMoveC2SPacket");
        C2S_PACKETS.put(class_2833.class, "VehicleMoveC2SPacket");
        C2S_PACKETS.put(class_2836.class, "BoatPaddleStateC2SPacket");
        C2S_PACKETS.put(class_2838.class, "PickFromInventoryC2SPacket");
        C2S_PACKETS.put(class_2840.class, "CraftRequestC2SPacket");
        C2S_PACKETS.put(class_2842.class, "UpdatePlayerAbilitiesC2SPacket");
        C2S_PACKETS.put(class_2846.class, "PlayerActionC2SPacket");
        C2S_PACKETS.put(class_2851.class, "PlayerInputC2SPacket");
        C2S_PACKETS.put(class_5427.class, "RecipeCategoryOptionsC2SPacket");
        C2S_PACKETS.put(class_2853.class, "RecipeBookDataC2SPacket");
        C2S_PACKETS.put(class_2855.class, "RenameItemC2SPacket");
        C2S_PACKETS.put(class_2856.class, "ResourcePackStatusC2SPacket");
        C2S_PACKETS.put(class_2848.class, "ClientCommandC2SPacket");
        C2S_PACKETS.put(class_2859.class, "AdvancementTabC2SPacket");
        C2S_PACKETS.put(class_2866.class, "UpdateBeaconC2SPacket");
        C2S_PACKETS.put(class_2868.class, "UpdateSelectedSlotC2SPacket");
        C2S_PACKETS.put(class_2873.class, "CreativeInventoryActionC2SPacket");
        C2S_PACKETS.put(class_2863.class, "SelectMerchantTradeC2SPacket");
        C2S_PACKETS.put(class_2871.class, "UpdateCommandBlockMinecartC2SPacket");
        C2S_PACKETS.put(class_3753.class, "UpdateJigsawC2SPacket");
        C2S_PACKETS.put(class_2877.class, "UpdateSignC2SPacket");
        C2S_PACKETS.put(class_2875.class, "UpdateStructureBlockC2SPacket");
        C2S_PACKETS.put(class_2879.class, "HandSwingC2SPacket");
        C2S_PACKETS.put(class_2870.class, "UpdateCommandBlockC2SPacket");
        C2S_PACKETS.put(class_2885.class, "PlayerInteractBlockC2SPacket");
        C2S_PACKETS.put(class_2886.class, "PlayerInteractItemC2SPacket");
        C2S_PACKETS.put(class_2889.class, "HandshakeC2SPacket");
        C2S_PACKETS.put(class_2884.class, "SpectatorTeleportC2SPacket");
        C2S_PACKETS.put(class_2913.class, "LoginQueryResponseC2SPacket");
        C2S_PACKETS.put(class_2917.class, "LoginKeyC2SPacket");
        C2S_PACKETS.put(class_2915.class, "LoginHelloC2SPacket");
        C2S_PACKETS.put(class_2937.class, "QueryRequestC2SPacket");
        C2S_PACKETS.put(class_2935.class, "QueryPingC2SPacket");
        S2C_PACKETS_R.put("EntitySpawnS2CPacket", class_2604.class);
        S2C_PACKETS_R.put("ExperienceOrbSpawnS2CPacket", class_2606.class);
        S2C_PACKETS_R.put("PaintingSpawnS2CPacket", class_2612.class);
        S2C_PACKETS_R.put("MobSpawnS2CPacket", class_2610.class);
        S2C_PACKETS_R.put("EntityAnimationS2CPacket", class_2616.class);
        S2C_PACKETS_R.put("StatisticsS2CPacket", class_2617.class);
        S2C_PACKETS_R.put("PlayerActionResponseS2CPacket", class_4463.class);
        S2C_PACKETS_R.put("PlayerSpawnS2CPacket", class_2613.class);
        S2C_PACKETS_R.put("BlockBreakingProgressS2CPacket", class_2620.class);
        S2C_PACKETS_R.put("BlockEntityUpdateS2CPacket", class_2622.class);
        S2C_PACKETS_R.put("BlockEventS2CPacket", class_2623.class);
        S2C_PACKETS_R.put("BlockUpdateS2CPacket", class_2626.class);
        S2C_PACKETS_R.put("DifficultyS2CPacket", class_2632.class);
        S2C_PACKETS_R.put("GameMessageS2CPacket", class_2635.class);
        S2C_PACKETS_R.put("BossBarS2CPacket", class_2629.class);
        S2C_PACKETS_R.put("ConfirmScreenActionS2CPacket", class_2644.class);
        S2C_PACKETS_R.put("CommandSuggestionsS2CPacket", class_2639.class);
        S2C_PACKETS_R.put("CloseScreenS2CPacket", class_2645.class);
        S2C_PACKETS_R.put("InventoryS2CPacket", class_2649.class);
        S2C_PACKETS_R.put("ScreenHandlerPropertyUpdateS2CPacket", class_2651.class);
        S2C_PACKETS_R.put("CooldownUpdateS2CPacket", class_2656.class);
        S2C_PACKETS_R.put("ScreenHandlerSlotUpdateS2CPacket", class_2653.class);
        S2C_PACKETS_R.put("CommandTreeS2CPacket", class_2641.class);
        S2C_PACKETS_R.put("DisconnectS2CPacket", class_2661.class);
        S2C_PACKETS_R.put("EntityStatusS2CPacket", class_2663.class);
        S2C_PACKETS_R.put("UnloadChunkS2CPacket", class_2666.class);
        S2C_PACKETS_R.put("ExplosionS2CPacket", class_2664.class);
        S2C_PACKETS_R.put("GameStateChangeS2CPacket", class_2668.class);
        S2C_PACKETS_R.put("OpenHorseScreenS2CPacket", class_2648.class);
        S2C_PACKETS_R.put("KeepAliveS2CPacket", class_2670.class);
        S2C_PACKETS_R.put("WorldEventS2CPacket", class_2673.class);
        S2C_PACKETS_R.put("ParticleS2CPacket", class_2675.class);
        S2C_PACKETS_R.put("ChunkDataS2CPacket", class_2672.class);
        S2C_PACKETS_R.put("CustomPayloadS2CPacket", class_2658.class);
        S2C_PACKETS_R.put("PlaySoundIdS2CPacket", class_2660.class);
        S2C_PACKETS_R.put("GameJoinS2CPacket", class_2678.class);
        S2C_PACKETS_R.put("SetTradeOffersS2CPacket", class_3943.class);
        S2C_PACKETS_R.put("VehicleMoveS2CPacket", class_2692.class);
        S2C_PACKETS_R.put("EntityS2CPacket", class_2684.class);
        S2C_PACKETS_R.put("OpenWrittenBookS2CPacket", class_3895.class);
        S2C_PACKETS_R.put("OpenScreenS2CPacket", class_3944.class);
        S2C_PACKETS_R.put("LightUpdateS2CPacket", class_2676.class);
        S2C_PACKETS_R.put("CraftFailedResponseS2CPacket", class_2695.class);
        S2C_PACKETS_R.put("MapUpdateS2CPacket", class_2683.class);
        S2C_PACKETS_R.put("PlayerAbilitiesS2CPacket", class_2696.class);
        S2C_PACKETS_R.put("CombatEventS2CPacket", class_2698.class);
        S2C_PACKETS_R.put("LookAtS2CPacket", class_2707.class);
        S2C_PACKETS_R.put("PlayerPositionLookS2CPacket", class_2708.class);
        S2C_PACKETS_R.put("PlayerListS2CPacket", class_2703.class);
        S2C_PACKETS_R.put("UnlockRecipesS2CPacket", class_2713.class);
        S2C_PACKETS_R.put("RemoveEntityStatusEffectS2CPacket", class_2718.class);
        S2C_PACKETS_R.put("ResourcePackSendS2CPacket", class_2720.class);
        S2C_PACKETS_R.put("EntitySetHeadYawS2CPacket", class_2726.class);
        S2C_PACKETS_R.put("PlayerRespawnS2CPacket", class_2724.class);
        S2C_PACKETS_R.put("SelectAdvancementTabS2CPacket", class_2729.class);
        S2C_PACKETS_R.put("ChunkDeltaUpdateS2CPacket", class_2637.class);
        S2C_PACKETS_R.put("SetCameraEntityS2CPacket", class_2734.class);
        S2C_PACKETS_R.put("HeldItemChangeS2CPacket", class_2735.class);
        S2C_PACKETS_R.put("WorldBorderS2CPacket", class_2730.class);
        S2C_PACKETS_R.put("ChunkLoadDistanceS2CPacket", class_4273.class);
        S2C_PACKETS_R.put("EntitiesDestroyS2CPacket", class_2716.class);
        S2C_PACKETS_R.put("SignEditorOpenS2CPacket", class_2693.class);
        S2C_PACKETS_R.put("EntityTrackerUpdateS2CPacket", class_2739.class);
        S2C_PACKETS_R.put("ChunkRenderDistanceCenterS2CPacket", class_4282.class);
        S2C_PACKETS_R.put("EntityAttachS2CPacket", class_2740.class);
        S2C_PACKETS_R.put("ScoreboardDisplayS2CPacket", class_2736.class);
        S2C_PACKETS_R.put("EntityVelocityUpdateS2CPacket", class_2743.class);
        S2C_PACKETS_R.put("HealthUpdateS2CPacket", class_2749.class);
        S2C_PACKETS_R.put("EntityEquipmentUpdateS2CPacket", class_2744.class);
        S2C_PACKETS_R.put("PlayerSpawnPositionS2CPacket", class_2759.class);
        S2C_PACKETS_R.put("ExperienceBarUpdateS2CPacket", class_2748.class);
        S2C_PACKETS_R.put("ScoreboardObjectiveUpdateS2CPacket", class_2751.class);
        S2C_PACKETS_R.put("TeamS2CPacket", class_2755.class);
        S2C_PACKETS_R.put("EntityPassengersSetS2CPacket", class_2752.class);
        S2C_PACKETS_R.put("ScoreboardPlayerUpdateS2CPacket", class_2757.class);
        S2C_PACKETS_R.put("TitleS2CPacket", class_2762.class);
        S2C_PACKETS_R.put("PlaySoundFromEntityS2CPacket", class_2765.class);
        S2C_PACKETS_R.put("WorldTimeUpdateS2CPacket", class_2761.class);
        S2C_PACKETS_R.put("PlayerListHeaderS2CPacket", class_2772.class);
        S2C_PACKETS_R.put("StopSoundS2CPacket", class_2770.class);
        S2C_PACKETS_R.put("ItemPickupAnimationS2CPacket", class_2775.class);
        S2C_PACKETS_R.put("TagQueryResponseS2CPacket", class_2774.class);
        S2C_PACKETS_R.put("PlaySoundS2CPacket", class_2767.class);
        S2C_PACKETS_R.put("AdvancementUpdateS2CPacket", class_2779.class);
        S2C_PACKETS_R.put("EntityPositionS2CPacket", class_2777.class);
        S2C_PACKETS_R.put("EntityAttributesS2CPacket", class_2781.class);
        S2C_PACKETS_R.put("EntityStatusEffectS2CPacket", class_2783.class);
        S2C_PACKETS_R.put("SynchronizeTagsS2CPacket", class_2790.class);
        S2C_PACKETS_R.put("SynchronizeRecipesS2CPacket", class_2788.class);
        S2C_PACKETS_R.put("LoginQueryRequestS2CPacket", class_2899.class);
        S2C_PACKETS_R.put("LoginSuccessS2CPacket", class_2901.class);
        S2C_PACKETS_R.put("LoginCompressionS2CPacket", class_2907.class);
        S2C_PACKETS_R.put("LoginHelloS2CPacket", class_2905.class);
        S2C_PACKETS_R.put("LoginDisconnectS2CPacket", class_2909.class);
        S2C_PACKETS_R.put("QueryResponseS2CPacket", class_2924.class);
        S2C_PACKETS_R.put("QueryPongS2CPacket", class_2923.class);
        C2S_PACKETS_R.put("TeleportConfirmC2SPacket", class_2793.class);
        C2S_PACKETS_R.put("QueryBlockNbtC2SPacket", class_2795.class);
        C2S_PACKETS_R.put("UpdateDifficultyC2SPacket", class_4210.class);
        C2S_PACKETS_R.put("ChatMessageC2SPacket", class_2797.class);
        C2S_PACKETS_R.put("ClientStatusC2SPacket", class_2799.class);
        C2S_PACKETS_R.put("ClientSettingsC2SPacket", class_2803.class);
        C2S_PACKETS_R.put("RequestCommandCompletionsC2SPacket", class_2805.class);
        C2S_PACKETS_R.put("ConfirmScreenActionC2SPacket", class_2809.class);
        C2S_PACKETS_R.put("ButtonClickC2SPacket", class_2811.class);
        C2S_PACKETS_R.put("CloseHandledScreenC2SPacket", class_2815.class);
        C2S_PACKETS_R.put("ClickSlotC2SPacket", class_2813.class);
        C2S_PACKETS_R.put("BookUpdateC2SPacket", class_2820.class);
        C2S_PACKETS_R.put("QueryEntityNbtC2SPacket", class_2822.class);
        C2S_PACKETS_R.put("JigsawGeneratingC2SPacket", class_5194.class);
        C2S_PACKETS_R.put("KeepAliveC2SPacket", class_2827.class);
        C2S_PACKETS_R.put("UpdateDifficultyLockC2SPacket", class_4211.class);
        C2S_PACKETS_R.put("CustomPayloadC2SPacket", class_2817.class);
        C2S_PACKETS_R.put("PlayerInteractEntityC2SPacket", class_2824.class);
        C2S_PACKETS_R.put("PlayerMoveC2SPacket", class_2828.class);
        C2S_PACKETS_R.put("VehicleMoveC2SPacket", class_2833.class);
        C2S_PACKETS_R.put("BoatPaddleStateC2SPacket", class_2836.class);
        C2S_PACKETS_R.put("PickFromInventoryC2SPacket", class_2838.class);
        C2S_PACKETS_R.put("CraftRequestC2SPacket", class_2840.class);
        C2S_PACKETS_R.put("UpdatePlayerAbilitiesC2SPacket", class_2842.class);
        C2S_PACKETS_R.put("PlayerActionC2SPacket", class_2846.class);
        C2S_PACKETS_R.put("PlayerInputC2SPacket", class_2851.class);
        C2S_PACKETS_R.put("RecipeCategoryOptionsC2SPacket", class_5427.class);
        C2S_PACKETS_R.put("RecipeBookDataC2SPacket", class_2853.class);
        C2S_PACKETS_R.put("RenameItemC2SPacket", class_2855.class);
        C2S_PACKETS_R.put("ResourcePackStatusC2SPacket", class_2856.class);
        C2S_PACKETS_R.put("ClientCommandC2SPacket", class_2848.class);
        C2S_PACKETS_R.put("AdvancementTabC2SPacket", class_2859.class);
        C2S_PACKETS_R.put("UpdateBeaconC2SPacket", class_2866.class);
        C2S_PACKETS_R.put("UpdateSelectedSlotC2SPacket", class_2868.class);
        C2S_PACKETS_R.put("CreativeInventoryActionC2SPacket", class_2873.class);
        C2S_PACKETS_R.put("SelectMerchantTradeC2SPacket", class_2863.class);
        C2S_PACKETS_R.put("UpdateCommandBlockMinecartC2SPacket", class_2871.class);
        C2S_PACKETS_R.put("UpdateJigsawC2SPacket", class_3753.class);
        C2S_PACKETS_R.put("UpdateSignC2SPacket", class_2877.class);
        C2S_PACKETS_R.put("UpdateStructureBlockC2SPacket", class_2875.class);
        C2S_PACKETS_R.put("HandSwingC2SPacket", class_2879.class);
        C2S_PACKETS_R.put("UpdateCommandBlockC2SPacket", class_2870.class);
        C2S_PACKETS_R.put("PlayerInteractBlockC2SPacket", class_2885.class);
        C2S_PACKETS_R.put("PlayerInteractItemC2SPacket", class_2886.class);
        C2S_PACKETS_R.put("HandshakeC2SPacket", class_2889.class);
        C2S_PACKETS_R.put("SpectatorTeleportC2SPacket", class_2884.class);
        C2S_PACKETS_R.put("LoginQueryResponseC2SPacket", class_2913.class);
        C2S_PACKETS_R.put("LoginKeyC2SPacket", class_2917.class);
        C2S_PACKETS_R.put("LoginHelloC2SPacket", class_2915.class);
        C2S_PACKETS_R.put("QueryRequestC2SPacket", class_2937.class);
        C2S_PACKETS_R.put("QueryPingC2SPacket", class_2935.class);
    }

    static Map access$100() {
        return C2S_PACKETS;
    }

    private static class PacketRegistry
    extends class_2378<Class<? extends class_2596<?>>> {
        @Nullable
        public Class<? extends class_2596<?>> get(@Nullable class_5321<Class<? extends class_2596<?>>> class_53212) {
            return null;
        }

        public Set<Map.Entry<class_5321<Class<? extends class_2596<?>>>, Class<? extends class_2596<?>>>> method_29722() {
            return null;
        }

        @Nullable
        public Object method_29107(@Nullable class_5321 class_53212) {
            return this.get(class_53212);
        }

        @Nullable
        public Class<? extends class_2596<?>> get(int n) {
            return null;
        }

        public boolean method_10250(class_2960 class_29602) {
            return false;
        }

        @Nullable
        public Class<? extends class_2596<?>> get(@Nullable class_2960 class_29602) {
            return null;
        }

        @Nullable
        public Object method_10200(int n) {
            return this.get(n);
        }

        public Set<class_2960> method_10235() {
            return null;
        }

        @Nullable
        public Object method_10223(@Nullable class_2960 class_29602) {
            return this.get(class_29602);
        }

        public Optional method_29113(Object object) {
            return this.getKey((Class)object);
        }

        @Nullable
        public class_2960 getId(Class<? extends class_2596<?>> clazz) {
            return null;
        }

        @Nullable
        public class_2960 method_10221(Object object) {
            return this.getId((Class)object);
        }

        public Optional<class_5321<Class<? extends class_2596<?>>>> getKey(Class<? extends class_2596<?>> clazz) {
            return Optional.empty();
        }

        protected Lifecycle getEntryLifecycle(Class<? extends class_2596<?>> clazz) {
            return null;
        }

        public PacketRegistry() {
            super(class_5321.method_29180((class_2960)new class_2960("meteor-client", "packets")), Lifecycle.stable());
        }

        public Lifecycle method_31138() {
            return null;
        }

        protected Lifecycle method_31139(Object object) {
            return this.getEntryLifecycle((Class)object);
        }

        public int getRawId(@Nullable Class<? extends class_2596<?>> clazz) {
            return 0;
        }

        @NotNull
        public Iterator<Class<? extends class_2596<?>>> iterator() {
            return Iterators.concat(PacketUtils.access$000().keySet().iterator(), PacketUtils.access$100().keySet().iterator());
        }

        public int method_10206(@Nullable Object object) {
            return this.getRawId((Class)object);
        }
    }
}

