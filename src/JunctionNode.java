import java.awt.geom.Point2D;

class JunctionNode {
    private Point2D.Double pos;
    private String type;
    private String value;

    public JunctionNode(Point2D.Double pos, String type, String value) {
        this.pos = pos;
        this.type = type;
        this.value = value;
    }

    public Point2D.Double getPos() {
        return pos;
    }

    public double getX( ) {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JunctionNode{" +
                "x=" + pos.x + ", y=" + pos.y +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
