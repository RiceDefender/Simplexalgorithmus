package ch.kbw.simplexalgorithmus.model;

import java.util.Arrays;

public class PivotTable {
    private double [][] pivotTable;

    public PivotTable(int varCount, int height){
        this.pivotTable = new double[height + 1][varCount + height + 1];
    }

    public PivotTable(){
        // nüscht
    }

    public void cycle(){
        // vars
        double [] temp = new double[pivotTable.length-1]; // will save the divisional result of "num pivot column" / "result of equation"
        int indexX = findMin(pivotTable[pivotTable.length-1]); // is the pivot row
        double pivotnum;




        // find the pivot number
        for(int i = 0; i < pivotTable.length -1; i++){
            temp[i] = pivotTable[i][pivotTable[i].length-1] / pivotTable[i][indexX];
        }
        int indexY = findMin(temp);
        pivotnum = pivotTable[indexY][indexX];
        System.out.println(toString());
        System.out.println(indexX + " : " + indexY + " : " + pivotnum);


        //Going subtract everything but pivot to 0
        for (int j=0; j <pivotTable.length;j++ ) {
            double devider = pivotTable[j][0] / pivotTable[indexY][indexX];
            for (int i = 0; i < pivotTable[0].length; i++) {
                if(pivotTable[j] != pivotTable[indexY]) { //Not deviding the pivot
                pivotTable[j][i] = pivotTable[j][i] - pivotTable[indexY][i] * devider;
                }
            }
        }
        System.out.println(toString());

        //Make the pivot 1
        double devider = pivotTable[indexY][0] / 1;
        for (int i = 0; i < pivotTable[0].length; i++) {
                pivotTable[indexY][i] = pivotTable[indexY][i] / devider;
        }
        System.out.println(toString());
        System.out.println("AAAAAAAAAAA");


        indexX = findMin(pivotTable[pivotTable.length-1]); // is the pivot row
        for(int i = 0; i < pivotTable.length -1; i++){
            temp[i] = pivotTable[i][pivotTable[i].length-1] / pivotTable[i][indexX];
        }
        indexY = findMin(temp);
        pivotnum = pivotTable[indexY][indexX];
        System.out.println(toString());
        System.out.println(indexX + " : " + indexY + " : " + pivotnum);
        System.out.println(pivotTable[indexY][indexX]);

        for (int j=0; j <pivotTable.length;j++ ) {
            devider = pivotTable[j][1] / pivotTable[indexY][indexX];
            for (int i = 0; i < pivotTable[1].length; i++) {
                if(pivotTable[j] != pivotTable[indexY]) { //Not deviding the pivot
                    pivotTable[j][i] = pivotTable[j][i] - pivotTable[indexY][i] * devider;
                }
            }
        }
        System.out.println(toString());

        devider = pivotTable[indexY][indexX] / 1;
        for (int i = 0; i < pivotTable[1].length; i++) {
            if (pivotTable[indexY][i]!= 0) {
                pivotTable[indexY][i] = pivotTable[indexY][i] / devider;
            }
        }
        System.out.println(toString());



        /*
        // make pivot number 1 and ratio the rest of the pivot row
        ratioRow(indexY, pivotnum);
        System.out.println(toString());
        for(int i = 0; i < pivotTable.length-1; i++){
            ratioRow(i, pivotTable[i][indexX]);
        }
        System.out.println(toString());

         */
    /*
        // make every number in pivotCol except pivot num 0
        for(int i = 0; i < pivotTable.length; i++){
            if (i != indexY) subtractAgainst(i, indexY);
        }
     */

    }

    public String toString() {
        String out = "";
        for(double [] d : pivotTable){
            out += Arrays.toString(d) + "\n";
        }
        return out;
    }

    //For later, when we implement FXML
    public void setValue(double val, int y, int x){
        this.pivotTable[y][x] = val;
    }

    public double getValue(int y, int x){
        return this.pivotTable[y][x];
    }

    public double[][] getPivotTable() {
        return pivotTable;
    }

    public void setPivotTable(double[][] pivotTable) {
        this.pivotTable = pivotTable;
    }

    private int findMin(double [] da){
        double min = da[0];
        int index = 0;
        for(int i = 0; i < da.length; i++){
            if(da[i] < min){
                min = da[i];
                index = i;
            }
        }
        return index;
    }

    private void ratioRow(int rowIndex, double factor){
        for(int i = 0; i < pivotTable[rowIndex].length; i++){
            this.pivotTable[rowIndex][i] /= factor;
        }
    }

    private void subtractAgainst(int rowI, int pivotRow) {
        for(int i = 0; i < pivotTable[0].length; i++){
            pivotTable[rowI][i] -= pivotTable[pivotRow][i];
        }
    }
}
