import java.awt.geom.Point2D;

/**
 * Test QuadTree
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe2 {
    public static void main(String[] args) {
        String path = "/data/junctions.csv";

        QuadTreeDataStructure data = new QuadTreeDataStructure(path);
        int[] res = data.inRange(new Point2D.Double(1818.54657, 5813.29982), 100);
        System.out.println("Using " + data.type + " as DataStructure.");

        new Umkreissuche(data);
    }
}