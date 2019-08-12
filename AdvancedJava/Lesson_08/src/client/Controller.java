package client;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;


public class Controller {


    @FXML
    TextField textField;

    @FXML
    TextField newLoginField;

    @FXML
    TextField newNickFiled;

    @FXML
    TextField newPasswordField;
    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    HBox welcomePanel;

    @FXML
    HBox loginPanel;

    @FXML
    HBox regPanel;

    @FXML
    ListView<String> clientList;

    @FXML
    VBox VboxChat;

    private boolean isAuthorized;
    private int index = 0;
    private String myNick;
    private boolean inRegMode;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            regPanel.setVisible(false);
            regPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            loginPanel.setVisible(false);
            loginPanel.setManaged(false);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }
    }

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (inRegMode) {
                         while (true) {
                              String str = in.readUTF();
                              if (str.startsWith("/regOk")) {
                                  inRegMode = false;
                                  showLoginPanel();
                                  addText("Успешно зарегистирован!");
                                  break;
                               } else {
                                   addText(str);
                              }
                              if (!inRegMode) {
                                  break;
                              }
                            }
                        }
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                String[] tokens = str.split(" ");
                                myNick = tokens[1];
                                break;
                            } else {
                                addText(str);
                            }
                        }


                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")) break;
                            if (str.startsWith("/clientlist")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        clientList.getItems().clear();
                                        for (int i = 1; i < tokens.length; i++) {
                                            clientList.getItems().add(tokens[i]);
                                        }
                                    }
                                });
                            } else {
                                addText(str);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Dispose() {
        System.out.println("Отправляем сообщение о закрытии");
        try {
            if(out != null) {
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectClient(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            System.out.println("Двойной клик");
        }
    }

    public void tryToReg() {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/reg " + newLoginField.getText() + " " + newNickFiled.getText() + " " + newPasswordField.getText());
            newLoginField.clear();
            newNickFiled.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cancelReg() {
        inRegMode = false;
        welcomePanel.setVisible(true);
        welcomePanel.setManaged(true);
        regPanel.setVisible(false);
        regPanel.setManaged(false);
        loginPanel.setVisible(false);
        loginPanel.setManaged(false);
    }
    public void showLoginPanel() {
        welcomePanel.setVisible(false);
        welcomePanel.setManaged(false);
        regPanel.setVisible(false);
        regPanel.setManaged(false);
        loginPanel.setVisible(true);
        loginPanel.setManaged(true);
    }
    public void showRegPanel() {
        inRegMode = true;
        welcomePanel.setVisible(false);
        welcomePanel.setManaged(false);
        loginPanel.setVisible(false);
        loginPanel.setManaged(false);
        regPanel.setVisible(true);
        regPanel.setManaged(true);
    }
    public void cancelAuth() {
        welcomePanel.setVisible(true);
        welcomePanel.setManaged(true);
        loginPanel.setVisible(false);
        loginPanel.setManaged(false);
        regPanel.setVisible(false);
        regPanel.setManaged(false);
    }

    private void addText (String msg) {

        Platform.runLater(new Runnable() {
            @Override public void run() {
                Label label = new Label(msg + "\n");
                VBox vBox = new VBox();
                if (myNick != null) {

                    if(msg.startsWith(myNick)) {
                       vBox.setAlignment(Pos.TOP_RIGHT);
                    } else {
                       vBox.setAlignment(Pos.TOP_LEFT);
                    }
                } else {
                    vBox.setAlignment(Pos.TOP_LEFT);
                }

                vBox.getChildren().add(label);
                VboxChat.getChildren().add(vBox);
            }
        });

    }
//    @FXML
//    private void closeButtonAction(){
//        // get a handle to the stage
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        out.writeUTF("/close")
//        // do what you have to do
//        stage.close();
//    }
}
