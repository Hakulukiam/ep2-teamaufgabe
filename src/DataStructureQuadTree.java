import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */
public class DataStructureQuadTree implements DataStructure {
    /**
     * The Type.
     */
    public String type;
    /**
     * The Junctions.
     */
    public QuadTree junctions = new QuadTree(new Point2D.Double(-20023.816080, -14666.861162), new Point2D.Double(20032.054293, 12330.232858));

    /**
     * Instantiates a new Quad tree data structure.
     *
     * @param path path
     */
    public DataStructureQuadTree(String path) {
        this.type = "QuadTree";
        this.loadData(path);
    }

    /**
     * Adds QuadTreeNode to QuadTree
     *
     * @param element QuadTreeNode
     */
    @Override
    public Boolean add(QuadTreeNode element) {
        this.junctions.add(element);
        return true;
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
        List<QuadTreeNode> found = new ArrayList<>();
        junctions.traverse(found, junctions);

        for (QuadTreeNode item : found) {
            if (item.getType().equals("AIRPORT")) {
                if (this.inRange(item.getPos(), r)[1] >= n) {
                    ret++;
                }
            }
        }
        return ret;
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
        QuadTree t = junctions.getInterval(coords, junctions, radius);
        List<QuadTreeNode> found = new ArrayList<>();
        junctions.traverse(found, t, radius, coords);

        for (QuadTreeNode item : found) {
            if (item.getType().equals("AIRPORT")) {
                inRange[0]++;
            } else if (item.getType().equals("TRAINSTATION")) {
                inRange[1]++;
            }
        }

        return inRange;
    }

    /**
     * Prints Junctions in Datastructure
     */
    @Override
    public void printJunctions() {
        junctions.traverse(new ArrayList<>(), junctions);
    }


}