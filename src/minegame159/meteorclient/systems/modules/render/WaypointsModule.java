/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalGetToBlock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.screens.settings.ColorSettingScreen;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WDoubleEdit;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.waypoints.Waypoint;
import minegame159.meteorclient.systems.waypoints.Waypoints;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_310;
import net.minecraft.class_418;
import net.minecraft.class_437;

public class WaypointsModule
extends Module {
    private final SettingGroup sgDeathPosition;
    private final SimpleDateFormat dateFormat;
    private final Setting<Boolean> dpChat;
    private static final Color GRAY = new Color(200, 200, 200);
    private final Setting<Integer> maxDeathPositions;

    static class_310 access$200(WaypointsModule waypointsModule) {
        return waypointsModule.mc;
    }

    private static void lambda$fillTable$2(Waypoint waypoint, WCheckbox wCheckbox) {
        waypoint.visible = wCheckbox.checked;
        Waypoints.get().save();
    }

    static class_310 access$300(WaypointsModule waypointsModule) {
        return waypointsModule.mc;
    }

    static class_310 access$100(WaypointsModule waypointsModule) {
        return waypointsModule.mc;
    }

    static class_310 access$000(WaypointsModule waypointsModule) {
        return waypointsModule.mc;
    }

    private void lambda$fillTable$4(Waypoint waypoint, WTable wTable, GuiTheme guiTheme) {
        Waypoints.get().remove(waypoint);
        wTable.clear();
        this.fillTable(guiTheme, wTable);
    }

    private void lambda$fillTable$5(Waypoint waypoint) {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        IBaritone iBaritone = BaritoneAPI.getProvider().getPrimaryBaritone();
        if (iBaritone.getPathingBehavior().isPathing()) {
            iBaritone.getPathingBehavior().cancelEverything();
        }
        class_243 class_2432 = Waypoints.get().getCoords(waypoint);
        class_2338 class_23382 = new class_2338(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
        iBaritone.getCustomGoalProcess().setGoalAndPath((Goal)new GoalGetToBlock(class_23382));
    }

    private void lambda$fillTable$3(GuiTheme guiTheme, Waypoint waypoint) {
        this.mc.method_1507((class_437)new EditWaypointScreen(this, guiTheme, waypoint, null));
    }

    private void cleanDeathWPs(int n) {
        int n2 = 0;
        ListIterator<Waypoint> listIterator = Waypoints.get().iteratorReverse();
        while (listIterator.hasPrevious()) {
            Waypoint waypoint = listIterator.previous();
            if (!waypoint.name.startsWith("Death ") || !"skull".equals(waypoint.icon) || ++n2 <= n) continue;
            Waypoints.get().remove(waypoint);
        }
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        Object object;
        if (!(openScreenEvent.screen instanceof class_418)) {
            return;
        }
        if (this.mc.field_1724 == null) {
            return;
        }
        class_243 class_2432 = this.mc.field_1724.method_19538();
        String string = this.dateFormat.format(new Date());
        if (this.dpChat.get().booleanValue()) {
            object = new class_2585("Died at ");
            object.method_10852((class_2561)ChatUtils.formatCoords(class_2432));
            object.method_27693(String.format(" on %s.", string));
            this.info((class_2561)object);
        }
        if (this.maxDeathPositions.get() > 0) {
            object = new Waypoint();
            object.name = String.valueOf(new StringBuilder().append("Death ").append(string));
            object.icon = "skull";
            object.x = (int)class_2432.field_1352;
            object.y = (int)class_2432.field_1351 + 2;
            object.z = (int)class_2432.field_1350;
            object.maxVisibleDistance = Integer.MAX_VALUE;
            object.actualDimension = PlayerUtils.getDimension();
            switch (1.$SwitchMap$minegame159$meteorclient$utils$world$Dimension[object.actualDimension.ordinal()]) {
                case 1: {
                    object.overworld = true;
                    break;
                }
                case 2: {
                    object.nether = true;
                    break;
                }
                case 3: {
                    object.end = true;
                }
            }
            Waypoints.get().add((Waypoint)object);
        }
        this.cleanDeathWPs(this.maxDeathPositions.get());
    }

    private void lambda$fillTable$1(GuiTheme guiTheme, WTable wTable) {
        this.mc.method_1507((class_437)new EditWaypointScreen(this, guiTheme, null, () -> this.lambda$fillTable$0(wTable, guiTheme)));
    }

    private void lambda$fillTable$0(WTable wTable, GuiTheme guiTheme) {
        wTable.clear();
        this.fillTable(guiTheme, wTable);
    }

    public WaypointsModule() {
        super(Categories.Render, "waypoints", "Allows you to create waypoints.");
        this.sgDeathPosition = this.settings.createGroup("Death Position");
        this.maxDeathPositions = this.sgDeathPosition.add(new IntSetting.Builder().name("max-death-positions").description("The amount of death positions to save, 0 to disable").min(0).sliderMin(0).sliderMax(20).defaultValue(0).onChanged(this::cleanDeathWPs).build());
        this.dpChat = this.sgDeathPosition.add(new BoolSetting.Builder().name("chat").description("Send a chat message with your position once you die").defaultValue(false).build());
        this.dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        if (!Utils.canUpdate()) {
            return guiTheme.label("You need to be in a world.");
        }
        WTable wTable = guiTheme.table();
        this.fillTable(guiTheme, wTable);
        return wTable;
    }

    private void fillTable(GuiTheme guiTheme, WTable wTable) {
        WButton wButton = wTable.add(guiTheme.button("Create")).expandX().widget();
        wButton.action = () -> this.lambda$fillTable$1(guiTheme, wTable);
        wTable.row();
        for (Waypoint waypoint : Waypoints.get()) {
            wTable.add(new WIcon(waypoint));
            WLabel wLabel = wTable.add(guiTheme.label(waypoint.name)).expandCellX().widget();
            boolean bl = false;
            Dimension dimension = PlayerUtils.getDimension();
            if (waypoint.overworld && dimension == Dimension.Overworld) {
                bl = true;
            } else if (waypoint.nether && dimension == Dimension.Nether) {
                bl = true;
            } else if (waypoint.end && dimension == Dimension.End) {
                bl = true;
            }
            if (!bl) {
                wLabel.color = GRAY;
            }
            WCheckbox wCheckbox = wTable.add(guiTheme.checkbox(waypoint.visible)).widget();
            wCheckbox.action = () -> WaypointsModule.lambda$fillTable$2(waypoint, wCheckbox);
            WButton wButton2 = wTable.add(guiTheme.button(GuiRenderer.EDIT)).widget();
            wButton2.action = () -> this.lambda$fillTable$3(guiTheme, waypoint);
            WMinus wMinus = wTable.add(guiTheme.minus()).widget();
            wMinus.action = () -> this.lambda$fillTable$4(waypoint, wTable, guiTheme);
            if (waypoint.actualDimension == dimension) {
                WButton wButton3 = wTable.add(guiTheme.button("Goto")).widget();
                wButton3.action = () -> this.lambda$fillTable$5(waypoint);
            }
            wTable.row();
        }
    }

    private class EditWaypointScreen
    extends WindowScreen {
        final WaypointsModule this$0;
        private final boolean newWaypoint;
        private final Waypoint waypoint;
        private final Runnable action;

        private void lambda$initWidgets$10(WCheckbox wCheckbox) {
            this.waypoint.overworld = wCheckbox.checked;
        }

        @Override
        protected void onClosed() {
            if (this.action != null) {
                this.action.run();
            }
        }

        private void lambda$initWidgets$12(WCheckbox wCheckbox) {
            this.waypoint.end = wCheckbox.checked;
        }

        public EditWaypointScreen(WaypointsModule waypointsModule, GuiTheme guiTheme, Waypoint waypoint, Runnable runnable) {
            this.this$0 = waypointsModule;
            super(guiTheme, waypoint == null ? "New Waypoint" : "Edit Waypoint");
            this.newWaypoint = waypoint == null;
            this.waypoint = this.newWaypoint ? new Waypoint() : waypoint;
            this.action = runnable;
            this.waypoint.validateIcon();
            if (this.newWaypoint) {
                this.waypoint.x = (int)WaypointsModule.access$000((WaypointsModule)waypointsModule).field_1724.method_23317();
                this.waypoint.y = (int)WaypointsModule.access$100((WaypointsModule)waypointsModule).field_1724.method_23318() + 2;
                this.waypoint.z = (int)WaypointsModule.access$200((WaypointsModule)waypointsModule).field_1724.method_23321();
                this.waypoint.actualDimension = PlayerUtils.getDimension();
                switch (PlayerUtils.getDimension()) {
                    case Overworld: {
                        this.waypoint.overworld = true;
                        break;
                    }
                    case Nether: {
                        this.waypoint.nether = true;
                        break;
                    }
                    case End: {
                        this.waypoint.end = true;
                    }
                }
            }
            this.initWidgets();
        }

        private void initWidgets() {
            WTable wTable = this.add(this.theme.table()).expandX().widget();
            wTable.add(this.theme.label("Name:"));
            WTextBox wTextBox = wTable.add(this.theme.textBox(this.waypoint.name)).minWidth(400.0).expandX().widget();
            wTextBox.action = () -> this.lambda$initWidgets$0(wTextBox);
            wTable.row();
            wTable.add(this.theme.label("Icon:"));
            WHorizontalList wHorizontalList = wTable.add(this.theme.horizontalList()).widget();
            wHorizontalList.add(this.theme.button((String)"<")).widget().action = this.waypoint::prevIcon;
            wHorizontalList.add(new WIcon(this.waypoint));
            wHorizontalList.add(this.theme.button((String)">")).widget().action = this.waypoint::nextIcon;
            wTable.row();
            wTable.add(this.theme.label("Color:"));
            wHorizontalList = wTable.add(this.theme.horizontalList()).widget();
            wHorizontalList.add(this.theme.quad(this.waypoint.color));
            wHorizontalList.add(this.theme.button((GuiTexture)GuiRenderer.EDIT)).widget().action = this::lambda$initWidgets$2;
            wTable.row();
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(this.theme.label("X:"));
            WIntEdit wIntEdit = this.theme.intEdit(this.waypoint.x, 0, 0);
            wIntEdit.hasSlider = false;
            wIntEdit.action = () -> this.lambda$initWidgets$3(wIntEdit);
            wTable.add(wIntEdit).expandX();
            wTable.row();
            wTable.add(this.theme.label("Y:"));
            WIntEdit wIntEdit2 = this.theme.intEdit(this.waypoint.y, 0, 0);
            wIntEdit2.hasSlider = false;
            wIntEdit2.actionOnRelease = () -> this.lambda$initWidgets$4(wIntEdit2);
            wTable.add(wIntEdit2).expandX();
            wTable.row();
            wTable.add(this.theme.label("Z:"));
            WIntEdit wIntEdit3 = this.theme.intEdit(this.waypoint.z, 0, 0);
            wIntEdit3.action = () -> this.lambda$initWidgets$5(wIntEdit3);
            wTable.add(wIntEdit3).expandX();
            wTable.row();
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(this.theme.label("Visible:"));
            WCheckbox wCheckbox = wTable.add(this.theme.checkbox(this.waypoint.visible)).widget();
            wCheckbox.action = () -> this.lambda$initWidgets$6(wCheckbox);
            wTable.row();
            wTable.add(this.theme.label("Max Visible Distance"));
            WIntEdit wIntEdit4 = wTable.add(this.theme.intEdit(this.waypoint.maxVisibleDistance, 0, 10000)).expandX().widget();
            wIntEdit4.action = () -> this.lambda$initWidgets$7(wIntEdit4);
            wTable.row();
            wTable.add(this.theme.label("Scale:"));
            WDoubleEdit wDoubleEdit = wTable.add(this.theme.doubleEdit(this.waypoint.scale, 0.0, 4.0)).expandX().widget();
            wDoubleEdit.action = () -> this.lambda$initWidgets$8(wDoubleEdit);
            wTable.add(this.theme.horizontalSeparator()).expandX();
            wTable.row();
            wTable.add(this.theme.label("Actual Dimension:"));
            WDropdown<Dimension> wDropdown = wTable.add(this.theme.dropdown(this.waypoint.actualDimension)).widget();
            wDropdown.action = () -> this.lambda$initWidgets$9(wDropdown);
            wTable.row();
            wTable.add(this.theme.label("Visible in Overworld:"));
            WCheckbox wCheckbox2 = wTable.add(this.theme.checkbox(this.waypoint.overworld)).widget();
            wCheckbox2.action = () -> this.lambda$initWidgets$10(wCheckbox2);
            wTable.row();
            wTable.add(this.theme.label("Visible in Nether:"));
            WCheckbox wCheckbox3 = wTable.add(this.theme.checkbox(this.waypoint.nether)).widget();
            wCheckbox3.action = () -> this.lambda$initWidgets$11(wCheckbox3);
            wTable.row();
            wTable.add(this.theme.label("Visible in End:"));
            WCheckbox wCheckbox4 = wTable.add(this.theme.checkbox(this.waypoint.end)).widget();
            wCheckbox4.action = () -> this.lambda$initWidgets$12(wCheckbox4);
            wTable.row();
            WButton wButton = wTable.add(this.theme.button("Save")).expandX().widget();
            this.enterAction = wButton.action = this::lambda$initWidgets$13;
        }

        private void lambda$initWidgets$0(WTextBox wTextBox) {
            this.waypoint.name = wTextBox.get().trim();
        }

        private void lambda$initWidgets$5(WIntEdit wIntEdit) {
            this.waypoint.z = wIntEdit.get();
        }

        private void lambda$initWidgets$13() {
            if (this.newWaypoint) {
                Waypoints.get().add(this.waypoint);
            } else {
                Waypoints.get().save();
            }
            this.method_25419();
        }

        private void lambda$initWidgets$11(WCheckbox wCheckbox) {
            this.waypoint.nether = wCheckbox.checked;
        }

        private void lambda$initWidgets$2() {
            WaypointsModule.access$300(this.this$0).method_1507((class_437)new ColorSettingScreen(this.theme, new ColorSetting("", "", this.waypoint.color, this::lambda$initWidgets$1, null, null)));
        }

        private void lambda$initWidgets$1(SettingColor settingColor) {
            this.waypoint.color.set(settingColor);
        }

        private void lambda$initWidgets$6(WCheckbox wCheckbox) {
            this.waypoint.visible = wCheckbox.checked;
        }

        private void lambda$initWidgets$4(WIntEdit wIntEdit) {
            if (wIntEdit.get() < 0) {
                wIntEdit.set(0);
            } else if (wIntEdit.get() > 255) {
                wIntEdit.set(255);
            }
            this.waypoint.y = wIntEdit.get();
        }

        private void lambda$initWidgets$9(WDropdown wDropdown) {
            this.waypoint.actualDimension = (Dimension)((Object)wDropdown.get());
        }

        private void lambda$initWidgets$3(WIntEdit wIntEdit) {
            this.waypoint.x = wIntEdit.get();
        }

        private void lambda$initWidgets$8(WDoubleEdit wDoubleEdit) {
            this.waypoint.scale = wDoubleEdit.get();
        }

        private void lambda$initWidgets$7(WIntEdit wIntEdit) {
            this.waypoint.maxVisibleDistance = wIntEdit.get();
        }
    }

    private static class WIcon
    extends WWidget {
        private final Waypoint waypoint;

        public WIcon(Waypoint waypoint) {
            this.waypoint = waypoint;
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            guiRenderer.post(this::lambda$onRender$0);
        }

        @Override
        protected void onCalculateSize() {
            double d;
            this.width = d = this.theme.scale(32.0);
            this.height = d;
        }

        private void lambda$onRender$0() {
            this.waypoint.renderIcon(this.x, this.y, 0.0, 1.0, this.width);
        }
    }
}

