/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ArrayListDataStructure implements DataStructure {
    public String type;
    private ArrayList<Node> junctions = new ArrayList<>();

    public ArrayListDataStructure(String pathtofile) {
        this.type = "ArrayList";
        this.loadData(pathtofile);
    }

    @Override
    public Boolean add(Node element) {
        return this.junctions.add(element);
    }

    @Override
    public int[] inRange(Point2D.Double coords, double radius) {
        int[] inRange = new int[2];
        for (Node element : this.junctions) {
            if (Math.sqrt(Math.pow((coords.x - element.getPos().x), 2) + Math.pow((coords.y - element.getPos().y), 2)) <= radius) {
                switch (element.getType()) {
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

    @Override
    public int AwTinRange(double r, int n) {
        int ret = 0;
        for (Node element : this.junctions) {
            if (element.getType().equals("AIRPORT")) {
                if (this.inRange(element.getPos(), r)[1] >= n) {
                    ret++;
                }
            }
        }
        return ret;
    }

    @Override
    public void printJunctions() {
        for (Node element : this.junctions) {
            System.out.println(element.toString());
        }
    }
}