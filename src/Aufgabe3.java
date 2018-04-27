/**
 * Test ArrayList
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe3 {
    public static void main(String[] args){
        //Change only these Variables
        String path = "/data/junctions.csv";
        int testsize = 100;
        int test = 0;
        //vars End

        long start;
        long ALtime;
        long QTtime;
        ArrayListDataStructure ALdata = new ArrayListDataStructure(path);
        QuadTreeDataStructure QTdata = new QuadTreeDataStructure(path);

        for(int i = 1; i <= testsize; i++) {
            start = System.nanoTime();
            new Umkreissuche(ALdata, test);
            ALtime = System.nanoTime() - start;

            start = System.nanoTime();
            new Umkreissuche(QTdata, test);
            QTtime = System.nanoTime() - start;

            System.out.println("[" + i + "] AL: " + ALtime + " --- QT: " + QTtime);
        }
    }
}