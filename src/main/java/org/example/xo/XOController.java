package org.example.xo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class XOController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}