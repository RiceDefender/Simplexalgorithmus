package ch.kbw.simplexalgorithmus.model;

import java.util.Arrays;

public class PivotTable {
    int height;
    int varCount;
    double [][] pivotTable;

    public PivotTable(int varCount, int height){
        this.varCount = varCount;
        this.height = height;
        this.pivotTable = new double[height + 1][varCount + height + 2];
    }

    public String toString() {
        String out = "";
        for(double [] d : pivotTable){
            out += Arrays.toString(d) + "\n";
        }
        return out;
    }

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
}
