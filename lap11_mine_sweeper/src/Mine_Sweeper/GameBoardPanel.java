package Mine_Sweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Mine_Sweeper.MineSweeperConstants.ROWS;
import static Mine_Sweeper.MineSweeperConstants.COLS;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60; // Cell width and height, in pixels
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS; // Game board width/height
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

    // Define properties (package-visible)
    /** The game board composes of ROWSxCOLS cells */
    Cell cells[][] = new Cell[ROWS][COLS];
    /** Number of mines */
    int numMines = 10;

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(ROWS, COLS, 2, 2)); // JPanel
        
        // Allocate the 2D array of Cell, and added into content-pane.
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }

        // [TODO 3] Allocate a common listener as the MouseEvent listener for all the
        // Cells (JButtons)
        // .........
     // Allocate a common listener for all cells
        
        CellMouseListener listener = new CellMouseListener();
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].addMouseListener(listener);
            }
        }
       
     
        // [TODO 4] Every cell adds this common listener
        
        // .........

        // Set the size of the content-pane and pack all the components
        // under this container.
        super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    // Initialize and re-initialize a new game
    public void newGame() {
        // Get a new mine map
        MineMap mineMap = new MineMap();
        mineMap.newMineMap(numMines);

        // Reset cells, mines, and flags
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Initialize each cell with/without mine
            	cells[row][col].board = this;
                cells[row][col].newGame(mineMap.isMined[row][col]);
                
            }
        }
        ///num mine near
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Initialize each cell with/without mine
                cells[row][col].has_mine_near = getSurroundingMines(row, col);
                
            }
        }
    }

    // Return the number of mines [0, 8] in the 8 neighboring cells
    // of the given cell at (srcRow, srcCol).
    public int getSurroundingMines(int srcRow, int srcCol) {
        int numMines = 0;
        for (int row = srcRow - 1; row <= srcRow + 1; row++) {
            for (int col = srcCol - 1; col <= srcCol + 1; col++) {
                // Need to ensure valid row and column numbers too
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                    if (cells[row][col].isMined)
                        numMines++;
                }
            }
        }
        return numMines;
    }

    // Reveal the cell at (srcRow, srcCol)
    // If this cell has 0 mines, reveal the 8 neighboring cells recursively
    private void revealCell(int srcRow, int srcCol) {
        int numMines = getSurroundingMines(srcRow, srcCol);
        cells[srcRow][srcCol].setText(numMines + "");
        cells[srcRow][srcCol].isRevealed = true;
        cells[srcRow][srcCol].paint(); // based on isRevealed
        if (numMines == 0) {
            // Recursively reveal the 8 neighboring cells
            for (int row = srcRow - 1; row <= srcRow + 1; row++) {
                for (int col = srcCol - 1; col <= srcCol + 1; col++) {
                    // Need to ensure valid row and column numbers too
                    if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                        if (!cells[row][col].isRevealed)
                            revealCell(row, col);
                    }
                }
            }
        }
    }

    // Return true if the player has won (all cells have been revealed or were
    // mined)
    public boolean hasWon() {
        // ......
        return true;
    }

    // [TODO 2] Define a Listener Inner Class
    // .........
}