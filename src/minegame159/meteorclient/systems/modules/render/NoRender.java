/*
 * Decompiled with CFR 0.151.
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
    private final SettingGroup sgOverlay;
    private final Setting<Boolean> noPumpkinOverlay;
    private final Setting<Boolean> noFireworkExplosions;
    private final Setting<Boolean> noCaveCulling;
    private final Setting<Boolean> noArmor;
    private final Setting<Boolean> noBlockBreakParticles;
    private final Setting<Boolean> noSkylightUpdates;
    private final Setting<Boolean> noFog;
    private final Setting<Boolean> noPortalOverlay;
    private final Setting<Boolean> noWaterOverlay;
    private final Setting<Boolean> noEnchTableBook;
    private final Setting<Boolean> noSignText;
    private final Setting<Boolean> noHurtCam;
    private final Setting<Boolean> noVignette;
    private final Setting<Boolean> noMapMarkers;
    private final Setting<Boolean> noNausea;
    private final SettingGroup sgHUD;
    private final SettingGroup sgWorld;
    private final Setting<Boolean> noWeather;
    private final Setting<Boolean> noBarrierInvis;
    private final Setting<Boolean> noFireOverlay;
    private final Setting<Boolean> noEatParticles;
    private final Setting<Boolean> noBlockBreakOverlay;
    private final Setting<Boolean> noInvisibility;
    private final Setting<List<class_2396<?>>> particles;
    private final Setting<Boolean> noFallingBlocks;
    private final Setting<Boolean> noGuiBackground;
    private final Setting<Boolean> noCrosshair;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Boolean> noTotemAnimation;
    private final Setting<Boolean> noInWallOverlay;
    private final Setting<Boolean> noBossBar;
    private final Setting<Boolean> noPotionIcons;
    private final Setting<Boolean> noScoreboard;
    private final Setting<BannerRenderMode> bannerRender;
    private final SettingGroup sgEntity;

    public boolean noArmor() {
        return this.isActive() && this.noArmor.get() != false;
    }

    public boolean noBossBar() {
        return this.isActive() && this.noBossBar.get() != false;
    }

    public boolean noBarrierInvis() {
        return this.isActive() && this.noBarrierInvis.get() != false;
    }

    public boolean noEntity(class_1297 class_12972) {
        return this.isActive() && this.entities.get().getBoolean((Object)class_12972.method_5864());
    }

    public boolean noWaterOverlay() {
        return this.isActive() && this.noWaterOverlay.get() != false;
    }

    public boolean noBlockBreakParticles() {
        return this.isActive() && this.noBlockBreakParticles.get() != false;
    }

    public boolean noFireOverlay() {
        return this.isActive() && this.noFireOverlay.get() != false;
    }

    public boolean noBlockBreakOverlay() {
        return this.isActive() && this.noBlockBreakOverlay.get() != false;
    }

    public boolean noPotionIcons() {
        return this.isActive() && this.noPotionIcons.get() != false;
    }

    public BannerRenderMode getBannerRenderMode() {
        if (!this.isActive()) {
            return BannerRenderMode.Everything;
        }
        return this.bannerRender.get();
    }

    public boolean noHurtCam() {
        return this.isActive() && this.noHurtCam.get() != false;
    }

    public boolean noSkylightUpdates() {
        return this.isActive() && this.noSkylightUpdates.get() != false;
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent chunkOcclusionEvent) {
        if (this.noCaveCulling.get().booleanValue()) {
            chunkOcclusionEvent.cancel();
        }
    }

    public boolean noFog() {
        return this.isActive() && this.noFog.get() != false;
    }

    public boolean noCrosshair() {
        return this.isActive() && this.noCrosshair.get() != false;
    }

    public boolean noInWallOverlay() {
        return this.isActive() && this.noInWallOverlay.get() != false;
    }

    public boolean noNausea() {
        return this.isActive() && this.noNausea.get() != false;
    }

    public boolean noEnchTableBook() {
        return this.isActive() && this.noEnchTableBook.get() != false;
    }

    public boolean noFallingBlocks() {
        return this.isActive() && this.noFallingBlocks.get() != false;
    }

    public boolean noFireworkExplosions() {
        return this.isActive() && this.noFireworkExplosions.get() != false;
    }

    public boolean noScoreboard() {
        return this.isActive() && this.noScoreboard.get() != false;
    }

    public boolean noSignText() {
        return this.isActive() && this.noSignText.get() != false;
    }

    public boolean noGuiBackground() {
        return this.isActive() && this.noGuiBackground.get() != false;
    }

    public boolean noMapMarkers() {
        return this.isActive() && this.noMapMarkers.get() != false;
    }

    public boolean noInvisibility() {
        return this.isActive() && this.noInvisibility.get() != false;
    }

    public boolean noPumpkinOverlay() {
        return this.isActive() && this.noPumpkinOverlay.get() != false;
    }

    public boolean noPortalOverlay() {
        return this.isActive() && this.noPortalOverlay.get() != false;
    }

    public boolean noEatParticles() {
        return this.isActive() && this.noEatParticles.get() != false;
    }

    @EventHandler
    private void onAddParticle(ParticleEvent particleEvent) {
        if (this.noWeather.get().booleanValue() && particleEvent.particle.method_10295() == class_2398.field_11242) {
            particleEvent.cancel();
        } else if (this.noFireworkExplosions.get().booleanValue() && particleEvent.particle.method_10295() == class_2398.field_11248) {
            particleEvent.cancel();
        } else if (this.particles.get().contains(particleEvent.particle.method_10295())) {
            particleEvent.cancel();
        }
    }

    public boolean noTotemAnimation() {
        return this.isActive() && this.noTotemAnimation.get() != false;
    }

    public boolean noWeather() {
        return this.isActive() && this.noWeather.get() != false;
    }

    public NoRender() {
        super(Categories.Render, "no-render", "Disables certain animations or overlays from rendering.");
        this.sgOverlay = this.settings.createGroup("Overlay");
        this.sgHUD = this.settings.createGroup("HUD");
        this.sgWorld = this.settings.createGroup("World");
        this.sgEntity = this.settings.createGroup("Entity");
        this.noHurtCam = this.sgOverlay.add(new BoolSetting.Builder().name("hurt-cam").description("Disables rendering of the hurt camera effect.").defaultValue(true).build());
        this.noPortalOverlay = this.sgOverlay.add(new BoolSetting.Builder().name("portal-overlay").description("Disables rendering of the nether portal overlay.").defaultValue(true).build());
        this.noNausea = this.sgOverlay.add(new BoolSetting.Builder().name("nausea").description("Disables rendering of the nausea overlay.").defaultValue(true).build());
        this.noPumpkinOverlay = this.sgOverlay.add(new BoolSetting.Builder().name("pumpkin-overlay").description("Disables rendering of the pumpkin head overlay").defaultValue(true).build());
        this.noFireOverlay = this.sgOverlay.add(new BoolSetting.Builder().name("fire-overlay").description("Disables rendering of the fire overlay.").defaultValue(true).build());
        this.noWaterOverlay = this.sgOverlay.add(new BoolSetting.Builder().name("water-overlay").description("Disables rendering of the water overlay.").defaultValue(true).build());
        this.noInWallOverlay = this.sgOverlay.add(new BoolSetting.Builder().name("in-wall-overlay").description("Disables rendering of the overlay when inside blocks.").defaultValue(true).build());
        this.noVignette = this.sgOverlay.add(new BoolSetting.Builder().name("vignette").description("Disables rendering of the vignette overlay.").defaultValue(true).build());
        this.noGuiBackground = this.sgOverlay.add(new BoolSetting.Builder().name("gui-background").description("Disables rendering of the GUI background overlay.").defaultValue(false).build());
        this.noTotemAnimation = this.sgOverlay.add(new BoolSetting.Builder().name("totem-animation").description("Disables rendering of the totem animation when you pop a totem.").defaultValue(true).build());
        this.noEatParticles = this.sgOverlay.add(new BoolSetting.Builder().name("eating-particles").description("Disables rendering of eating particles.").defaultValue(false).build());
        this.noBossBar = this.sgHUD.add(new BoolSetting.Builder().name("boss-bar").description("Disable rendering of boss bars.").defaultValue(true).build());
        this.noScoreboard = this.sgHUD.add(new BoolSetting.Builder().name("scoreboard").description("Disable rendering of the scoreboard.").defaultValue(true).build());
        this.noCrosshair = this.sgHUD.add(new BoolSetting.Builder().name("crosshair").description("Disables rendering of the crosshair.").defaultValue(false).build());
        this.noPotionIcons = this.sgHUD.add(new BoolSetting.Builder().name("potion-icons").description("Disables rendering of status effect icons.").defaultValue(false).build());
        this.noWeather = this.sgWorld.add(new BoolSetting.Builder().name("weather").description("Disables rendering of weather.").defaultValue(true).build());
        this.noFog = this.sgWorld.add(new BoolSetting.Builder().name("fog").description("Disables rendering of fog.").defaultValue(true).build());
        this.noEnchTableBook = this.sgWorld.add(new BoolSetting.Builder().name("enchantment-table-book").description("Disables rendering of books above enchanting tables.").defaultValue(false).build());
        this.noSignText = this.sgWorld.add(new BoolSetting.Builder().name("sign-text").description("Disables rendering of text on signs.").defaultValue(false).build());
        this.noBlockBreakParticles = this.sgWorld.add(new BoolSetting.Builder().name("block-break-particles").description("Disables rendering of block-break particles.").defaultValue(false).build());
        this.noBlockBreakOverlay = this.sgWorld.add(new BoolSetting.Builder().name("block-break-overlay").description("Disables rendering of block-break overlay.").defaultValue(false).build());
        this.noSkylightUpdates = this.sgWorld.add(new BoolSetting.Builder().name("skylight-updates").description("Disables rendering of skylight updates.").defaultValue(false).build());
        this.noFallingBlocks = this.sgWorld.add(new BoolSetting.Builder().name("falling-blocks").description("Disables rendering of falling blocks.").defaultValue(false).build());
        this.noCaveCulling = this.sgWorld.add(new BoolSetting.Builder().name("cave-culling").description("Disables Minecraft's cave culling algorithm.").defaultValue(false).build());
        this.noMapMarkers = this.sgWorld.add(new BoolSetting.Builder().name("map-markers").description("Disables markers on maps.").defaultValue(false).build());
        this.bannerRender = this.sgWorld.add(new EnumSetting.Builder().name("banners").description("Changes rendering of banners.").defaultValue(BannerRenderMode.Everything).build());
        this.noFireworkExplosions = this.sgWorld.add(new BoolSetting.Builder().name("firework-explosions").description("Disables rendering of firework explosions.").defaultValue(false).build());
        this.particles = this.sgWorld.add(new ParticleTypeListSetting.Builder().name("particles").description("Particles to not render.").defaultValue(new ArrayList(0)).build());
        this.noBarrierInvis = this.sgWorld.add(new BoolSetting.Builder().name("barrier-invisibility").description("Disables barriers being invisible when not holding one.").defaultValue(false).build());
        this.entities = this.sgEntity.add(new EntityTypeListSetting.Builder().name("entities").description("Disables rendering of selected entities.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).build());
        this.noArmor = this.sgEntity.add(new BoolSetting.Builder().name("armor").description("Disables rendering of armor on entities.").defaultValue(false).build());
        this.noInvisibility = this.sgEntity.add(new BoolSetting.Builder().name("invisibility").description("Shows invisible entities.").defaultValue(false).build());
    }

    public boolean noVignette() {
        return this.isActive() && this.noVignette.get() != false;
    }

    public static enum BannerRenderMode {
        Everything,
        Pillar,
        None;

    }
}

