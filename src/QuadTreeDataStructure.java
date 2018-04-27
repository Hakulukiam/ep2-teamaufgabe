/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

public class QuadTreeDataStructure implements DataStructure{
    public String type;
    private QuadTree junctions = new QuadTree();

    public QuadTreeDataStructure(String path) {
        this.type = "QuadTree";
        this.loadData(path);
    }

    @Override
    public Boolean add(String [] input){
        if(input.length == 4){
            this.junctions.add(input);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int[] inRange(double[] coords, double radius) {
        //TODO
        return new int[2];
    }

    @Override
    public int AwTinRange(double r, int n) {
        //TODO
        return 0;
    }

    @Override
    public void printJunctions() {
        //TODO
    }
}