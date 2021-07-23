/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.misc.swarm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import minegame159.meteorclient.utils.player.ChatUtils;

public class SwarmConnection
extends Thread {
    public final Socket socket;
    public String messageToSend;

    public SwarmConnection(Socket socket) {
        this.socket = socket;
        this.start();
    }

    private String getIp(String string) {
        return string.equals("127.0.0.1") ? "localhost" : string;
    }

    public String getConnection() {
        return String.valueOf(new StringBuilder().append(this.getIp(this.socket.getInetAddress().getHostAddress())).append(":").append(this.socket.getPort()));
    }

    @Override
    public void run() {
        ChatUtils.info("Swarm", "New worker connected on %s.", this.getIp(this.socket.getInetAddress().getHostAddress()));
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            while (!this.isInterrupted()) {
                if (this.messageToSend == null) continue;
                try {
                    dataOutputStream.writeUTF(this.messageToSend);
                    dataOutputStream.flush();
                }
                catch (Exception exception) {
                    ChatUtils.error("Swarm", "Encountered error when sending command.", new Object[0]);
                    exception.printStackTrace();
                }
                this.messageToSend = null;
            }
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            ChatUtils.info("Swarm", "Error creating a connection with %s on port %s.", this.getIp(this.socket.getInetAddress().getHostAddress()), this.socket.getPort());
            iOException.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            this.socket.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        ChatUtils.info("Swarm", "Worker disconnected on ip: %s.", this.socket.getInetAddress().getHostAddress());
        this.interrupt();
    }
}

