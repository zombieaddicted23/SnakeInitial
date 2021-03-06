
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Util {

    public static int numRows = 30;
    public static int numCols = 40;
    public static int score = 0;

    public static void setRows(int rows) {
        numRows = rows;
    }

    public static void setCols(int cols) {
        numCols = cols;
    }

    public static int getRows() {
        return numRows;
    }

    public static int getCols() {
        return numCols;
    }

    public static void drawSquare(Graphics2D g, int squareWidth,
            int squareHeight, int row, int col, Color color) {
        int x = col * squareWidth;
        int y = row * squareHeight;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1, x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1, x + squareWidth - 1, y + 1);

    }
     
    
    
}
