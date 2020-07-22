/*
    Node.java
    Henrik Berg, 4/20/20
    Abstract class for a set of tiles (also called a state) which is used by the PuzzleBoard class
*/

package puzzleboard;

public class Node {

    // Instance variables
    private int f;
    private int[][] stateTiles;
    private int movedValue;
    private int movedRow;
    private int movedCol;
    private int level;
    private Node parentNode;

    // Node constructor
    public Node(int f, int[][] stateTiles, int movedValue, int movedRow, int movedCol, int level, Node parentNode) {
        this.f = f;
        this.stateTiles = stateTiles;
        this.movedValue = movedValue;
        this.movedRow = movedRow;
        this.movedCol = movedCol;
        this.level = level;
        this.parentNode = parentNode;
    }

    // Get and set methods
    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int[][] getStateTiles() {
        return stateTiles;
    }

    public void setStateTiles(int[][] stateTiles) {
        this.stateTiles = stateTiles;
    }

    public int getMovedValue() {
        return movedValue;
    }

    public void setMovedValue(int movedValue) {
        this.movedValue = movedValue;
    }

    public int getMovedRow() {
        return movedRow;
    }

    public void setMovedRow(int movedRow) {
        this.movedRow = movedRow;
    }

    public int getMovedCol() {
        return movedCol;
    }

    public void setMovedCol(int movedCol) {
        this.movedCol = movedCol;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
}
