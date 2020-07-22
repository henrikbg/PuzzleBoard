/*
    Tile.java
    Henrik Berg, 4/20/20
    Abstract class representing each square in a puzzle with a value
*/

package puzzleboard;

public class Tile {
    
    // Instance variables
    private int value;
    private int row;
    private int col;

    // Tile constructor
    public Tile(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    // Get and set methods
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
}
