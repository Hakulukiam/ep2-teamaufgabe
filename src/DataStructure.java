/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface DataStructure {
    public Boolean add(String[] input);
    public int[] inRange(double[] coords, double radius);
    public int AwTinRange(double r, int n);
    public void printJunctions();
    default void loadData(String path){
        try(Scanner s = new Scanner(new File(System.getProperty("user.dir") +path), "UTF-8")) {
            s.useDelimiter("\n");
            String element;
            while(s.hasNext()){
                element = s.next();
                this.add(element.split(";"));
            }
            s.close();
        } catch(FileNotFoundException e) {
            System.err.println("File Error");
            System.exit(1);
        }
    }
}