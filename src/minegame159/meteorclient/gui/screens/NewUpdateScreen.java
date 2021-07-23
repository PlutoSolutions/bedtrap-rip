/*
 * Decompiled with CFR 0.151.
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
    public NewUpdateScreen(GuiTheme guiTheme, Version version) {
        super(guiTheme, "New Update");
        this.add(guiTheme.label("A new version of Meteor has been released."));
        this.add(guiTheme.horizontalSeparator()).expandX();
        WTable wTable = this.add(guiTheme.table()).widget();
        wTable.add(guiTheme.label("Your version:"));
        wTable.add(guiTheme.label(Config.get().version.getOriginalString()));
        wTable.row();
        wTable.add(guiTheme.label("Latest version"));
        wTable.add(guiTheme.label(version.getOriginalString()));
        this.add(guiTheme.horizontalSeparator()).expandX();
        WHorizontalList wHorizontalList = this.add(guiTheme.horizontalList()).widget();
        wHorizontalList.add(guiTheme.button((String)String.valueOf((Object)new StringBuilder().append((String)"Download ").append((String)version.getOriginalString())))).expandX().widget().action = NewUpdateScreen::lambda$new$0;
        wHorizontalList.add(guiTheme.button((String)"OK")).expandX().widget().action = this::method_25419;
    }

    private static void lambda$new$0() {
        class_156.method_668().method_670("http://meteorclient.com/");
    }
}

