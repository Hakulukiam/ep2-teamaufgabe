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
    Boolean add(Node input);

    int[] inRange(Point2D.Double coords, double radius);

    int AwTinRange(double r, int n);

    void printJunctions();

    default void loadData(String path) {
        try (Scanner s = new Scanner(new File(System.getProperty("user.dir") + path), "UTF-8")) {
            s.useDelimiter(";|\r\n");
            while (s.hasNext()) {

                String value = s.next();
                double x = Double.valueOf(s.next());
                double y = Double.valueOf(s.next());
                String type = s.next();

                Node element = new Node(new Point2D.Double(x, y), type, value);
                this.add(element);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Error");
            System.exit(1);
        }
    }
}