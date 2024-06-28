package org.example.xo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {

    @FXML
    private TextField nameTextField;
    @FXML
    private Label warningLable;

    public void enterButtonController(ActionEvent event) throws IOException {
        if (nameTextField.getText().isEmpty()) {
            warningLable.setText("Username can not be empty!");
        } else {
            User.userName = nameTextField.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nameTextField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
