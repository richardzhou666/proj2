package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    private static final Font title = new Font("Monaco", Font.BOLD, 50);
    private static final Font medium = new Font("Monaco", Font.BOLD, 35);
    private boolean start;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    public boolean isStart(String input) {
        return input.toCharArray()[0] == 'n';
    }

    public static void startUI() {
        int midWidth = WIDTH / 2;
        int midHeight = HEIGHT / 2;
        int x = 0;
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.setFont(title);
            StdDraw.clear(Color.BLACK);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(midWidth, midHeight + 10, "ZCC'S GAME: 还没想好叫什么");
            StdDraw.setFont(medium);
            StdDraw.text(midWidth, midHeight, "新游戏 New Game (N)");
            StdDraw.text(midWidth, midHeight - 5, "加载游戏 Load Game (L)");
            StdDraw.text(midWidth, midHeight - 10, "退出 Quit (Q)");
            StdDraw.text(midWidth, midHeight - 15, "选择人物 Select Character (C)");
            StdDraw.picture(midWidth + x, midHeight + 25,
                    "C:\\Users\\RichardZhou\\Desktop\\cs61b\\proj2\\byog\\Data\\Pikachu.png",
                    20, 20);
            StdDraw.show();
            StdDraw.pause(150);
            x++;
            if (x > midWidth) x = -midWidth;
        }
    }

    public static void main(String[] args) {
        startUI();
    }
}
