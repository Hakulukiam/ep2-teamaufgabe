/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListDataStructure implements DataStructure {
    public String type;
    private ArrayList<String[]> junctions = new ArrayList<>();

    public ArrayListDataStructure(String pathtofile) {
        this.type = "ArrayList";
        this.loadData(pathtofile);
    }

    @Override
    public Boolean add(String [] input){
        if(input.length == 4){
            this.junctions.add(input);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int[] inRange(double[] coords, double radius) {
        int[] inRange = new int[2];
        double x,y;
        for (String[] element: this.junctions ) {
            x = Double.valueOf(element[1]);
            y = Double.valueOf(element[2]);
            if(Math.sqrt(Math.pow((coords[0]-x),2)+Math.pow((coords[1]-y),2)) <= radius) {
                switch (element[3]) {
                    case "AIRPORT":
                        inRange[0]++;
                        break;
                    case "TRAINSTATION":
                        inRange[1]++;
                        break;
                }
            }
        }
        return inRange;
    }

    @Override
    public int AwTinRange(double r, int n) {
        int ret = 0;
        for (String[] element: this.junctions ) {
            if(element[3].equals("AIRPORT")){
                if(this.inRange(new double[]{Double.valueOf(element[1]),Double.valueOf(element[2])}, r)[1] >= n){
                    ret++;
                }
            }
        }
        return ret;
    }

    @Override
    public void printJunctions() {
        for (String[] element: this.junctions ) {
            System.out.println(Arrays.toString(element));
        }
    }
}