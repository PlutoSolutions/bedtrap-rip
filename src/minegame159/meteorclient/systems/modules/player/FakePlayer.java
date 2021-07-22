/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.player;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;

public class FakePlayer
extends Module {
    public final /* synthetic */ Setting<Boolean> copyInv;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<String> name;
    public final /* synthetic */ Setting<Integer> health;

    public FakePlayer() {
        super(Categories.Player, "fake-player", "Spawns a client-side fake player for testing usages.");
        FakePlayer llllllllllllllllIllIIIlIllIIIIlI;
        llllllllllllllllIllIIIlIllIIIIlI.sgGeneral = llllllllllllllllIllIIIlIllIIIIlI.settings.getDefaultGroup();
        llllllllllllllllIllIIIlIllIIIIlI.name = llllllllllllllllIllIIIlIllIIIIlI.sgGeneral.add(new StringSetting.Builder().name("name").description("The name of the fake player.").defaultValue("seasnail8169").build());
        llllllllllllllllIllIIIlIllIIIIlI.copyInv = llllllllllllllllIllIIIlIllIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("copy-inv").description("Copies your exact inventory to the fake player.").defaultValue(true).build());
        llllllllllllllllIllIIIlIllIIIIlI.health = llllllllllllllllIllIIIlIllIIIIlI.sgGeneral.add(new IntSetting.Builder().name("health").description("The fake player's default health.").defaultValue(20).min(1).sliderMax(100).build());
    }

    @Override
    public void onDeactivate() {
        FakePlayerManager.clear();
    }

    @Override
    public WWidget getWidget(GuiTheme llllllllllllllllIllIIIlIlIlllIIl) {
        FakePlayer llllllllllllllllIllIIIlIlIlllIlI;
        WHorizontalList llllllllllllllllIllIIIlIlIlllIII = llllllllllllllllIllIIIlIlIlllIIl.horizontalList();
        WButton llllllllllllllllIllIIIlIlIllIlll = llllllllllllllllIllIIIlIlIlllIII.add(llllllllllllllllIllIIIlIlIlllIIl.button("Spawn")).widget();
        llllllllllllllllIllIIIlIlIllIlll.action = () -> {
            FakePlayer llllllllllllllllIllIIIlIlIlIlIll;
            if (llllllllllllllllIllIIIlIlIlIlIll.isActive()) {
                FakePlayerManager.add(llllllllllllllllIllIIIlIlIlIlIll.name.get(), llllllllllllllllIllIIIlIlIlIlIll.health.get().intValue(), llllllllllllllllIllIIIlIlIlIlIll.copyInv.get());
            }
        };
        WButton llllllllllllllllIllIIIlIlIllIllI = llllllllllllllllIllIIIlIlIlllIII.add(llllllllllllllllIllIIIlIlIlllIIl.button("Clear")).widget();
        llllllllllllllllIllIIIlIlIllIllI.action = () -> {
            FakePlayer llllllllllllllllIllIIIlIlIlIllIl;
            if (llllllllllllllllIllIIIlIlIlIllIl.isActive()) {
                FakePlayerManager.clear();
            }
        };
        return llllllllllllllllIllIIIlIlIlllIII;
    }

    @Override
    public void onActivate() {
        FakePlayerManager.clear();
    }

    @Override
    public String getInfoString() {
        if (FakePlayerManager.getPlayers() != null) {
            return String.valueOf(FakePlayerManager.getPlayers().size());
        }
        return null;
    }
}

