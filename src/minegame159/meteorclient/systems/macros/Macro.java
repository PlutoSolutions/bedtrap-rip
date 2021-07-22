/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
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

public class Macro
implements ISerializable<Macro> {
    public /* synthetic */ Keybind keybind;
    public /* synthetic */ List<String> messages;
    public /* synthetic */ String name;

    public void removeMessage(int llllllllllllllllIllllIlIIlIIIIII) {
        Macro llllllllllllllllIllllIlIIlIIIIIl;
        llllllllllllllllIllllIlIIlIIIIIl.messages.remove(llllllllllllllllIllllIlIIlIIIIII);
    }

    public boolean equals(Object llllllllllllllllIllllIlIIIIlIllI) {
        Macro llllllllllllllllIllllIlIIIIllIlI;
        if (llllllllllllllllIllllIlIIIIllIlI == llllllllllllllllIllllIlIIIIlIllI) {
            return true;
        }
        if (llllllllllllllllIllllIlIIIIlIllI == null || llllllllllllllllIllllIlIIIIllIlI.getClass() != llllllllllllllllIllllIlIIIIlIllI.getClass()) {
            return false;
        }
        Macro llllllllllllllllIllllIlIIIIllIII = (Macro)llllllllllllllllIllllIlIIIIlIllI;
        return Objects.equals(llllllllllllllllIllllIlIIIIllIlI.name, llllllllllllllllIllllIlIIIIllIII.name);
    }

    public boolean onAction(boolean llllllllllllllllIllllIlIIIllIlIl, int llllllllllllllllIllllIlIIIllIlll) {
        Macro llllllllllllllllIllllIlIIIlllIIl;
        if (llllllllllllllllIllllIlIIIlllIIl.keybind.matches(llllllllllllllllIllllIlIIIllIlIl, llllllllllllllllIllllIlIIIllIlll) && Utils.mc.field_1755 == null) {
            for (String llllllllllllllllIllllIlIIIlllIlI : llllllllllllllllIllllIlIIIlllIIl.messages) {
                Utils.mc.field_1724.method_3142(llllllllllllllllIllllIlIIIlllIlI);
            }
            return true;
        }
        return false;
    }

    @Override
    public class_2487 toTag() {
        Macro llllllllllllllllIllllIlIIIlIlIII;
        class_2487 llllllllllllllllIllllIlIIIlIlIlI = new class_2487();
        llllllllllllllllIllllIlIIIlIlIlI.method_10582("name", llllllllllllllllIllllIlIIIlIlIII.name);
        llllllllllllllllIllllIlIIIlIlIlI.method_10566("keybind", (class_2520)llllllllllllllllIllllIlIIIlIlIII.keybind.toTag());
        class_2499 llllllllllllllllIllllIlIIIlIlIIl = new class_2499();
        for (String llllllllllllllllIllllIlIIIlIllII : llllllllllllllllIllllIlIIIlIlIII.messages) {
            llllllllllllllllIllllIlIIIlIlIIl.add((Object)class_2519.method_23256((String)llllllllllllllllIllllIlIIIlIllII));
        }
        llllllllllllllllIllllIlIIIlIlIlI.method_10566("messages", (class_2520)llllllllllllllllIllllIlIIIlIlIIl);
        return llllllllllllllllIllllIlIIIlIlIlI;
    }

    @Override
    public Macro fromTag(class_2487 llllllllllllllllIllllIlIIIlIIIII) {
        Macro llllllllllllllllIllllIlIIIlIIIIl;
        llllllllllllllllIllllIlIIIlIIIIl.name = llllllllllllllllIllllIlIIIlIIIII.method_10558("name");
        if (llllllllllllllllIllllIlIIIlIIIII.method_10545("key")) {
            llllllllllllllllIllllIlIIIlIIIIl.keybind.set(true, llllllllllllllllIllllIlIIIlIIIII.method_10550("key"));
        } else {
            llllllllllllllllIllllIlIIIlIIIIl.keybind.fromTag(llllllllllllllllIllllIlIIIlIIIII.method_10562("keybind"));
        }
        llllllllllllllllIllllIlIIIlIIIIl.messages = NbtUtils.listFromTag(llllllllllllllllIllllIlIIIlIIIII.method_10554("messages", 8), class_2520::method_10714);
        return llllllllllllllllIllllIlIIIlIIIIl;
    }

    public int hashCode() {
        Macro llllllllllllllllIllllIlIIIIlIIll;
        return Objects.hash(llllllllllllllllIllllIlIIIIlIIll.name);
    }

    public Macro() {
        Macro llllllllllllllllIllllIlIIlIIllII;
        llllllllllllllllIllllIlIIlIIllII.name = "";
        llllllllllllllllIllllIlIIlIIllII.messages = new ArrayList<String>(1);
        llllllllllllllllIllllIlIIlIIllII.keybind = Keybind.fromKey(-1);
    }

    public void addMessage(String llllllllllllllllIllllIlIIlIIIllI) {
        Macro llllllllllllllllIllllIlIIlIIlIIl;
        llllllllllllllllIllllIlIIlIIlIIl.messages.add(llllllllllllllllIllllIlIIlIIIllI);
    }
}

