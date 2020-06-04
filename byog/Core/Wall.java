package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Wall {
    static int k;
    static TETile floor = Tileset.FLOOR;
    static TETile wall = Tileset.WALL;

    // surround a position with wall. 3 * 3;
    static void surroundedWall(Position p, TETile[][] world) {
        for (int i = p.x - 1; i <= p.x + 1; i += 1) {
            for (int j = p.y - 1; j <= p.y + 1; j += 1) {
                if (world[i][j] == floor) {
                    k = 1;
                } else {
                    world[i][j] = wall;
                }
            }
        }
    }
}