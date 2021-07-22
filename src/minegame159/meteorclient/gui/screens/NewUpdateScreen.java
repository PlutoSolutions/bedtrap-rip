/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_156
 */
package minegame159.meteorclient.gui.screens;

import com.g00fy2.versioncompare.Version;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.systems.config.Config;
import net.minecraft.class_156;

public class NewUpdateScreen
extends WindowScreen {
    public NewUpdateScreen(GuiTheme llIIlIIIIllII, Version llIIlIIIlIIII) {
        super(llIIlIIIIllII, "New Update");
        NewUpdateScreen llIIlIIIIllIl;
        llIIlIIIIllIl.add(llIIlIIIIllII.label("A new version of Meteor has been released."));
        llIIlIIIIllIl.add(llIIlIIIIllII.horizontalSeparator()).expandX();
        WTable llIIlIIIIllll = llIIlIIIIllIl.add(llIIlIIIIllII.table()).widget();
        llIIlIIIIllll.add(llIIlIIIIllII.label("Your version:"));
        llIIlIIIIllll.add(llIIlIIIIllII.label(Config.get().version.getOriginalString()));
        llIIlIIIIllll.row();
        llIIlIIIIllll.add(llIIlIIIIllII.label("Latest version"));
        llIIlIIIIllll.add(llIIlIIIIllII.label(llIIlIIIlIIII.getOriginalString()));
        llIIlIIIIllIl.add(llIIlIIIIllII.horizontalSeparator()).expandX();
        WHorizontalList llIIlIIIIlllI = llIIlIIIIllIl.add(llIIlIIIIllII.horizontalList()).widget();
        llIIlIIIIlllI.add(llIIlIIIIllII.button((String)String.valueOf((Object)new StringBuilder().append((String)"Download ").append((String)llIIlIIIlIIII.getOriginalString())))).expandX().widget().action = () -> class_156.method_668().method_670("http://meteorclient.com/");
        llIIlIIIIlllI.add(llIIlIIIIllII.button((String)"OK")).expandX().widget().action = llIIlIIIIllIl::method_25419;
    }
}

