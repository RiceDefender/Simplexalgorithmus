/**
 * @since 22.11.2021
 * @version 26.03.2022
 * @author Trong-Nghia Dao
 * @author Nicolas Amberg
 * @author Jens Eichenberger
 */

package ch.kbw.simplexalgorithmus;

import ch.kbw.simplexalgorithmus.model.PivotTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private void initialize(){
        choicebox.getItems().addAll("Maximieren", "Minimieren");
        choicebox.setValue("Maximieren");
    }

    private ArrayList<String> ids;

    private ArrayList<TextField> flds;

    private ArrayList<ToggleButton> toggleButtons;

    private int varCount, equationCount;


    @FXML
    public void calc() {
        // Error handling
        if(flds == null){
            setLabel("Keine Felder. Klicken Sie auf 'Felder anzeigen'", Format.ERROR);
            System.out.println("Error:\tKeine Felder. Klicken Sie auf 'Felder anzeigen'");
            return;
        }

        for (int i = 0;i  < flds.size(); i++) {
            System.out.println(flds.get(i).getText());
        }

        // save the pivotTable dimension into variables
        varCount = Integer.parseInt(fld_varCount.getText());
        equationCount = Integer.parseInt(fld_equationCount.getText());

        PivotTable pivotTable = new PivotTable(varCount,equationCount);
        // read all textFields (except "RES") to get the values and store them into the newly created pivotTable
        for (int x = 0; x < varCount; x++) {
            for (int y = 0; y < equationCount + 1; y++) {
                if (!flds.get(getIndexFromId("fld_" + x + "_" + y)).getText().equals("")){
                    pivotTable.setValue(Double.parseDouble(flds.get(getIndexFromId("fld_" + x + "_" + y)).getText()),y,x);
                } else {
                    pivotTable.setValue(0D,y,x);
                }
            }
        }
        // Reads the values from the right-most column "RES" also stores in pivot table.
        for (int y = 0; y < equationCount + 1; y++) {
            if (!flds.get(getIndexFromId("fld_" + (varCount + 1) + "_" + y)).getText().equals("")){
                pivotTable.setValue(Double.parseDouble(flds.get(getIndexFromId("fld_" + (varCount + 1) + "_" + y)).getText()),y,varCount+equationCount);
            }else {
                pivotTable.setValue(0D,y,varCount+equationCount);
            }
        }

        if(choicebox.getValue().equals("Minimieren")){
            pivotTable.umkehren();
            System.out.println("Pivot umgekehrt.");
        }
        // Apply *-1 to row if toggle button is activated (unactivated >=, activated <=).
        System.out.println("Before: \n" + pivotTable.toString());
        for(int i = 0; i < equationCount; i++){
            if(toggleButtons.get(i).isSelected()){
                pivotTable.greaterEquals(i);
            }
        }
        System.out.println("After: \n" + pivotTable.toString());
        // calculate the most optimal solution and display it on the label

        System.out.println(pivotTable.toString());

        if(!pivotTable.negchecker()){
            setLabel(pivotTable.cycle(), Format.RESULT);
        } else {
            setLabel(pivotTable.dualcycle(), Format.RESULT);
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

    // This function takes the values (dimensions) and creates a matrix of dynamic TextFields und ToggleButtons in the main view.
    @FXML
    public void generate() {
        clean();

        // Error handling
        if(fld_varCount.getText().isBlank() || fld_equationCount.getText().isBlank()){
            setLabel("Anzahl der Variablen oder Gleichungen nicht bestimmt.", Format.ERROR);
            System.out.println("Error:\tAnzahl der Variablen oder Gleichungen nicht bestimmt.");
            return;
        }

        int val = Integer.parseInt(fld_varCount.getText());
        int val2 = Integer.parseInt(fld_equationCount.getText());
        if(val <= 8 && val2 <= 8 && val > 1 && val2 > 1){
            if(val <= val2){
                fillPane(val, val2);
            }else{
                setLabel("Error: more variables than equations", Format.ERROR);
                System.out.println("Error: more variables than equations");
            }
        }else{
            setLabel("Error: values invalid. valid => 2 - 8.", Format.ERROR);
            System.out.println("Error: values invalid. valid => 2 - 8.");
        }

    }

    @FXML
    public void minmaxPivotTable(){
        if(toggleButtons == null){
            setLabel("INFO: No toggle buttons to be written to.", Format.INFO);
            System.out.println("INFO: No toggle buttons to be written to.");
            return;
        }
        for(int i = 0; i < toggleButtons.size() - 1; i++){
            ToggleButton current = toggleButtons.get(i);
            if(current.getText().equals(">=")){
                current.setText("<=");
            }else{
                current.setText(">=");
            }
        }
        for(ToggleButton b : toggleButtons){
            System.out.println(b.isSelected());
        }
    }

    // produces the matrix of TextFields und ToggleButtons that take the values of the equations.
    private void fillPane(int val, int val2){
        ids = new ArrayList<String>();                  // clean() even necessary?
        flds = new ArrayList<TextField>();              //
        toggleButtons = new ArrayList<ToggleButton>();  //
        // iterate through the top label row.
        for (int i = 0; i < val+2; i++) {
            if(i == val){continue;}
            Label lbl = new Label();
            lbl.setLayoutX(130 + 62 * i);
            lbl.setLayoutY(130);
            lbl.setPrefWidth(60);
            lbl.setId("lblTop_" + i);
            ids.add("lblTop_" + i);
            if (i < val) { // variable column need to have different labels.
                lbl.setText("Variable " + (i+1));
            } else {
                lbl.setText("Resultat");
            }
            pain.getChildren().add(lbl);
        }
        // iterate over Equation labels
        for(int i = 0; i < val2+1; i++){
            Label lbl = new Label();
            lbl.setLayoutX(50);
            if(i == val2){ // goal function label needs to be different.
                lbl.setLayoutY(162 + 27 * i);
                lbl.setText("Zielfunktion");
            }else{
                lbl.setLayoutY(152 + 27 * i);
                lbl.setText("Gleichung " + (i + 1));
            }
            lbl.setPrefHeight(25);
            lbl.setPrefWidth(100);
            lbl.setId("lblSide_" + i);
            ids.add("lblSide_" + i);
            pain.getChildren().add(lbl);
        }
        // iterate columns of TextFields and ToggleButtons
        for (int i = 0; i < val+2; i++) {
            // draw first fields
            for (int j = 0; j < val2+1; j++) {
                if (i == val) { // look if the column has a button
                    ToggleButton tglBtn = new ToggleButton();
                    if(j == val2){ // toggle button of goal function? needs to be different.
                        tglBtn.setText("=");
                    }else{
                        tglBtn.setText(">=");
                        tglBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if(choicebox.getValue().equals("Maximieren")){
                                    if(tglBtn.isSelected()){
                                        tglBtn.setText("<=");
                                    }else{
                                        tglBtn.setText(">=");
                                    }
                                }else{
                                    if(tglBtn.isSelected()){
                                        tglBtn.setText(">=");
                                    }else{
                                        tglBtn.setText("<=");
                                    }
                                }
                            }
                        });
                    }
                    tglBtn.setLayoutX(130 + 62 * i);
                    if(j == val2){
                        tglBtn.setLayoutY(162 + 27 * j);
                    }else{
                        tglBtn.setLayoutY(152 + 27 * j);
                    }
                    tglBtn.setId("tglBtn_" + j);
                    tglBtn.setPrefWidth(60);
                    ids.add("tglBtn_" + j);
                    toggleButtons.add(tglBtn);
                    pain.getChildren().add(tglBtn);
                }else{ // create normal text field
                    TextField fld = new TextField();
                    fld.setLayoutX(130 + 62 * i);
                    if(j == val2){ // goal function needs have a bigger top-margin
                        fld.setLayoutY(162 + 27 * j);
                    }else{
                        fld.setLayoutY(152 + 27 * j);
                    }
                    fld.setPrefWidth(60);
                    fld.setId("fld_" + i + "_" + j);
                    ids.add("fld_" + i + "_" + j);
                    pain.getChildren().add(fld);
                    flds.add(fld);
                }
            }
        }
    }


    // removes the matrix, to be reset.
    private void clean(){
        setLabel("", Format.RESET);
        choicebox.setValue("Maximieren");
        if(toggleButtons != null){
            for(ToggleButton b : toggleButtons){
                b.setSelected(false);
                b.setText(">=");
            }
        }
        if(ids == null) return;
        pain.getChildren().removeIf(n -> n.getId() != null && ids.contains(n.getId()));
    }

    // format label
    private void setLabel(String out, Format format){
        lbl_error.setText(out);
        switch (format) {
            case ERROR -> lbl_error.setStyle("-fx-text-fill: red;");
            case RESULT -> lbl_error.setStyle("-fx-text-fill: green;");
            case RESET -> lbl_error.setStyle("-fx-text-fill: black;");
            case INFO -> lbl_error.setStyle("-fx-text-fill: orange;");
        }
    }
}