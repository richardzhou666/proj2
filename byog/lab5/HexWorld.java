package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    private static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void draw(Position p, int x, TETile[][] world, TETile t) {
        for (int i = 0; i < x; i++) {
            world[p.x + i][p.y] = t;
        }
    }

    // Calculate the starting position for each line of hexagon
    private static Position[] position(Position p, int s) {
        Position[] result = new Position[s * 2];
        result[0] = new Position(p.x, p.y);
        // Bottom
        for (int i = 1; i < s; i++) {
            result[i] = new Position(result[i - 1].x - 1, result[i - 1].y + 1);
        }
        // Middle
        result[s] = new Position(result[s-1].x, result[s-1].y + 1);
        // Top
        for (int i = 1; i < s; i++) {
            result[i + s] = new Position(result[i + s - 1].x + 1, result[i + s - 1].y + 1);
        }
        return result;
    }

    // Calculate how many tiles to draw for each line of hexagon
    private static int[] counter(int s) {
        int[] result = new int[s * 2];
        result[0] = s;
        // Bottom
        for (int i = 1; i < s; i++) {
            result[i] = result[i - 1] + 2;
        }
        // Middle
        result[s] = result[s-1];
        // Top
        for (int i = 1; i < s; i++) {
            result[s + i] = result[s + i - 1] - 2;
        }
        return result;
    }

    /** Draw Hexagon Method
     * @param world environment
     * @param p Position of the lowest left corner
     * @param s size of hexagon, definied by length of lowesr side
     * @param t TETile object to be drawn with
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        t = randomTile();
        Position[] positions = position(p, s);
        int[] counter = counter(s);
        for (int i = 0; i < s * 2; i++) {
            draw(positions[i], counter[i], world, t);
        }
    }

    private static TETile randomTile() {
        Random RANDOM = new Random();
        int tileNum = RANDOM.nextInt(8);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.GRASS;
            case 3 -> Tileset.TREE;
            case 4 -> Tileset.UNLOCKED_DOOR;
            case 5 -> Tileset.WATER;
            case 6 -> Tileset.FLOOR;
            case 7 -> Tileset.LOCKED_DOOR;
            default -> Tileset.PLAYER;
        };
    }

    public static Position topRightNeighbor(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y + s);
    }

    public static Position topLeftNeighbor(Position p, int s) {
        return new Position(p.x - 2 * s + 1, p.y + s);
    }

    public static Position[] getLeft(Position p, int s, int count) {
        Position[] result = new Position[count];
        result[0] = topLeftNeighbor(p, s);
        for (int i = 1; i < s; i++) {
            result[i] = topLeftNeighbor(result[i - 1], s);
        }
        return result;
    }

    public static void drawLeft(TETile[][] world, Position p, int s, int count) {
        Position[] index = getLeft(p, s, count);
        for (int i = 0; i < s - 1; i++) {
            drawVertical(world, index[i], s, count - 1);
            count -= 1 ;
        }
    }

    public static Position[] getRight(Position p, int s, int count) {
        Position[] result = new Position[count];
        result[0] = topRightNeighbor(p, s);
        for (int i = 1; i < s; i++) {
            result[i] = topRightNeighbor(result[i - 1], s);
        }
        return result;
    }

    public static void drawRight(TETile[][] world, Position p, int s, int count) {
        Position[] index = getRight(p, s, count);
        for (int i = 0; i < s - 1; i++) {
            drawVertical(world, index[i], s, count - 1);
            count -= 1 ;
        }
    }

    public static void drawVertical(TETile[][] world, Position p, int s, int count) {
        int init = 0;
        for (int i = 0; i < count; i++) {
            addHexagon(world, new Position(p.x, p.y + init), s, Tileset.FLOWER);
            init += 2 * s;
        }
    }

    public static void addBigHexagon(TETile[][] world, Position p, int s){
        int count = 2 * s - 1;
        if (p.y + s * count > HEIGHT) {
            throw new IllegalArgumentException("Height Overflow!");
        }
        if ((2 * s - 1) * s - 1 > WIDTH - p.x) {
            throw new IllegalArgumentException("Width Overflow!");
        }
        if ((2 * s) * (s - 1)  > p.x) {
            throw new IllegalArgumentException("Width Overflow!");
        }
        drawVertical(world, p , s, count);
        drawLeft(world, p, s, count);
        drawRight(world, p, s, count);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        // initialize empty world
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(WIDTH / 2, 0);
        addBigHexagon(world, p, 4);
        ter.renderFrame(world);
    }
}
