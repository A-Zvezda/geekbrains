package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
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
            AuthService.disconnect();
        }
    }
    public boolean checkUser(String nick) {
        boolean res = false;
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)) {
                res = true;
            }
        }
        return res;
    }
    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            if(!o.getSocket().isClosed()) {
                o.sendMsg(msg);
            } else {
                unsubscribe(o);
            }
        }
    }
    public boolean privateMsg(String msg,String clientNick) {
        boolean res = false;
        for (ClientHandler o: clients) {
            if (o.getNick().equals(clientNick)) {
                o.sendMsg(msg);
                res = true;
            }
        }
        return res;
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

}
