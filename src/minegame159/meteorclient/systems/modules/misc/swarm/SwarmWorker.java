/*
 * Decompiled with CFR 0.151.
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
    public class_2248 target;
    private Socket socket;

    public void disconnect() {
        try {
            this.socket.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        ChatUtils.info("Swarm", "Disconnected from host.", new Object[0]);
        this.interrupt();
    }

    private String getIp(String string) {
        return string.equals("127.0.0.1") ? "localhost" : string;
    }

    public void tick() {
        if (this.target == null) {
            return;
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
        BaritoneAPI.getProvider().getPrimaryBaritone().getMineProcess().mine(new class_2248[]{this.target});
        this.target = null;
    }

    public String getConnection() {
        return String.valueOf(new StringBuilder().append(this.getIp(this.socket.getInetAddress().getHostAddress())).append(":").append(this.socket.getPort()));
    }

    public SwarmWorker(String string, int n) {
        try {
            this.socket = new Socket(string, n);
        }
        catch (Exception exception) {
            this.socket = null;
            ChatUtils.warning("Swarm", "Server not found at %s on port %s.", string, n);
            exception.printStackTrace();
        }
        if (this.socket != null) {
            this.start();
        }
    }

    @Override
    public void run() {
        ChatUtils.info("Swarm", "Connected to Swarm host on at %s on port %s.", this.getIp(this.socket.getInetAddress().getHostAddress()), this.socket.getPort());
        try {
            DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
            while (!this.isInterrupted()) {
                String string = dataInputStream.readUTF();
                if (string.equals("")) continue;
                ChatUtils.info("Swarm", "Received command: (highlight)%s", string);
                try {
                    Commands.get().dispatch(string);
                }
                catch (Exception exception) {
                    ChatUtils.error("Error fetching command.", new Object[0]);
                    exception.printStackTrace();
                }
            }
            dataInputStream.close();
        }
        catch (IOException iOException) {
            ChatUtils.error("Swarm", "Error in connection to host.", new Object[0]);
            iOException.printStackTrace();
            this.disconnect();
        }
    }
}

