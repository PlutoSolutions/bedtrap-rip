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
    public final /* synthetic */ Setting<Boolean> stack;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Boolean> hideName;
    private final /* synthetic */ Setting<Double> spacing;
    public static final /* synthetic */ WeakHashMap<class_345, Integer> barMap;

    @EventHandler
    private void onFetchText(RenderBossBarEvent.BossText llIIlllIlllIlI) {
        BossStack llIIlllIllIlll;
        if (llIIlllIllIlll.hideName.get().booleanValue()) {
            llIIlllIlllIlI.name = class_2561.method_30163((String)"");
            return;
        }
        if (barMap.isEmpty() || !llIIlllIllIlll.stack.get().booleanValue()) {
            return;
        }
        class_345 llIIlllIlllIIl = llIIlllIlllIlI.bossBar;
        Integer llIIlllIlllIII = barMap.get((Object)llIIlllIlllIIl);
        barMap.remove((Object)llIIlllIlllIIl);
        if (llIIlllIlllIII != null && !llIIlllIllIlll.hideName.get().booleanValue()) {
            llIIlllIlllIlI.name = llIIlllIlllIlI.name.method_27662().method_27693(String.valueOf(new StringBuilder().append(" x").append(llIIlllIlllIII)));
        }
    }

    @EventHandler
    private void onGetBars(RenderBossBarEvent.BossIterator llIIlllIlIlIII) {
        BossStack llIIlllIlIlIIl;
        if (llIIlllIlIlIIl.stack.get().booleanValue()) {
            HashMap llIIlllIlIlIlI = new HashMap();
            llIIlllIlIlIII.iterator.forEachRemaining(llIIlllIIlllIl -> {
                String llIIlllIIlllll = llIIlllIIlllIl.method_5414().method_10851();
                if (llIIlllIlIlIlI.containsKey(llIIlllIIlllll)) {
                    barMap.compute((class_345)llIIlllIlIlIlI.get(llIIlllIIlllll), (llIIlllIIllIlI, llIIlllIIllIIl) -> llIIlllIIllIIl == null ? 2 : llIIlllIIllIIl + 1);
                } else {
                    llIIlllIlIlIlI.put(llIIlllIIlllll, llIIlllIIlllIl);
                }
            });
            llIIlllIlIlIII.iterator = llIIlllIlIlIlI.values().iterator();
        }
    }

    public BossStack() {
        super(Categories.Render, "boss-stack", "Stacks boss bars to make your HUD less cluttered.");
        BossStack llIIllllIIIIIl;
        llIIllllIIIIIl.sgGeneral = llIIllllIIIIIl.settings.getDefaultGroup();
        llIIllllIIIIIl.stack = llIIllllIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("stack").description("Stacks boss bars and adds a counter to the text.").defaultValue(true).build());
        llIIllllIIIIIl.hideName = llIIllllIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("hide-name").description("Hides the names of boss bars.").defaultValue(false).build());
        llIIllllIIIIIl.spacing = llIIllllIIIIIl.sgGeneral.add(new DoubleSetting.Builder().name("bar-spacing").description("The spacing reduction between each boss bar.").defaultValue(10.0).min(0.0).sliderMax(10.0).build());
    }

    static {
        barMap = new WeakHashMap();
    }

    @EventHandler
    private void onSpaceBars(RenderBossBarEvent.BossSpacing llIIlllIlIlllI) {
        BossStack llIIlllIlIllll;
        llIIlllIlIlllI.spacing = llIIlllIlIllll.spacing.get().intValue();
    }
}

