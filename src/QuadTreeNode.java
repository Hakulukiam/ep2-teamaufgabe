import java.awt.geom.Point2D;

class QuadTreeNode {
    private Point2D.Double pos;
    private String type;
    private String value;

    public QuadTreeNode(Point2D.Double pos, String type, String value) {
        this.pos = pos;
        this.type = type;
        this.value = value;
    }

    public Point2D.Double getPos() {
        return pos;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "QuadTreeNode{" +
                "x=" + pos.x + ", y=" + pos.y +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
