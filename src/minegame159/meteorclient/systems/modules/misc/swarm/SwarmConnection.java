/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.misc.swarm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import minegame159.meteorclient.utils.player.ChatUtils;

public class SwarmConnection
extends Thread {
    public final /* synthetic */ Socket socket;
    public /* synthetic */ String messageToSend;

    public SwarmConnection(Socket lllllIIIIllIl) {
        SwarmConnection lllllIIIIlllI;
        lllllIIIIlllI.socket = lllllIIIIllIl;
        lllllIIIIlllI.start();
    }

    private String getIp(String llllIllllIlII) {
        return llllIllllIlII.equals("127.0.0.1") ? "localhost" : llllIllllIlII;
    }

    public String getConnection() {
        SwarmConnection llllIlllllIIl;
        return String.valueOf(new StringBuilder().append(llllIlllllIIl.getIp(llllIlllllIIl.socket.getInetAddress().getHostAddress())).append(":").append(llllIlllllIIl.socket.getPort()));
    }

    @Override
    public void run() {
        SwarmConnection lllllIIIIIIll;
        ChatUtils.info("Swarm", "New worker connected on %s.", lllllIIIIIIll.getIp(lllllIIIIIIll.socket.getInetAddress().getHostAddress()));
        try {
            DataOutputStream lllllIIIIIllI = new DataOutputStream(lllllIIIIIIll.socket.getOutputStream());
            while (!lllllIIIIIIll.isInterrupted()) {
                if (lllllIIIIIIll.messageToSend == null) continue;
                try {
                    lllllIIIIIllI.writeUTF(lllllIIIIIIll.messageToSend);
                    lllllIIIIIllI.flush();
                }
                catch (Exception lllllIIIIIlll) {
                    ChatUtils.error("Swarm", "Encountered error when sending command.", new Object[0]);
                    lllllIIIIIlll.printStackTrace();
                }
                lllllIIIIIIll.messageToSend = null;
            }
            lllllIIIIIllI.close();
        }
        catch (IOException lllllIIIIIlIl) {
            ChatUtils.info("Swarm", "Error creating a connection with %s on port %s.", lllllIIIIIIll.getIp(lllllIIIIIIll.socket.getInetAddress().getHostAddress()), lllllIIIIIIll.socket.getPort());
            lllllIIIIIlIl.printStackTrace();
        }
    }

    public void disconnect() {
        SwarmConnection llllIllllllII;
        try {
            llllIllllllII.socket.close();
        }
        catch (IOException llllIlllllllI) {
            llllIlllllllI.printStackTrace();
        }
        ChatUtils.info("Swarm", "Worker disconnected on ip: %s.", llllIllllllII.socket.getInetAddress().getHostAddress());
        llllIllllllII.interrupt();
    }
}

