package iChat;

import java.io.IOException;

public class ServerApp {
    private static Server server;

    public Server getServer() {
        return server;
    }

    public static void main(String[] args) throws IOException {
        server = new Server();
    }
}