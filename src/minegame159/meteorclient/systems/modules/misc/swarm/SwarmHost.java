/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.misc.swarm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import minegame159.meteorclient.systems.modules.misc.swarm.SwarmConnection;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.player.ChatUtils;

public class SwarmHost
extends Thread {
    private final /* synthetic */ SwarmConnection[] clientConnections;
    private /* synthetic */ ServerSocket socket;

    public void sendMessage(String llllllllllllllllIlllIIIIlllIllII) {
        SwarmHost llllllllllllllllIlllIIIIlllIlIll;
        MeteorExecutor.execute(() -> {
            SwarmHost llllllllllllllllIlllIIIIllIIlllI;
            for (SwarmConnection llllllllllllllllIlllIIIIllIlIIIl : llllllllllllllllIlllIIIIllIIlllI.clientConnections) {
                if (llllllllllllllllIlllIIIIllIlIIIl == null) continue;
                llllllllllllllllIlllIIIIllIlIIIl.messageToSend = llllllllllllllllIlllIIIIlllIllII;
            }
        });
    }

    public void assignConnectionToSubServer(Socket llllllllllllllllIlllIIIlIIIIIIII) {
        SwarmHost llllllllllllllllIlllIIIIllllllll;
        for (int llllllllllllllllIlllIIIlIIIIIIlI = 0; llllllllllllllllIlllIIIlIIIIIIlI < llllllllllllllllIlllIIIIllllllll.clientConnections.length; ++llllllllllllllllIlllIIIlIIIIIIlI) {
            if (llllllllllllllllIlllIIIIllllllll.clientConnections[llllllllllllllllIlllIIIlIIIIIIlI] != null) continue;
            llllllllllllllllIlllIIIIllllllll.clientConnections[llllllllllllllllIlllIIIlIIIIIIlI] = new SwarmConnection(llllllllllllllllIlllIIIlIIIIIIII);
            break;
        }
    }

    public SwarmConnection[] getConnections() {
        SwarmHost llllllllllllllllIlllIIIIlllIlIII;
        return llllllllllllllllIlllIIIIlllIlIII.clientConnections;
    }

    public SwarmHost(int llllllllllllllllIlllIIIlIIIlIIII) {
        SwarmHost llllllllllllllllIlllIIIlIIIIllll;
        llllllllllllllllIlllIIIlIIIIllll.clientConnections = new SwarmConnection[50];
        try {
            llllllllllllllllIlllIIIlIIIIllll.socket = new ServerSocket(llllllllllllllllIlllIIIlIIIlIIII);
        }
        catch (IOException llllllllllllllllIlllIIIlIIIlIIlI) {
            llllllllllllllllIlllIIIlIIIIllll.socket = null;
            ChatUtils.error("Swarm", "Couldn't start a server on port %s.", llllllllllllllllIlllIIIlIIIlIIII);
            llllllllllllllllIlllIIIlIIIlIIlI.printStackTrace();
        }
        if (llllllllllllllllIlllIIIlIIIIllll.socket != null) {
            llllllllllllllllIlllIIIlIIIIllll.start();
        }
    }

    @Override
    public void run() {
        SwarmHost llllllllllllllllIlllIIIlIIIIlIII;
        ChatUtils.info("Swarm", "Listening for incoming connections on port %s.", llllllllllllllllIlllIIIlIIIIlIII.socket.getLocalPort());
        while (!llllllllllllllllIlllIIIlIIIIlIII.isInterrupted()) {
            try {
                Socket llllllllllllllllIlllIIIlIIIIlIlI = llllllllllllllllIlllIIIlIIIIlIII.socket.accept();
                llllllllllllllllIlllIIIlIIIIlIII.assignConnectionToSubServer(llllllllllllllllIlllIIIlIIIIlIlI);
            }
            catch (IOException llllllllllllllllIlllIIIlIIIIlIIl) {
                ChatUtils.error("Swarm", "Error making a connection to worker.", new Object[0]);
                llllllllllllllllIlllIIIlIIIIlIIl.printStackTrace();
            }
        }
    }

    public int getConnectionCount() {
        SwarmHost llllllllllllllllIlllIIIIllIlllll;
        int llllllllllllllllIlllIIIIllIllllI = 0;
        for (SwarmConnection llllllllllllllllIlllIIIIlllIIIII : llllllllllllllllIlllIIIIllIlllll.clientConnections) {
            if (llllllllllllllllIlllIIIIlllIIIII == null) continue;
            ++llllllllllllllllIlllIIIIllIllllI;
        }
        return llllllllllllllllIlllIIIIllIllllI;
    }

    public void disconnect() {
        SwarmHost llllllllllllllllIlllIIIIllllIlII;
        for (SwarmConnection llllllllllllllllIlllIIIIllllIlll : llllllllllllllllIlllIIIIllllIlII.clientConnections) {
            if (llllllllllllllllIlllIIIIllllIlll == null) continue;
            llllllllllllllllIlllIIIIllllIlll.disconnect();
        }
        try {
            llllllllllllllllIlllIIIIllllIlII.socket.close();
        }
        catch (IOException llllllllllllllllIlllIIIIllllIllI) {
            llllllllllllllllIlllIIIIllllIllI.printStackTrace();
        }
        ChatUtils.info("Swarm", "Server closed on port %s.", llllllllllllllllIlllIIIIllllIlII.socket.getLocalPort());
        llllllllllllllllIlllIIIIllllIlII.interrupt();
    }
}

