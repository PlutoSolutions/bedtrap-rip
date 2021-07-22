/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules;

import java.util.Objects;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_124;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_310;

public abstract class Module
implements ISerializable<Module> {
    public final /* synthetic */ Category category;
    public final /* synthetic */ String title;
    public final /* synthetic */ String name;
    private /* synthetic */ boolean active;
    public final /* synthetic */ Color color;
    public /* synthetic */ boolean toggleOnBindRelease;
    public final /* synthetic */ Keybind keybind;
    public /* synthetic */ boolean serialize;
    public final /* synthetic */ String description;
    public final /* synthetic */ Settings settings;
    private /* synthetic */ boolean visible;
    protected final /* synthetic */ class_310 mc;

    public void toggle(boolean lllllllllllllllllIlIllIIlIIIllII) {
        Module lllllllllllllllllIlIllIIlIIIlIll;
        if (!lllllllllllllllllIlIllIIlIIIlIll.active) {
            lllllllllllllllllIlIllIIlIIIlIll.active = true;
            Modules.get().addActive(lllllllllllllllllIlIllIIlIIIlIll);
            lllllllllllllllllIlIllIIlIIIlIll.settings.onActivated();
            if (lllllllllllllllllIlIllIIlIIIllII) {
                MeteorClient.EVENT_BUS.subscribe(lllllllllllllllllIlIllIIlIIIlIll);
                lllllllllllllllllIlIllIIlIIIlIll.onActivate();
            }
        } else {
            if (lllllllllllllllllIlIllIIlIIIllII) {
                MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIlIllIIlIIIlIll);
                lllllllllllllllllIlIllIIlIIIlIll.onDeactivate();
            }
            lllllllllllllllllIlIllIIlIIIlIll.active = false;
            Modules.get().removeActive(lllllllllllllllllIlIllIIlIIIlIll);
        }
    }

    public void sendToggledMsg() {
        if (Config.get().chatCommandsInfo) {
            Module lllllllllllllllllIlIllIIlIIIIlIl;
            ChatUtils.sendMsg(lllllllllllllllllIlIllIIlIIIIlIl.hashCode(), class_124.field_1080, "Toggled (highlight)%s(default) %s(default).", lllllllllllllllllIlIllIIlIIIIlIl.title, lllllllllllllllllIlIllIIlIIIIlIl.isActive() ? String.valueOf(new StringBuilder().append((Object)class_124.field_1060).append("on")) : String.valueOf(new StringBuilder().append((Object)class_124.field_1061).append("off")));
        }
    }

    public void info(String lllllllllllllllllIlIllIIIllllIIl, Object ... lllllllllllllllllIlIllIIIllllIII) {
        Module lllllllllllllllllIlIllIIIlllIlll;
        ChatUtils.info(lllllllllllllllllIlIllIIIlllIlll.title, lllllllllllllllllIlIllIIIllllIIl, lllllllllllllllllIlIllIIIllllIII);
    }

    public WWidget getWidget(GuiTheme lllllllllllllllllIlIllIIlIIlIIlI) {
        return null;
    }

    public void toggle() {
        Module lllllllllllllllllIlIllIIlIIIIlll;
        lllllllllllllllllIlIllIIlIIIIlll.toggle(true);
    }

    @Override
    public class_2487 toTag() {
        Module lllllllllllllllllIlIllIIIlIlIIll;
        if (!lllllllllllllllllIlIllIIIlIlIIll.serialize) {
            return null;
        }
        class_2487 lllllllllllllllllIlIllIIIlIlIIlI = new class_2487();
        lllllllllllllllllIlIllIIIlIlIIlI.method_10582("name", lllllllllllllllllIlIllIIIlIlIIll.name);
        lllllllllllllllllIlIllIIIlIlIIlI.method_10566("keybind", (class_2520)lllllllllllllllllIlIllIIIlIlIIll.keybind.toTag());
        lllllllllllllllllIlIllIIIlIlIIlI.method_10556("toggleOnKeyRelease", lllllllllllllllllIlIllIIIlIlIIll.toggleOnBindRelease);
        lllllllllllllllllIlIllIIIlIlIIlI.method_10566("settings", (class_2520)lllllllllllllllllIlIllIIIlIlIIll.settings.toTag());
        lllllllllllllllllIlIllIIIlIlIIlI.method_10556("active", lllllllllllllllllIlIllIIIlIlIIll.active);
        lllllllllllllllllIlIllIIIlIlIIlI.method_10556("visible", lllllllllllllllllIlIllIIIlIlIIll.visible);
        return lllllllllllllllllIlIllIIIlIlIIlI;
    }

    public int hashCode() {
        Module lllllllllllllllllIlIllIIIIlllIIl;
        return Objects.hash(lllllllllllllllllIlIllIIIIlllIIl.name);
    }

    public boolean isVisible() {
        Module lllllllllllllllllIlIllIIIlIllIll;
        return lllllllllllllllllIlIllIIIlIllIll.visible;
    }

    public void setVisible(boolean lllllllllllllllllIlIllIIIlIlllll) {
        lllllllllllllllllIlIllIIIlIllllI.visible = lllllllllllllllllIlIllIIIlIlllll;
    }

    public void onDeactivate() {
    }

    public void warning(String lllllllllllllllllIlIllIIIllIllIl, Object ... lllllllllllllllllIlIllIIIllIllll) {
        Module lllllllllllllllllIlIllIIIlllIIIl;
        ChatUtils.warning(lllllllllllllllllIlIllIIIlllIIIl.title, lllllllllllllllllIlIllIIIllIllIl, lllllllllllllllllIlIllIIIllIllll);
    }

    public void error(String lllllllllllllllllIlIllIIIllIIlll, Object ... lllllllllllllllllIlIllIIIllIIllI) {
        Module lllllllllllllllllIlIllIIIllIIlIl;
        ChatUtils.error(lllllllllllllllllIlIllIIIllIIlIl.title, lllllllllllllllllIlIllIIIllIIlll, lllllllllllllllllIlIllIIIllIIllI);
    }

    public boolean isActive() {
        Module lllllllllllllllllIlIllIIIlIllIII;
        return lllllllllllllllllIlIllIIIlIllIII.active;
    }

    public void onActivate() {
    }

    public String getInfoString() {
        return null;
    }

    public boolean equals(Object lllllllllllllllllIlIllIIIIllllll) {
        Module lllllllllllllllllIlIllIIIIllllIl;
        if (lllllllllllllllllIlIllIIIIllllIl == lllllllllllllllllIlIllIIIIllllll) {
            return true;
        }
        if (lllllllllllllllllIlIllIIIIllllll == null || lllllllllllllllllIlIllIIIIllllIl.getClass() != lllllllllllllllllIlIllIIIIllllll.getClass()) {
            return false;
        }
        Module lllllllllllllllllIlIllIIIIlllllI = (Module)lllllllllllllllllIlIllIIIIllllll;
        return Objects.equals(lllllllllllllllllIlIllIIIIllllIl.name, lllllllllllllllllIlIllIIIIlllllI.name);
    }

    public Module(Category lllllllllllllllllIlIllIIlIIlIllI, String lllllllllllllllllIlIllIIlIIlIlIl, String lllllllllllllllllIlIllIIlIIlIlII) {
        Module lllllllllllllllllIlIllIIlIIllIll;
        lllllllllllllllllIlIllIIlIIllIll.settings = new Settings();
        lllllllllllllllllIlIllIIlIIllIll.visible = true;
        lllllllllllllllllIlIllIIlIIllIll.serialize = true;
        lllllllllllllllllIlIllIIlIIllIll.keybind = Keybind.fromKey(-1);
        lllllllllllllllllIlIllIIlIIllIll.toggleOnBindRelease = false;
        lllllllllllllllllIlIllIIlIIllIll.mc = class_310.method_1551();
        lllllllllllllllllIlIllIIlIIllIll.category = lllllllllllllllllIlIllIIlIIlIllI;
        lllllllllllllllllIlIllIIlIIllIll.name = lllllllllllllllllIlIllIIlIIlIlIl;
        lllllllllllllllllIlIllIIlIIllIll.title = Utils.nameToTitle(lllllllllllllllllIlIllIIlIIlIlIl);
        lllllllllllllllllIlIllIIlIIllIll.description = lllllllllllllllllIlIllIIlIIlIlII;
        lllllllllllllllllIlIllIIlIIllIll.color = Color.fromHsv(Utils.random(0.0, 360.0), 0.35, 1.0);
    }

    @Override
    public Module fromTag(class_2487 lllllllllllllllllIlIllIIIlIIIllI) {
        boolean lllllllllllllllllIlIllIIIlIIlIII;
        Module lllllllllllllllllIlIllIIIlIIIlll;
        if (lllllllllllllllllIlIllIIIlIIIllI.method_10545("key")) {
            lllllllllllllllllIlIllIIIlIIIlll.keybind.set(true, lllllllllllllllllIlIllIIIlIIIllI.method_10550("key"));
        } else {
            lllllllllllllllllIlIllIIIlIIIlll.keybind.fromTag(lllllllllllllllllIlIllIIIlIIIllI.method_10562("keybind"));
        }
        lllllllllllllllllIlIllIIIlIIIlll.toggleOnBindRelease = lllllllllllllllllIlIllIIIlIIIllI.method_10577("toggleOnKeyRelease");
        class_2520 lllllllllllllllllIlIllIIIlIIlIIl = lllllllllllllllllIlIllIIIlIIIllI.method_10580("settings");
        if (lllllllllllllllllIlIllIIIlIIlIIl instanceof class_2487) {
            lllllllllllllllllIlIllIIIlIIIlll.settings.fromTag((class_2487)lllllllllllllllllIlIllIIIlIIlIIl);
        }
        if ((lllllllllllllllllIlIllIIIlIIlIII = lllllllllllllllllIlIllIIIlIIIllI.method_10577("active")) != lllllllllllllllllIlIllIIIlIIIlll.isActive()) {
            lllllllllllllllllIlIllIIIlIIIlll.toggle(Utils.canUpdate());
        }
        lllllllllllllllllIlIllIIIlIIIlll.setVisible(lllllllllllllllllIlIllIIIlIIIllI.method_10577("visible"));
        return lllllllllllllllllIlIllIIIlIIIlll;
    }

    public void info(class_2561 lllllllllllllllllIlIllIIIllllllI) {
        Module lllllllllllllllllIlIllIIlIIIIIIl;
        ChatUtils.sendMsg(lllllllllllllllllIlIllIIlIIIIIIl.title, lllllllllllllllllIlIllIIIllllllI);
    }
}

