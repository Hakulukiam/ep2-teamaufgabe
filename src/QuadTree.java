/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.awt.geom.Point2D;
import java.util.List;

/**
 * The type Quad tree.
 */
public class QuadTree {
    private Point2D.Double topLeft, botRight; //grenzen dieses knotens
    private JunctionNode n;

    private QuadTree topLeftTree;
    private QuadTree topRightTree;
    private QuadTree botLeftTree;
    private QuadTree botRightTree;

    /**
     * Instantiates a new Quad tree.
     *
     * @param topLeft  the top left
     * @param botRight the bot right
     */
    public QuadTree(Point2D.Double topLeft, Point2D.Double botRight) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        this.n = null;
        this.topLeftTree = null;
        this.topRightTree = null;
        this.botLeftTree = null;
        this.botRightTree = null;
    }

    /**
     * Adds JunctionNode to QuadTree
     *
     * @param junctionNode the junctionNode
     */
    public void add(JunctionNode junctionNode) {
        if (junctionNode == null) return;
        if (!inBoundary(junctionNode.getPos())) return;

        //cannot be splitted more
        if (Math.abs(topLeft.x - botRight.x) <= 0.0001 && Math.abs(topLeft.y - botRight.y) <= 0.0001) {
            if (this.n == null) {
                this.n = junctionNode;
            }
            return;
        }

        //find the correct sub QuadTree for the junctionNode
        if (Double.compare((topLeft.x + botRight.x) / 2, junctionNode.getX()) >= 0) { //horziontal mid
            if (Double.compare((topLeft.y + botRight.y) / 2, junctionNode.getY()) >= 0) { //vertical mid
                if (topLeftTree == null) {
                    topLeftTree = new QuadTree(
                            new Point2D.Double(topLeft.x, topLeft.y),
                            new Point2D.Double((topLeft.x + botRight.x) / 2, (topLeft.y + botRight.y) / 2));
                }
                topLeftTree.add(junctionNode);
            } else {
                if (botLeftTree == null) {
                    botLeftTree = new QuadTree(
                            new Point2D.Double(topLeft.x, (topLeft.y + botRight.y) / 2),
                            new Point2D.Double((topLeft.x + botRight.x) / 2, botRight.y));
                }
                botLeftTree.add(junctionNode);
            }
        } else {
            if (Double.compare((topLeft.y + botRight.y) / 2, junctionNode.getY()) >= 0) { //vertical mid
                if (topRightTree == null) {
                    topRightTree = new QuadTree(
                            new Point2D.Double((topLeft.x + botRight.x) / 2, topLeft.y),
                            new Point2D.Double(botRight.x, (topLeft.y + botRight.y) / 2));
                }
                topRightTree.add(junctionNode);
            } else {
                if (botRightTree == null) {
                    botRightTree = new QuadTree(
                            new Point2D.Double((topLeft.x + botRight.x) / 2, (topLeft.y + botRight.y) / 2),
                            new Point2D.Double(botRight.x, botRight.y));
                }
                botRightTree.add(junctionNode);
            }
        }
    }

    /**
     * checks if point is in QuadTree boundary
     *
     * @param p the p
     * @return the boolean
     */
    public boolean inBoundary(Point2D.Double p) {
        return inBoundary(p, this, 0);
    }

    /**
     * checks if point is in QuadTree boundary with radius
     *
     * @param p      the p
     * @param qt     the qt
     * @param radius the radius
     * @return the boolean
     */
    public boolean inBoundary(Point2D.Double p, QuadTree qt, double radius) {
        return (Double.compare(p.x - radius, qt.topLeft.x) >= 0 &&
                Double.compare(p.x + radius, qt.botRight.x) <= 0 &&
                Double.compare(p.y - radius, qt.topLeft.y) >= 0 &&
                Double.compare(p.y + radius, qt.botRight.y) <= 0);
        }

    /**
     * Gets node.
     *
     * @return the node
     */
    public JunctionNode getNode() {
        return n;
    }

    /**
     * Gets top left tree.
     *
     * @return the top left tree
     */
    public QuadTree getTopLeftTree() {
        return topLeftTree;
    }

    /**
     * Gets top right tree.
     *
     * @return the top right tree
     */
    public QuadTree getTopRightTree() {
        return topRightTree;
    }

    /**
     * Gets bot left tree.
     *
     * @return the bot left tree
     */
    public QuadTree getBotLeftTree() {
        return botLeftTree;
    }

    /**
     * Gets bot right tree.
     *
     * @return the bot right tree
     */
    public QuadTree getBotRightTree() {
        return botRightTree;
    }

    /**
     * checks if the point is in radius of current quadtree
     *
     */
    public boolean isInRadius(Point2D.Double p, double radius) {
        if (Math.sqrt(Math.pow((p.x - this.getNode().getX()), 2) + Math.pow((p.y - this.getNode().getY()), 2)) <= radius) {
            return true;
        }

        return false;
    }

    /**
     * Traverse the tree
     *
     * @param found the found
     * @param tree  the tree
     */
    public void traverse(List<JunctionNode> found, QuadTree tree) {
        if (tree == null) {
            return;
        }

        if (tree.getNode() != null) {
            found.add(tree.getNode());
        }

        traverse(found, tree.getTopLeftTree());
        traverse(found, tree.getTopRightTree());
        traverse(found, tree.getBotLeftTree());
        traverse(found, tree.getBotRightTree());
    }

    /**
     * Gets correct interval where the given point is located
     * recursive call in four directions
     *
     * @param p      the p
     * @param qt     the qt
     * @param radius the radius
     * @return the interval
     */
    public QuadTree getInterval(Point2D.Double p, QuadTree qt, double radius) {
        if(qt == null) { //abbruchbedingung
            return null;
        }

        QuadTree topLeft = qt.topLeftTree;
        QuadTree topRight = qt.topRightTree;
        QuadTree botLeft = qt.botLeftTree;
        QuadTree botRight = qt.botRightTree;

        //durchsucht alle kindknoten des quadtree und wenn keiner passt wird der aktuelle zurückgegeben
        if (topLeft != null && inBoundary(p, topLeft, radius)) {
            return getInterval(p, topLeft, radius);
        } else if (topRight != null && inBoundary(p, topRight, radius)) {
            return getInterval(p, topRight, radius);
        } else if (botLeft != null && inBoundary(p, botLeft, radius)) {
            return getInterval(p, botLeft, radius);
        } else if (botRight != null && inBoundary(p, botRight, radius)) {
            return getInterval(p, botRight, radius);
        } else {
            return qt;
        }

    }

}


