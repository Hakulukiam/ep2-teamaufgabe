/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DataStructureArrayList implements DataStructure {
    public String type;
    private ArrayList<QuadTreeNode> junctions = new ArrayList<>();

    public DataStructureArrayList(String pathtofile) {
        this.type = "ArrayList";
        this.loadData(pathtofile);
    }

    @Override
    public Boolean add(QuadTreeNode element) {
        return this.junctions.add(element);
    }

    /**
     * Gets Number of Airports and Trainstations in Range radius from Point
     *
     * @param coords point
     * @param radius radius
     */
    @Override
    public int[] inRange(Point2D.Double coords, double radius) {
        int[] inRange = new int[2];
        for (QuadTreeNode element : this.junctions) {
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

    /**
     * Calculates Number of Airports with >=n Trainstations in Range of r
     *
     * @param r Range arround Airport
     * @param n Number of Trainstations
     */
    @Override
    public int AwTinRange(double r, int n) {
        int ret = 0;
        for (QuadTreeNode element : this.junctions) {
            if (element.getType().equals("AIRPORT")) {
                if (this.inRange(element.getPos(), r)[1] >= n) {
                    ret++;
                }
            }
        }
        return ret;
    }

    /**
     * Prints Junctions in Datastructure
     */
    @Override
    public void printJunctions() {
        for (QuadTreeNode element : this.junctions) {
            System.out.println(element.toString());
        }
    }
}