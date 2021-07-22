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
    public final Setting<Boolean> copyInv;
    private final SettingGroup sgGeneral;
    public final Setting<String> name;
    public final Setting<Integer> health;

    public FakePlayer() {
        super(Categories.Player, "fake-player", "Spawns a client-side fake player for testing usages.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.name = this.sgGeneral.add(new StringSetting.Builder().name("name").description("The name of the fake player.").defaultValue("seasnail8169").build());
        this.copyInv = this.sgGeneral.add(new BoolSetting.Builder().name("copy-inv").description("Copies your exact inventory to the fake player.").defaultValue(true).build());
        this.health = this.sgGeneral.add(new IntSetting.Builder().name("health").description("The fake player's default health.").defaultValue(20).min(1).sliderMax(100).build());
    }

    @Override
    public void onDeactivate() {
        FakePlayerManager.clear();
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        WHorizontalList wHorizontalList = guiTheme.horizontalList();
        WButton wButton = wHorizontalList.add(guiTheme.button("Spawn")).widget();
        wButton.action = this::lambda$getWidget$0;
        WButton wButton2 = wHorizontalList.add(guiTheme.button("Clear")).widget();
        wButton2.action = this::lambda$getWidget$1;
        return wHorizontalList;
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

    private void lambda$getWidget$1() {
        if (this.isActive()) {
            FakePlayerManager.clear();
        }
    }

    private void lambda$getWidget$0() {
        if (this.isActive()) {
            FakePlayerManager.add(this.name.get(), this.health.get().intValue(), this.copyInv.get());
        }
    }
}

