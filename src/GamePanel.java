import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Random;

// GamePanel is the core panel for the snake game and handles all game logic, rendering, and events
public class GamePanel extends JPanel implements ActionListener {
    // Width of the game screen
    static final int SCREEN_WIDTH = 600;
    // Height of the game screen
    static final int SCREEN_HEIGHT = 600;
    // Size of each unit (block) in the game grid
    static final int UNIT_SIZE = 25;
    // Total number of units that can fit in the game screen
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    // Delay determines the game speed (lower value = faster game)
    static final int DELAY = 75;

    // Arrays to store the X and Y positions of the snake's body
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    // Initial number of body parts for the snake
    int bodyParts = 6;
    // Counter for the number of apples eaten
    int applesEaten;
    // Coordinates of the apple (x, y)
    int appleX;
    int appleY;
    // Initial direction of the snake ('R' = right)
    char direction = 'R';
    // Indicates whether the game is running
    boolean running = false;
    // Timer to control the game loop (e.g., movement and updates)
    Timer timer;
    // Random object to generate random positions for apples
    Random random;

    // Constructor initializes the game panel and starts the game
    GamePanel() {
        random = new Random();
        // Set the dimensions of the panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // Set the background color to black
        this.setBackground(Color.black);
        // Enable keyboard input for the game
        this.setFocusable(true);
        // Add a custom key listener to handle player input
        this.addKeyListener(new MyKeyAdapter());
        // Start the game
        startGame();
    }

    // Start the game by spawning an apple, setting the game to running, and starting the timer
    public void startGame() {
        newApple(); // Create the first apple
        running = true; // Set the game to running
        timer = new Timer(DELAY, this); // Create a timer to trigger updates
        timer.start(); // Start the timer
    }

    // Handles the rendering of the game panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the parent class's paint method
        draw(g); // Draw the game elements
    }

    // Draws the grid, the apple, and (in future) the snake and other elements
    public void draw(Graphics g) {
        if (running) {
            // Draw the grid for visual alignment
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); // Vertical lines
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE); // Horizontal lines
            }
            // Draw the apple as a red circle
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) { // Snake head
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else { // Snake body
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // Display the score
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(
                    "Score: " + applesEaten, // Text to display
                    (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, // Center text horizontally
                    g.getFont().getSize() // Vertical position (top of the screen)
            );
        } else {
            gameOver(g); // Call the game over method if the game is not running
        }
    }

    // Generate a new apple at a random position within the grid
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    // Move the snake by updating the coordinates of its body parts
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                ;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
        }
    }

    // Check if the snake has eaten the apple
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    // Check for collisions with walls, the snake's own body, or other obstacles
    public void checkCollisions() {
        //checks if head collides with body
        for (int i = 0; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left boarder
        if (x[0] < 0) {
            running = false;
        }
        // Check to see if head touches right boarder
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // Check to see if head touches top boarder
        if (y[0] < 0) {
            running = false;
        }
        // check if head touches bottom boarder
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (running) {
            timer.stop();
        }

    }

    // Display the game over screen or message
    public void gameOver(Graphics g) {
        // Display the score
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(
                "Score: " + applesEaten, // Text to display
                (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, // Center text horizontally
                g.getFont().getSize() // Vertical position (top of the screen)
        );
        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        FontMetrics metrics1 = g.getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/ 2);
    }

    // Handles the game loop (called by the Timer)
    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    // Custom key adapter to handle player keyboard input
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
