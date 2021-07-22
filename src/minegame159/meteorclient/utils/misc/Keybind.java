/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.utils.misc;

import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_2487;

public class Keybind
implements ISerializable<Keybind>,
ICopyable<Keybind> {
    private /* synthetic */ boolean isKey;
    private /* synthetic */ int value;

    public void set(boolean lllIIlIlIlllI, int lllIIlIllIIII) {
        lllIIlIllIIlI.isKey = lllIIlIlIlllI;
        lllIIlIllIIlI.value = lllIIlIllIIII;
    }

    public boolean canBindTo(boolean lllIIlIllIlll, int lllIIlIllIllI) {
        if (lllIIlIllIlll) {
            return true;
        }
        return lllIIlIllIllI != 0 && lllIIlIllIllI != 1;
    }

    private Keybind(boolean lllIIllIIllIl, int lllIIllIIlIIl) {
        Keybind lllIIllIIlIll;
        lllIIllIIlIll.set(lllIIllIIllIl, lllIIllIIlIIl);
    }

    @Override
    public Keybind copy() {
        Keybind lllIIlIIllIIl;
        return new Keybind(lllIIlIIllIIl.isKey, lllIIlIIllIIl.value);
    }

    public boolean matches(boolean lllIIlIIlllll, int lllIIlIlIIIIl) {
        Keybind lllIIlIlIIIII;
        if (lllIIlIlIIIII.isKey != lllIIlIIlllll) {
            return false;
        }
        return lllIIlIlIIIII.value == lllIIlIlIIIIl;
    }

    @Override
    public class_2487 toTag() {
        Keybind lllIIIlIIllll;
        class_2487 lllIIIlIlIIII = new class_2487();
        lllIIIlIlIIII.method_10556("isKey", lllIIIlIIllll.isKey);
        lllIIIlIlIIII.method_10569("value", lllIIIlIIllll.value);
        return lllIIIlIlIIII;
    }

    public int getValue() {
        Keybind lllIIllIIIIIl;
        return lllIIllIIIIIl.value;
    }

    public static Keybind fromKey(int lllIIllIIIllI) {
        return new Keybind(true, lllIIllIIIllI);
    }

    public boolean isPressed() {
        Keybind lllIIlIIlllII;
        return lllIIlIIlllII.isKey ? Input.isKeyPressed(lllIIlIIlllII.value) : Input.isButtonPressed(lllIIlIIlllII.value);
    }

    @Override
    public Keybind set(Keybind lllIIlIlIIlll) {
        Keybind lllIIlIlIlIII;
        lllIIlIlIlIII.isKey = lllIIlIlIIlll.isKey;
        lllIIlIlIlIII.value = lllIIlIlIIlll.value;
        return lllIIlIlIlIII;
    }

    @Override
    public Keybind fromTag(class_2487 lllIIIlIIlIlI) {
        Keybind lllIIIlIIlIIl;
        lllIIIlIIlIIl.isKey = lllIIIlIIlIlI.method_10577("isKey");
        lllIIIlIIlIIl.value = lllIIIlIIlIlI.method_10550("value");
        return lllIIIlIIlIIl;
    }

    public static Keybind fromButton(int lllIIllIIIlII) {
        return new Keybind(false, lllIIllIIIlII);
    }

    public String toString() {
        Keybind lllIIIlIlIlII;
        if (lllIIIlIlIlII.value == -1) {
            return "None";
        }
        return lllIIIlIlIlII.isKey ? Utils.getKeyName(lllIIIlIlIlII.value) : Utils.getButtonName(lllIIIlIlIlII.value);
    }

    public boolean isSet() {
        Keybind lllIIlIllllIl;
        return lllIIlIllllIl.value != -1;
    }
}

