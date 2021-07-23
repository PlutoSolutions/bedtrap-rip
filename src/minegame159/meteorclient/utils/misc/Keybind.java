/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Keybind
implements ISerializable<Keybind>,
ICopyable<Keybind> {
    private boolean isKey;
    private int value;

    public void set(boolean bl, int n) {
        this.isKey = bl;
        this.value = n;
    }

    public boolean canBindTo(boolean bl, int n) {
        if (bl) {
            return true;
        }
        return n != 0 && n != 1;
    }

    private Keybind(boolean bl, int n) {
        this.set(bl, n);
    }

    @Override
    public Keybind copy() {
        return new Keybind(this.isKey, this.value);
    }

    public boolean matches(boolean bl, int n) {
        if (this.isKey != bl) {
            return false;
        }
        return this.value == n;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10556("isKey", this.isKey);
        class_24872.method_10569("value", this.value);
        return class_24872;
    }

    public int getValue() {
        return this.value;
    }

    public static Keybind fromKey(int n) {
        return new Keybind(true, n);
    }

    @Override
    public ICopyable copy() {
        return this.copy();
    }

    public boolean isPressed() {
        return this.isKey ? Input.isKeyPressed(this.value) : Input.isButtonPressed(this.value);
    }

    @Override
    public ICopyable set(ICopyable iCopyable) {
        return this.set((Keybind)iCopyable);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    @Override
    public Keybind set(Keybind keybind) {
        this.isKey = keybind.isKey;
        this.value = keybind.value;
        return this;
    }

    @Override
    public Keybind fromTag(class_2487 class_24872) {
        this.isKey = class_24872.method_10577("isKey");
        this.value = class_24872.method_10550("value");
        return this;
    }

    public static Keybind fromButton(int n) {
        return new Keybind(false, n);
    }

    public String toString() {
        if (this.value == -1) {
            return "None";
        }
        return this.isKey ? Utils.getKeyName(this.value) : Utils.getButtonName(this.value);
    }

    public boolean isSet() {
        return this.value != -1;
    }
}

