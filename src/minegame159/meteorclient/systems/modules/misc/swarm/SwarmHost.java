/*
 * Decompiled with CFR 0.151.
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
    private final SwarmConnection[] clientConnections = new SwarmConnection[50];
    private ServerSocket socket;

    public void sendMessage(String string) {
        MeteorExecutor.execute(() -> this.lambda$sendMessage$0(string));
    }

    public void assignConnectionToSubServer(Socket socket) {
        for (int i = 0; i < this.clientConnections.length; ++i) {
            if (this.clientConnections[i] != null) continue;
            this.clientConnections[i] = new SwarmConnection(socket);
            break;
        }
    }

    public SwarmConnection[] getConnections() {
        return this.clientConnections;
    }

    public SwarmHost(int n) {
        try {
            this.socket = new ServerSocket(n);
        }
        catch (IOException iOException) {
            this.socket = null;
            ChatUtils.error("Swarm", "Couldn't start a server on port %s.", n);
            iOException.printStackTrace();
        }
        if (this.socket != null) {
            this.start();
        }
    }

    private void lambda$sendMessage$0(String string) {
        for (SwarmConnection swarmConnection : this.clientConnections) {
            if (swarmConnection == null) continue;
            swarmConnection.messageToSend = string;
            if (2 >= 0) continue;
            return;
        }
    }

    @Override
    public void run() {
        ChatUtils.info("Swarm", "Listening for incoming connections on port %s.", this.socket.getLocalPort());
        while (!this.isInterrupted()) {
            try {
                Socket socket = this.socket.accept();
                this.assignConnectionToSubServer(socket);
            }
            catch (IOException iOException) {
                ChatUtils.error("Swarm", "Error making a connection to worker.", new Object[0]);
                iOException.printStackTrace();
            }
        }
    }

    public int getConnectionCount() {
        int n = 0;
        for (SwarmConnection swarmConnection : this.clientConnections) {
            if (swarmConnection == null) continue;
            ++n;
            if (-4 <= 0) continue;
            return 0;
        }
        return n;
    }

    public void disconnect() {
        for (SwarmConnection swarmConnection : this.clientConnections) {
            if (swarmConnection == null) continue;
            swarmConnection.disconnect();
            if (null == null) continue;
            return;
        }
        try {
            this.socket.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        ChatUtils.info("Swarm", "Server closed on port %s.", this.socket.getLocalPort());
        this.interrupt();
    }
}

