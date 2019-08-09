package chat_my_you;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    VBox VboxChat;

    @FXML
    TextField textField;

    int index = 0;

    public void sendMsg(ActionEvent actionEvent) {
        Label label = new Label(textField.getText() + "\n");
        VBox vBox = new VBox();

        if(index % 2 == 0) {
            vBox.setAlignment(Pos.TOP_LEFT);
        } else {
            vBox.setAlignment(Pos.TOP_RIGHT);
        }

        vBox.getChildren().add(label);
        VboxChat.getChildren().add(vBox);

       // textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
        index++;
    }
}
