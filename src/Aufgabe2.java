/**
 * Test QuadTree
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe2 {
    public static void main(String[] args){
        String path = "/data/junctions.csv";

        QuadTreeDataStructure data = new QuadTreeDataStructure(path);

        System.out.println("Using "+data.type+" as DataStructure.");

        new Umkreissuche(data);
    }
}