/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.awt.geom.Point2D;

public class JunctionNode {
    private Point2D.Double pos;
    private String type;
    private String value;

    /**
     * Creates New Node
     *
     * @param pos   Coordinates of Juntion
     * @param type  Junction Type (AIRPORT/TRAINSTATION)
     * @param value Name of Junction
     */
    public JunctionNode(Point2D.Double pos, String type, String value) {
        this.pos = pos;
        this.type = type;
        this.value = value;
    }

    /**
     * Getter for Position
     *
     * @return 2DPoint of Junction Coordinates
     */
    public Point2D.Double getPos() {
        return pos;
    }

    /**
     * Gets X Coordinate of Junction
     *
     * @return
     */
    public double getX() {
        return pos.x;
    }

    /**
     * Gets Y Coordinate of Junction
     *
     * @return
     */
    public double getY() {
        return pos.y;
    }

    /**
     * Getter for Junction Type
     *
     * @return AIRPORT/TRAINSTATION
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for Junction Name
     *
     * @return Name
     */
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
