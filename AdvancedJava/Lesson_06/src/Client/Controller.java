package Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    TextArea textArea;
    @FXML
    TextField textFieldUserName;
    @FXML
    TextField textField;


    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADRESS = "localhost";
    private final int PORT = 8189;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(socket.isClosed());
                           //in.
                            String str = in.readUTF();
                            textArea.appendText(str + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.writeUTF("/end");
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            if (!textField.getText().isEmpty()) {
                out.writeUTF(textFieldUserName.getText() + ": " + textField.getText());
                textField.clear();
                textField.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
