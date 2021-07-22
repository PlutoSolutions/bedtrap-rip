/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  net.minecraft.class_2248
 */
package minegame159.meteorclient.systems.modules.misc.swarm;

import baritone.api.BaritoneAPI;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2248;

public class SwarmWorker
extends Thread {
    public /* synthetic */ class_2248 target;
    private /* synthetic */ Socket socket;

    public void disconnect() {
        SwarmWorker lllIlllIIIIlIll;
        try {
            lllIlllIIIIlIll.socket.close();
        }
        catch (IOException lllIlllIIIIllIl) {
            lllIlllIIIIllIl.printStackTrace();
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        ChatUtils.info("Swarm", "Disconnected from host.", new Object[0]);
        lllIlllIIIIlIll.interrupt();
    }

    private String getIp(String lllIlllIIIIIIII) {
        return lllIlllIIIIIIII.equals("127.0.0.1") ? "localhost" : lllIlllIIIIIIII;
    }

    public void tick() {
        SwarmWorker lllIlllIIIIlIII;
        if (lllIlllIIIIlIII.target == null) {
            return;
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{lllIlllIIIIlIII.target});
        lllIlllIIIIlIII.target = null;
    }

    public String getConnection() {
        SwarmWorker lllIlllIIIIIlIl;
        return String.valueOf(new StringBuilder().append(lllIlllIIIIIlIl.getIp(lllIlllIIIIIlIl.socket.getInetAddress().getHostAddress())).append(":").append(lllIlllIIIIIlIl.socket.getPort()));
    }

    public SwarmWorker(String lllIlllIIlIIIlI, int lllIlllIIIllllI) {
        SwarmWorker lllIlllIIlIIIll;
        try {
            lllIlllIIlIIIll.socket = new Socket(lllIlllIIlIIIlI, lllIlllIIIllllI);
        }
        catch (Exception lllIlllIIlIIlII) {
            lllIlllIIlIIIll.socket = null;
            ChatUtils.warning("Swarm", "Server not found at %s on port %s.", lllIlllIIlIIIlI, lllIlllIIIllllI);
            lllIlllIIlIIlII.printStackTrace();
        }
        if (lllIlllIIlIIIll.socket != null) {
            lllIlllIIlIIIll.start();
        }
    }

    @Override
    public void run() {
        SwarmWorker lllIlllIIIlIIll;
        ChatUtils.info("Swarm", "Connected to Swarm host on at %s on port %s.", lllIlllIIIlIIll.getIp(lllIlllIIIlIIll.socket.getInetAddress().getHostAddress()), lllIlllIIIlIIll.socket.getPort());
        try {
            DataInputStream lllIlllIIIlIllI = new DataInputStream(lllIlllIIIlIIll.socket.getInputStream());
            while (!lllIlllIIIlIIll.isInterrupted()) {
                String lllIlllIIIlIlll = lllIlllIIIlIllI.readUTF();
                if (lllIlllIIIlIlll.equals("")) continue;
                ChatUtils.info("Swarm", "Received command: (highlight)%s", lllIlllIIIlIlll);
                try {
                    Commands.get().dispatch(lllIlllIIIlIlll);
                }
                catch (Exception lllIlllIIIllIII) {
                    ChatUtils.error("Error fetching command.", new Object[0]);
                    lllIlllIIIllIII.printStackTrace();
                }
            }
            lllIlllIIIlIllI.close();
        }
        catch (IOException lllIlllIIIlIlIl) {
            ChatUtils.error("Swarm", "Error in connection to host.", new Object[0]);
            lllIlllIIIlIlIl.printStackTrace();
            lllIlllIIIlIIll.disconnect();
        }
    }
}

