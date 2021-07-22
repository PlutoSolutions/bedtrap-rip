/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_465
 */
package minegame159.meteorclient.systems.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.DropItemsEvent;
import minegame159.meteorclient.events.entity.player.BreakBlockEvent;
import minegame159.meteorclient.events.entity.player.PickItemsEvent;
import minegame159.meteorclient.events.entity.player.PlaceBlockEvent;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1792;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_465;

public class Announcer
extends Module {
    private final /* synthetic */ Feature[] features;
    private static final /* synthetic */ double TICK = 0.05;

    @Override
    public void onActivate() {
        Announcer lIlIllIIlllIII;
        for (Feature lIlIllIIlllIlI : lIlIllIIlllIII.features) {
            if (!lIlIllIIlllIlI.isEnabled()) continue;
            MeteorClient.EVENT_BUS.subscribe(lIlIllIIlllIlI);
            lIlIllIIlllIlI.reset();
        }
    }

    @Override
    public void onDeactivate() {
        Announcer lIlIllIIlIllIl;
        for (Feature lIlIllIIlIlllI : lIlIllIIlIllIl.features) {
            if (!lIlIllIIlIlllI.isEnabled()) continue;
            MeteorClient.EVENT_BUS.unsubscribe(lIlIllIIlIlllI);
        }
    }

    public Announcer() {
        super(Categories.Misc, "announcer", "Announces specified actions into chat.");
        Announcer lIlIllIlIIIIIl;
        lIlIllIlIIIIIl.features = new Feature[]{lIlIllIlIIIIIl.new Moving(), lIlIllIlIIIIIl.new Mining(), lIlIllIlIIIIIl.new Placing(), lIlIllIlIIIIIl.new DropItems(), lIlIllIlIIIIIl.new PickItems(), lIlIllIlIIIIIl.new OpenContainer()};
    }

    @EventHandler
    private void onTick(TickEvent.Post lIlIllIIlIIIII) {
        Announcer lIlIllIIIlllll;
        for (Feature lIlIllIIlIIIlI : lIlIllIIIlllll.features) {
            if (!lIlIllIIlIIIlI.isEnabled()) continue;
            lIlIllIIlIIIlI.tick();
        }
    }

    private class PickItems
    extends Feature {
        private /* synthetic */ class_1792 lastItem;
        private /* synthetic */ double notPickedUpTimer;
        private /* synthetic */ int count;
        private final /* synthetic */ Setting<String> msg;

        void sendMsg() {
            PickItems lIlIllllIIIlll;
            if (lIlIllllIIIlll.count > 0) {
                ((Announcer)lIlIllllIIIlll.Announcer.this).mc.field_1724.method_3142(lIlIllllIIIlll.msg.get().replace("{count}", Integer.toString(lIlIllllIIIlll.count)).replace("{item}", lIlIllllIIIlll.lastItem.method_7848().getString()));
                lIlIllllIIIlll.count = 0;
            }
        }

        @Override
        void tick() {
            PickItems lIlIllllIIlIIl;
            if (lIlIllllIIlIIl.notPickedUpTimer >= 1.0) {
                lIlIllllIIlIIl.sendMsg();
            } else {
                lIlIllllIIlIIl.notPickedUpTimer += 0.05;
            }
        }

        @EventHandler
        private void onPickItems(PickItemsEvent lIlIllllIIllII) {
            PickItems lIlIllllIIllIl;
            if (lIlIllllIIllIl.lastItem != null && lIlIllllIIllIl.lastItem != lIlIllllIIllII.itemStack.method_7909()) {
                lIlIllllIIllIl.sendMsg();
            }
            lIlIllllIIllIl.lastItem = lIlIllllIIllII.itemStack.method_7909();
            lIlIllllIIllIl.count += lIlIllllIIllII.itemStack.method_7947();
            lIlIllllIIllIl.notPickedUpTimer = 0.0;
        }

        PickItems() {
            PickItems lIlIllllIlIlll;
            super("Pick Items", "pick-items-enabled", "Send msg how much items you pick up.");
            lIlIllllIlIlll.msg = lIlIllllIlIlll.sg.add(new StringSetting.Builder().name("pick-items-msg").description("The chat message for picking up items.").defaultValue("I just picked up {count} {item}!").build());
        }

        @Override
        void reset() {
            lIlIllllIlIIlI.lastItem = null;
            lIlIllllIlIIlI.count = 0;
            lIlIllllIlIIlI.notPickedUpTimer = 0.0;
        }
    }

    private class Mining
    extends Feature {
        private /* synthetic */ double notBrokenTimer;
        private final /* synthetic */ Setting<String> msg;
        private /* synthetic */ int count;
        private /* synthetic */ class_2248 lastBlock;

        void sendMsg() {
            Mining llllllllllllllllllllIIIIIllllIll;
            if (llllllllllllllllllllIIIIIllllIll.count > 0) {
                ((Announcer)llllllllllllllllllllIIIIIllllIll.Announcer.this).mc.field_1724.method_3142(llllllllllllllllllllIIIIIllllIll.msg.get().replace("{count}", Integer.toString(llllllllllllllllllllIIIIIllllIll.count)).replace("{block}", llllllllllllllllllllIIIIIllllIll.lastBlock.method_9518().getString()));
                llllllllllllllllllllIIIIIllllIll.count = 0;
            }
        }

        @Override
        void reset() {
            llllllllllllllllllllIIIIlIIIlIll.lastBlock = null;
            llllllllllllllllllllIIIIlIIIlIll.count = 0;
            llllllllllllllllllllIIIIlIIIlIll.notBrokenTimer = 0.0;
        }

        @EventHandler
        private void onBreakBlock(BreakBlockEvent llllllllllllllllllllIIIIlIIIIlIl) {
            Mining llllllllllllllllllllIIIIlIIIIllI;
            class_2248 llllllllllllllllllllIIIIlIIIIlII = llllllllllllllllllllIIIIlIIIIlIl.getBlockState((class_1937)((Announcer)llllllllllllllllllllIIIIlIIIIllI.Announcer.this).mc.field_1687).method_26204();
            if (llllllllllllllllllllIIIIlIIIIllI.lastBlock != null && llllllllllllllllllllIIIIlIIIIllI.lastBlock != llllllllllllllllllllIIIIlIIIIlII) {
                llllllllllllllllllllIIIIlIIIIllI.sendMsg();
            }
            llllllllllllllllllllIIIIlIIIIllI.lastBlock = llllllllllllllllllllIIIIlIIIIlII;
            ++llllllllllllllllllllIIIIlIIIIllI.count;
            llllllllllllllllllllIIIIlIIIIllI.notBrokenTimer = 0.0;
        }

        Mining() {
            Mining llllllllllllllllllllIIIIlIIIllll;
            super("Mining", "mining-enabled", "Send msg how much blocks you mined.");
            llllllllllllllllllllIIIIlIIIllll.msg = llllllllllllllllllllIIIIlIIIllll.sg.add(new StringSetting.Builder().name("mining-msg").description("The chat message for mining blocks.").defaultValue("I just mined {count} {block}!").build());
        }

        @Override
        void tick() {
            Mining llllllllllllllllllllIIIIIlllllll;
            if (llllllllllllllllllllIIIIIlllllll.notBrokenTimer >= 2.0) {
                llllllllllllllllllllIIIIIlllllll.sendMsg();
            } else {
                llllllllllllllllllllIIIIIlllllll.notBrokenTimer += 0.05;
            }
        }
    }

    private class OpenContainer
    extends Feature {
        private final /* synthetic */ Setting<String> msg;

        @Override
        void reset() {
        }

        public OpenContainer() {
            OpenContainer lllllllllllllllllIlIIlIIllIIllll;
            super("Open Container", "open-container-enabled", "Sends msg when you open containers.");
            lllllllllllllllllIlIIlIIllIIllll.msg = lllllllllllllllllIlIIlIIllIIllll.sg.add(new StringSetting.Builder().name("open-container-msg").description("The chat message for opening a container.").defaultValue("I just opened {name}!").build());
        }

        @Override
        void tick() {
        }

        void sendMsg(String lllllllllllllllllIlIIlIIlIllllll) {
            OpenContainer lllllllllllllllllIlIIlIIlIlllllI;
            ((Announcer)lllllllllllllllllIlIIlIIlIlllllI.Announcer.this).mc.field_1724.method_3142(lllllllllllllllllIlIIlIIlIlllllI.msg.get().replace("{name}", lllllllllllllllllIlIIlIIlIllllll));
        }

        @EventHandler
        private void onOpenScreen(OpenScreenEvent lllllllllllllllllIlIIlIIllIIIlII) {
            String lllllllllllllllllIlIIlIIllIIlIII;
            if (lllllllllllllllllIlIIlIIllIIIlII.screen instanceof class_465 && !(lllllllllllllllllIlIIlIIllIIlIII = lllllllllllllllllIlIIlIIllIIIlII.screen.method_25440().getString()).isEmpty()) {
                OpenContainer lllllllllllllllllIlIIlIIllIIIlll;
                lllllllllllllllllIlIIlIIllIIIlll.sendMsg(lllllllllllllllllIlIIlIIllIIlIII);
            }
        }
    }

    private class Moving
    extends Feature {
        private /* synthetic */ double timer;
        private /* synthetic */ double lastX;
        private final /* synthetic */ Setting<String> msg;
        private /* synthetic */ double dist;
        private /* synthetic */ boolean first;
        private final /* synthetic */ Setting<Double> minDist;
        private /* synthetic */ double lastZ;
        private final /* synthetic */ Setting<Double> delay;

        void updateLastPos() {
            Moving lIIIIIllIlllII;
            lIIIIIllIlllII.lastX = ((Announcer)lIIIIIllIlllII.Announcer.this).mc.field_1724.method_23317();
            lIIIIIllIlllII.lastZ = ((Announcer)lIIIIIllIlllII.Announcer.this).mc.field_1724.method_23321();
        }

        Moving() {
            Moving lIIIIIlllIllII;
            super("Moving", "moving-enabled", "Send msg how much you moved.");
            lIIIIIlllIllII.msg = lIIIIIlllIllII.sg.add(new StringSetting.Builder().name("moving-msg").description("The chat message for moving a certain amount of blocks.").defaultValue("I just moved {dist} blocks!").build());
            lIIIIIlllIllII.delay = lIIIIIlllIllII.sg.add(new DoubleSetting.Builder().name("moving-delay").description("The amount of delay between moving messages in seconds.").defaultValue(10.0).sliderMax(60.0).build());
            lIIIIIlllIllII.minDist = lIIIIIlllIllII.sg.add(new DoubleSetting.Builder().name("moving-min-dist").description("The minimum distance for a moving message to send into chat.").defaultValue(10.0).sliderMax(100.0).build());
        }

        @Override
        void tick() {
            Moving lIIIIIlllIIlII;
            if (lIIIIIlllIIlII.first) {
                lIIIIIlllIIlII.first = false;
                lIIIIIlllIIlII.updateLastPos();
            }
            double lIIIIIlllIIIll = ((Announcer)lIIIIIlllIIlII.Announcer.this).mc.field_1724.method_23317() - lIIIIIlllIIlII.lastX;
            double lIIIIIlllIIIlI = ((Announcer)lIIIIIlllIIlII.Announcer.this).mc.field_1724.method_23321() - lIIIIIlllIIlII.lastZ;
            lIIIIIlllIIlII.dist += Math.sqrt(lIIIIIlllIIIll * lIIIIIlllIIIll + lIIIIIlllIIIlI * lIIIIIlllIIIlI);
            if (lIIIIIlllIIlII.timer >= lIIIIIlllIIlII.delay.get()) {
                lIIIIIlllIIlII.timer = 0.0;
                if (lIIIIIlllIIlII.dist >= lIIIIIlllIIlII.minDist.get()) {
                    lIIIIIlllIIlII.sendMsg();
                    lIIIIIlllIIlII.dist = 0.0;
                }
            } else {
                lIIIIIlllIIlII.timer += 0.05;
            }
            lIIIIIlllIIlII.updateLastPos();
        }

        @Override
        void reset() {
            lIIIIIlllIlIII.dist = 0.0;
            lIIIIIlllIlIII.timer = 0.0;
            lIIIIIlllIlIII.first = true;
        }

        void sendMsg() {
            Moving lIIIIIllIllIlI;
            ((Announcer)lIIIIIllIllIlI.Announcer.this).mc.field_1724.method_3142(lIIIIIllIllIlI.msg.get().replace("{dist}", String.format("%.1f", lIIIIIllIllIlI.dist)));
        }
    }

    private abstract class Feature {
        protected /* synthetic */ SettingGroup sg;
        private final /* synthetic */ Setting<Boolean> enabled;

        abstract void reset();

        boolean isEnabled() {
            Feature llIlllllllll;
            return llIlllllllll.enabled.get();
        }

        protected Feature(String lllIIIIIlIIl, String lllIIIIIIIll, String lllIIIIIIIlI) {
            Feature lllIIIIIIllI;
            lllIIIIIIllI.sg = lllIIIIIIllI.Announcer.this.settings.createGroup(lllIIIIIlIIl);
            lllIIIIIIllI.enabled = lllIIIIIIllI.sg.add(new BoolSetting.Builder().name(lllIIIIIIIll).description(lllIIIIIIIlI).defaultValue(true).onChanged(llIlllllllII -> {
                Feature llIllllllIll;
                if (llIllllllIll.Announcer.this.isActive() && llIllllllIll.isEnabled()) {
                    MeteorClient.EVENT_BUS.subscribe(llIllllllIll);
                    llIllllllIll.reset();
                } else if (llIllllllIll.Announcer.this.isActive() && !llIllllllIll.isEnabled()) {
                    MeteorClient.EVENT_BUS.unsubscribe(llIllllllIll);
                }
            }).build());
        }

        abstract void tick();
    }

    private class Placing
    extends Feature {
        private final /* synthetic */ Setting<String> msg;
        private /* synthetic */ int count;
        private /* synthetic */ double notPlacedTimer;
        private /* synthetic */ class_2248 lastBlock;

        @Override
        void tick() {
            Placing lllIIllIlIlIlII;
            if (lllIIllIlIlIlII.notPlacedTimer >= 2.0) {
                lllIIllIlIlIlII.sendMsg();
            } else {
                lllIIllIlIlIlII.notPlacedTimer += 0.05;
            }
        }

        @EventHandler
        private void onPlaceBlock(PlaceBlockEvent lllIIllIlIlIllI) {
            Placing lllIIllIlIlIlll;
            if (lllIIllIlIlIlll.lastBlock != null && lllIIllIlIlIlll.lastBlock != lllIIllIlIlIllI.block) {
                lllIIllIlIlIlll.sendMsg();
            }
            lllIIllIlIlIlll.lastBlock = lllIIllIlIlIllI.block;
            ++lllIIllIlIlIlll.count;
            lllIIllIlIlIlll.notPlacedTimer = 0.0;
        }

        @Override
        void reset() {
            lllIIllIlIlllII.lastBlock = null;
            lllIIllIlIlllII.count = 0;
            lllIIllIlIlllII.notPlacedTimer = 0.0;
        }

        Placing() {
            Placing lllIIllIllIIIII;
            super("Placing", "placing-enabled", "Send msg how much blocks you placed.");
            lllIIllIllIIIII.msg = lllIIllIllIIIII.sg.add(new StringSetting.Builder().name("placing-msg").description("The chat message for placing blocks.").defaultValue("I just placed {count} {block}!").build());
        }

        void sendMsg() {
            Placing lllIIllIlIlIIIl;
            if (lllIIllIlIlIIIl.count > 0) {
                ((Announcer)lllIIllIlIlIIIl.Announcer.this).mc.field_1724.method_3142(lllIIllIlIlIIIl.msg.get().replace("{count}", Integer.toString(lllIIllIlIlIIIl.count)).replace("{block}", lllIIllIlIlIIIl.lastBlock.method_9518().getString()));
                lllIIllIlIlIIIl.count = 0;
            }
        }
    }

    private class DropItems
    extends Feature {
        private /* synthetic */ double notDroppedTimer;
        private /* synthetic */ int count;
        private final /* synthetic */ Setting<String> msg;
        private /* synthetic */ class_1792 lastItem;

        DropItems() {
            DropItems lllllllllllllllllllIIIIIlllIllll;
            super("Drop Items", "drop-items-enabled", "Send msg how much items you dropped.");
            lllllllllllllllllllIIIIIlllIllll.msg = lllllllllllllllllllIIIIIlllIllll.sg.add(new StringSetting.Builder().name("drop-items-msg").description("The chat message for dropping items.").defaultValue("I just dropped {count} {item}!").build());
        }

        void sendMsg() {
            DropItems lllllllllllllllllllIIIIIllIlllll;
            if (lllllllllllllllllllIIIIIllIlllll.count > 0) {
                ((Announcer)lllllllllllllllllllIIIIIllIlllll.Announcer.this).mc.field_1724.method_3142(lllllllllllllllllllIIIIIllIlllll.msg.get().replace("{count}", Integer.toString(lllllllllllllllllllIIIIIllIlllll.count)).replace("{item}", lllllllllllllllllllIIIIIllIlllll.lastItem.method_7848().getString()));
                lllllllllllllllllllIIIIIllIlllll.count = 0;
            }
        }

        @Override
        void reset() {
            lllllllllllllllllllIIIIIlllIlIll.lastItem = null;
            lllllllllllllllllllIIIIIlllIlIll.count = 0;
            lllllllllllllllllllIIIIIlllIlIll.notDroppedTimer = 0.0;
        }

        @EventHandler
        private void onDropItems(DropItemsEvent lllllllllllllllllllIIIIIlllIIlIl) {
            DropItems lllllllllllllllllllIIIIIlllIlIII;
            if (lllllllllllllllllllIIIIIlllIlIII.lastItem != null && lllllllllllllllllllIIIIIlllIlIII.lastItem != lllllllllllllllllllIIIIIlllIIlIl.itemStack.method_7909()) {
                lllllllllllllllllllIIIIIlllIlIII.sendMsg();
            }
            lllllllllllllllllllIIIIIlllIlIII.lastItem = lllllllllllllllllllIIIIIlllIIlIl.itemStack.method_7909();
            lllllllllllllllllllIIIIIlllIlIII.count += lllllllllllllllllllIIIIIlllIIlIl.itemStack.method_7947();
            lllllllllllllllllllIIIIIlllIlIII.notDroppedTimer = 0.0;
        }

        @Override
        void tick() {
            DropItems lllllllllllllllllllIIIIIlllIIIlI;
            if (lllllllllllllllllllIIIIIlllIIIlI.notDroppedTimer >= 1.0) {
                lllllllllllllllllllIIIIIlllIIIlI.sendMsg();
            } else {
                lllllllllllllllllllIIIIIlllIIIlI.notDroppedTimer += 0.05;
            }
        }
    }
}

