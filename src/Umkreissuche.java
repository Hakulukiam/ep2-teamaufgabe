import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Umkreissuche {

    private ArrayList<String[]> Ljunctions;
    private Tree Tjunctions;
    private int DataStructure;

    public Umkreissuche(String path, int DataStructure){
        try(Scanner s = new Scanner(
            new File(System.getProperty("user.dir") +path), "UTF-8")) {
            s.useDelimiter("\n");
            switch (DataStructure){
                case 0:
                    //ArrayList Here
                    Ljunctions = new ArrayList<>();
                    String element;
                    while(s.hasNext()){
                        element = s.next();
                        String[] arr = element.split(";");
                        Ljunctions.add(arr);
                    }
                    s.close();
                    break;
                case 1:
                    //2D Tree Here
                    break;
                default:
                    System.err.println("No Valid Data Structure");
                    System.exit(1);
            }
        } catch(FileNotFoundException e) {
            System.exit(1);
        }
    }

    public void printJunctions(){
        for (String[] element: this.Ljunctions ) {
            System.out.println(Arrays.toString(element));
        }
    }

    public int[] inRange(double[] coords, double radius){
        int[] inRange = new int[2];
        double x,y;
        for (String[] element: this.Ljunctions ) {
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

    public int AirportsWithTrainstations(double r, int n){
        int ret = 0;
        for (String[] element: this.Ljunctions ) {
            if(element[3].equals("AIRPORT")){
                if(this.inRange(new double[]{Double.valueOf(element[1]),Double.valueOf(element[2])}, r)[1] >= n){
                    ret++;
                }
            }
        }
        return ret;
    }
}