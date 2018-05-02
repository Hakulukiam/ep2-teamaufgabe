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
        if (Math.abs(topLeft.x - botRight.x) <= 1 && Math.abs(topLeft.y - botRight.y) <= 1) {
            if (this.n == null) {
                this.n = junctionNode;
            }
            return;
        }

        //find the correct sub QuadTree for the junctionNode
        if ((topLeft.x + botRight.x) / 2 >= junctionNode.getPos().x) {
            if ((topLeft.y + botRight.y) / 2 >= junctionNode.getPos().y) {
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
            if ((topLeft.y + botRight.y) / 2 >= junctionNode.getPos().y) {
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
        return (p.x >= topLeft.x &&
                p.x <= botRight.x &&
                p.y >= topLeft.y &&
                p.y <= botRight.y);
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
        return (p.x - radius >= qt.topLeft.x &&
                p.x + radius <= qt.botRight.x &&
                p.y - radius >= qt.topLeft.y &&
                p.y + radius <= qt.botRight.y);
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
     * Traverse the tree
     *
     * @param found  the found
     * @param tree   the tree
     * @param radius the radius
     * @param p      the p
     */
    public void traverse(List<JunctionNode> found, QuadTree tree, double radius, Point2D.Double p) {
        if (tree == null) {
            return;
        }

        if (tree.getNode() != null) {
            if (tree.getNode().getPos().x >= p.x - radius && tree.getNode().getPos().x <= p.x + radius && tree.getNode().getPos().y <= p.y + radius && tree.getNode().getPos().y >= p.y - radius) {
                found.add(tree.getNode());
            }
        }

        traverse(found, tree.getTopLeftTree(), radius, p);
        traverse(found, tree.getTopRightTree(), radius, p);
        traverse(found, tree.getBotLeftTree(), radius, p);
        traverse(found, tree.getBotRightTree(), radius, p);
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
            System.out.println(tree.getNode().toString());
        }

        traverse(found, tree.getTopLeftTree());
        traverse(found, tree.getTopRightTree());
        traverse(found, tree.getBotLeftTree());
        traverse(found, tree.getBotRightTree());
    }

    /**
     * Gets correct interval with given point and interval
     *
     * @param p      the p
     * @param qt     the qt
     * @param radius the radius
     * @return the interval
     */
    public QuadTree getInterval(Point2D.Double p, QuadTree qt, double radius) {
        QuadTree topLeft = qt.topLeftTree;
        QuadTree topRight = qt.topRightTree;
        QuadTree botLeft = qt.botLeftTree;
        QuadTree botRight = qt.botRightTree;

        if (inBoundary(p, topLeft, radius)) {
            return getInterval(p, topLeft, radius);
        } else if (inBoundary(p, topRight, radius)) {
            return getInterval(p, topRight, radius);
        } else if (inBoundary(p, botLeft, radius)) {
            return getInterval(p, botLeft, radius);
        } else if (inBoundary(p, botRight, radius)) {
            return getInterval(p, botRight, radius);
        } else {
            return qt;
        }

    }

}


