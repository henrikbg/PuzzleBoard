/*
    PuzzleBoard.java
    Henrik Berg, 4/20/20
    Main class that uses A* algorithm to solve 8-puzzle given an initial and goal state
*/

package puzzleboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class PuzzleBoard {

    // Instance variables
    public static final int MAX_DIMENSION = 3;
    
    private int[][] initialState = new int[MAX_DIMENSION][MAX_DIMENSION];
    
    private TreeMap<Integer, Tile> goalState = new TreeMap<Integer, Tile>();
    
    private ArrayList<Node> statesVisited = new ArrayList<>();
    private ArrayList<Node> leaves = new ArrayList<>();
    
    private int cost;
    
    private int initialBlankRow = 0;
    private int initialBlankCol = 0;
    
    // Scanner for reading tile input
    Scanner inputReader = new Scanner(System.in);
    
    // PuzzleBoard constructor
    public PuzzleBoard() {
        
        this.cost = 0;
        
        int tileValue = 1;
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                Tile newTile = new Tile(tileValue, row, column);
                goalState.put(newTile.getValue(), newTile);
                
                tileValue++;
            }
            
        }
        
        tileValue = 0;
        Tile newTile = new Tile(tileValue, (MAX_DIMENSION - 1), (MAX_DIMENSION - 1));
        
        goalState.put(newTile.getValue(), newTile);

//        Tile goalTile1 = new Tile(1, 0, 0);
//        Tile goalTile2 = new Tile(2, 0, 1);
//        Tile goalTile3 = new Tile(3, 0, 2);
//        Tile goalTile4 = new Tile(4, 1, 0);
//        Tile goalTile5 = new Tile(5, 1, 1);
//        Tile goalTile6 = new Tile(6, 1, 2);
//        Tile goalTile7 = new Tile(7, 2, 0);
//        Tile goalTile8 = new Tile(8, 2, 1);
//        Tile goalTile9 = new Tile(0, 2, 2);
//        
//        goalState.put(0, goalTile1);
//        goalState.put(1, goalTile2);
//        goalState.put(2, goalTile3);
//        goalState.put(3, goalTile4);
//        goalState.put(4, goalTile5);
//        goalState.put(5, goalTile6);
//        goalState.put(6, goalTile7);
//        goalState.put(7, goalTile8);
//        goalState.put(8, goalTile9);
        
        initialState = readTiles();
//        initialState[0][0] = 8;
//        initialState[0][1] = 3;
//        initialState[0][2] = 2;
//        initialState[1][0] = 4;
//        initialState[1][1] = 7;
//        initialState[1][2] = 1;
//        initialState[2][0] = 0;
//        initialState[2][1] = 5;
//        initialState[2][2] = 6;
        
        System.out.println("");
        System.out.println("------------");
        System.out.println("");
        
        printTiles(initialState, "INITIAL STATE");
        
        System.out.println("");
        System.out.println("------------");
        
        if(!isSolvable(initialState)) {
            
            System.out.println("");
            System.out.println("BOARD IS UNSOLVABLE");
            System.out.println("");
            
            System.exit(0);
        }
        
    }
    
    // Reading input as tiles from user
    public int[][] readTiles() {
        
        int[][] tempTiles = new int[MAX_DIMENSION][MAX_DIMENSION];
        boolean correct = false;
        int counter = 1;
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                correct = false;
                
                while(!correct) {
                    
                    System.out.print("Enter a value for tile " + counter + ": ");
                    
                    try {
                        
                        tempTiles[row][column] = inputReader.nextInt();
                        
                        correct = true;
                        
                        if(tempTiles[row][column] == 0) {
                            initialBlankRow = row;
                            initialBlankCol = column;
                        }
                        
                        counter++;
                        
                    } catch(InputMismatchException e) {
                        
                        System.out.println("");
                        System.out.println("Please only enter integers.");
                        System.out.println("");
                        inputReader.nextLine();
                        
                    }
                }  
            }
        }
        
        return tempTiles;
    }
    
    // Checking if a puzzle is solvable
    public boolean isSolvable(int[][] allTiles) {
        
        int[] linearArray = new int[(MAX_DIMENSION * MAX_DIMENSION) - 1];
        
        boolean status = true;
        
        int index = 0;
        int counter = 0;
        int blankRow = 0;
        
        boolean isEven;
        
        if((MAX_DIMENSION * MAX_DIMENSION) % 2 == 0) {
            isEven = true;
        } else {
            isEven = false;
        }
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                if(allTiles[row][column] != 0) {
                    linearArray[index] = allTiles[row][column];
                    index++;
                } else {
                    blankRow = row;
                }
                
            }
        }
        
        for(int i = 0; i < linearArray.length; i++) {
            
            for(int j = 0; j < i; j++) {
                
                if(linearArray[i] < linearArray[j]) {
                    counter++;
                }
                
            }
        }
        
        if(isEven) {
            
            counter = counter + blankRow;
            
            if(counter % 2 == 0) {
                return false;
            } else {
                return true;
            }
            
        } else {
            
            if(counter % 2 == 0) {
                return true;
            } else {
                return false;
            }
            
        }
    }

    // Transferring an array to another array
    public int[][] transferArray(int[][] targetArray) {
        
        int[][] tempTiles = new int[MAX_DIMENSION][MAX_DIMENSION];
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                tempTiles[row][column] = targetArray[row][column];
            }
        }
        
        return tempTiles;
    }
    
    // Checking if two arrays are equal
    public boolean isArrayEqual(int[][] firstArray, int[][] secondArray) {
        
        boolean status = true;
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                if(firstArray[row][column] != secondArray[row][column]) {
                    
                    status = false;
                    
                    row = MAX_DIMENSION;
                    column = MAX_DIMENSION;
                }
            }
        }
        
        return status;
    }
    
    // Checking if a state is a repeated state
    public boolean checkRepeatState(ArrayList<Node> stateList, int[][] currentState) {
        
        for(Node eachState : stateList) {
            
            if(isArrayEqual(eachState.getStateTiles(), currentState)) {
                return true;
            }
            
        }
        
        return false;
    }
    
//    public int h(int[][] node) {
//        
//        int hScore;
//        
//        for(int row = 0; row < node.length; row++) {
//            
//            for(int column = 0; column < node[row].length; column++) {
//
//                if(node[row][column] != goalState[row][column])
//                    hScore++;
//               
//            }
//    
//        }
//        
//        return hScore;
//        
//    }
    
    // Calculating h-score (otherwise known as manhattan distance)
    public int h(int[][] node) {
        
        int distance = 0;
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                if(node[row][column] != 0) {
                    Tile goalTile = goalState.get(node[row][column]);
                    distance = distance + Math.abs(row - goalTile.getRow()) + Math.abs(column - goalTile.getCol());
                }
                
            }
        }
        
        return distance;
    }
    
    // Calculating g-score (or level)
    public int g(int cost) {
        
        return (cost + 1);
        
    }
    
    // Finding candidates given a certain state
    public ArrayList<Tile> findCandidates(Node node) {
        
        int blankRow = node.getMovedRow();
        int blankCol = node.getMovedCol();
        int repeatValue = node.getMovedValue();
        
        int[][] allTiles = transferArray(node.getStateTiles());
        
        ArrayList<Tile> candidates = new ArrayList<>(4);
        
        if(blankRow - 1 >= 0) {
            
            if(allTiles[blankRow - 1][blankCol] != repeatValue) {
                
                Tile topTile = new Tile(allTiles[blankRow - 1][blankCol], (blankRow - 1), blankCol);
                candidates.add(topTile);
                
            }
            
        }
        if(blankRow + 1 < MAX_DIMENSION) {
            
            if(allTiles[blankRow + 1][blankCol] != repeatValue) {
                
                Tile bottomTile = new Tile(allTiles[blankRow + 1][blankCol], (blankRow + 1), blankCol);
                candidates.add(bottomTile);
                
            }
            
        }
        if(blankCol - 1 >= 0) {
            
            if(allTiles[blankRow][blankCol - 1] != repeatValue) {
                
                Tile leftTile = new Tile(allTiles[blankRow][blankCol - 1], blankRow, (blankCol - 1));
                candidates.add(leftTile);
                
            }
            
        }
        if(blankCol + 1 < MAX_DIMENSION) {
            
            if(allTiles[blankRow][blankCol + 1] != repeatValue) {
                
                Tile rightTile = new Tile(allTiles[blankRow][blankCol + 1], blankRow, (blankCol + 1));
                candidates.add(rightTile);
                
            }
            
        }
        
        return candidates;
    }
    
    // Inserting nodes (states) into an ArrayList in sorted order (based on f-score)
    public void insertSorted(Node node, ArrayList<Node> nodeList) {
        
        boolean found = false;
        
        int i = 0;
        
        while(i < nodeList.size()) {
            
            Node tempState = nodeList.get(i);
            
            if(!found && (node.getF() <= tempState.getF())) {
                nodeList.add(i, node);
                found = true;
            }
            
            if(isArrayEqual(node.getStateTiles(), tempState.getStateTiles())) {
                
                if(node.getF() > tempState.getF()) {
                    i = nodeList.size();
                    found = true;
                } else {
                    nodeList.remove(tempState);
                }
                
            }
            i++;
        }
        
        if(!found) {
            nodeList.add(node);
        }
    }
    
    // Generating nodes based on the candidate list provided by findCandidates()
    public void generateNodes(ArrayList<Tile> candidates, Node parentNode) {
        
        int f;
        
        int[][] allTiles = transferArray(parentNode.getStateTiles());
        
        int blankRow = parentNode.getMovedRow();
        int blankCol = parentNode.getMovedCol();
        int level = parentNode.getLevel();
        
        for(Tile tile : candidates) {
            
            allTiles[tile.getRow()][tile.getCol()] = 0;
            allTiles[blankRow][blankCol] = tile.getValue();
            
            int[][] tempTiles = transferArray(allTiles);
            
            allTiles[tile.getRow()][tile.getCol()] = tile.getValue();
            allTiles[blankRow][blankCol] = 0;
            
            if(!checkRepeatState(statesVisited, tempTiles)) {
                
                f = g(level) + h(tempTiles);
                
                Node newNode = new Node(f, tempTiles, tile.getValue(), tile.getRow(), tile.getCol(), level + 1, parentNode);
                
                insertSorted(newNode, leaves);
            }
        }
    }

    // Finding the direction for a move
    public String findDirection(int currentRow, int currentCol, int nextRow, int nextCol) {
        
        if(currentRow < nextRow) {
            
            return "Up";
            
        } else if(currentRow > nextRow) {
            
            return "Down";
            
        } else if(currentCol < nextCol) {
            
            return "Left";
            
        } else if(currentCol > nextCol) {
            
            return "Right";
            
        }
        
        return "ERROR in findDirection()";
    }
    
    // Checking if the puzzle is done
    public boolean checkPuzzle(int[][] allTiles) {
        
        boolean done = true;
        
        int tileValue = 1;
        
        int maxTiles = (MAX_DIMENSION) * (MAX_DIMENSION);
        
        for(int row = 0; row < MAX_DIMENSION; row++) {
            
            for(int column = 0; column < MAX_DIMENSION; column++) {
                
                if(allTiles[row][column] != tileValue) {
                    done = false;
                    return done;
                }
                
                tileValue = (tileValue + 1) % (maxTiles);
            }
        }
        
        return done;
    }
    
    // Outputting a given state (with message)
    public void printTiles(int[][] givenState, String message) {
        
        System.out.println(message);

        for(int i = 0; i < givenState.length; i++) {
            System.out.println(Arrays.toString(givenState[i]));
        }
        
    }
    
    // Printing out the solution
    public void printSolution(Node finalNode) {
        
        Node tempNode = finalNode;
        
        ArrayList<String> direction = new ArrayList<>();
        
        int currentRow;
        int currentCol;
        int nextRow;
        int nextCol;
        
        while(tempNode != null) {
            
            printTiles(tempNode.getStateTiles(), "");
            
            currentRow = tempNode.getMovedRow();
            currentCol = tempNode.getMovedCol();
            
            tempNode = tempNode.getParentNode();
            
            if(tempNode != null) {
                
                nextRow = tempNode.getMovedRow();
                nextCol = tempNode.getMovedCol();
                
                direction.add(0, findDirection(currentRow, currentCol, nextRow, nextCol));
                
            }
        }
        
        System.out.println("");
        System.out.println("------------");
        System.out.println("");
        System.out.println("* moves shown from bottom to top *");
        System.out.println("");
        
        System.out.println("MOVES (" + direction.size() + ")");
        System.out.println(direction);
    }
    
    // Main a* algorithm
    public void aStar() {
        
        ArrayList<Tile> candidateList;
        
        Node selectedState = null;
        
        boolean goalFound = false;
        
        int f;
        int level = -1;
        
        if(checkPuzzle(initialState)) {
            goalFound = true;
        } else {
            
            int[][] tempTiles = transferArray(initialState);
            
            f = g(level) + h(tempTiles);
            
            Node initialNode = new Node(f, tempTiles, -1, initialBlankRow, initialBlankCol, 0, null);
            
            statesVisited.add(initialNode);
            
            selectedState = initialNode;
            
        }
        while(!goalFound) {
            
            candidateList = findCandidates(selectedState);
            
            generateNodes(candidateList, selectedState);
            
            selectedState = leaves.remove(0);
            
            if(checkPuzzle(selectedState.getStateTiles())) {
                goalFound = true;
            }
            
            statesVisited.add(selectedState);
        }
        
        printSolution(selectedState);
        
        System.out.println("");
        System.out.println("GOAL STATE has been reached.");
        
    }
    
    // Main method to start the program
    public static void main(String[] args) {
        
        PuzzleBoard puzzleObject = new PuzzleBoard();
        puzzleObject.aStar();
        
    }
    
}
