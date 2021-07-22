/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.gui.widgets.WKeybind;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class KeybindSetting
extends Setting<Keybind> {
    private final Runnable action;
    public WKeybind widget;

    @Override
    public void reset(boolean bl) {
        if (this.value == null) {
            this.value = ((Keybind)this.defaultValue).copy();
        } else {
            ((Keybind)this.value).set((Keybind)this.defaultValue);
        }
        if (bl) {
            this.changed();
        }
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    protected boolean isValueValid(Object object) {
        return this.isValueValid((Keybind)object);
    }

    @Override
    public Keybind fromTag(class_2487 class_24872) {
        ((Keybind)this.get()).fromTag(class_24872.method_10562("value"));
        return (Keybind)this.get();
    }

    @Override
    protected Keybind parseImpl(String string) {
        try {
            return Keybind.fromKey(Integer.parseInt(string.trim()));
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @EventHandler(priority=100)
    private void onKey(KeyEvent keyEvent) {
        if (keyEvent.action == KeyAction.Release && ((Keybind)this.get()).matches(true, keyEvent.key) && this.module.isActive() && this.action != null) {
            this.action.run();
        }
    }

    @Override
    protected Object parseImpl(String string) {
        return this.parseImpl(string);
    }

    @EventHandler(priority=100)
    private void onMouseButton(MouseButtonEvent mouseButtonEvent) {
        if (mouseButtonEvent.action == KeyAction.Release && ((Keybind)this.get()).matches(false, mouseButtonEvent.button) && this.module.isActive() && this.action != null) {
            this.action.run();
        }
    }

    public KeybindSetting(String string, String string2, Keybind keybind, Consumer<Keybind> consumer, Consumer<Setting<Keybind>> consumer2, IVisible iVisible, Runnable runnable) {
        super(string, string2, keybind, consumer, consumer2, iVisible);
        this.action = runnable;
        MeteorClient.EVENT_BUS.subscribe(this);
    }

    @Override
    protected boolean isValueValid(Keybind keybind) {
        return true;
    }

    @EventHandler(priority=200)
    private void onMouseButtonBinding(MouseButtonEvent mouseButtonEvent) {
        if (this.widget != null && this.widget.onAction(false, mouseButtonEvent.button)) {
            mouseButtonEvent.cancel();
        }
    }

    @EventHandler(priority=200)
    private void onKeyBinding(KeyEvent keyEvent) {
        if (this.widget != null && this.widget.onAction(true, keyEvent.key)) {
            keyEvent.cancel();
        }
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = this.saveGeneral();
        class_24872.method_10566("value", (class_2520)((Keybind)this.get()).toTag());
        return class_24872;
    }

    public static class Builder {
        private Runnable action;
        private Consumer<Keybind> onChanged;
        private IVisible visible;
        private String description = "";
        private Keybind defaultValue = Keybind.fromKey(-1);
        private Consumer<Setting<Keybind>> onModuleActivated;
        private String name = "undefined";

        public Builder onChanged(Consumer<Keybind> consumer) {
            this.onChanged = consumer;
            return this;
        }

        public Builder description(String string) {
            this.description = string;
            return this;
        }

        public Builder defaultValue(Keybind keybind) {
            this.defaultValue = keybind;
            return this;
        }

        public Builder onModuleActivated(Consumer<Setting<Keybind>> consumer) {
            this.onModuleActivated = consumer;
            return this;
        }

        public Builder name(String string) {
            this.name = string;
            return this;
        }

        public Builder visible(IVisible iVisible) {
            this.visible = iVisible;
            return this;
        }

        public KeybindSetting build() {
            return new KeybindSetting(this.name, this.description, this.defaultValue, this.onChanged, this.onModuleActivated, this.visible, this.action);
        }

        public Builder action(Runnable runnable) {
            this.action = runnable;
            return this;
        }
    }
}

