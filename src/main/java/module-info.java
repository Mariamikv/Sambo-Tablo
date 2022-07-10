module com.example.sambo_tablo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sambo_tablo to javafx.fxml;
    exports com.example.sambo_tablo;
    exports com.example.sambo_tablo.controller;
    opens com.example.sambo_tablo.controller to javafx.fxml;
}