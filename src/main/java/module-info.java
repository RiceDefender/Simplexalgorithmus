module ch.kbw.simplexalgorythmus {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.kbw.simplexalgorithmus to javafx.fxml;
    exports ch.kbw.simplexalgorithmus;

}