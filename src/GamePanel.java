import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    // The Screen width in the game
    static final int SCREEN_WIDTH = 600;
    // The Screen Height
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    //This is the speed of the game
    static final int DELAY = 75;
    // This array hold of X body parts including the head of the snake
    final int x[] = new int[GAME_UNITS];
    // Hold all Y cordinates for the game
    final int y[] = new int[GAME_UNITS];
    // Inital amounnt of body for the snake
    int bodyParts = 6;
    // To check the amount of apples being eaten
    int applesEaten;
    // X cordinates of where the apples are located
    int appleX;
    // Y cordinates for the y position
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;



    GamePanel(){
        random = new Random();
        // The set of Dimension
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    //How or fast the game will be running in 
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){

        for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        
    }
    g.setColor(Color.red);
    g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    public void move(){

    }
    public void checkApple(){

    }
    public void checkCollisions(){

    }
    public void gameOver(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void KeyPressed(KeyEvent e){

        }
    }
}
