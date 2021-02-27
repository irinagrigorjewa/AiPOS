import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static final int PORT = 7788;
    private int port = PORT;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    Server(int port) {
        this.port = port;

    }

    void start() {
        try (ServerSocket server = new ServerSocket(this.port)) {
            while (true) {
                Socket serverSocket = server.accept();
                Handler handler = new Handler(serverSocket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }

}

