package com.example.sambo_tablo;

import com.example.sambo_tablo.create_file.CreateFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoardApplication.class.getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 574);
        stage.setTitle("Tablo (Beta)");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        CreateFile createFile = new CreateFile();
        createFile.createFile();
        launch();
    }
}