/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;

public class NotebotHelpScreen
extends WindowScreen {
    public NotebotHelpScreen(GuiTheme lIIlllIIlIlIIll) {
        super(lIIlllIIlIlIIll, "Notebot - Help");
        NotebotHelpScreen lIIlllIIlIlIllI;
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("Loading songs", true)).widget();
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("To load songs you need to put a file with supported format inside: "));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label(String.format(" \"%s\"", MeteorClient.FOLDER.toPath().resolve("notebot").toString())));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("Supported formats", true)).padTop(10.0);
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("- Classic .nbs files"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("- OpenNBS v5 .nbs files"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("- .txt files using format <tick>:<note>"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("Playing", true)).padTop(10.0);
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("To play a song you can either:"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("-  place noteblocks around you in a 5 block radius"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("-  hold noteblocks in your hotbar and let the module do all the work"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("To start playing a song you can Press the \"Load\" button next to the song you want to load or use the .notebot command"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("Recording", true)).padTop(10.0);
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("You can also record in-game sound to play them back later"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("1. Run \".notebot record start\" to start recording"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("2. Stand next to some noteblocks"));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("3. Run \".notebot record save <name>\""));
        lIIlllIIlIlIllI.add(lIIlllIIlIlIIll.label("After that you will see your new recording inside the list of recordings"));
    }
}

