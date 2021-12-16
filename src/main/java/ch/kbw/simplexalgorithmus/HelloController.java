package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    private double [][] ex_table = {
            /*
            {2,2,1,0,0,16},
            {4,2,0,1,0,24},
            {4,6,0,0,1,36},
            {-80,-60,0,0,0,0}
             */
            {4,4,1,0,0,32},
            {8,4,0,1,0,48},
            {8,12,0,0,1,72},
            {-160,-120,0,0,0,0}
    };

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        PivotTable pt = new PivotTable();
        pt.setPivotTable(ex_table);
        pt.cycle();


    }
}