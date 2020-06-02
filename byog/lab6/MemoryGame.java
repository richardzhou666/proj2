package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private boolean gg;
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }
        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(50, 50, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        gg = false;
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return result.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        if (!gg) {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(4, height-2, s);
            String phrase = ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)];
            StdDraw.text(width /2, height -2, phrase);
            StdDraw.text(width - 7, height -2, "Richard Zhou");
            StdDraw.line(0, height -3, width, height - 3);
            StdDraw.show();
        } else {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(width / 2, height / 2, s);
            StdDraw.show();
        }
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (char letter: letters.toCharArray()) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(width / 2, height / 2, String.valueOf(letter));
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.setPenColor(Color.black);
            StdDraw.filledCircle(width / 2, height / 2, 2);
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (StdDraw.hasNextKeyTyped()) {
                result.append(StdDraw.nextKeyTyped());
            }
        }
        return result.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int i = 1;
        //TODO: Establish Game loop
        while (true) {
            drawFrame("Round: " + i);
            String target = generateRandomString(i);
            flashSequence(target);
            String input = solicitNCharsInput(i);
            if (target.equals(input)) {
                drawFrame("You made it");
                i ++;
            } else {
                gg = true;
                drawFrame("GG");
                return;
            }
        }
    }
}
