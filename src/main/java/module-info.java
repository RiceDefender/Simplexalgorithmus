module ch.kbw.simplexalgorythmus {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.kbw.simplexalgorythmus to javafx.fxml;
    exports ch.kbw.simplexalgorythmus;

}