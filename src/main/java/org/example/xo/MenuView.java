package org.example.xo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuView {
    MediaPlayer player;
    @FXML
    private CheckBox musicCheckBox;

    {
        URL resource = MenuView.class.getResource("/org/example/assets/m1.mp3");
        Media media = new Media(resource.toString());
        player = new MediaPlayer(media);
    }

    @FXML
    public void initialize() {
        musicCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                player.setCycleCount(MediaPlayer.INDEFINITE);
                player.play();
            } else {
                player.stop();
                player.setCycleCount(1);
            }
        });
    }

    public void startHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitHandler(ActionEvent event) {
        System.exit(0);
    }


}
