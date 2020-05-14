
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
    private boolean turn;
    private boolean specialMode;
    private int counterSpecialMode=0;

    public Snake(int row, int col, int size) { // Initial position of the head of the snake and number of inital nodes
        turn = false;
        body = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            body.add(new Node(Util.numRows / 2, Util.numCols / 2 - i));
        }
        direction = Direction.RIGHT;
    }

    public boolean eat(Food food) {
        if (body.get(0).getRow() == food.getPosition().getRow()
                && body.get(0).getCol() == food.getPosition().getCol()) {
            if (food.isSpecial()|| food.isSpecial2()) {
                remainingNodesToCreate++;
                if (food.isSpecial2()){
                    specialModeActivated();
                }
            }
              
            remainingNodesToCreate++;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean collidesWithWall(Wall wall) {
        for (Node node : wall.getWall()) {
            if (body.get(0).getRow() == node.getRow()
                    && body.get(0).getCol() == node.getCol()) {

                return true;
            }
        }
        return false;
    }
    
    public void specialModeActivated(){
        specialMode=true;
        counterSpecialMode=0;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean getTurn() {
        return turn;
    }

    public boolean canMove(int row, int col, Wall wall) {
        if (row < 0 || col < 0
                || row >= Util.getRows() || col >= Util.getCols()) {
            return false;
        } else {
            if (!specialMode){
                if (collidesWithItself()) {
                    return false;
                } if (collidesWithWall(wall)){
                    
                }    
            }
            moveTo(row, col);
            return true;
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
            remainingNodesToCreate--;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        if(specialMode){
            specialPaint(g,squareWidth,squareHeight,counterSpecialMode);
            counterSpecialMode++;
            if(counterSpecialMode>=body.size()){
                specialMode= false;
            }
        }
        boolean headColor = false;
        
        for (Node node : body) {
            if (!headColor) {
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.green);

                headColor = true;
            } else {
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.red);
            }
        }
    }

    public void specialPaint(Graphics g, int squareWidth, int squareHeight, int ticks) {
        int nodes = body.size();

        nodes -= ticks;
        for (Node node : body) {
            if (nodes > 0) {
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.YELLOW);
                nodes--;
            } else {
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.red);
            }
        }

    }
    
    public boolean move(Wall wall) {

        switch (direction) {
            case UP:
                if (!canMove(body.get(0).getRow() - 1, body.get(0).getCol(), wall)) {
                    return false;
                }
                return true;

            case DOWN:
                if (!canMove(body.get(0).getRow() + 1, body.get(0).getCol(), wall)) {
                    return false;
                }
                return true;

            case LEFT:
                if (!canMove(body.get(0).getRow(), body.get(0).getCol() - 1, wall)) {
                    return false;
                }
                return true;

            case RIGHT:
                if (!canMove(body.get(0).getRow(), body.get(0).getCol() + 1, wall)) {
                    return false;
                }
                return true;

        }
        return false;

    }

    private boolean collidesWithItself() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        for (int i = 1; i < body.size() - 1; i++) {
            if (row == body.get(i).getRow()
                    && col == body.get(i).getCol()) {
                return true;
            }
        }
        return false;
    }

}
