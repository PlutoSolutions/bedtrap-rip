/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.IBaritone
 *  baritone.api.pathing.goals.Goal
 *  baritone.api.pathing.goals.GoalGetToBlock
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_418
 *  net.minecraft.class_437
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
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_418;
import net.minecraft.class_437;

public class WaypointsModule
extends Module {
    private final /* synthetic */ SettingGroup sgDeathPosition;
    private final /* synthetic */ SimpleDateFormat dateFormat;
    private final /* synthetic */ Setting<Boolean> dpChat;
    private static final /* synthetic */ Color GRAY;
    private final /* synthetic */ Setting<Integer> maxDeathPositions;

    private void cleanDeathWPs(int llllllllllllllllllllIIIlllIlllII) {
        int llllllllllllllllllllIIIlllIllllI = 0;
        ListIterator<Waypoint> llllllllllllllllllllIIIlllIlllIl = Waypoints.get().iteratorReverse();
        while (llllllllllllllllllllIIIlllIlllIl.hasPrevious()) {
            Waypoint llllllllllllllllllllIIIllllIIIIl = llllllllllllllllllllIIIlllIlllIl.previous();
            if (!llllllllllllllllllllIIIllllIIIIl.name.startsWith("Death ") || !"skull".equals(llllllllllllllllllllIIIllllIIIIl.icon) || ++llllllllllllllllllllIIIlllIllllI <= llllllllllllllllllllIIIlllIlllII) continue;
            Waypoints.get().remove(llllllllllllllllllllIIIllllIIIIl);
        }
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent llllllllllllllllllllIIIllllIllIl) {
        WaypointsModule llllllllllllllllllllIIIllllIlIlI;
        if (!(llllllllllllllllllllIIIllllIllIl.screen instanceof class_418)) {
            return;
        }
        if (llllllllllllllllllllIIIllllIlIlI.mc.field_1724 == null) {
            return;
        }
        class_243 llllllllllllllllllllIIIllllIllII = llllllllllllllllllllIIIllllIlIlI.mc.field_1724.method_19538();
        String llllllllllllllllllllIIIllllIlIll = llllllllllllllllllllIIIllllIlIlI.dateFormat.format(new Date());
        if (llllllllllllllllllllIIIllllIlIlI.dpChat.get().booleanValue()) {
            class_2585 llllllllllllllllllllIIIlllllIIII = new class_2585("Died at ");
            llllllllllllllllllllIIIlllllIIII.method_10852((class_2561)ChatUtils.formatCoords(llllllllllllllllllllIIIllllIllII));
            llllllllllllllllllllIIIlllllIIII.method_27693(String.format(" on %s.", llllllllllllllllllllIIIllllIlIll));
            llllllllllllllllllllIIIllllIlIlI.info((class_2561)llllllllllllllllllllIIIlllllIIII);
        }
        if (llllllllllllllllllllIIIllllIlIlI.maxDeathPositions.get() > 0) {
            Waypoint llllllllllllllllllllIIIllllIllll = new Waypoint();
            llllllllllllllllllllIIIllllIllll.name = String.valueOf(new StringBuilder().append("Death ").append(llllllllllllllllllllIIIllllIlIll));
            llllllllllllllllllllIIIllllIllll.icon = "skull";
            llllllllllllllllllllIIIllllIllll.x = (int)llllllllllllllllllllIIIllllIllII.field_1352;
            llllllllllllllllllllIIIllllIllll.y = (int)llllllllllllllllllllIIIllllIllII.field_1351 + 2;
            llllllllllllllllllllIIIllllIllll.z = (int)llllllllllllllllllllIIIllllIllII.field_1350;
            llllllllllllllllllllIIIllllIllll.maxVisibleDistance = Integer.MAX_VALUE;
            llllllllllllllllllllIIIllllIllll.actualDimension = PlayerUtils.getDimension();
            switch (llllllllllllllllllllIIIllllIllll.actualDimension) {
                case Overworld: {
                    llllllllllllllllllllIIIllllIllll.overworld = true;
                    break;
                }
                case Nether: {
                    llllllllllllllllllllIIIllllIllll.nether = true;
                    break;
                }
                case End: {
                    llllllllllllllllllllIIIllllIllll.end = true;
                }
            }
            Waypoints.get().add(llllllllllllllllllllIIIllllIllll);
        }
        llllllllllllllllllllIIIllllIlIlI.cleanDeathWPs(llllllllllllllllllllIIIllllIlIlI.maxDeathPositions.get());
    }

    public WaypointsModule() {
        super(Categories.Render, "waypoints", "Allows you to create waypoints.");
        WaypointsModule llllllllllllllllllllIIIlllllIllI;
        llllllllllllllllllllIIIlllllIllI.sgDeathPosition = llllllllllllllllllllIIIlllllIllI.settings.createGroup("Death Position");
        llllllllllllllllllllIIIlllllIllI.maxDeathPositions = llllllllllllllllllllIIIlllllIllI.sgDeathPosition.add(new IntSetting.Builder().name("max-death-positions").description("The amount of death positions to save, 0 to disable").min(0).sliderMin(0).sliderMax(20).defaultValue(0).onChanged(llllllllllllllllllllIIIlllllIllI::cleanDeathWPs).build());
        llllllllllllllllllllIIIlllllIllI.dpChat = llllllllllllllllllllIIIlllllIllI.sgDeathPosition.add(new BoolSetting.Builder().name("chat").description("Send a chat message with your position once you die").defaultValue(false).build());
        llllllllllllllllllllIIIlllllIllI.dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    static {
        GRAY = new Color(200, 200, 200);
    }

    @Override
    public WWidget getWidget(GuiTheme llllllllllllllllllllIIIlllIlIlII) {
        WaypointsModule llllllllllllllllllllIIIlllIlIIlI;
        if (!Utils.canUpdate()) {
            return llllllllllllllllllllIIIlllIlIlII.label("You need to be in a world.");
        }
        WTable llllllllllllllllllllIIIlllIlIIll = llllllllllllllllllllIIIlllIlIlII.table();
        llllllllllllllllllllIIIlllIlIIlI.fillTable(llllllllllllllllllllIIIlllIlIlII, llllllllllllllllllllIIIlllIlIIll);
        return llllllllllllllllllllIIIlllIlIIll;
    }

    private void fillTable(GuiTheme llllllllllllllllllllIIIllIlllIIl, WTable llllllllllllllllllllIIIllIllIlII) {
        WaypointsModule llllllllllllllllllllIIIllIlllIlI;
        WButton llllllllllllllllllllIIIllIllIlll = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.button("Create")).expandX().widget();
        llllllllllllllllllllIIIllIllIlll.action = () -> {
            WaypointsModule llllllllllllllllllllIIIlIlllllII;
            llllllllllllllllllllIIIlIlllllII.mc.method_1507((class_437)llllllllllllllllllllIIIlIlllllII.new EditWaypointScreen(llllllllllllllllllllIIIllIlllIIl, null, () -> {
                WaypointsModule llllllllllllllllllllIIIlIlllIIII;
                llllllllllllllllllllIIIllIllIlII.clear();
                llllllllllllllllllllIIIlIlllIIII.fillTable(llllllllllllllllllllIIIllIlllIIl, llllllllllllllllllllIIIllIllIlII);
            }));
        };
        llllllllllllllllllllIIIllIllIlII.row();
        for (Waypoint llllllllllllllllllllIIIllIlllIll : Waypoints.get()) {
            llllllllllllllllllllIIIllIllIlII.add(new WIcon(llllllllllllllllllllIIIllIlllIll));
            WLabel llllllllllllllllllllIIIlllIIIIIl = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.label(llllllllllllllllllllIIIllIlllIll.name)).expandCellX().widget();
            boolean llllllllllllllllllllIIIlllIIIIII = false;
            Dimension llllllllllllllllllllIIIllIllllll = PlayerUtils.getDimension();
            if (llllllllllllllllllllIIIllIlllIll.overworld && llllllllllllllllllllIIIllIllllll == Dimension.Overworld) {
                llllllllllllllllllllIIIlllIIIIII = true;
            } else if (llllllllllllllllllllIIIllIlllIll.nether && llllllllllllllllllllIIIllIllllll == Dimension.Nether) {
                llllllllllllllllllllIIIlllIIIIII = true;
            } else if (llllllllllllllllllllIIIllIlllIll.end && llllllllllllllllllllIIIllIllllll == Dimension.End) {
                llllllllllllllllllllIIIlllIIIIII = true;
            }
            if (!llllllllllllllllllllIIIlllIIIIII) {
                llllllllllllllllllllIIIlllIIIIIl.color = GRAY;
            }
            WCheckbox llllllllllllllllllllIIIllIlllllI = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.checkbox(llllllllllllllllllllIIIllIlllIll.visible)).widget();
            llllllllllllllllllllIIIllIlllllI.action = () -> {
                llllllllllllllllllllIIIllIIIIIll.visible = llllllllllllllllllllIIIllIIIIIII.checked;
                Waypoints.get().save();
            };
            WButton llllllllllllllllllllIIIllIllllIl = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.button(GuiRenderer.EDIT)).widget();
            llllllllllllllllllllIIIllIllllIl.action = () -> {
                WaypointsModule llllllllllllllllllllIIIllIIIlIII;
                llllllllllllllllllllIIIllIIIlIII.mc.method_1507((class_437)llllllllllllllllllllIIIllIIIlIII.new EditWaypointScreen(llllllllllllllllllllIIIllIlllIIl, llllllllllllllllllllIIIllIlllIll, null));
            };
            WMinus llllllllllllllllllllIIIllIllllII = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.minus()).widget();
            llllllllllllllllllllIIIllIllllII.action = () -> {
                WaypointsModule llllllllllllllllllllIIIllIIlIIlI;
                Waypoints.get().remove(llllllllllllllllllllIIIllIlllIll);
                llllllllllllllllllllIIIllIllIlII.clear();
                llllllllllllllllllllIIIllIIlIIlI.fillTable(llllllllllllllllllllIIIllIlllIIl, llllllllllllllllllllIIIllIllIlII);
            };
            if (llllllllllllllllllllIIIllIlllIll.actualDimension == llllllllllllllllllllIIIllIllllll) {
                WButton llllllllllllllllllllIIIlllIIIIlI = llllllllllllllllllllIIIllIllIlII.add(llllllllllllllllllllIIIllIlllIIl.button("Goto")).widget();
                llllllllllllllllllllIIIlllIIIIlI.action = () -> {
                    WaypointsModule llllllllllllllllllllIIIllIlIIlII;
                    if (llllllllllllllllllllIIIllIlIIlII.mc.field_1724 == null || llllllllllllllllllllIIIllIlIIlII.mc.field_1687 == null) {
                        return;
                    }
                    IBaritone llllllllllllllllllllIIIllIlIIIlI = BaritoneAPI.getProvider().getPrimaryBaritone();
                    if (llllllllllllllllllllIIIllIlIIIlI.getPathingBehavior().isPathing()) {
                        llllllllllllllllllllIIIllIlIIIlI.getPathingBehavior().cancelEverything();
                    }
                    class_243 llllllllllllllllllllIIIllIlIIIIl = Waypoints.get().getCoords(llllllllllllllllllllIIIllIlllIll);
                    class_2338 llllllllllllllllllllIIIllIlIIIII = new class_2338(llllllllllllllllllllIIIllIlIIIIl.field_1352, llllllllllllllllllllIIIllIlIIIIl.field_1351, llllllllllllllllllllIIIllIlIIIIl.field_1350);
                    llllllllllllllllllllIIIllIlIIIlI.getCustomGoalProcess().setGoalAndPath((Goal)new GoalGetToBlock(llllllllllllllllllllIIIllIlIIIII));
                };
            }
            llllllllllllllllllllIIIllIllIlII.row();
        }
    }

    private class EditWaypointScreen
    extends WindowScreen {
        private final /* synthetic */ boolean newWaypoint;
        private final /* synthetic */ Waypoint waypoint;
        private final /* synthetic */ Runnable action;

        @Override
        protected void onClosed() {
            EditWaypointScreen lllllllllllllllllIllIIIlllIlIlIl;
            if (lllllllllllllllllIllIIIlllIlIlIl.action != null) {
                lllllllllllllllllIllIIIlllIlIlIl.action.run();
            }
        }

        public EditWaypointScreen(GuiTheme lllllllllllllllllIllIIlIIIIIlIll, Waypoint lllllllllllllllllIllIIlIIIIIIlIl, Runnable lllllllllllllllllIllIIlIIIIIlIIl) {
            EditWaypointScreen lllllllllllllllllIllIIlIIIIIlIII;
            super(lllllllllllllllllIllIIlIIIIIlIll, lllllllllllllllllIllIIlIIIIIIlIl == null ? "New Waypoint" : "Edit Waypoint");
            lllllllllllllllllIllIIlIIIIIlIII.newWaypoint = lllllllllllllllllIllIIlIIIIIIlIl == null;
            lllllllllllllllllIllIIlIIIIIlIII.waypoint = lllllllllllllllllIllIIlIIIIIlIII.newWaypoint ? new Waypoint() : lllllllllllllllllIllIIlIIIIIIlIl;
            lllllllllllllllllIllIIlIIIIIlIII.action = lllllllllllllllllIllIIlIIIIIlIIl;
            lllllllllllllllllIllIIlIIIIIlIII.waypoint.validateIcon();
            if (lllllllllllllllllIllIIlIIIIIlIII.newWaypoint) {
                lllllllllllllllllIllIIlIIIIIlIII.waypoint.x = (int)((WaypointsModule)lllllllllllllllllIllIIlIIIIIlIII.WaypointsModule.this).mc.field_1724.method_23317();
                lllllllllllllllllIllIIlIIIIIlIII.waypoint.y = (int)((WaypointsModule)lllllllllllllllllIllIIlIIIIIlIII.WaypointsModule.this).mc.field_1724.method_23318() + 2;
                lllllllllllllllllIllIIlIIIIIlIII.waypoint.z = (int)((WaypointsModule)lllllllllllllllllIllIIlIIIIIlIII.WaypointsModule.this).mc.field_1724.method_23321();
                lllllllllllllllllIllIIlIIIIIlIII.waypoint.actualDimension = PlayerUtils.getDimension();
                switch (PlayerUtils.getDimension()) {
                    case Overworld: {
                        lllllllllllllllllIllIIlIIIIIlIII.waypoint.overworld = true;
                        break;
                    }
                    case Nether: {
                        lllllllllllllllllIllIIlIIIIIlIII.waypoint.nether = true;
                        break;
                    }
                    case End: {
                        lllllllllllllllllIllIIlIIIIIlIII.waypoint.end = true;
                    }
                }
            }
            lllllllllllllllllIllIIlIIIIIlIII.initWidgets();
        }

        private void initWidgets() {
            EditWaypointScreen lllllllllllllllllIllIIIllllIIlIl;
            WTable lllllllllllllllllIllIIIlllllIIll = lllllllllllllllllIllIIIllllIIlIl.add(lllllllllllllllllIllIIIllllIIlIl.theme.table()).expandX().widget();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Name:"));
            WTextBox lllllllllllllllllIllIIIlllllIIlI = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.textBox(lllllllllllllllllIllIIIllllIIlIl.waypoint.name)).minWidth(400.0).expandX().widget();
            lllllllllllllllllIllIIIlllllIIlI.action = () -> {
                lllllllllllllllllIllIIIllIIIlIIl.waypoint.name = lllllllllllllllllIllIIIlllllIIlI.get().trim();
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Icon:"));
            WHorizontalList lllllllllllllllllIllIIIlllllIIIl = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.horizontalList()).widget();
            lllllllllllllllllIllIIIlllllIIIl.add(lllllllllllllllllIllIIIllllIIlIl.theme.button((String)"<")).widget().action = lllllllllllllllllIllIIIllllIIlIl.waypoint::prevIcon;
            lllllllllllllllllIllIIIlllllIIIl.add(new WIcon(lllllllllllllllllIllIIIllllIIlIl.waypoint));
            lllllllllllllllllIllIIIlllllIIIl.add(lllllllllllllllllIllIIIllllIIlIl.theme.button((String)">")).widget().action = lllllllllllllllllIllIIIllllIIlIl.waypoint::nextIcon;
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Color:"));
            lllllllllllllllllIllIIIlllllIIIl = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.horizontalList()).widget();
            lllllllllllllllllIllIIIlllllIIIl.add(lllllllllllllllllIllIIIllllIIlIl.theme.quad(lllllllllllllllllIllIIIllllIIlIl.waypoint.color));
            lllllllllllllllllIllIIIlllllIIIl.add(lllllllllllllllllIllIIIllllIIlIl.theme.button((GuiTexture)GuiRenderer.EDIT)).widget().action = () -> {
                EditWaypointScreen lllllllllllllllllIllIIIllIIlIIll;
                lllllllllllllllllIllIIIllIIlIIll.WaypointsModule.this.mc.method_1507((class_437)new ColorSettingScreen(lllllllllllllllllIllIIIllIIlIIll.theme, new ColorSetting("", "", lllllllllllllllllIllIIIllIIlIIll.waypoint.color, lllllllllllllllllIllIIIllIIIllII -> {
                    EditWaypointScreen lllllllllllllllllIllIIIllIIIllIl;
                    lllllllllllllllllIllIIIllIIIllIl.waypoint.color.set((Color)lllllllllllllllllIllIIIllIIIllII);
                }, null, null)));
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.horizontalSeparator()).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("X:"));
            WIntEdit lllllllllllllllllIllIIIlllllIIII = lllllllllllllllllIllIIIllllIIlIl.theme.intEdit(lllllllllllllllllIllIIIllllIIlIl.waypoint.x, 0, 0);
            lllllllllllllllllIllIIIlllllIIII.hasSlider = false;
            lllllllllllllllllIllIIIlllllIIII.action = () -> {
                lllllllllllllllllIllIIIllIIlIllI.waypoint.x = lllllllllllllllllIllIIIlllllIIII.get();
            };
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIlllllIIII).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Y:"));
            WIntEdit lllllllllllllllllIllIIIllllIllll = lllllllllllllllllIllIIIllllIIlIl.theme.intEdit(lllllllllllllllllIllIIIllllIIlIl.waypoint.y, 0, 0);
            lllllllllllllllllIllIIIllllIllll.hasSlider = false;
            lllllllllllllllllIllIIIllllIllll.actionOnRelease = () -> {
                if (lllllllllllllllllIllIIIllllIllll.get() < 0) {
                    lllllllllllllllllIllIIIllllIllll.set(0);
                } else if (lllllllllllllllllIllIIIllllIllll.get() > 255) {
                    lllllllllllllllllIllIIIllllIllll.set(255);
                }
                lllllllllllllllllIllIIIllIIllllI.waypoint.y = lllllllllllllllllIllIIIllllIllll.get();
            };
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIllll).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Z:"));
            WIntEdit lllllllllllllllllIllIIIllllIlllI = lllllllllllllllllIllIIIllllIIlIl.theme.intEdit(lllllllllllllllllIllIIIllllIIlIl.waypoint.z, 0, 0);
            lllllllllllllllllIllIIIllllIlllI.action = () -> {
                lllllllllllllllllIllIIIllIlIIIlI.waypoint.z = lllllllllllllllllIllIIIllllIlllI.get();
            };
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIlllI).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.horizontalSeparator()).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Visible:"));
            WCheckbox lllllllllllllllllIllIIIllllIllIl = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.checkbox(lllllllllllllllllIllIIIllllIIlIl.waypoint.visible)).widget();
            lllllllllllllllllIllIIIllllIllIl.action = () -> {
                lllllllllllllllllIllIIIllIlIlIII.waypoint.visible = lllllllllllllllllIllIIIllIlIIlll.checked;
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Max Visible Distance"));
            WIntEdit lllllllllllllllllIllIIIllllIllII = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.intEdit(lllllllllllllllllIllIIIllllIIlIl.waypoint.maxVisibleDistance, 0, 10000)).expandX().widget();
            lllllllllllllllllIllIIIllllIllII.action = () -> {
                lllllllllllllllllIllIIIllIlIlllI.waypoint.maxVisibleDistance = lllllllllllllllllIllIIIllllIllII.get();
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Scale:"));
            WDoubleEdit lllllllllllllllllIllIIIllllIlIll = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.doubleEdit(lllllllllllllllllIllIIIllllIIlIl.waypoint.scale, 0.0, 4.0)).expandX().widget();
            lllllllllllllllllIllIIIllllIlIll.action = () -> {
                lllllllllllllllllIllIIIllIllIlII.waypoint.scale = lllllllllllllllllIllIIIllllIlIll.get();
            };
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.horizontalSeparator()).expandX();
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Actual Dimension:"));
            WDropdown<Dimension> lllllllllllllllllIllIIIllllIlIlI = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.dropdown(lllllllllllllllllIllIIIllllIIlIl.waypoint.actualDimension)).widget();
            lllllllllllllllllIllIIIllllIlIlI.action = () -> {
                lllllllllllllllllIllIIIllIllllII.waypoint.actualDimension = (Dimension)((Object)((Object)lllllllllllllllllIllIIIllllIlIlI.get()));
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Visible in Overworld:"));
            WCheckbox lllllllllllllllllIllIIIllllIlIIl = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.checkbox(lllllllllllllllllIllIIIllllIIlIl.waypoint.overworld)).widget();
            lllllllllllllllllIllIIIllllIlIIl.action = () -> {
                lllllllllllllllllIllIIIlllIIIIlI.waypoint.overworld = lllllllllllllllllIllIIIlllIIIIIl.checked;
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Visible in Nether:"));
            WCheckbox lllllllllllllllllIllIIIllllIlIII = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.checkbox(lllllllllllllllllIllIIIllllIIlIl.waypoint.nether)).widget();
            lllllllllllllllllIllIIIllllIlIII.action = () -> {
                lllllllllllllllllIllIIIlllIIlIII.waypoint.nether = lllllllllllllllllIllIIIlllIIIlll.checked;
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.label("Visible in End:"));
            WCheckbox lllllllllllllllllIllIIIllllIIlll = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.checkbox(lllllllllllllllllIllIIIllllIIlIl.waypoint.end)).widget();
            lllllllllllllllllIllIIIllllIIlll.action = () -> {
                lllllllllllllllllIllIIIlllIIllII.waypoint.end = lllllllllllllllllIllIIIlllIIllIl.checked;
            };
            lllllllllllllllllIllIIIlllllIIll.row();
            WButton lllllllllllllllllIllIIIllllIIllI = lllllllllllllllllIllIIIlllllIIll.add(lllllllllllllllllIllIIIllllIIlIl.theme.button("Save")).expandX().widget();
            lllllllllllllllllIllIIIllllIIlIl.enterAction = lllllllllllllllllIllIIIllllIIllI.action = () -> {
                EditWaypointScreen lllllllllllllllllIllIIIlllIlIIIl;
                if (lllllllllllllllllIllIIIlllIlIIIl.newWaypoint) {
                    Waypoints.get().add(lllllllllllllllllIllIIIlllIlIIIl.waypoint);
                } else {
                    Waypoints.get().save();
                }
                lllllllllllllllllIllIIIlllIlIIIl.method_25419();
            };
        }
    }

    private static class WIcon
    extends WWidget {
        private final /* synthetic */ Waypoint waypoint;

        public WIcon(Waypoint llIIllIlIIIIIll) {
            WIcon llIIllIlIIIIlII;
            llIIllIlIIIIlII.waypoint = llIIllIlIIIIIll;
        }

        @Override
        protected void onRender(GuiRenderer llIIllIIlllIlII, double llIIllIIllllIII, double llIIllIIlllIlll, double llIIllIIlllIllI) {
            WIcon llIIllIIllllIlI;
            llIIllIIlllIlII.post(() -> {
                WIcon llIIllIIlllIIlI;
                llIIllIIlllIIlI.waypoint.renderIcon(llIIllIIlllIIlI.x, llIIllIIlllIIlI.y, 0.0, 1.0, llIIllIIlllIIlI.width);
            });
        }

        @Override
        protected void onCalculateSize() {
            WIcon llIIllIlIIIIIII;
            double llIIllIIlllllll;
            llIIllIlIIIIIII.width = llIIllIIlllllll = llIIllIlIIIIIII.theme.scale(32.0);
            llIIllIlIIIIIII.height = llIIllIIlllllll;
        }
    }
}

