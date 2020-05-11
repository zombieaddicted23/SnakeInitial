
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victoralonso
 */
public class Food {
    private Node position;
    private boolean isSpecial;
    
    public Food(Snake snake) {
        
        int specialProb=(int) (Math.random() * 5);
        int row = (int) (Math.random() * Util.getRows());
        int col = (int) (Math.random() * Util.getCols());
        
        while(snake.isOnSnake(row, col)){
            row = (int) (Math.random() * Util.getRows());
            col = (int) (Math.random() * Util.getCols());
        } 
        position= new Node(row, col);
        if (specialProb==2){
            isSpecial=true;
        }

    }
    public Node getPosition(){
        return position;
    }
    public boolean isSpecial(){
        return isSpecial;
    }
    
    
    public void paint(Graphics2D g, int squareWidth, int squareHeight) {
       Util.drawSquare(g, squareWidth, squareHeight, position.getRow(), position.getCol(), Color.YELLOW);
    }
    
    // Create all the methods you need here
}
