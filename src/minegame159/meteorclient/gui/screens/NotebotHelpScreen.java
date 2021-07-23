/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;

public class NotebotHelpScreen
extends WindowScreen {
    public NotebotHelpScreen(GuiTheme guiTheme) {
        super(guiTheme, "Notebot - Help");
        this.add(guiTheme.label("Loading songs", true)).widget();
        this.add(guiTheme.label("To load songs you need to put a file with supported format inside: "));
        this.add(guiTheme.label(String.format(" \"%s\"", MeteorClient.FOLDER.toPath().resolve("notebot").toString())));
        this.add(guiTheme.label("Supported formats", true)).padTop(10.0);
        this.add(guiTheme.label("- Classic .nbs files"));
        this.add(guiTheme.label("- OpenNBS v5 .nbs files"));
        this.add(guiTheme.label("- .txt files using format <tick>:<note>"));
        this.add(guiTheme.label("Playing", true)).padTop(10.0);
        this.add(guiTheme.label("To play a song you can either:"));
        this.add(guiTheme.label("-  place noteblocks around you in a 5 block radius"));
        this.add(guiTheme.label("-  hold noteblocks in your hotbar and let the module do all the work"));
        this.add(guiTheme.label("To start playing a song you can Press the \"Load\" button next to the song you want to load or use the .notebot command"));
        this.add(guiTheme.label("Recording", true)).padTop(10.0);
        this.add(guiTheme.label("You can also record in-game sound to play them back later"));
        this.add(guiTheme.label("1. Run \".notebot record start\" to start recording"));
        this.add(guiTheme.label("2. Stand next to some noteblocks"));
        this.add(guiTheme.label("3. Run \".notebot record save <name>\""));
        this.add(guiTheme.label("After that you will see your new recording inside the list of recordings"));
    }
}

