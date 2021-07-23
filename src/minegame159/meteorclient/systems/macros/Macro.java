/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.macros;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.misc.NbtUtils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Macro
implements ISerializable<Macro> {
    public Keybind keybind;
    public List<String> messages = new ArrayList<String>(1);
    public String name = "";

    public void removeMessage(int n) {
        this.messages.remove(n);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Macro macro = (Macro)object;
        return Objects.equals(this.name, macro.name);
    }

    public boolean onAction(boolean bl, int n) {
        if (this.keybind.matches(bl, n) && Utils.mc.field_1755 == null) {
            for (String string : this.messages) {
                Utils.mc.field_1724.method_3142(string);
            }
            return true;
        }
        return false;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        class_24872.method_10566("keybind", (class_2520)this.keybind.toTag());
        class_2499 class_24992 = new class_2499();
        for (String string : this.messages) {
            class_24992.add((Object)class_2519.method_23256((String)string));
        }
        class_24872.method_10566("messages", (class_2520)class_24992);
        return class_24872;
    }

    @Override
    public Macro fromTag(class_2487 class_24872) {
        this.name = class_24872.method_10558("name");
        if (class_24872.method_10545("key")) {
            this.keybind.set(true, class_24872.method_10550("key"));
        } else {
            this.keybind.fromTag(class_24872.method_10562("keybind"));
        }
        this.messages = NbtUtils.listFromTag(class_24872.method_10554("messages", 8), class_2520::method_10714);
        return this;
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public Macro() {
        this.keybind = Keybind.fromKey(-1);
    }

    public void addMessage(String string) {
        this.messages.add(string);
    }
}

