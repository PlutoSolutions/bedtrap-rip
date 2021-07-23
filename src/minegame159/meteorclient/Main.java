/*
 * Decompiled with CFR 0.151.
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
    public static void openUrl(String string) {
        String string2 = System.getProperty("os.name").toLowerCase();
        try {
            if (string2.contains("win")) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(string));
                }
            } else if (string2.contains("mac")) {
                Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("open ").append(string)));
            } else if (string2.contains("nix") || string2.contains("nux")) {
                Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("xdg-open ").append(string)));
            }
        }
        catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] stringArray) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
            exception.printStackTrace();
        }
        int n = JOptionPane.showOptionDialog(null, "To install Meteor Client you need to put it in your mods folder and run Fabric for latest Minecraft version.", "Meteor Client", 1, 0, null, new String[]{"Open Fabric link", "Open mods folder"}, null);
        if (n == 0) {
            Main.openUrl("http://fabricmc.net");
        } else if (n == 1) {
            String string = System.getProperty("os.name").toLowerCase();
            try {
                if (string.contains("win")) {
                    if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                        String string2 = String.valueOf(new StringBuilder().append(System.getenv("AppData")).append("/.minecraft/mods"));
                        new File(string2).mkdirs();
                        Desktop.getDesktop().open(new File(string2));
                    }
                } else if (string.contains("mac")) {
                    String string3 = String.valueOf(new StringBuilder().append(System.getProperty("user.home")).append("/Library/Application Support/minecraft/mods"));
                    new File(string3).mkdirs();
                    ProcessBuilder processBuilder = new ProcessBuilder("open", string3);
                    Process process = processBuilder.start();
                } else if (string.contains("nix") || string.contains("nux")) {
                    String string4 = String.valueOf(new StringBuilder().append(System.getProperty("user.home")).append("/.minecraft"));
                    new File(string4).mkdirs();
                    Runtime.getRuntime().exec(String.valueOf(new StringBuilder().append("xdg-open \"").append(string4).append("\"")));
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }
}

