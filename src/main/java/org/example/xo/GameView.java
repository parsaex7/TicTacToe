package org.example.xo;

import com.almasb.fxgl.procedural.BiomeMapGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class GameView {
    @FXML
    private Label timeLable;
    @FXML
    private Label resultLable;
    @FXML
    private Button playAgainButton;
    @FXML
    private Button returnButton;
    @FXML
    private ImageView a11;
    @FXML
    private ImageView a12;
    @FXML
    private ImageView a13;
    @FXML
    private ImageView a21;
    @FXML
    private ImageView a22;
    @FXML
    private ImageView a23;
    @FXML
    private ImageView a31;
    @FXML
    private ImageView a32;
    @FXML
    private ImageView a33;

    Image image = new Image(getClass().getResource("/org/example/assets/x.jpg").toString());
    Image botImage = new Image(getClass().getResource("/org/example/assets/o.jpg").toString());
    HashMap<String, Boolean> map = new HashMap<>();
    HashMap<String, Boolean> selected = new HashMap<>();
    HashMap<String, ImageView> imageViewHashMap = new HashMap<>();
    HashMap<String, String> gameBoard = new HashMap<>();
    {
        map.put("a11", false);
        map.put("a12", false);
        map.put("a13", false);
        map.put("a21", false);
        map.put("a22", false);
        map.put("a23", false);
        map.put("a31", false);
        map.put("a32", false);
        map.put("a33", false);
        selected.put("a11", false);
        selected.put("a12", false);
        selected.put("a13", false);
        selected.put("a21", false);
        selected.put("a22", false);
        selected.put("a23", false);
        selected.put("a31", false);
        selected.put("a32", false);
        selected.put("a33", false);
        gameBoard.put("a11", "");
        gameBoard.put("a12", "");
        gameBoard.put("a13", "");
        gameBoard.put("a21", "");
        gameBoard.put("a22", "");
        gameBoard.put("a23", "");
        gameBoard.put("a31", "");
        gameBoard.put("a32", "");
        gameBoard.put("a33", "");
    }



    public void chooseController(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        String id = imageView.getId();
        if (!selected.get(id)) {
            imageView.setImage(image);
            imageView.setOpacity(1.0);
            selected.put(id, true);
            map.put(id, true);
            gameBoard.put(id, "x");
            if (checkWinForX()) {
                resultLable.setText("You win!");
                finishGame();
                returnButton.setDisable(false);
                playAgainButton.setDisable(false);
                returnButton.setText("Return");
                playAgainButton.setText("Play Again");
                timeLable.setText("0:00 ");
                return;
            }
            for (String key : selected.keySet()) {
                if (!selected.get(key)) {
                    imageViewHashMap.get(key).setImage(botImage);
                    imageViewHashMap.get(key).setOpacity(1.0);
                    selected.put(key, true);
                    map.put(key, true);
                    gameBoard.put(key, "o");
                    break;
                }
            }
            if (checkWinForO()) {
                resultLable.setText("You lose!");
                finishGame();
                returnButton.setDisable(false);
                playAgainButton.setDisable(false);
                returnButton.setText("Return");
                playAgainButton.setText("Play Again");
                timeLable.setText("0:00 ");
            }
        }
    }

    @FXML
    public void initialize() {
        imageViewHashMap.put("a11", a11);
        imageViewHashMap.put("a12", a12);
        imageViewHashMap.put("a13", a13);
        imageViewHashMap.put("a21", a21);
        imageViewHashMap.put("a22", a22);
        imageViewHashMap.put("a23", a23);
        imageViewHashMap.put("a31", a31);
        imageViewHashMap.put("a32", a32);
        imageViewHashMap.put("a33", a33);
        returnButton.setDisable(true);
        playAgainButton.setDisable(true);
        timeLable.setText("1:00");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), a -> {
            timeLable.setText(decreaseTime(timeLable.getText()));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String decreaseTime(String time) {
        if (time.equals("0:00")) {
            resultLable.setText("Time is over!");
            finishGame();
            returnButton.setDisable(false);
            playAgainButton.setDisable(false);
            returnButton.setText("Return");
            playAgainButton.setText("Play Again");
            return "0:00";
        }
        if (time.equals("0:00 ")) {
            return "0:00 ";
        }
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        seconds--;
        if (seconds < 0) {
            minutes--;
            seconds = 59;
        }
        return String.format("%d:%02d", minutes, seconds);
    }

    public void hoverController(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        String id = imageView.getId();
        if (!map.get(id)) {
            imageView.setImage(image);
            imageView.setOpacity(0.5);
        }

    }

    public void exitHoverController(MouseEvent mouseEvent) {
            ImageView imageView = (ImageView) mouseEvent.getSource();
            String id = imageView.getId();
            if (!map.get(id)) {
                imageView.setImage(null);
                imageView.setOpacity(1.0);
            }
    }

    public void finishGame() {
        for (String key : selected.keySet()) {
            selected.put(key, true);
            map.put(key, true);

        }
    }
    public void restartGame() {
        for (String key : selected.keySet()) {
            selected.put(key, false);
            map.put(key, false);
            imageViewHashMap.get(key).setImage(null);
            imageViewHashMap.get(key).setOpacity(1.0);
            gameBoard.put(key, "");
        }
    }

    public void playAgainController(ActionEvent event) {
        restartGame();
        resultLable.setText("");
        returnButton.setText("");
        playAgainButton.setText("");
        returnButton.setDisable(true);
        playAgainButton.setDisable(true);
        timeLable.setText("1:00");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), a -> {
            timeLable.setText(decreaseTime(timeLable.getText()));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void returnController(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkWinForX() {
        if (gameBoard.get("a11").equals("x") && gameBoard.get("a22").equals("x") && gameBoard.get("a33").equals("x")) {
            return true;
        }
        if (gameBoard.get("a13").equals("x") && gameBoard.get("a22").equals("x") && gameBoard.get("a31").equals("x")) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard.get("a1" + (i + 1)).equals("x") && gameBoard.get("a2" + (i + 1)).equals("x") && gameBoard.get("a3" + (i + 1)).equals("x")) {
                return true;
            }
            if (gameBoard.get("a" + (i + 1) + "1").equals("x") && gameBoard.get("a" + (i + 1) + "2").equals("x") && gameBoard.get("a" + (i + 1) + "3").equals("x")) {
                return true;
            }
        }
        return false;
    }
    public boolean checkWinForO() {
        if (gameBoard.get("a11").equals("o") && gameBoard.get("a22").equals("o") && gameBoard.get("a33").equals("o")) {
            return true;
        }
        if (gameBoard.get("a13").equals("o") && gameBoard.get("a22").equals("o") && gameBoard.get("a31").equals("o")) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard.get("a1" + (i + 1)).equals("o") && gameBoard.get("a2" + (i + 1)).equals("o") && gameBoard.get("a3" + (i + 1)).equals("o")) {
                return true;
            }
            if (gameBoard.get("a" + (i + 1) + "1").equals("o") && gameBoard.get("a" + (i + 1) + "2").equals("o") && gameBoard.get("a" + (i + 1) + "3").equals("o")) {
                return true;
            }
        }
        return false;
    }
}
