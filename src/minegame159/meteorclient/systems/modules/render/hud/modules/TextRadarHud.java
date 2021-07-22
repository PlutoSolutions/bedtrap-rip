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
    private final /* synthetic */ Setting<Boolean> distance;
    private final /* synthetic */ List<class_742> players;
    private final /* synthetic */ Setting<Boolean> friends;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void render(HudRenderer llllllllllllllllIlIlIlIIIlllllll) {
        TextRadarHud llllllllllllllllIlIlIlIIIlllllII;
        double llllllllllllllllIlIlIlIIIllllllI = llllllllllllllllIlIlIlIIIlllllII.box.getX();
        double llllllllllllllllIlIlIlIIIlllllIl = llllllllllllllllIlIlIlIIIlllllII.box.getY();
        llllllllllllllllIlIlIlIIIlllllll.text("Players:", llllllllllllllllIlIlIlIIIllllllI, llllllllllllllllIlIlIlIIIlllllIl, llllllllllllllllIlIlIlIIIlllllII.hud.secondaryColor.get());
        if (llllllllllllllllIlIlIlIIIlllllII.mc.field_1687 == null) {
            return;
        }
        for (class_1657 class_16572 : llllllllllllllllIlIlIlIIIlllllII.getPlayers()) {
            if (class_16572.equals((Object)llllllllllllllllIlIlIlIIIlllllII.mc.field_1724) || !llllllllllllllllIlIlIlIIIlllllII.friends.get().booleanValue() && Friends.get().isFriend(class_16572)) continue;
            llllllllllllllllIlIlIlIIIllllllI = llllllllllllllllIlIlIlIIIlllllII.box.getX();
            String llllllllllllllllIlIlIlIIlIIIIIll = class_16572.method_5820();
            Color llllllllllllllllIlIlIlIIlIIIIIlI = PlayerUtils.getPlayerColor(class_16572, llllllllllllllllIlIlIlIIIlllllII.hud.primaryColor.get());
            llllllllllllllllIlIlIlIIIlllllll.text(llllllllllllllllIlIlIlIIlIIIIIll, llllllllllllllllIlIlIlIIIllllllI, llllllllllllllllIlIlIlIIIlllllIl += llllllllllllllllIlIlIlIIIlllllll.textHeight() + 2.0, llllllllllllllllIlIlIlIIlIIIIIlI);
            if (!llllllllllllllllIlIlIlIIIlllllII.distance.get().booleanValue()) continue;
            llllllllllllllllIlIlIlIIIllllllI += llllllllllllllllIlIlIlIIIlllllll.textWidth(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIlIIlIIIIIll).append(" ")));
            llllllllllllllllIlIlIlIIlIIIIIll = String.format("(%sm)", Math.round(llllllllllllllllIlIlIlIIIlllllII.mc.method_1560().method_5739((class_1297)class_16572)));
            llllllllllllllllIlIlIlIIlIIIIIlI = llllllllllllllllIlIlIlIIIlllllII.hud.secondaryColor.get();
            llllllllllllllllIlIlIlIIIlllllll.text(llllllllllllllllIlIlIlIIlIIIIIll, llllllllllllllllIlIlIlIIIllllllI, llllllllllllllllIlIlIlIIIlllllIl, llllllllllllllllIlIlIlIIlIIIIIlI);
        }
    }

    private List<class_742> getPlayers() {
        TextRadarHud llllllllllllllllIlIlIlIIIlllIIll;
        llllllllllllllllIlIlIlIIIlllIIll.players.clear();
        llllllllllllllllIlIlIlIIIlllIIll.players.addAll(llllllllllllllllIlIlIlIIIlllIIll.mc.field_1687.method_18456());
        llllllllllllllllIlIlIlIIIlllIIll.players.sort(Comparator.comparingDouble(llllllllllllllllIlIlIlIIIllIllII -> {
            TextRadarHud llllllllllllllllIlIlIlIIIllIllIl;
            return llllllllllllllllIlIlIlIIIllIllII.method_5739(llllllllllllllllIlIlIlIIIllIllIl.mc.method_1560());
        }));
        return llllllllllllllllIlIlIlIIIlllIIll.players;
    }

    public TextRadarHud(HUD llllllllllllllllIlIlIlIIlIlIIIII) {
        super(llllllllllllllllIlIlIlIIlIlIIIII, "player-info", "Displays players in your visual range.", false);
        TextRadarHud llllllllllllllllIlIlIlIIlIlIIIIl;
        llllllllllllllllIlIlIlIIlIlIIIIl.sgGeneral = llllllllllllllllIlIlIlIIlIlIIIIl.settings.getDefaultGroup();
        llllllllllllllllIlIlIlIIlIlIIIIl.distance = llllllllllllllllIlIlIlIIlIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("distance").description("Shows the distance to the player next to their name.").defaultValue(false).build());
        llllllllllllllllIlIlIlIIlIlIIIIl.friends = llllllllllllllllIlIlIlIIlIlIIIIl.sgGeneral.add(new BoolSetting.Builder().name("display-friends").description("Whether to show friends or not.").defaultValue(true).build());
        llllllllllllllllIlIlIlIIlIlIIIIl.players = new ArrayList<class_742>();
    }

    @Override
    public void update(HudRenderer llllllllllllllllIlIlIlIIlIIlIlIl) {
        TextRadarHud llllllllllllllllIlIlIlIIlIIlIIlI;
        double llllllllllllllllIlIlIlIIlIIlIlII = llllllllllllllllIlIlIlIIlIIlIlIl.textWidth("Players:");
        double llllllllllllllllIlIlIlIIlIIlIIll = llllllllllllllllIlIlIlIIlIIlIlIl.textHeight();
        if (llllllllllllllllIlIlIlIIlIIlIIlI.mc.field_1687 == null) {
            llllllllllllllllIlIlIlIIlIIlIIlI.box.setSize(llllllllllllllllIlIlIlIIlIIlIlII, llllllllllllllllIlIlIlIIlIIlIIll);
            return;
        }
        for (class_1657 class_16572 : llllllllllllllllIlIlIlIIlIIlIIlI.getPlayers()) {
            if (class_16572.equals((Object)llllllllllllllllIlIlIlIIlIIlIIlI.mc.field_1724) || !llllllllllllllllIlIlIlIIlIIlIIlI.friends.get().booleanValue() && Friends.get().isFriend(class_16572)) continue;
            String llllllllllllllllIlIlIlIIlIIllIII = class_16572.method_5820();
            if (llllllllllllllllIlIlIlIIlIIlIIlI.distance.get().booleanValue()) {
                llllllllllllllllIlIlIlIIlIIllIII = String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIlIIlIIllIII).append(String.format("(%sm)", Math.round(llllllllllllllllIlIlIlIIlIIlIIlI.mc.method_1560().method_5739((class_1297)class_16572)))));
            }
            llllllllllllllllIlIlIlIIlIIlIlII = Math.max(llllllllllllllllIlIlIlIIlIIlIlII, llllllllllllllllIlIlIlIIlIIlIlIl.textWidth(llllllllllllllllIlIlIlIIlIIllIII));
            llllllllllllllllIlIlIlIIlIIlIIll += llllllllllllllllIlIlIlIIlIIlIlIl.textHeight() + 2.0;
        }
        llllllllllllllllIlIlIlIIlIIlIIlI.box.setSize(llllllllllllllllIlIlIlIIlIIlIlII, llllllllllllllllIlIlIlIIlIIlIIll);
    }
}

