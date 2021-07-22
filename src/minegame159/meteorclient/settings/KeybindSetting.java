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

public class KeybindSetting
extends Setting<Keybind> {
    private final /* synthetic */ Runnable action;
    public /* synthetic */ WKeybind widget;

    @Override
    public void reset(boolean lllllllllllllllllIlIIIIIIIIlllIl) {
        KeybindSetting lllllllllllllllllIlIIIIIIIlIIIII;
        if (lllllllllllllllllIlIIIIIIIlIIIII.value == null) {
            lllllllllllllllllIlIIIIIIIlIIIII.value = ((Keybind)lllllllllllllllllIlIIIIIIIlIIIII.defaultValue).copy();
        } else {
            ((Keybind)lllllllllllllllllIlIIIIIIIlIIIII.value).set((Keybind)lllllllllllllllllIlIIIIIIIlIIIII.defaultValue);
        }
        if (lllllllllllllllllIlIIIIIIIIlllIl) {
            lllllllllllllllllIlIIIIIIIlIIIII.changed();
        }
    }

    @Override
    public Keybind fromTag(class_2487 lllllllllllllllllIlIIIIIIIIIlIlI) {
        KeybindSetting lllllllllllllllllIlIIIIIIIIIlIll;
        ((Keybind)lllllllllllllllllIlIIIIIIIIIlIll.get()).fromTag(lllllllllllllllllIlIIIIIIIIIlIlI.method_10562("value"));
        return (Keybind)lllllllllllllllllIlIIIIIIIIIlIll.get();
    }

    @Override
    protected Keybind parseImpl(String lllllllllllllllllIlIIIIIIIIllIII) {
        try {
            return Keybind.fromKey(Integer.parseInt(lllllllllllllllllIlIIIIIIIIllIII.trim()));
        }
        catch (NumberFormatException lllllllllllllllllIlIIIIIIIIllIlI) {
            return null;
        }
    }

    @EventHandler(priority=100)
    private void onKey(KeyEvent lllllllllllllllllIlIIIIIIIlIlIIl) {
        KeybindSetting lllllllllllllllllIlIIIIIIIlIllII;
        if (lllllllllllllllllIlIIIIIIIlIlIIl.action == KeyAction.Release && ((Keybind)lllllllllllllllllIlIIIIIIIlIllII.get()).matches(true, lllllllllllllllllIlIIIIIIIlIlIIl.key) && lllllllllllllllllIlIIIIIIIlIllII.module.isActive() && lllllllllllllllllIlIIIIIIIlIllII.action != null) {
            lllllllllllllllllIlIIIIIIIlIllII.action.run();
        }
    }

    @EventHandler(priority=100)
    private void onMouseButton(MouseButtonEvent lllllllllllllllllIlIIIIIIIlIIIll) {
        KeybindSetting lllllllllllllllllIlIIIIIIIlIIllI;
        if (lllllllllllllllllIlIIIIIIIlIIIll.action == KeyAction.Release && ((Keybind)lllllllllllllllllIlIIIIIIIlIIllI.get()).matches(false, lllllllllllllllllIlIIIIIIIlIIIll.button) && lllllllllllllllllIlIIIIIIIlIIllI.module.isActive() && lllllllllllllllllIlIIIIIIIlIIllI.action != null) {
            lllllllllllllllllIlIIIIIIIlIIllI.action.run();
        }
    }

    public KeybindSetting(String lllllllllllllllllIlIIIIIIlIIlIIl, String lllllllllllllllllIlIIIIIIlIIlIII, Keybind lllllllllllllllllIlIIIIIIIllllll, Consumer<Keybind> lllllllllllllllllIlIIIIIIlIIIllI, Consumer<Setting<Keybind>> lllllllllllllllllIlIIIIIIlIIIlIl, IVisible lllllllllllllllllIlIIIIIIIllllII, Runnable lllllllllllllllllIlIIIIIIIlllIll) {
        super(lllllllllllllllllIlIIIIIIlIIlIIl, lllllllllllllllllIlIIIIIIlIIlIII, lllllllllllllllllIlIIIIIIIllllll, lllllllllllllllllIlIIIIIIlIIIllI, lllllllllllllllllIlIIIIIIlIIIlIl, lllllllllllllllllIlIIIIIIIllllII);
        KeybindSetting lllllllllllllllllIlIIIIIIlIIIIlI;
        lllllllllllllllllIlIIIIIIlIIIIlI.action = lllllllllllllllllIlIIIIIIIlllIll;
        MeteorClient.EVENT_BUS.subscribe(lllllllllllllllllIlIIIIIIlIIIIlI);
    }

    @Override
    protected boolean isValueValid(Keybind lllllllllllllllllIlIIIIIIIIlIlII) {
        return true;
    }

    @EventHandler(priority=200)
    private void onMouseButtonBinding(MouseButtonEvent lllllllllllllllllIlIIIIIIIllIIIl) {
        KeybindSetting lllllllllllllllllIlIIIIIIIllIIII;
        if (lllllllllllllllllIlIIIIIIIllIIII.widget != null && lllllllllllllllllIlIIIIIIIllIIII.widget.onAction(false, lllllllllllllllllIlIIIIIIIllIIIl.button)) {
            lllllllllllllllllIlIIIIIIIllIIIl.cancel();
        }
    }

    @EventHandler(priority=200)
    private void onKeyBinding(KeyEvent lllllllllllllllllIlIIIIIIIllIlIl) {
        KeybindSetting lllllllllllllllllIlIIIIIIIlllIII;
        if (lllllllllllllllllIlIIIIIIIlllIII.widget != null && lllllllllllllllllIlIIIIIIIlllIII.widget.onAction(true, lllllllllllllllllIlIIIIIIIllIlIl.key)) {
            lllllllllllllllllIlIIIIIIIllIlIl.cancel();
        }
    }

    @Override
    public class_2487 toTag() {
        KeybindSetting lllllllllllllllllIlIIIIIIIIIllll;
        class_2487 lllllllllllllllllIlIIIIIIIIlIIII = lllllllllllllllllIlIIIIIIIIIllll.saveGeneral();
        lllllllllllllllllIlIIIIIIIIlIIII.method_10566("value", (class_2520)((Keybind)lllllllllllllllllIlIIIIIIIIIllll.get()).toTag());
        return lllllllllllllllllIlIIIIIIIIlIIII;
    }

    public static class Builder {
        private /* synthetic */ Runnable action;
        private /* synthetic */ Consumer<Keybind> onChanged;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String description;
        private /* synthetic */ Keybind defaultValue;
        private /* synthetic */ Consumer<Setting<Keybind>> onModuleActivated;
        private /* synthetic */ String name;

        public Builder onChanged(Consumer<Keybind> lllIllllIIIIlIl) {
            Builder lllIllllIIIlIII;
            lllIllllIIIlIII.onChanged = lllIllllIIIIlIl;
            return lllIllllIIIlIII;
        }

        public Builder description(String lllIllllIIlIIll) {
            Builder lllIllllIIlIIlI;
            lllIllllIIlIIlI.description = lllIllllIIlIIll;
            return lllIllllIIlIIlI;
        }

        public Builder defaultValue(Keybind lllIllllIIIlIll) {
            Builder lllIllllIIIllII;
            lllIllllIIIllII.defaultValue = lllIllllIIIlIll;
            return lllIllllIIIllII;
        }

        public Builder onModuleActivated(Consumer<Setting<Keybind>> lllIlllIlllllll) {
            Builder lllIllllIIIIIlI;
            lllIllllIIIIIlI.onModuleActivated = lllIlllIlllllll;
            return lllIllllIIIIIlI;
        }

        public Builder name(String lllIllllIIlIlll) {
            Builder lllIllllIIllIII;
            lllIllllIIllIII.name = lllIllllIIlIlll;
            return lllIllllIIllIII;
        }

        public Builder visible(IVisible lllIlllIllllIIl) {
            Builder lllIlllIllllIlI;
            lllIlllIllllIlI.visible = lllIlllIllllIIl;
            return lllIlllIllllIlI;
        }

        public Builder() {
            Builder lllIllllIIllllI;
            lllIllllIIllllI.name = "undefined";
            lllIllllIIllllI.description = "";
            lllIllllIIllllI.defaultValue = Keybind.fromKey(-1);
        }

        public KeybindSetting build() {
            Builder lllIlllIlllIIIl;
            return new KeybindSetting(lllIlllIlllIIIl.name, lllIlllIlllIIIl.description, lllIlllIlllIIIl.defaultValue, lllIlllIlllIIIl.onChanged, lllIlllIlllIIIl.onModuleActivated, lllIlllIlllIIIl.visible, lllIlllIlllIIIl.action);
        }

        public Builder action(Runnable lllIlllIlllIlIl) {
            Builder lllIlllIlllIlII;
            lllIlllIlllIlII.action = lllIlllIlllIlIl;
            return lllIlllIlllIlII;
        }
    }
}

