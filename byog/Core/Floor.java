package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * draw Floor between position1(house1) and position2(house2)
 */
public class Floor {
    static int k;
    static TETile floor = Tileset.FLOOR;
    static TETile wall = Tileset.WALL;

    //draw X direction
    static void drawFloorX(Position p1, Position p2, TETile[][] world) {
        int x2 = java.lang.Math.max(p1.x, p2.x);
        int x1 = p1.x + p2.x - x2;
        Wall.surroundedWall(p1, world);
        Wall.surroundedWall(p2, world);
        for (int i = x1; i <= x2; i += 1) {
            if (world[i][p1.y] == floor) {
                k = 1;
            } else {
                world[i][p1.y] = floor;
            }
            if (world[i][p1.y - 1] == floor) {
                k = 1;
            } else {
                world[i][p1.y - 1] = wall;
            }
            if (world[i][p1.y + 1] == floor) {
                k = 1;
            } else {
                world[i][p1.y + 1] = wall;
            }
        }
        p1.x = p2.x;
    }

    //draw y direction
    static void drawFloorY(Position p1, Position p2, TETile[][] world) {
        int y2 = java.lang.Math.max(p1.y, p2.y);
        int y1 = p1.y + p2.y - y2;
        Wall.surroundedWall(p1, world);
        Wall.surroundedWall(p2, world);
        for (int i = y1; i <= y2; i += 1) {
            if (world[p1.x][i] == floor) {
                k = 1;
            } else {
                world[p1.x][i] = floor;
            }
            if (world[p1.x - 1][i] == floor) {
                k = 1;
            } else {
                world[p1.x - 1][i] = wall;
            }
            if (world[p1.x + 1][i] == floor) {
                k = 1;
            } else {
                world[p1.x + 1][i] = wall;
            }
        }
        p1.y = p2.y;
    }

}
