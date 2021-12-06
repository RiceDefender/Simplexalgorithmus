package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        PivotTable pt = new PivotTable(2, 3);
        System.out.println(pt.toString());
    }
}