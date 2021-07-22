/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_345
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.HashMap;
import java.util.WeakHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderBossBarEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2561;
import net.minecraft.class_345;

public class BossStack
extends Module {
    public final Setting<Boolean> stack;
    private final SettingGroup sgGeneral;
    public final Setting<Boolean> hideName;
    private final Setting<Double> spacing;
    public static final WeakHashMap<class_345, Integer> barMap = new WeakHashMap();

    private static void lambda$onGetBars$1(HashMap hashMap, class_345 class_3452) {
        String string = class_3452.method_5414().method_10851();
        if (hashMap.containsKey(string)) {
            barMap.compute((class_345)hashMap.get(string), BossStack::lambda$onGetBars$0);
        } else {
            hashMap.put(string, class_3452);
        }
    }

    @EventHandler
    private void onFetchText(RenderBossBarEvent.BossText bossText) {
        if (this.hideName.get().booleanValue()) {
            bossText.name = class_2561.method_30163((String)"");
            return;
        }
        if (barMap.isEmpty() || !this.stack.get().booleanValue()) {
            return;
        }
        class_345 class_3452 = bossText.bossBar;
        Integer n = barMap.get((Object)class_3452);
        barMap.remove((Object)class_3452);
        if (n != null && !this.hideName.get().booleanValue()) {
            bossText.name = bossText.name.method_27662().method_27693(String.valueOf(new StringBuilder().append(" x").append(n)));
        }
    }

    @EventHandler
    private void onGetBars(RenderBossBarEvent.BossIterator bossIterator) {
        if (this.stack.get().booleanValue()) {
            HashMap hashMap = new HashMap();
            bossIterator.iterator.forEachRemaining(arg_0 -> BossStack.lambda$onGetBars$1(hashMap, arg_0));
            bossIterator.iterator = hashMap.values().iterator();
        }
    }

    public BossStack() {
        super(Categories.Render, "boss-stack", "Stacks boss bars to make your HUD less cluttered.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.stack = this.sgGeneral.add(new BoolSetting.Builder().name("stack").description("Stacks boss bars and adds a counter to the text.").defaultValue(true).build());
        this.hideName = this.sgGeneral.add(new BoolSetting.Builder().name("hide-name").description("Hides the names of boss bars.").defaultValue(false).build());
        this.spacing = this.sgGeneral.add(new DoubleSetting.Builder().name("bar-spacing").description("The spacing reduction between each boss bar.").defaultValue(10.0).min(0.0).sliderMax(10.0).build());
    }

    private static Integer lambda$onGetBars$0(class_345 class_3452, Integer n) {
        return n == null ? 2 : n + 1;
    }

    @EventHandler
    private void onSpaceBars(RenderBossBarEvent.BossSpacing bossSpacing) {
        bossSpacing.spacing = this.spacing.get().intValue();
    }
}

