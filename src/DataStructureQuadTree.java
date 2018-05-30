import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */
public class DataStructureQuadTree implements DataStructure {
    /**
     * The Type.
     */
    private String type;
    /**
     * The Junctions.
     */
    private QuadTree junctions = new QuadTree(new Point2D.Double(-21000, -15000), new Point2D.Double(21000, 13000));

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
     * Adds JunctionNode to QuadTree
     *
     * @param element JunctionNode
     */
    @Override
    public Boolean add(JunctionNode element) {
        this.junctions.add(element);
        return true;
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Calculates Number of Airports with >=n Trainstations in Range of r
     *
     * @param r Range arround Airport
     * @param n Number of Trainstations
     */
    @Override
    public int AwTinRange(double r, int n) {
        return AwtInRange(r, n, junctions);
    }

    /**
     * Gets Number of Airports and Trainstations in Range
     */
    private int AwtInRange(double r, int n, QuadTree tree) {
        if (tree == null) {
            return 0;
        }

        int ret = 0;

        if (tree.getNode() != null && tree.getNode().getType().equals("AIRPORT")) {
            if (this.inRange(tree.getNode().getPos(), r)[1] >= n) {
                ret++;
            }
        }

        return ret + AwtInRange(r, n, tree.getTopLeftTree())
                + AwtInRange(r, n, tree.getTopRightTree())
                + AwtInRange(r, n, tree.getBotLeftTree())
                + AwtInRange(r, n, tree.getBotRightTree());

    }

    /**
     * Gets Number of Airports and Trainstations in Range radius from Point
     *
     * @param coords point
     * @param radius radius
     */
    @Override
    public int[] inRange(Point2D.Double coords, double radius) {
        int[] inRange = new int[2]; //index 0 for airports //index 1 for trainstations
        QuadTree t = junctions.getInterval(coords, junctions, radius); //get quadtree with the correct boundaries
        inRange(coords, radius, t, inRange);
        return inRange;
    }

    /**
     * inRange Helper for Quadtree
     *
     * @param coords
     * @param radius
     * @param tree
     * @param inRange
     */
    private void inRange(Point2D.Double coords, double radius, QuadTree tree, int[] inRange) {
        if (tree == null) {
            return;
        }

        if (tree.getNode() != null) { //prüft ob der knoten einen wert hat
            if (tree.isInRadius(coords, radius)) { //prüft, ob der knoten in der gegebenen koordinate + radius liegt
                if (tree.getNode().getType().equals("AIRPORT")) {
                    inRange[0]++;
                } else if (tree.getNode().getType().equals("TRAINSTATION")) {
                    inRange[1]++;
                }
            } else {
                return;
            }
        }

        //prüft, welcher knoten besucht werden muss
        inRange(coords, radius, tree.getTopLeftTree(), inRange);
        inRange(coords, radius, tree.getTopRightTree(), inRange);
        inRange(coords, radius, tree.getBotLeftTree(), inRange);
        inRange(coords, radius, tree.getBotRightTree(), inRange);
    }

    /**
     * Prints Junctions in Datastructure
     */
    @Override
    public void printJunctions() {
        junctions.traverse(new ArrayList<>(), junctions);
    }


}