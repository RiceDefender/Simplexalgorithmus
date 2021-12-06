module ch.kbw.simplexalgorythmus {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.kbw.simplexalgorithmus to javafx.fxml;
    exports ch.kbw.simplexalgorithmus;
    exports ch.kbw.simplexalgorithmus.model;
    opens ch.kbw.simplexalgorithmus.model to javafx.fxml;

}