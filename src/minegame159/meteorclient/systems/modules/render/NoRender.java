/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_2396
 *  net.minecraft.class_2398
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.ChunkOcclusionEvent;
import minegame159.meteorclient.events.world.ParticleEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.ParticleTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_2396;
import net.minecraft.class_2398;

public class NoRender
extends Module {
    private final /* synthetic */ SettingGroup sgOverlay;
    private final /* synthetic */ Setting<Boolean> noPumpkinOverlay;
    private final /* synthetic */ Setting<Boolean> noFireworkExplosions;
    private final /* synthetic */ Setting<Boolean> noCaveCulling;
    private final /* synthetic */ Setting<Boolean> noArmor;
    private final /* synthetic */ Setting<Boolean> noBlockBreakParticles;
    private final /* synthetic */ Setting<Boolean> noSkylightUpdates;
    private final /* synthetic */ Setting<Boolean> noFog;
    private final /* synthetic */ Setting<Boolean> noPortalOverlay;
    private final /* synthetic */ Setting<Boolean> noWaterOverlay;
    private final /* synthetic */ Setting<Boolean> noEnchTableBook;
    private final /* synthetic */ Setting<Boolean> noSignText;
    private final /* synthetic */ Setting<Boolean> noHurtCam;
    private final /* synthetic */ Setting<Boolean> noVignette;
    private final /* synthetic */ Setting<Boolean> noMapMarkers;
    private final /* synthetic */ Setting<Boolean> noNausea;
    private final /* synthetic */ SettingGroup sgHUD;
    private final /* synthetic */ SettingGroup sgWorld;
    private final /* synthetic */ Setting<Boolean> noWeather;
    private final /* synthetic */ Setting<Boolean> noBarrierInvis;
    private final /* synthetic */ Setting<Boolean> noFireOverlay;
    private final /* synthetic */ Setting<Boolean> noEatParticles;
    private final /* synthetic */ Setting<Boolean> noBlockBreakOverlay;
    private final /* synthetic */ Setting<Boolean> noInvisibility;
    private final /* synthetic */ Setting<List<class_2396<?>>> particles;
    private final /* synthetic */ Setting<Boolean> noFallingBlocks;
    private final /* synthetic */ Setting<Boolean> noGuiBackground;
    private final /* synthetic */ Setting<Boolean> noCrosshair;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Boolean> noTotemAnimation;
    private final /* synthetic */ Setting<Boolean> noInWallOverlay;
    private final /* synthetic */ Setting<Boolean> noBossBar;
    private final /* synthetic */ Setting<Boolean> noPotionIcons;
    private final /* synthetic */ Setting<Boolean> noScoreboard;
    private final /* synthetic */ Setting<BannerRenderMode> bannerRender;
    private final /* synthetic */ SettingGroup sgEntity;

    public boolean noArmor() {
        NoRender llllllllllllllllIlIlIlllllIIlIII;
        return llllllllllllllllIlIlIlllllIIlIII.isActive() && llllllllllllllllIlIlIlllllIIlIII.noArmor.get() != false;
    }

    public boolean noBossBar() {
        NoRender llllllllllllllllIlIllIIIIIIIlIIl;
        return llllllllllllllllIlIllIIIIIIIlIIl.isActive() && llllllllllllllllIlIllIIIIIIIlIIl.noBossBar.get() != false;
    }

    public boolean noBarrierInvis() {
        NoRender llllllllllllllllIlIlIlllllIlIIII;
        return llllllllllllllllIlIlIlllllIlIIII.isActive() && llllllllllllllllIlIlIlllllIlIIII.noBarrierInvis.get() != false;
    }

    public boolean noEntity(class_1297 llllllllllllllllIlIlIlllllIIllII) {
        NoRender llllllllllllllllIlIlIlllllIIllIl;
        return llllllllllllllllIlIlIlllllIIllIl.isActive() && llllllllllllllllIlIlIlllllIIllIl.entities.get().getBoolean((Object)llllllllllllllllIlIlIlllllIIllII.method_5864());
    }

    public boolean noWaterOverlay() {
        NoRender llllllllllllllllIlIllIIIIIIlllII;
        return llllllllllllllllIlIllIIIIIIlllII.isActive() && llllllllllllllllIlIllIIIIIIlllII.noWaterOverlay.get() != false;
    }

    public boolean noBlockBreakParticles() {
        NoRender llllllllllllllllIlIlIlllllllIIlI;
        return llllllllllllllllIlIlIlllllllIIlI.isActive() && llllllllllllllllIlIlIlllllllIIlI.noBlockBreakParticles.get() != false;
    }

    public boolean noFireOverlay() {
        NoRender llllllllllllllllIlIllIIIIIIlllll;
        return llllllllllllllllIlIllIIIIIIlllll.isActive() && llllllllllllllllIlIllIIIIIIlllll.noFireOverlay.get() != false;
    }

    public boolean noBlockBreakOverlay() {
        NoRender llllllllllllllllIlIlIllllllIllll;
        return llllllllllllllllIlIlIllllllIllll.isActive() && llllllllllllllllIlIlIllllllIllll.noBlockBreakOverlay.get() != false;
    }

    public boolean noPotionIcons() {
        NoRender llllllllllllllllIlIllIIIIIIIIIIl;
        return llllllllllllllllIlIllIIIIIIIIIIl.isActive() && llllllllllllllllIlIllIIIIIIIIIIl.noPotionIcons.get() != false;
    }

    public BannerRenderMode getBannerRenderMode() {
        NoRender llllllllllllllllIlIlIlllllIlllIl;
        if (!llllllllllllllllIlIlIlllllIlllIl.isActive()) {
            return BannerRenderMode.Everything;
        }
        return llllllllllllllllIlIlIlllllIlllIl.bannerRender.get();
    }

    public boolean noHurtCam() {
        NoRender llllllllllllllllIlIllIIIIIlIlIlI;
        return llllllllllllllllIlIllIIIIIlIlIlI.isActive() && llllllllllllllllIlIllIIIIIlIlIlI.noHurtCam.get() != false;
    }

    public boolean noSkylightUpdates() {
        NoRender llllllllllllllllIlIlIllllllIlIll;
        return llllllllllllllllIlIlIllllllIlIll.isActive() && llllllllllllllllIlIlIllllllIlIll.noSkylightUpdates.get() != false;
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent llllllllllllllllIlIlIllllllIIlII) {
        NoRender llllllllllllllllIlIlIllllllIIIll;
        if (llllllllllllllllIlIlIllllllIIIll.noCaveCulling.get().booleanValue()) {
            llllllllllllllllIlIlIllllllIIlII.cancel();
        }
    }

    public boolean noFog() {
        NoRender llllllllllllllllIlIlIllllllllIlI;
        return llllllllllllllllIlIlIllllllllIlI.isActive() && llllllllllllllllIlIlIllllllllIlI.noFog.get() != false;
    }

    public boolean noCrosshair() {
        NoRender llllllllllllllllIlIllIIIIIIIIIll;
        return llllllllllllllllIlIllIIIIIIIIIll.isActive() && llllllllllllllllIlIllIIIIIIIIIll.noCrosshair.get() != false;
    }

    public boolean noInWallOverlay() {
        NoRender llllllllllllllllIlIllIIIIIIllIIl;
        return llllllllllllllllIlIllIIIIIIllIIl.isActive() && llllllllllllllllIlIllIIIIIIllIIl.noInWallOverlay.get() != false;
    }

    public boolean noNausea() {
        NoRender llllllllllllllllIlIllIIIIIlIIlII;
        return llllllllllllllllIlIllIIIIIlIIlII.isActive() && llllllllllllllllIlIllIIIIIlIIlII.noNausea.get() != false;
    }

    public boolean noEnchTableBook() {
        NoRender llllllllllllllllIlIlIlllllllIlll;
        return llllllllllllllllIlIlIlllllllIlll.isActive() && llllllllllllllllIlIlIlllllllIlll.noEnchTableBook.get() != false;
    }

    public boolean noFallingBlocks() {
        NoRender llllllllllllllllIlIlIllllllIlIII;
        return llllllllllllllllIlIlIllllllIlIII.isActive() && llllllllllllllllIlIlIllllllIlIII.noFallingBlocks.get() != false;
    }

    public boolean noFireworkExplosions() {
        NoRender llllllllllllllllIlIlIlllllIllIIl;
        return llllllllllllllllIlIlIlllllIllIIl.isActive() && llllllllllllllllIlIlIlllllIllIIl.noFireworkExplosions.get() != false;
    }

    public boolean noScoreboard() {
        NoRender llllllllllllllllIlIllIIIIIIIIlll;
        return llllllllllllllllIlIllIIIIIIIIlll.isActive() && llllllllllllllllIlIllIIIIIIIIlll.noScoreboard.get() != false;
    }

    public boolean noSignText() {
        NoRender llllllllllllllllIlIlIlllllllIlIl;
        return llllllllllllllllIlIlIlllllllIlIl.isActive() && llllllllllllllllIlIlIlllllllIlIl.noSignText.get() != false;
    }

    public boolean noGuiBackground() {
        NoRender llllllllllllllllIlIllIIIIIIlIIlI;
        return llllllllllllllllIlIllIIIIIIlIIlI.isActive() && llllllllllllllllIlIllIIIIIIlIIlI.noGuiBackground.get() != false;
    }

    public boolean noMapMarkers() {
        NoRender llllllllllllllllIlIlIlllllIlllll;
        return llllllllllllllllIlIlIlllllIlllll.isActive() && llllllllllllllllIlIlIlllllIlllll.noMapMarkers.get() != false;
    }

    public boolean noInvisibility() {
        NoRender llllllllllllllllIlIlIlllllIIIlII;
        return llllllllllllllllIlIlIlllllIIIlII.isActive() && llllllllllllllllIlIlIlllllIIIlII.noInvisibility.get() != false;
    }

    public boolean noPumpkinOverlay() {
        NoRender llllllllllllllllIlIllIIIIIlIIIlI;
        return llllllllllllllllIlIllIIIIIlIIIlI.isActive() && llllllllllllllllIlIllIIIIIlIIIlI.noPumpkinOverlay.get() != false;
    }

    public boolean noPortalOverlay() {
        NoRender llllllllllllllllIlIllIIIIIlIlIII;
        return llllllllllllllllIlIllIIIIIlIlIII.isActive() && llllllllllllllllIlIllIIIIIlIlIII.noPortalOverlay.get() != false;
    }

    public boolean noEatParticles() {
        NoRender llllllllllllllllIlIllIIIIIIIllIl;
        return llllllllllllllllIlIllIIIIIIIllIl.isActive() && llllllllllllllllIlIllIIIIIIIllIl.noEatParticles.get() != false;
    }

    @EventHandler
    private void onAddParticle(ParticleEvent llllllllllllllllIlIlIlllllIlIlIl) {
        NoRender llllllllllllllllIlIlIlllllIlIlII;
        if (llllllllllllllllIlIlIlllllIlIlII.noWeather.get().booleanValue() && llllllllllllllllIlIlIlllllIlIlIl.particle.method_10295() == class_2398.field_11242) {
            llllllllllllllllIlIlIlllllIlIlIl.cancel();
        } else if (llllllllllllllllIlIlIlllllIlIlII.noFireworkExplosions.get().booleanValue() && llllllllllllllllIlIlIlllllIlIlIl.particle.method_10295() == class_2398.field_11248) {
            llllllllllllllllIlIlIlllllIlIlIl.cancel();
        } else if (llllllllllllllllIlIlIlllllIlIlII.particles.get().contains((Object)llllllllllllllllIlIlIlllllIlIlIl.particle.method_10295())) {
            llllllllllllllllIlIlIlllllIlIlIl.cancel();
        }
    }

    public boolean noTotemAnimation() {
        NoRender llllllllllllllllIlIllIIIIIIlIIII;
        return llllllllllllllllIlIllIIIIIIlIIII.isActive() && llllllllllllllllIlIllIIIIIIlIIII.noTotemAnimation.get() != false;
    }

    public boolean noWeather() {
        NoRender llllllllllllllllIlIlIlllllllllIl;
        return llllllllllllllllIlIlIlllllllllIl.isActive() && llllllllllllllllIlIlIlllllllllIl.noWeather.get() != false;
    }

    public NoRender() {
        super(Categories.Render, "no-render", "Disables certain animations or overlays from rendering.");
        NoRender llllllllllllllllIlIllIIIIIlIllIl;
        llllllllllllllllIlIllIIIIIlIllIl.sgOverlay = llllllllllllllllIlIllIIIIIlIllIl.settings.createGroup("Overlay");
        llllllllllllllllIlIllIIIIIlIllIl.sgHUD = llllllllllllllllIlIllIIIIIlIllIl.settings.createGroup("HUD");
        llllllllllllllllIlIllIIIIIlIllIl.sgWorld = llllllllllllllllIlIllIIIIIlIllIl.settings.createGroup("World");
        llllllllllllllllIlIllIIIIIlIllIl.sgEntity = llllllllllllllllIlIllIIIIIlIllIl.settings.createGroup("Entity");
        llllllllllllllllIlIllIIIIIlIllIl.noHurtCam = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("hurt-cam").description("Disables rendering of the hurt camera effect.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noPortalOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("portal-overlay").description("Disables rendering of the nether portal overlay.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noNausea = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("nausea").description("Disables rendering of the nausea overlay.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noPumpkinOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("pumpkin-overlay").description("Disables rendering of the pumpkin head overlay").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noFireOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("fire-overlay").description("Disables rendering of the fire overlay.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noWaterOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("water-overlay").description("Disables rendering of the water overlay.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noInWallOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("in-wall-overlay").description("Disables rendering of the overlay when inside blocks.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noVignette = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("vignette").description("Disables rendering of the vignette overlay.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noGuiBackground = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("gui-background").description("Disables rendering of the GUI background overlay.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noTotemAnimation = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("totem-animation").description("Disables rendering of the totem animation when you pop a totem.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noEatParticles = llllllllllllllllIlIllIIIIIlIllIl.sgOverlay.add(new BoolSetting.Builder().name("eating-particles").description("Disables rendering of eating particles.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noBossBar = llllllllllllllllIlIllIIIIIlIllIl.sgHUD.add(new BoolSetting.Builder().name("boss-bar").description("Disable rendering of boss bars.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noScoreboard = llllllllllllllllIlIllIIIIIlIllIl.sgHUD.add(new BoolSetting.Builder().name("scoreboard").description("Disable rendering of the scoreboard.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noCrosshair = llllllllllllllllIlIllIIIIIlIllIl.sgHUD.add(new BoolSetting.Builder().name("crosshair").description("Disables rendering of the crosshair.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noPotionIcons = llllllllllllllllIlIllIIIIIlIllIl.sgHUD.add(new BoolSetting.Builder().name("potion-icons").description("Disables rendering of status effect icons.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noWeather = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("weather").description("Disables rendering of weather.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noFog = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("fog").description("Disables rendering of fog.").defaultValue(true).build());
        llllllllllllllllIlIllIIIIIlIllIl.noEnchTableBook = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("enchantment-table-book").description("Disables rendering of books above enchanting tables.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noSignText = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("sign-text").description("Disables rendering of text on signs.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noBlockBreakParticles = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("block-break-particles").description("Disables rendering of block-break particles.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noBlockBreakOverlay = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("block-break-overlay").description("Disables rendering of block-break overlay.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noSkylightUpdates = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("skylight-updates").description("Disables rendering of skylight updates.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noFallingBlocks = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("falling-blocks").description("Disables rendering of falling blocks.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noCaveCulling = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("cave-culling").description("Disables Minecraft's cave culling algorithm.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noMapMarkers = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("map-markers").description("Disables markers on maps.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.bannerRender = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new EnumSetting.Builder().name("banners").description("Changes rendering of banners.").defaultValue(BannerRenderMode.Everything).build());
        llllllllllllllllIlIllIIIIIlIllIl.noFireworkExplosions = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("firework-explosions").description("Disables rendering of firework explosions.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.particles = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new ParticleTypeListSetting.Builder().name("particles").description("Particles to not render.").defaultValue(new ArrayList(0)).build());
        llllllllllllllllIlIllIIIIIlIllIl.noBarrierInvis = llllllllllllllllIlIllIIIIIlIllIl.sgWorld.add(new BoolSetting.Builder().name("barrier-invisibility").description("Disables barriers being invisible when not holding one.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.entities = llllllllllllllllIlIllIIIIIlIllIl.sgEntity.add(new EntityTypeListSetting.Builder().name("entities").description("Disables rendering of selected entities.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).build());
        llllllllllllllllIlIllIIIIIlIllIl.noArmor = llllllllllllllllIlIllIIIIIlIllIl.sgEntity.add(new BoolSetting.Builder().name("armor").description("Disables rendering of armor on entities.").defaultValue(false).build());
        llllllllllllllllIlIllIIIIIlIllIl.noInvisibility = llllllllllllllllIlIllIIIIIlIllIl.sgEntity.add(new BoolSetting.Builder().name("invisibility").description("Shows invisible entities.").defaultValue(false).build());
    }

    public boolean noVignette() {
        NoRender llllllllllllllllIlIllIIIIIIlIlIl;
        return llllllllllllllllIlIllIIIIIIlIlIl.isActive() && llllllllllllllllIlIllIIIIIIlIlIl.noVignette.get() != false;
    }

    public static enum BannerRenderMode {
        Everything,
        Pillar,
        None;


        private BannerRenderMode() {
            BannerRenderMode lIllIIIlllllII;
        }
    }
}

