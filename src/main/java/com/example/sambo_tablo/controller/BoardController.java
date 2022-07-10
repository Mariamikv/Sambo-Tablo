package com.example.sambo_tablo.controller;

import com.example.sambo_tablo.BoardApplication;
import com.example.sambo_tablo.create_file.WriteIntoFile;
import com.example.sambo_tablo.data.Player;
import com.example.sambo_tablo.timer.SetTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.sambo_tablo.utils.Constants.*;

public class BoardController implements Initializable {
    public Label red_counter, red_time, red_action_label;
    public Button saveButton, red_add_one_button, red_add_two_button, red_add_four_button,
        red_subtract_button, red_reset, red_medic, red_pain, red_handle, red_stop;
    public TextField red_name, red_surname, red_city;

    public TextField weight;
    public Button start_button, reset_button, stop_button, set_5min_button, set_4Min_button, set_3Min_button;
    public TextArea fight_description;
    public Label timerDisplay;

    public TextField blue_name, blue_surname, blue_city;
    public Label blue_counter, blue_time, blue_action_label;
    public Button blue_add_one_button, blue_add_two_button, blue_add_four_button, blue_subtract_button, blue_reset,
            blue_medic, blue_pain, blue_handle, blue_stop;

    public int bluePlayerScore = 0, redPlayerScore = 0;

    WriteIntoFile file = new WriteIntoFile();

    SetTimer setTimer = new SetTimer();

    Boolean btn5min=false, btn3Min=false, btn4Min=false, btnStop=false, btnMedic=false, btnHandle=false, btnPain=false;

    Alert a = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //scores
        //getBluePlayerScore();
        //getRedPlayerScore();

        //start
        setRoundTimers();

        setRedPlayerSpecialActions();
        setBluePlayerSpecialActions();

        //set label background color and padding
        fight_description.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fight_description.setPadding(new Insets(8));

        saveInfo();
    }

//    public void getBluePlayerScore(){
//        blue_add_one_button.setOnAction(e -> {
//            bluePlayerScore++;
//            blue_counter.setText(String.valueOf(bluePlayerScore));
//        });
//
//        blue_add_two_button.setOnAction(e -> {
//            bluePlayerScore+=2;
//            blue_counter.setText(String.valueOf(bluePlayerScore));
//        });
//
//        blue_add_four_button.setOnAction(e -> {
//            bluePlayerScore+=4;
//            blue_counter.setText(String.valueOf(bluePlayerScore));
//        });
//
//        blue_subtract_button.setOnAction(e -> {
//            bluePlayerScore--;
//            blue_counter.setText(String.valueOf(bluePlayerScore));
//        });
//
//        blue_reset.setOnAction(e -> {
//            bluePlayerScore = 0;
//            blue_counter.setText(String.valueOf(bluePlayerScore));
//        });
//    }
//
//    public void getRedPlayerScore(){
//
//    }

    void writeInfoIntoFile(){
        // data from red player
        Player redPlayer = new Player(red_name.getText(), red_surname.getText(), red_city.getText(), weight.getText());

        // data from blue player
        Player bluePlayer = new Player(blue_name.getText(), blue_surname.getText(), blue_city.getText(), weight.getText());

        // write players data into file
        String redPlayerInfo = "ინფორმაცია შეჯიბრების შესახებ" + System.lineSeparator() +
                "მოთამაშის სახელი: " + redPlayer.getName() + System.lineSeparator() +
                "მოთამაშის გვარი: " + redPlayer.getSurname() + System.lineSeparator() +
                "ქალაქი: " + redPlayer.getCity() + System.lineSeparator() ;

        String bluePlayerInfo = "მოთამაშის სახელი: " + bluePlayer.getName() + System.lineSeparator()+
                "მოთამაშის გვარი: " + bluePlayer.getSurname() + System.lineSeparator() +
                "ქალაქი: " + bluePlayer.getCity() + System.lineSeparator() ;

        String playersScore = "ინფორმაცია შედეგების შესახებ:" + System.lineSeparator() +
                redPlayer.getSurname() + " " + redPlayer.getName() + " - " + redPlayerScore + System.lineSeparator() +
                bluePlayer.getSurname() + " " + bluePlayer.getName() + " - " + bluePlayerScore ;

        String info = redPlayerInfo + System.lineSeparator() +
                bluePlayerInfo + System.lineSeparator() +
                "მონაწილეების წონითი კატეგორია: " + redPlayer.getWeight() + System.lineSeparator() +
                playersScore + System.lineSeparator();

        file.writeIntoFile(info);
    }

    public void saveInfo(){
        // save into file
        saveButton.setOnAction(e -> writeInfoIntoFile());
    }

    private void setRoundTimers(){
        set_5min_button.setOnAction(e -> {
            btn5min=true;
            timerDisplay.setText("05:00");
            PauseTransition mainTimer = new PauseTransition(Duration.minutes(5));
            startGameTimer(mainTimer);
            //fight log
            fight_description.setText("დაიწყო შეჯიბრი 5 წუთი");
        });
        set_4Min_button.setOnAction(e -> {
            btn4Min=true;
            timerDisplay.setText("04:00");
            PauseTransition mainTimer = new PauseTransition(Duration.minutes(4));
            startGameTimer(mainTimer);
            //fight log
            fight_description.setText("დაიწყო შეჯიბრი 4 წუთი");
        });
        set_3Min_button.setOnAction(e -> {
            btn3Min=true;
            timerDisplay.setText("03:00");
            PauseTransition mainTimer = new PauseTransition(Duration.minutes(3));
            startGameTimer(mainTimer);
            //fight log
            fight_description.setText("დაიწყო შეჯიბრი 3 წუთი");
        });
    }

    private void startGameTimer(PauseTransition mainTimer ){
        start_button.setOnAction(e -> {
            if(red_name.getText().trim().equals("") || red_surname.getText().trim().equals("") || red_city.getText().trim().equals("") ||
                blue_name.getText().trim().equals("") || blue_surname.getText().trim().equals("") || blue_city.getText().trim().equals("") ||
                weight.getText().trim().equals("")){

                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("შეიყვანეთ მოთამაშეების ინფორმაცია");
                a.show();
            }else {
                Player[] players = {
                        new Player(red_name.getText(), red_surname.getText(), red_city.getText(), weight.getText()),
                        new Player(blue_name.getText(), blue_surname.getText(), blue_city.getText(), weight.getText())
                };
                timerDisplay.textProperty().bind(setTimer.timeLestAsString(mainTimer));
                mainTimer.play();
                try {
                    openNewWindowAndPassData(players);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        stop_button.setOnAction(e -> {
            btnStop=true;
            mainTimer.pause();
        });
        reset_button.setOnAction(e -> mainTimer.stop());
    }

    private void setRedPlayerSpecialActions(){

    }

    public void setBluePlayerSpecialActions(){

    }

    public void openNewWindowAndPassData(Player[] players) throws IOException {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(BoardApplication.class.getResource("scores-board.fxml")));
        Parent root = loader.load();

        ScoresBoard scoresBoard = loader.getController();
        scoresBoard.setParentController(this);

        if(btn5min){
            scoresBoard.setMainTimer(5.0);
        }else if(btn4Min){
            scoresBoard.setMainTimer(4.0);
        }else if(btn3Min){
            scoresBoard.setMainTimer(3.0);
        }

        scoresBoard.setPlayersData(players);

        blue_add_one_button.setOnAction(e -> {
            bluePlayerScore++;
            blue_counter.setText(String.valueOf(bluePlayerScore));
            scoresBoard.setBluePlayerScore(bluePlayerScore);
        });

        blue_add_two_button.setOnAction(e -> {
            bluePlayerScore+=2;
            blue_counter.setText(String.valueOf(bluePlayerScore));
            scoresBoard.setBluePlayerScore(bluePlayerScore);
        });

        blue_add_four_button.setOnAction(e -> {
            bluePlayerScore+=4;
            blue_counter.setText(String.valueOf(bluePlayerScore));
            scoresBoard.setBluePlayerScore(bluePlayerScore);
        });

        blue_subtract_button.setOnAction(e -> {
            bluePlayerScore--;
            blue_counter.setText(String.valueOf(bluePlayerScore));
            scoresBoard.setBluePlayerScore(bluePlayerScore);
        });

        blue_reset.setOnAction(e -> {
            bluePlayerScore = 0;
            blue_counter.setText(String.valueOf(bluePlayerScore));
            scoresBoard.setBluePlayerScore(bluePlayerScore);
        });

        red_add_one_button.setOnAction(e -> {
            redPlayerScore++;
            red_counter.setText(String.valueOf(redPlayerScore));
            scoresBoard.setRedPlayerScore(redPlayerScore);
        });

        red_add_two_button.setOnAction(e -> {
            redPlayerScore+=2;
            red_counter.setText(String.valueOf(redPlayerScore));
            scoresBoard.setRedPlayerScore(redPlayerScore);
        });

        red_add_four_button.setOnAction(e -> {
            redPlayerScore+=4;
            red_counter.setText(String.valueOf(redPlayerScore));
            scoresBoard.setRedPlayerScore(redPlayerScore);
        });

        red_subtract_button.setOnAction(e -> {
            redPlayerScore--;
            red_counter.setText(String.valueOf(redPlayerScore));
            scoresBoard.setRedPlayerScore(redPlayerScore);
        });

        red_reset.setOnAction(e -> {
            redPlayerScore = 0;
            red_counter.setText(String.valueOf(redPlayerScore));
            scoresBoard.setRedPlayerScore(redPlayerScore);
        });

        PauseTransition medicTimer = new PauseTransition(Duration.minutes(2));
        PauseTransition handleTimer = new PauseTransition(Duration.seconds(20));
        PauseTransition painTimer = new PauseTransition(Duration.minutes(1));

        blue_medic.setOnAction(e -> {
            blue_time.textProperty().bind(setTimer.timeLestAsString(medicTimer));
            medicTimer.play();
            blue_action_label.setText(MEDIC);
            //fight log
            fight_description.setText(MEDIC);
            scoresBoard.setSpecialActions(2.0, MEDIC);
        });

        blue_pain.setOnAction(e -> {
            blue_time.textProperty().bind(setTimer.timeLestAsString(painTimer));
            painTimer.play();
            blue_action_label.setText(PAIN);
            fight_description.setText(PAIN);
            scoresBoard.setSpecialActions(0.20, PAIN);
        });

        blue_handle.setOnAction(e -> {
            blue_time.textProperty().bind(setTimer.timeLestAsString(handleTimer));
            handleTimer.play();
            blue_action_label.setText(HANDLE);
            // fight log
            fight_description.setText(HANDLE);
            scoresBoard.setSpecialActions(1.0, HANDLE);
        });

        blue_stop.setOnAction(event -> {
            medicTimer.pause();
            painTimer.pause();
            handleTimer.pause();
            blue_action_label.setText(STOP);
            // fight log
            fight_description.setText(STOP);
            scoresBoard.pauseTimer(true);
        });

//        PauseTransition medicTimer = new PauseTransition(Duration.minutes(2));
//        PauseTransition handleTimer = new PauseTransition(Duration.seconds(20));
//        PauseTransition painTimer = new PauseTransition(Duration.minutes(1));

        red_medic.setOnAction(e -> {
            red_time.textProperty().bind(setTimer.timeLestAsString(medicTimer));
            medicTimer.play();
            red_action_label.setText(MEDIC);
            //fight log
            fight_description.setText(MEDIC);
            scoresBoard.setSpecialActions(2.0, MEDIC);
        });

        red_pain.setOnAction(e -> {
            red_time.textProperty().bind(setTimer.timeLestAsString(painTimer));
            painTimer.play();
            red_action_label.setText(PAIN);
            fight_description.setText(PAIN);
            scoresBoard.setSpecialActions(0.20, PAIN);
        });

        red_handle.setOnAction(e -> {
            red_time.textProperty().bind(setTimer.timeLestAsString(handleTimer));
            handleTimer.play();
            red_action_label.setText(HANDLE);
            // fight log
            fight_description.setText(HANDLE);
            scoresBoard.setSpecialActions(1.0, HANDLE);
        });

        red_stop.setOnAction(e -> {
            medicTimer.pause();
            painTimer.pause();
            handleTimer.pause();
            red_action_label.setText(STOP);
            // fight log
            fight_description.setText(STOP);
            scoresBoard.pauseTimer(true);
        });

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Scores Board");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.NONE);
        primaryStage.show();
    }
}