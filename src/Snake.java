
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
    public boolean eat(Food food) {
        if (body.get(0).getRow() == food.getPosition().getRow() &&
             body.get(0).getCol() == food.getPosition().getCol() ) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean canMove(int row, int col) {
       if (row < 0 || col < 0 || 
                row >= Util.getRows() || col >= Util.getCols()) {            
            return false;
        } else { 
            if (collidesWithItself()) {
                return false;
            }  else {
                moveTo(row, col);
                return true;
            }
        }
    }
     public boolean isOnSnake(int row, int col) {
        for (Node node : body) {
            if (row == node.getRow() && col == node.getCol()) {
                return true;
            }
        }
        return false;
    }
     
     private void moveTo(int row, int col) {
        body.add(0, new Node(row, col));
        if (remainingNodesToCreate == 0) {
            body.remove(body.size() - 1);
        } else {
            remainingNodesToCreate --;
        }
    }
     
     public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
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
    
    public void move() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch(direction) {
            case UP:
                
                canMove(body.get(0).getRow() - 1, body.get(0).getCol());
                break;

            case DOWN:
               canMove(body.get(0).getRow() + 1, body.get(0).getCol());
                break;
           case LEFT:
               canMove(body.get(0).getRow(), body.get(0).getCol() - 1);
                break;
 
           case RIGHT:
               canMove(body.get(0).getRow(), body.get(0).getCol() + 1);
                break;

        }
       
    }
    private boolean collidesWithItself() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        for (int i = 1; i < body.size() - 1; i++) {
            if (row == body.get(i).getRow() && 
                col == body.get(i).getCol()) {
                return true;
            }
        }
        return false;
    }
    
   
    
    
}
