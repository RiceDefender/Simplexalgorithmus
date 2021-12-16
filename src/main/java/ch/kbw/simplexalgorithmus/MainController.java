package ch.kbw.simplexalgorithmus;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MainController {
    @FXML
    private Pane pain;
    @FXML
    private TextField fld_varCount;
    @FXML
    private TextField fld_equationCount;
    @FXML
    private Label lbl_error;

    private ArrayList<String> ids;

    @FXML
    public void calc() {
        // implement the cycling of the pivot table (algorithm)
    }

    @FXML
    public void generate() {
        clean();
        int val = Integer.parseInt(fld_varCount.getText());
        int val2 = Integer.parseInt(fld_equationCount.getText());
        if(val <= 8 && val2 <= 8 && val > 1 && val2 > 1){
            if(val <= val2){
                fillPane(val, val2);
            }else{
                lbl_error.setText("generate: error: more variables than equations");
                System.out.println("generate: error: more variables than equations");
            }
        }else{
            lbl_error.setText("generate: error: values invalid. valid => 2 - 8.");
            System.out.println("generate: error: values invalid. valid => 2 - 8.");
        }
    }

    // produces the matrix of TextFields that take the values of the equations.
    private void fillPane(int val, int val2){
        ids = new ArrayList<String>();
        for (int i = 0; i < val+1; i++) {
            Label lbl = new Label();
            lbl.setLayoutX(50 + 62 * i);
            lbl.setLayoutY(130);
            lbl.setPrefWidth(60);
            lbl.setId("lbl_" + i);
            ids.add("lbl_" + i);
            if (i < val) {
                lbl.setText("X" + (i+1));
            } else {
                lbl.setText("RES");
            }
            pain.getChildren().add(lbl);
        }
        for (int i = 0; i < val+1; i++) {
            for (int j = 0; j < val2+1; j++) {
                TextField fld = new TextField();
                fld.setLayoutX(50 + 62 * i);
                fld.setLayoutY(152 + 27 * j);
                fld.setPrefWidth(60);
                fld.setId("fld_" + i + "_" + j);
                ids.add("fld_" + i + "_" + j);
                pain.getChildren().add(fld);
            }
        }
    }

    // removes the matrix, to be reset.
    private void clean(){
        if(ids == null) return;
        pain.getChildren().removeIf(n -> n.getId() != null && ids.contains(n.getId()));
    }
}