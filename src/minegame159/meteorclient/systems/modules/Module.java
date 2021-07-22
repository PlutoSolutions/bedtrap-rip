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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class Module
implements ISerializable<Module> {
    public final Category category;
    public final String title;
    public final String name;
    private boolean active;
    public final Color color;
    public boolean toggleOnBindRelease = false;
    public final Keybind keybind;
    public boolean serialize = true;
    public final String description;
    public final Settings settings = new Settings();
    private boolean visible = true;
    protected final class_310 mc;

    public void toggle(boolean bl) {
        if (!this.active) {
            this.active = true;
            Modules.get().addActive(this);
            this.settings.onActivated();
            if (bl) {
                MeteorClient.EVENT_BUS.subscribe(this);
                this.onActivate();
            }
        } else {
            if (bl) {
                MeteorClient.EVENT_BUS.unsubscribe(this);
                this.onDeactivate();
            }
            this.active = false;
            Modules.get().removeActive(this);
        }
    }

    public void sendToggledMsg() {
        if (Config.get().chatCommandsInfo) {
            ChatUtils.sendMsg(this.hashCode(), class_124.field_1080, "Toggled (highlight)%s(default) %s(default).", this.title, this.isActive() ? String.valueOf(new StringBuilder().append((Object)class_124.field_1060).append("on")) : String.valueOf(new StringBuilder().append((Object)class_124.field_1061).append("off")));
        }
    }

    public void info(String string, Object ... arrobject) {
        ChatUtils.info(this.title, string, arrobject);
    }

    public WWidget getWidget(GuiTheme guiTheme) {
        return null;
    }

    public void toggle() {
        this.toggle(true);
    }

    @Override
    public class_2487 toTag() {
        if (!this.serialize) {
            return null;
        }
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        class_24872.method_10566("keybind", (class_2520)this.keybind.toTag());
        class_24872.method_10556("toggleOnKeyRelease", this.toggleOnBindRelease);
        class_24872.method_10566("settings", (class_2520)this.settings.toTag());
        class_24872.method_10556("active", this.active);
        class_24872.method_10556("visible", this.visible);
        return class_24872;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean bl) {
        this.visible = bl;
    }

    public void onDeactivate() {
    }

    public void warning(String string, Object ... arrobject) {
        ChatUtils.warning(this.title, string, arrobject);
    }

    public void error(String string, Object ... arrobject) {
        ChatUtils.error(this.title, string, arrobject);
    }

    public boolean isActive() {
        return this.active;
    }

    public void onActivate() {
    }

    public String getInfoString() {
        return null;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Module module = (Module)object;
        return Objects.equals(this.name, module.name);
    }

    public Module(Category category, String string, String string2) {
        this.keybind = Keybind.fromKey(-1);
        this.mc = class_310.method_1551();
        this.category = category;
        this.name = string;
        this.title = Utils.nameToTitle(string);
        this.description = string2;
        this.color = Color.fromHsv(Utils.random(0.0, 360.0), 0.35, 1.0);
    }

    @Override
    public Module fromTag(class_2487 class_24872) {
        boolean bl;
        if (class_24872.method_10545("key")) {
            this.keybind.set(true, class_24872.method_10550("key"));
        } else {
            this.keybind.fromTag(class_24872.method_10562("keybind"));
        }
        this.toggleOnBindRelease = class_24872.method_10577("toggleOnKeyRelease");
        class_2520 class_25202 = class_24872.method_10580("settings");
        if (class_25202 instanceof class_2487) {
            this.settings.fromTag((class_2487)class_25202);
        }
        if ((bl = class_24872.method_10577("active")) != this.isActive()) {
            this.toggle(Utils.canUpdate());
        }
        this.setVisible(class_24872.method_10577("visible"));
        return this;
    }

    public void info(class_2561 class_25612) {
        ChatUtils.sendMsg(this.title, class_25612);
    }
}

