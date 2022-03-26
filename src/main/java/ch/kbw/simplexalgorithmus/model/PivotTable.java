package ch.kbw.simplexalgorithmus.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class PivotTable {
    private double[][] pivotTable;
    int tempvarCount, tempheight;
    double[] initial_gain_f;
    boolean downisneg = true;
    boolean rightisneg = true; //=> only for Dualer Simplex

    //Erstellt einen PivotTable mit der Einheitsmatrix
    public PivotTable(int varCount, int height) {
        tempvarCount = varCount;
        tempheight = height;
        this.pivotTable = new double[height + 1][varCount + height + 1];
        for (int i = 0; i < height; i++) {
            for (int j = varCount; j < varCount + height; j++) {
                if (i == j - varCount)
                    setValue(1, i, j);
                else
                    setValue(0, i, j);
            }
        }
    }

    public PivotTable() {
        // nüscht
    }

    //Hier ist der ganze Simplex Algorithmus
    public String cycle() {
        initial_gain_f = new double[tempvarCount];
        for (int i = 0; i < initial_gain_f.length; i++) {
            initial_gain_f[i] = pivotTable[pivotTable.length - 1][i];
        }
        int amountofvar = tempvarCount; //gets the amount of variables


        for (int i = 0; i < tempvarCount; i++) {
            pivotTable[pivotTable.length - 1][i] *= -1;
        }

        // k = Anz der Variablen
        int k = 0;
        while (downisneg && (k < amountofvar)) {
            // find the pivot number
            double[] temp = new double[pivotTable.length - 1];
            int indexX = findMin(pivotTable[pivotTable.length - 1]);
            for (int i = 0; i < pivotTable.length - 1; i++) {
                if (pivotTable[i].length - 1 != 0) { //if devider is not 0 devide!
                    temp[i] = pivotTable[i][pivotTable[i].length - 1] / pivotTable[i][indexX];
                }
            }
            int indexY = findMin(temp);
            double pivotnum = pivotTable[indexY][indexX];
            System.out.println(toString());


            //Going to subtract everything but pivot to 0
            for (int j = 0; j < pivotTable.length; j++) {
                double devider = pivotTable[j][indexX] / pivotTable[indexY][indexX];
                for (int i = 0; i < pivotTable[indexX].length; i++) {
                    if (pivotTable[j] != pivotTable[indexY]) { //Not dividing the pivot
                        pivotTable[j][i] = pivotTable[j][i] - pivotTable[indexY][i] * devider;
                    }
                }
            }
            System.out.println(toString());
            //Make the pivot 1
            double devider = pivotTable[indexY][indexX] / 1;
            for (int i = 0; i < pivotTable[k].length; i++) {
                pivotTable[indexY][i] = pivotTable[indexY][i] / devider;
            }
            System.out.println(toString());


            //Not used Experimental Code
            /*
            indexX = findMin(pivotTable[pivotTable.length-1]); // is the pivot row
            System.out.println(indexX);
                indexY = findMin(temp);

            System.out.println(indexY + "INDEXY");
            System.out.println(indexX + " INDEX X");
                if(k==0){
            for (int i = 0; i < pivotTable.length - 1; i++) {
                System.out.println(pivotTable[i][5] +"=> number");
                pivotTable[i][5] = pivotTable[i][5] / pivotTable[i][indexX];
                System.out.println(pivotTable[i][5]);
                System.out.println("Q");
            }
            }

             */
            /*
            devider = pivotTable[pivotTable.length-1][k] / pivotTable[indexY][indexX];
            for (int i = 0; i < pivotTable[k].length; i++) {//Not deviding the pivot

                    pivotTable[pivotTable.length-1][i] = pivotTable[pivotTable.length-1][i] - pivotTable[indexY][i] * devider;
                System.out.println(toString());
            }


            indexX++;

             */
            /*
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
            */
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
            int negativechecker = 0;
            for (int i = 0; i < tempvarCount; i++) { //If check the last row is pos
                if (pivotTable[tempheight][i] < 0) {
                    negativechecker++;
                }
            }
            if (negativechecker == 0) {
                downisneg = false;
                System.out.println("down is" + downisneg);
            }
            System.out.println("down is" + downisneg);
            k++;
        }

        //Output of Solution
        String out = "";
        for (int x = 0; x < tempvarCount; x++) {
            for (int y = 0; y < tempheight; y++) {
                if (pivotTable[y][x] == 1) {
                    BigDecimal bd = new BigDecimal(Double.toString(pivotTable[y][tempheight + tempvarCount]));
                    bd = bd.setScale(2, RoundingMode.HALF_EVEN);
                    out += initial_gain_f[x] + " * " + (bd);

                }
            }
            if (x != tempvarCount - 1) {
                out += " + ";
            }
        }
        out += " = " + pivotTable[pivotTable.length - 1][tempheight + tempvarCount];
        return out;
    }


    public String dualcycle() {
        // 1. pivotrow = smallest negative number in results column.
        // 2. divide every number of maximize function array with corresponding number in pivotrow -> chose smallest resulting number as pivotcolumn index.
        // 3.
        //////////////////////////////////////////////////////////

        // As long as the right side isn't positive

        while (rightisneg) {
            int indexY = 0;
            int indexX = 0;
            initial_gain_f = new double[tempvarCount];
            for (int i = 0; i < initial_gain_f.length; i++) {
                initial_gain_f[i] = pivotTable[pivotTable.length - 1][i];
            }
            // find the pivot number
            /*
            double[] temp = new double[pivotTable.length - 1];
            int indexX = findMin(pivotTable[pivotTable.length - 1]);
            for (int i = 0; i < pivotTable.length - 1; i++) {
                if (pivotTable[i].length - 1 != 0 && pivotTable[i][tempvarCount + tempheight] <= 0) { //if devider is not 0 devide!
                    temp[i] = pivotTable[i][pivotTable[i].length - 1] / pivotTable[i][indexX];
                }
            }
            int indexY = findMin(temp);
            System.out.println("INDEX X = "+ indexX + " INDEX Y = "+ indexY);
            double pivotnum = pivotTable[indexY][indexX];
            System.out.println(toString());
             */
            //--------------------------------------------------------------------------------------------------------
            double temp = 0;
            for (int i = 0; i < (tempheight - 1); i++) { //Searching for X in bi smallest num
                if (i != 0) {
                    if (pivotTable[i][tempheight + tempvarCount] < temp) {
                        indexY = i;
                    }
                } else {
                    indexY = i;
                }
            }
            for (int i = 0; i < (tempvarCount - 1); i++) {
                temp = 5000000;
                if (pivotTable[indexY][i] != 0) {
                    if ((pivotTable[tempheight - 1][i] / pivotTable[indexY][i]) < temp) {
                        temp = pivotTable[indexY][i];
                        indexX = i;
                    }
                }
                /*
                if (i != 0) {
                    if (pivotTable[indexY][i] == 0) {
                        if ((pivotTable[tempheight-1][i]/pivotTable[indexY][i]) <temp){
                            temp = pivotTable [indexY][i];
                            indexX = i;
                        }
                    }
                }else {
                    indexX = i;
                }

                 */
            }
            System.out.println("INDEX X = " + indexX + " INDEX Y = " + indexY);

            //--------------------------------------------------------------------------------------------------------


            //Make the pivot 1
            double devider = pivotTable[indexY][indexX] / 1;
            if (devider > 0) {
                devider *= -1;
            }
            for (int i = 0; i <= (tempheight + tempvarCount); i++) {
                pivotTable[indexY][i] = pivotTable[indexY][i] / devider;
            }
            System.out.println(toString());

            //Going to subtract everything but pivot to 0
            for (int j = 0; j < pivotTable.length; j++) { //Amount of gleichungen
                devider = pivotTable[j][indexX] / pivotTable[indexY][indexX];
                System.out.println(devider + "DEVIDER");
                for (int i = 0; i < pivotTable[indexX].length; i++) {

                    System.out.println(devider + "DEVIDER" + indexX + "INDEX X");
                    if (pivotTable[j] != pivotTable[indexY]) { //Not dividing the pivot
                        // pivotTable[j][i] = pivotTable[j][i] - pivotTable[indexY][i] * devider; /// [i]<--------!
                        // toString();


                        pivotTable[j][i] = pivotTable[j][i] - pivotTable[indexY][i] * devider;
                        System.out.println("AFTER: " + toString());
                    }
                }
                /*
                for (int i = 0; i < pivotTable[indexX].length; i++) { // subtract arrays
                    toString();
                    //System.out.println(devider +"DEVIDER" + indexX+"INDEX X");
                    if (pivotTable[j] != pivotTable[indexY]) { //Not dividing the pivot
                        pivotTable[j][i] = pivotTable[j][i] + pivotTable[indexY][i];
                    }
                }

                 */

            }


            int negativechecker = 0;
            for (int i = 0; i < (tempheight - 1); i++) {
                if (pivotTable[i][tempvarCount + tempheight] < 0) {
                    negativechecker++;
                }
            }
            if (negativechecker == 0) { //If a number of the right side is not smaller than 0
                rightisneg = false;
                System.out.println("right is" + rightisneg);
            }
            System.out.println("right is" + rightisneg);

            //ABC++;
        }
        //Check if it should switch to cycle for primaler Simplex.
        for (int i = 0; i < tempvarCount; i++) {
            if (pivotTable[tempheight][i] < 0) {
                cycle();
            }
        }

        /*
        for (int l = 0; l > tempvarCount; l++) {
            if (pivotTable[tempheight][l] != 0) {
                return cycle();
            }
        }

         */
        /////////////////////////////////////////////////////////
        System.out.println("A " + toString());
        String out = "";
        for (int x = 0; x < tempvarCount; x++) {
            for (int y = 0; y < tempheight; y++) {
                if (pivotTable[y][x] == 1) {
                    out += initial_gain_f[x] + " * " + pivotTable[y][tempheight + tempvarCount];
                }
            }
            if (x != tempvarCount - 1) {
                out += " + ";
            }
        }
        out += " = " + Math.abs(pivotTable[pivotTable.length - 1][tempheight + tempvarCount]);
        return out;
    }

    public boolean isDownisneg() {
        return true;
    }

    public void greaterEquals(int lineY) {
        for (int j = 0; j < tempvarCount; j++) {
            pivotTable[lineY][j] *= -1;
        }
        pivotTable[lineY][tempvarCount + tempheight] *= -1;
    }

    public String toString() {
        String out = "";
        for (double[] d : pivotTable) {
            out += Arrays.toString(d) + "\n";
        }
        return out;
    }

    //Getter und Setter
    public void setValue(double val, int y, int x) {
        this.pivotTable[y][x] = val;
    }

    public double getValue(int y, int x) {
        return this.pivotTable[y][x];
    }

    public double[][] getPivotTable() {
        return pivotTable;
    }

    public void setPivotTable(double[][] pivotTable) {
        this.pivotTable = pivotTable;
    }

    //Finds the index of the smallest number in an array
    private int findMin(double[] da) {
        double min = da[0];
        int index = 0;
        for (int i = 0; i < da.length; i++) {
            if (da[i] < min) {
                min = da[i];
                index = i;
            }
        }
        return index;
    }
}

