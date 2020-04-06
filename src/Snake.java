
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Snake {
    
    private Direction direction;
    private List<Node> body;
    private int remainingNodesToCreate = 0;
    
    
    public Snake(int row, int col, int size) { // Initial position of the head of the snake and number of inital nodes
        body = new ArrayList<>();
        for(int i=1; i <= size; i++) {
            body.add(new Node((row), (col) - i));
        }
    }
    
    public boolean canMove(int row, int col) {
        // Finish this method
        return true;
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        boolean headColor = false;
        for (Node node: body) {
            if(!headColor) {
                Util.drawSquare(g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.green);
                headColor = true;
            } else {
                 Util.drawSquare(g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.red);
            }
        }
    }
    
    public void move() {
        // Finish this method
    }
    
    
    
}
