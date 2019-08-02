package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                clients.add(new ClientHandler(this, socket));
                checkClients();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            if(!o.getSocket().isClosed()) {
                o.sendMsg(msg);
            } else {
                deleteClient(o);
            }
        }
    }
    public void deleteClient (ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
    public void checkClients() {
        for (ClientHandler o: clients) {
            if(o.getSocket().isClosed()) {
                deleteClient(o);
            }
        }
    }
}
