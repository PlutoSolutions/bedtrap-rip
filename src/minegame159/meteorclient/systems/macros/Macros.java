/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.macros;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.macros.Macro;
import minegame159.meteorclient.utils.misc.NbtUtils;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

public class Macros
extends System<Macros>
implements Iterable<Macro> {
    private /* synthetic */ List<Macro> macros;

    @EventHandler(priority=100)
    private void onButton(MouseButtonEvent lIlIllIlllllllI) {
        Macros lIlIllIllllllll;
        if (lIlIllIlllllllI.action == KeyAction.Release) {
            return;
        }
        for (Macro lIlIlllIIIIIIlI : lIlIllIllllllll.macros) {
            if (!lIlIlllIIIIIIlI.onAction(false, lIlIllIlllllllI.button)) continue;
            return;
        }
    }

    @EventHandler(priority=100)
    private void onKey(KeyEvent lIlIlllIIIIlIll) {
        Macros lIlIlllIIIIllII;
        if (lIlIlllIIIIlIll.action == KeyAction.Release) {
            return;
        }
        for (Macro lIlIlllIIIIllIl : lIlIlllIIIIllII.macros) {
            if (!lIlIlllIIIIllIl.onAction(true, lIlIlllIIIIlIll.key)) continue;
            return;
        }
    }

    @Override
    public Iterator<Macro> iterator() {
        Macros lIlIllIlllllIIl;
        return lIlIllIlllllIIl.macros.iterator();
    }

    public Macros() {
        super("macros");
        Macros lIlIlllIIlIIIlI;
        lIlIlllIIlIIIlI.macros = new ArrayList<Macro>();
    }

    @Override
    public class_2487 toTag() {
        Macros lIlIllIllllIllI;
        class_2487 lIlIllIllllIlIl = new class_2487();
        lIlIllIllllIlIl.method_10566("macros", (class_2520)NbtUtils.listToTag(lIlIllIllllIllI.macros));
        return lIlIllIllllIlIl;
    }

    @Override
    public Macros fromTag(class_2487 lIlIllIlllIlIll) {
        Macros lIlIllIlllIlIlI;
        for (Macro lIlIllIlllIlllI : lIlIllIlllIlIlI.macros) {
            MeteorClient.EVENT_BUS.unsubscribe(lIlIllIlllIlllI);
        }
        lIlIllIlllIlIlI.macros = NbtUtils.listFromTag(lIlIllIlllIlIll.method_10554("macros", 10), lIlIllIllIlllll -> new Macro().fromTag((class_2487)lIlIllIllIlllll));
        for (Macro lIlIllIlllIllIl : lIlIllIlllIlIlI.macros) {
            MeteorClient.EVENT_BUS.subscribe(lIlIllIlllIllIl);
        }
        return lIlIllIlllIlIlI;
    }

    public static Macros get() {
        return Systems.get(Macros.class);
    }

    public void add(Macro lIlIlllIIIllIll) {
        Macros lIlIlllIIIlllII;
        lIlIlllIIIlllII.macros.add(lIlIlllIIIllIll);
        MeteorClient.EVENT_BUS.subscribe(lIlIlllIIIllIll);
        lIlIlllIIIlllII.save();
    }

    public List<Macro> getAll() {
        Macros lIlIlllIIIllIII;
        return lIlIlllIIIllIII.macros;
    }

    public void remove(Macro lIlIlllIIIlIIlI) {
        Macros lIlIlllIIIlIIll;
        if (lIlIlllIIIlIIll.macros.remove(lIlIlllIIIlIIlI)) {
            MeteorClient.EVENT_BUS.unsubscribe(lIlIlllIIIlIIlI);
            lIlIlllIIIlIIll.save();
        }
    }
}

