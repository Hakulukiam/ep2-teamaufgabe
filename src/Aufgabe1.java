/**
 * Test ArrayList
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe1 {
    public static void main(String[] args) {
        String path = "/data/junctions.csv";

        DataStructureArrayList data = new DataStructureArrayList(path);

        System.out.println("Using " + data.type + " as DataStructure.");

        new Umkreissuche(data);
    }
}