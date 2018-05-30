/**
 * Test ArrayList
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class Aufgabe3 {
    public static void main(String[] args) {
        //Change only these Variables
        String path = "/data/junctions.csv";
        int testsize = 1000;
        int test = 0;
        //vars End

        long start;
        long ALtime;
        long QTtime;
        double ALTotal = 0.0;
        double QTTotal = 0.0;

        DataStructureArrayList ALdata = new DataStructureArrayList(path);
        DataStructureQuadTree QTdata = new DataStructureQuadTree(path);

        for (int i = 1; i <= testsize; i++) {
            start = System.nanoTime();
            new Umkreissuche(ALdata, test);
            ALtime = System.nanoTime() - start;
            ALTotal += ALtime / testsize;

            start = System.nanoTime();
            new Umkreissuche(QTdata, test);
            QTtime = System.nanoTime() - start;
            QTTotal += QTtime / testsize;

            System.out.println("[" + i + "] AL: " + ALtime + " --- QT: " + QTtime);
        }

        System.out.println("Total Times:");
        System.out.println("AL: " + ALTotal + " QT: " + QTTotal);
        double difference = ((ALTotal / QTTotal) * 100) - 100;
        System.out.println("The Implementations show a difference of " + String.format("%.2f", difference) + "%");
    }
}