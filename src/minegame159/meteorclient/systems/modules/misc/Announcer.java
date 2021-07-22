/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_310
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
import net.minecraft.class_310;
import net.minecraft.class_465;

public class Announcer
extends Module {
    private final Feature[] features = new Feature[]{new Moving(this), new Mining(this), new Placing(this), new DropItems(this), new PickItems(this), new OpenContainer(this)};
    private static final double TICK = 0.05;

    static class_310 access$000(Announcer announcer) {
        return announcer.mc;
    }

    @Override
    public void onActivate() {
        for (Feature feature : this.features) {
            if (!feature.isEnabled()) continue;
            MeteorClient.EVENT_BUS.subscribe(feature);
            feature.reset();
            if (0 <= 3) continue;
            return;
        }
    }

    static class_310 access$100(Announcer announcer) {
        return announcer.mc;
    }

    @Override
    public void onDeactivate() {
        for (Feature feature : this.features) {
            if (!feature.isEnabled()) continue;
            MeteorClient.EVENT_BUS.unsubscribe(feature);
            if (-4 <= 0) continue;
            return;
        }
    }

    static class_310 access$800(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$700(Announcer announcer) {
        return announcer.mc;
    }

    public Announcer() {
        super(Categories.Misc, "announcer", "Announces specified actions into chat.");
    }

    static class_310 access$500(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$900(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$200(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$600(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$400(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$300(Announcer announcer) {
        return announcer.mc;
    }

    static class_310 access$1000(Announcer announcer) {
        return announcer.mc;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        for (Feature feature : this.features) {
            if (!feature.isEnabled()) continue;
            feature.tick();
            if (2 != -1) continue;
            return;
        }
    }

    private class PickItems
    extends Feature {
        final Announcer this$0;
        private class_1792 lastItem;
        private double notPickedUpTimer;
        private int count;
        private final Setting<String> msg;

        void sendMsg() {
            if (this.count > 0) {
                Announcer.access$900((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{count}", Integer.toString(this.count)).replace("{item}", this.lastItem.method_7848().getString()));
                this.count = 0;
            }
        }

        @Override
        void tick() {
            if (this.notPickedUpTimer >= 1.0) {
                this.sendMsg();
            } else {
                this.notPickedUpTimer += 0.05;
            }
        }

        @EventHandler
        private void onPickItems(PickItemsEvent pickItemsEvent) {
            if (this.lastItem != null && this.lastItem != pickItemsEvent.itemStack.method_7909()) {
                this.sendMsg();
            }
            this.lastItem = pickItemsEvent.itemStack.method_7909();
            this.count += pickItemsEvent.itemStack.method_7947();
            this.notPickedUpTimer = 0.0;
        }

        PickItems(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Pick Items", "pick-items-enabled", "Send msg how much items you pick up.");
            this.msg = this.sg.add(new StringSetting.Builder().name("pick-items-msg").description("The chat message for picking up items.").defaultValue("I just picked up {count} {item}!").build());
        }

        @Override
        void reset() {
            this.lastItem = null;
            this.count = 0;
            this.notPickedUpTimer = 0.0;
        }
    }

    private class Mining
    extends Feature {
        private double notBrokenTimer;
        private final Setting<String> msg;
        private int count;
        final Announcer this$0;
        private class_2248 lastBlock;

        void sendMsg() {
            if (this.count > 0) {
                Announcer.access$600((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{count}", Integer.toString(this.count)).replace("{block}", this.lastBlock.method_9518().getString()));
                this.count = 0;
            }
        }

        @Override
        void reset() {
            this.lastBlock = null;
            this.count = 0;
            this.notBrokenTimer = 0.0;
        }

        @EventHandler
        private void onBreakBlock(BreakBlockEvent breakBlockEvent) {
            class_2248 class_22482 = breakBlockEvent.getBlockState((class_1937)Announcer.access$500((Announcer)this.this$0).field_1687).method_26204();
            if (this.lastBlock != null && this.lastBlock != class_22482) {
                this.sendMsg();
            }
            this.lastBlock = class_22482;
            ++this.count;
            this.notBrokenTimer = 0.0;
        }

        Mining(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Mining", "mining-enabled", "Send msg how much blocks you mined.");
            this.msg = this.sg.add(new StringSetting.Builder().name("mining-msg").description("The chat message for mining blocks.").defaultValue("I just mined {count} {block}!").build());
        }

        @Override
        void tick() {
            if (this.notBrokenTimer >= 2.0) {
                this.sendMsg();
            } else {
                this.notBrokenTimer += 0.05;
            }
        }
    }

    private class OpenContainer
    extends Feature {
        final Announcer this$0;
        private final Setting<String> msg;

        @Override
        void reset() {
        }

        public OpenContainer(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Open Container", "open-container-enabled", "Sends msg when you open containers.");
            this.msg = this.sg.add(new StringSetting.Builder().name("open-container-msg").description("The chat message for opening a container.").defaultValue("I just opened {name}!").build());
        }

        @Override
        void tick() {
        }

        void sendMsg(String string) {
            Announcer.access$1000((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{name}", string));
        }

        @EventHandler
        private void onOpenScreen(OpenScreenEvent openScreenEvent) {
            String string;
            if (openScreenEvent.screen instanceof class_465 && !(string = openScreenEvent.screen.method_25440().getString()).isEmpty()) {
                this.sendMsg(string);
            }
        }
    }

    private class Moving
    extends Feature {
        private double timer;
        private double lastX;
        private final Setting<String> msg;
        private double dist;
        final Announcer this$0;
        private boolean first;
        private final Setting<Double> minDist;
        private double lastZ;
        private final Setting<Double> delay;

        void updateLastPos() {
            this.lastX = Announcer.access$200((Announcer)this.this$0).field_1724.method_23317();
            this.lastZ = Announcer.access$300((Announcer)this.this$0).field_1724.method_23321();
        }

        Moving(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Moving", "moving-enabled", "Send msg how much you moved.");
            this.msg = this.sg.add(new StringSetting.Builder().name("moving-msg").description("The chat message for moving a certain amount of blocks.").defaultValue("I just moved {dist} blocks!").build());
            this.delay = this.sg.add(new DoubleSetting.Builder().name("moving-delay").description("The amount of delay between moving messages in seconds.").defaultValue(10.0).sliderMax(60.0).build());
            this.minDist = this.sg.add(new DoubleSetting.Builder().name("moving-min-dist").description("The minimum distance for a moving message to send into chat.").defaultValue(10.0).sliderMax(100.0).build());
        }

        @Override
        void tick() {
            if (this.first) {
                this.first = false;
                this.updateLastPos();
            }
            double d = Announcer.access$000((Announcer)this.this$0).field_1724.method_23317() - this.lastX;
            double d2 = Announcer.access$100((Announcer)this.this$0).field_1724.method_23321() - this.lastZ;
            this.dist += Math.sqrt(d * d + d2 * d2);
            if (this.timer >= this.delay.get()) {
                this.timer = 0.0;
                if (this.dist >= this.minDist.get()) {
                    this.sendMsg();
                    this.dist = 0.0;
                }
            } else {
                this.timer += 0.05;
            }
            this.updateLastPos();
        }

        @Override
        void reset() {
            this.dist = 0.0;
            this.timer = 0.0;
            this.first = true;
        }

        void sendMsg() {
            Announcer.access$400((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{dist}", String.format("%.1f", this.dist)));
        }
    }

    private abstract class Feature {
        final Announcer this$0;
        protected SettingGroup sg;
        private final Setting<Boolean> enabled;

        abstract void reset();

        boolean isEnabled() {
            return this.enabled.get();
        }

        private void lambda$new$0(Boolean bl) {
            if (this.this$0.isActive() && this.isEnabled()) {
                MeteorClient.EVENT_BUS.subscribe(this);
                this.reset();
            } else if (this.this$0.isActive() && !this.isEnabled()) {
                MeteorClient.EVENT_BUS.unsubscribe(this);
            }
        }

        protected Feature(Announcer announcer, String string, String string2, String string3) {
            this.this$0 = announcer;
            this.sg = announcer.settings.createGroup(string);
            this.enabled = this.sg.add(new BoolSetting.Builder().name(string2).description(string3).defaultValue(true).onChanged(this::lambda$new$0).build());
        }

        abstract void tick();
    }

    private class Placing
    extends Feature {
        final Announcer this$0;
        private final Setting<String> msg;
        private int count;
        private double notPlacedTimer;
        private class_2248 lastBlock;

        @Override
        void tick() {
            if (this.notPlacedTimer >= 2.0) {
                this.sendMsg();
            } else {
                this.notPlacedTimer += 0.05;
            }
        }

        @EventHandler
        private void onPlaceBlock(PlaceBlockEvent placeBlockEvent) {
            if (this.lastBlock != null && this.lastBlock != placeBlockEvent.block) {
                this.sendMsg();
            }
            this.lastBlock = placeBlockEvent.block;
            ++this.count;
            this.notPlacedTimer = 0.0;
        }

        @Override
        void reset() {
            this.lastBlock = null;
            this.count = 0;
            this.notPlacedTimer = 0.0;
        }

        Placing(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Placing", "placing-enabled", "Send msg how much blocks you placed.");
            this.msg = this.sg.add(new StringSetting.Builder().name("placing-msg").description("The chat message for placing blocks.").defaultValue("I just placed {count} {block}!").build());
        }

        void sendMsg() {
            if (this.count > 0) {
                Announcer.access$700((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{count}", Integer.toString(this.count)).replace("{block}", this.lastBlock.method_9518().getString()));
                this.count = 0;
            }
        }
    }

    private class DropItems
    extends Feature {
        private double notDroppedTimer;
        private int count;
        private final Setting<String> msg;
        final Announcer this$0;
        private class_1792 lastItem;

        DropItems(Announcer announcer) {
            this.this$0 = announcer;
            super(announcer, "Drop Items", "drop-items-enabled", "Send msg how much items you dropped.");
            this.msg = this.sg.add(new StringSetting.Builder().name("drop-items-msg").description("The chat message for dropping items.").defaultValue("I just dropped {count} {item}!").build());
        }

        void sendMsg() {
            if (this.count > 0) {
                Announcer.access$800((Announcer)this.this$0).field_1724.method_3142(this.msg.get().replace("{count}", Integer.toString(this.count)).replace("{item}", this.lastItem.method_7848().getString()));
                this.count = 0;
            }
        }

        @Override
        void reset() {
            this.lastItem = null;
            this.count = 0;
            this.notDroppedTimer = 0.0;
        }

        @EventHandler
        private void onDropItems(DropItemsEvent dropItemsEvent) {
            if (this.lastItem != null && this.lastItem != dropItemsEvent.itemStack.method_7909()) {
                this.sendMsg();
            }
            this.lastItem = dropItemsEvent.itemStack.method_7909();
            this.count += dropItemsEvent.itemStack.method_7947();
            this.notDroppedTimer = 0.0;
        }

        @Override
        void tick() {
            if (this.notDroppedTimer >= 1.0) {
                this.sendMsg();
            } else {
                this.notDroppedTimer += 0.05;
            }
        }
    }
}

