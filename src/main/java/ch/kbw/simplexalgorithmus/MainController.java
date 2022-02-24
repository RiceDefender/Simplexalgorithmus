package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

enum Format{
    ERROR,
    RESULT,
    RESET,
    INFO
}

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

    private ArrayList<TextField> flds;

    private int varCount, equationCount;


    @FXML
    public void calc() {
        for (int i = 0;i  < flds.size(); i++) {
            System.out.println(flds.get(i).getText());
        }

        // save the pivotTable dimension into variables
        varCount = Integer.parseInt(fld_varCount.getText());
        equationCount = Integer.parseInt(fld_equationCount.getText());

        PivotTable pivotTable = new PivotTable(varCount,equationCount);
        // read all textFields to get the values and store them into the newly created pivotTable
        for (int x = 0; x < varCount; x++) {
            for (int y = 0; y < equationCount + 1; y++) {
                if (!flds.get(getIndexFromId("fld_" + x + "_" + y)).getText().equals("")){
                    pivotTable.setValue(Double.parseDouble(flds.get(getIndexFromId("fld_" + x + "_" + y)).getText()),y,x);
                } else {
                    pivotTable.setValue(0D,y,x);
                }
            }
        }
        for (int y = 0; y < equationCount + 1; y++) {
            if (!flds.get(getIndexFromId("fld_" + varCount + "_" + y)).getText().equals("")){
                pivotTable.setValue(Double.parseDouble(flds.get(getIndexFromId("fld_" + varCount + "_" + y)).getText()),y,varCount+equationCount);
            }else {
                pivotTable.setValue(0D,y,varCount+equationCount);
            }
        }

        // calculate the most optimal solution and display it on the label
        //pivotTable.lastCol();

        if(!pivotTable.negchecker()){
            setLabel(pivotTable.cycle(), Format.RESULT);
        } else {
            System.out.println("Dualer Simplex wird ausgeführt...");
        }


    }

    // return the index of the textField in the list, by searching for its FXML id.
    private int getIndexFromId(String fld_id){
        for(int i = 0; i < flds.size();i++){
            if(!flds.get(i).getId().equals("") && flds.get(i).getId().equals(fld_id)){
                return i;
            }
        }
        return -1;
    }

    // This function takes the values (dimensions) and creates a matrix of dynamic TextFields in the Main view
    @FXML
    public void generate() {
        clean();
        setLabel("", Format.RESET);
        int val = Integer.parseInt(fld_varCount.getText());
        int val2 = Integer.parseInt(fld_equationCount.getText());
        if(val <= 8 && val2 <= 8 && val > 1 && val2 > 1){
            if(val <= val2){
                fillPane(val, val2);
            }else{
                setLabel("generate: error: more variables than equations", Format.ERROR);
                System.out.println("generate: error: more variables than equations");
            }
        }else{
            setLabel("generate: error: values invalid. valid => 2 - 8.", Format.ERROR);
            System.out.println("generate: error: values invalid. valid => 2 - 8.");
        }

    }

    // produces the matrix of TextFields that take the values of the equations.
    private void fillPane(int val, int val2){
        ids = new ArrayList<String>();
        flds = new ArrayList<TextField>();
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
                flds.add(fld);
            }
        }
    }


    // removes the matrix, to be reset.
    private void clean(){
        if(ids == null) return;
        pain.getChildren().removeIf(n -> n.getId() != null && ids.contains(n.getId()));
    }

    // format label
    private void setLabel(String out, Format format){
        lbl_error.setText(out);
        switch (format){
            case ERROR:
                lbl_error.setStyle("-fx-text-fill: red;");
                break;
            case RESULT:
                lbl_error.setStyle("-fx-text-fill: green;");
                break;
            case RESET:
                lbl_error.setStyle("-fx-text-fill: black;");
                break;
            case INFO:
                lbl_error.setStyle("-fx-text-fill: orange;");
                break;
        }
    }
}