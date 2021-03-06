
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Clock;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author victoralonso
 */
public class Board extends JPanel {

    private Snake snake;
    private Food food;
    private Timer snakeTimer;
    private int deltaTime;
    private MyKeyAdapter keyAdapter;
    private ScoreDelegate scoreBoard;
    private Wall wall;
    private int  foodTimerFake;

    /**
     * Creates new form Board
     */
    public Board() {
        super();
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        setFocusable(true);

        snakeTimer = new javax.swing.Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tick();
            }
        }
        );
        myInit();
    }
    public Food getfood(){
        return food;
    }
    public void setScoreDelegate(ScoreDelegate scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Board(ScoreDelegate scoreBoard) {
        this();
        setScoreDelegate(scoreBoard);
    }

    public void tick() {
        if (!snake.move(wall)) {
            gameOver();
        }
        check();
        snake.setTurn(false);
        repaint();
    }

    private void myInit() {
        deltaTime = 200;
        snake = new Snake(getSquareHeight() / 2, getSquareWidth() / 2, 10);
        food = new Food(snake);
        foodTimerFake=0;
        wall=new Wall();
        snakeTimer.start();
       
    }

    //To modify or creatre a diferent board
    public Board(int numRows, int numCols) {
        Util.setRows(numRows);
        Util.setCols(numCols);
    }

    public void check() {
        
        if (snake.eat(food)) {
            scoreBoard.incrementInterface(food.isSpecial() || food.isSpecial2());
            food = new Food(snake);
        } 
        if (foodTimerFake==10){
            foodTimerFake=0;
            wall.addWall(food.getPosition().getRow(),food.getPosition().getRow(),4, snake);
            food = new Food(snake);
        }else{
            foodTimerFake++;
        }
        repaint();
    }

    public void gameOver() {
        snakeTimer.stop();
        System.out.println("GameOver");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food != null) {
            food.paint((Graphics2D) g, getSquareWidth(), getSquareHeight());
        }
        if(wall!=null){
            wall.paint(g, WIDTH, HEIGHT);
        }
    }

    public int getSquareWidth() {
        return getWidth() / Util.getCols();
    }

    public int getSquareHeight() {
        return getHeight() / Util.getRows();
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!snake.getTurn()) {
                snake.setTurn(true);
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_LEFT:
                        if /*(canMove(Directions.LEFT) &&*/ (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if /*(canMove(Directions.LEFT) &&*/ (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if /*(canMove(Directions.LEFT) &&*/ (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if /*(canMove(Directions.LEFT) &&*/ (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                            
                        }
                        break;
                    case KeyEvent.VK_P:
                        System.out.println("here");
                        
                        pause();
                        break;
                }
                repaint();

            }
        }

        public void pause() {
            
            if (snakeTimer.isRunning()) {
                System.out.println("Pause on");
                snakeTimer.stop();
            } else {
                System.out.println("Pause off");
                snakeTimer.start();
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
