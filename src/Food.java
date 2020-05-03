
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
public class Food extends Node{
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
        if (specialProb==2){
            isSpecial=true;
        }
        position.setRow(row);
        position.setCol(col);
        
    }
    
    public void paint(Graphics2D g, int squareWidth, int squareHeight) {
       Util.drawSquare(g, squareWidth, squareHeight, position.getRow(), position.getCol(), Color.red);
    }
    
    // Create all the methods you need here
}
