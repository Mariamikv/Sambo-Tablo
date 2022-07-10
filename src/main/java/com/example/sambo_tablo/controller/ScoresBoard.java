package com.example.sambo_tablo.controller;

import com.example.sambo_tablo.data.Player;
import com.example.sambo_tablo.timer.SetTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static com.example.sambo_tablo.utils.Constants.HANDLE;

public class ScoresBoard implements Initializable {
    public Label red_name, red_surname, red_score, blue_score, blue_surname, blue_name, players_weight, main_timer,
            players_actions, actions_timer, red_city, blue_city;

    private BoardController boardController;

    SetTimer setTimer = new SetTimer();

    PauseTransition timer;
    PauseTransition mainTimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setPlayersData(Player[] players){
        red_name.setText(players[0].getName());
        red_surname.setText(players[0].getSurname());
        red_city.setText(players[0].getCity());

        blue_name.setText(players[1].getName());
        blue_surname.setText(players[1].getSurname());
        blue_city.setText(players[1].getCity());

        players_weight.setText("წონითი კატეგორია: " + players[1].getWeight()+ "კგ");
    }

    public void setMainTimer(Double minutes){
        mainTimer = new PauseTransition(Duration.minutes(minutes));
        main_timer.textProperty().bind(setTimer.timeLestAsString(mainTimer));
        mainTimer.play();
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

    public void setSpecialActions(Double minutes, String action){
        if(Objects.equals(action, HANDLE)){
            timer = new PauseTransition(Duration.seconds(30));
            actions_timer.textProperty().bind(setTimer.timeLestAsString(timer));
            timer.play();
        }
        timer = new PauseTransition(Duration.minutes(minutes));
        actions_timer.textProperty().bind(setTimer.timeLestAsString(timer));
        timer.play();

        players_actions.setText(action);
    }

    public void pauseMainTimer(boolean pauseAction){
        if(pauseAction){
            mainTimer.pause();
        }
    }

    public void pauseTimer(boolean pauseAction){
        if (pauseAction){
            timer.pause();
        }
    }
}
