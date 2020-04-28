
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.green);
                headColor = true;
            } else {
                 Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getCol(),node.getRow() , Color.red);
            }
        }
    }
    //Add  a canMove.There we will input the coords(row+1 or similar )if is true:call the method 
    public void move() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch(direction) {
            case UP:
                //here if(canMove(row-1))
                moveUp();
                break;

            case DOWN:
                moveDown();
                break;
           case LEFT:
                moveLeft();
                break;
 
           case RIGHT:
                moveRight();
                break;

        }
       
    }
    //remove this methods and create a method that move using the cords easyest ,and clean
    public void moveDown() {
        body.add(0, new Node(body.get(0).getRow() + 1, body.get(0).getCol()));
        body.remove(body.size() - 1);
    }
    public void moveRight() {
        body.add(0, new Node(body.get(0).getRow(), body.get(0).getCol() + 1));
        body.remove(body.size() - 1);
    }
    public void moveUp() {
        body.add(0, new Node(body.get(0).getRow() - 1, body.get(0).getCol()));
        body.remove(body.size() - 1);
    }
     public void moveLeft() {
        body.add(0, new Node(body.get(0).getRow(), body.get(0).getCol() - 1));
        body.remove(body.size() - 1);
    }
    
    
}
