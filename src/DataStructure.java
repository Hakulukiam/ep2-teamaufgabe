/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface DataStructure {
    Boolean add(JunctionNode input);

    /**
     * Gets Number of Airports and Trainstations in Range radius from Point
     *
     * @param coords point
     * @param radius radius
     */
    int[] inRange(Point2D.Double coords, double radius);

    /**
     * Calculates Number of Airports with >=n Trainstations in Range of r
     *
     * @param r Range arround Airport
     * @param n Number of Trainstations
     */
    int AwTinRange(double r, int n);

    /**
     * Prints Junctions in Datastructure
     */
    void printJunctions();

    /**
     * Loads junctions.csv into DataStructure
     *
     * @param path Path to junctions.csv
     */
    default void loadData(String path) {
        try (Scanner s = new Scanner(new File(System.getProperty("user.dir") + path), "UTF-8")) {
            s.useDelimiter("[;\n]");
            while (s.hasNext()) {

                String value = s.next();
                double x = Double.valueOf(s.next());
                double y = Double.valueOf(s.next());
                String type = s.next();

                JunctionNode element = new JunctionNode(new Point2D.Double(x, y), type, value);
                this.add(element);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Error");
            System.exit(1);
        }
    }
}