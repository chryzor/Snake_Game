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



    GamePanel(){

    }
    public void startGame(){

    }
    public void paintComponent(Graphics g){

    }
    public void draw(Graphics g){

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
