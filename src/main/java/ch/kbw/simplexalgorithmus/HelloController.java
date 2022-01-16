package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    private double [][] ex_table = {

            {2,2,1,0,0,16},
            {4,2,0,1,0,24},
            {4,6,0,0,1,36},
            {-80,-60,0,0,0,0}

            /*
            {1,1,1,0,0,90},
            {10,5,0,1,0,800},
            {3,6,0,0,1,420},
            {-36,-45,0,0,0,0}

             */




            /*
            {1,1,1,0,0,0,0,90},
            {10,5,0,1,0,0,0,800},
            {3,6,0,0,1,0,0,420},
            {-1,0,0,0,0,1,0,0},
            {0,-1,0,0,0,0,1,0},
            {36,45,0,0,0,0,0,0}

             */
            /*

            {1,1,1,1,1,0,0,0},
            { -1,2,0,0,0,1,0,0},
            {2,10,10,2,0,0,1,0},
            {2,3,3.5,4,0,0,0,1}
            */
            /*
            {4,4,1,0,0,32},
            {8,4,0,1,0,48},
            {8,12,0,0,1,72},
            {-160,-120,0,0,0,0}
             */
            /*
            {1,1,1,1,1,0,0,0},
            {4,2,0,1,0,24},
            {4,6,0,0,1,36},
            {-80,-60,0,0,0,0}
             */

    };

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        PivotTable pt = new PivotTable(2,3);
        pt.setPivotTable(ex_table);
        pt.cycle();


    }
}