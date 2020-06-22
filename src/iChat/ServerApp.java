package iChat;

public class ServerApp {
    private static Server server;

    public Server getServer() {
        return server;
    }

    public static void main(String[] args) {
        server = new Server();
    }
}