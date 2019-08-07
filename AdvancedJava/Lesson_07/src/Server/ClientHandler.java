package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    nick = newNick;
                                    if (server.checkUser(nick)) {
                                        sendMsg("Пользователь с такм ником уже в системе!");
                                    } else {
                                        sendMsg("/authok");
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            String[] tokens = str.split(" ");
                            System.out.println("Client " + nick + " " + str);
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }

                                if (tokens[0].equals("/w") & tokens.length > 1) {
                                   sendMsg(str);
                                   StringBuilder builder = new StringBuilder();
                                    for (int i = 2; i < tokens.length; i++) {
                                        builder.append(tokens[i]);
                                    }
                                    str = builder.toString();
                                    if (!server.privateMsg("От : " + nick + " : " + str, tokens[1])) {
                                        sendMsg("Пользователь не найден!");
                                    }

                            } else {
                                server.broadcastMsg(nick + ": " + str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        String res = String.valueOf(nick);
        return res;
    }

    public Socket getSocket() {
        return socket;
    }
}