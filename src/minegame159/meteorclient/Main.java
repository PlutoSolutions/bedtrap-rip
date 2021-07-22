/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public Main() {
        Main lIIlIIlIllIIlI;
    }

    public static void openUrl(String lIIlIIlIIllIIl) {
        String lIIlIIlIIllIII = System.getProperty("os.name").toLowerCase();
        try {
            if (lIIlIIlIIllIII.contains("win")) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(lIIlIIlIIllIIl));
                }
            } else if (lIIlIIlIIllIII.contains("mac")) {
                Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("open ").append(lIIlIIlIIllIIl)));
            } else if (lIIlIIlIIllIII.contains("nix") || lIIlIIlIIllIII.contains("nux")) {
                Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("xdg-open ").append(lIIlIIlIIllIIl)));
            }
        }
        catch (IOException | URISyntaxException lIIlIIlIIllIlI) {
            lIIlIIlIIllIlI.printStackTrace();
        }
    }

    public static void main(String[] lIIlIIlIlIIlII) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException lIIlIIlIlIlIll) {
            lIIlIIlIlIlIll.printStackTrace();
        }
        int lIIlIIlIlIIIll = JOptionPane.showOptionDialog(null, "To install Meteor Client you need to put it in your mods folder and run Fabric for latest Minecraft version.", "Meteor Client", 1, 0, null, new String[]{"Open Fabric link", "Open mods folder"}, null);
        if (lIIlIIlIlIIIll == 0) {
            Main.openUrl("http://fabricmc.net");
        } else if (lIIlIIlIlIIIll == 1) {
            String lIIlIIlIlIIlIl = System.getProperty("os.name").toLowerCase();
            try {
                if (lIIlIIlIlIIlIl.contains("win")) {
                    if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                        String lIIlIIlIlIlIlI = String.valueOf(new StringBuilder().append(System.getenv("AppData")).append("/.minecraft/mods"));
                        new File(lIIlIIlIlIlIlI).mkdirs();
                        Desktop.getDesktop().open(new File(lIIlIIlIlIlIlI));
                    }
                } else if (lIIlIIlIlIIlIl.contains("mac")) {
                    String lIIlIIlIlIlIIl = String.valueOf(new StringBuilder().append(System.getProperty("user.home")).append("/Library/Application Support/minecraft/mods"));
                    new File(lIIlIIlIlIlIIl).mkdirs();
                    ProcessBuilder lIIlIIlIlIlIII = new ProcessBuilder("open", lIIlIIlIlIlIIl);
                    Process lIIlIIlIIllllI = lIIlIIlIlIlIII.start();
                } else if (lIIlIIlIlIIlIl.contains("nix") || lIIlIIlIlIIlIl.contains("nux")) {
                    String lIIlIIlIlIIlll = String.valueOf(new StringBuilder().append(System.getProperty("user.home")).append("/.minecraft"));
                    new File(lIIlIIlIlIIlll).mkdirs();
                    Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("xdg-open \"").append(lIIlIIlIlIIlll).append("\"")));
                }
            }
            catch (IOException lIIlIIlIlIIllI) {
                lIIlIIlIlIIllI.printStackTrace();
            }
        }
    }
}

