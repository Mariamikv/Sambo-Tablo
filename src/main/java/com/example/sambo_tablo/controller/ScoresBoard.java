package com.example.sambo_tablo.controller;

import com.example.sambo_tablo.data.Player;
import com.example.sambo_tablo.timer.SetTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import com.example.sambo_tablo.utils.Constants.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.sambo_tablo.utils.Constants.PAIN;

public class ScoresBoard implements Initializable {
    public Label red_name;
    public Label red_surname;
    public Label red_score;
    public Label blue_score;
    public Label blue_surname;
    public Label blue_name;
    public Label players_weight;
    public Label main_timer;
    public Label players_actions;
    public Label actions_timer;
    public Label red_city;
    public Label blue_city;

    private BoardController boardController;

    SetTimer setTimer = new SetTimer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // setMainTimer();
    }

    public void setPlayersData(Player[] players){
        red_name.setText(players[0].getName());
        red_surname.setText(players[0].getSurname());
        red_city.setText(players[0].getCity());

        blue_name.setText(players[1].getName());
        blue_surname.setText(players[1].getSurname());
        blue_city.setText(players[1].getCity());

        players_weight.setText("წონა: " + players[1].getWeight()+ "კგ");

    }

    public void setMainTimer(Double minutes){
        PauseTransition mainTimer = new PauseTransition(Duration.minutes(minutes));
        main_timer.textProperty().bind(setTimer.timeLestAsString(mainTimer));
        mainTimer.play();
//        if(action){
//            mainTimer.pause();
//        }
    }



    public void setParentController(BoardController boardController){
        this.boardController = boardController;
    }

    public void setRedPlayerScore(int score) {
        red_score.setText(String.valueOf(score));
    }

    public void setBluePlayerScore(int score){
        blue_score.setText(String.valueOf(score));
    }

    public Boolean pauseTimer(Boolean pause){
        return pause;
    }

    public void setSpecialActions(Double minutes, String action){
        if(Objects.equals(action, PAIN)){
            PauseTransition timer = new PauseTransition(Duration.seconds(20));
            actions_timer.textProperty().bind(setTimer.timeLestAsString(timer));
            timer.play();
        }
        PauseTransition timer = new PauseTransition(Duration.minutes(minutes));
        actions_timer.textProperty().bind(setTimer.timeLestAsString(timer));
        timer.play();

        if(pauseTimer(true)){
            timer.pause();
        }

        players_actions.setText(action);
    }
}
