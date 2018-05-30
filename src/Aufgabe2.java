/**
 * Test QuadTree
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe2 {
    public static void main(String[] args) {
        String path = "/data/junctions.csv";

        DataStructureQuadTree data = new DataStructureQuadTree(path);
        System.out.println("Using " + data.getType() + " as DataStructure.");

        new Umkreissuche(data);
    }
}