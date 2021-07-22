/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.gui.screens.HudElementScreen;
import minegame159.meteorclient.gui.tabs.builtin.HudTab;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.modules.render.hud.BoundingBox;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_310;

public abstract class HudElement
implements ISerializable<HudElement> {
    public final /* synthetic */ String title;
    protected final /* synthetic */ HUD hud;
    protected final /* synthetic */ class_310 mc;
    public final /* synthetic */ BoundingBox box;
    public final /* synthetic */ boolean defaultActive;
    public final /* synthetic */ Settings settings;
    public final /* synthetic */ String name;
    public /* synthetic */ boolean active;
    public final /* synthetic */ String description;

    @Override
    public class_2487 toTag() {
        HudElement llIlIlIIlllIII;
        class_2487 llIlIlIIlllIIl = new class_2487();
        llIlIlIIlllIIl.method_10582("name", llIlIlIIlllIII.name);
        llIlIlIIlllIIl.method_10556("active", llIlIlIIlllIII.active);
        llIlIlIIlllIIl.method_10566("settings", (class_2520)llIlIlIIlllIII.settings.toTag());
        llIlIlIIlllIIl.method_10566("box", (class_2520)llIlIlIIlllIII.box.toTag());
        return llIlIlIIlllIIl;
    }

    protected boolean isInEditor() {
        HudElement llIlIlIIllllIl;
        return HudTab.INSTANCE.isScreen(llIlIlIIllllIl.mc.field_1755) || llIlIlIIllllIl.mc.field_1755 instanceof HudElementScreen || !Utils.canUpdate();
    }

    public abstract void update(HudRenderer var1);

    public void toggle() {
        HudElement llIlIlIlIIIIII;
        llIlIlIlIIIIII.active = !llIlIlIlIIIIII.active;
    }

    @Override
    public HudElement fromTag(class_2487 llIlIlIIllIIll) {
        HudElement llIlIlIIllIlII;
        boolean bl = llIlIlIIllIlII.active = llIlIlIIllIIll.method_10545("active") ? llIlIlIIllIIll.method_10577("active") : llIlIlIIllIlII.defaultActive;
        if (llIlIlIIllIIll.method_10545("settings")) {
            llIlIlIIllIlII.settings.fromTag(llIlIlIIllIIll.method_10562("settings"));
        }
        llIlIlIIllIlII.box.fromTag(llIlIlIIllIIll.method_10562("box"));
        return llIlIlIIllIlII;
    }

    public abstract void render(HudRenderer var1);

    public HudElement(HUD llIlIlIlIIlIIl, String llIlIlIlIIlIII, String llIlIlIlIIIlll) {
        HudElement llIlIlIlIIlIlI;
        llIlIlIlIIlIlI.settings = new Settings();
        llIlIlIlIIlIlI.box = new BoundingBox();
        llIlIlIlIIlIlI.hud = llIlIlIlIIlIIl;
        llIlIlIlIIlIlI.name = llIlIlIlIIlIII;
        llIlIlIlIIlIlI.title = Utils.nameToTitle(llIlIlIlIIlIII);
        llIlIlIlIIlIlI.description = llIlIlIlIIIlll;
        llIlIlIlIIlIlI.defaultActive = true;
        llIlIlIlIIlIlI.mc = class_310.method_1551();
    }

    public HudElement(HUD llIlIlIlIlIIlI, String llIlIlIlIlIllI, String llIlIlIlIlIIII, boolean llIlIlIlIIllll) {
        HudElement llIlIlIlIllIII;
        llIlIlIlIllIII.settings = new Settings();
        llIlIlIlIllIII.box = new BoundingBox();
        llIlIlIlIllIII.hud = llIlIlIlIlIIlI;
        llIlIlIlIllIII.name = llIlIlIlIlIllI;
        llIlIlIlIllIII.title = Utils.nameToTitle(llIlIlIlIlIllI);
        llIlIlIlIllIII.description = llIlIlIlIlIIII;
        llIlIlIlIllIII.defaultActive = llIlIlIlIIllll;
        llIlIlIlIllIII.mc = class_310.method_1551();
    }
}

