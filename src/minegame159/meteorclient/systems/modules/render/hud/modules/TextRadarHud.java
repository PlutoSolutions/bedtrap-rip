/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_742
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_742;

public class TextRadarHud
extends HudElement {
    private final Setting<Boolean> distance;
    private final List<class_742> players;
    private final Setting<Boolean> friends;
    private final SettingGroup sgGeneral;

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        hudRenderer.text("Players:", d, d2, this.hud.secondaryColor.get());
        if (this.mc.field_1687 == null) {
            return;
        }
        for (class_1657 class_16572 : this.getPlayers()) {
            if (class_16572.equals((Object)this.mc.field_1724) || !this.friends.get().booleanValue() && Friends.get().isFriend(class_16572)) continue;
            d = this.box.getX();
            String string = class_16572.method_5820();
            Color color = PlayerUtils.getPlayerColor(class_16572, this.hud.primaryColor.get());
            hudRenderer.text(string, d, d2 += hudRenderer.textHeight() + 2.0, color);
            if (!this.distance.get().booleanValue()) continue;
            d += hudRenderer.textWidth(String.valueOf(new StringBuilder().append(string).append(" ")));
            string = String.format("(%sm)", Math.round(this.mc.method_1560().method_5739((class_1297)class_16572)));
            color = this.hud.secondaryColor.get();
            hudRenderer.text(string, d, d2, color);
        }
    }

    private List<class_742> getPlayers() {
        this.players.clear();
        this.players.addAll(this.mc.field_1687.method_18456());
        this.players.sort(Comparator.comparingDouble(this::lambda$getPlayers$0));
        return this.players;
    }

    public TextRadarHud(HUD hUD) {
        super(hUD, "player-info", "Displays players in your visual range.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.distance = this.sgGeneral.add(new BoolSetting.Builder().name("distance").description("Shows the distance to the player next to their name.").defaultValue(false).build());
        this.friends = this.sgGeneral.add(new BoolSetting.Builder().name("display-friends").description("Whether to show friends or not.").defaultValue(true).build());
        this.players = new ArrayList<class_742>();
    }

    private double lambda$getPlayers$0(class_742 class_7422) {
        return class_7422.method_5739(this.mc.method_1560());
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        double d = hudRenderer.textWidth("Players:");
        double d2 = hudRenderer.textHeight();
        if (this.mc.field_1687 == null) {
            this.box.setSize(d, d2);
            return;
        }
        for (class_1657 class_16572 : this.getPlayers()) {
            if (class_16572.equals((Object)this.mc.field_1724) || !this.friends.get().booleanValue() && Friends.get().isFriend(class_16572)) continue;
            String string = class_16572.method_5820();
            if (this.distance.get().booleanValue()) {
                string = String.valueOf(new StringBuilder().append(string).append(String.format("(%sm)", Math.round(this.mc.method_1560().method_5739((class_1297)class_16572)))));
            }
            d = Math.max(d, hudRenderer.textWidth(string));
            d2 += hudRenderer.textHeight() + 2.0;
        }
        this.box.setSize(d, d2);
    }
}

