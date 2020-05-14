
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
 * @author oripa
 */
public class Wall {
  
//First of all i should create a method , isOnWall,isOnFood(like isOnSnake). Not that code but i work alot trying to find good code so i want to let it here
    private List<Node> wall;
    
    public Wall(){
        wall= new ArrayList<>();
    }
   public List<Node> getWall(){
       return wall;
   }
   
    public void createWall(int row, int col, int size, Direction direction) {
        switch (direction) {
            case UP:
                for (int i = 1; i <= size; i++) {
                    wall.add(new Node(Util.numRows / 2 - i , Util.numCols / 2 ));
                }
                break;
            case DOWN:
                for (int i = 1; i <= size; i++) {
                    wall.add(new Node(Util.numRows /2 + i, Util.numCols / 2 ));
                }
                break;
            case LEFT:
                for (int i = 1; i <= size; i++) {
                    wall.add(new Node(Util.numRows / 2, Util.numCols / 2 - i));
                }
                break;

            case RIGHT:
                for (int i = 1; i <= size; i++) {
                    wall.add(new Node(Util.numRows / 2, Util.numCols / 2 + i));
                }
                break;
        }

    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
  
        for (Node node : wall) {           
                Util.drawSquare((Graphics2D) g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.BLACK);
        }
    }

    public void addWall(int row, int col,int size, Snake snake/*, Food food*/){
        Direction direction;
        int directionChooser = (int) (Math.random() * 4);
        boolean[] directionBoolean = checkSpaceAroundWall(row, col, size);
        directionBoolean = checkWallPosition(row, col, size, directionBoolean);
       // directionBoolean = checkFoodPosition(row, col, size, food, directionBoolean);
        directionBoolean = checkSnakePosition(row, col, size, snake, directionBoolean);
        if(anyDirection(directionBoolean)){
            while(directionBoolean[directionChooser]){
                directionChooser = (int) (Math.random() * 4);
            }
            createWall(row, col, size, getDirection(directionChooser));
        }else{
            // method generate randomWalls without structure
        }
       
    }
    public Direction getDirection(int num) {
        if (num == 0) {
            return Direction.LEFT;
        }
        if (num == 1) {
            return Direction.RIGHT;
        }
        if (num == 2) {
            return Direction.UP;
        }       
            return Direction.DOWN;
    }
    private boolean[] checkWallPosition(int row, int col, int size,boolean[] direction) {
        
        for (Node node : wall) {
            int nodeRow = node.getRow();
            int nodeCol = node.getCol();
            //can left0 and right1
            if (row < nodeRow) {
                if (direction[1]) {
                    direction[1] = canDirection(row, col, size, nodeRow, nodeCol, true);
                }
            } else {
                if (direction[0]) {
                    direction[0] = canDirection(row, col, size, nodeRow, nodeCol, true);
                }
            }
             //can up2 and down3
            if (col < nodeCol) {
                if (direction[3]) {
                    direction[3] = canDirection(row, col, size, nodeRow, nodeCol, false);
                }

            } else {
                if (direction[2]) {
                    direction[2] = canDirection(row, col, size, nodeRow, nodeCol, false);
                }
            }
        }
        return direction;
    }
    private boolean[] checkSnakePosition(int row, int col, int size,Snake snake,boolean[] direction){
        for (int i = 0; i < direction.length; i++) {
             for (int j = 0; j < size; j++) {
                 if(direction[i]){
                     if(i ==0)
                     direction[i]=!snake.isOnSnake(row-j, col);
                     if(i == 1)
                     direction[i]=!snake.isOnSnake(row+j, col);
                     if(i == 2)
                     direction[i]=!snake.isOnSnake(row, col-j);
                     if(i == 3)
                     direction[i]=!snake.isOnSnake(row, col+j);
                     
                 }
             }
        }
        return direction;
        
    }

    private boolean[] checkFoodPosition(int row, int col, int size, Food food,boolean[] direction) {
        
        int foodRow = food.getPosition().getRow();
        int foodCol = food.getPosition().getCol();
        //can left0 and right1
       if (row < foodRow) {
                if (direction[1]) {
                    direction[1] = canDirection(row, col, size, foodRow, foodCol, true);
                }
            } else {
                if (direction[0]) {
                    direction[0] = canDirection(row, col, size, foodRow, foodCol, true);
                }
            }
             //can up2 and down3
            if (col < foodCol) {
                if (direction[3]) {
                    direction[3] = canDirection(row, col, size, foodRow, foodCol, false);
                }

            } else {
                if (direction[2]) {
                    direction[2] = canDirection(row, col, size, foodRow, foodCol, false);
                }
            }
        return direction;
    }

    private boolean[] checkSpaceAroundWall(int row, int col, int size) {
        boolean[] direction = new boolean[4];
        //can left
        direction[0] = colision(row, size, 0);
        //can right
        direction[1] = colision(row, size, Util.getRows());
        //can up
        direction[2] = colision(col, size, 0);
        //can down
        direction[3] = colision(col, size, Util.getCols());

        return direction;
    }

    public boolean canDirection(int row, int col, int size, int colisionRow, int colisionCol, boolean isRow) {
        if (isRow) {
            if (col == colisionCol) {
                return colision(row, size, colisionRow);
            }
            return true;
        } else {
            if (row == colisionRow) {
                return colision(col, size, colisionCol);
            }
            return true;
        }
    }

    
    private boolean colision(int site, int size, int colision) {
        boolean counter = false;
        for (int i = 1; i < size; i++) {
            if (site > colision) {
                if (site - size > colision) {
                    counter = true;
                } else {
                    counter = false;
                }
            } else {
                if (site - size > colision) {
                    counter = true;
                } else {
                    counter = false;
                }
            }
            if (!counter) {
                return counter;
            }

        }
        return counter;
    }
    private boolean anyDirection(boolean[] direction){
        boolean anyDirection=false;
        for (int i = 0; i < direction.length; i++) {
            if(direction[i])
                anyDirection = true;
        }
        return anyDirection;
    }

}
